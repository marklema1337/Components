package com.lbs.control.interfaces;

public interface ILbsContainer extends ILbsComponent {
  void addChild(ILbsComponent paramILbsComponent);
  
  int getComponentCount();
  
  Object getChildAt(int paramInt);
  
  void removeChild(ILbsComponent paramILbsComponent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */