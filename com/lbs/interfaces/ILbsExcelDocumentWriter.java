package com.lbs.interfaces;

import java.awt.Font;
import java.io.IOException;

public interface ILbsExcelDocumentWriter {
  void setFileName(int paramInt, String paramString) throws IOException;
  
  int addFont(Font paramFont, boolean paramBoolean) throws IOException;
  
  void closeFile() throws IOException;
  
  void writeString(int paramInt1, int paramInt2, String paramString) throws IOException;
  
  void writeString(int paramInt1, int paramInt2, int paramInt3, String paramString) throws IOException;
  
  void writeString(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) throws IOException;
  
  void writeNumber(int paramInt1, int paramInt2, double paramDouble) throws IOException;
  
  void writeNumber(int paramInt1, int paramInt2, int paramInt3, double paramDouble) throws IOException;
  
  void writeNumber(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble) throws IOException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsExcelDocumentWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */