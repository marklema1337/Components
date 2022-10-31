/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*    */ import com.lbs.message.ILbsMessageDialogImpl;
/*    */ import com.lbs.message.ILbsMessageDialogImplOwner;
/*    */ import com.lbs.message.ILbsMessageListener;
/*    */ import com.lbs.message.JLbsMessageDialogResult;
/*    */ import com.lbs.message.JLbsMessageUtil;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
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
/*    */ public class JLbsContextMessageUtil
/*    */ {
/*    */   public static JLbsMessageDialogResult showMessage(IApplicationContext context, String messageId, String defaultMessage, ILbsMessageListener listener, Object[] messageFormatArguments) {
/* 26 */     ILbsMessageDialogImpl dialogImpl = null;
/* 27 */     if (context instanceof ILbsMessageDialogImplOwner) {
/* 28 */       dialogImpl = ((ILbsMessageDialogImplOwner)context).getMessageDialogImpl();
/*    */     }
/* 30 */     ILbsCultureInfo culture = getCulture(context);
/*    */     
/* 32 */     return JLbsMessageUtil.showMessageDialog(dialogImpl, context.getLocalizationService(), messageId, defaultMessage, messageFormatArguments, culture, listener);
/*    */   }
/*    */   
/*    */   public static ILbsCultureInfo getCulture(IApplicationContext context) {
/*    */     JLbsDefaultCultureInfo jLbsDefaultCultureInfo;
/* 37 */     ILbsCultureInfo culture = null;
/* 38 */     Object var = context.getVariable("CLI-CULTUREINFO");
/* 39 */     if (var instanceof ILbsCultureInfo)
/* 40 */       culture = (ILbsCultureInfo)var; 
/* 41 */     if (culture == null)
/* 42 */       jLbsDefaultCultureInfo = new JLbsDefaultCultureInfo(); 
/* 43 */     return (ILbsCultureInfo)jLbsDefaultCultureInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsContextMessageUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */