/*    */ package com.lbs.mail;
/*    */ 
/*    */ import com.lbs.crypto.JLbsBase64;
/*    */ import com.lbs.interfaces.IParameter;
/*    */ import java.io.Serializable;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class JLbsMailContent
/*    */   implements Serializable, IParameter
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_ContentID;
/* 22 */   private static Random ms_Random = new Random();
/*    */ 
/*    */   
/*    */   public String getHeaders() {
/* 26 */     StringBuilder buffer = new StringBuilder();
/* 27 */     if (this.m_ContentID != null) {
/*    */       
/* 29 */       buffer.append("Content-ID:<");
/* 30 */       buffer.append(this.m_ContentID);
/* 31 */       buffer.append(">\r\n");
/*    */     } 
/* 33 */     buffer.append("\r\n");
/* 34 */     return buffer.toString();
/*    */   }
/*    */   public abstract String getEncodedMessage();
/*    */   public static String generateRandomString() {
/* 38 */     StringBuilder buff = new StringBuilder();
/* 39 */     byte[] data = new byte[20];
/* 40 */     ms_Random.nextBytes(data);
/* 41 */     buff.append(JLbsBase64.encodeAsString(data));
/* 42 */     return buff.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void generateContentID() {
/* 47 */     this.m_ContentID = generateRandomString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getContentID() {
/* 52 */     return this.m_ContentID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */