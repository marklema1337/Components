/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.console.LbsLevel;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.invoke.SessionTimeoutException;
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.transport.health.TransportHealthUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsOpenWindowListing;
/*     */ import com.lbs.util.StackUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteMethodInvoker
/*     */   implements IRemoteMethodInvoker
/*     */ {
/*     */   protected ObjectTransportClient m_TransClient;
/*     */   protected String m_RootURI;
/*     */   protected IRemoteSessionTimeoutHandler m_TimeoutHandler;
/*     */   protected IRemoteSessionInvokeHandler m_InvokeHandler;
/*     */   protected ILbsServerEventListener m_ServerEventListener;
/*     */   public static final String MONITORED_METHOD_CALLS = "";
/*  32 */   private static final transient LbsConsole ms_Logger = LbsConsole.getLogger("Transport.RemoteMethodInvoker");
/*     */ 
/*     */   
/*     */   public RemoteMethodInvoker(ObjectTransportClient transClient, String rootURI) {
/*  36 */     this.m_TransClient = transClient;
/*  37 */     this.m_RootURI = rootURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(String objDescr, String methodName) throws Exception {
/*  42 */     return InvokeMethod(objDescr, methodName, (Object[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(String objDescr, String methodName, Object[] parameters) throws Exception {
/*  47 */     return InvokeMethod(objDescr, methodName, parameters, (boolean[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(String objDescr, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/*  53 */     return InvokeMethod(objDescr, methodName, parameters, returnParams, (ClassLoader)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader) throws Exception {
/*  59 */     return InvokeMethod(false, objDescr, methodName, parameters, returnParams, clsLoader);
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(boolean newInstance, String objDescr, String methodName) throws Exception {
/*  64 */     return InvokeMethod(newInstance, objDescr, methodName, (Object[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(boolean newInstance, String objDescr, String methodName, Object[] parameters) throws Exception {
/*  70 */     return InvokeMethod(newInstance, objDescr, methodName, parameters, (boolean[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(boolean newInstance, String objDescr, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/*  76 */     return InvokeMethod(newInstance, objDescr, methodName, parameters, returnParams, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse InvokeMethod(boolean newInstance, String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader) throws Exception {
/*     */     try {
/*  84 */       if (this.m_TransClient == null) {
/*  85 */         throw new Exception("Object transport client is not set to an instance!");
/*     */       }
/*  87 */       if (ms_Logger != null && ms_Logger.isEnabledFor2(LbsLevel.TRACE)) {
/*  88 */         ms_Logger.trace("Remoting " + objDescr + "." + methodName);
/*     */       }
/*     */       try {
/*  91 */         RemoteMethodRequest request = new RemoteMethodRequest();
/*  92 */         request.TargetServlet = objDescr;
/*  93 */         request.MethodName = methodName;
/*  94 */         request.Parameters = parameters;
/*  95 */         request.ReturnParams = returnParams;
/*  96 */         request.newInstance = newInstance;
/*  97 */         if (!StringUtil.isEmpty("") && (
/*  98 */           "".indexOf(String.valueOf(objDescr) + "." + methodName) >= 0 || "".equals("*"))) {
/*     */           
/* 100 */           request.StackInfo = StackUtil.getStack(true);
/* 101 */           request.OpenWindowNames = JLbsOpenWindowListing.getOpenWindowNames();
/*     */         } 
/* 103 */         byte[] returnData = this.m_TransClient.postData(this.m_RootURI, request, clsLoader);
/* 104 */         if (this.m_InvokeHandler != null)
/* 105 */           this.m_InvokeHandler.remoteSessionInvoked(); 
/* 106 */         if (returnData != null) {
/*     */           
/* 108 */           Object obj = TransportUtil.deserializeObject(clsLoader, returnData);
/* 109 */           if (obj != null)
/*     */           {
/* 111 */             if (obj instanceof SessionTimeoutException)
/*     */             
/* 113 */             { if (!JLbsConstants.DESIGN_TIME)
/*     */               {
/*     */                 
/* 116 */                 if (this.m_TimeoutHandler != null) {
/*     */                   
/* 118 */                   boolean result = this.m_TimeoutHandler.remoteSessionTimeout((Exception)obj);
/* 119 */                   if (!result) {
/* 120 */                     throw (SessionTimeoutException)obj;
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 127 */                   throw (SessionTimeoutException)obj;
/*     */                 }  }  }
/* 129 */             else { if (obj instanceof LbsLocalizableException) {
/*     */                 
/* 131 */                 ((LbsLocalizableException)obj).localizeMessage(JLbsLocalizer.getLocalizationService());
/* 132 */                 throw (LbsLocalizableException)obj;
/*     */               } 
/* 134 */               if (obj instanceof BasicException)
/* 135 */                 throw new Exception(((BasicException)obj).Message); 
/* 136 */               if (obj instanceof Exception)
/* 137 */                 throw (Exception)obj; 
/* 138 */               if (obj instanceof RemoteMethodResponse) {
/*     */                 
/* 140 */                 RemoteMethodResponse invokeResponse = (RemoteMethodResponse)obj;
/* 141 */                 if (this.m_ServerEventListener != null && invokeResponse.Events != null) {
/*     */                   
/* 143 */                   LbsServerEventList eventList = invokeResponse.Events;
/* 144 */                   LbsServerEvent event = null;
/* 145 */                   for (int idx = 0; idx < eventList.size(); idx++) {
/*     */                     
/* 147 */                     event = eventList.getEvent(idx);
/* 148 */                     this.m_ServerEventListener.onServerEvent(eventList, event);
/* 149 */                     if (event != null && !event.canDispatch())
/*     */                       break; 
/*     */                   } 
/*     */                 } 
/* 153 */                 return invokeResponse;
/*     */               }  }
/*     */           
/*     */           }
/*     */         } 
/* 158 */       } catch (SessionReestablishedException e) {
/*     */ 
/*     */         
/* 161 */         return InvokeMethod(newInstance, objDescr, methodName, parameters, returnParams, clsLoader);
/*     */       } 
/* 163 */       throw new Exception("The invoke result is not valid, can be a communication error!");
/*     */     }
/* 165 */     catch (Exception e) {
/*     */       
/* 167 */       TransportHealthUtil.onTransportException(e);
/* 168 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IRemoteSessionTimeoutHandler getTimeoutHandler() {
/* 174 */     return this.m_TimeoutHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeoutHandler(IRemoteSessionTimeoutHandler handler) {
/* 179 */     this.m_TimeoutHandler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRemoteSessionInvokeHandler getInvokeHandler() {
/* 184 */     return this.m_InvokeHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvokeHandler(IRemoteSessionInvokeHandler handler) {
/* 189 */     this.m_InvokeHandler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsServerEventListener getServerEventListener() {
/* 194 */     return this.m_ServerEventListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServerEventListener(ILbsServerEventListener serverEventListener) {
/* 199 */     this.m_ServerEventListener = serverEventListener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse invoke(String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader, boolean newInstance) throws Exception {
/* 205 */     return InvokeMethod(newInstance, objDescr, methodName, parameters, returnParams, clsLoader);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse invoke(String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader) throws Exception {
/* 211 */     return InvokeMethod(false, objDescr, methodName, parameters, returnParams, clsLoader);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\RemoteMethodInvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */