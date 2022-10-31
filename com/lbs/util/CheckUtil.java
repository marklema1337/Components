/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.data.query.QueryTableLink;
/*    */ import java.util.List;
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
/*    */ public class CheckUtil
/*    */ {
/*    */   public static boolean canUserHaveCustomizedLink(List<String> guids, QueryTableLink link) {
/* 20 */     if (link != null && link.getTable() != null && guids != null) {
/*    */       
/* 22 */       String guid = link.getTable().getGuid();
/* 23 */       if (JLbsStringUtil.isEmpty(guid)) {
/* 24 */         return true;
/*    */       }
/* 26 */       for (int i = 0; i < guids.size(); i++) {
/*    */         
/* 28 */         String userGUID = guids.get(i);
/* 29 */         if (guid.equals(userGUID))
/* 30 */           return true; 
/*    */       } 
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\CheckUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */