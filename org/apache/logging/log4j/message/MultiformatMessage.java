package org.apache.logging.log4j.message;

public interface MultiformatMessage extends Message {
  String getFormattedMessage(String[] paramArrayOfString);
  
  String[] getFormats();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\message\MultiformatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */