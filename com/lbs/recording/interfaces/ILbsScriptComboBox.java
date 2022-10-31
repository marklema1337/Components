package com.lbs.recording.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsScriptComboBox {
  void setSelectedIndex(int paramInt) throws InterruptedException;
  
  void setSelectedTag(int paramInt) throws InterruptedException;
  
  void setString(String paramString) throws InterruptedException;
  
  void setObject(Object paramObject) throws InterruptedException;
  
  JLbsStringList getItems();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */