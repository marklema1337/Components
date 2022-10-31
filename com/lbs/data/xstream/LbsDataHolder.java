/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.DataHolder;
/*    */ import java.util.Hashtable;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class LbsDataHolder
/*    */   implements DataHolder
/*    */ {
/* 11 */   private Map<Object, Object> map = new Hashtable<>();
/*    */   
/*    */   public void put(Object key, Object value) {
/* 14 */     this.map.put(key, value);
/*    */   }
/*    */   
/*    */   public Iterator keys() {
/* 18 */     return this.map.keySet().iterator();
/*    */   }
/*    */   
/*    */   public Object get(Object key) {
/* 22 */     return this.map.get(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsDataHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */