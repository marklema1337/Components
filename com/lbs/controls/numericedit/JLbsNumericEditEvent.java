/*    */ package com.lbs.controls.numericedit;
/*    */ 
/*    */ import java.util.EventObject;
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
/*    */ public class JLbsNumericEditEvent
/*    */   extends EventObject
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_szText;
/*    */   
/*    */   public JLbsNumericEditEvent(Object oSource, String szText) {
/* 26 */     super(oSource);
/* 27 */     this.m_szText = szText;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getInvalidText() {
/* 37 */     return this.m_szText;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsNumericEditEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */