/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.NetworkInterface;
/*    */ import java.net.UnknownHostException;
/*    */ import java.util.Enumeration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InetUtil
/*    */ {
/*    */   public static InetAddress findFirstNonLoopbackAddress() {
/* 24 */     InetAddress result = null;
/*    */     
/*    */     try {
/* 27 */       int lowest = Integer.MAX_VALUE;
/* 28 */       for (Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces(); nics.hasMoreElements(); ) {
/*    */         
/* 30 */         NetworkInterface ifc = nics.nextElement();
/* 31 */         if (ifc.isUp()) {
/*    */           
/* 33 */           if (ifc.getIndex() < lowest || result == null) {
/* 34 */             lowest = ifc.getIndex();
/* 35 */           } else if (result != null) {
/*    */             continue;
/*    */           } 
/* 38 */           for (Enumeration<InetAddress> addrs = ifc.getInetAddresses(); addrs.hasMoreElements(); ) {
/*    */             
/* 40 */             InetAddress address = addrs.nextElement();
/* 41 */             if (address instanceof java.net.Inet4Address && !address.isLoopbackAddress()) {
/* 42 */               result = address;
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/* 47 */     } catch (IOException iOException) {}
/*    */ 
/*    */ 
/*    */     
/* 51 */     if (result != null) {
/* 52 */       return result;
/*    */     }
/*    */     
/*    */     try {
/* 56 */       return InetAddress.getLocalHost();
/*    */     }
/* 58 */     catch (UnknownHostException unknownHostException) {
/*    */ 
/*    */ 
/*    */       
/* 62 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getHostname() {
/* 67 */     InetAddress address = findFirstNonLoopbackAddress();
/* 68 */     if (address != null)
/* 69 */       return address.getHostName(); 
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\InetUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */