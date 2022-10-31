package com.lbs.control.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsCheckBoxGroup extends ILbsMultiColGroupBox {
  void addItem(Object paramObject, int paramInt, boolean paramBoolean);
  
  ILbsCheckBox getCheckBoxControl(int paramInt);
  
  void setItemDeselectedByTag(int paramInt);
  
  void setPopupList(JLbsStringList paramJLbsStringList);
  
  void setLayout();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsCheckBoxGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */