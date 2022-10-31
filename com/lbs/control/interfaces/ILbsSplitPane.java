package com.lbs.control.interfaces;

public interface ILbsSplitPane extends ILbsComponent {
  void setOrientation(int paramInt);
  
  int getOrientation();
  
  void setDividerSize(int paramInt);
  
  int getDividerSize();
  
  void setDividerLocation(int paramInt);
  
  int getDividerLocation();
  
  void setDividerLocation(double paramDouble);
  
  int getMaximumDividerLocation();
  
  int getMinimumDividerLocation();
  
  int getLastDividerLocation();
  
  void setLastDividerLocation(int paramInt);
  
  double getResizeWeight();
  
  void setResizeWeight(double paramDouble);
  
  boolean isContinuousLayout();
  
  void setContinuousLayout(boolean paramBoolean);
  
  boolean isOneTouchExpandable();
  
  void setOneTouchExpandable(boolean paramBoolean);
  
  void resetToPreferredSizes();
  
  void setRightComponent(ILbsComponent paramILbsComponent);
  
  void setLeftComponent(ILbsComponent paramILbsComponent);
  
  ILbsComponent getRightComp();
  
  ILbsComponent getLeftComp();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsSplitPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */