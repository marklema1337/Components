/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ public abstract class LbsClassInstanceProvider
/*     */ {
/*   7 */   private static LbsClassInstanceProvider defaultProvider = new LbsClassInstanceProviderDefaultImpl();
/*     */   
/*     */   private static LbsClassInstanceProvider provider;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  14 */       Class.forName("com.vaadin.ui.UI");
/*  15 */       Class<?> cls = Class.forName("com.lbs.util.LbsClassInstanceProviderVaadin");
/*  16 */       Object object = cls.newInstance();
/*  17 */       provider = (LbsClassInstanceProvider)object;
/*     */     }
/*  19 */     catch (Throwable e) {
/*     */       
/*  21 */       System.out.println("Provider class not found. using default provider.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getInstanceByClass(Class<T> clazz) {
/*     */     T instance;
/*  31 */     if (provider != null) {
/*     */       
/*  33 */       instance = provider.getInstance(clazz);
/*  34 */       if (instance == null)
/*     */       {
/*  36 */         instance = defaultProvider.getInstance(clazz);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  41 */       instance = defaultProvider.getInstance(clazz);
/*     */     } 
/*     */     
/*  44 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract <T> T getInstance(Class<T> paramClass);
/*     */   
/*     */   public static boolean isRemoteCall() {
/*  51 */     if (provider != null)
/*     */     {
/*  53 */       return provider.hasRemoteCall();
/*     */     }
/*     */ 
/*     */     
/*  57 */     return defaultProvider.hasRemoteCall();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void incrementRemoteCallCounter() {
/*  63 */     incrementRemoteCallCounter(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void decrementRemoteCallCounter() {
/*  68 */     decrementRemoteCallCounter(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T executeRemoteCall(Callable<T> callable) {
/*  73 */     incrementRemoteCallCounter();
/*     */     
/*     */     try {
/*  76 */       return callable.call();
/*     */     }
/*  78 */     catch (RuntimeException e) {
/*     */       
/*  80 */       throw e;
/*     */     }
/*  82 */     catch (Exception e) {
/*     */       
/*  84 */       throw new RuntimeException(e);
/*     */     }
/*     */     finally {
/*     */       
/*  88 */       decrementRemoteCallCounter();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void incrementRemoteCallCounter(boolean force) {
/*  94 */     if (!force && !JLbsConstants.ENABLE_SERVER_CLASS_INSTRUMENTATION) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     if (provider != null) {
/*     */       
/* 100 */       provider.beginRemoteCall();
/*     */     }
/*     */     else {
/*     */       
/* 104 */       defaultProvider.beginRemoteCall();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void decrementRemoteCallCounter(boolean force) {
/* 110 */     if (!force && !JLbsConstants.ENABLE_SERVER_CLASS_INSTRUMENTATION) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (provider != null) {
/*     */       
/* 117 */       provider.endRemoteCall();
/*     */     }
/*     */     else {
/*     */       
/* 121 */       defaultProvider.endRemoteCall();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void beginRemoteCall();
/*     */   
/*     */   protected abstract void endRemoteCall();
/*     */   
/*     */   protected abstract boolean hasRemoteCall();
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsClassInstanceProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */