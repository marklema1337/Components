package com.lbs.mail;

import com.lbs.localization.ILocalizationServices;
import java.io.Serializable;

public interface IEmailAddressDefinition extends Serializable, EmailDefinitionConstants {
  int getId();
  
  String getName(ILocalizationServices paramILocalizationServices);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\IEmailAddressDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */