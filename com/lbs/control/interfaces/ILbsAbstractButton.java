package com.lbs.control.interfaces;

import java.awt.event.ItemListener;
import javax.swing.Icon;

public interface ILbsAbstractButton extends ILbsComponent, ILbsActionTarget {
  void doClick();
  
  void setText(String paramString);
  
  String getText();
  
  boolean isSelected();
  
  void setSelected(boolean paramBoolean);
  
  void addItemListener(ItemListener paramItemListener);
  
  void removeItemListener(ItemListener paramItemListener);
  
  void setActionCommand(String paramString);
  
  String getActionCommand();
  
  void setIcon(Icon paramIcon);
  
  Icon getIcon();
  
  Icon getDisabledIcon();
  
  void setDisabledIcon(Icon paramIcon);
  
  Icon getDisabledSelectedIcon();
  
  void setDisabledSelectedIcon(Icon paramIcon);
  
  int getHorizontalAlignment();
  
  void setHorizontalAlignment(int paramInt);
  
  int getHorizontalTextPosition();
  
  void setHorizontalTextPosition(int paramInt);
  
  int getVerticalAlignment();
  
  void setVerticalAlignment(int paramInt);
  
  int getVerticalTextPosition();
  
  void setVerticalTextPosition(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsAbstractButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */