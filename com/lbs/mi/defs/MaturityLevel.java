/*    */ package com.lbs.mi.defs;
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
/*    */ public enum MaturityLevel
/*    */ {
/* 14 */   release(0), release_candidate(1), beta(2), alpha(3);
/*    */   
/*    */   public static final String XML_TAG_NAME = "maturity-level";
/*    */   
/*    */   private int m_IntValue;
/*    */ 
/*    */   
/*    */   MaturityLevel(int intValue) {
/* 22 */     this.m_IntValue = intValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIntValue() {
/* 27 */     return this.m_IntValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MaturityLevel get(int intValue) {
/* 32 */     MaturityLevel[] values = values(); byte b; int i; MaturityLevel[] arrayOfMaturityLevel1;
/* 33 */     for (i = (arrayOfMaturityLevel1 = values).length, b = 0; b < i; ) { MaturityLevel level = arrayOfMaturityLevel1[b];
/*    */       
/* 35 */       if (level.getIntValue() == intValue)
/* 36 */         return level;  b++; }
/*    */     
/* 38 */     return release_candidate;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MaturityLevel get(String value) {
/* 43 */     value = value.replace('-', '_');
/* 44 */     return valueOf(value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\MaturityLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */