package com.lbs.contract.execution;

import com.lbs.contract.ContractException;
import com.lbs.contract.ContractInstance;
import com.lbs.contract.ContractParameter;
import com.lbs.contract.applet.IContractCallback;
import com.lbs.remoteclient.IClientContext;
import java.util.Hashtable;

public interface ContractExecutorService {
  ContractInstance getContractInstance(IClientContext paramIClientContext, String paramString) throws ContractException;
  
  void executeContract(IClientContext paramIClientContext, ContractInstance paramContractInstance, IContractCallback paramIContractCallback, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  ContractParameter[] executeModalContract(IClientContext paramIClientContext, String paramString, ContractParameter... paramVarArgs);
  
  ContractParameter[] executeModalContract(IClientContext paramIClientContext, ContractInstance paramContractInstance, ContractParameter... paramVarArgs);
  
  ContractParameter[] createInputArray(IClientContext paramIClientContext, ContractInstance paramContractInstance);
  
  ContractParameter[] createOutputArray(IClientContext paramIClientContext, ContractInstance paramContractInstance);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\ContractExecutorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */