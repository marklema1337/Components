package com.lbs.control.interfaces;

import javax.swing.Icon;

public interface ILbsButton extends ILbsAbstractButton, ILbsActionTarget {
  void setMnemonic(char paramChar);
  
  int getMnemonic();
  
  boolean isDefaultButton();
  
  boolean isDefaultCapable();
  
  void setDefaultCapable(boolean paramBoolean);
  
  void setHighlightIcon(Icon paramIcon);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */