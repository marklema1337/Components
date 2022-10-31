/*     */ package com.lbs.http;
/*     */ 
/*     */ import com.lbs.channel.ChannelStatisticsEvent;
/*     */ import com.lbs.channel.IChannel;
/*     */ import com.lbs.channel.IChannelDataReader;
/*     */ import com.lbs.channel.IChannelListener;
/*     */ import com.lbs.channel.IChannelStatisticsListener;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.console.LbsLevel;
/*     */ import com.lbs.http.cookie.JLbsCookieHive;
/*     */ import com.lbs.http.cookie.JLbsNVPair;
/*     */ import com.lbs.transport.ChannelSendException;
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.TrustManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpChannel
/*     */   implements IChannel
/*     */ {
/*  43 */   private static final transient LbsConsole ms_Logger = LbsConsole.getLogger("Transport.HttpChannel");
/*     */   protected HttpURLConnection m_Connection;
/*     */   protected IChannelListener m_Listener;
/*     */   protected IChannelDataReader m_ChannelReadListener;
/*  47 */   protected ArrayList m_SendParams = new ArrayList();
/*  48 */   protected HashMap m_RetParams = new HashMap<>();
/*     */   protected String m_URIString;
/*     */   protected boolean m_CookiesEnabled = true;
/*     */   protected boolean m_CollectHeader = true;
/*  52 */   private static IChannelStatisticsListener ms_StatisticsListener = null;
/*     */   
/*     */   private static boolean m_SSLInitialized = false;
/*     */   private boolean m_SpringBoot = false;
/*     */   private String m_SessionCode;
/*     */   
/*     */   public HttpChannel() {
/*  59 */     this.m_CollectHeader = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpChannel(boolean collectHeader, boolean useSSL) {
/*  64 */     this();
/*  65 */     this.m_CollectHeader = collectHeader;
/*  66 */     if (useSSL && !m_SSLInitialized) {
/*     */       
/*     */       try {
/*     */         
/*  70 */         SSLContext ctx = SSLContext.getInstance("TLS");
/*  71 */         LbsX509TrustManager tm = new LbsX509TrustManager();
/*  72 */         ctx.init(null, new TrustManager[] { tm }, null);
/*  73 */         HostnameVerifier hv = new HostnameVerifier()
/*     */           {
/*     */             public boolean verify(String urlHostName, SSLSession session)
/*     */             {
/*  77 */               return true;
/*     */             }
/*     */           };
/*  80 */         HttpsURLConnection.setDefaultHostnameVerifier(hv);
/*  81 */         HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
/*  82 */         m_SSLInitialized = true;
/*     */       }
/*  84 */       catch (Exception e) {
/*     */         
/*  86 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean open(String sUri, Object param) {
/*     */     try {
/*  98 */       close(false);
/*  99 */       this.m_URIString = sUri;
/* 100 */       URL url = new URL(sUri);
/* 101 */       URLConnection conn = url.openConnection();
/* 102 */       if (conn instanceof HttpURLConnection) {
/*     */         
/* 104 */         this.m_Connection = (HttpURLConnection)conn;
/* 105 */         this.m_Connection.setRequestMethod("POST");
/* 106 */         this.m_Connection.setUseCaches(true);
/* 107 */         this.m_Connection.setAllowUserInteraction(false);
/*     */         
/*     */         try {
/* 110 */           HttpURLConnection.setFollowRedirects(true);
/*     */         }
/* 112 */         catch (Exception exception) {}
/*     */       } 
/*     */ 
/*     */       
/* 116 */       return true;
/*     */     }
/* 118 */     catch (Exception ex) {
/*     */       
/* 120 */       ms_Logger.error("HttpChannel.open", ex);
/*     */       
/* 122 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean close(boolean bForce) {
/* 131 */     if (bForce)
/* 132 */       setListener(null); 
/* 133 */     if (this.m_Connection != null) {
/*     */ 
/*     */       
/*     */       try {
/* 137 */         InputStream stream = this.m_Connection.getInputStream();
/* 138 */         if (stream != null) {
/* 139 */           stream.close();
/*     */         }
/* 141 */       } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */       
/* 145 */       this.m_Connection = null;
/* 146 */       if (this.m_Listener != null)
/* 147 */         this.m_Listener.channelClosed(this); 
/*     */     } 
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setUseCache(boolean bUseCache) {
/* 157 */     if (this.m_Connection != null) {
/*     */       
/* 159 */       this.m_Connection.setUseCaches(bUseCache);
/* 160 */       return true;
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setIfModifiedSince(Date date) {
/* 170 */     if (this.m_Connection != null && date != null) {
/*     */       
/* 172 */       this.m_Connection.setIfModifiedSince(date.getTime());
/* 173 */       return true;
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int sendData(byte[] data, int index, int length, boolean bReadResponse) throws ChannelSendException {
/*     */     try {
/* 186 */       if (this.m_Connection != null) {
/*     */         
/* 188 */         long start = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */         
/* 192 */         if (data != null && length > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 199 */           this.m_Connection.setDoOutput(true);
/* 200 */           this.m_Connection.setDoInput(true);
/*     */           
/* 202 */           this.m_Connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 207 */           this.m_Connection.setRequestProperty("Content-length", Integer.toString(length));
/* 208 */           int headerLength = setHeaderProperties();
/* 209 */           OutputStream outStream = this.m_Connection.getOutputStream();
/* 210 */           outStream.write(data, index, length);
/* 211 */           outStream.flush();
/* 212 */           outStream.close();
/* 213 */           sendDataNotify(length, headerLength, System.currentTimeMillis() - start);
/*     */         }
/*     */         else {
/*     */           
/* 217 */           this.m_Connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*     */           
/* 219 */           this.m_Connection.setDoInput(true);
/* 220 */           setHeaderProperties();
/* 221 */           this.m_Connection.connect();
/*     */         } 
/* 223 */         if (!bReadResponse)
/* 224 */           return 0; 
/* 225 */         InputStream inStream = acquireInputStream(0);
/* 226 */         if (this.m_CollectHeader)
/* 227 */           collectHeaderProperties(); 
/* 228 */         int iContSize = this.m_Connection.getContentLength();
/* 229 */         if (this.m_ChannelReadListener != null) {
/* 230 */           this.m_ChannelReadListener.readChannelData(this.m_Listener, inStream, iContSize);
/*     */         } else {
/*     */           
/* 233 */           byte[] readData = TransportUtil.readStream(inStream, iContSize);
/* 234 */           receiveDataNotify(readData, System.currentTimeMillis() - start);
/* 235 */           if (this.m_Listener != null) {
/* 236 */             this.m_Listener.channelDataRead(this, readData);
/*     */           }
/*     */         } 
/*     */       } 
/* 240 */     } catch (Exception e) {
/*     */       
/* 242 */       if (JLbsConstants.DEBUG)
/* 243 */         e.printStackTrace(); 
/*     */     } 
/* 245 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private InputStream acquireInputStream(int retryCount) throws Exception {
/*     */     try {
/* 252 */       if (this.m_Connection != null) {
/* 253 */         return this.m_Connection.getInputStream();
/*     */       }
/* 255 */     } catch (Exception e) {
/*     */       
/* 257 */       if (retryCount < 3) {
/*     */         
/* 259 */         InputStream result = acquireInputStream(++retryCount);
/* 260 */         if (result != null) {
/* 261 */           return result;
/*     */         }
/*     */       } else {
/* 264 */         throw e;
/*     */       } 
/* 266 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void receiveDataNotify(byte[] readData, long timeElapsed) {
/* 274 */     if (ms_StatisticsListener != null) {
/*     */       
/*     */       try {
/*     */         
/* 278 */         Map<String, List<String>> fields = this.m_Connection.getHeaderFields();
/* 279 */         int responseLength = (readData != null) ? 
/* 280 */           readData.length : 
/* 281 */           0;
/* 282 */         int headerSize = fields.toString().length();
/* 283 */         String uri = this.m_Connection.getURL().toString();
/* 284 */         ms_StatisticsListener.receiveData(new ChannelStatisticsEvent(this, responseLength, headerSize, uri, timeElapsed));
/*     */       }
/* 286 */       catch (Exception e) {
/*     */         
/* 288 */         ms_Logger.error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendDataNotify(int length, int headerLength, long timeElapsed) {
/* 299 */     if (ms_StatisticsListener != null) {
/*     */       
/*     */       try {
/*     */         
/* 303 */         String uri = this.m_Connection.getURL().toString();
/* 304 */         ms_StatisticsListener.sendData(new ChannelStatisticsEvent(this, length, headerLength, uri, timeElapsed));
/*     */       }
/* 306 */       catch (Exception e) {
/*     */         
/* 308 */         ms_Logger.error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setStatisticsListener(IChannelStatisticsListener statisticsListener) {
/* 319 */     ms_StatisticsListener = statisticsListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IChannelStatisticsListener getStatisticsListener() {
/* 327 */     return ms_StatisticsListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getData() throws FileNotFoundException {
/*     */     try {
/* 337 */       if (this.m_Connection != null) {
/*     */         
/* 339 */         setHeaderProperties();
/* 340 */         this.m_Connection.setDoOutput(false);
/* 341 */         this.m_Connection.setDoInput(true);
/* 342 */         collectHeaderProperties();
/* 343 */         int iContSize = this.m_Connection.getContentLength();
/* 344 */         InputStream inStream = this.m_Connection.getInputStream();
/* 345 */         byte[] readData = TransportUtil.readStream(inStream, iContSize);
/* 346 */         if (this.m_Listener != null) {
/* 347 */           this.m_Listener.channelDataRead(this, readData);
/*     */         } else {
/* 349 */           throw new FileNotFoundException();
/*     */         } 
/*     */       } 
/* 352 */     } catch (Exception e) {
/*     */       
/* 354 */       ms_Logger.error(e.getMessage(), e);
/*     */     } 
/*     */     
/* 357 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChannelCode() {
/* 365 */     return "http";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListener(IChannelListener listener) {
/* 373 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPostParams() {
/* 381 */     synchronized (this.m_SendParams) {
/*     */       
/* 383 */       this.m_SendParams.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPostParam(String name, String param) {
/* 392 */     synchronized (this.m_SendParams) {
/*     */       
/* 394 */       this.m_SendParams.add(new JLbsNVPair(name, param));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReturnParam(String name) {
/*     */     try {
/*     */       Object o;
/* 406 */       synchronized (this.m_RetParams) {
/*     */         
/* 408 */         o = this.m_RetParams.get(name);
/*     */       } 
/* 410 */       if (o != null)
/*     */       {
/* 412 */         StringBuilder buff = new StringBuilder(o.toString());
/* 413 */         if (buff.charAt(0) == '[')
/* 414 */           buff = buff.delete(0, 1); 
/* 415 */         int iLen = buff.length();
/* 416 */         if (buff.charAt(iLen - 1) == ']')
/* 417 */           buff = buff.delete(iLen - 1, iLen); 
/* 418 */         return buff.toString();
/*     */       }
/*     */     
/* 421 */     } catch (Exception o) {}
/*     */ 
/*     */     
/* 424 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private int setHeaderProperties() {
/* 429 */     int result = 0;
/* 430 */     clearReturnHeaderProperties();
/* 431 */     if (this.m_Listener != null)
/* 432 */       this.m_Listener.channelBeforeDataSend(this); 
/* 433 */     if (this.m_Connection != null) {
/*     */       
/* 435 */       synchronized (this.m_SendParams) {
/*     */ 
/*     */         
/* 438 */         for (int i = 0; i < this.m_SendParams.size(); i++) {
/*     */           
/* 440 */           JLbsNVPair tuple = this.m_SendParams.get(i);
/* 441 */           this.m_Connection.setRequestProperty(tuple.getName(), tuple.getValue());
/* 442 */           if (ms_StatisticsListener != null)
/*     */           {
/* 444 */             result += tuple.getName().length() + tuple.getValue().length() + 3;
/*     */           }
/*     */         } 
/* 447 */         this.m_SendParams.clear();
/*     */       } 
/* 449 */       if (this.m_CookiesEnabled) {
/*     */         
/* 451 */         String[] cookies = JLbsCookieHive.getCookies(this.m_URIString);
/* 452 */         if (!JLbsStringUtil.isEmpty(this.m_SessionCode)) {
/* 453 */           JLbsCookieHive.addCookie("JSESSIONID=" + this.m_SessionCode, this.m_URIString, true);
/*     */         }
/* 455 */         cookies = JLbsCookieHive.getCookies(this.m_URIString);
/* 456 */         if (cookies != null)
/*     */         {
/* 458 */           for (int i = 0; i < cookies.length; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 463 */             if (ms_Logger.isEnabledFor2(LbsLevel.TRACE))
/* 464 */               ms_Logger.trace("Sending cookie to server: " + cookies[i]); 
/* 465 */             this.m_Connection.addRequestProperty("Cookie", cookies[i]);
/* 466 */             if (ms_StatisticsListener != null)
/*     */             {
/* 468 */               result += cookies[i].length() + 9;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 474 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void collectHeaderProperties() {
/* 479 */     if (this.m_Connection != null) {
/*     */       
/* 481 */       synchronized (this.m_RetParams) {
/*     */         
/* 483 */         this.m_RetParams = new HashMap<>(this.m_Connection.getHeaderFields());
/*     */       } 
/* 485 */       if (this.m_CookiesEnabled) {
/* 486 */         JLbsCookieHive.processHeaderForCookie(this.m_RetParams, this.m_URIString);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clearReturnHeaderProperties() {
/* 492 */     synchronized (this.m_RetParams) {
/*     */       
/* 494 */       this.m_RetParams.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IChannelDataReader getChannelReadListener() {
/* 500 */     return this.m_ChannelReadListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChannelReadListener(IChannelDataReader reader) {
/* 505 */     this.m_ChannelReadListener = reader;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpringBoot(boolean springBoot) {
/* 511 */     this.m_SpringBoot = springBoot;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSessionCode(String sessionCode) {
/* 516 */     this.m_SessionCode = sessionCode;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\HttpChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */