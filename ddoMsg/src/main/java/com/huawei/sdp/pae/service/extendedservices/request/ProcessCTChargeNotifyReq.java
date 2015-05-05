/**
 * ProcessCTChargeNotifyReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class ProcessCTChargeNotifyReq  implements java.io.Serializable {
    private java.lang.String extension;

    private java.lang.String sign;

    private java.lang.String trade_no;

    private java.lang.String trade_status;

    private java.lang.String trade_time;

    public ProcessCTChargeNotifyReq() {
    }

    public ProcessCTChargeNotifyReq(
           java.lang.String extension,
           java.lang.String sign,
           java.lang.String trade_no,
           java.lang.String trade_status,
           java.lang.String trade_time) {
           this.extension = extension;
           this.sign = sign;
           this.trade_no = trade_no;
           this.trade_status = trade_status;
           this.trade_time = trade_time;
    }


    /**
     * Gets the extension value for this ProcessCTChargeNotifyReq.
     * 
     * @return extension
     */
    public java.lang.String getExtension() {
        return extension;
    }


    /**
     * Sets the extension value for this ProcessCTChargeNotifyReq.
     * 
     * @param extension
     */
    public void setExtension(java.lang.String extension) {
        this.extension = extension;
    }


    /**
     * Gets the sign value for this ProcessCTChargeNotifyReq.
     * 
     * @return sign
     */
    public java.lang.String getSign() {
        return sign;
    }


    /**
     * Sets the sign value for this ProcessCTChargeNotifyReq.
     * 
     * @param sign
     */
    public void setSign(java.lang.String sign) {
        this.sign = sign;
    }


    /**
     * Gets the trade_no value for this ProcessCTChargeNotifyReq.
     * 
     * @return trade_no
     */
    public java.lang.String getTrade_no() {
        return trade_no;
    }


    /**
     * Sets the trade_no value for this ProcessCTChargeNotifyReq.
     * 
     * @param trade_no
     */
    public void setTrade_no(java.lang.String trade_no) {
        this.trade_no = trade_no;
    }


    /**
     * Gets the trade_status value for this ProcessCTChargeNotifyReq.
     * 
     * @return trade_status
     */
    public java.lang.String getTrade_status() {
        return trade_status;
    }


    /**
     * Sets the trade_status value for this ProcessCTChargeNotifyReq.
     * 
     * @param trade_status
     */
    public void setTrade_status(java.lang.String trade_status) {
        this.trade_status = trade_status;
    }


    /**
     * Gets the trade_time value for this ProcessCTChargeNotifyReq.
     * 
     * @return trade_time
     */
    public java.lang.String getTrade_time() {
        return trade_time;
    }


    /**
     * Sets the trade_time value for this ProcessCTChargeNotifyReq.
     * 
     * @param trade_time
     */
    public void setTrade_time(java.lang.String trade_time) {
        this.trade_time = trade_time;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcessCTChargeNotifyReq)) return false;
        ProcessCTChargeNotifyReq other = (ProcessCTChargeNotifyReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.extension==null && other.getExtension()==null) || 
             (this.extension!=null &&
              this.extension.equals(other.getExtension()))) &&
            ((this.sign==null && other.getSign()==null) || 
             (this.sign!=null &&
              this.sign.equals(other.getSign()))) &&
            ((this.trade_no==null && other.getTrade_no()==null) || 
             (this.trade_no!=null &&
              this.trade_no.equals(other.getTrade_no()))) &&
            ((this.trade_status==null && other.getTrade_status()==null) || 
             (this.trade_status!=null &&
              this.trade_status.equals(other.getTrade_status()))) &&
            ((this.trade_time==null && other.getTrade_time()==null) || 
             (this.trade_time!=null &&
              this.trade_time.equals(other.getTrade_time())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getExtension() != null) {
            _hashCode += getExtension().hashCode();
        }
        if (getSign() != null) {
            _hashCode += getSign().hashCode();
        }
        if (getTrade_no() != null) {
            _hashCode += getTrade_no().hashCode();
        }
        if (getTrade_status() != null) {
            _hashCode += getTrade_status().hashCode();
        }
        if (getTrade_time() != null) {
            _hashCode += getTrade_time().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessCTChargeNotifyReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "ProcessCTChargeNotifyReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trade_no");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trade_no"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trade_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trade_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trade_time");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trade_time"));
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
