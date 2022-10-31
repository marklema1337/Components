package com.lbs.control.interfaces;

import com.lbs.controls.ILbsMenuButtonListener;
import com.lbs.util.JLbsStringList;
import javax.swing.Icon;

public interface ILbsMenuButton extends ILbsPanel {
  void setActions(JLbsStringList paramJLbsStringList, boolean paramBoolean);
  
  void setActions(JLbsStringList paramJLbsStringList);
  
  void setActionIndex(int paramInt);
  
  JLbsStringList getItems();
  
  boolean isItemEnabled(ILbsMenuItem paramILbsMenuItem);
  
  boolean isItemVisible(ILbsMenuItem paramILbsMenuItem);
  
  int getActionIndex();
  
  ILbsPopupMenu getPopupControl();
  
  void setMenuButtonListener(ILbsMenuButtonListener paramILbsMenuButtonListener);
  
  ILbsMenuButtonListener getMenuButtonListener();
  
  void putClientPropertyInternal(Object paramObject1, Object paramObject2);
  
  void setIcon(Icon paramIcon);
  
  void setHighlightIcon(Icon paramIcon);
  
  Icon getIcon();
  
  void doClick();
  
  boolean hasItemTag(int paramInt);
  
  boolean isItemWTagEnabled(int paramInt);
  
  void reinitialize();
  
  int getActionId();
  
  void setActionId(int paramInt);
  
  void doPerformAction(int paramInt);
  
  void doMainButtonClick();
  
  boolean isPopupButton(ILbsImageButton paramILbsImageButton);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsMenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */