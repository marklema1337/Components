package com.lbs.console;

public interface ILbsConsole {
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
  
  void cloudinfo(Object paramObject);
  
  void cloudinfo(Object paramObject, Throwable paramThrowable);
  
  void cloudinfo(Throwable paramThrowable);
  
  void fatal(Object paramObject);
  
  void fatal(Object paramObject, Throwable paramThrowable);
  
  void fatal(Throwable paramThrowable);
  
  void log(LbsLevel paramLbsLevel, Object paramObject);
  
  void log(LbsLevel paramLbsLevel, Object paramObject, Throwable paramThrowable);
  
  void log(LbsLevel paramLbsLevel, Throwable paramThrowable);
  
  LbsLevel getEffectiveLevel();
  
  LbsLevel getLevel2();
  
  void setLevel2(LbsLevel paramLbsLevel);
  
  boolean isEnabledFor2(LbsLevel paramLbsLevel);
  
  boolean isDebugEnabled();
  
  boolean isInfoEnabled();
  
  boolean isTraceEnabled();
  
  boolean isWarnEnabled();
  
  boolean isErrorEnabled();
  
  void setAdditivity(boolean paramBoolean);
  
  boolean isAdditive();
  
  String getName();
  
  void addAppender(ILbsAppender paramILbsAppender);
  
  ILbsAppender removeAppender(String paramString);
  
  void removeAllAppenders();
  
  ILbsAppender getAppender(String paramString);
  
  ILbsAppender[] getAppenders();
  
  boolean isRootLogger();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\ILbsConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */