/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class JLbsNameValueMap
/*    */   implements Serializable
/*    */ {
/* 17 */   protected Hashtable m_Table = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public boolean setValue(String name, Object value) {
/* 21 */     if (name != null && name.length() > 0) {
/*    */       
/* 23 */       if (value == null)
/* 24 */         value = new HashNullValue(); 
/* 25 */       this.m_Table.put(name, value);
/*    */     } 
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue(String name) {
/* 32 */     return (name != null && name.length() > 0) ? 
/* 33 */       this.m_Table.get(name) : 
/* 34 */       null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValueEx(String name) throws NotFoundException {
/* 39 */     if (name != null && name.length() > 0) {
/*    */       
/* 41 */       if (!this.m_Table.containsKey(name))
/* 42 */         throw new NotFoundException(name); 
/* 43 */       Object o = this.m_Table.get(name);
/* 44 */       if (o instanceof HashNullValue)
/* 45 */         return null; 
/* 46 */       return o;
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable getTable() {
/* 53 */     return this.m_Table;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeValue(String name) {
/* 58 */     if (name != null && name.length() > 0)
/*    */     {
/* 60 */       if (this.m_Table.get(name) != null) {
/* 61 */         this.m_Table.remove(name);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public Enumeration keys() {
/* 67 */     return this.m_Table.keys();
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 72 */     return this.m_Table.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsNameValueMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */