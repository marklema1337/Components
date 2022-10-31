/*    */ package com.lbs.channel;
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
/*    */ 
/*    */ public class JLbsChannelAllStatistics
/*    */ {
/* 16 */   private ArrayList<JLbsChannelStatistic> m_UriStatistics = new ArrayList<>();
/*    */   
/*    */   private int m_TotalRequestsSend;
/*    */   private int m_TotalRequestsReceived;
/*    */   private int m_TotalDataSend;
/*    */   private int m_TotalDataReceived;
/*    */   private long m_TotalTime;
/*    */   
/*    */   public long getTotalTime() {
/* 25 */     return this.m_TotalTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotalTime(long totalTime) {
/* 30 */     this.m_TotalTime = totalTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<JLbsChannelStatistic> getUriStatistics() {
/* 35 */     return this.m_UriStatistics;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUriStatistics(ArrayList<JLbsChannelStatistic> uriStatistics) {
/* 40 */     this.m_UriStatistics = uriStatistics;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalRequestsSend() {
/* 45 */     return this.m_TotalRequestsSend;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotalRequestsSend(int totalRequestsSend) {
/* 50 */     this.m_TotalRequestsSend = totalRequestsSend;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalRequestsReceived() {
/* 55 */     return this.m_TotalRequestsReceived;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotalRequestsReceived(int totalRequestsReceived) {
/* 60 */     this.m_TotalRequestsReceived = totalRequestsReceived;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalDataSend() {
/* 65 */     return this.m_TotalDataSend;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotalDataSend(int totalDataSend) {
/* 70 */     this.m_TotalDataSend = totalDataSend;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalDataReceived() {
/* 75 */     return this.m_TotalDataReceived;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotalDataReceived(int totalDataReceived) {
/* 80 */     this.m_TotalDataReceived = totalDataReceived;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\JLbsChannelAllStatistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */