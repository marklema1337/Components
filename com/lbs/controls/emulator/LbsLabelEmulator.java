/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsLabel;
/*    */ import javax.swing.Icon;
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
/*    */ public class LbsLabelEmulator
/*    */   extends LbsComponentEmulator
/*    */   implements ILbsLabel
/*    */ {
/*    */   private String m_Text;
/*    */   
/*    */   public void autosize() {}
/*    */   
/*    */   public int getHorizontalAlignment() {
/* 27 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon() {
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumLines() {
/* 37 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 42 */     return this.m_Text;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getVerticalAlignment() {
/* 47 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHorizontalAlignment(int alignment) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setIcon(Icon icon) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 62 */     this.m_Text = text;
/*    */   }
/*    */   
/*    */   public void setVerticalAlignment(int alignment) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsLabelEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */