package com.lbs.controls.interfaces.easycustomization;

import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.localization.LbsLocalizableException;
import com.lbs.platform.interfaces.IApplicationContext;

public interface ILbsEasyCustomization {
  void addContentTo(ILbsPanel paramILbsPanel) throws LbsLocalizableException;
  
  void setContext(IApplicationContext paramIApplicationContext);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\interfaces\easycustomization\ILbsEasyCustomization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */