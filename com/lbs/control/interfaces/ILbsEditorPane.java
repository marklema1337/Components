/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ public interface ILbsEditorPane
/*    */   extends ILbsTextComponent {
/*    */   void setContentType(String paramString);
/*    */   
/*    */   String getContentType();
/*    */   
/*    */   String getOriginalText();
/*    */   
/*    */   void scrollToReference(String paramString);
/*    */   
/*    */   void setTextLimit(int paramInt);
/*    */   
/*    */   int getTextLimit();
/*    */   
/*    */   default String getText() {
/* 18 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsEditorPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */