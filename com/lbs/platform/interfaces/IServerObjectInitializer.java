package com.lbs.platform.interfaces;

public interface IServerObjectInitializer {
  ILbsValidationResult initializeOnServer(ILbsValidationEvent paramILbsValidationEvent);
  
  boolean initOnServerDBNew();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IServerObjectInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */