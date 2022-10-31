/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.interfaces.IVariableHolder;
/*     */ import com.lbs.platform.interfaces.IDeveloperContext;
/*     */ import com.lbs.transport.ISessionBase;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.transport.TransportClient;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Component;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IClientContext
/*     */   extends IDeveloperContext, IVariableHolder
/*     */ {
/*     */   public static final String URI_LOGIN = "Login";
/*     */   public static final String URI_LOGOUT = "Logout";
/*     */   public static final String URI_REMOTING = "Remoting";
/*     */   public static final String URI_RESOURCES = "ResDownload";
/*     */   public static final String URI_CLASSES = "ClassDownload";
/*     */   public static final String URI_PUBLICINFO = "PublicInfo";
/*     */   public static final String URI_REPORTING = "Reporting";
/*     */   public static final String URI_RUNBATCH = "RunBatch";
/*     */   public static final String URI_HELP = "Help";
/*     */   
/*     */   boolean relogin(String paramString);
/*     */   
/*     */   void clearUserInfoAndHashTable();
/*     */   
/*     */   ISessionBase getSession();
/*     */   
/*     */   Class loadClass(String paramString) throws Exception;
/*     */   
/*     */   boolean loadResourceJAR(String paramString1, String paramString2, boolean paramBoolean) throws FileNotFoundException;
/*     */   
/*     */   void replaceContent(Component paramComponent);
/*     */   
/*     */   boolean changeTheme(String paramString);
/*     */   
/*     */   Object getPublicObject(String paramString, Object paramObject, boolean paramBoolean) throws Exception;
/*     */   
/*     */   Object[] getPublicObjects(String[] paramArrayOfString, Object[] paramArrayOfObject, boolean paramBoolean) throws Exception;
/*     */   
/*     */   byte[][] loadMultipleResource(String[] paramArrayOfString, boolean paramBoolean);
/*     */   
/*     */   void terminateSession(boolean paramBoolean);
/*     */   
/*     */   void terminateApplication();
/*     */   
/*     */   void setSessionTimeout(int paramInt);
/*     */   
/*     */   void resetTimeoutTimer();
/*     */   
/*     */   void setReportExecutor(IReportExecutor paramIReportExecutor);
/*     */   
/*     */   String getRootUri();
/*     */   
/*     */   String getClientUri();
/*     */   
/*     */   boolean changeCompany(UserInfo paramUserInfo);
/*     */   
/*     */   String getCookie(String paramString);
/*     */   
/*     */   void setCookie(String paramString1, String paramString2) throws Exception;
/*     */   
/*     */   TransportClient getTransportClient();
/*     */   
/*     */   RemoteMethodResponse callRemoteMethod(String paramString1, String paramString2, Object[] paramArrayOfObject, boolean[] paramArrayOfboolean, String paramString3) throws Exception;
/*     */   
/*     */   void initializeCacheConfiguration();
/*     */   
/*     */   void startLoadingLocalizationCache();
/*     */   
/*     */   void setTerminateURI(String paramString);
/*     */   
/*     */   void setCustomizationResourceList();
/*     */   
/*     */   void addToCustomizationResourceList(String paramString);
/*     */   
/*     */   TimeZone getServerTimeZone();
/*     */   
/*     */   void setServerTimeZone();
/*     */   
/*     */   TimeZone getDefaultTimeZone();
/*     */   
/*     */   Calendar getServerTime();
/*     */   
/*     */   boolean runReport(String paramString, Object paramObject1, Object paramObject2) throws FileNotFoundException, InvocationTargetException;
/*     */   
/*     */   boolean openNewWindow();
/*     */   
/*     */   static boolean testReportingURIExistance(String reportingUri) {
/* 238 */     if (!JLbsStringUtil.isEmpty(reportingUri)) {
/*     */       
/*     */       try {
/*     */         
/* 242 */         URL url = null;
/* 243 */         if (reportingUri.endsWith("Reporting/") || reportingUri.endsWith("Reporting")) {
/*     */           
/* 245 */           url = new URL(reportingUri);
/*     */ 
/*     */         
/*     */         }
/* 249 */         else if (reportingUri.endsWith("/")) {
/* 250 */           url = new URL(String.valueOf(reportingUri) + "Reporting");
/*     */         } else {
/* 252 */           url = new URL(String.valueOf(reportingUri) + "/" + "Reporting");
/*     */         } 
/*     */         
/* 255 */         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/* 256 */         connection.getInputStream();
/* 257 */         return true;
/*     */       }
/* 259 */       catch (IOException ex) {
/*     */         
/* 261 */         LbsConsole.getLogger("IClientContext").error(ex);
/*     */       } 
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Path getTempDirectory() {
/* 274 */     return Paths.get(System.getProperty("java.io.tmpdir"), new String[0]);
/*     */   }
/*     */   
/*     */   void setGlobalPopups(List<Object> paramList);
/*     */   
/*     */   List<Object> getGlobalPopups();
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\IClientContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */