/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.controls.ILogger;
/*     */ 
/*     */ public class LocalizationConsoleLogger
/*     */   implements ILogger
/*     */ {
/*     */   private LbsConsole m_Logger;
/*     */   
/*     */   public LocalizationConsoleLogger(String loggerName) {
/*  12 */     this.m_Logger = LbsConsole.getLogger(loggerName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void trace(Object message) {
/*  18 */     this.m_Logger.trace(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void trace(Object message, Throwable t) {
/*  24 */     this.m_Logger.trace(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void trace(Throwable t) {
/*  30 */     this.m_Logger.trace(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Object message) {
/*  36 */     this.m_Logger.debug(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Object message, Throwable t) {
/*  42 */     this.m_Logger.debug(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Throwable t) {
/*  48 */     this.m_Logger.debug(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Object message) {
/*  54 */     this.m_Logger.info(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Object message, Throwable t) {
/*  60 */     this.m_Logger.info(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Throwable t) {
/*  66 */     this.m_Logger.info(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Object message) {
/*  72 */     this.m_Logger.warn(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Object message, Throwable t) {
/*  78 */     this.m_Logger.warn(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Throwable t) {
/*  84 */     this.m_Logger.warn(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message) {
/*  90 */     this.m_Logger.error(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message, Throwable t) {
/*  96 */     this.m_Logger.error(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Throwable t) {
/* 102 */     this.m_Logger.error(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatal(Object message) {
/* 108 */     this.m_Logger.fatal(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatal(Object message, Throwable t) {
/* 114 */     this.m_Logger.fatal(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatal(Throwable t) {
/* 120 */     this.m_Logger.fatal(t);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LocalizationConsoleLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */