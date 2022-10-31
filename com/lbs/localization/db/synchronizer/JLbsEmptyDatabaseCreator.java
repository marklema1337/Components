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
/*    */ public class JLbsEmptyDatabaseCreator
/*    */ {
/*    */   public static void main(String[] args) {
/* 18 */     String dbCreationDir = null, dbIdentifiers = null;
/* 19 */     if (args.length == 2) {
/*    */       
/* 21 */       dbCreationDir = args[0];
/* 22 */       dbIdentifiers = args[1];
/*    */     }
/*    */     else {
/*    */       
/* 26 */       dbCreationDir = "D:\\projects\\Unity_CVS_SET\\UnitySource\\Packages\\LocalizationDBs\\Empty";
/* 27 */       dbIdentifiers = "ARJO,DEDE,ENUS,FAIR,RURU,TRTR";
/*    */     } 
/*    */     
/*    */     try {
/* 31 */       String[] dbIds = dbIdentifiers.split(",");
/* 32 */       for (int i = 0; i < dbIds.length; i++)
/*    */       {
/* 34 */         JLbsLocalizationDBSynchronizer.createEmptyDatabase(dbCreationDir, dbIds[i].trim(), JLbsFileUtil.appendPath(
/* 35 */               dbCreationDir, String.valueOf(dbIds[i].trim()) + ".zip"));
/*    */       }
/*    */     }
/* 38 */     catch (Exception e) {
/*    */       
/* 40 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\JLbsEmptyDatabaseCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */