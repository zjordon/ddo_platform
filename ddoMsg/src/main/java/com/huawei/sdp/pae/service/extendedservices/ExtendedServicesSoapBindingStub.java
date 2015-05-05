/**
 * ExtendedServicesSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices;

public class ExtendedServicesSoapBindingStub extends org.apache.axis.client.Stub implements com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStub {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    private java.lang.String version;
    private java.lang.String sourceDeviceCode;
    private java.lang.String authenticatorSource;
    private java.lang.String timeStamp;

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[18];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVerificationCodeInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetVerificationCodeReq"), com.huawei.sdp.pae.service.extendedservices.request.GetVerificationCodeReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetVerificationCodeRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getVerificationCodeInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("clientMMSRead");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "ClientMMSReadReq"), com.huawei.sdp.pae.service.extendedservices.request.ClientMMSReadReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "ClientMMSReadRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "clientMMSReadReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifySmsCX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "NotifySmsCXReq"), com.huawei.sdp.pae.service.extendedservices.request.NotifySmsCXReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "NotifySmsCXRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "notifySmsCXReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("usageAuth");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "UsageAuthReq"), com.huawei.sdp.pae.service.extendedservices.request.UsageAuthReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "UsageAuthRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "usageAuthReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("rdoCharge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "RdoChargeReq"), com.huawei.sdp.pae.service.extendedservices.request.RdoChargeReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "RdoChargeRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "rdoChargeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendMMS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendMMSReq"), com.huawei.sdp.pae.service.extendedservices.request.SendMMSReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "SendMMSRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "sendMMSReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ddoCharge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "DdoChargeReq"), com.huawei.sdp.pae.service.extendedservices.request.DdoChargeReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "DdoChargeRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ddoChargeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addBlackIP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "AddBlackIPReq"), com.huawei.sdp.pae.service.extendedservices.request.AddBlackIPReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "AddBlackIPRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "addBlackIPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendSmsReq"), com.huawei.sdp.pae.service.extendedservices.request.SendSmsReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "SendSmsRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "sendSmsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("serviceHandle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "BussinessReq"), com.huawei.sdp.pae.service.extendedservices.request.BussinessReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "BussinessRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "serviceHandleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("gentlyStoryRead");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadReq"), com.huawei.sdp.pae.service.extendedservices.request.GentlyStoryReadReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "gentlyStoryReadReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createCMShortURL");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "CreateCMShortURLReq"), com.huawei.sdp.pae.service.extendedservices.request.CreateCMShortURLReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "CreateCMShortURLRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createCMShortURLReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVirtualNumber");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "VirtualNumberGetReq"), com.huawei.sdp.pae.service.extendedservices.request.VirtualNumberGetReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "VirtualNumberGetRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getVirtualNumberReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIPBlackList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetIPBlackListReq"), com.huawei.sdp.pae.service.extendedservices.request.GetIPBlackListReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPBlackListRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIPBlackListReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIPWhiteList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetIPWhiteListReq"), com.huawei.sdp.pae.service.extendedservices.request.GetIPWhiteListReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPWhiteListRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIPWhiteListReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCTChargeInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoReq"), com.huawei.sdp.pae.service.extendedservices.request.GetCTChargeInfoReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getCTChargeInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("processCTChargeNotify");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyReq"), com.huawei.sdp.pae.service.extendedservices.request.ProcessCTChargeNotifyReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "processCTChargeNotifyReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("presentFlow");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "PresentFlowReq"), com.huawei.sdp.pae.service.extendedservices.request.PresentFlowReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "PresentFlowRsp"));
        oper.setReturnClass(com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "presentFlowReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[17] = oper;

    }

    public ExtendedServicesSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ExtendedServicesSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ExtendedServicesSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ArrayOf_tns2_ClientMMSFileInfo");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ArrayOf_tns2_NamedParameter");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.NamedParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "NamedParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ArrayOf_xsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "AddBlackIPReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.AddBlackIPReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "BussinessReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.BussinessReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "ClientMMSReadReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.ClientMMSReadReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "CreateCMShortURLReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.CreateCMShortURLReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "DdoChargeReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.DdoChargeReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.GentlyStoryReadReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.GetCTChargeInfoReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetIPBlackListReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.GetIPBlackListReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetIPWhiteListReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.GetIPWhiteListReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetVerificationCodeReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.GetVerificationCodeReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "NotifySmsCXReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.NotifySmsCXReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "PresentFlowReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.PresentFlowReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.ProcessCTChargeNotifyReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "RdoChargeReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.RdoChargeReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendMMSReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.SendMMSReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendSmsReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.SendSmsReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "UsageAuthReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.UsageAuthReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "VirtualNumberGetReq");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.request.VirtualNumberGetReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "AddBlackIPRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "BussinessRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "ClientMMSReadRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "CreateCMShortURLRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "DdoChargeRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPBlackListRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPWhiteListRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetVerificationCodeRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "NotifySmsCXRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "PresentFlowRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "RdoChargeRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "SendMMSRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "SendSmsRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "UsageAuthRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "VirtualNumberGetRsp");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "BlackIP");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.BlackIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ChargingInformation");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.ChargingInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.ClientMMSFileInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSInfo");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.ClientMMSInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "MMSHeaderInfo");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.MMSHeaderInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "NamedParameter");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.NamedParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "RequestCommon");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.RequestCommon.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ResponseCommon");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.ResponseCommon.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "Result");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "SimpleReference");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.SimpleReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "UserID");
            cachedSerQNames.add(qName);
            cls = com.huawei.sdp.pae.service.schema.UserID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp getVerificationCodeInfo(com.huawei.sdp.pae.service.extendedservices.request.GetVerificationCodeReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("getVerificationCodeInfo");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "getVerificationCodeInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.GetVerificationCodeRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp clientMMSRead(com.huawei.sdp.pae.service.extendedservices.request.ClientMMSReadReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("clientMMSRead");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "clientMMSRead"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.ClientMMSReadRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp notifySmsCX(com.huawei.sdp.pae.service.extendedservices.request.NotifySmsCXReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("notifySmsCX");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "notifySmsCX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.NotifySmsCXRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp usageAuth(com.huawei.sdp.pae.service.extendedservices.request.UsageAuthReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("usageAuth");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "usageAuth"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.UsageAuthRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp rdoCharge(com.huawei.sdp.pae.service.extendedservices.request.RdoChargeReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("rdoCharge");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "rdoCharge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.RdoChargeRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }
    
    /*
    public void setHeader(java.lang.String version,java.lang.String sourceDeviceCode,java.lang.String authenticatorSource,java.lang.String timeStamp){
    	this.version = version;
    	this.sourceDeviceCode = sourceDeviceCode;
    	this.authenticatorSource = authenticatorSource;
    	this.timeStamp = timeStamp;
    }
    */
 
//2014-06-21
    /*
    public void setRequestHeaders(org.apache.axis.client.Call _call){
    	org.apache.axis.message.SOAPHeaderElement header = new org.apache.axis.message.SOAPHeaderElement("http://extendedservices.service.pae.sdp.huawei.com","SoapHeader");
    	try{
    		// 版本号
    		header.addChildElement("version").setValue(this.version);
    		// 接入账号,统一由PAE配置，一个账号对应一种门户类型或者平台
    		header.addChildElement("sourceDeviceCode").setValue(this.sourceDeviceCode);
            // 认证码
    		header.addChildElement("authenticatorSource").setValue(this.authenticatorSource);
    		// 时间戳
            header.addChildElement("timeStamp").setValue(this.timeStamp);
    	}catch(javax.xml.soap.SOAPException e){
    		e.printStackTrace();
    	}
    	
    	_call.addHeader(header);
    }
    */

    public com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp sendMMS(com.huawei.sdp.pae.service.extendedservices.request.SendMMSReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("sendMMS");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "sendMMS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.SendMMSRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp ddoCharge(com.huawei.sdp.pae.service.extendedservices.request.DdoChargeReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ddoCharge");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ddoCharge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.DdoChargeRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp addBlackIP(com.huawei.sdp.pae.service.extendedservices.request.AddBlackIPReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("addBlackIP");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "addBlackIP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.AddBlackIPRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp sendSms(com.huawei.sdp.pae.service.extendedservices.request.SendSmsReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("sendSms");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "sendSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.SendSmsRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp serviceHandle(com.huawei.sdp.pae.service.extendedservices.request.BussinessReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("serviceHandle");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "serviceHandle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.BussinessRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp gentlyStoryRead(com.huawei.sdp.pae.service.extendedservices.request.GentlyStoryReadReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("gentlyStoryRead");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "gentlyStoryRead"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.GentlyStoryReadRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp createCMShortURL(com.huawei.sdp.pae.service.extendedservices.request.CreateCMShortURLReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("createCMShortURL");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "createCMShortURL"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.CreateCMShortURLRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp getVirtualNumber(com.huawei.sdp.pae.service.extendedservices.request.VirtualNumberGetReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("getVirtualNumber");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "getVirtualNumber"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.VirtualNumberGetRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp getIPBlackList(com.huawei.sdp.pae.service.extendedservices.request.GetIPBlackListReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("getIPBlackList");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "getIPBlackList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.GetIPBlackListRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp getIPWhiteList(com.huawei.sdp.pae.service.extendedservices.request.GetIPWhiteListReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("getIPWhiteList");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "getIPWhiteList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.GetIPWhiteListRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp getCTChargeInfo(com.huawei.sdp.pae.service.extendedservices.request.GetCTChargeInfoReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("getCTChargeInfo");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "getCTChargeInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.GetCTChargeInfoRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp processCTChargeNotify(com.huawei.sdp.pae.service.extendedservices.request.ProcessCTChargeNotifyReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("processCTChargeNotify");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "processCTChargeNotify"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.ProcessCTChargeNotifyRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp presentFlow(com.huawei.sdp.pae.service.extendedservices.request.PresentFlowReq in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("presentFlow");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "presentFlow"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.huawei.sdp.pae.service.extendedservices.response.PresentFlowRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
