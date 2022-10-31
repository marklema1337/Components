package com.lbs.util;

public interface ILbsExportStatusMonitor {
  public static final int MSG_TYPE_INFO = 1;
  
  public static final int MSG_TYPE_MESSAGE = 2;
  
  public static final int MSG_TYPE_ERROR = 3;
  
  public static final int LEVEL_ERROR = 1;
  
  public static final int LEVEL_WARNING = 2;
  
  public static final int LEVEL_MESSAGE = 3;
  
  public static final int LEVEL_INFO = 4;
  
  public static final int LEVEL_VERBOSE = 5;
  
  void setDetailLevel(int paramInt);
  
  int getDetailLevel();
  
  void updateStatus(String paramString, int paramInt1, int paramInt2);
  
  void updateStatus(String paramString, Exception paramException, int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ILbsExportStatusMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */