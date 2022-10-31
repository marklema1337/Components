/*    */ package com.lbs.controls.menu;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsMenuItem;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenuItem;
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
/*    */ public class LbsMenuImplementor
/*    */ {
/*    */   private ILbsInternalMenu m_Component;
/* 23 */   private InternalActionListener m_ItemListener = null;
/* 24 */   private ActionListener m_ActionListener = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsMenuImplementor(ILbsInternalMenu component) {
/* 29 */     this.m_Component = component;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItems(String[] items) {
/* 34 */     if (items != null && items.length > 0) {
/* 35 */       for (int i = 0; i < items.length; i++) {
/*    */         
/* 37 */         String itemCaption = items[i];
/* 38 */         if (itemCaption == null || itemCaption.length() == 0 || itemCaption.charAt(0) == '-') {
/* 39 */           this.m_Component.addSeparator();
/*    */         } else {
/*    */           
/* 42 */           this.m_Component.addMenuItem(itemCaption);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void beforeAddItem(Object item) {
/* 49 */     if (this.m_ItemListener == null)
/* 50 */       this.m_ItemListener = new InternalActionListener(); 
/* 51 */     if (item instanceof ILbsMenuItem)
/* 52 */       ((ILbsMenuItem)item).addActionListener(this.m_ItemListener); 
/* 53 */     if (item instanceof JMenuItem) {
/* 54 */       ((JMenuItem)item).addActionListener(this.m_ItemListener);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setItemActionListener(ActionListener listener) {
/* 59 */     this.m_ActionListener = listener;
/*    */   }
/*    */   
/*    */   class InternalActionListener
/*    */     implements ActionListener
/*    */   {
/*    */     public void actionPerformed(ActionEvent event) {
/* 66 */       if (LbsMenuImplementor.this.m_ActionListener != null)
/* 67 */         LbsMenuImplementor.this.m_ActionListener.actionPerformed(event); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\LbsMenuImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */