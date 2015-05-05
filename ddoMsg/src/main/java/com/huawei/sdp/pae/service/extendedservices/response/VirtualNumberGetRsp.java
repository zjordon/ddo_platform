/**
 * VirtualNumberGetRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class VirtualNumberGetRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String billingNumber;

    private java.lang.String virtualNumber;

    public VirtualNumberGetRsp() {
    }

    public VirtualNumberGetRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String billingNumber,
           java.lang.String virtualNumber) {
        super(
            extensionInfo,
            result);
        this.billingNumber = billingNumber;
        this.virtualNumber = virtualNumber;
    }


    /**
     * Gets the billingNumber value for this VirtualNumberGetRsp.
     * 
     * @return billingNumber
     */
    public java.lang.String getBillingNumber() {
        return billingNumber;
    }


    /**
     * Sets the billingNumber value for this VirtualNumberGetRsp.
     * 
     * @param billingNumber
     */
    public void setBillingNumber(java.lang.String billingNumber) {
        this.billingNumber = billingNumber;
    }


    /**
     * Gets the virtualNumber value for this VirtualNumberGetRsp.
     * 
     * @return virtualNumber
     */
    public java.lang.String getVirtualNumber() {
        return virtualNumber;
    }


    /**
     * Sets the virtualNumber value for this VirtualNumberGetRsp.
     * 
     * @param virtualNumber
     */
    public void setVirtualNumber(java.lang.String virtualNumber) {
        this.virtualNumber = virtualNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VirtualNumberGetRsp)) return false;
        VirtualNumberGetRsp other = (VirtualNumberGetRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.billingNumber==null && other.getBillingNumber()==null) || 
             (this.billingNumber!=null &&
              this.billingNumber.equals(other.getBillingNumber()))) &&
            ((this.virtualNumber==null && other.getVirtualNumber()==null) || 
             (this.virtualNumber!=null &&
              this.virtualNumber.equals(other.getVirtualNumber())));
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
        if (getBillingNumber() != null) {
            _hashCode += getBillingNumber().hashCode();
        }
        if (getVirtualNumber() != null) {
            _hashCode += getVirtualNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VirtualNumberGetRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "VirtualNumberGetRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("virtualNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "virtualNumber"));
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
