/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import com.lbs.localization.LbsMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyNotAvailableException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public PropertyNotAvailableException() {}
/*    */   
/*    */   public PropertyNotAvailableException(Throwable cause) {
/* 17 */     super(cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(int listID, int stringTag, String defaultMessage) {
/* 22 */     super(listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/* 27 */     super(cause, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 32 */     super(listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 37 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(String resGroup, int listID, int stringTag, String defaultMessage) {
/* 42 */     super(resGroup, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(Throwable cause, String resGroup, int listID, int stringTag, String defaultMessage) {
/* 47 */     super(cause, resGroup, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 52 */     super(resGroup, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyNotAvailableException(Throwable cause, String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 57 */     super(cause, resGroup, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyNotAvailableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */