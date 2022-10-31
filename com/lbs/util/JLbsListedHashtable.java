/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsListedHashtable
/*     */   extends Hashtable
/*     */   implements Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public synchronized Object put(Object key, Object value) {
/*     */     ArrayList<Object> list;
/*  31 */     Object obj = get(key);
/*  32 */     if (obj instanceof ArrayList) {
/*     */       
/*  34 */       list = (ArrayList)obj;
/*     */     }
/*     */     else {
/*     */       
/*  38 */       list = new ArrayList();
/*  39 */       super.put(key, list);
/*     */     } 
/*  41 */     list.add(value);
/*  42 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void putAll(Map<?, ?> t) {
/*  47 */     if (t instanceof JLbsListedHashtable) {
/*     */       
/*  49 */       Iterator<Map.Entry> i = t.entrySet().iterator();
/*  50 */       while (i.hasNext()) {
/*     */         
/*  52 */         Map.Entry e = i.next();
/*  53 */         super.put(e.getKey(), e.getValue());
/*     */       } 
/*     */     } else {
/*     */       
/*  57 */       super.putAll(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  62 */     Enumeration<V> values = elements();
/*  63 */     while (values.hasMoreElements()) {
/*     */       
/*  65 */       ArrayList list = (ArrayList)values.nextElement();
/*  66 */       if (list.size() == 1) {
/*     */         
/*  68 */         Object o = list.get(0);
/*  69 */         if (o instanceof ArrayList) {
/*     */           
/*  71 */           ArrayList orgList = (ArrayList)o;
/*  72 */           list.clear();
/*  73 */           list.addAll(orgList);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/*  81 */     out.writeInt(size());
/*  82 */     for (Enumeration<K> e = keys(); e.hasMoreElements(); ) {
/*     */       
/*  84 */       Object key = e.nextElement();
/*  85 */       out.writeObject(key);
/*     */       
/*  87 */       ArrayList list = (ArrayList)get(key);
/*  88 */       ExternalizationUtil.writeExternal(list, out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  94 */     int size = in.readInt();
/*  95 */     for (; size > 0; size--) {
/*     */       
/*  97 */       Object key = in.readObject();
/*  98 */       ArrayList list = new ArrayList();
/*  99 */       ExternalizationUtil.readExternal(list, in);
/* 100 */       super.put(key, list);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static <K, V> void put(Hashtable<K, ArrayList<V>> hashtable, K key, V value) {
/* 106 */     ArrayList<V> values = hashtable.get(key);
/* 107 */     if (values == null) {
/*     */       
/* 109 */       values = new ArrayList<>();
/* 110 */       hashtable.put(key, values);
/*     */     } 
/* 112 */     if (!values.contains(value)) {
/* 113 */       values.add(value);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <K, V> void putAll(Hashtable<K, ArrayList<V>> hashtable, K key, List<V> value) {
/* 118 */     ArrayList<V> values = hashtable.get(key);
/* 119 */     if (values == null) {
/*     */       
/* 121 */       values = new ArrayList<>();
/* 122 */       hashtable.put(key, values);
/*     */     } 
/* 124 */     values.addAll(value);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsListedHashtable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */