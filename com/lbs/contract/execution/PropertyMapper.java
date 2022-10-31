package com.lbs.contract.execution;

import com.lbs.contract.ContractException;
import com.lbs.contract.ContractInstance;
import com.lbs.contract.ContractParameter;
import com.lbs.interfaces.IParameter;
import com.lbs.remoteclient.IClientContext;
import java.io.Serializable;

public interface PropertyMapper extends Serializable {
  boolean canMapPropertyValue(IParameter paramIParameter, IClientContext paramIClientContext, ContractInstance paramContractInstance, ContractParameter paramContractParameter, String paramString);
  
  Object mapPropertyValue(IParameter paramIParameter, IClientContext paramIClientContext, ContractInstance paramContractInstance, ContractParameter paramContractParameter, String paramString);
  
  void initialize() throws ContractException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\PropertyMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */