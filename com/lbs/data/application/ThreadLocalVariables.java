/*    */ package com.lbs.data.application;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.concurrent.ConcurrentHashMap;
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
/*    */ public class ThreadLocalVariables
/*    */ {
/* 17 */   private static ThreadLocal<ConcurrentHashMap<String, Object>> ms_Vars = new ThreadLocal<>();
/*    */ 
/*    */   
/*    */   public static String[] getThreadLocalVars() {
/* 21 */     ConcurrentHashMap<String, Object> table = ms_Vars.get();
/* 22 */     if (table != null) {
/*    */       
/* 24 */       Enumeration<String> keys = table.keys();
/* 25 */       String[] vars = new String[table.size()];
/* 26 */       int i = 0;
/* 27 */       while (keys.hasMoreElements())
/*    */       {
/* 29 */         vars[i++] = keys.nextElement();
/*    */       }
/* 31 */       return vars;
/*    */     } 
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Object getThreadLocalValue(String varName) {
/* 38 */     ConcurrentHashMap<String, Object> table = ms_Vars.get();
/* 39 */     if (table != null)
/* 40 */       return table.get(varName); 
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void putThreadLocalValue(String varName, Object varValue) {
/* 46 */     ConcurrentHashMap<String, Object> table = ms_Vars.get();
/* 47 */     if (table == null) {
/*    */       
/* 49 */       table = new ConcurrentHashMap<>();
/* 50 */       ms_Vars.set(table);
/*    */     } 
/* 52 */     table.put(varName, varValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void removeThreadLocalValue(String varName) {
/* 57 */     ConcurrentHashMap<String, Object> table = ms_Vars.get();
/* 58 */     if (table != null) {
/* 59 */       table.remove(varName);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Object checkOverriddenVariable(String varName, Object value) {
/* 64 */     Object threadValue = getThreadLocalValue(varName);
/* 65 */     if (threadValue != null)
/* 66 */       return threadValue; 
/* 67 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\ThreadLocalVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */