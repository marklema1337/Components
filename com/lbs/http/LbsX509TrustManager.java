/*    */ package com.lbs.http;
/*    */ 
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsX509TrustManager
/*    */   implements X509TrustManager
/*    */ {
/*    */   public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
/*    */   
/*    */   public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
/*    */   
/*    */   public X509Certificate[] getAcceptedIssuers() {
/* 17 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\LbsX509TrustManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */