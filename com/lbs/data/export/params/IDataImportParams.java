package com.lbs.data.export.params;

import com.lbs.platform.interfaces.ILbsExchangeValidator;

public interface IDataImportParams extends IDataExchangeParams {
  IntegratorBOParameter getPKResolveParams();
  
  void setPKResolveParams(IntegratorBOParameter paramIntegratorBOParameter);
  
  byte[] getFileContent();
  
  void setFileContent(byte[] paramArrayOfbyte);
  
  void readFileContent();
  
  void setValidator(ILbsExchangeValidator paramILbsExchangeValidator);
  
  boolean isUseGivenPrimaryKey();
  
  void setUseGivenPrimaryKey(boolean paramBoolean);
  
  void setSingleTransaction(boolean paramBoolean);
  
  boolean isSingleTransaction();
  
  void setUseEnvironmentVariables(boolean paramBoolean);
  
  boolean isUseEnvironmentVariables();
  
  void setUpdateOverwrite(boolean paramBoolean);
  
  boolean isUpdateOverwrite();
  
  boolean isEnableUsePrimaryKey();
  
  void setEnableUsePrimaryKey(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataImportParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */