/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.controls.datedit.JLbsDateFormatter;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
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
/*     */ 
/*     */ 
/*     */ public class JLbsMailMessage
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLbsMailAddress m_MailFrom;
/*  32 */   private JLbsMailAddressList m_MailTo = new JLbsMailAddressList();
/*  33 */   private JLbsMailAddressList m_MailToCC = new JLbsMailAddressList();
/*  34 */   private JLbsMailAddressList m_MailToBCC = new JLbsMailAddressList();
/*     */   private JLbsMailContent m_MailContent;
/*     */   private String m_Subject;
/*     */   private Calendar m_Date;
/*     */   
/*     */   public boolean setMailFrom(String address) {
/*     */     try {
/*  41 */       JLbsMailAddress c = new JLbsMailAddress(address);
/*  42 */       this.m_MailFrom = c;
/*  43 */       return true;
/*     */     }
/*  45 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  48 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setMailFrom(String name, String address) {
/*     */     try {
/*  55 */       JLbsMailAddress c = new JLbsMailAddress(name, address);
/*  56 */       this.m_MailFrom = c;
/*  57 */       return true;
/*     */     }
/*  59 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  62 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public JLbsMailAddress getMailFrom() {
/*  67 */     return this.m_MailFrom;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMailTo(JLbsMailAddressList list) {
/*  72 */     boolean result = (list != null);
/*  73 */     if (result)
/*     */     {
/*  75 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/*  77 */         JLbsMailAddress adr = list.getAddress(i);
/*  78 */         if (adr != null)
/*  79 */           this.m_MailTo.add(adr); 
/*     */       } 
/*     */     }
/*  82 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMailToCC(JLbsMailAddressList list) {
/*  87 */     boolean result = (list != null);
/*  88 */     if (result)
/*     */     {
/*  90 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/*  92 */         JLbsMailAddress adr = list.getAddress(i);
/*  93 */         if (adr != null)
/*  94 */           this.m_MailToCC.add(adr); 
/*     */       } 
/*     */     }
/*  97 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMailToBCC(JLbsMailAddressList list) {
/* 102 */     boolean result = (list != null);
/* 103 */     if (result)
/*     */     {
/* 105 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 107 */         JLbsMailAddress adr = list.getAddress(i);
/* 108 */         if (adr != null)
/* 109 */           this.m_MailToBCC.add(adr); 
/*     */       } 
/*     */     }
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addMailTo(String name, String address) {
/*     */     try {
/* 119 */       JLbsMailAddress c = new JLbsMailAddress(name, address);
/* 120 */       this.m_MailTo.add(c);
/* 121 */       return true;
/*     */     }
/* 123 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 126 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public JLbsMailAddressList getMailTo() {
/* 131 */     return this.m_MailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addMailToCC(String name, String address) {
/*     */     try {
/* 138 */       JLbsMailAddress c = new JLbsMailAddress(name, address);
/* 139 */       this.m_MailToCC.add(c);
/* 140 */       return true;
/*     */     }
/* 142 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 145 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public JLbsMailAddressList getMailToCC() {
/* 150 */     return this.m_MailToCC;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addMailToBCC(String name, String address) {
/*     */     try {
/* 157 */       JLbsMailAddress c = new JLbsMailAddress(name, address);
/* 158 */       this.m_MailToBCC.add(c);
/* 159 */       return true;
/*     */     }
/* 161 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 164 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public JLbsMailAddressList getMailToBCC() {
/* 169 */     return this.m_MailToBCC;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMailContent getMailContent() {
/* 174 */     return this.m_MailContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailContentText(String string) {
/* 179 */     this.m_MailContent = new JLbsMailTextContent(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailContentHTML(String html) {
/* 184 */     this.m_MailContent = new JLbsMailHTMLContent(html);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailContent(JLbsMailContent content) {
/* 189 */     this.m_MailContent = content;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubject() {
/* 194 */     return this.m_Subject;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubject(String string) {
/* 199 */     this.m_Subject = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getDate() {
/* 204 */     return this.m_Date;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar calendar) {
/* 209 */     this.m_Date = calendar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMailHeaders() {
/* 214 */     StringBuilder buff = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (this.m_Date == null)
/* 224 */       this.m_Date = new GregorianCalendar(); 
/* 225 */     buff.append("Date:");
/* 226 */     JLbsDateFormatter formatter = new JLbsDateFormatter("dd MMM yy hh:mm:ss");
/* 227 */     buff.append(formatter.format(this.m_Date.getTime()));
/* 228 */     buff.append("\r\n");
/* 229 */     if (this.m_MailFrom != null) {
/*     */       
/* 231 */       buff.append("From: ");
/* 232 */       if (this.m_MailFrom.getName() != null) {
/*     */         
/* 234 */         buff.append(this.m_MailFrom.getName());
/* 235 */         buff.append(" ");
/*     */       } 
/* 237 */       buff.append("<");
/* 238 */       buff.append(this.m_MailFrom.getMailAddress());
/* 239 */       buff.append(">");
/* 240 */       buff.append("\r\n");
/*     */     } 
/* 242 */     buff.append("Subject: ");
/* 243 */     if (this.m_Subject != null)
/* 244 */       buff.append(this.m_Subject); 
/* 245 */     buff.append("\r\n");
/*     */     
/* 247 */     buff.append("To:");
/* 248 */     buff.append(getAddresList(this.m_MailTo));
/* 249 */     buff.append("\r\n");
/* 250 */     String cc = getAddresList(this.m_MailToCC);
/* 251 */     if (!JLbsStringUtil.isEmpty(cc)) {
/*     */       
/* 253 */       buff.append("Cc:");
/* 254 */       buff.append(cc);
/* 255 */       buff.append("\r\n");
/*     */     } 
/* 257 */     String ch = (this.m_MailContent != null) ? 
/* 258 */       this.m_MailContent.getHeaders() : 
/* 259 */       null;
/* 260 */     if (ch != null)
/* 261 */       buff.append(ch); 
/* 262 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getAddresList(JLbsMailAddressList list) {
/* 267 */     StringBuilder buff = new StringBuilder();
/* 268 */     if (list != null)
/*     */     {
/* 270 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 272 */         if (i > 0)
/* 273 */           buff.append("; "); 
/* 274 */         JLbsMailAddress address = list.getAddress(i);
/* 275 */         if (address != null) {
/*     */           
/* 277 */           if (address.getName() != null) {
/*     */             
/* 279 */             buff.append(address.getName());
/* 280 */             buff.append(" ");
/*     */           } 
/* 282 */           buff.append(address.getMailAddress());
/*     */         } 
/*     */       }  } 
/* 285 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailTo(JLbsMailAddressList mailTo) {
/* 290 */     this.m_MailTo = mailTo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailToBCC(JLbsMailAddressList mailToBCC) {
/* 295 */     this.m_MailToBCC = mailToBCC;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMailToCC(JLbsMailAddressList mailToCC) {
/* 300 */     this.m_MailToCC = mailToCC;
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateEmailMessage() throws Exception {
/* 305 */     if (this.m_MailFrom == null) {
/*     */       
/* 307 */       String exceptionMessage = JLbsLocalizer.getString("UN", 12161, 1);
/* 308 */       if (exceptionMessage == null)
/* 309 */         exceptionMessage = "From e-mail address is empty!"; 
/* 310 */       throw new Exception(exceptionMessage);
/*     */     } 
/* 312 */     if (this.m_MailTo == null || this.m_MailTo.size() == 0) {
/*     */       
/* 314 */       String exceptionMessage = JLbsLocalizer.getString("UN", 12161, 2);
/* 315 */       if (exceptionMessage == null)
/* 316 */         exceptionMessage = "To e-mail address list is null or empty!"; 
/* 317 */       throw new Exception(exceptionMessage);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */