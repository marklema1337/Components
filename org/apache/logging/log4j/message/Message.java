package org.apache.logging.log4j.message;

import java.io.Serializable;

public interface Message extends Serializable {
  String getFormattedMessage();
  
  String getFormat();
  
  Object[] getParameters();
  
  Throwable getThrowable();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\message\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */