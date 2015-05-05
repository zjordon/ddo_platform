/**
 * AddBlackIPReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class AddBlackIPReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private com.huawei.sdp.pae.service.schema.BlackIP blackIP;

    public AddBlackIPReq() {
    }

    public AddBlackIPReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.BlackIP blackIP) {
        super(
            extensionInfo);
        this.blackIP = blackIP;
    }


    /**
     * Gets the blackIP value for this AddBlackIPReq.
     * 
     * @return blackIP
     */
    public com.huawei.sdp.pae.service.schema.BlackIP getBlackIP() {
        return blackIP;
    }


    /**
     * Sets the blackIP value for this AddBlackIPReq.
     * 
     * @param blackIP
     */
    public void setBlackIP(com.huawei.sdp.pae.service.schema.BlackIP blackIP) {
        this.blackIP = blackIP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddBlackIPReq)) return false;
        AddBlackIPReq other = (AddBlackIPReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.blackIP==null && other.getBlackIP()==null) || 
             (this.blackIP!=null &&
              this.blackIP.equals(other.getBlackIP())));
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
        if (getBlackIP() != null) {
            _hashCode += getBlackIP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddBlackIPReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "AddBlackIPReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "BlackIP"));
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
