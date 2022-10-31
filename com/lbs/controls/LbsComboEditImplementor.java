/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboEdit;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsComboEditImplementor
/*     */ {
/*     */   private ILbsComboEdit m_Component;
/*     */   protected ILbsMaskedEdit m_Edit;
/*     */   protected int m_EmptyBlock;
/*  26 */   protected ActionListener m_ButtonListener = null;
/*     */   
/*  28 */   protected ActionListener m_ExtraButtonListener = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsComboEditImplementor(ILbsComboEdit component, ILbsMaskedEdit edit) {
/*  33 */     this.m_Component = component;
/*  34 */     this.m_Edit = edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean grabFocus() {
/*  39 */     if (this.m_Edit != null) {
/*     */       
/*  41 */       this.m_Edit.requestFocus();
/*  42 */       return true;
/*     */     } 
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color bg) {
/*  49 */     if (this.m_Edit != null) {
/*  50 */       this.m_Edit.setBackground(bg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/*  55 */     this.m_ButtonListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraButtonListener(ActionListener extraButtonListener) {
/*  60 */     this.m_ExtraButtonListener = extraButtonListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/*  65 */     if (this.m_Edit != null) {
/*  66 */       this.m_Edit.addLbsFocusListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/*  71 */     if (this.m_Edit != null) {
/*  72 */       this.m_Edit.removeLbsFocusListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/*  77 */     return this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/*  82 */     return this.m_EmptyBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/*  87 */     this.m_EmptyBlock = width;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPopup(int index, int id) {
/*  92 */     if (!this.m_Component.isEnabled())
/*     */       return; 
/*  94 */     if (this.m_Edit != null)
/*  95 */       this.m_Edit.enableFocusEvent(false); 
/*  96 */     if (id == 0 && this.m_ButtonListener != null)
/*     */     {
/*  98 */       this.m_ButtonListener.actionPerformed(new ActionEvent(this.m_Component, 1001, "Clicked", id));
/*     */     }
/* 100 */     if (id == 1 && this.m_ExtraButtonListener != null)
/*     */     {
/* 102 */       this.m_ExtraButtonListener.actionPerformed(new ActionEvent(this.m_Component, 1001, "Clicked", id));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditor(ILbsMaskedEdit edit) {
/* 108 */     this.m_Edit = edit;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\LbsComboEditImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */