package com.lbs.recording.interfaces;

public interface ILbsReportTestListener {
  public static final int MODE_RECORD = 1;
  
  public static final int MODE_COMPARE = 2;
  
  int getMode();
  
  String getRCFileName();
  
  void addError(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsReportTestListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */