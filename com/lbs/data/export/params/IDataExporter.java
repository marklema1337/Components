package com.lbs.data.export.params;

import com.lbs.data.objects.SimpleBusinessObject;

public interface IDataExporter extends IDataExchanger {
  SimpleBusinessObject readBusinessObject(String paramString1, String paramString2, String paramString3, int paramInt);
  
  void setRecordListener(IDataRecordListener paramIDataRecordListener);
  
  void add2Log(Exception paramException);
  
  boolean exportData();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataExporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */