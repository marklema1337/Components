/*    */ package com.lbs.cache;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ICachedHashTable;
/*    */ import java.util.Enumeration;
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
/*    */ public class LbsNonCachedHashtable<T, V>
/*    */   implements ICachedHashTable<T, V>
/*    */ {
/* 19 */   private Hashtable<T, V> m_Hashtable = new Hashtable<>();
/* 20 */   private String m_InstanceName = "NonCachedHashtable" + hashCode();
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsNonCachedHashtable() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsNonCachedHashtable(String instanceName) {
/* 29 */     this.m_InstanceName = String.valueOf(instanceName) + hashCode();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public V get(T key) {
/* 35 */     return this.m_Hashtable.get(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public V put(T key, V object) {
/* 41 */     return this.m_Hashtable.put(key, object);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(T key) {
/* 47 */     this.m_Hashtable.remove(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void clear() {
/* 53 */     this.m_Hashtable.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Enumeration<T> keys() {
/* 59 */     return this.m_Hashtable.keys();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getInstanceName() {
/* 65 */     return this.m_InstanceName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsKey(T key) {
/* 71 */     return this.m_Hashtable.containsKey(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsValue(V value) {
/* 77 */     return this.m_Hashtable.containsValue(value);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 83 */     return this.m_Hashtable.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 89 */     return this.m_Hashtable.isEmpty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsNonCachedHashtable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */