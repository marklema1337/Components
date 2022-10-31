/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.resource.JLbsLocalizer;
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
/*    */ public class JLbsMessageUtil
/*    */   implements ILbsMessageConstants
/*    */ {
/*    */   public static JLbsMessageDialogResult showMessageDialog(ILbsMessageDialogImpl dialogImpl, ILocalizationServices localizationService, JLbsMessage message, Object[] messageFormatArguments, ILbsCultureInfo culture, ILbsMessageListener listener) {
/* 23 */     if (dialogImpl == null)
/* 24 */       dialogImpl = JLbsMessageDialogRegistry.getDefaultDialog(); 
/* 25 */     if (culture == null)
/* 26 */       culture = getCultureInfo(); 
/* 27 */     return message.show(dialogImpl, localizationService, culture, listener, messageFormatArguments);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static JLbsMessageDialogResult showMessageDialog(ILbsMessageDialogImpl dialogImpl, ILocalizationServices localizationService, String messageId, String defaultMessage, Object[] messageFormatArguments, ILbsCultureInfo culture, ILbsMessageListener listener) {
/* 33 */     JLbsMessage message = localizationService.getMessage(messageId);
/* 34 */     if (message == null)
/* 35 */       throw new RuntimeException("Message with id '" + messageId + "' could not be found!"); 
/* 36 */     message.setDefaultMessage(defaultMessage);
/* 37 */     return showMessageDialog(dialogImpl, localizationService, message, messageFormatArguments, culture, listener);
/*    */   }
/*    */   
/*    */   protected static ILbsCultureInfo getCultureInfo() {
/*    */     JLbsDefaultCultureInfo jLbsDefaultCultureInfo;
/* 42 */     ILbsCultureInfo info = JLbsLocalizer.getCultureInfo();
/* 43 */     if (info == null)
/* 44 */       jLbsDefaultCultureInfo = new JLbsDefaultCultureInfo(); 
/* 45 */     return (ILbsCultureInfo)jLbsDefaultCultureInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessageUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */