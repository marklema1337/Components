/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BasicObjectIdentifier
/*    */   implements Serializable, ILbsKeyOrder, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 21 */   private transient Hashtable m_ClientProperties = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String CUSTOM_UNIQUE_IDENTIFIER = "CustomUniqueIdentifier";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   private int m_Order = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Hashtable getClientProperties() {
/* 40 */     if (this.m_ClientProperties == null) {
/* 41 */       this.m_ClientProperties = new Hashtable<>();
/*    */     }
/* 43 */     return this.m_ClientProperties;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putClientProperty(Object key, Object value) {
/* 48 */     if (value == null) {
/*    */       return;
/*    */     }
/* 51 */     getClientProperties().put(key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClientProperty(Object key) {
/* 56 */     if (this.m_ClientProperties != null) {
/* 57 */       return this.m_ClientProperties.get(key);
/*    */     }
/* 59 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrder(int order) {
/* 64 */     this.m_Order = order;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOrder() {
/* 69 */     return this.m_Order;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(ILbsKeyOrder o) {
/* 74 */     return this.m_Order - o.getOrder();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 80 */     Object obj = super.clone();
/*    */     
/* 82 */     BasicObjectIdentifier bobj = (BasicObjectIdentifier)obj;
/*    */     
/* 84 */     if (this.m_ClientProperties != null)
/* 85 */       bobj.m_ClientProperties = (Hashtable)this.m_ClientProperties.clone(); 
/* 86 */     bobj.m_Order = this.m_Order;
/*    */     
/* 88 */     return obj;
/*    */   }
/*    */   
/*    */   public abstract int getSimpleKey();
/*    */   
/*    */   public abstract void setSimpleKey(int paramInt);
/*    */   
/*    */   public abstract void update(BasicBusinessObject paramBasicBusinessObject);
/*    */   
/*    */   public abstract Object getAssociatedData();
/*    */   
/*    */   public abstract void setAssociatedData(Object paramObject);
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BasicObjectIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */