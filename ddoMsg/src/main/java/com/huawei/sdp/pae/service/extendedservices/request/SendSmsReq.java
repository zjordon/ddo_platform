/**
 * SendSmsReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class SendSmsReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String addresses;

    private com.huawei.sdp.pae.service.schema.ChargingInformation charging;

    private com.huawei.sdp.pae.service.schema.MMSHeaderInfo header;

    private java.lang.String message;

    private com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest;

    private java.lang.String senderName;

    public SendSmsReq() {
    }

    public SendSmsReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String addresses,
           com.huawei.sdp.pae.service.schema.ChargingInformation charging,
           com.huawei.sdp.pae.service.schema.MMSHeaderInfo header,
           java.lang.String message,
           com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest,
           java.lang.String senderName) {
        super(
            extensionInfo);
        this.addresses = addresses;
        this.charging = charging;
        this.header = header;
        this.message = message;
        this.receiptRequest = receiptRequest;
        this.senderName = senderName;
    }


    /**
     * Gets the addresses value for this SendSmsReq.
     * 
     * @return addresses
     */
    public java.lang.String getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this SendSmsReq.
     * 
     * @param addresses
     */
    public void setAddresses(java.lang.String addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the charging value for this SendSmsReq.
     * 
     * @return charging
     */
    public com.huawei.sdp.pae.service.schema.ChargingInformation getCharging() {
        return charging;
    }


    /**
     * Sets the charging value for this SendSmsReq.
     * 
     * @param charging
     */
    public void setCharging(com.huawei.sdp.pae.service.schema.ChargingInformation charging) {
        this.charging = charging;
    }


    /**
     * Gets the header value for this SendSmsReq.
     * 
     * @return header
     */
    public com.huawei.sdp.pae.service.schema.MMSHeaderInfo getHeader() {
        return header;
    }


    /**
     * Sets the header value for this SendSmsReq.
     * 
     * @param header
     */
    public void setHeader(com.huawei.sdp.pae.service.schema.MMSHeaderInfo header) {
        this.header = header;
    }


    /**
     * Gets the message value for this SendSmsReq.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SendSmsReq.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the receiptRequest value for this SendSmsReq.
     * 
     * @return receiptRequest
     */
    public com.huawei.sdp.pae.service.schema.SimpleReference getReceiptRequest() {
        return receiptRequest;
    }


    /**
     * Sets the receiptRequest value for this SendSmsReq.
     * 
     * @param receiptRequest
     */
    public void setReceiptRequest(com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest) {
        this.receiptRequest = receiptRequest;
    }


    /**
     * Gets the senderName value for this SendSmsReq.
     * 
     * @return senderName
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this SendSmsReq.
     * 
     * @param senderName
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendSmsReq)) return false;
        SendSmsReq other = (SendSmsReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              this.addresses.equals(other.getAddresses()))) &&
            ((this.charging==null && other.getCharging()==null) || 
             (this.charging!=null &&
              this.charging.equals(other.getCharging()))) &&
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.receiptRequest==null && other.getReceiptRequest()==null) || 
             (this.receiptRequest!=null &&
              this.receiptRequest.equals(other.getReceiptRequest()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName())));
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
        if (getAddresses() != null) {
            _hashCode += getAddresses().hashCode();
        }
        if (getCharging() != null) {
            _hashCode += getCharging().hashCode();
        }
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getReceiptRequest() != null) {
            _hashCode += getReceiptRequest().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSmsReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendSmsReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("charging");
        elemField.setXmlName(new javax.xml.namespace.QName("", "charging"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ChargingInformation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "MMSHeaderInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiptRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiptRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "SimpleReference"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderName"));
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
