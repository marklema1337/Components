/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.crypto.JLbsBase64;
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMailBinaryContent
/*     */   extends JLbsMailContent
/*     */   implements IParameter
/*     */ {
/*     */   private String m_ContentType;
/*     */   private String m_FileName;
/*     */   private byte[] m_Data;
/*     */   private boolean m_IsInline;
/*     */   
/*     */   public JLbsMailBinaryContent(String contentType) {
/*  30 */     this.m_ContentType = contentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMailBinaryContent(String contentType, byte[] data) {
/*  35 */     this.m_ContentType = contentType;
/*  36 */     this.m_Data = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeaders() {
/*  41 */     StringBuilder buff = new StringBuilder();
/*  42 */     if (this.m_ContentType != null) {
/*     */       
/*  44 */       buff.append("Content-Type: ");
/*  45 */       buff.append(this.m_ContentType);
/*  46 */       buff.append("\r\n");
/*     */     } 
/*  48 */     buff.append("Content-Transfer-Encoding: base64\r\n");
/*  49 */     buff.append("Content-Disposition: ");
/*  50 */     buff.append(this.m_IsInline ? "inline" : "attachment");
/*     */     
/*  52 */     if (this.m_FileName != null) {
/*     */       
/*  54 */       buff.append("; filename=");
/*  55 */       buff.append(this.m_FileName);
/*     */     } 
/*  57 */     buff.append("\r\n");
/*  58 */     buff.append(super.getHeaders());
/*  59 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncodedMessage() {
/*  64 */     if (this.m_Data != null) {
/*     */       
/*  66 */       StringBuilder buff = new StringBuilder();
/*  67 */       ByteArrayInputStream inStream = new ByteArrayInputStream(this.m_Data);
/*  68 */       byte[] buffer = new byte[30];
/*     */       int read;
/*  70 */       while ((read = inStream.read(buffer, 0, buffer.length)) > 0) {
/*     */         
/*  72 */         if (read == buffer.length) {
/*  73 */           buff.append(JLbsBase64.encodeAsString(buffer));
/*     */         } else {
/*     */           
/*  76 */           byte[] data = RttiUtil.copyByteArray(buffer, 0, read);
/*  77 */           buff.append(JLbsBase64.encodeAsString(data));
/*     */         } 
/*  79 */         buff.append("\r\n");
/*     */       } 
/*  81 */       return buff.toString();
/*     */     } 
/*  83 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContentType() {
/*  88 */     return this.m_ContentType;
/*     */   }
/*     */   
/*     */   public String getFileName() {
/*  92 */     return this.m_FileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileName(String string) {
/*     */     try {
/*  98 */       File file = new File(string);
/*  99 */       this.m_FileName = file.getName();
/*     */     }
/* 101 */     catch (Exception e) {
/*     */       
/* 103 */       this.m_FileName = string;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] getData() {
/* 108 */     return this.m_Data;
/*     */   }
/*     */   
/*     */   public void setData(byte[] bs) {
/* 112 */     this.m_Data = bs;
/*     */   }
/*     */   
/*     */   public void setData(String s) {
/* 116 */     if (s != null) {
/* 117 */       setData(s.getBytes());
/*     */     } else {
/* 119 */       this.m_Data = null;
/*     */     } 
/*     */   }
/*     */   public boolean isInline() {
/* 123 */     return this.m_IsInline;
/*     */   }
/*     */   
/*     */   public void setInline(boolean b) {
/* 127 */     this.m_IsInline = b;
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
/* 148 */     return null;
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
/* 162 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailBinaryContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */