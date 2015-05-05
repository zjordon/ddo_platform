/**
 * HrmsServicesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.ib.hrmsservices;

public class HrmsServicesServiceLocator extends org.apache.axis.client.Service implements com.huawei.sdp.ib.hrmsservices.HrmsServicesService {

    public HrmsServicesServiceLocator() {
    }


    public HrmsServicesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HrmsServicesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HrmsServicesPort
    private java.lang.String HrmsServicesPort_address = "http://127.0.0.1:9001/services/HrmsServicesPort";

    public java.lang.String getHrmsServicesPortAddress() {
        return HrmsServicesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HrmsServicesPortWSDDServiceName = "HrmsServicesPort";

    public java.lang.String getHrmsServicesPortWSDDServiceName() {
        return HrmsServicesPortWSDDServiceName;
    }

    public void setHrmsServicesPortWSDDServiceName(java.lang.String name) {
        HrmsServicesPortWSDDServiceName = name;
    }

    public com.huawei.sdp.ib.hrmsservices.HrmsServices getHrmsServicesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HrmsServicesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHrmsServicesPort(endpoint);
    }

    public com.huawei.sdp.ib.hrmsservices.HrmsServices getHrmsServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.huawei.sdp.ib.hrmsservices.HrmsServicesBindingStub _stub = new com.huawei.sdp.ib.hrmsservices.HrmsServicesBindingStub(portAddress, this);
            _stub.setPortName(getHrmsServicesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHrmsServicesPortEndpointAddress(java.lang.String address) {
        HrmsServicesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.huawei.sdp.ib.hrmsservices.HrmsServices.class.isAssignableFrom(serviceEndpointInterface)) {
                com.huawei.sdp.ib.hrmsservices.HrmsServicesBindingStub _stub = new com.huawei.sdp.ib.hrmsservices.HrmsServicesBindingStub(new java.net.URL(HrmsServicesPort_address), this);
                _stub.setPortName(getHrmsServicesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HrmsServicesPort".equals(inputPortName)) {
            return getHrmsServicesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://hrmsservices.ib.sdp.huawei.com", "HrmsServicesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://hrmsservices.ib.sdp.huawei.com", "HrmsServicesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HrmsServicesPort".equals(portName)) {
            setHrmsServicesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
