/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import javax.swing.AbstractButton;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinnedButtonIndexModel
/*     */ {
/*     */   private AbstractButton m_Button;
/*     */   private int m_Normal;
/*     */   private int m_Rollover;
/*     */   private int m_Pressed;
/*     */   private int m_Disabled;
/*     */   private int m_Selected;
/*     */   private int m_SelectedRollover;
/*     */   private int m_SelectedPressed;
/*     */   private int m_SelectedDisabled;
/*     */   private int m_DefaultButton;
/*     */   boolean m_CheckForDefaultButton;
/*     */   
/*     */   public SkinnedButtonIndexModel() {
/*  34 */     this.m_Normal = 0;
/*  35 */     this.m_Rollover = 1;
/*  36 */     this.m_Pressed = 2;
/*  37 */     this.m_Disabled = 3;
/*  38 */     this.m_Selected = 4;
/*  39 */     this.m_SelectedRollover = 5;
/*  40 */     this.m_SelectedPressed = 6;
/*  41 */     this.m_SelectedDisabled = 7;
/*  42 */     this.m_DefaultButton = 8;
/*  43 */     this.m_CheckForDefaultButton = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinnedButtonIndexModel(boolean checkForDefaultButton) {
/*  48 */     this();
/*  49 */     this.m_CheckForDefaultButton = checkForDefaultButton;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SkinnedButtonIndexModel(int normal, int rollover, int pressed, int disabled, int selected, int selectedRollover, int selectedPressed, int selectedDisabled) {
/*  55 */     this.m_Normal = normal;
/*  56 */     this.m_Rollover = rollover;
/*  57 */     this.m_Pressed = pressed;
/*  58 */     this.m_Disabled = disabled;
/*     */     
/*  60 */     this.m_Selected = selected;
/*  61 */     this.m_SelectedRollover = selectedRollover;
/*  62 */     this.m_SelectedPressed = selectedPressed;
/*  63 */     this.m_SelectedDisabled = selectedDisabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexForState() {
/*  68 */     if (!this.m_Button.isSelected()) {
/*     */       
/*  70 */       if (!this.m_Button.isEnabled())
/*  71 */         return this.m_Disabled; 
/*  72 */       if (this.m_Button.getModel().isPressed())
/*  73 */         return this.m_Pressed; 
/*  74 */       if (this.m_Button.getModel().isRollover())
/*  75 */         return this.m_Rollover; 
/*  76 */       if (this.m_CheckForDefaultButton) {
/*     */         
/*  78 */         JButton jb = (JButton)this.m_Button;
/*  79 */         if (jb.isDefaultButton())
/*  80 */           return this.m_DefaultButton; 
/*     */       } 
/*  82 */       return this.m_Normal;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (!this.m_Button.isEnabled())
/*  87 */       return this.m_SelectedDisabled; 
/*  88 */     if (this.m_Button.getModel().isPressed())
/*  89 */       return this.m_SelectedPressed; 
/*  90 */     if (this.m_Button.getModel().isRollover())
/*  91 */       return this.m_SelectedRollover; 
/*  92 */     return this.m_Selected;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndexForState(boolean isSelected, boolean isEnabled, boolean isPressed, boolean isRollover) {
/*  98 */     if (!isSelected) {
/*     */       
/* 100 */       if (!isEnabled)
/* 101 */         return this.m_Disabled; 
/* 102 */       if (isPressed)
/* 103 */         return this.m_Pressed; 
/* 104 */       if (isRollover)
/* 105 */         return this.m_Rollover; 
/* 106 */       return this.m_Normal;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     if (!isEnabled)
/* 111 */       return this.m_SelectedDisabled; 
/* 112 */     if (isPressed)
/* 113 */       return this.m_SelectedPressed; 
/* 114 */     if (isRollover)
/* 115 */       return this.m_SelectedRollover; 
/* 116 */     return this.m_Selected;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndexForState(boolean isEnabled, boolean isRollover, boolean isPressed) {
/* 122 */     if (!isEnabled)
/* 123 */       return this.m_Disabled; 
/* 124 */     if (isPressed)
/* 125 */       return this.m_Pressed; 
/* 126 */     if (isRollover) {
/* 127 */       return this.m_Rollover;
/*     */     }
/* 129 */     return this.m_Normal;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractButton getButton() {
/* 134 */     return this.m_Button;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButton(AbstractButton button) {
/* 139 */     this.m_Button = button;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCheckForDefaultButton() {
/* 144 */     return this.m_CheckForDefaultButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCheckForDefaultButton(boolean hasToggleButton) {
/* 149 */     this.m_CheckForDefaultButton = hasToggleButton;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedButtonIndexModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */