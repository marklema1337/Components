/*     */ package com.lbs.channel;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.http.HttpChannel;
/*     */ import com.lbs.message.JLbsMessageDialogResult;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.rmi.HttpInvokerRequestExecutor;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsIniProperties;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.WritableExcelAPI;
/*     */ import java.io.File;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.filechooser.FileFilter;
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
/*     */ public class ChannelStatisticsListener
/*     */   implements IChannelStatisticsListener
/*     */ {
/*     */   private static final int COL_TIME_RECEIVE = 6;
/*     */   private static final int COL_TIME_SEND = 5;
/*     */   private static final int COL_DATA_RECEIVE = 4;
/*     */   private static final int COL_DATA_SEND = 3;
/*     */   private static final int COL_HIT_COUNT_RECEIVE = 2;
/*     */   private static final int COL_HIT_COUNT_SEND = 1;
/*     */   private static final int COL_URI = 0;
/*     */   private static final int HEADER_ROW = 0;
/*     */   private static final int SHEET_STATISTICS = 0;
/*  46 */   private static JLbsIniProperties ms_Settings = null;
/*     */   
/*     */   private static final String LOGS_DIR = "Logs";
/*     */   
/*     */   private static final String LOGS_FILE = "ChannelStat.csv";
/*     */   
/*     */   private static final String SETTINGS_DIR = "Settings";
/*     */   
/*     */   private static final String SETTINGS_FILE = "ChannelStat.ini";
/*     */   
/*     */   private static final String KEY_COLLECT_STATISTICS = "collectStats";
/*     */   
/*     */   private static final String COLUMN_TITLE_URI = "URI";
/*     */   
/*     */   private static final String COLUMN_TITLE_TOTAL_DATA_LENGTH = "TOTAL DATA LENGTH (bytes)";
/*     */   private static final String COLUMN_TITLE_HIT_COUNT = "HIT COUNT";
/*     */   private static final String COLUMN_TITLE_TIME = "AVG TIME (msec)";
/*     */   private static final String CSV_COLUMN_TITLES = "URI,TOTAL DATA LENGTH (bytes),HIT COUNT,AVG TIME (msec)";
/*     */   private static Date ms_CounterStartTime;
/*     */   private static Date ms_CounterEndTime;
/*     */   private static Hashtable<String, Integer> ms_URIReceived;
/*     */   private static Hashtable<String, Integer> ms_URIReceivedCounter;
/*     */   private static Hashtable<String, Long> ms_URIReceivedTime;
/*     */   private static Hashtable<String, Integer> ms_URISent;
/*     */   private static Hashtable<String, Integer> ms_URISentCounter;
/*     */   private static Hashtable<String, Long> ms_URISentTime;
/*  72 */   private static int ms_TotalRequestsSent = 0;
/*  73 */   private static int ms_TotalResponses = 0;
/*  74 */   private static int ms_TotalDataSent = 0;
/*  75 */   private static int ms_TotalDataReceived = 0;
/*     */   
/*     */   private static boolean COLLECT_CHANNEL_STATISTICS = false;
/*     */   
/*     */   public static boolean SAVE_TO_EXCEL = true;
/*     */   
/*  81 */   private static String ms_LastDirectory = null;
/*  82 */   private static final LbsConsole ms_Console = LbsConsole.getLogger(ChannelStatisticsListener.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void receiveData(ChannelStatisticsEvent e) {
/*  90 */     if (COLLECT_CHANNEL_STATISTICS && e != null && ms_URIReceived != null && ms_URIReceivedCounter != null && 
/*  91 */       ms_URIReceivedTime != null)
/*     */     {
/*  93 */       synchronized (ms_URIReceived) {
/*     */         
/*  95 */         collectStat(e, ms_URIReceived, ms_URIReceivedCounter, ms_URIReceivedTime);
/*  96 */         ms_TotalDataReceived += e.getDataLength();
/*  97 */         ms_TotalResponses++;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendData(ChannelStatisticsEvent e) {
/* 104 */     if (COLLECT_CHANNEL_STATISTICS && e != null && ms_URISent != null && ms_URISentCounter != null && ms_URISentTime != null)
/*     */     {
/* 106 */       synchronized (ms_URISent) {
/*     */         
/* 108 */         collectStat(e, ms_URISent, ms_URISentCounter, ms_URISentTime);
/* 109 */         ms_TotalRequestsSent++;
/* 110 */         ms_TotalDataSent += e.getDataLength();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void collectStat(ChannelStatisticsEvent e, Hashtable<String, Integer> statTable, Hashtable<String, Integer> counterTable, Hashtable<String, Long> timeTable) {
/* 118 */     String uri = e.getUri();
/* 119 */     if (!JLbsStringUtil.isEmpty(uri)) {
/*     */       
/* 121 */       Integer obj = statTable.get(uri);
/*     */       
/* 123 */       if (obj != null) {
/*     */         
/* 125 */         int totalLength = obj.intValue();
/* 126 */         totalLength += e.getDataLength();
/* 127 */         statTable.put(uri, Integer.valueOf(totalLength));
/*     */       }
/*     */       else {
/*     */         
/* 131 */         statTable.put(uri, Integer.valueOf(e.getDataLength()));
/*     */       } 
/*     */       
/* 134 */       obj = counterTable.get(uri);
/*     */       
/* 136 */       if (obj != null) {
/*     */         
/* 138 */         int count = obj.intValue();
/* 139 */         counterTable.put(uri, Integer.valueOf(++count));
/*     */       }
/*     */       else {
/*     */         
/* 143 */         counterTable.put(uri, Integer.valueOf(1));
/*     */       } 
/*     */       
/* 146 */       Long time = timeTable.get(uri);
/* 147 */       if (time != null) {
/*     */         
/* 149 */         timeTable.put(uri, Long.valueOf(e.getTimeElapsed() + time.longValue()));
/*     */       }
/*     */       else {
/*     */         
/* 153 */         timeTable.put(uri, Long.valueOf(e.getTimeElapsed()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reflectCountersToLogFile(IClientContext context) {
/* 160 */     if (COLLECT_CHANNEL_STATISTICS) {
/*     */       
/* 162 */       ms_CounterEndTime = Calendar.getInstance().getTime();
/*     */       
/* 164 */       if (SAVE_TO_EXCEL) {
/*     */         
/* 166 */         saveToExcel(context);
/*     */       }
/*     */       else {
/*     */         
/* 170 */         saveToLogsAsCSV();
/*     */       } 
/* 172 */       initialize();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void saveToExcel(IClientContext context) {
/* 179 */     if (ms_LastDirectory == null)
/*     */     {
/* 181 */       ms_LastDirectory = JLbsFileUtil.getUserDirectory();
/*     */     }
/* 183 */     JFileChooser chooser = new JFileChooser(ms_LastDirectory);
/* 184 */     chooser.setFileSelectionMode(0);
/* 185 */     chooser.setFileFilter(new FileFilter()
/*     */         {
/*     */           
/*     */           public String getDescription()
/*     */           {
/* 190 */             return "*.xls, *.xlsx";
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean accept(File f) {
/* 196 */             return !(!f.getName().endsWith(".xls") && !f.getName().endsWith("*.xlsx"));
/*     */           }
/*     */         });
/* 199 */     int result = chooser.showSaveDialog(null);
/* 200 */     if (result == 0) {
/*     */       
/* 202 */       File f = chooser.getSelectedFile();
/* 203 */       ms_LastDirectory = f.getParent();
/* 204 */       if (f.exists()) {
/*     */         
/* 206 */         JLbsMessageDialogResult result2 = context.showMessage("OVERWRITE_EXCEL_FILE", "File exists, overwrite?", null, 
/* 207 */             null);
/* 208 */         if (result2.button != 4)
/*     */           return; 
/* 210 */         f.delete();
/*     */       } 
/* 212 */       WritableExcelAPI api = new WritableExcelAPI();
/* 213 */       boolean open = api.openExcelFile(f.getAbsolutePath(), true);
/* 214 */       if (open) {
/*     */         
/* 216 */         writeExcelFile(context, api);
/*     */       }
/*     */       else {
/*     */         
/* 220 */         context.showMessage("CANNOT_OPEN_EXCEL_FILE", "Cannot open excel file!", null, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveToExcel(IClientContext context, String path) {
/*     */     try {
/* 230 */       if (COLLECT_CHANNEL_STATISTICS)
/*     */       {
/* 232 */         ms_CounterEndTime = Calendar.getInstance().getTime();
/* 233 */         boolean isWrittenExcel = false;
/* 234 */         WritableExcelAPI api = new WritableExcelAPI();
/* 235 */         if (!JLbsStringUtil.isEmpty(path) && (path.endsWith(".xls") || path.endsWith(".xlsx"))) {
/*     */           
/* 237 */           File f = new File(path);
/* 238 */           if (f.exists()) {
/* 239 */             f.delete();
/*     */           }
/* 241 */           boolean open = api.openExcelFile(f.getAbsolutePath(), true, path.endsWith(".xlsx"));
/*     */ 
/*     */           
/* 244 */           if (open) {
/*     */             
/* 246 */             writeExcelFile(context, api);
/* 247 */             isWrittenExcel = true;
/*     */           } 
/*     */         } 
/*     */         
/* 251 */         if (!isWrittenExcel) {
/* 252 */           saveToLogsAsCSV();
/*     */         }
/* 254 */         initialize();
/*     */       }
/*     */     
/* 257 */     } catch (Exception e) {
/*     */       
/* 259 */       ms_Console.error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeExcelFile(IClientContext context, WritableExcelAPI api) {
/* 266 */     prepareSheet(context, api, getResource(context, 3, "Statistics"), 0);
/* 267 */     Enumeration<String> keys = ms_URISent.keys();
/* 268 */     int row = 1;
/* 269 */     while (keys.hasMoreElements()) {
/*     */       
/* 271 */       String uri = keys.nextElement();
/* 272 */       int countSend = ((Integer)ms_URISentCounter.get(uri)).intValue();
/* 273 */       int dataSend = ((Integer)ms_URISent.get(uri)).intValue();
/* 274 */       long timeSend = ((Long)ms_URISentTime.get(uri)).longValue();
/* 275 */       Integer countReceive = ms_URIReceivedCounter.get(uri);
/* 276 */       Integer dataReceive = ms_URIReceived.get(uri);
/* 277 */       Long timeReceive = ms_URIReceivedTime.get(uri);
/* 278 */       api.setCellValue(0, row, 4, ((dataReceive == null) ? 
/* 279 */           false : 
/* 280 */           dataReceive.intValue()));
/* 281 */       api.setCellValue(0, row, 3, dataSend);
/* 282 */       api.setCellValue(0, row, 2, ((countReceive == null) ? 
/* 283 */           false : 
/* 284 */           countReceive.intValue()));
/* 285 */       api.setCellValue(0, row, 1, countSend);
/* 286 */       api.setCellValue(0, row, 6, ((timeReceive == null) ? 
/* 287 */           0L : 
/* 288 */           timeReceive.longValue()));
/* 289 */       api.setCellValue(0, row, 5, timeSend);
/* 290 */       api.setCellValue(0, row, 0, uri);
/* 291 */       row++;
/*     */     } 
/* 293 */     keys = ms_URIReceived.keys();
/* 294 */     while (keys.hasMoreElements()) {
/*     */       
/* 296 */       String uri = keys.nextElement();
/* 297 */       if (ms_URISent.containsKey(uri))
/*     */         continue; 
/* 299 */       int countReceive = ((Integer)ms_URIReceivedCounter.get(uri)).intValue();
/* 300 */       int dataReceive = ((Integer)ms_URIReceived.get(uri)).intValue();
/* 301 */       long timeReceive = ((Long)ms_URIReceivedTime.get(uri)).longValue();
/* 302 */       Integer countSend = ms_URISentCounter.get(uri);
/* 303 */       Integer dataSend = ms_URISent.get(uri);
/* 304 */       Long timeSend = ms_URISentTime.get(uri);
/* 305 */       api.setCellValue(0, row, 4, dataReceive);
/* 306 */       api.setCellValue(0, row, 3, ((dataSend == null) ? 
/* 307 */           false : 
/* 308 */           dataSend.intValue()));
/* 309 */       api.setCellValue(0, row, 2, countReceive);
/* 310 */       api.setCellValue(0, row, 1, ((countSend == null) ? 
/* 311 */           false : 
/* 312 */           countSend.intValue()));
/* 313 */       api.setCellValue(0, row, 6, timeReceive);
/* 314 */       api.setCellValue(0, row, 5, ((timeSend == null) ? 
/* 315 */           0L : 
/* 316 */           timeSend.longValue()));
/* 317 */       api.setCellValue(0, row, 0, uri);
/* 318 */       row++;
/*     */     } 
/*     */     
/* 321 */     api.setCellFontBoldProp(0, row + 1, 0, true);
/* 322 */     api.setCellFontBoldProp(0, row + 2, 0, true);
/* 323 */     api.setCellFontBoldProp(0, row + 3, 0, true);
/* 324 */     api.setCellFontBoldProp(0, row + 4, 0, true);
/* 325 */     api.setCellFontBoldProp(0, row + 5, 0, true);
/* 326 */     api.setCellValue(0, row + 1, 0, 
/* 327 */         getResource(context, 11, "Total time for collecting statistics (miliseconds)"));
/* 328 */     api.setCellValue(0, row + 2, 0, getResource(context, 12, "Total request count sent to server"));
/* 329 */     api.setCellValue(0, row + 3, 0, getResource(context, 13, "Total response count received from server"));
/* 330 */     api.setCellValue(0, row + 4, 0, getResource(context, 14, "Total data sent to server"));
/* 331 */     api.setCellValue(0, row + 5, 0, getResource(context, 15, "Total data received from server"));
/* 332 */     long totalTimeElapsed = ms_CounterEndTime.getTime() - ms_CounterStartTime.getTime();
/* 333 */     api.setCellValue(0, row + 1, 1, totalTimeElapsed);
/* 334 */     api.setCellValue(0, row + 2, 1, ms_TotalRequestsSent);
/* 335 */     api.setCellValue(0, row + 3, 1, ms_TotalResponses);
/* 336 */     api.setCellValue(0, row + 4, 1, ms_TotalDataSent);
/* 337 */     api.setCellValue(0, row + 5, 1, ms_TotalDataReceived);
/*     */     
/* 339 */     api.closeExcelFile(true, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void prepareSheet(IClientContext context, WritableExcelAPI api, String sheetTitle, int sheetIdx) {
/* 344 */     api.addSheet(sheetTitle, sheetIdx);
/* 345 */     api.setCellValue(sheetIdx, 0, 0, getResource(context, 4, "URI"));
/* 346 */     api.setCellValue(sheetIdx, 0, 1, getResource(context, 7, "HIT COUNT (Send)"));
/* 347 */     api.setCellValue(sheetIdx, 0, 2, 
/* 348 */         getResource(context, 8, "HIT COUNT (Receive)"));
/* 349 */     api.setCellValue(sheetIdx, 0, 3, getResource(context, 5, "TOTAL DATA LENGTH (bytes) (Send)"));
/* 350 */     api.setCellValue(sheetIdx, 0, 4, 
/* 351 */         getResource(context, 6, "TOTAL DATA LENGTH (bytes) (Receive)"));
/* 352 */     api.setCellValue(sheetIdx, 0, 5, getResource(context, 9, "AVG TIME (msec) (Send)"));
/* 353 */     api.setCellValue(sheetIdx, 0, 6, getResource(context, 10, "AVG TIME (msec) (Receive)"));
/* 354 */     api.setCellFontBoldProp(sheetIdx, 0, 0, 6, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getResource(IClientContext context, int tag, String defaultValue) {
/* 359 */     String msg = context.getLocalizationService().getItem(9000, tag);
/* 360 */     if (StringUtil.isEmpty(msg))
/* 361 */       return defaultValue; 
/* 362 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void saveToLogsAsCSV() {
/* 367 */     JLbsClientFS.makeDirectory("Logs");
/* 368 */     String logFilePath = JLbsClientFS.appendPath("Logs", "ChannelStat.csv");
/*     */ 
/*     */     
/*     */     try {
/* 372 */       byte[] statContent = getStatTableContents();
/* 373 */       JLbsClientFS.saveFile(logFilePath, statContent, true, true, false);
/*     */     }
/* 375 */     catch (Exception e) {
/*     */       
/* 377 */       ms_Console.error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initialize() {
/* 383 */     if (COLLECT_CHANNEL_STATISTICS) {
/*     */       
/* 385 */       HttpInvokerRequestExecutor.setStatisticsListener(new ChannelStatisticsListener());
/* 386 */       HttpChannel.setStatisticsListener(new ChannelStatisticsListener());
/* 387 */       ms_CounterStartTime = Calendar.getInstance().getTime();
/* 388 */       ms_URIReceived = new Hashtable<>();
/* 389 */       ms_URIReceivedCounter = new Hashtable<>();
/* 390 */       ms_URIReceivedTime = new Hashtable<>();
/* 391 */       ms_URISent = new Hashtable<>();
/* 392 */       ms_URISentCounter = new Hashtable<>();
/* 393 */       ms_URISentTime = new Hashtable<>();
/* 394 */       ms_TotalDataReceived = 0;
/* 395 */       ms_TotalDataSent = 0;
/* 396 */       ms_TotalRequestsSent = 0;
/* 397 */       ms_TotalResponses = 0;
/*     */     }
/*     */     else {
/*     */       
/* 401 */       HttpInvokerRequestExecutor.setStatisticsListener(null);
/* 402 */       HttpChannel.setStatisticsListener(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] getStatTableContents() {
/* 408 */     StringBuilder sb = new StringBuilder();
/* 409 */     sb.append("Start Time : ").append(ms_CounterStartTime);
/* 410 */     sb.append("\n\nReceive Statistics");
/*     */     
/* 412 */     sb.append("\nURI,TOTAL DATA LENGTH (bytes),HIT COUNT,AVG TIME (msec)");
/*     */     
/* 414 */     synchronized (ms_URIReceived) {
/*     */       
/* 416 */       reportStat(sb, ms_URIReceived, ms_URIReceivedCounter, ms_URIReceivedTime);
/*     */     } 
/*     */     
/* 419 */     sb.append("\n\nSend Statistics");
/*     */     
/* 421 */     sb.append("\nURI,TOTAL DATA LENGTH (bytes),HIT COUNT,AVG TIME (msec)");
/* 422 */     synchronized (ms_URISent) {
/*     */       
/* 424 */       reportStat(sb, ms_URISent, ms_URISentCounter, ms_URISentTime);
/*     */     } 
/*     */     
/* 427 */     sb.append("\n\nEnd Time : " + ms_CounterEndTime);
/* 428 */     sb.append("\n\n");
/*     */     
/* 430 */     return sb.toString().getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void reportStat(StringBuilder sb, Hashtable<String, Integer> statTable, Hashtable<String, Integer> counterTable, Hashtable<String, Long> timeTable) {
/* 440 */     Enumeration<String> statEnum = statTable.keys();
/* 441 */     while (statEnum.hasMoreElements()) {
/*     */       
/* 443 */       String key = statEnum.nextElement();
/* 444 */       Integer length = statTable.get(key);
/* 445 */       int totalLength = length.intValue();
/* 446 */       sb.append("\n").append(key).append(",").append(totalLength);
/*     */       
/* 448 */       Integer count = counterTable.get(key);
/* 449 */       sb.append(",").append(count);
/*     */       
/* 451 */       Long time = timeTable.get(key);
/* 452 */       sb.append(",").append(time.longValue() / count.intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadChannelStatSettings(IClientContext context) {
/* 458 */     byte[] localSettings = null;
/*     */     
/*     */     try {
/* 461 */       localSettings = context.loadLocalFile("Settings" + File.separator + "ChannelStat.ini");
/*     */     }
/* 463 */     catch (Exception ex) {
/*     */       
/* 465 */       System.out.println("Could not load Channel Statistic settings. Default values will be used.");
/*     */     } 
/*     */     
/* 468 */     if (localSettings != null) {
/*     */       
/* 470 */       JLbsIniProperties settings = new JLbsIniProperties();
/* 471 */       settings.load(localSettings);
/* 472 */       ms_Settings = settings;
/* 473 */       initializeStatSettings();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void initializeStatSettings() {
/* 479 */     String strCollectStat = getChannelStatSetting("collectStats");
/*     */     
/* 481 */     if (!JLbsStringUtil.isEmpty(strCollectStat)) {
/*     */       
/* 483 */       boolean collectStat = false;
/*     */       
/*     */       try {
/* 486 */         collectStat = Boolean.valueOf(strCollectStat).booleanValue();
/*     */       }
/* 488 */       catch (Exception e) {
/*     */         
/* 490 */         collectStat = false;
/*     */       } 
/*     */       
/* 493 */       COLLECT_CHANNEL_STATISTICS = collectStat;
/* 494 */       JLbsClientFS.deleteFile(JLbsClientFS.appendPath("Logs", "ChannelStat.csv"));
/* 495 */       initialize();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getChannelStatSetting(String key) {
/* 501 */     if (ms_Settings != null)
/* 502 */       return ms_Settings.getProperty(key); 
/* 503 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getChannelStatDebug() {
/* 508 */     return COLLECT_CHANNEL_STATISTICS;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setChannelStatDebug(boolean debug) {
/* 513 */     COLLECT_CHANNEL_STATISTICS = debug;
/* 514 */     if (COLLECT_CHANNEL_STATISTICS) {
/* 515 */       initialize();
/*     */     }
/*     */   }
/*     */   
/*     */   public static JLbsChannelAllStatistics getChannelAllStatistics() {
/* 520 */     JLbsChannelAllStatistics statistics = new JLbsChannelAllStatistics();
/*     */ 
/*     */     
/*     */     try {
/* 524 */       if (COLLECT_CHANNEL_STATISTICS)
/*     */       {
/* 526 */         ms_CounterEndTime = Calendar.getInstance().getTime();
/*     */         
/* 528 */         Enumeration<String> keys = ms_URISent.keys();
/*     */         
/* 530 */         while (keys.hasMoreElements()) {
/*     */           
/* 532 */           JLbsChannelStatistic statistic = new JLbsChannelStatistic();
/* 533 */           String uri = keys.nextElement();
/* 534 */           int countSend = ((Integer)ms_URISentCounter.get(uri)).intValue();
/* 535 */           int dataSend = ((Integer)ms_URISent.get(uri)).intValue();
/* 536 */           long timeSend = ((Long)ms_URISentTime.get(uri)).longValue();
/* 537 */           Integer countReceive = ms_URIReceivedCounter.get(uri);
/* 538 */           Integer dataReceive = ms_URIReceived.get(uri);
/* 539 */           Long timeReceive = ms_URIReceivedTime.get(uri);
/* 540 */           statistic.setUri(uri);
/* 541 */           statistic.setHitCountSend(countSend);
/* 542 */           statistic.setHitCountReceived((countReceive == null) ? 
/* 543 */               0 : 
/* 544 */               countReceive.intValue());
/* 545 */           statistic.setDataSend(dataSend);
/* 546 */           statistic.setDataReceived((dataReceive == null) ? 
/* 547 */               0 : 
/* 548 */               dataReceive.intValue());
/* 549 */           statistic.setTimeReceived((timeReceive == null) ? 
/* 550 */               0L : 
/* 551 */               timeReceive.longValue());
/* 552 */           statistic.setTimeSend(timeSend);
/* 553 */           statistics.getUriStatistics().add(statistic);
/*     */         } 
/* 555 */         keys = ms_URIReceived.keys();
/* 556 */         while (keys.hasMoreElements()) {
/*     */           
/* 558 */           String uri = keys.nextElement();
/* 559 */           if (ms_URISent.containsKey(uri))
/*     */             continue; 
/* 561 */           JLbsChannelStatistic statistic = new JLbsChannelStatistic();
/* 562 */           int countReceive = ((Integer)ms_URIReceivedCounter.get(uri)).intValue();
/* 563 */           int dataReceive = ((Integer)ms_URIReceived.get(uri)).intValue();
/* 564 */           long timeReceive = ((Long)ms_URIReceivedTime.get(uri)).longValue();
/* 565 */           Integer countSend = ms_URISentCounter.get(uri);
/* 566 */           Integer dataSend = ms_URISent.get(uri);
/* 567 */           Long timeSend = ms_URISentTime.get(uri);
/* 568 */           statistic.setUri(uri);
/* 569 */           statistic.setHitCountSend((countSend == null) ? 
/* 570 */               0 : 
/* 571 */               countSend.intValue());
/* 572 */           statistic.setHitCountReceived(countReceive);
/* 573 */           statistic.setDataSend((dataSend == null) ? 
/* 574 */               0 : 
/* 575 */               dataSend.intValue());
/* 576 */           statistic.setDataReceived(dataReceive);
/* 577 */           statistic.setTimeReceived(timeReceive);
/* 578 */           statistic.setTimeSend((timeSend == null) ? 
/* 579 */               0L : 
/* 580 */               timeSend.longValue());
/* 581 */           statistics.getUriStatistics().add(statistic);
/*     */         } 
/*     */         
/* 584 */         statistics.setTotalDataReceived(ms_TotalDataReceived);
/* 585 */         statistics.setTotalDataSend(ms_TotalDataSent);
/* 586 */         statistics.setTotalRequestsReceived(ms_TotalResponses);
/* 587 */         statistics.setTotalRequestsSend(ms_TotalRequestsSent);
/* 588 */         statistics.setTotalTime(ms_CounterEndTime.getTime() - ms_CounterStartTime.getTime());
/*     */       }
/*     */     
/* 591 */     } catch (Exception e) {
/*     */       
/* 593 */       ms_Console.error(e);
/*     */     } 
/*     */     
/* 596 */     return statistics;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ChannelStatisticsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */