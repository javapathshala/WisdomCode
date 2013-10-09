package com.jp.service.impl;

public class AddServiceImplProxy implements com.jp.service.impl.AddServiceImpl {
  private String _endpoint = null;
  private com.jp.service.impl.AddServiceImpl addServiceImpl = null;
  
  public AddServiceImplProxy() {
    _initAddServiceImplProxy();
  }
  
  public AddServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initAddServiceImplProxy();
  }
  
  private void _initAddServiceImplProxy() {
    try {
      addServiceImpl = (new com.jp.service.impl.AddServiceImplServiceLocator()).getAddServiceImpl();
      if (addServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)addServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)addServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (addServiceImpl != null)
      ((javax.xml.rpc.Stub)addServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.jp.service.impl.AddServiceImpl getAddServiceImpl() {
    if (addServiceImpl == null)
      _initAddServiceImplProxy();
    return addServiceImpl;
  }
  
  public com.jp.service.response.NumResponse doAdd(com.jp.service.request.NumRequest numRequest) throws java.rmi.RemoteException{
    if (addServiceImpl == null)
      _initAddServiceImplProxy();
    return addServiceImpl.doAdd(numRequest);
  }
  
  
}