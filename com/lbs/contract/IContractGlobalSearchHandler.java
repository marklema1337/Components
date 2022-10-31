package com.lbs.contract;

import com.lbs.data.query.QueryBusinessObjects;
import com.lbs.mi.defs.GlobalSearchDefinition;
import com.lbs.platform.interfaces.IApplicationContext;
import java.util.Hashtable;

public interface IContractGlobalSearchHandler {
  QueryBusinessObjects doGlobalSearch(IContractService paramIContractService, IApplicationContext paramIApplicationContext, GlobalSearchDefinition paramGlobalSearchDefinition, String paramString, ContractInstance paramContractInstance, Hashtable<String, String> paramHashtable, boolean paramBoolean, ContractParameter... paramVarArgs) throws ContractException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\IContractGlobalSearchHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */