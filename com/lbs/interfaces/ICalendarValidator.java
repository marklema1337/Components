package com.lbs.interfaces;

import com.lbs.globalization.ILbsCultureInfo;

public interface ICalendarValidator {
  boolean verifyCalendarValue(Object paramObject1, int paramInt, Object paramObject2);
  
  boolean verifyCalendarValue(Object paramObject1, int paramInt, Object paramObject2, ILbsCultureInfo paramILbsCultureInfo, boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ICalendarValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */