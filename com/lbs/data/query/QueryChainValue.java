/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.util.ILbsQueryChainValue;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public class QueryChainValue
/*     */   implements ILbsQueryChainValue, Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int CHAIN_AND = 1;
/*     */   public static final int CHAIN_OR = 2;
/*  25 */   protected int m_ChainType = 2;
/*     */   
/*     */   protected ArrayList<Object> m_Values;
/*     */   protected ArrayList<Object> m_ExcludedValues;
/*     */   
/*     */   public QueryChainValue() {
/*  31 */     this(2);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryChainValue(int chainType) {
/*  36 */     this.m_ChainType = chainType;
/*  37 */     this.m_Values = new ArrayList();
/*  38 */     this.m_ExcludedValues = new ArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addValue(Object value) {
/*  43 */     this.m_Values.add(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addValues(Object values) {
/*  48 */     internalAddValues(values, this.m_Values);
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalAddValues(Object values, ArrayList<Object> arrayList) {
/*  53 */     if (values.getClass().isArray()) {
/*     */       
/*  55 */       int cnt = Array.getLength(values);
/*  56 */       for (int i = 0; i < cnt; i++) {
/*  57 */         arrayList.add(Array.get(values, i));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     if (values instanceof Collection) {
/*     */       
/*  64 */       Collection<?> list = (Collection)values;
/*  65 */       arrayList.addAll(list);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExcludedValue(Object value) {
/*  73 */     this.m_ExcludedValues.add(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExcludedValues(Object values) {
/*  78 */     internalAddValues(values, this.m_ExcludedValues);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Object> getExcludedValues() {
/*  83 */     return this.m_ExcludedValues;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/*  88 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Object> getValues() {
/*  96 */     return this.m_Values;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 101 */     int size = 0;
/* 102 */     size += (this.m_Values != null) ? this.m_Values.size() : 0;
/* 103 */     size += (this.m_ExcludedValues != null) ? this.m_ExcludedValues.size() : 0;
/* 104 */     return size;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryChainValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */