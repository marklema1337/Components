package com.lbs.interfaces;

import com.lbs.util.JLbsStringListItemEx;

public interface IModuleAction {
  public static final int MIACT_FORM = 1;
  
  public static final int MIACT_REPORT = 2;
  
  public static final int MIACT_CUSTOM = 3;
  
  public static final int MIACT_CONTRACT = 4;
  
  int getActionType();
  
  String getDataClass();
  
  int[] getHiddenFilters();
  
  JLbsStringListItemEx[] getParamValues();
  
  String getQueryOrder();
  
  JLbsStringListItemEx[] getQueryParamValues();
  
  String[] getQueryTableLinks();
  
  String[] getQueryTerms();
  
  String getResource();
  
  void setActionType(int paramInt);
  
  void setDataClass(String paramString);
  
  void setHiddenFilters(int[] paramArrayOfint);
  
  void setParamValues(JLbsStringListItemEx[] paramArrayOfJLbsStringListItemEx);
  
  void setQueryOrder(String paramString);
  
  void setQueryParamValues(JLbsStringListItemEx[] paramArrayOfJLbsStringListItemEx);
  
  void setQueryTableLinks(String[] paramArrayOfString);
  
  void setQueryTerms(String[] paramArrayOfString);
  
  void setResource(String paramString);
  
  int getActionId();
  
  void setActionId(int paramInt);
  
  JLbsStringListItemEx[] getQueryVariableValues();
  
  void setQueryVariableValues(JLbsStringListItemEx[] paramArrayOfJLbsStringListItemEx);
  
  boolean isModal();
  
  void setModal(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\IModuleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */