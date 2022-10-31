/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.IPerformanceLogger;
/*     */ import com.lbs.console.PerformanceLogItem;
/*     */ import com.lbs.console.PerformanceLogItemLine;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Hashtable;
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
/*     */ public class ExcelLogger
/*     */   implements IPerformanceLogger
/*     */ {
/*     */   private WritableExcelAPI m_ExcelAPI;
/*  42 */   private Hashtable m_LogItemContainers = new Hashtable<>(); private int m_SheetIndex; private int m_RowIndex; private static final int NUMBER_COL_ITEM_NAME = 0;
/*     */   private static final int NUMBER_COL_EFFECTED_ROW_COUNT = 1;
/*     */   private static final int NUMBER_COL_START_TIME = 2;
/*     */   
/*     */   public void flush(String logFilePath) {
/*  47 */     this.m_ExcelAPI = new WritableExcelAPI();
/*  48 */     this.m_SheetIndex = 0;
/*  49 */     File logFile = new File(logFilePath);
/*  50 */     if (logFile.exists())
/*  51 */       logFile.delete(); 
/*  52 */     this.m_ExcelAPI.openExcelFile(logFilePath, false);
/*     */     
/*  54 */     Enumeration enumThreadContent = this.m_LogItemContainers.keys();
/*  55 */     ArrayList<Object> threadNames = new ArrayList();
/*     */     
/*  57 */     while (enumThreadContent.hasMoreElements()) {
/*     */       
/*  59 */       Object object = enumThreadContent.nextElement();
/*  60 */       if (object instanceof String && !JLbsStringUtil.isEmpty((String)object)) {
/*  61 */         threadNames.add(object);
/*     */       }
/*     */     } 
/*  64 */     Object[] objects = threadNames.toArray();
/*  65 */     Arrays.sort(objects);
/*     */     
/*  67 */     for (int i = 0; i < objects.length; i++) {
/*     */       
/*  69 */       String threadName = (String)objects[i];
/*  70 */       processThreadSheet(threadName);
/*  71 */       this.m_SheetIndex++;
/*     */     } 
/*     */     
/*  74 */     this.m_ExcelAPI.closeExcelFile(false, false);
/*     */     
/*  76 */     this.m_LogItemContainers.clear();
/*     */   }
/*     */   private static final int NUMBER_COL_EXTRA_DATA = 3; private static final String LOG_HEADER_ITEM_NAME = "ITEM NAME"; private static final String LOG_HEADER_EFFECTED_ROW_COUNT = "ROW COUNT (APPROXIMATE)"; private static final String LOG_HEADER_START_TIME = "START TIME"; private static final String LOG_HEADER_EXTRA_DATA = "DETAIL";
/*     */   
/*     */   private void processThreadSheet(String threadName) {
/*  81 */     this.m_ExcelAPI.addSheet(threadName, this.m_SheetIndex);
/*  82 */     this.m_RowIndex = 0;
/*  83 */     processHeaderLine();
/*     */     
/*  85 */     Object objLogItems = this.m_LogItemContainers.get(threadName);
/*  86 */     if (objLogItems instanceof ArrayList) {
/*     */       
/*  88 */       ArrayList<PerformanceLogItem> logItems = (ArrayList)objLogItems;
/*  89 */       for (int i = 0; i < logItems.size(); i++) {
/*     */         
/*  91 */         PerformanceLogItem logItem = logItems.get(i);
/*  92 */         processLogItem(logItem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void processLogItem(PerformanceLogItem logItem) {
/*  99 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 0, logItem.ItemName, 
/* 100 */         1, -1, false, false);
/*     */     
/* 102 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 1, String.valueOf(logItem.EffectedRowCount), 
/* 103 */         3, -1, false, false);
/*     */     
/* 105 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 2, String.valueOf(logItem.StartTime), 
/* 106 */         3, -1, false, false);
/*     */     
/* 108 */     String extraData = (logItem.ExtraData == null) ? 
/* 109 */       "" : 
/* 110 */       String.valueOf(logItem.ExtraData);
/*     */     
/* 112 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 3, extraData, 
/* 113 */         1, -1, false, false);
/*     */     
/* 115 */     if (logItem.Erronous) {
/*     */       
/* 117 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 0, 8);
/* 118 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 1, 8);
/* 119 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 2, 8);
/* 120 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 3, 8);
/*     */     } 
/*     */     
/* 123 */     this.m_RowIndex++;
/*     */     
/* 125 */     ArrayList<PerformanceLogItemLine> itemLines = logItem.ItemLines;
/*     */     
/* 127 */     int startRowIndex = this.m_RowIndex;
/*     */     
/* 129 */     if (itemLines.size() > 0) {
/* 130 */       processLogItemHeaderLine();
/*     */     }
/* 132 */     for (int i = 0; i < itemLines.size(); i++) {
/* 133 */       processLogItemLine(itemLines.get(i));
/*     */     }
/* 135 */     int endRowIndex = this.m_RowIndex - 1;
/*     */     
/* 137 */     if (endRowIndex > startRowIndex) {
/* 138 */       this.m_ExcelAPI.groupAndOutlineRows(this.m_SheetIndex, startRowIndex, endRowIndex, true);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processLogItemLine(PerformanceLogItemLine logItemLine) {
/* 143 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 1, logItemLine.ItemName, 
/* 144 */         1, -1, false, false);
/*     */     
/* 146 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 2, String.valueOf(logItemLine.StartTime), 
/* 147 */         3, -1, false, false);
/*     */     
/* 149 */     String extraData = (logItemLine.ExtraData == null) ? 
/* 150 */       "" : 
/* 151 */       String.valueOf(logItemLine.ExtraData);
/*     */     
/* 153 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 3, extraData, 
/* 154 */         1, -1, false, false);
/*     */     
/* 156 */     if (logItemLine.Erronous) {
/*     */       
/* 158 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 1, 8);
/* 159 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 2, 8);
/* 160 */       this.m_ExcelAPI.setCellBackgroundColor(this.m_SheetIndex, this.m_RowIndex, 3, 8);
/*     */     } 
/*     */     
/* 163 */     this.m_RowIndex++;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processHeaderLine() {
/* 168 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 0, "ITEM NAME", 
/* 169 */         3, -1, false, true);
/*     */     
/* 171 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 1, "ROW COUNT (APPROXIMATE)", 
/* 172 */         3, -1, false, true);
/*     */     
/* 174 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 2, "START TIME", 
/* 175 */         3, -1, false, true);
/*     */     
/* 177 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 3, "DETAIL", 
/* 178 */         3, -1, false, true);
/*     */     
/* 180 */     this.m_RowIndex++;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processLogItemHeaderLine() {
/* 185 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 1, "ITEM NAME", 
/* 186 */         3, -1, false, true);
/*     */     
/* 188 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 2, "START TIME", 
/* 189 */         3, -1, false, true);
/*     */     
/* 191 */     this.m_ExcelAPI.setCellValue(this.m_SheetIndex, this.m_RowIndex, 3, "DETAIL", 
/* 192 */         3, -1, false, true);
/*     */     
/* 194 */     this.m_RowIndex++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String threadName, PerformanceLogItem logItem) {
/* 199 */     Object objLogItems = this.m_LogItemContainers.get(threadName);
/* 200 */     ArrayList<PerformanceLogItem> logItems = null;
/* 201 */     if (objLogItems instanceof ArrayList) {
/* 202 */       logItems = (ArrayList)objLogItems;
/*     */     } else {
/* 204 */       logItems = new ArrayList();
/*     */     } 
/* 206 */     logItems.add(logItem);
/*     */     
/* 208 */     this.m_LogItemContainers.put(threadName, logItems);
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String threadName, String parentName, PerformanceLogItemLine logItemLine) {
/* 213 */     PerformanceLogItem logItem = getLogItem(threadName, parentName);
/* 214 */     if (logItem == null) {
/* 215 */       logItem = new PerformanceLogItem(parentName, logItemLine.StartTime, 0, null);
/*     */     }
/* 217 */     logItem.ItemLines.add(logItemLine);
/* 218 */     if (logItemLine.Erronous) {
/* 219 */       logItem.Erronous = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private PerformanceLogItem getLogItem(String threadName, String itemName) {
/* 224 */     PerformanceLogItem dummyLogItem = new PerformanceLogItem(itemName, null, 0, null);
/*     */     
/* 226 */     Object objLogItems = this.m_LogItemContainers.get(threadName);
/* 227 */     ArrayList<PerformanceLogItem> logItems = null;
/* 228 */     if (objLogItems instanceof ArrayList) {
/*     */       
/* 230 */       logItems = (ArrayList)objLogItems;
/* 231 */       int index = logItems.lastIndexOf(dummyLogItem);
/* 232 */       if (index >= 0) {
/* 233 */         return logItems.get(index);
/*     */       }
/*     */     } 
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 241 */     ExcelLogger excelLogger = new ExcelLogger();
/*     */     
/* 243 */     excelLogger.log("Thread 03", createPerformanceLogItem("Thread 03", "001", 2300, null));
/* 244 */     excelLogger.log("Thread 03", createPerformanceLogItem("Thread 03", "002", 4500, null));
/* 245 */     excelLogger.log("Thread 02", createPerformanceLogItem("Thread 02", "001", 4500, null));
/*     */     
/* 247 */     excelLogger.log("Thread 03", "Thread 03 - 002", createPerformanceLogItemLine("Thread 03", "002", "001", null));
/* 248 */     excelLogger.log("Thread 03", "Thread 03 - 002", createPerformanceLogItemLine("Thread 03", "002", "002", null));
/*     */     
/* 250 */     excelLogger.flush("D:\\Temp\\PerfLog.xls");
/*     */   }
/*     */ 
/*     */   
/*     */   private static PerformanceLogItem createPerformanceLogItem(String threadName, String extension, int rowCount, Object extraData) {
/* 255 */     String logItemName = String.valueOf(threadName) + " - " + extension;
/* 256 */     return new PerformanceLogItem(logItemName, GregorianCalendar.getInstance().getTime(), rowCount, extraData);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static PerformanceLogItemLine createPerformanceLogItemLine(String threadName, String extension, String lineExtension, Object extraData) {
/* 262 */     String parentName = String.valueOf(threadName) + " - " + extension;
/* 263 */     String lineItemName = String.valueOf(parentName) + " - " + lineExtension;
/* 264 */     return new PerformanceLogItemLine(lineItemName, GregorianCalendar.getInstance().getTime(), extraData);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ExcelLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */