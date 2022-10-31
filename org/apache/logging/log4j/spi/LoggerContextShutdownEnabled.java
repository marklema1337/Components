package org.apache.logging.log4j.spi;

import java.util.List;

public interface LoggerContextShutdownEnabled {
  void addShutdownListener(LoggerContextShutdownAware paramLoggerContextShutdownAware);
  
  List<LoggerContextShutdownAware> getListeners();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\spi\LoggerContextShutdownEnabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */