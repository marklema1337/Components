package com.lbs.controller;

public interface IControllerWithChildren {
  ChildController<?, ?, ?> insertChildController(String paramString) throws PropertyDisabledException, PropertyInvisibleException, PropertyNotAvailableException;
  
  ChildController<?, ?, ?> insertChildController(String paramString, int paramInt) throws PropertyDisabledException, PropertyInvisibleException, PropertyNotAvailableException;
  
  void deleteChildController(String paramString, int paramInt) throws PropertyDisabledException, PropertyInvisibleException, PropertyNotAvailableException;
  
  void addListener(ControllerListener paramControllerListener);
  
  void removeListener(ControllerListener paramControllerListener);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\IControllerWithChildren.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */