/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class LbsLayoutFactory
/*     */ {
/*     */   private static final String LINE_INFO_PATTERN = "\t(at %l)";
/*     */   
/*     */   public static LbsLayout createLayout(LogFormat format) {
/*     */     LbsXMLLayout lbsXMLLayout;
/*  20 */     switch (format.m_LogFormat) {
/*     */ 
/*     */       
/*     */       case 2:
/*  24 */         lbsXMLLayout = new LbsXMLLayout(format.m_IncludeLineInfo);
/*  25 */         return new LbsLayout(lbsXMLLayout);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  30 */     LbsPatternLayout layout = new LbsPatternLayout(format.m_PlainLogLayoutPattern);
/*  31 */     return new LbsLayout(layout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LogFormat fromLbsLayout(LbsLayout layout) {
/*  38 */     if (layout == null || layout.getLayout() == null)
/*  39 */       return null; 
/*  40 */     LogFormat format = new LogFormat(null);
/*  41 */     if (layout.getLayout() instanceof LbsXMLLayout) {
/*     */       
/*  43 */       format.m_LogFormat = 2;
/*  44 */       format.m_IncludeLineInfo = ((LbsXMLLayout)layout.getLayout()).getLocationInfo();
/*  45 */       return format;
/*     */     } 
/*  47 */     if (layout.getLayout() instanceof LbsPatternLayout) {
/*     */       
/*  49 */       format.m_LogFormat = 1;
/*  50 */       String pattern = ((LbsPatternLayout)layout.getLayout()).getConversionPattern();
/*  51 */       format.m_PlainLogLayoutPattern = pattern;
/*  52 */       return format;
/*     */     } 
/*     */     
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  60 */     LbsLayout layout = createLayout(LogFormat.XML(true));
/*  61 */     LogFormat format = fromLbsLayout(layout);
/*     */     
/*  63 */     layout = createLayout(LogFormat.XML(false));
/*  64 */     format = fromLbsLayout(layout);
/*     */     
/*  66 */     layout = createLayout(LogFormat.PLAIN());
/*  67 */     format = fromLbsLayout(layout);
/*  68 */     layout = createLayout(LogFormat.PLAIN(true));
/*  69 */     format = fromLbsLayout(layout);
/*  70 */     layout = createLayout(LogFormat.PLAIN(false));
/*  71 */     format = fromLbsLayout(layout);
/*     */     
/*  73 */     layout = createLayout(LogFormat.PLAIN("%d [%c %5p] %m\t(%l)%n"));
/*  74 */     format = fromLbsLayout(layout);
/*  75 */     layout = createLayout(LogFormat.PLAIN("%d [%c %5p] %m\t(at %l)%n"));
/*  76 */     format = fromLbsLayout(layout);
/*  77 */     System.out.println(format);
/*     */   }
/*     */   
/*     */   public static class LogFormat
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     private static final int PLAIN = 1;
/*     */     private static final int XML = 2;
/*     */     private int m_LogFormat;
/*     */     private String m_PlainLogLayoutPattern;
/*     */     private boolean m_IncludeLineInfo;
/*     */     
/*     */     public boolean isIncludeLineInfo() {
/*  91 */       return this.m_IncludeLineInfo;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isPlain() {
/*  96 */       return (this.m_LogFormat == 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isXML() {
/* 101 */       return (this.m_LogFormat == 2);
/*     */     }
/*     */ 
/*     */     
/*     */     public String logLayoutPattern() {
/* 106 */       return this.m_PlainLogLayoutPattern;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 111 */       LogFormat format = new LogFormat();
/* 112 */       format.m_LogFormat = this.m_LogFormat;
/* 113 */       format.m_IncludeLineInfo = this.m_IncludeLineInfo;
/* 114 */       format.m_PlainLogLayoutPattern = this.m_PlainLogLayoutPattern;
/* 115 */       return format;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private LogFormat() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static LogFormat PLAIN() {
/* 127 */       return PLAIN(false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static LogFormat PLAIN(boolean includeLineInfo) {
/* 138 */       String layoutString = "%d [%c %5p] %m";
/* 139 */       if (includeLineInfo)
/* 140 */         layoutString = String.valueOf(layoutString) + "\t(at %l)"; 
/* 141 */       layoutString = String.valueOf(layoutString) + "%n";
/* 142 */       return PLAIN(layoutString);
/*     */     }
/*     */ 
/*     */     
/*     */     public static LogFormat PLAIN(String layoutPattern) {
/* 147 */       if (layoutPattern == null || layoutPattern.length() == 0)
/* 148 */         return PLAIN(); 
/* 149 */       LogFormat format = new LogFormat();
/* 150 */       format.m_LogFormat = 1;
/* 151 */       format.m_PlainLogLayoutPattern = layoutPattern;
/* 152 */       return format;
/*     */     }
/*     */ 
/*     */     
/*     */     public static LogFormat XML(boolean includeLineInfo) {
/* 157 */       LogFormat format = new LogFormat();
/* 158 */       format.m_LogFormat = 2;
/* 159 */       format.m_IncludeLineInfo = includeLineInfo;
/* 160 */       return format;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsLayoutFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */