/*     */ package com.lbs.smtp;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.util.ArrayList;
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
/*     */ public class JLbsSMTPConnectionInfo
/*     */   implements IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1067473767679447035L;
/*     */   private String m_MailAddress;
/*     */   private String m_MailSubject;
/*     */   private String m_Server;
/*     */   private int m_Port;
/*     */   private String m_Sender;
/*     */   private boolean m_Authentication;
/*     */   private boolean m_OpenTls;
/*     */   private String m_Username;
/*     */   private String m_Password;
/*     */   private String m_ImapServer;
/*     */   private int m_ImapPort;
/*     */   private boolean m_ImapSsl;
/*     */   private String m_ExtraInfo;
/*     */   
/*     */   public boolean isAuthentication() {
/*  45 */     return this.m_Authentication;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthentication(boolean authentication) {
/*  53 */     this.m_Authentication = authentication;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailAddress() {
/*  61 */     return this.m_MailAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailAddress(String mailAddress) {
/*  69 */     this.m_MailAddress = mailAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailSubject() {
/*  77 */     return this.m_MailSubject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailSubject(String mailSubject) {
/*  85 */     this.m_MailSubject = mailSubject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  93 */     return this.m_Password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/* 101 */     this.m_Password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSender() {
/* 109 */     return this.m_Sender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSender(String sender) {
/* 117 */     this.m_Sender = sender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServer() {
/* 125 */     return this.m_Server;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServer(String server) {
/* 133 */     if (JLbsStringUtil.areEqualNoCase(server, "null")) {
/* 134 */       this.m_Server = null;
/*     */     } else {
/* 136 */       this.m_Server = server;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 144 */     return this.m_Username;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUsername(String username) {
/* 152 */     this.m_Username = username;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(int port) {
/* 160 */     this.m_Port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 168 */     return this.m_Port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenTls(boolean openTls) {
/* 176 */     this.m_OpenTls = openTls;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpenTls() {
/* 184 */     return this.m_OpenTls;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getImapServer() {
/* 189 */     return this.m_ImapServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapServer(String imapServer) {
/* 194 */     this.m_ImapServer = imapServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getExtraInfo() {
/* 199 */     return this.m_ExtraInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraInfo(String extraInfo) {
/* 204 */     this.m_ExtraInfo = extraInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImapPort() {
/* 210 */     return this.m_ImapPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapPort(int imapPort) {
/* 215 */     this.m_ImapPort = imapPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImapSsl() {
/* 220 */     return this.m_ImapSsl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImapSsl(boolean imapSsl) {
/* 225 */     this.m_ImapSsl = imapSsl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 260 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\JLbsSMTPConnectionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */