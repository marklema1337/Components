package com.lbs.contract.applet;

import com.lbs.contract.ContractInstance;
import com.lbs.remoteclient.IClientContext;

public interface IContractInternalCallback extends IContractCallback {
  void beforeExecute(IClientContext paramIClientContext, ContractInstance paramContractInstance, IContractExecutor paramIContractExecutor);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\IContractInternalCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */