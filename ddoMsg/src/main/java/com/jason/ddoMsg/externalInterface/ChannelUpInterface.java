/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DeliverResponse;
import com.jason.ddoMsg.util.HttpHelper;

/**
 * 渠道上行信息接口
 * @author jasonzhang
 *
 */
public class ChannelUpInterface {
	private static final Logger logger = Logger.getLogger(ChannelUpInterface.class);
	
	private final static ChannelUpInterface instance = new ChannelUpInterface();
	
	private ChannelUpInterface() {}
	
	public final static ChannelUpInterface getInstance() {
		return instance;
	}

	/**
	 * 上行消息
	 * @param msdId 消息id
	 * @param msisdn 手机号码
	 * @param instruct 发送指令
	 * @param statusCode 状态
	 * @param upUrl 上行链接
	 * @return
	 */
	public DeliverResponse deliverMsg(String msdId, long msisdn, String instruct, String statusCode, String upUrl) {
		DeliverResponse ret = null;
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("msgid", msdId);
		paramMap.put("mobile", Long.toString(msisdn));
		paramMap.put("content", instruct);
		paramMap.put("status", statusCode);
		try {
			ret = HttpHelper.getInstnace().get(upUrl, paramMap);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error("exception when deliverMsg", e);
			//TODO 超时的处理
		} catch (ClientProtocolException e) {
			//TODO 异常处理
			logger.error("exception when deliverMsg", e);
		} catch (IOException e) {
			//TODO 异常处理
			logger.error("exception when deliverMsg", e);
		}
		if (ret == null) {
			ret = new DeliverResponse();
			ret.setStatusCode(5500);
		}
		return ret;
	}
}
