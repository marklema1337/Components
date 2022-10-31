/*     */ package com.lbs.message;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
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
/*     */ class JLbsMessageDialogImplEmulator
/*     */   implements ILbsMessageDialogImpl
/*     */ {
/*     */   private ILbsMessageExecutor m_Executor;
/*     */   
/*     */   public JLbsMessageDialogImplEmulator(ILbsMessageExecutor executor) {
/*  97 */     this.m_Executor = executor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessageDialogResult showLbsMessageDialog(JLbsMessage sourceMessage, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/* 103 */     if (this.m_Executor != null)
/* 104 */       return this.m_Executor.onMessageShow(sourceMessage, mainMessage, messages, title, culture, defButton, buttonDefs); 
/* 105 */     JLbsMessageDialogResult result = new JLbsMessageDialogResult();
/* 106 */     result.button = defButton;
/* 107 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void showMultiLineMessages(JLbsMessage sourceMessage, String[] messages, String[] titles) {
/* 112 */     if (this.m_Executor != null)
/* 113 */       this.m_Executor.onMultiMessageShow(sourceMessage); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessageDialogImplEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */