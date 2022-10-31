/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ICacheManager;
/*    */ import com.lbs.platform.interfaces.ICachedHashTable;
/*    */ import java.util.Enumeration;
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
/*    */ public class ParameterSchemaContainer
/*    */ {
/*    */   private String m_Guid;
/*    */   private ICachedHashTable<String, ParameterSchema> m_CachedParameters;
/*    */   
/*    */   public ParameterSchemaContainer(String guid) {
/* 25 */     this.m_Guid = guid;
/*    */   }
/*    */ 
/*    */   
/*    */   public ICachedHashTable<String, ParameterSchema> getParameters() {
/* 30 */     return this.m_CachedParameters;
/*    */   }
/*    */ 
/*    */   
/*    */   public ParameterSchema findParameter(String id) {
/* 35 */     return (ParameterSchema)this.m_CachedParameters.get(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addSchema(ParameterSchema param) {
/* 40 */     this.m_CachedParameters.put(param.getIdentifier().getId(), param);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeParameters(boolean checkWrappers) throws ParameterSchemaException {
/* 45 */     Enumeration<String> keys = this.m_CachedParameters.keys();
/* 46 */     while (keys.hasMoreElements()) {
/*    */       
/* 48 */       String key = keys.nextElement();
/* 49 */       ParameterSchema param = (ParameterSchema)this.m_CachedParameters.get(key);
/* 50 */       param.initialize(checkWrappers);
/* 51 */       this.m_CachedParameters.put(key, param);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initForCache(ICacheManager cacheManager) {
/* 57 */     if (cacheManager != null)
/* 58 */       this.m_CachedParameters = cacheManager.getCachedHashTable("Parameters" + this.m_Guid, String.class, ParameterSchema.class, false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterSchemaContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */