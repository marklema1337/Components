package org.apache.logging.log4j.core.time;

import org.apache.logging.log4j.core.util.Clock;

public interface PreciseClock extends Clock {
  void init(MutableInstant paramMutableInstant);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\time\PreciseClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */