/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsRadioButton;
/*    */ import com.lbs.controls.ILbsComponentBase;
/*    */ import com.lbs.controls.groupbox.ILbsInternalRadioButtonGroup;
/*    */ import com.lbs.controls.groupbox.LbsMultiColGroupBoxImplementor;
/*    */ import com.lbs.controls.groupbox.LbsRadioButtonGroupImplementor;
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
/*    */ public class LbsRadioButtonGroupEmulator
/*    */   extends LbsMultiColGroupBoxEmulator
/*    */   implements ILbsInternalRadioButtonGroup
/*    */ {
/*    */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/* 23 */     return (LbsMultiColGroupBoxImplementor)new LbsRadioButtonGroupImplementor(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsRadioButton createRadioButton(String caption, boolean selected) {
/* 28 */     ILbsRadioButton radio = new LbsRadioButtonEmulator();
/* 29 */     radio.setText(caption);
/* 30 */     radio.setSelected(selected);
/* 31 */     return radio;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void recordSetItemSelected(ILbsComponentBase comp, boolean selected) {}
/*    */ 
/*    */   
/*    */   public void setSelected(ILbsRadioButton selected) {
/* 40 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).setSelected(selected);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addItem(Object value, int tag, boolean checked) {
/* 45 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).addItem(value, tag, checked);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsRadioButton getRadioButtonControl(int index) {
/* 50 */     return ((LbsRadioButtonGroupImplementor)this.m_Implementor).getRadioButtonControl(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSelectedItemTag() {
/* 55 */     return ((LbsRadioButtonGroupImplementor)this.m_Implementor).getSelectedItemTag();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSelectedItemByTag(int tag) {
/* 60 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).setSelectedItemByTag(tag);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsRadioButtonGroupEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */