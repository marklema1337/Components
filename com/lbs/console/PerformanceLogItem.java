/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public class PerformanceLogItem
/*    */ {
/*    */   public String ItemName;
/*    */   public Date StartTime;
/*    */   public ArrayList ItemLines;
/*    */   public int EffectedRowCount;
/*    */   public Object ExtraData;
/*    */   public boolean Erronous = false;
/*    */   
/*    */   public PerformanceLogItem(String itemName, Date startTime, int effectedRowCount, Object extraData) {
/* 26 */     this.ItemName = itemName;
/* 27 */     this.StartTime = startTime;
/* 28 */     this.EffectedRowCount = effectedRowCount;
/* 29 */     this.ExtraData = extraData;
/* 30 */     this.ItemLines = new ArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 35 */     if (!(obj instanceof PerformanceLogItem)) {
/* 36 */       return false;
/*    */     }
/* 38 */     return areEqual(this.ItemName, ((PerformanceLogItem)obj).ItemName);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean areEqual(String s1, String s2) {
/* 48 */     if (s1 == null || s2 == null)
/* 49 */       return (s1 == s2); 
/* 50 */     return (s1.compareTo(s2) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return String.valueOf(String.valueOf(this.ItemName)) + " with " + this.ItemLines.size() + "items , " + String.valueOf(this.ExtraData);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\PerformanceLogItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */