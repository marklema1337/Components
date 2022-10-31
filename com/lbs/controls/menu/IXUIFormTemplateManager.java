package com.lbs.controls.menu;

public interface IXUIFormTemplateManager {
  void startTemplate(int paramInt);
  
  void finishTemplate(int paramInt);
  
  void loadFromTemplate(int paramInt);
  
  void loadDefaultTemplate(int paramInt);
  
  boolean canStartTemplate(int paramInt);
  
  boolean canFinishTemplate(int paramInt);
  
  boolean canLoadFromTemplate(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\IXUIFormTemplateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */