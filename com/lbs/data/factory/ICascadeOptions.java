package com.lbs.data.factory;

public interface ICascadeOptions {
  public static final int CASCADE_NONE = 0;
  
  public static final int CASCADE_READ = 1;
  
  public static final int CASCADE_INSERT = 2;
  
  public static final int CASCADE_UPDATE = 4;
  
  public static final int CASCADE_DELETE = 8;
  
  public static final int CASCADE_TRIGGER_SPECIAL1 = 16;
  
  public static final int CASCADE_ALL = 31;
  
  public static final int CASCADE_PERSIST = 6;
  
  public static final int SET_NOT = 0;
  
  public static final int SET_FALSE = 1;
  
  public static final int SET_TRUE = 2;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ICascadeOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */