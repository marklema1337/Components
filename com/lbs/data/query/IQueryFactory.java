/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.factory.IFactorySearchConstants;
/*    */ import com.lbs.data.factory.INamedVariables;
/*    */ import com.lbs.data.objects.ObjectMode;
/*    */ import com.lbs.invoke.SessionReestablishedException;
/*    */ import com.lbs.invoke.SessionTimeoutException;
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
/*    */ 
/*    */ public interface IQueryFactory
/*    */   extends IFactorySearchConstants, IQueryNavigation, IQueryHelpers, IQueryAtomicOperations, IQueryFactoryEvents
/*    */ {
/* 24 */   public static final QueryProperties m_QueryProperties = new QueryProperties();
/*    */   
/*    */   QueryParams prepareParams(QueryParams paramQueryParams) throws QueryFactoryException;
/*    */   
/*    */   INamedVariables getVariables();
/*    */   
/*    */   void setVariables(INamedVariables paramINamedVariables);
/*    */   
/*    */   ObjectMode resolveQueryObjectMode(QueryBusinessObject paramQueryBusinessObject) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
/*    */   
/*    */   QueryProperties getQueryProperties();
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */