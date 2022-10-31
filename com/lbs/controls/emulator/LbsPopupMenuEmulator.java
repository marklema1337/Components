/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsSubMenu;
/*     */ import com.lbs.controls.menu.ILbsCustomPopupMenuItem;
/*     */ import com.lbs.controls.menu.ILbsInternalPopupMenu;
/*     */ import com.lbs.controls.menu.ILbsMenuFilter;
/*     */ import com.lbs.controls.menu.ILbsSystemMenuListener;
/*     */ import com.lbs.controls.menu.LbsPopupMenuImplementor;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
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
/*     */ public class LbsPopupMenuEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsInternalPopupMenu
/*     */ {
/*     */   private LbsPopupMenuImplementor m_Implementor;
/*  32 */   private ILbsComponent m_Invoker = null;
/*  33 */   private String m_Label = null;
/*     */ 
/*     */   
/*     */   public LbsPopupMenuEmulator() {
/*  37 */     this.m_Implementor = new LbsPopupMenuImplementor(this);
/*  38 */     this.m_Implementor.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenuItem createItem(ILbsPopupMenu rootMenu, String s, int id, int index) {
/*  43 */     return new LbsMenuItemEmulator(rootMenu, s, id, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsSubMenu createSubMenu(String s) {
/*  49 */     return new LbsSubMenuEmulator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object item) {
/*  54 */     add((LbsComponentEmulator)item, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object createSeparator() {
/*  59 */     return new LbsMenuItemEmulator();
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(String s, int id) {
/*  64 */     return this.m_Implementor.add(s, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/*  69 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSeparator() {
/*  74 */     this.m_Implementor.addSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/*  79 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionListener getActionListener() {
/*  84 */     return this.m_Implementor.getActionListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getInvokerControl() {
/*  89 */     return this.m_Invoker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvoker(ILbsComponent invoker) {
/*  94 */     this.m_Invoker = invoker;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/*  99 */     return this.m_Implementor.getItemsSList();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 104 */     return this.m_Label;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMenuItemCount() {
/* 109 */     return this.m_Implementor.getMenuItemCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getMenuItems() {
/* 114 */     return this.m_Implementor.getMenuItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsSystemMenuListener getSystemMenuListener() {
/* 119 */     return this.m_Implementor.getSystemMenuListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 124 */     return this.m_Implementor.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int insert(int index, String s, int id) {
/* 129 */     return this.m_Implementor.insert(index, s, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDynamicMenuExtensionEnabled() {
/* 134 */     return this.m_Implementor.isDynamicMenuExtensionEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 139 */     return this.m_Implementor.isItemWTagEnabled(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagVisible(int tag) {
/* 144 */     return this.m_Implementor.isItemWTagVisible(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener actionListener) {
/* 149 */     this.m_Implementor.setActionListener(actionListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDynamicMenuExtensionEnabled(boolean dynamicMenuExtensionEnabled) {
/* 154 */     this.m_Implementor.setDynamicMenuExtensionEnabled(dynamicMenuExtensionEnabled);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(int index, Icon icon) {}
/*     */ 
/*     */   
/*     */   public void setItemEnabled(int index, boolean enabled) {
/* 163 */     if (index >= 0 && index < getMenuItemCount()) {
/*     */       
/* 165 */       ILbsPopupMenuItem item = this.m_Implementor.getItems().get(index);
/* 166 */       item.setEnabled(enabled);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemFilter(ILbsMenuFilter filter) {
/* 172 */     this.m_Implementor.setItemFilter(filter);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemVisible(int index, boolean visible) {
/* 177 */     if (index >= 0 && index < getMenuItemCount()) {
/*     */       
/* 179 */       ILbsPopupMenuItem item = this.m_Implementor.getItems().get(index);
/* 180 */       item.setVisible(visible);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLabel(String label) {
/* 186 */     this.m_Label = label;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSystemMenuListener(ILbsSystemMenuListener systemMenuListener) {
/* 191 */     this.m_Implementor.setSystemMenuListener(systemMenuListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectItemByTag(int tag) {
/* 196 */     removeAll();
/* 197 */     this.m_Implementor.preparePopup();
/* 198 */     this.m_Implementor.selectItemByTag(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getPopupParent() {
/* 204 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getShowingItemList() {
/* 210 */     return this.m_Implementor.getShowingItemsSList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsCustomPopupMenuItem getCustomMenuItemListener() {
/* 217 */     return this.m_Implementor.getCustomMenuItemListener();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomMenuItemListener(ILbsCustomPopupMenuItem customMenuItemListener) {
/* 223 */     this.m_Implementor.setCustomMenuItemListener(customMenuItemListener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getFocusOwner() {
/* 231 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void open() {
/* 237 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsPopupMenuEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */