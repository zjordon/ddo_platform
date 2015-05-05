/**
 * NotifySmsCXReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class NotifySmsCXReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private com.huawei.sdp.pae.service.schema.UserID sourceUser;

    private com.huawei.sdp.pae.service.schema.UserID targetUser;

    private java.lang.Integer type;

    public NotifySmsCXReq() {
    }

    public NotifySmsCXReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.UserID sourceUser,
           com.huawei.sdp.pae.service.schema.UserID targetUser,
           java.lang.Integer type) {
        super(
            extensionInfo);
        this.sourceUser = sourceUser;
        this.targetUser = targetUser;
        this.type = type;
    }


    /**
     * Gets the sourceUser value for this NotifySmsCXReq.
     * 
     * @return sourceUser
     */
    public com.huawei.sdp.pae.service.schema.UserID getSourceUser() {
        return sourceUser;
    }


    /**
     * Sets the sourceUser value for this NotifySmsCXReq.
     * 
     * @param sourceUser
     */
    public void setSourceUser(com.huawei.sdp.pae.service.schema.UserID sourceUser) {
        this.sourceUser = sourceUser;
    }


    /**
     * Gets the targetUser value for this NotifySmsCXReq.
     * 
     * @return targetUser
     */
    public com.huawei.sdp.pae.service.schema.UserID getTargetUser() {
        return targetUser;
    }


    /**
     * Sets the targetUser value for this NotifySmsCXReq.
     * 
     * @param targetUser
     */
    public void setTargetUser(com.huawei.sdp.pae.service.schema.UserID targetUser) {
        this.targetUser = targetUser;
    }


    /**
     * Gets the type value for this NotifySmsCXReq.
     * 
     * @return type
     */
    public java.lang.Integer getType() {
        return type;
    }


    /**
     * Sets the type value for this NotifySmsCXReq.
     * 
     * @param type
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotifySmsCXReq)) return false;
        NotifySmsCXReq other = (NotifySmsCXReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sourceUser==null && other.getSourceUser()==null) || 
             (this.sourceUser!=null &&
              this.sourceUser.equals(other.getSourceUser()))) &&
            ((this.targetUser==null && other.getTargetUser()==null) || 
             (this.targetUser!=null &&
              this.targetUser.equals(other.getTargetUser()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getSourceUser() != null) {
            _hashCode += getSourceUser().hashCode();
        }
        if (getTargetUser() != null) {
            _hashCode += getTargetUser().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NotifySmsCXReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "NotifySmsCXReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "UserID"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "targetUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "UserID"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
