package com.lbs.platform.interfaces;

public interface IResolveEnvironment {
  IApplicationContext getContext();
  
  IResolverHolder.IResolveListener getListener();
  
  boolean isTraverseLinkObjects();
  
  boolean isUseGivenPrimaryKeys();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IResolveEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */