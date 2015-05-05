/**
 * ExtendedServicesStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices;

public interface ExtendedServicesStub extends java.rmi.Remote {
	//public void setHeader(java.lang.String version,java.lang.String sourceDeviceCode,java.lang.String authenticatorSource,java.lang.String timeStamp);
    public com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp getVerificationCodeInfo(com.huawei.sdp.pae.service.extendedservices.request.GetVerificationCodeReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp clientMMSRead(com.huawei.sdp.pae.service.extendedservices.request.ClientMMSReadReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp notifySmsCX(com.huawei.sdp.pae.service.extendedservices.request.NotifySmsCXReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp usageAuth(com.huawei.sdp.pae.service.extendedservices.request.UsageAuthReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp rdoCharge(com.huawei.sdp.pae.service.extendedservices.request.RdoChargeReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp sendMMS(com.huawei.sdp.pae.service.extendedservices.request.SendMMSReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp ddoCharge(com.huawei.sdp.pae.service.extendedservices.request.DdoChargeReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp addBlackIP(com.huawei.sdp.pae.service.extendedservices.request.AddBlackIPReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp sendSms(com.huawei.sdp.pae.service.extendedservices.request.SendSmsReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp serviceHandle(com.huawei.sdp.pae.service.extendedservices.request.BussinessReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp gentlyStoryRead(com.huawei.sdp.pae.service.extendedservices.request.GentlyStoryReadReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp createCMShortURL(com.huawei.sdp.pae.service.extendedservices.request.CreateCMShortURLReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp getVirtualNumber(com.huawei.sdp.pae.service.extendedservices.request.VirtualNumberGetReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp getIPBlackList(com.huawei.sdp.pae.service.extendedservices.request.GetIPBlackListReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp getIPWhiteList(com.huawei.sdp.pae.service.extendedservices.request.GetIPWhiteListReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp getCTChargeInfo(com.huawei.sdp.pae.service.extendedservices.request.GetCTChargeInfoReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp processCTChargeNotify(com.huawei.sdp.pae.service.extendedservices.request.ProcessCTChargeNotifyReq in0) throws java.rmi.RemoteException;
    public com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp presentFlow(com.huawei.sdp.pae.service.extendedservices.request.PresentFlowReq in0) throws java.rmi.RemoteException;
}
