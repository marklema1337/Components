package com.lbs.contract;

import com.lbs.data.objects.SimpleBusinessObject;
import com.lbs.data.query.QueryBusinessObjects;
import com.lbs.mi.defs.GlobalSearchDefinition;
import java.util.Hashtable;

public interface IContractService {
  ContractInstance getContractInstance(String paramString) throws ContractException;
  
  QueryBusinessObjects doContractGlobalSearch(String paramString1, GlobalSearchDefinition paramGlobalSearchDefinition, String paramString2, Hashtable<String, String> paramHashtable, boolean paramBoolean, ContractParameter... paramVarArgs) throws ContractException;
  
  boolean hasDBRight(String paramString1, String paramString2, SimpleBusinessObject paramSimpleBusinessObject, int paramInt, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  SimpleBusinessObject createNewBusinessObject(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  SimpleBusinessObject readBusinessObject(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  Object prepareBrowserData(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  Object prepareFormData(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  ContractCanDoResult canExecuteContract(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter... paramVarArgs) throws ContractException;
  
  void beforeExecuteContract(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter) throws ContractException;
  
  void onCompleteContract(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter1, ContractParameter[] paramArrayOfContractParameter2) throws ContractException;
  
  void onContractException(String paramString1, String paramString2, Hashtable<String, String> paramHashtable, ContractParameter[] paramArrayOfContractParameter, Throwable paramThrowable) throws ContractException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\IContractService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */