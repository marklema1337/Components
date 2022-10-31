package com.lbs.control.interfaces;

import com.lbs.controls.datedit.JLbsTimeDuration;
import java.util.Calendar;

public interface ILbsTimeEdit extends ILbsMaskedEdit {
  public static final int HH24 = 0;
  
  public static final int HH12 = 1;
  
  public static final int DURATION = 2;
  
  void setTime(JLbsTimeDuration paramJLbsTimeDuration);
  
  JLbsTimeDuration getTime();
  
  void setTime(Calendar paramCalendar);
  
  void setTimeFormatEx(int paramInt, boolean paramBoolean);
  
  char getMaskPlaceHolder();
  
  void setMaskPlaceHolder(char paramChar);
  
  String getPostfix();
  
  void setPostfix(String paramString);
  
  boolean getShowSeconds();
  
  void setShowSeconds(boolean paramBoolean);
  
  char getTimeSeparator();
  
  boolean setTimeSeparator(char paramChar);
  
  boolean setTimeFormat(int paramInt);
  
  void setCalendarValue(Calendar paramCalendar);
  
  Calendar getCalendarValue();
  
  boolean isValueModified();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsTimeEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */