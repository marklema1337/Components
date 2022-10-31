/*    */ package com.lbs.cache;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
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
/*    */ public class LbsNonCachedList<T>
/*    */   implements ICachedIdentifiableList<T>
/*    */ {
/* 19 */   private ArrayList<T> m_List = new ArrayList<>();
/* 20 */   private String m_InstanceName = "NonCachedList" + hashCode();
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsNonCachedList() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsNonCachedList(String instanceName) {
/* 29 */     this.m_InstanceName = String.valueOf(instanceName) + hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public T get(int index) throws IndexOutOfBoundsException {
/* 34 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(T object) {
/* 39 */     this.m_List.add(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addAll(Collection<? extends T> o) {
/* 44 */     this.m_List.addAll(o);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 49 */     this.m_List.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInstanceName() {
/* 54 */     return this.m_InstanceName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 59 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(int index) {
/* 64 */     this.m_List.remove(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean contains(T object) {
/* 69 */     return this.m_List.contains(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public int indexOf(T object) {
/* 74 */     return this.m_List.indexOf(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(int index, T object) {
/* 79 */     this.m_List.set(index, object);
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(T object) {
/* 84 */     this.m_List.remove(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object[] toArray() {
/* 89 */     return this.m_List.toArray();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 94 */     return (this.m_List.size() == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsNonCachedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */