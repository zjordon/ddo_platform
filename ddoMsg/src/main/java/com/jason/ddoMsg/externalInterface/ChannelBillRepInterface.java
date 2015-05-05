/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DeliverResponse;
import com.jason.ddoMsg.util.HttpHelper;

/**
 * 下发状态报告接口
 * @author jasonzhang
 *
 */
public class ChannelBillRepInterface {
	private static final Logger logger = Logger.getLogger(ChannelBillRepInterface.class);
	private final static ChannelBillRepInterface instance = new ChannelBillRepInterface();
	
	private ChannelBillRepInterface(){}
	
	public final static ChannelBillRepInterface getInstnace() {
		return instance;
	}

	/**
	 * 下发状态报告
	 * @param msdId 消息id
	 * @param msisdn 手机号码
	 * @param statusCode 状态
	 * @param reportUrl 请求链接
	 * @return
	 */
	public DeliverResponse deliverReport(String msgId, long msisdn, String statusCode, String reportUrl) {
		DeliverResponse ret = null;
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("msgid", msgId);
		paramMap.put("mobile", Long.toString(msisdn));
		paramMap.put("status", statusCode);
		try {
			ret = HttpHelper.getInstnace().get(reportUrl, paramMap);
		} catch (URISyntaxException e) {
			//TODO 异常处理
			logger.error("exception when deliverReport", e);
		} catch (ClientProtocolException e) {
			//TODO 异常处理
			logger.error("exception when deliverReport", e);
		} catch (IOException e) {
			//TODO 异常处理
			logger.error("exception when deliverReport", e);
		}
		if (ret == null) {
			ret = new DeliverResponse();
			ret.setStatusCode(5500);
		}
		return ret;
	}
}
