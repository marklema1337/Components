/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsClassInstanceProviderDefaultImpl
/*    */   extends LbsClassInstanceProvider
/*    */ {
/*    */   public LbsClassInstanceProviderDefaultImpl() {
/* 18 */     this.instances = new ConcurrentHashMap<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T getInstance(Class<T> instanceClass) {
/* 23 */     T instance = (T)this.instances.get(instanceClass);
/* 24 */     if (instance == null) {
/*    */       
/*    */       try {
/*    */         
/* 28 */         instance = instanceClass.newInstance();
/* 29 */         this.instances.put(instanceClass, instance);
/*    */       }
/* 31 */       catch (InstantiationException|IllegalAccessException e) {
/*    */         
/* 33 */         throw new RuntimeException(e);
/*    */       } 
/*    */     }
/* 36 */     return instance;
/*    */   } private static final ThreadLocal<AtomicInteger> counters = new ThreadLocal<AtomicInteger>() { protected AtomicInteger initialValue() {
/*    */         return new AtomicInteger(0);
/*    */       } }
/*    */   ; Map<Class<?>, Object> instances;
/*    */   protected void beginRemoteCall() {
/* 42 */     ((AtomicInteger)counters.get()).incrementAndGet();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void endRemoteCall() {
/* 48 */     ((AtomicInteger)counters.get()).decrementAndGet();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean hasRemoteCall() {
/* 54 */     return (((AtomicInteger)counters.get()).get() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsClassInstanceProviderDefaultImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */