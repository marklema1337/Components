package com.lbs.javax.el;

public abstract class VariableMapper {
  public abstract ValueExpression resolveVariable(String paramString);
  
  public abstract ValueExpression setVariable(String paramString, ValueExpression paramValueExpression);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\VariableMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */