/*     */ package com.lbs.localization.db.synchronizer;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
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
/*     */ public class LuceneLocalizationDBCopier
/*     */   extends JLbsLocalizationDBSynchronizer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public LuceneLocalizationDBCopier(boolean userInterface) {
/*  33 */     super(userInterface);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyLocalizationDB(String sourceFilePath, String destFilePath) {
/*     */     try {
/*  40 */       ZipFile sourceZip = new ZipFile(sourceFilePath);
/*  41 */       ZipFile destZip = new ZipFile(destFilePath);
/*     */       
/*  43 */       if (sourceZip != null && destZip != null) {
/*     */         
/*  45 */         Timestamp sourceTime = getLastSynchronizationTime(sourceZip);
/*  46 */         Timestamp destTime = getLastSynchronizationTime(destZip);
/*     */         
/*  48 */         if (sourceTime != null && destTime != null)
/*     */         {
/*  50 */           if (sourceTime.compareTo(destTime) > 0) {
/*     */ 
/*     */             
/*  53 */             System.out.println("The source file is more recently synchronized [" + sourceTime + 
/*  54 */                 "] than the destination one [" + destTime + "]. Copying localization DB..");
/*  55 */             JLbsFileUtil.copyFile(sourceFilePath, destFilePath);
/*     */           }
/*  57 */           else if (sourceTime.compareTo(destTime) < 0) {
/*     */             
/*  59 */             System.out.println("The destination file is more recently synchronized [" + destTime + 
/*  60 */                 "] than the source one [" + sourceTime + "]. No need to copy..");
/*     */           } else {
/*     */             
/*  63 */             System.out.println("The source and destination files are synchronized at the same time [" + sourceTime + 
/*  64 */                 "]. No need to copy..");
/*     */           } 
/*  66 */           System.out.println("Source file : " + sourceFilePath);
/*  67 */           System.out.println("Destination file : " + destFilePath);
/*     */         }
/*     */       
/*     */       } 
/*  71 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Timestamp getLastSynchronizationTime(ZipFile zip) throws IOException {
/*  78 */     ZipEntry ze = zip.getEntry("synctime.txt");
/*  79 */     InputStream is = zip.getInputStream(ze);
/*  80 */     byte[] buffer = new byte[64];
/*  81 */     int read = 0;
/*  82 */     StringBuilder sb = new StringBuilder();
/*  83 */     while ((read = is.read(buffer)) > 0) {
/*  84 */       sb.append(new String(buffer, 0, read));
/*     */     }
/*  86 */     return new Timestamp(Long.valueOf(sb.toString()).longValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/*  92 */     if (args.length < 4) {
/*     */       
/*  94 */       System.err.println("The parameter set is not campatible! Please supply 5 arguments.");
/*  95 */       System.err.println("Argument 1 : Force DB copy?");
/*  96 */       System.err.println("Argument 2 : Source folder");
/*  97 */       System.err.println("Argument 3 : Destination folder");
/*  98 */       System.err.println("Argument 4 : Language prefix");
/*  99 */       System.err.println("Argument 5 : Use System.Exit");
/*     */     }
/*     */     else {
/*     */       
/* 103 */       boolean force = Boolean.valueOf(args[0]).booleanValue();
/* 104 */       String sourceFolderPath = args[1];
/* 105 */       String destFolderPath = args[2];
/* 106 */       String langPrefix = args[3];
/* 107 */       boolean useExit = (args.length > 4) ? Boolean.valueOf(args[4]).booleanValue() : true;
/* 108 */       String fileName = String.valueOf(langPrefix) + ".zip";
/*     */       
/* 110 */       String sourceFilePath = JLbsFileUtil.appendPath(sourceFolderPath, fileName);
/* 111 */       File sourceFile = new File(sourceFilePath);
/* 112 */       if (!sourceFile.exists()) {
/*     */         
/* 114 */         sourceFilePath = JLbsFileUtil.appendPath(String.valueOf(sourceFolderPath) + "/Empty", fileName);
/* 115 */         sourceFile = new File(sourceFilePath);
/* 116 */         if (!sourceFile.exists()) {
/* 117 */           System.err.println("The source file does not exist : " + sourceFilePath);
/* 118 */           if (useExit) {
/* 119 */             System.exit(0);
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 125 */       File destFolder = new File(destFolderPath);
/* 126 */       if (!destFolder.exists()) {
/*     */         
/* 128 */         System.err.println("Destination folder does not exist : " + destFolderPath);
/* 129 */         if (useExit) {
/* 130 */           System.exit(0);
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       } 
/* 135 */       String destFilePath = JLbsFileUtil.appendPath(destFolderPath, fileName);
/* 136 */       File destFile = new File(destFilePath);
/*     */ 
/*     */       
/* 139 */       if (force || !destFile.exists()) {
/*     */         
/* 141 */         if (force && destFile.exists())
/* 142 */           destFile.delete(); 
/* 143 */         System.out.println("Copy operation forced or destination file does not exist! Copying localization DB..");
/* 144 */         System.out.println("Source file : " + sourceFilePath);
/* 145 */         System.out.println("Destination file : " + destFilePath);
/*     */         
/*     */         try {
/* 148 */           JLbsFileUtil.copyFile(sourceFilePath, destFilePath);
/* 149 */           if (useExit) {
/* 150 */             System.exit(0);
/*     */           } else {
/*     */             return;
/*     */           } 
/* 154 */         } catch (IOException e) {
/*     */           
/* 156 */           e.printStackTrace();
/* 157 */           if (useExit) {
/* 158 */             System.exit(0);
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 165 */       LuceneLocalizationDBCopier copier = new LuceneLocalizationDBCopier(false);
/* 166 */       copier.copyLocalizationDB(sourceFilePath, destFilePath);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\LuceneLocalizationDBCopier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */