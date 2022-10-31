/*    */ package com.lbs.remoting.matchers;
/*    */ 
/*    */ import com.lbs.remoting.annotations.LbsRemotingClass;
/*    */ import net.bytebuddy.description.annotation.AnnotationDescription;
/*    */ import net.bytebuddy.matcher.ElementMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsRemotingClassMatcher
/*    */   implements ElementMatcher<AnnotationDescription>
/*    */ {
/*    */   public boolean matches(AnnotationDescription target) {
/* 13 */     if (target.getAnnotationType().getTypeName().equals(LbsRemotingClass.class.getName()))
/*    */     {
/* 15 */       return true;
/*    */     }
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoting\matchers\LbsRemotingClassMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */