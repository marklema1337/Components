/*     */ package com.lbs.platform.client;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.IModeOwner;
/*     */ import com.lbs.data.objects.ObjectValueManager;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.IAuthorizationManager;
/*     */ import com.lbs.transport.IUserDetailInfo;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.LbsAuthCodeHelper;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsAuthorizationManager
/*     */   implements IAuthorizationManager
/*     */ {
/*     */   public JLbsStringList getAuthCodesWithRight(JLbsStringList dbRights, int id) {
/*  34 */     return LbsAuthCodeHelper.getAuthCodesWithRight(dbRights, id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultAuthCode(JLbsStringList dbRights) {
/*  40 */     return LbsAuthCodeHelper.getDefaultAuthCode(dbRights);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRight(JLbsStringList rightList, int rightID, int id, String authCode) {
/*  46 */     return LbsAuthCodeHelper.hasRight(rightList, rightID, id, authCode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRight(BasicBusinessObject object, int rightID, JLbsStringList authCodeFields, JLbsStringList dbRights, Hashtable<String, String> implProps) {
/*     */     int i;
/*  53 */     if (object == null) {
/*  54 */       return true;
/*     */     }
/*  56 */     if (dbRights == null || dbRights.size() == 0 || authCodeFields == null || authCodeFields.size() == 0) {
/*  57 */       return true;
/*     */     }
/*  59 */     JLbsStringListItem item = null;
/*  60 */     String authCode = null;
/*  61 */     String authCodeField = null;
/*  62 */     boolean result = true;
/*  63 */     boolean allEmpty = true;
/*  64 */     boolean hasOrgUnitAuth = true;
/*  65 */     boolean hasParentAsgOrgUnit = false;
/*  66 */     boolean processOrgUnitAuth = false;
/*  67 */     String asgOUCodeFieldName = null;
/*  68 */     IApplicationContext context = null;
/*     */     
/*  70 */     boolean checkAsgOrgUnitCode = (implProps != null && implProps.containsKey("CheckAsgOrgUnitCode") && (
/*  71 */       (String)implProps.get("CheckAsgOrgUnitCode")).equalsIgnoreCase("True"));
/*     */     
/*  73 */     if (checkAsgOrgUnitCode) {
/*     */       
/*  75 */       hasParentAsgOrgUnit = true;
/*  76 */       asgOUCodeFieldName = implProps.get("AsgOUCodeFieldName");
/*  77 */       authCodeFields.add(asgOUCodeFieldName, 291);
/*     */     } 
/*     */     
/*  80 */     for (int idx = 0; idx < authCodeFields.size(); idx++) {
/*     */ 
/*     */       
/*     */       try {
/*  84 */         item = authCodeFields.getItemAt(idx);
/*  85 */         if (item.Tag == -1) {
/*  86 */           authCodeField = "UnitInfoCode";
/*  87 */         } else if (item.Tag == 291) {
/*  88 */           authCodeField = asgOUCodeFieldName;
/*     */         } else {
/*  90 */           authCodeField = "AuthCode";
/*  91 */         }  if (!JLbsStringUtil.isEmpty(item.Value)) {
/*  92 */           authCodeField = item.Value;
/*     */         }
/*  94 */         authCode = (String)ObjectValueManager.getMemberValue(object, authCodeField, true);
/*     */         
/*  96 */         int authId = item.Tag;
/*  97 */         if (authId == JLbsConstants.DUMMY_AUTH_ID) {
/*     */           
/*  99 */           Object src = object;
/* 100 */           int dotIdx = authCodeField.lastIndexOf('.');
/* 101 */           if (dotIdx > 0)
/* 102 */             src = ObjectValueManager.getMemberValue(object, authCodeField.substring(0, dotIdx), false); 
/* 103 */           if (src instanceof IModeOwner) {
/* 104 */             authId = ((IModeOwner)src).getObjectMode().getAuthId();
/*     */           }
/*     */         } 
/* 107 */         if (authId == -1 && !JLbsStringUtil.isEmpty(authCode)) {
/* 108 */           processOrgUnitAuth = true;
/*     */         }
/* 110 */         if (LbsAuthCodeHelper.hasRight(dbRights, rightID, authId, authCode)) {
/*     */           
/* 112 */           if (!JLbsStringUtil.isEmpty(authCode) && authId != -1 && authId != 291)
/* 113 */             allEmpty = false; 
/* 114 */           i = result & true;
/*     */         }
/*     */         else {
/*     */           
/* 118 */           if (authId == -1) {
/* 119 */             hasOrgUnitAuth = false;
/* 120 */           } else if (authId == 291) {
/* 121 */             hasParentAsgOrgUnit = false;
/*     */           } else {
/* 123 */             i &= 0x0;
/*     */           } 
/* 125 */           if (JLbsConstants.DEBUG) {
/*     */             
/* 127 */             if (context == null) {
/*     */               
/* 129 */               Object obj = JLbsComponentHelper.getCurrentContext();
/* 130 */               if (obj instanceof IApplicationContext)
/* 131 */                 context = (IApplicationContext)obj; 
/*     */             } 
/* 133 */             if (context != null)
/*     */             {
/* 135 */               String msg = context.getLocalizationService().getItem(70120, authId);
/* 136 */               context.getLogger().debug("Missing Right :" + msg + " " + authCodeField + " : " + authCode);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 141 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 145 */     if (allEmpty || LbsAuthCodeHelper.forceOrgunitAuth(processOrgUnitAuth)) {
/* 146 */       return (i != 0 && (hasOrgUnitAuth || hasParentAsgOrgUnit));
/*     */     }
/* 148 */     if (i != 0)
/*     */     {
/* 150 */       if (rightID != 16 || hasOrgUnitAuth || hasParentAsgOrgUnit)
/*     */         return true;  } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static boolean businessObjectHasDBRight(IApplicationContext context, int dbRightId, BusinessObject bo) {
/* 156 */     return businessObjectHasDBRight(context, dbRightId, bo, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean businessObjectHasDBRight(IApplicationContext context, int dbRightId, BusinessObject bo, Hashtable<String, String> implProps) {
/* 161 */     return businessObjectHasDBRight(context, dbRightId, bo, implProps, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean businessObjectHasDBRight(IApplicationContext context, int dbRightId, BusinessObject bo, Hashtable<String, String> implProps, JLbsStringList authCodeFields) {
/* 167 */     IUserDetailInfo userDetailInfo = context.getUserDetailInfo();
/* 168 */     IAuthorizationManager authorizationManager = context.getAuthorizationManager();
/* 169 */     if (userDetailInfo != null && authorizationManager != null) {
/*     */       
/* 171 */       JLbsStringList dbRights = userDetailInfo.getDBRights();
/* 172 */       if (authCodeFields == null)
/* 173 */         authCodeFields = bo.getAuthFields(); 
/* 174 */       return authorizationManager.hasRight((BasicBusinessObject)bo, dbRightId, authCodeFields, dbRights, implProps);
/*     */     } 
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRight(BasicBusinessObject rowObject, int rightID, JLbsStringList authCodeFields, JLbsStringList dbRights) {
/* 182 */     return hasRight(rowObject, rightID, authCodeFields, dbRights, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\LbsAuthorizationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */