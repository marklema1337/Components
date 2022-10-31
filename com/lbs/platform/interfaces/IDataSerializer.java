package com.lbs.platform.interfaces;

import com.lbs.data.export.IExportPropertyProvider;
import com.lbs.data.export.params.IDataExchangeParams;
import com.lbs.data.factory.ObjectInitializationException;
import com.lbs.data.objects.SimpleBusinessObject;
import java.io.Serializable;
import org.w3c.dom.Element;

public interface IDataSerializer extends Serializable, IExportPropertyProvider {
  void write(IDataWriter paramIDataWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
  
  SimpleBusinessObject read(Element paramElement, IDataExchangeParams paramIDataExchangeParams, int[] paramArrayOfint, String paramString) throws ObjectInitializationException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IDataSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */