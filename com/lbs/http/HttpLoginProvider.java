/*     */ package com.lbs.http;
/*     */ 
/*     */ import com.lbs.channel.ChannelListener;
/*     */ import com.lbs.channel.IChannel;
/*     */ import com.lbs.channel.IChannelListener;
/*     */ import com.lbs.channel.IChannelProvider;
/*     */ import com.lbs.channel.ICompressor;
/*     */ import com.lbs.channel.ICryptor;
/*     */ import com.lbs.channel.ILoginProvider;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.console.LbsLevel;
/*     */ import com.lbs.crypto.JLbsSymCryptoFactory;
/*     */ import com.lbs.crypto.UUCryptor;
/*     */ import com.lbs.globalization.JLbsInitializationData;
/*     */ import com.lbs.interfaces.ILbsSessionInitializer;
/*     */ import com.lbs.invoke.InvalidSessionException;
/*     */ import com.lbs.platform.client.SessionInitializerRegistry;
/*     */ import com.lbs.remoteclient.LocalizableException;
/*     */ import com.lbs.remoteclient.URLRedirectionException;
/*     */ import com.lbs.transport.BasicException;
/*     */ import com.lbs.transport.ISessionBase;
/*     */ import com.lbs.transport.LoginException;
/*     */ import com.lbs.transport.LoginInfo;
/*     */ import com.lbs.transport.LoginResult;
/*     */ import com.lbs.transport.RemoteRequest;
/*     */ import com.lbs.transport.SessionBase;
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsTransportMode;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ public class HttpLoginProvider
/*     */   implements ILoginProvider
/*     */ {
/*  48 */   public static final byte[] SECRETKEY = new byte[] { 17, 9, 19, 75, 29, 5, 78, 91, 125, -17, -9, -19, -75 };
/*     */   
/*     */   private ICompressor m_Compressor;
/*  51 */   private SessionBase m_SessionInfo = null;
/*     */   private Object m_OpResult;
/*  53 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger("Transport.HttpLoginProv");
/*  54 */   public static String ms_SessionCode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpLoginProvider(ICompressor comp) {
/*  60 */     this.m_Compressor = comp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ISessionBase login(IChannelProvider channelProv, String sStartUri, UserInfo info, ClassLoader clsLoader) throws LoginException {
/*     */     class LoginDataListener
/*     */       extends ChannelListener
/*     */     {
/*     */       private Object m_LocalCryptoData;
/*     */ 
/*     */       
/*     */       private ClassLoader m_clsLoader;
/*     */ 
/*     */       
/*     */       public LoginDataListener(Object localCryptoData, ClassLoader clsLoader) {
/*  76 */         this.m_LocalCryptoData = localCryptoData;
/*  77 */         this.m_clsLoader = clsLoader;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean channelDataRead(IChannel channel, byte[] data) {
/*     */         try {
/*  84 */           if (JLbsTransportMode.ROBOTMODE) {
/*     */             
/*  86 */             UUCryptor cryptor = new UUCryptor();
/*  87 */             data = cryptor.decrypt(data, 0, data.length);
/*     */           } 
/*     */           
/*  90 */           if (HttpLoginProvider.this.m_Compressor != null && data != null) {
/*  91 */             data = HttpLoginProvider.this.m_Compressor.uncompress(data, 0, data.length);
/*     */           }
/*     */ 
/*     */           
/*  95 */           if (data != null) {
/*     */             
/*  97 */             Object o = TransportUtil.deserializeRemoteObject(data, this.m_clsLoader);
/*  98 */             if (o instanceof LoginResult) {
/*     */               
/* 100 */               LoginResult loginRes = (LoginResult)o;
/* 101 */               HttpLoginProvider.this.m_SessionInfo = new SessionBase(loginRes, this.m_LocalCryptoData);
/* 102 */               HttpLoginProvider.this.m_OpResult = HttpLoginProvider.this.m_SessionInfo;
/*     */             }
/* 104 */             else if (o instanceof BasicException || o instanceof Exception) {
/* 105 */               HttpLoginProvider.this.m_OpResult = o;
/*     */             } 
/*     */           } 
/* 108 */         } catch (Exception e) {
/*     */           
/* 110 */           HttpLoginProvider.this.m_OpResult = e;
/*     */         } 
/* 112 */         return true;
/*     */       }
/*     */     };
/*     */     
/*     */     try {
/* 117 */       this.m_OpResult = null;
/* 118 */       LoginInfo loginInfo = new LoginInfo();
/* 119 */       loginInfo.UserInfo = info;
/* 120 */       if (JLbsTransportMode.ROBOTMODE) {
/*     */         
/* 122 */         String rStr = (new BigDecimal(Math.random())).toString();
/* 123 */         loginInfo.forcedSessionCode = "FSC-" + rStr;
/*     */       } 
/* 125 */       loginInfo.LocalCryptoData = TransportUtil.getRandomKey(16);
/* 126 */       sendChannelData(channelProv, sStartUri, getSendData(loginInfo), 
/* 127 */           (IChannelListener)new LoginDataListener(loginInfo.LocalCryptoData, clsLoader));
/*     */     }
/* 129 */     catch (Exception e) {
/*     */       
/* 131 */       throw new LoginException(-5, e.toString());
/*     */     } 
/* 133 */     if (this.m_OpResult instanceof BasicException)
/* 134 */       throw new LoginException(-6, ((BasicException)this.m_OpResult).Message); 
/* 135 */     if (this.m_OpResult instanceof InvalidSessionException) {
/*     */       
/* 137 */       InvalidSessionException e = (InvalidSessionException)this.m_OpResult;
/* 138 */       throw prepareLoginException(e);
/*     */     } 
/* 140 */     if (this.m_OpResult instanceof URLRedirectionException)
/* 141 */       throw new LoginException((URLRedirectionException)this.m_OpResult, -12); 
/* 142 */     if (this.m_OpResult instanceof Exception) {
/*     */       
/* 144 */       if (this.m_OpResult instanceof LocalizableException) {
/*     */         
/* 146 */         if (((LocalizableException)this.m_OpResult).getMessage().contains("Okta Correct Answer Required~"))
/* 147 */           throw new LoginException(-19, String.valueOf(((LocalizableException)this.m_OpResult).getMessage()) + "~" + ((LocalizableException)this.m_OpResult).getiResStr()); 
/* 148 */         if (((LocalizableException)this.m_OpResult).getiResStr() == 1070) {
/* 149 */           throw new LoginException(-6, ((Exception)this.m_OpResult).getMessage(), true);
/*     */         }
/* 151 */         throw new LoginException(-6, ((Exception)this.m_OpResult).getMessage());
/*     */       } 
/*     */       
/* 154 */       throw new LoginException(-6, ((Exception)this.m_OpResult).getMessage());
/*     */     } 
/*     */ 
/*     */     
/* 158 */     if (this.m_SessionInfo != null) {
/*     */       
/* 160 */       Object o = this.m_SessionInfo.getSessionData();
/* 161 */       if (o instanceof Hashtable) {
/*     */         
/* 163 */         Hashtable sessionHash = (Hashtable)o;
/* 164 */         Object p = sessionHash.get("SessionInitializers");
/* 165 */         if (p instanceof Vector) {
/*     */           
/* 167 */           Vector<Class<?>> initClasses = (Vector<Class<?>>)p;
/* 168 */           SessionInitializerRegistry.getInstance().getClientInitializers().clear();
/* 169 */           for (Class<ILbsSessionInitializer> c : initClasses) {
/*     */             
/*     */             try
/*     */             {
/* 173 */               SessionInitializerRegistry.getInstance().registerInitializer(
/* 174 */                   c.newInstance(), 2);
/*     */             }
/* 176 */             catch (Throwable t)
/*     */             {
/* 178 */               ms_Logger.error(t, t);
/*     */             }
/*     */           
/*     */           } 
/* 182 */         } else if (p instanceof ArrayList) {
/*     */           
/* 184 */           ArrayList<Class<?>> initClasses = (ArrayList<Class<?>>)p;
/* 185 */           SessionInitializerRegistry.getInstance().getClientInitializers().clear();
/* 186 */           for (Class<ILbsSessionInitializer> c : initClasses) {
/*     */ 
/*     */             
/*     */             try {
/* 190 */               SessionInitializerRegistry.getInstance().registerInitializer(
/* 191 */                   c.newInstance(), 2);
/*     */             }
/* 193 */             catch (Throwable t) {
/*     */               
/* 195 */               ms_Logger.error(t, t);
/*     */             } 
/*     */           } 
/*     */         } 
/* 199 */         info.globalResources = (JLbsStringList)sessionHash.get("GlobalResource_");
/* 200 */         JLbsInitializationData initData = (JLbsInitializationData)sessionHash.get("SessionInitData_");
/* 201 */         initData.LoginData = sessionHash;
/* 202 */         SessionInitializerRegistry.getInstance().initializeSession(info.variableHolder, initData, 
/* 203 */             2);
/*     */       } 
/*     */     } 
/* 206 */     return (ISessionBase)this.m_SessionInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginException prepareLoginException(InvalidSessionException e) {
/* 212 */     switch (e.getType()) {
/*     */       
/*     */       case 1:
/* 215 */         return new LoginException(-7, e.getMessage());
/*     */       case 2:
/* 217 */         return new LoginException(-8, e.getMessage());
/*     */       case 3:
/* 219 */         return new LoginException(-9, e.getMessage());
/*     */       case 4:
/* 221 */         return new LoginException(-10, e.getMessage());
/*     */       case 5:
/* 223 */         return new LoginException(-11, e.getMessage());
/*     */       case 6:
/* 225 */         return new LoginException(-13, e.getMessage());
/*     */       case 10:
/* 227 */         return new LoginException(-16, e.getMessage());
/*     */       case 7:
/* 229 */         return new LoginException(-14, e.getMessage());
/*     */       case 9:
/* 231 */         return new LoginException(-15, e.getMessage());
/*     */       case 11:
/* 233 */         return new LoginException(-17, e.getMessage());
/*     */       case 12:
/* 235 */         return new LoginException(-18, e.getMessage());
/*     */     } 
/* 237 */     return new LoginException(-7, e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean logout(IChannelProvider channelProv, String sLogoutUri, ISessionBase session) throws LoginException {
/*     */     class LoginDataListener
/*     */       extends ChannelListener
/*     */     {
/*     */       public boolean channelDataRead(IChannel channel, byte[] data) {
/*     */         try {
/* 252 */           if (HttpLoginProvider.ms_Logger.isEnabledFor2(LbsLevel.TRACE)) {
/* 253 */             HttpLoginProvider.ms_Logger.trace("Length:" + data.length + "\n" + TransportUtil.dumpToString(data));
/*     */           }
/* 255 */         } catch (Exception e) {
/*     */           
/* 257 */           HttpLoginProvider.this.m_OpResult = e;
/*     */         } 
/* 259 */         return true;
/*     */       }
/*     */     };
/*     */     
/*     */     try {
/* 264 */       RemoteRequest request = new RemoteRequest();
/* 265 */       request.SessionCode = session.getSessionCode();
/*     */       
/* 267 */       request.Parameters = new Object[1];
/* 268 */       request.Parameters[0] = session.getSessionData();
/*     */       
/* 270 */       request.TargetServlet = "Goodbye";
/* 271 */       sendChannelData(channelProv, sLogoutUri, getSendData(request), (IChannelListener)new LoginDataListener());
/*     */     }
/* 273 */     catch (Exception e) {
/*     */       
/* 275 */       throw new LoginException(-5, e.toString());
/*     */     } 
/* 277 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected byte[] getSendData(Object obj) throws Exception {
/* 282 */     byte[] userData = TransportUtil.serializeObject(obj);
/* 283 */     if (this.m_Compressor != null)
/* 284 */       userData = this.m_Compressor.compress(userData, 0, userData.length); 
/* 285 */     ICryptor cryptor = JLbsSymCryptoFactory.createCryptor(SECRETKEY);
/* 286 */     byte[] sendData = cryptor.encrypt(userData, 0, userData.length);
/* 287 */     return sendData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sendChannelData(IChannelProvider channelProv, String sLogoutUri, byte[] sendData, IChannelListener listener) throws Exception {
/* 293 */     IChannel channel = channelProv.createChannel();
/* 294 */     if (channel == null)
/* 295 */       throw new LoginException(-1, null); 
/* 296 */     channel.setSessionCode(ms_SessionCode);
/* 297 */     if (!channel.open(sLogoutUri, null))
/* 298 */       throw new LoginException(-2, sLogoutUri); 
/* 299 */     channel.setListener(listener);
/* 300 */     channel.sendData(sendData, 0, sendData.length, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\HttpLoginProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */