/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsInvokeHandler;
/*     */ import com.lbs.control.interfaces.ILbsWindow;
/*     */ import com.lbs.control.interfaces.ILbsWindowListener;
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
/*     */ public class LbsFocusManager
/*     */ {
/*  19 */   private LbsComponentEmulator m_FocusOwner = null;
/*  20 */   private ILbsWindow m_FocusOwnerWindow = null;
/*  21 */   private LbsTabPageEmulator m_FocusedTabPage = null;
/*     */ 
/*     */   
/*     */   private ILbsWindowListener m_WindowListener;
/*     */ 
/*     */   
/*     */   private ILbsInvokeHandler m_InvokeHandler;
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getFocusOwner() {
/*  32 */     return (ILbsComponent)this.m_FocusOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsWindow getFocusOwnerWindow() {
/*  37 */     return this.m_FocusOwnerWindow;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusOwner(LbsComponentEmulator component, boolean temporary) {
/*  42 */     ILbsWindow parent = getParentWindow((ILbsComponent)component);
/*  43 */     if (this.m_FocusOwnerWindow != null && this.m_FocusOwnerWindow.isModal() && !parent.isModal() && this.m_FocusOwnerWindow != parent)
/*  44 */       throw new LbsFocusException("There is a modal focused window with title '" + this.m_FocusOwnerWindow.getTitle() + 
/*  45 */           "'! Focus cannot be traversed to another window, if there is a modal focused window."); 
/*  46 */     LbsComponentEmulator focusLostComp = this.m_FocusOwner;
/*  47 */     this.m_FocusOwner = component;
/*  48 */     ILbsWindow focusLostWindow = this.m_FocusOwnerWindow;
/*  49 */     this.m_FocusOwnerWindow = parent;
/*     */     
/*  51 */     if (focusLostComp != component) {
/*     */       
/*  53 */       fireFocusLost(temporary, focusLostComp, component);
/*  54 */       fireFocusGained(component, temporary, focusLostComp, focusLostWindow, parent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void fireFocusGained(LbsComponentEmulator component, boolean temporary, LbsComponentEmulator focusLostComp, ILbsWindow focusLostWindow, ILbsWindow focusWindow) {
/*  61 */     if (focusLostWindow != focusWindow && this.m_WindowListener != null) {
/*  62 */       this.m_WindowListener.focusedWindowChanged(focusLostWindow, focusWindow);
/*     */     }
/*     */     
/*  65 */     LbsTabPageEmulator tabPage = getTabPage(component);
/*  66 */     if (tabPage != null)
/*     */     {
/*  68 */       if (this.m_FocusedTabPage != tabPage) {
/*     */         
/*  70 */         LbsTabbedPaneEmulator tabPane = (LbsTabbedPaneEmulator)tabPage.getParentComponent();
/*  71 */         if (tabPane != null)
/*     */         {
/*  73 */           for (int i = 0; i < tabPane.getTabCount(); i++) {
/*     */             
/*  75 */             Object o = tabPane.getPageAt(i);
/*  76 */             if (o == tabPage) {
/*     */               
/*  78 */               tabPane.setSelectedIndex(i);
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*  85 */     this.m_FocusedTabPage = tabPage;
/*     */     
/*  87 */     LbsFocusEvent e = new LbsFocusEvent((ILbsComponent)component, 1004, temporary, (ILbsComponent)focusLostComp);
/*  88 */     component.processFocusEvent(e);
/*     */   }
/*     */ 
/*     */   
/*     */   private void fireFocusLost(boolean temporary, LbsComponentEmulator focusLostComp, LbsComponentEmulator oppositeComp) {
/*  93 */     if (focusLostComp != null) {
/*     */       
/*  95 */       LbsFocusEvent e = new LbsFocusEvent((ILbsComponent)focusLostComp, 1005, temporary, (ILbsComponent)oppositeComp);
/*  96 */       focusLostComp.processFocusEvent(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearFocusOwner() {
/* 102 */     LbsComponentEmulator focusLostComp = this.m_FocusOwner;
/* 103 */     if (focusLostComp != null)
/*     */     {
/* 105 */       if (focusLostComp.getLbsInputVerifier() != null)
/* 106 */         focusLostComp.getLbsInputVerifier().verify((ILbsComponent)focusLostComp); 
/*     */     }
/* 108 */     this.m_FocusOwner = null;
/* 109 */     this.m_FocusedTabPage = null;
/* 110 */     fireFocusLost(false, focusLostComp, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearFocusOwnerWindow() {
/* 115 */     this.m_FocusOwnerWindow = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsWindow getParentWindow(ILbsComponent component) {
/* 120 */     Object parent = component.getParentComponent();
/* 121 */     while (parent != null) {
/*     */       
/* 123 */       if (parent instanceof ILbsWindow)
/* 124 */         return (ILbsWindow)parent; 
/* 125 */       if (parent instanceof ILbsComponent) {
/* 126 */         parent = ((ILbsComponent)parent).getParentComponent();
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private LbsTabPageEmulator getTabPage(LbsComponentEmulator component) {
/* 136 */     Object parent = component.getParentComponent();
/* 137 */     while (parent != null) {
/*     */       
/* 139 */       if (parent instanceof LbsTabPageEmulator)
/* 140 */         return (LbsTabPageEmulator)parent; 
/* 141 */       if (parent instanceof ILbsComponent) {
/* 142 */         parent = ((ILbsComponent)parent).getParentComponent();
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWindowListener(ILbsWindowListener windowListener) {
/* 152 */     this.m_WindowListener = windowListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsWindowListener getWindowListener() {
/* 157 */     return this.m_WindowListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsInvokeHandler getInvokeHandler() {
/* 162 */     return this.m_InvokeHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvokeHandler(ILbsInvokeHandler invokeHandler) {
/* 167 */     this.m_InvokeHandler = invokeHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LbsFocusException
/*     */     extends RuntimeException
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */     
/*     */     public LbsFocusException() {}
/*     */ 
/*     */     
/*     */     public LbsFocusException(String message, Throwable cause) {
/* 182 */       super(message, cause);
/*     */     }
/*     */ 
/*     */     
/*     */     public LbsFocusException(String message) {
/* 187 */       super(message);
/*     */     }
/*     */ 
/*     */     
/*     */     public LbsFocusException(Throwable cause) {
/* 192 */       super(cause);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsFocusManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */