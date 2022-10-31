/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Locale;
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
/*    */ public class LbsLocalizationUtil
/*    */ {
/*    */   public static void localizePrintDialog() {
/* 20 */     ResourceBundle bundle = ResourceBundle.getBundle("sun.print.resources.serviceui", Locale.getDefault(), 
/* 21 */         Thread.currentThread().getContextClassLoader());
/*    */     
/*    */     try {
/* 24 */       Class<?> cl = Class.forName("sun.print.ServiceDialog");
/* 25 */       if (cl != null) {
/*    */         
/* 27 */         Field fld = cl.getDeclaredField("messageRB");
/* 28 */         if (fld != null)
/*    */         {
/* 30 */           fld.setAccessible(true);
/* 31 */           fld.set(cl, bundle);
/*    */         }
/*    */       
/*    */       } 
/* 35 */     } catch (ClassNotFoundException classNotFoundException) {
/*    */ 
/*    */     
/* 38 */     } catch (NoSuchFieldException noSuchFieldException) {
/*    */ 
/*    */     
/* 41 */     } catch (SecurityException securityException) {
/*    */ 
/*    */     
/* 44 */     } catch (IllegalArgumentException illegalArgumentException) {
/*    */ 
/*    */     
/* 47 */     } catch (IllegalAccessException illegalAccessException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsLocalizationUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */