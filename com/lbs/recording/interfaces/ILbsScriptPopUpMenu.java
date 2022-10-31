package com.lbs.recording.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsScriptPopUpMenu {
  void show() throws InterruptedException;
  
  void show(int paramInt) throws InterruptedException;
  
  void show(int paramInt1, int paramInt2, int paramInt3) throws InterruptedException;
  
  void selectItem(int paramInt) throws InterruptedException;
  
  void selectAll() throws InterruptedException;
  
  void deselectAll() throws InterruptedException;
  
  void invertSelection() throws InterruptedException;
  
  void selectAll(int paramInt) throws InterruptedException;
  
  void deselectAll(int paramInt) throws InterruptedException;
  
  void invertSelection(int paramInt) throws InterruptedException;
  
  JLbsStringList getItems();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptPopUpMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */