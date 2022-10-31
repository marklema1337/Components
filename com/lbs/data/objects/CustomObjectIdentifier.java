/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.StringUtil;
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
/*     */ public class CustomObjectIdentifier
/*     */   extends BasicObjectIdentifier
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected int m_KeyValue;
/*     */   protected Object m_AssociatedData;
/*     */   protected CustomBusinessObject m_Object;
/*     */   
/*     */   public CustomObjectIdentifier(CustomBusinessObject obj) {
/*  24 */     this.m_Object = obj;
/*     */     
/*  26 */     getObjectKey(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   private void getObjectKey(CustomBusinessObject obj) {
/*  31 */     if (!StringUtil.isEmpty(obj.m_KeyPropertyName)) {
/*     */       
/*  33 */       String propName = obj.m_KeyPropertyName;
/*  34 */       if (obj.getProperties().containsProperty(propName)) {
/*     */         
/*     */         try {
/*     */           
/*  38 */           this.m_KeyValue = obj.getInt(propName);
/*     */         }
/*  40 */         catch (Exception e) {
/*     */           
/*  42 */           LbsConsole.getLogger("Data.Client.CustomObjectIdentifier").error(null, e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSimpleKey() {
/*  55 */     return this.m_KeyValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSimpleKey(int key) {
/*  63 */     this.m_KeyValue = key;
/*     */     
/*  65 */     if (!StringUtil.isEmpty(this.m_Object.m_KeyPropertyName))
/*     */     {
/*  67 */       this.m_Object.set(this.m_Object.m_KeyPropertyName, key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  76 */     if (obj instanceof Integer) {
/*  77 */       return (((Integer)obj).intValue() == this.m_KeyValue);
/*     */     }
/*  79 */     if (obj instanceof CustomBusinessObject) {
/*  80 */       return (((CustomBusinessObject)obj).getUniqueIdentifier().getSimpleKey() == this.m_KeyValue);
/*     */     }
/*  82 */     if (obj instanceof CustomObjectIdentifier) {
/*  83 */       return (((CustomObjectIdentifier)obj).m_KeyValue == this.m_KeyValue);
/*     */     }
/*  85 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  91 */     if (this.m_Object != null)
/*     */     {
/*  93 */       return ("" + this.m_KeyValue).hashCode();
/*     */     }
/*  95 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     return "{" + this.m_KeyValue + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(BasicBusinessObject obj) {
/* 111 */     if (obj instanceof CustomBusinessObject) {
/* 112 */       getObjectKey((CustomBusinessObject)obj);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAssociatedData() {
/* 120 */     return this.m_AssociatedData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAssociatedData(Object data) {
/* 128 */     this.m_AssociatedData = data;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\CustomObjectIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */