/**
 * GetCTChargeInfoReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class GetCTChargeInfoReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String contentCode;

    private java.lang.String fee;

    private com.huawei.sdp.pae.service.schema.UserID userID;

    public GetCTChargeInfoReq() {
    }

    public GetCTChargeInfoReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String contentCode,
           java.lang.String fee,
           com.huawei.sdp.pae.service.schema.UserID userID) {
        super(
            extensionInfo);
        this.contentCode = contentCode;
        this.fee = fee;
        this.userID = userID;
    }


    /**
     * Gets the contentCode value for this GetCTChargeInfoReq.
     * 
     * @return contentCode
     */
    public java.lang.String getContentCode() {
        return contentCode;
    }


    /**
     * Sets the contentCode value for this GetCTChargeInfoReq.
     * 
     * @param contentCode
     */
    public void setContentCode(java.lang.String contentCode) {
        this.contentCode = contentCode;
    }


    /**
     * Gets the fee value for this GetCTChargeInfoReq.
     * 
     * @return fee
     */
    public java.lang.String getFee() {
        return fee;
    }


    /**
     * Sets the fee value for this GetCTChargeInfoReq.
     * 
     * @param fee
     */
    public void setFee(java.lang.String fee) {
        this.fee = fee;
    }


    /**
     * Gets the userID value for this GetCTChargeInfoReq.
     * 
     * @return userID
     */
    public com.huawei.sdp.pae.service.schema.UserID getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this GetCTChargeInfoReq.
     * 
     * @param userID
     */
    public void setUserID(com.huawei.sdp.pae.service.schema.UserID userID) {
        this.userID = userID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCTChargeInfoReq)) return false;
        GetCTChargeInfoReq other = (GetCTChargeInfoReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.contentCode==null && other.getContentCode()==null) || 
             (this.contentCode!=null &&
              this.contentCode.equals(other.getContentCode()))) &&
            ((this.fee==null && other.getFee()==null) || 
             (this.fee!=null &&
              this.fee.equals(other.getFee()))) &&
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
        if (getContentCode() != null) {
            _hashCode += getContentCode().hashCode();
        }
        if (getFee() != null) {
            _hashCode += getFee().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCTChargeInfoReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GetCTChargeInfoReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
