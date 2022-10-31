package com.lbs.data.factory;

public interface IFactoryParams {
  public static final int OPTION_NONE = 0;
  
  public static final int OPTION_REQUESTLOCK = 1;
  
  public static final int OPTION_RELEASELOCK = 2;
  
  public static final int OPTION_OBJTRANSFEREDBACK = 4;
  
  public static final int OPTION_CUSTOMOBJONLY = 8;
  
  public static final int OPTION_NO_BLOBFIELDS = 16;
  
  public static final int OPTION_DISABLE_WFTRIGGERSERVICE = 32;
  
  public static final int OPTION_ENABLE_SECKEYRESOLVE = 64;
  
  public static final int OPTION_IGNOREERROR_ONSECKEYRESOLVE = 128;
  
  public static final int OPTION_READ_MODIFICATION_ONLY = 256;
  
  public static final int OPTION_ESCAPE_READ_PROCESSOR = 512;
  
  public static final int OPTION_DOMAINLESS = 1024;
  
  public static final int OPTION_SKIP_RECORD_LOG = 2048;
  
  public static final int OPTION_READ_MULTILANG_VALUES = 4096;
  
  public static final int OPTION_INITIALIZEOBJ_ONSERVER = 8192;
  
  public static final int OPTION_AUTO_INCREMENT_ALREADYLOCK = 16384;
  
  public static final int OPTION_FOR_EXCHANGE = 32768;
  
  public static final int OPTION_SEGMENTS_FOR_USER_ORDER = 65536;
  
  public static final int OPTION_DEEP_RECORD_LOG = 131072;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */