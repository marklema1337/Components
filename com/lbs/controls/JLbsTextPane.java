/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTextPane;
/*    */ import javax.swing.JTextPane;
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
/*    */ public class JLbsTextPane
/*    */   extends JTextPane
/*    */   implements ILbsTextPane
/*    */ {
/*    */   public int getUniqueIdentifier() {
/* 20 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceIdentifier() {
/* 25 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHaveLayoutManager() {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 35 */     return getPage();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResetTime(boolean resetTime) {}
/*    */ 
/*    */   
/*    */   public boolean isResetTime() {
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsTextPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */