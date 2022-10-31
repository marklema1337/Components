/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboEdit;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.LbsComboEditImplementor;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsComboEditEmulator
/*     */   extends LbsPanelEmulator
/*     */   implements ILbsComboEdit
/*     */ {
/*     */   protected LbsComboEditImplementor m_Implementor;
/*     */   protected boolean m_HasExtraButton = false;
/*     */   
/*     */   public LbsComboEditEmulator() {
/*  31 */     this(new LbsTextEditEmulator());
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsComboEditEmulator(ILbsMaskedEdit edit) {
/*  36 */     this.m_Implementor = createImplementor(edit);
/*  37 */     addChild((ILbsComponent)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected LbsComboEditImplementor createImplementor(ILbsMaskedEdit edit) {
/*  42 */     return new LbsComboEditImplementor(this, edit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtraButton(int buttonType) {
/*  47 */     this.m_HasExtraButton = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doButtonAction(int index, int id) {
/*  52 */     if (id == 1 && !this.m_HasExtraButton) {
/*     */       return;
/*     */     }
/*  55 */     this.m_Implementor.doPopup(index, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/*  60 */     return this.m_Implementor.getEditControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/*  65 */     return this.m_Implementor.getEmptyBlockWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/*  70 */     this.m_Implementor.setActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/*  75 */     this.m_Implementor.setEmptyBlockWidth(width);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraButtonListener(ActionListener extraButtonListener) {
/*  80 */     this.m_Implementor.setExtraButtonListener(extraButtonListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/*  85 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/*  90 */     this.m_Implementor.removeLbsFocusListener(listener);
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
/*     */   public void setFocusManager(LbsFocusManager focusManager) {
/* 107 */     super.setFocusManager(focusManager);
/* 108 */     ((LbsComponentEmulator)getEditControl()).setFocusManager(focusManager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstLookupButtonVisible(boolean visible) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMultilangButton(JButton button) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconToMultilangButton(ImageIcon icon) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getMultilangButton() {
/* 136 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsComboEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */