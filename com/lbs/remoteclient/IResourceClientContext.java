package com.lbs.remoteclient;

import com.lbs.platform.interfaces.ICachedHashTable;
import java.util.Hashtable;

public interface IResourceClientContext {
  ICachedHashTable getResourceCache();
  
  Hashtable getConfigParameters();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\IResourceClientContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */