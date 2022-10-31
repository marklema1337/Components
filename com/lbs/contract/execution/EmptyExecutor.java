package com.lbs.contract.execution;

import com.lbs.contract.ContractException;
import com.lbs.contract.ContractInstance;
import com.lbs.contract.ContractParameter;
import com.lbs.remoteclient.IClientContext;
import java.io.Serializable;

public interface EmptyExecutor extends Serializable {
  ContractParameter[] execute(ContractExecutorService paramContractExecutorService, IClientContext paramIClientContext, ContractInstance paramContractInstance, ContractParameter paramContractParameter, String paramString) throws ContractException;
  
  void initialize() throws ContractException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\EmptyExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */