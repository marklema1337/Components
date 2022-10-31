/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsCheckBox;
/*    */ import com.lbs.controls.groupbox.ILbsInternalCheckBoxGroup;
/*    */ import com.lbs.controls.groupbox.LbsCheckBoxGroupImplementor;
/*    */ import com.lbs.controls.groupbox.LbsMultiColGroupBoxImplementor;
/*    */ import com.lbs.util.JLbsStringList;
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
/*    */ public class LbsCheckBoxGroupEmulator
/*    */   extends LbsMultiColGroupBoxEmulator
/*    */   implements ILbsInternalCheckBoxGroup
/*    */ {
/*    */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/* 23 */     return (LbsMultiColGroupBoxImplementor)new LbsCheckBoxGroupImplementor(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsCheckBox createCheckBox(String caption, boolean selected) {
/* 28 */     ILbsCheckBox chkBox = new LbsCheckBoxEmulator();
/* 29 */     chkBox.setSelected(selected);
/* 30 */     chkBox.setText(caption);
/* 31 */     return chkBox;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItem(Object value, int tag, boolean checked) {
/* 36 */     ((LbsCheckBoxGroupImplementor)this.m_Implementor).addItem(value, tag, checked);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsCheckBox getCheckBoxControl(int index) {
/* 41 */     return ((LbsCheckBoxGroupImplementor)this.m_Implementor).getCheckBoxControl(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItemDeselectedByTag(int tag) {
/* 46 */     ((LbsCheckBoxGroupImplementor)this.m_Implementor).setItemDeselectedByTag(tag);
/*    */   }
/*    */   
/*    */   public void setPopupList(JLbsStringList list) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsCheckBoxGroupEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */