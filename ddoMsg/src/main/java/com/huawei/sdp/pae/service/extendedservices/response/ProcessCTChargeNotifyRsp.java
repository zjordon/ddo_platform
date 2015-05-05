/**
 * ProcessCTChargeNotifyRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class ProcessCTChargeNotifyRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String status;

    private java.lang.String trade_no;

    public ProcessCTChargeNotifyRsp() {
    }

    public ProcessCTChargeNotifyRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String status,
           java.lang.String trade_no) {
        super(
            extensionInfo,
            result);
        this.status = status;
        this.trade_no = trade_no;
    }


    /**
     * Gets the status value for this ProcessCTChargeNotifyRsp.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ProcessCTChargeNotifyRsp.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the trade_no value for this ProcessCTChargeNotifyRsp.
     * 
     * @return trade_no
     */
    public java.lang.String getTrade_no() {
        return trade_no;
    }


    /**
     * Sets the trade_no value for this ProcessCTChargeNotifyRsp.
     * 
     * @param trade_no
     */
    public void setTrade_no(java.lang.String trade_no) {
        this.trade_no = trade_no;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcessCTChargeNotifyRsp)) return false;
        ProcessCTChargeNotifyRsp other = (ProcessCTChargeNotifyRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.trade_no==null && other.getTrade_no()==null) || 
             (this.trade_no!=null &&
              this.trade_no.equals(other.getTrade_no())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTrade_no() != null) {
            _hashCode += getTrade_no().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessCTChargeNotifyRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trade_no");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trade_no"));
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
