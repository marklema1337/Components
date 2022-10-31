package com.lbs.juel.tree;

public interface FunctionNode extends Node {
  String getName();
  
  int getIndex();
  
  int getParamCount();
  
  boolean isVarArgs();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\FunctionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */