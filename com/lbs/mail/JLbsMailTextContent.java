/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
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
/*     */ public class JLbsMailTextContent
/*     */   extends JLbsMailContent
/*     */   implements IParameter
/*     */ {
/*     */   private String m_Message;
/*     */   
/*     */   public JLbsMailTextContent() {}
/*     */   
/*     */   public JLbsMailTextContent(String msg) {
/*  28 */     this.m_Message = msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncodedMessage() {
/*  33 */     return refineContent(this.m_Message);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  38 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessage(String string) {
/*  43 */     this.m_Message = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeaders() {
/*  48 */     StringBuilder buffer = new StringBuilder();
/*  49 */     buffer.append("Content-Type: text/plain; charset=\"iso-8859-9\"");
/*  50 */     buffer.append("\r\n");
/*  51 */     buffer.append("Content-Transfer-Encoding: quoted-printable");
/*  52 */     buffer.append("\r\n");
/*  53 */     buffer.append(super.getHeaders());
/*  54 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String refineContent(String content) {
/*  59 */     if (content == null)
/*  60 */       return content; 
/*  61 */     byte[] data = content.getBytes();
/*  62 */     byte aByte = 0;
/*  63 */     StringBuilder buffer = new StringBuilder();
/*  64 */     for (int i = 0; i < data.length; i++) {
/*     */       
/*  66 */       aByte = data[i];
/*  67 */       if (aByte > Byte.MAX_VALUE || aByte < 0) {
/*  68 */         buffer.append(byteToHex(aByte));
/*     */       } else {
/*  70 */         buffer.append((char)(data[i] & 0xFF));
/*     */       } 
/*  72 */     }  return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String byteToHex(byte data) {
/*  78 */     StringBuilder buffer = new StringBuilder();
/*  79 */     buffer.append("=");
/*  80 */     buffer.append(toHexChar(data >>> 4 & 0xF));
/*  81 */     buffer.append(toHexChar(data & 0xF));
/*  82 */     return buffer.toString().toUpperCase();
/*     */   }
/*     */ 
/*     */   
/*     */   private char toHexChar(int i) {
/*  87 */     if (i >= 0 && i <= 9) {
/*  88 */       return (char)(48 + i);
/*     */     }
/*  90 */     return (char)(97 + i - 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/*  97 */     return null;
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
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
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
/* 125 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailTextContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */