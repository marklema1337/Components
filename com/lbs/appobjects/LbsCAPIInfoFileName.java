/*    */ package com.lbs.appobjects;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsCAPIInfoFileName
/*    */ {
/* 16 */   String fullFilePath = "";
/* 17 */   String resourcePath = "";
/*    */ 
/*    */   
/*    */   public LbsCAPIInfoFileName(String resPath, String subject, int owner1, int owner2, int owner3) {
/* 21 */     this.resourcePath = resPath;
/*    */     
/* 23 */     SetFullPath(resPath, subject, owner1, owner2, owner3);
/*    */   }
/*    */ 
/*    */   
/*    */   private String IntToStr(int number, int len) {
/* 28 */     DecimalFormat nf = new DecimalFormat();
/* 29 */     String str = new String(nf.format(number));
/*    */     
/* 31 */     while (str.length() < len) {
/* 32 */       str = "0" + str;
/*    */     }
/* 34 */     return str;
/*    */   }
/*    */ 
/*    */   
/*    */   private void SetFullPath(String resPath, String subject, int owner1, int owner2, int owner3) {
/* 39 */     this.fullFilePath = resPath;
/*    */     
/* 41 */     if (resPath.compareTo("") != 0) {
/*    */       
/* 43 */       String lastCh = resPath.substring(resPath.length() - 1, resPath.length());
/* 44 */       if (lastCh.compareTo(":") != 0 && lastCh.compareTo(File.separator) != 0 && lastCh.compareTo("/") != 0) {
/* 45 */         this.fullFilePath += File.separator;
/*    */       }
/*    */     } 
/* 48 */     if (owner1 != 0)
/* 49 */       subject = IntToStr(owner1, 5) + "_" + subject; 
/* 50 */     if (owner2 != 0)
/* 51 */       subject = IntToStr(owner2, 5) + "_" + subject; 
/* 52 */     if (owner3 != 0) {
/* 53 */       subject = IntToStr(owner3, 5) + "_" + subject;
/*    */     }
/* 55 */     this.fullFilePath += subject + ".dat";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFullFilePath() {
/* 63 */     return this.fullFilePath;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFullFilePath(String fullFilePath) {
/* 72 */     this.fullFilePath = fullFilePath;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getResourcePath() {
/* 80 */     return this.resourcePath;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResourcePath(String resourcePath) {
/* 89 */     this.resourcePath = resourcePath;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\LbsCAPIInfoFileName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */