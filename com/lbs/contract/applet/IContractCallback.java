package com.lbs.contract.applet;

import com.lbs.contract.ContractParameter;
import com.lbs.remoteclient.IClientContext;

public interface IContractCallback {
  void onComplete(IClientContext paramIClientContext, ContractParameter... paramVarArgs);
  
  void onException(IClientContext paramIClientContext, ContractExceptionEvent paramContractExceptionEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\IContractCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */