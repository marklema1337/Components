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
/*    */ public class SampleTestCache<K>
/*    */   extends JLbsCache<K, Object>
/*    */ {
/*    */   static {
/* 16 */     ms_RegisteredImplementors.put(SampleTestCache.class, new JLbsCache.JLbsCachePolicy(true, SampleTestCache.class));
/*    */   }
/*    */ 
/*    */   
/*    */   public SampleTestCache(JLbsCacheScope scope) {
/* 21 */     super(scope);
/*    */   }
/*    */ 
/*    */   
/*    */   public SampleTestCache(String nameExtension, JLbsCacheScope scope) {
/* 26 */     super(nameExtension, scope);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\SampleTestCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */