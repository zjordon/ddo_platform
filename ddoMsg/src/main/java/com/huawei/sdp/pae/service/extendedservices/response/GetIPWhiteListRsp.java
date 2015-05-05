/**
 * GetIPWhiteListRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class GetIPWhiteListRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String[] ipWhiteList;

    public GetIPWhiteListRsp() {
    }

    public GetIPWhiteListRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String[] ipWhiteList) {
        super(
            extensionInfo,
            result);
        this.ipWhiteList = ipWhiteList;
    }


    /**
     * Gets the ipWhiteList value for this GetIPWhiteListRsp.
     * 
     * @return ipWhiteList
     */
    public java.lang.String[] getIpWhiteList() {
        return ipWhiteList;
    }


    /**
     * Sets the ipWhiteList value for this GetIPWhiteListRsp.
     * 
     * @param ipWhiteList
     */
    public void setIpWhiteList(java.lang.String[] ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetIPWhiteListRsp)) return false;
        GetIPWhiteListRsp other = (GetIPWhiteListRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ipWhiteList==null && other.getIpWhiteList()==null) || 
             (this.ipWhiteList!=null &&
              java.util.Arrays.equals(this.ipWhiteList, other.getIpWhiteList())));
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
        if (getIpWhiteList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIpWhiteList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIpWhiteList(), i);
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
        new org.apache.axis.description.TypeDesc(GetIPWhiteListRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPWhiteListRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipWhiteList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipWhiteList"));
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
