/**
 * DdoChargeNotifyReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.ib.hrmsservices.request;

public class DdoChargeNotifyReq  implements java.io.Serializable {
    private java.lang.String phoneNum;

    private java.lang.String serviceId;

    private com.huawei.sdp.ib.schema.Result chargeResult;

    private com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo;

    public DdoChargeNotifyReq() {
    }

    public DdoChargeNotifyReq(
           java.lang.String phoneNum,
           java.lang.String serviceId,
           com.huawei.sdp.ib.schema.Result chargeResult,
           com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo) {
           this.phoneNum = phoneNum;
           this.serviceId = serviceId;
           this.chargeResult = chargeResult;
           this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the phoneNum value for this DdoChargeNotifyReq.
     * 
     * @return phoneNum
     */
    public java.lang.String getPhoneNum() {
        return phoneNum;
    }


    /**
     * Sets the phoneNum value for this DdoChargeNotifyReq.
     * 
     * @param phoneNum
     */
    public void setPhoneNum(java.lang.String phoneNum) {
        this.phoneNum = phoneNum;
    }


    /**
     * Gets the serviceId value for this DdoChargeNotifyReq.
     * 
     * @return serviceId
     */
    public java.lang.String getServiceId() {
        return serviceId;
    }


    /**
     * Sets the serviceId value for this DdoChargeNotifyReq.
     * 
     * @param serviceId
     */
    public void setServiceId(java.lang.String serviceId) {
        this.serviceId = serviceId;
    }


    /**
     * Gets the chargeResult value for this DdoChargeNotifyReq.
     * 
     * @return chargeResult
     */
    public com.huawei.sdp.ib.schema.Result getChargeResult() {
        return chargeResult;
    }


    /**
     * Sets the chargeResult value for this DdoChargeNotifyReq.
     * 
     * @param chargeResult
     */
    public void setChargeResult(com.huawei.sdp.ib.schema.Result chargeResult) {
        this.chargeResult = chargeResult;
    }


    /**
     * Gets the extensionInfo value for this DdoChargeNotifyReq.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.ib.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this DdoChargeNotifyReq.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DdoChargeNotifyReq)) return false;
        DdoChargeNotifyReq other = (DdoChargeNotifyReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.phoneNum==null && other.getPhoneNum()==null) || 
             (this.phoneNum!=null &&
              this.phoneNum.equals(other.getPhoneNum()))) &&
            ((this.serviceId==null && other.getServiceId()==null) || 
             (this.serviceId!=null &&
              this.serviceId.equals(other.getServiceId()))) &&
            ((this.chargeResult==null && other.getChargeResult()==null) || 
             (this.chargeResult!=null &&
              this.chargeResult.equals(other.getChargeResult()))) &&
            ((this.extensionInfo==null && other.getExtensionInfo()==null) || 
             (this.extensionInfo!=null &&
              java.util.Arrays.equals(this.extensionInfo, other.getExtensionInfo())));
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
        if (getPhoneNum() != null) {
            _hashCode += getPhoneNum().hashCode();
        }
        if (getServiceId() != null) {
            _hashCode += getServiceId().hashCode();
        }
        if (getChargeResult() != null) {
            _hashCode += getChargeResult().hashCode();
        }
        if (getExtensionInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtensionInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtensionInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DdoChargeNotifyReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.hrmsservices.ib.sdp.huawei.com", ">DdoChargeNotifyReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phoneNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chargeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.ib.sdp.huawei.com", "Result"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.ib.sdp.huawei.com", "NamedParameter"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schema.ib.sdp.huawei.com", "item"));
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
