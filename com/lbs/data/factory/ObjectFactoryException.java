/*    */ package com.lbs.data.factory;
/*    */ 
/*    */ import com.lbs.localization.LbsMessage;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjectFactoryException
/*    */   extends FactoryException
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ErrorType errorType;
/*    */   private String userInfoHaveLocked;
/*    */   
/*    */   public enum ErrorType
/*    */   {
/* 21 */     UserRestrictedError;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUserInfoHaveLocked() {
/* 30 */     return this.userInfoHaveLocked;
/*    */   }
/*    */ 
/*    */   
/*    */   public ErrorType getErrorType() {
/* 35 */     return this.errorType;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException setErrorType(ErrorType errorType) {
/* 40 */     this.errorType = errorType;
/* 41 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 46 */     super(listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(int listID, int stringTag, String defaultMessage) {
/* 51 */     super(listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 56 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/* 61 */     super(cause, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(int id, int listID, int stringTag, LbsMessage message) {
/* 66 */     super(id, listID, stringTag, message);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(int id, int listID, int stringTag, String message) {
/* 71 */     super(id, listID, stringTag, message);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(int id, int listID, int stringTag, String message, String userInfo) {
/* 76 */     super(id, listID, stringTag, message);
/* 77 */     this.userInfoHaveLocked = userInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(Exception e) {
/* 82 */     super(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectFactoryException(DBErrors errors) {
/* 87 */     super(errors);
/* 88 */     if (errors != null)
/* 89 */       this.userInfoHaveLocked = errors.getUserInfoHaveLocked(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ObjectFactoryException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */