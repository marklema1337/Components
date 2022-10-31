package com.lbs.remoteclient;

public interface ILbsRunContextListener {
  boolean beforeRun(JLbsRunContextParameters paramJLbsRunContextParameters, Object paramObject);
  
  void beforeDesign(JLbsRunContextParameters paramJLbsRunContextParameters, Object paramObject);
  
  int getReportID(JLbsRunContextParameters paramJLbsRunContextParameters, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\ILbsRunContextListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */