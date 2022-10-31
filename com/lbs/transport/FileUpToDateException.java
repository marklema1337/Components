/*    */ package com.lbs.transport;
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
/*    */ public class FileUpToDateException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private byte[] m_Data;
/*    */   
/*    */   public FileUpToDateException(byte[] data) {
/* 20 */     this.m_Data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 25 */     return this.m_Data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\FileUpToDateException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */