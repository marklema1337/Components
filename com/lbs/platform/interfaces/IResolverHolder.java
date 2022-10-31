package com.lbs.platform.interfaces;

import com.lbs.data.factory.FactoryParams;
import com.lbs.data.objects.IExchangeBusinessObject;
import com.lbs.data.objects.SimpleBusinessObject;

public interface IResolverHolder extends IExchangeBusinessObject {
  void resolvePropertyValues(IResolveEnvironment paramIResolveEnvironment);
  
  IObjectResolver getResolver(String paramString1, String paramString2);
  
  public static interface IResolveListener {
    int resolvePropertyValue(IApplicationContext param1IApplicationContext, SimpleBusinessObject param1SimpleBusinessObject, IObjectResolver param1IObjectResolver, String param1String1, String param1String2);
    
    SimpleBusinessObject[] getInsertedLinkObjects();
    
    FactoryParams getParameters();
  }
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IResolverHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */