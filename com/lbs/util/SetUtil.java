/*    */ package com.lbs.util;
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
/*    */ public class SetUtil
/*    */ {
/*    */   public static boolean isOptionSet(int options, int option) {
/* 18 */     return ((options & option) == option);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int setOption(int options, int option) {
/* 23 */     options |= option;
/* 24 */     return options;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int resetOption(int options, int option) {
/* 29 */     options &= option ^ 0xFFFFFFFF;
/* 30 */     return options;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int toggleOption(int options, int option, boolean set) {
/* 35 */     if (set) {
/* 36 */       return setOption(options, option);
/*    */     }
/* 38 */     return resetOption(options, option);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isOptionSet(long options, long option) {
/* 43 */     return ((options & option) == option);
/*    */   }
/*    */ 
/*    */   
/*    */   public static long setOption(long options, long option) {
/* 48 */     options |= option;
/* 49 */     return options;
/*    */   }
/*    */ 
/*    */   
/*    */   public static long resetOption(long options, long option) {
/* 54 */     options &= option ^ 0xFFFFFFFFFFFFFFFFL;
/* 55 */     return options;
/*    */   }
/*    */ 
/*    */   
/*    */   public static long toggleOption(long options, long option, boolean set) {
/* 60 */     if (set) {
/* 61 */       return setOption(options, option);
/*    */     }
/* 63 */     return resetOption(options, option);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int[] getOptionsSet(int options) {
/* 69 */     int len = Integer.toBinaryString(options).length();
/* 70 */     int[] arr = new int[len];
/* 71 */     int i = 0;
/* 72 */     while (options != 0) {
/*    */       
/* 74 */       if ((options & 0x1) == 1)
/*    */       {
/* 76 */         arr[len - 1 - i] = (int)Math.pow(2.0D, i);
/*    */       }
/* 78 */       i++;
/* 79 */       options >>= 1;
/*    */     } 
/* 81 */     return arr;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\SetUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */