package com.lbs.control.interfaces;

import com.lbs.globalization.ILbsCultureInfo;
import java.util.Calendar;
import java.util.Date;

public interface ILbsDateEdit extends ILbsMaskedEdit {
  void setDate(Calendar paramCalendar);
  
  void setDate(Date paramDate);
  
  Calendar getCalendar();
  
  Object getValue();
  
  Date getDate();
  
  int getDefaultVisibleChars();
  
  void setCultureInfo(ILbsCultureInfo paramILbsCultureInfo);
  
  ILbsCultureInfo getCultureInfo();
  
  boolean setDateFormat(String paramString);
  
  String getDateFormat();
  
  boolean setDateFormat(int paramInt);
  
  void setDisplayFormat(String paramString);
  
  void setDisplayFormat(int paramInt);
  
  String getDisplayFormat();
  
  char getDateSeparator();
  
  boolean setDateSeparator(char paramChar);
  
  String getDateString(Date paramDate);
  
  char getMaskPlaceHolder();
  
  void setMaskPlaceHolder(char paramChar);
  
  String parseDate(Date paramDate);
  
  Date parseDate(String paramString1, String paramString2);
  
  String stripDateNumbers(String paramString);
  
  void setResetTime(boolean paramBoolean);
  
  boolean isResetTime();
  
  void setCalendarValue(Calendar paramCalendar);
  
  Calendar getCalendarValue();
  
  boolean isValueModified();
  
  void resetModified(Calendar paramCalendar);
  
  void increaseDay();
  
  void decreaseDay();
  
  default void setNoCompanyValidation(boolean b) {}
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsDateEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */