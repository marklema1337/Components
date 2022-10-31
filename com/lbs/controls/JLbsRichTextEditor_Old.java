/*      */ package com.lbs.controls;
/*      */ 
/*      */ import com.lbs.control.interfaces.ILbsRichTextEditor;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.AdjustmentEvent;
/*      */ import java.awt.event.AdjustmentListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.AbstractButton;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JColorChooser;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JScrollBar;
/*      */ import javax.swing.JTextPane;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.event.CaretEvent;
/*      */ import javax.swing.event.CaretListener;
/*      */ import javax.swing.text.AttributeSet;
/*      */ import javax.swing.text.BadLocationException;
/*      */ import javax.swing.text.DefaultEditorKit;
/*      */ import javax.swing.text.DefaultStyledDocument;
/*      */ import javax.swing.text.Element;
/*      */ import javax.swing.text.MutableAttributeSet;
/*      */ import javax.swing.text.SimpleAttributeSet;
/*      */ import javax.swing.text.StyleConstants;
/*      */ import javax.swing.text.StyleContext;
/*      */ import javax.swing.text.rtf.RTFEditorKit;
/*      */ import javax.swing.undo.CannotUndoException;
/*      */ import javax.swing.undo.UndoManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsRichTextEditor_Old
/*      */   extends JLbsPanel
/*      */   implements ActionListener, MouseListener, KeyListener, ILbsRichTextEditor
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   public static final String LOAD_FILE_CHOOSER_TITLE = "Load";
/*      */   public static final String SAVE_FILE_CHOOSER_TITLE = "Save";
/*      */   private static final String COLOR_DIALOG_TITLE = "Color Chooser";
/*      */   private static final String WORDWRAP_DIALOG_TITLE = "Word Wrap";
/*      */   private static final String WORDWRAP_NOWRAP_TEXT = "No Wrap";
/*      */   private static final String WORDWRAP_WRAPTOWINDOW_TEXT = "Wrap to window";
/*      */   private static final String OK_BUTTON_TEXT = "OK";
/*      */   private static final String CANCEL_BUTTON = "Cancel";
/*      */   private static final String CONTEXTMENU_CUT = "Cut";
/*      */   private static final String CONTEXTMENU_COPY = "Copy";
/*      */   private static final String CONTEXTMENU_PASTE = "Paste";
/*      */   private static final String CONTEXTMENU_SELECTALL = "Select All";
/*   92 */   private static final String[] fontSizes = new String[] { "", "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", 
/*   93 */       "36", "48", "72" };
/*      */   
/*      */   private static final String BOLD_BUTTON = "bold_button";
/*      */   
/*      */   private static final String ITALIC_BUTTON = "italic_button";
/*      */   
/*      */   private static final String UNDERLINE_BUTTON = "underline_button";
/*      */   
/*      */   private static final String ALIGN_LEFT_BUTTON = "left_button";
/*      */   
/*      */   private static final String ALIGN_CENTER_BUTTON = "center_button";
/*      */   
/*      */   private static final String ALIGN_RIGHT_BUTTON = "right_button";
/*      */   
/*      */   public static final String PICTURE_DIALOG_TITLE = "Select a picture to insert into document";
/*      */   
/*      */   public static final String DOCUMENT_TYPE_ERROR_MSG = "Text pane's document isn't an AbstractDocument!";
/*      */   
/*      */   public static final String DIALOG_ERROR_MSG = "The number must be between 1 and 1638";
/*      */   
/*      */   public static final String RTF_FILE_DESCRIPTION = "Rich text files";
/*      */   
/*      */   public static final String PNG_BOLD_CHECKED = "BoldChecked.png";
/*      */   
/*      */   public static final String PNG_BOLD_HALFCHECKED = "BoldHalfchecked.png";
/*      */   
/*      */   public static final String PNG_BOLD_UNCHECKED = "BoldUnchecked.png";
/*      */   
/*      */   public static final String PNG_ITALIC_CHECKED = "ItalicChecked.png";
/*      */   
/*      */   public static final String PNG_ITALIC_HALFCHECKED = "ItalicHalfchecked.png";
/*      */   
/*      */   public static final String PNG_ITALIC_UNCHECKED = "ItalicUnchecked.png";
/*      */   
/*      */   public static final String PNG_UNDERLINE_CHECKED = "UnderlineChecked.png";
/*      */   
/*      */   public static final String PNG_UNDERLINE_HALFCHECKED = "UnderlineHalfchecked.png";
/*      */   
/*      */   public static final String PNG_UNDERLINE_UNCHECKED = "UnderlineUnchecked.png";
/*      */   
/*      */   public static final String PNG_COLOR_PANE_BUTTON = "ColorPane.png";
/*      */   
/*      */   public static final String PNG_ALIGN_LEFT_CHECKED = "AlignLeftChecked.png";
/*      */   
/*      */   public static final String PNG_ALIGN_LEFT_UNCHECKED = "AlignLeftUnchecked.png";
/*      */   
/*      */   public static final String PNG_ALIGN_CENTER_CHECKED = "AlignCenterChecked.png";
/*      */   
/*      */   public static final String PNG_ALIGN_CENTER_UNCHECKED = "AlignCenterUnchecked.png";
/*      */   
/*      */   public static final String PNG_ALIGN_RIGHT_CHECKED = "AlignRightChecked.png";
/*      */   
/*      */   public static final String PNG_ALIGN_RIGHT_UNCHECKED = "AlignRightUnchecked.png";
/*      */   
/*      */   public static final String PNG_ICON_ATTACH_BUTTON = "IconAttach.png";
/*      */   
/*      */   public static final String PNG_WRAP_BUTTON = "Wrap.png";
/*      */   
/*      */   public static final String PNG_NEW_BUTTON = "New.png";
/*      */   
/*      */   public static final String PNG_SAVE_BUTTON = "Save.png";
/*      */   
/*      */   public static final String PNG_LOAD_BUTTON = "Open.png";
/*      */   
/*      */   public static final String PNG_PRINT_BUTTON = "Print.png";
/*      */   
/*      */   public static final String PNG_UNDO_BUTTON = "Undo.png";
/*      */   
/*      */   public static final String PNG_REDO_BUTTON = "Redo.png";
/*      */   
/*      */   public static final int BUTTON_UNCHECKED = 0;
/*      */   
/*      */   public static final int BUTTON_HALFCHECKED = 1;
/*      */   
/*      */   public static final int BUTTON_CHECKED = 2;
/*      */   
/*      */   public static final int OPTION_SAVE_BUTTON = 0;
/*      */   
/*      */   public static final int OPTION_LOAD_BUTTON = 1;
/*      */   
/*      */   public static final int OPTION_PRINT_BUTTON = 2;
/*      */   public static final int RTF_FONT_BOLD = 1;
/*      */   public static final int RTF_FONT_ITALIC = 2;
/*      */   public static final int RTF_FONT_UNDERLINE = 4;
/*      */   public JLbsScrollTextPane m_scrollTextPane;
/*      */   protected ILbsRichTextSaveLoadListener m_OperationListener;
/*      */   private JComboBox m_fontFamily;
/*      */   private JComboBox m_fontSize;
/*      */   private JPanel m_formatBar;
/*      */   private JPanel m_mainPanel;
/*      */   private JLbsHorizontalRuler m_horizontalRuler;
/*      */   private JLbsVerticalRuler m_verticalRuler;
/*      */   private JToggleButton m_boldButton;
/*      */   private JToggleButton m_italicButton;
/*      */   private JToggleButton m_underlineButton;
/*      */   private JButton m_colorDialogButton;
/*      */   private JButton m_leftAlignButton;
/*      */   private JButton m_centerAlignButton;
/*      */   private JButton m_rightAlignButton;
/*      */   private JFileChooser m_fileChooser;
/*      */   private JLbsFileFilter m_fileFilter;
/*      */   private JDialog m_colorDialog;
/*      */   private JButton m_colorDialogCancelButton;
/*      */   private JButton m_colorDialogOkButton;
/*      */   private JColorChooser m_jColorChooser;
/*      */   private JDialog m_wordWrapDialog;
/*      */   private JRadioButton m_wordWrapON;
/*      */   private JRadioButton m_wordWrapOFF;
/*      */   private JButton m_wordWrapDialogOkButton;
/*      */   private StyleContext m_sc;
/*      */   private MutableAttributeSet m_mas;
/*      */   public JLbsDefaultStyledDocument m_dse;
/*      */   private RTFEditorKit m_rtfkit;
/*      */   private BorderLayout m_borderLayout;
/*      */   private FlowLayout m_formatBarLayout;
/*      */   private BorderLayout m_mainPanelLayout;
/*      */   private JButton m_saveButton;
/*      */   private JButton m_loadButton;
/*      */   private JButton m_undoButton;
/*      */   private JButton m_redoButton;
/*      */   private JButton m_wordWrapButton;
/*      */   private JButton m_printButton;
/*      */   private boolean m_fontSizeAction;
/*      */   private boolean m_fontFamilyAction;
/*      */   private Action m_cutAction;
/*      */   private Action m_copyAction;
/*      */   private Action m_pasteAction;
/*      */   private Action m_selectAllAction;
/*      */   protected UndoAction undoAction;
/*      */   protected RedoAction redoAction;
/*      */   protected UndoManager manager;
/*      */   JPopupMenu m_contextMenu;
/*      */   
/*      */   public JLbsRichTextEditor_Old(int option) {
/*  227 */     internalInit();
/*      */     
/*      */     try {
/*  230 */       initialize(option);
/*      */     }
/*  232 */     catch (Exception ex) {
/*      */       
/*  234 */       if (JLbsConstants.DEBUG) {
/*  235 */         ex.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public JLbsRichTextEditor_Old() {
/*  241 */     internalInit();
/*      */     
/*      */     try {
/*  244 */       initialize(3);
/*      */     }
/*  246 */     catch (Exception ex) {
/*      */       
/*  248 */       if (JLbsConstants.DEBUG) {
/*  249 */         ex.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void internalInit() {
/*  255 */     this.m_scrollTextPane = new JLbsScrollTextPane();
/*  256 */     this.m_fontFamily = new JComboBox();
/*  257 */     this.m_fontSize = new JComboBox();
/*  258 */     this.m_formatBar = new JPanel();
/*  259 */     this.m_mainPanel = new JPanel();
/*      */ 
/*      */     
/*  262 */     this.m_boldButton = new JToggleButton();
/*  263 */     this.m_italicButton = new JToggleButton();
/*  264 */     this.m_underlineButton = new JToggleButton();
/*  265 */     this.m_colorDialogButton = new JButton();
/*      */     
/*  267 */     this.m_leftAlignButton = new JButton();
/*  268 */     this.m_centerAlignButton = new JButton();
/*  269 */     this.m_rightAlignButton = new JButton();
/*      */     
/*  271 */     this.m_jColorChooser = new JColorChooser();
/*  272 */     this.m_fileChooser = new JFileChooser();
/*  273 */     this.m_fileFilter = new JLbsFileFilter();
/*  274 */     this.m_fileFilter.addExtension("rtf");
/*  275 */     this.m_fileFilter.setDescription("Rich text files");
/*  276 */     this.m_fileChooser.setFileFilter(this.m_fileFilter);
/*      */     
/*  278 */     this.m_colorDialog = new JDialog();
/*  279 */     this.m_colorDialogCancelButton = new JButton();
/*  280 */     this.m_colorDialogOkButton = new JButton();
/*      */     
/*  282 */     this.m_wordWrapDialog = new JDialog();
/*  283 */     this.m_wordWrapON = new JRadioButton();
/*  284 */     this.m_wordWrapOFF = new JRadioButton();
/*  285 */     this.m_wordWrapDialogOkButton = new JButton();
/*      */     
/*  287 */     this.m_sc = new StyleContext();
/*  288 */     this.m_mas = new SimpleAttributeSet();
/*  289 */     this.m_dse = new JLbsDefaultStyledDocument(this.m_sc);
/*  290 */     this.m_rtfkit = new RTFEditorKit();
/*      */     
/*  292 */     this.m_borderLayout = new BorderLayout();
/*  293 */     this.m_formatBarLayout = new FlowLayout(0, 1, 4);
/*  294 */     this.m_mainPanelLayout = new BorderLayout();
/*      */     
/*  296 */     this.m_saveButton = new JButton();
/*  297 */     this.m_loadButton = new JButton();
/*  298 */     this.m_undoButton = new JButton();
/*  299 */     this.m_redoButton = new JButton();
/*  300 */     this.m_wordWrapButton = new JButton();
/*  301 */     this.m_printButton = new JButton();
/*      */     
/*  303 */     this.m_fontSizeAction = false;
/*  304 */     this.m_fontFamilyAction = false;
/*  305 */     this.manager = new UndoManager();
/*  306 */     this.m_dse.addUndoableEditListener(this.manager);
/*  307 */     this.m_contextMenu = new JPopupMenu();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeButtons() {
/*  313 */     this.m_leftAlignButton.setSelected(false);
/*  314 */     this.m_centerAlignButton.setSelected(false);
/*  315 */     this.m_rightAlignButton.setSelected(false);
/*  316 */     setButtonIcon(this.m_leftAlignButton, 0, "left_button");
/*  317 */     setButtonIcon(this.m_centerAlignButton, 0, "center_button");
/*  318 */     setButtonIcon(this.m_rightAlignButton, 0, "right_button");
/*  319 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  320 */     int end = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  321 */     StyleConstants.setAlignment(this.m_mas, 0);
/*  322 */     this.m_dse.setParagraphAttributes(start, end - start, this.m_mas, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialize(int compOption) throws Exception {
/*  331 */     initializeComboFontSize();
/*  332 */     initializeComboFontFamily();
/*  333 */     initializeImageIcons();
/*  334 */     initializeButtons();
/*      */     
/*  336 */     addKeyListener(this);
/*  337 */     this.m_scrollTextPane.getTextPane().addCaretListener(new CaretListener()
/*      */         {
/*      */           public void caretUpdate(CaretEvent ce)
/*      */           {
/*  341 */             JLbsRichTextEditor_Old.this.caretPositionChange(ce);
/*      */           }
/*      */         });
/*  344 */     this.m_scrollTextPane.getTextPane().setEditorKit(this.m_rtfkit);
/*  345 */     this.m_scrollTextPane.getTextPane().addKeyListener(this);
/*  346 */     this.m_scrollTextPane.getTextPane().setDocument(this.m_dse);
/*  347 */     this.m_scrollTextPane.getTextPane().setCaretPosition(0);
/*  348 */     this.m_scrollTextPane.setWordWrap(true);
/*      */     
/*  350 */     FontComboActionListener listener = new FontComboActionListener(this);
/*  351 */     AdjustmentListener adjustmentListener = new MyAdjustmentListener();
/*  352 */     this.m_scrollTextPane.getScrollPane().getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);
/*  353 */     this.m_scrollTextPane.getScrollPane().getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
/*      */     
/*  355 */     this.m_fontFamily.addActionListener(listener);
/*  356 */     this.m_fontSize.addActionListener(listener);
/*      */     
/*  358 */     this.m_saveButton.addActionListener(this);
/*  359 */     this.m_loadButton.addActionListener(this);
/*      */     
/*  361 */     this.undoAction = new UndoAction();
/*  362 */     this.redoAction = new RedoAction();
/*  363 */     this.m_undoButton.addActionListener(this.undoAction);
/*  364 */     this.m_redoButton.addActionListener(this.redoAction);
/*      */     
/*  366 */     this.m_boldButton.addMouseListener(this);
/*  367 */     this.m_italicButton.addMouseListener(this);
/*  368 */     this.m_underlineButton.addMouseListener(this);
/*      */     
/*  370 */     this.m_colorDialogButton.addActionListener(this);
/*  371 */     this.m_colorDialogCancelButton.addActionListener(this);
/*  372 */     this.m_colorDialogOkButton.addActionListener(this);
/*      */     
/*  374 */     this.m_wordWrapButton.addActionListener(this);
/*  375 */     this.m_wordWrapOFF.addActionListener(this);
/*  376 */     this.m_wordWrapON.addActionListener(this);
/*  377 */     this.m_wordWrapDialogOkButton.addActionListener(this);
/*      */     
/*  379 */     this.m_printButton.addActionListener(this);
/*      */     
/*  381 */     this.m_leftAlignButton.addMouseListener(this);
/*  382 */     this.m_centerAlignButton.addMouseListener(this);
/*  383 */     this.m_rightAlignButton.addMouseListener(this);
/*      */     
/*  385 */     this.m_cutAction = getActionByName("cut-to-clipboard");
/*  386 */     this.m_copyAction = getActionByName("copy-to-clipboard");
/*  387 */     this.m_pasteAction = getActionByName("paste-from-clipboard");
/*  388 */     this.m_selectAllAction = getActionByName("select-all");
/*      */     
/*  390 */     setLayout(this.m_borderLayout);
/*  391 */     this.m_mainPanel.setPreferredSize(new Dimension(20, 60));
/*  392 */     this.m_mainPanel.setLayout(this.m_mainPanelLayout);
/*  393 */     this.m_formatBar.setPreferredSize(new Dimension(20, 40));
/*  394 */     this.m_formatBar.setLayout(this.m_formatBarLayout);
/*      */     
/*  396 */     this.m_horizontalRuler.setPreferredSize(new Dimension(10, 20));
/*  397 */     this.m_horizontalRuler.setLayout((LayoutManager)null);
/*  398 */     this.m_verticalRuler.setPreferredSize(new Dimension(20, 10));
/*  399 */     this.m_verticalRuler.setLayout((LayoutManager)null);
/*      */     
/*  401 */     this.m_saveButton.setPreferredSize(new Dimension(22, 22));
/*  402 */     this.m_loadButton.setPreferredSize(new Dimension(22, 22));
/*  403 */     this.m_printButton.setPreferredSize(new Dimension(22, 22));
/*  404 */     this.m_fontFamily.setPreferredSize(new Dimension(149, 22));
/*  405 */     this.m_fontFamily.setSelectedIndex(3);
/*  406 */     this.m_fontSize.setPreferredSize(new Dimension(52, 22));
/*  407 */     this.m_fontSize.setSelectedIndex(5);
/*  408 */     this.m_boldButton.setPreferredSize(new Dimension(22, 22));
/*  409 */     this.m_italicButton.setPreferredSize(new Dimension(22, 22));
/*  410 */     this.m_underlineButton.setPreferredSize(new Dimension(22, 22));
/*  411 */     this.m_colorDialogButton.setPreferredSize(new Dimension(22, 22));
/*  412 */     this.m_leftAlignButton.setPreferredSize(new Dimension(22, 22));
/*  413 */     this.m_centerAlignButton.setPreferredSize(new Dimension(22, 22));
/*  414 */     this.m_rightAlignButton.setPreferredSize(new Dimension(22, 22));
/*  415 */     this.m_undoButton.setPreferredSize(new Dimension(22, 22));
/*  416 */     this.m_redoButton.setPreferredSize(new Dimension(22, 22));
/*  417 */     this.m_wordWrapButton.setPreferredSize(new Dimension(22, 22));
/*      */     
/*  419 */     this.m_jColorChooser.setBounds(new Rectangle(0, 0, 440, 333));
/*  420 */     this.m_jColorChooser.setColor(Color.black);
/*  421 */     this.m_colorDialogCancelButton.setBounds(new Rectangle(365, 336, 75, 23));
/*  422 */     this.m_colorDialogCancelButton.setText("Cancel");
/*  423 */     this.m_colorDialogOkButton.setBounds(new Rectangle(284, 336, 75, 23));
/*  424 */     this.m_colorDialogOkButton.setText("OK");
/*      */     
/*  426 */     this.m_colorDialog.getContentPane().setLayout((LayoutManager)null);
/*  427 */     this.m_colorDialog.setModal(true);
/*  428 */     this.m_colorDialog.setSize(448, 400);
/*  429 */     this.m_colorDialog.setResizable(false);
/*  430 */     this.m_colorDialog.setTitle("Color Chooser");
/*  431 */     this.m_colorDialog.getContentPane().add(this.m_jColorChooser);
/*  432 */     this.m_colorDialog.getContentPane().add(this.m_jColorChooser, (Object)null);
/*  433 */     this.m_colorDialog.getContentPane().add(this.m_colorDialogCancelButton);
/*  434 */     this.m_colorDialog.getContentPane().add(this.m_colorDialogOkButton);
/*      */     
/*  436 */     this.m_wordWrapDialogOkButton.setBounds(new Rectangle(80, 52, 75, 23));
/*  437 */     this.m_wordWrapDialogOkButton.setText("OK");
/*  438 */     this.m_wordWrapOFF.setBounds(new Rectangle(5, 5, 172, 26));
/*  439 */     this.m_wordWrapOFF.setText("No Wrap");
/*  440 */     this.m_wordWrapOFF.setSelected(true);
/*  441 */     this.m_wordWrapON.setBounds(new Rectangle(5, 27, 172, 26));
/*  442 */     this.m_wordWrapON.setText("Wrap to window");
/*      */     
/*  444 */     this.m_wordWrapDialog.getContentPane().setLayout((LayoutManager)null);
/*  445 */     this.m_wordWrapDialog.setModal(true);
/*  446 */     this.m_wordWrapDialog.setSize(165, 110);
/*  447 */     this.m_wordWrapDialog.setResizable(false);
/*  448 */     this.m_wordWrapDialog.setTitle("Word Wrap");
/*  449 */     this.m_wordWrapDialog.getContentPane().add(this.m_wordWrapDialogOkButton);
/*  450 */     this.m_wordWrapDialog.getContentPane().add(this.m_wordWrapOFF);
/*  451 */     this.m_wordWrapDialog.getContentPane().add(this.m_wordWrapON);
/*      */     
/*  453 */     int option = compOption & 0x1;
/*  454 */     if (option == 1)
/*  455 */       this.m_formatBar.add(this.m_loadButton); 
/*  456 */     option = 0;
/*  457 */     if (option == 0)
/*  458 */       this.m_formatBar.add(this.m_saveButton); 
/*  459 */     option = compOption & 0x2;
/*  460 */     if (option == 2) {
/*  461 */       this.m_formatBar.add(this.m_printButton);
/*      */     }
/*  463 */     this.m_formatBar.add(this.m_fontFamily);
/*  464 */     this.m_formatBar.add(this.m_fontSize);
/*  465 */     this.m_formatBar.add(this.m_boldButton);
/*  466 */     this.m_formatBar.add(this.m_italicButton);
/*  467 */     this.m_formatBar.add(this.m_underlineButton);
/*  468 */     this.m_formatBar.add(this.m_colorDialogButton);
/*  469 */     this.m_formatBar.add(this.m_leftAlignButton);
/*  470 */     this.m_formatBar.add(this.m_centerAlignButton);
/*  471 */     this.m_formatBar.add(this.m_rightAlignButton);
/*  472 */     this.m_formatBar.add(this.m_undoButton);
/*  473 */     this.m_formatBar.add(this.m_redoButton);
/*  474 */     this.m_formatBar.add(this.m_wordWrapButton);
/*  475 */     this.m_formatBar.add(this.m_horizontalRuler);
/*      */     
/*  477 */     this.m_mainPanel.add(this.m_formatBar, "North");
/*  478 */     this.m_mainPanel.add(this.m_horizontalRuler, "South");
/*  479 */     add(this.m_mainPanel, "North");
/*  480 */     add(this.m_scrollTextPane, "Center");
/*  481 */     add(this.m_verticalRuler, "West");
/*      */     
/*  483 */     ActionListener menuListener = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent event)
/*      */         {
/*  487 */           if (event.getActionCommand().equals("Cut")) {
/*  488 */             JLbsRichTextEditor_Old.this.m_cutAction.actionPerformed(event);
/*  489 */           } else if (event.getActionCommand().equals("Copy")) {
/*  490 */             JLbsRichTextEditor_Old.this.m_copyAction.actionPerformed(event);
/*  491 */           } else if (event.getActionCommand().equals("Paste")) {
/*  492 */             JLbsRichTextEditor_Old.this.m_pasteAction.actionPerformed(event);
/*  493 */           } else if (event.getActionCommand().equals("Select All")) {
/*  494 */             JLbsRichTextEditor_Old.this.m_selectAllAction.actionPerformed(event);
/*      */           } 
/*      */         }
/*      */       };
/*      */     JMenuItem item;
/*  499 */     this.m_contextMenu.add(item = new JMenuItem("Cut"));
/*  500 */     item.addActionListener(menuListener);
/*  501 */     this.m_contextMenu.add(item = new JMenuItem("Copy"));
/*  502 */     item.addActionListener(menuListener);
/*  503 */     this.m_contextMenu.add(item = new JMenuItem("Paste"));
/*  504 */     item.addActionListener(menuListener);
/*  505 */     this.m_contextMenu.addSeparator();
/*  506 */     this.m_contextMenu.add(item = new JMenuItem("Select All"));
/*  507 */     item.addActionListener(menuListener);
/*  508 */     this.m_scrollTextPane.getTextPane().addMouseListener(new MousePopupListener());
/*      */     
/*  510 */     setCurrentFont();
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsCharProps getCurrentCharProps() {
/*  515 */     if (this.m_fontFamily.getSelectedIndex() == 0)
/*      */     {
/*  517 */       this.m_fontFamily.setSelectedIndex(3);
/*      */     }
/*  519 */     if (this.m_fontSize.getSelectedIndex() == 0)
/*      */     {
/*  521 */       this.m_fontSize.setSelectedIndex(5);
/*      */     }
/*  523 */     return new JLbsCharProps(this.m_boldButton.isSelected(), this.m_italicButton.isSelected(), this.m_underlineButton.isSelected(), 
/*  524 */         this.m_leftAlignButton.isSelected(), this.m_centerAlignButton.isSelected(), this.m_rightAlignButton.isSelected(), this.m_fontFamily
/*  525 */         .getSelectedItem().toString(), Integer.parseInt(this.m_fontSize.getSelectedItem().toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void setCurrentFont() {
/*  530 */     setFontFamilyAttribute((String)this.m_fontFamily.getSelectedItem());
/*  531 */     setFontSizeAttribute(Integer.parseInt((String)this.m_fontSize.getSelectedItem()));
/*  532 */     StyleConstants.setBold(this.m_mas, this.m_boldButton.isSelected());
/*  533 */     StyleConstants.setItalic(this.m_mas, this.m_italicButton.isSelected());
/*  534 */     StyleConstants.setUnderline(this.m_mas, this.m_underlineButton.isSelected());
/*  535 */     StyleConstants.setForeground(this.m_mas, this.m_jColorChooser.getSelectionModel().getSelectedColor());
/*  536 */     this.m_mas = this.m_rtfkit.getInputAttributes();
/*  537 */     this.m_mas.addAttributes(this.m_mas);
/*      */   }
/*      */ 
/*      */   
/*      */   private Action getActionByName(String actionName) {
/*  542 */     Action[] actions = this.m_scrollTextPane.getTextPane().getActions();
/*  543 */     for (int i = 0; i < actions.length; i++) {
/*      */       
/*  545 */       if (actions[i].getValue("Name").equals(actionName))
/*      */       {
/*  547 */         return actions[i];
/*      */       }
/*      */     } 
/*  550 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void initializeImageIcons() {
/*  555 */     ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "BoldUnchecked.png");
/*  556 */     this.m_boldButton.setIcon(icon);
/*  557 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "ItalicUnchecked.png");
/*  558 */     this.m_italicButton.setIcon(icon);
/*  559 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "UnderlineUnchecked.png");
/*  560 */     this.m_underlineButton.setIcon(icon);
/*  561 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "ColorPane.png");
/*  562 */     this.m_colorDialogButton.setIcon(icon);
/*      */     
/*  564 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignLeftUnchecked.png");
/*  565 */     this.m_leftAlignButton.setIcon(icon);
/*  566 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignCenterUnchecked.png");
/*  567 */     this.m_centerAlignButton.setIcon(icon);
/*  568 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignRightUnchecked.png");
/*  569 */     this.m_rightAlignButton.setIcon(icon);
/*      */     
/*  571 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Save.png");
/*  572 */     this.m_saveButton.setIcon(icon);
/*  573 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Open.png");
/*  574 */     this.m_loadButton.setIcon(icon);
/*  575 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Undo.png");
/*  576 */     this.m_undoButton.setIcon(icon);
/*  577 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Redo.png");
/*  578 */     this.m_redoButton.setIcon(icon);
/*  579 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Wrap.png");
/*  580 */     this.m_wordWrapButton.setIcon(icon);
/*  581 */     icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "Print.png");
/*  582 */     this.m_printButton.setIcon(icon);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setButtonIcon(AbstractButton button, int buttonState, String buttonName) {
/*  588 */     if (buttonState == 0) {
/*      */       
/*  590 */       if (buttonName.equals("bold_button")) {
/*      */         
/*  592 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "BoldUnchecked.png");
/*  593 */         button.setIcon(icon);
/*      */       }
/*  595 */       else if (buttonName.equals("italic_button")) {
/*      */         
/*  597 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "ItalicUnchecked.png");
/*  598 */         button.setIcon(icon);
/*      */       }
/*  600 */       else if (buttonName.equals("underline_button")) {
/*      */         
/*  602 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "UnderlineUnchecked.png");
/*  603 */         button.setIcon(icon);
/*      */       }
/*  605 */       else if (buttonName.equals("left_button")) {
/*      */         
/*  607 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignLeftUnchecked.png");
/*  608 */         button.setIcon(icon);
/*      */       }
/*  610 */       else if (buttonName.equals("center_button")) {
/*      */         
/*  612 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignCenterUnchecked.png");
/*  613 */         button.setIcon(icon);
/*      */       }
/*  615 */       else if (buttonName.equals("right_button")) {
/*      */         
/*  617 */         ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignRightUnchecked.png");
/*  618 */         button.setIcon(icon);
/*      */       } 
/*      */     } else {
/*      */       
/*  622 */       if (buttonState == 1)
/*      */         return; 
/*  624 */       if (buttonState == 2) {
/*      */         
/*  626 */         if (buttonName.equals("bold_button")) {
/*      */           
/*  628 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "BoldChecked.png");
/*  629 */           button.setIcon(icon);
/*      */         }
/*  631 */         else if (buttonName.equals("italic_button")) {
/*      */           
/*  633 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "ItalicChecked.png");
/*  634 */           button.setIcon(icon);
/*      */         }
/*  636 */         else if (buttonName.equals("underline_button")) {
/*      */           
/*  638 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "UnderlineChecked.png");
/*  639 */           button.setIcon(icon);
/*      */         }
/*  641 */         else if (buttonName.equals("left_button")) {
/*      */           
/*  643 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignLeftChecked.png");
/*  644 */           button.setIcon(icon);
/*      */         }
/*  646 */         else if (buttonName.equals("center_button")) {
/*      */           
/*  648 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignCenterChecked.png");
/*  649 */           button.setIcon(icon);
/*      */         }
/*  651 */         else if (buttonName.equals("right_button")) {
/*      */           
/*  653 */           ImageIcon icon = JLbsControlHelper.getImageIcon(JLbsRichTextEditor_Old.class, "AlignRightChecked.png");
/*  654 */           button.setIcon(icon);
/*      */         } 
/*      */       } else {
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void initializeComboFontFamily() {
/*  663 */     GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  664 */     String[] k = ge1.getAvailableFontFamilyNames();
/*  665 */     this.m_fontFamily.addItem("");
/*  666 */     for (int i = 0; i < k.length; i++)
/*      */     {
/*  668 */       this.m_fontFamily.addItem(k[i]);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void initializeComboFontSize() {
/*  674 */     for (int i = 0; i < fontSizes.length; i++)
/*      */     {
/*  676 */       this.m_fontSize.addItem(fontSizes[i]);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void savePreviousCharProps(int start, int finish) {
/*  682 */     int length = finish - start;
/*  683 */     int offset = start;
/*      */     
/*  685 */     this.m_dse.beginUpdate();
/*  686 */     for (int i = 0; i < length; i++, offset++) {
/*      */       
/*  688 */       JLbsCharProps c = getCharProps(offset);
/*  689 */       this.m_dse.postEditCharProps(c);
/*      */     } 
/*  691 */     this.m_dse.endUpdate();
/*      */   }
/*      */ 
/*      */   
/*      */   private void savePreviousParagraphProps() {
/*  696 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  697 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  698 */     int length = finish - start;
/*  699 */     int offset = start;
/*      */     
/*  701 */     this.m_dse.beginUpdate();
/*  702 */     for (int i = 0; i < length; i++, offset++) {
/*      */       
/*  704 */       JLbsCharProps c = getCharProps(offset);
/*  705 */       this.m_dse.postEditParagraphProps(c);
/*      */     } 
/*  707 */     this.m_dse.endUpdate();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setColorAttribute(Color color) {
/*  712 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  713 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  714 */     if (start != finish) {
/*      */       
/*  716 */       savePreviousCharProps(start, finish);
/*  717 */       JLbsCharProps[] selectedText = getStyledText(start, finish);
/*  718 */       JLbsCharProps c = null;
/*  719 */       int offset = start;
/*  720 */       for (int i = 0; i < selectedText.length; i++, offset++)
/*      */       {
/*  722 */         c = selectedText[i];
/*  723 */         MutableAttributeSet mas = c.getAttributeSet();
/*  724 */         StyleConstants.setForeground(mas, color);
/*  725 */         this.m_dse.setCharacterAttributes(offset, 1, mas, false);
/*      */       }
/*      */     
/*      */     }
/*  729 */     else if (start == finish) {
/*      */       
/*  731 */       StyleConstants.setForeground(this.m_mas, color);
/*  732 */       this.m_mas = this.m_rtfkit.getInputAttributes();
/*  733 */       this.m_mas.addAttributes(this.m_mas);
/*      */     } 
/*  735 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setStyleAttribute(int styleType, boolean style) {
/*  740 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  741 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  742 */     boolean bold = false;
/*  743 */     boolean italic = false;
/*  744 */     boolean underline = false;
/*  745 */     if (start != finish) {
/*      */       
/*  747 */       savePreviousCharProps(start, finish);
/*  748 */       JLbsCharProps[] selectedText = getStyledText(start, finish);
/*  749 */       JLbsCharProps c = null;
/*  750 */       int offset = start;
/*  751 */       for (int i = 0; i < selectedText.length; i++, offset++)
/*      */       {
/*  753 */         c = selectedText[i];
/*  754 */         MutableAttributeSet mas = c.getAttributeSet();
/*  755 */         switch (styleType) {
/*      */           
/*      */           case 0:
/*  758 */             bold = style;
/*  759 */             StyleConstants.setBold(mas, bold);
/*      */             break;
/*      */           case 1:
/*  762 */             italic = style;
/*  763 */             StyleConstants.setItalic(mas, italic);
/*      */             break;
/*      */           case 2:
/*  766 */             underline = style;
/*  767 */             StyleConstants.setUnderline(mas, underline);
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  772 */         this.m_dse.setCharacterAttributes(offset, 1, mas, false);
/*      */       }
/*      */     
/*  775 */     } else if (start == finish) {
/*      */       
/*  777 */       switch (styleType) {
/*      */         
/*      */         case 0:
/*  780 */           bold = style;
/*  781 */           StyleConstants.setBold(this.m_mas, bold);
/*      */           break;
/*      */         case 1:
/*  784 */           italic = style;
/*  785 */           StyleConstants.setItalic(this.m_mas, italic);
/*      */           break;
/*      */         case 2:
/*  788 */           underline = style;
/*  789 */           StyleConstants.setUnderline(this.m_mas, underline);
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  794 */       this.m_mas = this.m_rtfkit.getInputAttributes();
/*  795 */       this.m_mas.addAttributes(this.m_mas);
/*      */     } 
/*  797 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setFontSizeAttribute(int fontSize) {
/*  802 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  803 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  804 */     if (start != finish) {
/*      */       
/*  806 */       savePreviousCharProps(start, finish);
/*  807 */       JLbsCharProps[] selectedText = getStyledText(start, finish);
/*  808 */       JLbsCharProps c = null;
/*  809 */       int offset = start;
/*  810 */       for (int i = 0; i < selectedText.length; i++, offset++)
/*      */       {
/*  812 */         c = selectedText[i];
/*  813 */         MutableAttributeSet mas = c.getAttributeSet();
/*  814 */         StyleConstants.setFontSize(mas, fontSize);
/*  815 */         this.m_dse.setCharacterAttributes(offset, 1, mas, false);
/*      */       }
/*      */     
/*      */     }
/*  819 */     else if (start == finish) {
/*      */       
/*  821 */       StyleConstants.setFontSize(this.m_mas, fontSize);
/*  822 */       this.m_mas = this.m_rtfkit.getInputAttributes();
/*  823 */       this.m_mas.addAttributes(this.m_mas);
/*      */     } 
/*  825 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setFontFamilyAttribute(String fontFamily) {
/*  830 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/*  831 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/*  832 */     if (start != finish) {
/*      */       
/*  834 */       savePreviousCharProps(start, finish);
/*  835 */       JLbsCharProps[] selectedText = getStyledText(start, finish);
/*  836 */       JLbsCharProps c = null;
/*  837 */       int offset = start;
/*  838 */       for (int i = 0; i < selectedText.length; i++, offset++)
/*      */       {
/*  840 */         c = selectedText[i];
/*  841 */         MutableAttributeSet mas = c.getAttributeSet();
/*  842 */         StyleConstants.setFontFamily(mas, fontFamily);
/*  843 */         this.m_dse.setCharacterAttributes(offset, 1, mas, false);
/*      */       }
/*      */     
/*  846 */     } else if (start == finish) {
/*      */       
/*  848 */       StyleConstants.setFontFamily(this.m_mas, fontFamily);
/*  849 */       this.m_mas = this.m_rtfkit.getInputAttributes();
/*  850 */       this.m_mas.addAttributes(this.m_mas);
/*      */     } 
/*  852 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setAlignmentAttribute(int alignment) {
/*  857 */     savePreviousParagraphProps();
/*  858 */     int offset = 0;
/*  859 */     JLbsCharProps[] line = null;
/*  860 */     line = getSelectedStyledLine();
/*  861 */     if (line != null && line.length != 0)
/*  862 */       offset = line[0].getOffset(); 
/*  863 */     JLbsCharProps c = null;
/*  864 */     for (int i = 0; i < line.length; i++, offset++) {
/*      */       
/*  866 */       c = line[i];
/*  867 */       c.setAlignment(alignment);
/*  868 */       this.m_dse.setCharacterAttributes(offset, 1, c.getAttributeSet(), true);
/*  869 */       this.m_dse.setParagraphAttributes(offset, 1, c.getAttributeSet(), true);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  879 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLbsCharProps getCharProps(int pos) {
/*  931 */     Element element = this.m_dse.getCharacterElement(pos);
/*  932 */     AttributeSet attr = element.getAttributes();
/*  933 */     boolean isBold = StyleConstants.isBold(attr);
/*  934 */     boolean isItalic = StyleConstants.isItalic(attr);
/*  935 */     boolean isUnderline = StyleConstants.isUnderline(attr);
/*  936 */     int fontSize = StyleConstants.getFontSize(attr);
/*  937 */     String fontFamily = StyleConstants.getFontFamily(attr);
/*  938 */     Color fontColor = StyleConstants.getForeground(attr);
/*  939 */     int alignment = StyleConstants.getAlignment(attr);
/*  940 */     int style = 0;
/*  941 */     if (isBold)
/*  942 */       style |= 0x1; 
/*  943 */     if (isItalic)
/*  944 */       style |= 0x2; 
/*  945 */     if (isUnderline)
/*  946 */       style |= 0x4; 
/*  947 */     String value = "";
/*      */     
/*      */     try {
/*  950 */       value = this.m_scrollTextPane.getTextPane().getText(pos, 1);
/*      */     }
/*  952 */     catch (BadLocationException e) {
/*      */       
/*  954 */       return null;
/*      */     } 
/*  956 */     return new JLbsCharProps(value.charAt(0), style, fontSize, fontFamily, fontColor, alignment, pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsScrollTextPane getScrollTextPane() {
/*  961 */     return this.m_scrollTextPane;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveDocument() {
/*  972 */     if (this.m_OperationListener != null)
/*  973 */       this.m_OperationListener.saveRichTextDocument(this); 
/*  974 */     setCursor(Cursor.getDefaultCursor());
/*  975 */     this.m_scrollTextPane.getTextPane().grabFocus();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadDocument() {
/* 1001 */     if (this.m_OperationListener != null) {
/*      */       
/* 1003 */       InputStream inStream = this.m_OperationListener.loadRichTextDocument();
/* 1004 */       if (inStream != null) {
/*      */         
/*      */         try {
/*      */           
/* 1008 */           this.m_scrollTextPane.getTextPane().setText("");
/* 1009 */           this.m_rtfkit.read(inStream, this.m_scrollTextPane.getTextPane().getDocument(), 0);
/*      */         }
/* 1011 */         catch (Exception exception) {}
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1018 */       this.m_fileChooser.setDialogType(0);
/* 1019 */       this.m_fileChooser.setDialogTitle("Load");
/* 1020 */       if (this.m_fileChooser.showDialog(this, "Load") != 0) {
/*      */         return;
/*      */       }
/*      */       
/* 1024 */       File file = this.m_fileChooser.getSelectedFile();
/*      */       
/*      */       try {
/* 1027 */         FileInputStream in = new FileInputStream(file);
/* 1028 */         this.m_scrollTextPane.getTextPane().setText("");
/* 1029 */         this.m_rtfkit.read(in, this.m_scrollTextPane.getTextPane().getDocument(), 0);
/*      */       }
/* 1031 */       catch (Exception exception) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentContents(byte[] doc) {
/* 1039 */     if (doc == null)
/*      */       return; 
/* 1041 */     ByteArrayInputStream inStream = new ByteArrayInputStream(doc);
/* 1042 */     if (inStream != null) {
/*      */       
/*      */       try {
/*      */         
/* 1046 */         this.m_scrollTextPane.getTextPane().setText("");
/* 1047 */         this.m_rtfkit.read(inStream, this.m_scrollTextPane.getTextPane().getDocument(), 0);
/*      */       }
/* 1049 */       catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getDocumentContents() {
/* 1057 */     if (this.m_OperationListener != null) {
/*      */       
/* 1059 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 1060 */       this.m_OperationListener.save(this, outStream);
/* 1061 */       return outStream.toByteArray();
/*      */     } 
/* 1063 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private DefaultStyledDocument getRichTextDocument() {
/* 1068 */     return this.m_dse;
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent e) {
/* 1073 */     JComponent c = (JComponent)e.getSource();
/* 1074 */     if (c == this.m_colorDialogButton) {
/*      */       
/* 1076 */       this.m_colorDialog.setLocation(300, 300);
/* 1077 */       this.m_colorDialog.setVisible(true);
/*      */     
/*      */     }
/* 1080 */     else if (c == this.m_colorDialogCancelButton) {
/* 1081 */       this.m_colorDialog.setVisible(false);
/* 1082 */     } else if (c == this.m_colorDialogOkButton) {
/*      */       
/* 1084 */       this.m_colorDialog.setVisible(false);
/* 1085 */       Color selectedColor = this.m_jColorChooser.getSelectionModel().getSelectedColor();
/* 1086 */       setColorAttribute(selectedColor);
/*      */     
/*      */     }
/* 1089 */     else if (c == this.m_wordWrapButton) {
/*      */       
/* 1091 */       this.m_wordWrapDialog.setLocation(300, 300);
/* 1092 */       if (this.m_scrollTextPane.isWordWrap()) {
/*      */         
/* 1094 */         this.m_wordWrapOFF.setSelected(false);
/* 1095 */         this.m_wordWrapON.setSelected(true);
/*      */       }
/*      */       else {
/*      */         
/* 1099 */         this.m_wordWrapOFF.setSelected(true);
/* 1100 */         this.m_wordWrapON.setSelected(false);
/*      */       } 
/* 1102 */       this.m_wordWrapDialog.setVisible(true);
/*      */     
/*      */     }
/* 1105 */     else if (c == this.m_wordWrapDialogOkButton) {
/*      */       
/* 1107 */       this.m_wordWrapDialog.setVisible(false);
/*      */     }
/* 1109 */     else if (c == this.m_wordWrapOFF) {
/*      */       
/* 1111 */       if (this.m_wordWrapOFF.isSelected())
/*      */       {
/* 1113 */         this.m_wordWrapON.setSelected(false);
/* 1114 */         this.m_scrollTextPane.setWordWrap(false);
/*      */       }
/*      */       else
/*      */       {
/* 1118 */         this.m_wordWrapOFF.setSelected(true);
/*      */       }
/*      */     
/* 1121 */     } else if (c == this.m_wordWrapON) {
/*      */       
/* 1123 */       if (this.m_wordWrapON.isSelected())
/*      */       {
/* 1125 */         this.m_wordWrapOFF.setSelected(false);
/* 1126 */         this.m_scrollTextPane.setWordWrap(true);
/*      */       }
/*      */       else
/*      */       {
/* 1130 */         this.m_wordWrapON.setSelected(true);
/*      */       }
/*      */     
/* 1133 */     } else if (c == this.m_printButton) {
/* 1134 */       printDocument();
/* 1135 */     } else if (c == this.m_saveButton) {
/*      */       
/* 1137 */       saveDocument();
/*      */     }
/* 1139 */     else if (c == this.m_loadButton) {
/* 1140 */       loadDocument();
/*      */     } 
/*      */   }
/*      */   
/*      */   private JLbsCharProps getCommonProps(boolean extraCondition, int extraAlign) {
/* 1145 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/* 1146 */     int end = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/* 1147 */     int endOfText = this.m_dse.getLength();
/*      */     
/* 1149 */     boolean isFontSizeSame = true;
/* 1150 */     boolean isFontFamilySame = true;
/* 1151 */     boolean bold = true;
/* 1152 */     boolean italic = true;
/* 1153 */     boolean underline = true;
/* 1154 */     boolean left_align = true;
/* 1155 */     boolean center_align = true;
/* 1156 */     boolean right_align = true;
/* 1157 */     int alignment = -1;
/*      */     
/* 1159 */     if (start == end) {
/*      */       
/* 1161 */       if (start == 0)
/*      */       {
/* 1163 */         if (endOfText == 0)
/*      */         {
/* 1165 */           if (extraCondition) {
/*      */             
/* 1167 */             if (extraAlign == 0) {
/* 1168 */               int j = left_align & true;
/*      */             } else {
/* 1170 */               left_align = false;
/* 1171 */             }  if (extraAlign == 1) {
/* 1172 */               int j = center_align & true;
/*      */             } else {
/* 1174 */               center_align = false;
/* 1175 */             }  if (extraAlign == 2) {
/* 1176 */               int j = right_align & true;
/*      */             } else {
/* 1178 */               right_align = false;
/* 1179 */             }  return new JLbsCharProps(false, false, false, left_align, center_align, right_align, "Arial", 12);
/*      */           } 
/*      */ 
/*      */           
/* 1183 */           return getCurrentCharProps();
/*      */         }
/*      */       
/*      */       }
/* 1187 */       else if (end == endOfText)
/*      */       {
/* 1189 */         JLbsCharProps lastChar = getCharProps(start);
/* 1190 */         start--;
/* 1191 */         end--;
/* 1192 */         JLbsCharProps c = getCharProps(start);
/* 1193 */         if (c != null && c.getValue() == '\n')
/*      */         {
/* 1195 */           extraCondition = true;
/* 1196 */           extraAlign = lastChar.getAlignment();
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/* 1210 */         start--;
/* 1211 */         end--;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1216 */       end--;
/*      */     } 
/*      */     
/* 1219 */     Element element = null;
/* 1220 */     AttributeSet attr = null;
/*      */     
/* 1222 */     element = this.m_dse.getCharacterElement(start);
/* 1223 */     attr = element.getAttributes();
/* 1224 */     int fontSize = StyleConstants.getFontSize(attr);
/* 1225 */     String fontFamily = StyleConstants.getFontFamily(attr);
/*      */     
/* 1227 */     for (int i = start; i <= end; i++) {
/*      */       
/* 1229 */       element = this.m_dse.getCharacterElement(i);
/* 1230 */       attr = element.getAttributes();
/* 1231 */       if (extraCondition) {
/* 1232 */         alignment = extraAlign;
/*      */       } else {
/* 1234 */         alignment = StyleConstants.getAlignment(attr);
/*      */       } 
/* 1236 */       if (alignment == 0) {
/* 1237 */         int j = left_align & true;
/*      */       } else {
/* 1239 */         left_align = false;
/* 1240 */       }  if (alignment == 1) {
/* 1241 */         int j = center_align & true;
/*      */       } else {
/* 1243 */         center_align = false;
/* 1244 */       }  if (alignment == 2) {
/* 1245 */         int j = right_align & true;
/*      */       } else {
/* 1247 */         right_align = false;
/*      */       } 
/* 1249 */       if (StyleConstants.isBold(attr)) {
/* 1250 */         int j = bold & true;
/*      */       } else {
/* 1252 */         bold = false;
/* 1253 */       }  if (StyleConstants.isItalic(attr)) {
/* 1254 */         int j = italic & true;
/*      */       } else {
/* 1256 */         italic = false;
/* 1257 */       }  if (StyleConstants.isUnderline(attr)) {
/* 1258 */         int j = underline & true;
/*      */       } else {
/* 1260 */         underline = false;
/* 1261 */       }  if (fontSize == StyleConstants.getFontSize(attr) && isFontSizeSame) {
/* 1262 */         fontSize = StyleConstants.getFontSize(attr);
/*      */       } else {
/* 1264 */         isFontSizeSame = false;
/* 1265 */       }  if (fontFamily.equals(StyleConstants.getFontFamily(attr)) && isFontFamilySame) {
/* 1266 */         fontFamily = StyleConstants.getFontFamily(attr);
/*      */       } else {
/* 1268 */         isFontFamilySame = false;
/*      */       } 
/* 1270 */     }  if (!isFontFamilySame)
/*      */     {
/* 1272 */       fontFamily = null;
/*      */     }
/* 1274 */     if (!isFontSizeSame)
/*      */     {
/* 1276 */       fontSize = -1;
/*      */     }
/* 1278 */     return new JLbsCharProps(bold, italic, underline, left_align, center_align, right_align, fontFamily, fontSize);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setCommonProps(JLbsCharProps commonCharProps) {
/* 1283 */     if (commonCharProps == null) {
/*      */       return;
/*      */     }
/* 1286 */     if (commonCharProps.isBold()) {
/*      */       
/* 1288 */       setButtonIcon(this.m_boldButton, 2, "bold_button");
/* 1289 */       this.m_boldButton.setSelected(true);
/*      */     }
/*      */     else {
/*      */       
/* 1293 */       setButtonIcon(this.m_boldButton, 0, "bold_button");
/* 1294 */       this.m_boldButton.setSelected(false);
/*      */     } 
/* 1296 */     if (commonCharProps.isItalic()) {
/*      */       
/* 1298 */       setButtonIcon(this.m_italicButton, 2, "italic_button");
/* 1299 */       this.m_italicButton.setSelected(true);
/*      */     }
/*      */     else {
/*      */       
/* 1303 */       setButtonIcon(this.m_italicButton, 0, "italic_button");
/* 1304 */       this.m_italicButton.setSelected(false);
/*      */     } 
/* 1306 */     if (commonCharProps.isUnderline()) {
/*      */       
/* 1308 */       setButtonIcon(this.m_underlineButton, 2, "underline_button");
/* 1309 */       this.m_underlineButton.setSelected(true);
/*      */     }
/*      */     else {
/*      */       
/* 1313 */       setButtonIcon(this.m_underlineButton, 0, "underline_button");
/* 1314 */       this.m_underlineButton.setSelected(false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1354 */     setComboBoxItem(commonCharProps.getFontSize());
/* 1355 */     setComboBoxItem(commonCharProps.getFontFamily());
/*      */   }
/*      */ 
/*      */   
/*      */   private void setComboBoxItem(int fontSize) {
/* 1360 */     this.m_fontSizeAction = true;
/* 1361 */     if (fontSize == -1) {
/* 1362 */       this.m_fontSize.setSelectedIndex(0);
/*      */     } else {
/*      */       
/* 1365 */       for (int i = 0; i < this.m_fontSize.getItemCount(); i++) {
/*      */         
/* 1367 */         if (this.m_fontSize.getItemAt(i).toString().equals(Integer.valueOf(fontSize).toString())) {
/*      */           
/* 1369 */           this.m_fontSize.setSelectedIndex(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setComboBoxItem(String fontFamily) {
/* 1378 */     this.m_fontFamilyAction = true;
/* 1379 */     if (fontFamily == null) {
/* 1380 */       this.m_fontFamily.setSelectedIndex(0);
/*      */     } else {
/*      */       
/* 1383 */       for (int i = 0; i < this.m_fontFamily.getItemCount(); i++) {
/*      */         
/* 1385 */         if (this.m_fontFamily.getItemAt(i).toString().equals(fontFamily)) {
/*      */           
/* 1387 */           this.m_fontFamily.setSelectedIndex(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent event) {
/* 1396 */     JComponent c = (JComponent)event.getSource();
/* 1397 */     if (c == this.m_boldButton) {
/*      */       
/* 1399 */       if (this.m_boldButton.isSelected())
/*      */       {
/* 1401 */         setButtonIcon(this.m_boldButton, 2, "bold_button");
/* 1402 */         setStyleAttribute(0, true);
/*      */       }
/*      */       else
/*      */       {
/* 1406 */         setButtonIcon(this.m_boldButton, 0, "bold_button");
/* 1407 */         setStyleAttribute(0, false);
/*      */       }
/*      */     
/* 1410 */     } else if (c == this.m_italicButton) {
/*      */       
/* 1412 */       if (this.m_italicButton.isSelected())
/*      */       {
/* 1414 */         setButtonIcon(this.m_italicButton, 2, "italic_button");
/* 1415 */         setStyleAttribute(1, true);
/*      */       }
/*      */       else
/*      */       {
/* 1419 */         setButtonIcon(this.m_italicButton, 0, "italic_button");
/* 1420 */         setStyleAttribute(1, false);
/*      */       }
/*      */     
/* 1423 */     } else if (c == this.m_underlineButton) {
/*      */       
/* 1425 */       if (this.m_underlineButton.isSelected())
/*      */       {
/* 1427 */         setButtonIcon(this.m_underlineButton, 2, "underline_button");
/* 1428 */         setStyleAttribute(2, true);
/*      */       }
/*      */       else
/*      */       {
/* 1432 */         setButtonIcon(this.m_underlineButton, 0, "underline_button");
/* 1433 */         setStyleAttribute(2, false);
/*      */       }
/*      */     
/* 1436 */     } else if (c == this.m_leftAlignButton) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1450 */       setAlignmentAttribute(0);
/*      */     }
/* 1452 */     else if (c == this.m_centerAlignButton) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1466 */       setAlignmentAttribute(1);
/*      */     }
/* 1468 */     else if (c == this.m_rightAlignButton) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1481 */       setAlignmentAttribute(2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private JLbsCharProps[] getStyledText(int start, int end) {
/* 1488 */     int length = end - start;
/* 1489 */     JLbsCharProps[] styledText = new JLbsCharProps[length];
/* 1490 */     for (int i = 0, offset = start; i < length; i++, offset++)
/*      */     {
/* 1492 */       styledText[i] = getCharProps(offset);
/*      */     }
/* 1494 */     return styledText;
/*      */   }
/*      */   
/*      */   private JLbsCharProps[] getSelectedStyledLine() {
/*      */     JLbsCharProps c;
/* 1499 */     int start = this.m_scrollTextPane.getTextPane().getSelectionStart();
/* 1500 */     int finish = this.m_scrollTextPane.getTextPane().getSelectionEnd();
/* 1501 */     int lineStart = start;
/* 1502 */     int lineEnd = finish;
/* 1503 */     boolean first = true;
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/* 1508 */       c = getCharProps(lineStart);
/*      */       
/* 1510 */       if ((c != null && c.getValue() != '\n') || first) {
/*      */         
/* 1512 */         first = false;
/* 1513 */         lineStart--; continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1517 */     lineStart++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/* 1525 */       c = getCharProps(lineEnd);
/* 1526 */       if (c != null && c.getValue() != '\n') {
/* 1527 */         lineEnd++; continue;
/*      */       }  break;
/*      */     } 
/* 1530 */     if (c == null)
/* 1531 */       lineEnd--; 
/* 1532 */     lineEnd++;
/*      */ 
/*      */ 
/*      */     
/* 1536 */     return getStyledText(lineStart, lineEnd);
/*      */   }
/*      */ 
/*      */   
/*      */   public void printRTFContents(Graphics componentToBePainted, double w, double h, boolean wrapON) {
/* 1541 */     JTextPane c = this.m_scrollTextPane.getTextPane();
/* 1542 */     if (wrapON) {
/*      */       
/* 1544 */       int originalW = c.getWidth();
/* 1545 */       int originalH = c.getHeight();
/* 1546 */       c.setSize((int)w, (int)h);
/* 1547 */       this.m_scrollTextPane.setWordWrap(true);
/* 1548 */       c.paint(componentToBePainted);
/* 1549 */       componentToBePainted.dispose();
/* 1550 */       this.m_scrollTextPane.setWordWrap(false);
/* 1551 */       c.setSize(originalW, originalH);
/*      */     }
/*      */     else {
/*      */       
/* 1555 */       c.paint(componentToBePainted);
/* 1556 */       componentToBePainted.dispose();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void printRTFContents(Graphics componentToBePainted) {
/* 1562 */     JTextPane c = this.m_scrollTextPane.getTextPane();
/* 1563 */     c.paint(componentToBePainted);
/* 1564 */     componentToBePainted.dispose();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void printDocument() {
/* 1570 */     JLbsPrintIt x = new JLbsPrintIt();
/* 1571 */     x.print(this.m_scrollTextPane.getTextPane());
/*      */   }
/*      */ 
/*      */   
/*      */   private void caretPositionChange(CaretEvent ce) {
/* 1576 */     if (this.m_dse.getLength() == 0)
/* 1577 */       setCurrentFont(); 
/* 1578 */     setCommonProps(getCommonProps(false, -1));
/* 1579 */     refreshIcons();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseEntered(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseExited(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/* 1604 */     if (this.m_scrollTextPane.getTextPane().getSelectionStart() == this.m_dse.getLength())
/*      */     {
/* 1606 */       setCurrentFont();
/*      */     }
/* 1608 */     if (!e.isConsumed() && e.isControlDown() && !e.isAltDown() && !e.isShiftDown()) {
/*      */       
/* 1610 */       switch (e.getKeyCode()) {
/*      */         
/*      */         case 66:
/* 1613 */           if (this.m_boldButton.isSelected()) {
/*      */             
/* 1615 */             setButtonIcon(this.m_boldButton, 0, "bold_button");
/* 1616 */             setStyleAttribute(0, false);
/* 1617 */             this.m_boldButton.setSelected(false);
/*      */             
/*      */             break;
/*      */           } 
/* 1621 */           setButtonIcon(this.m_boldButton, 2, "bold_button");
/* 1622 */           setStyleAttribute(0, true);
/* 1623 */           this.m_boldButton.setSelected(true);
/*      */           break;
/*      */         
/*      */         case 73:
/* 1627 */           if (this.m_italicButton.isSelected()) {
/*      */             
/* 1629 */             setButtonIcon(this.m_italicButton, 0, "italic_button");
/* 1630 */             setStyleAttribute(1, false);
/* 1631 */             this.m_italicButton.setSelected(false);
/*      */             
/*      */             break;
/*      */           } 
/* 1635 */           setButtonIcon(this.m_italicButton, 2, "italic_button");
/* 1636 */           setStyleAttribute(1, true);
/* 1637 */           this.m_italicButton.setSelected(true);
/*      */           break;
/*      */         
/*      */         case 85:
/* 1641 */           if (this.m_underlineButton.isSelected()) {
/*      */             
/* 1643 */             setButtonIcon(this.m_underlineButton, 0, "underline_button");
/* 1644 */             setStyleAttribute(2, false);
/* 1645 */             this.m_underlineButton.setSelected(false);
/*      */             
/*      */             break;
/*      */           } 
/* 1649 */           setButtonIcon(this.m_underlineButton, 2, "underline_button");
/* 1650 */           setStyleAttribute(2, true);
/* 1651 */           this.m_underlineButton.setSelected(true);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 76:
/* 1661 */           setAlignmentAttribute(0);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 69:
/* 1670 */           setAlignmentAttribute(1);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 82:
/* 1679 */           setAlignmentAttribute(2);
/*      */           break;
/*      */         case 83:
/* 1682 */           saveDocument();
/*      */           break;
/*      */         case 79:
/* 1685 */           loadDocument();
/*      */           break;
/*      */         case 90:
/* 1688 */           this.undoAction.actionPerformed(null);
/*      */           break;
/*      */         case 89:
/* 1691 */           this.redoAction.actionPerformed(null);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/* 1697 */     } else if (!e.isConsumed() && !e.isControlDown() && !e.isAltDown() && e.isShiftDown()) {
/*      */       
/* 1699 */       switch (e.getKeyCode()) {
/*      */         
/*      */         case 127:
/* 1702 */           this.m_cutAction.actionPerformed(null);
/*      */           break;
/*      */         case 155:
/* 1705 */           this.m_pasteAction.actionPerformed(null);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyReleased(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   private void refreshIcons() {
/* 1719 */     if (this.m_boldButton.isSelected()) {
/* 1720 */       setButtonIcon(this.m_boldButton, 2, "bold_button");
/*      */     } else {
/* 1722 */       setButtonIcon(this.m_boldButton, 0, "bold_button");
/* 1723 */     }  if (this.m_italicButton.isSelected()) {
/* 1724 */       setButtonIcon(this.m_italicButton, 2, "italic_button");
/*      */     } else {
/* 1726 */       setButtonIcon(this.m_italicButton, 0, "italic_button");
/* 1727 */     }  if (this.m_underlineButton.isSelected()) {
/* 1728 */       setButtonIcon(this.m_underlineButton, 2, "underline_button");
/*      */     } else {
/* 1730 */       setButtonIcon(this.m_underlineButton, 0, "underline_button");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRawText(String filePath) {
/* 1747 */     String text = "";
/* 1748 */     StyleContext sc = new StyleContext();
/* 1749 */     DefaultStyledDocument dse = new DefaultStyledDocument(sc);
/*      */ 
/*      */     
/*      */     try {
/* 1753 */       FileInputStream in = new FileInputStream(filePath);
/* 1754 */       this.m_rtfkit.read(in, dse, 0);
/* 1755 */       return dse.getText(0, dse.getLength());
/*      */     }
/* 1757 */     catch (FileNotFoundException fileNotFoundException) {
/*      */ 
/*      */     
/* 1760 */     } catch (BadLocationException badLocationException) {
/*      */ 
/*      */     
/* 1763 */     } catch (IOException iOException) {}
/*      */ 
/*      */     
/* 1766 */     return text;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getText() {
/*      */     try {
/* 1773 */       return this.m_dse.getText(0, this.m_dse.getLength());
/*      */     }
/* 1775 */     catch (Exception exception) {
/*      */ 
/*      */       
/* 1778 */       return "";
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getScrollX() {
/* 1783 */     JScrollBar horizontalBar = this.m_scrollTextPane.getScrollPane().getHorizontalScrollBar();
/* 1784 */     return horizontalBar.isVisible() ? 
/* 1785 */       horizontalBar.getValue() : 
/* 1786 */       0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getScrollY() {
/* 1791 */     JScrollBar verticalBar = this.m_scrollTextPane.getScrollPane().getVerticalScrollBar();
/* 1792 */     return verticalBar.isVisible() ? 
/* 1793 */       verticalBar.getValue() : 
/* 1794 */       0;
/*      */   }
/*      */ 
/*      */   
/*      */   class UndoAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*      */       try {
/* 1803 */         JLbsRichTextEditor_Old.this.manager.undo();
/*      */       }
/* 1805 */       catch (CannotUndoException ex) {
/*      */         
/* 1807 */         Toolkit.getDefaultToolkit().beep();
/*      */       }
/* 1809 */       catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class RedoAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class MyAdjustmentListener
/*      */     implements AdjustmentListener
/*      */   {
/*      */     public void adjustmentValueChanged(AdjustmentEvent evt) {
/* 1853 */       JLbsRichTextEditor_Old.this.m_verticalRuler.repaint();
/* 1854 */       JLbsRichTextEditor_Old.this.m_horizontalRuler.repaint();
/*      */     }
/*      */   }
/*      */   
/*      */   class FontComboActionListener
/*      */     implements ActionListener
/*      */   {
/*      */     JLbsRichTextEditor_Old m_Editor;
/*      */     
/*      */     public FontComboActionListener(JLbsRichTextEditor_Old editor) {
/* 1864 */       this.m_Editor = editor;
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1869 */       JComponent c = (JComponent)e.getSource();
/* 1870 */       if (c == this.m_Editor.m_fontFamily) {
/*      */         
/* 1872 */         if (this.m_Editor.m_fontFamilyAction || this.m_Editor.m_fontSizeAction) {
/*      */           
/* 1874 */           this.m_Editor.m_fontFamilyAction = false;
/*      */           return;
/*      */         } 
/* 1877 */         if (this.m_Editor.m_fontFamily.getSelectedIndex() == 0)
/*      */           return; 
/* 1879 */         String j = (String)this.m_Editor.m_fontFamily.getSelectedItem();
/* 1880 */         this.m_Editor.setFontFamilyAttribute(j);
/*      */       }
/* 1882 */       else if (c == this.m_Editor.m_fontSize) {
/*      */         
/* 1884 */         if (this.m_Editor.m_fontSizeAction || this.m_Editor.m_fontFamilyAction) {
/*      */           
/* 1886 */           this.m_Editor.m_fontSizeAction = false;
/*      */           return;
/*      */         } 
/* 1889 */         if (this.m_Editor.m_fontSize.getSelectedIndex() == 0)
/*      */           return; 
/* 1891 */         String h = (String)this.m_Editor.m_fontSize.getSelectedItem();
/* 1892 */         int r = 8;
/*      */         
/*      */         try {
/* 1895 */           r = Integer.parseInt(h);
/* 1896 */           if (r < 1 || r > 1638)
/*      */           {
/* 1898 */             this.m_Editor.m_fontSize.setSelectedIndex(1);
/* 1899 */             JOptionPane.showMessageDialog(this.m_Editor, "The number must be between 1 and 1638");
/*      */           }
/*      */         
/* 1902 */         } catch (Exception ex) {
/*      */           
/* 1904 */           this.m_Editor.m_fontSize.setSelectedIndex(1);
/*      */         } 
/* 1906 */         this.m_Editor.setFontSizeAttribute(r);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class MousePopupListener
/*      */     extends MouseAdapter
/*      */   {
/*      */     public void mousePressed(MouseEvent e) {
/* 1915 */       checkPopup(e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/* 1920 */       checkPopup(e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {
/* 1925 */       checkPopup(e);
/*      */     }
/*      */ 
/*      */     
/*      */     private void checkPopup(MouseEvent e) {
/* 1930 */       if (e.isPopupTrigger())
/*      */       {
/* 1932 */         JLbsRichTextEditor_Old.this.m_contextMenu.show(JLbsRichTextEditor_Old.this.m_scrollTextPane, e.getX(), e.getY());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ILbsRichTextSaveLoadListener getOperationListener() {
/* 1939 */     return this.m_OperationListener;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOperationListener(ILbsRichTextSaveLoadListener operationListener) {
/* 1944 */     this.m_OperationListener = operationListener;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canHaveLayoutManager() {
/* 1949 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUniqueIdentifier() {
/* 1954 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLayout(LayoutManager mgr) {
/* 1959 */     super.setLayout(mgr);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DefaultEditorKit getEditorKit() {
/* 1965 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSelectedText() {
/* 1978 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSelectionStart() {
/* 1985 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSelectionEnd() {
/* 1992 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setText(String text) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCaretPosition(int pos) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCaretPosition() {
/* 2013 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void select(int first, int end) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPaneEnabled(boolean enable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentReplace(int offset, int length, String text, AttributeSet attrs) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRichDocText(int offset, int length) {
/* 2041 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRichDocTextLenght() {
/* 2048 */     return 0;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsRichTextEditor_Old.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */