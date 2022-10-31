package com.lbs.util;

import com.lbs.data.export.params.IDataExchangeParams;
import com.lbs.data.objects.XMLSerializerBase;
import com.lbs.platform.interfaces.IDataWriter;
import org.w3c.dom.Element;

public interface IParameterSerializer {
  void writeParameter(XMLSerializerBase paramXMLSerializerBase, ILbsDataExchangeWriter paramILbsDataExchangeWriter, IDataExchangeParams paramIDataExchangeParams) throws Exception;
  
  void writeParameter(XMLSerializerBase paramXMLSerializerBase, IDataWriter paramIDataWriter, IDataExchangeParams paramIDataExchangeParams) throws Exception;
  
  void readParameter(XMLSerializerBase paramXMLSerializerBase, Element paramElement, IDataExchangeParams paramIDataExchangeParams);
  
  void readParameter(XMLSerializerBase paramXMLSerializerBase, Element paramElement, IDataExchangeParams paramIDataExchangeParams, String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\IParameterSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */