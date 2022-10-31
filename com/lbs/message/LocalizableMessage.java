/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.platform.interfaces.ILocalizableMessage;
/*    */ import com.lbs.platform.interfaces.MessageType;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class LocalizableMessage
/*    */   extends Message
/*    */   implements ILocalizableMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected int m_ListId;
/*    */   protected int m_StringTag;
/*    */   protected String[] m_MessageParamValues;
/*    */   
/*    */   public LocalizableMessage(MessageType type, int listId, int stringTag, String defaultMessage) {
/* 28 */     super(defaultMessage, type);
/* 29 */     this.m_ListId = listId;
/* 30 */     this.m_StringTag = stringTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public LocalizableMessage(String message, MessageType type, int listId, int stringTag, String[] messageParamValues) {
/* 35 */     super(message, type);
/* 36 */     this.m_ListId = listId;
/* 37 */     this.m_StringTag = stringTag;
/* 38 */     this.m_MessageParamValues = messageParamValues;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LocalizableMessage() {}
/*    */ 
/*    */   
/*    */   public String getMessage(ILocalizationServices localService) {
/* 47 */     if (localService != null) {
/*    */       
/* 49 */       String s = localService.getItem(this.m_ListId, this.m_StringTag);
/* 50 */       if (!StringUtil.isEmpty(s))
/* 51 */         return getSubsMessage(s, this.m_MessageParamValues); 
/*    */     } 
/* 53 */     return getSubsMessage(getMessage(), this.m_MessageParamValues);
/*    */   }
/*    */ 
/*    */   
/*    */   private String getSubsMessage(String message, String[] paramValues) {
/* 58 */     if (paramValues == null || paramValues.length == 0)
/* 59 */       return message; 
/* 60 */     return JLbsStringUtil.mergeParameters(message, paramValues, getParams(paramValues));
/*    */   }
/*    */ 
/*    */   
/*    */   private int[] getParams(String[] paramValues) {
/* 65 */     int[] arr = new int[paramValues.length];
/* 66 */     for (int i = 0; i < arr.length; i++)
/*    */     {
/* 68 */       arr[i] = i + 1;
/*    */     }
/* 70 */     return arr;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\LocalizableMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */