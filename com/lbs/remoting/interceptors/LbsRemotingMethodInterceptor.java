/*    */ package com.lbs.remoting.interceptors;
/*    */ 
/*    */ import com.lbs.util.LbsClassInstanceProvider;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.concurrent.Callable;
/*    */ import net.bytebuddy.implementation.bind.annotation.Origin;
/*    */ import net.bytebuddy.implementation.bind.annotation.RuntimeType;
/*    */ import net.bytebuddy.implementation.bind.annotation.SuperCall;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsRemotingMethodInterceptor
/*    */ {
/*    */   @RuntimeType
/*    */   public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
/*    */     try {
/* 19 */       LbsClassInstanceProvider.incrementRemoteCallCounter();
/* 20 */       return callable.call();
/*    */     }
/*    */     finally {
/*    */       
/* 24 */       LbsClassInstanceProvider.decrementRemoteCallCounter();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoting\interceptors\LbsRemotingMethodInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */