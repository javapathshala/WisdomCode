package com.jp.service.impl;

public class MathServiceImplProxy implements com.jp.service.impl.MathServiceImpl {
  private String _endpoint = null;
  private com.jp.service.impl.MathServiceImpl mathServiceImpl = null;
  
  public MathServiceImplProxy() {
    _initMathServiceImplProxy();
  }
  
  public MathServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initMathServiceImplProxy();
  }
  
  private void _initMathServiceImplProxy() {
    try {
      mathServiceImpl = (new com.jp.service.impl.MathServiceImplServiceLocator()).getMathServiceImpl();
      if (mathServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mathServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mathServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mathServiceImpl != null)
      ((javax.xml.rpc.Stub)mathServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.jp.service.impl.MathServiceImpl getMathServiceImpl() {
    if (mathServiceImpl == null)
      _initMathServiceImplProxy();
    return mathServiceImpl;
  }
  
  public com.jp.service.response.MathResponse doAdd(com.jp.service.request.MathRequest mathRequest) throws java.rmi.RemoteException{
    if (mathServiceImpl == null)
      _initMathServiceImplProxy();
    return mathServiceImpl.doAdd(mathRequest);
  }
  
  
}