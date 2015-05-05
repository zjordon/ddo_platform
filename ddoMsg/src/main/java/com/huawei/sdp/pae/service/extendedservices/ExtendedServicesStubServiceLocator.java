/**
 * ExtendedServicesStubServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.huawei.sdp.pae.service.extendedservices;

public class ExtendedServicesStubServiceLocator extends org.apache.axis.client.Service implements com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStubService {

    public ExtendedServicesStubServiceLocator() {
    }


    public ExtendedServicesStubServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ExtendedServicesStubServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ExtendedServices
    private java.lang.String ExtendedServices_address = "http://218.207.208.30:8080/pae/soap/ExtendedServices";

    public java.lang.String getExtendedServicesAddress() {
        return ExtendedServices_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ExtendedServicesWSDDServiceName = "ExtendedServices";

    public java.lang.String getExtendedServicesWSDDServiceName() {
        return ExtendedServicesWSDDServiceName;
    }

    public void setExtendedServicesWSDDServiceName(java.lang.String name) {
        ExtendedServicesWSDDServiceName = name;
    }

    public com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStub getExtendedServices() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ExtendedServices_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getExtendedServices(endpoint);
    }

    public com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStub getExtendedServices(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.huawei.sdp.pae.service.extendedservices.ExtendedServicesSoapBindingStub _stub = new com.huawei.sdp.pae.service.extendedservices.ExtendedServicesSoapBindingStub(portAddress, this);
            _stub.setPortName(getExtendedServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setExtendedServicesEndpointAddress(java.lang.String address) {
        ExtendedServices_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.huawei.sdp.pae.service.extendedservices.ExtendedServicesStub.class.isAssignableFrom(serviceEndpointInterface)) {
                com.huawei.sdp.pae.service.extendedservices.ExtendedServicesSoapBindingStub _stub = new com.huawei.sdp.pae.service.extendedservices.ExtendedServicesSoapBindingStub(new java.net.URL(ExtendedServices_address), this);
                _stub.setPortName(getExtendedServicesWSDDServiceName());
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
        if ("ExtendedServices".equals(inputPortName)) {
            return getExtendedServices();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ExtendedServicesStubService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://extendedservices.service.pae.sdp.huawei.com", "ExtendedServices"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ExtendedServices".equals(portName)) {
            setExtendedServicesEndpointAddress(address);
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
