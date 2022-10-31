package com.lbs.controller;

public interface ControllerListener {
  void groupStateChanged(GroupStateChangeEvent<?> paramGroupStateChangeEvent);
  
  void propertyStateChanged(PropertyStateChangeEvent<?> paramPropertyStateChangeEvent);
  
  void propertyValueChanged(PropertyValueChangeEvent<?> paramPropertyValueChangeEvent);
  
  void beforeChildAdd(ChildAddEvent paramChildAddEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */