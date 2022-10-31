/*    */ package com.lbs.cache;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*    */ import com.lbs.platform.interfaces.ICacheManager;
/*    */ import com.lbs.platform.interfaces.ICachedHashTable;
/*    */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*    */ import com.lbs.platform.interfaces.ICachedList;
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
/*    */ public class LbsNonCachedCacheManager
/*    */   implements ICacheManager
/*    */ {
/*    */   public <T, V> ICachedHashTable<T, V> getCachedHashTable(String name, Class<T> keyClass, Class<V> valueClass, boolean domainAware) {
/* 24 */     return new LbsNonCachedHashtable<>(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> ICachedList<T> getCachedList(String name, Class<T> valueClass, boolean domainAware) {
/* 30 */     return (ICachedList<T>)new LbsNonCachedList(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> ICachedIdentifiableList<T> getCachedList(String name, Class<T> valueClass, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/* 37 */     return new LbsNonCachedList<>(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void releaseCachedHashTable(ICachedHashTable<?, ?> table) {
/* 43 */     if (table != null) {
/* 44 */       table.clear();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void releaseCachedList(ICachedList<?> list) {
/* 50 */     if (list != null) {
/* 51 */       list.clear();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getEHCacheManager() {
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsNonCachedCacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */