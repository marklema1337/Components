package com.lbs.globalization;

import java.util.Calendar;
import java.util.Date;

public interface ILbsCultureCalendarHandler {
  Date parseDate(String paramString1, String paramString2, Object paramObject);
  
  String formatDate(Date paramDate, String paramString, Object paramObject);
  
  Object beforeDateFormat(Date paramDate);
  
  Calendar beforeCalendarPanelShow(Calendar paramCalendar);
  
  Calendar beforeCalendarPanelSetCalendar(Calendar paramCalendar);
  
  int getDayOfWeek(int paramInt1, int paramInt2, int paramInt3);
  
  int getWeekIndex(int paramInt1, int paramInt2, int paramInt3);
  
  int getDateField(int paramInt, Calendar paramCalendar);
  
  Calendar getAsPersian(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\ILbsCultureCalendarHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */