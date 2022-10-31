/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import java.awt.ComponentOrientation;
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
/*    */ public class JLbsCultureInfoTRRL
/*    */   extends JLbsCultureInfoTRTR
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public ComponentOrientation getComponentOrientation() {
/* 20 */     return ComponentOrientation.RIGHT_TO_LEFT;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 25 */     JLbsCultureInfoTRRL trl = new JLbsCultureInfoTRRL();
/* 26 */     System.out.println(trl.getNumberSpelled(new BigDecimal(1215), false));
/* 27 */     System.out.println(trl.getNumberSpelled(new BigDecimal(1001), false));
/* 28 */     System.out.println(trl.getNumberSpelled(new BigDecimal(11), false));
/* 29 */     System.out.println(trl.getNumberSpelled(new BigDecimal(1215.45D), true));
/* 30 */     System.out.println(trl.getNumberSpelled(new BigDecimal(0), false));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoTRRL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */