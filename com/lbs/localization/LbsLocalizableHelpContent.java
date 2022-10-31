/*    */ package com.lbs.localization;
/*    */ 
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
/*    */ 
/*    */ public class LbsLocalizableHelpContent
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_Id;
/*    */   private String m_DocName;
/*    */   private String m_DocTitle;
/*    */   private int m_DocType;
/*    */   private String m_DocBody;
/*    */   
/*    */   public LbsLocalizableHelpContent() {}
/*    */   
/*    */   public LbsLocalizableHelpContent(int id, String docName, String docTitle, int docType, String docBody) {
/* 31 */     this.m_Id = id;
/* 32 */     this.m_DocName = docName;
/* 33 */     this.m_DocTitle = docTitle;
/* 34 */     this.m_DocType = docType;
/* 35 */     this.m_DocBody = docBody;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 40 */     return this.m_Id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 45 */     this.m_Id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDocName() {
/* 50 */     return this.m_DocName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDocName(String docName) {
/* 55 */     this.m_DocName = docName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDocTitle() {
/* 60 */     return this.m_DocTitle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDocTitle(String docTitle) {
/* 65 */     this.m_DocTitle = docTitle;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDocType() {
/* 70 */     return this.m_DocType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDocType(int docType) {
/* 75 */     this.m_DocType = docType;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDocBody() {
/* 80 */     return this.m_DocBody;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDocBody(String docBody) {
/* 85 */     this.m_DocBody = docBody;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsLocalizableHelpContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */