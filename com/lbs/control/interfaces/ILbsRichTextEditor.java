/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ import com.lbs.controls.ILbsRichTextSaveLoadListener;
/*    */ import java.awt.event.FocusListener;
/*    */ import javax.swing.text.AttributeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ILbsRichTextEditor
/*    */   extends ILbsComponent
/*    */ {
/*    */   void setOperationListener(ILbsRichTextSaveLoadListener paramILbsRichTextSaveLoadListener);
/*    */   
/*    */   ILbsRichTextSaveLoadListener getOperationListener();
/*    */   
/*    */   void saveDocument();
/*    */   
/*    */   void loadDocument();
/*    */   
/*    */   void setDocumentContents(byte[] paramArrayOfbyte);
/*    */   
/*    */   byte[] getDocumentContents();
/*    */   
/*    */   void documentReplace(int paramInt1, int paramInt2, String paramString, AttributeSet paramAttributeSet);
/*    */   
/*    */   default Object getCharacterElement(int pos) {
/* 29 */     return null;
/*    */   }
/*    */   
/*    */   default void insertString(int offset, String str, AttributeSet a) {}
/*    */   
/*    */   String getRichDocText(int paramInt1, int paramInt2);
/*    */   
/*    */   int getRichDocTextLenght();
/*    */   
/*    */   String getText();
/*    */   
/*    */   default void addPaneFocusListener(FocusListener l) {}
/*    */   
/*    */   default void putPaneClientProperty(Object key, Object value) {}
/*    */   
/*    */   String getSelectedText();
/*    */   
/*    */   int getSelectionStart();
/*    */   
/*    */   int getSelectionEnd();
/*    */   
/*    */   void setText(String paramString);
/*    */   
/*    */   void setCaretPosition(int paramInt);
/*    */   
/*    */   int getCaretPosition();
/*    */   
/*    */   void select(int paramInt1, int paramInt2);
/*    */   
/*    */   void setPaneEnabled(boolean paramBoolean);
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsRichTextEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */