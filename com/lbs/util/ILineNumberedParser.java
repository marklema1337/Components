package com.lbs.util;

import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface ILineNumberedParser {
  void parse(String paramString) throws SAXException, IOException;
  
  void parse(InputSource paramInputSource) throws SAXException, IOException;
  
  Document getDocument();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ILineNumberedParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */