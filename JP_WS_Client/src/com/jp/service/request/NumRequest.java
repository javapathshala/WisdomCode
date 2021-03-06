/**
 * NumRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jp.service.request;

public class NumRequest  implements java.io.Serializable {
    private int num1;

    private int num2;

    public NumRequest() {
    }

    public NumRequest(
           int num1,
           int num2) {
           this.num1 = num1;
           this.num2 = num2;
    }


    /**
     * Gets the num1 value for this NumRequest.
     * 
     * @return num1
     */
    public int getNum1() {
        return num1;
    }


    /**
     * Sets the num1 value for this NumRequest.
     * 
     * @param num1
     */
    public void setNum1(int num1) {
        this.num1 = num1;
    }


    /**
     * Gets the num2 value for this NumRequest.
     * 
     * @return num2
     */
    public int getNum2() {
        return num2;
    }


    /**
     * Sets the num2 value for this NumRequest.
     * 
     * @param num2
     */
    public void setNum2(int num2) {
        this.num2 = num2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NumRequest)) return false;
        NumRequest other = (NumRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.num1 == other.getNum1() &&
            this.num2 == other.getNum2();
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
        _hashCode += getNum1();
        _hashCode += getNum2();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NumRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.service.jp.com", "NumRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.service.jp.com", "num1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.service.jp.com", "num2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
