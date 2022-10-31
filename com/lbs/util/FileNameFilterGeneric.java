/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
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
/*     */ 
/*     */ 
/*     */ class FileNameFilterGeneric
/*     */   implements FilenameFilter
/*     */ {
/*     */   String m_Filter;
/*     */   
/*     */   public FileNameFilterGeneric(String filter) {
/* 113 */     this.m_Filter = filter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean accept(File dir, String name) {
/* 118 */     if (this.m_Filter == null || this.m_Filter.compareTo("*") == 0)
/* 119 */       return true; 
/* 120 */     int iDotIndex = name.lastIndexOf(".");
/* 121 */     if (iDotIndex < 0)
/* 122 */       return false; 
/* 123 */     int iSlashIndex = name.lastIndexOf(File.separator);
/* 124 */     if (iSlashIndex > iDotIndex)
/* 125 */       return false; 
/* 126 */     return (name.substring(iDotIndex + 1, name.length()).compareToIgnoreCase(this.m_Filter) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptStartingWith(String fileName, int preWildCardLength, String searchStr, String extension) {
/*     */     try {
/* 137 */       if (!fileName.endsWith("." + extension)) {
/* 138 */         return false;
/*     */       }
/* 140 */       if (preWildCardLength > fileName.length()) {
/* 141 */         return false;
/*     */       }
/* 143 */       String subFileName = fileName.substring(preWildCardLength);
/*     */       
/* 145 */       if (subFileName.startsWith(searchStr)) {
/* 146 */         return true;
/*     */       }
/* 148 */       return false;
/*     */     }
/* 150 */     catch (Exception e) {
/*     */       
/* 152 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\FileNameFilterGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */