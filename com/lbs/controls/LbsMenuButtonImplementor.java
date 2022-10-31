/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsButton;
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.control.interfaces.ILbsMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.controls.menu.ILbsMenuFilter;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsMenuButtonImplementor
/*     */   implements ActionListener, ILbsMenuFilter
/*     */ {
/*     */   private ILbsInternalMenuButton m_Component;
/*     */   protected ILbsImageButton m_mainButton;
/*     */   protected ILbsImageButton m_popupButton;
/*     */   private ILbsPopupMenu m_popup;
/*     */   private JLbsStringList m_Items;
/*     */   private int m_actionIndex;
/*     */   private int m_actionId;
/*     */   private ILbsMenuButtonListener m_listener;
/*     */   
/*     */   public LbsMenuButtonImplementor(ILbsInternalMenuButton component) {
/*  48 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  53 */     this.m_popupButton = this.m_Component.createPopupButton();
/*     */ 
/*     */     
/*  56 */     if (this.m_Component instanceof ActionListener) {
/*  57 */       this.m_popupButton.addActionListener((ActionListener)this.m_Component);
/*     */     } else {
/*  59 */       this.m_popupButton.addActionListener(this);
/*  60 */     }  if (this.m_Component instanceof MouseListener)
/*  61 */       this.m_popupButton.addMouseListener((MouseListener)this.m_Component); 
/*  62 */     this.m_popupButton.setPreferredSize(new Dimension(12, 12));
/*     */     
/*  64 */     this.m_actionIndex = 0;
/*     */     
/*  66 */     this.m_popup = this.m_Component.createPopupMenu();
/*  67 */     if (this.m_Component instanceof ActionListener) {
/*  68 */       this.m_popup.setActionListener((ActionListener)this.m_Component);
/*     */     } else {
/*  70 */       this.m_popup.setActionListener(this);
/*  71 */     }  if (this.m_Component instanceof ILbsMenuFilter) {
/*  72 */       this.m_popup.setItemFilter((ILbsMenuFilter)this.m_Component);
/*     */     } else {
/*  74 */       this.m_popup.setItemFilter(this);
/*  75 */     }  this.m_popup.setDynamicMenuExtensionEnabled(false);
/*     */     
/*  77 */     setIcon(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(Icon main) {
/*  83 */     if (this.m_mainButton == null) {
/*     */       
/*  85 */       this.m_mainButton = this.m_Component.createMainButton();
/*  86 */       if (this.m_Component instanceof MouseListener)
/*  87 */         this.m_mainButton.addMouseListener((MouseListener)this.m_Component); 
/*  88 */       if (this.m_Component instanceof ActionListener) {
/*  89 */         this.m_mainButton.addActionListener((ActionListener)this.m_Component);
/*     */       } else {
/*  91 */         this.m_mainButton.addActionListener(this);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  96 */     this.m_mainButton.setIcon(main);
/*     */     
/*  98 */     this.m_mainButton.setToolTipText(this.m_popupButton.getToolTipText());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHighlightIcon(Icon main) {
/* 103 */     this.m_mainButton.setHighlightIcon(main);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPopupButton(ILbsImageButton button) {
/* 108 */     return (button == this.m_popupButton);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/* 113 */     if (this.m_mainButton != null)
/* 114 */       this.m_mainButton.reinitialize(); 
/* 115 */     if (this.m_popupButton != null) {
/* 116 */       this.m_popupButton.reinitialize();
/*     */     }
/*     */   }
/*     */   
/*     */   public Icon getIcon() {
/* 121 */     if (this.m_mainButton != null) {
/*     */       
/* 123 */       Icon icon = this.m_mainButton.getIcon();
/* 124 */       return icon;
/*     */     } 
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList actions, boolean clear) {
/* 131 */     if (actions != null) {
/*     */       
/* 133 */       if (clear)
/*     */       {
/* 135 */         this.m_popup.clearItems();
/*     */       }
/* 137 */       if (clear || this.m_Items == null) {
/* 138 */         this.m_Items = new JLbsStringList(actions);
/*     */       } else {
/* 140 */         this.m_Items.addAll(actions);
/* 141 */       }  this.m_popup.addItems(actions);
/* 142 */       JLbsStringListItem first = actions.getAt(0);
/* 143 */       if (first != null) {
/* 144 */         this.m_actionIndex = first.Tag;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setActions(JLbsStringList actions) {
/* 150 */     this.m_Component.setActions(actions, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuButtonListener(ILbsMenuButtonListener listener) {
/* 155 */     this.m_listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMenuButtonListener getMenuButtonListener() {
/* 160 */     return this.m_listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPerformAction(int actionID) {
/* 165 */     this.m_actionIndex = actionID;
/* 166 */     if (this.m_listener != null) {
/* 167 */       this.m_listener.actionPerformed(this.m_Component, actionID);
/*     */     }
/*     */   }
/*     */   
/*     */   public void doClick() {
/* 172 */     if (this.m_listener != null) {
/* 173 */       this.m_listener.popupButtonPressed(this.m_Component);
/*     */     }
/*     */   }
/*     */   
/*     */   public void doMainButtonClick() {
/* 178 */     if (this.m_mainButton != null) {
/* 179 */       this.m_mainButton.doClick();
/*     */     }
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/* 184 */     Object source = evt.getSource();
/*     */     
/* 186 */     if (source instanceof ILbsPopupMenuItem) {
/*     */       
/* 188 */       int actionID = ((ILbsPopupMenuItem)source).getId();
/* 189 */       doPerformAction(actionID);
/* 190 */       this.m_Component.repaint();
/*     */     
/*     */     }
/* 193 */     else if (source instanceof ILbsButton) {
/*     */       
/* 195 */       ILbsButton button = (ILbsButton)source;
/* 196 */       if (button == this.m_popupButton) {
/*     */         
/* 198 */         this.m_Component.doClick();
/*     */       }
/* 200 */       else if (this.m_listener != null) {
/*     */ 
/*     */         
/* 203 */         if (this.m_popup != null)
/*     */         {
/*     */           
/* 206 */           if (selectAvailableAction() == -1) {
/*     */             return;
/*     */           }
/*     */         }
/* 210 */         this.m_Component.putClientProperty("Event", evt);
/* 211 */         this.m_listener.actionPerformed(this.m_Component, this.m_actionIndex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int selectAvailableAction() {
/* 219 */     if ((!this.m_popup.isItemWTagVisible(this.m_actionIndex) || !this.m_popup.isItemWTagEnabled(this.m_actionIndex)) && this.m_Items != null) {
/*     */ 
/*     */       
/* 222 */       int i = 0;
/* 223 */       for (; i < this.m_Items.size(); i++) {
/*     */         
/* 225 */         int tag = this.m_Items.getTagAt(i);
/* 226 */         if (this.m_popup.isItemWTagVisible(tag) && this.m_popup.isItemWTagEnabled(tag)) {
/*     */           
/* 228 */           this.m_actionIndex = tag;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 234 */       if (i == this.m_Items.size())
/* 235 */         return -1; 
/*     */     } 
/* 237 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionIndex() {
/* 242 */     return this.m_actionIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionIndex(int index) {
/* 247 */     this.m_actionIndex = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemVisible(ILbsMenuItem item) {
/* 252 */     if (this.m_listener != null) {
/* 253 */       return this.m_listener.menuButtonActionFiltered(this.m_Component, item.getTag());
/*     */     }
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String hint) {
/* 260 */     if (this.m_mainButton != null)
/* 261 */       this.m_mainButton.setToolTipText(hint); 
/* 262 */     this.m_popupButton.setToolTipText(hint);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 267 */     if (this.m_mainButton != null)
/* 268 */       return this.m_mainButton.getToolTipText(); 
/* 269 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemEnabled(ILbsMenuItem item) {
/* 274 */     if (this.m_listener != null) {
/* 275 */       return this.m_listener.menuButtonActionValid(this.m_Component, item.getTag());
/*     */     }
/* 277 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addKeyListener(KeyListener listener) {
/* 282 */     if (this.m_mainButton != null)
/* 283 */       this.m_mainButton.addKeyListener(listener); 
/* 284 */     if (this.m_popupButton != null) {
/* 285 */       this.m_popupButton.addKeyListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void addMouseListener(MouseListener listener) {
/* 290 */     if (this.m_mainButton != null)
/* 291 */       this.m_mainButton.addMouseListener(listener); 
/* 292 */     if (this.m_popupButton != null) {
/* 293 */       this.m_popupButton.addMouseListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void putClientPropertyInternal(Object property, Object value) {
/* 298 */     if (this.m_mainButton != null)
/* 299 */       this.m_mainButton.putClientProperty(property, value); 
/* 300 */     if (this.m_popupButton != null) {
/* 301 */       this.m_popupButton.putClientProperty(property, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 306 */     if (this.m_popup != null) {
/* 307 */       return this.m_popup.hasItemTag(tag);
/*     */     }
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 314 */     if (this.m_popup != null) {
/* 315 */       return this.m_popup.isItemWTagEnabled(tag);
/*     */     }
/* 317 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 322 */     return new JLbsStringList(this.m_Items);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu getPopupControl() {
/* 327 */     return this.m_popup;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionId() {
/* 332 */     return this.m_actionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int actionId) {
/* 337 */     this.m_actionId = actionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 342 */     this.m_mainButton.setEnabled(enabled);
/* 343 */     this.m_popupButton.setEnabled(enabled);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\LbsMenuButtonImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */