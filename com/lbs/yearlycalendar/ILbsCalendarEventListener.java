package com.lbs.yearlycalendar;

import java.util.Calendar;

public interface ILbsCalendarEventListener {
  void onSelectionChanged(Object paramObject, JLbsSelectionList paramJLbsSelectionList);
  
  void onSelectionCleared(Object paramObject);
  
  void onDateChanged(Object paramObject, Calendar paramCalendar1, Calendar paramCalendar2);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\ILbsCalendarEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */