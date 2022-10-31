/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsCheckBox;
/*     */ import com.lbs.controls.groupbox.JLbsCheckBoxGroup;
/*     */ import com.lbs.recording.JLbsRecordItem;
/*     */ import com.lbs.recording.interfaces.ILbsSelectionRecordingEvents;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JCheckBox;
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
/*     */ public class JLbsCheckBox
/*     */   extends JCheckBox
/*     */   implements ILbsCheckBox, ILbsSelectionRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsCheckBox() {
/*  31 */     super((String)null, (Icon)null, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCheckBox(String text, boolean selected) {
/*  36 */     super(text, (Icon)null, selected);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*  42 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  47 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetItemSelected(ILbsComponentBase checkbox, boolean flag) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireItemStateChanged(ItemEvent event) {
/*  75 */     super.fireItemStateChanged(event);
/*  76 */     recordFireItemStateChanged(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordFireItemStateChanged(ItemEvent event) {
/*  82 */     if (!(getParent() instanceof JLbsCheckBoxGroup)) {
/*     */       
/*  84 */       Integer val = Integer.valueOf(0);
/*  85 */       if (isSelected())
/*  86 */         val = Integer.valueOf(1); 
/*  87 */       JLbsRecordItem recordItem = 
/*  88 */         JLbsEventRecorderHelper.prepareRecordItem((ILbsComponentBase)this, "SET_SELECTED", val);
/*  89 */       recordItem.getDataPoolItem().setParentTag(-1);
/*  90 */       recordItem.setComponent((ILbsComponentBase)this);
/*  91 */       JLbsEventRecorderHelper.addRecordItem(recordItem);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetSelected(boolean flag, int actionID) {
/*  97 */     final boolean mFlag = flag;
/*  98 */     final int action = actionID;
/*  99 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 103 */             JLbsCheckBox.this.setSelected(mFlag);
/* 104 */             JLbsCheckBox.this.fireActionPerformed(new ActionEvent(this, -1, null));
/* 105 */             if (JLbsCheckBox.this.isSelected() != mFlag && mFlag) {
/* 106 */               JLbsComponentHelper.statusChanged(1, action, null);
/* 107 */             } else if (JLbsCheckBox.this.isSelected() != mFlag && !mFlag) {
/* 108 */               JLbsComponentHelper.statusChanged(1, action, null);
/*     */             } else {
/* 110 */               JLbsComponentHelper.statusChanged(4, action, null);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void requestFocus() {
/* 117 */     super.requestFocus();
/* 118 */     recordRequestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 123 */     if (getParent() instanceof JLbsCheckBoxGroup) {
/*     */       
/* 125 */       JLbsCheckBoxGroup group = (JLbsCheckBoxGroup)getParent();
/* 126 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)group, "REQUEST_FOCUS", Integer.valueOf(group
/* 127 */             .getComponentTag(this)));
/*     */     } else {
/*     */       
/* 130 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 135 */     final int action = actionID;
/* 136 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 140 */             JLbsCheckBox.this.requestFocus();
/* 141 */             JLbsComponentHelper.statusChanged(4, action, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 153 */     return getParent();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */