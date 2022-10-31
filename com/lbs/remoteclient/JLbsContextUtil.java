/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.data.objects.BasicBusinessObject;
/*    */ import com.lbs.data.objects.BusinessObject;
/*    */ import com.lbs.data.objects.ObjectValueManager;
/*    */ import com.lbs.invoke.RttiUtil;
/*    */ import com.lbs.transport.UserInfo;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.util.Calendar;
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
/*    */ 
/*    */ public class JLbsContextUtil
/*    */ {
/*    */   private static void setFieldValue(BasicBusinessObject bo, String fieldName, String alternateName, Object value) {
/*    */     try {
/* 30 */       RttiUtil.setProperty(bo, fieldName, value);
/*    */     }
/* 32 */     catch (Exception e) {
/*    */ 
/*    */       
/*    */       try {
/* 36 */         RttiUtil.setProperty(bo, alternateName, value);
/*    */       }
/* 38 */       catch (Exception exception) {}
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void createUserLogInfo(IClientContext context, int boReference, BusinessObject bo) {
/* 47 */     if (context == null || bo == null) {
/*    */       return;
/*    */     }
/* 50 */     Object userLoginInfo = context.getVariable("UserLoginInfo");
/*    */     
/* 52 */     Integer userNr = Integer.valueOf(0);
/* 53 */     String userName = "";
/*    */     
/*    */     try {
/* 56 */       userNr = (Integer)ObjectValueManager.getMemberValue(userLoginInfo, "User.Nr");
/* 57 */       userName = (String)ObjectValueManager.getMemberValue(userLoginInfo, "User.Name");
/*    */     }
/* 59 */     catch (Exception e) {
/*    */       
/* 61 */       LbsConsole.getLogger("Transport.JLbsContextUtil").debug(e.getMessage(), e);
/*    */     } 
/*    */ 
/*    */     
/*    */     try {
/* 66 */       if (JLbsStringUtil.isEmpty(userName)) {
/*    */         
/* 68 */         UserInfo userInfo = context.getUserInfo();
/* 69 */         if (userInfo != null) {
/* 70 */           userName = userInfo.Name;
/*    */         }
/*    */       } 
/* 73 */     } catch (Exception e) {
/*    */       
/* 75 */       LbsConsole.getLogger("Transport.JLbsContextUtil").debug(e.getMessage(), e);
/*    */     } 
/*    */     
/* 78 */     Calendar date = Calendar.getInstance();
/* 79 */     if (boReference == 0) {
/*    */       
/* 81 */       setFieldValue((BasicBusinessObject)bo, "Created_By", "CreatedBy", userNr);
/* 82 */       setFieldValue((BasicBusinessObject)bo, "Created_ByName", "CreatedByName", (userName == null) ? "" : userName);
/* 83 */       setFieldValue((BasicBusinessObject)bo, "Created_On", "CreatedOn", date);
/* 84 */       setFieldValue((BasicBusinessObject)bo, "Modified_By", "ModifiedBy", null);
/* 85 */       setFieldValue((BasicBusinessObject)bo, "Modified_ByName", "ModifiedByName", "");
/* 86 */       setFieldValue((BasicBusinessObject)bo, "Modified_On", "ModifiedOn", null);
/*    */     }
/*    */     else {
/*    */       
/* 90 */       setFieldValue((BasicBusinessObject)bo, "Modified_By", "ModifiedBy", userNr);
/* 91 */       setFieldValue((BasicBusinessObject)bo, "Modified_ByName", "ModifiedByName", (userName == null) ? "" : userName);
/* 92 */       setFieldValue((BasicBusinessObject)bo, "Modified_On", "ModifiedOn", date);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsContextUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */