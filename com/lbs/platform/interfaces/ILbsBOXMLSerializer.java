package com.lbs.platform.interfaces;

import com.lbs.data.export.IExportPropertyProvider;
import com.lbs.data.export.params.IDataExchangeParams;
import com.lbs.data.factory.ObjectInitializationException;
import com.lbs.data.objects.SimpleBusinessObject;
import com.lbs.util.ILbsDataExchangeWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import org.w3c.dom.Element;

public interface ILbsBOXMLSerializer extends Serializable, IExportPropertyProvider {
  public static final int GRAMMAR_TYPE_DTD = 0;
  
  public static final int GRAMMAR_TYPE_XSD = 1;
  
  void write(ILbsDataExchangeWriter paramILbsDataExchangeWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
  
  String[] getDXRequiredProperties(SimpleBusinessObject paramSimpleBusinessObject, boolean paramBoolean);
  
  SimpleBusinessObject read(Element paramElement, int[] paramArrayOfint, IDataExchangeParams paramIDataExchangeParams) throws ObjectInitializationException;
  
  void writeGrammar(int paramInt, PrintWriter paramPrintWriter, ArrayList paramArrayList);
  
  String getNameSpace();
  
  String getRootElement();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ILbsBOXMLSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */