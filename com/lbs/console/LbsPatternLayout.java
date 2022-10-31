/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.core.Layout;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.core.layout.ByteBufferDestination;
/*    */ import org.apache.logging.log4j.core.layout.PatternLayout;
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
/*    */ public class LbsPatternLayout
/*    */   implements Layout<Serializable>
/*    */ {
/*    */   private PatternLayout m_PatternLayout;
/*    */   
/*    */   LbsPatternLayout() {
/* 26 */     this.m_PatternLayout = PatternLayout.createDefaultLayout();
/*    */   }
/*    */ 
/*    */   
/*    */   LbsPatternLayout(String pattern) {
/* 31 */     this.m_PatternLayout = PatternLayout.newBuilder().withPattern(pattern).build();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return this.m_PatternLayout.getConversionPattern();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getFooter() {
/* 42 */     return this.m_PatternLayout.getFooter();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getHeader() {
/* 48 */     return this.m_PatternLayout.getHeader();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] toByteArray(LogEvent event) {
/* 54 */     return this.m_PatternLayout.toByteArray(event);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Serializable toSerializable(LogEvent event) {
/* 60 */     return this.m_PatternLayout.toSerializable(event);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getContentType() {
/* 66 */     return this.m_PatternLayout.getContentType();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map getContentFormat() {
/* 72 */     return this.m_PatternLayout.getContentFormat();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConversionPattern() {
/* 77 */     return this.m_PatternLayout.getConversionPattern();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(LogEvent source, ByteBufferDestination destination) {
/* 82 */     this.m_PatternLayout.encode(source, destination);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsPatternLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */