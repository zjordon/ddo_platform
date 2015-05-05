/**
 * GetCTChargeInfoRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class GetCTChargeInfoRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String accessCode;

    private java.lang.String smsChargeCmd;

    public GetCTChargeInfoRsp() {
    }

    public GetCTChargeInfoRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String accessCode,
           java.lang.String smsChargeCmd) {
        super(
            extensionInfo,
            result);
        this.accessCode = accessCode;
        this.smsChargeCmd = smsChargeCmd;
    }


    /**
     * Gets the accessCode value for this GetCTChargeInfoRsp.
     * 
     * @return accessCode
     */
    public java.lang.String getAccessCode() {
        return accessCode;
    }


    /**
     * Sets the accessCode value for this GetCTChargeInfoRsp.
     * 
     * @param accessCode
     */
    public void setAccessCode(java.lang.String accessCode) {
        this.accessCode = accessCode;
    }


    /**
     * Gets the smsChargeCmd value for this GetCTChargeInfoRsp.
     * 
     * @return smsChargeCmd
     */
    public java.lang.String getSmsChargeCmd() {
        return smsChargeCmd;
    }


    /**
     * Sets the smsChargeCmd value for this GetCTChargeInfoRsp.
     * 
     * @param smsChargeCmd
     */
    public void setSmsChargeCmd(java.lang.String smsChargeCmd) {
        this.smsChargeCmd = smsChargeCmd;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCTChargeInfoRsp)) return false;
        GetCTChargeInfoRsp other = (GetCTChargeInfoRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accessCode==null && other.getAccessCode()==null) || 
             (this.accessCode!=null &&
              this.accessCode.equals(other.getAccessCode()))) &&
            ((this.smsChargeCmd==null && other.getSmsChargeCmd()==null) || 
             (this.smsChargeCmd!=null &&
              this.smsChargeCmd.equals(other.getSmsChargeCmd())));
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
        if (getAccessCode() != null) {
            _hashCode += getAccessCode().hashCode();
        }
        if (getSmsChargeCmd() != null) {
            _hashCode += getSmsChargeCmd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCTChargeInfoRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accessCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsChargeCmd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smsChargeCmd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
