package com.lbs.interfaces;

public interface ILbsURLActionHandler {
  public static final int URL_TYPE_APP = 0;
  
  public static final int URL_TYPE_HTTP = 1;
  
  public static final int URL_TYPE_DOC = 2;
  
  public static final int URL_TYPE_CUSTOM = 3;
  
  void processURL(int paramInt1, int paramInt2, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsURLActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */