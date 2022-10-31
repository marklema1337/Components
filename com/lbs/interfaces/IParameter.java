package com.lbs.interfaces;

import com.lbs.data.IParameterMandatoryListener;
import com.lbs.data.Identifier;
import java.io.Serializable;
import java.util.ArrayList;

public interface IParameter extends Serializable, IXMLDescriber {
  void checkMandatoryFields(IParameterMandatoryListener paramIParameterMandatoryListener) throws ParameterException;
  
  Identifier getParameterIdentifier();
  
  ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener paramIParameterMandatoryListener);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\IParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */