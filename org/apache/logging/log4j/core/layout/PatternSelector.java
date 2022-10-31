package org.apache.logging.log4j.core.layout;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.pattern.PatternFormatter;

public interface PatternSelector {
  public static final String ELEMENT_TYPE = "patternSelector";
  
  PatternFormatter[] getFormatters(LogEvent paramLogEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\layout\PatternSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */