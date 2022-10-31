/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsRadioButton;
/*     */ import com.lbs.controls.groupbox.JLbsRadioButtonGroup;
/*     */ import com.lbs.recording.JLbsRecordItem;
/*     */ import com.lbs.recording.interfaces.ILbsSelectionRecordingEvents;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JRadioButton;
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
/*     */ public class JLbsRadioButton
/*     */   extends JRadioButton
/*     */   implements ILbsRadioButton, ILbsSelectionRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsRadioButton() {
/*  31 */     super("", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRadioButton(String caption, boolean selected) {
/*  40 */     super(caption, selected);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*  45 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  50 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireItemStateChanged(ItemEvent event) {
/*  55 */     super.fireItemStateChanged(event);
/*  56 */     recordFireItemStateChanged(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordFireItemStateChanged(ItemEvent event) {
/*  62 */     if (!(getParent() instanceof JLbsRadioButtonGroup)) {
/*     */       
/*  64 */       Integer val = Integer.valueOf(0);
/*  65 */       if (isSelected())
/*  66 */         val = Integer.valueOf(1); 
/*  67 */       JLbsRecordItem recordItem = 
/*  68 */         JLbsEventRecorderHelper.prepareRecordItem((ILbsComponentBase)this, "SET_SELECTED", val);
/*  69 */       recordItem.getDataPoolItem().setParentTag(-1);
/*  70 */       recordItem.setComponent((ILbsComponentBase)this);
/*  71 */       JLbsEventRecorderHelper.addRecordItem(recordItem);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetSelected(boolean flag, int actionID) {
/*  77 */     final boolean mFlag = flag;
/*  78 */     final int action = actionID;
/*     */     
/*  80 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  84 */             JLbsRadioButton.this.setSelected(mFlag);
/*  85 */             JLbsRadioButton.this.fireActionPerformed(new ActionEvent(this, -1, null));
/*  86 */             if (JLbsRadioButton.this.isSelected() != mFlag && mFlag) {
/*  87 */               JLbsComponentHelper.statusChanged(1, action, null);
/*  88 */             } else if (JLbsRadioButton.this.isSelected() != mFlag && !mFlag) {
/*  89 */               JLbsComponentHelper.statusChanged(1, action, null);
/*     */             } else {
/*  91 */               JLbsComponentHelper.statusChanged(4, action, null);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void requestFocus() {
/*  98 */     super.requestFocus();
/*  99 */     recordRequestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 104 */     if (getParent() instanceof JLbsRadioButtonGroup) {
/*     */       
/* 106 */       JLbsRadioButtonGroup group = (JLbsRadioButtonGroup)getParent();
/* 107 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)group, "REQUEST_FOCUS", Integer.valueOf(group
/* 108 */             .getComponentTag(this)));
/*     */     } else {
/*     */       
/* 111 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 116 */     final int action = actionID;
/* 117 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 121 */             JLbsRadioButton.this.requestFocus();
/* 122 */             JLbsComponentHelper.statusChanged(4, action, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetItemSelected(ILbsComponentBase c, boolean flag) {}
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
/*     */   public boolean canHaveLayoutManager() {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 150 */     return getParent();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsRadioButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */