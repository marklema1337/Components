/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import com.lbs.data.Identifier;
/*    */ import com.lbs.platform.interfaces.ICacheManager;
/*    */ import com.lbs.util.LbsClassInstanceProvider;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ public class ParameterSchemaRegistry
/*    */ {
/*    */   public static boolean isInitForCache() {
/* 25 */     return ((ParameterSchemaRegistryFieldHolder.getInstance()).ms_CacheManager != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initForCache(ICacheManager cacheManager) {
/* 30 */     if ((ParameterSchemaRegistryFieldHolder.getInstance()).ms_CacheManager != cacheManager) {
/*    */       
/* 32 */       (ParameterSchemaRegistryFieldHolder.getInstance()).ms_CacheManager = cacheManager;
/*    */       
/* 34 */       Collection<ParameterSchemaContainer> containers = (ParameterSchemaRegistryFieldHolder.getInstance()).ms_Containers.values();
/* 35 */       for (ParameterSchemaContainer container : containers)
/*    */       {
/* 37 */         container.initForCache((ParameterSchemaRegistryFieldHolder.getInstance()).ms_CacheManager);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initializeParameters(boolean checkWrappers) throws ParameterSchemaException {
/* 44 */     Collection<ParameterSchemaContainer> cons = (ParameterSchemaRegistryFieldHolder.getInstance()).ms_Containers.values();
/* 45 */     for (ParameterSchemaContainer container : cons)
/*    */     {
/* 47 */       container.initializeParameters(checkWrappers);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addSchema(ParameterSchema param) {
/* 53 */     Identifier identifier = param.getIdentifier();
/* 54 */     if (identifier.hasGuid()) {
/* 55 */       getContainer(identifier.getGuid()).addSchema(param);
/*    */     } else {
/* 57 */       getApplicationContainer().addSchema(param);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static ParameterSchemaContainer getContainer(String guid) {
/* 62 */     if (guid == null)
/* 63 */       guid = ""; 
/* 64 */     ParameterSchemaContainer container = (ParameterSchemaContainer)(ParameterSchemaRegistryFieldHolder.getInstance()).ms_Containers.get(guid);
/* 65 */     if (container == null) {
/*    */       
/* 67 */       container = new ParameterSchemaContainer(guid);
/* 68 */       container.initForCache((ParameterSchemaRegistryFieldHolder.getInstance()).ms_CacheManager);
/* 69 */       (ParameterSchemaRegistryFieldHolder.getInstance()).ms_Containers.put(guid, container);
/*    */     } 
/* 71 */     return container;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ParameterSchemaContainer getApplicationContainer() {
/* 76 */     return getContainer("");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ParameterSchema findParameter(Identifier identifier) {
/* 82 */     ParameterSchemaContainer container = (ParameterSchemaContainer)(ParameterSchemaRegistryFieldHolder.getInstance()).ms_Containers.get(identifier.getGuid());
/* 83 */     if (container == null)
/* 84 */       return null; 
/* 85 */     return container.findParameter(identifier.getId());
/*    */   }
/*    */   
/*    */   public static class ParameterSchemaRegistryFieldHolder
/*    */   {
/* 90 */     private Hashtable<String, ParameterSchemaContainer> ms_Containers = new Hashtable<>();
/*    */     
/*    */     private ICacheManager ms_CacheManager;
/*    */     
/*    */     private static ParameterSchemaRegistryFieldHolder getInstance() {
/* 95 */       return (ParameterSchemaRegistryFieldHolder)LbsClassInstanceProvider.getInstanceByClass(ParameterSchemaRegistryFieldHolder.class);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterSchemaRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */