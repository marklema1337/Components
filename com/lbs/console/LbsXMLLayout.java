/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.layout.ByteBufferDestination;
/*     */ import org.apache.logging.log4j.core.layout.XmlLayout;
/*     */ import org.apache.logging.log4j.core.util.Transform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsXMLLayout
/*     */   implements Layout<Serializable>
/*     */ {
/*  27 */   private final int DEFAULT_SIZE = 256;
/*  28 */   private final int UPPER_LIMIT = 2048;
/*     */   
/*     */   private XmlLayout m_XmlLayout;
/*     */   private boolean m_LocationInfo;
/*  32 */   private StringBuilder buf = new StringBuilder(256);
/*     */ 
/*     */   
/*     */   LbsXMLLayout(boolean locationInfo) {
/*  36 */     this.m_LocationInfo = locationInfo;
/*  37 */     this.m_XmlLayout = XmlLayout.createLayout(locationInfo, false, false, false, Charset.forName("UTF-8"), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(LogEvent event) {
/*  48 */     if (this.buf.capacity() > 2048) {
/*     */       
/*  50 */       this.buf = new StringBuilder(256);
/*     */     }
/*     */     else {
/*     */       
/*  54 */       this.buf.setLength(0);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.buf.append("<log4j_event logger=\"");
/*  60 */     this.buf.append(event.getLoggerName());
/*  61 */     this.buf.append("\" timestamp=\"");
/*  62 */     this.buf.append(event.getTimeMillis());
/*  63 */     this.buf.append("\" formattedTime=\"");
/*  64 */     this.buf.append(new Date(event.getTimeMillis()));
/*  65 */     this.buf.append("\" level=\"");
/*  66 */     this.buf.append(event.getLevel());
/*  67 */     this.buf.append("\" thread=\"");
/*  68 */     this.buf.append(event.getThreadName());
/*  69 */     this.buf.append("\">\r\n");
/*     */     
/*  71 */     this.buf.append("<log4j_message><![CDATA[");
/*     */ 
/*     */     
/*  74 */     Transform.appendEscapingCData(this.buf, event.getMessage().getFormattedMessage());
/*  75 */     this.buf.append("]]></log4j_message>\r\n");
/*     */     
/*  77 */     String ndc = event.getContextStack().toString();
/*  78 */     if (ndc != null) {
/*     */       
/*  80 */       this.buf.append("<log4j_NDC><![CDATA[");
/*  81 */       this.buf.append(ndc);
/*  82 */       this.buf.append("]]></log4j_NDC>\r\n");
/*     */     } 
/*     */     
/*  85 */     StackTraceElement[] s = event.getThrown().getStackTrace();
/*  86 */     if (s != null) {
/*     */       
/*  88 */       this.buf.append("<log4j_throwable><![CDATA[");
/*  89 */       for (int i = 0; i < s.length; i++) {
/*     */         
/*  91 */         this.buf.append(s[i]);
/*  92 */         this.buf.append("\r\n");
/*     */       } 
/*  94 */       this.buf.append("]]></log4j_throwable>\r\n");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.buf.append("</log4j_event>\r\n\r\n");
/*     */     
/* 113 */     return this.buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     return "XML (include-line-info: " + getLocationInfo() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getFooter() {
/* 124 */     return this.m_XmlLayout.getFooter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getHeader() {
/* 130 */     return this.m_XmlLayout.getHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toByteArray(LogEvent event) {
/* 136 */     return this.m_XmlLayout.toByteArray(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Serializable toSerializable(LogEvent event) {
/* 142 */     return this.m_XmlLayout.toSerializable(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/* 148 */     return this.m_XmlLayout.getContentType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getContentFormat() {
/* 154 */     return this.m_XmlLayout.getContentFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getLocationInfo() {
/* 159 */     return this.m_LocationInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(LogEvent source, ByteBufferDestination destination) {
/* 164 */     this.m_XmlLayout.encode(source, destination);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsXMLLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */