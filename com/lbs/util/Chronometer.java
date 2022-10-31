/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Chronometer
/*     */   implements Serializable
/*     */ {
/*  22 */   private static String CMARKER = "!#";
/*     */   
/*     */   private static Vector values;
/*     */ 
/*     */   
/*     */   public static void reset(boolean list) {
/*  28 */     if (values != null) {
/*     */       
/*  30 */       if (list) {
/*     */         
/*  32 */         record("Reset ");
/*  33 */         printDetail();
/*  34 */         printTime();
/*     */       } 
/*  36 */       values = null;
/*     */     } 
/*  38 */     values = new Vector();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reset() {
/*  43 */     reset(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void record(String s) {
/*  48 */     if (values == null) {
/*     */       return;
/*     */     }
/*  51 */     values.add(new ChronometerValue(s));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void print() {
/*  56 */     if (!DebugUtil.getDebug() || values == null) {
/*     */       return;
/*     */     }
/*  59 */     System.out.println("Chronometer log: ");
/*     */     
/*  61 */     Calendar time0 = ((ChronometerValue)values.get(0)).getTime();
/*  62 */     ChronometerValue cv = values.get(size() - 1);
/*  63 */     Calendar time = cv.getTime();
/*  64 */     long interval = time.getTimeInMillis() - time0.getTimeInMillis();
/*  65 */     System.out.println(String.valueOf(cv.getTitle()) + ": " + interval);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printDetail() {
/*  70 */     if (values == null) {
/*     */       return;
/*     */     }
/*  73 */     System.out.println("Chronometer detail log: ");
/*     */     
/*  75 */     Calendar time0 = ((ChronometerValue)values.get(0)).getTime();
/*  76 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  78 */       ChronometerValue cv = values.get(i);
/*  79 */       Calendar time = cv.getTime();
/*  80 */       long interval = time.getTimeInMillis() - time0.getTimeInMillis();
/*  81 */       System.out.println(String.valueOf(cv.getTitle()) + ": " + interval);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printTime() {
/*  87 */     DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS a,Z");
/*  88 */     String sTimeStamp = format.format(Calendar.getInstance().getTime());
/*  89 */     System.out.println(sTimeStamp);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int size() {
/*  94 */     if (values == null) {
/*  95 */       return 0;
/*     */     }
/*  97 */     return values.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String threadStriped(String title, String threadSpliter) {
/* 102 */     if (title != null) {
/*     */       
/* 104 */       int pos = title.indexOf(threadSpliter);
/* 105 */       if (pos != -1)
/* 106 */         title = title.substring(0, pos); 
/*     */     } 
/* 108 */     return title;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getThreadNr(String title, String threadSeperator) {
/* 113 */     int threadNr = -1;
/*     */     
/* 115 */     int pos = title.indexOf(threadSeperator);
/* 116 */     if (pos != -1) {
/*     */       
/* 118 */       String thread = title.substring(pos + threadSeperator.length(), title.length());
/*     */       
/*     */       try {
/* 121 */         threadNr = Integer.valueOf(thread).intValue();
/*     */       }
/* 123 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return threadNr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long getThreadCount(String threadSeperator) {
/* 133 */     Hashtable<Object, Object> h = new Hashtable<>();
/*     */     
/* 135 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 137 */       ChronometerValue cv = values.get(i);
/* 138 */       String title = cv.getTitle();
/* 139 */       if (title.indexOf(CMARKER) != 0) {
/*     */         
/* 141 */         int tNr = getThreadNr(cv.getTitle(), threadSeperator);
/*     */         
/* 143 */         if (tNr != -1)
/*     */         {
/* 145 */           h.put(Integer.valueOf(tNr), Integer.valueOf(tNr));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     return h.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private static long calculateTotal(String cTitle, String threadSeperator) {
/* 155 */     Hashtable<Object, Object> h = new Hashtable<>();
/*     */     
/* 157 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 159 */       ChronometerValue cv = values.get(i);
/* 160 */       String title = cv.getTitle();
/* 161 */       if (title.indexOf(CMARKER) != 0) {
/*     */         
/* 163 */         int tNr = getThreadNr(cv.getTitle(), threadSeperator);
/*     */         
/* 165 */         if (tNr != -1)
/*     */         {
/* 167 */           h.put(Integer.valueOf(tNr), Integer.valueOf(tNr));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     long total = 0L;
/*     */     
/* 174 */     Enumeration<Integer> e = h.elements();
/*     */     
/* 176 */     while (e.hasMoreElements()) {
/*     */       
/* 178 */       Integer tNr = e.nextElement();
/*     */       
/* 180 */       long startTimer = -1L;
/* 181 */       long stopTimer = -1L;
/* 182 */       int pCnt = 0;
/*     */       
/* 184 */       for (int j = 0; j < size(); j++) {
/*     */         
/* 186 */         ChronometerValue cv = values.get(j);
/* 187 */         String title = threadStriped(cv.getTitle(), threadSeperator);
/* 188 */         if (title.indexOf(CMARKER) == -1 && getThreadNr(cv.getTitle(), threadSeperator) == tNr.intValue() && 
/* 189 */           title.compareTo(cTitle) == 0) {
/*     */           
/* 191 */           pCnt++;
/*     */           
/* 193 */           cv.setTitle(String.valueOf(CMARKER) + cv.getTitle());
/* 194 */           if (pCnt == 2) {
/* 195 */             stopTimer = cv.getTime().getTimeInMillis();
/*     */           } else {
/* 197 */             startTimer = cv.getTime().getTimeInMillis();
/*     */           } 
/* 199 */           if (pCnt == 2) {
/*     */             
/* 201 */             pCnt = 0;
/*     */             
/* 203 */             total = total + stopTimer - startTimer;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 208 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String durationStr(long durationInMilisec) {
/* 213 */     BigDecimal b = new BigDecimal(durationInMilisec);
/* 214 */     b = b.divide(new BigDecimal(1000), 3, 0);
/* 215 */     return b.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void print(FileWriter f, String msg) {
/* 220 */     if (f != null) {
/*     */       
/*     */       try {
/*     */         
/* 224 */         f.write(msg);
/* 225 */         f.flush();
/*     */       }
/* 227 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 232 */     System.out.print(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void println(FileWriter f, String msg) {
/* 237 */     print(f, msg);
/* 238 */     print(f, "\n");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printDurations(String threadSeperator, Vector<ChronometerValue> repeatCounts, boolean summaryOnly, String fileName, ArrayList<String> additionalLines) {
/* 243 */     if (values == null) {
/*     */       return;
/*     */     }
/* 246 */     File f = null;
/* 247 */     FileWriter fw = null;
/*     */     
/* 249 */     if (fileName != null) {
/*     */       boolean ok;
/*     */       
/* 252 */       f = new File(fileName);
/* 253 */       if (!f.exists()) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 258 */           ok = f.createNewFile();
/*     */         }
/* 260 */         catch (Exception e) {
/*     */           
/* 262 */           ok = false;
/*     */         } 
/*     */       } else {
/*     */         
/* 266 */         ok = true;
/*     */       } 
/* 268 */       if (ok) {
/*     */         
/*     */         try {
/*     */           
/* 272 */           fw = new FileWriter(f);
/*     */         }
/* 274 */         catch (Exception e) {
/*     */           
/* 276 */           fw = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 282 */     long numThreads = getThreadCount(threadSeperator);
/*     */     
/* 284 */     if (!summaryOnly)
/* 285 */       println(fw, "Chronometer total durations : "); 
/* 286 */     long totalDuration = 0L; int i;
/* 287 */     for (i = 0; i < size(); i++) {
/*     */       
/* 289 */       ChronometerValue cv = values.get(i);
/* 290 */       if (cv.getTitle().indexOf(CMARKER) == -1) {
/*     */         
/* 292 */         String title = threadStriped(cv.getTitle(), threadSeperator);
/*     */         
/* 294 */         long intervals = calculateTotal(title, threadSeperator);
/*     */         
/* 296 */         totalDuration += intervals;
/*     */         
/* 298 */         if (!summaryOnly) {
/* 299 */           println(fw, String.valueOf(title) + ": " + durationStr(intervals));
/*     */         }
/* 301 */         long repeats = numThreads;
/*     */         
/* 303 */         long rCnt = 0L;
/*     */         
/* 305 */         if (repeatCounts != null) {
/* 306 */           for (int k = 0; k < repeatCounts.size(); k++) {
/*     */             
/* 308 */             ChronometerValue v = repeatCounts.elementAt(k);
/* 309 */             if (v.getTitle().compareTo(title) == 0) {
/*     */               
/* 311 */               rCnt = v.getRepeats();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/* 316 */         if (rCnt != 0L) {
/* 317 */           repeats *= rCnt;
/*     */         }
/* 319 */         if (repeats < 1L) {
/* 320 */           repeats = 1L;
/*     */         }
/* 322 */         if (!summaryOnly) {
/* 323 */           print(fw, ",  (" + repeats + " times, " + durationStr(intervals / repeats) + " each)");
/*     */         } else {
/*     */           
/* 326 */           print(fw, String.valueOf(durationStr(intervals / repeats)) + ", ");
/*     */         } 
/*     */         
/* 329 */         if (!summaryOnly)
/* 330 */           println(fw, ""); 
/*     */       } 
/*     */     } 
/* 333 */     if (summaryOnly) {
/* 334 */       println(fw, "");
/*     */     }
/* 336 */     if (!summaryOnly) {
/*     */       
/* 338 */       println(fw, "Total duration : " + durationStr(totalDuration));
/* 339 */       if (numThreads > 0L) {
/* 340 */         println(fw, "Average duration per thread : " + (1.0D * totalDuration / numThreads / 1000.0D));
/*     */       }
/*     */     } 
/* 343 */     if (additionalLines != null)
/*     */     {
/* 345 */       for (i = 0; i < additionalLines.size(); i++) {
/*     */         
/* 347 */         String s = additionalLines.get(i);
/* 348 */         println(fw, s);
/*     */       } 
/*     */     }
/*     */     
/* 352 */     if (fw != null) {
/*     */       
/*     */       try {
/* 355 */         fw.close();
/*     */       }
/* 357 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void printDurations(String threadSeperator, Vector repeatCounts, boolean summaryOnly, String fileName) {
/* 366 */     printDurations(threadSeperator, repeatCounts, summaryOnly, fileName, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printDurations(String threadSeperator, Vector repeatCounts, boolean summaryOnly) {
/* 371 */     printDurations(threadSeperator, repeatCounts, summaryOnly, "");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\Chronometer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */