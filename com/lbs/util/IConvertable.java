package com.lbs.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public interface IConvertable {
  void setCultureInfo(Object paramObject);
  
  void setValue(Object paramObject);
  
  boolean getBoolean() throws ConversionNotSupportedException, ConversionDataLoss;
  
  byte getByte() throws ConversionNotSupportedException, ConversionDataLoss;
  
  char getChar() throws ConversionNotSupportedException, ConversionDataLoss;
  
  int getInt() throws ConversionNotSupportedException, ConversionDataLoss;
  
  long getLong() throws ConversionNotSupportedException, ConversionDataLoss;
  
  short getShort() throws ConversionNotSupportedException, ConversionDataLoss;
  
  float getFloat() throws ConversionNotSupportedException, ConversionDataLoss;
  
  double getDouble() throws ConversionNotSupportedException, ConversionDataLoss;
  
  BigInteger getBigInteger() throws ConversionNotSupportedException, ConversionDataLoss;
  
  BigDecimal getBigDecimal() throws ConversionNotSupportedException, ConversionDataLoss;
  
  String getString() throws ConversionNotSupportedException, ConversionDataLoss;
  
  Calendar getCalendar() throws ConversionNotSupportedException, ConversionDataLoss;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\IConvertable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */