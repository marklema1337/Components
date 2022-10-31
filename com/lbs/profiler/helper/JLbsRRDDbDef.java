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
/*    */ public class JLbsRRDDbDef
/*    */   implements Serializable
/*    */ {
/* 16 */   private static final JLbsRRDLineDef DEFAULT_LINEDEF = new JLbsRRDLineDef(null, 600L, 0.0D, 1000000.0D, 4320, 1);
/*    */   private static final long serialVersionUID = 1L;
/* 18 */   public String dbName = null;
/* 19 */   public String description = null;
/* 20 */   public long startTime = System.currentTimeMillis() / 1000L;
/* 21 */   public JLbsRRDLineDef[] lines = new JLbsRRDLineDef[] { DEFAULT_LINEDEF.createCopy() };
/* 22 */   public long rrdStep = 60L;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsRRDDbDef(String dbName, String description, long startTime, JLbsRRDLineDef[] lines) {
/* 33 */     this.dbName = dbName;
/* 34 */     this.description = description;
/* 35 */     this.startTime = startTime;
/* 36 */     this.lines = lines;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsRRDLineDef getDefaultLineDef() {
/* 41 */     return DEFAULT_LINEDEF.createCopy();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsRRDDbDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */