/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class LevelExt
/*    */   extends Level
/*    */ {
/* 16 */   private static ArrayList known = new ArrayList();
/*    */   
/* 18 */   public static final Level FATAL = new LevelExt("FATAL", 2000);
/* 19 */   public static final Level ERROR = new LevelExt("ERROR", Level.SEVERE.intValue());
/* 20 */   public static final Level CLOUDINFO = new LevelExt("CLOUDINFO", 950);
/* 21 */   public static final Level WARN = new LevelExt("WARN", Level.WARNING.intValue());
/* 22 */   public static final Level DEBUG = new LevelExt("DEBUG", Level.FINE.intValue());
/* 23 */   public static final Level TRACE = new LevelExt("TRACE", Level.FINEST.intValue());
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected LevelExt(String name, int value) {
/* 27 */     super(name, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Object readResolve() {
/* 36 */     synchronized (Level.class) {
/*    */       
/* 38 */       for (int i = 0; i < known.size(); i++) {
/*    */         
/* 40 */         Level other = known.get(i);
/* 41 */         if (getName().equals(other.getName()) && 
/* 42 */           intValue() == other.intValue() && (
/* 43 */           getResourceBundleName() == other.getResourceBundleName() || (getResourceBundleName() != null && 
/* 44 */           getResourceBundleName().equals(other.getResourceBundleName()))))
/*    */         {
/* 46 */           return other;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 51 */       known.add(this);
/* 52 */       return this;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LevelExt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */