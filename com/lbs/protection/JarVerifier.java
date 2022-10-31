/*     */ package com.lbs.protection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.Manifest;
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
/*     */ public class JarVerifier
/*     */ {
/*     */   public static void verify(JarFile jf, X509Certificate[] trustedCaCerts) throws IOException, CertificateException {
/*  45 */     Vector<JarEntry> entriesVec = new Vector<>();
/*     */ 
/*     */     
/*  48 */     Manifest man = jf.getManifest();
/*  49 */     if (man == null) {
/*  50 */       throw new SecurityException("The JAR is not signed");
/*     */     }
/*     */     
/*  53 */     byte[] buffer = new byte[8192];
/*  54 */     Enumeration<JarEntry> entries = jf.entries();
/*     */     
/*  56 */     while (entries.hasMoreElements()) {
/*     */       
/*  58 */       JarEntry je = entries.nextElement();
/*  59 */       entriesVec.addElement(je);
/*  60 */       InputStream is = jf.getInputStream(je); int n; do {
/*     */       
/*  62 */       } while ((n = is.read(buffer, 0, buffer.length)) != -1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  67 */       is.close();
/*     */     } 
/*  69 */     jf.close();
/*     */ 
/*     */     
/*  72 */     Enumeration<JarEntry> e = entriesVec.elements();
/*  73 */     while (e.hasMoreElements()) {
/*     */       
/*  75 */       JarEntry je = e.nextElement();
/*     */       
/*  77 */       if (je.isDirectory()) {
/*     */         continue;
/*     */       }
/*     */       
/*  81 */       Certificate[] certs = je.getCertificates();
/*  82 */       if (certs == null || certs.length == 0) {
/*     */         
/*  84 */         if (!je.getName().startsWith("META-INF")) {
/*  85 */           throw new SecurityException("The JCE framework has unsigned class files.");
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  96 */       X509Certificate[] chainRoots = getChainRoots(certs);
/*  97 */       boolean signedAsExpected = false;
/*     */       
/*  99 */       for (int i = 0; i < chainRoots.length; i++) {
/*     */         
/* 101 */         if (isTrusted(chainRoots[i], trustedCaCerts)) {
/*     */           
/* 103 */           signedAsExpected = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 108 */       if (!signedAsExpected)
/*     */       {
/* 110 */         throw new SecurityException("The JAR is not signed by a trusted signer");
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
/*     */   public static boolean isTrusted(X509Certificate cert, X509Certificate[] trustedCaCerts) {
/*     */     int i;
/* 123 */     for (i = 0; i < trustedCaCerts.length; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       if (cert.getSubjectDN().equals(trustedCaCerts[i].getSubjectDN()))
/*     */       {
/* 130 */         if (cert.equals(trustedCaCerts[i]))
/*     */         {
/* 132 */           return true;
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     for (i = 0; i < trustedCaCerts.length; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       if (cert.getIssuerDN().equals(trustedCaCerts[i].getSubjectDN())) {
/*     */         
/*     */         try {
/*     */           
/* 150 */           cert.verify(trustedCaCerts[i].getPublicKey());
/* 151 */           return true;
/*     */         }
/* 153 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static X509Certificate[] getChainRoots(Certificate[] certs) {
/* 165 */     Vector<X509Certificate> result = new Vector<>(3);
/*     */     
/* 167 */     for (int i = 0; i < certs.length - 1; i++)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 172 */       result.addElement((X509Certificate)certs[i]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 177 */     result.addElement((X509Certificate)certs[certs.length - 1]);
/* 178 */     X509Certificate[] ret = new X509Certificate[result.size()];
/* 179 */     result.copyInto((Object[])ret);
/*     */     
/* 181 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\protection\JarVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */