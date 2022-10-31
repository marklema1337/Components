/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HourInfo
/*    */ {
/* 13 */   private String m_Text = "";
/* 14 */   private int m_Hour = 0;
/* 15 */   private Object m_Object = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HourInfo() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public HourInfo(String text, int hour, Object object) {
/* 25 */     this();
/* 26 */     setHour(hour);
/* 27 */     setObject(object);
/* 28 */     setText(text);
/*    */   }
/*    */   
/*    */   public int getHour() {
/* 32 */     return this.m_Hour;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getObject() {
/* 40 */     return this.m_Object;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getText() {
/* 48 */     return this.m_Text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHour(int i) {
/* 56 */     this.m_Hour = i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setObject(Object object) {
/* 64 */     this.m_Object = object;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String string) {
/* 72 */     this.m_Text = string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\HourInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */