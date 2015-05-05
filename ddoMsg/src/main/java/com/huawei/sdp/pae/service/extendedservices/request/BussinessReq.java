/**
 * BussinessReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices.request;

public class BussinessReq  extends com.huawei.sdp.pae.service.schema.RequestCommon  implements java.io.Serializable {
    private java.lang.String accountId;

    private java.lang.String accountPwd;

    private java.lang.String authenticator;

    private java.lang.String batchNO;

    private java.lang.String businessId;

    private java.lang.String isFree;

    private java.lang.String isTelecom;

    private java.lang.String mmsId;

    private java.lang.String mmsSubject;

    private java.lang.String priority;

    private java.lang.String recipient;

    private java.lang.String sendDate;

    private java.lang.String sender;

    private java.lang.String timeStamp;

    private java.lang.String tradeNo;

    private java.lang.String transactionType;

    private java.lang.String type;

    private java.lang.String userId;

    public BussinessReq() {
    }

    public BussinessReq(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String accountId,
           java.lang.String accountPwd,
           java.lang.String authenticator,
           java.lang.String batchNO,
           java.lang.String businessId,
           java.lang.String isFree,
           java.lang.String isTelecom,
           java.lang.String mmsId,
           java.lang.String mmsSubject,
           java.lang.String priority,
           java.lang.String recipient,
           java.lang.String sendDate,
           java.lang.String sender,
           java.lang.String timeStamp,
           java.lang.String tradeNo,
           java.lang.String transactionType,
           java.lang.String type,
           java.lang.String userId) {
        super(
            extensionInfo);
        this.accountId = accountId;
        this.accountPwd = accountPwd;
        this.authenticator = authenticator;
        this.batchNO = batchNO;
        this.businessId = businessId;
        this.isFree = isFree;
        this.isTelecom = isTelecom;
        this.mmsId = mmsId;
        this.mmsSubject = mmsSubject;
        this.priority = priority;
        this.recipient = recipient;
        this.sendDate = sendDate;
        this.sender = sender;
        this.timeStamp = timeStamp;
        this.tradeNo = tradeNo;
        this.transactionType = transactionType;
        this.type = type;
        this.userId = userId;
    }


    /**
     * Gets the accountId value for this BussinessReq.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this BussinessReq.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountPwd value for this BussinessReq.
     * 
     * @return accountPwd
     */
    public java.lang.String getAccountPwd() {
        return accountPwd;
    }


    /**
     * Sets the accountPwd value for this BussinessReq.
     * 
     * @param accountPwd
     */
    public void setAccountPwd(java.lang.String accountPwd) {
        this.accountPwd = accountPwd;
    }


    /**
     * Gets the authenticator value for this BussinessReq.
     * 
     * @return authenticator
     */
    public java.lang.String getAuthenticator() {
        return authenticator;
    }


    /**
     * Sets the authenticator value for this BussinessReq.
     * 
     * @param authenticator
     */
    public void setAuthenticator(java.lang.String authenticator) {
        this.authenticator = authenticator;
    }


    /**
     * Gets the batchNO value for this BussinessReq.
     * 
     * @return batchNO
     */
    public java.lang.String getBatchNO() {
        return batchNO;
    }


    /**
     * Sets the batchNO value for this BussinessReq.
     * 
     * @param batchNO
     */
    public void setBatchNO(java.lang.String batchNO) {
        this.batchNO = batchNO;
    }


    /**
     * Gets the businessId value for this BussinessReq.
     * 
     * @return businessId
     */
    public java.lang.String getBusinessId() {
        return businessId;
    }


    /**
     * Sets the businessId value for this BussinessReq.
     * 
     * @param businessId
     */
    public void setBusinessId(java.lang.String businessId) {
        this.businessId = businessId;
    }


    /**
     * Gets the isFree value for this BussinessReq.
     * 
     * @return isFree
     */
    public java.lang.String getIsFree() {
        return isFree;
    }


    /**
     * Sets the isFree value for this BussinessReq.
     * 
     * @param isFree
     */
    public void setIsFree(java.lang.String isFree) {
        this.isFree = isFree;
    }


    /**
     * Gets the isTelecom value for this BussinessReq.
     * 
     * @return isTelecom
     */
    public java.lang.String getIsTelecom() {
        return isTelecom;
    }


    /**
     * Sets the isTelecom value for this BussinessReq.
     * 
     * @param isTelecom
     */
    public void setIsTelecom(java.lang.String isTelecom) {
        this.isTelecom = isTelecom;
    }


    /**
     * Gets the mmsId value for this BussinessReq.
     * 
     * @return mmsId
     */
    public java.lang.String getMmsId() {
        return mmsId;
    }


    /**
     * Sets the mmsId value for this BussinessReq.
     * 
     * @param mmsId
     */
    public void setMmsId(java.lang.String mmsId) {
        this.mmsId = mmsId;
    }


    /**
     * Gets the mmsSubject value for this BussinessReq.
     * 
     * @return mmsSubject
     */
    public java.lang.String getMmsSubject() {
        return mmsSubject;
    }


    /**
     * Sets the mmsSubject value for this BussinessReq.
     * 
     * @param mmsSubject
     */
    public void setMmsSubject(java.lang.String mmsSubject) {
        this.mmsSubject = mmsSubject;
    }


    /**
     * Gets the priority value for this BussinessReq.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this BussinessReq.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the recipient value for this BussinessReq.
     * 
     * @return recipient
     */
    public java.lang.String getRecipient() {
        return recipient;
    }


    /**
     * Sets the recipient value for this BussinessReq.
     * 
     * @param recipient
     */
    public void setRecipient(java.lang.String recipient) {
        this.recipient = recipient;
    }


    /**
     * Gets the sendDate value for this BussinessReq.
     * 
     * @return sendDate
     */
    public java.lang.String getSendDate() {
        return sendDate;
    }


    /**
     * Sets the sendDate value for this BussinessReq.
     * 
     * @param sendDate
     */
    public void setSendDate(java.lang.String sendDate) {
        this.sendDate = sendDate;
    }


    /**
     * Gets the sender value for this BussinessReq.
     * 
     * @return sender
     */
    public java.lang.String getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this BussinessReq.
     * 
     * @param sender
     */
    public void setSender(java.lang.String sender) {
        this.sender = sender;
    }


    /**
     * Gets the timeStamp value for this BussinessReq.
     * 
     * @return timeStamp
     */
    public java.lang.String getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this BussinessReq.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.String timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the tradeNo value for this BussinessReq.
     * 
     * @return tradeNo
     */
    public java.lang.String getTradeNo() {
        return tradeNo;
    }


    /**
     * Sets the tradeNo value for this BussinessReq.
     * 
     * @param tradeNo
     */
    public void setTradeNo(java.lang.String tradeNo) {
        this.tradeNo = tradeNo;
    }


    /**
     * Gets the transactionType value for this BussinessReq.
     * 
     * @return transactionType
     */
    public java.lang.String getTransactionType() {
        return transactionType;
    }


    /**
     * Sets the transactionType value for this BussinessReq.
     * 
     * @param transactionType
     */
    public void setTransactionType(java.lang.String transactionType) {
        this.transactionType = transactionType;
    }


    /**
     * Gets the type value for this BussinessReq.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this BussinessReq.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the userId value for this BussinessReq.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this BussinessReq.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BussinessReq)) return false;
        BussinessReq other = (BussinessReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accountId==null && other.getAccountId()==null) || 
             (this.accountId!=null &&
              this.accountId.equals(other.getAccountId()))) &&
            ((this.accountPwd==null && other.getAccountPwd()==null) || 
             (this.accountPwd!=null &&
              this.accountPwd.equals(other.getAccountPwd()))) &&
            ((this.authenticator==null && other.getAuthenticator()==null) || 
             (this.authenticator!=null &&
              this.authenticator.equals(other.getAuthenticator()))) &&
            ((this.batchNO==null && other.getBatchNO()==null) || 
             (this.batchNO!=null &&
              this.batchNO.equals(other.getBatchNO()))) &&
            ((this.businessId==null && other.getBusinessId()==null) || 
             (this.businessId!=null &&
              this.businessId.equals(other.getBusinessId()))) &&
            ((this.isFree==null && other.getIsFree()==null) || 
             (this.isFree!=null &&
              this.isFree.equals(other.getIsFree()))) &&
            ((this.isTelecom==null && other.getIsTelecom()==null) || 
             (this.isTelecom!=null &&
              this.isTelecom.equals(other.getIsTelecom()))) &&
            ((this.mmsId==null && other.getMmsId()==null) || 
             (this.mmsId!=null &&
              this.mmsId.equals(other.getMmsId()))) &&
            ((this.mmsSubject==null && other.getMmsSubject()==null) || 
             (this.mmsSubject!=null &&
              this.mmsSubject.equals(other.getMmsSubject()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.recipient==null && other.getRecipient()==null) || 
             (this.recipient!=null &&
              this.recipient.equals(other.getRecipient()))) &&
            ((this.sendDate==null && other.getSendDate()==null) || 
             (this.sendDate!=null &&
              this.sendDate.equals(other.getSendDate()))) &&
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.tradeNo==null && other.getTradeNo()==null) || 
             (this.tradeNo!=null &&
              this.tradeNo.equals(other.getTradeNo()))) &&
            ((this.transactionType==null && other.getTransactionType()==null) || 
             (this.transactionType!=null &&
              this.transactionType.equals(other.getTransactionType()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId())));
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
        if (getAccountId() != null) {
            _hashCode += getAccountId().hashCode();
        }
        if (getAccountPwd() != null) {
            _hashCode += getAccountPwd().hashCode();
        }
        if (getAuthenticator() != null) {
            _hashCode += getAuthenticator().hashCode();
        }
        if (getBatchNO() != null) {
            _hashCode += getBatchNO().hashCode();
        }
        if (getBusinessId() != null) {
            _hashCode += getBusinessId().hashCode();
        }
        if (getIsFree() != null) {
            _hashCode += getIsFree().hashCode();
        }
        if (getIsTelecom() != null) {
            _hashCode += getIsTelecom().hashCode();
        }
        if (getMmsId() != null) {
            _hashCode += getMmsId().hashCode();
        }
        if (getMmsSubject() != null) {
            _hashCode += getMmsSubject().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getRecipient() != null) {
            _hashCode += getRecipient().hashCode();
        }
        if (getSendDate() != null) {
            _hashCode += getSendDate().hashCode();
        }
        if (getSender() != null) {
            _hashCode += getSender().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getTradeNo() != null) {
            _hashCode += getTradeNo().hashCode();
        }
        if (getTransactionType() != null) {
            _hashCode += getTransactionType().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BussinessReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.extendedservices.service.pae.sdp.huawei.com", "BussinessReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authenticator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "batchNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "businessId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isFree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTelecom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isTelecom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mmsId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mmsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mmsSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mmsSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipient");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
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
