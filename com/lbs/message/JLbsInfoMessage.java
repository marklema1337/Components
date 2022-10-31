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
/*    */ public class JLbsInfoMessage
/*    */   extends JlbsSingleMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsInfoMessage(String id, String module, JLbsMessResource message, JLbsMessResource title) {
/* 19 */     super(id, module, 2, title, message, 1, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsInfoMessage(String id, String module, JLbsMessResource message) {
/* 24 */     super(id, module, 2, message, 1, 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsInfoMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */