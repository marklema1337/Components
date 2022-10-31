/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsRichTextEditor;
/*     */ import com.lbs.controls.ILbsRichTextSaveLoadListener;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import javax.swing.text.AttributeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsRichTextEditorEmulator
/*     */   extends LbsPanelEmulator
/*     */   implements ILbsRichTextEditor
/*     */ {
/*     */   private ILbsRichTextSaveLoadListener m_Listener;
/*     */   
/*     */   public ILbsRichTextSaveLoadListener getOperationListener() {
/*  25 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadDocument() {
/*  30 */     if (this.m_Listener != null)
/*     */     {
/*  32 */       this.m_Listener.loadRichTextDocument();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveDocument() {
/*  38 */     if (this.m_Listener != null) {
/*  39 */       this.m_Listener.saveRichTextDocument(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOperationListener(ILbsRichTextSaveLoadListener listener) {
/*  44 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentContents(byte[] doc) {}
/*     */ 
/*     */   
/*     */   public byte[] getDocumentContents() {
/*  53 */     if (this.m_Listener != null) {
/*     */       
/*  55 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  56 */       this.m_Listener.save(this, outStream);
/*  57 */       return outStream.toByteArray();
/*     */     } 
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/*  81 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/*  88 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int pos) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 109 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void select(int first, int end) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void documentReplace(int offset, int length, String text, AttributeSet attrs) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRichDocText(int offset, int length) {
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRichDocTextLenght() {
/* 137 */     return 0;
/*     */   }
/*     */   
/*     */   public void setPaneEnabled(boolean enable) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsRichTextEditorEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */