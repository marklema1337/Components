/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.smtp.ILbsSMTPMessageListener;
/*     */ import com.lbs.smtp.JLbsSMTPConnectionInfo;
/*     */ import com.lbs.smtp.JLbsSMTPSendQueue;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMailStyledTemplateBase
/*     */ {
/*     */   private JLbsMailBinaryContent m_BinaryMailContent;
/*     */   private String m_ContentType;
/*     */   private JLbsSMTPConnectionInfo m_ConInfo;
/*     */   private ByteArrayOutputStream m_Stream;
/*     */   private Writer m_Writer;
/*     */   private String m_Title;
/*     */   
/*     */   public JLbsMailStyledTemplateBase(String m_ContentType) {
/*  29 */     this.m_ContentType = m_ContentType;
/*  30 */     this.m_ConInfo = new JLbsSMTPConnectionInfo();
/*  31 */     this.m_Stream = new ByteArrayOutputStream();
/*  32 */     this.m_Writer = new OutputStreamWriter(this.m_Stream);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContentType() {
/*  37 */     return this.m_ContentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContentType(String m_ContentType) {
/*  42 */     this.m_ContentType = m_ContentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  47 */     return this.m_Title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String m_Title) {
/*  52 */     this.m_Title = m_Title;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsSMTPConnectionInfo getConInfo() {
/*  57 */     return this.m_ConInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConInfo(JLbsSMTPConnectionInfo m_ConInfo) {
/*  62 */     this.m_ConInfo = m_ConInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void write(String s) {
/*     */     try {
/*  69 */       if (this.m_Writer != null) {
/*  70 */         this.m_Writer.write(s);
/*     */       }
/*  72 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeln(String s) {
/*  79 */     write(String.valueOf(s) + "\n");
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginDocment(String backGroundPath) {
/*  84 */     writeln("<html>");
/*  85 */     writeln("\t<head>");
/*  86 */     writeln("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=base64\">");
/*  87 */     String title = JLbsStringUtil.isEmpty(this.m_Title) ? 
/*  88 */       "Untitled Mail" : 
/*  89 */       this.m_Title;
/*  90 */     writeln("<title>" + title + "</title>");
/*  91 */     writeln("</head>");
/*  92 */     String body = "<body id=\"d\" style=\"margin: 0px;\"";
/*  93 */     if (!JLbsStringUtil.isEmpty(backGroundPath)) {
/*  94 */       body = String.valueOf(body) + " background=\"" + backGroundPath + "\">";
/*     */     } else {
/*  96 */       body = String.valueOf(body) + ">";
/*  97 */     }  writeln("\t" + body);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addImage(String imagePath) {
/* 102 */     if (!JLbsStringUtil.isEmpty(imagePath)) {
/* 103 */       writeln("<img src=\"" + imagePath + "\">");
/*     */     }
/*     */   }
/*     */   
/*     */   public void addText(String text) {
/* 108 */     addStyledText(text, "", "", "", -1, false, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStyledText(String text, String font, String align, String color, int size, boolean isBold, boolean isItalic, boolean isUnderlined) {
/* 114 */     if (JLbsStringUtil.isEmpty(text))
/*     */       return; 
/* 116 */     String spanTag = "<span";
/* 117 */     if (!JLbsStringUtil.isEmpty(color) || !JLbsStringUtil.isEmpty(font) || (size != -1 && size != 0)) {
/*     */       
/* 119 */       spanTag = String.valueOf(spanTag) + " style=\"";
/* 120 */       if (!JLbsStringUtil.isEmpty(color))
/* 121 */         spanTag = String.valueOf(spanTag) + "color: " + color + ";"; 
/* 122 */       if (!JLbsStringUtil.isEmpty(font))
/* 123 */         spanTag = String.valueOf(spanTag) + "font-family: " + font + ";"; 
/* 124 */       if (size != -1 && size != 0) {
/*     */         
/* 126 */         double sizeRatio = size / 16.0D;
/* 127 */         spanTag = String.valueOf(spanTag) + "font-size: " + sizeRatio + ";";
/*     */       } 
/* 129 */       spanTag = String.valueOf(spanTag) + "\"";
/*     */     } 
/* 131 */     spanTag = String.valueOf(spanTag) + ">";
/* 132 */     if (isUnderlined)
/* 133 */       spanTag = String.valueOf(spanTag) + "<u>"; 
/* 134 */     if (isItalic)
/* 135 */       spanTag = String.valueOf(spanTag) + "<i>"; 
/* 136 */     if (isBold)
/* 137 */       spanTag = String.valueOf(spanTag) + "<b>"; 
/* 138 */     if (!JLbsStringUtil.isEmpty(align)) {
/* 139 */       spanTag = String.valueOf(spanTag) + "<p style=\"text-align: " + align + ";\">";
/*     */     }
/* 141 */     spanTag = String.valueOf(spanTag) + text;
/*     */     
/* 143 */     if (isUnderlined)
/* 144 */       spanTag = String.valueOf(spanTag) + "</u>"; 
/* 145 */     if (isItalic)
/* 146 */       spanTag = String.valueOf(spanTag) + "</i>"; 
/* 147 */     if (isBold)
/* 148 */       spanTag = String.valueOf(spanTag) + "</b>"; 
/* 149 */     if (!JLbsStringUtil.isEmpty(align)) {
/* 150 */       spanTag = String.valueOf(spanTag) + "</p>";
/*     */     }
/* 152 */     spanTag = String.valueOf(spanTag) + "</span>";
/* 153 */     writeln("\t\t" + spanTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] finalizeDocument(boolean justGet, long id, ILbsSMTPMessageListener listener) {
/* 159 */     writeln("\t</body>");
/* 160 */     writeln("</html>");
/* 161 */     if (this.m_Stream == null)
/* 162 */       return null; 
/* 163 */     byte[] data = this.m_Stream.toByteArray();
/* 164 */     if (justGet)
/* 165 */       return data; 
/* 166 */     postMail(data, id, listener);
/* 167 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postMail(byte[] data, long id, ILbsSMTPMessageListener listener) {
/* 172 */     if (data == null || data.length == 0) {
/*     */       return;
/*     */     }
/*     */     try {
/* 176 */       JLbsMailMessage message = new JLbsMailMessage();
/* 177 */       JLbsMailMultipartContent content = new JLbsMailMultipartContent();
/* 178 */       this.m_BinaryMailContent = new JLbsMailBinaryContent(this.m_ContentType, data);
/* 179 */       this.m_BinaryMailContent.setInline(true);
/* 180 */       content.addContent(0, this.m_BinaryMailContent);
/* 181 */       message.setMailContent(content);
/* 182 */       if (listener == null) {
/* 183 */         JLbsSMTPSendQueue.sendMailDirect(this.m_ConInfo, message);
/*     */       } else {
/* 185 */         JLbsSMTPSendQueue.sendMail(this.m_ConInfo, message, id, listener);
/*     */       } 
/* 187 */     } catch (Throwable e) {
/*     */       
/* 189 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailStyledTemplateBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */