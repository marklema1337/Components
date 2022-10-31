/*    */ package com.lbs.mail;
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
/*    */ public class JLbsMailHTMLContent
/*    */   extends JLbsMailTextContent
/*    */ {
/*    */   public JLbsMailHTMLContent() {}
/*    */   
/*    */   public JLbsMailHTMLContent(String msg) {
/* 18 */     super(msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHeaders() {
/* 23 */     StringBuilder buffer = new StringBuilder();
/* 24 */     buffer.append("Content-Type: text/html\r\n");
/*    */     
/* 26 */     String sHeader = super.getHeaders();
/* 27 */     sHeader = sHeader.replaceAll("Content-Type: text/plain", "");
/*    */     
/* 29 */     buffer.append(sHeader);
/* 30 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailHTMLContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */