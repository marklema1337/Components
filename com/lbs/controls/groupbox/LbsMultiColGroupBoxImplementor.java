/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsAbstractButton;
/*     */ import com.lbs.control.interfaces.ILbsCheckBoxGroup;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.emulator.LbsFocusEvent;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.JComponent;
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
/*     */ public class LbsMultiColGroupBoxImplementor
/*     */   implements ActionListener, FocusListener
/*     */ {
/*     */   protected ILbsInternalMultiColGroupBox m_Component;
/*  41 */   protected int m_iColumns = 0;
/*  42 */   private int m_PrevMask = 0;
/*  43 */   protected HashMap m_HashMap = new HashMap<>();
/*     */   
/*     */   protected boolean m_ContainerFocus = false;
/*  46 */   protected FocusListener m_FocusListener = null;
/*     */   
/*     */   private ILbsButtonGroupListener m_Listener;
/*     */   
/*     */   private JLbsStringList m_Items;
/*     */   private JLbsStringList m_VisibleItems;
/*     */   
/*     */   public LbsMultiColGroupBoxImplementor(ILbsInternalMultiColGroupBox component) {
/*  54 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  59 */     this.m_Component.setColumnCount(2);
/*  60 */     this.m_Component.setFocusable(false);
/*  61 */     this.m_Items = new JLbsStringList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setColumnCount(int iColCount) {
/*  66 */     if (this.m_iColumns != iColCount) {
/*     */       
/*  68 */       this.m_iColumns = iColCount;
/*  69 */       return true;
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  76 */     return this.m_iColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value) {
/*  81 */     this.m_Component.addItem(value, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, TableLayoutConstraints constr) {
/*  86 */     this.m_Component.addItem(value, constr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag) {
/*  91 */     addItem(value, iTag, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, TableLayoutConstraints constr) {
/*  96 */     JLbsMultiColGroupBoxItem item = createItem(value, null, iTag);
/*  97 */     internalAdd(item, constr);
/*  98 */     this.m_Items.add((value != null) ? 
/*  99 */         value.toString() : 
/* 100 */         null, iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/* 105 */     if (list != null) {
/*     */       
/* 107 */       int iLen = list.size();
/* 108 */       for (int i = 0; i < iLen; i++) {
/*     */         
/* 110 */         JLbsStringListItem item = list.getAt(i);
/* 111 */         this.m_Component.addItem(item.Value, item.Tag, addTableLayoutConstraint(i));
/*     */       } 
/*     */     } 
/* 114 */     this.m_Component.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   private TableLayoutConstraints addTableLayoutConstraint(int i) {
/* 119 */     TableLayoutConstraints constr = new TableLayoutConstraints();
/* 120 */     constr.row1 = 0;
/* 121 */     constr.row2 = 0;
/* 122 */     constr.hAlign = 2;
/* 123 */     constr.vAlign = 2;
/* 124 */     constr.col1 = i;
/* 125 */     constr.col2 = i;
/* 126 */     return constr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLayout() {
/* 131 */     this.m_Component.setLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 137 */     if (this.m_Component.getComponentCount() > 0)
/* 138 */       this.m_Component.removeAll(); 
/* 139 */     this.m_HashMap.clear();
/* 140 */     this.m_Items.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 145 */     this.m_Component.clearItems();
/* 146 */     this.m_Component.addItems(list);
/* 147 */     this.m_Component.validate();
/* 148 */     this.m_Component.invalidate();
/* 149 */     this.m_Component.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemMask() {
/* 154 */     int iLen = this.m_Component.getComponentCount();
/* 155 */     int iResult = 0;
/* 156 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 158 */       Object c = this.m_Component.getChildAt(i);
/* 159 */       if (isItemSelected(c))
/* 160 */         iResult |= getComponentTag(c); 
/*     */     } 
/* 162 */     return iResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMask(int mask) {
/* 167 */     int iLen = this.m_Component.getComponentCount();
/* 168 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 170 */       Object c = this.m_Component.getChildAt(i);
/* 171 */       int tag = getComponentTag(c);
/* 172 */       setItemSelected(c, ((mask & tag) == tag));
/*     */     } 
/* 174 */     this.m_PrevMask = mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMaskWithSameTag(int mask) {
/* 179 */     int iLen = this.m_Component.getComponentCount();
/* 180 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 182 */       Object c = this.m_Component.getChildAt(i);
/* 183 */       int tag = getComponentTag(c);
/* 184 */       setItemSelected(c, (mask == tag));
/*     */     } 
/* 186 */     this.m_PrevMask = mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemArray() {
/* 191 */     int iLen = this.m_Component.getComponentCount();
/* 192 */     ArrayList<Integer> list = new ArrayList();
/* 193 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 195 */       Object c = this.m_Component.getChildAt(i);
/* 196 */       if (isItemSelected(c))
/* 197 */         list.add(Integer.valueOf(getComponentTag(c))); 
/*     */     } 
/* 199 */     int iListSize = list.size();
/* 200 */     if (iListSize > 0) {
/*     */       
/* 202 */       int[] result = new int[iListSize];
/* 203 */       for (int j = 0; j < iListSize; j++)
/* 204 */         result[j] = ((Integer)list.get(j)).intValue(); 
/* 205 */       return result;
/*     */     } 
/* 207 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemIndexes() {
/* 212 */     int iLen = this.m_Component.getComponentCount();
/* 213 */     ArrayList<Integer> list = new ArrayList();
/* 214 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 216 */       Object c = this.m_Component.getChildAt(i);
/* 217 */       if (isItemSelected(c))
/* 218 */         list.add(Integer.valueOf(i)); 
/*     */     } 
/* 220 */     int iListSize = list.size();
/* 221 */     if (iListSize > 0) {
/*     */       
/* 223 */       int[] result = new int[iListSize];
/* 224 */       for (int j = 0; j < iListSize; j++)
/* 225 */         result[j] = ((Integer)list.get(j)).intValue(); 
/* 226 */       return result;
/*     */     } 
/* 228 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemArray(int[] iList) {
/* 233 */     int iLen = this.m_Component.getComponentCount();
/* 234 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 236 */       Object c = this.m_Component.getChildAt(i);
/* 237 */       int tag = getComponentTag(c);
/* 238 */       boolean selected = false;
/* 239 */       if (iList != null)
/* 240 */         for (int j = 0; j < iList.length; j++) {
/* 241 */           if (tag == iList[j]) {
/*     */             
/* 243 */             selected = true; break;
/*     */           } 
/*     */         }  
/* 246 */       setItemSelected(c, selected);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addLbsFocusListener(FocusListener listener) {
/* 254 */     this.m_FocusListener = AWTEventMulticaster.add(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeLbsFocusListener(FocusListener listener) {
/* 259 */     this.m_FocusListener = AWTEventMulticaster.remove(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void fireLbsFocusEvent(FocusEvent event, int id) {
/* 264 */     if (this.m_FocusListener != null) {
/*     */       
/* 266 */       Object source = event.getSource();
/* 267 */       event.setSource(this.m_Component);
/*     */       
/*     */       try {
/* 270 */         if (id == 1004) {
/* 271 */           this.m_FocusListener.focusGained(event);
/* 272 */         } else if (id == 1005) {
/* 273 */           this.m_FocusListener.focusLost(event);
/*     */         } 
/*     */       } finally {
/*     */         
/* 277 */         event.setSource(source);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComponentTag(Object c) {
/*     */     try {
/* 286 */       Object obj = this.m_HashMap.get(c);
/* 287 */       if (obj instanceof Integer) {
/* 288 */         return ((Integer)obj).intValue();
/*     */       }
/* 290 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 293 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent event) {
/* 298 */     Object source = event.getSource();
/* 299 */     Object opposite = event.getOppositeComponent();
/* 300 */     if (event instanceof LbsFocusEvent) {
/* 301 */       opposite = ((LbsFocusEvent)event).getOpposite();
/*     */     }
/* 303 */     if (isComponent(source) && isComponent(opposite) && this.m_Component instanceof JComponent) {
/*     */       
/* 305 */       Component comp = (Component)source;
/* 306 */       Component target = (Component)opposite;
/* 307 */       if (JLbsControlHelper.isChildComponent((JComponent)this.m_Component, comp) && 
/* 308 */         !JLbsControlHelper.isChildComponent((JComponent)this.m_Component, target)) {
/*     */         
/* 310 */         fireLbsFocusEvent(event, event.getID());
/*     */         return;
/*     */       } 
/*     */     } 
/* 314 */     if (isLbsComponent(source) && isLbsComponent(opposite)) {
/*     */       
/* 316 */       ILbsComponent comp = (ILbsComponent)source;
/* 317 */       ILbsComponent target = (ILbsComponent)opposite;
/* 318 */       if (JLbsControlHelper.isChildComponent(this.m_Component, comp) && !JLbsControlHelper.isChildComponent(this.m_Component, target)) {
/* 319 */         fireLbsFocusEvent(event, event.getID());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void focusLost(FocusEvent event) {
/* 325 */     Object source = event.getSource();
/* 326 */     Object opposite = event.getOppositeComponent();
/* 327 */     if (event instanceof LbsFocusEvent) {
/* 328 */       opposite = ((LbsFocusEvent)event).getOpposite();
/*     */     }
/* 330 */     if (isComponent(source) && isComponent(opposite) && this.m_Component instanceof JComponent) {
/*     */       
/* 332 */       Component sourceComp = (Component)source;
/* 333 */       Component oppositeComp = (Component)opposite;
/* 334 */       if (!JLbsControlHelper.isChildComponent((JComponent)this.m_Component, oppositeComp) && 
/* 335 */         JLbsControlHelper.isChildComponent((JComponent)this.m_Component, sourceComp)) {
/*     */         
/* 337 */         fireLbsFocusEvent(event, event.getID());
/*     */         return;
/*     */       } 
/*     */     } 
/* 341 */     if (isLbsComponent(source) && isLbsComponent(opposite)) {
/*     */       
/* 343 */       ILbsComponent sourceComp = (ILbsComponent)source;
/* 344 */       ILbsComponent oppositeComp = (ILbsComponent)opposite;
/* 345 */       if (!JLbsControlHelper.isChildComponent(this.m_Component, oppositeComp) && 
/* 346 */         JLbsControlHelper.isChildComponent(this.m_Component, sourceComp)) {
/* 347 */         fireLbsFocusEvent(event, event.getID());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isComponent(Object o) {
/* 353 */     return !(o != null && !(o instanceof Component));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isLbsComponent(Object o) {
/* 358 */     return !(o != null && !(o instanceof ILbsComponent));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 363 */     if (this.m_Listener == null)
/*     */       return; 
/* 365 */     int newMask = this.m_Component.getSelectedItemMask();
/* 366 */     if (this.m_PrevMask != newMask) {
/*     */       
/* 368 */       Object btn = e.getSource();
/* 369 */       this.m_Listener.buttonStateChanged(new JLbsButtonStateChangeEvent(this.m_Component, btn, this.m_PrevMask, newMask, 
/* 370 */             getComponentTag(btn)));
/* 371 */       this.m_PrevMask = newMask;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsButtonGroupListener getButtonGroupListener() {
/* 377 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonGroupListener(ILbsButtonGroupListener listener) {
/* 382 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setControlEnabled(int tag, boolean enabled) {
/* 387 */     ILbsComponent comp = getControlByTag(tag);
/* 388 */     if (comp != null) {
/*     */       
/* 390 */       comp.setEnabled(enabled);
/* 391 */       return true;
/*     */     } 
/* 393 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getControlByTag(int tag) {
/*     */     try {
/* 400 */       Iterator it = this.m_HashMap.keySet().iterator();
/* 401 */       while (it.hasNext()) {
/*     */         
/* 403 */         Object o = it.next();
/* 404 */         Object value = this.m_HashMap.get(o);
/* 405 */         if (value instanceof Integer && ((Integer)value).intValue() == tag && o instanceof ILbsComponent) {
/* 406 */           return (ILbsComponent)o;
/*     */         }
/*     */       } 
/* 409 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 412 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 417 */     return new JLbsStringList(this.m_Items);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getVisibleItems() {
/* 422 */     if (this.m_VisibleItems != null) {
/* 423 */       return this.m_VisibleItems;
/*     */     }
/* 425 */     return prepareVisibleItems();
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsStringList prepareVisibleItems() {
/* 430 */     this.m_VisibleItems = new JLbsStringList();
/* 431 */     if (this.m_Items.size() > 0)
/*     */     {
/* 433 */       for (int i = 0; i < this.m_Items.size(); i++) {
/*     */         
/* 435 */         boolean visible = ((ILbsCheckBoxGroup)this.m_Component).getCheckBoxControl(i).isVisible();
/* 436 */         if (visible)
/* 437 */           this.m_VisibleItems.addItem(this.m_Items.getAt(i)); 
/*     */       } 
/*     */     }
/* 440 */     return this.m_VisibleItems;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void internalAdd(JLbsMultiColGroupBoxItem item, TableLayoutConstraints constr) {
/* 445 */     if (item != null) {
/*     */       
/* 447 */       ILbsComponent comp = item.getComponent();
/* 448 */       if (comp != null) {
/*     */         
/* 450 */         if (this.m_Component instanceof FocusListener) {
/* 451 */           comp.addFocusListener((FocusListener)this.m_Component);
/*     */         } else {
/* 453 */           comp.addFocusListener(this);
/* 454 */         }  if (comp instanceof ILbsAbstractButton) {
/*     */           
/* 456 */           ILbsAbstractButton btn = (ILbsAbstractButton)comp;
/* 457 */           if (this.m_Component instanceof ActionListener) {
/* 458 */             btn.addActionListener((ActionListener)this.m_Component);
/*     */           } else {
/* 460 */             btn.addActionListener(this);
/*     */           } 
/* 462 */         }  if (constr != null)
/* 463 */           this.m_Component.setTableLayoutConstraints(constr); 
/* 464 */         this.m_Component.addChild(comp);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsMultiColGroupBoxItem createItem(Object value, Object params, int iTag) {
/* 471 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemSelected(Object c) {
/* 476 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemSelected(Object c, boolean flag) {}
/*     */ 
/*     */   
/*     */   protected void createItemSucceeded(JLbsMultiColGroupBoxItem item) {
/* 485 */     if (item != null)
/*     */       
/*     */       try {
/* 488 */         this.m_HashMap.put(item.getComponent(), Integer.valueOf(item.getTag()));
/*     */       }
/* 490 */       catch (Exception exception) {} 
/*     */   }
/*     */   
/*     */   public void setPopupList(JLbsStringList list) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\LbsMultiColGroupBoxImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */