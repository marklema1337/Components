package com.lbs.control.interfaces;

public interface IRowOperationsHandler {
  Object addRowObject(int paramInt, IRowAddListener paramIRowAddListener) throws Exception;
  
  Object addRowObject(IRowAddListener paramIRowAddListener) throws Exception;
  
  void deleteRowObject(int paramInt) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\IRowOperationsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */