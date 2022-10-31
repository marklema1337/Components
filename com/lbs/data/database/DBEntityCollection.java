/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.factory.INamedVariables;
/*     */ import com.lbs.data.factory.ISubstitutionListener;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ public class DBEntityCollection
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private List<DBEntityItem> m_List;
/*     */   private HashMap<String, DBEntityItem> entityMap;
/*  27 */   private Object Owner = null;
/*     */ 
/*     */   
/*     */   public DBEntityCollection(Object owner) {
/*  31 */     this.m_List = new ArrayList<>();
/*  32 */     this.entityMap = new HashMap<>();
/*  33 */     this.Owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  38 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntityItem itemAt(int index) {
/*  43 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(DBEntityItem item) {
/*  48 */     item.setCollection(this);
/*  49 */     this.entityMap.put(item.getName(), item);
/*  50 */     return this.m_List.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(int index, DBEntityItem item) {
/*  55 */     item.setCollection(this);
/*  56 */     this.entityMap.put(item.getName(), item);
/*  57 */     this.m_List.add(index, item);
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int find(List<DBEntity> list, String name) {
/*  64 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/*  66 */       DBEntity item = list.get(i);
/*     */       
/*  68 */       if (item.getName().equals(name))
/*  69 */         return i; 
/*     */     } 
/*  71 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int find(String name) {
/*  76 */     return find(this.m_List, name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int findByPhysicalName(List<DBEntity> list, String name) {
/*  82 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/*  84 */       DBEntity item = list.get(i);
/*     */       
/*  86 */       if (item.getPhysicalName().equals(name))
/*  87 */         return i; 
/*     */     } 
/*  89 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findByPhysicalName(String name) {
/*  94 */     return findByPhysicalName(this.m_List, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntity getEntity(String name) {
/*  99 */     return this.entityMap.get(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntity entity(int index) {
/* 104 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getOwner() {
/* 113 */     return this.Owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 118 */     this.m_List.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void changeNames(INameProducer namer, INamedVariables variables, ISubstitutionListener listener, Object dbConnection) {
/* 123 */     for (int i = 0; i < size(); i++) {
/* 124 */       entity(i).changeName(namer, variables, listener, dbConnection);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 132 */     DBEntityCollection clonedObj = (DBEntityCollection)super.clone();
/*     */ 
/*     */     
/* 135 */     for (int i = 0; i < clonedObj.size(); i++) {
/*     */       
/* 137 */       DBEntityItem entity = clonedObj.m_List.get(i);
/* 138 */       clonedObj.m_List.set(i, (DBEntityItem)entity.clone());
/*     */     } 
/* 140 */     return clonedObj;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(int index) {
/* 145 */     this.m_List.remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort() {
/* 150 */     Collections.sort(this.m_List, new Comparator<DBEntityItem>()
/*     */         {
/*     */           public int compare(Object o1, Object o2)
/*     */           {
/* 154 */             if (o1 instanceof DBEntityItem && o2 instanceof DBEntityItem) {
/*     */               
/* 156 */               DBEntityItem e1 = (DBEntityItem)o1;
/* 157 */               DBEntityItem e2 = (DBEntityItem)o2;
/*     */               
/* 159 */               return e1.getName().compareToIgnoreCase(e2.getName());
/*     */             } 
/* 161 */             return 0;
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBEntityCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */