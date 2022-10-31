/*     */ package org.apache.logging.log4j.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
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
/*     */ public final class NetUtils
/*     */ {
/*  39 */   private static final Logger LOGGER = (Logger)StatusLogger.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String UNKNOWN_LOCALHOST = "UNKNOWN_LOCALHOST";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getLocalHostname() {
/*     */     try {
/*  54 */       InetAddress addr = InetAddress.getLocalHost();
/*  55 */       return (addr == null) ? "UNKNOWN_LOCALHOST" : addr.getHostName();
/*  56 */     } catch (UnknownHostException uhe) {
/*     */       try {
/*  58 */         Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
/*  59 */         if (interfaces != null) {
/*  60 */           while (interfaces.hasMoreElements()) {
/*  61 */             NetworkInterface nic = interfaces.nextElement();
/*  62 */             Enumeration<InetAddress> addresses = nic.getInetAddresses();
/*  63 */             while (addresses.hasMoreElements()) {
/*  64 */               InetAddress address = addresses.nextElement();
/*  65 */               if (!address.isLoopbackAddress()) {
/*  66 */                 String hostname = address.getHostName();
/*  67 */                 if (hostname != null) {
/*  68 */                   return hostname;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*  74 */       } catch (SocketException socketException) {}
/*     */ 
/*     */       
/*  77 */       LOGGER.error("Could not determine local host name", uhe);
/*  78 */       return "UNKNOWN_LOCALHOST";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getMacAddress() {
/*  89 */     byte[] mac = null;
/*     */     try {
/*  91 */       InetAddress localHost = InetAddress.getLocalHost();
/*     */       try {
/*  93 */         NetworkInterface localInterface = NetworkInterface.getByInetAddress(localHost);
/*  94 */         if (isUpAndNotLoopback(localInterface)) {
/*  95 */           mac = localInterface.getHardwareAddress();
/*     */         }
/*  97 */         if (mac == null) {
/*  98 */           Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
/*  99 */           if (networkInterfaces != null) {
/* 100 */             while (networkInterfaces.hasMoreElements() && mac == null) {
/* 101 */               NetworkInterface nic = networkInterfaces.nextElement();
/* 102 */               if (isUpAndNotLoopback(nic)) {
/* 103 */                 mac = nic.getHardwareAddress();
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/* 108 */       } catch (SocketException e) {
/* 109 */         LOGGER.catching(e);
/*     */       } 
/* 111 */       if (ArrayUtils.isEmpty(mac) && localHost != null) {
/*     */         
/* 113 */         byte[] address = localHost.getAddress();
/*     */         
/* 115 */         mac = Arrays.copyOf(address, 6);
/*     */       } 
/* 117 */     } catch (UnknownHostException unknownHostException) {}
/*     */ 
/*     */     
/* 120 */     return mac;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMacAddressString() {
/* 128 */     byte[] macAddr = getMacAddress();
/* 129 */     if (!ArrayUtils.isEmpty(macAddr)) {
/* 130 */       StringBuilder sb = new StringBuilder(String.format("%02x", new Object[] { Byte.valueOf(macAddr[0]) }));
/* 131 */       for (int i = 1; i < macAddr.length; i++) {
/* 132 */         sb.append(":").append(String.format("%02x", new Object[] { Byte.valueOf(macAddr[i]) }));
/*     */       } 
/* 134 */       return sb.toString();
/*     */     } 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean isUpAndNotLoopback(NetworkInterface ni) throws SocketException {
/* 141 */     return (ni != null && !ni.isLoopback() && ni.isUp());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static URI toURI(String path) {
/*     */     try {
/* 153 */       return new URI(path);
/* 154 */     } catch (URISyntaxException e) {
/*     */ 
/*     */       
/*     */       try {
/* 158 */         URL url = new URL(path);
/* 159 */         return new URI(url.getProtocol(), url.getHost(), url.getPath(), null);
/* 160 */       } catch (MalformedURLException|URISyntaxException nestedEx) {
/* 161 */         return (new File(path)).toURI();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\cor\\util\NetUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */