/**
 * RdoChargeReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class RdoChargeReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String asyncNotifyURL;

    private java.lang.String channelId;

    private java.lang.String msisdn;

    private java.lang.String serviceId;

    private java.lang.String spId;

    public RdoChargeReq() {
    }

    public RdoChargeReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String asyncNotifyURL,
           java.lang.String channelId,
           java.lang.String msisdn,
           java.lang.String serviceId,
           java.lang.String spId) {
        super(
            extensionInfo);
        this.asyncNotifyURL = asyncNotifyURL;
        this.channelId = channelId;
        this.msisdn = msisdn;
        this.serviceId = serviceId;
        this.spId = spId;
    }


    /**
     * Gets the asyncNotifyURL value for this RdoChargeReq.
     * 
     * @return asyncNotifyURL
     */
    public java.lang.String getAsyncNotifyURL() {
        return asyncNotifyURL;
    }


    /**
     * Sets the asyncNotifyURL value for this RdoChargeReq.
     * 
     * @param asyncNotifyURL
     */
    public void setAsyncNotifyURL(java.lang.String asyncNotifyURL) {
        this.asyncNotifyURL = asyncNotifyURL;
    }


    /**
     * Gets the channelId value for this RdoChargeReq.
     * 
     * @return channelId
     */
    public java.lang.String getChannelId() {
        return channelId;
    }


    /**
     * Sets the channelId value for this RdoChargeReq.
     * 
     * @param channelId
     */
    public void setChannelId(java.lang.String channelId) {
        this.channelId = channelId;
    }


    /**
     * Gets the msisdn value for this RdoChargeReq.
     * 
     * @return msisdn
     */
    public java.lang.String getMsisdn() {
        return msisdn;
    }


    /**
     * Sets the msisdn value for this RdoChargeReq.
     * 
     * @param msisdn
     */
    public void setMsisdn(java.lang.String msisdn) {
        this.msisdn = msisdn;
    }


    /**
     * Gets the serviceId value for this RdoChargeReq.
     * 
     * @return serviceId
     */
    public java.lang.String getServiceId() {
        return serviceId;
    }


    /**
     * Sets the serviceId value for this RdoChargeReq.
     * 
     * @param serviceId
     */
    public void setServiceId(java.lang.String serviceId) {
        this.serviceId = serviceId;
    }


    /**
     * Gets the spId value for this RdoChargeReq.
     * 
     * @return spId
     */
    public java.lang.String getSpId() {
        return spId;
    }


    /**
     * Sets the spId value for this RdoChargeReq.
     * 
     * @param spId
     */
    public void setSpId(java.lang.String spId) {
        this.spId = spId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RdoChargeReq)) return false;
        RdoChargeReq other = (RdoChargeReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.asyncNotifyURL==null && other.getAsyncNotifyURL()==null) || 
             (this.asyncNotifyURL!=null &&
              this.asyncNotifyURL.equals(other.getAsyncNotifyURL()))) &&
            ((this.channelId==null && other.getChannelId()==null) || 
             (this.channelId!=null &&
              this.channelId.equals(other.getChannelId()))) &&
            ((this.msisdn==null && other.getMsisdn()==null) || 
             (this.msisdn!=null &&
              this.msisdn.equals(other.getMsisdn()))) &&
            ((this.serviceId==null && other.getServiceId()==null) || 
             (this.serviceId!=null &&
              this.serviceId.equals(other.getServiceId()))) &&
            ((this.spId==null && other.getSpId()==null) || 
             (this.spId!=null &&
              this.spId.equals(other.getSpId())));
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
        if (getAsyncNotifyURL() != null) {
            _hashCode += getAsyncNotifyURL().hashCode();
        }
        if (getChannelId() != null) {
            _hashCode += getChannelId().hashCode();
        }
        if (getMsisdn() != null) {
            _hashCode += getMsisdn().hashCode();
        }
        if (getServiceId() != null) {
            _hashCode += getServiceId().hashCode();
        }
        if (getSpId() != null) {
            _hashCode += getSpId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RdoChargeReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "RdoChargeReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asyncNotifyURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asyncNotifyURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("channelId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "channelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msisdn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msisdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
