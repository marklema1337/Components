package com.lbs.controls.groupbox;

import com.lbs.control.interfaces.ILbsCheckBox;
import com.lbs.control.interfaces.ILbsCheckBoxGroup;

public interface ILbsInternalCheckBoxGroup extends ILbsCheckBoxGroup, ILbsInternalMultiColGroupBox {
  ILbsCheckBox createCheckBox(String paramString, boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\ILbsInternalCheckBoxGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */