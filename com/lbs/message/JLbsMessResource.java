/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import java.io.Serializable;
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
/*    */ public class JLbsMessResource
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_ResourceGroup;
/*    */   private int m_ListId;
/*    */   private int m_StringTag;
/*    */   
/*    */   public JLbsMessResource(String resourceGroup, int listId, int stringTag) {
/* 27 */     this.m_ResourceGroup = resourceGroup;
/* 28 */     this.m_ListId = listId;
/* 29 */     this.m_StringTag = stringTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceGroup() {
/* 34 */     return this.m_ResourceGroup;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListId() {
/* 39 */     return this.m_ListId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStringTag() {
/* 44 */     return this.m_StringTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStringValue(ILocalizationServices localizationService) {
/* 49 */     return localizationService.getItem(this.m_ListId, this.m_StringTag, this.m_ResourceGroup);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */