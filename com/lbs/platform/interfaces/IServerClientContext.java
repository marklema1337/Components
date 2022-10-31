package com.lbs.platform.interfaces;

import java.io.FileNotFoundException;

public interface IServerClientContext {
  Object getObject(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws FileNotFoundException;
  
  ClassLoader getCustClassLoader(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IServerClientContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */