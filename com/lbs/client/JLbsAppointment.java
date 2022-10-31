/*    */ package com.lbs.client;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class JLbsAppointment
/*    */ {
/*    */   public static final int IMPORTANCE_LOW = 0;
/*    */   public static final int IMPORTANCE_NORMAL = 1;
/*    */   public static final int IMPORTANCE_HIGH = 2;
/*    */   private String m_Id;
/*    */   private Date m_StartTime;
/*    */   private Date m_EndTime;
/*    */   private String m_Subject;
/*    */   private String m_Location;
/*    */   private String m_Body;
/* 17 */   private int m_Importance = 1;
/*    */   
/*    */   private boolean m_AllDayEvent;
/*    */ 
/*    */   
/*    */   public String getId() {
/* 23 */     return this.m_Id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 27 */     this.m_Id = id;
/*    */   }
/*    */   
/*    */   public Date getStartTime() {
/* 31 */     return this.m_StartTime;
/*    */   }
/*    */   
/*    */   public void setStartTime(Date startTime) {
/* 35 */     this.m_StartTime = startTime;
/*    */   }
/*    */   
/*    */   public Date getEndTime() {
/* 39 */     return this.m_EndTime;
/*    */   }
/*    */   
/*    */   public void setEndTime(Date endTime) {
/* 43 */     this.m_EndTime = endTime;
/*    */   }
/*    */   
/*    */   public String getSubject() {
/* 47 */     return this.m_Subject;
/*    */   }
/*    */   
/*    */   public void setSubject(String subject) {
/* 51 */     this.m_Subject = subject;
/*    */   }
/*    */   
/*    */   public String getLocation() {
/* 55 */     return this.m_Location;
/*    */   }
/*    */   
/*    */   public void setLocation(String location) {
/* 59 */     this.m_Location = location;
/*    */   }
/*    */   
/*    */   public String getBody() {
/* 63 */     return this.m_Body;
/*    */   }
/*    */   
/*    */   public void setBody(String body) {
/* 67 */     this.m_Body = body;
/*    */   }
/*    */   
/*    */   public int getImportance() {
/* 71 */     return this.m_Importance;
/*    */   }
/*    */   
/*    */   public void setImportance(int importance) {
/* 75 */     this.m_Importance = importance;
/*    */   }
/*    */   
/*    */   public boolean isAllDayEvent() {
/* 79 */     return this.m_AllDayEvent;
/*    */   }
/*    */   
/*    */   public void setAllDayEvent(boolean allDayEvent) {
/* 83 */     this.m_AllDayEvent = allDayEvent;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\JLbsAppointment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */