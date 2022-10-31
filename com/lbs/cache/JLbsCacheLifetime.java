/*     */ package com.lbs.cache;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class JLbsCacheLifetime
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int _FOREVER = 0;
/*     */   private static final int _SESSION = 1;
/*     */   private static final int _VERSION = 2;
/*     */   private static final int _TIMED = 3;
/*     */   private int m_Lifetime;
/*     */   private int m_Timeout;
/*     */   
/*     */   private JLbsCacheLifetime(int lifetime) {
/*  28 */     this.m_Lifetime = lifetime;
/*  29 */     this.m_Timeout = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCacheLifetime forever() {
/*  37 */     return new JLbsCacheLifetime(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCacheLifetime session() {
/*  45 */     return new JLbsCacheLifetime(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCacheLifetime version() {
/*  53 */     return new JLbsCacheLifetime(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCacheLifetime timed(int timeout) {
/*  62 */     JLbsCacheLifetime lbsCacheLifetime = new JLbsCacheLifetime(3);
/*  63 */     lbsCacheLifetime.m_Timeout = timeout;
/*  64 */     return lbsCacheLifetime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  72 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  77 */     if (obj instanceof JLbsCacheLifetime) {
/*     */       
/*  79 */       boolean equals = (((JLbsCacheLifetime)obj).m_Lifetime == this.m_Lifetime);
/*  80 */       if (equals && this.m_Lifetime == 3)
/*  81 */         return (((JLbsCacheLifetime)obj).m_Timeout == this.m_Timeout); 
/*  82 */       return equals;
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeout() {
/*  89 */     return this.m_Timeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForever() {
/*  94 */     return (this.m_Lifetime == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSession() {
/*  99 */     return (this.m_Lifetime == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVersion() {
/* 104 */     return (this.m_Lifetime == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTimed() {
/* 109 */     return (this.m_Lifetime == 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     switch (this.m_Lifetime) {
/*     */       
/*     */       case 0:
/* 117 */         return "FOREVER";
/*     */       case 1:
/* 119 */         return "SESSION";
/*     */       case 2:
/* 121 */         return "VERSION";
/*     */       case 3:
/* 123 */         return "TIMED (" + this.m_Timeout + " msecs)";
/*     */     } 
/* 125 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCacheLifetime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */