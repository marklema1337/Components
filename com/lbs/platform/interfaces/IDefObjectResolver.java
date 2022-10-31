package com.lbs.platform.interfaces;

import com.lbs.data.export.params.IDataExchangeParams;
import com.lbs.util.ILbsDataExchangeWriter;
import org.w3c.dom.NodeList;

public interface IDefObjectResolver extends IObjectResolver {
  void write(ILbsDataExchangeWriter paramILbsDataExchangeWriter, IDefObjectResolver paramIDefObjectResolver, IDataExchangeParams paramIDataExchangeParams, String paramString);
  
  IDefObjectResolver read(NodeList paramNodeList, IDataExchangeParams paramIDataExchangeParams, String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IDefObjectResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */