/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringItemList
/*     */   implements Serializable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamImplicit(itemFieldName = "item")
/*  30 */   protected List<String> m_List = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  35 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItem(int index) {
/*  40 */     return this.m_List.get(index);
/*     */   }
/*     */   
/*     */   public void setItem(int index, String value) {
/*  44 */     this.m_List.set(index, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(String value) {
/*  49 */     int idx = this.m_List.indexOf(value);
/*     */     
/*  51 */     if (idx == -1) {
/*  52 */       return this.m_List.add(value);
/*     */     }
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(String item) {
/*  59 */     return this.m_List.indexOf(item);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(StringItemList sourceList) {
/*  65 */     for (int i = 0; i < sourceList.size(); i++) {
/*     */       
/*  67 */       String item = sourceList.getItem(i);
/*  68 */       add(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String addToClause(String clause, String entity, String separator, boolean paranthesis) {
/*  74 */     if (StringUtil.isWhiteSpace(clause)) {
/*  75 */       clause = entity;
/*     */     } else {
/*     */       
/*  78 */       if (paranthesis) {
/*  79 */         clause = "(" + clause + ")";
/*     */       }
/*  81 */       if (!StringUtil.isWhiteSpace(entity)) {
/*  82 */         clause = clause + separator + entity;
/*     */       }
/*     */     } 
/*  85 */     return clause;
/*     */   }
/*     */ 
/*     */   
/*     */   public String generateClause(String separator, boolean paranthesis) {
/*  90 */     String clause = "";
/*     */     
/*  92 */     for (int i = 0; i < this.m_List.size(); i++) {
/*  93 */       clause = addToClause(clause, getItem(i), separator, paranthesis);
/*     */     }
/*  95 */     return clause;
/*     */   }
/*     */ 
/*     */   
/*     */   public void replace(String oldItem, String newItem) {
/* 100 */     int idx = indexOf(oldItem);
/*     */     
/* 102 */     if (idx != -1) {
/* 103 */       setItem(idx, newItem);
/*     */     } else {
/* 105 */       add(newItem);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 110 */     ExternalizationUtil.readExternal(this.m_List, in);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 115 */     ArrayList listtToWrite = (ArrayList)this.m_List;
/* 116 */     ExternalizationUtil.writeExternal(listtToWrite, out);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\StringItemList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */