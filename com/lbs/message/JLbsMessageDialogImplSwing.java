/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import com.lbs.util.JLbsStringList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JLbsMessageDialogImplSwing
/*    */   implements ILbsMessageDialogImpl
/*    */ {
/* 63 */   private static final JLbsMessageDialogImplSwing ms_Instance = new JLbsMessageDialogImplSwing();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsMessageDialogResult showLbsMessageDialog(JLbsMessage sourceMessage, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/* 72 */     return JLbsMessageDialog.showLbsMessageDialog((sourceMessage != null) ? 
/* 73 */         sourceMessage.getId() : 
/* 74 */         null, mainMessage, messages, title, culture, defButton, buttonDefs);
/*    */   }
/*    */ 
/*    */   
/*    */   public void showMultiLineMessages(JLbsMessage sourceMessage, String[] messages, String[] titles) {
/* 79 */     JLbsMessageDialog.showMultiLineMessagesImpl((sourceMessage != null) ? 
/* 80 */         sourceMessage.getId() : 
/* 81 */         null, messages, titles);
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsMessageDialogImplSwing getInstance() {
/* 86 */     return ms_Instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessageDialogImplSwing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */