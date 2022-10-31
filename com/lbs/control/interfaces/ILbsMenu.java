package com.lbs.control.interfaces;

import java.awt.event.ActionListener;

public interface ILbsMenu extends ILbsComponent, ILbsActionTarget {
  void addItems(String[] paramArrayOfString);
  
  void setItemActionListener(ActionListener paramActionListener);
  
  void addSeparator();
  
  int getItemCount();
  
  boolean isTopLevelMenu();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */