/*    */ package com.lbs.data.factory;
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
/*    */ public interface ICacheKey
/*    */ {
/* 15 */   public static final Integer DO_NOT_CACHE = Integer.valueOf(-1);
/* 16 */   public static final Integer ALREADY_CACHED = Integer.valueOf(-2);
/*    */   
/*    */   Integer toKeyValue();
/*    */   
/*    */   String getCacheObjectName();
/*    */   
/*    */   void setCacheObjectName(String paramString);
/*    */   
/*    */   void setCacheKeyValue_(int paramInt);
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ICacheKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */