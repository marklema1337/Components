/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.hexidec.ekit.ResourceProvider;
/*    */ import com.lbs.util.JLbsConstants;
/*    */ import java.util.Locale;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EkitResourceProvider
/*    */   implements ResourceProvider
/*    */ {
/*    */   public ResourceBundle getResourceBundle(String name) {
/* 17 */     if (name.endsWith("LanguageResources"))
/* 18 */       return ResourceBundle.getBundle("com.lbs.controls.LanguageResources"); 
/* 19 */     return ResourceBundle.getBundle(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceBundle getResourceBundle(Locale locale, String name) {
/* 25 */     if (name.endsWith("LanguageResources"))
/* 26 */       return ResourceBundle.getBundle("com.lbs.controls.LanguageResources", locale, 
/* 27 */           Thread.currentThread().getContextClassLoader()); 
/* 28 */     return ResourceBundle.getBundle(name, locale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageIcon getIcon(String iconName) {
/* 34 */     if (JLbsConstants.DESKTOP_MODE) {
/* 35 */       return JLbsControlHelper.getImageIcon(JLbsControlHelper.class, String.valueOf(iconName) + "D.png");
/*    */     }
/* 37 */     return JLbsControlHelper.getImageIcon(JLbsControlHelper.class, String.valueOf(iconName) + "HK.png");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\EkitResourceProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */