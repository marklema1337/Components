/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsAbstractButton;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.ItemSelectable;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import javax.swing.Icon;
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
/*     */ public class LbsAbstractButtonEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsAbstractButton, ItemSelectable
/*     */ {
/*     */   private String m_ActionCommand;
/*     */   private String m_Text;
/*     */   private boolean m_Selected = false;
/*     */   private boolean m_Pressed = false;
/*     */   private ActionListener m_ActionListener;
/*     */   private ItemListener m_ItemListener;
/*     */   
/*     */   public void addItemListener(ItemListener l) {
/*  36 */     if (l == null)
/*     */       return; 
/*  38 */     this.m_ItemListener = AWTEventMulticaster.add(this.m_ItemListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doClick() {
/*  43 */     setPressed(true);
/*  44 */     setPressed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setPressed(boolean b) {
/*  49 */     if (isPressed() == b || !isEnabled()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  54 */     this.m_Pressed = b;
/*     */     
/*  56 */     if (!isPressed())
/*     */     {
/*  58 */       fireActionPerformed();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isPressed() {
/*  64 */     return this.m_Pressed;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getActionCommand() {
/*  69 */     return this.m_ActionCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getDisabledIcon() {
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getDisabledSelectedIcon() {
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/*  84 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalTextPosition() {
/*  89 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon() {
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  99 */     return this.m_Text;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVerticalAlignment() {
/* 104 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVerticalTextPosition() {
/* 109 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelected() {
/* 114 */     return this.m_Selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItemListener(ItemListener l) {
/* 119 */     if (l == null) {
/*     */       return;
/*     */     }
/* 122 */     this.m_ItemListener = AWTEventMulticaster.remove(this.m_ItemListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionCommand(String command) {
/* 127 */     this.m_ActionCommand = command;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisabledIcon(Icon disabledIcon) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisabledSelectedIcon(Icon disabledSelectedIcon) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int alignment) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHorizontalTextPosition(int textPosition) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(Icon defaultIcon) {}
/*     */ 
/*     */   
/*     */   public void setSelected(boolean selected) {
/* 152 */     if (selected == isSelected()) {
/*     */       return;
/*     */     }
/* 155 */     this.m_Selected = selected;
/*     */     
/* 157 */     fireItemStateChanged(new ItemEvent(this, 701, this, selected ? 
/* 158 */           1 : 
/* 159 */           2));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 164 */     this.m_Text = text;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVerticalAlignment(int alignment) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVerticalTextPosition(int textPosition) {}
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/* 177 */     if (listener == null) {
/*     */       return;
/*     */     }
/* 180 */     this.m_ActionListener = AWTEventMulticaster.add(this.m_ActionListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/* 185 */     if (l == null) {
/*     */       return;
/*     */     }
/* 188 */     this.m_ActionListener = AWTEventMulticaster.remove(this.m_ActionListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireActionPerformed() {
/* 193 */     if (this.m_ActionListener == null) {
/*     */       return;
/*     */     }
/* 196 */     this.m_ActionListener.actionPerformed(new ActionEvent(this, 1001, getActionCommand(), 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireItemStateChanged(ItemEvent e) {
/* 201 */     if (this.m_ItemListener == null) {
/*     */       return;
/*     */     }
/* 204 */     this.m_ItemListener.itemStateChanged(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getSelectedObjects() {
/* 209 */     if (!isSelected())
/*     */     {
/* 211 */       return null;
/*     */     }
/* 213 */     Object[] selectedObjects = new Object[1];
/* 214 */     selectedObjects[0] = getText();
/* 215 */     return selectedObjects;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsAbstractButtonEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */