package com.lbs.data.export.params;

import com.lbs.data.query.QueryParams;
import com.lbs.platform.interfaces.ILbsExchangeCustomizationSerializer;

public interface IDataExportParams extends IDataExchangeParams {
  void setQueryName(String paramString);
  
  String getQueryName();
  
  void setQueryParams(QueryParams paramQueryParams);
  
  QueryParams getQueryParams();
  
  boolean zipExportData();
  
  void setZipExportData(boolean paramBoolean);
  
  boolean isSkipDefaultValues();
  
  void setSkipDefaultValues(boolean paramBoolean);
  
  int[] getRefObjects();
  
  void setRefObjects(int[] paramArrayOfint);
  
  int getRefObjectsState();
  
  void setRefObjectsState(int paramInt);
  
  boolean isCustomObject();
  
  void setCustomSerializerFinder(ILbsExchangeCustomizationSerializer paramILbsExchangeCustomizationSerializer);
  
  ILbsExchangeCustomizationSerializer getCustomSerializerFinder();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataExportParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */