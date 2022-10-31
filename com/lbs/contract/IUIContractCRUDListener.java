package com.lbs.contract;

import com.lbs.data.objects.SimpleBusinessObject;
import com.lbs.platform.interfaces.IApplicationContext;
import java.io.Serializable;
import java.util.Hashtable;

public interface IUIContractCRUDListener extends Serializable {
  SimpleBusinessObject createNewBusinessObject(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws Exception;
  
  SimpleBusinessObject readBusinessObject(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws Exception;
  
  Object prepareBrowserData(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws Exception;
  
  boolean hasDBRight(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, SimpleBusinessObject paramSimpleBusinessObject, int paramInt, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\IUIContractCRUDListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */