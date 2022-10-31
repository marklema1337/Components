/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import java.util.HashMap;
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
/*    */ public class QueryProperties
/*    */ {
/* 17 */   HashMap<String, Object> m_Properties = new HashMap<>();
/*    */   
/*    */   public void addProperty(String key, Object value) {
/* 20 */     this.m_Properties.put(key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getProperty(String key) {
/* 25 */     return this.m_Properties.get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeProperty(String key) {
/* 30 */     this.m_Properties.remove(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */