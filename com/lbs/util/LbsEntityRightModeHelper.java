/*    */ package com.lbs.util;
/*    */ 
/*    */ 
/*    */ public class LbsEntityRightModeHelper
/*    */ {
/*    */   public static final int OP_UPDATE = 1;
/*    */   public static final int OP_SELECT = 2;
/*    */   public static final int OP_VIEW = 4;
/*    */   public static final int OP_DUPLICATE = 8;
/*    */   public static final int OP_INSERT = 16;
/* 11 */   public static final int[] OP_ALL = new int[] { 1, 2, 4, 8, 16 };
/*    */ 
/*    */   
/*    */   public static boolean hasRight(int rights, int right) {
/* 15 */     return ((rights & right) == right);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean hasRights(int rights1, int rights2) {
/* 20 */     return ((rights1 & rights2) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int setRight(int rights, int right) {
/* 25 */     rights |= right;
/* 26 */     return rights;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int resetRight(int rights, int right) {
/* 31 */     rights &= right ^ 0xFFFFFFFF;
/* 32 */     return rights;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int toggleRight(int options, int option, boolean set) {
/* 37 */     if (set)
/* 38 */       return setRight(options, option); 
/* 39 */     return resetRight(options, option);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsEntityRightModeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */