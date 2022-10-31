/*    */ package com.lbs.util;
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
/*    */ public class XMLNodeInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_FileName;
/*    */   private int m_LineNumber;
/*    */   private int m_ColumnNumber;
/*    */   private int m_CharOffset;
/*    */   
/*    */   public XMLNodeInfo(String fileName, int lineNumber) {
/* 25 */     this.m_FileName = fileName;
/* 26 */     this.m_LineNumber = lineNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFileName() {
/* 31 */     return this.m_FileName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLineNumber() {
/* 36 */     return this.m_LineNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return this.m_FileName + ":" + this.m_LineNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCharOffset() {
/* 46 */     return this.m_CharOffset;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharOffset(int charOffset) {
/* 51 */     this.m_CharOffset = charOffset;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnNumber() {
/* 56 */     return this.m_ColumnNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColumnNumber(int columnNumber) {
/* 61 */     this.m_ColumnNumber = columnNumber;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XMLNodeInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */