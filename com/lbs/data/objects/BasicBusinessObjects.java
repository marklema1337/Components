/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public abstract class BasicBusinessObjects<T extends BasicBusinessObject>
/*     */   extends ArrayList<T>
/*     */   implements Serializable, Cloneable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public T itemAt(int index) {
/*  29 */     if (index < 0 || index >= size()) {
/*  30 */       return null;
/*     */     }
/*  32 */     return (T)get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public T elementAt(int index) {
/*  37 */     return (T)get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addElement(T object) {
/*  42 */     add((E)object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reverse() {
/*  47 */     Collections.reverse(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public T getFirst() {
/*  52 */     if (size() > 0) {
/*  53 */       return get(0);
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getLast() {
/*  60 */     int len = size();
/*  61 */     if (len > 0) {
/*  62 */       return get(len - 1);
/*     */     }
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public T firstElement() {
/*  69 */     return getFirst();
/*     */   }
/*     */ 
/*     */   
/*     */   public T lastElement() {
/*  74 */     return getLast();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int findByKey(int key) {
/*  80 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  82 */       BasicBusinessObject basicBusinessObject = (BasicBusinessObject)get(i);
/*     */       
/*  84 */       if (basicBusinessObject.getUniqueIdentifier().getSimpleKey() == key) {
/*  85 */         return i;
/*     */       }
/*     */     } 
/*  88 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getByKey(int key) {
/*  93 */     int idx = findByKey(key);
/*     */     
/*  95 */     if (idx != -1) {
/*  96 */       return itemAt(idx);
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int fromIndex, int count) {
/* 109 */     removeRange(fromIndex, fromIndex + count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(BasicBusinessObjects<T> srcList, boolean append) {
/* 116 */     if (append) {
/*     */       
/* 118 */       for (int i = 0; i < srcList.size(); i++) {
/*     */         
/* 120 */         T obj = srcList.itemAt(i);
/* 121 */         if (!contains(obj)) {
/* 122 */           add(obj);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 127 */       for (int i = srcList.size() - 1; i >= 0; i--) {
/*     */         
/* 129 */         T obj = srcList.itemAt(i);
/* 130 */         if (!contains(obj)) {
/* 131 */           add(0, obj);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public BasicBusinessObjects<T> cloneFirst(int n) {
/* 138 */     BasicBusinessObjects<T> result = createObjectList();
/*     */     
/* 140 */     int count = Math.min(n, size());
/*     */     
/* 142 */     for (int i = 0; i < count; i++) {
/* 143 */       result.add(itemAt(i));
/*     */     }
/* 145 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicBusinessObjects<T> cloneRange(int start, int count) {
/* 150 */     BasicBusinessObjects<T> result = createObjectList();
/* 151 */     if (start >= 0 && start < size()) {
/*     */       
/* 153 */       count = Math.min(count, size() - start);
/* 154 */       for (int i = 0; i < count; i++)
/* 155 */         result.add(itemAt(start + i)); 
/*     */     } 
/* 157 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BasicBusinessObjects<T> createObjectList() {
/*     */     try {
/* 165 */       return (BasicBusinessObjects<T>)getClass().newInstance();
/*     */     }
/* 167 */     catch (InstantiationException e) {
/*     */       
/* 169 */       LbsConsole.getLogger("LbsData.Client.BasicBO").error(null, e);
/*     */     
/*     */     }
/* 172 */     catch (IllegalAccessException e) {
/*     */       
/* 174 */       LbsConsole.getLogger("LbsData.Client.BasicBO").error(null, e);
/*     */     } 
/*     */ 
/*     */     
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFirst(BasicBusinessObjects<T> src, int n) {
/* 183 */     int count = Math.min(n, src.size());
/*     */     
/* 185 */     for (int i = 0; i < count; i++) {
/* 186 */       add(src.itemAt(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public BasicBusinessObjects<T> cloneLast(int n) {
/* 191 */     BasicBusinessObjects<T> result = createObjectList();
/*     */     
/* 193 */     int length = size();
/* 194 */     int count = Math.min(n, length);
/*     */     
/* 196 */     for (int i = 0; i < count; i++) {
/* 197 */       result.add(itemAt(length - count + i));
/*     */     }
/* 199 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyLast(BasicBusinessObjects<T> src, int n) {
/* 204 */     int length = src.size();
/* 205 */     int count = Math.min(n, length);
/*     */     
/* 207 */     for (int i = 0; i < count; i++) {
/* 208 */       add(src.itemAt(length - count + i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object clone() {
/* 217 */     BasicBusinessObjects<T> newCol = (BasicBusinessObjects<T>)super.clone();
/*     */     
/* 219 */     for (int i = 0; i < newCol.size(); i++) {
/*     */       
/* 221 */       BasicBusinessObject basicBusinessObject = (BasicBusinessObject)newCol.get(i);
/*     */       
/*     */       try {
/* 224 */         basicBusinessObject = (BasicBusinessObject)basicBusinessObject.clone();
/*     */       }
/* 226 */       catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */ 
/*     */       
/* 230 */       newCol.set(i, (T)basicBusinessObject);
/*     */     } 
/* 232 */     return newCol;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialValuesEnabled(boolean enabled) {
/* 238 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 240 */       BasicBusinessObject basicBusinessObject = (BasicBusinessObject)get(i);
/* 241 */       basicBusinessObject.setInitialValuesEnabled(enabled);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean equals(Object o) {
/* 247 */     if (o != null && o.getClass() != getClass()) {
/* 248 */       return false;
/*     */     }
/* 250 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 255 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 260 */     ExternalizationUtil.writeExternal(this, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 265 */     ExternalizationUtil.readExternal(this, in);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BasicBusinessObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */