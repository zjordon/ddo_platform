/**
 * GetIPBlackListRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class GetIPBlackListRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String[] ipBlackList;

    public GetIPBlackListRsp() {
    }

    public GetIPBlackListRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String[] ipBlackList) {
        super(
            extensionInfo,
            result);
        this.ipBlackList = ipBlackList;
    }


    /**
     * Gets the ipBlackList value for this GetIPBlackListRsp.
     * 
     * @return ipBlackList
     */
    public java.lang.String[] getIpBlackList() {
        return ipBlackList;
    }


    /**
     * Sets the ipBlackList value for this GetIPBlackListRsp.
     * 
     * @param ipBlackList
     */
    public void setIpBlackList(java.lang.String[] ipBlackList) {
        this.ipBlackList = ipBlackList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetIPBlackListRsp)) return false;
        GetIPBlackListRsp other = (GetIPBlackListRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ipBlackList==null && other.getIpBlackList()==null) || 
             (this.ipBlackList!=null &&
              java.util.Arrays.equals(this.ipBlackList, other.getIpBlackList())));
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
        if (getIpBlackList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIpBlackList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIpBlackList(), i);
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
        new org.apache.axis.description.TypeDesc(GetIPBlackListRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GetIPBlackListRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipBlackList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipBlackList"));
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
