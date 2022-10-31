/*    */ package com.lbs.controls.menu;
/*    */ 
/*    */ import com.lbs.controls.ILbsComponentBase;
/*    */ import com.lbs.controls.JLbsComponentHelper;
/*    */ import com.lbs.util.UIHelperUtil;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenu;
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
/*    */ public class JLbsMenu
/*    */   extends JMenu
/*    */   implements ILbsInternalMenu
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 25 */   private LbsMenuImplementor m_Implementor = new LbsMenuImplementor(this);
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsMenu() {}
/*    */ 
/*    */   
/*    */   public JLbsMenu(String caption) {
/* 33 */     this(caption, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsMenu(String caption, String[] items) {
/* 39 */     UIHelperUtil.setCaption(this, caption);
/* 40 */     addItems(items);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItems(String[] items) {
/* 45 */     this.m_Implementor.addItems(items);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMenuItem(String caption) {
/* 50 */     JLbsMenuItem item = new JLbsMenuItem();
/* 51 */     UIHelperUtil.setCaption(item, caption);
/* 52 */     add(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public JMenuItem add(JMenuItem item) {
/* 57 */     this.m_Implementor.beforeAddItem(item);
/* 58 */     return super.add(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItemActionListener(ActionListener listener) {
/* 63 */     this.m_Implementor.setItemActionListener(listener);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceIdentifier() {
/* 68 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUniqueIdentifier() {
/* 73 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHaveLayoutManager() {
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 83 */     return getParent();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\JLbsMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */