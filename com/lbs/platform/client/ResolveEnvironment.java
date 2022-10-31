/*    */ package com.lbs.platform.client;
/*    */ 
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ import com.lbs.platform.interfaces.IResolveEnvironment;
/*    */ import com.lbs.platform.interfaces.IResolverHolder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResolveEnvironment
/*    */   implements IResolveEnvironment
/*    */ {
/*    */   private IApplicationContext m_Context;
/*    */   private IResolverHolder.IResolveListener m_Listener;
/*    */   private boolean m_UseGivenPrimaryKeys;
/*    */   private boolean m_TraverseLinkObjects;
/*    */   
/*    */   public ResolveEnvironment(IApplicationContext context, boolean traverseLinkObjects, IResolverHolder.IResolveListener listener, boolean useGivenPrimaryKeys) {
/* 21 */     this.m_Context = context;
/* 22 */     this.m_Listener = listener;
/* 23 */     this.m_UseGivenPrimaryKeys = useGivenPrimaryKeys;
/* 24 */     this.m_TraverseLinkObjects = traverseLinkObjects;
/*    */   }
/*    */ 
/*    */   
/*    */   public IApplicationContext getContext() {
/* 29 */     return this.m_Context;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContext(IApplicationContext context) {
/* 34 */     this.m_Context = context;
/*    */   }
/*    */ 
/*    */   
/*    */   public IResolverHolder.IResolveListener getListener() {
/* 39 */     return this.m_Listener;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListener(IResolverHolder.IResolveListener listener) {
/* 44 */     this.m_Listener = listener;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUseGivenPrimaryKeys() {
/* 49 */     return this.m_UseGivenPrimaryKeys;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUseGivenPrimaryKeys(boolean useGivenPrimaryKeys) {
/* 54 */     this.m_UseGivenPrimaryKeys = useGivenPrimaryKeys;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTraverseLinkObjects() {
/* 59 */     return this.m_TraverseLinkObjects;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTraverseLinkObjects(boolean traverseLinkObjects) {
/* 64 */     this.m_TraverseLinkObjects = traverseLinkObjects;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\ResolveEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */