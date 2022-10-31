/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public class LbsConsoleTester
/*     */ {
/*     */   public static void main(String[] args) {
/*  26 */     multithreadedTest();
/*     */   }
/*     */ 
/*     */   
/*     */   static void multithreadedTest() {
/*  31 */     for (int k = 0; k < 100; k++) {
/*     */       
/*  33 */       LbsConsole.getRootLogger().setLevel2(LbsLevel.ALL);
/*     */       
/*  35 */       LbsLayout layout = LbsLayoutFactory.createLayout(LbsLayoutFactory.LogFormat.PLAIN(false));
/*  36 */       ILbsAppender appender = LbsAppenderFactory.createConsoleAppender("myAppender", layout, false);
/*  37 */       LbsConsole.getRootLogger().addAppender(appender);
/*     */       
/*  39 */       final LbsConsole logger = LbsConsole.getLogger("multithreadedTester");
/*  40 */       Thread[] threads = new Thread[100]; int i;
/*  41 */       for (i = 0; i < threads.length; i++) {
/*     */         
/*  43 */         final int currentIndex = i;
/*  44 */         threads[i] = new Thread(new Runnable()
/*     */             {
/*     */               public void run()
/*     */               {
/*  48 */                 if (currentIndex % 5 == 0) {
/*     */                   
/*  50 */                   System.out.println(LbsConsoleTester.getTestMessage());
/*     */                 } else {
/*     */                   
/*  53 */                   logger.fatal(LbsConsoleTester.getTestMessage());
/*     */                 }  }
/*     */             });
/*  56 */         threads[i].setName("TestThread-" + i);
/*     */       } 
/*  58 */       for (i = 0; i < threads.length; i++)
/*  59 */         threads[i].start(); 
/*  60 */       for (i = 0; i < threads.length; i++) {
/*     */ 
/*     */         
/*     */         try {
/*  64 */           threads[i].join();
/*     */         }
/*  66 */         catch (InterruptedException e) {
/*     */           
/*  68 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*  72 */     System.out.println("finished");
/*     */   }
/*     */ 
/*     */   
/*     */   static void clientLoggerTest() {
/*  77 */     LbsConsole.getRootLogger().setLevel2(LbsLevel.ALL);
/*  78 */     LbsConsole.getRootLogger().warn("test warn");
/*  79 */     LbsConsole.getLogger(LbsConsole.class).warn("test warn");
/*  80 */     LbsConsole.getRootLogger().debug("test debug");
/*  81 */     LbsConsole.getLogger(LbsConsole.class).debug("test debug");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void memoryTest() {
/* 109 */     int numberOfObjects = 300000;
/* 110 */     for (int i = 0; i < 10; i++)
/*     */     {
/* 112 */       System.gc();
/*     */     }
/* 114 */     long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); int j;
/* 115 */     for (j = 0; j < 300000; j++)
/*     */     {
/* 117 */       LbsConsole.getLogger("Logger" + j);
/*     */     }
/* 119 */     for (j = 0; j < 10; j++)
/*     */     {
/* 121 */       System.gc();
/*     */     }
/* 123 */     long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/* 124 */     System.out.println("Memory used: " + (endMemory - startMemory) + "bytes");
/* 125 */     System.out.println("Memory per object: " + ((endMemory - startMemory) / 300000L) + "bytes");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void test1() {
/* 132 */     LbsConsole.getRootLogger().fatal("rootlogger fatal test");
/* 133 */     LbsConsole.getRootLogger().error("rootlogger error test");
/* 134 */     LbsConsole.getRootLogger().warn("rootlogger warn test");
/* 135 */     LbsConsole.getRootLogger().info("rootlogger info test");
/* 136 */     LbsConsole.getRootLogger().debug("rootlogger debug test");
/* 137 */     LbsConsole.getRootLogger().trace("rootlogger trace test");
/* 138 */     LbsConsole.getRootLogger().fatal("rootlogger fatal test", new Throwable());
/* 139 */     LbsConsole.getRootLogger().error("rootlogger error test", new Throwable());
/* 140 */     LbsConsole.getRootLogger().warn("rootlogger warn test", new Throwable());
/* 141 */     LbsConsole.getRootLogger().info("rootlogger info test", new Throwable());
/* 142 */     LbsConsole.getRootLogger().debug("rootlogger debug test", new Throwable());
/* 143 */     LbsConsole.getRootLogger().trace("rootlogger trace test", new Throwable());
/*     */     
/* 145 */     LbsConsole.getLogger(LbsConsole.class).error(null, new Throwable());
/*     */     
/* 147 */     LbsConsole.getLogger(LbsConsole.class.getName()).debug("logger debug test");
/*     */     
/* 149 */     LbsConsole.getLogger(LbsConsole.class.getName()).log(LbsLevel.FATAL, "log method test message");
/* 150 */     LbsConsole.getLogger(LbsConsole.class.getName()).log(LbsLevel.FATAL, "log method /w exception test message", 
/* 151 */         new Throwable());
/*     */   }
/*     */ 
/*     */   
/*     */   static void test2() {
/* 156 */     Logger logger = Logger.getLogger("com.wombat.nose");
/* 157 */     LbsConsole.ConsoleHandlerExt handler = new LbsConsole.ConsoleHandlerExt();
/*     */     
/* 159 */     logger.addHandler(handler);
/* 160 */     logger.setUseParentHandlers(false);
/*     */     
/* 162 */     logger.setLevel(Level.ALL);
/*     */     
/* 164 */     logger.info("doing stuff");
/*     */     
/*     */     try {
/* 167 */       System.out.println("hebele");
/*     */     }
/* 169 */     catch (Error ex) {
/*     */       
/* 171 */       logger.log(Level.WARNING, "trouble sneezing", ex);
/*     */     } 
/* 173 */     logger.fine("done");
/*     */   }
/*     */ 
/*     */   
/*     */   static String getTestMessage() {
/* 178 */     StringBuilder buf = new StringBuilder(10000);
/* 179 */     while (buf.length() < 10000)
/* 180 */       buf.append((int)(Math.random() * 1000.0D)); 
/* 181 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsConsoleTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */