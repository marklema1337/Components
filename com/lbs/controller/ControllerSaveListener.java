package com.lbs.controller;

import com.lbs.data.factory.FactoryParams;
import com.lbs.platform.interfaces.ILbsValidationError;

public interface ControllerSaveListener {
  void beforeSave(Controller<?, ?> paramController, FactoryParams paramFactoryParams);
  
  void onValidateError(Controller<?, ?> paramController, ILbsValidationError paramILbsValidationError);
  
  void onSaveException(Controller<?, ?> paramController, Exception paramException);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerSaveListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */