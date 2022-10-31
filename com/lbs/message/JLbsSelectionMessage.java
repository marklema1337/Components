/*    */ package com.lbs.message;
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
/*    */ public class JLbsSelectionMessage
/*    */   extends JlbsSingleMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsSelectionMessage(String id, String module, JLbsMessResource message, int buttons, int defaultButton) {
/* 19 */     super(id, module, 4, message, buttons, defaultButton);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsSelectionMessage(String id, String module, JLbsMessResource message, JLbsMessResource[] buttonCaptions, int defaultButton) {
/* 24 */     super(id, module, 4, message, buttonCaptions, defaultButton);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsSelectionMessage(String id, String module, JLbsMessResource title, JLbsMessResource message, int buttons, int defaultButton) {
/* 29 */     super(id, module, 4, title, message, buttons, defaultButton);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsSelectionMessage(String id, String module, JLbsMessResource title, JLbsMessResource message, JLbsMessResource[] buttonCaptions, int defaultButton) {
/* 35 */     super(id, module, 4, title, message, buttonCaptions, defaultButton);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsSelectionMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */