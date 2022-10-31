package com.lbs.contract;

import com.lbs.platform.interfaces.IApplicationContext;
import java.io.Serializable;
import java.util.Hashtable;

public interface IUIContractListener extends Serializable {
  Object prepareFormData(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\IUIContractListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */