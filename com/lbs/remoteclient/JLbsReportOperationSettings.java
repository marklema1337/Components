/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.mail.MailSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsReportOperationSettings
/*     */ {
/*     */   private MailSettings m_AccountSettings;
/*     */   private boolean m_ServerInfoEditable;
/*     */   private boolean m_SenderInfoEditable;
/*     */   private String m_Subject;
/*     */   private String m_MailTo;
/*     */   
/*     */   public JLbsReportOperationSettings(IClientContext context) {
/*  29 */     this.m_ServerInfoEditable = true;
/*  30 */     this.m_SenderInfoEditable = true;
/*  31 */     this.m_Subject = "";
/*  32 */     this.m_MailTo = "";
/*  33 */     this.m_AccountSettings = new MailSettings(context, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MailSettings getAccountSettings() {
/*  41 */     return this.m_AccountSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAccountSettings(MailSettings accountSettings) {
/*  49 */     this.m_AccountSettings = accountSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailTo() {
/*  57 */     return this.m_MailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailTo(String mailTo) {
/*  65 */     this.m_MailTo = mailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubject() {
/*  73 */     return this.m_Subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String subject) {
/*  81 */     this.m_Subject = subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSenderInfoEditable() {
/*  89 */     return this.m_SenderInfoEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSenderInfoEditable(boolean senderInfoEditable) {
/*  97 */     this.m_SenderInfoEditable = senderInfoEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isServerInfoEditable() {
/* 105 */     return this.m_ServerInfoEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerInfoEditable(boolean serverInfoEditable) {
/* 113 */     this.m_ServerInfoEditable = serverInfoEditable;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsReportOperationSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */