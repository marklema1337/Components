/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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
/*     */ public class UpdatelessExcelConsole
/*     */   extends LbsConsole
/*     */ {
/*     */   private IPerformanceLogger m_PerfLogger;
/*  19 */   private ThreadLocal m_CurrentTable = new ThreadLocal();
/*  20 */   private ThreadLocal m_CurrentItem = new ThreadLocal();
/*  21 */   private ThreadLocal m_TableStarted = new ThreadLocal();
/*     */   
/*  23 */   private ThreadLocal m_FilterTable = new ThreadLocal();
/*  24 */   private ThreadLocal m_FilterTableStarted = new ThreadLocal();
/*  25 */   private ThreadLocal m_LastCurrentTable = new ThreadLocal();
/*     */   
/*  27 */   private ThreadLocal m_ErrorTable = new ThreadLocal();
/*  28 */   private ThreadLocal m_ErrorTableStarted = new ThreadLocal();
/*     */   
/*     */   public static boolean WRITE_UNKNOWN = false;
/*     */   
/*     */   public static boolean WRITE_TRIGGERS = false;
/*     */ 
/*     */   
/*     */   public UpdatelessExcelConsole(IPerformanceLogger logger) {
/*  36 */     this.m_PerfLogger = logger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startTable(String tableName, String message, int lastLREF) {
/*  41 */     if (!WRITE_UNKNOWN && "Unknown".equals(tableName)) {
/*     */       return;
/*     */     }
/*  44 */     if (isTableStarted()) {
/*  45 */       endTable();
/*     */     }
/*  47 */     Date now = Calendar.getInstance().getTime();
/*  48 */     this.m_CurrentTable.set(tableName);
/*  49 */     this.m_CurrentItem.set(tableName);
/*  50 */     this.m_TableStarted.set(new Boolean(true));
/*  51 */     if (this.m_PerfLogger != null) {
/*  52 */       this.m_PerfLogger.log(Thread.currentThread().getName(), new PerformanceLogItem(tableName, now, lastLREF, message));
/*     */     }
/*  54 */     this.m_LastCurrentTable.set(new Object[] { tableName, now, Integer.valueOf(lastLREF), message });
/*     */   }
/*     */ 
/*     */   
/*     */   public void endTable() {
/*  59 */     this.m_CurrentTable.set("Unknown");
/*  60 */     this.m_CurrentItem.set("Unknown");
/*  61 */     this.m_TableStarted.set(new Boolean(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void startItem(String item) {
/*  66 */     this.m_CurrentItem.set(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endItem() {
/*  71 */     this.m_CurrentItem.set(this.m_CurrentTable.get());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isTableStarted() {
/*  76 */     Object o = this.m_TableStarted.get();
/*  77 */     if (o instanceof Boolean)
/*  78 */       return ((Boolean)o).booleanValue(); 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void startFilterTable(String tableName, Date now, int lastLREF, String message) {
/*  84 */     if (!WRITE_UNKNOWN && "Unknown".equals(tableName)) {
/*     */       return;
/*     */     }
/*  87 */     if (isFilterTableStarted()) {
/*  88 */       endFilterTable();
/*     */     }
/*  90 */     this.m_FilterTable.set(tableName);
/*  91 */     this.m_FilterTableStarted.set(new Boolean(true));
/*  92 */     if (this.m_PerfLogger != null) {
/*  93 */       this.m_PerfLogger.log(String.valueOf(Thread.currentThread().getName()) + "_Filtered", new PerformanceLogItem(tableName, now, lastLREF, 
/*  94 */             message));
/*     */     }
/*     */   }
/*     */   
/*     */   private void endFilterTable() {
/*  99 */     this.m_FilterTable.set("Unknown");
/* 100 */     this.m_FilterTableStarted.set(new Boolean(false));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isFilterTableStarted() {
/* 105 */     Object o = this.m_FilterTableStarted.get();
/* 106 */     if (o instanceof Boolean)
/* 107 */       return ((Boolean)o).booleanValue(); 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void startErrorTable(String tableName, Date now, int lastLREF, String message) {
/* 113 */     if (!WRITE_UNKNOWN && "Unknown".equals(tableName)) {
/*     */       return;
/*     */     }
/* 116 */     if (isErrorTableStarted()) {
/* 117 */       endErrorTable();
/*     */     }
/* 119 */     this.m_ErrorTable.set(tableName);
/* 120 */     this.m_ErrorTableStarted.set(new Boolean(true));
/* 121 */     if (this.m_PerfLogger != null) {
/* 122 */       this.m_PerfLogger.log("Errors", new PerformanceLogItem(tableName, now, lastLREF, message));
/*     */     }
/*     */   }
/*     */   
/*     */   private void endErrorTable() {
/* 127 */     this.m_ErrorTable.set("Unknown");
/* 128 */     this.m_ErrorTableStarted.set(new Boolean(false));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isErrorTableStarted() {
/* 133 */     Object o = this.m_ErrorTableStarted.get();
/* 134 */     if (o instanceof Boolean)
/* 135 */       return ((Boolean)o).booleanValue(); 
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Object message) {
/* 141 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Object message, Throwable t) {
/* 146 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Throwable t) {
/* 151 */     internalLogPerformanceItem(getMessage(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Object message) {
/* 156 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Object message, Throwable t) {
/* 161 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Throwable t) {
/* 166 */     internalLogPerformanceItem(getMessage(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Object message) {
/* 171 */     internalLogPerformanceItem(message, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Object message, Throwable t) {
/* 176 */     internalLogPerformanceItem(message + " Exception=(" + getMessage(t) + ")", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Throwable t) {
/* 181 */     internalLogPerformanceItem(getMessage(t), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Object message) {
/* 186 */     internalLogPerformanceItem(message, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Object message, Throwable t) {
/* 191 */     internalLogPerformanceItem(message + " Exception=(" + getMessage(t) + ")", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Throwable t) {
/* 196 */     internalLogPerformanceItem(getMessage(t), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Object message) {
/* 201 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Object message, Throwable t) {
/* 206 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Throwable t) {
/* 211 */     internalLogPerformanceItem(getMessage(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Object message) {
/* 216 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Object message, Throwable t) {
/* 221 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Throwable t) {
/* 226 */     internalLogPerformanceItem(getMessage(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Object message) {
/* 231 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalLogPerformanceItem(Object message) {
/* 236 */     internalLogPerformanceItem(message, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalLogPerformanceItem(Object message, boolean error) {
/* 241 */     if (this.m_PerfLogger != null) {
/*     */       
/* 243 */       String table = getThreadLocal(this.m_CurrentTable);
/*     */       
/* 245 */       if (!WRITE_UNKNOWN && "Unknown".equals(table)) {
/*     */         return;
/*     */       }
/* 248 */       Date now = Calendar.getInstance().getTime();
/* 249 */       String item = getThreadLocal(this.m_CurrentItem);
/*     */       
/* 251 */       if (!isTableStarted())
/* 252 */         startTable(table, "Starting table inside Excel Logger", -1); 
/* 253 */       this.m_PerfLogger.log(Thread.currentThread().getName(), table, new PerformanceLogItemLine(item, now, message, error));
/*     */       
/* 255 */       if (!table.equals(item) && !"Unknown".equals(item)) {
/*     */         
/* 257 */         String filter = "";
/* 258 */         Object filterTable = this.m_FilterTable.get();
/* 259 */         if (filterTable instanceof String)
/*     */         {
/* 261 */           filter = (String)filterTable;
/*     */         }
/* 263 */         if (!table.equals(filter)) {
/*     */           
/* 265 */           Object[] last = this.m_LastCurrentTable.get();
/* 266 */           startFilterTable((String)last[0], (Date)last[1], ((Integer)last[2]).intValue(), (String)last[3]);
/*     */         } 
/* 268 */         this.m_PerfLogger.log(String.valueOf(Thread.currentThread().getName()) + "_Filtered", table, new PerformanceLogItemLine(item, now, 
/* 269 */               message, error));
/*     */       } 
/* 271 */       if (error) {
/*     */         
/* 273 */         String err = "";
/* 274 */         Object errTable = this.m_ErrorTable.get();
/* 275 */         if (errTable instanceof String)
/* 276 */           err = (String)errTable; 
/* 277 */         if (!table.equals(err)) {
/*     */           
/* 279 */           Object[] last = this.m_LastCurrentTable.get();
/* 280 */           startErrorTable((String)last[0], (Date)last[1], ((Integer)last[2]).intValue(), (String)last[3]);
/*     */         } 
/* 282 */         this.m_PerfLogger.log("Errors", table, new PerformanceLogItemLine(item, now, message));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getThreadLocal(ThreadLocal var) {
/* 289 */     Object o = var.get();
/* 290 */     if (o instanceof String)
/* 291 */       return (String)o; 
/* 292 */     return "Unknown";
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Object message, Throwable t) {
/* 297 */     internalLogPerformanceItem(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Throwable t) {
/* 302 */     internalLogPerformanceItem(getMessage(t));
/*     */   }
/*     */ 
/*     */   
/*     */   private Object getMessage(Throwable t) {
/* 307 */     String message = t.getLocalizedMessage();
/* 308 */     if (message == null || message.length() == 0) {
/*     */       
/* 310 */       message = t.getClass().getName();
/* 311 */       StackTraceElement[] trace = t.getStackTrace();
/* 312 */       StringBuilder strBuffer = new StringBuilder(message);
/* 313 */       for (int i = 0; i < trace.length && i < 3; i++)
/*     */       {
/* 315 */         strBuffer.append(" " + trace[i]);
/*     */       }
/*     */       
/* 318 */       message = strBuffer.toString();
/*     */     } 
/* 320 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void finalizeLog(String logFilePath) {
/* 325 */     if (isTableStarted())
/* 326 */       endTable(); 
/* 327 */     if (isFilterTableStarted())
/* 328 */       endFilterTable(); 
/* 329 */     if (isErrorTableStarted())
/* 330 */       endErrorTable(); 
/* 331 */     this.m_PerfLogger.flush(logFilePath);
/* 332 */     this.m_PerfLogger = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabledFor2(LbsLevel level) {
/* 337 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getEffectiveLevel() {
/* 342 */     return LbsLevel.ALL;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getLevel2() {
/* 347 */     return LbsLevel.ALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel2(LbsLevel level) {}
/*     */ 
/*     */   
/*     */   public boolean isRootLogger() {
/* 356 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 361 */     return "ExcelLogger";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\UpdatelessExcelConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */