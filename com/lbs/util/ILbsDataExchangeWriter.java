package com.lbs.util;

import com.lbs.util.xml.LineBreak;
import java.io.IOException;

public interface ILbsDataExchangeWriter {
  void writeObjectHeader(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, boolean paramBoolean) throws IllegalStateException, IllegalArgumentException, IOException;
  
  void writeObjectProperty(String paramString1, String paramString2);
  
  void writePropertyStartTag(String paramString);
  
  void writePropertyEndTag(String paramString);
  
  void setLineBreak(LineBreak paramLineBreak);
  
  void writeLinkedObjectStartTag(String paramString);
  
  void writeLinkedObjects();
  
  void writeLinkedObjectEndTag(String paramString);
  
  void writeCollectionsStartTag(String paramString);
  
  void writeCollections();
  
  void writeCollectionsEndTag(String paramString);
  
  void writeExtensionsStartTag(String paramString);
  
  void writeExtensions();
  
  void writeExtensionsEndTag(String paramString);
  
  void writePropertyResolverStartTag(String paramString);
  
  void writeResolvers();
  
  void writePropertyResolverEndTag(String paramString);
  
  void writeParameterStartTag(String paramString);
  
  void writeParameterEndTag(String paramString);
  
  void writeObjectFooter(String paramString);
  
  LineBreak getLineBreak();
  
  void pcdata(String paramString);
  
  void setIndentation(String paramString);
  
  String getIndentation();
  
  void writeComment(String paramString);
  
  void endTag();
  
  void endDocument();
  
  void closeFile();
  
  void writeParameterHeader(String paramString1, String paramString2, String paramString3) throws IllegalStateException, IllegalArgumentException, IOException;
  
  void writeParameterFooter(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ILbsDataExchangeWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */