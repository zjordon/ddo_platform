/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.HttpRequestResponse;
import com.jason.ddoTimingTask.bean.SmMsisdnList;
import com.jason.ddoTimingTask.bean.SmRequest;
import com.jason.ddoTimingTask.bean.SmTask;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.util.DateUtil;
import com.jason.ddoTimingTask.util.HttpHelper;
import com.jason.ddoTimingTask.util.PropertiesHelper;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 * 请求处理器
 * @author jasonzhang
 *
 */
public class SmRequestHandler {
	private static final Logger logger = Logger
			.getLogger(SmRequestHandler.class);
	
	private final static SmRequestHandler instance = new SmRequestHandler();
	
	private SmRequestHandler(){}
	
	public final static SmRequestHandler getInstacne() {
		return instance;
	}

	public void handle(List<SmMsisdnList> msisdnList, SmTask smTask) throws HandlerException {
		SmRequest smRequest = new SmRequest();
		smRequest.setId((new UUIDGenerator()).generate());
		smRequest.setSmTaskId(smTask.getId());
		smRequest.setSendTime(new Date());
		HttpRequestResponse response = this.sendRequestDdoMsg(smTask, msisdnList);
		if (response.getMsg().startsWith("1,")) {
			smRequest.setResponseState(new Integer(1));
			String responseNo = response.getMsg().substring(2);
			if (responseNo.length() > 32) {
				responseNo.substring(0, 31);
			}
			smRequest.setResponseNo(responseNo);
		} else {
			//判断是否是数字
			if (NumberUtils.isDigits(response.getMsg())) {
				smRequest.setResponseState(new Integer(response.getMsg()));
			} else if ("-1".equals(response.getMsg())){
				smRequest.setResponseState(new Integer(-1));
			} else {
				//算发送失败
				throw new HandlerException(response.getMsg());
			}
		}
		smRequest.setResponseTime(new Date());
		try {
			DaoManager.getInstance().getSmRequestDao().saveSmRequest(smRequest);
			for (SmMsisdnList msisdn : msisdnList) {
				msisdn.setSmRequestId(smRequest.getId());
			}
			DaoManager.getInstance().getSmMsisdnListDao().updateSmMsisdnList(msisdnList);
		} catch (DaoException e) {
			logger.error("exception when handle", e);
			throw new HandlerException(e.getMessage());
		}
		
	}
	
	private HttpRequestResponse sendRequestDdoMsg(SmTask smTask, List<SmMsisdnList> msisdnList) throws HandlerException {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", smTask.getChannelUserName());
		paramMap.put("password", smTask.getChannelUserPass());
		StringBuilder builder = new StringBuilder();
		for (SmMsisdnList msisdn : msisdnList) {
			builder.append(msisdn.getMsisdn()).append(',');
		}
		
		paramMap.put("mobile", builder.toString());
		paramMap.put("productid", this.getChannelBillCode(smTask.getBillBusinessId()));
		if (smTask.getSendType().intValue() == 2) {
			paramMap.put("productid", DateUtil.formatDate(smTask.getTimeToSendTime()));
		}
		HttpRequestResponse response = null;
		//获取请求的url
		String postUrl = this.getPostUrl();
		try {
			response = HttpHelper.getInstnace().post(postUrl, paramMap);
		} catch (ClientProtocolException e) {
			logger.error("exception when sendRequestDdoMsg", e);
			throw new HandlerException(e.getMessage());
		} catch (IOException e) {
			logger.error("exception when sendRequestDdoMsg", e);
			throw new HandlerException(e.getMessage());
		}
		if (response != null) {
			if (response.getStatusCode() != 200) {
				throw new HandlerException("response status code is " + response.getStatusCode());
			} 
		}
		return response;
	}
	
	private String getChannelBillCode(String billBusinessId) throws HandlerException {
		String channelBillCode = null;
		try {
			channelBillCode = DaoManager.getInstance().getBillBusinessDao().getChannelBillCode(billBusinessId);
		} catch (DaoException e) {
			logger.error("exception when getChannelBillCode", e);
			throw new HandlerException(e.getMessage());
		}
		if (channelBillCode == null) {
			throw new HandlerException("找不到对应的产品id");
		}
		return channelBillCode;
	}
	
	private String getPostUrl() throws HandlerException {
		String postUrl = null;
		PropertiesHelper propsHelper = PropertiesHelper.getInstance();
		Properties props = propsHelper.loadProps("config/sys-config.properties",
				"sys-config.properties");
		if (props != null) {
			postUrl = props.getProperty("postUrl");
			if (postUrl == null) {
				throw new HandlerException("postUrl is null");
			}
		} else {
			throw new HandlerException("props is null");
		}
		return postUrl;
	}
}
