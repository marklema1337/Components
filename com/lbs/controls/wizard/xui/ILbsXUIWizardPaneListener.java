package com.lbs.controls.wizard.xui;

import com.lbs.control.interfaces.ILbsTabbedPane;

public interface ILbsXUIWizardPaneListener {
  boolean wizardCanGoBack(ILbsTabbedPane paramILbsTabbedPane, int paramInt) throws Exception;
  
  boolean wizardCanGoNext(ILbsTabbedPane paramILbsTabbedPane, int paramInt) throws Exception;
  
  boolean wizardCanGoFinish(ILbsTabbedPane paramILbsTabbedPane, int paramInt) throws Exception;
  
  boolean wizardFinish(ILbsTabbedPane paramILbsTabbedPane, boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\wizard\xui\ILbsXUIWizardPaneListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */