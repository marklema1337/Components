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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMailMultipartContent
/*     */   extends JLbsMailContent
/*     */   implements IParameter
/*     */ {
/*  26 */   private String m_MessageBoundary = generateRandomString();
/*  27 */   private ArrayList m_Parts = new ArrayList();
/*     */   
/*     */   private boolean m_Attachment;
/*     */   
/*     */   public String getHeaders() {
/*  32 */     StringBuilder buff = new StringBuilder("MIME-Version: 1.0\r\nContent-Type:multipart/");
/*  33 */     buff.append(getMultipartType());
/*  34 */     buff.append("; boundary=\"");
/*  35 */     buff.append(this.m_MessageBoundary);
/*  36 */     buff.append("\"");
/*  37 */     if (this.m_Attachment) {
/*     */       
/*  39 */       buff.append("\r\nContent-Disposition: ");
/*  40 */       buff.append("attachment");
/*     */     } 
/*  42 */     buff.append("\r\n");
/*  43 */     return buff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodedMessage() {
/*  49 */     StringBuilder buff = new StringBuilder();
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
/*  64 */     for (int i = 0; i < this.m_Parts.size(); i++) {
/*     */       
/*  66 */       buff.append("\r\n--");
/*  67 */       buff.append(this.m_MessageBoundary);
/*  68 */       buff.append("\r\n");
/*  69 */       JLbsMailContent content = this.m_Parts.get(i);
/*  70 */       String hdr = content.getHeaders();
/*  71 */       if (hdr != null)
/*  72 */         buff.append(hdr); 
/*  73 */       buff.append(content.getEncodedMessage());
/*     */     } 
/*     */ 
/*     */     
/*  77 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addContent(JLbsMailContent content) {
/*  82 */     if (content != null) {
/*  83 */       this.m_Parts.add(content);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addContent(int index, JLbsMailContent content) {
/*  88 */     if (content != null) {
/*  89 */       this.m_Parts.add(index, content);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String getMultipartType() {
/*  94 */     return "mixed";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAttachment() {
/*  99 */     return this.m_Attachment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttachment(boolean b) {
/* 104 */     this.m_Attachment = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMailContent getContent(int idx) {
/* 109 */     if (this.m_Parts != null && this.m_Parts.size() > idx)
/* 110 */       return this.m_Parts.get(idx); 
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 116 */     return (this.m_Parts != null) ? 
/* 117 */       this.m_Parts.size() : 
/* 118 */       0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 125 */     return null;
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
/* 153 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailMultipartContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */