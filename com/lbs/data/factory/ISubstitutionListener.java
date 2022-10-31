package com.lbs.data.factory;

public interface ISubstitutionListener {
  String process(String paramString, INamedVariables paramINamedVariables, int paramInt, Object paramObject);
  
  String process(String paramString1, INamedVariables paramINamedVariables, int paramInt, String paramString2, Object paramObject);
  
  String getDBName(String paramString, INamedVariables paramINamedVariables, int paramInt, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ISubstitutionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */