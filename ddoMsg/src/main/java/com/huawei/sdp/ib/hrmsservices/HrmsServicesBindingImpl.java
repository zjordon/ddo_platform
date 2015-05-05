/**
 * HrmsServicesBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.ib.hrmsservices;


import com.huawei.sdp.ib.hrmsservices.response.RdoChargeResultNotifyRsp;
import com.huawei.sdp.ib.schema.Result;
import com.jason.ddoMsg.externalInterface.BillReportInterface;

public class HrmsServicesBindingImpl implements
		com.huawei.sdp.ib.hrmsservices.HrmsServices {
	public com.huawei.sdp.ib.hrmsservices.response.RdoChargeResultNotifyRsp rdoChargeResultNotify(
			com.huawei.sdp.ib.hrmsservices.request.RdoChargeResultNotifyReq parameters)
			throws java.rmi.RemoteException {
		RdoChargeResultNotifyRsp _resp = null;

		try {
			_resp = new RdoChargeResultNotifyRsp();
			String transationId = parameters.getTransationId();
			String resultCode = parameters.getChargeResult().getResultCode();
			String ret = BillReportInterface.getInstance().receiveBillReport(transationId, resultCode);
			Result result = new Result();
			result.setResultCode(ret);
			if ("00000000".equals(ret)) {
				result.setResultMessage("success");
			} else {
				result.setResultMessage("fail");
			}
			_resp.setResult(result);
			_resp.setExtensionInfo(parameters.getExtensionInfo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return _resp;

	}

	public com.huawei.sdp.ib.hrmsservices.response.DdoChargeNotifyRsp ddoChargeNotify(
			com.huawei.sdp.ib.hrmsservices.request.DdoChargeNotifyReq parameters)
			throws java.rmi.RemoteException {
		return null;
	}

}
