/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.smTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SmMsisdnList;
import com.jason.ddoTimingTask.bean.SmTask;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 短信任务处理器
 * @author jasonzhang
 *
 */
public class SmTaskHandler {

	private static final Logger logger = Logger
			.getLogger(SmTaskHandler.class);
	
	private final static SmTaskHandler instance = new SmTaskHandler();
	
	private SmTaskHandler(){}
	
	public final static SmTaskHandler getInstacne() {
		return instance;
	}
	
	public void handle(SmTask smTask) throws HandlerException {
		//必填参数是否为空
		this.validateNecessaryParam(smTask);
		//是否定时发送
		if (smTask.getSendType().intValue() == 2) {
			//定时发送
			//定时时间是否有效
			this.validateTime(smTask.getTimeToSendTime());
		}
		//获取短信任务对应的号码
		List<SmMsisdnList> msisdnList = this.getSmMsisdnLists(smTask.getId());
		//号码清单是否为空
		if (msisdnList.isEmpty()) {
			throw new HandlerException("任务对应的号码清单为空");
		}
		//是否去重
		if (smTask.getRecapture().intValue() == 1) {
			//号码去重处理
			msisdnList = this.duplicateRemoval(msisdnList);
		}
		
		if (msisdnList.size() > 2000) {
			//按2000条一批进行处理
			List<SmMsisdnList> todoList = new ArrayList<SmMsisdnList>(2000);
			for (int i=0; i<msisdnList.size(); i++) {
				todoList.add(msisdnList.get(i));
				if (i%2000 == 0) {
					SmRequestHandler.getInstacne().handle(todoList, smTask);
					todoList.clear();
				}
			}
			if (!todoList.isEmpty()) {
				SmRequestHandler.getInstacne().handle(todoList, smTask);
			}
		} else {
			SmRequestHandler.getInstacne().handle(msisdnList, smTask);
		}
		//更新任务信息
		smTask.setState(new Integer(2));
		smTask.setExecuteTime(new Date());
	}
	
	private void validateNecessaryParam(SmTask smTask) throws HandlerException {
		StringBuilder builder = new StringBuilder();
		if (StringUtils.isBlank(smTask.getChannelUserName())) {
			builder.append("渠道用户名为空");
		}
		if (StringUtils.isBlank(smTask.getChannelUserPass())) {
			builder.append("渠道密码为空");
		}
		if (StringUtils.isBlank(smTask.getBillBusinessId())) {
			builder.append("计费业务id为空");
		}
		if (smTask.getRecapture() == null) {
			builder.append("是否去重参数为空");
		} else if (smTask.getRecapture().intValue() != 1 && smTask.getRecapture().intValue() !=0) {
			builder.append("去重参数不合法，必须为1或0");
		}
		if (smTask.getMsisdnNum() == null) {
			builder.append("号码数量为空");
		}
		if (smTask.getSendType() == null) {
			builder.append("发送类型为空");
		} else if (smTask.getSendType().intValue() != 1 && smTask.getSendType().intValue() != 2) {
			builder.append("发送类型不合法，必须为1或2");
		}
		if (builder.length() > 0) {
			throw new HandlerException(builder.toString());
		}
	}
	
	private void validateTime(Date time) throws HandlerException {
		if (time == null) {
			throw new HandlerException("定时发送时间为空");
		} else if (time.getTime() < System.currentTimeMillis()) {
			throw new HandlerException("定时发送时间已过期，小于当前时间");
		}
	}
	
	private List<SmMsisdnList> getSmMsisdnLists(String taskId) throws HandlerException {
		List<SmMsisdnList> list = null;
		try {
			list = DaoManager.getInstance().getSmMsisdnListDao().getSmMsisdnListList(taskId);
		} catch (DaoException e) {
			logger.error("exception when getSmMsisdnLists", e);
			throw new HandlerException(e.getMessage());
		}
		return list;
	}
	
	private List<SmMsisdnList> duplicateRemoval(List<SmMsisdnList> originalList) {
		List<SmMsisdnList> destList = new ArrayList<SmMsisdnList>();
		for (SmMsisdnList msisdnList : originalList) {
			if (!destList.contains(msisdnList)) {
				destList.add(msisdnList);
			}
		}
		return destList;
	}
}
