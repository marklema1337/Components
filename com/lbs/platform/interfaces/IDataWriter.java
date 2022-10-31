package com.lbs.platform.interfaces;

import com.lbs.util.xml.InvalidXMLException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public interface IDataWriter {
  void writeObjectStart(String paramString1, String paramString2, String paramString3, String paramString4) throws IllegalStateException, IOException;
  
  void writeObjectEnd() throws IllegalStateException, IOException;
  
  void writeProperty(String paramString1, String paramString2, Hashtable<String, String> paramHashtable) throws IllegalStateException, IllegalArgumentException, IOException;
  
  void writeInnerObjectStart(String paramString) throws IllegalStateException, IllegalArgumentException, IOException;
  
  void writeInnerObjectEnd(String paramString) throws IllegalStateException, NoSuchElementException, IOException;
  
  void writeComment(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void pcdata(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void endTag() throws IllegalStateException, IOException;
  
  void endDocument() throws IllegalStateException, IOException;
  
  void closeFile();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IDataWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */