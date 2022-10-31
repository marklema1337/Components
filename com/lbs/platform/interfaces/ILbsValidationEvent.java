package com.lbs.platform.interfaces;

import java.util.WeakHashMap;

public interface ILbsValidationEvent {
  Object getData();
  
  void setData(Object paramObject);
  
  Object getSource();
  
  void setSource(Object paramObject);
  
  Object getTypeInfo();
  
  void setTypeInfo(Object paramObject);
  
  int getMode();
  
  void setMode(int paramInt);
  
  WeakHashMap getParameters();
  
  IApplicationContext getContext();
  
  void setContext(IApplicationContext paramIApplicationContext);
  
  ILbsContainer getContainer();
  
  void setContainer(ILbsContainer paramILbsContainer);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ILbsValidationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */