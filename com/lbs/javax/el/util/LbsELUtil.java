/*    */ package com.lbs.javax.el.util;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ExpressionFactory;
/*    */ import com.lbs.javax.el.ValueExpression;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ import java.util.Properties;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsELUtil
/*    */ {
/*    */   private ELContext m_ELContext;
/*    */   private ExpressionFactory m_ExpressionFactory;
/*    */   
/*    */   public LbsELUtil(IApplicationContext context) {
/* 27 */     this.m_ELContext = (ELContext)new LbsELContext(context);
/* 28 */     Properties properties = new Properties();
/* 29 */     properties.setProperty("javax.el.nullProperties", "true");
/* 30 */     this.m_ExpressionFactory = ExpressionFactory.newInstance(properties);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValueExpression createValueExpression(String expression) {
/* 35 */     return createValueExpression(expression, Object.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValueExpression createValueExpression(String expression, Class<?> expectedType) {
/* 40 */     return this.m_ExpressionFactory.createValueExpression(this.m_ELContext, expression, expectedType);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\e\\util\LbsELUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */