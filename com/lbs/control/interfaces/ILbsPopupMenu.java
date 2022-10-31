package com.lbs.control.interfaces;

import com.lbs.controls.menu.ILbsCustomPopupMenuItem;
import com.lbs.controls.menu.ILbsMenuFilter;
import com.lbs.controls.menu.ILbsSystemMenuListener;
import com.lbs.util.JLbsStringList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;

public interface ILbsPopupMenu extends ILbsComponent {
  int getMenuItemCount();
  
  void setItemEnabled(int paramInt, boolean paramBoolean);
  
  void setItemVisible(int paramInt, boolean paramBoolean);
  
  void setItemFilter(ILbsMenuFilter paramILbsMenuFilter);
  
  void clearItems();
  
  void addItems(JLbsStringList paramJLbsStringList);
  
  String getItemsSList();
  
  ArrayList getMenuItems();
  
  void setActionListener(ActionListener paramActionListener);
  
  ActionListener getActionListener();
  
  void setSystemMenuListener(ILbsSystemMenuListener paramILbsSystemMenuListener);
  
  ILbsSystemMenuListener getSystemMenuListener();
  
  ILbsComponent getInvokerControl();
  
  int add(String paramString, int paramInt);
  
  void addSeparator();
  
  int insert(int paramInt1, String paramString, int paramInt2);
  
  boolean hasItemTag(int paramInt);
  
  boolean isDynamicMenuExtensionEnabled();
  
  void setDynamicMenuExtensionEnabled(boolean paramBoolean);
  
  boolean isItemWTagEnabled(int paramInt);
  
  boolean isItemWTagVisible(int paramInt);
  
  void setIcon(int paramInt, Icon paramIcon);
  
  String getLabel();
  
  void setLabel(String paramString);
  
  void selectItemByTag(int paramInt);
  
  Object getPopupParent();
  
  String getShowingItemList();
  
  ILbsCustomPopupMenuItem getCustomMenuItemListener();
  
  void setCustomMenuItemListener(ILbsCustomPopupMenuItem paramILbsCustomPopupMenuItem);
  
  void open();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsPopupMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */