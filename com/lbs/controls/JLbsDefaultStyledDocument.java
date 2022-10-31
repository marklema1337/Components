/*     */ package com.lbs.controls;
/*     */ 
/*     */ import javax.swing.event.UndoableEditListener;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.DefaultStyledDocument;
/*     */ import javax.swing.text.StyleContext;
/*     */ import javax.swing.undo.AbstractUndoableEdit;
/*     */ import javax.swing.undo.CannotRedoException;
/*     */ import javax.swing.undo.CannotUndoException;
/*     */ import javax.swing.undo.UndoableEditSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsDefaultStyledDocument
/*     */   extends DefaultStyledDocument
/*     */ {
/*  24 */   private UndoableEditSupport m_Support = new UndoableEditSupport(this);
/*     */   
/*     */   boolean isUndoAction = false;
/*     */   
/*     */   public JLbsDefaultStyledDocument(StyleContext sc) {
/*  29 */     super(sc);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addUndoableEditListener(UndoableEditListener l) {
/*  34 */     this.m_Support.addUndoableEditListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeUndoableEditListener(UndoableEditListener l) {
/*  39 */     this.m_Support.removeUndoableEditListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginUpdate() {
/*  44 */     this.m_Support.beginUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postEditCharProps(JLbsCharProps c) {
/*  49 */     this.m_Support.postEdit(new SetCharacterProps(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public void postEditParagraphProps(JLbsCharProps c) {
/*  54 */     this.m_Support.postEdit(new SetParagraphProps(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public void postEditTextReplace(int offset, int length, String text, AttributeSet attrs) {
/*  59 */     this.m_Support.postEdit(new ReplaceText(offset, length, text, attrs));
/*     */   }
/*     */ 
/*     */   
/*     */   public void endUpdate() {
/*  64 */     this.m_Support.endUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
/*  69 */     if (!this.isUndoAction) {
/*     */       
/*  71 */       beginUpdate();
/*  72 */       postEditTextReplace(offset, length, text, attrs);
/*  73 */       endUpdate();
/*  74 */       super.replace(offset, length, text, attrs);
/*     */       return;
/*     */     } 
/*  77 */     this.isUndoAction = false;
/*  78 */     int len = text.length();
/*  79 */     super.replace(offset, len, null, attrs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharacterAttributes(int offset, int length, AttributeSet s, boolean replace) {
/*  84 */     super.setCharacterAttributes(offset, length, s, replace);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParagraphAttributes(int offset, int length, AttributeSet s, boolean replace) {
/*  89 */     super.setParagraphAttributes(offset, length, s, replace);
/*     */   }
/*     */ 
/*     */   
/*     */   public void undoReplace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
/*  94 */     replace(offset, length, text, attrs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void redoReplace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
/*  99 */     replace(offset, length, text, attrs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void undoCharacterProps(JLbsCharProps c) {
/* 104 */     setCharacterAttributes(c.getOffset(), 1, c.getAttributeSet(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void redoCharacterProps(JLbsCharProps c) {
/* 109 */     setCharacterAttributes(c.getOffset(), 1, c.getAttributeSet(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void undoParagraphProps(JLbsCharProps c) {
/* 114 */     setParagraphAttributes(c.getOffset(), 1, c.getAttributeSet(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void redoParagraphProps(JLbsCharProps c) {
/* 119 */     setParagraphAttributes(c.getOffset(), 1, c.getAttributeSet(), false);
/*     */   }
/*     */   
/*     */   private class SetCharacterProps
/*     */     extends AbstractUndoableEdit
/*     */   {
/*     */     JLbsCharProps m_c;
/*     */     
/*     */     public SetCharacterProps(JLbsCharProps c) {
/* 128 */       this.m_c = c;
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() throws CannotUndoException {
/* 133 */       super.undo();
/* 134 */       JLbsDefaultStyledDocument.this.undoCharacterProps(this.m_c);
/*     */     }
/*     */ 
/*     */     
/*     */     public void redo() throws CannotRedoException {
/* 139 */       super.redo();
/* 140 */       JLbsDefaultStyledDocument.this.redoCharacterProps(this.m_c);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPresentationName() {
/* 145 */       return "setCharacterProperties";
/*     */     }
/*     */   }
/*     */   
/*     */   private class SetParagraphProps
/*     */     extends AbstractUndoableEdit
/*     */   {
/*     */     JLbsCharProps m_c;
/*     */     
/*     */     public SetParagraphProps(JLbsCharProps c) {
/* 155 */       this.m_c = c;
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() throws CannotUndoException {
/* 160 */       super.undo();
/* 161 */       JLbsDefaultStyledDocument.this.undoParagraphProps(this.m_c);
/*     */     }
/*     */ 
/*     */     
/*     */     public void redo() throws CannotRedoException {
/* 166 */       super.redo();
/* 167 */       JLbsDefaultStyledDocument.this.redoParagraphProps(this.m_c);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPresentationName() {
/* 172 */       return "setParagraphProperties";
/*     */     }
/*     */   }
/*     */   
/*     */   private class ReplaceText
/*     */     extends AbstractUndoableEdit
/*     */   {
/*     */     int m_offset;
/*     */     int m_length;
/*     */     String m_text;
/*     */     AttributeSet m_attrs;
/*     */     
/*     */     public ReplaceText(int offset, int length, String text, AttributeSet attrs) {
/* 185 */       this.m_offset = offset;
/* 186 */       this.m_length = length;
/* 187 */       this.m_text = text;
/* 188 */       this.m_attrs = attrs;
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() throws CannotUndoException {
/* 193 */       super.undo();
/*     */       
/*     */       try {
/* 196 */         JLbsDefaultStyledDocument.this.isUndoAction = true;
/* 197 */         JLbsDefaultStyledDocument.this.undoReplace(this.m_offset, this.m_length, this.m_text, this.m_attrs);
/*     */       }
/* 199 */       catch (BadLocationException e) {
/*     */         
/* 201 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void redo() throws CannotRedoException {
/* 207 */       super.redo();
/*     */       
/*     */       try {
/* 210 */         JLbsDefaultStyledDocument.this.redoReplace(this.m_offset, this.m_length, this.m_text, this.m_attrs);
/*     */       }
/* 212 */       catch (BadLocationException e) {
/*     */         
/* 214 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPresentationName() {
/* 220 */       return "replaceText";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsDefaultStyledDocument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */