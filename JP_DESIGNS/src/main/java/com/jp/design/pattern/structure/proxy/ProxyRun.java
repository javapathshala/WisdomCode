/**
 * 
 */
package com.jp.design.pattern.structure.proxy;

/**
 * @author dimit.chadha
 * 
 */
public class ProxyRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Image imageReal = new RealImage("Dimit.jpeg");
		imageReal.displayImage();
		imageReal.displayImage();
		Image imageProxy=new ProxyImage("Chadha.jpeg");
		imageProxy.displayImage();
		
		Image imageProxy1=new ProxyImage("Chadha222.jpeg");
		imageProxy1.displayImage();
		imageProxy1.displayImage();
	}

}
