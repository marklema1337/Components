/*    */ package com.lbs.data;
/*    */ 
/*    */ import com.lbs.data.factory.IObjectFactory;
/*    */ import com.lbs.data.query.IQueryFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientDataContext
/*    */   implements IDataContext
/*    */ {
/*    */   private IQueryFactory m_QueryFactory;
/*    */   private IObjectFactory m_ObjectFactory;
/*    */   
/*    */   public ClientDataContext(IObjectFactory objFactory, IQueryFactory qryFactory) {
/* 21 */     this.m_ObjectFactory = objFactory;
/* 22 */     this.m_QueryFactory = qryFactory;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IObjectFactory getObjectFactory() {
/* 30 */     return this.m_ObjectFactory;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IQueryFactory getQueryFactory() {
/* 38 */     return this.m_QueryFactory;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\ClientDataContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */