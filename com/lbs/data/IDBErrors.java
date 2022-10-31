package com.lbs.data;

public interface IDBErrors {
  public static final int ERROR_EXCEPTION = 1;
  
  public static final int ERROR_GENERAL = 2;
  
  public static final int ERROR_UNCLASSIFIED = 3;
  
  public static final int ERROR_SQL = 4;
  
  public static final int ERROR_SCHEMA = 5;
  
  public static final int ERROR_ARGUMENT = 6;
  
  public static final int ERROR_ALREADY_LOCKED = 10;
  
  public static final int ERROR_NOT_LOCKED = 11;
  
  public static final int ERROR_NOT_ACTIVE = 12;
  
  public static final int ERROR_ROLLED_BACK = 20;
  
  public static final int ERROR_CONNECTION = 21;
  
  public static final int ERROR_CONSTRAINT_VIOLATION = 31;
  
  public static final int ERROR_REFERENCE_EXISTS = 32;
  
  public static final int ERROR_RECORD_LIMIT = 33;
  
  public static final int ERROR_CANNOT_CHANGE = 34;
  
  public static final int ERROR_NO_EQUIVALENT = 40;
  
  public static final int ERROR_SECONDARY_KEY = 41;
  
  public static final int ERROR_SESSION_TIMEOUT = 50;
  
  public static final int ERROR_SESSION_REESTABLISHED = 51;
  
  public static final int ERROR_SQL_STATEMENT_TIMEOUT = 52;
  
  public static final int ERROR_OALL8_INCONSISTENT_STATE = 70;
  
  public static final int ERROR_ORA_BLOB_FAILURE = 71;
  
  public static final String PROP_INDEX_NAME = "IndexName";
  
  public static final String PROP_INDEX_SEGMENTS = "IndexSegments";
  
  public static final String PROP_QUERY_NAME = "queryName";
  
  public static final String PROP_QUERY_TIMEOUT = "queryTimeout";
  
  public static final String PROP_SQL_STATEMENT = "SQLStatement";
  
  public static final String PROP_TABLE_NAME = "TableName";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\IDBErrors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */