/*    */ package com.lbs.transport.health;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class TransportExceptionAnalysers
/*    */ {
/*  8 */   private static List<TransportExceptionAnalyser> ms_Analysers = new Vector<>();
/*    */ 
/*    */   
/*    */   static {
/* 12 */     registerAnalyser(new NetworkTransportExceptionAnalyser());
/* 13 */     registerAnalyser(new DataLayerExceptionAnalyser());
/* 14 */     registerAnalyser(new SqlTransportExceptionAnalyser());
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerAnalyser(TransportExceptionAnalyser analyser) {
/* 19 */     ms_Analysers.add(analyser);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isBlocker(Throwable t) {
/* 24 */     for (TransportExceptionAnalyser analyser : ms_Analysers) {
/*    */       
/* 26 */       if (analyser.isTransportBlocker(t))
/* 27 */         return true; 
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\health\TransportExceptionAnalysers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */