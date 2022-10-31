/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChronometerValue
/*    */ {
/*    */   private String theTitle;
/*    */   private Calendar theTime;
/*    */   private int repeats;
/*    */   
/*    */   public ChronometerValue() {
/* 17 */     this("Start: ");
/*    */   }
/*    */ 
/*    */   
/*    */   public ChronometerValue(String title) {
/* 22 */     this.theTitle = title;
/* 23 */     this.theTime = Calendar.getInstance();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTitle() {
/* 28 */     return this.theTitle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTitle(String title) {
/* 33 */     this.theTitle = title;
/*    */   }
/*    */ 
/*    */   
/*    */   public Calendar getTime() {
/* 38 */     return this.theTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTime(Calendar time) {
/* 43 */     this.theTime = time;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRepeats() {
/* 50 */     return this.repeats;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRepeats(int i) {
/* 58 */     this.repeats = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ChronometerValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */