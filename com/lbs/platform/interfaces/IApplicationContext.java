/*    */ package com.lbs.platform.interfaces;public interface IApplicationContext { public static final String CONTEXT_TYPE = "!élbsContextTypeé!"; boolean login(UserInfo paramUserInfo);
/*    */   boolean isLoggedIn();
/*    */   boolean logout();
/*    */   UserInfo getUserInfo();
/*    */   IUserDetailInfo getUserDetailInfo();
/*    */   byte[] getResource(String paramString, boolean paramBoolean) throws FileNotFoundException;
/*    */   void setVariable(Object paramObject1, Object paramObject2);
/*    */   Object getVariable(Object paramObject);
/*    */   void removeVariable(Object paramObject);
/*    */   void removeAllVariables();
/*    */   Object createInstance(String paramString) throws Exception;
/*    */   Class<?> loadClass(String paramString) throws Exception;
/*    */   RemoteMethodResponse callRemoteMethod(String paramString1, String paramString2, Object[] paramArrayOfObject) throws Exception;
/*    */   RemoteMethodResponse callRemoteMethod(boolean paramBoolean, String paramString1, String paramString2, Object[] paramArrayOfObject) throws Exception;
/*    */   RemoteMethodResponse callRemoteMethod(String paramString1, String paramString2, Object[] paramArrayOfObject, boolean[] paramArrayOfboolean) throws Exception;
/*    */   RemoteMethodResponse callRemoteMethod(boolean paramBoolean, String paramString1, String paramString2, Object[] paramArrayOfObject, boolean[] paramArrayOfboolean) throws Exception;
/*    */   IAuthorizationManager getAuthorizationManager();
/*    */   ICacheManager getCacheManager();
/*    */   IIterationManager getIterationManager();
/*    */   IObjectFactory getObjectFactory();
/*    */   IQueryFactory getQueryFactory();
/*    */   ILocalizationServices getLocalizationService();
/*    */   ILocalizationServices getReportingLocalizationService();
/*    */   ILbsConsole getLogger();
/*    */   JLbsMessageDialogResult showMessage(String paramString1, String paramString2, Object[] paramArrayOfObject, ILbsMessageListener paramILbsMessageListener);
/*    */   IMessageService getMessageService();
/* 27 */   public enum ContextType { STANDART, EMULATING, TEST_PLAYER; }
/*    */    }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IApplicationContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */