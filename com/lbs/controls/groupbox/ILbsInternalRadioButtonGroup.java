package com.lbs.controls.groupbox;

import com.lbs.control.interfaces.ILbsRadioButton;
import com.lbs.control.interfaces.ILbsRadioButtonGroup;
import com.lbs.controls.ILbsComponentBase;

public interface ILbsInternalRadioButtonGroup extends ILbsRadioButtonGroup, ILbsInternalMultiColGroupBox {
  void setSelected(ILbsRadioButton paramILbsRadioButton);
  
  void recordSetItemSelected(ILbsComponentBase paramILbsComponentBase, boolean paramBoolean);
  
  ILbsRadioButton createRadioButton(String paramString, boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\ILbsInternalRadioButtonGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */