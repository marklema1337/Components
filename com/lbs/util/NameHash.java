/*    */ package com.lbs.util;
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
/*    */ public class NameHash
/*    */ {
/* 16 */   private Hashtable<String, Integer> m_Hash = new Hashtable<>();
/*    */ 
/*    */   
/*    */   private String m_ClassName;
/*    */ 
/*    */   
/*    */   public NameHash() {}
/*    */ 
/*    */   
/*    */   public NameHash(String className) {
/* 26 */     this.m_ClassName = className;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, Integer> getHash() {
/* 31 */     return this.m_Hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 36 */     return this.m_ClassName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClassName(String className) {
/* 41 */     this.m_ClassName = className;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer get(Object key) {
/* 51 */     return this.m_Hash.get(key);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer put(String key, Integer value) {
/* 62 */     return this.m_Hash.put(key, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\NameHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */