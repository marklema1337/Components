/*    */ package com.lbs.performance;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import java.util.logging.LogRecord;
/*    */ import java.util.logging.SimpleFormatter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StressFormatter
/*    */   extends SimpleFormatter
/*    */ {
/*    */   public String format(LogRecord record) {
/* 18 */     StringBuilder sb = new StringBuilder();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     Object[] params = record.getParameters();
/*    */     
/* 25 */     sb.append(record.getMessage());
/* 26 */     sb.append(" ");
/* 27 */     sb.append(params[3]);
/*    */ 
/*    */     
/* 30 */     sb.append(" ");
/* 31 */     sb.append(record.getMillis());
/* 32 */     sb.append(" ");
/* 33 */     sb.append(params[2]);
/* 34 */     sb.append(" ");
/* 35 */     sb.append(record.getThreadID());
/* 36 */     sb.append(" ");
/*    */ 
/*    */     
/* 39 */     sb.append(params[0]);
/*    */     
/* 41 */     if (record.getSourceClassName() != null) {
/*    */       
/* 43 */       sb.append(" ");
/* 44 */       sb.append(record.getSourceClassName());
/*    */     } 
/*    */     
/* 47 */     if (record.getSourceMethodName() != null) {
/*    */       
/* 49 */       sb.append(" ");
/* 50 */       sb.append(record.getSourceMethodName());
/*    */     } 
/*    */     
/* 53 */     if (params[1] != null) {
/*    */       
/* 55 */       sb.append(" ");
/* 56 */       sb.append(params[1]);
/*    */     } 
/*    */     
/* 59 */     if (record.getThrown() != null) {
/*    */       
/*    */       try {
/*    */         
/* 63 */         StringWriter sw = new StringWriter();
/* 64 */         PrintWriter pw = new PrintWriter(sw);
/* 65 */         record.getThrown().printStackTrace(pw);
/* 66 */         pw.close();
/* 67 */         sb.append(" ");
/* 68 */         sb.append(sw.toString());
/*    */       }
/* 70 */       catch (Exception exception) {}
/*    */     }
/*    */ 
/*    */     
/* 74 */     sb.append("\n");
/*    */     
/* 76 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\StressFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */