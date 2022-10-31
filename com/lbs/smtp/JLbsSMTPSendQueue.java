/*     */ package com.lbs.smtp;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.mail.JLbsMailMessage;
/*     */ import com.lbs.mail.JLbsWorkQueue;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class JLbsSMTPSendQueue
/*     */ {
/*     */   private static JLbsWorkQueue m_MailQueue;
/*     */   private static Thread m_Worker;
/*  25 */   private static Pattern ms_Pattern = Pattern.compile("[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?");
/*     */ 
/*     */   
/*     */   static {
/*  29 */     m_MailQueue = new JLbsWorkQueue();
/*     */     
/*     */     try {
/*  32 */       Class<?> clazz = Class.forName("com.lbs.util.JLbsJavaMailWorker");
/*  33 */       if (clazz != null) {
/*     */         
/*  35 */         Constructor<?> constructor = clazz.getConstructor(new Class[] { JLbsWorkQueue.class });
/*  36 */         if (constructor != null) {
/*  37 */           m_Worker = (Thread)constructor.newInstance(new Object[] { m_MailQueue });
/*     */         }
/*     */       } 
/*  40 */     } catch (Throwable t) {
/*     */       
/*  42 */       LbsConsole.getLogger(JLbsSMTPSendQueue.class).warn("No java mail support", t);
/*     */     } 
/*  44 */     if (m_Worker == null)
/*  45 */       m_Worker = new JLbsSMTPSendWorker(m_MailQueue); 
/*  46 */     m_Worker.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValidEmail(String emailAddress) {
/*  51 */     Matcher m = ms_Pattern.matcher(emailAddress);
/*  52 */     return m.matches();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMail(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail) {
/*  57 */     sendMail(conInfo, mail, 0L, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMail(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail, long id, ILbsSMTPMessageListener listener) {
/*  62 */     if (mail != null && conInfo != null) {
/*     */       
/*  64 */       JLbsSMTPQueueMessage message = new JLbsSMTPQueueMessage(mail, conInfo);
/*  65 */       message.setID(id);
/*  66 */       message.setMesssageListener(listener);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       if (mail.getMailTo() == null) {
/*     */         
/*  78 */         reportError(listener, message, "Invalid mail to list (null)");
/*     */         return;
/*     */       } 
/*  81 */       String[] mailToList = mail.getMailTo().toSimpleAdressArray();
/*  82 */       if (mailToList == null || mailToList.length == 0) {
/*     */         
/*  84 */         reportError(listener, message, "Invalid mail to list (empty)");
/*     */         return;
/*     */       } 
/*  87 */       for (int i = 0; i < mailToList.length; i++) {
/*     */         
/*  89 */         if (!isValidEmail(mailToList[i])) {
/*     */           
/*  91 */           reportError(listener, message, "Invalid mail to address : " + mailToList[i]);
/*     */           return;
/*     */         } 
/*     */       } 
/*  95 */       m_MailQueue.addWork(message);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMailDirect(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail) throws Throwable {
/* 101 */     sendMailDirect(conInfo, mail, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMailDirect(JLbsSMTPConnectionInfo conInfo, JLbsMailMessage mail, boolean allRecepients) throws Throwable {
/* 106 */     Thread smtpWorker = null;
/*     */     
/*     */     try {
/* 109 */       Class<?> clazz = Class.forName("com.lbs.util.JLbsJavaMailWorker");
/* 110 */       if (clazz != null) {
/*     */         
/* 112 */         Constructor<?> constructor = clazz.getConstructor(new Class[] { JLbsWorkQueue.class });
/* 113 */         if (constructor != null) {
/* 114 */           smtpWorker = (Thread)constructor.newInstance(new Object[1]);
/*     */         }
/*     */       } 
/* 117 */     } catch (Throwable t) {
/*     */       
/* 119 */       LbsConsole.getLogger(JLbsSMTPSendQueue.class).warn("No java mail support", t);
/*     */     } 
/* 121 */     if (smtpWorker == null) {
/* 122 */       smtpWorker = new JLbsSMTPSendWorker(null);
/*     */     }
/* 124 */     if (!allRecepients) {
/* 125 */       ((ILbsMailWorker)smtpWorker).sendMailDirect(conInfo, mail);
/*     */     } else {
/* 127 */       ((ILbsMailWorker)smtpWorker).sendMailDirect(conInfo, mail, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void reportError(ILbsSMTPMessageListener listener, JLbsSMTPQueueMessage message, String error) {
/* 136 */     if (listener != null) {
/* 137 */       listener.messageFailed(message, error);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 143 */     System.out.println(isValidEmail("xyz @ali.com"));
/* 144 */     System.out.println(isValidEmail("xyzA98@ali.com"));
/* 145 */     System.out.println(isValidEmail("AAA@veli.com"));
/* 146 */     System.out.println(isValidEmail("1A0xbyA@ali.com"));
/* 147 */     System.out.println(isValidEmail("ali.com"));
/* 148 */     System.out.println(isValidEmail("AAA@XYZ.com"));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\JLbsSMTPSendQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */