package com.lbs.console;

import java.util.Enumeration;

public interface ILog4jConsole {
  LbsConsole internalGetLogger(String paramString);
  
  LbsConsole internalGetRootLogger();
  
  Enumeration internalGetLoggerNames();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\ILog4jConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */