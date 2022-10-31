package com.lbs.globalization;

import java.awt.ComponentOrientation;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;

public interface ILbsCultureInfo {
  int getCalendarType();
  
  int getLanguageID();
  
  String getLanguagePrefix();
  
  Locale getLocale();
  
  String getDefaultNumberFormat();
  
  int getDefaultNumberFormatIndex();
  
  int getDefaultCurrencyFormatIndex();
  
  String getPercentSign();
  
  String getDebitText();
  
  String getCreditText();
  
  String getNumberSpelled(BigDecimal paramBigDecimal, boolean paramBoolean);
  
  String getDefaultDateFormat();
  
  int getDefaultDateFormatIndex();
  
  String getDefaultTimeFormat();
  
  int getDefaultTimeFormatIndex();
  
  String getMonthFullName(int paramInt);
  
  String getMonthFullName(int paramInt1, int paramInt2);
  
  String getMonthShortName(int paramInt);
  
  String getMonthChar(int paramInt);
  
  String getDayFullName(int paramInt);
  
  String getDayFullName(int paramInt1, int paramInt2);
  
  String getDayShortName(int paramInt);
  
  String getDayShortName(int paramInt1, int paramInt2);
  
  String getDayChar(int paramInt);
  
  int getDatePatternChars(char paramChar);
  
  String getEra(int paramInt);
  
  String getAMPM(int paramInt);
  
  String getYes();
  
  String getNo();
  
  String getOK();
  
  String getCancel();
  
  String getSave();
  
  String getTimeZone();
  
  int getLengthForMonetaryFigures();
  
  int getLengthForNonMonetaryFigures();
  
  int getNumberOfDaysInMonth(int paramInt1, int paramInt2);
  
  Calendar getCalendarInstance();
  
  ComponentOrientation getComponentOrientation();
  
  boolean hasLeftToRightOrientation();
  
  String getLanguageName(int paramInt);
  
  String formatDateTime(Calendar paramCalendar, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  int getCurrencyIdx();
  
  String getCultureResStr(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\ILbsCultureInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */