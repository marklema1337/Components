/*    */ package com.lbs.transport;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsCachedData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Version;
/*    */   private byte[] m_Data;
/*    */   
/*    */   public void setData(byte[] data) {
/* 35 */     this.m_Data = data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsCachedData(String versionTag, byte[] data) {
/* 46 */     this.m_Version = versionTag;
/* 47 */     this.m_Data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 52 */     return this.m_Data;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 57 */     return this.m_Version;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVersion(String version) {
/* 62 */     this.m_Version = version;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\JLbsCachedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */