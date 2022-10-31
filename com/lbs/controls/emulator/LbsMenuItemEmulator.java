/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsMenuItem;
/*    */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*    */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*    */ import com.lbs.controls.menu.JLbsPopupMenuItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsMenuItemEmulator
/*    */   extends LbsAbstractButtonEmulator
/*    */   implements ILbsMenuItem, ILbsPopupMenuItem
/*    */ {
/* 19 */   private int m_Tag = 0;
/*    */   private int m_Index;
/* 21 */   protected ILbsPopupMenu m_RootMenu = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsMenuItemEmulator() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsMenuItemEmulator(ILbsPopupMenu rootMenu, int tag, int index) {
/* 30 */     this.m_RootMenu = rootMenu;
/* 31 */     this.m_Tag = tag;
/* 32 */     this.m_Index = index;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsMenuItemEmulator(ILbsPopupMenu rootMenu, String s, int tag, int index) {
/* 38 */     setText(s);
/* 39 */     this.m_RootMenu = rootMenu;
/* 40 */     this.m_Tag = tag;
/* 41 */     this.m_Index = index;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTag() {
/* 46 */     return this.m_Tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTag(int tag) {
/* 51 */     this.m_Tag = tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 56 */     return this.m_Tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIndex() {
/* 61 */     return this.m_Index;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIndex(int index) {
/* 66 */     this.m_Index = index;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toEditString() {
/* 71 */     String text = getText();
/* 72 */     return JLbsPopupMenuItem.toEditString(text, this.m_Tag);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParentComponent() {
/* 77 */     return this.m_RootMenu;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsMenuItemEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */