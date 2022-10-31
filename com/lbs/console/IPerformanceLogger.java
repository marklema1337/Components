package com.lbs.console;

public interface IPerformanceLogger {
  void log(String paramString, PerformanceLogItem paramPerformanceLogItem);
  
  void log(String paramString1, String paramString2, PerformanceLogItemLine paramPerformanceLogItemLine);
  
  void flush(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\IPerformanceLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */