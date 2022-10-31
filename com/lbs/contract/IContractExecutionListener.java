package com.lbs.contract;

import com.lbs.localization.LbsLocalizableException;
import com.lbs.platform.interfaces.IApplicationContext;
import java.io.Serializable;
import java.util.Hashtable;

public interface IContractExecutionListener extends Serializable {
  ContractCanDoResult canExecuteContract(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws LbsLocalizableException;
  
  void beforeExecute(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter) throws Exception;
  
  void onComplete(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter1, ContractParameter[] paramArrayOfContractParameter2) throws Exception;
  
  void onException(IApplicationContext paramIApplicationContext, IContractService paramIContractService, String paramString, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter, Throwable paramThrowable) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\IContractExecutionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */