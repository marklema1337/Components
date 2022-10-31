package com.lbs.controls.wizard.xui;

public interface ILbsXUIWizardPane {
  void setWizardMode(boolean paramBoolean);
  
  boolean isWizardMode();
  
  void setWizardPaneListener(ILbsXUIWizardPaneListener paramILbsXUIWizardPaneListener);
  
  boolean canGoBack() throws Exception;
  
  boolean canGoNext() throws Exception;
  
  boolean canGoFinish() throws Exception;
  
  boolean wizardFinish(boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\wizard\xui\ILbsXUIWizardPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */