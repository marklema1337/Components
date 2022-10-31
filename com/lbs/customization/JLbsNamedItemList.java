/*     */ package com.lbs.customization;
/*     */ 
/*     */ import com.lbs.util.JLbsSortedIntList;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.util.ArrayList;
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
/*     */ public class JLbsNamedItemList
/*     */   extends ArrayList
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected transient Object m_Owner;
/*     */   protected int m_IdBase;
/*     */   
/*     */   public JLbsNamedItemList() {}
/*     */   
/*     */   public JLbsNamedItemList(Object owner) {
/*  26 */     this.m_Owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItemList(JLbsNamedItemList list) {
/*  31 */     super(list);
/*  32 */     if (list != null) {
/*  33 */       this.m_Owner = list.m_Owner;
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsNamedItemList createClone() {
/*  38 */     JLbsNamedItemList result = new JLbsNamedItemList();
/*  39 */     modifyCopy(result);
/*  40 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void modifyCopy(JLbsNamedItemList target) {
/*  45 */     target.setOwner(this.m_Owner);
/*  46 */     target.clear();
/*     */     
/*     */     try {
/*  49 */       for (int i = 0; i < size(); i++) {
/*     */         
/*  51 */         JLbsNamedItem src = getItemAt(i);
/*  52 */         if (src != null) {
/*     */           
/*  54 */           JLbsNamedItem item = createItem(src);
/*  55 */           if (item != null)
/*     */           {
/*  57 */             item.assign(src);
/*  58 */             item._setOwner(target);
/*  59 */             target.add((E)item);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*  64 */     } catch (Exception e) {
/*     */       
/*  66 */       target = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getOwner() {
/*  72 */     return this.m_Owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(Object owner) {
/*  77 */     this.m_Owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem getItemAt(int index) {
/*  82 */     if (index >= 0 && index < size()) {
/*     */       
/*  84 */       Object o = get(index);
/*  85 */       if (o instanceof JLbsNamedItem)
/*  86 */         return (JLbsNamedItem)o; 
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem getItemById(int id) {
/*  93 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  95 */       Object o = get(i);
/*  96 */       if (o instanceof JLbsNamedItem && ((JLbsNamedItem)o).getId() == id)
/*  97 */         return (JLbsNamedItem)o; 
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem getItemByName(String name) {
/* 104 */     if (name != null)
/* 105 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 107 */         Object o = get(i);
/* 108 */         if (o instanceof JLbsNamedItem) {
/*     */           
/* 110 */           String itemName = ((JLbsNamedItem)o).getName();
/* 111 */           if (itemName != null && itemName.compareTo(name) == 0)
/* 112 */             return (JLbsNamedItem)o; 
/*     */         } 
/*     */       }  
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem createItemWithOwner() {
/* 120 */     JLbsNamedItem result = createItem();
/* 121 */     if (result != null)
/* 122 */       result._setOwnerSafe(this); 
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem createItem() {
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem createItem(JLbsNamedItem src) {
/* 133 */     JLbsNamedItem result = createItem();
/* 134 */     if (result != null)
/* 135 */       result.assign(src); 
/* 136 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean checkIdUnique(JLbsNamedItem item, int id) {
/* 141 */     JLbsSortedIntList list = getIdList();
/* 142 */     return (list.indexOf(id) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean checkNameUnique(JLbsNamedItem item, String name) {
/* 147 */     JLbsStringList list = getNameList();
/* 148 */     return (list.indexOf(name) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueId() {
/* 153 */     return getUniqueId(this.m_IdBase + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueId(int base) {
/* 158 */     if (base != this.m_IdBase + 1)
/* 159 */       base += this.m_IdBase; 
/* 160 */     JLbsSortedIntList list = getIdList();
/* 161 */     int index = base;
/* 162 */     for (; list.indexOf(index) >= 0 && index < base + 20000; index++);
/* 163 */     return index;
/*     */   }
/*     */   
/*     */   public String getUniqueName(String prefix) {
/*     */     String name;
/* 168 */     JLbsStringList list = getNameList();
/* 169 */     int index = this.m_IdBase + 1;
/*     */     
/*     */     do {
/* 172 */       name = String.valueOf(prefix) + index;
/* 173 */     } while (list.indexOf(name) >= 0 && index++ < this.m_IdBase + 20000);
/* 174 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsSortedIntList getIdList() {
/* 179 */     JLbsSortedIntList list = new JLbsSortedIntList();
/* 180 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 182 */       Object o = get(i);
/* 183 */       if (o instanceof JLbsNamedItem)
/* 184 */         list.add(((JLbsNamedItem)o).getId()); 
/*     */     } 
/* 186 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsStringList getNameList() {
/* 191 */     JLbsStringList list = new JLbsStringList();
/* 192 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 194 */       Object o = get(i);
/* 195 */       if (o instanceof JLbsNamedItem)
/* 196 */         list.add(((JLbsNamedItem)o).getName(), 0); 
/*     */     } 
/* 198 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getPrefix() {
/* 203 */     return "nit";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\customization\JLbsNamedItemList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */