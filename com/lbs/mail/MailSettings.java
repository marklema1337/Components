/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.crypto.JLbsBase64;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsIniProperties;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.IOException;
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
/*     */ public class MailSettings
/*     */ {
/*     */   private static final String FILEPATH = "Settings/SMTPSettings.ini";
/*     */   private JLbsIniProperties m_Props;
/*     */   private IClientContext m_Context;
/*     */   
/*     */   public MailSettings(IClientContext context, boolean bLoadFromFile) {
/*  27 */     if (context == null)
/*     */       return; 
/*  29 */     this.m_Context = context;
/*     */     
/*     */     try {
/*  32 */       this.m_Props = new JLbsIniProperties();
/*  33 */       if (bLoadFromFile) {
/*     */         
/*  35 */         byte[] localSettings = context.loadLocalFile("Settings/SMTPSettings.ini");
/*  36 */         if (localSettings != null) {
/*  37 */           this.m_Props.load(localSettings);
/*     */         }
/*     */       } 
/*  40 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() throws IOException {
/*  47 */     if (this.m_Props != null) {
/*  48 */       this.m_Context.saveLocalFile("Settings/SMTPSettings.ini", this.m_Props.toByteArray(), true, false);
/*     */     }
/*     */   }
/*     */   
/*     */   private String getProperty(String key) {
/*  53 */     return (this.m_Props != null) ? 
/*  54 */       this.m_Props.getProperty(key) : 
/*  55 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setProperty(String key, String value) {
/*  60 */     if (this.m_Props == null)
/*  61 */       this.m_Props = new JLbsIniProperties(); 
/*  62 */     if (value == null)
/*  63 */       value = ""; 
/*  64 */     this.m_Props.setProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getServer() {
/*  69 */     return getProperty("SMTP.server");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getImapServer() {
/*  74 */     return getProperty("IMAP.server");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServer(String server) {
/*  79 */     setProperty("SMTP.server", server);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapServer(String imapServer) {
/*  84 */     setProperty("IMAP.server", imapServer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapSSL(boolean enabled) {
/*  89 */     setProperty("IMAP.usessl", enabled ? 
/*  90 */         "true" : 
/*  91 */         "false");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getImapSsl() {
/*  96 */     return getProperty("IMAP.usessl");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPort(String port) {
/* 101 */     setProperty("SMTP.port", port);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapPort(String imapport) {
/* 106 */     setProperty("IMAP.port", imapport);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPort() {
/* 111 */     return getProperty("SMTP.port");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getImapPort() {
/* 116 */     return getProperty("IMAP.port");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpenTLS(boolean enabled) {
/* 121 */     setProperty("SMTP.opentls", enabled ? 
/* 122 */         "true" : 
/* 123 */         "false");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOpenTls() {
/* 128 */     return getProperty("SMTP.opentls");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 133 */     return getProperty("SMTP.user");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsername(String value) {
/* 138 */     setProperty("SMTP.user", value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 143 */     return JLbsBase64.decodeString(getProperty("SMTP.pass"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String value) {
/* 148 */     setProperty("SMTP.pass", JLbsBase64.encodeString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSenderName() {
/* 153 */     return getProperty("Mail.sendername");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSenderName(String value) {
/* 158 */     setProperty("Mail.sendername", value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSenderAddress() {
/* 163 */     return getProperty("Mail.senderaddress");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSenderAddress(String value) {
/* 168 */     setProperty("Mail.senderaddress", value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(StringBuilder buffer) {
/* 173 */     buffer.append("server:");
/* 174 */     String server = getServer();
/* 175 */     if (JLbsStringUtil.isEmpty(server))
/* 176 */       server = "localhost"; 
/* 177 */     buffer.append(server);
/* 178 */     buffer.append(";from:");
/* 179 */     String senderName = getSenderName();
/* 180 */     if (!JLbsStringUtil.isEmpty(senderName))
/* 181 */       buffer.append(senderName); 
/* 182 */     String senderAddress = getSenderAddress();
/* 183 */     if (JLbsStringUtil.isEmpty(senderName))
/* 184 */       senderAddress = "nobody@nowhere.com"; 
/* 185 */     buffer.append(" <");
/* 186 */     buffer.append(senderAddress);
/* 187 */     buffer.append(">");
/* 188 */     String username = getUsername();
/* 189 */     if (!JLbsStringUtil.isEmpty(username)) {
/*     */       
/* 191 */       buffer.append(";auth:true;user:");
/* 192 */       buffer.append(username);
/* 193 */       buffer.append(";pass:");
/* 194 */       String pass = getPassword();
/* 195 */       if (pass != null)
/* 196 */         buffer.append(pass); 
/*     */     } 
/* 198 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\MailSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */