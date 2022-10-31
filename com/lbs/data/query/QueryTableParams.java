/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.util.EnabledList;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
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
/*     */ 
/*     */ 
/*     */ public class QueryTableParams
/*     */   extends EnabledList
/*     */   implements Externalizable, ILbsXStreamListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamImplicit(itemFieldName = "item")
/*     */   protected ArrayList<QueryTableParam> m_Items;
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  38 */     super.afterXStreamDeserialization();
/*  39 */     if (this.m_Items == null) {
/*  40 */       this.m_Items = new ArrayList<>();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected QueryTableParam find(String alias) {
/*  46 */     for (int i = 0; i < this.m_Items.size(); i++) {
/*     */       
/*  48 */       QueryTableParam params = this.m_Items.get(i);
/*     */       
/*  50 */       if (params.getAlias().equals(alias)) {
/*  51 */         return params;
/*     */       }
/*     */     } 
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableParam getParams(String alias) {
/*  59 */     QueryTableParam params = find(alias);
/*     */     
/*  61 */     if (params == null) {
/*     */       
/*  63 */       params = new QueryTableParam(alias);
/*  64 */       this.m_Items.add(params);
/*     */     } 
/*     */     
/*  67 */     return params;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableParam items(int index) {
/*  72 */     return this.m_Items.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int itemCount() {
/*  77 */     return this.m_Items.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  85 */     QueryTableParams params = new QueryTableParams();
/*  86 */     params.m_AllDisabled = this.m_AllDisabled;
/*  87 */     params.m_AllowRegularExpressions = this.m_AllowRegularExpressions;
/*  88 */     params.m_AutoSwitch = this.m_AutoSwitch;
/*  89 */     cloneFast(params);
/*  90 */     if (this.m_Items != null && this.m_Items.size() > 0) {
/*     */       
/*  92 */       List<QueryTableParam> items = params.m_Items;
/*  93 */       for (QueryTableParam param : this.m_Items)
/*     */       {
/*  95 */         items.add(param);
/*     */       }
/*     */     } 
/*  98 */     return params;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 103 */     super.readExternal(in);
/* 104 */     ExternalizationUtil.readExternal(this.m_Items, in);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 109 */     super.writeExternal(out);
/* 110 */     ExternalizationUtil.writeExternal(this.m_Items, out);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTableParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */