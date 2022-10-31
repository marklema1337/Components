package com.lbs.data.objects;

import com.lbs.data.IDataContext;
import com.lbs.data.factory.FactoryParams;

public interface IObjectLoadingData {
  FactoryParams getFactoryParams();
  
  IDataContext getContext();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\IObjectLoadingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */