//$Id: Register.java 2056 2006-09-28 00:36:56Z gavin $
package com.jp.seam.ejb.reg;

import javax.ejb.Local;

@Local
public interface Register
{
   public String register();
}