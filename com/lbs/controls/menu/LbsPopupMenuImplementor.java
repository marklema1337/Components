/*     */ package com.lbs.controls.menu;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsSubMenu;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
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
/*     */ public class LbsPopupMenuImplementor
/*     */ {
/*     */   private ILbsInternalPopupMenu m_Component;
/*  36 */   protected int m_iIndex = -1;
/*  37 */   protected ArrayList m_Items = new ArrayList();
/*  38 */   protected ActionListener m_ActionListener = null;
/*  39 */   protected ILbsMenuFilter m_ItemFilter = null;
/*     */   
/*     */   protected ILbsSystemMenuListener m_SystemMenuListener;
/*     */   
/*     */   protected ILbsCustomPopupMenuItem m_CustomMenuItemListener;
/*     */   
/*     */   protected boolean m_ItemsAddedToParent = false;
/*     */   private boolean m_DynamicMenuExtensionEnabled = true;
/*     */   
/*     */   public LbsPopupMenuImplementor(ILbsInternalPopupMenu component) {
/*  49 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  54 */     ILbsCultureInfo cultureInfo = JLbsLocalizer.getCultureInfo();
/*  55 */     if (cultureInfo != null) {
/*  56 */       this.m_Component.setComponentOrientation(cultureInfo.getComponentOrientation());
/*     */     }
/*     */   }
/*     */   
/*     */   public void preparePopup() {
/*  61 */     if (this.m_CustomMenuItemListener != null) {
/*     */       
/*  63 */       Object[] result = this.m_CustomMenuItemListener.getCustomMenuItems();
/*  64 */       if (result != null && result instanceof JLbsCustomPopupMenuItem[]) {
/*     */         
/*  66 */         JLbsCustomPopupMenuItem[] list = (JLbsCustomPopupMenuItem[])result;
/*  67 */         for (int i = 0; i < list.length; i++) {
/*     */           
/*  69 */           JLbsCustomPopupMenuItem item = list[i];
/*  70 */           if (item != null && !existItemWithId(item.getId()))
/*  71 */             insert(item.getIndex(), item.getCaption(), item.getId()); 
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     fillPopupMenu(null, 0, this.m_Items.size());
/*     */   }
/*     */ 
/*     */   
/*     */   private int fillPopupMenu(ILbsSubMenu parentItem, int startIndx, int endIndx) {
/*  80 */     if (startIndx >= this.m_Items.size() || endIndx > this.m_Items.size()) {
/*  81 */       return 0;
/*     */     }
/*  83 */     boolean bSepWanted = false;
/*  84 */     int submenuCount = 0;
/*  85 */     for (int i = startIndx; i < endIndx; i++) {
/*     */       
/*  87 */       Object item = this.m_Items.get(i);
/*  88 */       String itemStr = getText(item);
/*     */       
/*  90 */       boolean bInclude = (this.m_ItemFilter != null && item instanceof ILbsPopupMenuItem) ? 
/*  91 */         this.m_ItemFilter.isItemVisible((ILbsMenuItem)item) : true;
/*     */ 
/*     */       
/*  94 */       if (bInclude)
/*     */       {
/*  96 */         if (JLbsStringUtil.equals("-", itemStr)) {
/*     */           
/*  98 */           bSepWanted = true;
/*     */         }
/*     */         else {
/*     */           
/* 102 */           if (bSepWanted && isVisible(item)) {
/*     */             
/* 104 */             bSepWanted = false;
/* 105 */             if (parentItem == null) {
/* 106 */               this.m_Component.addItem(this.m_Component.createSeparator());
/*     */             } else {
/* 108 */               parentItem.addItem(this.m_Component.createSeparator());
/*     */             } 
/*     */           } 
/* 111 */           int submenuMark = itemStr.indexOf("#");
/* 112 */           if (item instanceof ILbsPopupMenuItem && submenuMark >= 0 && submenuMark < itemStr.length() - 1) {
/*     */             
/* 114 */             submenuCount = Integer.parseInt(itemStr.substring(submenuMark + 1));
/* 115 */             item = this.m_Component.createSubMenu(itemStr.substring(0, submenuMark));
/* 116 */             submenuCount += fillPopupMenu((ILbsSubMenu)item, i + 1, i + 1 + submenuCount);
/* 117 */             i += submenuCount;
/*     */           } 
/*     */           
/* 120 */           if (item instanceof JMenu)
/*     */           {
/* 122 */             for (int j = 0; j < ((JMenu)item).getPopupMenu().getComponentCount(); j++) {
/*     */               
/* 124 */               if (!((JMenu)item).getPopupMenu().getComponent(j).isVisible()) {
/* 125 */                 ((JMenu)item).setEnabled(false);
/*     */               } else {
/*     */                 
/* 128 */                 ((JMenu)item).setEnabled(true);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           }
/* 134 */           if (parentItem == null) {
/* 135 */             this.m_Component.addItem(item);
/*     */           } else {
/* 137 */             parentItem.addItem(item);
/*     */           } 
/* 139 */           if (item instanceof ILbsPopupMenuItem)
/* 140 */             ((ILbsPopupMenuItem)item).setEnabled(!(this.m_ItemFilter != null && 
/* 141 */                 !this.m_ItemFilter.isItemEnabled((ILbsMenuItem)item))); 
/*     */         } 
/*     */       }
/*     */     } 
/* 145 */     return submenuCount;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getText(Object item) {
/* 150 */     if (item instanceof JMenuItem)
/* 151 */       return ((JMenuItem)item).getText(); 
/* 152 */     if (item instanceof ILbsMenuItem)
/* 153 */       return ((ILbsMenuItem)item).getText(); 
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isVisible(Object item) {
/* 159 */     if (item instanceof JMenuItem)
/* 160 */       return ((JMenuItem)item).isVisible(); 
/* 161 */     if (item instanceof ILbsMenuItem)
/* 162 */       return ((ILbsMenuItem)item).isVisible(); 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(String s, int id) {
/* 168 */     this.m_iIndex++;
/* 169 */     if (s == null || s.length() == 0)
/* 170 */       s = "-"; 
/* 171 */     ILbsPopupMenuItem item = this.m_Component.createItem(this.m_Component, s, id, this.m_iIndex);
/*     */     
/* 173 */     item.putClientProperty("EVENT_TAG", Integer.valueOf(id));
/* 174 */     item.putClientProperty("EVENT_VALUE", "");
/* 175 */     item.setComponentOrientation(this.m_Component.getComponentOrientation());
/*     */     
/* 177 */     if (this.m_ActionListener != null) {
/* 178 */       item.addActionListener(this.m_ActionListener);
/*     */     }
/* 180 */     this.m_Items.add(item);
/* 181 */     return this.m_iIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public int insert(int index, String s, int id) {
/* 186 */     this.m_iIndex++;
/* 187 */     if (s == null || s.length() == 0)
/* 188 */       s = "-"; 
/* 189 */     ILbsPopupMenuItem item = this.m_Component.createItem(this.m_Component, s, id, index);
/* 190 */     item.setComponentOrientation(this.m_Component.getComponentOrientation());
/*     */     
/* 192 */     if (this.m_ActionListener != null) {
/* 193 */       item.addActionListener(this.m_ActionListener);
/*     */     }
/* 195 */     this.m_Items.add(index, item);
/* 196 */     return this.m_iIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean existItemWithId(int id) {
/* 202 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/* 204 */       Object item = this.m_Items.get(i);
/* 205 */       if (item instanceof ILbsPopupMenuItem) {
/*     */         
/* 207 */         ILbsPopupMenuItem menuItem = (ILbsPopupMenuItem)item;
/* 208 */         if (menuItem.getId() == id)
/* 209 */           return true; 
/*     */       } 
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSeparator() {
/* 217 */     if (this.m_ItemsAddedToParent) {
/*     */       
/* 219 */       Object separator = this.m_Component.createSeparator();
/* 220 */       if (separator instanceof ILbsComponent)
/* 221 */         ((ILbsComponent)separator).setComponentOrientation(this.m_Component.getComponentOrientation()); 
/* 222 */       if (separator instanceof JComponent)
/* 223 */         ((JComponent)separator).setComponentOrientation(this.m_Component.getComponentOrientation()); 
/* 224 */       this.m_Component.addItem(separator);
/*     */     } else {
/*     */       
/* 227 */       this.m_Component.add("-", 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/* 232 */     if (list != null) {
/*     */       
/* 234 */       int iLen = list.size();
/* 235 */       for (int i = 0; i < iLen; i++) {
/*     */         
/* 237 */         JLbsStringListItem lItem = list.getAt(i);
/* 238 */         this.m_Component.add(lItem.Value, lItem.Tag);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 245 */     if (this.m_Items != null) {
/*     */       
/* 247 */       StringBuilder buffer = new StringBuilder();
/*     */       
/* 249 */       for (int i = 0; i < this.m_Items.size(); i++) {
/*     */         
/* 251 */         Object obj = this.m_Items.get(i);
/* 252 */         if (obj instanceof ILbsPopupMenuItem) {
/*     */           
/* 254 */           ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/*     */           
/* 256 */           if (buffer.length() > 0)
/* 257 */             buffer.append("\n"); 
/* 258 */           buffer.append(item.toEditString());
/*     */         } 
/*     */       } 
/* 261 */       return buffer.toString().replaceAll("\n", "|");
/*     */     } 
/* 263 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getShowingItemsSList() {
/* 268 */     if (this.m_Items != null) {
/*     */       
/* 270 */       StringBuilder buffer = new StringBuilder();
/*     */       
/* 272 */       for (int i = 0; i < this.m_Items.size(); i++) {
/*     */         
/* 274 */         Object obj = this.m_Items.get(i);
/* 275 */         if (obj instanceof ILbsPopupMenuItem) {
/*     */           
/* 277 */           ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/*     */           
/* 279 */           if (buffer.length() > 0)
/* 280 */             buffer.append("\n"); 
/* 281 */           if ((item.isEnabled() && item.isShowing()) || (
/* 282 */             this.m_ItemFilter != null && this.m_ItemFilter.isItemVisible((ILbsMenuItem)item) && this.m_ItemFilter.isItemEnabled((ILbsMenuItem)item)))
/* 283 */             buffer.append(item.toEditString()); 
/*     */         } 
/*     */       } 
/* 286 */       return buffer.toString().replaceAll("\n", "|");
/*     */     } 
/* 288 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 293 */     this.m_Items.clear();
/* 294 */     this.m_iIndex = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener actionListener) {
/* 299 */     this.m_ActionListener = actionListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionListener getActionListener() {
/* 304 */     return this.m_ActionListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemFilter(ILbsMenuFilter listener) {
/* 309 */     this.m_ItemFilter = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 314 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/* 316 */       Object obj = this.m_Items.get(i);
/* 317 */       if (obj instanceof ILbsPopupMenuItem) {
/*     */         
/* 319 */         ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/* 320 */         if (item.getId() == tag)
/* 321 */           return true; 
/*     */       } 
/*     */     } 
/* 324 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 329 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/* 331 */       Object obj = this.m_Items.get(i);
/* 332 */       if (obj instanceof ILbsPopupMenuItem) {
/*     */         
/* 334 */         ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/* 335 */         if (item.getId() == tag)
/* 336 */           return (item.isEnabled() && (this.m_ItemFilter == null || this.m_ItemFilter.isItemEnabled((ILbsMenuItem)item))); 
/*     */       } 
/*     */     } 
/* 339 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagVisible(int tag) {
/* 344 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/* 346 */       Object obj = this.m_Items.get(i);
/* 347 */       if (obj instanceof ILbsPopupMenuItem) {
/*     */         
/* 349 */         ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/* 350 */         if (item.getId() == tag)
/* 351 */           return (item.isVisible() && (this.m_ItemFilter == null || this.m_ItemFilter.isItemVisible((ILbsMenuItem)item))); 
/*     */       } 
/*     */     } 
/* 354 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectItemByTag(int tag) {
/* 359 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/* 361 */       Object obj = this.m_Items.get(i);
/* 362 */       if (obj instanceof ILbsPopupMenuItem) {
/*     */         
/* 364 */         ILbsPopupMenuItem item = (ILbsPopupMenuItem)obj;
/* 365 */         if (item.getId() == tag) {
/*     */           
/* 367 */           item.doClick();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMenuItemCount() {
/* 376 */     return this.m_Items.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean b) {
/* 381 */     if (b) {
/*     */       
/* 383 */       ComponentOrientation o = this.m_Component.getComponentOrientation();
/* 384 */       this.m_Component.applyComponentOrientation(o);
/* 385 */       for (int i = 0; i < this.m_Items.size(); i++) {
/*     */         
/* 387 */         Object obj = this.m_Items.get(i);
/* 388 */         if (obj instanceof Component)
/*     */         {
/* 390 */           ((Component)obj).setComponentOrientation(o);
/*     */         }
/* 392 */         if (obj instanceof ILbsComponent)
/*     */         {
/* 394 */           ((ILbsComponent)obj).setComponentOrientation(o);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDynamicMenuExtensionEnabled() {
/* 402 */     return this.m_DynamicMenuExtensionEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDynamicMenuExtensionEnabled(boolean dynamicMenuExtensionEnabled) {
/* 407 */     this.m_DynamicMenuExtensionEnabled = dynamicMenuExtensionEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsSystemMenuListener getSystemMenuListener() {
/* 412 */     return this.m_SystemMenuListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSystemMenuListener(ILbsSystemMenuListener systemMenuListener) {
/* 417 */     this.m_SystemMenuListener = systemMenuListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCustomPopupMenuItem getCustomMenuItemListener() {
/* 422 */     return this.m_CustomMenuItemListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomMenuItemListener(ILbsCustomPopupMenuItem customMenuItemListener) {
/* 427 */     this.m_CustomMenuItemListener = customMenuItemListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getMenuItems() {
/* 432 */     return new ArrayList(this.m_Items);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getItems() {
/* 437 */     return this.m_Items;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\LbsPopupMenuImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */