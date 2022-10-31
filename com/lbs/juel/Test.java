/*    */ package com.lbs.juel;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ExpressionFactory;
/*    */ import com.lbs.javax.el.ValueExpression;
/*    */ import com.lbs.juel.util.SimpleContext;
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
/*    */ public class Test
/*    */ {
/*    */   public static void main(String[] args) {
/* 20 */     SimpleContext elContext = new SimpleContext();
/* 21 */     ExpressionFactory factory = ExpressionFactory.newInstance();
/* 22 */     ValueExpression expression = factory.createValueExpression((ELContext)elContext, "Deneme", Object.class);
/* 23 */     System.out.println(expression);
/* 24 */     System.out.println(expression.getValue((ELContext)elContext));
/* 25 */     expression = factory.createValueExpression((ELContext)elContext, "1", Object.class);
/* 26 */     System.out.println(expression);
/* 27 */     System.out.println(expression.getValue((ELContext)elContext));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */