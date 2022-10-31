/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.localization.LbsMessage;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FactoryException
/*     */   extends LbsLocalizableException
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   private DBErrors m_Errors = null;
/*     */ 
/*     */   
/*     */   public FactoryException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  26 */     super(listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(int listID, int stringTag, String defaultMessage) {
/*  31 */     super(listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  36 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/*  41 */     super(cause, listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(int id, int listID, int stringTag, String message) {
/*  46 */     this.m_Errors = new DBErrors();
/*  47 */     this.m_Errors.addError(id, listID, stringTag, message);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(int id, int listID, int stringTag, LbsMessage message) {
/*  52 */     this.m_Errors = new DBErrors();
/*  53 */     this.m_Errors.addError(id, listID, stringTag, message);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(DBErrors errors) {
/*  58 */     this.m_Errors = new DBErrors();
/*  59 */     this.m_Errors.load(errors);
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryException(Exception e) {
/*  64 */     this.m_Errors = new DBErrors();
/*     */     
/*  66 */     if (e instanceof FactoryException) {
/*  67 */       this.m_Errors.load(((FactoryException)e).getErrors());
/*  68 */     } else if (e instanceof SQLException) {
/*  69 */       this.m_Errors.add(DBError.makeErrorOutOfSQLException((SQLException)e));
/*     */     } else {
/*  71 */       this.m_Errors.add(DBError.makeErrorOutOfException(e));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBErrors getErrors() {
/*  80 */     return this.m_Errors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrors(DBErrors errors) {
/*  89 */     this.m_Errors = errors;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  94 */     return getClass().getName() + " caused by:" + ((this.m_Errors == null) ? "Unknown" : this.m_Errors
/*     */       
/*  96 */       .getErrorsString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void localizeMessage(ILocalizationServices lclService) {
/* 101 */     super.localizeMessage(lclService);
/* 102 */     if (this.m_Errors != null) {
/* 103 */       this.m_Errors.localizeErrors(lclService);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getLocalizedMessage() {
/* 108 */     String message = super.getLocalizedMessage();
/* 109 */     if (this.m_ListID == -1003 && this.m_StringTag == 1 && this.m_Errors != null) {
/*     */       
/* 111 */       String errorMessage = this.m_Errors.toString();
/* 112 */       if (!StringUtil.isEmpty(errorMessage))
/* 113 */         return errorMessage; 
/*     */     } 
/* 115 */     return message + " caused by:" + ((this.m_Errors == null) ? "" : this.m_Errors
/*     */       
/* 117 */       .toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void printStackTrace() {
/* 122 */     super.printStackTrace();
/* 123 */     if (this.m_Errors != null)
/* 124 */       this.m_Errors.printErrorStackTraces(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\FactoryException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */