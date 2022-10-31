/*    */ package com.lbs.juel.misc;
/*    */ 
/*    */ import java.text.MessageFormat;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LocalMessages
/*    */ {
/*    */   private static final String BUNDLE_NAME = "com.lbs.juel.misc.LocalStrings";
/* 25 */   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.lbs.juel.misc.LocalStrings");
/*    */ 
/*    */   
/*    */   public static String get(String key, Object... args) {
/* 29 */     String template = null;
/*    */     
/*    */     try {
/* 32 */       template = RESOURCE_BUNDLE.getString(key);
/*    */     }
/* 34 */     catch (MissingResourceException e) {
/*    */       
/* 36 */       StringBuilder b = new StringBuilder();
/*    */       
/*    */       try {
/* 39 */         b.append(RESOURCE_BUNDLE.getString("message.unknown"));
/* 40 */         b.append(": ");
/*    */       }
/* 42 */       catch (MissingResourceException missingResourceException) {}
/*    */ 
/*    */       
/* 45 */       b.append(key);
/* 46 */       if (args != null && args.length > 0) {
/*    */         
/* 48 */         b.append("(");
/* 49 */         b.append(args[0]);
/* 50 */         for (int i = 1; i < args.length; i++) {
/*    */           
/* 52 */           b.append(", ");
/* 53 */           b.append(args[i]);
/*    */         } 
/* 55 */         b.append(")");
/*    */       } 
/* 57 */       return b.toString();
/*    */     } 
/* 59 */     return MessageFormat.format(template, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\misc\LocalMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */