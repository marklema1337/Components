/*     */ package com.lbs.smtp;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.mail.JLbsMailMessage;
/*     */ import com.lbs.mail.JLbsWorkQueue;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsSMTPSendWorker
/*     */   extends Thread
/*     */   implements ILbsMailWorker
/*     */ {
/*     */   private JLbsWorkQueue m_WorkQueue;
/*  20 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(JLbsSMTPSendWorker.class);
/*     */ 
/*     */   
/*     */   public JLbsSMTPSendWorker(JLbsWorkQueue queue) {
/*  24 */     this.m_WorkQueue = queue;
/*  25 */     setName("JLbsSTMPSendWorker-1");
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsSMTP getSMTP(JLbsSMTPConnectionInfo conInfo) {
/*  30 */     JLbsSMTP smtp = null;
/*  31 */     int tryCount = 0;
/*  32 */     while (tryCount < 4) {
/*     */ 
/*     */       
/*     */       try {
/*  36 */         Thread.sleep((tryCount * 2000));
/*  37 */         tryCount++;
/*  38 */         smtp = new JLbsSMTP(conInfo.getServer());
/*  39 */         smtp.cmdHelo("LbsSTMPSendWorker");
/*  40 */         if (conInfo.isAuthentication())
/*  41 */           smtp.cmdAuth(conInfo.getUsername(), conInfo.getPassword()); 
/*  42 */         tryCount = 5;
/*     */       }
/*  44 */       catch (Exception e) {
/*     */         
/*  46 */         if (smtp != null) {
/*     */           
/*  48 */           smtp.close();
/*  49 */           smtp = null;
/*     */         } 
/*  51 */         String error = e.getMessage();
/*  52 */         if (error != null && error.toLowerCase().indexOf("authentication") >= 0)
/*  53 */           tryCount = 5; 
/*  54 */         ms_Logger.error("getSMTP", e);
/*     */       } 
/*     */     } 
/*  57 */     return smtp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*     */       while (true) {
/*  66 */         Object work = this.m_WorkQueue.getWork();
/*  67 */         if (work instanceof JLbsSMTPQueueMessage) {
/*     */           
/*  69 */           JLbsSMTPQueueMessage queueMessage = (JLbsSMTPQueueMessage)work;
/*  70 */           JLbsSMTPConnectionInfo conInfo = queueMessage.getConnectionInfo();
/*  71 */           JLbsMailMessage mail = queueMessage.getMessage();
/*  72 */           ILbsSMTPMessageListener listener = queueMessage.getMesssageListener();
/*  73 */           if (conInfo != null && mail != null) {
/*     */             
/*  75 */             boolean serverDefined = true;
/*  76 */             if (JLbsStringUtil.isEmpty(conInfo.getServer())) {
/*     */               
/*  78 */               serverDefined = false;
/*  79 */               if (listener != null)
/*  80 */                 listener.messageFailed(queueMessage, "Server undefined"); 
/*     */             } 
/*  82 */             JLbsSMTP smtp = serverDefined ? 
/*  83 */               getSMTP(conInfo) : 
/*  84 */               null;
/*  85 */             if (smtp != null) {
/*     */               
/*     */               try {
/*     */                 
/*  89 */                 Thread.sleep(1500L);
/*  90 */                 smtp.send(mail);
/*  91 */                 if (listener != null)
/*  92 */                   listener.messageSent(queueMessage); 
/*  93 */                 Thread.sleep(1000L);
/*     */               }
/*  95 */               catch (Exception e) {
/*     */                 
/*  97 */                 ms_Logger.error("SMTP send failed " + mail.getSubject());
/*  98 */                 ms_Logger.error("smtp Send", e);
/*     */ 
/*     */                 
/* 101 */                 smtp.close();
/* 102 */                 smtp = null;
/*     */                 
/* 104 */                 if (listener != null) {
/* 105 */                   listener.messageFailed(queueMessage, e.getMessage());
/*     */                 }
/*     */                 continue;
/*     */               } finally {
/* 109 */                 if (smtp != null) {
/* 110 */                   smtp.close();
/*     */                 }
/*     */               }
/*     */             
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 118 */     } catch (InterruptedException interruptedException) {
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMailDirect(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail) throws Throwable {
/* 125 */     if (conInfo == null) {
/* 126 */       throw new Exception("Connection information missing");
/*     */     }
/* 128 */     if (mail == null) {
/* 129 */       throw new Exception("Invalid mail message");
/*     */     }
/* 131 */     if (JLbsStringUtil.isEmpty(conInfo.getServer())) {
/* 132 */       throw new Exception("Server undefined");
/*     */     }
/* 134 */     JLbsSMTP smtp = getSMTP(conInfo);
/* 135 */     if (smtp != null) {
/*     */       
/*     */       try {
/*     */         
/* 139 */         smtp.send(mail);
/*     */       
/*     */       }
/*     */       finally {
/*     */ 
/*     */         
/* 145 */         smtp.close();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMailDirect(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail, boolean allRecipients) throws Throwable {
/* 153 */     sendMailDirect(conInfo, mail);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\JLbsSMTPSendWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */