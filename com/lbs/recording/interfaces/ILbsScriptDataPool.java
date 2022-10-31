package com.lbs.recording.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsScriptDataPool {
  String getValue(int paramInt);
  
  Object getValueAsObject(int paramInt);
  
  String getValueAsString(int paramInt);
  
  int getValueAsInteger(int paramInt);
  
  double getValueAsDouble(int paramInt);
  
  JLbsStringList getValueAsStringList(int paramInt);
  
  String getValue(int paramInt1, int paramInt2);
  
  Object getValueAsObject(int paramInt1, int paramInt2);
  
  String getValueAsString(int paramInt1, int paramInt2);
  
  int getValueAsInteger(int paramInt1, int paramInt2);
  
  double getValueAsDouble(int paramInt1, int paramInt2);
  
  JLbsStringList getValueAsStringList(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptDataPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */