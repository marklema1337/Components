package com.lbs.contract.applet;

import com.lbs.contract.ContractParameter;
import com.lbs.remoteclient.IClientContext;

public interface IContractEmbeddedCallback {
  void onEmbeddedClose(IClientContext paramIClientContext, ContractParameter... paramVarArgs);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\IContractEmbeddedCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */