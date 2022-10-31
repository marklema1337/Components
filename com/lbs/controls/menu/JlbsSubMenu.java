/*    */ package com.lbs.controls.menu;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsSubMenu;
/*    */ import com.lbs.controls.ILbsComponentBase;
/*    */ import com.lbs.controls.JLbsComponentHelper;
/*    */ import com.lbs.util.UIHelperUtil;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JMenu;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JlbsSubMenu
/*    */   extends JMenu
/*    */   implements ILbsSubMenu
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JlbsSubMenu(String s) {
/* 25 */     UIHelperUtil.setCaption(this, s);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItem(Object item) {
/* 30 */     if (item instanceof JComponent) {
/* 31 */       add((JComponent)item);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 37 */     return getParent();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUniqueIdentifier() {
/* 43 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getResourceIdentifier() {
/* 49 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canHaveLayoutManager() {
/* 55 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\JlbsSubMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */