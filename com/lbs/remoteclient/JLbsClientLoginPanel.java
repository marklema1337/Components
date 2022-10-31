/*      */ package com.lbs.remoteclient;
/*      */ 
/*      */ import com.lbs.contract.applet.ClientContractService;
/*      */ import com.lbs.controls.JLbsComboBox;
/*      */ import com.lbs.controls.JLbsComponentHelper;
/*      */ import com.lbs.controls.JLbsImageButton;
/*      */ import com.lbs.controls.JLbsLabel;
/*      */ import com.lbs.controls.JLbsLinkLabel;
/*      */ import com.lbs.controls.JLbsPanel;
/*      */ import com.lbs.controls.JLbsSwingUtilities;
/*      */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.globalization.ILbsCultureResInfo;
/*      */ import com.lbs.globalization.JLbsCultureConstants;
/*      */ import com.lbs.globalization.JLbsCultureInfoBase;
/*      */ import com.lbs.globalization.JLbsCurrenciesBase;
/*      */ import com.lbs.laf.LAFPluginManager;
/*      */ import com.lbs.localization.DSignatureLocalizer;
/*      */ import com.lbs.resource.JLbsLocalizer;
/*      */ import com.lbs.start.ITokenProcessor;
/*      */ import com.lbs.transport.UserInfo;
/*      */ import com.lbs.util.JLbsClientFS;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import com.lbs.util.JLbsDialog;
/*      */ import com.lbs.util.JLbsIniProperties;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringListItem;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import com.lbs.util.UIHelperUtil;
/*      */ import info.clearthought.layout.TableLayout;
/*      */ import info.clearthought.layout.TableLayoutConstraints;
/*      */ import java.applet.Applet;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JApplet;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPasswordField;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.ListCellRenderer;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.border.LineBorder;
/*      */ import javax.swing.plaf.FontUIResource;
/*      */ import javax.swing.plaf.metal.MetalButtonUI;
/*      */ import net.java.balloontip.BalloonTip;
/*      */ import net.java.balloontip.styles.BalloonTipStyle;
/*      */ import net.java.balloontip.styles.RoundedBalloonStyle;
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
/*      */ public abstract class JLbsClientLoginPanel
/*      */   extends JLbsClientPanel
/*      */   implements ActionListener, KeyListener, Runnable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected static final boolean COMBO_WITH_IMAGE = true;
/*      */   protected static final String ms_SettingsDir = "Settings";
/*  103 */   protected static final String[] CAPTCHA_OP_LIST = new String[] { "+", "-" };
/*      */   
/*      */   protected static final int CAPTCHA_EXP_MAX = 20;
/*      */   
/*  107 */   protected int m_DBError = 0;
/*      */   
/*      */   protected JLbsCultureInfoBase[] supportedCultures;
/*      */   
/*      */   protected JTextField editUsername;
/*      */   
/*      */   protected JPasswordField editPassword;
/*      */   protected BalloonTip capsLockTip;
/*      */   protected JComboBox comboLanguage;
/*  116 */   protected String loginPropHeader = "";
/*      */   
/*      */   protected JButton btnOk;
/*      */   
/*      */   protected JButton btnCancel;
/*      */   
/*      */   protected JLbsImageButton btnLocaleTimeZone;
/*      */   
/*      */   protected JCheckBox chk;
/*      */   
/*      */   protected JButton btnDSignature;
/*      */   
/*      */   protected JPanel pnlMain;
/*      */   
/*      */   protected JPanel pnlBottom;
/*      */   protected TableLayout mainLayout;
/*      */   protected JLabel lblHeader;
/*      */   protected JLabel lblUsername;
/*      */   protected JLabel lblPassword;
/*      */   protected JLabel lblLanguage;
/*      */   protected JLabel lblVersion;
/*      */   protected JLbsLinkLabel lblLinkAdvanced;
/*      */   protected JLbsLinkLabel lblRemember;
/*      */   protected JLabel lblLocalizationWarning;
/*      */   protected ImageIcon loginImg;
/*      */   protected JProgressBar progressBar;
/*  142 */   protected static int editHeight = 22;
/*  143 */   protected static double ratio = 1.0D;
/*      */   public static boolean isRatioCalculated = false;
/*      */   protected static final int lineHeight = 26;
/*  146 */   protected static int buttonWidth = 80;
/*  147 */   protected static int buttonHeight = 22;
/*      */   
/*      */   protected static final int headerHeight = 76;
/*      */   protected static final int firstColumn = 10;
/*      */   protected static final int secondColumn = 110;
/*      */   protected int yLoc;
/*  153 */   protected int m_Row = 0;
/*      */   protected int m_SelectedLanguage;
/*      */   protected JLbsCultureInfoBase m_SelectedCulture;
/*      */   protected String m_SelectedCompany;
/*      */   protected String m_SelectedTimeZone;
/*  158 */   protected ComponentOrientation m_LastComponentOrientation = ComponentOrientation.UNKNOWN;
/*      */   
/*  160 */   protected Thread threadBackground = null;
/*      */   
/*      */   protected Object userLoginInfo;
/*      */   
/*      */   protected ArrayList<Object> m_LoginPreferences;
/*      */   
/*      */   protected JLbsCurrenciesBase currencyBase;
/*      */   
/*  168 */   protected String ltpaToken = "";
/*      */   
/*      */   protected String m_Title;
/*      */   
/*      */   protected String m_ImageName;
/*      */   
/*      */   protected String m_IniFileName;
/*      */   protected String m_JarFiles;
/*      */   protected String m_MainForm;
/*      */   protected String m_IniLanguage;
/*      */   private boolean m_ShowVersionText;
/*      */   protected boolean userInfoGiven;
/*      */   protected boolean m_LoginFailedBefore = false;
/*      */   protected boolean m_ShowCaptcha = false;
/*  182 */   protected int m_LoginFailTime = 0;
/*  183 */   protected int m_CaptchaShowLimit = 0;
/*      */   
/*  185 */   protected int extraComponentSize = 1;
/*  186 */   protected JLbsIniProperties loginProp = null;
/*      */   
/*      */   protected JPanel pnlCaptcha;
/*      */   
/*      */   protected JTextField editCaptchaResult;
/*      */   protected JLabel lblCaptcha;
/*  192 */   protected int buttonOkBeginCol = 2;
/*  193 */   protected int buttonOkEndCol = 3;
/*  194 */   protected int buttonCancelBeginCol = 4;
/*  195 */   protected int buttonCancelEndCol = 6;
/*  196 */   private String access_token = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private static UserInfo ldapUserInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setContext(final IClientContext context) {
/*  206 */     super.setContext(context);
/*      */ 
/*      */     
/*  209 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/*  214 */             LAFPluginManager.installPreferenceLAF(context);
/*  215 */             JLbsComponentHelper.setCurrentContext(context);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void init(int width, int height, String title, String imageName, String iniFileName, String jarFiles, String mainForm) {
/*  226 */     this.m_Title = title;
/*  227 */     this.m_ImageName = imageName;
/*  228 */     this.m_IniFileName = iniFileName;
/*  229 */     this.m_JarFiles = jarFiles;
/*  230 */     this.m_MainForm = mainForm;
/*      */     
/*  232 */     ClientContractService.setCacheEnabled(true);
/*      */     
/*  234 */     initSupportedCultures();
/*  235 */     this.m_SelectedLanguage = 0;
/*  236 */     this.m_SelectedCulture = this.supportedCultures[this.m_SelectedLanguage];
/*      */     
/*  238 */     setLayout(new BorderLayout());
/*  239 */     setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
/*  240 */     this.lblHeader = new JLabel();
/*  241 */     this.lblHeader.setFocusable(false);
/*  242 */     this.lblHeader.setText(getHeaderLabel());
/*  243 */     this.lblHeader.setForeground(Color.BLUE);
/*  244 */     this.lblHeader.setPreferredSize(new Dimension(315, 76));
/*  245 */     this.lblHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
/*  246 */     this.lblHeader.setHorizontalAlignment(0);
/*  247 */     add(this.lblHeader, "North");
/*      */     
/*  249 */     initializeLayout();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  257 */     if (JLbsConstants.DESKTOP_MODE) {
/*  258 */       createMainPanel(height);
/*      */     } else {
/*  260 */       createMainPanel(height, width);
/*      */     } 
/*  262 */     createBottomPanel();
/*      */     
/*  264 */     createComponents();
/*  265 */     setDefaultLanguage();
/*      */     
/*  267 */     addMainPanel();
/*      */ 
/*      */     
/*  270 */     if (this.m_Context != null && this.threadBackground == null) {
/*      */       
/*  272 */       this.threadBackground = new Thread(this);
/*  273 */       this.threadBackground.start();
/*      */     } 
/*      */     
/*  276 */     createLanguageCombo();
/*      */     
/*  278 */     String serverName = getServerName();
/*  279 */     if (serverName != null)
/*      */     {
/*  281 */       this.loginPropHeader = String.valueOf(serverName) + ".";
/*      */     }
/*      */     
/*  284 */     if (isShowVersionText()) {
/*      */       
/*  286 */       String version = updateVersion();
/*  287 */       this.lblVersion.setText(version);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  294 */       loadLoginProperties(JLbsConstants.CHAR_SET);
/*      */       
/*  296 */       this.editUsername.selectAll();
/*  297 */       this.editPassword.selectAll();
/*      */     }
/*  299 */     catch (Exception e) {
/*      */       
/*  301 */       System.out.println(e);
/*      */     } 
/*      */     
/*  304 */     finalizeComponentProps();
/*      */     
/*      */     try {
/*  307 */       this.m_DBError = ((Integer)this.m_Context.getPublicObject("DBError", (Object)null, true)).intValue();
/*  308 */       checkDBError();
/*      */     }
/*  310 */     catch (Exception e) {
/*      */       
/*  312 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createMainPanel(int height) {
/*  319 */     this.pnlMain = new JPanel();
/*  320 */     this.pnlMain.setBorder(new EmptyBorder(10, 10, 0, 10));
/*      */     
/*  322 */     setPreferredSize(height);
/*      */     
/*  324 */     this.pnlMain.setLayout((LayoutManager)this.mainLayout);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createMainPanel(int height, int width) {
/*  329 */     this.pnlMain = new JPanel();
/*  330 */     this.pnlMain.setBorder(new EmptyBorder(10, 10, 0, 10));
/*  331 */     setPreferredSize(height, width);
/*  332 */     this.pnlMain.setLayout((LayoutManager)this.mainLayout);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String updateVersion() {
/*  337 */     String version = JLbsConstants.VERSION_STR;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  343 */     if (JLbsStringUtil.isEmpty(version) || (
/*  344 */       !JLbsStringUtil.isEmpty(JLbsConstants.ORG_VERSION_STR) && version.equals(JLbsConstants.ORG_VERSION_STR))) {
/*      */ 
/*      */       
/*      */       try {
/*  348 */         version = (String)this.m_Context.getPublicObject("ApplicationVersion", (Object)null, true);
/*      */       }
/*  350 */       catch (Exception e) {
/*      */         
/*  352 */         this.m_Context.showError(e);
/*      */       } 
/*  354 */       if (!JLbsStringUtil.isEmpty(version))
/*  355 */         setVersionStr(version); 
/*      */     } 
/*  357 */     return version;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createBottomPanel() {
/*  362 */     this.pnlBottom = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addMainPanel() {
/*  367 */     add(this.pnlMain);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createLanguageCombo() {
/*  372 */     if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */       
/*  374 */       if (hasLanguageSupport())
/*      */       {
/*      */         
/*  377 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  379 */           JLbsCultureInfoBase cultureInfo = this.supportedCultures[i];
/*  380 */           ImageIcon imageIcon = createImageIcon("Flags//" + cultureInfo.getLanguagePrefix() + ".gif");
/*  381 */           if (imageIcon != null) {
/*  382 */             imageIcon.setDescription(cultureInfo.getCultureResStr(101));
/*      */           }
/*  384 */           LanguageComboItemWithImage item = this.comboLanguage.getItemAt(i);
/*  385 */           item.setIcon(imageIcon);
/*  386 */           item.setLanguageID(cultureInfo.getLanguageID());
/*      */         } 
/*      */       }
/*      */       
/*  390 */       ImageIcon icon = createImageIcon("World.png");
/*  391 */       if (icon != null) {
/*  392 */         this.btnLocaleTimeZone.setIcon(icon);
/*      */       } else {
/*  394 */         this.btnLocaleTimeZone.setText("...");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isUserInfoGiven() {
/*  400 */     return this.userInfoGiven;
/*      */   }
/*      */ 
/*      */   
/*      */   public void buttonOKClick() {
/*  405 */     this.btnOk.doClick();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPreferredSize(int height) {
/*  410 */     int extraComponent = 0;
/*  411 */     if (!hasLanguageSupport()) {
/*  412 */       extraComponent++;
/*      */     }
/*  414 */     this.pnlMain.setPreferredSize(new Dimension(315, height - 70 + extraComponent * editHeight));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPreferredSize(int height, int width) {
/*  419 */     int extraComponent = 0;
/*  420 */     if (!hasLanguageSupport()) {
/*  421 */       extraComponent++;
/*      */     }
/*  423 */     if (JLbsConstants.DESKTOP_MODE) {
/*  424 */       this.pnlMain.setPreferredSize(new Dimension(315, height - 70 + extraComponent * editHeight));
/*      */     } else {
/*  426 */       this.pnlMain.setPreferredSize(new Dimension(width, height - 70 + extraComponent * editHeight));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setDefaultLanguage() {
/*  431 */     int idx = getLanguageIdx(JLbsCultureInfoBase.getLanguagePrefix(2));
/*  432 */     if (idx != -1) {
/*      */       
/*  434 */       this.m_SelectedLanguage = idx;
/*  435 */       if (this.comboLanguage != null) {
/*  436 */         this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */       } else {
/*  438 */         updateLanguage();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean hasLanguageSupport() {
/*  444 */     if (this.supportedCultures.length == 0) {
/*  445 */       return false;
/*      */     }
/*  447 */     if (this.supportedCultures.length == 1) {
/*      */       
/*  449 */       String langPrefix = this.supportedCultures[0].getLanguagePrefix();
/*  450 */       if (!JLbsStringUtil.isEmpty(langPrefix) && langPrefix.equalsIgnoreCase("NEUT")) {
/*  451 */         return false;
/*      */       }
/*      */     } 
/*  454 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void finalizeComponentProps() {
/*  459 */     if (this.comboLanguage != null && this.comboLanguage.getItemCount() == 0) {
/*      */       
/*  461 */       this.comboLanguage.setEnabled(false);
/*      */       
/*  463 */       this.editUsername.setText("");
/*  464 */       this.editUsername.setEnabled(false);
/*  465 */       this.editPassword.setText("");
/*  466 */       this.editPassword.setEnabled(false);
/*      */       
/*  468 */       this.btnOk.setEnabled(false);
/*  469 */       this.btnLocaleTimeZone.setEnabled(false);
/*      */       
/*  471 */       finalizeUserComponentProps();
/*      */     } 
/*  473 */     updateUI();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initializeLayout() {
/*  478 */     double[] columns = { 95.0D, 30.0D, 20.0D, 65.0D, 10.0D, 25.0D, 25.0D };
/*  479 */     double[] rows = { editHeight, editHeight, editHeight, (editHeight - 10), editHeight, editHeight };
/*      */     
/*  481 */     innerInitializeLayout(columns, rows, 4);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void innerInitializeLayout(double[] columns, double[] rows, int thinRow) {
/*  486 */     int rowCount = rows.length;
/*  487 */     rowCount = getRowCount(rowCount);
/*      */     
/*  489 */     rows = new double[rowCount];
/*  490 */     for (int i = 0; i < rowCount; i++) {
/*      */       
/*  492 */       if (i == rowCount - thinRow) {
/*  493 */         rows[i] = (editHeight - 10);
/*      */       } else {
/*  495 */         rows[i] = editHeight;
/*      */       } 
/*      */     } 
/*  498 */     double[][] size = { columns, rows };
/*  499 */     this.mainLayout = new TableLayout(size);
/*  500 */     this.mainLayout.setVGap(3);
/*  501 */     this.mainLayout.setHGap(2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getRowCount(int rowCount) {
/*  506 */     rowCount++;
/*      */     
/*  508 */     if (!hasLanguageSupport())
/*  509 */       rowCount++; 
/*  510 */     return rowCount;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getHeaderLabel() {
/*  515 */     return this.m_Title;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSupportedCultureList() {
/*  520 */     Object o = null;
/*      */     
/*      */     try {
/*  523 */       o = this.m_Context.getPublicObject("SupportedLanguages", (Object)null, true);
/*      */     }
/*  525 */     catch (Exception e1) {
/*      */       
/*  527 */       this.m_Context.showError(e1);
/*      */     } 
/*      */     
/*  530 */     if (o instanceof ArrayList) {
/*      */       
/*  532 */       ArrayList supportedLanguages = (ArrayList)o;
/*  533 */       JLbsCultureConstants.SUPPORTED_CULTURES = new int[supportedLanguages.size()];
/*      */       
/*  535 */       for (int i = 0; i < supportedLanguages.size(); i++) {
/*      */         
/*  537 */         String langPrefix = String.valueOf(supportedLanguages.get(i));
/*  538 */         if (!JLbsStringUtil.isEmpty(langPrefix)) {
/*  539 */           JLbsCultureConstants.SUPPORTED_CULTURES[i] = JLbsCultureInfoBase.getLanguageTagOfPrefix(langPrefix);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initSupportedCultures() {
/*  546 */     updateSupportedCultureList();
/*  547 */     this.supportedCultures = JLbsCultureConstants.prepareCultureObjects();
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
/*      */   protected void createComponents() {
/*  565 */     int width = (int)Math.floor(200.0D * ratio);
/*      */     
/*  567 */     if (!hasLanguageSupport()) {
/*      */       
/*  569 */       this.lblLocalizationWarning = addLabel("No language support! Contact your system administrator.", 0, this.m_Row, width, editHeight, 
/*  570 */           5);
/*  571 */       this.lblLocalizationWarning.setForeground(Color.RED);
/*  572 */       this.m_Row++;
/*      */     } 
/*      */     
/*  575 */     this.lblUsername = addLabel(getCultureResStr(102), 0, this.m_Row, width, editHeight, 0);
/*  576 */     this.editUsername = addTextField(1, this.m_Row, width, editHeight, 6);
/*  577 */     this.m_Row++;
/*  578 */     this.lblPassword = addLabel(getCultureResStr(103), 0, this.m_Row, width, editHeight, 0);
/*  579 */     this.editPassword = addPasswordField(1, this.m_Row, width, editHeight, 6);
/*      */     
/*  581 */     createBalloonTip();
/*      */     
/*  583 */     this.m_Row++;
/*  584 */     createUserComponents();
/*      */     
/*  586 */     addLanguageAndProgressBar();
/*      */     
/*  588 */     this.m_Row++;
/*      */     
/*  590 */     createButtonPanel(this.m_Row);
/*      */     
/*  592 */     this.m_Row++;
/*  593 */     this.lblRemember = addLinkLabel(getCultureResStr(112), 0, this.m_Row, width, editHeight, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createButtonPanel(int row) {
/*  600 */     JLbsPanel buttonPanel = new JLbsPanel();
/*      */     
/*  602 */     TableLayout buttonPnlLayout = new TableLayout(new double[] { -2.0D, 75.0D * ratio, -2.0D, 
/*  603 */           -2.0D }, new double[] { -2.0D });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  608 */     buttonPnlLayout.setVGap((int)Math.round(2.0D * ratio));
/*  609 */     buttonPnlLayout.setHGap((int)Math.round(2.0D * ratio));
/*  610 */     buttonPanel.setLayout((LayoutManager)buttonPnlLayout);
/*      */     
/*  612 */     this.lblVersion = addLabeltoPanel((JPanel)buttonPanel, isShowVersionText() ? 
/*  613 */         getVersionText() : 
/*  614 */         "", 0, 0, 0);
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
/*  625 */     this.lblLinkAdvanced = addAdvancedLink();
/*  626 */     if (this.lblLinkAdvanced != null && !JLbsConstants.ADVANCED_LOGIN) {
/*      */       
/*  628 */       TableLayoutConstraints tableLayoutConstraints = getLayoutConstraints(1, 0, 1);
/*  629 */       buttonPanel.add((Component)this.lblLinkAdvanced, tableLayoutConstraints);
/*      */     } 
/*      */     
/*  632 */     int btnWidth = (int)(buttonWidth * ratio);
/*  633 */     int btnHeight = (int)(buttonHeight * ratio);
/*      */     
/*  635 */     this.btnOk = addButtontoPanel((JPanel)buttonPanel, getCultureResStr(107), 2, 0, btnWidth, btnHeight, false, 2);
/*      */     
/*  637 */     this.btnCancel = addButtontoPanel((JPanel)buttonPanel, getCultureResStr(108), 3, 0, btnWidth, btnHeight, false, 3);
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
/*  656 */     TableLayoutConstraints constraint = getLayoutConstraints(0, row, 6);
/*  657 */     this.pnlMain.add((Component)buttonPanel, constraint);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createBalloonTip() {
/*      */     try {
/*  664 */       RoundedBalloonStyle roundedBalloonStyle = new RoundedBalloonStyle(5, 5, Color.WHITE, new Color(226, 10, 23));
/*  665 */       JLabel label = new JLabel(new ImageIcon(this.m_Context.getResource(getCapsLockImageName(), false)));
/*  666 */       label.setIconTextGap(10);
/*  667 */       label.setText(getCultureResStr(111));
/*  668 */       this.capsLockTip = new BalloonTip(this.editPassword, label, (BalloonTipStyle)roundedBalloonStyle, BalloonTip.Orientation.LEFT_BELOW, BalloonTip.AttachLocation.ALIGNED, 20, 10, 
/*  669 */           false);
/*  670 */       this.capsLockTip.setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*  671 */       this.capsLockTip.setPadding(5);
/*      */     }
/*  673 */     catch (FileNotFoundException e) {
/*      */       
/*  675 */       if (JLbsConstants.DEBUG)
/*  676 */         System.out.println("img Thread: " + e); 
/*      */     } 
/*  678 */     this.capsLockTip.setVisible(false);
/*      */     
/*  680 */     this.editPassword.addKeyListener(new KeyListener()
/*      */         {
/*      */           public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void keyReleased(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void keyPressed(KeyEvent e) {
/*  696 */             if (e.getKeyCode() == 20)
/*      */             {
/*  698 */               if (JLbsClientLoginPanel.this.getToolkit().getLockingKeyState(20)) {
/*  699 */                 JLbsClientLoginPanel.this.capsLockTip.setVisible(true);
/*      */               } else {
/*  701 */                 JLbsClientLoginPanel.this.capsLockTip.setVisible(false);
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*      */     
/*  707 */     this.editPassword.addFocusListener(new FocusListener()
/*      */         {
/*      */ 
/*      */           
/*      */           public void focusLost(FocusEvent e)
/*      */           {
/*  713 */             JLbsClientLoginPanel.this.capsLockTip.setVisible(false);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void focusGained(FocusEvent e) {
/*  719 */             if (JLbsClientLoginPanel.this.getToolkit().getLockingKeyState(20)) {
/*  720 */               JLbsClientLoginPanel.this.capsLockTip.setVisible(true);
/*      */             } else {
/*  722 */               JLbsClientLoginPanel.this.capsLockTip.setVisible(false);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addLanguageAndProgressBar() {
/*  732 */     int width = (int)Math.floor(200.0D * ratio);
/*      */     
/*  734 */     if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */       
/*  736 */       this.lblLanguage = addLabel(getCultureResStr(106), 0, this.m_Row, width, editHeight, 0);
/*      */       
/*  738 */       ILbsCultureResInfo resInfo = null;
/*      */ 
/*      */       
/*  741 */       this.comboLanguage = addComboBoxWithImage(1, this.m_Row, (int)Math.round(140.0D * ratio), editHeight, 3);
/*      */       
/*  743 */       if (hasLanguageSupport())
/*      */       {
/*  745 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  747 */           JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/*  748 */           LanguageComboItemWithImage item = new LanguageComboItemWithImage(
/*  749 */               jLbsCultureInfoBase.getCultureResStr(101), i);
/*  750 */           item.setFont(jLbsCultureInfoBase.getFont());
/*  751 */           this.comboLanguage.addItem(item);
/*      */         } 
/*      */       }
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
/*  771 */       addBtnLocaleTimezone();
/*      */     } 
/*  773 */     this.progressBar = addProgress(0, this.m_Row, (int)Math.floor(300.0D * ratio), (int)Math.floor(10.0D * ratio), 6);
/*  774 */     this.progressBar.setVisible(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ImageIcon createImageIcon(String path) {
/*      */     try {
/*  783 */       byte[] imgData = this.m_Context.getResource(path, false);
/*  784 */       if (imgData != null)
/*      */       {
/*  786 */         ImageIcon imageIcon = new ImageIcon(imgData);
/*  787 */         if (imageIcon != null)
/*      */         {
/*  789 */           return imageIcon;
/*      */         }
/*      */       }
/*      */     
/*  793 */     } catch (Exception e) {
/*      */       
/*  795 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  798 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void toggleProgress(boolean flag) {
/*  803 */     this.progressBar.setVisible(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setProgressIndex(int x) {
/*  808 */     this.progressBar.setValue(x);
/*  809 */     this.progressBar.paintImmediately(new Rectangle(this.progressBar.getPreferredSize()));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateLanguage(boolean readIni) {
/*  814 */     if (readIni)
/*      */     {
/*  816 */       if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */         
/*  818 */         this.m_SelectedCulture = this.supportedCultures[this.m_SelectedLanguage];
/*      */       }
/*      */       else {
/*      */         
/*  822 */         String lang = getIniLanguage();
/*      */ 
/*      */         
/*  825 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  827 */           JLbsCultureInfoBase cultureInfo = this.supportedCultures[i];
/*  828 */           if (cultureInfo.getLanguagePrefix().equals(lang)) {
/*  829 */             this.m_SelectedCulture = cultureInfo;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  836 */     if (this.m_SelectedCulture != null) {
/*      */       
/*  838 */       String langPrefix = this.m_SelectedCulture.getLanguagePrefix();
/*  839 */       if (langPrefix != null) {
/*      */         
/*  841 */         this.m_Context.setVariable("CLI-LANG", langPrefix);
/*  842 */         this.m_Context.setTerminateURI(langPrefix);
/*      */       } 
/*  844 */       JLbsLocalizer.setCultureInfo((ILbsCultureInfo)this.m_SelectedCulture);
/*  845 */       JLbsLocalizer.setCultureInfoRes((ILbsCultureResInfo)this.m_SelectedCulture);
/*      */       
/*  847 */       this.lblUsername.setText(this.m_SelectedCulture.getCultureResStr(102));
/*  848 */       this.lblPassword.setText(this.m_SelectedCulture.getCultureResStr(103));
/*  849 */       if (this.lblLanguage != null)
/*  850 */         this.lblLanguage.setText(this.m_SelectedCulture.getCultureResStr(106)); 
/*  851 */       UIHelperUtil.setCaption(this.btnOk, this.m_SelectedCulture.getCultureResStr(107));
/*  852 */       UIHelperUtil.setCaption(this.btnCancel, this.m_SelectedCulture.getCultureResStr(108));
/*      */       
/*      */       try {
/*  855 */         JLabel label = new JLabel(new ImageIcon(this.m_Context.getResource(getCapsLockImageName(), false)));
/*  856 */         label.setIconTextGap(10);
/*  857 */         label.setText(this.m_SelectedCulture.getCultureResStr(111));
/*  858 */         this.capsLockTip.setContents(label);
/*      */       }
/*  860 */       catch (FileNotFoundException e) {
/*      */         
/*  862 */         if (JLbsConstants.DEBUG) {
/*  863 */           System.out.println("img Thread: " + e);
/*      */         }
/*      */       } 
/*  866 */       if (this.lblRemember != null)
/*  867 */         this.lblRemember.setText(this.m_SelectedCulture.getCultureResStr(112)); 
/*  868 */       if (this.lblLinkAdvanced != null) {
/*      */         
/*  870 */         StringBuilder stringBuilder = new StringBuilder();
/*  871 */         stringBuilder.append(this.m_SelectedCulture.getCultureResStr(120));
/*  872 */         stringBuilder.append(">");
/*  873 */         this.lblLinkAdvanced.setText(stringBuilder.toString());
/*      */       } 
/*  875 */       if (this.btnLocaleTimeZone != null)
/*  876 */         this.btnLocaleTimeZone.setToolTipText(this.m_SelectedCulture.getTimeZone()); 
/*  877 */       if (this.btnDSignature != null) {
/*  878 */         this.btnDSignature.setToolTipText(DSignatureLocalizer.getResource(this.m_Context, this.m_SelectedCulture.getLanguagePrefix(), 
/*  879 */               2));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateLanguage() {
/*  885 */     updateLanguage(true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getCultureResStr(int cultureResID) {
/*  890 */     return this.m_SelectedCulture.getCultureResStr(cultureResID);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void loadJAR(String jarName) {
/*      */     try {
/*  897 */       this.m_Context.loadJAR(jarName, false, true);
/*      */     
/*      */     }
/*  900 */     catch (Exception e) {
/*      */       
/*  902 */       System.out.println("Exception while loading " + jarName + ": " + e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setContextLanguageParameters() {
/*  908 */     JLbsCultureInfoBase cultureInfo = this.m_SelectedCulture;
/*      */     
/*  910 */     Locale.setDefault(cultureInfo.getLocale());
/*      */     
/*  912 */     this.m_Context.setVariable("CLI-LANG", cultureInfo.getLanguagePrefix());
/*  913 */     this.m_Context.setVariable("CLI-CULTUREINFO", cultureInfo);
/*  914 */     this.m_Context.setVariable("CLI-LANGNR", Integer.valueOf(cultureInfo.getLanguageID()));
/*      */     
/*  916 */     JLbsLocalizer.setCultureInfo((ILbsCultureInfo)cultureInfo);
/*  917 */     JLbsRealNumberFormatter.setCultureInfo((ILbsCultureInfo)cultureInfo);
/*  918 */     if (!StringUtil.isEmpty(this.m_SelectedTimeZone)) {
/*      */       
/*  920 */       TimeZone timeZone = JLbsCultureInfoBase.string2TimeZone(this.m_SelectedTimeZone);
/*  921 */       if (timeZone != null) {
/*      */         
/*  923 */         TimeZone.setDefault(timeZone);
/*  924 */         System.setProperty("user.timezone", this.m_SelectedTimeZone);
/*  925 */         JLbsDateUtil.resetDateFormaters();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applySelectedLanguage() {
/*  934 */     setContextLanguageParameters();
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
/*      */   protected String getImageName() {
/*  963 */     return this.m_ImageName;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getCapsLockImageName() {
/*  968 */     return "warning.png";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  976 */       byte[] imgData = this.m_Context.getResource(getImageName(), false);
/*  977 */       if (imgData != null) {
/*      */         
/*  979 */         final ImageIcon imageIcon = new ImageIcon(imgData);
/*  980 */         if (imageIcon != null) {
/*      */           
/*  982 */           this.loginImg = imageIcon;
/*  983 */           this.loginImg.getImage().getWidth(null);
/*  984 */           JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */               {
/*      */                 
/*      */                 public void run()
/*      */                 {
/*  989 */                   JLbsClientLoginPanel.this.lblHeader.setText("");
/*  990 */                   JLbsClientLoginPanel.this.lblHeader.setIcon(imageIcon);
/*  991 */                   JLbsClientLoginPanel.this.prepareButtonOKClickThread();
/*      */                 }
/*      */               });
/*      */         } 
/*      */       } else {
/*      */         
/*  997 */         prepareButtonOKClickThread();
/*      */       } 
/*  999 */     } catch (Exception e) {
/*      */       
/* 1001 */       if (JLbsConstants.DEBUG) {
/* 1002 */         System.out.println("img Thread: " + e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareButtonOKClickThread() {
/*      */     try {
/* 1010 */       JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/* 1015 */               if (JLbsClientLoginPanel.this.isUserInfoGiven()) {
/* 1016 */                 JLbsClientLoginPanel.this.buttonOKClick();
/*      */               }
/*      */             }
/*      */           });
/* 1020 */     } catch (Exception e) {
/*      */       
/* 1022 */       if (JLbsConstants.DEBUG) {
/* 1023 */         System.out.println("button click Thread: " + e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ILbsCultureInfo createCultureInfo(int languageNr) {
/* 1033 */     return JLbsCultureInfoBase.createInstance(languageNr);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getLoginIniFileName() {
/* 1038 */     return this.m_IniFileName;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JLbsIniProperties loadLoginProperties() {
/*      */     try {
/* 1045 */       byte[] localSettings = this.m_Context.loadLocalFile("Settings/" + getLoginIniFileName());
/* 1046 */       if (localSettings != null)
/*      */       {
/* 1048 */         JLbsIniProperties loginProp = new JLbsIniProperties();
/* 1049 */         loginProp.load(localSettings, JLbsConstants.CHAR_SET);
/*      */         
/* 1051 */         String loginName = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "UserName");
/* 1052 */         if (loginName != null && this.editUsername != null) {
/* 1053 */           this.editUsername.setText(loginName);
/*      */         }
/* 1055 */         String language = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Language");
/* 1056 */         String hasLTROrientation = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation");
/*      */         
/* 1058 */         if (language != null)
/*      */         {
/*      */           
/* 1061 */           for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */             
/* 1063 */             JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/* 1064 */             if (language.equals(jLbsCultureInfoBase.getLanguagePrefix()) && !JLbsStringUtil.isEmpty(hasLTROrientation) && 
/* 1065 */               hasLTROrientation.equals(String.valueOf(jLbsCultureInfoBase.hasLeftToRightOrientation()))) {
/*      */               
/* 1067 */               this.m_SelectedLanguage = i;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/* 1072 */         if (this.comboLanguage != null) {
/* 1073 */           this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */         } else {
/* 1075 */           updateLanguage();
/*      */         } 
/* 1077 */         String selectedTimeZone = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "TimeZone");
/* 1078 */         if (!StringUtil.isEmpty(selectedTimeZone)) {
/* 1079 */           this.m_SelectedTimeZone = selectedTimeZone;
/*      */         }
/* 1081 */         String selectedCompany = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Company");
/* 1082 */         if (selectedCompany != null) {
/* 1083 */           this.m_SelectedCompany = selectedCompany;
/*      */         }
/* 1085 */         return loginProp;
/*      */       }
/*      */     
/* 1088 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 1091 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JLbsIniProperties loadLoginProperties(String charSet) {
/*      */     try {
/* 1098 */       Object langPre = this.m_Context.getVariable("LANGUAGE");
/* 1099 */       this.m_Context.removeVariable("LANGUAGE");
/* 1100 */       String langPrefix = null;
/* 1101 */       if (langPre instanceof String && !langPre.equals("null")) {
/* 1102 */         langPrefix = (String)langPre;
/*      */       }
/* 1104 */       byte[] localSettings = this.m_Context.loadLocalFile("Settings/" + getLoginIniFileName());
/*      */       
/* 1106 */       if (localSettings == null && langPrefix != null) {
/*      */         
/* 1108 */         int idx = getLanguageIdx(langPrefix);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1113 */         if (this.comboLanguage != null) {
/* 1114 */           this.comboLanguage.setSelectedIndex(idx);
/*      */         } else {
/* 1116 */           this.m_SelectedLanguage = idx;
/* 1117 */           updateLanguage();
/*      */         } 
/* 1119 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1130 */       if (localSettings != null)
/*      */       {
/* 1132 */         this.loginProp = new JLbsIniProperties();
/* 1133 */         this.loginProp.load(localSettings, charSet);
/*      */         
/* 1135 */         String loginName = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "UserName");
/* 1136 */         if (loginName != null && this.editUsername != null) {
/* 1137 */           this.editUsername.setText(loginName);
/*      */         }
/* 1139 */         String selectedTimeZone = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "TimeZone");
/* 1140 */         if (!StringUtil.isEmpty(selectedTimeZone)) {
/* 1141 */           this.m_SelectedTimeZone = selectedTimeZone;
/*      */         }
/* 1143 */         String language = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Language");
/*      */         
/* 1145 */         if (langPrefix != null && !langPrefix.equals(language)) {
/*      */           
/* 1147 */           int idx = getLanguageIdx(langPrefix);
/* 1148 */           if (idx < 0)
/* 1149 */             idx = 0; 
/* 1150 */           if (this.comboLanguage != null) {
/* 1151 */             this.comboLanguage.setSelectedIndex(idx);
/*      */           } else {
/* 1153 */             this.m_SelectedLanguage = idx;
/* 1154 */             updateLanguage();
/*      */           } 
/* 1156 */           return null;
/*      */         } 
/*      */         
/* 1159 */         String hasLTROrientation = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation");
/*      */         
/* 1161 */         if (language != null)
/*      */         {
/*      */           
/* 1164 */           for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */             
/* 1166 */             JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/* 1167 */             if (language.equals(jLbsCultureInfoBase.getLanguagePrefix()) && !JLbsStringUtil.isEmpty(hasLTROrientation) && 
/* 1168 */               hasLTROrientation.equals(String.valueOf(jLbsCultureInfoBase.hasLeftToRightOrientation()))) {
/*      */               
/* 1170 */               this.m_SelectedLanguage = i;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/* 1175 */         if (this.m_SelectedLanguage < 0) {
/* 1176 */           this.m_SelectedLanguage = 0;
/*      */         }
/* 1178 */         if (this.comboLanguage != null) {
/* 1179 */           this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */         } else {
/* 1181 */           updateLanguage();
/*      */         } 
/* 1183 */         String selectedCompany = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Company");
/* 1184 */         if (selectedCompany != null) {
/* 1185 */           this.m_SelectedCompany = selectedCompany;
/*      */         }
/* 1187 */         return this.loginProp;
/*      */       }
/*      */     
/* 1190 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 1193 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getLanguageIdx(String langPrefix) {
/* 1198 */     if (this.supportedCultures == null) {
/* 1199 */       return -1;
/*      */     }
/* 1201 */     for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */       
/* 1203 */       JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/* 1204 */       if (langPrefix.equals(jLbsCultureInfoBase.getLanguagePrefix()))
/* 1205 */         return i; 
/*      */     } 
/* 1207 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveLoginProperties() {
/* 1212 */     JLbsIniProperties loginProp = new JLbsIniProperties();
/*      */     
/* 1214 */     fillIniProperties(loginProp);
/*      */     
/*      */     try {
/* 1217 */       byte[] localSettings = loginProp.toByteArray(JLbsConstants.CHAR_SET);
/* 1218 */       if (localSettings != null)
/*      */       {
/* 1220 */         JLbsClientFS.makeDirectory("Settings");
/* 1221 */         this.m_Context.saveLocalFile("Settings/" + getLoginIniFileName(), localSettings, true, false);
/*      */       }
/*      */     
/*      */     }
/* 1225 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillIniProperties(JLbsIniProperties loginProp) {
/* 1232 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "UserName", this.editUsername.getText());
/* 1233 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "Language", this.m_SelectedCulture.getLanguagePrefix());
/* 1234 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation", 
/* 1235 */         String.valueOf(this.m_SelectedCulture.hasLeftToRightOrientation()));
/* 1236 */     if (!StringUtil.isEmpty(this.m_SelectedTimeZone)) {
/* 1237 */       loginProp.setProperty(String.valueOf(this.loginPropHeader) + "TimeZone", this.m_SelectedTimeZone);
/*      */     }
/*      */   }
/*      */   
/*      */   protected TableLayoutConstraints getLayoutConstraints(int col, int row, int endCol) {
/* 1242 */     return getLayoutConstraints(col, row, endCol, row);
/*      */   }
/*      */ 
/*      */   
/*      */   protected TableLayoutConstraints getLayoutConstraints(int col, int row, int endCol, int endRow) {
/* 1247 */     String constraints = String.valueOf(col) + ", " + row + "," + endCol + ", " + endRow + ", LD, C";
/* 1248 */     return new TableLayoutConstraints(constraints);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLabel addLabel(String text, int col, int row, int width, int height, int endCol) {
/* 1253 */     return addLabeltoPanel(this.pnlMain, text, col, row, endCol);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLabel addLabeltoPanel(JPanel panel, String text, int col, int row, int endCol) {
/* 1258 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1259 */     JLbsLabel label = new JLbsLabel(text);
/* 1260 */     Font f = this.m_SelectedCulture.getFont();
/* 1261 */     if (f != null)
/* 1262 */       label.setFont(f); 
/* 1263 */     panel.add((Component)label, constraint);
/* 1264 */     return (JLabel)label;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsLinkLabel addLinkLabel(String text, int col, int row, int width, int height, int endCol) {
/* 1270 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1271 */     JLbsLinkLabel label = new JLbsLinkLabel();
/* 1272 */     label.setBorder(new EmptyBorder(0, 0, 0, 0));
/* 1273 */     label.setText(text);
/* 1274 */     this.pnlMain.add((Component)label, constraint);
/* 1275 */     label.setClickListener(new LinkLabelListener());
/* 1276 */     return label;
/*      */   }
/*      */ 
/*      */   
/*      */   public JCheckBox addCheckBox(int col, int row, int width, int height, int endCol, String text) {
/* 1281 */     TableLayoutConstraints contraint = getLayoutConstraints(col, row, endCol);
/* 1282 */     this.chk = new JCheckBox();
/* 1283 */     this.chk.setText(text);
/* 1284 */     this.chk.addKeyListener(this);
/* 1285 */     this.chk.setPreferredSize(new Dimension(width, height));
/* 1286 */     this.chk.setSize(width, height);
/* 1287 */     this.pnlMain.add(this.chk, contraint);
/*      */     
/* 1289 */     return this.chk;
/*      */   }
/*      */ 
/*      */   
/*      */   public class LinkLabelListener
/*      */     implements MouseListener
/*      */   {
/*      */     public void mouseClicked(MouseEvent e) {
/* 1297 */       if (e.getSource() == JLbsClientLoginPanel.this.lblRemember) {
/*      */         
/* 1299 */         JLbsRememberPassEditor editor = new JLbsRememberPassEditor(JLbsClientLoginPanel.this.m_Context, JLbsClientLoginPanel.this.m_SelectedCulture, JLbsClientLoginPanel.this.editUsername.getText());
/* 1300 */         editor.show();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JTextField addTextField(int col, int row, int width, int height, int endCol) {
/* 1327 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1328 */     JTextField textField = new JTextField();
/* 1329 */     textField.addKeyListener(this);
/* 1330 */     textField.setPreferredSize(new Dimension(width, height));
/* 1331 */     textField.setSize(width, height);
/* 1332 */     this.pnlMain.add(textField, constraint);
/* 1333 */     return textField;
/*      */   }
/*      */ 
/*      */   
/*      */   public JPasswordField addPasswordField(int col, int row, int width, int height, int endCol) {
/* 1338 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1340 */     JPasswordField passwordField = new JPasswordField();
/* 1341 */     passwordField.setEchoChar('●');
/* 1342 */     passwordField.addKeyListener(this);
/* 1343 */     passwordField.setPreferredSize(new Dimension(width, height));
/* 1344 */     passwordField.setSize(width, height);
/* 1345 */     this.pnlMain.add(passwordField, constraint);
/* 1346 */     return passwordField;
/*      */   }
/*      */ 
/*      */   
/*      */   public JProgressBar addProgress(int col, int row, int width, int height, int endCol) {
/* 1351 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1352 */     JProgressBar bar = new JProgressBar();
/* 1353 */     bar.setBorder((Border)null);
/* 1354 */     bar.setMinimum(0);
/* 1355 */     bar.setMaximum(100);
/* 1356 */     bar.setPreferredSize(new Dimension(width, height));
/* 1357 */     bar.setSize(width, height);
/* 1358 */     bar.setBackground(Color.WHITE);
/* 1359 */     bar.setForeground(Color.DARK_GRAY);
/* 1360 */     bar.setOpaque(true);
/* 1361 */     this.pnlMain.add(bar, constraint);
/* 1362 */     return bar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JButton addButton(String text, int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1369 */     return addButtontoPanel(this.pnlMain, text, col, row, width, height, defButton, endCol);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JButton addButtontoPanel(JPanel panel, String text, int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1375 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1377 */     JButton button = new JButton();
/* 1378 */     UIHelperUtil.setCaption(button, text);
/* 1379 */     if (defButton)
/* 1380 */       button.setDefaultCapable(true); 
/* 1381 */     button.addKeyListener(this);
/* 1382 */     button.addActionListener(this);
/* 1383 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 1384 */     button.setPreferredSize(new Dimension(width, height));
/* 1385 */     button.setSize(width, height);
/* 1386 */     panel.add(button, constraint);
/* 1387 */     return button;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsImageButton addImageButton(int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1393 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1395 */     JLbsImageButton button = new JLbsImageButton()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public void updateUI() {
/* 1402 */           setUI(new MetalButtonUI());
/*      */         }
/*      */       };
/* 1405 */     if (defButton)
/* 1406 */       button.setDefaultCapable(true); 
/* 1407 */     button.addKeyListener(this);
/* 1408 */     button.addActionListener(this);
/* 1409 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 1410 */     button.setPreferredSize(new Dimension(width, height));
/* 1411 */     button.setSize(width, height);
/* 1412 */     this.pnlMain.add((Component)button, constraint);
/* 1413 */     return button;
/*      */   }
/*      */ 
/*      */   
/*      */   public JComboBox addComboBox(int col, int row, int width, int height, int endCol) {
/* 1418 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1420 */     JComboBox comboBox = new JComboBox();
/* 1421 */     comboBox.addActionListener(this);
/* 1422 */     comboBox.addKeyListener(this);
/* 1423 */     comboBox.setSize(width, height);
/* 1424 */     this.pnlMain.add(comboBox, constraint);
/*      */     
/* 1426 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsComboBox addLbsComboBox(int col, int row, int width, int height, int endCol) {
/* 1431 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1433 */     JLbsComboBox comboBox = new JLbsComboBox();
/* 1434 */     comboBox.addActionListener(this);
/* 1435 */     comboBox.addKeyListener(this);
/* 1436 */     comboBox.setSize(width, height);
/* 1437 */     this.pnlMain.add((Component)comboBox, constraint);
/*      */     
/* 1439 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public JComboBox addComboBoxWithImage(int col, int row, int width, int height, int endCol) {
/* 1444 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1446 */     JComboBox comboBox = new JComboBox();
/* 1447 */     comboBox.addActionListener(this);
/* 1448 */     comboBox.addKeyListener(this);
/* 1449 */     comboBox.setSize(width, height);
/*      */     
/* 1451 */     ComboBoxRenderer renderer = new ComboBoxRenderer();
/* 1452 */     renderer.setPreferredSize(new Dimension(width, height));
/* 1453 */     comboBox.setRenderer(renderer);
/*      */     
/* 1455 */     this.pnlMain.add(comboBox, constraint);
/*      */     
/* 1457 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doOKBase() {
/* 1462 */     if (checkDBError()) {
/* 1463 */       doOK();
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean checkDBError() {
/* 1468 */     if (this.m_DBError == 1) {
/*      */       
/* 1470 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1029));
/* 1471 */       return false;
/*      */     } 
/* 1473 */     if (this.m_DBError == 2) {
/*      */       
/* 1475 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1027));
/* 1476 */       return false;
/*      */     } 
/*      */     
/* 1479 */     if (this.m_DBError == 3) {
/*      */       
/* 1481 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1025));
/* 1482 */       return false;
/*      */     } 
/* 1484 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doCancel() {
/* 1491 */     this.m_Context.terminateApplication();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFocusToUser() {
/* 1496 */     String uName = this.editUsername.getText();
/* 1497 */     if (uName == null || uName.length() == 0) {
/* 1498 */       this.editUsername.grabFocus();
/*      */     } else {
/* 1500 */       this.editPassword.grabFocus();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected UserInfo createLoginUserInfo() {
/* 1506 */     UserInfo userInfo = new UserInfo();
/* 1507 */     userInfo.Name = this.editUsername.getText().trim();
/* 1508 */     userInfo.Password = new String(this.editPassword.getPassword());
/*      */ 
/*      */     
/* 1511 */     userInfo.LtpaToken = this.ltpaToken;
/* 1512 */     userInfo.selectedLanguage = this.m_SelectedCulture.getLanguagePrefix();
/* 1513 */     userInfo.variableHolder = this.m_Context; return userInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void openMainForm(UserInfo userInfo) {
/* 1519 */     Object o = this.m_Context.getVariable("CLI-ALLOWED-DAYS");
/* 1520 */     if (o instanceof Integer) {
/*      */       
/* 1522 */       int allowedDays = ((Integer)o).intValue();
/* 1523 */       if (allowedDays != Integer.MAX_VALUE) {
/*      */         
/* 1525 */         String s = this.m_Context.getLocalizationService().getItem(JLbsConstants.GLOBAL_RESOURCES, 19);
/* 1526 */         if (StringUtil.isEmpty(s))
/* 1527 */           s = "Your license has expired. In ~1 days your product will become unusable."; 
/* 1528 */         s = JLbsStringUtil.mergeParameters(s, new String[] { (new StringBuilder(String.valueOf(allowedDays))).toString() }, new int[] { 1 });
/* 1529 */         final String message = s;
/* 1530 */         SwingUtilities.invokeLater(new Runnable()
/*      */             {
/*      */               
/*      */               public void run()
/*      */               {
/* 1535 */                 JLbsClientLoginPanel.this.m_Context.showMessage("", message);
/*      */               }
/*      */             });
/*      */       } 
/*      */     } 
/*      */     
/* 1541 */     this.m_Context.setSessionTimeout(3600000);
/*      */     
/* 1543 */     if (this.threadBackground != null) {
/*      */ 
/*      */       
/*      */       try {
/* 1547 */         Thread runner = this.threadBackground;
/* 1548 */         this.threadBackground = null;
/* 1549 */         runner.interrupt();
/*      */       
/*      */       }
/* 1552 */       catch (Exception exception) {}
/*      */ 
/*      */       
/* 1555 */       this.threadBackground = null;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1560 */       if (JLbsConstants.USE_LOCALIZATION_SERVICES) {
/* 1561 */         this.m_Context.startLoadingLocalizationCache();
/*      */       }
/* 1563 */       setProgressIndex(60);
/*      */       
/* 1565 */       loadJAR(this.m_JarFiles);
/*      */       
/* 1567 */       setProgressIndex(80);
/*      */       
/* 1569 */       Object obj = getMainFormInstance(userInfo);
/*      */       
/* 1571 */       setProgressIndex(90);
/*      */       
/* 1573 */       if (obj instanceof JComponent) {
/*      */         
/* 1575 */         this.m_Context.replaceContent((JComponent)obj);
/* 1576 */         if (obj instanceof IClientDataConsumer)
/*      */         {
/* 1578 */           if (obj instanceof ITokenProcessor) {
/*      */             
/* 1580 */             if (((ITokenProcessor)obj).isShowWithProduct()) {
/* 1581 */               ((IClientDataConsumer)obj).initializeData(userInfo, null);
/*      */             }
/*      */           } else {
/* 1584 */             ((IClientDataConsumer)obj).initializeData(userInfo, null);
/*      */           } 
/*      */         }
/*      */       } else {
/*      */         
/* 1589 */         this.m_Context.showMessage("LoginForm!", "Could not initialize the MainForm!");
/*      */       } 
/*      */       
/* 1592 */       this.m_Context.setCustomizationResourceList();
/*      */       
/* 1594 */       setProgressIndex(100);
/*      */     }
/* 1596 */     catch (Exception ex) {
/*      */       
/* 1598 */       System.out.println("Exception in LoginForm while creating the MainForm! " + ex.getMessage() + " - " + ex.getCause());
/* 1599 */       ex.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareExitJavascript() {
/*      */     try {
/* 1607 */       JApplet baseApplet = getBaseApplet();
/* 1608 */       if (baseApplet != null) {
/*      */         
/* 1610 */         Class<?> c = Class.forName("netscape.javascript.JSObject");
/* 1611 */         Method m = c.getMethod("getWindow", new Class[] { Applet.class });
/* 1612 */         Object window = m.invoke(null, new Object[] { baseApplet });
/* 1613 */         if (window != null)
/*      */         {
/* 1615 */           m = c.getMethod("eval", new Class[] { String.class });
/* 1616 */           String message = this.m_Context.getLocalizationService().getItem(-1000, 33);
/* 1617 */           if (StringUtil.isEmpty(message))
/*      */           {
/* 1619 */             message = "If you close the application using this button you may loose your unsaved changes. To be able to save your unsaved changes, you can use the logout button from the toolbar. Do you still want to leave?";
/*      */           }
/*      */ 
/*      */           
/* 1623 */           m.invoke(window, new Object[] { " $(window).bind('beforeunload', function() {return '" + message + "';" + "});" });
/*      */         }
/*      */       
/*      */       } 
/* 1627 */     } catch (Exception e) {
/*      */       
/* 1629 */       if (JLbsConstants.DEBUG) {
/* 1630 */         e.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Object getMainFormInstance(UserInfo userInfo) throws Exception {
/* 1636 */     return this.m_Context.createInstance(this.m_MainForm);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent event) {
/* 1642 */     Object evtSource = event.getSource();
/* 1643 */     if (evtSource == this.btnCancel) {
/*      */       
/* 1645 */       doCancel();
/*      */     }
/* 1647 */     else if (evtSource == this.btnOk && this.m_Context != null) {
/*      */       
/* 1649 */       doOKBase();
/*      */     }
/* 1651 */     else if (evtSource == this.comboLanguage && this.m_Context != null) {
/*      */       
/* 1653 */       this.m_SelectedLanguage = ((JLbsStringListItem)this.comboLanguage.getSelectedItem()).Tag;
/* 1654 */       updateLanguage();
/*      */       
/* 1656 */       if (this.m_SelectedCulture.getFont() != null) {
/*      */         
/* 1658 */         JLbsConstants.APP_FONT = this.m_SelectedCulture.getFont().getName();
/* 1659 */         JLbsConstants.APP_FONT_SIZE = this.m_SelectedCulture.getFont().getSize();
/*      */       }
/*      */       else {
/*      */         
/* 1663 */         JLbsConstants.APP_FONT = JLbsConstants.APP_FONT_DEFAULT;
/* 1664 */         JLbsConstants.APP_FONT_SIZE = JLbsConstants.APP_SIZE_DEFAULT;
/*      */       } 
/* 1666 */       UIManager.put("ToolTip.font", new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/* 1667 */       UIManager.put("Button.font", new FontUIResource(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE - 1));
/*      */       
/* 1669 */       this.m_Context.changeTheme("com.lbs.laf.mac.LookAndFeel");
/*      */       
/* 1671 */       applyComponentOrientation(this.m_SelectedCulture.getComponentOrientation());
/* 1672 */       applyComponentFont(this.m_SelectedCulture.getFont());
/*      */     
/*      */     }
/* 1675 */     else if (evtSource == this.btnLocaleTimeZone) {
/*      */ 
/*      */       
/* 1678 */       TimeZoneSettingsDialog dialog = new TimeZoneSettingsDialog();
/* 1679 */       String timeZone = this.m_SelectedTimeZone;
/* 1680 */       if (StringUtil.isEmpty(timeZone))
/*      */       {
/* 1682 */         timeZone = TimeZone.getDefault().getID();
/*      */       }
/* 1684 */       dialog.setSelectedTimeZone(timeZone);
/* 1685 */       dialog.setModal(true);
/* 1686 */       dialog.show();
/* 1687 */       if (dialog.m_Ok) {
/* 1688 */         this.m_SelectedTimeZone = dialog.m_SelectedTimeZone;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void applyComponentFont(Font font) {
/* 1694 */     if (font == null) {
/* 1695 */       font = new Font(JLbsConstants.APP_FONT_DEFAULT, JLbsConstants.APP_STYLE_DEFAULT, JLbsConstants.APP_SIZE_DEFAULT);
/*      */     }
/* 1697 */     if (this.lblHeader != null)
/* 1698 */       this.lblHeader.setFont(font); 
/* 1699 */     if (this.lblUsername != null)
/* 1700 */       this.lblUsername.setFont(font); 
/* 1701 */     if (this.lblPassword != null)
/* 1702 */       this.lblPassword.setFont(font); 
/* 1703 */     if (this.lblLanguage != null)
/* 1704 */       this.lblLanguage.setFont(font); 
/* 1705 */     if (this.lblVersion != null)
/* 1706 */       this.lblVersion.setFont(font); 
/* 1707 */     if (this.lblLinkAdvanced != null)
/* 1708 */       this.lblLinkAdvanced.setFont(font); 
/* 1709 */     if (this.lblRemember != null)
/* 1710 */       this.lblRemember.setFont(font); 
/* 1711 */     if (this.editUsername != null)
/* 1712 */       this.editUsername.setFont(font); 
/* 1713 */     if (this.editPassword != null)
/* 1714 */       this.editPassword.setFont(new Font(JLbsConstants.APP_FONT_DEFAULT, JLbsConstants.APP_STYLE_DEFAULT, 
/* 1715 */             JLbsConstants.APP_SIZE_DEFAULT)); 
/* 1716 */     if (this.capsLockTip != null)
/* 1717 */       this.capsLockTip.setFont(font); 
/* 1718 */     if (this.comboLanguage != null)
/* 1719 */       this.comboLanguage.setFont(font); 
/* 1720 */     if (this.btnOk != null)
/* 1721 */       this.btnOk.setFont(new Font(font.getFontName(), 1, font.getSize() - 1)); 
/* 1722 */     if (this.btnCancel != null)
/* 1723 */       this.btnCancel.setFont(new Font(font.getFontName(), 1, font.getSize() - 1)); 
/* 1724 */     if (this.btnLocaleTimeZone != null)
/* 1725 */       this.btnLocaleTimeZone.setFont(font); 
/* 1726 */     if (this.chk != null) {
/* 1727 */       this.chk.setFont(font);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/* 1733 */     if (e.getModifiers() != 0) {
/*      */       return;
/*      */     }
/* 1736 */     int key = e.getKeyCode();
/*      */     
/* 1738 */     switch (key) {
/*      */       
/*      */       case 27:
/* 1741 */         System.out.println("Escape");
/* 1742 */         doCancel();
/*      */         break;
/*      */       
/*      */       case 10:
/* 1746 */         System.out.println("Enter");
/* 1747 */         doOKBase();
/*      */         break;
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
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIniLanguage(String iniLanguage) {
/* 1764 */     this.m_IniLanguage = iniLanguage;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getIniLanguage() {
/* 1769 */     return this.m_IniLanguage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setShowVersionText(boolean showVersionText) {
/* 1774 */     this.m_ShowVersionText = showVersionText;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isShowVersionText() {
/* 1779 */     return this.m_ShowVersionText;
/*      */   }
/*      */   
/*      */   class ComboBoxRenderer
/*      */     extends JLabel
/*      */     implements ListCellRenderer {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     public ComboBoxRenderer() {
/* 1788 */       setOpaque(true);
/*      */       
/* 1790 */       setVerticalAlignment(0);
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
/*      */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 1815 */       if (value == null) {
/* 1816 */         return this;
/*      */       }
/* 1818 */       if (isSelected) {
/*      */         
/* 1820 */         setBackground(list.getSelectionBackground());
/* 1821 */         setForeground(list.getSelectionForeground());
/*      */       }
/*      */       else {
/*      */         
/* 1825 */         setBackground(list.getBackground());
/* 1826 */         setForeground(list.getForeground());
/*      */       } 
/*      */       
/* 1829 */       ImageIcon icon = ((JLbsClientLoginPanel.LanguageComboItemWithImage)value).getIcon();
/* 1830 */       String text = ((JLbsClientLoginPanel.LanguageComboItemWithImage)value).getValue();
/* 1831 */       String tooltip = ((JLbsClientLoginPanel.LanguageComboItemWithImage)value).getTooltipText();
/* 1832 */       Font font = ((JLbsClientLoginPanel.LanguageComboItemWithImage)value).getFont();
/*      */       
/* 1834 */       if (icon != null) {
/* 1835 */         setIcon(icon);
/*      */       }
/* 1837 */       setText(text);
/*      */       
/* 1839 */       if (font == null) {
/* 1840 */         setFont(list.getFont());
/*      */       } else {
/* 1842 */         setFont(font);
/*      */       } 
/* 1844 */       setToolTipText(tooltip);
/*      */       
/* 1846 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */   class LanguageComboItem
/*      */     extends JLbsStringListItem
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     private Font m_Font;
/*      */     
/*      */     public LanguageComboItem(String s) {
/* 1857 */       super(s);
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItem(String s, int iTag) {
/* 1862 */       super(s, iTag);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1868 */       return this.Value;
/*      */     }
/*      */ 
/*      */     
/*      */     public Font getFont() {
/* 1873 */       return this.m_Font;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFont(Font font) {
/* 1878 */       this.m_Font = font;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class LanguageComboItemWithImage
/*      */     extends JLbsStringListItem
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     private ImageIcon m_icon;
/*      */     private int m_LanguageID;
/*      */     private Font m_Font;
/*      */     
/*      */     public LanguageComboItemWithImage(String s, ImageIcon icon) {
/* 1892 */       super(s);
/* 1893 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public Font getFont() {
/* 1898 */       return this.m_Font;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFont(Font font) {
/* 1903 */       this.m_Font = font;
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItemWithImage(String s, int iTag, ImageIcon icon) {
/* 1908 */       super(s, iTag);
/* 1909 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItemWithImage(String s, int iTag) {
/* 1914 */       super(s, iTag);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1920 */       return this.Value;
/*      */     }
/*      */ 
/*      */     
/*      */     public ImageIcon getIcon() {
/* 1925 */       return this.m_icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setIcon(ImageIcon icon) {
/* 1930 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setLanguageID(int languageID) {
/* 1935 */       this.m_LanguageID = languageID;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getTooltipText() {
/* 1940 */       if (JLbsClientLoginPanel.this.m_SelectedCulture != null)
/*      */       {
/* 1942 */         return JLbsClientLoginPanel.this.m_SelectedCulture.getLanguageName(this.m_LanguageID);
/*      */       }
/* 1944 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class TimeZoneSettingsDialog
/*      */     extends JLbsDialog
/*      */     implements ActionListener
/*      */   {
/*      */     private static final int RES_TIMEZONE = 1;
/*      */     private static final int RES_OK = 2;
/*      */     private static final int RES_CANCEL = 3;
/*      */     private final JLbsComboBox m_TimeZoneCombo;
/*      */     private final JButton m_OkButton;
/*      */     private final JButton m_CancelButton;
/*      */     private String m_SelectedTimeZone;
/*      */     private boolean m_Ok = false;
/*      */     
/*      */     public TimeZoneSettingsDialog() {
/* 1963 */       JPanel contentPane = new JPanel();
/*      */       
/* 1965 */       JLabel timeZoneLabel = new JLabel();
/* 1966 */       timeZoneLabel.setText(getResource(1));
/* 1967 */       this.m_TimeZoneCombo = new JLbsComboBox();
/* 1968 */       this.m_TimeZoneCombo.setItems(JLbsCultureInfoBase.getTimeZones());
/*      */       
/* 1970 */       JPanel panel = new JPanel();
/* 1971 */       panel.add(timeZoneLabel);
/* 1972 */       panel.add((Component)this.m_TimeZoneCombo);
/*      */       
/* 1974 */       JPanel buttonPanel = new JPanel();
/* 1975 */       this.m_OkButton = new JButton(getResource(2));
/* 1976 */       this.m_OkButton.addActionListener(this);
/* 1977 */       this.m_CancelButton = new JButton(getResource(3));
/* 1978 */       this.m_CancelButton.addActionListener(this);
/* 1979 */       buttonPanel.add(this.m_OkButton);
/* 1980 */       buttonPanel.add(this.m_CancelButton);
/*      */       
/* 1982 */       contentPane.setLayout(new BorderLayout());
/* 1983 */       contentPane.add(panel, "Center");
/* 1984 */       contentPane.add(buttonPanel, "South");
/*      */       
/* 1986 */       setContentPane(contentPane);
/* 1987 */       setSize(350, 120);
/* 1988 */       centerScreen();
/* 1989 */       setTitle(getResource(1));
/*      */     }
/*      */ 
/*      */     
/*      */     public void setSelectedTimeZone(String selectedTimeZone) {
/* 1994 */       selectedTimeZone = searchTimeZoneValue(selectedTimeZone);
/* 1995 */       this.m_SelectedTimeZone = selectedTimeZone;
/* 1996 */       if (this.m_TimeZoneCombo != null)
/*      */       {
/* 1998 */         this.m_TimeZoneCombo.setSelectedItemValue(this.m_SelectedTimeZone);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private String searchTimeZoneValue(String selectedTimeZone) {
/* 2004 */       JLbsStringList list = new JLbsStringList(this.m_TimeZoneCombo.getItemsSList());
/* 2005 */       if (list != null)
/*      */       {
/* 2007 */         for (int i = 0; i < list.size(); i++) {
/*      */           
/* 2009 */           String listValue = list.getValueAt(i);
/* 2010 */           if (listValue.indexOf(selectedTimeZone) > 0)
/*      */           {
/* 2012 */             return listValue;
/*      */           }
/*      */         } 
/*      */       }
/* 2016 */       return selectedTimeZone;
/*      */     }
/*      */ 
/*      */     
/*      */     private String getResource(int resTag) {
/* 2021 */       String resource = "";
/* 2022 */       if (JLbsClientLoginPanel.this.m_SelectedCulture != null)
/*      */       {
/* 2024 */         switch (resTag) {
/*      */           
/*      */           case 1:
/* 2027 */             resource = JLbsClientLoginPanel.this.m_SelectedCulture.getTimeZone();
/*      */             break;
/*      */           case 2:
/* 2030 */             resource = JLbsClientLoginPanel.this.m_SelectedCulture.getOK();
/*      */             break;
/*      */           case 3:
/* 2033 */             resource = JLbsClientLoginPanel.this.m_SelectedCulture.getCancel();
/*      */             break;
/*      */         } 
/*      */       }
/* 2037 */       if (StringUtil.isEmpty(resource))
/*      */       {
/* 2039 */         switch (resTag) {
/*      */           
/*      */           case 1:
/* 2042 */             resource = "Time Zone";
/*      */             break;
/*      */           case 2:
/* 2045 */             resource = "Ok";
/*      */             break;
/*      */           case 3:
/* 2048 */             resource = "Cancel";
/*      */             break;
/*      */         } 
/*      */       }
/* 2052 */       return resource;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2058 */       if (e.getSource() == this.m_OkButton) {
/*      */         
/* 2060 */         this.m_SelectedTimeZone = String.valueOf(this.m_TimeZoneCombo.getSelectedItemValue());
/* 2061 */         this.m_Ok = true;
/* 2062 */         dispose();
/*      */       }
/* 2064 */       else if (e.getSource() == this.m_CancelButton) {
/*      */         
/* 2066 */         dispose();
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
/*      */   protected UserInfo getLdapUserInfo() {
/* 2083 */     return ldapUserInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLdapUserInfo(UserInfo userInfo) {
/* 2088 */     ldapUserInfo = userInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getAccess_token() {
/* 2093 */     return this.access_token;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAccess_token(String access_token) {
/* 2098 */     this.access_token = access_token;
/*      */   }
/*      */   
/*      */   protected abstract String getVersionText();
/*      */   
/*      */   protected abstract void createUserComponents();
/*      */   
/*      */   protected abstract void finalizeUserComponentProps();
/*      */   
/*      */   protected abstract boolean canShowCaptcha();
/*      */   
/*      */   protected abstract JApplet getBaseApplet();
/*      */   
/*      */   protected abstract JApplet getApplet();
/*      */   
/*      */   protected abstract void createDSignatureComponents();
/*      */   
/*      */   protected abstract void addBtnLocaleTimezone();
/*      */   
/*      */   protected abstract String getServerName();
/*      */   
/*      */   protected abstract void setVersionStr(String paramString);
/*      */   
/*      */   protected abstract void doOK();
/*      */   
/*      */   public abstract void loginViaSSOWith(UserInfo paramUserInfo);
/*      */   
/*      */   public abstract void loginViaLdapWith(UserInfo paramUserInfo);
/*      */   
/*      */   public abstract void login(UserInfo paramUserInfo);
/*      */   
/*      */   public abstract JLbsLinkLabel addAdvancedLink();
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsClientLoginPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */