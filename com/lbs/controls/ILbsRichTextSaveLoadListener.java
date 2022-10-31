package com.lbs.controls;

import java.io.InputStream;
import java.io.OutputStream;

public interface ILbsRichTextSaveLoadListener {
  void saveRichTextDocument(Object paramObject);
  
  void save(Object paramObject, OutputStream paramOutputStream);
  
  InputStream loadRichTextDocument();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsRichTextSaveLoadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */