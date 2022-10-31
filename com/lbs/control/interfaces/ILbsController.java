package com.lbs.control.interfaces;

import com.lbs.controllers.LbsControllerException;
import com.lbs.controls.controllers.NameIdentifier;
import com.lbs.message.ILbsMessageExecutor;

public interface ILbsController extends ILbsDefaultController {
  void close() throws LbsControllerException;
  
  void controllerClosing();
  
  void finalizeForClose();
  
  String getControllerId();
  
  String getName(NameIdentifier paramNameIdentifier);
  
  Object getFormData();
  
  ILbsMessageExecutor getMessageExecutor();
  
  void setMessageExecutor(ILbsMessageExecutor paramILbsMessageExecutor);
  
  void clickButton(int paramInt) throws LbsControllerException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */