/*    */ package com.lbs.localization;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public class LbsLocalizationLanguage
/*    */ {
/*    */   private String m_Name;
/*    */   private String m_Description;
/*    */   private String m_DatabaseTableName;
/* 19 */   private ArrayList<String> m_PreferredLanguages = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsLocalizationLanguage() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsLocalizationLanguage(String name, String description, String databaseTableName, String[] preferredLanguages) {
/* 28 */     this.m_Name = name;
/* 29 */     this.m_Description = description;
/* 30 */     this.m_DatabaseTableName = databaseTableName;
/* 31 */     if (preferredLanguages != null)
/* 32 */       for (int i = 0; i < preferredLanguages.length; i++) {
/* 33 */         this.m_PreferredLanguages.add(preferredLanguages[i]);
/*    */       } 
/*    */   }
/*    */   
/*    */   public String getName() {
/* 38 */     return this.m_Name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 42 */     this.m_Name = name;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 46 */     return this.m_Description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 50 */     this.m_Description = description;
/*    */   }
/*    */   
/*    */   public String getDatabaseTableName() {
/* 54 */     return this.m_DatabaseTableName;
/*    */   }
/*    */   
/*    */   public void setDatabaseTableName(String databaseTableName) {
/* 58 */     this.m_DatabaseTableName = databaseTableName;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getPreferredLanguages() {
/* 62 */     return this.m_PreferredLanguages;
/*    */   }
/*    */   
/*    */   public void setPreferredLanguages(ArrayList<String> preferredLanguages) {
/* 66 */     this.m_PreferredLanguages = preferredLanguages;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsLocalizationLanguage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */