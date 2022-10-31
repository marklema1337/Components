/*    */ package com.lbs.customization.propertygrid;
/*    */ 
/*    */ import com.lbs.customization.report.design.JLbsReportDesignerBase;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsObjectSchemaCache
/*    */ {
/* 15 */   private static JLbsObjectSchemaCache ms_Cache = new JLbsObjectSchemaCache();
/* 16 */   private HashMap<Class<? extends Object>, JLbsObjectSchema> m_HashMap = new HashMap<>();
/*    */ 
/*    */   
/*    */   public JLbsPropertyDescriptor[] getProperties(Object obj) {
/* 20 */     if (obj != null) {
/*    */       
/* 22 */       Class<? extends Object> cls = (Class)obj.getClass();
/* 23 */       if (!JLbsReportDesignerBase.m_IsLPT) {
/*    */ 
/*    */         
/* 26 */         JLbsObjectSchema cacheObj = this.m_HashMap.get(cls);
/* 27 */         if (cacheObj != null)
/* 28 */           return cacheObj.getProperties(); 
/*    */       } 
/* 30 */       JLbsObjectSchema schema = new JLbsObjectSchema(obj);
/* 31 */       if (!JLbsReportDesignerBase.m_IsLPT)
/* 32 */         this.m_HashMap.put(cls, schema); 
/* 33 */       return schema.getProperties();
/*    */     } 
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsObjectSchemaCache getDefaultCache() {
/* 40 */     return ms_Cache;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsPropertyDescriptor[] getPropertiesFromCache(Object obj) {
/* 45 */     return ms_Cache.getProperties(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void clear() {
/* 50 */     ms_Cache = new JLbsObjectSchemaCache();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\customization\propertygrid\JLbsObjectSchemaCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */