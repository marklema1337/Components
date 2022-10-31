/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsButton;
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.recording.interfaces.ILbsButtonRecordingEvents;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.Icon;
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
/*     */ 
/*     */ 
/*     */ public class JLbsButton
/*     */   extends JButton
/*     */   implements ILbsButton, ILbsButtonRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsButton() {
/*  36 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  41 */             JLbsButton.this.recordRequestFocus();
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
/*     */   public String getResourceIdentifier() {
/*  53 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  58 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireActionPerformed(ActionEvent event) {
/*  63 */     boolean controlKeyPressed = ((event.getModifiers() & 0x2) == 2);
/*  64 */     if (controlKeyPressed) {
/*  65 */       System.setProperty("ModifierKeyPressed", JLbsComponentHelper.getFormName(this));
/*     */     }
/*  67 */     recordActionPerformed(event);
/*  68 */     super.fireActionPerformed(event);
/*     */     
/*  70 */     if (controlKeyPressed) {
/*  71 */       System.setProperty("ModifierKeyPressed", "0");
/*     */     }
/*     */   }
/*     */   
/*     */   public void doFireActionPerformed(ActionEvent event, int actionID) {
/*  76 */     final ActionEvent mEvent = event;
/*  77 */     final int fActionID = actionID;
/*  78 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  82 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*  83 */             JLbsButton.this.fireActionPerformed(mEvent);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {
/*  90 */     if (evt == null) {
/*     */       return;
/*     */     }
/*  93 */     if (getParent() instanceof JLbsMenuButton) {
/*     */       
/*  95 */       JLbsMenuButton menuButton = (JLbsMenuButton)getParent();
/*     */ 
/*     */       
/*  98 */       int tag = JLbsComponentHelper.getTag((ILbsComponentBase)menuButton);
/*  99 */       if (tag == -1002) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 105 */       if (this instanceof ILbsImageButton && !menuButton.isPopupButton((ILbsImageButton)this)) {
/*     */         
/* 107 */         StringBuilder stringBuilder = new StringBuilder();
/* 108 */         stringBuilder.append("MENU_BUTTON_CLICK");
/* 109 */         stringBuilder.append("|");
/* 110 */         stringBuilder.append(evt.getModifiers());
/*     */         
/* 112 */         JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)menuButton, stringBuilder.toString());
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 117 */     StringBuilder buffer = new StringBuilder();
/* 118 */     buffer.append("BUTTON_CLICK");
/* 119 */     buffer.append("|");
/* 120 */     buffer.append(evt.getModifiers());
/*     */     
/* 122 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, buffer.toString());
/*     */   }
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
/*     */   public void recordRequestFocus() {
/* 135 */     if (getParent() instanceof JLbsMenuButton) {
/*     */       
/* 137 */       Object o = ((JLbsMenuButton)getParent()).getClientProperty("EVENT_TAG");
/* 138 */       if (o instanceof Integer) {
/*     */         
/* 140 */         int nValue = ((Integer)o).intValue();
/* 141 */         if (nValue == -1002)
/*     */         {
/*     */           return;
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 149 */         JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */       } 
/*     */     } else {
/*     */       
/* 153 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 158 */     final int fActionID = actionID;
/* 159 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 163 */             JLbsButton.this.requestFocus();
/* 164 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 177 */     return getParent();
/*     */   }
/*     */   
/*     */   public void setHighlightIcon(Icon icon) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */