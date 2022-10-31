/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.ILbsInternalDualComboEdit;
/*     */ import com.lbs.controls.LbsDualComboEditImplementor;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsDualComboEditEmulator
/*     */   extends LbsPanelEmulator
/*     */   implements ILbsInternalDualComboEdit
/*     */ {
/*  22 */   private LbsDualComboEditImplementor m_Implementor = new LbsDualComboEditImplementor(this);
/*     */ 
/*     */   
/*     */   public LbsDualComboEditEmulator() {
/*  26 */     this(new LbsTextEditEmulator());
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsDualComboEditEmulator(ILbsMaskedEdit edit) {
/*  31 */     this.m_Implementor.setEditors(edit, new LbsTextEditEmulator());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFillControl(ILbsMaskedEdit edit) {}
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/*  40 */     return this.m_Implementor.getEditControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEditorText() {
/*  45 */     return this.m_Implementor.getEditorText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/*  50 */     return this.m_Implementor.getEmptyBlockWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/*  55 */     this.m_Implementor.setActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditorText(String text) {
/*  60 */     this.m_Implementor.setEditorText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/*  65 */     this.m_Implementor.setEmptyBlockWidth(width);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text1, String text2) {
/*  70 */     this.m_Implementor.setText(text1, text2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipFormat(String format) {
/*  75 */     this.m_Implementor.setToolTipFormat(format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/*  80 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/*  85 */     this.m_Implementor.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getText() {
/*  90 */     return this.m_Implementor.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/*  95 */     if (!this.m_Implementor.grabFocus()) {
/*  96 */       super.grabFocus();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBackground(Color bg) {
/* 101 */     super.setBackground(bg);
/* 102 */     this.m_Implementor.setBackground(bg);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 107 */     super.setEnabled(enabled);
/* 108 */     this.m_Implementor.setEnabled(enabled);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsDualComboEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */