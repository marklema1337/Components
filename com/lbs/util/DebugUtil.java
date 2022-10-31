/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.swing.JOptionPane;
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
/*    */ public abstract class DebugUtil
/*    */   implements Serializable
/*    */ {
/*    */   public static boolean getDebug() {
/* 21 */     return (DebugUtilFieldHolder.getInstance()).debug;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setDebug(boolean value) {
/* 26 */     (DebugUtilFieldHolder.getInstance()).debug = value;
/* 27 */     JLbsConstants.DEBUG = value;
/* 28 */     System.out.println("Debug " + ((DebugUtilFieldHolder.getInstance()).debug ? 
/* 29 */         "on" : 
/* 30 */         "off"));
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean getError() {
/* 35 */     return (DebugUtilFieldHolder.getInstance()).error;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setError(boolean value) {
/* 40 */     (DebugUtilFieldHolder.getInstance()).error = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void debugLn(boolean value) {
/* 45 */     setDebug(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void debugLn(String s, boolean value) {
/* 50 */     setDebug(value);
/* 51 */     debugLn(s);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void debugLn(String s) {
/* 56 */     if ((DebugUtilFieldHolder.getInstance()).debug) {
/* 57 */       System.out.println(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void debugMsg(String s) {
/* 62 */     if ((DebugUtilFieldHolder.getInstance()).debug) {
/* 63 */       JOptionPane.showMessageDialog(null, s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void errorLn(boolean value) {
/* 68 */     setError(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void errorLn(String s, boolean value) {
/* 73 */     setError(value);
/* 74 */     errorLn(s);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void errorLn(String s) {
/* 79 */     if ((DebugUtilFieldHolder.getInstance()).error) {
/* 80 */       System.err.println(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void errorMsg(String s) {
/* 85 */     if ((DebugUtilFieldHolder.getInstance()).error) {
/* 86 */       JOptionPane.showMessageDialog(null, s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DebugUtilFieldHolder
/*    */   {
/*    */     private boolean debug = false;
/*    */     private boolean error = true;
/*    */     
/*    */     public static DebugUtilFieldHolder getInstance() {
/* 96 */       return LbsClassInstanceProvider.<DebugUtilFieldHolder>getInstanceByClass(DebugUtilFieldHolder.class);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\DebugUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */