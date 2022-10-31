package com.lbs.controls.wizard;

import javax.swing.JComponent;

public interface ILbsWizardPane {
  boolean canGoPrevious();
  
  boolean canGoNext();
  
  boolean canGoCancel();
  
  boolean isFinalPage();
  
  JComponent getNextPage();
  
  String getTitle();
  
  String getDescription();
  
  void initialize();
  
  boolean finalize(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\wizard\ILbsWizardPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */