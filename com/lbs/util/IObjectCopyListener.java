package com.lbs.util;

import java.lang.reflect.Field;

public interface IObjectCopyListener {
  Object createCopy(ObjectCopyEvent paramObjectCopyEvent) throws ObjectCopyException;
  
  void doneCopy(ObjectCopyEvent paramObjectCopyEvent);
  
  Field[] sortObjectFields(Object paramObject, Field[] paramArrayOfField);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\IObjectCopyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */