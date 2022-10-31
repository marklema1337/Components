/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusinessObjectCollectionListener
/*    */   extends BusinessObjectMemberListener
/*    */ {
/*    */   private SimpleBusinessObjects m_List;
/*    */   
/*    */   public BusinessObjectCollectionListener(IBusinessObjectChangeListener listener, String memberName) {
/* 17 */     super(listener, memberName);
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleBusinessObjects getList() {
/* 22 */     return this.m_List;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setList(SimpleBusinessObjects list) {
/* 27 */     this.m_List = list;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getItemIndex(IBusinessObjectChangeListener listener) {
/* 33 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 35 */       SimpleBusinessObject obj = this.m_List.get(i);
/*    */       
/* 37 */       if (obj.containsChangeListener(listener)) {
/* 38 */         return i;
/*    */       }
/*    */     } 
/* 41 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     if (obj instanceof BusinessObjectCollectionListener) {
/*    */       
/* 49 */       BusinessObjectCollectionListener listener = (BusinessObjectCollectionListener)obj;
/* 50 */       return (listener.m_List == this.m_List && super.equals(obj));
/*    */     } 
/* 52 */     return super.equals(obj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 60 */     return super.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectCollectionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */