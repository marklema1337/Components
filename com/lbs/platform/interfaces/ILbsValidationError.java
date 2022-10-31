package com.lbs.platform.interfaces;

public interface ILbsValidationError {
  public static final int ERR_INFO = 0;
  
  public static final int ERR_WARNING = 1;
  
  public static final int ERR_FATAL = 2;
  
  int getID();
  
  void setID(int paramInt);
  
  String getLocalizedMessage();
  
  void setResourceID(int paramInt1, int paramInt2);
  
  int getSeverity();
  
  void setSeverity(int paramInt);
  
  String getMessage();
  
  void setMessage(String paramString);
  
  int getControlTag();
  
  void setControlTag(int paramInt);
  
  int getSecondTag();
  
  void setSecondTag(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ILbsValidationError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */