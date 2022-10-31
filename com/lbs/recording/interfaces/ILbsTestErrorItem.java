package com.lbs.recording.interfaces;

public interface ILbsTestErrorItem {
  String getErrorMessage();
  
  void setErrorMessage(String paramString);
  
  int getLevel();
  
  void setLevel(int paramInt);
  
  int getType();
  
  void setType(int paramInt);
  
  int getControlTag();
  
  void setControlTag(int paramInt);
  
  String getFormName();
  
  void setFormName(String paramString);
  
  int getSnapshotIndex();
  
  void setSnapshotIndex(int paramInt);
  
  Object getData();
  
  void setData(Object paramObject);
  
  String toString();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsTestErrorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */