package com.lbs.platform.interfaces;

import java.util.List;

public interface ILbsValidationResult extends List<ILbsValidationError> {
  boolean canContinue();
  
  ILbsValidationError getLastError();
  
  String getLastErrorMessage();
  
  void setCanContinue(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ILbsValidationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */