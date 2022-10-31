/*    */ package com.lbs.controller;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public enum ControllerMode
/*    */ {
/*  9 */   New(1), Update(2), Duplicate(3), View(
/* 10 */     11), Default(0);
/*    */   
/*    */   private final int m_XUIMode;
/*    */   private static final Map<Integer, ControllerMode> ms_LookupTable;
/*    */   
/*    */   ControllerMode(int xuiMode) {
/* 16 */     this.m_XUIMode = xuiMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getXUIMode() {
/* 21 */     return this.m_XUIMode;
/*    */   }
/*    */   static {
/* 24 */     ms_LookupTable = new HashMap<>();
/*    */ 
/*    */ 
/*    */     
/* 28 */     for (ControllerMode s : EnumSet.<ControllerMode>allOf(ControllerMode.class)) {
/* 29 */       ms_LookupTable.put(Integer.valueOf(s.getXUIMode()), s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static ControllerMode get(int xuiMode) {
/* 34 */     ControllerMode controllerMode = ms_LookupTable.get(Integer.valueOf(xuiMode));
/* 35 */     if (controllerMode == null)
/* 36 */       return Default; 
/* 37 */     return controllerMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */