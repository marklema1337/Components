/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.data.application.IApplicationEnvironment;
/*    */ import com.lbs.data.objects.BasicBusinessObject;
/*    */ import java.io.Serializable;
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
/*    */ public class BusinessObjectChangeHeader
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_UserID;
/*    */   private String m_XUIFormName;
/*    */   private String m_XUIFormTitle;
/*    */   private BasicBusinessObject m_InitialObject;
/*    */   private IApplicationEnvironment m_ApplicationEnvironment;
/*    */   private Calendar m_Date;
/*    */   private String m_FileCanonicalPath;
/*    */   
/*    */   public BusinessObjectChangeHeader(int userID, String formName, String formTitle, BasicBusinessObject busObj, IApplicationEnvironment applicationEnvironment, Calendar date) {
/* 32 */     this.m_UserID = userID;
/* 33 */     this.m_XUIFormName = formName;
/* 34 */     this.m_XUIFormTitle = formTitle;
/* 35 */     this.m_InitialObject = busObj;
/* 36 */     this.m_ApplicationEnvironment = applicationEnvironment;
/* 37 */     this.m_Date = date;
/*    */   }
/*    */ 
/*    */   
/*    */   public IApplicationEnvironment getApplicationEnvironment() {
/* 42 */     return this.m_ApplicationEnvironment;
/*    */   }
/*    */   
/*    */   public void setApplicationEnvironment(IApplicationEnvironment applicationEnvironment) {
/* 46 */     this.m_ApplicationEnvironment = applicationEnvironment;
/*    */   }
/*    */   
/*    */   public int getUserID() {
/* 50 */     return this.m_UserID;
/*    */   }
/*    */   
/*    */   public void setUserID(int userID) {
/* 54 */     this.m_UserID = userID;
/*    */   }
/*    */   
/*    */   public String getXUIFormName() {
/* 58 */     return this.m_XUIFormName;
/*    */   }
/*    */   
/*    */   public void setXUIFormName(String formName) {
/* 62 */     this.m_XUIFormName = formName;
/*    */   }
/*    */   
/*    */   public BasicBusinessObject getInitialObject() {
/* 66 */     return this.m_InitialObject;
/*    */   }
/*    */   
/*    */   public void setInitialObject(BasicBusinessObject initialObject) {
/* 70 */     this.m_InitialObject = initialObject;
/*    */   }
/*    */   
/*    */   public Calendar getDate() {
/* 74 */     return this.m_Date;
/*    */   }
/*    */   
/*    */   public void setDate(Calendar date) {
/* 78 */     this.m_Date = date;
/*    */   }
/*    */   
/*    */   public String getFileCanonicalPath() {
/* 82 */     return this.m_FileCanonicalPath;
/*    */   }
/*    */   
/*    */   public void setFileCanonicalPath(String fileCanonicalPath) {
/* 86 */     this.m_FileCanonicalPath = fileCanonicalPath;
/*    */   }
/*    */   
/*    */   public String getXUIFormTitle() {
/* 90 */     return this.m_XUIFormTitle;
/*    */   }
/*    */   
/*    */   public void setXUIFormTitle(String formTitle) {
/* 94 */     this.m_XUIFormTitle = formTitle;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\BusinessObjectChangeHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */