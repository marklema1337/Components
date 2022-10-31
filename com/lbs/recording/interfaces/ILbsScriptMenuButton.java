package com.lbs.recording.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsScriptMenuButton {
  void click() throws InterruptedException;
  
  void showItems() throws InterruptedException;
  
  void clickItem(int paramInt) throws InterruptedException;
  
  void clickItem(int paramInt1, int paramInt2) throws InterruptedException;
  
  JLbsStringList getItems();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptMenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */