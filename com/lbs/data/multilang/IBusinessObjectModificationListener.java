package com.lbs.data.multilang;

public interface IBusinessObjectModificationListener {
  boolean hasChanged(Object paramObject);
  
  void clearChangedValues(Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\multilang\IBusinessObjectModificationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */