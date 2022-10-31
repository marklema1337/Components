package com.lbs.interfaces;

public interface ILbsExpressionEvaluator {
  public static final int OPEN_PARAM_COUNT = 9998;
  
  Object evaluateExpression(String paramString) throws Exception;
  
  void setObject(Object paramObject);
  
  void setDoubleDiv(boolean paramBoolean);
  
  void markExtendedVars(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsExpressionEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */