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
/*    */ 
/*    */ public final class JLbsManuallyControlledCache<K, V>
/*    */   extends JLbsCache<K, V>
/*    */ {
/*    */   public JLbsManuallyControlledCache(JLbsCacheScope scope) {
/* 17 */     this("", scope);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsManuallyControlledCache(String nameExtension, JLbsCacheScope scope) {
/* 22 */     super(nameExtension, scope);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void destroy() {
/* 30 */     super.destroy();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsManuallyControlledCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */