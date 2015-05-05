/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.huawei.sdp.pae.service.extendedservices.ExtendedServicesSoapBindingStub;
import com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStubServiceLocator;
import com.huawei.sdp.pae.service.extendedservices.request.DdoChargeReq;
import com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp;
import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.DdoMsgResult;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ConfigCache;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.util.AesUtils;
import com.jason.ddoMsg.util.DateUtil;

/**
 * 提交ddo请求接口
 * @author jasonzhang
 *
 */
public class DdoMsgInterface {
	private static final Logger logger = Logger.getLogger(DdoMsgInterface.class);
	private final static DdoMsgInterface instance = new DdoMsgInterface();
	
	private DdoMsgInterface(){}
	
	public final static DdoMsgInterface getInstance() {
		return instance;
	}

	/**
	 * 提交ddo请求
	 * @param ddoMsg 消息
	 * @return
	 */
	public DdoMsgResult submitDdoMsg(DdoMsg ddoMsg) {
		DdoMsgResult result = null;
		String serviceId = this.getServiceId(ddoMsg.getBillingBusinessId());
		if (serviceId != null) {
			//logger.debug("serviceId is " + serviceId);
			ConfigCache configCache = CacheManager.getInstance().getConfigCache();
			String url = configCache.getDdoUrl();
			System.out.println("url is " + url);
			URL soapURL = null;;
			try {
				soapURL = new URL(url);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			if (soapURL != null) {
				// 获取soap服务
				ExtendedServicesSoapBindingStub extendedService;
				DdoChargeRsp rsp = null;
				try {
					extendedService = (ExtendedServicesSoapBindingStub) new ExtendedServicesStubServiceLocator()
							.getExtendedServices(soapURL);

					String timestamp = DateUtil.formatDate(ddoMsg.getSendTime());
					String sourceDeviceCode = configCache.getDdoUsername();
					String passwd = configCache.getDdoPassword();
					String authenticatorSource = sourceDeviceCode + passwd + timestamp;
					String spId = configCache.getDdoSpId();
					String asyncNotifyURL = configCache.getDdoAsyncNotifyURL();
					String mobile = Long.toString(ddoMsg.getMsisdn().longValue());

					authenticatorSource = DigestUtils.sha256Hex(authenticatorSource);

					// extendedService.setHeader("1.0", sourceDeviceCode,
					// authenticatorSource, timestamp);
					extendedService.setHeader("", "sourceDeviceCode", sourceDeviceCode);
					extendedService.setHeader("", "timeStamp", timestamp);
					extendedService.setHeader("", "authenticatorSource",
							authenticatorSource);
					extendedService.setHeader("", "version", "1.0");
					passwd = passwd
							+ String.format("%1$0" + (16 - passwd.length()) + "d", 0);// 密钥

					DdoChargeReq req = new DdoChargeReq();
					req.setChannelId("");
					if (StringUtils.isNotBlank(asyncNotifyURL)) {
						req.setAsyncNotifyURL(asyncNotifyURL);
					}
					byte[] encryptmobile = AesUtils.encrypt3(mobile, passwd);
					req.setMsisdn(AesUtils.parseByte2HexStr(encryptmobile));
					byte[] encryptserviceid = AesUtils.encrypt3(serviceId, passwd);
					req.setServiceId(AesUtils.parseByte2HexStr(encryptserviceid));
					byte[] encryptspid = AesUtils.encrypt3(spId, passwd);
					req.setSpId(AesUtils.parseByte2HexStr(encryptspid));
//					NamedParameter[] extensionInfo = rsp.getExtensionInfo();
					logger.debug("start send ddo msg");
					rsp = extendedService.ddoCharge(req);
					logger.debug("end send ddo msg");
					result = new DdoMsgResult();
					result.setTransationId(rsp.getTransationId());
					result.setReturnMsgCode(rsp.getResult().getResultCode());
				} catch (ServiceException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (result == null) {
					//提交时有异常，把状态设置为提交ddo平台失几
					result = new DdoMsgResult();
					result.setSendResult(3);
				}
			}
		} else {
			result = new DdoMsgResult();
			//找不到对应的service id把状态设置为错误状态
			result.setSendResult(4);
		}
		
		
		return result;
	}
	
	private String getServiceId(String billBusinessId) {
		BillBusiness billBusiness = null;
		try {
			billBusiness = CacheManager.getInstance().getBillBusinessCache().getBillBusiness(billBusinessId);
		} catch (ElementNotFoundException e) {
			logger.warn("could not find serviceId with billBusinessId " + billBusinessId);
		}
		return (billBusiness != null) ? billBusiness.getCode() : null;
	}
}
