package com.lbs.platform.interfaces;

import com.lbs.data.export.params.IDataExchangeParams;
import com.lbs.data.factory.ISubstitutionVariables;
import java.io.Serializable;
import java.util.Hashtable;

public interface IObjectResolver extends Serializable {
  int resolvePrimaryKey(IApplicationContext paramIApplicationContext, boolean paramBoolean, ISubstitutionVariables paramISubstitutionVariables);
  
  Hashtable<String, String> prepareAttributes(Hashtable<String, String> paramHashtable, String paramString, IDataExchangeParams paramIDataExchangeParams);
  
  void processAttributes(Hashtable<String, String> paramHashtable, String paramString, IDataExchangeParams paramIDataExchangeParams);
  
  void setResolverPropName(String paramString);
  
  boolean areFieldsSet();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IObjectResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */