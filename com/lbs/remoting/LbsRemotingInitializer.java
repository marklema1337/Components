/*    */ package com.lbs.remoting;
/*    */ 
/*    */ import com.lbs.remoting.interceptors.LbsRemotingMethodInterceptor;
/*    */ import com.lbs.remoting.matchers.LbsRemotingClassMatcher;
/*    */ import com.lbs.remoting.matchers.LbsRemotingMethodMatcher;
/*    */ import com.lbs.util.JLbsConstants;
/*    */ import java.lang.instrument.Instrumentation;
/*    */ import net.bytebuddy.agent.ByteBuddyAgent;
/*    */ import net.bytebuddy.agent.builder.AgentBuilder;
/*    */ import net.bytebuddy.description.type.TypeDescription;
/*    */ import net.bytebuddy.dynamic.DynamicType;
/*    */ import net.bytebuddy.implementation.Implementation;
/*    */ import net.bytebuddy.implementation.MethodDelegation;
/*    */ import net.bytebuddy.matcher.ElementMatcher;
/*    */ import net.bytebuddy.matcher.ElementMatchers;
/*    */ import net.bytebuddy.utility.JavaModule;
/*    */ 
/*    */ public class LbsRemotingInitializer
/*    */ {
/*    */   public static void initRemotingAgent() {
/* 21 */     if (!JLbsConstants.ENABLE_SERVER_CLASS_INSTRUMENTATION) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 26 */     Instrumentation instrumentation = ByteBuddyAgent.install();
/* 27 */     AgentBuilder.Default default_ = new AgentBuilder.Default();
/* 28 */     LbsRemotingClassMatcher lbsClassMatcher = new LbsRemotingClassMatcher();
/* 29 */     LbsRemotingMethodMatcher lbsMethodMatcher = new LbsRemotingMethodMatcher();
/*    */     
/* 31 */     AgentBuilder.Identified.Narrowable narrowable = default_.type((ElementMatcher)ElementMatchers.hasAnnotation((ElementMatcher)lbsClassMatcher));
/* 32 */     AgentBuilder.Identified.Extendable extendable = narrowable.transform((builder, type, classLoader, module) -> builder.method((ElementMatcher)paramLbsRemotingMethodMatcher).intercept((Implementation)MethodDelegation.to(LbsRemotingMethodInterceptor.class)));
/*    */ 
/*    */     
/* 35 */     extendable.installOn(instrumentation);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoting\LbsRemotingInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */