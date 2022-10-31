/*     */ package com.lbs.controller;
/*     */ 
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.localization.LbsMessage;
/*     */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InvalidDataException
/*     */   extends LbsLocalizableException
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private boolean m_Silent = false;
/*     */   
/*     */   public InvalidDataException() {}
/*     */   
/*     */   public InvalidDataException(boolean silent) {
/*  22 */     this.m_Silent = silent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InvalidDataException(ILbsValidationResult result) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public InvalidDataException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  32 */     super(listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(int listID, int stringTag, String defaultMessage) {
/*  37 */     super(listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  42 */     super(resGroup, listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(String resGroup, int listID, int stringTag, String defaultMessage) {
/*  47 */     super(resGroup, listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  52 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/*  57 */     super(cause, listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  62 */     super(cause, resGroup, listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, String resGroup, int listID, int stringTag, String defaultMessage) {
/*  67 */     super(cause, resGroup, listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause) {
/*  72 */     super(cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(String messageId, LbsMessage defaultTemplateMessage) {
/*  77 */     super(messageId, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(String messageId, String defaultMessage) {
/*  82 */     super(messageId, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, String messageId, LbsMessage defaultTemplateMessage) {
/*  87 */     super(cause, messageId, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public InvalidDataException(Throwable cause, String messageId, String defaultMessage) {
/*  92 */     super(cause, messageId, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSilent() {
/*  97 */     return this.m_Silent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSilent(boolean isSilent) {
/* 102 */     this.m_Silent = isSilent;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\InvalidDataException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */