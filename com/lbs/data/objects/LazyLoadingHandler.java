/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.data.IDataContext;
/*    */ import com.lbs.data.factory.FactoryException;
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
/*    */ public class LazyLoadingHandler
/*    */   implements IObjectLoadingHandler
/*    */ {
/*    */   protected LazyLoadingData m_LoadingData;
/*    */   protected String m_PropertyName;
/*    */   protected BusinessObject m_ParentObject;
/*    */   
/*    */   public LazyLoadingHandler(BusinessObject parent, String propertyName) {
/* 23 */     this.m_ParentObject = parent;
/* 24 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LazyLoadingHandler(LazyLoadingHandler parentHandler, String propertyName) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public IObjectLoadingData getLoadingData() {
/* 34 */     return this.m_LoadingData;
/*    */   }
/*    */ 
/*    */   
/*    */   public BusinessObject getParentObject() {
/* 39 */     return this.m_ParentObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 44 */     return this.m_PropertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load() throws FactoryException {
/* 49 */     IDataContext context = this.m_LoadingData.getContext();
/*    */     
/* 51 */     if (context == null)
/*    */       return; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\LazyLoadingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */