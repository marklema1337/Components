package com.lbs.control.interfaces;

public interface ILbsRadioButtonGroup extends ILbsMultiColGroupBox {
  void setSelectedItemByTag(int paramInt);
  
  int getSelectedItemTag();
  
  void addItem(Object paramObject, int paramInt, boolean paramBoolean);
  
  ILbsRadioButton getRadioButtonControl(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsRadioButtonGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */