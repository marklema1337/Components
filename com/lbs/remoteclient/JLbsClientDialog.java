/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JDialog;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsClientDialog
/*    */   extends JDialog
/*    */   implements IClientContextConsumer
/*    */ {
/* 16 */   protected IClientContext m_Context = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setContext(IClientContext context) {
/* 23 */     this.m_Context = context;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void add(JComponent comp, int x, int y, int width, int height) {
/* 28 */     add(comp);
/* 29 */     comp.setBounds(x, y, width, height);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsClientDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */