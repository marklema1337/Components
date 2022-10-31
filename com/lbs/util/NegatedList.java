/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
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
/*     */ public class NegatedList
/*     */   implements Serializable, Cloneable, Externalizable, ILbsXStreamListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("AllNegated")
/*     */   protected boolean m_AllNegated = false;
/*     */   @XStreamAlias("NegatedItems")
/*     */   @XStreamImplicit(itemFieldName = "NegatedItem")
/*  29 */   private List<String> m_NegatedItems = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegatedList() {
/*  35 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  40 */     if (this.m_NegatedItems == null) {
/*  41 */       this.m_NegatedItems = new ArrayList<>();
/*     */     }
/*     */   }
/*     */   
/*     */   public void negate(String name) {
/*  46 */     this.m_NegatedItems.add(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void negateAll() {
/*  51 */     this.m_AllNegated = true;
/*  52 */     this.m_NegatedItems.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNegated(String itemName) {
/*  57 */     if (this.m_AllNegated) {
/*  58 */       return true;
/*     */     }
/*  60 */     return this.m_NegatedItems.contains(itemName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/*  68 */     NegatedList negatedList = new NegatedList();
/*  69 */     negatedList.m_AllNegated = this.m_AllNegated;
/*  70 */     negatedList.m_NegatedItems = new ArrayList<>();
/*  71 */     if (this.m_NegatedItems != null && this.m_NegatedItems.size() > 0) {
/*     */       
/*  73 */       List<String> enabledItems = negatedList.m_NegatedItems;
/*  74 */       for (String enabledItem : this.m_NegatedItems) {
/*     */         
/*  76 */         if (enabledItem != null)
/*  77 */           enabledItems.add(new String(enabledItem)); 
/*     */       } 
/*     */     } 
/*  80 */     return negatedList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllNegated() {
/*  85 */     return this.m_AllNegated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllNegated(boolean b) {
/*  90 */     this.m_AllNegated = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  95 */     this.m_NegatedItems.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(String itemName) {
/* 100 */     int idx = this.m_NegatedItems.indexOf(itemName);
/*     */     
/* 102 */     if (idx == -1) {
/*     */       return;
/*     */     }
/* 105 */     this.m_NegatedItems.remove(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 110 */     this.m_AllNegated = in.readBoolean();
/* 111 */     ExternalizationUtil.readExternal(this.m_NegatedItems, in);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 116 */     out.writeBoolean(this.m_AllNegated);
/* 117 */     ArrayList negatedItems = (ArrayList)this.m_NegatedItems;
/* 118 */     ExternalizationUtil.writeExternal(negatedItems, out);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\NegatedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */