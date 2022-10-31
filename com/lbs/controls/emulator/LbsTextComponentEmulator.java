/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsTextArea;
/*     */ import com.lbs.control.interfaces.ILbsTextComponent;
/*     */ import com.lbs.control.interfaces.ILbsTextField;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.text.BadLocationException;
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
/*     */ 
/*     */ public class LbsTextComponentEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsTextComponent, ILbsTextArea, ILbsTextField
/*     */ {
/*     */   private boolean m_Editable = true;
/*     */   private String m_Text;
/*  29 */   private int m_CaretPos = 0;
/*  30 */   private int m_SelectionStart = 0;
/*  31 */   private int m_SelectionEnd = 0;
/*     */   protected String m_OriginalText;
/*     */   private boolean m_LineWrap;
/*     */   private int m_Columns;
/*     */   
/*     */   public int getCaretPosition() {
/*  37 */     return this.m_CaretPos;
/*     */   }
/*     */   private int m_Rows; private boolean m_WrapStyleWord; private ActionListener m_ActionListener;
/*     */   
/*     */   public Color getDisabledTextColor() {
/*  42 */     return Color.black;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/*     */     try {
/*  49 */       int start = Math.min(0, this.m_SelectionStart);
/*  50 */       int end = Math.max(0, this.m_SelectionEnd);
/*  51 */       return getText(start, end - start);
/*     */     }
/*  53 */     catch (BadLocationException e) {
/*     */       
/*  55 */       throw new IllegalArgumentException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectedTextColor() {
/*  61 */     return Color.blue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionColor() {
/*  66 */     return Color.red;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/*  71 */     return this.m_SelectionEnd;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/*  76 */     return this.m_SelectionStart;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  81 */     return this.m_Text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText(int offs, int len) throws BadLocationException {
/*  86 */     if (this.m_Text == null) {
/*  87 */       return null;
/*     */     }
/*  89 */     int end = offs + len;
/*  90 */     if (offs < 0 || end > this.m_Text.length())
/*  91 */       throw new BadLocationException(this.m_Text, offs); 
/*  92 */     return this.m_Text.substring(offs, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/*  97 */     return this.m_Editable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSelection(String content) {
/* 102 */     if (this.m_Text == null) {
/*     */       return;
/*     */     }
/* 105 */     int selectionStart = this.m_SelectionStart;
/* 106 */     int selectionEnd = this.m_SelectionEnd;
/* 107 */     int docLength = this.m_Text.length();
/*     */     
/* 109 */     if (selectionStart < 0)
/*     */     {
/* 111 */       selectionStart = 0;
/*     */     }
/* 113 */     if (selectionStart > docLength)
/*     */     {
/* 115 */       selectionStart = docLength;
/*     */     }
/* 117 */     if (selectionEnd > docLength)
/*     */     {
/* 119 */       selectionEnd = docLength;
/*     */     }
/* 121 */     if (selectionEnd < selectionStart)
/*     */     {
/* 123 */       selectionEnd = selectionStart;
/*     */     }
/* 125 */     this.m_Text = String.valueOf(this.m_Text.substring(0, selectionStart)) + content + this.m_Text.substring(selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void select(int selectionStart, int selectionEnd) {
/* 130 */     int docLength = (this.m_Text == null) ? 
/* 131 */       0 : 
/* 132 */       this.m_Text.length();
/*     */     
/* 134 */     if (selectionStart < 0)
/*     */     {
/* 136 */       selectionStart = 0;
/*     */     }
/* 138 */     if (selectionStart > docLength)
/*     */     {
/* 140 */       selectionStart = docLength;
/*     */     }
/* 142 */     if (selectionEnd > docLength)
/*     */     {
/* 144 */       selectionEnd = docLength;
/*     */     }
/* 146 */     if (selectionEnd < selectionStart)
/*     */     {
/* 148 */       selectionEnd = selectionStart;
/*     */     }
/* 150 */     this.m_SelectionStart = selectionStart;
/* 151 */     this.m_SelectionEnd = selectionEnd;
/*     */     
/* 153 */     setCaretPosition(selectionStart);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 158 */     int length = (this.m_Text == null) ? 
/* 159 */       0 : 
/* 160 */       this.m_Text.length();
/* 161 */     select(0, length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int position) {
/* 166 */     this.m_CaretPos = position;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisabledTextColor(Color c) {}
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 175 */     this.m_Editable = editable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedTextColor(Color c) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionColor(Color c) {}
/*     */ 
/*     */   
/*     */   public void setSelectionEnd(int selectionEnd) {
/* 188 */     select(getSelectionStart(), selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionStart(int selectionStart) {
/* 193 */     select(selectionStart, getSelectionEnd());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 198 */     this.m_OriginalText = this.m_Text;
/* 199 */     this.m_Text = text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 204 */     return this.m_OriginalText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumns() {
/* 214 */     return this.m_Columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getLineWrap() {
/* 219 */     return this.m_LineWrap;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRows() {
/* 224 */     return this.m_Rows;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getWrapStyleWord() {
/* 229 */     return this.m_WrapStyleWord;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(int columns) {
/* 234 */     this.m_Columns = columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLineWrap(boolean wrap) {
/* 239 */     this.m_LineWrap = wrap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRows(int rows) {
/* 244 */     this.m_Rows = rows;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWrapStyleWord(boolean word) {
/* 249 */     this.m_WrapStyleWord = word;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 254 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int alignment) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/* 265 */     if (listener == null)
/*     */       return; 
/* 267 */     this.m_ActionListener = AWTEventMulticaster.add(this.m_ActionListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/* 272 */     if (l == null)
/*     */       return; 
/* 274 */     this.m_ActionListener = AWTEventMulticaster.remove(this.m_ActionListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireActionPerformed() {
/* 279 */     if (this.m_ActionListener != null) {
/*     */       
/* 281 */       ActionEvent e = new ActionEvent(this, 1001, getText(), 0L, 0);
/* 282 */       this.m_ActionListener.actionPerformed(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {}
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 292 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsTextComponentEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */