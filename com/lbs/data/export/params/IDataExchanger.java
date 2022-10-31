package com.lbs.data.export.params;

import com.lbs.data.objects.SimpleBusinessObjects;

public interface IDataExchanger {
  public static final byte TYPE_EXPORT = 1;
  
  public static final byte TYPE_IMPORT = 2;
  
  void setContext(Object paramObject);
  
  Object getContext();
  
  void setTemplateHandler(Object paramObject);
  
  SimpleBusinessObjects getObjectExtensions(String paramString1, String paramString2);
  
  void setExchangeParams(IDataExchangeParams paramIDataExchangeParams);
  
  DataExchangeTemplate selectTemplate(String paramString);
  
  void saveTemplate(DataExchangeTemplate paramDataExchangeTemplate, String paramString);
  
  DataExchangeTemplate getDefaultTemplate(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataExchanger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */