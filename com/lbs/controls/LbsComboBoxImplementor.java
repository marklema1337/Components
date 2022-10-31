/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboBox;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsComboBoxImplementor
/*     */ {
/*     */   private final ILbsComboBox m_Component;
/*  24 */   private final ArrayList m_ItemList = new ArrayList();
/*  25 */   private ILbsComboFilterListener m_FilterListener = null;
/*     */   
/*     */   private boolean m_DisableEvents;
/*     */   private boolean m_Internal = false;
/*  29 */   private final ArrayList m_FilteredItemList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsComboBoxImplementor(ILbsComboBox component) {
/*  34 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag) {
/*  40 */     addItem(anObject, iTag, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag, ImageIcon icon) {
/*  50 */     JLbsComboBoxItem item = new JLbsComboBoxItem(anObject, iTag, icon);
/*  51 */     this.m_Component.addItem(item);
/*  52 */     this.m_ItemList.add(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/*  62 */     if (list != null) {
/*     */       
/*  64 */       int iLen = list.size();
/*  65 */       for (int i = 0; i < iLen; i++) {
/*     */         
/*  67 */         JLbsStringListItem item = list.getAt(i);
/*  68 */         addItem(item.Value, item.Tag);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearItems() {
/*  78 */     this.m_Component.removeAllItems();
/*  79 */     this.m_ItemList.clear();
/*  80 */     this.m_FilteredItemList.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/*  88 */     clearItems();
/*  89 */     addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/*  94 */     Object selObject = this.m_Component.getSelectedItem();
/*  95 */     if (selObject instanceof JLbsComboBoxItem) {
/*     */       
/*  97 */       JLbsComboBoxItem item = (JLbsComboBoxItem)selObject;
/*  98 */       return item.getTag();
/*     */     } 
/* 100 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setSelectedItemTag(int iTag) {
/*     */     try {
/* 111 */       this.m_Internal = true;
/* 112 */       int iLen = this.m_Component.getItemCount();
/* 113 */       for (int i = 0; i < iLen; i++) {
/*     */         
/* 115 */         Object itemObj = this.m_Component.getItemAt(i);
/* 116 */         if (itemObj instanceof JLbsComboBoxItem && ((JLbsComboBoxItem)itemObj).getTag() == iTag) {
/*     */           
/* 118 */           this.m_Component.setSelectedIndex(i);
/* 119 */           return true;
/*     */         } 
/*     */       } 
/* 122 */       this.m_Component.setSelectedIndex(-1);
/* 123 */       return false;
/*     */     }
/*     */     finally {
/*     */       
/* 127 */       this.m_Internal = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsComboBoxItem getItemTagObject(int iTag) {
/* 137 */     int iLen = this.m_Component.getItemCount();
/* 138 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 140 */       Object itemObj = this.m_Component.getItemAt(i);
/* 141 */       if (itemObj instanceof JLbsComboBoxItem && ((JLbsComboBoxItem)itemObj).getTag() == iTag)
/* 142 */         return (JLbsComboBoxItem)itemObj; 
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int iTag) {
/* 154 */     return (getItemTagObject(iTag) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getItemTagValue(int iTag) {
/* 164 */     JLbsComboBoxItem item = getItemTagObject(iTag);
/* 165 */     return (item != null) ? 
/* 166 */       item.getValue() : 
/* 167 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemTagString(int iTag) {
/* 177 */     Object v = getItemTagObject(iTag);
/* 178 */     return (v != null) ? 
/* 179 */       v.toString() : 
/* 180 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setSelectedItem(String itemText) {
/*     */     try {
/* 191 */       this.m_Internal = true;
/* 192 */       if (itemText == null) {
/*     */         
/* 194 */         this.m_Component.setSelectedIndex(-1);
/* 195 */         return true;
/*     */       } 
/* 197 */       int iLen = this.m_Component.getItemCount();
/* 198 */       for (int i = 0; i < iLen; i++) {
/*     */         
/* 200 */         Object itemObj = this.m_Component.getItemAt(i);
/* 201 */         if (itemObj != null && itemObj.toString().compareTo(itemText) == 0) {
/*     */           
/* 203 */           this.m_Component.setSelectedIndex(i);
/* 204 */           return true;
/*     */         } 
/*     */       } 
/* 207 */       return false;
/*     */     }
/*     */     finally {
/*     */       
/* 211 */       this.m_Internal = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItemValue() {
/* 217 */     Object obj = this.m_Component.getSelectedItem();
/* 218 */     if (obj instanceof JLbsComboBoxItem)
/*     */     {
/* 220 */       obj = ((JLbsComboBoxItem)obj).getValue();
/*     */     }
/* 222 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int setSelectedItemValue(Object value) {
/*     */     try {
/* 229 */       this.m_Internal = true;
/* 230 */       if (value != null) {
/* 231 */         for (int i = 0; i < this.m_ItemList.size(); i++) {
/*     */           
/* 233 */           JLbsComboBoxItem item = this.m_ItemList.get(i);
/* 234 */           Object itemValue = item.getValue();
/* 235 */           if (itemValue != null && itemValue.equals(value)) {
/*     */             
/* 237 */             this.m_Component.setSelectedItem(item);
/* 238 */             return i;
/*     */           } 
/* 240 */           if (itemValue != null && itemValue instanceof JLbsPropertyVariable) {
/*     */             
/* 242 */             JLbsPropertyVariable var = (JLbsPropertyVariable)itemValue;
/* 243 */             if (var.getName().equals(value)) {
/*     */               
/* 245 */               this.m_Component.setSelectedItem(item);
/* 246 */               return i;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 251 */       return -1;
/*     */     }
/*     */     finally {
/*     */       
/* 255 */       this.m_Internal = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagOfItem(Object item) {
/* 261 */     if (item instanceof JLbsComboBoxItem)
/* 262 */       return ((JLbsComboBoxItem)item).getTag(); 
/* 263 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(Object value) {
/* 268 */     return (value != null) ? 
/* 269 */       findItemIndex(value.toString()) : 
/* 270 */       -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(String value) {
/* 275 */     if (value != null)
/* 276 */       for (int i = 0; i < this.m_ItemList.size(); i++) {
/*     */         
/* 278 */         JLbsComboBoxItem item = this.m_ItemList.get(i);
/* 279 */         Object itemValue = item.getValue();
/* 280 */         if (itemValue != null && itemValue.toString().compareTo(value) == 0)
/* 281 */           return i; 
/*     */       }  
/* 283 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getItemlist() {
/* 288 */     int size = (this.m_ItemList != null) ? 
/* 289 */       this.m_ItemList.size() : 
/* 290 */       0;
/* 291 */     if (size == 0)
/* 292 */       return null; 
/* 293 */     Object[] result = new Object[size];
/* 294 */     for (int i = 0; i < size; i++) {
/*     */       
/* 296 */       JLbsComboBoxItem item = this.m_ItemList.get(i);
/* 297 */       result[i] = item.getValue();
/*     */     } 
/* 299 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComboFilterListener getFilterListener() {
/* 304 */     return this.m_FilterListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilterListener(ILbsComboFilterListener listener) {
/* 309 */     this.m_FilterListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doFilterItems() {
/* 314 */     this.m_DisableEvents = true;
/* 315 */     int currentCaretPosition = 0;
/* 316 */     this.m_FilteredItemList.clear();
/*     */     
/*     */     try {
/* 319 */       Object oSelected = this.m_Component.getSelectedItem();
/* 320 */       currentCaretPosition = this.m_Component.getCurrentCaretPosition();
/* 321 */       if (this.m_FilterListener != null) {
/*     */         
/* 323 */         if (this.m_Component.getItemCount() > 0)
/* 324 */           this.m_Component.removeAllItems(); 
/* 325 */         int iLen = this.m_ItemList.size();
/* 326 */         for (int i = 0; i < iLen; i++) {
/*     */           
/* 328 */           JLbsComboBoxItem item = this.m_ItemList.get(i);
/* 329 */           if (this.m_FilterListener.getFilterMask(this.m_Component, i, item.getTag()))
/*     */           {
/* 331 */             this.m_Component.addItem(item);
/* 332 */             this.m_FilteredItemList.add(item);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 338 */         if (this.m_Component.getItemCount() > 0)
/* 339 */           this.m_Component.removeAllItems(); 
/* 340 */         int iLen = this.m_ItemList.size();
/* 341 */         for (int i = 0; i < iLen; i++) {
/*     */           
/* 343 */           this.m_Component.addItem(this.m_ItemList.get(i));
/* 344 */           this.m_FilteredItemList.add(this.m_ItemList.get(i));
/*     */         } 
/*     */       } 
/* 347 */       this.m_Component.setSelectedItem(oSelected);
/*     */     }
/*     */     finally {
/*     */       
/* 351 */       this.m_Component.setCurrentCaretPosition(currentCaretPosition);
/* 352 */       this.m_DisableEvents = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 358 */     if (this.m_ItemList != null) {
/*     */       
/* 360 */       StringBuilder buffer = new StringBuilder();
/* 361 */       for (int i = 0; i < this.m_ItemList.size(); i++) {
/*     */         
/* 363 */         JLbsComboBoxItem item = this.m_ItemList.get(i);
/* 364 */         if (buffer.length() > 0)
/* 365 */           buffer.append("\n"); 
/* 366 */         buffer.append(item.toEditString());
/*     */       } 
/* 368 */       return buffer.toString().replaceAll("\n", "|");
/*     */     } 
/* 370 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getItems() {
/* 375 */     return this.m_ItemList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisableEvents() {
/* 380 */     return this.m_DisableEvents;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisableEvents(boolean disableEvents) {
/* 385 */     this.m_DisableEvents = disableEvents;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInternal() {
/* 390 */     return this.m_Internal;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getFilteredItemList() {
/* 395 */     JLbsStringList list = new JLbsStringList();
/*     */     
/* 397 */     if (this.m_FilteredItemList != null)
/*     */     {
/* 399 */       for (int i = 0; i < this.m_FilteredItemList.size(); i++) {
/*     */         
/* 401 */         JLbsComboBoxItem item = this.m_FilteredItemList.get(i);
/* 402 */         Object itemValue = item.getValue();
/* 403 */         if (itemValue instanceof String) {
/* 404 */           list.add((String)item.getValue(), item.getTag());
/*     */         }
/*     */       } 
/*     */     }
/* 408 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\LbsComboBoxImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */