package com.lbs.data.database;

public interface IDBTable {
  DBEntityCollection getFieldList();
  
  DBEntityCollection getPartitionFieldList();
  
  DBEntityCollection getIndexList();
  
  String getName();
  
  String getPhysicalName();
  
  String getTypeName();
  
  boolean isTemplateTable();
  
  boolean isDomainAware();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\IDBTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */