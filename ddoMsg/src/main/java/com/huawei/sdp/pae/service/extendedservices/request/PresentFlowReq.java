/**
 * PresentFlowReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class PresentFlowReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.Integer activityType;

    private java.lang.Integer flow;

    private java.lang.String imei;

    private java.lang.Integer platform;

    private com.huawei.sdp.pae.service.schema.UserID userID;

    public PresentFlowReq() {
    }

    public PresentFlowReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.Integer activityType,
           java.lang.Integer flow,
           java.lang.String imei,
           java.lang.Integer platform,
           com.huawei.sdp.pae.service.schema.UserID userID) {
        super(
            extensionInfo);
        this.activityType = activityType;
        this.flow = flow;
        this.imei = imei;
        this.platform = platform;
        this.userID = userID;
    }


    /**
     * Gets the activityType value for this PresentFlowReq.
     * 
     * @return activityType
     */
    public java.lang.Integer getActivityType() {
        return activityType;
    }


    /**
     * Sets the activityType value for this PresentFlowReq.
     * 
     * @param activityType
     */
    public void setActivityType(java.lang.Integer activityType) {
        this.activityType = activityType;
    }


    /**
     * Gets the flow value for this PresentFlowReq.
     * 
     * @return flow
     */
    public java.lang.Integer getFlow() {
        return flow;
    }


    /**
     * Sets the flow value for this PresentFlowReq.
     * 
     * @param flow
     */
    public void setFlow(java.lang.Integer flow) {
        this.flow = flow;
    }


    /**
     * Gets the imei value for this PresentFlowReq.
     * 
     * @return imei
     */
    public java.lang.String getImei() {
        return imei;
    }


    /**
     * Sets the imei value for this PresentFlowReq.
     * 
     * @param imei
     */
    public void setImei(java.lang.String imei) {
        this.imei = imei;
    }


    /**
     * Gets the platform value for this PresentFlowReq.
     * 
     * @return platform
     */
    public java.lang.Integer getPlatform() {
        return platform;
    }


    /**
     * Sets the platform value for this PresentFlowReq.
     * 
     * @param platform
     */
    public void setPlatform(java.lang.Integer platform) {
        this.platform = platform;
    }


    /**
     * Gets the userID value for this PresentFlowReq.
     * 
     * @return userID
     */
    public com.huawei.sdp.pae.service.schema.UserID getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this PresentFlowReq.
     * 
     * @param userID
     */
    public void setUserID(com.huawei.sdp.pae.service.schema.UserID userID) {
        this.userID = userID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PresentFlowReq)) return false;
        PresentFlowReq other = (PresentFlowReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.activityType==null && other.getActivityType()==null) || 
             (this.activityType!=null &&
              this.activityType.equals(other.getActivityType()))) &&
            ((this.flow==null && other.getFlow()==null) || 
             (this.flow!=null &&
              this.flow.equals(other.getFlow()))) &&
            ((this.imei==null && other.getImei()==null) || 
             (this.imei!=null &&
              this.imei.equals(other.getImei()))) &&
            ((this.platform==null && other.getPlatform()==null) || 
             (this.platform!=null &&
              this.platform.equals(other.getPlatform()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getActivityType() != null) {
            _hashCode += getActivityType().hashCode();
        }
        if (getFlow() != null) {
            _hashCode += getFlow().hashCode();
        }
        if (getImei() != null) {
            _hashCode += getImei().hashCode();
        }
        if (getPlatform() != null) {
            _hashCode += getPlatform().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PresentFlowReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "PresentFlowReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flow");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imei");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imei"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platform");
        elemField.setXmlName(new javax.xml.namespace.QName("", "platform"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "UserID"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
