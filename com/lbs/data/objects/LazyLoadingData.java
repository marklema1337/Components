/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.data.IDataContext;
/*    */ import com.lbs.data.factory.FactoryParams;
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
/*    */ public class LazyLoadingData
/*    */   implements IObjectLoadingData
/*    */ {
/*    */   protected FactoryParams m_FactoryParams;
/*    */   protected IDataContext m_Context;
/*    */   
/*    */   public LazyLoadingData(IDataContext context, FactoryParams params) {
/* 22 */     this.m_Context = context;
/* 23 */     this.m_FactoryParams = params;
/*    */   }
/*    */ 
/*    */   
/*    */   public FactoryParams getFactoryParams() {
/* 28 */     return this.m_FactoryParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFactoryParams(FactoryParams factoryParams) {
/* 33 */     this.m_FactoryParams = factoryParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDataContext getContext() {
/* 38 */     return this.m_Context;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContext(IDataContext context) {
/* 43 */     this.m_Context = context;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\LazyLoadingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */