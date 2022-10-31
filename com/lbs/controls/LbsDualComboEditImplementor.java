/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
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
/*     */ public class LbsDualComboEditImplementor
/*     */   implements FocusListener
/*     */ {
/*     */   private ILbsInternalDualComboEdit m_Component;
/*     */   private ILbsMaskedEdit m_Edit;
/*     */   private ILbsMaskedEdit m_Edit2;
/*     */   protected String m_Text;
/*     */   protected int m_EmptyBlock;
/*  32 */   protected ActionListener m_ButtonListener = null;
/*     */   
/*     */   protected boolean m_Internal = false;
/*     */   
/*     */   private String m_Format;
/*     */ 
/*     */   
/*     */   public LbsDualComboEditImplementor(ILbsInternalDualComboEdit component) {
/*  40 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditors(ILbsMaskedEdit edit, ILbsMaskedEdit edit2) {
/*  45 */     this.m_Edit = edit;
/*  46 */     this.m_Edit2 = edit2;
/*     */     
/*  48 */     if (this.m_Edit != null)
/*  49 */       this.m_Edit.addFocusListener(this); 
/*  50 */     if (this.m_Edit2 != null) {
/*     */       
/*  52 */       this.m_Edit2.addFocusListener(this);
/*  53 */       this.m_Edit2.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean grabFocus() {
/*  59 */     if (this.m_Edit != null) {
/*     */       
/*  61 */       this.m_Edit.requestFocus();
/*  62 */       return true;
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color bg) {
/*  69 */     if (this.m_Edit != null) {
/*     */       
/*  71 */       this.m_Edit.setBackground(bg);
/*  72 */       this.m_Edit2.setBackground(bg);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/*  78 */     this.m_ButtonListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/*  83 */     if (this.m_Edit != null) {
/*  84 */       this.m_Edit.addLbsFocusListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/*  89 */     if (this.m_Edit != null) {
/*  90 */       this.m_Edit.removeLbsFocusListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/*  95 */     return this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/* 100 */     return this.m_EmptyBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/* 105 */     this.m_EmptyBlock = width;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPopup(int index, int id) {
/* 110 */     if (this.m_Edit != null)
/* 111 */       this.m_Edit.enableFocusEvent(false); 
/* 112 */     if (this.m_ButtonListener != null)
/*     */     {
/* 114 */       this.m_ButtonListener.actionPerformed(new ActionEvent(this.m_Component, 1001, "Clicked"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 120 */     Object source = e.getSource();
/* 121 */     if ((source == this.m_Edit || source == this.m_Edit2) && !JLbsConstants.DESIGN_TIME) {
/*     */       
/* 123 */       this.m_Edit.setVisible(true);
/* 124 */       this.m_Edit2.setVisible(false);
/* 125 */       this.m_Component.setFillControl(this.m_Edit);
/* 126 */       if (source != this.m_Edit) {
/*     */         
/* 128 */         this.m_Edit.requestFocus();
/* 129 */         this.m_Edit.selectAll();
/*     */       } 
/* 131 */       updateToolTipText();
/* 132 */       resetBkColor(true, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 139 */     Object source = e.getSource();
/* 140 */     if ((source == this.m_Edit || source == this.m_Edit2) && !JLbsConstants.DESIGN_TIME) {
/*     */       
/* 142 */       this.m_Edit.setVisible(false);
/* 143 */       this.m_Edit2.setVisible(true);
/* 144 */       String displayText = getDisplayText(false);
/* 145 */       this.m_Edit2.setText(displayText);
/* 146 */       if (!JLbsStringUtil.isEmpty(displayText))
/* 147 */         this.m_Edit2.setCaretPosition(0); 
/* 148 */       updateToolTipText();
/* 149 */       this.m_Component.setFillControl(this.m_Edit2);
/* 150 */       resetBkColor(false, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditorText(String text) {
/* 156 */     if (this.m_Edit != null)
/* 157 */       this.m_Edit.setText(text); 
/* 158 */     updateToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEditorText() {
/* 163 */     if (this.m_Edit != null)
/* 164 */       return this.m_Edit.getText(); 
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text1, String text2) {
/* 170 */     if (this.m_Edit != null)
/* 171 */       this.m_Edit.setText(text1); 
/* 172 */     this.m_Text = text2;
/* 173 */     updateToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getText() {
/* 178 */     String[] result = new String[2];
/* 179 */     if (this.m_Edit != null)
/* 180 */       result[0] = this.m_Edit.getText(); 
/* 181 */     result[1] = this.m_Text;
/* 182 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipFormat(String format) {
/* 187 */     this.m_Format = format;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 192 */     resetBkColor(this.m_Component.isFocusOwner(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void resetBkColor(boolean bFocused, boolean bForce) {
/* 197 */     if (this.m_Edit != null && this.m_Component.isEnabled() && !JLbsConstants.DESIGN_TIME) {
/*     */       
/* 199 */       boolean focused = bForce ? 
/* 200 */         bFocused : 
/* 201 */         this.m_Edit.hasFocus();
/* 202 */       setBackground(focused ? 
/* 203 */           JLbsMaskedEdit.getSelectedBkColor() : 
/* 204 */           this.m_Edit.getNormalBkColor());
/* 205 */       this.m_Component.repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDisplayText(boolean bFormated) {
/* 211 */     if (this.m_Format == null)
/* 212 */       this.m_Format = "%s - %s"; 
/* 213 */     String format = "%s %s";
/* 214 */     if (bFormated)
/* 215 */       format = this.m_Format; 
/* 216 */     String result = JLbsStringUtil.format(format, (this.m_Edit != null) ? 
/* 217 */         this.m_Edit.getText() : 
/* 218 */         "", this.m_Text);
/* 219 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateToolTipText() {
/* 224 */     String toolTip = getDisplayText(true);
/* 225 */     if (this.m_Edit != null) {
/*     */       
/* 227 */       this.m_Edit.setToolTipText(toolTip);
/* 228 */       this.m_Edit2.setToolTipText(toolTip);
/*     */     } 
/* 230 */     this.m_Component.setToolTipText(toolTip);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\LbsDualComboEditImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */