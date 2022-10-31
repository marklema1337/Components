/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsTabPage;
/*     */ import com.lbs.recording.interfaces.ILbsTabPageRecordingEvents;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsTabPage
/*     */   extends JLbsPanel
/*     */   implements ILbsTabPageRecordingEvents, ILbsTabPage
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getCaption() {
/*  25 */     Container cont = getParent();
/*  26 */     if (cont instanceof JTabbedPane) {
/*     */       
/*  28 */       JTabbedPane pane = (JTabbedPane)cont;
/*     */       
/*  30 */       int tabCount = pane.getTabCount();
/*  31 */       for (int i = 0; i < tabCount; i++) {
/*     */         
/*  33 */         if (pane.getComponentAt(i) == this) {
/*  34 */           return pane.getTitleAt(i);
/*     */         }
/*     */       } 
/*     */     } 
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaption(String caption) {
/*  43 */     Container cont = getParent();
/*  44 */     if (cont instanceof JTabbedPane) {
/*     */       
/*  46 */       JTabbedPane pane = (JTabbedPane)cont;
/*     */       
/*  48 */       int tabCount = pane.getTabCount();
/*  49 */       for (int i = 0; i < tabCount; i++) {
/*     */         
/*  51 */         if (pane.getComponentAt(i) == this) {
/*     */           
/*  53 */           pane.setTitleAt(i, caption);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPageIndex() {
/*  63 */     Container cont = getParent();
/*  64 */     if (cont instanceof JTabbedPane) {
/*     */       
/*  66 */       JTabbedPane pane = (JTabbedPane)cont;
/*     */       
/*  68 */       int tabCount = pane.getTabCount();
/*  69 */       for (int i = 0; i < tabCount; i++) {
/*     */         
/*  71 */         if (pane.getComponentAt(i) == this)
/*     */         {
/*  73 */           return i;
/*     */         }
/*     */       } 
/*     */     } 
/*  77 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPageIndex(int value) {
/*  82 */     Container cont = getParent();
/*  83 */     if (cont instanceof JTabbedPane) {
/*     */       
/*  85 */       JTabbedPane pane = (JTabbedPane)cont;
/*     */       
/*  87 */       int tabCount = pane.getTabCount();
/*  88 */       if (value >= tabCount)
/*  89 */         value = tabCount - 1; 
/*  90 */       for (int i = 0; i < tabCount; i++) {
/*     */         
/*  92 */         if (pane.getComponentAt(i) == this) {
/*     */           
/*  94 */           if (value == i)
/*     */             return; 
/*  96 */           if (value < 0)
/*  97 */             value = 0; 
/*  98 */           String title = pane.getTitleAt(i);
/*  99 */           pane.remove(i);
/* 100 */           pane.insertTab(title, null, this, title, value);
/* 101 */           recordSetPageIndex(value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doSetPageIndex(int index, int actionID) {
/* 110 */     final int mActionID = actionID;
/* 111 */     final int mIndex = index;
/* 112 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/* 118 */               JLbsTabPage.this.setPageIndex(mIndex);
/*     */             }
/* 120 */             catch (Exception e) {
/*     */               
/* 122 */               JLbsComponentHelper.statusChanged(1, mActionID, "Page index could not be set to " + 
/* 123 */                   mIndex);
/*     */               return;
/*     */             } 
/* 126 */             if (JLbsTabPage.this.isPageIndexChanged(mIndex)) {
/* 127 */               JLbsComponentHelper.statusChanged(4, mActionID, "Set Page Index succedded.");
/*     */             } else {
/* 129 */               JLbsComponentHelper.statusChanged(1, mActionID, "Page index could not be set to " + 
/* 130 */                   mIndex);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private boolean isPageIndexChanged(int anIndex) {
/* 137 */     if (anIndex == getPageIndex()) {
/* 138 */       return true;
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 145 */     final int mActionID = actionID;
/* 146 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 150 */             JLbsTabPage.this.requestFocus();
/* 151 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 158 */     return super.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordSetPageIndex(int value) {
/* 163 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "SET_PAGE_INDEX");
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
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 180 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsTabPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */