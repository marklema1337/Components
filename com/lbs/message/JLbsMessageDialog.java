/*      */ package com.lbs.message;
/*      */ 
/*      */ import com.lbs.control.interfaces.ILbsTabPage;
/*      */ import com.lbs.controls.ILbsComponentBase;
/*      */ import com.lbs.controls.JLbsComponentHelper;
/*      */ import com.lbs.controls.JLbsEventRecorderHelper;
/*      */ import com.lbs.controls.JLbsListBox;
/*      */ import com.lbs.controls.JLbsSwingUtilities;
/*      */ import com.lbs.controls.JLbsTabPage;
/*      */ import com.lbs.controls.JLbsTabbedPane;
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.recording.JLbsDataPoolItem;
/*      */ import com.lbs.recording.JLbsRecordItem;
/*      */ import com.lbs.recording.interfaces.ILbsMessageDlgRecordingEvents;
/*      */ import com.lbs.util.DebugUtil;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDialog;
/*      */ import com.lbs.util.JLbsOpenWindowListing;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringListItem;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.Font;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.LineBorder;
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
/*      */ public class JLbsMessageDialog
/*      */   extends JLbsDialog
/*      */   implements ActionListener, MouseListener, KeyListener, ILbsMessageDlgRecordingEvents, ILbsMessageConstants
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static boolean USE_LISTBOX = true;
/*      */   private String m_MessageId;
/*      */   private String m_title;
/*      */   private String m_mainMessage;
/*      */   private JLbsStringList m_messages;
/*      */   private ILbsCultureInfo m_culture;
/*      */   private int m_defButton;
/*      */   private JLbsStringList m_buttonList;
/*      */   private JLbsListBox listBox;
/*   75 */   private int m_buttonPressed = 0;
/*      */   
/*      */   private String m_selectedObject;
/*   78 */   private static int minDialogWidth = 300;
/*   79 */   private static int maxDialogWidth = 500;
/*   80 */   private static int minDialogHeight = 150;
/*   81 */   private static int maxDialogHeight = 500;
/*   82 */   private static int minListBoxHeight = 50;
/*      */   
/*   84 */   private static int minButtonWidth = 80;
/*      */ 
/*      */   
/*   87 */   private static int btnSeparator = 10;
/*   88 */   private static Color activeMsgColor = new Color(248, 143, 143);
/*      */   
/*   90 */   private final Font dlgFontPlain = new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE + 1);
/*   91 */   private final Font dlgFontBold = new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE);
/*      */   
/*      */   private JTextArea oldJTextObj;
/*      */   
/*      */   private int maxMsgWidth;
/*      */   
/*      */   private int listBoxHeight;
/*      */   
/*      */   private int totButtonWidth;
/*      */   private int dialogWidth;
/*      */   private int dialogHeight;
/*  102 */   private static JLbsMessageDialog ms_Instance = null;
/*      */ 
/*      */   
/*      */   private static boolean displayingMessage = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void showMultiLineMessagesImpl(String messageId, String[] messages, String[] titles) {
/*      */     try {
/*  111 */       JLbsMessageDialog dialog = new JLbsMessageDialog(messageId, messages, titles);
/*  112 */       dialog.setVisible(true);
/*      */     }
/*  114 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JLbsMessageDialogResult showLbsMessageDialog(String messageId, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  124 */     JLbsMessageDialogResult result = new JLbsMessageDialogResult();
/*      */     
/*  126 */     if (displayingMessage) {
/*      */       
/*  128 */       result.button = 0;
/*  129 */       result.message = 0;
/*      */       
/*  131 */       return result;
/*      */     } 
/*      */     
/*  134 */     displayingMessage = true;
/*      */ 
/*      */     
/*      */     try {
/*  138 */       if (mainMessage == null) {
/*  139 */         mainMessage = "";
/*      */       }
/*  141 */       boolean testScriptValidation = (JLbsComponentHelper.isPlayingTest() && 
/*  142 */         JLbsComponentHelper.getTestPlayerWrapper() != null && 
/*  143 */         JLbsComponentHelper.getTestPlayerWrapper().getCurrentValidationButton() > 0);
/*      */       
/*  145 */       if (!testScriptValidation) {
/*      */         
/*  147 */         synchronized (JLbsMessageDialog.class) {
/*      */           
/*  149 */           if (ms_Instance == null || JLbsConstants.EVENT_RECORDING || JLbsConstants.TEST_PLAYING) {
/*  150 */             ms_Instance = new JLbsMessageDialog(messageId, mainMessage, messages, title, culture, defButton, 
/*  151 */                 buttonDefs);
/*      */           } else {
/*  153 */             ms_Instance.applyParameters(messageId, mainMessage, messages, title, culture, defButton, buttonDefs);
/*      */           } 
/*  155 */         }  ms_Instance.setVisible(true);
/*  156 */         result.button = ms_Instance.getButtonClicked();
/*  157 */         result.message = ms_Instance.getMessageSelected();
/*      */       } else {
/*      */         
/*  160 */         result.button = JLbsComponentHelper.getTestPlayerWrapper().getCurrentValidationButton();
/*      */       } 
/*  162 */       if (JLbsComponentHelper.getTestPlayerWrapper() != null) {
/*  163 */         JLbsComponentHelper.getTestPlayerWrapper().setCurrentValidationButton(-1);
/*      */       }
/*      */     }
/*  166 */     catch (Exception exception) {
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  172 */       displayingMessage = false;
/*      */     } 
/*      */     
/*  175 */     return result;
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
/*      */   private void applyParameters(String messageId, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  190 */     this.m_MessageId = messageId;
/*  191 */     setTitle(this.m_title);
/*  192 */     getContentPane().removeAll();
/*  193 */     this.m_buttonPressed = 0;
/*  194 */     this.m_selectedObject = null;
/*  195 */     this.m_mainMessage = mainMessage;
/*  196 */     this.m_messages = messages;
/*  197 */     this.m_title = title;
/*  198 */     this.m_culture = culture;
/*  199 */     this.m_defButton = defButton;
/*  200 */     this.maxMsgWidth = 0;
/*  201 */     this.listBoxHeight = 0;
/*  202 */     this.totButtonWidth = 0;
/*  203 */     this.dialogWidth = 0;
/*  204 */     this.dialogHeight = 0;
/*  205 */     this.m_buttonList = new JLbsStringList();
/*  206 */     extractButtonNamesExt(buttonDefs);
/*  207 */     createDialog();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getButtonClicked() {
/*  212 */     return this.m_buttonPressed;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMessageSelected() {
/*  217 */     if (this.m_messages == null) {
/*  218 */       return -1;
/*      */     }
/*  220 */     if (USE_LISTBOX) {
/*      */       
/*  222 */       int idx = this.listBox.getSelectedIndex();
/*      */       
/*  224 */       if (idx < 0) {
/*  225 */         return idx;
/*      */       }
/*  227 */       return (this.m_messages.getAt(idx)).Tag;
/*      */     } 
/*      */ 
/*      */     
/*  231 */     if (this.m_selectedObject == null) {
/*  232 */       return 0;
/*      */     }
/*  234 */     String s = this.m_selectedObject.substring(1, this.m_selectedObject.length());
/*      */     
/*  236 */     return Integer.valueOf(s).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLbsMessageDialog(String messageId, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  243 */     super(null);
/*      */     
/*  245 */     this.m_MessageId = messageId;
/*      */     
/*  247 */     this.m_mainMessage = mainMessage;
/*  248 */     this.m_messages = messages;
/*  249 */     this.m_title = title;
/*  250 */     this.m_culture = culture;
/*  251 */     this.m_defButton = defButton;
/*      */     
/*  253 */     setTitle(this.m_title);
/*      */     
/*  255 */     this.m_buttonList = new JLbsStringList();
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
/*  268 */     extractButtonNamesExt(buttonDefs);
/*      */     
/*  270 */     createDialog();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLbsMessageDialog(String messageId, String[] messages, String[] titles) {
/*  279 */     super(null);
/*  280 */     this.m_MessageId = messageId;
/*  281 */     Container cp = getContentPane();
/*  282 */     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/*  283 */     Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
/*      */     
/*  285 */     JPanel buttonPanel = new JPanel(new FlowLayout(1));
/*  286 */     cp.add(buttonPanel, "South");
/*      */     
/*  288 */     JPanel xPanel = new JPanel();
/*  289 */     xPanel.setLayout(new BorderLayout());
/*  290 */     cp.add(xPanel, "Center");
/*      */     
/*  292 */     JPanel messagePanel = new JPanel();
/*  293 */     messagePanel.setLayout(new BoxLayout(messagePanel, 3));
/*  294 */     Component mainComp = messagePanel;
/*      */     
/*  296 */     int tabCount = messages.length;
/*  297 */     if (tabCount > 1) {
/*      */       
/*  299 */       JLbsTabbedPane tabPane = new JLbsTabbedPane();
/*  300 */       for (int i = 0; i < titles.length; i++) {
/*      */         
/*  302 */         JLbsTabPage page = new JLbsTabPage();
/*  303 */         page.setLayout(new BorderLayout());
/*  304 */         JScrollPane scrollPane = new JScrollPane(new JTextArea(messages[i]), 
/*  305 */             20, 30);
/*  306 */         page.add(scrollPane, "Center");
/*  307 */         tabPane.addTab(titles[i], (ILbsTabPage)page);
/*      */       } 
/*  309 */       messagePanel.add((Component)tabPane);
/*      */     }
/*      */     else {
/*      */       
/*  313 */       JTextArea t = new JTextArea(messages[0]);
/*  314 */       messagePanel.add(t);
/*  315 */       JScrollPane scrollPane = new JScrollPane(messagePanel, 20, 
/*  316 */           30);
/*  317 */       mainComp = scrollPane;
/*      */     } 
/*      */     
/*  320 */     JPanel yPanel = new JPanel();
/*  321 */     yPanel.setLayout(new BorderLayout());
/*  322 */     yPanel.setBorder(emptyBorder);
/*  323 */     yPanel.add(mainComp, "Center");
/*      */     
/*  325 */     xPanel.add(yPanel, "Center");
/*      */     
/*  327 */     this.m_buttonList = new JLbsStringList();
/*  328 */     createButtons(buttonPanel);
/*      */     
/*  330 */     setTitle(titles[0]);
/*      */     
/*  332 */     this.dialogWidth = screen.width / 3;
/*  333 */     this.dialogHeight = screen.height / 3;
/*  334 */     setBounds((screen.width - this.dialogWidth) / 2, (screen.height - this.dialogHeight) / 2, this.dialogWidth, this.dialogHeight);
/*      */     
/*  336 */     setModal(true);
/*  337 */     setResizable(true);
/*  338 */     setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createDialog() {
/*  343 */     Container cp = getContentPane();
/*  344 */     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/*      */     
/*  346 */     this.dialogHeight = 0;
/*  347 */     Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
/*      */     
/*  349 */     JPanel buttonPanel = new JPanel(new FlowLayout(1));
/*      */     
/*  351 */     cp.add(buttonPanel, "South");
/*      */     
/*  353 */     JPanel xPanel = new JPanel();
/*  354 */     xPanel.setLayout(new BorderLayout());
/*  355 */     cp.add(xPanel, "Center");
/*      */     
/*  357 */     boolean onlyMainMessage = (this.m_messages == null);
/*  358 */     if (this.m_mainMessage != null && this.m_mainMessage.length() != 0) {
/*      */       
/*  360 */       this.m_mainMessage = JLbsStringUtil.replaceAll(this.m_mainMessage, "~", "\n");
/*  361 */       JTextArea txtMainMessage = new JTextArea(this.m_mainMessage);
/*  362 */       txtMainMessage.setFocusable(false);
/*  363 */       txtMainMessage.setEditable(false);
/*  364 */       if (!onlyMainMessage)
/*  365 */         txtMainMessage.setBorder(emptyBorder); 
/*  366 */       txtMainMessage.setBackground(Color.WHITE);
/*  367 */       Font font = onlyMainMessage ? 
/*  368 */         this.dlgFontPlain : 
/*  369 */         this.dlgFontBold;
/*  370 */       txtMainMessage.setFont(font);
/*  371 */       txtMainMessage.setForeground(Color.BLACK);
/*  372 */       txtMainMessage.setDisabledTextColor(Color.BLACK);
/*  373 */       txtMainMessage.setWrapStyleWord(true);
/*  374 */       txtMainMessage.setLineWrap(true);
/*  375 */       JScrollPane txtMainMessagePane = new JScrollPane(txtMainMessage);
/*  376 */       txtMainMessagePane.setVerticalScrollBarPolicy(20);
/*  377 */       if (onlyMainMessage) {
/*  378 */         xPanel.add(txtMainMessagePane, "Center");
/*      */       } else {
/*  380 */         xPanel.add(txtMainMessagePane, "North");
/*  381 */       }  int width = 0;
/*  382 */       int h = txtMainMessage.getFontMetrics(font).getHeight();
/*  383 */       String[] lines = JLbsStringUtil.split(this.m_mainMessage, "\n", true);
/*  384 */       int lineCount = 0;
/*  385 */       for (int i = 0; i < lines.length; i++) {
/*      */         
/*  387 */         int w = txtMainMessage.getFontMetrics(font).stringWidth(lines[i]);
/*  388 */         if (w > width)
/*  389 */           width = w; 
/*      */       } 
/*  391 */       lineCount += lines.length;
/*  392 */       if (this.m_messages != null && this.m_messages.getList() != null) {
/*      */         
/*  394 */         List<JLbsStringListItem> items = this.m_messages.getList();
/*  395 */         lineCount += items.size();
/*      */       } 
/*      */       
/*  398 */       if (width > this.maxMsgWidth)
/*  399 */         this.maxMsgWidth = width; 
/*  400 */       if (onlyMainMessage) {
/*      */         
/*  402 */         h = StrictMath.max(h * lineCount, minDialogHeight);
/*  403 */         this.dialogWidth = StrictMath.min(this.maxMsgWidth, maxDialogWidth);
/*  404 */         this.dialogWidth = StrictMath.max(this.dialogWidth, this.totButtonWidth);
/*  405 */         this.dialogWidth = StrictMath.max(this.dialogWidth, minDialogWidth);
/*      */       } else {
/*      */         
/*  408 */         h *= lineCount;
/*  409 */       }  if (h > maxDialogHeight) {
/*  410 */         this.dialogHeight = maxDialogHeight;
/*      */       } else {
/*  412 */         this.dialogHeight += h;
/*      */       } 
/*      */     } 
/*  415 */     createButtons(buttonPanel);
/*      */     
/*  417 */     if (!onlyMainMessage) {
/*      */       
/*  419 */       JPanel messagePanel = new JPanel();
/*      */       
/*  421 */       if (USE_LISTBOX) {
/*  422 */         messagePanel.setLayout(new BorderLayout());
/*      */       } else {
/*  424 */         messagePanel.setLayout(new BoxLayout(messagePanel, 3));
/*      */       } 
/*  426 */       JScrollPane scrollPane = new JScrollPane(messagePanel, 20, 
/*  427 */           30);
/*      */       
/*  429 */       JPanel yPanel = new JPanel();
/*  430 */       yPanel.setLayout(new BorderLayout());
/*  431 */       yPanel.setBorder(emptyBorder);
/*  432 */       yPanel.add(scrollPane, "Center");
/*      */       
/*  434 */       xPanel.add(yPanel, "Center");
/*      */       
/*  436 */       if (USE_LISTBOX) {
/*  437 */         createListBox(messagePanel);
/*      */       } else {
/*  439 */         createTextAreas(messagePanel);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  445 */     setTitle(this.m_title);
/*      */     
/*  447 */     setBounds((screen.width - this.dialogWidth) / 2, (screen.height - this.dialogHeight) / 2, this.dialogWidth, this.dialogHeight);
/*      */     
/*  449 */     if (!onlyMainMessage && USE_LISTBOX) {
/*  450 */       this.listBox.setSize(new Dimension(this.dialogWidth, this.dialogHeight));
/*      */     }
/*  452 */     setModal(true);
/*  453 */     setResizable(true);
/*  454 */     setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createButtons(JPanel p) {
/*  459 */     if (this.m_buttonList.size() == 0) {
/*      */       
/*  461 */       this.m_buttonList.add("Tamam", 1);
/*  462 */       this.m_defButton = 0;
/*      */     } 
/*  464 */     int bHeight = 0;
/*  465 */     for (int i = 0; i < this.m_buttonList.size(); i++) {
/*      */       
/*  467 */       String s = (this.m_buttonList.getAt(i)).Value;
/*  468 */       JButton b = new JButton(s);
/*      */       
/*  470 */       b.setActionCommand(s);
/*  471 */       b.addActionListener(this);
/*  472 */       b.addKeyListener(this);
/*  473 */       b.setDefaultCapable(false);
/*  474 */       Dimension preferredSize = b.getPreferredSize();
/*  475 */       b.setPreferredSize(new Dimension(StrictMath.max(preferredSize.width, minButtonWidth), 
/*  476 */             preferredSize.height));
/*  477 */       p.add(b);
/*  478 */       this.totButtonWidth += preferredSize.width + btnSeparator;
/*  479 */       bHeight = preferredSize.height;
/*      */     } 
/*      */     
/*  482 */     this.dialogHeight += bHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   private void decorateTextArea(JTextArea t) {
/*  487 */     Container cp = getContentPane();
/*  488 */     LineBorder border = new LineBorder(Color.LIGHT_GRAY);
/*      */     
/*  490 */     t.setEnabled(false);
/*  491 */     t.setBorder(border);
/*  492 */     t.setBackground(cp.getBackground());
/*  493 */     t.setForeground(cp.getForeground());
/*  494 */     t.setDisabledTextColor(cp.getForeground());
/*  495 */     t.setWrapStyleWord(true);
/*  496 */     t.setLineWrap(true);
/*  497 */     t.addMouseListener(this);
/*  498 */     t.addKeyListener(this);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addIconAndMessage(JPanel p, String s, int tag) {
/*  503 */     if (s == null) {
/*      */       return;
/*      */     }
/*  506 */     int i = -1;
/*  507 */     int j = -1;
/*  508 */     String strIcon = "";
/*  509 */     String strMessage = "";
/*      */     
/*  511 */     i = s.indexOf("<");
/*  512 */     if (i >= 0) {
/*      */       
/*  514 */       j = s.indexOf(">", i);
/*      */       
/*  516 */       if (j >= 0)
/*      */       {
/*  518 */         strIcon = s.substring(i + 1, j);
/*      */       }
/*      */     } 
/*      */     
/*  522 */     strMessage = s.substring(j + 1);
/*      */     
/*  524 */     int intIcon = 0;
/*      */     
/*  526 */     if ("ERROR".equals(strIcon)) {
/*      */       
/*  528 */       intIcon = 4;
/*      */     }
/*  530 */     else if ("WARNING".equals(strIcon)) {
/*      */       
/*  532 */       intIcon = 1;
/*      */     }
/*  534 */     else if ("INFORMATION".equals(strIcon)) {
/*      */       
/*  536 */       intIcon = 3;
/*      */     }
/*  538 */     else if ("QUESTION".equals(strIcon)) {
/*      */       
/*  540 */       intIcon = 2;
/*      */     } 
/*      */     
/*  543 */     if (USE_LISTBOX) {
/*      */       
/*  545 */       this.listBox.addItem(strMessage, intIcon);
/*      */       
/*  547 */       int w = this.listBox.getFontMetrics(this.dlgFontPlain).stringWidth(strMessage) * 12 / 10;
/*  548 */       int h = this.listBox.getFontMetrics(this.dlgFontPlain).getHeight();
/*  549 */       this.listBoxHeight += h;
/*      */       
/*  551 */       if (w > this.maxMsgWidth) {
/*  552 */         this.maxMsgWidth = w;
/*      */       }
/*      */     } else {
/*      */       
/*  556 */       JTextArea t = new JTextArea(strMessage);
/*  557 */       t.setName("#" + tag);
/*  558 */       decorateTextArea(t);
/*  559 */       p.add(t);
/*      */       
/*  561 */       int width2 = (t.getPreferredSize()).width;
/*  562 */       if (width2 > this.maxMsgWidth)
/*      */       {
/*  564 */         this.maxMsgWidth = width2;
/*      */       }
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
/*      */   private void createTextAreas(JPanel p) {
/*  583 */     this.maxMsgWidth = 0;
/*      */     
/*  585 */     if (this.m_messages != null) {
/*      */       
/*  587 */       for (int i = 0; i < this.m_messages.size(); i++)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  593 */         JLbsStringListItem item = this.m_messages.getAt(i);
/*      */         
/*  595 */         addIconAndMessage(p, item.Value, item.Tag);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  600 */       DebugUtil.debugLn("1OL - dlgMessages = null");
/*      */     } 
/*      */     
/*  603 */     this.dialogHeight += (p.getPreferredSize()).height;
/*      */     
/*  605 */     this.dialogWidth = StrictMath.min(this.maxMsgWidth, maxDialogWidth);
/*  606 */     this.dialogWidth = StrictMath.max(this.dialogWidth, this.totButtonWidth);
/*  607 */     this.dialogWidth = StrictMath.max(this.dialogWidth, minDialogWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createListBox(JPanel p) {
/*  614 */     Container cp = getContentPane();
/*      */     
/*  616 */     this.listBox = new JLbsListBox();
/*      */ 
/*      */ 
/*      */     
/*  620 */     this.listBox.setFont(this.dlgFontPlain);
/*  621 */     this.listBox.setBackground(cp.getBackground());
/*  622 */     this.listBox.setForeground(cp.getForeground());
/*  623 */     this.listBox.setSelectionMode(0);
/*  624 */     this.listBox.addKeyListener(this);
/*  625 */     p.add((Component)this.listBox);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  633 */     if (this.m_messages != null) {
/*      */       
/*  635 */       for (int i = 0; i < this.m_messages.size(); i++)
/*      */       {
/*  637 */         JLbsStringListItem item = this.m_messages.getAt(i);
/*      */         
/*  639 */         addIconAndMessage((JPanel)null, item.Value, item.Tag);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  644 */       DebugUtil.debugLn("1OL - dlgMessages = null");
/*      */     } 
/*      */     
/*  647 */     this.listBoxHeight = StrictMath.max(this.listBoxHeight, minListBoxHeight);
/*  648 */     this.listBoxHeight = StrictMath.min(this.listBoxHeight, 300);
/*  649 */     this.dialogHeight += this.listBoxHeight;
/*      */     
/*  651 */     int titleHeight = (getPreferredSize()).height;
/*  652 */     this.dialogHeight += titleHeight;
/*      */     
/*  654 */     this.dialogHeight += (p.getPreferredSize()).height;
/*  655 */     this.dialogHeight = this.dialogHeight * 10 / 9;
/*  656 */     this.dialogWidth = StrictMath.min(this.maxMsgWidth, maxDialogWidth);
/*  657 */     this.dialogWidth = StrictMath.max(this.dialogWidth, this.totButtonWidth);
/*  658 */     this.dialogWidth = StrictMath.max(this.dialogWidth, minDialogWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   private void extractButtonNamesExt(Object obj) {
/*  663 */     if (obj instanceof String) {
/*      */       
/*  665 */       extractButtonNames((String)obj);
/*      */     }
/*  667 */     else if (obj instanceof Integer) {
/*      */       
/*  669 */       extractButtonNames(((Integer)obj).intValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void extractButtonNames(String btnCaptions) {
/*  675 */     int i = 1;
/*  676 */     int p = 1;
/*  677 */     int loc = 0;
/*  678 */     StringBuilder sb = new StringBuilder(btnCaptions);
/*      */     
/*  680 */     while (p >= 0) {
/*      */       
/*  682 */       p = sb.indexOf("|", loc);
/*  683 */       if (p >= 0) {
/*      */         
/*  685 */         this.m_buttonList.add(sb.substring(loc, p), i++);
/*  686 */         loc = p + 1;
/*      */         
/*      */         continue;
/*      */       } 
/*  690 */       this.m_buttonList.add(sb.substring(loc, sb.length()), i++);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void extractButtonNames(int buttons) {
/*  697 */     int defButton = this.m_defButton;
/*  698 */     this.m_defButton = -1;
/*  699 */     int buttonIdx = 0;
/*      */     
/*  701 */     if ((buttons & 0x1) == 1) {
/*      */       
/*  703 */       this.m_buttonList.add(this.m_culture.getOK(), 1);
/*  704 */       if (defButton == 1)
/*  705 */         this.m_defButton = buttonIdx; 
/*  706 */       buttonIdx++;
/*      */     } 
/*      */     
/*  709 */     if ((buttons & 0x4) == 4) {
/*      */       
/*  711 */       this.m_buttonList.add(this.m_culture.getYes(), 4);
/*  712 */       if (defButton == 4)
/*  713 */         this.m_defButton = buttonIdx; 
/*  714 */       buttonIdx++;
/*      */     } 
/*      */     
/*  717 */     if ((buttons & 0x8) == 8) {
/*      */       
/*  719 */       this.m_buttonList.add(this.m_culture.getNo(), 8);
/*  720 */       if (defButton == 8)
/*  721 */         this.m_defButton = buttonIdx; 
/*  722 */       buttonIdx++;
/*      */     } 
/*      */     
/*  725 */     if ((buttons & 0x10) == 16) {
/*      */       
/*  727 */       this.m_buttonList.add(this.m_culture.getSave(), 16);
/*  728 */       if (defButton == 16)
/*  729 */         this.m_defButton = buttonIdx; 
/*  730 */       buttonIdx++;
/*      */     } 
/*      */     
/*  733 */     if ((buttons & 0x2) == 2) {
/*      */       
/*  735 */       this.m_buttonList.add(this.m_culture.getCancel(), 2);
/*  736 */       if (defButton == 2)
/*  737 */         this.m_defButton = buttonIdx; 
/*  738 */       buttonIdx++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  746 */     JLbsOpenWindowListing.removeWindow(this);
/*  747 */     super.dispose();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVisible(boolean b) {
/*  753 */     if (!b) {
/*  754 */       JLbsOpenWindowListing.removeWindow(this);
/*      */     } else {
/*      */       
/*  757 */       registerWindow();
/*  758 */       SwingUtilities.invokeLater(new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/*  763 */               JLbsMessageDialog.this.focusDefaultButton();
/*      */             }
/*      */           });
/*      */     } 
/*  767 */     super.setVisible(b);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean shouldRegisterWindow() {
/*  773 */     ArrayList openDialogs = JLbsOpenWindowListing.getOpenDialogs();
/*  774 */     boolean fnd = false;
/*  775 */     for (Object window : openDialogs) {
/*      */       
/*  777 */       if (window == this) {
/*      */         
/*  779 */         fnd = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  783 */     return !fnd;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent event) {
/*  789 */     String cmd = event.getActionCommand();
/*      */     
/*  791 */     for (int i = 0; i < this.m_buttonList.size(); i++) {
/*      */       
/*  793 */       String s = (this.m_buttonList.getAt(i)).Value;
/*      */       
/*  795 */       if (cmd.equals(s)) {
/*      */         
/*  797 */         this.m_buttonPressed = (this.m_buttonList.getAt(i)).Tag;
/*  798 */         recordActionPerformed(this.m_buttonPressed);
/*      */         break;
/*      */       } 
/*      */     } 
/*  802 */     if (JLbsConstants.TEST_PLAYING || JLbsConstants.EVENT_RECORDING || JLbsStringUtil.isEmpty(this.m_title)) {
/*  803 */       dispose();
/*      */     } else {
/*  805 */       setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void hiliteObject(JTextArea t, boolean active) {
/*  810 */     if (active) {
/*      */       
/*  812 */       t.setBackground(activeMsgColor);
/*      */     }
/*      */     else {
/*      */       
/*  816 */       t.setBackground(getContentPane().getBackground());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowActivated(WindowEvent arg0) {
/*  823 */     super.windowActivated(arg0);
/*  824 */     focusDefaultButton();
/*      */   }
/*      */ 
/*      */   
/*      */   private void focusDefaultButton() {
/*  829 */     JPanel panel = (JPanel)getContentPane().getComponent(0);
/*      */     
/*      */     try {
/*  832 */       JButton def = (JButton)panel.getComponent(this.m_defButton);
/*  833 */       requestButtonFocus(def);
/*      */     }
/*  835 */     catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent e) {
/*  845 */     Object object = e.getSource();
/*      */     
/*  847 */     if (object.getClass() == JTextArea.class) {
/*      */       
/*  849 */       JTextArea curJTextObj = (JTextArea)object;
/*  850 */       if (this.oldJTextObj != null)
/*      */       {
/*  852 */         hiliteObject(this.oldJTextObj, false);
/*      */       }
/*      */       
/*  855 */       hiliteObject(curJTextObj, true);
/*  856 */       this.oldJTextObj = curJTextObj;
/*      */       
/*  858 */       this.m_selectedObject = curJTextObj.getName();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseEntered(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseExited(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/*      */     Component focused;
/*  893 */     recordKeyPressed(e);
/*      */     
/*  895 */     switch (e.getKeyCode()) {
/*      */       
/*      */       case 9:
/*  898 */         shiftFocus(1);
/*      */         break;
/*      */       
/*      */       case 37:
/*  902 */         shiftFocus(-1);
/*      */         break;
/*      */       
/*      */       case 39:
/*  906 */         shiftFocus(1);
/*      */         break;
/*      */       
/*      */       case 27:
/*  910 */         dispose();
/*      */         break;
/*      */       
/*      */       case 10:
/*  914 */         focused = getFocusOwner();
/*  915 */         if (focused instanceof JButton)
/*      */         {
/*  917 */           for (int i = 0; i < (focused.getParent().getComponents()).length; i++) {
/*  918 */             if (focused.getParent().getComponent(i) == focused) {
/*      */               
/*  920 */               JButton bt = (JButton)focused;
/*  921 */               bt.doClick();
/*  922 */               this.m_buttonPressed = (this.m_buttonList.getAt(i)).Tag;
/*  923 */               dispose();
/*      */               return;
/*      */             } 
/*      */           }  } 
/*  927 */         if (this.m_defButton > 0) {
/*      */           
/*  929 */           this.m_buttonPressed = (this.m_buttonList.getAt(this.m_defButton)).Tag;
/*  930 */           dispose();
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void shiftFocus(int dir) {
/*  938 */     JPanel p = (JPanel)getContentPane().getComponent(0);
/*  939 */     for (int i = 0; i < (p.getComponents()).length; i++) {
/*      */       
/*  941 */       JButton b = (JButton)p.getComponent(i);
/*  942 */       if (b.hasFocus()) {
/*      */         
/*  944 */         b.setFocusTraversalKeysEnabled(false);
/*  945 */         int cand = i + dir;
/*  946 */         b = (JButton)p.getComponent((cand < 0) ? (
/*  947 */             p.getComponentCount() - 1) : (
/*  948 */             (cand == p.getComponentCount()) ? 
/*  949 */             0 : 
/*  950 */             cand));
/*  951 */         requestButtonFocus(b);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void requestButtonFocus(JButton b) {
/*  958 */     b.setFocusTraversalKeysEnabled(false);
/*  959 */     b.requestFocusInWindow();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyReleased(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResourceIdentifier() {
/*  971 */     return this.m_mainMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUniqueIdentifier() {
/*  977 */     if (this.m_MessageId != null) {
/*  978 */       return this.m_MessageId.hashCode();
/*      */     }
/*  980 */     if (this.m_mainMessage == null)
/*  981 */       return -1; 
/*  982 */     return this.m_mainMessage.hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   public void doActionPerformed(int buttonPressed, int actionID, final boolean informPlayer) {
/*  987 */     final int mButtonPressed = buttonPressed;
/*  988 */     final int mActionID = actionID;
/*  989 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/*  994 */             String actionCommand = null;
/*      */             
/*  996 */             for (int i = 0; i < JLbsMessageDialog.this.m_buttonList.size(); i++) {
/*      */               
/*  998 */               if ((JLbsMessageDialog.this.m_buttonList.getAt(i)).Tag == mButtonPressed) {
/*      */                 
/* 1000 */                 actionCommand = (JLbsMessageDialog.this.m_buttonList.getAt(i)).Value;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1005 */             if (!JLbsStringUtil.isEmpty(actionCommand)) {
/*      */               
/* 1007 */               ActionEvent evt = new ActionEvent(this, 0, actionCommand);
/* 1008 */               JLbsMessageDialog.this.actionPerformed(evt);
/* 1009 */               if (informPlayer) {
/* 1010 */                 JLbsComponentHelper.statusChanged(4, mActionID, null);
/*      */               
/*      */               }
/*      */             }
/* 1014 */             else if (informPlayer) {
/* 1015 */               JLbsComponentHelper.statusChanged(1, mActionID, 
/* 1016 */                   "The action command corresponding to button with tag " + mButtonPressed + " could not be found!");
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void doKeyPressed(int keyCode, int actionID) {
/* 1024 */     final int mActionID = actionID;
/* 1025 */     final KeyEvent mEvt = new KeyEvent((Component)this, 0, System.currentTimeMillis(), 0, keyCode, (char)keyCode);
/* 1026 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/* 1031 */             JLbsMessageDialog.this.keyPressed(mEvt);
/* 1032 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void recordActionPerformed(int pressedButton) {
/* 1039 */     StringBuilder sb = new StringBuilder();
/* 1040 */     sb.append("MSG_DLG_ACTION_PERFORMED");
/* 1041 */     sb.append("|");
/* 1042 */     sb.append(getUniqueIdentifier());
/* 1043 */     sb.append("|");
/* 1044 */     sb.append(pressedButton);
/* 1045 */     sb.append("|");
/* 1046 */     sb.append(this.m_MessageId);
/* 1047 */     JLbsRecordItem item = new JLbsRecordItem(new JLbsDataPoolItem(-1, -1, JLbsComponentHelper.getType((ILbsComponentBase)this), 
/* 1048 */           getMessageDialogValueForRecording(), null, null), sb.toString(), false);
/* 1049 */     JLbsEventRecorderHelper.addRecordItem(item);
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getMessageDialogValueForRecording() {
/* 1054 */     StringBuilder sb = new StringBuilder();
/* 1055 */     sb.append(getStringValue(this.m_title));
/* 1056 */     sb.append("@_@");
/* 1057 */     sb.append(getStringValue(this.m_mainMessage));
/* 1058 */     sb.append("@_@");
/* 1059 */     sb.append(getStringValue(this.m_messages));
/* 1060 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsStringList getMessages() {
/* 1065 */     return this.m_messages;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getStringValue(String str) {
/* 1070 */     return JLbsStringUtil.isEmpty(str) ? 
/* 1071 */       "null" : 
/* 1072 */       str;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getStringValue(JLbsStringList strList) {
/* 1077 */     return (strList == null || strList.size() == 0) ? 
/* 1078 */       "null" : 
/* 1079 */       strList.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void recordKeyPressed(KeyEvent evt) {
/* 1085 */     StringBuilder sb = new StringBuilder();
/* 1086 */     sb.append("MSG_DLG_KEY_PRESSED");
/* 1087 */     sb.append("|");
/* 1088 */     sb.append(getUniqueIdentifier());
/* 1089 */     sb.append("|");
/* 1090 */     sb.append(evt.getKeyCode());
/* 1091 */     sb.append("|");
/* 1092 */     sb.append(this.m_MessageId);
/* 1093 */     JLbsRecordItem item = new JLbsRecordItem(new JLbsDataPoolItem(-1, -1, -1, null, null, null), sb.toString(), false);
/* 1094 */     JLbsEventRecorderHelper.addRecordItem(item);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 1100 */     ms_Instance = null;
/* 1101 */     super.finalize();
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessageDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */