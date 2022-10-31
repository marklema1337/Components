package com.lbs.controls;

import java.io.File;
import java.io.OutputStream;

public interface IRtfExecutor {
  void save(Object paramObject, File paramFile);
  
  void setContext(Object paramObject);
  
  void save(Object paramObject, OutputStream paramOutputStream);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\IRtfExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */