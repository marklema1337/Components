package com.lbs.control.interfaces;

import java.awt.Color;

public interface ILbsPrefixPanel extends ILbsPanel {
  String getPrefix();
  
  void setPrefix(String paramString);
  
  ILbsMaskedEdit getFillControl();
  
  Color getPrefixColor();
  
  void setPrefixColor(Color paramColor);
  
  int getPrefixOfset();
  
  void setPrefixOfset(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsPrefixPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */