package com.lbs.controls;

import com.lbs.controls.emulator.ILbsComponentEmulator;

public interface ILbsGridComponent extends ILbsComponentBase, ILbsComponentEmulator {
  boolean hideSelectedColumn();
  
  boolean showAllColumns();
  
  boolean doGridPopupOperations();
  
  void doToggleAction();
  
  void setNonModal(boolean paramBoolean);
  
  boolean getNonModal();
  
  boolean gridHasOption(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsGridComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */