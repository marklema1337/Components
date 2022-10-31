package com.lbs.platform.interfaces;

public interface ILbsObjectValidator {
  ILbsValidationResult initialize(ILbsValidationEvent paramILbsValidationEvent);
  
  ILbsValidationResult prepare(ILbsValidationEvent paramILbsValidationEvent);
  
  ILbsValidationResult validate(ILbsValidationEvent paramILbsValidationEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ILbsObjectValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */