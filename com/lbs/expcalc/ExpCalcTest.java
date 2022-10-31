/*    */ package com.lbs.expcalc;
/*    */ 
/*    */ import com.lbs.util.JLbsStringListEx;
/*    */ import java.math.BigDecimal;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpCalcTest
/*    */ {
/*    */   public static void main(String[] args) {
/* 25 */     System.out.println("Testing expression calculator...");
/*    */     
/* 27 */     LbsExpCalcTestAdapter adapter = new LbsExpCalcTestAdapter();
/*    */     
/* 29 */     LbsExpCalculator m_ExpCalc = new LbsExpCalculator(adapter, null);
/*    */     
/* 31 */     String exp = "12*MYNUMFUNC(10,20,\"Deneme\")";
/*    */     
/* 33 */     BigDecimal b = m_ExpCalc.numericExpression(exp);
/*    */     
/* 35 */     System.out.println("Result for " + exp + "  is " + b);
/*    */     
/* 37 */     JLbsStringListEx list = m_ExpCalc.getFunctionList();
/* 38 */     if (list != null)
/* 39 */       list = null; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\expcalc\ExpCalcTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */