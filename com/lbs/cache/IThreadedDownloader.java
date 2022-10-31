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
/*    */ public interface IThreadedDownloader<K, V>
/*    */ {
/*    */   JLbsDownloadedItem<K, V> feedMe();
/*    */   
/*    */   public static class JLbsDownloadedItem<K, V>
/*    */   {
/*    */     private K m_Key;
/*    */     private V m_Item;
/*    */     private String m_Group;
/*    */     
/*    */     public JLbsDownloadedItem(K key, V item, String group) {
/* 24 */       this.m_Key = key;
/* 25 */       this.m_Item = item;
/* 26 */       this.m_Group = group;
/*    */     }
/*    */ 
/*    */     
/*    */     public JLbsDownloadedItem(K key, V item) {
/* 31 */       this(key, item, "DefaultGroup");
/*    */     }
/*    */ 
/*    */     
/*    */     public K getKey() {
/* 36 */       return this.m_Key;
/*    */     }
/*    */ 
/*    */     
/*    */     public V getItem() {
/* 41 */       return this.m_Item;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getGroup() {
/* 46 */       return this.m_Group;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\IThreadedDownloader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */