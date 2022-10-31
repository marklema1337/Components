package com.lbs.platform.interfaces;

import com.lbs.remoteclient.IReportExecutor;
import com.lbs.remoteclient.JLbsRunContextParameters;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.InvocationTargetException;
import java.security.SignatureException;

public interface IDeveloperContext extends IApplicationContext {
  void showMessage(String paramString1, String paramString2);
  
  void showError(Exception paramException);
  
  boolean loadJAR(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws SignatureException, FileNotFoundException;
  
  Object createNamedInstance(String paramString1, String paramString2) throws Exception;
  
  Object getObjectResource(String paramString, boolean paramBoolean) throws FileNotFoundException, InvalidObjectException;
  
  Object getSerializedObject(String paramString, boolean paramBoolean);
  
  Object deserializeObject(byte[] paramArrayOfbyte);
  
  Object deserializeObjectPlain(byte[] paramArrayOfbyte);
  
  IReportExecutor getReportExecutor();
  
  boolean runReport(String paramString, JLbsRunContextParameters paramJLbsRunContextParameters) throws FileNotFoundException, InvocationTargetException;
  
  void requestBatchOperation(String paramString, Object[] paramArrayOfObject) throws Exception;
  
  boolean saveLocalFile(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
  
  boolean saveLocalFile(String paramString1, byte[] paramArrayOfbyte, boolean paramBoolean1, boolean paramBoolean2, String paramString2) throws IOException;
  
  boolean saveLocalFile(String paramString, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
  
  byte[] loadLocalFile(String paramString) throws IOException;
  
  boolean deleteLocalFile(String paramString);
  
  boolean openURL(String paramString1, String paramString2);
  
  boolean openHelpURL(String paramString1, String paramString2);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IDeveloperContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */