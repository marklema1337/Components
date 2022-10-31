/*    */ package com.lbs.profiler.helper;
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
/*    */ public class JLbsRRDLineDef
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String sourceName;
/*    */   public long lineHeartbeats;
/*    */   public double lineMinValue;
/*    */   public double lineMaxValue;
/*    */   public int lineRowCount;
/*    */   public int steps;
/*    */   
/*    */   private JLbsRRDLineDef(JLbsRRDLineDef def) {
/* 26 */     this.sourceName = def.sourceName;
/* 27 */     this.lineHeartbeats = def.lineHeartbeats;
/* 28 */     this.lineMinValue = def.lineMinValue;
/* 29 */     this.lineMaxValue = def.lineMaxValue;
/* 30 */     this.lineRowCount = def.lineRowCount;
/* 31 */     this.steps = def.steps;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsRRDLineDef(String sourceName, long lineHeartbeats, double lineMinValue, double lineMaxValue, int lineRowCount, int steps) {
/* 37 */     this.sourceName = sourceName;
/* 38 */     this.lineHeartbeats = lineHeartbeats;
/* 39 */     this.lineMinValue = lineMinValue;
/* 40 */     this.lineMaxValue = lineMaxValue;
/* 41 */     this.lineRowCount = lineRowCount;
/* 42 */     this.steps = steps;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsRRDLineDef createCopy() {
/* 47 */     return new JLbsRRDLineDef(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsRRDLineDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */