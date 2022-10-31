/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsRadioButton;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsRadioButton;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsSelectionRecordingEvents;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
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
/*     */ public class JLbsRadioButtonGroup
/*     */   extends JLbsMultiColGroupBox
/*     */   implements ILbsSelectionRecordingEvents, ILbsInternalRadioButtonGroup
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsRadioButtonGroup() {
/*  40 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  45 */             JLbsRadioButtonGroup.this.recordRequestFocus();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/*  57 */     return new LbsRadioButtonGroupImplementor(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsRadioButton createRadioButton(String caption, boolean selected) {
/*  62 */     JLbsRadioButton button = new JLbsRadioButton(caption, selected);
/*  63 */     UIHelperUtil.setCaption((JComponent)button, caption);
/*  64 */     return (ILbsRadioButton)button;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, boolean bChecked) {
/*  75 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).addItem(value, iTag, bChecked);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/*  84 */     return ((LbsRadioButtonGroupImplementor)this.m_Implementor).getSelectedItemTag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedItemByTag(int tag) {
/*  92 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).setSelectedItemByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnableItemByTag(int tag, boolean enabled) {
/*  97 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).setEnableItemByTag(tag, enabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(ILbsRadioButton button) {
/* 104 */     ((LbsRadioButtonGroupImplementor)this.m_Implementor).setSelected(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetItemSelected(Component c, boolean flag, int actionID) {
/* 109 */     if (c == null)
/*     */     {
/* 111 */       JLbsComponentHelper.statusChanged(1, actionID, null);
/*     */     }
/* 113 */     final Component comp = c;
/* 114 */     final boolean mFlag = flag;
/* 115 */     final int action = actionID;
/*     */     
/* 117 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 121 */             JLbsRadioButtonGroup.this.setItemSelected(comp, mFlag);
/* 122 */             if (((JLbsRadioButton)comp).isSelected() != mFlag && mFlag) {
/* 123 */               JLbsComponentHelper.statusChanged(1, action, null);
/* 124 */             } else if (((JLbsRadioButton)comp).isSelected() != mFlag && !mFlag) {
/* 125 */               JLbsComponentHelper.statusChanged(1, action, null);
/*     */             } else {
/* 127 */               JLbsComponentHelper.statusChanged(4, action, null);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void recordSetItemSelected(ILbsComponentBase radioButton, boolean b) {
/* 134 */     ((JLbsRadioButton)radioButton).putClientProperty("EVENT_TAG", Integer.valueOf(
/* 135 */           getComponentTag((Component)radioButton)));
/* 136 */     ((JLbsRadioButton)radioButton).putClientProperty("EVENT_PARENT_TAG", Integer.valueOf(
/* 137 */           JLbsComponentHelper.getTag((ILbsComponentBase)this)));
/* 138 */     ((JLbsRadioButton)radioButton).putClientProperty("EVENT_TYPE", Integer.valueOf(108));
/* 139 */     ((JLbsRadioButton)radioButton).putClientProperty("EVENT_VALUE", 
/* 140 */         ((JLbsRadioButton)radioButton).isSelected() ? 
/* 141 */         Integer.valueOf(1) : 
/* 142 */         Integer.valueOf(0));
/* 143 */     JLbsEventRecorderHelper.addRecordItem(radioButton, "SET_SELECTED");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordFireItemStateChanged(ItemEvent event) {}
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 164 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 169 */     final int action = actionID;
/* 170 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 174 */             JLbsRadioButtonGroup.this.requestFocus();
/* 175 */             JLbsComponentHelper.statusChanged(4, action, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsRadioButton getRadioButtonControl(int index) {
/* 182 */     return ((LbsRadioButtonGroupImplementor)this.m_Implementor).getRadioButtonControl(index);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsRadioButtonGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */