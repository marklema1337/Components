/*    */ package com.lbs.cache;
/*    */ 
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
/*    */ public class JLbsCacheInfoHive
/*    */ {
/*    */   private static JLbsCacheInfoHive ms_CacheInfoHive;
/* 19 */   private Hashtable m_KnownCustomizationVersions = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static JLbsCacheInfoHive getInstance() {
/* 27 */     if (ms_CacheInfoHive == null)
/* 28 */       ms_CacheInfoHive = new JLbsCacheInfoHive(); 
/* 29 */     return ms_CacheInfoHive;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putCustomizationVersion(String guid, String version) {
/* 35 */     if (this.m_KnownCustomizationVersions == null)
/* 36 */       this.m_KnownCustomizationVersions = new Hashtable<>(); 
/* 37 */     this.m_KnownCustomizationVersions.put(guid, version);
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable getKnownCustomizationVersions() {
/* 42 */     return (Hashtable)this.m_KnownCustomizationVersions.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCacheInfoHive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */