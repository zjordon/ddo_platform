package com.jason.ddoWeb.service.impl.smtask;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.entity.smtask.SmMsisdnListEntity;
import com.jason.ddoWeb.entity.smtask.SmTaskEntity;
import com.jason.ddoWeb.service.smtask.SmMsisdnListServiceI;
import com.jason.ddoWeb.service.smtask.SmRequestServiceI;
import com.jason.ddoWeb.service.smtask.SmTaskServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("smTaskService")
@Transactional
public class SmTaskServiceImpl extends CommonServiceImpl implements
		SmTaskServiceI {
	private final static String FIND_ENABLE_CHANNEL_USER = "from ChannelUserEntity where channelId = ? and state = 1";

	@Autowired
	private SmRequestServiceI smRequestService;
	@Autowired
	private SmMsisdnListServiceI smMsisdnListService;
	
	@Override
	public <T> Serializable save(T entity) {
		this.setTaskUserAndPass((SmTaskEntity)entity);
		return super.save(entity);
	}

	@Override
	public String save(SmTaskEntity smTask, String msisdnContent) {
		// 状态默认为未发送
		smTask.setState(new Integer(0));
		this.setTaskUserAndPass(smTask);
		String id = (String) super.save(smTask);
		// 解析输出框输入的手机号码，以换行符分隔
		char[] cs = msisdnContent.toCharArray();
		List<SmMsisdnListEntity> msisdnList = new ArrayList<SmMsisdnListEntity>();
		StringBuilder builder = new StringBuilder(11);
		for (char c : cs) {
			if (c == '\r') {
				// do nothing
			} else if (c == '\n') {
				// 换行符结尾
				SmMsisdnListEntity msisdn = new SmMsisdnListEntity();
				msisdn.setMsisdn(new Long(builder.toString()));
				msisdn.setSmTaskId(id);
				msisdnList.add(msisdn);
				builder.delete(0, builder.length());
			} else {
				if (Character.isDigit(c)) {
					builder.append(c);
				}
			}
		}
		if (builder.length() > 1) {
			SmMsisdnListEntity msisdn = new SmMsisdnListEntity();
			msisdn.setMsisdn(new Long(builder.toString()));
			msisdn.setSmTaskId(id);
			msisdnList.add(msisdn);
		}
		super.batchSave(msisdnList);
		smTask.setMsisdnNum(msisdnList.size());
		super.updateEntitie(smTask);
		return id;
	}

	@Override
	public String save(SmTaskEntity smTask, InputStream in) {
		// 状态默认为未发送
		smTask.setState(new Integer(0));
		this.setTaskUserAndPass(smTask);
		String id = (String) super.save(smTask);
		this.doSaveFile(smTask, in);
		return id;
	}

	@Override
	public String saveFile(String taskId, InputStream in) {
		SmTaskEntity smTask = super.get(SmTaskEntity.class, taskId);
		if (smTask != null) {
			this.doSaveFile(smTask, in);
		}
		return taskId;
	}

	private void doSaveFile(SmTaskEntity smTask, InputStream in) {
		List<SmMsisdnListEntity> msisdnList = new ArrayList<SmMsisdnListEntity>();
		StringBuilder builder = new StringBuilder(11);
		char c = 0;
		int i = -1;
		try {
			while ((i = in.read()) != -1) {
				c = (char) i;
				if (c == '\r') {
					// do nothing
				} else if (c == '\n') {
					// 换行符结尾
					SmMsisdnListEntity msisdn = new SmMsisdnListEntity();
					msisdn.setMsisdn(new Long(builder.toString()));
					msisdn.setSmTaskId(smTask.getId());
					msisdnList.add(msisdn);
					builder.delete(0, builder.length());
					if (msisdnList.size() == 500) {
						// 500条提交一次，防止占用内存过多
						this.saveMsisdnNum(smTask, msisdnList);
						// 清空list
						msisdnList.clear();
					}
				} else {
					if (Character.isDigit(c)) {
						builder.append(c);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (builder.length() > 1) {
			SmMsisdnListEntity msisdn = new SmMsisdnListEntity();
			msisdn.setMsisdn(new Long(builder.toString()));
			msisdn.setSmTaskId(smTask.getId());
			msisdnList.add(msisdn);
		}
		if (!msisdnList.isEmpty()) {
			this.saveMsisdnNum(smTask, msisdnList);
		}
		super.updateEntitie(smTask);
	}

	private void saveMsisdnNum(SmTaskEntity smTask,
			List<SmMsisdnListEntity> msisdnList) {
		Integer oldNum = smTask.getMsisdnNum();
		if (oldNum == null) {
			oldNum = new Integer(0);
		}
		smTask.setMsisdnNum(new Integer(oldNum.intValue() + msisdnList.size()));
		super.batchSave(msisdnList);
	}

	private void setTaskUserAndPass(SmTaskEntity smTask) {
		if (StringUtil.isEmpty(smTask.getChannelUserName())) {
			// 根据渠道id查找一个渠道用户并设置到任务表中
			List<ChannelUserEntity> list = super.getSession()
					.createQuery(FIND_ENABLE_CHANNEL_USER)
					.setString(0, smTask.getChannelId()).setMaxResults(1).list();
			if (!list.isEmpty()) {
				ChannelUserEntity channelUser = (ChannelUserEntity) list.get(0);
				smTask.setChannelUserName(channelUser.getUsername());
				smTask.setChannelUserPass(channelUser.getPassword());
			}
		}
		
	}

	@Override
	public void deleteByChannelId(String channelId) {
		List<SmTaskEntity> list = super.findByProperty(SmTaskEntity.class, "channelId", channelId);
		for (SmTaskEntity entity : list) {
			//删除对应的号码清单
			this.smMsisdnListService.deleteByTaskId(entity.getId());
			//删除对应的短信请求
			this.smRequestService.deleteByTaskId(entity.getId());
			super.delete(entity);
		}
		
	}

}