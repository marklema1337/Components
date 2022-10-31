/*     */ package com.lbs.performance;
/*     */ 
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class LogEntry
/*     */ {
/*     */   public static final int LOG_TYPE_ENTRY = 0;
/*     */   public static final int LOG_TYPE_RETURN = 1;
/*     */   public static final int LOG_TYPE_INFO = 2;
/*     */   public int Type;
/*     */   public int Level;
/*     */   public long ThreadID;
/*     */   public String SessionID;
/*     */   public long TimeInMillis;
/*     */   public long Micros;
/*     */   public String ClassName;
/*     */   public String MethodName;
/*     */   public String Message;
/*     */   
/*     */   public LogEntry() {}
/*     */   
/*     */   public LogEntry(int type, String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/*  40 */     this.Type = type;
/*  41 */     this.Level = level;
/*  42 */     this.SessionID = sessionID;
/*  43 */     this.ClassName = sourceClass;
/*  44 */     this.MethodName = sourceMethod;
/*  45 */     this.Message = message;
/*  46 */     fill();
/*     */   }
/*     */ 
/*     */   
/*     */   public void fill() {
/*  51 */     this.ThreadID = (LogEntryFieldHolder.getInstance()).id.getThreadID();
/*  52 */     this.TimeInMillis = System.currentTimeMillis();
/*  53 */     this.Micros = JLbsPreciseTime.getMicroSeconds()[1];
/*     */   }
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
/*     */   public String toString() {
/*  75 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  77 */     switch (this.Type) {
/*     */       
/*     */       case 0:
/*  80 */         sb.append("ENTRY");
/*     */         break;
/*     */       case 1:
/*  83 */         sb.append("RETURN");
/*     */         break;
/*     */       case 2:
/*  86 */         sb.append("INFO");
/*     */         break;
/*     */     } 
/*     */     
/*  90 */     sb.append(" ");
/*  91 */     sb.append(this.Level);
/*  92 */     sb.append(" ");
/*  93 */     sb.append(this.TimeInMillis);
/*  94 */     sb.append(" ");
/*  95 */     sb.append(this.Micros);
/*  96 */     sb.append(" ");
/*  97 */     sb.append(this.ThreadID);
/*  98 */     sb.append(" ");
/*  99 */     sb.append(this.SessionID);
/* 100 */     sb.append(" ");
/* 101 */     sb.append(this.ClassName);
/* 102 */     sb.append(" ");
/* 103 */     sb.append(this.MethodName);
/* 104 */     sb.append(" ");
/*     */     
/* 106 */     if (this.Message != null) {
/* 107 */       sb.append(this.Message);
/*     */     }
/* 109 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String[] split(String str, int maxEntries) {
/* 114 */     StringTokenizer tok = new StringTokenizer(str, " ");
/*     */     
/* 116 */     int count = tok.countTokens();
/*     */     
/* 118 */     if (count == 0) {
/* 119 */       return null;
/*     */     }
/* 121 */     String[] res = new String[maxEntries];
/* 122 */     int i = 0;
/* 123 */     while (tok.hasMoreTokens()) {
/*     */       
/* 125 */       if (i <= maxEntries - 1) {
/* 126 */         res[i] = tok.nextToken();
/*     */       } else {
/* 128 */         res[maxEntries - 1] = res[maxEntries - 1] + " " + tok.nextToken();
/*     */       } 
/* 130 */       i++;
/*     */     } 
/*     */     
/* 133 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(String line) {
/* 138 */     String[] words = split(line, 9);
/*     */     
/* 140 */     if (words[0].equals("ENTRY")) {
/* 141 */       this.Type = 0;
/* 142 */     } else if (words[0].equals("RETURN")) {
/* 143 */       this.Type = 1;
/*     */     } else {
/* 145 */       this.Type = 2;
/*     */     } 
/* 147 */     this.Level = Integer.parseInt(words[1]);
/* 148 */     this.TimeInMillis = Long.parseLong(words[2]);
/* 149 */     this.Micros = Long.parseLong(words[3]);
/* 150 */     this.ThreadID = Long.parseLong(words[4]);
/* 151 */     this.SessionID = words[5];
/* 152 */     this.ClassName = words[6];
/* 153 */     this.MethodName = words[7];
/*     */     
/* 155 */     if (words.length >= 9)
/* 156 */       this.Message = words[8]; 
/*     */   }
/*     */   
/*     */   public static class LogEntryFieldHolder
/*     */   {
/* 161 */     private ThreadID id = new ThreadID();
/*     */ 
/*     */     
/*     */     private static LogEntryFieldHolder getInstance() {
/* 165 */       return (LogEntryFieldHolder)LbsClassInstanceProvider.getInstanceByClass(LogEntryFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\LogEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */