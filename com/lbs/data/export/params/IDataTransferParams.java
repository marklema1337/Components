package com.lbs.data.export.params;

import java.io.Serializable;

public interface IDataTransferParams extends Serializable {
  void setImportParams(IDataImportParams paramIDataImportParams);
  
  IDataImportParams getImportParams();
  
  void setExportParams(IDataExportParams paramIDataExportParams);
  
  IDataExportParams getExportParams();
  
  Object getTargetLoginParameters();
  
  void setTargetLoginParameters(Object paramObject);
  
  boolean isExecuteInBatch();
  
  void setExecuteInBatch(boolean paramBoolean);
  
  int[] getPrimaryKeysToExport();
  
  void setPrimaryKeysToExport(int[] paramArrayOfint);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataTransferParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */