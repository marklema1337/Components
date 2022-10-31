package org.apache.logging.log4j.util;

import org.apache.logging.log4j.message.MultiformatMessage;

public interface MultiFormatStringBuilderFormattable extends MultiformatMessage, StringBuilderFormattable {
  void formatTo(String[] paramArrayOfString, StringBuilder paramStringBuilder);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4\\util\MultiFormatStringBuilderFormattable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */