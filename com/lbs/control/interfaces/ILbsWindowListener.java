package com.lbs.control.interfaces;

import com.lbs.controls.emulator.LbsFocusManager;

public interface ILbsWindowListener {
  void focusedWindowChanged(ILbsWindow paramILbsWindow1, ILbsWindow paramILbsWindow2);
  
  void windowClosed(ILbsWindow paramILbsWindow);
  
  void windowOpened(ILbsWindow paramILbsWindow);
  
  LbsFocusManager getFocusManager();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsWindowListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */