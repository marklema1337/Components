package com.lbs.controls.interfaces.proposalevaluation;

import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.localization.LbsLocalizableException;
import com.lbs.platform.interfaces.IApplicationContext;

public interface ILbsProposalEvaluation {
  void addContentTo(ILbsPanel paramILbsPanel) throws LbsLocalizableException;
  
  void setContext(IApplicationContext paramIApplicationContext);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\interfaces\proposalevaluation\ILbsProposalEvaluation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */