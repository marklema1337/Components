/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HourlyInfoList
/*    */ {
/* 15 */   private ArrayList m_List = null;
/*    */ 
/*    */   
/*    */   public HourlyInfoList() {
/* 19 */     this.m_List = new ArrayList();
/*    */   }
/*    */   
/*    */   public boolean addItem(String displayText, int hour, Object object) {
/* 23 */     HourInfo anInfo = new HourInfo(displayText, hour, object);
/* 24 */     int idx = this.m_List.indexOf(anInfo);
/* 25 */     if (idx == -1) {
/*    */       
/* 27 */       this.m_List.add(anInfo);
/* 28 */       return true;
/*    */     } 
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   public void removeItem(HourInfo anInfo) {
/* 34 */     this.m_List.remove(anInfo);
/*    */   }
/*    */   
/*    */   public HourInfo getItem(int anIndex) {
/* 38 */     if (anIndex < this.m_List.size())
/* 39 */       return this.m_List.get(anIndex); 
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public void addOrReplaceItem(String displayText, int hour, Object object) {
/* 44 */     HourInfo anInfo = new HourInfo(displayText, hour, object);
/* 45 */     removeItem(anInfo);
/* 46 */     this.m_List.add(anInfo);
/*    */   }
/*    */   
/*    */   public int indexOf(HourInfo anInfo) {
/* 50 */     return this.m_List.indexOf(anInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\HourlyInfoList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */