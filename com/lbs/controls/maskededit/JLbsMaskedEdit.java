/*     */ package com.lbs.controls.maskededit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsMaskedEditRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.Color;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.text.DefaultFormatterFactory;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.MaskFormatter;
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
/*     */ public class JLbsMaskedEdit
/*     */   extends JFormattedTextField
/*     */   implements ILbsMaskedEdit, ILbsMaskedEditRecordingEvents, DropTargetListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  53 */   public static final JLbsMaskedEditInputVerifier ms_Verifier = new JLbsMaskedEditInputVerifier();
/*  54 */   protected FocusListener m_FocusListener = null;
/*     */   protected boolean m_bFocusEvents = true;
/*  56 */   protected InputVerifier m_InputVerifier = null;
/*  57 */   protected String m_OriginalText = null;
/*  58 */   protected String m_CharFilter = null;
/*  59 */   protected JLbsCharMap m_CharMap = null;
/*     */   
/*     */   private ITextLimitChangeListener m_TextLimitChangeListener;
/*     */   
/*     */   protected boolean m_ValueChangedByParent = false;
/*     */   
/*     */   private boolean m_clipMode = false;
/*     */   
/*     */   private boolean hasFileDialog;
/*     */   private File[] selectedFiles;
/*     */   
/*     */   public static Color getSelectedBkColor() {
/*  71 */     Color result = UIManager.getColor("TextField.selectedbackground");
/*  72 */     if (result == null)
/*  73 */       result = UIManager.getColor("ToolTip.background"); 
/*  74 */     if (JLbsControlHelper.isColorEqual(result, UIManager.getColor("ComboBox.selectionBackground"), 20.0D) || 
/*  75 */       JLbsControlHelper.isColorEqual(result, UIManager.getColor("TextField.selectionBackground"), 20.0D))
/*  76 */       result = result.darker(); 
/*  77 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMaskedEdit() {
/*  82 */     super.setInputVerifier(ms_Verifier);
/*  83 */     enableEvents(12L);
/*  84 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  89 */             JLbsMaskedEdit.this.recordRequestFocus();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {}
/*     */         });
/*  97 */     if (!GraphicsEnvironment.isHeadless());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 103 */     super.updateUI();
/* 104 */     JLbsControlHelper.updateUIBorder(this, "FormattedTextField.border");
/* 105 */     setBackground(UIManager.getColor("FormattedTextField.background"));
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addLbsFocusListener(FocusListener listener) {
/* 110 */     this.m_FocusListener = AWTEventMulticaster.add(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeLbsFocusListener(FocusListener listener) {
/* 115 */     this.m_FocusListener = AWTEventMulticaster.remove(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableFocusEvent(boolean bEnable) {
/* 120 */     this.m_bFocusEvents = bEnable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableFocusEvent() {
/* 125 */     return this.m_bFocusEvents;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInputVerifier(InputVerifier inputVerifier) {
/* 130 */     this.m_InputVerifier = inputVerifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputVerifier getInputVerifier() {
/* 135 */     return ms_Verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized void fireLbsFocusEvent(FocusEvent event, int id) {
/* 140 */     if (this.m_FocusListener != null)
/*     */     {
/* 142 */       if (id == 1004) {
/* 143 */         this.m_FocusListener.focusGained(event);
/* 144 */       } else if (id == 1005) {
/* 145 */         this.m_FocusListener.focusLost(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void processKeyEvent(KeyEvent e) {
/* 151 */     switch (e.getID()) {
/*     */       
/*     */       case 401:
/* 154 */         if (e.getKeyCode() == 90 && e.isControlDown()) {
/* 155 */           setText(getOriginalText()); break;
/* 156 */         }  if (e.getModifiers() != 1 && e.getKeyCode() == 39 && 
/* 157 */           JLbsConstants.VK_RIGHT_CARET_POSITION) {
/* 158 */           setCaretPosition(getText().length()); break;
/* 159 */         }  if (this.m_CharFilter != null && this.m_CharFilter.indexOf(e.getKeyChar()) >= 0) {
/*     */           return;
/*     */         }
/*     */         break;
/*     */       case 400:
/* 164 */         if (this.m_CharFilter != null && this.m_CharFilter.indexOf(e.getKeyChar()) >= 0) {
/*     */           return;
/*     */         }
/* 167 */         if (this.m_CharMap != null && this.m_CharMap.hasMapping(e.getKeyChar())) {
/*     */           
/* 169 */           char newChar = this.m_CharMap.get(e.getKeyChar());
/* 170 */           KeyEvent ek = new KeyEvent(e.getComponent(), 400, e.getWhen(), 0, 0, 
/* 171 */               newChar);
/* 172 */           dispatchEvent(ek);
/*     */           return;
/*     */         } 
/*     */         break;
/*     */     } 
/* 177 */     super.processKeyEvent(e);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 182 */     processFocusEvent(e, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e, boolean fireOption) {
/* 187 */     if (!JLbsConstants.DESIGN_TIME)
/* 188 */       switch (e.getID()) {
/*     */         
/*     */         case 1004:
/* 191 */           setBackground(getSelectedBkColor());
/*     */           break;
/*     */         case 1005:
/* 194 */           setBackground(getNormalBkColor());
/*     */           break;
/*     */       }  
/* 197 */     super.processFocusEvent(e);
/* 198 */     if (fireOption && this.m_bFocusEvents)
/* 199 */       fireLbsFocusEvent(e, e.getID()); 
/* 200 */     if (e.getID() == 1004) {
/*     */ 
/*     */       
/* 203 */       if (!isValueChangedByParent())
/* 204 */         setOriginalText(getText()); 
/* 205 */       setValueChangedByParent(false);
/*     */       
/* 207 */       if (!this.m_bFocusEvents) {
/* 208 */         this.m_bFocusEvents = true;
/*     */       }
/* 210 */       if (isEditable() && (getSelectedText() == null || getSelectedText().length() == 0)) {
/* 211 */         selectAll();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 218 */     Document doc = getDocument();
/* 219 */     if (doc != null) {
/*     */       
/* 221 */       setCaretPosition(doc.getLength());
/* 222 */       moveCaretPosition(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setOriginalText(String text) {
/* 228 */     this.m_OriginalText = text;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 233 */     recordVerifyContent();
/* 234 */     if (this.m_InputVerifier != null) {
/* 235 */       return this.m_InputVerifier.verify(this);
/*     */     }
/* 237 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordVerifyContent() {
/* 242 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "VERIFY");
/*     */   }
/*     */ 
/*     */   
/*     */   public void doVerifyContent(int actionID) {
/* 247 */     final int mActionID = actionID;
/* 248 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 252 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/* 253 */             JLbsMaskedEdit.this.verifyContent();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/* 262 */     return UIManager.getColor("TextField.background");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String s) {
/* 267 */     if (s != null && s.length() > 0 && s.charAt(0) == '\000') {
/* 268 */       super.setText((String)null);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/* 273 */         String docText = getDocument().getText(0, getDocument().getLength());
/*     */         
/* 275 */         JFormattedTextField.AbstractFormatter formatter = getFormatter();
/*     */         
/* 277 */         if (formatter != null && getValue() != null && !JLbsStringUtil.isEmpty(docText) && 
/* 278 */           docText.equals(formatter.valueToString(null))) {
/* 279 */           setValue((Object)null);
/*     */         } else {
/* 281 */           super.setText(s);
/*     */         } 
/* 283 */       } catch (Exception e) {
/*     */         
/* 285 */         super.setText(s);
/*     */       } 
/*     */     } 
/* 288 */     if (getHorizontalAlignment() == 2 || getHorizontalAlignment() == 10)
/*     */     {
/* 290 */       setCaretPosition(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharFilter() {
/* 296 */     return this.m_CharFilter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharFilter(String string) {
/* 301 */     this.m_CharFilter = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 309 */     Document doc = getDocument();
/* 310 */     if (doc instanceof JLbsLimitedTextDocument)
/* 311 */       return ((JLbsLimitedTextDocument)doc).getMaxLength(); 
/* 312 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int iLimit) {
/* 320 */     Document doc = getDocument();
/* 321 */     if (doc instanceof JLbsLimitedTextDocument) {
/*     */       
/*     */       try {
/* 324 */         if (((JLbsLimitedTextDocument)doc).getMaxLength() == iLimit) {
/* 325 */           return false;
/*     */         }
/* 327 */         ((JLbsLimitedTextDocument)doc).setMaxLength(iLimit);
/* 328 */         if (this.m_TextLimitChangeListener != null)
/*     */         {
/* 330 */           this.m_TextLimitChangeListener.textLimitChanged(this);
/*     */         }
/* 332 */         return true;
/*     */       }
/* 334 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 337 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextLimitChangeListener(ITextLimitChangeListener textLimitChangeListener) {
/* 342 */     this.m_TextLimitChangeListener = textLimitChangeListener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMask(String mask) {
/*     */     try {
/* 349 */       MaskFormatter formatter = new MaskFormatter(mask);
/* 350 */       formatter.setPlaceholderCharacter('_');
/* 351 */       setFormatterFactory(new DefaultFormatterFactory(formatter));
/*     */     }
/* 353 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCharMap getCharMap() {
/* 360 */     if (this.m_CharMap == null) {
/* 361 */       this.m_CharMap = new JLbsCharMap();
/*     */     }
/* 363 */     return this.m_CharMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharMap(JLbsCharMap charMap) {
/* 368 */     this.m_CharMap = charMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 373 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 378 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 383 */     if (!(this instanceof com.lbs.controls.numericedit.JLbsNumericEdit)) {
/* 384 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */     }
/*     */   }
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 389 */     final int fActionID = actionID;
/* 390 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 394 */             JLbsMaskedEdit.this.requestFocus();
/* 395 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 414 */     return this.m_OriginalText;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 419 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValueChangedByParent() {
/* 427 */     return this.m_ValueChangedByParent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueChangedByParent(boolean valueChangedByParent) {
/* 435 */     this.m_ValueChangedByParent = valueChangedByParent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 440 */     return getParent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {}
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public FocusListener getFocusListener() {
/* 454 */     return this.m_FocusListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClipMode() {
/* 459 */     return this.m_clipMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClipMode(boolean clipMode) {
/* 464 */     this.m_clipMode = clipMode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMask() {
/* 470 */     if (getFormatter() != null && getFormatter() instanceof MaskFormatter)
/*     */     {
/* 472 */       return ((MaskFormatter)getFormatter()).getMask();
/*     */     }
/* 474 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/* 480 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {
/* 486 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {
/* 492 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void drop(DropTargetDropEvent dtde) {
/* 503 */     dtde.rejectDrop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFileDialog() {
/* 509 */     return this.hasFileDialog;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHasFileDialog(boolean hasFileDialog) {
/* 515 */     this.hasFileDialog = hasFileDialog;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public File[] getSelectedFiles() {
/* 521 */     return this.selectedFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedFiles(File[] selectedFiles) {
/* 526 */     this.selectedFiles = selectedFiles;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\maskededit\JLbsMaskedEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */