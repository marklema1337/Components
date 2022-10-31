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
/*    */ public class JLbsWarningMessage
/*    */   extends JlbsSingleMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsWarningMessage(String id, String module, JLbsMessResource message, JLbsMessResource title) {
/* 19 */     super(id, module, 3, title, message, 1, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsWarningMessage(String id, String module, JLbsMessResource message) {
/* 24 */     super(id, module, 3, message, 1, 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsWarningMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */