package com.lbs.transport;

import java.util.Hashtable;

public interface ITransportCache {
  String getFileHash(String paramString) throws FileUpToDateException;
  
  String getFileNameHash(String paramString);
  
  String getFileDataHash(String paramString) throws FileUpToDateException;
  
  boolean saveFileData(String paramString1, String paramString2, byte[] paramArrayOfbyte, String paramString3);
  
  byte[] getPureFileData(String paramString);
  
  void setCustomizationResourceList(Hashtable paramHashtable);
  
  boolean isCustomizationResource(String paramString);
  
  void addToCustomizationResourceList(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ITransportCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */