/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import com.lbs.controls.pivottable.PivotCellValue;
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import java.text.DateFormat;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.NumberFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
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
/*    */ public class TableReportFormatter
/*    */ {
/*    */   private DateFormat dateFormat;
/*    */   private NumberFormat numberFormat;
/*    */   
/*    */   public TableReportFormatter(ILbsCultureInfo cultureInfo) {
/* 28 */     this.dateFormat = new SimpleDateFormat(cultureInfo.getDefaultDateFormat());
/* 29 */     this.numberFormat = new DecimalFormat(cultureInfo.getDefaultNumberFormat());
/*    */   }
/*    */   
/*    */   public String format(Object object) {
/* 33 */     String text = null;
/*    */     
/* 35 */     if (object != null && object instanceof PivotCellValue) {
/* 36 */       object = ((PivotCellValue)object).getValue();
/*    */     }
/*    */     
/* 39 */     if (object == null) {
/* 40 */       text = null;
/*    */     }
/* 42 */     else if (object instanceof Calendar) {
/* 43 */       Date date = ((Calendar)object).getTime();
/* 44 */       text = this.dateFormat.format(date);
/*    */     }
/* 46 */     else if (object instanceof Date) {
/* 47 */       text = this.dateFormat.format((Date)object);
/*    */     }
/* 49 */     else if (object instanceof Number) {
/* 50 */       text = this.numberFormat.format(((Number)object).doubleValue());
/*    */     } else {
/*    */       
/* 53 */       text = object.toString();
/*    */     } 
/*    */     
/* 56 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */