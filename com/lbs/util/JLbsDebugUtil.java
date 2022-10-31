/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ public class JLbsDebugUtil
/*     */ {
/*  13 */   private static final Map<String, JLbsDebugUtilEntry> namedEntities = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*     */   public static void resetAllCounters() {
/*  17 */     namedEntities.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean testTrue(Object... parameters) {
/*  22 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean testFalse(Object... parameters) {
/*  27 */     String result = printStackTrace(3, 10);
/*  28 */     System.err.println();
/*  29 */     System.err.println(result);
/*  30 */     System.err.println();
/*  31 */     System.err.println();
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String printStackTrace(int start, int level) {
/*  37 */     StackTraceElement[] st = Thread.currentThread().getStackTrace();
/*  38 */     List<StackTraceElement> list = new ArrayList<>();
/*     */     
/*  40 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  42 */     level += start;
/*  43 */     for (int i = start; i < st.length; i++) {
/*     */       
/*  45 */       if (i >= level) {
/*     */         break;
/*     */       }
/*     */       
/*  49 */       StackTraceElement stackTraceElement = st[i];
/*  50 */       list.add(stackTraceElement);
/*     */     } 
/*     */     
/*  53 */     for (StackTraceElement stackTraceElement : list)
/*     */     {
/*  55 */       sb.append(String.valueOf(stackTraceElement.getClassName()) + "." + stackTraceElement.getMethodName() + "(" + 
/*  56 */           stackTraceElement.getLineNumber() + ")" + "\n");
/*     */     }
/*  58 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static class JLbsDebugUtilEntry
/*     */   {
/*  63 */     private Map<String, Integer> stackTraceCounters = new ConcurrentHashMap<>();
/*     */     private boolean groupByStackTrace;
/*     */     private int stackTraceLevel;
/*     */     private volatile int totalIteration;
/*     */     private volatile int currentIterationTotal;
/*     */     private volatile int currentIteration;
/*  69 */     private volatile int intermediateLogPeriod = 1000;
/*     */     
/*     */     private String name;
/*     */     private boolean finished;
/*     */     private int countFilter;
/*     */     private boolean repeat;
/*     */     
/*     */     private JLbsDebugUtilEntry(String name) {
/*  77 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry groupByStackTrace(boolean groupByStackTrace) {
/*  82 */       this.groupByStackTrace = groupByStackTrace;
/*  83 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry stackTraceLevel(int stackTraceLevel) {
/*  88 */       this.stackTraceLevel = stackTraceLevel;
/*  89 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry finishAfterIterations(int iteration) {
/*  94 */       this.totalIteration = iteration;
/*  95 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry countFilter(int countFilter) {
/* 100 */       this.countFilter = countFilter;
/* 101 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry intermediateLogPeriod(int intermediateLogPeriod) {
/* 106 */       this.intermediateLogPeriod = intermediateLogPeriod;
/* 107 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public JLbsDebugUtilEntry repeat(boolean repeat) {
/* 112 */       this.repeat = repeat;
/* 113 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     private String getKey(Object[] parameters) {
/* 118 */       String result = ""; byte b; int i; Object[] arrayOfObject;
/* 119 */       for (i = (arrayOfObject = parameters).length, b = 0; b < i; ) { Object key = arrayOfObject[b];
/*     */         
/* 121 */         result = String.valueOf(result) + " @@@ " + key;
/*     */         b++; }
/*     */       
/* 124 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean call() {
/* 129 */       return call(new Object[0], 5);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean call(Object[] parameters) {
/* 134 */       return call(parameters, 5);
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized boolean call(Object[] parameters, int start) {
/* 139 */       if (!this.repeat && this.finished)
/*     */       {
/* 141 */         return false;
/*     */       }
/*     */       
/* 144 */       this.currentIteration++;
/* 145 */       this.currentIterationTotal++;
/*     */       
/* 147 */       String stackTrace = JLbsDebugUtil.printStackTrace(start, this.stackTraceLevel);
/* 148 */       String parameterStr = getKey(parameters);
/* 149 */       String key = String.valueOf(stackTrace) + parameterStr;
/*     */       
/* 151 */       if (this.finished) {
/*     */         
/* 153 */         this.stackTraceCounters.remove(key);
/* 154 */         this.finished = false;
/* 155 */         this.currentIteration = 1;
/*     */       } 
/*     */       
/* 158 */       if (this.groupByStackTrace) {
/*     */         
/* 160 */         Integer counter = this.stackTraceCounters.get(key);
/* 161 */         if (counter == null)
/*     */         {
/* 163 */           counter = Integer.valueOf(0);
/*     */         }
/* 165 */         counter = Integer.valueOf(counter.intValue() + 1);
/* 166 */         this.stackTraceCounters.put(key, counter);
/*     */         
/* 168 */         if (this.currentIteration % this.intermediateLogPeriod == 0)
/*     */         {
/* 170 */           System.err.println("Total current iteration: " + this.currentIterationTotal + ", current iteration: " + 
/* 171 */               this.currentIteration + ", for debug entry: " + this.name + ". Time: " + (new Date()).toGMTString());
/*     */         }
/*     */         
/* 174 */         if (this.currentIteration >= this.totalIteration)
/*     */         {
/* 176 */           System.err.println();
/* 177 */           System.err.println();
/* 178 */           System.err.println(
/* 179 */               "Printing call statistics for debug entity: (" + this.name + "), after " + this.totalIteration + " iterations:");
/* 180 */           System.err.println();
/*     */           
/* 182 */           Set<Map.Entry<String, Integer>> entries = this.stackTraceCounters.entrySet();
/* 183 */           for (Map.Entry<String, Integer> entry : entries) {
/*     */             String stackTraceStr, parametersStr;
/* 185 */             if (((Integer)entry.getValue()).intValue() <= this.countFilter) {
/*     */               continue;
/*     */             }
/*     */ 
/*     */             
/* 190 */             int index = ((String)entry.getKey()).indexOf(" @@@ ");
/*     */ 
/*     */             
/* 193 */             if (index >= 0) {
/*     */               
/* 195 */               stackTraceStr = ((String)entry.getKey()).substring(0, index);
/* 196 */               parametersStr = ((String)entry.getKey()).substring(index + 1);
/*     */             }
/*     */             else {
/*     */               
/* 200 */               stackTraceStr = entry.getKey();
/* 201 */               parametersStr = "(No parameter)";
/*     */             } 
/*     */             
/* 204 */             System.err.println("Below stack trace called: " + entry.getValue() + " times");
/* 205 */             System.err.println();
/* 206 */             System.err.println("Parameters: " + parametersStr);
/* 207 */             System.err.println(stackTraceStr);
/* 208 */             System.err.println();
/*     */           } 
/*     */           
/* 211 */           System.err.println();
/* 212 */           System.err.println();
/* 213 */           System.err.println();
/*     */           
/* 215 */           JLbsDebugUtil.namedEntities.remove(key);
/* 216 */           this.finished = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 221 */         System.err.println("Debug entity: (" + key + "), iteration: " + this.currentIteration + " of " + this.totalIteration);
/* 222 */         System.err.println(parameterStr);
/* 223 */         System.err.println();
/* 224 */         System.err.println(stackTrace);
/* 225 */         System.err.println();
/* 226 */         System.err.println();
/*     */       } 
/*     */       
/* 229 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class JLbsDebugUtilBuilder
/*     */   {
/*     */     public static JLbsDebugUtil.JLbsDebugUtilEntry buildEntity(String name) {
/* 241 */       JLbsDebugUtil.JLbsDebugUtilEntry entity = (JLbsDebugUtil.JLbsDebugUtilEntry)JLbsDebugUtil.namedEntities.get(name);
/* 242 */       if (entity == null) {
/*     */         
/* 244 */         entity = new JLbsDebugUtil.JLbsDebugUtilEntry(name, null);
/* 245 */         JLbsDebugUtil.namedEntities.put(name, entity);
/*     */       } 
/*     */       
/* 248 */       return entity;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsDebugUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */