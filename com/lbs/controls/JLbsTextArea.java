/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTextArea;
/*    */ import javax.swing.JTextArea;
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
/*    */ public class JLbsTextArea
/*    */   extends JTextArea
/*    */   implements ILbsTextArea
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public String getResourceIdentifier() {
/* 22 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUniqueIdentifier() {
/* 27 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHaveLayoutManager() {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 37 */     return getParent();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResetTime(boolean resetTime) {}
/*    */ 
/*    */   
/*    */   public boolean isResetTime() {
/* 46 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsTextArea.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */