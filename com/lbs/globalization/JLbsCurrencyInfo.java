/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class JLbsCurrencyInfo
/*    */   implements Serializable
/*    */ {
/*    */   public static final int CRND_NONE = 0;
/*    */   public static final int CRND_NEARMUHALFUP = 1;
/*    */   public static final int CRND_NEARSUHALFUP = 2;
/*    */   public static final int CRND_NEARSCN = 3;
/*    */   public static final int CRND_NEARMUHALFDOWN = 4;
/*    */   public static final int CRND_NEARSUHALFDOWN = 5;
/*    */   public static final int CI_DOLLAR = 1;
/*    */   public static final int CI_EURO = 20;
/*    */   public int m_currency;
/*    */   public String m_code;
/*    */   public String m_decCode;
/*    */   public String m_shortCode;
/*    */   public int m_decimals;
/*    */   public double m_coef;
/*    */   public boolean m_invertRate;
/*    */   public boolean m_EUROIndexed;
/*    */   public double m_EURORate;
/*    */   public int m_smallestCoin;
/*    */   public int m_rounding;
/*    */   public boolean m_triangulation;
/*    */   
/*    */   public JLbsCurrencyInfo(int index, String code, String decCode, String shortCode, int decimals, double coef, boolean invertRate, boolean EUROIndexed, double EURORate, int smallestCoin, int rounding, boolean triangulation) {
/* 59 */     this.m_currency = index;
/* 60 */     this.m_code = code;
/* 61 */     this.m_decCode = decCode;
/* 62 */     this.m_shortCode = shortCode;
/* 63 */     this.m_decimals = decimals;
/* 64 */     this.m_coef = coef;
/* 65 */     this.m_invertRate = invertRate;
/* 66 */     this.m_EUROIndexed = EUROIndexed;
/* 67 */     this.m_EURORate = EURORate;
/* 68 */     this.m_smallestCoin = smallestCoin;
/* 69 */     this.m_rounding = rounding;
/* 70 */     this.m_triangulation = triangulation;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsCurrencyInfo(int index) {
/* 76 */     this.m_currency = index;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCurrencyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */