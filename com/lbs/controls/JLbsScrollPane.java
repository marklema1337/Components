/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComponent;
/*    */ import com.lbs.control.interfaces.ILbsScrollPane;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JScrollPane;
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
/*    */ public class JLbsScrollPane
/*    */   extends JScrollPane
/*    */   implements ILbsScrollPane
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsScrollPane() {}
/*    */   
/*    */   public JLbsScrollPane(Component view) {
/* 31 */     super(view);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsComponent getInnerComponent() {
/* 36 */     JComponent comp = this;
/* 37 */     while (comp.getComponentCount() > 0) {
/*    */       
/* 39 */       comp = (JComponent)comp.getComponent(0);
/* 40 */       if (comp instanceof ILbsComponent)
/* 41 */         return (ILbsComponent)comp; 
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceIdentifier() {
/* 48 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUniqueIdentifier() {
/* 53 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canHaveLayoutManager() {
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 64 */     return getParent();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsScrollPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */