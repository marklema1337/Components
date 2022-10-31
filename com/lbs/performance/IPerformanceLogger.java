package com.lbs.performance;

public interface IPerformanceLogger {
  void entering(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4);
  
  void exiting(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4);
  
  void info(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4);
  
  void flush();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\IPerformanceLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */