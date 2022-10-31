package com.lbs.platform.interfaces;

import com.lbs.data.export.params.DataExchangeTemplate;
import com.lbs.remoteclient.IClientContext;

public interface IDataExchangeTemplateHandler {
  DataExchangeTemplate selectTemplate(IClientContext paramIClientContext, String paramString, byte paramByte);
  
  void saveTemplate(IClientContext paramIClientContext, DataExchangeTemplate paramDataExchangeTemplate);
  
  DataExchangeTemplate getDefaultTemplate(IClientContext paramIClientContext, String paramString, byte paramByte);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IDataExchangeTemplateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */