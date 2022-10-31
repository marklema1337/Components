/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.util.Locale;
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
/*     */ public class LbsLevel
/*     */ {
/*  19 */   public static final LbsLevel OFF = new LbsLevel(LevelExt.OFF, LevelExt.OFF.intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   public static final LbsLevel FATAL = new LbsLevel(LevelExt.FATAL, LevelExt.FATAL.intValue());
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static final LbsLevel ERROR = new LbsLevel(LevelExt.ERROR, LevelExt.ERROR.intValue());
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static final LbsLevel CLOUDINFO = new LbsLevel(LevelExt.CLOUDINFO, LevelExt.CLOUDINFO.intValue());
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final LbsLevel WARN = new LbsLevel(LevelExt.WARN, LevelExt.WARN.intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final LbsLevel INFO = new LbsLevel(LevelExt.INFO, LevelExt.INFO.intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final LbsLevel DEBUG = new LbsLevel(LevelExt.DEBUG, LevelExt.DEBUG.intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final LbsLevel TRACE = new LbsLevel(LevelExt.TRACE, LevelExt.TRACE.intValue());
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static final LbsLevel ALL = new LbsLevel(LevelExt.ALL, LevelExt.ALL.intValue());
/*     */   
/*     */   private Object m_LevelObject;
/*     */   
/*     */   private int m_LevelInt;
/*     */   
/*     */   LbsLevel(Object level, int levelInt) {
/*  62 */     setLevel(level, levelInt);
/*     */   }
/*     */ 
/*     */   
/*     */   Object getLevel() {
/*  67 */     return this.m_LevelObject;
/*     */   }
/*     */ 
/*     */   
/*     */   void setLevel(Object levelObject, int levelInt) {
/*  72 */     this.m_LevelObject = levelObject;
/*  73 */     this.m_LevelInt = levelInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  78 */     return this.m_LevelObject.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevelInt() {
/*  83 */     return this.m_LevelInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     return getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     int prime = 31;
/*  94 */     int result = 1;
/*  95 */     result = 31 * result + this.m_LevelInt;
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (this == obj)
/* 102 */       return true; 
/* 103 */     if (obj == null)
/* 104 */       return false; 
/* 105 */     if (getClass() != obj.getClass())
/* 106 */       return false; 
/* 107 */     LbsLevel other = (LbsLevel)obj;
/* 108 */     if (this.m_LevelInt != other.m_LevelInt)
/* 109 */       return false; 
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LbsLevel getLevelByName(String name) {
/* 115 */     if (name == null)
/* 116 */       return null; 
/* 117 */     name = name.toUpperCase(Locale.US);
/*     */     
/* 119 */     if (name.equals(OFF.getName()))
/* 120 */       return OFF; 
/* 121 */     if (name.equals(FATAL.getName()))
/* 122 */       return FATAL; 
/* 123 */     if (name.equals(ERROR.getName()))
/* 124 */       return ERROR; 
/* 125 */     if (name.equals(CLOUDINFO.getName()))
/* 126 */       return CLOUDINFO; 
/* 127 */     if (name.equals(WARN.getName()))
/* 128 */       return WARN; 
/* 129 */     if (name.equals(INFO.getName()))
/* 130 */       return INFO; 
/* 131 */     if (name.equals(DEBUG.getName()))
/* 132 */       return DEBUG; 
/* 133 */     if (name.equals(TRACE.getName()))
/* 134 */       return TRACE; 
/* 135 */     if (name.equals(ALL.getName())) {
/* 136 */       return ALL;
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */