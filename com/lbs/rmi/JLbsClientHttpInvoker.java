/*     */ package com.lbs.rmi;
/*     */ 
/*     */ import com.lbs.data.factory.FactoryParams;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.invoke.SessionTimeoutException;
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.transport.BasicException;
/*     */ import com.lbs.transport.ILbsServerEventListener;
/*     */ import com.lbs.transport.IRemoteMethodInvoker;
/*     */ import com.lbs.transport.IRemoteSessionInvokeHandler;
/*     */ import com.lbs.transport.IRemoteSessionTimeoutHandler;
/*     */ import com.lbs.transport.ISessionBase;
/*     */ import com.lbs.transport.LbsServerEvent;
/*     */ import com.lbs.transport.LbsServerEventList;
/*     */ import com.lbs.transport.ObjectInputStreamWithLoader;
/*     */ import com.lbs.transport.RemoteMethodRequest;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.transport.TransportClientRecorder;
/*     */ import com.lbs.transport.health.TransportHealthUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsOpenWindowListing;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.JLbsTransportMode;
/*     */ import com.lbs.util.StackUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.CipherInputStream;
/*     */ import javax.crypto.CipherOutputStream;
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
/*     */ public class JLbsClientHttpInvoker
/*     */   extends HttpInvokerRequestExecutor
/*     */   implements IRemoteMethodInvoker
/*     */ {
/*     */   protected String m_RootUrl;
/*     */   protected ISessionBase m_Session;
/*     */   protected String m_Service;
/*     */   protected ClassLoader m_ClassLoader;
/*     */   private IRemoteSessionTimeoutHandler m_TimeoutHandler;
/*     */   private IRemoteSessionInvokeHandler m_InvokeHandler;
/*     */   private ILbsServerEventListener m_ServerEventListener;
/*     */   protected TransportClientRecorder recorder;
/*  67 */   private WeakReference<JLbsClientHttpInvoker> m_InvokerRef = null;
/*     */   
/*  69 */   private static ArrayList<WeakReference<JLbsClientHttpInvoker>> ms_Invokers = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsClientHttpInvoker(String rootUrl, String service, ISessionBase session, ClassLoader classLoader) {
/*  80 */     this.m_RootUrl = rootUrl;
/*     */     
/*  82 */     this.m_Session = session;
/*  83 */     this.m_Service = service;
/*  84 */     this.m_ClassLoader = classLoader;
/*  85 */     this.m_InvokerRef = new WeakReference<>(this);
/*  86 */     synchronized (ms_Invokers) {
/*     */       
/*  88 */       ms_Invokers.add(this.m_InvokerRef);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateInvokersSession(ISessionBase session) {
/*  94 */     synchronized (ms_Invokers) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 100 */         int iSize = ms_Invokers.size();
/* 101 */         for (int i = iSize - 1; i >= 0; i--) {
/*     */           
/* 103 */           WeakReference<JLbsClientHttpInvoker> ref = ms_Invokers.get(i);
/* 104 */           Object invoker = ref.get();
/* 105 */           if (invoker instanceof JLbsClientHttpInvoker) {
/* 106 */             ((JLbsClientHttpInvoker)invoker).m_Session = session;
/*     */           }
/*     */         } 
/* 109 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransportClientRecorder getRecorder() {
/* 117 */     return this.recorder;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsClientHttpInvoker setRecorder(TransportClientRecorder watcher) {
/* 122 */     this.recorder = watcher;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareConnection(HttpURLConnection con, int contentLength) throws IOException {
/* 129 */     super.prepareConnection(con, contentLength);
/* 130 */     con.setRequestProperty("Session-Id", this.m_Session.getSessionCode());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ObjectInputStream createObjectInputStream(InputStream is) throws IOException {
/* 136 */     return (ObjectInputStream)new ObjectInputStreamWithLoader(is, this.m_ClassLoader);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ObjectOutputStream createObjectOutputStream(OutputStream os) throws IOException {
/* 142 */     return new ObjectOutputStream(os);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream readResponseBody(HttpURLConnection con) throws IOException {
/* 148 */     return con.getInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected RemoteMethodResponse doReadRemoteInvocationResult(ObjectInputStream ois) throws Exception {
/* 154 */     if (this.m_InvokeHandler != null) {
/* 155 */       this.m_InvokeHandler.remoteSessionInvoked();
/*     */     }
/* 157 */     Object obj = ois.readObject();
/* 158 */     checkInvocationResult(obj);
/* 159 */     if (!(obj instanceof RemoteMethodResponse))
/*     */     {
/* 161 */       throw new RemoteException("Deserialized object needs to be assignable to type [" + RemoteMethodResponse.class.getName() + 
/* 162 */           "]: " + obj);
/*     */     }
/* 164 */     return (RemoteMethodResponse)obj;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected OutputStream decorateOutputStream(OutputStream os) throws IOException {
/* 170 */     if (JLbsTransportMode.ENCRYPTION_ON) {
/*     */       
/* 172 */       Cipher cipher = this.m_Session.getEncryptionCipher();
/* 173 */       if (cipher != null) {
/* 174 */         os = new CipherOutputStream(os, cipher);
/*     */       }
/*     */     } 
/* 177 */     if (JLbsTransportMode.COMPRESSION_ON) {
/* 178 */       os = new GZIPOutputStream(os);
/*     */     }
/* 180 */     if (JLbsHttpInvoker.STREAM_BUFFER_SIZE > 0) {
/* 181 */       os = new BufferedOutputStream(os, JLbsHttpInvoker.STREAM_BUFFER_SIZE);
/*     */     }
/* 183 */     return os;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream decorateInputStream(InputStream is, HttpURLConnection con) throws IOException {
/* 189 */     boolean hasEnc = JLbsTransportMode.ENCRYPTION_ON;
/* 190 */     if (JLbsStringUtil.equals(con.getHeaderField("Content-Mode"), "gzip"))
/* 191 */       hasEnc = false; 
/* 192 */     if (hasEnc) {
/*     */       
/* 194 */       Cipher cipher = this.m_Session.getDecryptionCipher();
/* 195 */       if (cipher != null) {
/* 196 */         is = new CipherInputStream(is, cipher);
/*     */       }
/*     */     } 
/* 199 */     if (JLbsTransportMode.COMPRESSION_ON) {
/* 200 */       is = new GZIPInputStream(is);
/*     */     }
/* 202 */     if (JLbsHttpInvoker.STREAM_BUFFER_SIZE > 0) {
/* 203 */       is = new BufferedInputStream(is, JLbsHttpInvoker.STREAM_BUFFER_SIZE);
/*     */     }
/* 205 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getServiceUrl() {
/* 211 */     return String.valueOf(this.m_RootUrl) + this.m_Service;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRootUrl() {
/* 216 */     return this.m_RootUrl;
/*     */   }
/*     */   
/* 219 */   private static final String[] SECONDARY_KEY_RESOLVE_ON = new String[] { "SF.delete" };
/* 220 */   private static final int[][] SECONDARY_KEY_RESOLVE_PR = new int[][] { { 0, 1, 2 } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse invoke(String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader, boolean newInstance) throws Exception {
/*     */     try {
/* 227 */       logger.trace("Remoting " + objDescr + "." + methodName);
/*     */       
/*     */       try {
/* 230 */         RemoteMethodRequest request = new RemoteMethodRequest();
/* 231 */         request.TargetServlet = objDescr;
/* 232 */         request.MethodName = methodName;
/* 233 */         request.Parameters = renderParamsIfTestScope(request, parameters);
/* 234 */         request.ReturnParams = returnParams;
/* 235 */         request.newInstance = newInstance;
/*     */         
/* 237 */         if (!StringUtil.isEmpty("") && (
/* 238 */           "".indexOf(String.valueOf(objDescr) + "." + methodName) >= 0 || ""
/* 239 */           .equals("*"))) {
/*     */           
/* 241 */           request.StackInfo = StackUtil.getStack(true);
/* 242 */           request.OpenWindowNames = JLbsOpenWindowListing.getOpenWindowNames();
/*     */         } 
/*     */         
/* 245 */         RemoteMethodResponse response = executeRequest(request);
/* 246 */         checkServerEvents(response);
/* 247 */         checkInvocationResult(response.Result);
/* 248 */         if (this.recorder != null)
/* 249 */           this.recorder.remoteMethodInvoked(request, response); 
/* 250 */         return response;
/*     */       
/*     */       }
/* 253 */       catch (SessionReestablishedException e) {
/*     */ 
/*     */         
/* 256 */         return invoke(objDescr, methodName, parameters, returnParams, clsLoader, newInstance);
/*     */       }
/*     */     
/*     */     }
/* 260 */     catch (Exception e) {
/*     */       
/* 262 */       TransportHealthUtil.onTransportException(e);
/* 263 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Object[] renderParamsIfTestScope(RemoteMethodRequest request, Object[] parameters) throws Exception {
/* 269 */     if (this.recorder == null) {
/* 270 */       return parameters;
/*     */     }
/*     */     
/* 273 */     String sm = String.valueOf(request.TargetServlet) + "." + request.MethodName;
/* 274 */     for (int i = 0; i < SECONDARY_KEY_RESOLVE_ON.length; i++) {
/*     */       
/* 276 */       String cand = SECONDARY_KEY_RESOLVE_ON[i];
/* 277 */       if (sm.equals(cand)) {
/*     */         
/* 279 */         int[] paramMap = SECONDARY_KEY_RESOLVE_PR[i];
/* 280 */         String className = (String)parameters[paramMap[0]];
/* 281 */         Integer key = (Integer)parameters[paramMap[1]];
/* 282 */         if (this.m_InvokeHandler instanceof IClientContext) {
/*     */           
/* 284 */           IClientContext context = (IClientContext)this.m_InvokeHandler;
/* 285 */           FactoryParams fp = (FactoryParams)parameters[paramMap[2]];
/*     */           
/*     */           try {
/* 288 */             this.recorder.setEnabled(false);
/* 289 */             String skd = context.getObjectFactory().getSecondaryKeyData(className, key);
/* 290 */             fp.getParameters().setObject(">SECONDARY_KEY_DATA<", skd);
/*     */           }
/*     */           finally {
/*     */             
/* 294 */             this.recorder.setEnabled(true);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 299 */     return parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteMethodResponse invoke(String objDescr, String methodName, Object[] parameters, boolean[] returnParams, ClassLoader clsLoader) throws Exception {
/* 306 */     return invoke(objDescr, methodName, parameters, returnParams, clsLoader, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkServerEvents(RemoteMethodResponse response) {
/* 311 */     if (this.m_ServerEventListener != null && response.Events != null) {
/*     */       
/* 313 */       LbsServerEventList eventList = response.Events;
/* 314 */       LbsServerEvent event = null;
/* 315 */       for (int idx = 0; idx < eventList.size(); idx++) {
/*     */         
/* 317 */         event = eventList.getEvent(idx);
/* 318 */         this.m_ServerEventListener.onServerEvent(eventList, event);
/* 319 */         if (event != null && !event.canDispatch()) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void checkInvocationResult(Object result) throws Exception {
/* 327 */     if (result instanceof SessionTimeoutException)
/*     */     
/* 329 */     { if (!JLbsConstants.DESIGN_TIME)
/*     */       {
/*     */         
/* 332 */         if (this.m_TimeoutHandler != null) {
/*     */           
/* 334 */           boolean ok = this.m_TimeoutHandler.remoteSessionTimeout((Exception)result);
/* 335 */           if (!ok) {
/* 336 */             throw (SessionTimeoutException)result;
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 343 */           throw (SessionTimeoutException)result;
/*     */         }  }  }
/* 345 */     else { if (result instanceof LbsLocalizableException) {
/*     */         
/* 347 */         ((LbsLocalizableException)result).localizeMessage(JLbsLocalizer.getLocalizationService());
/* 348 */         throw (LbsLocalizableException)result;
/*     */       } 
/* 350 */       if (result instanceof BasicException)
/* 351 */         throw new Exception(((BasicException)result).Message); 
/* 352 */       if (result instanceof Exception)
/* 353 */         throw (Exception)result;  }
/*     */   
/*     */   }
/*     */   
/*     */   public IRemoteSessionTimeoutHandler getTimeoutHandler() {
/* 358 */     return this.m_TimeoutHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeoutHandler(IRemoteSessionTimeoutHandler handler) {
/* 363 */     this.m_TimeoutHandler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRemoteSessionInvokeHandler getInvokeHandler() {
/* 368 */     return this.m_InvokeHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvokeHandler(IRemoteSessionInvokeHandler handler) {
/* 373 */     this.m_InvokeHandler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsServerEventListener getServerEventListener() {
/* 378 */     return this.m_ServerEventListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServerEventListener(ILbsServerEventListener serverEventListener) {
/* 383 */     this.m_ServerEventListener = serverEventListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 390 */     synchronized (ms_Invokers) {
/*     */       
/* 392 */       if (this.m_InvokerRef != null)
/* 393 */         ms_Invokers.remove(this.m_InvokerRef); 
/*     */     } 
/* 395 */     super.finalize();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\JLbsClientHttpInvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */