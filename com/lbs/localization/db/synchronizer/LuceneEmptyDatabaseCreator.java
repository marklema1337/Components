/*    */ package com.lbs.localization.db.synchronizer;
/*    */ 
/*    */ import com.lbs.util.JLbsFileUtil;
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
/*    */ @Deprecated
/*    */ public class LuceneEmptyDatabaseCreator
/*    */ {
/*    */   public static void main(String[] args) {
/* 19 */     String dbCreationDir = null, dbIdentifiers = null;
/* 20 */     if (args.length == 2) {
/*    */       
/* 22 */       dbCreationDir = args[0];
/* 23 */       dbIdentifiers = args[1];
/*    */     }
/*    */     else {
/*    */       
/* 27 */       dbCreationDir = "D:/Projects/jGuar_GIT_Set/jaf/Packages/LocalizationDBs/Empty";
/* 28 */       dbIdentifiers = "AREG,ARJO,ARSA,AZAZ,BGBG,DEDE,ENUS,FAIR,KAGE,RURU,SQAL,THTH,TKTM,TRTR,VIVN,ZHCN";
/*    */     } 
/*    */ 
/*    */     
/*    */     try {
/* 33 */       String[] dbIds = dbIdentifiers.split(",");
/* 34 */       for (int i = 0; i < dbIds.length; i++)
/*    */       {
/* 36 */         LuceneLocalizationDBSynchronizer.createEmptyDatabase(dbCreationDir, dbIds[i].trim(), JLbsFileUtil.appendPath(dbCreationDir, String.valueOf(dbIds[i].trim()) + ".zip"));
/*    */       }
/*    */     }
/* 39 */     catch (Exception e) {
/*    */       
/* 41 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\LuceneEmptyDatabaseCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */