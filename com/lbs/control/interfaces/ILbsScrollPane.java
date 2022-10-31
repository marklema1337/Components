package com.lbs.control.interfaces;

public interface ILbsScrollPane extends ILbsComponent {
  int getHorizontalScrollBarPolicy();
  
  void setHorizontalScrollBarPolicy(int paramInt);
  
  int getVerticalScrollBarPolicy();
  
  void setVerticalScrollBarPolicy(int paramInt);
  
  boolean isWheelScrollingEnabled();
  
  void setWheelScrollingEnabled(boolean paramBoolean);
  
  ILbsComponent getInnerComponent();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsScrollPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */