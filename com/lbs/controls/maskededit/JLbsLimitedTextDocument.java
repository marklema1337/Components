/*    */ package com.lbs.controls.maskededit;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
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
/*    */ public class JLbsLimitedTextDocument
/*    */   extends PlainDocument
/*    */ {
/*    */   public static final int EDITATTRNORMAL = 0;
/*    */   public static final int EDITATTRUPPERCASE = 1;
/*    */   public static final int EDITATTRLOWERCASE = 2;
/*    */   protected int m_MaxLength;
/*    */   protected int m_EditType;
/*    */   
/*    */   public JLbsLimitedTextDocument() {
/* 29 */     this(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsLimitedTextDocument(int value) {
/* 34 */     this.m_MaxLength = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
/* 39 */     if (this.m_EditType == 1) {
/* 40 */       str = str.toUpperCase();
/* 41 */     } else if (this.m_EditType == 2) {
/* 42 */       str = str.toLowerCase(Locale.getDefault());
/* 43 */     }  if (this.m_MaxLength > 0) {
/*    */       
/* 45 */       int iSelfLength = getLength();
/* 46 */       if (str.length() + iSelfLength > this.m_MaxLength) {
/* 47 */         super.insertString(offset, str.substring(0, this.m_MaxLength - iSelfLength), a);
/*    */       } else {
/* 49 */         super.insertString(offset, str, a);
/*    */       } 
/*    */     } else {
/* 52 */       super.insertString(offset, str, a);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setMaxLength(int value) throws BadLocationException {
/* 57 */     int currentLength = getLength();
/* 58 */     if (currentLength <= value) {
/* 59 */       this.m_MaxLength = value;
/*    */     } else {
/*    */       
/* 62 */       String currentData = getText(0, currentLength);
/* 63 */       remove(0, currentLength);
/* 64 */       this.m_MaxLength = value;
/* 65 */       insertString(0, currentData, (AttributeSet)null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLength() {
/* 71 */     return this.m_MaxLength;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEditStyle(int iEditType) {
/* 76 */     if (this.m_EditType != iEditType) {
/*    */       
/*    */       try {
/* 79 */         this.m_EditType = iEditType;
/* 80 */         String text = getText(0, getLength());
/* 81 */         remove(0, getLength());
/* 82 */         insertString(0, text, (AttributeSet)null);
/*    */       }
/* 84 */       catch (Exception exception) {}
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEditStyle() {
/* 91 */     return this.m_EditType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\maskededit\JLbsLimitedTextDocument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */