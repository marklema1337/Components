/*     */ package com.lbs.localization.db.synchronizer;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Timestamp;
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
/*     */ public class JLbsLocalizationDBCopier
/*     */   extends JLbsLocalizationDBSynchronizer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsLocalizationDBCopier(boolean userInterface) {
/*  31 */     super(userInterface);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyLocalizationDB(String sourceFilePath, String destFilePath) {
/*     */     try {
/*  38 */       Connection sourceConnection = getEmbeddedConnectionFromZip(sourceFilePath, JLbsFileUtil.getFileName(sourceFilePath, 
/*  39 */             true));
/*  40 */       Connection destConnection = getEmbeddedConnectionFromZip(destFilePath, JLbsFileUtil.getFileName(destFilePath, true));
/*     */       
/*  42 */       if (sourceConnection != null && destConnection != null)
/*     */       {
/*  44 */         Timestamp sourceTime = getLastSynchronizationTime(sourceConnection);
/*  45 */         Timestamp destTime = getLastSynchronizationTime(destConnection);
/*     */         
/*  47 */         if (sourceTime != null && destTime != null)
/*     */         {
/*  49 */           if (sourceTime.compareTo(destTime) > 0) {
/*     */ 
/*     */             
/*  52 */             System.out.println("The source file is more recently synchronized [" + sourceTime + 
/*  53 */                 "] than the destination one [" + destTime + "]. Copying localization DB..");
/*  54 */             JLbsFileUtil.copyFile(sourceFilePath, destFilePath);
/*     */           }
/*  56 */           else if (sourceTime.compareTo(destTime) < 0) {
/*     */             
/*  58 */             System.out.println("The destination file is more recently synchronized [" + destTime + 
/*  59 */                 "] than the source one [" + sourceTime + "]. No need to copy..");
/*     */           } else {
/*     */             
/*  62 */             System.out.println("The source and destination files are synchronized at the same time [" + sourceTime + 
/*  63 */                 "]. No need to copy..");
/*     */           } 
/*  65 */           System.out.println("Source file : " + sourceFilePath);
/*  66 */           System.out.println("Destination file : " + destFilePath);
/*     */         }
/*     */       
/*     */       }
/*     */     
/*  71 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  80 */     if (args.length < 4) {
/*     */       
/*  82 */       System.err.println("The parameter set is not campatible! Please supply 5 arguments.");
/*  83 */       System.err.println("Argument 1 : Force DB copy?");
/*  84 */       System.err.println("Argument 2 : Source folder");
/*  85 */       System.err.println("Argument 3 : Destination folder");
/*  86 */       System.err.println("Argument 4 : Language prefix");
/*  87 */       System.err.println("Argument 5 : Use System.Exit");
/*     */     }
/*     */     else {
/*     */       
/*  91 */       boolean force = Boolean.valueOf(args[0]).booleanValue();
/*  92 */       String sourceFolderPath = args[1];
/*  93 */       String destFolderPath = args[2];
/*  94 */       String langPrefix = args[3];
/*  95 */       boolean useExit = (args.length > 4) ? Boolean.valueOf(args[4]).booleanValue() : true;
/*  96 */       String fileName = String.valueOf(langPrefix) + ".zip";
/*     */       
/*  98 */       String sourceFilePath = JLbsFileUtil.appendPath(sourceFolderPath, fileName);
/*  99 */       File sourceFile = new File(sourceFilePath);
/* 100 */       if (!sourceFile.exists()) {
/*     */         
/* 102 */         System.err.println("The source file does not exist : " + sourceFilePath);
/* 103 */         if (useExit) {
/* 104 */           System.exit(0);
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       } 
/* 109 */       File destFolder = new File(destFolderPath);
/* 110 */       if (!destFolder.exists()) {
/*     */         
/* 112 */         System.err.println("Destination folder does not exist : " + destFolderPath);
/* 113 */         if (useExit) {
/* 114 */           System.exit(0);
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       } 
/* 119 */       String destFilePath = JLbsFileUtil.appendPath(destFolderPath, fileName);
/* 120 */       File destFile = new File(destFilePath);
/*     */ 
/*     */       
/* 123 */       if (force || !destFile.exists()) {
/*     */         
/* 125 */         System.out.println("Copy operation forced or destination file does not exist! Copying localization DB..");
/* 126 */         System.out.println("Source file : " + sourceFilePath);
/* 127 */         System.out.println("Destination file : " + destFilePath);
/*     */ 
/*     */         
/*     */         try {
/* 131 */           JLbsFileUtil.copyFile(sourceFilePath, destFilePath);
/* 132 */           if (useExit) {
/* 133 */             System.exit(0);
/*     */           } else {
/*     */             return;
/*     */           } 
/* 137 */         } catch (IOException e) {
/*     */           
/* 139 */           e.printStackTrace();
/* 140 */           if (useExit) {
/* 141 */             System.exit(0);
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       JLbsLocalizationDBCopier copier = new JLbsLocalizationDBCopier(false);
/* 149 */       copier.copyLocalizationDB(sourceFilePath, destFilePath);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\JLbsLocalizationDBCopier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */