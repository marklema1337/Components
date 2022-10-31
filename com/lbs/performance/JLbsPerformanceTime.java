/*    */ package com.lbs.performance;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsPerformanceTime
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 24 */   protected long m_TimeInMillis = System.currentTimeMillis();
/* 25 */   protected long m_MicroSeconds = JLbsPreciseTime.getMicroSeconds()[1];
/*    */ 
/*    */ 
/*    */   
/*    */   public long getTimeInMillis() {
/* 30 */     return this.m_TimeInMillis;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTimeInMillis(long l) {
/* 35 */     this.m_TimeInMillis = l;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getMicroSeconds() {
/* 40 */     return this.m_MicroSeconds;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMicroSeconds(long l) {
/* 45 */     this.m_MicroSeconds = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return this.m_TimeInMillis + " " + this.m_MicroSeconds;
/*    */   }
/*    */ 
/*    */   
/*    */   public void parse(String line) {
/* 58 */     String[] words = StringUtil.split(line, ' ');
/*    */     
/* 60 */     if (words.length == 2) {
/*    */       
/* 62 */       this.m_TimeInMillis = Long.parseLong(words[0]);
/* 63 */       this.m_MicroSeconds = Long.parseLong(words[1]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsPerformanceTime substruct(JLbsPerformanceTime time1, JLbsPerformanceTime time2) {
/* 69 */     JLbsPerformanceTime res = new JLbsPerformanceTime();
/*    */     
/* 71 */     time1.m_TimeInMillis -= time2.m_TimeInMillis;
/* 72 */     time1.m_MicroSeconds -= time2.m_MicroSeconds;
/*    */     
/* 74 */     if (res.m_MicroSeconds < 0L) {
/*    */       
/* 76 */       res.m_MicroSeconds = Math.abs(res.m_MicroSeconds);
/* 77 */       res.m_TimeInMillis -= 1000L;
/*    */     } 
/*    */     
/* 80 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public static long getTimeInMicors(JLbsPerformanceTime time) {
/* 85 */     return getTimeInMicors(time.m_TimeInMillis, time.m_MicroSeconds);
/*    */   }
/*    */ 
/*    */   
/*    */   public static long getTimeInMicors(long timeInMilis, long micros) {
/* 90 */     long res = timeInMilis / 1000L;
/* 91 */     res = res * 1000000L + micros;
/* 92 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\JLbsPerformanceTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */