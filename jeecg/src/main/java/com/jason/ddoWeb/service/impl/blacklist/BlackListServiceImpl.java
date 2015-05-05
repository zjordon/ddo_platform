package com.jason.ddoWeb.service.impl.blacklist;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jason.ddoWeb.common.model.ErrorMsg;
import com.jason.ddoWeb.entity.blacklist.BlackListEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.entity.smtask.SmMsisdnListEntity;
import com.jason.ddoWeb.service.blacklist.BlackListServiceI;
import com.jason.ddoWeb.service.common.ErrorMsgServiceI;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("blackListService")
@Transactional
public class BlackListServiceImpl extends CommonServiceImpl implements
		BlackListServiceI {
	@Autowired
	private ErrorMsgServiceI errorMsgService;

	private final static String GET_COUNT = "select count(*) from ddo_black_list where msisdn = ?";
	private final static String GET_STATE = "select state from ddo_black_list where id = ?";

	@Override
	public AjaxJson saveBlackLists(List<BlackListEntity> listBlackList,
			HttpServletRequest request) {

		List<BlackListEntity> actualList = new ArrayList<BlackListEntity>();
		List<ErrorMsg> errorMsgList = new ArrayList<ErrorMsg>();
		long tempCount = 0;
		int idx = 0;
		for (BlackListEntity blackList : listBlackList) {
			idx++;
			// 判断黑名单号码在数据库中是否存在
			tempCount = super.getCountForJdbcParam(GET_COUNT,
					new String[] { blackList.getMsisdn().toString() });
			if (tempCount == 0) {
				// 设置状态为启用(由于jeecg框架的bug，不能把设置默认状态放在BlackListEntity的构造函数当中)
				blackList.setState(new Integer(1));
				actualList.add(blackList);
			} else {
				// 创建错误信息,包括行号，错误信息两个字段
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setNum(idx + 1);
				errorMsg.setMsg("手机号码" + blackList.getMsisdn() + "在系统中已经存在");
				errorMsgList.add(errorMsg);
			}
		}
		if (!actualList.isEmpty()) {
			super.batchSave(actualList);
			// 产生重新加载黑名单事件
			EventEntity event = new EventEntity();
			event.setEventId("ReloadBlackListEvent");
			super.save(event);
		}
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("successNum", actualList.size());
		attributes.put("failNum", errorMsgList.size());
		if (!errorMsgList.isEmpty()) {
			String downloadHref = this.errorMsgService.saveErrorMsg(
					errorMsgList, request, attributes);
			StringBuilder builder = new StringBuilder("导入完成，但有一些数据导入失败，您可以");
			builder.append("<a href=\"").append(downloadHref)
					.append("\">下载错误信息</a>进行查看");
			j.setMsg(builder.toString());

		} else {
			j.setMsg("导入成功");
		}
		return j;
	}

	@Override
	public <T> Serializable save(T entity) {
		Serializable id = super.save(entity);
		// 产生新增黑名单事件
		this.createAddEvent((BlackListEntity) entity);
		return id;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		BlackListEntity blackList = (BlackListEntity) entity;

		// 使用sql语句从数据库中直接获取原来数据的状态(绕过hibernate的一级缓存和二级缓存)
		Map map = super.findOneForJdbc(GET_STATE, blackList.getId());
		Integer state = (Integer) map.get("state");
		if (state != null) {
			// 检测黑名单的状态是滞改变
			if (state.intValue() != blackList.getState().intValue()) {
				// 状态有改变
				if (blackList.getState().intValue() == 0) {
					// 被改成禁用，产生删除黑名单事件
					this.createDeleteEvent(blackList);
				} else {
					// 被改成启用，产生新增黑名单事件
					this.createAddEvent((BlackListEntity) entity);
				}
			}
		}
		super.saveOrUpdate(entity);
	}

	@Override
	public <T> void delete(T entity) {
		this.createDeleteEvent((BlackListEntity) entity);
		super.delete(entity);
	}

	private void createDeleteEvent(BlackListEntity blackList) {
		// 产生删除黑名单事件
		EventEntity event = new EventEntity();
		event.setEventId("DeleteBlackListEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("msisdn:").append(blackList.getMsisdn());
		event.setParam(builder.toString());
		super.save(event);
	}

	private void createAddEvent(BlackListEntity blackList) {
		// 产生删除黑名单事件
		EventEntity event = new EventEntity();
		event.setEventId("AddBlackListEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("msisdn:").append(blackList.getMsisdn());
		event.setParam(builder.toString());
		super.save(event);
	}

	@Override
	public AjaxJson saveFile(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		List<BlackListEntity> actualList = new ArrayList<BlackListEntity>();
		List<ErrorMsg> errorMsgList = new ArrayList<ErrorMsg>();
		StringBuilder builder = new StringBuilder(11);
		char c = 0;
		int i = -1;
		long tempCount = 0;
		Long tempMsisdn = null;
		Integer enableState = new Integer(1);
		int idx = 0;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			try {
				InputStream in = entity.getValue().getInputStream();

				while ((i = in.read()) != -1) {
					c = (char) i;
					if (c == '\r') {
						// do nothing
					} else if (c == '\n') {
						// 换行符结尾
						idx++;
						tempMsisdn = new Long(builder.toString());
						builder.delete(0, builder.length());
						// 判断黑名单号码在数据库中是否存在
						if (!this.isExistInList(actualList, tempMsisdn)) {
							tempCount = super.getCountForJdbcParam(GET_COUNT,
									new Object[] {tempMsisdn});
							if (tempCount == 0) {
								BlackListEntity blackList = new BlackListEntity();
								blackList.setMsisdn(tempMsisdn);
								blackList.setState(enableState);

								actualList.add(blackList);
								if (actualList.size() == 500) {
									// 500条提交一次，防止占用内存过多
									super.batchSave(actualList);
									// 清空list
									actualList.clear();
								}
							} else {
								this.addErrorMsg(errorMsgList, idx, tempMsisdn);
							}
						} else {
							this.addErrorMsg(errorMsgList, idx, tempMsisdn);
						}
						

					} else {
						if (Character.isDigit(c)) {
							builder.append(c);
						}
					}
				}
				if (builder.length() > 1) {
					tempMsisdn = new Long(builder.toString());
					if (!this.isExistInList(actualList, tempMsisdn)) {
						tempCount = super.getCountForJdbcParam(GET_COUNT,
								new Object[] {tempMsisdn});
						if (tempCount == 0) {
							BlackListEntity blackList = new BlackListEntity();
							blackList.setMsisdn(new Long(builder.toString()));
							blackList.setState(enableState);
							actualList.add(blackList);
						} else {
							this.addErrorMsg(errorMsgList, idx, tempMsisdn);
						}
					} else {
						this.addErrorMsg(errorMsgList, idx, tempMsisdn);
					}
					
				}
				if (!actualList.isEmpty()) {
					super.batchSave(actualList);
				}
			} catch (IOException e) {
				j.setMsg("黑名单导入失败");
				e.printStackTrace();
			} finally {
				try {
					entity.getValue().getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			j.setMsg("黑名单导入成功");
		}

		if (!errorMsgList.isEmpty()) {
			String downloadHref = this.errorMsgService.saveErrorMsg(
					errorMsgList, request, null);
			builder = new StringBuilder("导入完成，但有一些数据导入失败，您可以");
			builder.append("<a href=\"").append(downloadHref)
					.append("\">下载错误信息</a>进行查看");
			j.setMsg(builder.toString());

		} else {
			j.setMsg("黑名单导入成功");
		}
		return j;
	}
	
	private boolean isExistInList(List<BlackListEntity> list, Long msisdn) {
		for (BlackListEntity blackList : list) {
			if (blackList.getMsisdn().equals(msisdn)) {
				return true;
			}
		}
		return false;
	}
	
	private void addErrorMsg(List<ErrorMsg> errorMsgList, int idx, Long msisdn) {
		// 创建错误信息,包括行号，错误信息两个字段
		ErrorMsg errorMsg = new ErrorMsg();
		errorMsg.setNum(idx);
		errorMsg.setMsg("手机号码" + msisdn + "在系统中已经存在");
		errorMsgList.add(errorMsg);
	}

}