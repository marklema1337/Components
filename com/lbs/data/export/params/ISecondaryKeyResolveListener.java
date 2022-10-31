package com.lbs.data.export.params;

import com.lbs.data.factory.FactoryParams;
import com.lbs.data.objects.SimpleBusinessObject;

public interface ISecondaryKeyResolveListener {
  public static final int ERR_KEY_SEGMENT_VALUE_NULL = 0;
  
  public static final int ERR_COL_NOT_EXIST = 1;
  
  public static final int ERR_KEY_NOT_RESOLVED = 2;
  
  public static final int ERR_EXCEPTION = 3;
  
  boolean canContinueOnResolveError(SimpleBusinessObject paramSimpleBusinessObject1, SimpleBusinessObject paramSimpleBusinessObject2, String paramString, FactoryParams paramFactoryParams, int paramInt);
  
  int resolveSecondaryKey(SimpleBusinessObject paramSimpleBusinessObject1, SimpleBusinessObject paramSimpleBusinessObject2, String paramString, FactoryParams paramFactoryParams);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ISecondaryKeyResolveListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */