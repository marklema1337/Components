/*     */ package com.lbs.data.objects;
/*     */ 
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
/*     */ public class BusinessObjects<T extends BusinessObject>
/*     */   extends SimpleBusinessObjects<T>
/*     */   implements Serializable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("LoadingState")
/*  23 */   protected int m_LoadingState = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public T item(int index) {
/*  28 */     return itemAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChanges() {
/*  33 */     if (this.m_Deleted.size() != 0) {
/*  34 */       return true;
/*     */     }
/*     */     
/*  37 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  39 */       T obj = item(i);
/*     */       
/*  41 */       if (obj.hasChanges()) {
/*  42 */         return true;
/*     */       }
/*     */     } 
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChangedProperties() {
/*  50 */     if (this.m_Deleted.size() != 0) {
/*  51 */       return true;
/*     */     }
/*     */     
/*  54 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  56 */       T obj = item(i);
/*     */       
/*  58 */       if (obj.hasChangedProperties()) {
/*  59 */         return true;
/*     */       }
/*     */     } 
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAllInitialValues() {
/*  68 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  70 */       BusinessObject obj = (BusinessObject)item(i);
/*  71 */       obj.clearInitialValues();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadingState() {
/*  77 */     return this.m_LoadingState;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setLoadingState(int loadingState) {
/*  82 */     this.m_LoadingState = loadingState;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChangedSinceValidation() {
/*  87 */     if (this.m_Deleted.size() != 0) {
/*  88 */       return true;
/*     */     }
/*     */     
/*  91 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  93 */       T item = item(i);
/*  94 */       if (item.hasChangedSinceValidation())
/*  95 */         return true; 
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearValidatedValues() {
/* 103 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 105 */       T item = item(i);
/* 106 */       item.clearValidatedValues();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 112 */     super.writeExternal(out);
/*     */     
/* 114 */     out.writeInt(this.m_LoadingState);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 119 */     super.readExternal(in);
/*     */     
/* 121 */     this.m_LoadingState = in.readInt();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */