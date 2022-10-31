package com.lbs.appobjects;

import java.util.ArrayList;

public interface LoginPreferenceCollector {
  ArrayList<Object> getUserLoginPreferences(String paramString) throws Exception;
  
  ArrayList<Object> getAdminLoginPreferences(String paramString) throws Exception;
  
  String getVersion();
  
  int getFirmForVersion(String paramString1, String paramString2, String paramString3) throws Exception;
  
  Object[] getSupportedCultures();
  
  void loginPreferencesReady(String paramString1, int paramInt, String paramString2, Object paramObject, Integer paramInteger);
  
  boolean canCollectDefaultCompany();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\LoginPreferenceCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */