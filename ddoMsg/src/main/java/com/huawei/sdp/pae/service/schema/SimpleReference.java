/**
 * SimpleReference.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.schema;

public class SimpleReference  implements java.io.Serializable {
    private java.lang.String correlator;

    private java.lang.String endpoint;

    private com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo;

    private java.lang.String interfaceName;

    public SimpleReference() {
    }

    public SimpleReference(
           java.lang.String correlator,
           java.lang.String endpoint,
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String interfaceName) {
           this.correlator = correlator;
           this.endpoint = endpoint;
           this.extensionInfo = extensionInfo;
           this.interfaceName = interfaceName;
    }


    /**
     * Gets the correlator value for this SimpleReference.
     * 
     * @return correlator
     */
    public java.lang.String getCorrelator() {
        return correlator;
    }


    /**
     * Sets the correlator value for this SimpleReference.
     * 
     * @param correlator
     */
    public void setCorrelator(java.lang.String correlator) {
        this.correlator = correlator;
    }


    /**
     * Gets the endpoint value for this SimpleReference.
     * 
     * @return endpoint
     */
    public java.lang.String getEndpoint() {
        return endpoint;
    }


    /**
     * Sets the endpoint value for this SimpleReference.
     * 
     * @param endpoint
     */
    public void setEndpoint(java.lang.String endpoint) {
        this.endpoint = endpoint;
    }


    /**
     * Gets the extensionInfo value for this SimpleReference.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.pae.service.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this SimpleReference.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the interfaceName value for this SimpleReference.
     * 
     * @return interfaceName
     */
    public java.lang.String getInterfaceName() {
        return interfaceName;
    }


    /**
     * Sets the interfaceName value for this SimpleReference.
     * 
     * @param interfaceName
     */
    public void setInterfaceName(java.lang.String interfaceName) {
        this.interfaceName = interfaceName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimpleReference)) return false;
        SimpleReference other = (SimpleReference) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.correlator==null && other.getCorrelator()==null) || 
             (this.correlator!=null &&
              this.correlator.equals(other.getCorrelator()))) &&
            ((this.endpoint==null && other.getEndpoint()==null) || 
             (this.endpoint!=null &&
              this.endpoint.equals(other.getEndpoint()))) &&
            ((this.extensionInfo==null && other.getExtensionInfo()==null) || 
             (this.extensionInfo!=null &&
              java.util.Arrays.equals(this.extensionInfo, other.getExtensionInfo()))) &&
            ((this.interfaceName==null && other.getInterfaceName()==null) || 
             (this.interfaceName!=null &&
              this.interfaceName.equals(other.getInterfaceName())));
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
        if (getCorrelator() != null) {
            _hashCode += getCorrelator().hashCode();
        }
        if (getEndpoint() != null) {
            _hashCode += getEndpoint().hashCode();
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
        if (getInterfaceName() != null) {
            _hashCode += getInterfaceName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimpleReference.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "SimpleReference"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endpoint");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endpoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "NamedParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaceName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "interfaceName"));
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
