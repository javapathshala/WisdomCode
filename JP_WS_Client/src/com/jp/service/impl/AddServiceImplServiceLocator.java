/**
 * AddServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jp.service.impl;

public class AddServiceImplServiceLocator extends org.apache.axis.client.Service implements com.jp.service.impl.AddServiceImplService {

    public AddServiceImplServiceLocator() {
    }


    public AddServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AddServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AddServiceImpl
    private java.lang.String AddServiceImpl_address = "http://localhost:8080/JP_WS/services/AddServiceImpl";
   // private java.lang.String AddServiceImpl_address = "https://localhost:8443/JP_WS/services/AddServiceImpl";

    public java.lang.String getAddServiceImplAddress() {
        return AddServiceImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AddServiceImplWSDDServiceName = "AddServiceImpl";

    public java.lang.String getAddServiceImplWSDDServiceName() {
        return AddServiceImplWSDDServiceName;
    }

    public void setAddServiceImplWSDDServiceName(java.lang.String name) {
        AddServiceImplWSDDServiceName = name;
    }

    public com.jp.service.impl.AddServiceImpl getAddServiceImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AddServiceImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAddServiceImpl(endpoint);
    }

    public com.jp.service.impl.AddServiceImpl getAddServiceImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.jp.service.impl.AddServiceImplSoapBindingStub _stub = new com.jp.service.impl.AddServiceImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getAddServiceImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAddServiceImplEndpointAddress(java.lang.String address) {
        AddServiceImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.jp.service.impl.AddServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.jp.service.impl.AddServiceImplSoapBindingStub _stub = new com.jp.service.impl.AddServiceImplSoapBindingStub(new java.net.URL(AddServiceImpl_address), this);
                _stub.setPortName(getAddServiceImplWSDDServiceName());
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
        if ("AddServiceImpl".equals(inputPortName)) {
            return getAddServiceImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.service.jp.com", "AddServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.service.jp.com", "AddServiceImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AddServiceImpl".equals(portName)) {
            setAddServiceImplEndpointAddress(address);
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
