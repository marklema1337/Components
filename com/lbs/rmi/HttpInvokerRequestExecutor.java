/*     */ package com.lbs.rmi;
/*     */ 
/*     */ import com.lbs.channel.ChannelStatisticsEvent;
/*     */ import com.lbs.channel.IChannelStatisticsListener;
/*     */ import com.lbs.http.cookie.JLbsCookieHive;
/*     */ import com.lbs.transport.RemoteMethodRequest;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.zip.GZIPInputStream;
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
/*     */ public abstract class HttpInvokerRequestExecutor
/*     */   extends AbstractHttpInvokerRequestExecutor
/*     */ {
/*  30 */   private static IChannelStatisticsListener ms_StatisticsListener = null;
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
/*     */   protected RemoteMethodResponse doExecuteRequest(ByteArrayOutputStream baos, RemoteMethodRequest invocation) throws Exception {
/*  49 */     long start = System.currentTimeMillis();
/*  50 */     HttpURLConnection con = openConnection();
/*  51 */     prepareConnection(con, baos.size());
/*  52 */     writeRequestBody(con, baos);
/*  53 */     InputStream responseBody = null;
/*     */     
/*     */     try {
/*  56 */       responseBody = readResponseBody(con);
/*  57 */       collectHeaderProperties(con);
/*     */     }
/*  59 */     catch (Exception e) {
/*     */       
/*  61 */       validateResponse(con);
/*     */     } 
/*  63 */     int receivedSize = (responseBody == null) ? 
/*  64 */       0 : 
/*  65 */       responseBody.available();
/*  66 */     RemoteMethodResponse response = readRemoteInvocationResult(responseBody, con);
/*  67 */     if (ms_StatisticsListener != null) {
/*     */       
/*  69 */       long end = System.currentTimeMillis();
/*  70 */       StringBuilder stringBuilder = new StringBuilder();
/*  71 */       stringBuilder.append(getServiceUrl()).append("?").append(invocation.TargetServlet).append(".")
/*  72 */         .append(invocation.MethodName);
/*  73 */       String uri = stringBuilder.toString();
/*  74 */       long timeElapsed = end - start;
/*  75 */       ms_StatisticsListener.sendData(new ChannelStatisticsEvent(this, baos.size(), 0, uri, timeElapsed));
/*  76 */       ms_StatisticsListener.receiveData(new ChannelStatisticsEvent(this, receivedSize, 0, uri, timeElapsed));
/*     */     } 
/*  78 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void collectHeaderProperties(HttpURLConnection con) {
/*  86 */     JLbsCookieHive.processHeaderForCookie(new HashMap<>(con.getHeaderFields()), con.getURL().toExternalForm());
/*     */   }
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
/*     */   protected HttpURLConnection openConnection() throws IOException {
/*  99 */     URLConnection con = (new URL(getServiceUrl())).openConnection();
/* 100 */     if (!(con instanceof HttpURLConnection))
/*     */     {
/* 102 */       throw new IOException("Service URL [" + getServiceUrl() + "] is not an HTTP URL");
/*     */     }
/* 104 */     return (HttpURLConnection)con;
/*     */   }
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
/*     */   protected void prepareConnection(HttpURLConnection con, int contentLength) throws IOException {
/* 120 */     con.setDoOutput(true);
/* 121 */     con.setDoInput(true);
/* 122 */     con.setRequestMethod("POST");
/* 123 */     con.setRequestProperty("Content-Type", getContentType());
/* 124 */     con.setRequestProperty("Content-Length", Integer.toString(contentLength));
/* 125 */     con.setUseCaches(true);
/* 126 */     con.setAllowUserInteraction(false);
/*     */     
/*     */     try {
/* 129 */       HttpURLConnection.setFollowRedirects(true);
/*     */     }
/* 131 */     catch (Exception exception) {}
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
/* 143 */     if (isAcceptGzipEncoding())
/*     */     {
/* 145 */       con.setRequestProperty("Accept-Encoding", "gzip");
/*     */     }
/*     */     
/* 148 */     String[] cookies = JLbsCookieHive.getCookies(con.getURL().toExternalForm());
/* 149 */     if (cookies != null) {
/* 150 */       for (int i = 0; i < cookies.length; i++)
/*     */       {
/* 152 */         con.addRequestProperty("Cookie", cookies[i]);
/*     */       }
/*     */     }
/*     */   }
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
/*     */   protected void writeRequestBody(HttpURLConnection con, ByteArrayOutputStream baos) throws IOException {
/* 172 */     baos.writeTo(con.getOutputStream());
/*     */   }
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
/*     */   protected void validateResponse(HttpURLConnection con) throws IOException {
/* 187 */     if (con.getResponseCode() >= 300)
/*     */     {
/* 189 */       throw new IOException("Did not receive successful HTTP response: status code = " + con.getResponseCode() + 
/* 190 */           ", status message = [" + con.getResponseMessage() + "]");
/*     */     }
/*     */   }
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
/*     */   protected InputStream readResponseBody(HttpURLConnection con) throws IOException {
/* 212 */     if (isGzipResponse(con))
/*     */     {
/*     */       
/* 215 */       return new GZIPInputStream(con.getInputStream());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 220 */     return con.getInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isGzipResponse(HttpURLConnection con) {
/* 232 */     String encodingHeader = con.getHeaderField("Content-Encoding");
/* 233 */     return (encodingHeader != null && encodingHeader.toLowerCase().indexOf("gzip") != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setStatisticsListener(IChannelStatisticsListener statisticsListener) {
/* 238 */     ms_StatisticsListener = statisticsListener;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\HttpInvokerRequestExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */