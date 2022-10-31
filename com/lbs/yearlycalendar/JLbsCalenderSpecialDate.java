/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.GregorianCalendar;
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
/*    */ public class JLbsCalenderSpecialDate
/*    */ {
/* 17 */   private Calendar m_Date = null;
/* 18 */   private String m_Description = "";
/*    */ 
/*    */   
/*    */   public JLbsCalenderSpecialDate() {
/* 22 */     this.m_Date = new GregorianCalendar();
/*    */   }
/*    */   
/*    */   public JLbsCalenderSpecialDate(Calendar aDate, String aDesc) {
/* 26 */     this();
/* 27 */     setDate(aDate);
/* 28 */     setDescription(aDesc);
/*    */   }
/*    */   
/*    */   public Calendar getDate() {
/* 32 */     return this.m_Date;
/*    */   }
/*    */   
/*    */   public void setDate(Calendar aDate) {
/* 36 */     this.m_Date = aDate;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 40 */     return this.m_Description;
/*    */   }
/*    */   
/*    */   public void setDescription(String aDesc) {
/* 44 */     this.m_Description = aDesc;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsCalenderSpecialDate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */