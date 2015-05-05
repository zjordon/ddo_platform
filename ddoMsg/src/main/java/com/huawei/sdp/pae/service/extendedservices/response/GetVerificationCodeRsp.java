/**
 * GetVerificationCodeRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class GetVerificationCodeRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String verificationCode;

    private java.lang.String verificationImg;

    public GetVerificationCodeRsp() {
    }

    public GetVerificationCodeRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String verificationCode,
           java.lang.String verificationImg) {
        super(
            extensionInfo,
            result);
        this.verificationCode = verificationCode;
        this.verificationImg = verificationImg;
    }


    /**
     * Gets the verificationCode value for this GetVerificationCodeRsp.
     * 
     * @return verificationCode
     */
    public java.lang.String getVerificationCode() {
        return verificationCode;
    }


    /**
     * Sets the verificationCode value for this GetVerificationCodeRsp.
     * 
     * @param verificationCode
     */
    public void setVerificationCode(java.lang.String verificationCode) {
        this.verificationCode = verificationCode;
    }


    /**
     * Gets the verificationImg value for this GetVerificationCodeRsp.
     * 
     * @return verificationImg
     */
    public java.lang.String getVerificationImg() {
        return verificationImg;
    }


    /**
     * Sets the verificationImg value for this GetVerificationCodeRsp.
     * 
     * @param verificationImg
     */
    public void setVerificationImg(java.lang.String verificationImg) {
        this.verificationImg = verificationImg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetVerificationCodeRsp)) return false;
        GetVerificationCodeRsp other = (GetVerificationCodeRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.verificationCode==null && other.getVerificationCode()==null) || 
             (this.verificationCode!=null &&
              this.verificationCode.equals(other.getVerificationCode()))) &&
            ((this.verificationImg==null && other.getVerificationImg()==null) || 
             (this.verificationImg!=null &&
              this.verificationImg.equals(other.getVerificationImg())));
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
        if (getVerificationCode() != null) {
            _hashCode += getVerificationCode().hashCode();
        }
        if (getVerificationImg() != null) {
            _hashCode += getVerificationImg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetVerificationCodeRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetVerificationCodeRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "verificationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationImg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "verificationImg"));
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
