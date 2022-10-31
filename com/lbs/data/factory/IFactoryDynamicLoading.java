package com.lbs.data.factory;

import com.lbs.data.objects.BusinessObject;

public interface IFactoryDynamicLoading {
  boolean readProperty(BusinessObject paramBusinessObject, FactoryParams paramFactoryParams, String paramString, int paramInt) throws ObjectFactoryException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryDynamicLoading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */