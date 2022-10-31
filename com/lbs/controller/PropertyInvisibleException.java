/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import com.lbs.localization.LbsMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyInvisibleException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public PropertyInvisibleException() {}
/*    */   
/*    */   public PropertyInvisibleException(Throwable cause) {
/* 17 */     super(cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(int listID, int stringTag, String defaultMessage) {
/* 22 */     super(listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/* 27 */     super(cause, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 32 */     super(listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 37 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(String resGroup, int listID, int stringTag, String defaultMessage) {
/* 42 */     super(resGroup, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(Throwable cause, String resGroup, int listID, int stringTag, String defaultMessage) {
/* 47 */     super(cause, resGroup, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 52 */     super(resGroup, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyInvisibleException(Throwable cause, String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 57 */     super(cause, resGroup, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyInvisibleException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */