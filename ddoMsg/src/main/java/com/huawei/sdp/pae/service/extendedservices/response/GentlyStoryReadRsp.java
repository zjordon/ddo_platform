/**
 * GentlyStoryReadRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.response;

public class GentlyStoryReadRsp  extends com.huawei.sdp.pae.service.schema.ResponseCommon  implements java.io.Serializable {
    private java.lang.String[] largePicHtml;

    private java.lang.String[] middlePicHtml;

    private java.lang.String[] smallPicHtml;

    public GentlyStoryReadRsp() {
    }

    public GentlyStoryReadRsp(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           com.huawei.sdp.pae.service.schema.Result result,
           java.lang.String[] largePicHtml,
           java.lang.String[] middlePicHtml,
           java.lang.String[] smallPicHtml) {
        super(
            extensionInfo,
            result);
        this.largePicHtml = largePicHtml;
        this.middlePicHtml = middlePicHtml;
        this.smallPicHtml = smallPicHtml;
    }


    /**
     * Gets the largePicHtml value for this GentlyStoryReadRsp.
     * 
     * @return largePicHtml
     */
    public java.lang.String[] getLargePicHtml() {
        return largePicHtml;
    }


    /**
     * Sets the largePicHtml value for this GentlyStoryReadRsp.
     * 
     * @param largePicHtml
     */
    public void setLargePicHtml(java.lang.String[] largePicHtml) {
        this.largePicHtml = largePicHtml;
    }


    /**
     * Gets the middlePicHtml value for this GentlyStoryReadRsp.
     * 
     * @return middlePicHtml
     */
    public java.lang.String[] getMiddlePicHtml() {
        return middlePicHtml;
    }


    /**
     * Sets the middlePicHtml value for this GentlyStoryReadRsp.
     * 
     * @param middlePicHtml
     */
    public void setMiddlePicHtml(java.lang.String[] middlePicHtml) {
        this.middlePicHtml = middlePicHtml;
    }


    /**
     * Gets the smallPicHtml value for this GentlyStoryReadRsp.
     * 
     * @return smallPicHtml
     */
    public java.lang.String[] getSmallPicHtml() {
        return smallPicHtml;
    }


    /**
     * Sets the smallPicHtml value for this GentlyStoryReadRsp.
     * 
     * @param smallPicHtml
     */
    public void setSmallPicHtml(java.lang.String[] smallPicHtml) {
        this.smallPicHtml = smallPicHtml;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GentlyStoryReadRsp)) return false;
        GentlyStoryReadRsp other = (GentlyStoryReadRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.largePicHtml==null && other.getLargePicHtml()==null) || 
             (this.largePicHtml!=null &&
              java.util.Arrays.equals(this.largePicHtml, other.getLargePicHtml()))) &&
            ((this.middlePicHtml==null && other.getMiddlePicHtml()==null) || 
             (this.middlePicHtml!=null &&
              java.util.Arrays.equals(this.middlePicHtml, other.getMiddlePicHtml()))) &&
            ((this.smallPicHtml==null && other.getSmallPicHtml()==null) || 
             (this.smallPicHtml!=null &&
              java.util.Arrays.equals(this.smallPicHtml, other.getSmallPicHtml())));
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
        if (getLargePicHtml() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLargePicHtml());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLargePicHtml(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMiddlePicHtml() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMiddlePicHtml());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMiddlePicHtml(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSmallPicHtml() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSmallPicHtml());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSmallPicHtml(), i);
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
        new org.apache.axis.description.TypeDesc(GentlyStoryReadRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.extendedservices.service.pae.sdp.huawei.com", "GentlyStoryReadRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("largePicHtml");
        elemField.setXmlName(new javax.xml.namespace.QName("", "largePicHtml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middlePicHtml");
        elemField.setXmlName(new javax.xml.namespace.QName("", "middlePicHtml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smallPicHtml");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smallPicHtml"));
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
