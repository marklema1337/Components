/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Calendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLUtil
/*    */ {
/* 16 */   protected static char[] SPECIAL_CHARS = new char[] { '&', '<', '>', '"', '\'' };
/* 17 */   protected static String[] SPECIAL_SUBST = new String[] { "&amp;", "&lt;", "&gt;", "&quot;", "&apos;" };
/*    */ 
/*    */   
/*    */   protected static String convertChar(char c) {
/* 21 */     for (int i = 0; i < SPECIAL_CHARS.length; i++) {
/*    */       
/* 23 */       if (c == SPECIAL_CHARS[i]) {
/* 24 */         return SPECIAL_SUBST[i];
/*    */       }
/*    */     } 
/* 27 */     return c + "";
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getAttribute(String value) {
/* 32 */     StringBuilder sb = new StringBuilder();
/*    */     
/* 34 */     if (value != null) {
/* 35 */       for (int i = 0; i < value.length(); i++) {
/*    */         
/* 37 */         char c = value.charAt(i);
/*    */         
/* 39 */         sb.append(convertChar(c));
/*    */       } 
/*    */     }
/* 42 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getAttribute(String name, String value) {
/* 47 */     if (value == null) {
/* 48 */       return name + "=\"\" ";
/*    */     }
/* 50 */     value = getAttribute(value);
/* 51 */     return name + "=\"" + value + "\" ";
/*    */   }
/*    */ 
/*    */   
/*    */   public static String readAttribute(String value) {
/* 56 */     String res = value;
/*    */     
/* 58 */     for (int i = 0; i < SPECIAL_SUBST.length; i++) {
/* 59 */       res = res.replaceAll(SPECIAL_SUBST[i], "" + SPECIAL_CHARS[i]);
/*    */     }
/* 61 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void printAttribute(PrintWriter writer, String name, String value) {
/* 66 */     if (value == null) {
/*    */       
/* 68 */       writer.print(name + "=\"\" ");
/*    */       
/*    */       return;
/*    */     } 
/* 72 */     value = getAttribute(value);
/* 73 */     writer.print(name + "=\"" + value + "\" ");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void printAttribute(PrintWriter writer, String name, Object value) {
/* 78 */     if (value instanceof Calendar) {
/* 79 */       value = StringUtil.toCanonicalString((Calendar)value);
/*    */     }
/* 81 */     if (value == null) {
/* 82 */       value = "";
/*    */     }
/* 84 */     writer.print(name + "=\"" + value + "\" ");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void printAttribute(PrintWriter writer, String name, boolean value) {
/* 89 */     writer.print(name + "=\"" + value + "\" ");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void printAttribute(PrintWriter writer, String name, int value) {
/* 94 */     writer.print(name + "=\"" + value + "\" ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XMLUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */