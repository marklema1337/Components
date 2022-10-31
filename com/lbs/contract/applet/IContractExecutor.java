package com.lbs.contract.applet;

import com.lbs.contract.ContractException;
import com.lbs.contract.ContractInstance;
import com.lbs.contract.ContractParameter;
import com.lbs.remoteclient.IClientContext;
import java.util.Hashtable;

public interface IContractExecutor {
  void checkImplementationProps(Hashtable<String, String> paramHashtable) throws ContractException;
  
  void execute(IClientContext paramIClientContext, ContractInstance paramContractInstance, IContractCallback paramIContractCallback, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\IContractExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */