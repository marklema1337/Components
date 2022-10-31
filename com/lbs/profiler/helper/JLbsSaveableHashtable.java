/*     */ package com.lbs.profiler.helper;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsSaveableHashtable
/*     */   extends Hashtable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   private String m_FileName = null;
/*  23 */   private String m_Path = null;
/*     */ 
/*     */   
/*     */   public JLbsSaveableHashtable(String path, String fileName) {
/*  27 */     this.m_Path = path;
/*  28 */     this.m_FileName = fileName;
/*  29 */     load();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clear() {
/*  34 */     super.clear();
/*  35 */     save();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized Object put(Object key, Object value) {
/*  40 */     Object previousValue = super.put(key, value);
/*  41 */     save();
/*  42 */     return previousValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized Object remove(Object key) {
/*  47 */     Object value = super.remove(key);
/*  48 */     save();
/*  49 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void putAll(Map<?, ?> t) {
/*  54 */     super.putAll(t);
/*  55 */     save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save() {
/*  60 */     if (this.m_Path == null || this.m_FileName == null) {
/*  61 */       return false;
/*     */     }
/*  63 */     FileOutputStream stream = null;
/*     */     
/*     */     try {
/*  66 */       byte[] data = JLbsProfileUtil.serializeObjectPlain(this);
/*  67 */       File path = new File(this.m_Path);
/*  68 */       File file = new File(String.valueOf(this.m_Path) + this.m_FileName);
/*  69 */       boolean pathExists = JLbsProfileUtil.ensurePathExists(path);
/*  70 */       if (!pathExists)
/*  71 */         return false; 
/*  72 */       stream = new FileOutputStream(file);
/*  73 */       stream.write(data, 0, data.length);
/*  74 */       return true;
/*     */     }
/*  76 */     catch (IOException e) {
/*     */       
/*  78 */       this.m_Path = null;
/*  79 */       this.m_FileName = null;
/*  80 */       return false;
/*     */     }
/*     */     finally {
/*     */       
/*  84 */       if (stream != null) {
/*     */         
/*     */         try {
/*     */           
/*  88 */           stream.close();
/*     */         }
/*  90 */         catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean load() {
/*     */     try {
/* 101 */       if (this.m_Path == null || this.m_FileName == null) {
/* 102 */         return false;
/*     */       }
/* 104 */       byte[] data = JLbsProfileUtil.readFile(String.valueOf(this.m_Path) + this.m_FileName);
/*     */       
/* 106 */       JLbsSaveableHashtable items = (JLbsSaveableHashtable)JLbsProfileUtil.deserializeObjectPlain(data);
/*     */       
/* 108 */       super.clear();
/* 109 */       super.putAll(items);
/* 110 */       return true;
/*     */     }
/* 112 */     catch (FileNotFoundException e) {
/*     */       
/* 114 */       return false;
/*     */     }
/* 116 */     catch (Exception e) {
/*     */       
/* 118 */       clear();
/* 119 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsSaveableHashtable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */