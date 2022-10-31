package com.lbs.remoteclient;

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface IReportExecutor {
  boolean executeReport(Frame paramFrame, JLbsReportExecParams paramJLbsReportExecParams) throws FileNotFoundException, InvocationTargetException;
  
  boolean executeReport(Frame paramFrame, Object paramObject, JLbsReportExecParams paramJLbsReportExecParams, boolean paramBoolean) throws FileNotFoundException, InvocationTargetException;
  
  void setContext(IClientContext paramIClientContext);
  
  IClientContext getContext();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\IReportExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */