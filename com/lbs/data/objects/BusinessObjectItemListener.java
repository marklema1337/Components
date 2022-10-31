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
/*    */ public class BusinessObjectItemListener
/*    */   extends BusinessObjectMemberListener
/*    */ {
/*    */   public BusinessObjectItemListener(IBusinessObjectChangeListener listener, String memberName) {
/* 15 */     super(listener, memberName);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMemberFullPath() {
/* 20 */     int idx = ((BusinessObjectCollectionListener)this.m_InnerListener).getItemIndex(this);
/* 21 */     String itemName = "item[" + idx + "]";
/* 22 */     return this.m_InnerListener.getMemberFullPath() + "." + itemName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */