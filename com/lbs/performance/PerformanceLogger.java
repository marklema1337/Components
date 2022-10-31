/*     */ package com.lbs.performance;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PerformanceLogger
/*     */   implements IPerformanceLogger
/*     */ {
/*  23 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(PerformanceLogger.class);
/*     */   
/*     */   protected String m_FileName;
/*     */   protected int m_CachedCount;
/*     */   protected ArrayList m_LogEnties;
/*     */   protected boolean m_AutoFlash = true;
/*     */   
/*     */   public PerformanceLogger(String fileName, int cachedCount) {
/*  31 */     this.m_FileName = fileName;
/*  32 */     this.m_CachedCount = cachedCount;
/*     */     
/*  34 */     if (this.m_CachedCount <= 0) {
/*     */       
/*  36 */       this.m_AutoFlash = (this.m_CachedCount == 0);
/*  37 */       this.m_LogEnties = new ArrayList();
/*     */     }
/*     */     else {
/*     */       
/*  41 */       this.m_AutoFlash = true;
/*  42 */       this.m_LogEnties = new ArrayList(this.m_CachedCount);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkFlash() {
/*  48 */     if (this.m_AutoFlash && this.m_LogEnties.size() >= this.m_CachedCount) {
/*  49 */       flush();
/*     */     }
/*     */   }
/*     */   
/*     */   protected PrintWriter openWriter() {
/*  54 */     PrintWriter writer = null;
/*  55 */     OutputStreamWriter stream = null;
/*     */     
/*     */     try {
/*  58 */       File out = new File(this.m_FileName);
/*  59 */       stream = new FileWriter(out, true);
/*  60 */       writer = new PrintWriter(stream);
/*     */     }
/*  62 */     catch (IOException e) {
/*     */       
/*  64 */       return null;
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  70 */         if (stream != null) {
/*  71 */           stream.close();
/*     */         }
/*  73 */       } catch (IOException ex) {
/*     */         
/*  75 */         ms_Logger.error(ex);
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     return writer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/*     */     try {
/*  86 */       if (this.m_LogEnties.size() == 0) {
/*     */         return;
/*     */       }
/*  89 */       synchronized (PerformanceLogger.class) {
/*     */         
/*  91 */         PrintWriter writer = openWriter();
/*     */         
/*     */         try {
/*  94 */           if (writer == null) {
/*     */             return;
/*     */           }
/*     */           
/*  98 */           for (int i = 0; i < this.m_LogEnties.size(); i++) {
/*     */             
/* 100 */             LogEntry entry = this.m_LogEnties.get(i);
/*     */             
/* 102 */             if (entry != null && !StringUtil.isEmpty(entry.toString())) {
/*     */               
/* 104 */               writer.write(entry.toString());
/* 105 */               writer.write("\n");
/*     */             } 
/*     */           } 
/*     */           
/* 109 */           this.m_LogEnties.clear();
/*     */         }
/*     */         finally {
/*     */           
/* 113 */           if (writer != null)
/*     */           {
/* 115 */             writer.flush();
/* 116 */             writer.close();
/* 117 */             writer = null;
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 122 */     } catch (Exception e) {
/*     */       
/* 124 */       LbsConsole.getLogger("Data.Client.PerfLogger").error(null, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void add(LogEntry entry) {
/* 133 */     this.m_LogEnties.add(entry);
/* 134 */     checkFlash();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void entering(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/* 142 */     LogEntry entry = new LogEntry(0, sourceClass, sourceMethod, level, sessionID, message);
/* 143 */     add(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exiting(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/* 152 */     LogEntry entry = new LogEntry(1, sourceClass, sourceMethod, level, sessionID, message);
/* 153 */     add(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/* 162 */     LogEntry entry = new LogEntry(2, sourceClass, sourceMethod, level, sessionID, message);
/* 163 */     add(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 172 */     flush();
/* 173 */     super.finalize();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\PerformanceLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */