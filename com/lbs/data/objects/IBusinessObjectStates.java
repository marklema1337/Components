package com.lbs.data.objects;

public interface IBusinessObjectStates {
  public static final int STATE_NEW = 0;
  
  public static final int STATE_UNMODIFIED = 1;
  
  public static final int STATE_MODIFIED = 2;
  
  public static final int STATE_DELETED = 3;
  
  public static final int LAZY_STATE_DELAYED = 0;
  
  public static final int LAZY_STATE_MINIMAL = 1;
  
  public static final int LAZY_STATE_NOBLOBS = 2;
  
  public static final int LAZY_STATE_FULL = 3;
  
  public static final String READ_FROM_XML = "!!!éééREAD_FROM_XMLééé!!!";
  
  public static final String SERVERSIDE_INITIALIZED = "!!!éééSERVERSIDE_INITIALIZEDééé!!!";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\IBusinessObjectStates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */