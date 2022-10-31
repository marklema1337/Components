/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.swing.text.StyleContext;
/*    */ 
/*    */ 
/*    */ public class FontMetricHandler
/*    */ {
/* 12 */   public static Map<String, FontMetrics> ms_Font_Metrics = new HashMap<>();
/* 13 */   public static int ms_Font_Metrics_Size = 40;
/* 14 */   public static StyleContext context = new StyleContext();
/*    */ 
/*    */   
/*    */   public static FontMetrics getFontMetricsFromStyleContext(Font font) {
/* 18 */     FontMetrics fontMetrics = null;
/* 19 */     String key = String.valueOf(font.getName()) + "_" + font.getStyle() + "_" + font.getSize();
/* 20 */     fontMetrics = ms_Font_Metrics.get(key);
/* 21 */     if (fontMetrics != null)
/* 22 */       return fontMetrics; 
/* 23 */     synchronized (ms_Font_Metrics) {
/*    */       
/* 25 */       if (ms_Font_Metrics.keySet().size() >= ms_Font_Metrics_Size)
/*    */       {
/* 27 */         ms_Font_Metrics = new HashMap<>();
/*    */       }
/* 29 */       if (fontMetrics == null) {
/*    */         
/* 31 */         fontMetrics = context.getFontMetrics(font);
/* 32 */         ms_Font_Metrics.put(key, fontMetrics);
/*    */       } 
/*    */     } 
/* 35 */     return fontMetrics;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\FontMetricHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */