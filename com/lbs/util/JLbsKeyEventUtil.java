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
/*    */ 
/*    */ public class JLbsKeyEventUtil
/*    */ {
/*    */   public static JLbsStringList getKeyList() {
/* 19 */     JLbsStringList sLst = new JLbsStringList();
/* 20 */     sLst.add("---", 0);
/* 21 */     sLst.add("A", 65);
/* 22 */     sLst.add("B", 66);
/* 23 */     sLst.add("C", 67);
/* 24 */     sLst.add("D", 68);
/* 25 */     sLst.add("E", 69);
/* 26 */     sLst.add("F", 70);
/* 27 */     sLst.add("G", 71);
/* 28 */     sLst.add("H", 72);
/* 29 */     sLst.add("I", 73);
/* 30 */     sLst.add("J", 74);
/* 31 */     sLst.add("K", 75);
/* 32 */     sLst.add("L", 76);
/* 33 */     sLst.add("M", 77);
/* 34 */     sLst.add("N", 78);
/* 35 */     sLst.add("O", 79);
/* 36 */     sLst.add("P", 80);
/* 37 */     sLst.add("Q", 81);
/* 38 */     sLst.add("R", 82);
/* 39 */     sLst.add("S", 83);
/* 40 */     sLst.add("T", 84);
/* 41 */     sLst.add("U", 85);
/* 42 */     sLst.add("V", 86);
/* 43 */     sLst.add("Y", 89);
/* 44 */     sLst.add("Z", 90);
/*    */     
/* 46 */     sLst.add("0", 48);
/* 47 */     sLst.add("1", 49);
/* 48 */     sLst.add("2", 50);
/* 49 */     sLst.add("3", 51);
/* 50 */     sLst.add("4", 52);
/* 51 */     sLst.add("5", 53);
/* 52 */     sLst.add("6", 54);
/* 53 */     sLst.add("7", 55);
/* 54 */     sLst.add("8", 56);
/* 55 */     sLst.add("9", 57);
/*    */     
/* 57 */     sLst.add("F1", 112);
/* 58 */     sLst.add("F2", 113);
/* 59 */     sLst.add("F3", 114);
/* 60 */     sLst.add("F4", 115);
/* 61 */     sLst.add("F5", 116);
/* 62 */     sLst.add("F6", 117);
/* 63 */     sLst.add("F7", 118);
/* 64 */     sLst.add("F8", 119);
/* 65 */     sLst.add("F9", 120);
/* 66 */     sLst.add("F10", 121);
/* 67 */     sLst.add("F11", 122);
/* 68 */     sLst.add("F12", 113);
/*    */     
/* 70 */     return sLst;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsStringList getModifierList(int exclude) {
/* 75 */     JLbsStringList sLst = new JLbsStringList();
/* 76 */     sLst.add("---", 0);
/* 77 */     sLst.add("Ctrl", 2);
/* 78 */     sLst.add("Shift", 1);
/* 79 */     sLst.add("Alt", 8);
/* 80 */     if (exclude != 0)
/* 81 */       sLst.remove(exclude); 
/* 82 */     return sLst;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsKeyEventUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */