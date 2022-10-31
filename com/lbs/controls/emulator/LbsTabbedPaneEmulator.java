/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsTabPage;
/*     */ import com.lbs.control.interfaces.ILbsTabbedPane;
/*     */ import com.lbs.controls.ILbsTabbedPaneListener;
/*     */ import com.lbs.controls.wizard.xui.ILbsXUIWizardPaneListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ public class LbsTabbedPaneEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsTabbedPane
/*     */ {
/*     */   private ILbsTabbedPaneListener m_Listener;
/*  30 */   private ArrayList m_Listeners = new ArrayList();
/*     */   
/*  32 */   private int m_SelectedIdx = 0;
/*  33 */   private Vector m_Pages = new Vector();
/*     */   
/*  35 */   private int m_IndexFraction = 0;
/*     */ 
/*     */   
/*     */   public void addChangeListener(ChangeListener listener) {
/*  39 */     if (listener == null)
/*     */       return; 
/*  41 */     this.m_Listeners.add(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/*  46 */     return this.m_SelectedIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsTabPage getSelectedPage() {
/*  51 */     if (this.m_SelectedIdx >= 0 && this.m_SelectedIdx < getTabCount())
/*  52 */       return this.m_Pages.get(this.m_SelectedIdx); 
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPage(ILbsTabPage page) {
/*  58 */     this.m_Pages.add(page);
/*  59 */     if (page instanceof LbsComponentEmulator) {
/*  60 */       add((LbsComponentEmulator)page, -1);
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsTabPage getPageAt(int index) {
/*  65 */     return this.m_Pages.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedPageTag() {
/*  70 */     ILbsTabPage page = getSelectedPage();
/*  71 */     if (page != null)
/*  72 */       return page.getUniqueIdentifier(); 
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabCount() {
/*  78 */     return this.m_Pages.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabLayoutPolicy() {
/*  83 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsTabbedPaneListener getTabbedPaneListener() {
/*  88 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitleAt(int tabIndex) {
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabledAt(int index) {
/*  98 */     ILbsTabPage page = (ILbsTabPage)getChildAt(index);
/*  99 */     return page.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(ChangeListener l) {
/* 104 */     if (l == null) {
/*     */       return;
/*     */     }
/* 107 */     this.m_Listeners.remove(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTabAt(int index) {
/* 112 */     checkIndex(index);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     int tabCount = getTabCount();
/* 118 */     int selected = getSelectedIndex();
/* 119 */     if (selected >= tabCount - 1)
/*     */     {
/* 121 */       setSelectedIndex(selected - 1);
/*     */     }
/*     */     
/* 124 */     ILbsComponent component = (ILbsComponent)getChildAt(index);
/*     */     
/* 126 */     this.m_Pages.removeElementAt(index);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     if (component != null)
/*     */     {
/* 133 */       for (int i = getComponentCount(); --i >= 0;) {
/*     */         
/* 135 */         if (getChildAt(i) == component) {
/*     */           
/* 137 */           remove(i);
/* 138 */           component.setVisible(true);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 144 */     revalidate();
/* 145 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabledAt(int index, boolean enabled) {
/* 151 */     ILbsTabPage page = this.m_Pages.get(index);
/* 152 */     page.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int tabIndex) {
/* 157 */     if (tabIndex != -1)
/*     */     {
/* 159 */       checkIndex(tabIndex);
/*     */     }
/* 161 */     if (this.m_SelectedIdx != tabIndex) {
/*     */       
/* 163 */       this.m_SelectedIdx = tabIndex;
/* 164 */       fireStateChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireStateChanged() {
/* 170 */     for (int i = this.m_Listeners.size() - 1; i >= 0; i--)
/*     */     {
/* 172 */       ((ChangeListener)this.m_Listeners.get(i)).stateChanged(new ChangeEvent(this));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkIndex(int index) {
/* 178 */     if (index < 0 || index >= this.m_Pages.size())
/*     */     {
/* 180 */       throw new IndexOutOfBoundsException("Index: " + index + ", Tab count: " + this.m_Pages.size());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTabLayoutPolicy(int tabLayoutPolicy) {}
/*     */ 
/*     */   
/*     */   public void setTabbedPaneListener(ILbsTabbedPaneListener listener) {
/* 190 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitleAt(int tabIndex, String title) {}
/*     */ 
/*     */   
/*     */   private boolean m_Wizard = false;
/*     */ 
/*     */   
/*     */   public void setWizardMode(boolean wizardMode) {
/* 202 */     this.m_Wizard = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWizardMode() {
/* 207 */     return this.m_Wizard;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWizardPaneListener(ILbsXUIWizardPaneListener listener) {}
/*     */   
/*     */   public boolean canGoBack() throws Exception {
/* 214 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canGoNext() throws Exception {
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canGoFinish() throws Exception {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public boolean wizardFinish(boolean cancelled) throws Exception {
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTabPage(String title, ILbsComponent tabPage) {
/* 232 */     if (tabPage instanceof ILbsTabPage) {
/* 233 */       addPage((ILbsTabPage)tabPage);
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(ILbsComponent component) {
/* 238 */     int index = -1;
/* 239 */     if (component != null)
/*     */     {
/* 241 */       for (int i = getComponentCount(); --i >= 0;) {
/*     */         
/* 243 */         if (getChildAt(i) == component) {
/*     */           
/* 245 */           index = i;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 251 */     if (index > -1) {
/* 252 */       removeTabAt(index);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTab(String title, ILbsTabPage tabPage) {
/* 258 */     addPage(tabPage);
/* 259 */     tabPage.setCaption(title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertTab(String title, Icon icon, Object component, String tip, int index) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getComponentAt(int index) {
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexFraction() {
/* 276 */     return this.m_IndexFraction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexFraction(int indexFraction) {
/* 281 */     this.m_IndexFraction = indexFraction;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsTabbedPaneEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */