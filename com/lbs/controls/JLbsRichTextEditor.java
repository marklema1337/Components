/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.hexidec.ekit.EkitCore;
/*     */ import com.hexidec.ekit.component.ExtendedHTMLEditorKit;
/*     */ import com.lbs.control.interfaces.ILbsRichTextEditor;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Locale;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.DefaultEditorKit;
/*     */ import javax.swing.text.DefaultStyledDocument;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.EditorKit;
/*     */ import javax.swing.text.html.HTML;
/*     */ import javax.swing.text.html.HTMLDocument;
/*     */ import javax.swing.text.html.HTMLEditorKit;
/*     */ import javax.swing.text.rtf.RTFEditorKit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsRichTextEditor
/*     */   extends JLbsPanel
/*     */   implements ILbsRichTextEditor
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected ILbsRichTextSaveLoadListener m_OperationListener;
/*     */   public static final int OPTION_SAVE_BUTTON = 1;
/*     */   public static final int OPTION_LOAD_BUTTON = 2;
/*     */   public static final int OPTION_PRINT_BUTTON = 4;
/*     */   private EkitCore m_EkitCore;
/*     */   
/*     */   public JLbsRichTextEditor() {
/*  49 */     this(7);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRichTextEditor(int options) {
/*  54 */     boolean multiBar = true;
/*  55 */     Locale locale = JLbsLocalizer.getLocale();
/*  56 */     String langName = (locale == null) ? 
/*  57 */       "en" : 
/*  58 */       locale.getLanguage();
/*  59 */     String country = (locale == null) ? 
/*  60 */       "US" : 
/*  61 */       locale.getCountry();
/*  62 */     EkitCore.setResourceProvider(new EkitResourceProvider());
/*  63 */     this.m_EkitCore = new EkitCore(false, null, null, null, null, null, true, false, true, false, langName, country, false, false, 
/*  64 */         false, multiBar, prepareToolbar(multiBar, options), true, JLbsConstants.DESKTOP_MODE);
/*     */     
/*  66 */     if (multiBar) {
/*     */       
/*  68 */       setLayout(new GridBagLayout());
/*  69 */       GridBagConstraints gbc = new GridBagConstraints();
/*  70 */       gbc.fill = 2;
/*  71 */       gbc.anchor = 11;
/*  72 */       gbc.gridheight = 1;
/*  73 */       gbc.gridwidth = 1;
/*  74 */       gbc.weightx = 1.0D;
/*  75 */       gbc.weighty = 0.0D;
/*  76 */       gbc.gridx = 1;
/*     */       
/*  78 */       gbc.gridy = 1;
/*  79 */       add(this.m_EkitCore.getToolBarMain(true), gbc);
/*     */       
/*  81 */       gbc.gridy = 2;
/*  82 */       add(this.m_EkitCore.getToolBarFormat(true), gbc);
/*     */       
/*  84 */       gbc.gridy = 3;
/*  85 */       add(this.m_EkitCore.getToolBarStyles(true), gbc);
/*     */       
/*  87 */       gbc.anchor = 15;
/*  88 */       gbc.fill = 1;
/*  89 */       gbc.weightx = 1.0D;
/*  90 */       gbc.weighty = 1.0D;
/*  91 */       gbc.gridy = 4;
/*  92 */       add((Component)this.m_EkitCore, gbc);
/*     */     }
/*     */     else {
/*     */       
/*  96 */       setLayout(new BorderLayout());
/*  97 */       add((Component)this.m_EkitCore, "Center");
/*  98 */       add(this.m_EkitCore.getToolBar(true), "North");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String prepareToolbar(boolean multiBar, int options) {
/* 104 */     StringBuilder sb = new StringBuilder();
/* 105 */     if (multiBar) {
/*     */       
/* 107 */       if (isOptionSet(options, 2))
/* 108 */         appendToolbar(sb, new String[] { "OP" }); 
/* 109 */       if (isOptionSet(options, 1))
/* 110 */         appendToolbar(sb, new String[] { "SV" }); 
/* 111 */       if (isOptionSet(options, 4))
/* 112 */         appendToolbar(sb, new String[] { "PR" }); 
/* 113 */       if (sb.length() > 0)
/* 114 */         appendToolbar(sb, new String[] { "SP" }); 
/* 115 */       appendToolbar(sb, new String[] { "CT", "CP", "PS", "SP" });
/* 116 */       appendToolbar(sb, new String[] { "UN", "RE", "SP", "FN", 
/* 117 */             "SP" });
/* 118 */       appendToolbar(sb, new String[] { "*", "BL", "IT", "UD", 
/* 119 */             "SP" });
/* 120 */       appendToolbar(sb, new String[] { "SK", "SU", "SB", "SP" });
/* 121 */       appendToolbar(sb, new String[] { "AL", "AC", "AR", 
/* 122 */             "AJ", "SP" });
/* 123 */       appendToolbar(sb, new String[] { "UL", "OL", "SP", "TI", "PI", "PB", 
/* 124 */             "LK" });
/* 125 */       appendToolbar(sb, new String[] { "*", "ST", "SP", "FO" });
/*     */     }
/*     */     else {
/*     */       
/* 129 */       if (isOptionSet(options, 2))
/* 130 */         appendToolbar(sb, new String[] { "OP" }); 
/* 131 */       if (isOptionSet(options, 1))
/* 132 */         appendToolbar(sb, new String[] { "SV" }); 
/* 133 */       if (isOptionSet(options, 4))
/* 134 */         appendToolbar(sb, new String[] { "PR" }); 
/* 135 */       if (sb.length() > 0)
/* 136 */         appendToolbar(sb, new String[] { "SP" }); 
/* 137 */       appendToolbar(sb, new String[] { "CT", "CP", "PS", "SP" });
/* 138 */       appendToolbar(sb, new String[] { "UN", "RE", "SP" });
/* 139 */       appendToolbar(sb, new String[] { "BL", "IT", "UD", 
/* 140 */             "PI", "PB", "SP" });
/* 141 */       appendToolbar(sb, new String[] { "ST", "SP", "FO" });
/*     */     } 
/* 143 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isOptionSet(int options, int option) {
/* 148 */     return ((options & option) == option);
/*     */   }
/*     */ 
/*     */   
/*     */   private void appendToolbar(StringBuilder sb, String... items) {
/* 153 */     for (int i = 0; i < items.length; i++) {
/*     */       
/* 155 */       if (sb.length() > 0)
/* 156 */         sb.append("|"); 
/* 157 */       sb.append(items[i]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 163 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperationListener(ILbsRichTextSaveLoadListener listener) {
/* 174 */     this.m_OperationListener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsRichTextSaveLoadListener getOperationListener() {
/* 180 */     return this.m_OperationListener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveDocument() {
/* 186 */     if (this.m_OperationListener != null)
/* 187 */       this.m_OperationListener.saveRichTextDocument(this); 
/* 188 */     setCursor(Cursor.getDefaultCursor());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadDocument() {
/* 194 */     if (this.m_OperationListener != null) {
/*     */       
/* 196 */       InputStream inStream = this.m_OperationListener.loadRichTextDocument();
/* 197 */       if (inStream != null) {
/*     */         
/*     */         try {
/*     */           
/* 201 */           this.m_EkitCore.loadDocument(inStream);
/*     */         }
/* 203 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentContents(byte[] doc) {
/* 213 */     if (doc == null)
/*     */       return; 
/* 215 */     String text = new String(doc);
/* 216 */     if (text.indexOf("<html>") < 0) {
/*     */       
/* 218 */       ByteArrayInputStream inStream = new ByteArrayInputStream(doc);
/*     */       
/*     */       try {
/* 221 */         RTFEditorKit kitRtf = new RTFEditorKit();
/* 222 */         Document document = kitRtf.createDefaultDocument();
/* 223 */         kitRtf.read(inStream, document, 0);
/* 224 */         kitRtf = null;
/* 225 */         EditorKit kitHtml = new HTMLEditorKit();
/* 226 */         Writer writer = new StringWriter();
/* 227 */         kitHtml.write(writer, document, 0, document.getLength());
/* 228 */         getTextPane().setText(writer.toString());
/*     */       }
/* 230 */       catch (Exception exception) {}
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 236 */       getTextPane().setText(text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getDocumentContents() {
/* 243 */     if (this.m_OperationListener != null) {
/*     */       
/* 245 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 246 */       this.m_OperationListener.save(this, outStream);
/* 247 */       return outStream.toByteArray();
/*     */     } 
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultStyledDocument getRichTextDocument() {
/* 254 */     return (DefaultStyledDocument)this.m_EkitCore.getExtendedHtmlDoc();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultEditorKit getEditorKit() {
/* 260 */     return (DefaultEditorKit)this.m_EkitCore.getExtendedHtmlEditorKit();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JTextPane getTextPane() {
/* 266 */     return this.m_EkitCore.getTextPane();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JTextArea getTextArea() {
/* 272 */     return this.m_EkitCore.getSourcePane();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPaneFocusListener(FocusListener l) {
/* 278 */     getTextPane().addFocusListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putPaneClientProperty(Object key, Object value) {
/* 284 */     getTextPane().putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 290 */     return this.m_EkitCore.getTextPane().getText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertHtml(HTMLDocument doc, int offset, String html, int popDepth, int pushDepth, HTML.Tag insertTag) {
/*     */     try {
/* 297 */       if (doc != null) {
/* 298 */         ((ExtendedHTMLEditorKit)getEditorKit()).insertHTML(doc, offset, html, popDepth, pushDepth, insertTag);
/*     */       }
/* 300 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertString(int offset, String str, AttributeSet a) {
/*     */     try {
/* 311 */       getRichTextDocument().insertString(offset, str, a);
/*     */     }
/* 313 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 321 */     return getTextPane().getSelectedText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/* 327 */     return getTextPane().getSelectionStart();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 333 */     return getTextPane().getSelectionEnd();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 339 */     getTextPane().setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int pos) {
/* 346 */     getTextPane().setCaretPosition(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 353 */     return getTextPane().getCaretPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void select(int first, int end) {
/* 359 */     getTextPane().select(first, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaneEnabled(boolean enable) {
/* 366 */     getTextPane().setEnabled(enable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void documentReplace(int offset, int length, String text, AttributeSet attrs) {
/*     */     try {
/* 375 */       getRichTextDocument().replace(offset, length, text, attrs);
/*     */     }
/* 377 */     catch (Exception e) {
/*     */       
/* 379 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCharacterElement(int pos) {
/* 387 */     return getRichTextDocument().getCharacterElement(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRichDocText(int offset, int length) {
/*     */     try {
/* 395 */       return getRichTextDocument().getText(offset, length);
/*     */     }
/* 397 */     catch (BadLocationException e) {
/*     */       
/* 399 */       e.printStackTrace();
/*     */       
/* 401 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRichDocTextLenght() {
/* 407 */     return getRichTextDocument().getLength();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsRichTextEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */