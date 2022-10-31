/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusinessObjectMemberListener
/*    */   implements IBusinessObjectChangeListener
/*    */ {
/*    */   protected String m_MemberName;
/*    */   protected IBusinessObjectChangeListener m_InnerListener;
/*    */   
/*    */   public BusinessObjectMemberListener(IBusinessObjectChangeListener listener, String memberName) {
/* 20 */     this.m_MemberName = memberName;
/* 21 */     this.m_InnerListener = listener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void changed(BusinessObjectEvent e) {
/* 29 */     this.m_InnerListener.changed(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMemberName() {
/* 34 */     return this.m_MemberName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMemberFullPath() {
/* 39 */     if (this.m_InnerListener == null) {
/* 40 */       return this.m_MemberName;
/*    */     }
/* 42 */     String fullName = this.m_InnerListener.getMemberFullPath();
/*    */     
/* 44 */     if (!StringUtil.isEmpty(fullName)) {
/* 45 */       return fullName + "." + this.m_MemberName;
/*    */     }
/* 47 */     return this.m_MemberName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 53 */     if (obj instanceof BusinessObjectMemberListener) {
/*    */       
/* 55 */       BusinessObjectMemberListener listener = (BusinessObjectMemberListener)obj;
/* 56 */       if ((this.m_InnerListener == null) ? (listener.m_InnerListener == null) : this.m_InnerListener
/*    */         
/* 58 */         .equals(listener.m_InnerListener)) if (StringUtil.equals(this.m_MemberName, listener.m_MemberName));  return false;
/*    */     } 
/* 60 */     return super.equals(obj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 68 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBusinessObjectChangeListener getInnerListener() {
/* 73 */     return this.m_InnerListener;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectMemberListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */