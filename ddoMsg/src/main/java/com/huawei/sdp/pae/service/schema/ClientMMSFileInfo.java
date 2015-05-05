/**
 * ClientMMSFileInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.schema;

public class ClientMMSFileInfo  implements java.io.Serializable {
    private com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo;

    private java.lang.String fileContent;

    private java.lang.String fileEntityID;

    private java.lang.String filePath;

    private java.lang.String fileType;

    private java.lang.String sequence;

    private java.lang.String terminalGroups;

    public ClientMMSFileInfo() {
    }

    public ClientMMSFileInfo(
           com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo,
           java.lang.String fileContent,
           java.lang.String fileEntityID,
           java.lang.String filePath,
           java.lang.String fileType,
           java.lang.String sequence,
           java.lang.String terminalGroups) {
           this.extensionInfo = extensionInfo;
           this.fileContent = fileContent;
           this.fileEntityID = fileEntityID;
           this.filePath = filePath;
           this.fileType = fileType;
           this.sequence = sequence;
           this.terminalGroups = terminalGroups;
    }


    /**
     * Gets the extensionInfo value for this ClientMMSFileInfo.
     * 
     * @return extensionInfo
     */
    public com.huawei.sdp.pae.service.schema.NamedParameter[] getExtensionInfo() {
        return extensionInfo;
    }


    /**
     * Sets the extensionInfo value for this ClientMMSFileInfo.
     * 
     * @param extensionInfo
     */
    public void setExtensionInfo(com.huawei.sdp.pae.service.schema.NamedParameter[] extensionInfo) {
        this.extensionInfo = extensionInfo;
    }


    /**
     * Gets the fileContent value for this ClientMMSFileInfo.
     * 
     * @return fileContent
     */
    public java.lang.String getFileContent() {
        return fileContent;
    }


    /**
     * Sets the fileContent value for this ClientMMSFileInfo.
     * 
     * @param fileContent
     */
    public void setFileContent(java.lang.String fileContent) {
        this.fileContent = fileContent;
    }


    /**
     * Gets the fileEntityID value for this ClientMMSFileInfo.
     * 
     * @return fileEntityID
     */
    public java.lang.String getFileEntityID() {
        return fileEntityID;
    }


    /**
     * Sets the fileEntityID value for this ClientMMSFileInfo.
     * 
     * @param fileEntityID
     */
    public void setFileEntityID(java.lang.String fileEntityID) {
        this.fileEntityID = fileEntityID;
    }


    /**
     * Gets the filePath value for this ClientMMSFileInfo.
     * 
     * @return filePath
     */
    public java.lang.String getFilePath() {
        return filePath;
    }


    /**
     * Sets the filePath value for this ClientMMSFileInfo.
     * 
     * @param filePath
     */
    public void setFilePath(java.lang.String filePath) {
        this.filePath = filePath;
    }


    /**
     * Gets the fileType value for this ClientMMSFileInfo.
     * 
     * @return fileType
     */
    public java.lang.String getFileType() {
        return fileType;
    }


    /**
     * Sets the fileType value for this ClientMMSFileInfo.
     * 
     * @param fileType
     */
    public void setFileType(java.lang.String fileType) {
        this.fileType = fileType;
    }


    /**
     * Gets the sequence value for this ClientMMSFileInfo.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this ClientMMSFileInfo.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the terminalGroups value for this ClientMMSFileInfo.
     * 
     * @return terminalGroups
     */
    public java.lang.String getTerminalGroups() {
        return terminalGroups;
    }


    /**
     * Sets the terminalGroups value for this ClientMMSFileInfo.
     * 
     * @param terminalGroups
     */
    public void setTerminalGroups(java.lang.String terminalGroups) {
        this.terminalGroups = terminalGroups;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClientMMSFileInfo)) return false;
        ClientMMSFileInfo other = (ClientMMSFileInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.extensionInfo==null && other.getExtensionInfo()==null) || 
             (this.extensionInfo!=null &&
              java.util.Arrays.equals(this.extensionInfo, other.getExtensionInfo()))) &&
            ((this.fileContent==null && other.getFileContent()==null) || 
             (this.fileContent!=null &&
              this.fileContent.equals(other.getFileContent()))) &&
            ((this.fileEntityID==null && other.getFileEntityID()==null) || 
             (this.fileEntityID!=null &&
              this.fileEntityID.equals(other.getFileEntityID()))) &&
            ((this.filePath==null && other.getFilePath()==null) || 
             (this.filePath!=null &&
              this.filePath.equals(other.getFilePath()))) &&
            ((this.fileType==null && other.getFileType()==null) || 
             (this.fileType!=null &&
              this.fileType.equals(other.getFileType()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence()))) &&
            ((this.terminalGroups==null && other.getTerminalGroups()==null) || 
             (this.terminalGroups!=null &&
              this.terminalGroups.equals(other.getTerminalGroups())));
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
        if (getFileContent() != null) {
            _hashCode += getFileContent().hashCode();
        }
        if (getFileEntityID() != null) {
            _hashCode += getFileEntityID().hashCode();
        }
        if (getFilePath() != null) {
            _hashCode += getFilePath().hashCode();
        }
        if (getFileType() != null) {
            _hashCode += getFileType().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        if (getTerminalGroups() != null) {
            _hashCode += getTerminalGroups().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClientMMSFileInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "ClientMMSFileInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schema.service.pae.sdp.huawei.com", "NamedParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileContent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileEntityID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileEntityID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filePath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalGroups");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalGroups"));
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
