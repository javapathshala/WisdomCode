/**
 * MathServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jp.service.impl;

public class MathServiceImplServiceLocator extends org.apache.axis.client.Service implements com.jp.service.impl.MathServiceImplService {

    public MathServiceImplServiceLocator() {
    }


    public MathServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MathServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MathServiceImpl
    private java.lang.String MathServiceImpl_address = "http://localhost:8081/JP_WS2/services/MathServiceImpl";

    public java.lang.String getMathServiceImplAddress() {
        return MathServiceImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MathServiceImplWSDDServiceName = "MathServiceImpl";

    public java.lang.String getMathServiceImplWSDDServiceName() {
        return MathServiceImplWSDDServiceName;
    }

    public void setMathServiceImplWSDDServiceName(java.lang.String name) {
        MathServiceImplWSDDServiceName = name;
    }

    public com.jp.service.impl.MathServiceImpl getMathServiceImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MathServiceImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMathServiceImpl(endpoint);
    }

    public com.jp.service.impl.MathServiceImpl getMathServiceImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.jp.service.impl.MathServiceImplSoapBindingStub _stub = new com.jp.service.impl.MathServiceImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getMathServiceImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMathServiceImplEndpointAddress(java.lang.String address) {
        MathServiceImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.jp.service.impl.MathServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.jp.service.impl.MathServiceImplSoapBindingStub _stub = new com.jp.service.impl.MathServiceImplSoapBindingStub(new java.net.URL(MathServiceImpl_address), this);
                _stub.setPortName(getMathServiceImplWSDDServiceName());
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
        if ("MathServiceImpl".equals(inputPortName)) {
            return getMathServiceImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.service.jp.com", "MathServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.service.jp.com", "MathServiceImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MathServiceImpl".equals(portName)) {
            setMathServiceImplEndpointAddress(address);
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
