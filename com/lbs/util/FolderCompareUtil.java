/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JFileChooser;
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
/*    */ public class FolderCompareUtil
/*    */ {
/*    */   public static void compareFolders(File sourceFolder, File destFolder) {
/* 21 */     File[] sourceList = sourceFolder.listFiles();
/* 22 */     File[] destList = destFolder.listFiles();
/* 23 */     ArrayList<Integer> destIdxs = new ArrayList<>();
/* 24 */     if (sourceList != null)
/* 25 */       for (int i = 0; i < sourceList.length; i++) {
/*    */         
/* 27 */         File sourceItem = sourceList[i];
/* 28 */         boolean fnd = false;
/* 29 */         if (destList != null)
/* 30 */           for (int j = 0; j < destList.length; j++) {
/*    */             
/* 32 */             File destItem = destList[j];
/* 33 */             if (JLbsStringUtil.equals(sourceItem.getName(), destItem.getName())) {
/*    */               
/* 35 */               fnd = true;
/* 36 */               destIdxs.add(Integer.valueOf(j));
/* 37 */               if (sourceItem.isDirectory())
/* 38 */                 compareFolders(sourceItem, destItem); 
/*    */             } 
/*    */           }  
/* 41 */         if (!fnd)
/*    */         {
/* 43 */           System.out.println("Could not find source file '" + sourceItem.getAbsolutePath() + "' in destination folder!");
/*    */         }
/*    */       }  
/* 46 */     if (destList != null)
/*    */     {
/* 48 */       for (int i = 0; i < destList.length; i++) {
/*    */         
/* 50 */         if (!destIdxs.contains(Integer.valueOf(i)))
/*    */         {
/* 52 */           System.out.println("Could not find destination file '" + destList[i].getAbsolutePath() + "' in source folder!");
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 60 */     JFileChooser chooser = new JFileChooser();
/* 61 */     chooser.setFileSelectionMode(1);
/* 62 */     chooser.setDialogTitle("Select the source directory");
/* 63 */     int status = chooser.showOpenDialog(null);
/* 64 */     if (status == 0) {
/*    */       
/* 66 */       File sourceFolder = chooser.getSelectedFile();
/* 67 */       chooser.setDialogTitle("Select the destination directory");
/* 68 */       status = chooser.showOpenDialog(null);
/* 69 */       if (status == 0) {
/*    */         
/* 71 */         File destFolder = chooser.getSelectedFile();
/* 72 */         compareFolders(sourceFolder, destFolder);
/*    */       } 
/*    */     } 
/* 75 */     System.exit(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\FolderCompareUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */