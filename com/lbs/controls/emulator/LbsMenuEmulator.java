/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.controls.menu.ILbsInternalMenu;
/*    */ import com.lbs.controls.menu.LbsMenuImplementor;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsMenuEmulator
/*    */   extends LbsAbstractButtonEmulator
/*    */   implements ILbsInternalMenu
/*    */ {
/* 20 */   private ArrayList m_Items = new ArrayList();
/*    */   
/* 22 */   private LbsMenuImplementor m_Implementor = new LbsMenuImplementor(this);
/*    */ 
/*    */   
/*    */   public void addItems(String[] items) {
/* 26 */     this.m_Implementor.addItems(items);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addSeparator() {
/* 31 */     this.m_Items.add(new LbsMenuItemEmulator());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemCount() {
/* 36 */     return this.m_Items.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTopLevelMenu() {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItemActionListener(ActionListener listener) {
/* 46 */     this.m_Implementor.setItemActionListener(listener);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMenuItem(String caption) {
/* 51 */     LbsMenuItemEmulator item = new LbsMenuItemEmulator();
/* 52 */     item.setText(caption);
/* 53 */     this.m_Implementor.beforeAddItem(item);
/* 54 */     this.m_Items.add(item);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsMenuEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */