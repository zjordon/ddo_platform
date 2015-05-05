/**
 * ClientMMSInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.schema;

public class ClientMMSInfo  implements java.io.Serializable {
    private java.lang.String contentCode;

    private com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo;

    private com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] lagreMMSFileList;

    private com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] middleMMSFileList;

    private java.lang.String serviceCode;

    private com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] smallMMSFileList;

    public ClientMMSInfo() {
    }

    public ClientMMSInfo(
           java.lang.String contentCode,
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] lagreMMSFileList,
           com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] middleMMSFileList,
           java.lang.String serviceCode,
           com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] smallMMSFileList) {
           this.contentCode = contentCode;
           this.extensionInfo = extensionInfo;
           this.lagreMMSFileList = lagreMMSFileList;
           this.middleMMSFileList = middleMMSFileList;
           this.serviceCode = serviceCode;
           this.smallMMSFileList = smallMMSFileList;
    }


    /**
     * Gets the contentCode value for this ClientMMSInfo.
     * 
     * @return contentCode
     */
    public java.lang.String getContentCode() {
        return contentCode;
    }


    /**
     * Sets the contentCode value for this ClientMMSInfo.
     * 
     * @param contentCode
     */
    public void setContentCode(java.lang.String contentCode) {
        this.contentCode = contentCode;
    }


    /**
     * Gets the extensionInfo value for this ClientMMSInfo.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.pae.service.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this ClientMMSInfo.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the lagreMMSFileList value for this ClientMMSInfo.
     * 
     * @return lagreMMSFileList
     */
    public com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] getLagreMMSFileList() {
        return lagreMMSFileList;
    }


    /**
     * Sets the lagreMMSFileList value for this ClientMMSInfo.
     * 
     * @param lagreMMSFileList
     */
    public void setLagreMMSFileList(com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] lagreMMSFileList) {
        this.lagreMMSFileList = lagreMMSFileList;
    }


    /**
     * Gets the middleMMSFileList value for this ClientMMSInfo.
     * 
     * @return middleMMSFileList
     */
    public com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] getMiddleMMSFileList() {
        return middleMMSFileList;
    }


    /**
     * Sets the middleMMSFileList value for this ClientMMSInfo.
     * 
     * @param middleMMSFileList
     */
    public void setMiddleMMSFileList(com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] middleMMSFileList) {
        this.middleMMSFileList = middleMMSFileList;
    }


    /**
     * Gets the serviceCode value for this ClientMMSInfo.
     * 
     * @return serviceCode
     */
    public java.lang.String getServiceCode() {
        return serviceCode;
    }


    /**
     * Sets the serviceCode value for this ClientMMSInfo.
     * 
     * @param serviceCode
     */
    public void setServiceCode(java.lang.String serviceCode) {
        this.serviceCode = serviceCode;
    }


    /**
     * Gets the smallMMSFileList value for this ClientMMSInfo.
     * 
     * @return smallMMSFileList
     */
    public com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] getSmallMMSFileList() {
        return smallMMSFileList;
    }


    /**
     * Sets the smallMMSFileList value for this ClientMMSInfo.
     * 
     * @param smallMMSFileList
     */
    public void setSmallMMSFileList(com.huawei.sdp.pae.service.schema.ClientMMSFileInfo[] smallMMSFileList) {
        this.smallMMSFileList = smallMMSFileList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClientMMSInfo)) return false;
        ClientMMSInfo other = (ClientMMSInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contentCode==null && other.getContentCode()==null) || 
             (this.contentCode!=null &&
              this.contentCode.equals(other.getContentCode()))) &&
            ((this.extensionInfo==null && other.getExtensionInfo()==null) || 
             (this.extensionInfo!=null &&
              java.util.Arrays.equals(this.extensionInfo, other.getExtensionInfo()))) &&
            ((this.lagreMMSFileList==null && other.getLagreMMSFileList()==null) || 
             (this.lagreMMSFileList!=null &&
              java.util.Arrays.equals(this.lagreMMSFileList, other.getLagreMMSFileList()))) &&
            ((this.middleMMSFileList==null && other.getMiddleMMSFileList()==null) || 
             (this.middleMMSFileList!=null &&
              java.util.Arrays.equals(this.middleMMSFileList, other.getMiddleMMSFileList()))) &&
            ((this.serviceCode==null && other.getServiceCode()==null) || 
             (this.serviceCode!=null &&
              this.serviceCode.equals(other.getServiceCode()))) &&
            ((this.smallMMSFileList==null && other.getSmallMMSFileList()==null) || 
             (this.smallMMSFileList!=null &&
              java.util.Arrays.equals(this.smallMMSFileList, other.getSmallMMSFileList())));
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
        if (getContentCode() != null) {
            _hashCode += getContentCode().hashCode();
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
        if (getLagreMMSFileList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLagreMMSFileList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLagreMMSFileList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMiddleMMSFileList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMiddleMMSFileList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMiddleMMSFileList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceCode() != null) {
            _hashCode += getServiceCode().hashCode();
        }
        if (getSmallMMSFileList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSmallMMSFileList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSmallMMSFileList(), i);
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
        new org.apache.axis.description.TypeDesc(ClientMMSInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentCode"));
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
        elemField.setFieldName("lagreMMSFileList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lagreMMSFileList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleMMSFileList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "middleMMSFileList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smallMMSFileList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smallMMSFileList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo"));
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
