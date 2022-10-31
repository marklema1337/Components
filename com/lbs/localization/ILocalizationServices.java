/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.customization.CustomizationGUID;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.message.JLbsMessage;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ILocalizationServices
/*     */ {
/*     */   public static final String LCL_SERVICE = "LSW";
/*     */   
/*     */   void setLanguage(String paramString);
/*     */   
/*     */   JLbsCultureInfoBase getCulture() throws Exception;
/*     */   
/*     */   LbsLocalizableHelpContent getHelpContent(String paramString1, String paramString2);
/*     */   
/*     */   LbsLocalizableHelpContent getHelpContent(String paramString);
/*     */   
/*     */   ArrayList getAllHelpContents(String paramString);
/*     */   
/*     */   ArrayList getAllHelpContents();
/*     */   
/*     */   ArrayList getHelpContents(String paramString, int[] paramArrayOfint);
/*     */   
/*     */   ArrayList getHelpContents(int[] paramArrayOfint);
/*     */   
/*     */   JLbsMessage getMessage(String paramString);
/*     */   
/*     */   String[] getMessageConstantIds(String paramString);
/*     */   
/*     */   JLbsMessage getMessage(String paramString1, String paramString2);
/*     */   
/*     */   String[] getMessageConstantIds(String paramString1, String paramString2);
/*     */   
/*     */   JLbsStringList getList(String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   JLbsStringList getList(String paramString, int paramInt);
/*     */   
/*     */   JLbsStringList getList(int paramInt, String paramString);
/*     */   
/*     */   JLbsStringList getList(int paramInt);
/*     */   
/*     */   JLbsStringList getList(int paramInt, int[] paramArrayOfint);
/*     */   
/*     */   ArrayList getListsByRange(int paramInt1, int paramInt2);
/*     */   
/*     */   ArrayList getListsByRange(int paramInt1, int paramInt2, String paramString);
/*     */   
/*     */   ArrayList getListsByRange(String paramString1, int paramInt1, int paramInt2, String paramString2);
/*     */   
/*     */   ArrayList getListsByIDSet(int[] paramArrayOfint);
/*     */   
/*     */   ArrayList getListsByIDSet(int[] paramArrayOfint, String paramString);
/*     */   
/*     */   ArrayList getListsByIDSet(String paramString1, int[] paramArrayOfint, String paramString2);
/*     */   
/*     */   ArrayList getListsByStartID(int paramInt);
/*     */   
/*     */   ArrayList getListsByStartID(int paramInt, String paramString);
/*     */   
/*     */   ArrayList getListsByStartID(String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   ArrayList getListsByEndID(int paramInt);
/*     */   
/*     */   ArrayList getListsByEndID(int paramInt, String paramString);
/*     */   
/*     */   ArrayList getListsByEndID(String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   ArrayList getListHeadersByRange(int paramInt1, int paramInt2);
/*     */   
/*     */   ArrayList getListHeadersByRange(int paramInt1, int paramInt2, String paramString);
/*     */   
/*     */   ArrayList getListHeadersByRange(String paramString1, int paramInt1, int paramInt2, String paramString2);
/*     */   
/*     */   ArrayList getListHeadersByIDSet(int[] paramArrayOfint);
/*     */   
/*     */   ArrayList getListHeadersByIDSet(int[] paramArrayOfint, String paramString);
/*     */   
/*     */   ArrayList getListHeadersByIDSet(String paramString1, int[] paramArrayOfint, String paramString2);
/*     */   
/*     */   ArrayList getListHeadersByStartID(int paramInt);
/*     */   
/*     */   ArrayList getListHeadersByStartID(int paramInt, String paramString);
/*     */   
/*     */   ArrayList getListHeadersByStartID(String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   ArrayList getListHeadersByEndID(int paramInt);
/*     */   
/*     */   ArrayList getListHeadersByEndID(int paramInt, String paramString);
/*     */   
/*     */   ArrayList getListHeadersByEndID(String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   String getItem(String paramString1, int paramInt1, int paramInt2, String paramString2);
/*     */   
/*     */   String getItem(String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */   String getItem(int paramInt1, int paramInt2, String paramString);
/*     */   
/*     */   String getItem(int paramInt1, int paramInt2);
/*     */   
/*     */   int getMaxListID(String paramString);
/*     */   
/*     */   int getMaxListID(String paramString1, String paramString2);
/*     */   
/*     */   int getMinListID(String paramString);
/*     */   
/*     */   int getMinListID(String paramString1, String paramString2);
/*     */   
/*     */   JLbsStringList getList(CustomizationGUID paramCustomizationGUID, String paramString1, int paramInt, String paramString2);
/*     */   
/*     */   JLbsStringList getList(CustomizationGUID paramCustomizationGUID, String paramString, int paramInt);
/*     */   
/*     */   JLbsStringList getList(CustomizationGUID paramCustomizationGUID, int paramInt, String paramString);
/*     */   
/*     */   JLbsStringList getList(CustomizationGUID paramCustomizationGUID, int paramInt);
/*     */   
/*     */   String getItem(CustomizationGUID paramCustomizationGUID, String paramString1, int paramInt1, int paramInt2, String paramString2);
/*     */   
/*     */   String getItem(CustomizationGUID paramCustomizationGUID, String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */   String getItem(CustomizationGUID paramCustomizationGUID, int paramInt1, int paramInt2, String paramString);
/*     */   
/*     */   String getItem(CustomizationGUID paramCustomizationGUID, int paramInt1, int paramInt2);
/*     */   
/*     */   default JLbsStringList getListByGroup(String langPrefix, int listID, String group) {
/* 446 */     return getList(langPrefix, listID);
/*     */   }
/*     */ 
/*     */   
/*     */   default JLbsStringList getListByGroup(int listID, String group) {
/* 451 */     return getList(listID);
/*     */   }
/*     */ 
/*     */   
/*     */   default String getItemByGroup(String langPrefix, int listID, int stringTag, String group) {
/* 456 */     return getItem(langPrefix, listID, stringTag);
/*     */   }
/*     */ 
/*     */   
/*     */   default String getItemByGroup(int listID, int stringTag, String group) {
/* 461 */     return getItem(listID, stringTag);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\ILocalizationServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */