package com.lbs.control.interfaces;

public interface ILbsWebBrowser extends ILbsComponent {
  void setStatusBarVisible(boolean paramBoolean);
  
  boolean isStatusBarVisible();
  
  void setMenuBarVisible(boolean paramBoolean);
  
  boolean isMenuBarVisible();
  
  void setButtonBarVisible(boolean paramBoolean);
  
  boolean isButtonBarVisible();
  
  void setLocationBarVisible(boolean paramBoolean);
  
  boolean isLocationBarVisible();
  
  String getPageTitle();
  
  String getStatusText();
  
  String getResourceLocation();
  
  boolean isBackNavigationEnabled();
  
  boolean isForwardNavigationEnabled();
  
  void reloadPage();
  
  boolean isJavascriptEnabled();
  
  void setJavascriptEnabled(boolean paramBoolean);
  
  void executeJavascript(String paramString);
  
  Object executeJavascriptWithResult(String paramString);
  
  int getLoadingProgress();
  
  void setBarsVisible(boolean paramBoolean);
  
  boolean navigate(String paramString);
  
  boolean setHTMLContent(String paramString);
  
  String getHTMLContent();
  
  void navigateBack();
  
  void navigateForward();
  
  void stopLoading();
  
  void addWebBrowserListener(Object paramObject);
  
  void navigate(String paramString, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsWebBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */