/*    */ package com.lbs.cache;
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
/*    */ public class JLbsPersistentCache<K, V>
/*    */   extends JLbsCache<K, V>
/*    */ {
/*    */   public JLbsPersistentCache(JLbsCacheScope scope) {
/* 16 */     this("", scope);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsPersistentCache(String nameExtension, JLbsCacheScope scope) {
/* 21 */     super(nameExtension, scope);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void destroy() {
/* 29 */     super.destroy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void shutdown() {
/* 37 */     super.shutdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsPersistentCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */