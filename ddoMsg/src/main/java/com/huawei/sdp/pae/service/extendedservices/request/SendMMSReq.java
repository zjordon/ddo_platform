/**
 * SendMMSReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class SendMMSReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String addresses;

    private com.huawei.sdp.pae.service.schema.ChargingInformation charging;

    private com.huawei.sdp.pae.service.schema.MMSHeaderInfo header;

    private java.lang.String priority;

    private com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest;

    private java.lang.String senderAddress;

    private java.lang.String subject;

    public SendMMSReq() {
    }

    public SendMMSReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String addresses,
           com.huawei.sdp.pae.service.schema.ChargingInformation charging,
           com.huawei.sdp.pae.service.schema.MMSHeaderInfo header,
           java.lang.String priority,
           com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest,
           java.lang.String senderAddress,
           java.lang.String subject) {
        super(
            extensionInfo);
        this.addresses = addresses;
        this.charging = charging;
        this.header = header;
        this.priority = priority;
        this.receiptRequest = receiptRequest;
        this.senderAddress = senderAddress;
        this.subject = subject;
    }


    /**
     * Gets the addresses value for this SendMMSReq.
     * 
     * @return addresses
     */
    public java.lang.String getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this SendMMSReq.
     * 
     * @param addresses
     */
    public void setAddresses(java.lang.String addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the charging value for this SendMMSReq.
     * 
     * @return charging
     */
    public com.huawei.sdp.pae.service.schema.ChargingInformation getCharging() {
        return charging;
    }


    /**
     * Sets the charging value for this SendMMSReq.
     * 
     * @param charging
     */
    public void setCharging(com.huawei.sdp.pae.service.schema.ChargingInformation charging) {
        this.charging = charging;
    }


    /**
     * Gets the header value for this SendMMSReq.
     * 
     * @return header
     */
    public com.huawei.sdp.pae.service.schema.MMSHeaderInfo getHeader() {
        return header;
    }


    /**
     * Sets the header value for this SendMMSReq.
     * 
     * @param header
     */
    public void setHeader(com.huawei.sdp.pae.service.schema.MMSHeaderInfo header) {
        this.header = header;
    }


    /**
     * Gets the priority value for this SendMMSReq.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this SendMMSReq.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the receiptRequest value for this SendMMSReq.
     * 
     * @return receiptRequest
     */
    public com.huawei.sdp.pae.service.schema.SimpleReference getReceiptRequest() {
        return receiptRequest;
    }


    /**
     * Sets the receiptRequest value for this SendMMSReq.
     * 
     * @param receiptRequest
     */
    public void setReceiptRequest(com.huawei.sdp.pae.service.schema.SimpleReference receiptRequest) {
        this.receiptRequest = receiptRequest;
    }


    /**
     * Gets the senderAddress value for this SendMMSReq.
     * 
     * @return senderAddress
     */
    public java.lang.String getSenderAddress() {
        return senderAddress;
    }


    /**
     * Sets the senderAddress value for this SendMMSReq.
     * 
     * @param senderAddress
     */
    public void setSenderAddress(java.lang.String senderAddress) {
        this.senderAddress = senderAddress;
    }


    /**
     * Gets the subject value for this SendMMSReq.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this SendMMSReq.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendMMSReq)) return false;
        SendMMSReq other = (SendMMSReq) obj;
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
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.receiptRequest==null && other.getReceiptRequest()==null) || 
             (this.receiptRequest!=null &&
              this.receiptRequest.equals(other.getReceiptRequest()))) &&
            ((this.senderAddress==null && other.getSenderAddress()==null) || 
             (this.senderAddress!=null &&
              this.senderAddress.equals(other.getSenderAddress()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject())));
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
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getReceiptRequest() != null) {
            _hashCode += getReceiptRequest().hashCode();
        }
        if (getSenderAddress() != null) {
            _hashCode += getSenderAddress().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendMMSReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "SendMMSReq"));
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
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priority"));
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
        elemField.setFieldName("senderAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
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
