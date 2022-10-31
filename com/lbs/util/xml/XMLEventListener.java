package com.lbs.util.xml;

import java.io.IOException;

public interface XMLEventListener {
  public static final int UNINITIALIZED = 0;
  
  public static final int BEFORE_XML_DECLARATION = 1;
  
  public static final int BEFORE_DTD_DECLARATION = 2;
  
  public static final int BEFORE_ROOT_ELEMENT = 3;
  
  public static final int START_TAG_OPEN = 4;
  
  public static final int WITHIN_ELEMENT = 5;
  
  public static final int AFTER_ROOT_ELEMENT = 6;
  
  public static final int DOCUMENT_ENDED = 7;
  
  public static final int ERROR_STATE = 8;
  
  void reset();
  
  int getState();
  
  void setState(int paramInt, String[] paramArrayOfString) throws IllegalArgumentException;
  
  void declaration() throws IllegalStateException, IOException;
  
  void dtd(String paramString1, String paramString2, String paramString3) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void startTag(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void attribute(String paramString1, String paramString2) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void endTag() throws IllegalStateException, IOException;
  
  void pcdata(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void pcdata(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IllegalStateException, IllegalArgumentException, IndexOutOfBoundsException, InvalidXMLException, IOException;
  
  void cdata(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void whitespace(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void whitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IllegalStateException, IllegalArgumentException, IndexOutOfBoundsException, InvalidXMLException, IOException;
  
  void comment(String paramString) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void pi(String paramString1, String paramString2) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException;
  
  void endDocument() throws IllegalStateException, IOException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\XMLEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */