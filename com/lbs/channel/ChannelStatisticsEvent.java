/*    */ package com.lbs.channel;
/*    */ 
/*    */ import java.util.EventObject;
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
/*    */ public class ChannelStatisticsEvent
/*    */   extends EventObject
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_dataLength;
/*    */   private int m_headerLength;
/*    */   private String m_uri;
/*    */   private long m_timeElapsed;
/*    */   
/*    */   public ChannelStatisticsEvent(Object source, int dataLength, int headerLength, String uri, long timeElapsed) {
/* 25 */     super(source);
/* 26 */     this.m_dataLength = dataLength;
/* 27 */     this.m_headerLength = headerLength;
/* 28 */     this.m_uri = uri;
/* 29 */     this.m_timeElapsed = timeElapsed;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDataLength() {
/* 34 */     return this.m_dataLength;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeaderLength() {
/* 39 */     return this.m_headerLength;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUri() {
/* 44 */     return this.m_uri;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getTimeElapsed() {
/* 49 */     return this.m_timeElapsed;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     StringBuilder sb = new StringBuilder();
/* 55 */     sb.append("Channel Stat Event [");
/* 56 */     sb.append("URI : " + getUri());
/* 57 */     sb.append(", Header Length : " + getHeaderLength());
/* 58 */     sb.append(", Data Length : " + getDataLength() + ", Time Elapsed : " + this.m_timeElapsed + "]");
/* 59 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ChannelStatisticsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */