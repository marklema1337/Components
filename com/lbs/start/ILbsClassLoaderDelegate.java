package com.lbs.start;

import java.io.InputStream;
import java.net.URL;
import java.security.ProtectionDomain;

public interface ILbsClassLoaderDelegate {
  byte[] findLocalClass(String paramString) throws ClassNotFoundException;
  
  InputStream findLocalResource(String paramString);
  
  URL findResourceURL(String paramString);
  
  URL findLocalResourceURL(String paramString);
  
  ProtectionDomain getProtectionDomain(String paramString);
  
  String getGUID();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\ILbsClassLoaderDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */