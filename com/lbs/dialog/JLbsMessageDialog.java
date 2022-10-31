/*      */ package com.lbs.dialog;
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
/*      */ import com.lbs.message.ILbsMessageDialogImpl;
/*      */ import com.lbs.recording.JLbsDataPoolItem;
/*      */ import com.lbs.recording.JLbsRecordItem;
/*      */ import com.lbs.recording.interfaces.ILbsMessageDlgRecordingEvents;
/*      */ import com.lbs.util.DebugUtil;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDialog;
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
/*      */ import java.awt.Frame;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.util.ArrayList;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTextArea;
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
/*      */   implements ActionListener, MouseListener, KeyListener, ILbsMessageDlgRecordingEvents
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static boolean USE_LISTBOX = true;
/*      */   private String m_title;
/*      */   private String m_mainMessage;
/*      */   private JLbsStringList m_messages;
/*      */   private ILbsCultureInfo m_culture;
/*      */   private int m_defButton;
/*      */   private JLbsStringList m_buttonList;
/*      */   private JLbsListBox listBox;
/*   73 */   private int m_buttonPressed = 0;
/*      */   private String m_selectedObject;
/*   75 */   private static Pattern patternSpace = Pattern.compile(" ");
/*   76 */   private static Pattern patternSpecial = Pattern.compile("~");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   public static int BUT_OK = JLbsMessageUtil.BUT_OK;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   87 */   public static int BUT_CANCEL = JLbsMessageUtil.BUT_CANCEL;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   public static int BUT_YES = JLbsMessageUtil.BUT_YES;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   public static int BUT_NO = JLbsMessageUtil.BUT_NO;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   public static int BUT_SAVE = JLbsMessageUtil.BUT_SAVE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  108 */   public static String defButtonCaption = JLbsMessageUtil.defButtonCaption;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   public static int defButtonTag = JLbsMessageUtil.defButtonTag;
/*      */   
/*  115 */   private static int minDialogWidth = 300;
/*  116 */   private static int maxDialogWidth = 400;
/*  117 */   private static int minListBoxHeight = 50;
/*      */   
/*  119 */   private static int minButtonWidth = 80;
/*      */ 
/*      */   
/*  122 */   private static int btnSeparator = 10;
/*  123 */   private static Color activeMsgColor = new Color(248, 143, 143);
/*      */   
/*  125 */   private Font dlgFontPlain = new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE + 1);
/*  126 */   private Font dlgFontBold = new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE);
/*      */   
/*      */   private JTextArea oldJTextObj;
/*      */   
/*      */   private int maxMsgWidth;
/*      */   
/*      */   private int listBoxHeight;
/*      */   
/*      */   private int totButtonWidth;
/*      */   
/*      */   private int dialogWidth;
/*      */   
/*      */   private int dialogHeight;
/*      */   private static boolean displayingMessage = false;
/*      */   
/*      */   private static ArrayList split(String msg) {
/*  142 */     String[] msgArr = msg.split("~");
/*  143 */     ArrayList<String> list = new ArrayList();
/*  144 */     for (int i = 0; i < msgArr.length; i++) {
/*      */       
/*  146 */       String[] subList = splitToLines(msgArr[i]);
/*  147 */       for (int j = 0; j < subList.length; j++)
/*      */       {
/*  149 */         list.add(subList[j]);
/*      */       }
/*      */     } 
/*  152 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String[] splitToLines(String msg) {
/*  157 */     StringBuilder strBuffer = new StringBuilder("");
/*      */     
/*  159 */     String[] msgArr = patternSpace.split(msg);
/*  160 */     int len = 0;
/*  161 */     for (int i = 0; i < msgArr.length; i++) {
/*      */       
/*  163 */       if (len > 0) {
/*  164 */         strBuffer.append(" ");
/*      */       }
/*  166 */       if (len > 80) {
/*      */         
/*  168 */         strBuffer.append("~");
/*  169 */         len = 0;
/*      */       } 
/*  171 */       len += msgArr[i].length();
/*  172 */       strBuffer.append(msgArr[i]);
/*      */     } 
/*  174 */     return patternSpecial.split(strBuffer.toString());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*  179 */     String[] messages = { "message1", "message2", "message3" };
/*  180 */     String[] titles = { "title1", "title2", "title3" };
/*  181 */     showMultiLineMessages(messages, titles);
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
/*      */   public static void showMultiLineMessages(String[] messages, String[] titles) {
/*  218 */     JLbsMessageUtil.showMultiLineMessages(null, messages, titles);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void showMultiLineMessagesImpl(String[] messages, String[] titles) {
/*      */     try {
/*  225 */       JLbsMessageDialog dialog = new JLbsMessageDialog(messages, titles);
/*  226 */       dialog.setVisible(true);
/*      */     }
/*  228 */     catch (Exception exception) {}
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
/*      */   public static JLbsMessageDialogResult showLbsYesNoConfirmDialog(String mainMessage, String title) {
/*  241 */     return JLbsMessageUtil.showLbsYesNoConfirmDialog(null, mainMessage, title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsErrorDialog(String mainMessage, String title) {
/*  250 */     return JLbsMessageUtil.showLbsErrorDialog(null, mainMessage, title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsInfoDialog(String mainMessage, String title) {
/*  259 */     return JLbsMessageUtil.showLbsInfoDialog(null, mainMessage, title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsMessageDialog(String mainMessage, JLbsStringList messages, String title, String btnCaptions, int defButton) {
/*  269 */     return JLbsMessageUtil.showLbsMessageDialog((ILbsMessageDialogImpl)null, mainMessage, messages, title, btnCaptions, defButton);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsMessageDialog(String mainMessage, JLbsStringList messages, String title, String btnCaptions, ILbsCultureInfo culture, int defButton) {
/*  279 */     return JLbsMessageUtil.showLbsMessageDialog((ILbsMessageDialogImpl)null, mainMessage, messages, title, btnCaptions, culture, defButton);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsMessageDialog(String mainMessage, JLbsStringList messages, String title, int buttons, int defButton) {
/*  289 */     return JLbsMessageUtil.showLbsMessageDialog((ILbsMessageDialogImpl)null, mainMessage, messages, title, buttons, defButton);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JLbsMessageDialogResult showLbsMessageDialog(String mainMessage, JLbsStringList messages, String title, int buttons, ILbsCultureInfo culture, int defButton) {
/*  299 */     return JLbsMessageUtil.showLbsMessageDialog((ILbsMessageDialogImpl)null, mainMessage, messages, title, buttons, culture, defButton);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JLbsMessageDialogResult showLbsMessageDialog(String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  305 */     JLbsMessageDialogResult result = new JLbsMessageDialogResult();
/*      */     
/*  307 */     if (displayingMessage) {
/*      */       
/*  309 */       result.button = 0;
/*  310 */       result.message = 0;
/*      */       
/*  312 */       return result;
/*      */     } 
/*      */     
/*  315 */     displayingMessage = true;
/*      */ 
/*      */     
/*      */     try {
/*  319 */       if (mainMessage == null) {
/*  320 */         mainMessage = "";
/*      */       }
/*  322 */       boolean testScriptValidation = (JLbsComponentHelper.isPlayingTest() && 
/*  323 */         JLbsComponentHelper.getTestPlayerWrapper() != null && 
/*  324 */         JLbsComponentHelper.getTestPlayerWrapper().getCurrentValidationButton() > 0);
/*      */       
/*  326 */       if (!testScriptValidation) {
/*      */         
/*  328 */         JLbsMessageDialog dialog = new JLbsMessageDialog(null, mainMessage, messages, title, culture, defButton, 
/*  329 */             buttonDefs);
/*  330 */         dialog.setVisible(true);
/*  331 */         result.button = dialog.getButtonClicked();
/*  332 */         result.message = dialog.getMessageSelected();
/*      */       } else {
/*      */         
/*  335 */         result.button = JLbsComponentHelper.getTestPlayerWrapper().getCurrentValidationButton();
/*      */       } 
/*  337 */       if (JLbsComponentHelper.getTestPlayerWrapper() != null) {
/*  338 */         JLbsComponentHelper.getTestPlayerWrapper().setCurrentValidationButton(-1);
/*      */       }
/*      */     }
/*  341 */     catch (Throwable throwable) {
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  347 */       displayingMessage = false;
/*      */     } 
/*      */     
/*  350 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int showLbsConfirmDialog(String message, String title) {
/*  359 */     return JLbsMessageUtil.showLbsConfirmDialog(null, message, title);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getButtonClicked() {
/*  364 */     return this.m_buttonPressed;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMessageSelected() {
/*  369 */     if (USE_LISTBOX) {
/*      */       
/*  371 */       int idx = this.listBox.getSelectedIndex();
/*      */       
/*  373 */       if (idx < 0) {
/*  374 */         return idx;
/*      */       }
/*  376 */       return (this.m_messages.getAt(idx)).Tag;
/*      */     } 
/*      */ 
/*      */     
/*  380 */     if (this.m_selectedObject == null) {
/*  381 */       return 0;
/*      */     }
/*  383 */     String s = this.m_selectedObject.substring(1, this.m_selectedObject.length());
/*      */     
/*  385 */     return Integer.valueOf(s).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessageDialog(Frame parent, String mainMessage, JLbsStringList messages, String title, String btnCaptions, ILbsCultureInfo culture, int defButton) {
/*  395 */     this(parent, mainMessage, messages, title, culture, defButton, btnCaptions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessageDialog(Frame parent, String mainMessage, JLbsStringList messages, String title, int buttons, ILbsCultureInfo culture, int defButton) {
/*  404 */     this(parent, mainMessage, messages, title, culture, defButton, Integer.valueOf(buttons));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private JLbsMessageDialog(Frame parent, String mainMessage, JLbsStringList messages, String title, ILbsCultureInfo culture, int defButton, Object buttonDefs) {
/*  410 */     super(parent);
/*      */     
/*  412 */     this.m_mainMessage = mainMessage;
/*  413 */     this.m_messages = messages;
/*  414 */     this.m_title = title;
/*  415 */     this.m_culture = culture;
/*  416 */     this.m_defButton = defButton;
/*      */     
/*  418 */     setTitle(this.m_title);
/*      */     
/*  420 */     this.m_buttonList = new JLbsStringList();
/*      */     
/*  422 */     if (this.m_messages == null) {
/*      */       
/*  424 */       this.m_messages = new JLbsStringList();
/*  425 */       ArrayList<String> msgs = split(this.m_mainMessage);
/*  426 */       for (int i = 0; i < msgs.size(); i++)
/*      */       {
/*  428 */         this.m_messages.add(msgs.get(i), i);
/*      */       }
/*  430 */       this.m_mainMessage = "";
/*      */     } 
/*      */     
/*  433 */     extractButtonNamesExt(buttonDefs);
/*      */     
/*  435 */     createDialog();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessageDialog(String[] messages, String[] titles) {
/*  444 */     super(null);
/*  445 */     Container cp = getContentPane();
/*  446 */     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/*  447 */     Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
/*      */     
/*  449 */     JPanel buttonPanel = new JPanel(new FlowLayout(1));
/*  450 */     cp.add(buttonPanel, "South");
/*      */     
/*  452 */     JPanel xPanel = new JPanel();
/*  453 */     xPanel.setLayout(new BorderLayout());
/*  454 */     cp.add(xPanel, "Center");
/*      */     
/*  456 */     JPanel messagePanel = new JPanel();
/*  457 */     messagePanel.setLayout(new BoxLayout(messagePanel, 3));
/*  458 */     Component mainComp = messagePanel;
/*      */     
/*  460 */     int tabCount = messages.length;
/*  461 */     if (tabCount > 1) {
/*      */       
/*  463 */       JLbsTabbedPane tabPane = new JLbsTabbedPane();
/*  464 */       for (int i = 0; i < titles.length; i++) {
/*      */         
/*  466 */         JLbsTabPage page = new JLbsTabPage();
/*  467 */         page.setLayout(new BorderLayout());
/*  468 */         JScrollPane scrollPane = new JScrollPane(new JTextArea(messages[i]), 
/*  469 */             20, 30);
/*  470 */         page.add(scrollPane, "Center");
/*  471 */         tabPane.addTab(titles[i], (ILbsTabPage)page);
/*      */       } 
/*  473 */       messagePanel.add((Component)tabPane);
/*      */     }
/*      */     else {
/*      */       
/*  477 */       JTextArea t = new JTextArea(messages[0]);
/*  478 */       messagePanel.add(t);
/*  479 */       JScrollPane scrollPane = new JScrollPane(messagePanel, 20, 
/*  480 */           30);
/*  481 */       mainComp = scrollPane;
/*      */     } 
/*      */     
/*  484 */     JPanel yPanel = new JPanel();
/*  485 */     yPanel.setLayout(new BorderLayout());
/*  486 */     yPanel.setBorder(emptyBorder);
/*  487 */     yPanel.add(mainComp, "Center");
/*      */     
/*  489 */     xPanel.add(yPanel, "Center");
/*      */     
/*  491 */     this.m_buttonList = new JLbsStringList();
/*  492 */     createButtons(buttonPanel);
/*      */     
/*  494 */     setTitle(titles[0]);
/*      */     
/*  496 */     this.dialogWidth = screen.width / 3;
/*  497 */     this.dialogHeight = screen.height / 3;
/*  498 */     setBounds((screen.width - this.dialogWidth) / 2, (screen.height - this.dialogHeight) / 2, this.dialogWidth, this.dialogHeight);
/*      */     
/*  500 */     setModal(true);
/*  501 */     setResizable(true);
/*  502 */     setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createDialog() {
/*  507 */     Container cp = getContentPane();
/*  508 */     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/*  509 */     maxDialogWidth = screen.width / 2;
/*  510 */     this.dialogHeight = 0;
/*  511 */     Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
/*      */     
/*  513 */     JPanel buttonPanel = new JPanel(new FlowLayout(1));
/*      */     
/*  515 */     cp.add(buttonPanel, "South");
/*      */     
/*  517 */     JPanel xPanel = new JPanel();
/*  518 */     xPanel.setLayout(new BorderLayout());
/*  519 */     cp.add(xPanel, "Center");
/*      */     
/*  521 */     if (this.m_mainMessage != null && this.m_mainMessage.length() != 0) {
/*      */       
/*  523 */       JTextArea txtMainMessage = new JTextArea(this.m_mainMessage);
/*  524 */       txtMainMessage.setEnabled(false);
/*  525 */       txtMainMessage.setBorder(emptyBorder);
/*  526 */       txtMainMessage.setBackground(Color.WHITE);
/*  527 */       txtMainMessage.setFont(this.dlgFontBold);
/*  528 */       txtMainMessage.setForeground(Color.BLACK);
/*  529 */       txtMainMessage.setDisabledTextColor(Color.BLACK);
/*  530 */       txtMainMessage.setWrapStyleWord(true);
/*  531 */       txtMainMessage.setLineWrap(true);
/*  532 */       xPanel.add(txtMainMessage, "North");
/*  533 */       int w = txtMainMessage.getFontMetrics(this.dlgFontBold).stringWidth(this.m_mainMessage);
/*  534 */       int h = txtMainMessage.getFontMetrics(this.dlgFontBold).getHeight();
/*  535 */       if (w > this.maxMsgWidth)
/*  536 */         this.maxMsgWidth = w; 
/*  537 */       this.dialogHeight += h;
/*      */     } 
/*      */     
/*  540 */     JPanel messagePanel = new JPanel();
/*      */     
/*  542 */     if (USE_LISTBOX) {
/*  543 */       messagePanel.setLayout(new BorderLayout());
/*      */     } else {
/*  545 */       messagePanel.setLayout(new BoxLayout(messagePanel, 3));
/*      */     } 
/*  547 */     JScrollPane scrollPane = new JScrollPane(messagePanel, 20, 
/*  548 */         30);
/*      */     
/*  550 */     JPanel yPanel = new JPanel();
/*  551 */     yPanel.setLayout(new BorderLayout());
/*  552 */     yPanel.setBorder(emptyBorder);
/*  553 */     yPanel.add(scrollPane, "Center");
/*      */     
/*  555 */     xPanel.add(yPanel, "Center");
/*      */     
/*  557 */     createButtons(buttonPanel);
/*  558 */     if (USE_LISTBOX) {
/*  559 */       createListBox(messagePanel);
/*      */     } else {
/*  561 */       createTextAreas(messagePanel);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  566 */     setTitle(this.m_title);
/*      */     
/*  568 */     setBounds((screen.width - this.dialogWidth) / 2, (screen.height - this.dialogHeight) / 2, this.dialogWidth, this.dialogHeight);
/*      */     
/*  570 */     if (USE_LISTBOX) {
/*  571 */       this.listBox.setSize(new Dimension(this.dialogWidth, this.dialogHeight));
/*      */     }
/*  573 */     setModal(true);
/*  574 */     setResizable(true);
/*  575 */     setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createButtons(JPanel p) {
/*  580 */     if (this.m_buttonList.size() == 0) {
/*      */       
/*  582 */       this.m_buttonList.add(defButtonCaption, defButtonTag);
/*  583 */       this.m_defButton = 0;
/*      */     } 
/*  585 */     int bHeight = 0;
/*  586 */     for (int i = 0; i < this.m_buttonList.size(); i++) {
/*      */       
/*  588 */       String s = (this.m_buttonList.getAt(i)).Value;
/*  589 */       JButton b = new JButton(s);
/*  590 */       b.setFont(this.dlgFontPlain);
/*  591 */       b.setActionCommand(s);
/*  592 */       b.setDefaultCapable(true);
/*  593 */       b.addActionListener(this);
/*  594 */       b.addKeyListener(this);
/*  595 */       Dimension preferredSize = b.getPreferredSize();
/*  596 */       b.setPreferredSize(new Dimension(StrictMath.max(preferredSize.width, minButtonWidth), 
/*  597 */             preferredSize.height));
/*  598 */       p.add(b);
/*  599 */       if (i == this.m_defButton)
/*  600 */         p.getRootPane().setDefaultButton(b); 
/*  601 */       this.totButtonWidth += preferredSize.width + btnSeparator;
/*  602 */       bHeight = preferredSize.height;
/*      */     } 
/*      */     
/*  605 */     this.dialogHeight += bHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   private void decorateTextArea(JTextArea t) {
/*  610 */     Container cp = getContentPane();
/*  611 */     LineBorder border = new LineBorder(Color.LIGHT_GRAY);
/*      */     
/*  613 */     t.setEnabled(false);
/*  614 */     t.setBorder(border);
/*  615 */     t.setBackground(cp.getBackground());
/*  616 */     t.setForeground(cp.getForeground());
/*  617 */     t.setDisabledTextColor(cp.getForeground());
/*  618 */     t.setWrapStyleWord(true);
/*  619 */     t.setLineWrap(true);
/*  620 */     t.addMouseListener(this);
/*  621 */     t.addKeyListener(this);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addIconAndMessage(JPanel p, String s, int tag) {
/*  626 */     if (s == null) {
/*      */       return;
/*      */     }
/*  629 */     int i = -1;
/*  630 */     int j = -1;
/*  631 */     String strIcon = "";
/*  632 */     String strMessage = "";
/*      */     
/*  634 */     i = s.indexOf("<");
/*  635 */     if (i >= 0) {
/*      */       
/*  637 */       j = s.indexOf(">", i);
/*      */       
/*  639 */       if (j >= 0)
/*      */       {
/*  641 */         strIcon = s.substring(i + 1, j);
/*      */       }
/*      */     } 
/*      */     
/*  645 */     strMessage = s.substring(j + 1);
/*      */     
/*  647 */     int intIcon = 0;
/*      */     
/*  649 */     if ("ERROR".equals(strIcon)) {
/*      */       
/*  651 */       intIcon = 4;
/*      */     }
/*  653 */     else if ("WARNING".equals(strIcon)) {
/*      */       
/*  655 */       intIcon = 1;
/*      */     }
/*  657 */     else if ("INFORMATION".equals(strIcon)) {
/*      */       
/*  659 */       intIcon = 3;
/*      */     }
/*  661 */     else if ("QUESTION".equals(strIcon)) {
/*      */       
/*  663 */       intIcon = 2;
/*      */     } 
/*      */     
/*  666 */     if (USE_LISTBOX) {
/*      */       
/*  668 */       this.listBox.addItem(strMessage, intIcon);
/*      */       
/*  670 */       int w = this.listBox.getFontMetrics(this.dlgFontPlain).stringWidth(strMessage) * 12 / 10;
/*  671 */       int h = this.listBox.getFontMetrics(this.dlgFontPlain).getHeight();
/*  672 */       this.listBoxHeight += h;
/*      */       
/*  674 */       if (w > this.maxMsgWidth) {
/*  675 */         this.maxMsgWidth = w;
/*      */       }
/*      */     } else {
/*      */       
/*  679 */       JTextArea t = new JTextArea(strMessage);
/*  680 */       t.setName("#" + tag);
/*  681 */       decorateTextArea(t);
/*  682 */       p.add(t);
/*      */       
/*  684 */       int width2 = (t.getPreferredSize()).width;
/*  685 */       if (width2 > this.maxMsgWidth)
/*      */       {
/*  687 */         this.maxMsgWidth = width2;
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
/*  706 */     this.maxMsgWidth = 0;
/*      */     
/*  708 */     if (this.m_messages != null) {
/*      */       
/*  710 */       for (int i = 0; i < this.m_messages.size(); i++)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  716 */         JLbsStringListItem item = this.m_messages.getAt(i);
/*      */         
/*  718 */         addIconAndMessage(p, item.Value, item.Tag);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  723 */       DebugUtil.debugLn("1OL - dlgMessages = null");
/*      */     } 
/*      */     
/*  726 */     this.dialogHeight += (p.getPreferredSize()).height;
/*      */     
/*  728 */     this.dialogWidth = StrictMath.min(this.maxMsgWidth, maxDialogWidth);
/*  729 */     this.dialogWidth = StrictMath.max(this.dialogWidth, this.totButtonWidth);
/*  730 */     this.dialogWidth = StrictMath.max(this.dialogWidth, minDialogWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createListBox(JPanel p) {
/*  737 */     Container cp = getContentPane();
/*      */     
/*  739 */     this.listBox = new JLbsListBox();
/*      */ 
/*      */ 
/*      */     
/*  743 */     this.listBox.setFont(this.dlgFontPlain);
/*  744 */     this.listBox.setBackground(cp.getBackground());
/*  745 */     this.listBox.setForeground(cp.getForeground());
/*  746 */     this.listBox.setSelectionMode(0);
/*  747 */     this.listBox.addKeyListener(this);
/*  748 */     p.add((Component)this.listBox);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  756 */     if (this.m_messages != null) {
/*      */       
/*  758 */       for (int i = 0; i < this.m_messages.size(); i++)
/*      */       {
/*  760 */         JLbsStringListItem item = this.m_messages.getAt(i);
/*      */         
/*  762 */         addIconAndMessage((JPanel)null, item.Value, item.Tag);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  767 */       DebugUtil.debugLn("1OL - dlgMessages = null");
/*      */     } 
/*      */     
/*  770 */     this.listBoxHeight = StrictMath.max(this.listBoxHeight, minListBoxHeight);
/*  771 */     this.listBoxHeight = StrictMath.min(this.listBoxHeight, 300);
/*  772 */     this.dialogHeight += this.listBoxHeight;
/*      */     
/*  774 */     int titleHeight = (getPreferredSize()).height;
/*  775 */     this.dialogHeight += titleHeight;
/*      */     
/*  777 */     this.dialogHeight += (p.getPreferredSize()).height;
/*  778 */     this.dialogHeight = this.dialogHeight * 10 / 9;
/*  779 */     this.dialogWidth = StrictMath.min(this.maxMsgWidth, maxDialogWidth);
/*  780 */     this.dialogWidth = StrictMath.max(this.dialogWidth, this.totButtonWidth);
/*  781 */     this.dialogWidth = StrictMath.max(this.dialogWidth, minDialogWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   private void extractButtonNamesExt(Object obj) {
/*  786 */     if (obj instanceof String) {
/*      */       
/*  788 */       extractButtonNames((String)obj);
/*      */     }
/*  790 */     else if (obj instanceof Integer) {
/*      */       
/*  792 */       extractButtonNames(((Integer)obj).intValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void extractButtonNames(String btnCaptions) {
/*  798 */     int i = 1;
/*  799 */     int p = 1;
/*  800 */     int loc = 0;
/*  801 */     StringBuilder sb = new StringBuilder(btnCaptions);
/*      */     
/*  803 */     while (p >= 0) {
/*      */       
/*  805 */       p = sb.indexOf("|", loc);
/*  806 */       if (p >= 0) {
/*      */         
/*  808 */         this.m_buttonList.add(sb.substring(loc, p), i++);
/*  809 */         loc = p + 1;
/*      */         
/*      */         continue;
/*      */       } 
/*  813 */       this.m_buttonList.add(sb.substring(loc, sb.length()), i++);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void extractButtonNames(int buttons) {
/*  820 */     int defButton = this.m_defButton;
/*  821 */     this.m_defButton = -1;
/*  822 */     int buttonIdx = 0;
/*      */     
/*  824 */     if ((buttons & BUT_OK) == BUT_OK) {
/*      */       
/*  826 */       this.m_buttonList.add(this.m_culture.getOK(), BUT_OK);
/*  827 */       if (defButton == BUT_OK)
/*  828 */         this.m_defButton = buttonIdx; 
/*  829 */       buttonIdx++;
/*      */     } 
/*      */     
/*  832 */     if ((buttons & BUT_CANCEL) == BUT_CANCEL) {
/*      */       
/*  834 */       this.m_buttonList.add(this.m_culture.getCancel(), BUT_CANCEL);
/*  835 */       if (defButton == BUT_CANCEL)
/*  836 */         this.m_defButton = buttonIdx; 
/*  837 */       buttonIdx++;
/*      */     } 
/*      */     
/*  840 */     if ((buttons & BUT_YES) == BUT_YES) {
/*      */       
/*  842 */       this.m_buttonList.add(this.m_culture.getYes(), BUT_YES);
/*  843 */       if (defButton == BUT_YES)
/*  844 */         this.m_defButton = buttonIdx; 
/*  845 */       buttonIdx++;
/*      */     } 
/*      */     
/*  848 */     if ((buttons & BUT_NO) == BUT_NO) {
/*      */       
/*  850 */       this.m_buttonList.add(this.m_culture.getNo(), BUT_NO);
/*  851 */       if (defButton == BUT_NO)
/*  852 */         this.m_defButton = buttonIdx; 
/*  853 */       buttonIdx++;
/*      */     } 
/*      */     
/*  856 */     if ((buttons & BUT_SAVE) == BUT_SAVE) {
/*      */       
/*  858 */       this.m_buttonList.add(this.m_culture.getSave(), BUT_SAVE);
/*  859 */       if (defButton == BUT_SAVE)
/*  860 */         this.m_defButton = buttonIdx; 
/*  861 */       buttonIdx++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setVisible(boolean b) {
/*  867 */     super.setVisible(b);
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent event) {
/*  872 */     String cmd = event.getActionCommand();
/*      */     
/*  874 */     for (int i = 0; i < this.m_buttonList.size(); i++) {
/*      */       
/*  876 */       String s = (this.m_buttonList.getAt(i)).Value;
/*      */       
/*  878 */       if (cmd.equals(s)) {
/*      */         
/*  880 */         this.m_buttonPressed = (this.m_buttonList.getAt(i)).Tag;
/*  881 */         recordActionPerformed(this.m_buttonPressed);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  886 */     dispose();
/*      */   }
/*      */ 
/*      */   
/*      */   private void hiliteObject(JTextArea t, boolean active) {
/*  891 */     if (active) {
/*      */       
/*  893 */       t.setBackground(activeMsgColor);
/*      */     }
/*      */     else {
/*      */       
/*  897 */       t.setBackground(getContentPane().getBackground());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent e) {
/*  903 */     Object object = e.getSource();
/*      */     
/*  905 */     if (object.getClass() == JTextArea.class) {
/*      */       
/*  907 */       JTextArea curJTextObj = (JTextArea)object;
/*  908 */       if (this.oldJTextObj != null)
/*      */       {
/*  910 */         hiliteObject(this.oldJTextObj, false);
/*      */       }
/*      */       
/*  913 */       hiliteObject(curJTextObj, true);
/*  914 */       this.oldJTextObj = curJTextObj;
/*      */       
/*  916 */       this.m_selectedObject = curJTextObj.getName();
/*      */     } 
/*      */   }
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
/*      */   public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/*  945 */     recordKeyPressed(e);
/*      */     
/*  947 */     switch (e.getKeyCode()) {
/*      */       
/*      */       case 27:
/*  950 */         dispose();
/*      */         break;
/*      */       
/*      */       case 10:
/*  954 */         if (this.m_defButton > 0) {
/*      */           
/*  956 */           this.m_buttonPressed = (this.m_buttonList.getAt(this.m_defButton)).Tag;
/*  957 */           dispose();
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyReleased(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResourceIdentifier() {
/*  978 */     return this.m_mainMessage;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUniqueIdentifier() {
/*  983 */     if (this.m_title == null)
/*  984 */       return -1; 
/*  985 */     int idx = this.m_title.indexOf('[');
/*  986 */     if (idx >= 0) {
/*      */       
/*  988 */       String t = this.m_title.substring(0, idx);
/*  989 */       return t.hashCode();
/*      */     } 
/*  991 */     return this.m_title.hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   public void doActionPerformed(int buttonPressed, int actionID, final boolean informPlayer) {
/*  996 */     final int mButtonPressed = buttonPressed;
/*  997 */     final int mActionID = actionID;
/*  998 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1002 */             String actionCommand = null;
/*      */             
/* 1004 */             for (int i = 0; i < JLbsMessageDialog.this.m_buttonList.size(); i++) {
/*      */               
/* 1006 */               if ((JLbsMessageDialog.this.m_buttonList.getAt(i)).Tag == mButtonPressed) {
/*      */                 
/* 1008 */                 actionCommand = (JLbsMessageDialog.this.m_buttonList.getAt(i)).Value;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1013 */             if (!JLbsStringUtil.isEmpty(actionCommand)) {
/*      */               
/* 1015 */               ActionEvent evt = new ActionEvent(this, 0, actionCommand);
/* 1016 */               JLbsMessageDialog.this.actionPerformed(evt);
/* 1017 */               if (informPlayer) {
/* 1018 */                 JLbsComponentHelper.statusChanged(4, mActionID, null);
/*      */               
/*      */               }
/*      */             }
/* 1022 */             else if (informPlayer) {
/* 1023 */               JLbsComponentHelper.statusChanged(1, mActionID, 
/* 1024 */                   "The action command corresponding to button with tag " + mButtonPressed + " could not be found!");
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void doKeyPressed(int keyCode, int actionID) {
/* 1032 */     final int mActionID = actionID;
/* 1033 */     final KeyEvent mEvt = new KeyEvent((Component)this, 0, System.currentTimeMillis(), 0, keyCode, (char)keyCode);
/* 1034 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1038 */             JLbsMessageDialog.this.keyPressed(mEvt);
/* 1039 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void recordActionPerformed(int pressedButton) {
/* 1046 */     StringBuilder sb = new StringBuilder();
/* 1047 */     sb.append("MSG_DLG_ACTION_PERFORMED");
/* 1048 */     sb.append("|");
/* 1049 */     sb.append(getUniqueIdentifier());
/* 1050 */     sb.append("|");
/* 1051 */     sb.append(pressedButton);
/* 1052 */     JLbsRecordItem item = new JLbsRecordItem(new JLbsDataPoolItem(-1, -1, JLbsComponentHelper.getType((ILbsComponentBase)this), 
/* 1053 */           getMessageDialogValueForRecording(), null, null), sb.toString(), false);
/* 1054 */     JLbsEventRecorderHelper.addRecordItem(item);
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getMessageDialogValueForRecording() {
/* 1059 */     StringBuilder sb = new StringBuilder();
/* 1060 */     sb.append(getStringValue(this.m_title));
/* 1061 */     sb.append("@_@");
/* 1062 */     sb.append(getStringValue(this.m_mainMessage));
/* 1063 */     sb.append("@_@");
/* 1064 */     sb.append(getStringValue(this.m_messages));
/* 1065 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsStringList getMessages() {
/* 1070 */     return this.m_messages;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getStringValue(String str) {
/* 1075 */     return JLbsStringUtil.isEmpty(str) ? 
/* 1076 */       "null" : 
/* 1077 */       str;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getStringValue(JLbsStringList strList) {
/* 1082 */     return (strList == null || strList.size() == 0) ? 
/* 1083 */       "null" : 
/* 1084 */       strList.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public void recordKeyPressed(KeyEvent evt) {
/* 1089 */     StringBuilder sb = new StringBuilder();
/* 1090 */     sb.append("MSG_DLG_KEY_PRESSED");
/* 1091 */     sb.append("|");
/* 1092 */     sb.append(getUniqueIdentifier());
/* 1093 */     sb.append("|");
/* 1094 */     sb.append(evt.getKeyCode());
/* 1095 */     JLbsRecordItem item = new JLbsRecordItem(new JLbsDataPoolItem(-1, -1, -1, null, null, null), sb.toString(), false);
/* 1096 */     JLbsEventRecorderHelper.addRecordItem(item);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dialog\JLbsMessageDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */