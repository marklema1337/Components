/*    */ package com.lbs.localization;
/*    */ 
/*    */ import com.lbs.util.JLbsStringList;
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
/*    */ public class ReportingLocalizationServices
/*    */   extends LuceneLocalizationServices
/*    */ {
/*    */   public ReportingLocalizationServices(String langPrefix) {
/* 19 */     super(langPrefix);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getItem(int listID, int stringTag) {
/* 25 */     String item = getItem(this.m_Language, listID, stringTag, "UNRP");
/* 26 */     if (item != null) {
/* 27 */       return item;
/*    */     }
/* 29 */     item = getItem(this.m_Language, listID, stringTag, "HRRP");
/*    */     
/* 31 */     if (item != null) {
/* 32 */       return item;
/*    */     }
/* 34 */     return getItem(this.m_Language, listID, stringTag, "UN");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getItem(String langPrefix, int listID, int stringTag) {
/* 40 */     String item = getItem(langPrefix, listID, stringTag, "UNRP");
/* 41 */     if (item != null) {
/* 42 */       return item;
/*    */     }
/* 44 */     item = getItem(langPrefix, listID, stringTag, "HRRP");
/* 45 */     if (item != null) {
/* 46 */       return item;
/*    */     }
/* 48 */     return getItem(langPrefix, listID, stringTag, "UN");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsStringList getList(int listID) throws RuntimeException {
/* 54 */     JLbsStringList list = getList(this.m_Language, listID, "UNRP");
/* 55 */     if (list != null && list.size() > 0) {
/* 56 */       return list;
/*    */     }
/* 58 */     list = getList(this.m_Language, listID, "HRRP");
/* 59 */     if (list != null && list.size() > 0) {
/* 60 */       return list;
/*    */     }
/* 62 */     return getList(this.m_Language, listID, "UN");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsStringList getList(String langPrefix, int listID) {
/* 68 */     JLbsStringList list = getList(langPrefix, listID, "UNRP");
/* 69 */     if (list != null && list.size() > 0) {
/* 70 */       return list;
/*    */     }
/* 72 */     list = getList(langPrefix, listID, "HRRP");
/* 73 */     if (list != null && list.size() > 0) {
/* 74 */       return list;
/*    */     }
/* 76 */     return getList(langPrefix, listID, "UN");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\ReportingLocalizationServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */