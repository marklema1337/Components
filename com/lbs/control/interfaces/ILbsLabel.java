package com.lbs.control.interfaces;

import javax.swing.Icon;

public interface ILbsLabel extends ILbsComponent {
  void setText(String paramString);
  
  void autosize();
  
  String getText();
  
  void setHorizontalAlignment(int paramInt);
  
  int getHorizontalAlignment();
  
  void setVerticalAlignment(int paramInt);
  
  int getVerticalAlignment();
  
  void setIcon(Icon paramIcon);
  
  Icon getIcon();
  
  int getNumLines();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */