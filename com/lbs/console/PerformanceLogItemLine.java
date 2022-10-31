/*    */ package com.lbs.console;
/*    */ 
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
/*    */ public class PerformanceLogItemLine
/*    */ {
/*    */   public String ItemName;
/*    */   public Date StartTime;
/*    */   public Object ExtraData;
/*    */   public boolean Erronous = false;
/*    */   
/*    */   public PerformanceLogItemLine(String itemName, Date startTime, Object extraData) {
/* 23 */     this.ItemName = itemName;
/* 24 */     this.StartTime = startTime;
/* 25 */     this.ExtraData = extraData;
/*    */   }
/*    */ 
/*    */   
/*    */   public PerformanceLogItemLine(String itemName, Date startTime, Object extraData, boolean erronous) {
/* 30 */     this(itemName, startTime, extraData);
/* 31 */     this.Erronous = erronous;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\PerformanceLogItemLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */