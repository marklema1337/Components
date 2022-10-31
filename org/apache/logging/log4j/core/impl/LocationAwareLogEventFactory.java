package org.apache.logging.log4j.core.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.message.Message;

public interface LocationAwareLogEventFactory {
  LogEvent createEvent(String paramString1, Marker paramMarker, String paramString2, StackTraceElement paramStackTraceElement, Level paramLevel, Message paramMessage, List<Property> paramList, Throwable paramThrowable);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\impl\LocationAwareLogEventFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */