/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.channel.IChannelProvider;
/*     */ import com.lbs.channel.ICompressor;
/*     */ import com.lbs.console.ILbsConsole;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.crypto.JLbsSymCryptoFactory;
/*     */ import com.lbs.data.factory.IObjectFactory;
/*     */ import com.lbs.data.query.IQueryFactory;
/*     */ import com.lbs.http.HttpChannelProvider;
/*     */ import com.lbs.http.HttpLoginProvider;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.message.ILbsMessageListener;
/*     */ import com.lbs.message.JLbsMessageDialogResult;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.IAuthorizationManager;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import com.lbs.platform.interfaces.IIterationManager;
/*     */ import com.lbs.platform.interfaces.IMessageService;
/*     */ import com.lbs.transport.ISessionBase;
/*     */ import com.lbs.transport.IUserDetailInfo;
/*     */ import com.lbs.transport.ObjectTransportClient;
/*     */ import com.lbs.transport.RemoteMethodInvoker;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.transport.StdCompressor;
/*     */ import com.lbs.transport.TransportClient;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Component;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.security.SignatureException;
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
/*     */ public class SimpleClientContext
/*     */   implements IClientContext
/*     */ {
/*     */   private String m_RootURI;
/*     */   private HttpChannelProvider m_ChannelProvider;
/*     */   private TransportClient m_BaseClient;
/*     */   private HttpLoginProvider m_LoginProvider;
/*     */   protected RemoteMethodInvoker m_RemoteInvoker;
/*     */   protected ISessionBase m_Session;
/*     */   protected UserInfo m_UserInfo;
/*     */   protected ObjectTransportClient m_TransportClient;
/*     */   private transient LbsConsole m_Logger;
/*     */   private List<Object> m_GlobalPopups;
/*     */   
/*     */   public SimpleClientContext(String rootURI) {
/*  63 */     this.m_ChannelProvider = new HttpChannelProvider(false);
/*  64 */     this.m_BaseClient = new TransportClient((IChannelProvider)this.m_ChannelProvider, null, null, null);
/*  65 */     this.m_LoginProvider = new HttpLoginProvider((ICompressor)new StdCompressor());
/*     */     
/*  67 */     this.m_RootURI = rootURI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessageDialogResult showMessage(String messageId, String defaultMessage, Object[] messageFormatArguments, ILbsMessageListener listener) {
/*  73 */     return JLbsContextMessageUtil.showMessage((IApplicationContext)this, messageId, defaultMessage, listener, messageFormatArguments);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean login(UserInfo userInfo) {
/*  78 */     logout();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       this.m_Session = this.m_LoginProvider.login((IChannelProvider)this.m_ChannelProvider, String.valueOf(this.m_RootURI) + "Login", userInfo, null);
/*  85 */       if (this.m_Session == null)
/*  86 */         return false; 
/*  87 */       this.m_UserInfo = userInfo;
/*     */       
/*  89 */       return true;
/*     */     }
/*  91 */     catch (Exception e) {
/*     */       
/*  93 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean relogin(String password) {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected RemoteMethodInvoker ensureTransClientExist(String serverRootURI) {
/* 105 */     if (JLbsStringUtil.isEmpty(serverRootURI)) {
/*     */       
/* 107 */       ensureTransClientExist();
/* 108 */       return this.m_RemoteInvoker;
/*     */     } 
/* 110 */     if (this.m_Session != null) {
/*     */       
/* 112 */       ObjectTransportClient transportClient = new ObjectTransportClient((IChannelProvider)this.m_ChannelProvider, this.m_Session, 
/* 113 */           JLbsSymCryptoFactory.createCryptor(this.m_Session.getLocalCryptoParam()), 
/* 114 */           JLbsSymCryptoFactory.createCryptor(this.m_Session.getRemoteCryptoParam()));
/* 115 */       RemoteMethodInvoker remoteInvoker = new RemoteMethodInvoker(transportClient, String.valueOf(serverRootURI) + "Remoting");
/* 116 */       return remoteInvoker;
/*     */     } 
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void ensureTransClientExist() {
/* 123 */     if (this.m_TransportClient == null && this.m_Session != null) {
/*     */       
/* 125 */       this.m_TransportClient = new ObjectTransportClient((IChannelProvider)this.m_ChannelProvider, this.m_Session, 
/* 126 */           JLbsSymCryptoFactory.createCryptor(this.m_Session.getLocalCryptoParam()), 
/* 127 */           JLbsSymCryptoFactory.createCryptor(this.m_Session.getRemoteCryptoParam()));
/* 128 */       this.m_RemoteInvoker = new RemoteMethodInvoker(this.m_TransportClient, String.valueOf(this.m_RootURI) + "Remoting");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IObjectFactory getObjectFactory() {
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IQueryFactory getQueryFactory() {
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/* 145 */     ensureTransClientExist();
/* 146 */     return this.m_RemoteInvoker.InvokeMethod(objName, methodName, parameters, returnParams, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters) throws Exception {
/* 151 */     return callRemoteMethod(objName, methodName, parameters, (boolean[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLoggedIn() {
/* 156 */     synchronized (this) {
/*     */       
/* 158 */       return (this.m_Session != null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean logout() {
/* 164 */     return logoutEx(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean logoutEx(boolean closeForms) {
/* 169 */     if (this.m_Session == null) {
/* 170 */       return true;
/*     */     }
/*     */     
/*     */     try {
/* 174 */       synchronized (this) {
/*     */         
/* 176 */         this.m_LoginProvider.logout((IChannelProvider)this.m_ChannelProvider, String.valueOf(this.m_RootURI) + "Logout", this.m_Session);
/* 177 */         this.m_Session = null;
/* 178 */         this.m_TransportClient = null;
/* 179 */         this.m_RemoteInvoker = null;
/*     */       } 
/* 181 */       return true;
/*     */     }
/* 183 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 186 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ISessionBase getSession() {
/* 191 */     return this.m_Session;
/*     */   }
/*     */ 
/*     */   
/*     */   public UserInfo getUserInfo() {
/* 196 */     return this.m_UserInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remoteSessionInvoked() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUserInfoAndHashTable() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void showMessage(String title, String message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] loadLocalFile(String relativePath) throws IOException {
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReportExecutor(IReportExecutor executor) {}
/*     */ 
/*     */   
/*     */   public boolean openHelpURL(String url, String targetWindow) {
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IReportExecutor getReportExecutor() {
/* 228 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean openURL(String url, String targetWindow) {
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void terminateSession(boolean exit) {}
/*     */ 
/*     */   
/*     */   public byte[] getResource(String resName, boolean bLangDep) throws FileNotFoundException {
/* 242 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void showError(Exception ex) {}
/*     */ 
/*     */   
/*     */   public String getRootUri() {
/* 251 */     return this.m_RootURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClientUri() {
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deleteLocalFile(String relativePath) {
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getPublicObject(String objectID, Object params, boolean bAsObject) throws Exception {
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getPublicObjects(String[] objectIDs, Object[] params, boolean asObject) throws Exception {
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveLocalFile(String relativePath, byte[] data, boolean overwrite, boolean append) throws IOException {
/* 276 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveLocalFile(String relativePath, byte[] data, int index, int length, boolean overwrite, boolean append) throws IOException {
/* 282 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object deserializeObject(byte[] data) {
/* 287 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void terminateApplication() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runReport(String repName, JLbsRunContextParameters ctxParams) throws FileNotFoundException, InvocationTargetException {
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadResourceJAR(String jarName, String pathPrefix, boolean silentLoad) throws FileNotFoundException {
/* 302 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getVariable(Object key) {
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean changeTheme(String newThemeClass) {
/* 312 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariable(Object key, Object obj) {}
/*     */ 
/*     */   
/*     */   public Class loadClass(String className) throws Exception {
/* 321 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObjectResource(String resName, boolean bLangDep) throws FileNotFoundException, InvalidObjectException {
/* 326 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[][] loadMultipleResource(String[] resNames, boolean bLangDep) {
/* 331 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSerializedObject(String resName, boolean bLangDep) {
/* 336 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object createInstance(String className) throws Exception {
/* 341 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestBatchOperation(String batchName, Object[] parameters) throws Exception {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceContent(Component comp) {}
/*     */ 
/*     */   
/*     */   public boolean loadJAR(String jarName, boolean signVerify, boolean silentLoad) throws SignatureException, FileNotFoundException {
/* 354 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSessionTimeout(int timeout) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTimeoutTimer() {}
/*     */ 
/*     */   
/*     */   public Object createNamedInstance(String className, String name) throws Exception {
/* 367 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean changeCompany(UserInfo userInfo) {
/* 372 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCookie(String name, String value) throws Exception {}
/*     */ 
/*     */   
/*     */   public String getCookie(String name) {
/* 381 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClient getTransportClient() {
/* 386 */     return this.m_BaseClient;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveLocalFile(String relativePath, byte[] data, boolean overwrite, boolean append, String charSet) throws IOException {
/* 392 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IAuthorizationManager getAuthorizationManager() {
/* 397 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICacheManager getCacheManager() {
/* 402 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIterationManager getIterationManager() {
/* 407 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IUserDetailInfo getUserDetailInfo() {
/* 412 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllVariables() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeVariable(Object key) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters, boolean[] returnParams, String serverRootURI) throws Exception {
/* 426 */     RemoteMethodInvoker remoteInvoker = ensureTransClientExist(serverRootURI);
/* 427 */     return remoteInvoker.InvokeMethod(objName, methodName, parameters, returnParams, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILocalizationServices getLocalizationService() {
/* 432 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILocalizationServices getReportingLocalizationService() {
/* 437 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeCacheConfiguration() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startLoadingLocalizationCache() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTerminateURI(String langPrefix) {}
/*     */ 
/*     */   
/*     */   public ILbsConsole getLogger() {
/* 454 */     if (this.m_Logger == null) {
/*     */       
/* 456 */       String extension = "";
/* 457 */       if (getUserInfo() != null && (getUserInfo()).Name != null)
/* 458 */         extension = "." + (getUserInfo()).Name; 
/* 459 */       this.m_Logger = LbsConsole.getLogger(String.valueOf(getClass().getName()) + extension);
/*     */     } 
/* 461 */     return (ILbsConsole)this.m_Logger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse callRemoteMethod(boolean newInstance, String objName, String methodName, Object[] parameters) throws Exception {
/* 471 */     return callRemoteMethod(newInstance, objName, methodName, parameters, (boolean[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse callRemoteMethod(boolean newInstance, String objName, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/* 481 */     ensureTransClientExist();
/* 482 */     return this.m_RemoteInvoker.InvokeMethod(newInstance, objName, methodName, parameters, returnParams, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToCustomizationResourceList(String fileName) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomizationResourceList() {}
/*     */ 
/*     */   
/*     */   public TimeZone getServerTimeZone() {
/* 495 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerTimeZone() {}
/*     */ 
/*     */   
/*     */   public TimeZone getDefaultTimeZone() {
/* 504 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runReport(String repName, Object ctxParams, Object runParams) throws FileNotFoundException, InvocationTargetException {
/* 510 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IMessageService getMessageService() {
/* 515 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getServerTime() {
/* 520 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean openNewWindow() {
/* 526 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object deserializeObjectPlain(byte[] data) {
/* 532 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getGlobalPopups() {
/* 537 */     return this.m_GlobalPopups;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGlobalPopups(List<Object> globalPopups) {
/* 542 */     this.m_GlobalPopups = globalPopups;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\SimpleClientContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */