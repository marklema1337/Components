package com.lbs.controls;

public interface ILogger {
  void trace(Object paramObject);
  
  void trace(Object paramObject, Throwable paramThrowable);
  
  void trace(Throwable paramThrowable);
  
  void debug(Object paramObject);
  
  void debug(Object paramObject, Throwable paramThrowable);
  
  void debug(Throwable paramThrowable);
  
  void info(Object paramObject);
  
  void info(Object paramObject, Throwable paramThrowable);
  
  void info(Throwable paramThrowable);
  
  void warn(Object paramObject);
  
  void warn(Object paramObject, Throwable paramThrowable);
  
  void warn(Throwable paramThrowable);
  
  void error(Object paramObject);
  
  void error(Object paramObject, Throwable paramThrowable);
  
  void error(Throwable paramThrowable);
  
  void fatal(Object paramObject);
  
  void fatal(Object paramObject, Throwable paramThrowable);
  
  void fatal(Throwable paramThrowable);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */