/*    */ package com.lbs.remoting.matchers;
/*    */ 
/*    */ import com.lbs.remoting.annotations.LbsRemotingIgnoreMethod;
/*    */ import net.bytebuddy.description.annotation.AnnotationDescription;
/*    */ import net.bytebuddy.description.method.MethodDescription;
/*    */ import net.bytebuddy.matcher.ElementMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsRemotingMethodMatcher
/*    */   implements ElementMatcher<MethodDescription>
/*    */ {
/*    */   public boolean matches(MethodDescription target) {
/* 14 */     if (target.isPrivate()) {
/*    */       
/* 16 */       System.out.println("ignored private method: " + target.toString());
/* 17 */       return false;
/*    */     } 
/*    */     
/* 20 */     for (AnnotationDescription annotationDescription : target.getDeclaredAnnotations()) {
/*    */       
/* 22 */       if (annotationDescription.getAnnotationType().getTypeName().equals(LbsRemotingIgnoreMethod.class.getName())) {
/*    */         
/* 24 */         System.out.println("ignorable method found: " + target.toString());
/* 25 */         return false;
/*    */       } 
/*    */     } 
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoting\matchers\LbsRemotingMethodMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */