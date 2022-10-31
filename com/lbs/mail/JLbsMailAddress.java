/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Serializable;
/*     */ import java.security.InvalidParameterException;
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
/*     */ public class JLbsMailAddress
/*     */   implements Serializable, IParameter, EmailDefinitionConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("MailAddress")
/*     */   protected String m_MailAddress;
/*     */   @XStreamAlias("Name")
/*     */   private String m_Name;
/*     */   
/*     */   public JLbsMailAddress() {}
/*     */   
/*     */   public JLbsMailAddress(String mailAddress) throws InvalidParameterException {
/*  39 */     String[] tokens = mailAddress.split("<", 2);
/*  40 */     if (tokens.length == 2) {
/*     */       
/*  42 */       int index = tokens[1].indexOf('>');
/*  43 */       if (index >= 0)
/*  44 */         tokens[1] = tokens[1].substring(0, index); 
/*  45 */       setAddress(tokens[1]);
/*  46 */       this.m_Name = tokens[0];
/*     */     }
/*     */     else {
/*     */       
/*  50 */       setAddress(mailAddress);
/*  51 */       if (JLbsStringUtil.isEmpty(mailAddress)) {
/*  52 */         this.m_Name = "automail";
/*     */       } else {
/*     */         
/*  55 */         String[] address = mailAddress.split("@");
/*  56 */         if (address != null && address.length > 0) {
/*  57 */           this.m_Name = address[0];
/*     */         } else {
/*  59 */           this.m_Name = "automail";
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public JLbsMailAddress(String Name, String mailAddress) throws InvalidParameterException {
/*  66 */     setAddress(mailAddress);
/*  67 */     setName(Name);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmailDefinitionId() {
/*  72 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMailAddress() {
/*  77 */     return this.m_MailAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAddress(String string) throws InvalidParameterException {
/*  82 */     this.m_MailAddress = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  87 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String string) {
/*  92 */     this.m_Name = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String calculateMailAddress(IClientContext context, JLbsMailInputObjects inputObjects) {
/*  97 */     return this.m_MailAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringRepresentation() {
/* 102 */     return this.m_MailAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringRepresentation(String value) {
/* 107 */     this.m_MailAddress = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 112 */     StringBuilder buffer = new StringBuilder();
/* 113 */     buffer.append((this.m_Name == null) ? "automail" : this.m_Name);
/* 114 */     buffer.append(" <");
/* 115 */     buffer.append(this.m_MailAddress);
/* 116 */     buffer.append(">");
/* 117 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 123 */     writer.startObject(getClass().getName(), "");
/* 124 */     describePropertiesXML(writer, localizationService);
/* 125 */     writer.endObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 131 */     writer.writeProperty("MailAddress", "String", "", false);
/* 132 */     writer.writeProperty("Name", "String", "", false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 138 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 149 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */