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
/*    */ public class JlbsErrorMessage
/*    */   extends JlbsSingleMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JlbsErrorMessage(String id, String module, JLbsMessResource message, JLbsMessResource title) {
/* 19 */     super(id, module, 1, title, message, 1, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public JlbsErrorMessage(String id, String module, JLbsMessResource message) {
/* 24 */     super(id, module, 1, message, 1, 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JlbsErrorMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */