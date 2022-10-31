package com.lbs.controls;

import com.lbs.control.interfaces.ILbsContainer;
import com.lbs.control.interfaces.ILbsImageButton;
import com.lbs.control.interfaces.ILbsMenuButton;
import com.lbs.control.interfaces.ILbsPopupMenu;

public interface ILbsInternalMenuButton extends ILbsMenuButton, ILbsContainer {
  ILbsImageButton createMainButton();
  
  ILbsImageButton createPopupButton();
  
  ILbsPopupMenu createPopupMenu();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsInternalMenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */