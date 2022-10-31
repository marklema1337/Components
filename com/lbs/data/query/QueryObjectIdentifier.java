/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BasicObjectIdentifier;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.ValueConverter;
/*     */ import java.util.Arrays;
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
/*     */ public class QueryObjectIdentifier
/*     */   extends BasicObjectIdentifier
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected Object[] m_KeyValues;
/*     */   protected transient Object m_AssociatedData;
/*     */   
/*     */   public void update(QueryBusinessObject obj) {
/*  34 */     this.m_KeyValues = collectKeyValues(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   private Object[] collectKeyValues(QueryBusinessObject obj) {
/*  39 */     Object[] keyValues = new Object[obj.m_KeyNames.size()];
/*     */     int i;
/*  41 */     for (i = 0; i < obj.m_KeyNames.size(); i++) {
/*  42 */       keyValues[i] = obj.getProperties().getValue(obj.m_KeyNames.getItem(i));
/*     */     }
/*  44 */     if (keyValues.length == 0) {
/*     */       
/*  46 */       i = -1;
/*     */       
/*     */       try {
/*  49 */         i = obj.getProperties().getInt("CustomUniqueIdentifier");
/*     */       }
/*  51 */       catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */       
/*  55 */       if (i > 0) {
/*     */         
/*  57 */         keyValues = new Object[1];
/*  58 */         keyValues[0] = Integer.valueOf(i);
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     return keyValues;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean compareKeyValues(Object[] keyValues1, Object[] keyValues2) {
/*  67 */     if (keyValues1.length != keyValues2.length) {
/*  68 */       return false;
/*     */     }
/*  70 */     for (int i = 0; i < keyValues1.length; i++) {
/*     */       
/*  72 */       if (!ObjectUtil.areEqual(keyValues1[i], keyValues2[i])) {
/*  73 */         return false;
/*     */       }
/*     */     } 
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  83 */     Object[] keyValues = null;
/*  84 */     if (obj instanceof QueryObjectIdentifier) {
/*  85 */       keyValues = ((QueryObjectIdentifier)obj).m_KeyValues;
/*     */     }
/*  87 */     if (obj instanceof Object[]) {
/*  88 */       keyValues = (Object[])obj;
/*     */     }
/*  90 */     if (obj instanceof QueryBusinessObject) {
/*  91 */       keyValues = collectKeyValues((QueryBusinessObject)obj);
/*     */     }
/*  93 */     if (keyValues != null) {
/*  94 */       return compareKeyValues(keyValues, this.m_KeyValues);
/*     */     }
/*  96 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 102 */     if (this.m_KeyValues != null)
/*     */     {
/* 104 */       return Arrays.hashCode(this.m_KeyValues);
/*     */     }
/* 106 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 116 */     sb.append("{");
/* 117 */     for (int i = 0; i < this.m_KeyValues.length; i++) {
/*     */       
/* 119 */       sb.append(this.m_KeyValues[i]);
/*     */       
/* 121 */       if (i < this.m_KeyValues.length - 1)
/* 122 */         sb.append(", "); 
/*     */     } 
/* 124 */     sb.append("}");
/*     */     
/* 126 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSimpleKey() {
/* 134 */     if (this.m_KeyValues.length > 0) {
/*     */       
/*     */       try {
/*     */         
/* 138 */         Integer i = (Integer)ValueConverter.convert(Integer.class, this.m_KeyValues[0]);
/* 139 */         return i.intValue();
/*     */       }
/* 141 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 146 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSimpleKey(int key) {
/* 154 */     if (this.m_KeyValues.length == 0)
/* 155 */       this.m_KeyValues = new Object[1]; 
/* 156 */     this.m_KeyValues[0] = Integer.valueOf(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(BasicBusinessObject obj) {
/* 164 */     BasicObjectIdentifier uniqueIdentifier = obj.getUniqueIdentifier();
/*     */     
/* 166 */     if (uniqueIdentifier != null && uniqueIdentifier.getSimpleKey() < 0) {
/*     */       
/* 168 */       int i = -1;
/*     */       
/*     */       try {
/* 171 */         i = obj.getProperties().getInt("CustomUniqueIdentifier");
/*     */       }
/* 173 */       catch (Exception exception) {}
/*     */ 
/*     */       
/* 176 */       if (i > 0)
/*     */       {
/* 178 */         uniqueIdentifier.setSimpleKey(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAssociatedData() {
/* 188 */     return this.m_AssociatedData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAssociatedData(Object data) {
/* 196 */     this.m_AssociatedData = data;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryObjectIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */