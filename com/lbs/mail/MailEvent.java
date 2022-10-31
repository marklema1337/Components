/*    */ package com.lbs.mail;
/*    */ 
/*    */ import com.lbs.smtp.JLbsSMTPConnectionInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailEvent
/*    */ {
/*    */   private JLbsMailMessage m_Message;
/*    */   private JLbsSMTPConnectionInfo m_SmtpSettings;
/*    */   private boolean m_Consumed = false;
/*    */   
/*    */   public MailEvent() {}
/*    */   
/*    */   public MailEvent(JLbsMailMessage message, JLbsSMTPConnectionInfo smtpSettings) {
/* 18 */     this.m_Message = message;
/* 19 */     this.m_SmtpSettings = smtpSettings;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsMailMessage getMessage() {
/* 24 */     return this.m_Message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(JLbsMailMessage message) {
/* 29 */     this.m_Message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsumed() {
/* 34 */     return this.m_Consumed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConsumed(boolean consumed) {
/* 39 */     this.m_Consumed = consumed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSmtpSettings(JLbsSMTPConnectionInfo smtpSettings) {
/* 44 */     this.m_SmtpSettings = smtpSettings;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsSMTPConnectionInfo getSmtpSettings() {
/* 49 */     return this.m_SmtpSettings;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\MailEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */