/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryFilterTree
/*     */   extends QueryFilterTermBase
/*     */   implements Serializable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ArrayList<QueryFilterTree> m_SubItems;
/*     */   
/*     */   public QueryFilterTree() {}
/*     */   
/*     */   public QueryFilterTree(int operation, Object value) {
/*  34 */     this.m_SearchValue = value;
/*  35 */     setOperation(operation);
/*  36 */     setSearchValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSubTree(QueryFilterTree subTree) {
/*  41 */     if (this.m_SubItems == null)
/*  42 */       this.m_SubItems = new ArrayList<>(); 
/*  43 */     this.m_SubItems.add(subTree);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<QueryFilterTree> getSubItems() {
/*  48 */     return this.m_SubItems;
/*     */   }
/*     */   
/*     */   public boolean isValidExpression() {
/*     */     int i;
/*  53 */     switch (this.m_Operation) {
/*     */       
/*     */       case 101:
/*     */       case 102:
/*  57 */         if (this.m_SubItems == null)
/*  58 */           return false; 
/*  59 */         if (this.m_SubItems.size() < 2) {
/*  60 */           return false;
/*     */         }
/*  62 */         for (i = 0; i < this.m_SubItems.size(); i++) {
/*     */           
/*  64 */           QueryFilterTree subItem = this.m_SubItems.get(i);
/*  65 */           if (subItem == null)
/*  66 */             return false; 
/*  67 */           if (!subItem.isValidExpression())
/*  68 */             return false; 
/*     */         } 
/*  70 */         return true;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 9:
/*  79 */         return (this.m_SearchValue != null);
/*     */     } 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeaf() {
/*  87 */     return (this.m_SubItems == null || this.m_SubItems.size() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/*  92 */     super.writeExternal(out);
/*  93 */     ExternalizationUtil.writeExternal(this.m_SubItems, out);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  99 */     super.readExternal(in);
/* 100 */     this.m_SubItems = (ArrayList<QueryFilterTree>)ExternalizationUtil.readExternal(this.m_SubItems, in, ArrayList.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFilterTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */