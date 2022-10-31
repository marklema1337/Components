/*    */ package com.lbs.util.xml;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LineBreak
/*    */ {
/*    */   private final String _lineBreak;
/*    */   final char[] _lineBreakChars;
/*    */   
/*    */   private LineBreak(String lineBreak) {
/* 11 */     this._lineBreak = lineBreak;
/* 12 */     this._lineBreakChars = lineBreak.toCharArray();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public static final LineBreak NONE = new LineBreak("");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public static final LineBreak UNIX = new LineBreak("\n");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public static final LineBreak DOS = new LineBreak("\r\n");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public static final LineBreak MACOS = new LineBreak("\r");
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return this._lineBreak;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\LineBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */