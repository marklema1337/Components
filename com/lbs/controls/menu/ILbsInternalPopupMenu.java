package com.lbs.controls.menu;

import com.lbs.control.interfaces.ILbsComponent;
import com.lbs.control.interfaces.ILbsPopupMenu;
import com.lbs.control.interfaces.ILbsPopupMenuItem;
import com.lbs.control.interfaces.ILbsSubMenu;

public interface ILbsInternalPopupMenu extends ILbsPopupMenu {
  ILbsPopupMenuItem createItem(ILbsPopupMenu paramILbsPopupMenu, String paramString, int paramInt1, int paramInt2);
  
  Object createSeparator();
  
  void addItem(Object paramObject);
  
  ILbsSubMenu createSubMenu(String paramString);
  
  ILbsComponent getFocusOwner();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\ILbsInternalPopupMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */