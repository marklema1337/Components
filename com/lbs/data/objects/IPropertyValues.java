package com.lbs.data.objects;

import com.lbs.util.ConversionDataLoss;
import com.lbs.util.ConversionNotSupportedException;
import java.math.BigDecimal;
import java.util.Calendar;

public interface IPropertyValues {
  void set(String paramString, Object paramObject);
  
  void set(String paramString, boolean paramBoolean);
  
  void set(String paramString, char paramChar);
  
  void set(String paramString, byte paramByte);
  
  void set(String paramString, short paramShort);
  
  void set(String paramString, int paramInt);
  
  void set(String paramString, long paramLong);
  
  void set(String paramString, double paramDouble);
  
  void set(String paramString, float paramFloat);
  
  Object get(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  boolean getBoolean(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  char getChar(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  byte getByte(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  short getShort(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  int getInt(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  long getLong(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  double getDouble(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  float getFloat(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  BigDecimal getBigDecimal(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  String getString(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  byte[] getByteArray(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
  
  Calendar getCalendar(String paramString) throws ConversionNotSupportedException, ConversionDataLoss, Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\IPropertyValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */