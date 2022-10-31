/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.util.EnabledList;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryTableParam
/*     */   implements Serializable, Cloneable, Externalizable, ILbsXStreamListener, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("Alias")
/*     */   protected String m_Alias;
/*     */   @XStreamAlias("EnabledColumns")
/*     */   protected EnabledList m_EnabledColumns;
/*     */   @XStreamAlias("OrderName")
/*     */   protected String m_OrderName;
/*     */   @XStreamAlias("OrderDescending")
/*     */   protected boolean m_OrderDescending;
/*     */   
/*     */   public QueryTableParam() {
/*  46 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableParam(String alias) {
/*  51 */     this.m_Alias = alias;
/*  52 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  57 */     if (this.m_EnabledColumns == null) {
/*  58 */       this.m_EnabledColumns = new EnabledList();
/*     */     } else {
/*  60 */       this.m_EnabledColumns.afterXStreamDeserialization();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getAlias() {
/*  65 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnabledList getEnabledColumns() {
/*  70 */     return this.m_EnabledColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOrderName() {
/*  75 */     return this.m_OrderName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrder(String orderName, boolean descending) {
/*  80 */     this.m_OrderName = orderName;
/*  81 */     this.m_OrderDescending = descending;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  86 */     Object obj = super.clone();
/*     */     
/*  88 */     ObjectUtil.deepCopy(this, obj, QueryTableParam.class);
/*     */     
/*  90 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOrderDescending() {
/*  95 */     return this.m_OrderDescending;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 100 */     this.m_Alias = (String)in.readObject();
/* 101 */     this.m_EnabledColumns.readExternal(in);
/* 102 */     this.m_OrderName = (String)in.readObject();
/* 103 */     this.m_OrderDescending = in.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 108 */     out.writeObject(this.m_Alias);
/* 109 */     this.m_EnabledColumns.writeExternal(out);
/* 110 */     out.writeObject(this.m_OrderName);
/* 111 */     out.writeBoolean(this.m_OrderDescending);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTableParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */