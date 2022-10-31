/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboBox;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsInputVerifier;
/*     */ import com.lbs.controls.ILbsComboFilterListener;
/*     */ import com.lbs.controls.JLbsComboBoxItem;
/*     */ import com.lbs.controls.LbsComboBoxImplementor;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.ItemSelectable;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ImageIcon;
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
/*     */ public class LbsComboBoxEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsComboBox, ItemSelectable
/*     */ {
/*  32 */   private ArrayList m_AllItems = new ArrayList();
/*     */   
/*     */   private Object m_SelectedItem;
/*     */   private Object m_SelectedItemReminder;
/*     */   private ItemListener m_ItemListener;
/*     */   private ActionListener m_ActionListener;
/*     */   private String m_ActionCommand;
/*     */   private int m_CurrentCaretPosition;
/*  40 */   private LbsComboBoxImplementor m_Implementor = new LbsComboBoxImplementor(this);
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject) {
/*  44 */     if (anObject instanceof JLbsComboBoxItem) {
/*  45 */       this.m_AllItems.add(anObject);
/*     */     } else {
/*  47 */       this.m_AllItems.add(new JLbsComboBoxItem(anObject, 0));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addItem(Object anObject, int tag) {
/*  52 */     this.m_Implementor.addItem(anObject, tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemListener(ItemListener listener) {
/*  57 */     if (listener == null) {
/*     */       return;
/*     */     }
/*  60 */     this.m_ItemListener = AWTEventMulticaster.add(this.m_ItemListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/*  65 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/*  70 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doFilterItems() {
/*  75 */     this.m_Implementor.doFilterItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(Object value) {
/*  80 */     return this.m_Implementor.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(String value) {
/*  85 */     return this.m_Implementor.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComboFilterListener getFilterListener() {
/*  90 */     return this.m_Implementor.getFilterListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getItemAt(int index) {
/*  95 */     if (index >= 0 && index < getItemCount())
/*  96 */       return this.m_AllItems.get(index); 
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemCount() {
/* 102 */     return this.m_AllItems.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemTagString(int tag) {
/* 107 */     return this.m_Implementor.getItemTagString(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getItemTagValue(int tag) {
/* 112 */     return this.m_Implementor.getItemTagValue(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsComboBoxItem getItemTagObject(int iTag) {
/* 117 */     return this.m_Implementor.getItemTagObject(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getItemlist() {
/* 122 */     return this.m_Implementor.getItemlist();
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getItems() {
/* 127 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 132 */     return this.m_Implementor.getItemsSList();
/*     */   }
/*     */   
/* 135 */   private int m_MaxRowCount = 0;
/*     */ 
/*     */   
/*     */   public int getMaximumRowCount() {
/* 139 */     return this.m_MaxRowCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/* 144 */     Object sObject = getSelectedItem();
/*     */ 
/*     */ 
/*     */     
/* 148 */     for (int i = 0, c = this.m_AllItems.size(); i < c; i++) {
/*     */       
/* 150 */       Object obj = this.m_AllItems.get(i);
/* 151 */       if (obj != null && obj.equals(sObject))
/* 152 */         return i; 
/*     */     } 
/* 154 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItem() {
/* 159 */     return this.m_SelectedItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/* 164 */     return this.m_Implementor.getSelectedItemTag();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItemValue() {
/* 169 */     return this.m_Implementor.getSelectedItemValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagOfItem(Object item) {
/* 174 */     return this.m_Implementor.getTagOfItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 179 */     return this.m_Implementor.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean m_Editable = false;
/*     */   
/*     */   public boolean isEditable() {
/* 186 */     return this.m_Editable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllItems() {
/* 191 */     this.m_AllItems.clear();
/* 192 */     this.m_SelectedItem = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItemAt(int index) {
/* 197 */     if (getItemAt(index) == this.m_SelectedItem)
/*     */     {
/* 199 */       if (index == 0) {
/*     */         
/* 201 */         setSelectedItem((this.m_AllItems.size() == 1) ? 
/* 202 */             null : 
/* 203 */             getItemAt(index + 1));
/*     */       }
/*     */       else {
/*     */         
/* 207 */         setSelectedItem(getItemAt(index - 1));
/*     */       } 
/*     */     }
/*     */     
/* 211 */     this.m_AllItems.remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItemListener(ItemListener listener) {
/* 216 */     if (listener == null) {
/*     */       return;
/*     */     }
/* 219 */     this.m_ItemListener = AWTEventMulticaster.remove(this.m_ItemListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 224 */     this.m_Editable = editable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilterListener(ILbsComboFilterListener listener) {
/* 229 */     this.m_Implementor.setFilterListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 234 */     this.m_Implementor.setItems(list);
/* 235 */     internalVerifyInput();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumRowCount(int count) {
/* 240 */     this.m_MaxRowCount = count;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int index) {
/* 245 */     if (!this.m_Implementor.isInternal())
/* 246 */       doFilterItems(); 
/* 247 */     int size = this.m_AllItems.size();
/*     */     
/* 249 */     if (index == -1) {
/*     */       
/* 251 */       setSelectedItem((Object)null);
/*     */     } else {
/* 253 */       if (index < -1 || index >= size)
/*     */       {
/* 255 */         throw new IllegalArgumentException("setSelectedIndex: " + index + " out of bounds");
/*     */       }
/*     */ 
/*     */       
/* 259 */       setSelectedItem(this.m_AllItems.get(index));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSelectedItem(String itemText) {
/* 265 */     return this.m_Implementor.setSelectedItem(itemText);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSelectedItemTag(int tag) {
/* 270 */     return this.m_Implementor.setSelectedItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int setSelectedItemValue(Object value) {
/* 275 */     return this.m_Implementor.setSelectedItemValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/* 280 */     if (listener == null)
/*     */       return; 
/* 282 */     this.m_ActionListener = AWTEventMulticaster.add(this.m_ActionListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/* 287 */     if (l == null)
/*     */       return; 
/* 289 */     this.m_ActionListener = AWTEventMulticaster.remove(this.m_ActionListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getSelectedObjects() {
/* 294 */     Object selectedObject = getSelectedItem();
/* 295 */     if (selectedObject == null) {
/* 296 */       return new Object[0];
/*     */     }
/*     */     
/* 299 */     Object[] result = new Object[1];
/* 300 */     result[0] = selectedObject;
/* 301 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedItem(Object anObject) {
/* 307 */     Object oldSelection = this.m_SelectedItemReminder;
/* 308 */     if (this.m_SelectedItem == null || oldSelection == null || !oldSelection.equals(anObject)) {
/*     */ 
/*     */       
/* 311 */       if (anObject != null && !isEditable()) {
/*     */ 
/*     */ 
/*     */         
/* 315 */         boolean found = false;
/* 316 */         for (int i = 0; i < this.m_AllItems.size(); i++) {
/*     */           
/* 318 */           if (anObject.equals(this.m_AllItems.get(i))) {
/*     */             
/* 320 */             found = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 324 */         if (!found) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 330 */       if ((this.m_SelectedItem != null && !this.m_SelectedItem.equals(anObject)) || (this.m_SelectedItem == null && anObject != null))
/*     */       {
/* 332 */         this.m_SelectedItem = anObject;
/*     */       }
/* 334 */       if (this.m_SelectedItemReminder != getSelectedItem())
/*     */       {
/* 336 */         selectedItemChanged();
/*     */       }
/*     */     } 
/* 339 */     fireActionEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean internalVerifyInput() {
/* 344 */     ILbsInputVerifier verifier = getLbsInputVerifier();
/* 345 */     if (verifier != null)
/*     */     {
/* 347 */       return verifier.verify((ILbsComponent)this);
/*     */     }
/* 349 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean firingActionEvent = false;
/*     */   
/*     */   protected void fireActionEvent() {
/* 356 */     if (!this.firingActionEvent && this.m_ActionListener != null) {
/*     */ 
/*     */       
/* 359 */       this.firingActionEvent = true;
/* 360 */       int modifiers = 0;
/* 361 */       ActionEvent e = new ActionEvent(this, 1001, getActionCommand(), 0L, modifiers);
/* 362 */       this.m_ActionListener.actionPerformed(e);
/* 363 */       this.firingActionEvent = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void selectedItemChanged() {
/* 369 */     if (this.m_SelectedItemReminder != null)
/*     */     {
/* 371 */       fireItemStateChanged(new ItemEvent(this, 701, this.m_SelectedItemReminder, 2));
/*     */     }
/*     */ 
/*     */     
/* 375 */     this.m_SelectedItemReminder = getSelectedItem();
/*     */     
/* 377 */     if (this.m_SelectedItemReminder != null)
/*     */     {
/* 379 */       fireItemStateChanged(new ItemEvent(this, 701, this.m_SelectedItemReminder, 1));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireItemStateChanged(ItemEvent e) {
/* 385 */     if (this.m_ItemListener != null) {
/* 386 */       this.m_ItemListener.itemStateChanged(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getActionCommand() {
/* 391 */     return this.m_ActionCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionCommand(String command) {
/* 396 */     this.m_ActionCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean m_PopupVisible = false;
/*     */   
/*     */   public boolean isPopupVisible() {
/* 403 */     return this.m_PopupVisible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopupVisible(boolean v) {
/* 408 */     if (this.m_PopupVisible != v && !v)
/*     */     {
/* 410 */       if (getLbsInputVerifier() != null)
/* 411 */         getLbsInputVerifier().verify((ILbsComponent)this); 
/*     */     }
/* 413 */     this.m_PopupVisible = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag, ImageIcon icon) {
/* 418 */     this.m_Implementor.addItem(anObject, iTag, icon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentCaretPosition() {
/* 425 */     return this.m_CurrentCaretPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentCaretPosition(int currentCaretPosition) {
/* 431 */     this.m_CurrentCaretPosition = currentCaretPosition;
/*     */   }
/*     */   
/*     */   public void setEditableForAutoComplete(boolean aFlag) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsComboBoxEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */