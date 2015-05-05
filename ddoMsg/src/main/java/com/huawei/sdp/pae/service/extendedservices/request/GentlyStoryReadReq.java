/**
 * GentlyStoryReadReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class GentlyStoryReadReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String contentCode;

    public GentlyStoryReadReq() {
    }

    public GentlyStoryReadReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String contentCode) {
        super(
            extensionInfo);
        this.contentCode = contentCode;
    }


    /**
     * Gets the contentCode value for this GentlyStoryReadReq.
     * 
     * @return contentCode
     */
    public java.lang.String getContentCode() {
        return contentCode;
    }


    /**
     * Sets the contentCode value for this GentlyStoryReadReq.
     * 
     * @param contentCode
     */
    public void setContentCode(java.lang.String contentCode) {
        this.contentCode = contentCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GentlyStoryReadReq)) return false;
        GentlyStoryReadReq other = (GentlyStoryReadReq) obj;
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
              this.contentCode.equals(other.getContentCode())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GentlyStoryReadReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentCode"));
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
