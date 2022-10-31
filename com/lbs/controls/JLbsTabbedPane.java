/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsTabPage;
/*     */ import com.lbs.control.interfaces.ILbsTabbedPane;
/*     */ import com.lbs.controls.wizard.xui.ILbsXUIWizardPaneListener;
/*     */ import com.lbs.recording.interfaces.ILbsTabbedPaneRecordingEvents;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.plaf.TabbedPaneUI;
/*     */ import javax.swing.text.StyleContext;
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
/*     */ public class JLbsTabbedPane
/*     */   extends JTabbedPane
/*     */   implements ILbsTabbedPane, ILbsTabbedPaneRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   ILbsTabbedPaneListener m_TabbedPaneListener;
/*  37 */   private static StyleContext context = new StyleContext();
/*  38 */   public static Map<String, FontMetrics> ms_Font_Metrics = new HashMap<>();
/*  39 */   public static int ms_Font_Metrics_Size = 40;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ILbsXUIWizardPaneListener m_WizardPaneListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_Wizard;
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
/*     */   protected void processMouseEvent(MouseEvent e) {
/*     */     if (this.m_TabbedPaneListener != null && e.getID() == 501 && e.getButton() == 3 && e.getClickCount() > 1) {
/*     */       TabbedPaneUI ui = getUI();
/*     */       if (ui != null) {
/*     */         int index = ui.tabForCoordinate(this, e.getX(), e.getY());
/*     */         if (index >= 0) {
/*     */           e.consume();
/*     */           if (this.m_TabbedPaneListener.closeTab(this, index)) {
/*     */             removeTabAt(index);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     super.processMouseEvent(e);
/*     */   }
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
/*     */   public ILbsTabbedPaneListener getTabbedPaneListener() {
/*     */     return this.m_TabbedPaneListener;
/*     */   }
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
/*     */   public void setTabbedPaneListener(ILbsTabbedPaneListener listener) {
/*     */     this.m_TabbedPaneListener = listener;
/*     */   }
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
/*     */   public Component getComponentAt(int x, int y) {
/*     */     if (!contains(x, y)) {
/*     */       return null;
/*     */     }
/*     */     synchronized (getTreeLock()) {
/*     */       int i;
/*     */       for (i = 0; i < getComponentCount(); i++) {
/*     */         Component comp = getComponent(i);
/*     */         if (comp != null && comp.isVisible() && comp.contains(x - comp.getX(), y - comp.getY())) {
/*     */           return comp;
/*     */         }
/*     */       } 
/*     */       for (i = 0; i < getComponentCount(); i++) {
/*     */         Component comp = getComponent(i);
/*     */         if (comp != null && comp.isVisible() && comp.contains(x - comp.getX(), y - comp.getY())) {
/*     */           return comp;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAll() {
/*     */     super.removeAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTabAt(int index) {
/*     */     if (getComponent(index) instanceof JLbsTabPage) {
/*     */       JLbsTabPage page = (JLbsTabPage)getComponent(index);
/*     */       int tag = JLbsComponentHelper.getTag((ILbsComponentBase)page);
/*     */       if (tag >= 4000000 && tag < 4000010) {
/*     */         setSelectedIndex(index);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     super.removeTabAt(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*     */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*     */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int index) {
/*     */     int selected = getSelectedIndex();
/*     */     super.setSelectedIndex(index);
/*     */     if (index != selected && selected >= 0) {
/*     */       recordSetSelectedIndex(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doSetSelectedIndex(int index, int actionID) {
/*     */     final int mActionID = actionID;
/*     */     final int mIndex = index;
/*     */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run() {
/*     */             try {
/*     */               JLbsTabbedPane.this.setSelectedIndex(mIndex);
/*     */             } catch (Exception e) {
/*     */               JLbsComponentHelper.statusChanged(1, mActionID, "Index " + mIndex + " could not be set!");
/*     */               return;
/*     */             } 
/*     */             if (JLbsTabbedPane.this.isSelectedIndexChanged(mIndex)) {
/*     */               JLbsComponentHelper.statusChanged(4, mActionID, "Set Selected Index succedded.");
/*     */             } else {
/*     */               JLbsComponentHelper.statusChanged(1, mActionID, "Index " + mIndex + " could not be set!");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTabbedPane() {
/* 253 */     this.m_Wizard = false; enableEvents(16L);
/*     */   } public void doRequestFocus(int actionID) { final int mActionID = actionID;
/*     */     SwingUtilities.invokeLater(new Runnable() { public void run() { JLbsTabbedPane.this.requestFocus();
/*     */             JLbsComponentHelper.statusChanged(4, mActionID, null); } }
/* 257 */       ); } public boolean isWizardMode() { return this.m_Wizard; }
/*     */   private boolean isSelectedIndexChanged(int anIndex) { if (anIndex == getSelectedIndex())
/*     */       return true; 
/*     */     return false; }
/*     */   public boolean isFocusOwner() { return super.isFocusOwner(); }
/* 262 */   public void recordSetSelectedIndex(int index) { JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "TABBEDPANE_SET_SELECTED_INDEX"); } public void setWizardMode(boolean wizardMode) { this.m_Wizard = true; }
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */   public void recordMouseClicked(MouseEvent evt) {} public void recordActionPerformed(ActionEvent evt) {} public void recordRequestFocus() { JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS"); } public boolean canHaveLayoutManager() { return false; } public Object getParentComponent() {
/*     */     return getParent();
/*     */   } public boolean canGoBack() throws Exception {
/* 267 */     return (getSelectedIndex() > 0 && (
/* 268 */       this.m_WizardPaneListener == null || this.m_WizardPaneListener.wizardCanGoBack(this, getSelectedIndex()))); } public int getSelectedPageTag() { Component comp = getSelectedComponent(); if (comp instanceof ILbsTabPage)
/*     */       return ((ILbsTabPage)comp).getUniqueIdentifier();  return -1; }
/*     */   public ILbsTabPage getSelectedPage() { Component comp = getSelectedComponent(); if (comp instanceof ILbsTabPage)
/*     */       return (ILbsTabPage)comp;  return null; }
/*     */   public void setWizardPaneListener(ILbsXUIWizardPaneListener listener) { this.m_WizardPaneListener = listener; }
/* 273 */   public boolean canGoNext() throws Exception { return (getSelectedIndex() < getTabCount() - 1 && (
/* 274 */       this.m_WizardPaneListener == null || this.m_WizardPaneListener.wizardCanGoNext(this, getSelectedIndex()))); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGoFinish() throws Exception {
/* 279 */     if (this.m_WizardPaneListener == null || this.m_WizardPaneListener.wizardCanGoFinish(this, getSelectedIndex()))
/* 280 */       return true; 
/* 281 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wizardFinish(boolean cancelled) throws Exception {
/* 286 */     if (this.m_WizardPaneListener == null || this.m_WizardPaneListener.wizardFinish(this, cancelled))
/* 287 */       return true; 
/* 288 */     return false;
/*     */   }
/*     */   
/*     */   protected FontMetrics getFontMetrics() {
/* 292 */     Font font = getFont();
/* 293 */     return getFontMetrics(font);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontMetrics getFontMetrics(Font font) {
/* 299 */     FontMetrics fontMetrics = null;
/* 300 */     String key = String.valueOf(font.getName()) + "_" + font.getStyle() + "_" + font.getSize();
/* 301 */     fontMetrics = ms_Font_Metrics.get(key);
/* 302 */     if (fontMetrics != null)
/* 303 */       return fontMetrics; 
/* 304 */     synchronized (ms_Font_Metrics) {
/*     */       
/* 306 */       if (ms_Font_Metrics.keySet().size() >= ms_Font_Metrics_Size)
/*     */       {
/* 308 */         ms_Font_Metrics = new HashMap<>();
/*     */       }
/* 310 */       if (fontMetrics == null) {
/*     */         
/* 312 */         fontMetrics = context.getFontMetrics(font);
/* 313 */         ms_Font_Metrics.put(key, fontMetrics);
/*     */       } 
/*     */     } 
/* 316 */     return fontMetrics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean bEnable) {
/* 323 */     super.setEnabled(bEnable);
/* 324 */     int iCompCount = getComponentCount();
/* 325 */     for (int i = 0; i < iCompCount; i++) {
/* 326 */       getComponent(i).setEnabled(bEnable);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTabPage(String title, ILbsComponent tabPage) {
/* 332 */     if (tabPage instanceof Component)
/*     */     {
/* 334 */       addTab(title, (Component)tabPage);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(ILbsComponent component) {
/* 340 */     int index = -1;
/* 341 */     Component[] components = getComponents();
/* 342 */     for (int i = 0; i < components.length; i++) {
/*     */       
/* 344 */       if (components[i] instanceof ILbsComponent && components[i] == component) {
/*     */         
/* 346 */         index = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 350 */     if (index > -1) {
/* 351 */       removeTabAt(index);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addTab(String title, ILbsTabPage tabPage) {
/* 356 */     addTab(title, (Component)tabPage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertTab(String title, Icon icon, Object component, String tip, int index) {
/* 362 */     if (component instanceof Component)
/*     */     {
/* 364 */       insertTab(title, icon, (Component)component, tip, index);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsTabbedPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */