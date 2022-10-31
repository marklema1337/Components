/*     */ package com.lbs.dialog;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*     */ import com.lbs.message.ILbsMessageDialogImpl;
/*     */ import com.lbs.message.JLbsMessageDialogRegistry;
/*     */ import com.lbs.message.JLbsMessageDialogResult;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsStringList;
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
/*     */ public class JLbsMessageUtil
/*     */ {
/*  25 */   public static int BUT_OK = 1;
/*  26 */   public static int BUT_CANCEL = 2;
/*  27 */   public static int BUT_YES = 4;
/*  28 */   public static int BUT_NO = 8;
/*  29 */   public static int BUT_SAVE = 16;
/*     */   
/*  31 */   public static String defButtonCaption = "Tamam";
/*  32 */   public static int defButtonTag = 1;
/*     */   
/*  34 */   public static int CONFIRM_OK_OPTION = 0;
/*  35 */   public static int CONFIRM_CANCEL_OPTION = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsYesNoConfirmDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, String title) {
/*  40 */     return showLbsMessageDialog(dialogImpl, mainMessage, (JLbsStringList)null, title, BUT_YES | BUT_NO, BUT_YES);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsErrorDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, String title) {
/*  45 */     return showLbsMessageDialog(dialogImpl, mainMessage, (JLbsStringList)null, title, BUT_OK, BUT_OK);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsInfoDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, String title) {
/*  50 */     return showLbsMessageDialog(dialogImpl, mainMessage, (JLbsStringList)null, title, BUT_OK, BUT_OK);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsMessageDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, JLbsStringList messages, String title, String btnCaptions, int defButton) {
/*  56 */     return showLbsMessageDialog(dialogImpl, mainMessage, messages, title, getCultureInfo(), defButton, btnCaptions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsMessageDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, JLbsStringList messages, String title, String btnCaptions, ILbsCultureInfo culture, int defButton) {
/*  62 */     return showLbsMessageDialog(dialogImpl, mainMessage, messages, title, culture, defButton, btnCaptions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsMessageDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, JLbsStringList messages, String title, int buttons, int defButton) {
/*  68 */     return showLbsMessageDialog(dialogImpl, mainMessage, messages, title, buttons, getCultureInfo(), defButton);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsMessageDialogResult showLbsMessageDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, JLbsStringList messages, String title, int buttons, ILbsCultureInfo culture, int defButton) {
/*  74 */     return showLbsMessageDialog(dialogImpl, mainMessage, messages, title, culture, defButton, Integer.valueOf(buttons));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static JLbsMessageDialogResult showLbsMessageDialog(ILbsMessageDialogImpl dialogImpl, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  80 */     if (dialogImpl == null)
/*  81 */       dialogImpl = JLbsMessageDialogRegistry.getDefaultDialog(); 
/*  82 */     JLbsMessageDialogResult result = dialogImpl.showLbsMessageDialog(null, mainMessage, messages, title, culture, 
/*  83 */         defButton, buttonDefs);
/*  84 */     JLbsMessageDialogResult res = new JLbsMessageDialogResult();
/*  85 */     res.button = result.button;
/*  86 */     res.message = result.message;
/*  87 */     return res;
/*     */   }
/*     */   
/*     */   protected static ILbsCultureInfo getCultureInfo() {
/*     */     JLbsDefaultCultureInfo jLbsDefaultCultureInfo;
/*  92 */     ILbsCultureInfo info = JLbsLocalizer.getCultureInfo();
/*  93 */     if (info == null)
/*  94 */       jLbsDefaultCultureInfo = new JLbsDefaultCultureInfo(); 
/*  95 */     return (ILbsCultureInfo)jLbsDefaultCultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int showLbsConfirmDialog(ILbsMessageDialogImpl dialogImpl, String message, String title) {
/* 100 */     JLbsMessageDialogResult result = showLbsMessageDialog(dialogImpl, message, (JLbsStringList)null, title, BUT_OK + BUT_CANCEL, BUT_CANCEL);
/*     */     
/* 102 */     if (result.button == BUT_OK) {
/* 103 */       return CONFIRM_OK_OPTION;
/*     */     }
/* 105 */     return CONFIRM_CANCEL_OPTION;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void showMultiLineMessages(ILbsMessageDialogImpl dialogImpl, String[] messages, String[] titles) {
/* 110 */     if (dialogImpl == null)
/* 111 */       dialogImpl = JLbsMessageDialogRegistry.getDefaultDialog(); 
/* 112 */     dialogImpl.showMultiLineMessages(null, messages, titles);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dialog\JLbsMessageUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */