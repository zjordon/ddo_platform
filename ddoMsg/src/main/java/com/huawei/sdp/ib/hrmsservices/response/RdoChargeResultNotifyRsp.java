/**
 * RdoChargeResultNotifyRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.ib.hrmsservices.response;

public class RdoChargeResultNotifyRsp  implements java.io.Serializable {
    private com.huawei.sdp.ib.schema.Result result;

    private com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo;

    public RdoChargeResultNotifyRsp() {
    }

    public RdoChargeResultNotifyRsp(
           com.huawei.sdp.ib.schema.Result result,
           com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo) {
           this.result = result;
           this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the result value for this RdoChargeResultNotifyRsp.
     * 
     * @return result
     */
    public com.huawei.sdp.ib.schema.Result getResult() {
        return result;
    }


    /**
     * Sets the result value for this RdoChargeResultNotifyRsp.
     * 
     * @param result
     */
    public void setResult(com.huawei.sdp.ib.schema.Result result) {
        this.result = result;
    }


    /**
     * Gets the extensionInfo value for this RdoChargeResultNotifyRsp.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.ib.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this RdoChargeResultNotifyRsp.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.ib.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RdoChargeResultNotifyRsp)) return false;
        RdoChargeResultNotifyRsp other = (RdoChargeResultNotifyRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
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
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(RdoChargeResultNotifyRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.hrmsservices.ib.sdp.huawei.com", ">RdoChargeResultNotifyRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("", "result"));
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
