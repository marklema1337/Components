package com.lbs.controls;

import com.lbs.control.interfaces.ILbsMenuButton;

public interface ILbsMenuButtonListener {
  void actionPerformed(ILbsMenuButton paramILbsMenuButton, int paramInt);
  
  void popupButtonPressed(ILbsMenuButton paramILbsMenuButton);
  
  boolean menuButtonActionFiltered(ILbsMenuButton paramILbsMenuButton, int paramInt);
  
  boolean menuButtonActionValid(ILbsMenuButton paramILbsMenuButton, int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsMenuButtonListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */