/*     */ package com.lbs.data.objects;
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
/*     */ public class BusinessObjectIdentifier
/*     */   extends BasicObjectIdentifier
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected int m_KeyValue;
/*     */   protected BusinessObject m_Object;
/*     */   protected Object m_AssociatedData;
/*     */   
/*     */   public BusinessObjectIdentifier(BusinessObject obj) {
/*  24 */     this.m_Object = obj;
/*  25 */     this.m_KeyValue = this.m_Object.getAutoIncrementValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  34 */     if (obj instanceof Integer) {
/*  35 */       return (((Integer)obj).intValue() == this.m_KeyValue);
/*     */     }
/*  37 */     if (obj instanceof BusinessObject) {
/*  38 */       return (((BusinessObject)obj).getAutoIncrementValue() == this.m_KeyValue && (this.m_Object == null || this.m_Object
/*  39 */         .getClass().equals(obj.getClass())));
/*     */     }
/*     */     
/*  42 */     if (obj instanceof BusinessObjectIdentifier) {
/*  43 */       return (((BusinessObjectIdentifier)obj).m_KeyValue == this.m_KeyValue && (this.m_Object == null || ((BusinessObjectIdentifier)obj).m_Object == null || this.m_Object
/*     */         
/*  45 */         .getClass().equals(((BusinessObjectIdentifier)obj).m_Object.getClass())));
/*     */     }
/*     */     
/*  48 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  54 */     if (this.m_Object != null)
/*     */     {
/*  56 */       return (this.m_Object.getClass().getName() + "," + this.m_KeyValue).hashCode();
/*     */     }
/*  58 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  67 */     return "{" + this.m_KeyValue + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSimpleKey() {
/*  76 */     return this.m_KeyValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSimpleKey(int key) {
/*  85 */     this.m_KeyValue = key;
/*  86 */     this.m_Object.setAutoIncrementValue(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(BasicBusinessObject obj) {
/*  92 */     if (obj instanceof BusinessObject) {
/*  93 */       this.m_KeyValue = ((BusinessObject)obj).getAutoIncrementValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getAssociatedData() {
/*  99 */     return this.m_AssociatedData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAssociatedData(Object associatedData) {
/* 104 */     this.m_AssociatedData = associatedData;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */