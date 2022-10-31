package com.lbs.data.export.params;

import com.lbs.data.objects.SimpleBusinessObjects;

public interface IDataImporter extends IDataExchanger {
  boolean importData();
  
  ImportFailures getFailedObjects();
  
  boolean importFailedObjects(SimpleBusinessObjects paramSimpleBusinessObjects);
  
  boolean isShowEnvironmentCheck();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataImporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */