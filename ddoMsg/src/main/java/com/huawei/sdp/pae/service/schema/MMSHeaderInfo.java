/**
 * MMSHeaderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.schema;

public class MMSHeaderInfo  implements java.io.Serializable {
    private java.lang.String FA;

    private java.lang.String OA;

    private com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo;

    private java.lang.String serviceId;

    private java.lang.String spId;

    private java.lang.String spPassword;

    private java.lang.String timeStamp;

    public MMSHeaderInfo() {
    }

    public MMSHeaderInfo(
           java.lang.String FA,
           java.lang.String OA,
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String serviceId,
           java.lang.String spId,
           java.lang.String spPassword,
           java.lang.String timeStamp) {
           this.FA = FA;
           this.OA = OA;
           this.extensionInfo = extensionInfo;
           this.serviceId = serviceId;
           this.spId = spId;
           this.spPassword = spPassword;
           this.timeStamp = timeStamp;
    }


    /**
     * Gets the FA value for this MMSHeaderInfo.
     * 
     * @return FA
     */
    public java.lang.String getFA() {
        return FA;
    }


    /**
     * Sets the FA value for this MMSHeaderInfo.
     * 
     * @param FA
     */
    public void setFA(java.lang.String FA) {
        this.FA = FA;
    }


    /**
     * Gets the OA value for this MMSHeaderInfo.
     * 
     * @return OA
     */
    public java.lang.String getOA() {
        return OA;
    }


    /**
     * Sets the OA value for this MMSHeaderInfo.
     * 
     * @param OA
     */
    public void setOA(java.lang.String OA) {
        this.OA = OA;
    }


    /**
     * Gets the extensionInfo value for this MMSHeaderInfo.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.pae.service.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this MMSHeaderInfo.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the serviceId value for this MMSHeaderInfo.
     * 
     * @return serviceId
     */
    public java.lang.String getServiceId() {
        return serviceId;
    }


    /**
     * Sets the serviceId value for this MMSHeaderInfo.
     * 
     * @param serviceId
     */
    public void setServiceId(java.lang.String serviceId) {
        this.serviceId = serviceId;
    }


    /**
     * Gets the spId value for this MMSHeaderInfo.
     * 
     * @return spId
     */
    public java.lang.String getSpId() {
        return spId;
    }


    /**
     * Sets the spId value for this MMSHeaderInfo.
     * 
     * @param spId
     */
    public void setSpId(java.lang.String spId) {
        this.spId = spId;
    }


    /**
     * Gets the spPassword value for this MMSHeaderInfo.
     * 
     * @return spPassword
     */
    public java.lang.String getSpPassword() {
        return spPassword;
    }


    /**
     * Sets the spPassword value for this MMSHeaderInfo.
     * 
     * @param spPassword
     */
    public void setSpPassword(java.lang.String spPassword) {
        this.spPassword = spPassword;
    }


    /**
     * Gets the timeStamp value for this MMSHeaderInfo.
     * 
     * @return timeStamp
     */
    public java.lang.String getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this MMSHeaderInfo.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MMSHeaderInfo)) return false;
        MMSHeaderInfo other = (MMSHeaderInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.FA==null && other.getFA()==null) || 
             (this.FA!=null &&
              this.FA.equals(other.getFA()))) &&
            ((this.OA==null && other.getOA()==null) || 
             (this.OA!=null &&
              this.OA.equals(other.getOA()))) &&
            ((this.extensionInfo==null && other.getExtensionInfo()==null) || 
             (this.extensionInfo!=null &&
              java.util.Arrays.equals(this.extensionInfo, other.getExtensionInfo()))) &&
            ((this.serviceId==null && other.getServiceId()==null) || 
             (this.serviceId!=null &&
              this.serviceId.equals(other.getServiceId()))) &&
            ((this.spId==null && other.getSpId()==null) || 
             (this.spId!=null &&
              this.spId.equals(other.getSpId()))) &&
            ((this.spPassword==null && other.getSpPassword()==null) || 
             (this.spPassword!=null &&
              this.spPassword.equals(other.getSpPassword()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp())));
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
        if (getFA() != null) {
            _hashCode += getFA().hashCode();
        }
        if (getOA() != null) {
            _hashCode += getOA().hashCode();
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
        if (getServiceId() != null) {
            _hashCode += getServiceId().hashCode();
        }
        if (getSpId() != null) {
            _hashCode += getSpId().hashCode();
        }
        if (getSpPassword() != null) {
            _hashCode += getSpPassword().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MMSHeaderInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "MMSHeaderInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OA"));
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
        elemField.setFieldName("serviceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeStamp"));
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
