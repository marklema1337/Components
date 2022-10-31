/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ComboBoxEditor;
/*     */ import javax.swing.ComboBoxModel;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import javax.swing.text.PlainDocument;
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
/*     */ public class AutoCompletion
/*     */   extends PlainDocument
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLbsComboBox m_ComboBox;
/*     */   private ComboBoxModel m_Model;
/*     */   private JTextComponent m_Editor;
/*     */   private boolean m_Selecting = false;
/*     */   private boolean m_HidePopupOnFocusLoss;
/*     */   private boolean m_HitBackspace = false;
/*     */   private boolean m_HitBackspaceOnSelection;
/*     */   private int m_CurrentCaretPosition;
/*     */   private transient KeyListener m_EditorKeyListener;
/*     */   private transient FocusListener m_EditorFocusListener;
/*     */   
/*     */   public AutoCompletion(final JLbsComboBox comboBox) {
/*  60 */     this.m_ComboBox = comboBox;
/*  61 */     this.m_Model = comboBox.getModel();
/*  62 */     comboBox.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  66 */             if (!AutoCompletion.this.m_Selecting)
/*  67 */               AutoCompletion.this.highlightCompletedText(0); 
/*     */           }
/*     */         });
/*  70 */     comboBox.addPropertyChangeListener(new PropertyChangeListener()
/*     */         {
/*     */           public void propertyChange(PropertyChangeEvent e)
/*     */           {
/*  74 */             if (e.getPropertyName().equals("editor"))
/*  75 */               AutoCompletion.this.configureEditor((ComboBoxEditor)e.getNewValue()); 
/*  76 */             if (e.getPropertyName().equals("model")) {
/*  77 */               AutoCompletion.this.m_Model = (ComboBoxModel)e.getNewValue();
/*     */             }
/*     */           }
/*     */         });
/*  81 */     this.m_EditorKeyListener = new KeyAdapter()
/*     */       {
/*     */         public void keyPressed(KeyEvent e)
/*     */         {
/*  85 */           AutoCompletion.this.m_Selecting = false;
/*     */ 
/*     */           
/*  88 */           if (AutoCompletion.this.m_ComboBox.isAlreadyEditable()) {
/*     */             return;
/*     */           }
/*  91 */           int keyCode = e.getKeyCode();
/*  92 */           if (comboBox.isDisplayable()) {
/*     */             
/*  94 */             boolean popupTrigger = true;
/*     */             
/*  96 */             if (keyCode >= 112 && keyCode <= 123) {
/*  97 */               popupTrigger = false;
/*  98 */             } else if (keyCode >= 61440 && keyCode <= 61451) {
/*  99 */               popupTrigger = false;
/* 100 */             }  comboBox.setPopupVisible(popupTrigger);
/*     */           } 
/* 102 */           AutoCompletion.this.m_HitBackspace = false;
/* 103 */           switch (keyCode) {
/*     */             
/*     */             case 10:
/* 106 */               AutoCompletion.this.m_Selecting = true;
/*     */               break;
/*     */             case 8:
/* 109 */               AutoCompletion.this.m_HitBackspace = true;
/* 110 */               AutoCompletion.this.m_HitBackspaceOnSelection = (AutoCompletion.this.m_Editor.getSelectionStart() != AutoCompletion.this.m_Editor.getSelectionEnd());
/*     */               break;
/*     */             
/*     */             case 127:
/* 114 */               e.consume();
/* 115 */               comboBox.getToolkit().beep();
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/*     */         }
/*     */       };
/* 122 */     this.m_HidePopupOnFocusLoss = System.getProperty("java.version").startsWith("1.5");
/*     */     
/* 124 */     this.m_EditorFocusListener = new FocusAdapter()
/*     */       {
/*     */         public void focusGained(FocusEvent e)
/*     */         {
/* 128 */           AutoCompletion.this.highlightCompletedText(0);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void focusLost(FocusEvent e) {
/* 134 */           if (AutoCompletion.this.m_HidePopupOnFocusLoss)
/* 135 */             comboBox.setPopupVisible(false); 
/*     */         }
/*     */       };
/* 138 */     configureEditor(comboBox.getEditor());
/*     */     
/* 140 */     Object selected = comboBox.getSelectedItem();
/* 141 */     if (selected != null)
/* 142 */       setText(selected.toString()); 
/* 143 */     highlightCompletedText(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static AutoCompletion enable(JLbsComboBox comboBox) {
/* 149 */     comboBox.setEditableForAutoComplete(true);
/* 150 */     return new AutoCompletion(comboBox);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComboBox getComboBox() {
/* 155 */     return this.m_ComboBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComboBox(JLbsComboBox comboBox) {
/* 160 */     this.m_ComboBox = comboBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public ComboBoxModel getModel() {
/* 165 */     return this.m_Model;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModel(ComboBoxModel model) {
/* 170 */     this.m_Model = model;
/*     */   }
/*     */ 
/*     */   
/*     */   public JTextComponent getEditor() {
/* 175 */     return this.m_Editor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditor(JTextComponent editor) {
/* 180 */     this.m_Editor = editor;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentCaretPosition() {
/* 185 */     return this.m_CurrentCaretPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCaretPosition(int currentCaretPosition) {
/* 190 */     highlightCompletedText(currentCaretPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   void configureEditor(ComboBoxEditor newEditor) {
/* 195 */     if (this.m_Editor != null) {
/*     */       
/* 197 */       this.m_Editor.removeKeyListener(this.m_EditorKeyListener);
/* 198 */       this.m_Editor.removeFocusListener(this.m_EditorFocusListener);
/*     */     } 
/* 200 */     if (newEditor != null) {
/*     */       
/* 202 */       this.m_Editor = (JTextComponent)newEditor.getEditorComponent();
/* 203 */       this.m_Editor.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
/* 204 */       this.m_Editor.addKeyListener(this.m_EditorKeyListener);
/* 205 */       this.m_Editor.addFocusListener(this.m_EditorFocusListener);
/* 206 */       Object object = this.m_ComboBox.getClientProperty("inplace");
/* 207 */       if (object instanceof Boolean && ((Boolean)object).booleanValue())
/* 208 */         this.m_Editor.setFocusTraversalKeysEnabled(false); 
/* 209 */       this.m_Editor.setDocument(this);
/* 210 */       this.m_Editor.setInputVerifier(this.m_ComboBox.getInputVerifier());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int offs, int len) throws BadLocationException {
/* 217 */     if (this.m_Selecting)
/*     */       return; 
/* 219 */     if (this.m_HitBackspace) {
/*     */ 
/*     */ 
/*     */       
/* 223 */       if (offs > 0) {
/*     */         
/* 225 */         if (this.m_HitBackspaceOnSelection) {
/* 226 */           offs--;
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 232 */         this.m_ComboBox.getToolkit().beep();
/*     */       } 
/*     */       
/* 235 */       highlightCompletedText(offs);
/*     */     }
/*     */     else {
/*     */       
/* 239 */       super.remove(offs, len);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
/* 246 */     if (this.m_Selecting) {
/*     */       return;
/*     */     }
/* 249 */     super.insertString(offs, str, a);
/*     */ 
/*     */     
/* 252 */     if (this.m_ComboBox.isAlreadyEditable()) {
/*     */       return;
/*     */     }
/*     */     
/* 256 */     Object item = lookupItem(getText(0, getLength()));
/* 257 */     if (item != null) {
/*     */       
/* 259 */       setSelectedItem(item);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 264 */       item = this.m_ComboBox.getSelectedItem();
/*     */ 
/*     */       
/* 267 */       offs -= str.length();
/*     */ 
/*     */       
/* 270 */       this.m_ComboBox.getToolkit().beep();
/*     */     } 
/*     */     
/* 273 */     setText(item.toString());
/*     */     
/* 275 */     highlightCompletedText(offs + str.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/*     */     try {
/* 283 */       super.remove(0, getLength());
/* 284 */       super.insertString(0, text, (AttributeSet)null);
/*     */     }
/* 286 */     catch (BadLocationException e) {
/*     */       
/* 288 */       throw new RuntimeException(e.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void highlightCompletedText(int start) {
/* 294 */     this.m_Editor.setCaretPosition(getLength());
/* 295 */     this.m_Editor.moveCaretPosition(start);
/* 296 */     this.m_CurrentCaretPosition = start;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSelectedItem(Object item) {
/* 301 */     this.m_Selecting = true;
/* 302 */     this.m_Model.setSelectedItem(item);
/* 303 */     this.m_Selecting = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object lookupItem(String pattern) {
/* 308 */     Object selectedItem = this.m_Model.getSelectedItem();
/*     */ 
/*     */     
/* 311 */     if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern))
/*     */     {
/* 313 */       return selectedItem;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 318 */     for (int i = 0, n = this.m_Model.getSize(); i < n; i++) {
/*     */       
/* 320 */       Object currentItem = this.m_Model.getElementAt(i);
/*     */       
/* 322 */       if (currentItem != null && startsWithIgnoreCase(currentItem.toString(), pattern))
/*     */       {
/* 324 */         return currentItem;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 329 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean startsWithIgnoreCase(String str1, String str2) {
/* 335 */     if (str1 == null)
/* 336 */       return (str2 == null); 
/* 337 */     if (str2 == null)
/* 338 */       return false; 
/* 339 */     return str1.toUpperCase().startsWith(str2.toUpperCase());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\AutoCompletion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */