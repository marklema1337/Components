/*     */ package com.lbs.smtp;
/*     */ 
/*     */ import com.lbs.mail.JLbsMailMessage;
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
/*     */ public class JLbsSMTPQueueMessage
/*     */ {
/*     */   private JLbsMailMessage m_Message;
/*     */   private JLbsSMTPConnectionInfo m_ConnectionInfo;
/*     */   private ILbsSMTPMessageListener m_MesssageListener;
/*  19 */   private long m_ID = 0L;
/*     */ 
/*     */   
/*     */   public JLbsSMTPQueueMessage(JLbsMailMessage mail, JLbsSMTPConnectionInfo conInfo) {
/*  23 */     this.m_ConnectionInfo = conInfo;
/*  24 */     this.m_Message = mail;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMailMessage getMessage() {
/*  30 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessage(JLbsMailMessage message) {
/*  35 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsSMTPConnectionInfo getConnectionInfo() {
/*  43 */     return this.m_ConnectionInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionInfo(JLbsSMTPConnectionInfo connectionInfo) {
/*  51 */     this.m_ConnectionInfo = connectionInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsSMTPMessageListener getMesssageListener() {
/*  59 */     return this.m_MesssageListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMesssageListener(ILbsSMTPMessageListener messsageListener) {
/*  67 */     this.m_MesssageListener = messsageListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getID() {
/*  75 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setID(long id) {
/*  83 */     this.m_ID = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     if (this.m_Message != null) {
/*     */       
/*  90 */       StringBuilder result = new StringBuilder();
/*  91 */       result.append("To:");
/*  92 */       result.append(this.m_Message.getMailTo());
/*  93 */       result.append("\n");
/*  94 */       result.append("From:");
/*  95 */       result.append(this.m_Message.getMailFrom());
/*  96 */       result.append("\n");
/*  97 */       result.append("Subject:");
/*  98 */       result.append(this.m_Message.getSubject());
/*     */     } 
/* 100 */     return "JLbsSMTPQueueMessage";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\JLbsSMTPQueueMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */