/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
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
/*     */ public class FactoryCascadeOptions
/*     */   implements Serializable, Cloneable, Externalizable, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private final HashMap m_Map = new HashMap<>();
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
/*     */   public void put(Class objClass, int cascadeOptions) {
/*  44 */     this.m_Map.put(objClass.getName(), Integer.valueOf(cascadeOptions));
/*     */   }
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
/*     */   public void put(Class objClass, String propName, int cascadeOptions) {
/*  58 */     String key = getKey(objClass, propName);
/*  59 */     this.m_Map.put(key, Integer.valueOf(cascadeOptions));
/*     */   }
/*     */ 
/*     */   
/*     */   private String getKey(Class objClass, String propName) {
/*  64 */     if (objClass == null) {
/*  65 */       return propName;
/*     */     }
/*  67 */     return objClass.getName() + '.' + propName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int get(Class<BusinessObject> objClass, String propName) {
/*  72 */     while (objClass != BusinessObject.class && objClass != null) {
/*     */       
/*  74 */       String key = getKey(objClass, propName);
/*     */       
/*  76 */       if (this.m_Map.containsKey(key)) {
/*     */         
/*  78 */         Integer options = (Integer)this.m_Map.get(key);
/*  79 */         return options.intValue();
/*     */       } 
/*     */       
/*  82 */       objClass = (Class)objClass.getSuperclass();
/*     */     } 
/*     */     
/*  85 */     while (objClass != BusinessObject.class && objClass != null) {
/*     */       
/*  87 */       String key = objClass.getName();
/*     */       
/*  89 */       if (this.m_Map.containsKey(key)) {
/*     */         
/*  91 */         Integer options = (Integer)this.m_Map.get(key);
/*  92 */         return options.intValue();
/*     */       } 
/*     */       
/*  95 */       objClass = (Class)objClass.getSuperclass();
/*     */     } 
/*     */     
/*  98 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 107 */     Object obj = super.clone();
/*     */     
/* 109 */     ObjectUtil.deepCopy(this, obj, FactoryCascadeOptions.class);
/*     */     
/* 111 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 116 */     ExternalizationUtil.writeExternal(this.m_Map, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 121 */     ExternalizationUtil.readExternal(this.m_Map, in);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\FactoryCascadeOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */