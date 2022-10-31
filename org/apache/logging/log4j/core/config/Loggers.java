/*    */ package org.apache.logging.log4j.core.config;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Loggers
/*    */ {
/*    */   private final ConcurrentMap<String, LoggerConfig> map;
/*    */   private final LoggerConfig root;
/*    */   
/*    */   public Loggers(ConcurrentMap<String, LoggerConfig> map, LoggerConfig root) {
/* 29 */     this.map = map;
/* 30 */     this.root = root;
/*    */   }
/*    */   
/*    */   public ConcurrentMap<String, LoggerConfig> getMap() {
/* 34 */     return this.map;
/*    */   }
/*    */   
/*    */   public LoggerConfig getRoot() {
/* 38 */     return this.root;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\config\Loggers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */