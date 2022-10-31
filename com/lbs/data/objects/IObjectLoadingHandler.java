package com.lbs.data.objects;

import com.lbs.data.factory.FactoryException;

public interface IObjectLoadingHandler {
  IObjectLoadingData getLoadingData();
  
  BusinessObject getParentObject();
  
  String getPropertyName();
  
  void load() throws FactoryException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\IObjectLoadingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */