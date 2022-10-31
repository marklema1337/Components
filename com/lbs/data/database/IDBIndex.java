package com.lbs.data.database;

public interface IDBIndex {
  public static final String PREFIX_EXCLUSION_LIST = "EXC_";
  
  public static final String PREFIX_ADDITIONAL_LIST = "ADD_";
  
  public static final String PREFIX_DONOTTOUCH_LIST = "PASS_";
  
  public static final int IDX_TYPE_DEFAULT = 0;
  
  public static final int IDX_TYPE_EXCLUDED = 1;
  
  public static final int IDX_TYPE_ADDITIONAL = 2;
  
  public static final int IDX_TYPE_DO_NOT_TOUCH = 3;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\IDBIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */