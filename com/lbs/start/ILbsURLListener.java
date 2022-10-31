package com.lbs.start;

public interface ILbsURLListener {
  void launchURL(String paramString1, String paramString2);
  
  void launchURL(String paramString1, int paramInt1, int paramInt2, String paramString2);
  
  void login(String paramString1, String paramString2, String paramString3);
  
  String getSessionCode();
  
  String getUODUrl();
  
  String getUODFirm();
  
  boolean canLogout();
  
  void toggleOneClickLogging();
  
  void changeCompany(String paramString1, String paramString2, String paramString3);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\ILbsURLListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */