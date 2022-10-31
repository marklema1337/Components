/*    */ package com.lbs.data.database;
/*    */ 
/*    */ import com.lbs.globalization.JLbsCultureInfoBase;
/*    */ import com.lbs.util.JLbsStringUtil;
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
/*    */ public class TableUpgradeProcessListener
/*    */   implements ITableUpgradeProcessListener
/*    */ {
/* 18 */   public static int ms_SystemTableCount = 0;
/* 19 */   public static int ms_FirmTableCount = 0;
/* 20 */   public static int ms_UpgradedTableCount = 0;
/* 21 */   public static StringBuilder ms_UpgradingTables = new StringBuilder();
/* 22 */   public static StringBuilder ms_UpgradedTables = new StringBuilder();
/* 23 */   public static StringBuilder ms_Errors = new StringBuilder();
/*    */   public static boolean isFinished = true;
/* 25 */   public static int ms_SystemorFirm = 0;
/*    */   
/* 27 */   private static String[] ms_TotalTableStr = new String[] { "Total Table Count : ", "Toplam Tablo Sayısı : ", "Gesamt Tabelle Zählen : " };
/*    */ 
/*    */   
/* 30 */   private static String[] ms_RemainingTableStr = new String[] { " Remaining Table Count : ", " Kalan Tablo Sayısı : ", " Rest Tabelle Zählen : " };
/*    */ 
/*    */   
/* 33 */   private static String[] ms_UpgradedTableStr = new String[] { "Upgraded Tables : ", "Güncellenen Tablolar : ", "Aktualisierte Tabellen : " };
/*    */ 
/*    */   
/* 36 */   private static String[] ms_UpgradingTableStr = new String[] { "Upgrading Tables : ", "Güncellenmekte Olan Tablolar : ", "Aktualisieren von Tabellen : " };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String whileProcessingTables(String loginLang) {
/* 44 */     if (isFinished) {
/*    */       
/* 46 */       if (ms_SystemorFirm == 0) {
/*    */         
/* 48 */         if (JLbsStringUtil.isEmpty(ms_Errors.toString())) {
/* 49 */           return null;
/*    */         }
/* 51 */         return "errors" + ms_Errors;
/*    */       } 
/*    */       
/* 54 */       return null;
/*    */     } 
/*    */     
/* 57 */     int remainigTableCount = 0;
/* 58 */     int lang = 0;
/* 59 */     if (loginLang == null) {
/* 60 */       lang = 0;
/*    */     
/*    */     }
/* 63 */     else if (loginLang.compareToIgnoreCase(JLbsCultureInfoBase.getLanguagePrefix(1)) == 0) {
/* 64 */       lang = 1;
/* 65 */     } else if (loginLang.compareToIgnoreCase(JLbsCultureInfoBase.getLanguagePrefix(4)) == 0) {
/* 66 */       lang = 2;
/*    */     } else {
/* 68 */       lang = 0;
/*    */     } 
/* 70 */     if (ms_SystemorFirm == 0) {
/*    */       
/* 72 */       if (ms_SystemTableCount == 0)
/* 73 */         return ""; 
/* 74 */       remainigTableCount = (ms_SystemTableCount - ms_UpgradedTableCount < 0) ? 0 : (ms_SystemTableCount - ms_UpgradedTableCount);
/*    */     }
/*    */     else {
/*    */       
/* 78 */       if (ms_FirmTableCount == 0)
/* 79 */         return ""; 
/* 80 */       remainigTableCount = (ms_FirmTableCount - ms_UpgradedTableCount < 0) ? 0 : (ms_FirmTableCount - ms_UpgradedTableCount);
/*    */     } 
/*    */     
/* 83 */     if (JLbsStringUtil.isEmpty(ms_Errors.toString())) {
/* 84 */       return ms_TotalTableStr[lang] + ((ms_SystemorFirm == 0) ? ms_SystemTableCount : ms_FirmTableCount) + ms_RemainingTableStr[lang] + remainigTableCount + ";;:" + ms_UpgradingTableStr[lang] + ";;:" + ms_UpgradingTables + ms_UpgradedTableStr[lang] + ";;:" + ms_UpgradedTables;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 91 */     if (ms_SystemorFirm == 0) {
/* 92 */       return ms_TotalTableStr[lang] + ((ms_SystemorFirm == 0) ? ms_SystemTableCount : ms_FirmTableCount) + ms_RemainingTableStr[lang] + remainigTableCount + ";;:" + ms_UpgradingTableStr[lang] + ";;:" + ms_UpgradingTables + ms_UpgradedTableStr[lang] + ";;:" + ms_UpgradedTables + "errors" + ms_Errors;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 98 */     return ms_TotalTableStr[lang] + ((ms_SystemorFirm == 0) ? ms_SystemTableCount : ms_FirmTableCount) + ms_RemainingTableStr[lang] + remainigTableCount + ";;:" + ms_UpgradingTableStr[lang] + ";;:" + ms_UpgradingTables + ms_UpgradedTableStr[lang] + ";;:" + ms_UpgradedTables;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\TableUpgradeProcessListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */