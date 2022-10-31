/*    */ package com.lbs.localization;
/*    */ 
/*    */ import com.lbs.cache.IVersionSource;
/*    */ import com.lbs.remoteclient.IClientContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientReportingLocalizationServices
/*    */   extends ClientLocalizationServices
/*    */ {
/*    */   public ClientReportingLocalizationServices(IClientContext clientContext, IVersionSource versionSource) {
/* 24 */     super(clientContext, versionSource);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getItem(String langPrefix, int listID, int stringTag) {
/* 29 */     String item = getItem(langPrefix, listID, stringTag, "UNRP");
/* 30 */     if (item != null) {
/* 31 */       return item;
/*    */     }
/* 33 */     item = getItem(langPrefix, listID, stringTag, "HRRP");
/* 34 */     if (item != null) {
/* 35 */       return item;
/*    */     }
/* 37 */     return getItem(langPrefix, listID, stringTag, "UN");
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringList getList(String langPrefix, int listID) {
/* 42 */     JLbsStringList list = getList(langPrefix, listID, "UNRP");
/* 43 */     if (list != null && list.size() > 0) {
/* 44 */       return list;
/*    */     }
/* 46 */     list = getList(langPrefix, listID, "HRRP");
/* 47 */     if (list != null && list.size() > 0) {
/* 48 */       return list;
/*    */     }
/* 50 */     return getList(langPrefix, listID, "UN");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\ClientReportingLocalizationServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */