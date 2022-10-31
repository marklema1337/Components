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
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
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
/*      */ import net.java.balloontip.BalloonTip;
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
/*      */ public abstract class JLbsDesktopClientLoginPanel
/*      */   extends JLbsClientPanel
/*      */   implements ActionListener, KeyListener, Runnable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected static final boolean COMBO_WITH_IMAGE = true;
/*      */   protected static final String ms_SettingsDir = "Settings";
/*   93 */   protected static final String[] CAPTCHA_OP_LIST = new String[] { "+", "-" };
/*      */   
/*      */   protected static final int CAPTCHA_EXP_MAX = 20;
/*      */   
/*   97 */   protected int m_DBError = 0;
/*      */   
/*      */   protected JLbsCultureInfoBase[] supportedCultures;
/*      */   
/*      */   protected JTextField editUsername;
/*      */   
/*      */   protected JPasswordField editPassword;
/*      */   protected BalloonTip capsLockTip;
/*      */   protected JComboBox comboLanguage;
/*  106 */   protected String loginPropHeader = "";
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
/*      */   
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
/*      */   protected static final int editHeight = 33;
/*      */   protected static final int lineHeight = 26;
/*      */   protected static final int buttonWidth = 150;
/*      */   protected static final int buttonHeight = 33;
/*      */   protected static final int headerHeight = 76;
/*      */   protected static final int firstColumn = 10;
/*      */   protected static final int secondColumn = 110;
/*      */   protected int yLoc;
/*  141 */   protected int m_Row = 0;
/*      */   protected int m_SelectedLanguage;
/*      */   protected JLbsCultureInfoBase m_SelectedCulture;
/*      */   protected String m_SelectedCompany;
/*      */   protected String m_SelectedTimeZone;
/*  146 */   protected ComponentOrientation m_LastComponentOrientation = ComponentOrientation.UNKNOWN;
/*      */   
/*  148 */   protected Thread threadBackground = null;
/*      */ 
/*      */   
/*      */   protected Object userLoginInfo;
/*      */   
/*      */   protected JLbsCurrenciesBase currencyBase;
/*      */   
/*  155 */   protected String ltpaToken = "";
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
/*  169 */   protected int m_LoginFailTime = 0;
/*  170 */   protected int m_CaptchaShowLimit = 0;
/*      */   
/*  172 */   protected int extraComponentSize = 1;
/*  173 */   protected JLbsIniProperties loginProp = null;
/*      */   
/*      */   protected JPanel pnlCaptcha;
/*      */   
/*      */   protected JTextField editCaptchaResult;
/*      */   protected JLabel lblCaptcha;
/*  179 */   protected int buttonOkBeginCol = 2;
/*  180 */   protected int buttonOkEndCol = 3;
/*  181 */   protected int buttonCancelBeginCol = 4;
/*  182 */   protected int buttonCancelEndCol = 6;
/*      */ 
/*      */ 
/*      */   
/*      */   private static UserInfo ldapUserInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setContext(final IClientContext context) {
/*  192 */     super.setContext(context);
/*      */ 
/*      */     
/*  195 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/*  200 */             LAFPluginManager.installPreferenceLAF(context);
/*  201 */             JLbsComponentHelper.setCurrentContext(context);
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
/*  212 */     this.m_Title = title;
/*  213 */     this.m_ImageName = imageName;
/*  214 */     this.m_IniFileName = iniFileName;
/*  215 */     this.m_JarFiles = jarFiles;
/*  216 */     this.m_MainForm = mainForm;
/*      */     
/*  218 */     ClientContractService.setCacheEnabled(true);
/*      */     
/*  220 */     initSupportedCultures();
/*  221 */     this.m_SelectedLanguage = 0;
/*  222 */     this.m_SelectedCulture = this.supportedCultures[this.m_SelectedLanguage];
/*      */     
/*  224 */     setLayout(new BorderLayout());
/*  225 */     setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
/*  226 */     this.lblHeader = new JLabel();
/*  227 */     this.lblHeader.setFocusable(false);
/*  228 */     this.lblHeader.setText(getHeaderLabel());
/*  229 */     this.lblHeader.setForeground(Color.BLUE);
/*  230 */     this.lblHeader.setPreferredSize(new Dimension(515, 126));
/*  231 */     this.lblHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
/*  232 */     add(this.lblHeader, "North");
/*      */     
/*  234 */     initializeLayout();
/*      */     
/*  236 */     this.pnlMain = new JPanel();
/*  237 */     this.pnlMain.setBorder(new EmptyBorder(10, 10, 0, 10));
/*      */     
/*  239 */     setPreferredSize(height);
/*      */     
/*  241 */     this.pnlMain.setLayout((LayoutManager)this.mainLayout);
/*      */     
/*  243 */     createBottomPanel();
/*      */     
/*  245 */     createComponents();
/*  246 */     setDefaultLanguage();
/*      */     
/*  248 */     addMainPanel();
/*      */ 
/*      */     
/*  251 */     if (this.m_Context != null && this.threadBackground == null) {
/*      */       
/*  253 */       this.threadBackground = new Thread(this);
/*  254 */       this.threadBackground.start();
/*      */     } 
/*      */     
/*  257 */     createLanguageCombo();
/*      */     
/*  259 */     String serverName = getServerName();
/*  260 */     if (serverName != null)
/*      */     {
/*  262 */       this.loginPropHeader = String.valueOf(serverName) + ".";
/*      */     }
/*      */     
/*  265 */     if (isShowVersionText()) {
/*      */       
/*  267 */       String version = updateVersion();
/*  268 */       this.lblVersion.setText(version);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  275 */       loadLoginProperties(JLbsConstants.CHAR_SET);
/*      */       
/*  277 */       this.editUsername.selectAll();
/*  278 */       this.editPassword.selectAll();
/*      */     }
/*  280 */     catch (Exception e) {
/*      */       
/*  282 */       System.out.println(e);
/*      */     } 
/*      */     
/*  285 */     finalizeComponentProps();
/*      */     
/*      */     try {
/*  288 */       this.m_DBError = ((Integer)this.m_Context.getPublicObject("DBError", (Object)null, true)).intValue();
/*  289 */       checkDBError();
/*      */     }
/*  291 */     catch (Exception e) {
/*      */       
/*  293 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String updateVersion() {
/*  300 */     String version = JLbsConstants.VERSION_STR;
/*      */ 
/*      */     
/*  303 */     if (JLbsStringUtil.isEmpty(version) || (
/*  304 */       !JLbsStringUtil.isEmpty(JLbsConstants.ORG_VERSION_STR) && version.equals(JLbsConstants.ORG_VERSION_STR))) {
/*      */ 
/*      */       
/*      */       try {
/*  308 */         version = (String)this.m_Context.getPublicObject("ApplicationVersion", (Object)null, true);
/*      */       }
/*  310 */       catch (Exception e) {
/*      */         
/*  312 */         this.m_Context.showError(e);
/*      */       } 
/*  314 */       if (!JLbsStringUtil.isEmpty(version))
/*  315 */         setVersionStr(version); 
/*      */     } 
/*  317 */     return version;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createBottomPanel() {
/*  322 */     this.pnlBottom = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addMainPanel() {
/*  327 */     add(this.pnlMain);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createLanguageCombo() {
/*  332 */     if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */       
/*  334 */       if (hasLanguageSupport())
/*      */       {
/*      */         
/*  337 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  339 */           JLbsCultureInfoBase cultureInfo = this.supportedCultures[i];
/*  340 */           ImageIcon imageIcon = createImageIcon("Flags//" + cultureInfo.getLanguagePrefix() + ".gif");
/*  341 */           if (imageIcon != null) {
/*  342 */             imageIcon.setDescription(cultureInfo.getCultureResStr(101));
/*      */           }
/*  344 */           LanguageComboItemWithImage item = this.comboLanguage.getItemAt(i);
/*  345 */           item.setIcon(imageIcon);
/*  346 */           item.setLanguageID(cultureInfo.getLanguageID());
/*      */         } 
/*      */       }
/*      */       
/*  350 */       ImageIcon icon = createImageIcon("World.png");
/*  351 */       if (icon != null) {
/*  352 */         this.btnLocaleTimeZone.setIcon(icon);
/*      */       } else {
/*  354 */         this.btnLocaleTimeZone.setText("...");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isUserInfoGiven() {
/*  360 */     return this.userInfoGiven;
/*      */   }
/*      */ 
/*      */   
/*      */   public void buttonOKClick() {
/*  365 */     this.btnOk.doClick();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPreferredSize(int height) {
/*  370 */     int extraComponent = 0;
/*  371 */     if (!hasLanguageSupport()) {
/*  372 */       extraComponent++;
/*      */     }
/*  374 */     this.pnlMain.setPreferredSize(new Dimension(515, height - 70 + extraComponent * 33));
/*      */   }
/*      */ 
/*      */   
/*      */   private void setDefaultLanguage() {
/*  379 */     int idx = getLanguageIdx(JLbsCultureInfoBase.getLanguagePrefix(2));
/*  380 */     if (idx != -1) {
/*      */       
/*  382 */       this.m_SelectedLanguage = idx;
/*  383 */       if (this.comboLanguage != null) {
/*  384 */         this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */       } else {
/*  386 */         updateLanguage();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean hasLanguageSupport() {
/*  392 */     if (this.supportedCultures.length == 0) {
/*  393 */       return false;
/*      */     }
/*  395 */     if (this.supportedCultures.length == 1) {
/*      */       
/*  397 */       String langPrefix = this.supportedCultures[0].getLanguagePrefix();
/*  398 */       if (!JLbsStringUtil.isEmpty(langPrefix) && langPrefix.equalsIgnoreCase("NEUT")) {
/*  399 */         return false;
/*      */       }
/*      */     } 
/*  402 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void finalizeComponentProps() {
/*  407 */     if (this.comboLanguage != null && this.comboLanguage.getItemCount() == 0) {
/*      */       
/*  409 */       this.comboLanguage.setEnabled(false);
/*      */       
/*  411 */       this.editUsername.setText("");
/*  412 */       this.editUsername.setEnabled(false);
/*  413 */       this.editPassword.setText("");
/*  414 */       this.editPassword.setEnabled(false);
/*      */       
/*  416 */       this.btnOk.setEnabled(false);
/*  417 */       this.btnLocaleTimeZone.setEnabled(false);
/*      */       
/*  419 */       finalizeUserComponentProps();
/*      */     } 
/*  421 */     updateUI();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initializeLayout() {
/*  426 */     double[] columns = { 95.0D, 30.0D, 20.0D, 65.0D, 10.0D, 25.0D, 25.0D };
/*  427 */     double[] rows = { 33.0D, 33.0D, 33.0D, 23.0D, 33.0D, 33.0D };
/*      */     
/*  429 */     innerInitializeLayout(columns, rows, 4);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void innerInitializeLayout(double[] columns, double[] rows, int thinRow) {
/*  434 */     int rowCount = rows.length;
/*  435 */     rowCount = getRowCount(rowCount);
/*      */     
/*  437 */     rows = new double[rowCount];
/*  438 */     for (int i = 0; i < rowCount; i++) {
/*      */       
/*  440 */       if (i == rowCount - thinRow) {
/*  441 */         rows[i] = 23.0D;
/*      */       } else {
/*  443 */         rows[i] = 33.0D;
/*      */       } 
/*      */     } 
/*  446 */     double[][] size = { columns, rows };
/*  447 */     this.mainLayout = new TableLayout(size);
/*  448 */     this.mainLayout.setVGap(3);
/*  449 */     this.mainLayout.setHGap(2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getRowCount(int rowCount) {
/*  454 */     rowCount++;
/*      */     
/*  456 */     if (!hasLanguageSupport())
/*  457 */       rowCount++; 
/*  458 */     return rowCount;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getHeaderLabel() {
/*  463 */     return this.m_Title;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSupportedCultureList() {
/*  468 */     Object o = null;
/*      */     
/*      */     try {
/*  471 */       o = this.m_Context.getPublicObject("SupportedLanguages", (Object)null, true);
/*      */     }
/*  473 */     catch (Exception e1) {
/*      */       
/*  475 */       this.m_Context.showError(e1);
/*      */     } 
/*      */     
/*  478 */     if (o instanceof ArrayList) {
/*      */       
/*  480 */       ArrayList supportedLanguages = (ArrayList)o;
/*  481 */       JLbsCultureConstants.SUPPORTED_CULTURES = new int[supportedLanguages.size()];
/*      */       
/*  483 */       for (int i = 0; i < supportedLanguages.size(); i++) {
/*      */         
/*  485 */         String langPrefix = String.valueOf(supportedLanguages.get(i));
/*  486 */         if (!JLbsStringUtil.isEmpty(langPrefix)) {
/*  487 */           JLbsCultureConstants.SUPPORTED_CULTURES[i] = JLbsCultureInfoBase.getLanguageTagOfPrefix(langPrefix);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initSupportedCultures() {
/*  494 */     updateSupportedCultureList();
/*  495 */     this.supportedCultures = JLbsCultureConstants.prepareCultureObjects();
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
/*      */   protected void createComponents() {
/*  510 */     if (!hasLanguageSupport()) {
/*      */       
/*  512 */       this.lblLocalizationWarning = addLabel("No language support! Contact your system administrator.", 0, this.m_Row, 200, 33, 
/*  513 */           5);
/*  514 */       this.lblLocalizationWarning.setForeground(Color.RED);
/*  515 */       this.m_Row++;
/*      */     } 
/*      */     
/*  518 */     this.lblUsername = addLabel(getCultureResStr(102), 0, this.m_Row, 200, 33, 0);
/*  519 */     this.editUsername = addTextField(1, this.m_Row, 420, 33, 7);
/*  520 */     this.m_Row++;
/*  521 */     this.lblPassword = addLabel(getCultureResStr(103), 0, this.m_Row, 200, 33, 0);
/*  522 */     this.editPassword = addPasswordField(1, this.m_Row, 325, 33, 7);
/*      */ 
/*      */ 
/*      */     
/*  526 */     this.m_Row++;
/*  527 */     createUserComponents();
/*      */     
/*  529 */     addLanguageAndProgressBar();
/*      */     
/*  531 */     this.m_Row++;
/*      */     
/*  533 */     createButtonPanel(this.m_Row);
/*      */     
/*  535 */     this.m_Row++;
/*  536 */     this.lblRemember = addLinkLabel(getCultureResStr(112), 0, this.m_Row, 200, 33, 2);
/*      */   }
/*      */ 
/*      */   
/*      */   private void createButtonPanel(int row) {
/*  541 */     JLbsPanel buttonPanel = new JLbsPanel();
/*      */     
/*  543 */     TableLayout buttonPnlLayout = new TableLayout(new double[] { -2.0D, 75.0D, -2.0D, 
/*  544 */           -2.0D }, new double[] { -2.0D });
/*  545 */     buttonPnlLayout.setVGap(2);
/*  546 */     buttonPnlLayout.setHGap(2);
/*  547 */     buttonPanel.setLayout((LayoutManager)buttonPnlLayout);
/*      */     
/*  549 */     this.lblVersion = addLabeltoPanel((JPanel)buttonPanel, isShowVersionText() ? 
/*  550 */         getVersionText() : 
/*  551 */         "", 0, 0, 0);
/*      */     
/*  553 */     this.lblLinkAdvanced = addAdvancedLink();
/*  554 */     if (this.lblLinkAdvanced != null && !JLbsConstants.ADVANCED_LOGIN) {
/*      */       
/*  556 */       TableLayoutConstraints tableLayoutConstraints = getLayoutConstraints(1, 0, 1);
/*  557 */       buttonPanel.add((Component)this.lblLinkAdvanced, tableLayoutConstraints);
/*      */     } 
/*      */     
/*  560 */     this.btnOk = addButtontoPanel((JPanel)buttonPanel, getCultureResStr(107), 2, 0, 150, 33, false, 
/*  561 */         2);
/*      */     
/*  563 */     this.btnCancel = addButtontoPanel((JPanel)buttonPanel, getCultureResStr(108), 3, 0, 150, 33, 
/*  564 */         false, 3);
/*      */     
/*  566 */     TableLayoutConstraints constraint = getLayoutConstraints(0, row, 6);
/*  567 */     this.pnlMain.add((Component)buttonPanel, constraint);
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
/*      */   protected void addLanguageAndProgressBar() {
/*  642 */     if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */       
/*  644 */       this.lblLanguage = addLabel(getCultureResStr(106), 0, this.m_Row, 200, 33, 0);
/*      */       
/*  646 */       ILbsCultureResInfo resInfo = null;
/*      */ 
/*      */       
/*  649 */       this.comboLanguage = addComboBoxWithImage(1, this.m_Row, 140, 33, 3);
/*      */       
/*  651 */       if (hasLanguageSupport())
/*      */       {
/*  653 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  655 */           JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/*  656 */           LanguageComboItemWithImage item = new LanguageComboItemWithImage(
/*  657 */               jLbsCultureInfoBase.getCultureResStr(101), i);
/*  658 */           item.setFont(jLbsCultureInfoBase.getFont());
/*  659 */           this.comboLanguage.addItem(item);
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
/*  679 */       this.btnLocaleTimeZone = addImageButton(4, this.m_Row, 20, 33, false, 4);
/*  680 */       this.btnLocaleTimeZone.setToolTipText((this.m_SelectedCulture == null) ? 
/*  681 */           "Time Zone" : 
/*  682 */           this.m_SelectedCulture.getTimeZone());
/*  683 */       createDSignatureComponents();
/*  684 */       this.m_Row++;
/*      */     } 
/*  686 */     this.progressBar = addProgress(0, this.m_Row, 300, 10, 5);
/*  687 */     this.progressBar.setVisible(false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ImageIcon createImageIcon(String path) {
/*      */     try {
/*  694 */       byte[] imgData = this.m_Context.getResource(path, false);
/*  695 */       if (imgData != null)
/*      */       {
/*  697 */         ImageIcon imageIcon = new ImageIcon(imgData);
/*  698 */         if (imageIcon != null)
/*      */         {
/*  700 */           return imageIcon;
/*      */         }
/*      */       }
/*      */     
/*  704 */     } catch (Exception e) {
/*      */       
/*  706 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  709 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void toggleProgress(boolean flag) {
/*  714 */     this.progressBar.setVisible(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setProgressIndex(int x) {
/*  719 */     this.progressBar.setValue(x);
/*  720 */     this.progressBar.paintImmediately(new Rectangle(this.progressBar.getPreferredSize()));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateLanguage(boolean readIni) {
/*  725 */     if (readIni)
/*      */     {
/*  727 */       if (JLbsStringUtil.isEmpty(getIniLanguage())) {
/*      */         
/*  729 */         this.m_SelectedCulture = this.supportedCultures[this.m_SelectedLanguage];
/*      */       }
/*      */       else {
/*      */         
/*  733 */         String lang = getIniLanguage();
/*      */ 
/*      */         
/*  736 */         for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */           
/*  738 */           JLbsCultureInfoBase cultureInfo = this.supportedCultures[i];
/*  739 */           if (cultureInfo.getLanguagePrefix().equals(lang)) {
/*  740 */             this.m_SelectedCulture = cultureInfo;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  747 */     if (this.m_SelectedCulture != null) {
/*      */       
/*  749 */       String langPrefix = this.m_SelectedCulture.getLanguagePrefix();
/*  750 */       if (langPrefix != null) {
/*      */         
/*  752 */         this.m_Context.setVariable("CLI-LANG", langPrefix);
/*  753 */         this.m_Context.setTerminateURI(langPrefix);
/*      */       } 
/*  755 */       JLbsLocalizer.setCultureInfo((ILbsCultureInfo)this.m_SelectedCulture);
/*  756 */       JLbsLocalizer.setCultureInfoRes((ILbsCultureResInfo)this.m_SelectedCulture);
/*      */       
/*  758 */       this.lblUsername.setText(this.m_SelectedCulture.getCultureResStr(102));
/*  759 */       this.lblPassword.setText(this.m_SelectedCulture.getCultureResStr(103));
/*  760 */       if (this.lblLanguage != null)
/*  761 */         this.lblLanguage.setText(this.m_SelectedCulture.getCultureResStr(106)); 
/*  762 */       UIHelperUtil.setCaption(this.btnOk, this.m_SelectedCulture.getCultureResStr(107));
/*  763 */       UIHelperUtil.setCaption(this.btnCancel, this.m_SelectedCulture.getCultureResStr(108));
/*      */       
/*  765 */       if (this.lblRemember != null)
/*  766 */         this.lblRemember.setText(this.m_SelectedCulture.getCultureResStr(112)); 
/*  767 */       if (this.lblLinkAdvanced != null) {
/*      */         
/*  769 */         StringBuilder stringBuilder = new StringBuilder();
/*  770 */         stringBuilder.append(this.m_SelectedCulture.getCultureResStr(120));
/*  771 */         stringBuilder.append(">");
/*  772 */         this.lblLinkAdvanced.setText(stringBuilder.toString());
/*      */       } 
/*  774 */       if (this.btnLocaleTimeZone != null)
/*  775 */         this.btnLocaleTimeZone.setToolTipText(this.m_SelectedCulture.getTimeZone()); 
/*  776 */       if (this.btnDSignature != null) {
/*  777 */         this.btnDSignature.setToolTipText(DSignatureLocalizer.getResource(this.m_Context, this.m_SelectedCulture.getLanguagePrefix(), 
/*  778 */               2));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateLanguage() {
/*  784 */     updateLanguage(true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getCultureResStr(int cultureResID) {
/*  789 */     return this.m_SelectedCulture.getCultureResStr(cultureResID);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void loadJAR(String jarName) {
/*      */     try {
/*  796 */       this.m_Context.loadJAR(jarName, false, true);
/*      */     
/*      */     }
/*  799 */     catch (Exception e) {
/*      */       
/*  801 */       System.out.println("Exception while loading " + jarName + ": " + e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setContextLanguageParameters() {
/*  807 */     JLbsCultureInfoBase cultureInfo = this.m_SelectedCulture;
/*      */     
/*  809 */     Locale.setDefault(cultureInfo.getLocale());
/*      */     
/*  811 */     this.m_Context.setVariable("CLI-LANG", cultureInfo.getLanguagePrefix());
/*  812 */     this.m_Context.setVariable("CLI-CULTUREINFO", cultureInfo);
/*  813 */     this.m_Context.setVariable("CLI-LANGNR", Integer.valueOf(cultureInfo.getLanguageID()));
/*      */     
/*  815 */     JLbsLocalizer.setCultureInfo((ILbsCultureInfo)cultureInfo);
/*  816 */     if (!StringUtil.isEmpty(this.m_SelectedTimeZone)) {
/*      */       
/*  818 */       TimeZone timeZone = JLbsCultureInfoBase.string2TimeZone(this.m_SelectedTimeZone);
/*  819 */       if (timeZone != null) {
/*      */         
/*  821 */         TimeZone.setDefault(timeZone);
/*  822 */         System.setProperty("user.timezone", this.m_SelectedTimeZone);
/*  823 */         JLbsDateUtil.resetDateFormaters();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applySelectedLanguage() {
/*  832 */     setContextLanguageParameters();
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
/*  861 */     return this.m_ImageName;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getCapsLockImageName() {
/*  866 */     return "warning.png";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  874 */       byte[] imgData = this.m_Context.getResource(getImageName(), false);
/*  875 */       if (imgData != null) {
/*      */         
/*  877 */         final ImageIcon imageIcon = new ImageIcon(imgData);
/*  878 */         if (imageIcon != null) {
/*      */           
/*  880 */           this.loginImg = imageIcon;
/*  881 */           this.loginImg.getImage().getWidth(null);
/*  882 */           JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */               {
/*      */                 
/*      */                 public void run()
/*      */                 {
/*  887 */                   JLbsDesktopClientLoginPanel.this.lblHeader.setText("");
/*  888 */                   JLbsDesktopClientLoginPanel.this.lblHeader.setIcon(imageIcon);
/*  889 */                   JLbsDesktopClientLoginPanel.this.prepareButtonOKClickThread();
/*      */                 }
/*      */               });
/*      */         } 
/*      */       } else {
/*      */         
/*  895 */         prepareButtonOKClickThread();
/*      */       } 
/*  897 */     } catch (Exception e) {
/*      */       
/*  899 */       if (JLbsConstants.DEBUG) {
/*  900 */         System.out.println("img Thread: " + e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareButtonOKClickThread() {
/*      */     try {
/*  908 */       JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/*  913 */               if (JLbsDesktopClientLoginPanel.this.isUserInfoGiven()) {
/*  914 */                 JLbsDesktopClientLoginPanel.this.buttonOKClick();
/*      */               }
/*      */             }
/*      */           });
/*  918 */     } catch (Exception e) {
/*      */       
/*  920 */       if (JLbsConstants.DEBUG) {
/*  921 */         System.out.println("button click Thread: " + e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ILbsCultureInfo createCultureInfo(int languageNr) {
/*  931 */     return JLbsCultureInfoBase.createInstance(languageNr);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getLoginIniFileName() {
/*  936 */     return this.m_IniFileName;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JLbsIniProperties loadLoginProperties() {
/*      */     try {
/*  943 */       byte[] localSettings = this.m_Context.loadLocalFile("Settings/" + getLoginIniFileName());
/*  944 */       if (localSettings != null)
/*      */       {
/*  946 */         JLbsIniProperties loginProp = new JLbsIniProperties();
/*  947 */         loginProp.load(localSettings, JLbsConstants.CHAR_SET);
/*      */         
/*  949 */         String loginName = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "UserName");
/*  950 */         if (loginName != null && this.editUsername != null) {
/*  951 */           this.editUsername.setText(loginName);
/*      */         }
/*  953 */         String language = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Language");
/*  954 */         String hasLTROrientation = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation");
/*      */         
/*  956 */         if (language != null)
/*      */         {
/*      */           
/*  959 */           for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */             
/*  961 */             JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/*  962 */             if (language.equals(jLbsCultureInfoBase.getLanguagePrefix()) && !JLbsStringUtil.isEmpty(hasLTROrientation) && 
/*  963 */               hasLTROrientation.equals(String.valueOf(jLbsCultureInfoBase.hasLeftToRightOrientation()))) {
/*      */               
/*  965 */               this.m_SelectedLanguage = i;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/*  970 */         if (this.comboLanguage != null) {
/*  971 */           this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */         } else {
/*  973 */           updateLanguage();
/*      */         } 
/*  975 */         String selectedTimeZone = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "TimeZone");
/*  976 */         if (!StringUtil.isEmpty(selectedTimeZone)) {
/*  977 */           this.m_SelectedTimeZone = selectedTimeZone;
/*      */         }
/*  979 */         String selectedCompany = loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Company");
/*  980 */         if (selectedCompany != null) {
/*  981 */           this.m_SelectedCompany = selectedCompany;
/*      */         }
/*  983 */         return loginProp;
/*      */       }
/*      */     
/*  986 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  989 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JLbsIniProperties loadLoginProperties(String charSet) {
/*      */     try {
/*  996 */       Object langPre = this.m_Context.getVariable("LANGUAGE");
/*  997 */       this.m_Context.removeVariable("LANGUAGE");
/*  998 */       String langPrefix = null;
/*  999 */       if (langPre instanceof String && !langPre.equals("null")) {
/* 1000 */         langPrefix = (String)langPre;
/*      */       }
/* 1002 */       byte[] localSettings = this.m_Context.loadLocalFile("Settings/" + getLoginIniFileName());
/*      */       
/* 1004 */       if (localSettings == null && langPrefix != null) {
/*      */         
/* 1006 */         int idx = getLanguageIdx(langPrefix);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1011 */         if (this.comboLanguage != null) {
/* 1012 */           this.comboLanguage.setSelectedIndex(idx);
/*      */         } else {
/* 1014 */           updateLanguage();
/* 1015 */         }  return null;
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
/* 1026 */       if (localSettings != null)
/*      */       {
/* 1028 */         this.loginProp = new JLbsIniProperties();
/* 1029 */         this.loginProp.load(localSettings, charSet);
/*      */         
/* 1031 */         String loginName = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "UserName");
/* 1032 */         if (loginName != null && this.editUsername != null) {
/* 1033 */           this.editUsername.setText(loginName);
/*      */         }
/* 1035 */         String selectedTimeZone = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "TimeZone");
/* 1036 */         if (!StringUtil.isEmpty(selectedTimeZone)) {
/* 1037 */           this.m_SelectedTimeZone = selectedTimeZone;
/*      */         }
/* 1039 */         String language = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Language");
/*      */         
/* 1041 */         if (langPrefix != null && !langPrefix.equals(language)) {
/*      */           
/* 1043 */           int idx = getLanguageIdx(langPrefix);
/* 1044 */           if (idx < 0)
/* 1045 */             idx = 0; 
/* 1046 */           if (this.comboLanguage != null) {
/* 1047 */             this.comboLanguage.setSelectedIndex(idx);
/*      */           } else {
/* 1049 */             updateLanguage();
/* 1050 */           }  return null;
/*      */         } 
/*      */         
/* 1053 */         String hasLTROrientation = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation");
/*      */         
/* 1055 */         if (language != null)
/*      */         {
/*      */           
/* 1058 */           for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */             
/* 1060 */             JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/* 1061 */             if (language.equals(jLbsCultureInfoBase.getLanguagePrefix()) && !JLbsStringUtil.isEmpty(hasLTROrientation) && 
/* 1062 */               hasLTROrientation.equals(String.valueOf(jLbsCultureInfoBase.hasLeftToRightOrientation()))) {
/*      */               
/* 1064 */               this.m_SelectedLanguage = i;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/* 1069 */         if (this.m_SelectedLanguage < 0) {
/* 1070 */           this.m_SelectedLanguage = 0;
/*      */         }
/* 1072 */         if (this.comboLanguage != null) {
/* 1073 */           this.comboLanguage.setSelectedIndex(this.m_SelectedLanguage);
/*      */         } else {
/* 1075 */           updateLanguage();
/*      */         } 
/* 1077 */         String selectedCompany = this.loginProp.getProperty(String.valueOf(this.loginPropHeader) + "Company");
/* 1078 */         if (selectedCompany != null) {
/* 1079 */           this.m_SelectedCompany = selectedCompany;
/*      */         }
/* 1081 */         return this.loginProp;
/*      */       }
/*      */     
/* 1084 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 1087 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getLanguageIdx(String langPrefix) {
/* 1092 */     if (this.supportedCultures == null) {
/* 1093 */       return -1;
/*      */     }
/* 1095 */     for (int i = 0; i < this.supportedCultures.length; i++) {
/*      */       
/* 1097 */       JLbsCultureInfoBase jLbsCultureInfoBase = this.supportedCultures[i];
/* 1098 */       if (langPrefix.equals(jLbsCultureInfoBase.getLanguagePrefix()))
/* 1099 */         return i; 
/*      */     } 
/* 1101 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveLoginProperties() {
/* 1106 */     JLbsIniProperties loginProp = new JLbsIniProperties();
/*      */     
/* 1108 */     fillIniProperties(loginProp);
/*      */     
/*      */     try {
/* 1111 */       byte[] localSettings = loginProp.toByteArray(JLbsConstants.CHAR_SET);
/* 1112 */       if (localSettings != null)
/*      */       {
/* 1114 */         JLbsClientFS.makeDirectory("Settings");
/* 1115 */         this.m_Context.saveLocalFile("Settings/" + getLoginIniFileName(), localSettings, true, false);
/*      */       }
/*      */     
/*      */     }
/* 1119 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fillIniProperties(JLbsIniProperties loginProp) {
/* 1126 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "UserName", this.editUsername.getText());
/* 1127 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "Language", this.m_SelectedCulture.getLanguagePrefix());
/* 1128 */     loginProp.setProperty(String.valueOf(this.loginPropHeader) + "HasLTROrientation", 
/* 1129 */         String.valueOf(this.m_SelectedCulture.hasLeftToRightOrientation()));
/* 1130 */     if (!StringUtil.isEmpty(this.m_SelectedTimeZone)) {
/* 1131 */       loginProp.setProperty(String.valueOf(this.loginPropHeader) + "TimeZone", this.m_SelectedTimeZone);
/*      */     }
/*      */   }
/*      */   
/*      */   protected TableLayoutConstraints getLayoutConstraints(int col, int row, int endCol) {
/* 1136 */     return getLayoutConstraints(col, row, endCol, row);
/*      */   }
/*      */ 
/*      */   
/*      */   protected TableLayoutConstraints getLayoutConstraints(int col, int row, int endCol, int endRow) {
/* 1141 */     String constraints = String.valueOf(col) + ", " + row + "," + endCol + ", " + endRow + ", LD, C";
/* 1142 */     return new TableLayoutConstraints(constraints);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLabel addLabel(String text, int col, int row, int width, int height, int endCol) {
/* 1147 */     return addLabeltoPanel(this.pnlMain, text, col, row, endCol);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLabel addLabeltoPanel(JPanel panel, String text, int col, int row, int endCol) {
/* 1152 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1153 */     JLbsLabel label = new JLbsLabel(text);
/* 1154 */     Font f = this.m_SelectedCulture.getFont();
/* 1155 */     if (f != null)
/* 1156 */       label.setFont(f); 
/* 1157 */     panel.add((Component)label, constraint);
/* 1158 */     return (JLabel)label;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsLinkLabel addLinkLabel(String text, int col, int row, int width, int height, int endCol) {
/* 1164 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1165 */     JLbsLinkLabel label = new JLbsLinkLabel();
/* 1166 */     label.setBorder(new EmptyBorder(0, 0, 0, 0));
/* 1167 */     label.setText(text);
/* 1168 */     this.pnlMain.add((Component)label, constraint);
/* 1169 */     label.setClickListener(new LinkLabelListener());
/* 1170 */     return label;
/*      */   }
/*      */ 
/*      */   
/*      */   public JCheckBox addCheckBox(int col, int row, int width, int height, int endCol, String text) {
/* 1175 */     TableLayoutConstraints contraint = getLayoutConstraints(col, row, endCol);
/* 1176 */     this.chk = new JCheckBox();
/* 1177 */     this.chk.setText(text);
/* 1178 */     this.chk.addKeyListener(this);
/* 1179 */     this.chk.setPreferredSize(new Dimension(width, height));
/* 1180 */     this.chk.setSize(width, height);
/* 1181 */     this.pnlMain.add(this.chk, contraint);
/*      */     
/* 1183 */     return this.chk;
/*      */   }
/*      */ 
/*      */   
/*      */   class LinkLabelListener
/*      */     implements MouseListener
/*      */   {
/*      */     public void mouseClicked(MouseEvent e) {
/* 1191 */       if (e.getSource() == JLbsDesktopClientLoginPanel.this.lblRemember) {
/*      */         
/* 1193 */         JLbsRememberPassEditor editor = new JLbsRememberPassEditor(JLbsDesktopClientLoginPanel.this.m_Context, JLbsDesktopClientLoginPanel.this.m_SelectedCulture, JLbsDesktopClientLoginPanel.this.editUsername.getText());
/* 1194 */         editor.show();
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
/* 1221 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1222 */     JTextField textField = new JTextField();
/* 1223 */     textField.addKeyListener(this);
/* 1224 */     textField.setPreferredSize(new Dimension(width, height));
/* 1225 */     textField.setSize(width, height);
/* 1226 */     this.pnlMain.add(textField, constraint);
/* 1227 */     return textField;
/*      */   }
/*      */ 
/*      */   
/*      */   public JPasswordField addPasswordField(int col, int row, int width, int height, int endCol) {
/* 1232 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1234 */     JPasswordField passwordField = new JPasswordField();
/* 1235 */     passwordField.setEchoChar('●');
/* 1236 */     passwordField.addKeyListener(this);
/* 1237 */     passwordField.setPreferredSize(new Dimension(width, height));
/* 1238 */     passwordField.setSize(width, height);
/* 1239 */     this.pnlMain.add(passwordField, constraint);
/* 1240 */     return passwordField;
/*      */   }
/*      */ 
/*      */   
/*      */   public JProgressBar addProgress(int col, int row, int width, int height, int endCol) {
/* 1245 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/* 1246 */     JProgressBar bar = new JProgressBar();
/* 1247 */     bar.setBorder((Border)null);
/* 1248 */     bar.setMinimum(0);
/* 1249 */     bar.setMaximum(100);
/* 1250 */     bar.setPreferredSize(new Dimension(width, height));
/* 1251 */     bar.setSize(width, height);
/* 1252 */     bar.setBackground(Color.WHITE);
/* 1253 */     bar.setForeground(Color.DARK_GRAY);
/* 1254 */     bar.setOpaque(true);
/* 1255 */     this.pnlMain.add(bar, constraint);
/* 1256 */     return bar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JButton addButton(String text, int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1263 */     return addButtontoPanel(this.pnlMain, text, col, row, width, height, defButton, endCol);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JButton addButtontoPanel(JPanel panel, String text, int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1269 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1271 */     JButton button = new JButton();
/* 1272 */     UIHelperUtil.setCaption(button, text);
/* 1273 */     if (defButton)
/* 1274 */       button.setDefaultCapable(true); 
/* 1275 */     button.addKeyListener(this);
/* 1276 */     button.addActionListener(this);
/* 1277 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 1278 */     button.setPreferredSize(new Dimension(width, height));
/* 1279 */     button.setSize(width, height);
/* 1280 */     panel.add(button, constraint);
/* 1281 */     return button;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsImageButton addImageButton(int col, int row, int width, int height, boolean defButton, int endCol) {
/* 1287 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1289 */     JLbsImageButton button = new JLbsImageButton();
/* 1290 */     if (defButton)
/* 1291 */       button.setDefaultCapable(true); 
/* 1292 */     button.addKeyListener(this);
/* 1293 */     button.addActionListener(this);
/* 1294 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 1295 */     button.setPreferredSize(new Dimension(width, height));
/* 1296 */     button.setSize(width, height);
/* 1297 */     this.pnlMain.add((Component)button, constraint);
/* 1298 */     return button;
/*      */   }
/*      */ 
/*      */   
/*      */   public JComboBox addComboBox(int col, int row, int width, int height, int endCol) {
/* 1303 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1305 */     JComboBox comboBox = new JComboBox();
/* 1306 */     comboBox.addActionListener(this);
/* 1307 */     comboBox.addKeyListener(this);
/* 1308 */     comboBox.setSize(width, height);
/* 1309 */     this.pnlMain.add(comboBox, constraint);
/*      */     
/* 1311 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsComboBox addLbsComboBox(int col, int row, int width, int height, int endCol) {
/* 1316 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1318 */     JLbsComboBox comboBox = new JLbsComboBox();
/* 1319 */     comboBox.addActionListener(this);
/* 1320 */     comboBox.addKeyListener(this);
/* 1321 */     comboBox.setSize(width, height);
/* 1322 */     this.pnlMain.add((Component)comboBox, constraint);
/*      */     
/* 1324 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public JComboBox addComboBoxWithImage(int col, int row, int width, int height, int endCol) {
/* 1329 */     TableLayoutConstraints constraint = getLayoutConstraints(col, row, endCol);
/*      */     
/* 1331 */     JComboBox comboBox = new JComboBox();
/* 1332 */     comboBox.addActionListener(this);
/* 1333 */     comboBox.addKeyListener(this);
/* 1334 */     comboBox.setSize(width, height);
/*      */     
/* 1336 */     ComboBoxRenderer renderer = new ComboBoxRenderer();
/* 1337 */     renderer.setPreferredSize(new Dimension(width, height));
/* 1338 */     comboBox.setRenderer(renderer);
/*      */     
/* 1340 */     this.pnlMain.add(comboBox, constraint);
/*      */     
/* 1342 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doOKBase() {
/* 1347 */     if (checkDBError()) {
/* 1348 */       doOK();
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean checkDBError() {
/* 1353 */     if (this.m_DBError == 1) {
/*      */       
/* 1355 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1029));
/* 1356 */       return false;
/*      */     } 
/* 1358 */     if (this.m_DBError == 2) {
/*      */       
/* 1360 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1027));
/* 1361 */       return false;
/*      */     } 
/*      */     
/* 1364 */     if (this.m_DBError == 3) {
/*      */       
/* 1366 */       this.m_Context.showMessage(null, JLbsLocalizer.getCultureResource(1025));
/* 1367 */       return false;
/*      */     } 
/* 1369 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doCancel() {
/* 1376 */     this.m_Context.terminateApplication();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFocusToUser() {
/* 1381 */     String uName = this.editUsername.getText();
/* 1382 */     if (uName == null || uName.length() == 0) {
/* 1383 */       this.editUsername.grabFocus();
/*      */     } else {
/* 1385 */       this.editPassword.grabFocus();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected UserInfo createLoginUserInfo() {
/* 1391 */     UserInfo userInfo = new UserInfo();
/* 1392 */     userInfo.Name = this.editUsername.getText().trim();
/* 1393 */     userInfo.Password = new String(this.editPassword.getPassword());
/*      */ 
/*      */     
/* 1396 */     userInfo.LtpaToken = this.ltpaToken;
/* 1397 */     userInfo.selectedLanguage = this.m_SelectedCulture.getLanguagePrefix();
/* 1398 */     userInfo.variableHolder = this.m_Context;
/* 1399 */     return userInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void openMainForm(UserInfo userInfo) {
/* 1404 */     prepareExitJavascript();
/* 1405 */     Object o = this.m_Context.getVariable("CLI-ALLOWED-DAYS");
/* 1406 */     if (o instanceof Integer) {
/*      */       
/* 1408 */       int allowedDays = ((Integer)o).intValue();
/* 1409 */       if (allowedDays != Integer.MAX_VALUE) {
/*      */         
/* 1411 */         String s = this.m_Context.getLocalizationService().getItem(JLbsConstants.GLOBAL_RESOURCES, 19);
/* 1412 */         if (StringUtil.isEmpty(s))
/* 1413 */           s = "Your license has expired. In ~1 days your product will become unusable."; 
/* 1414 */         s = JLbsStringUtil.mergeParameters(s, new String[] { (new StringBuilder(String.valueOf(allowedDays))).toString() }, new int[] { 1 });
/* 1415 */         final String message = s;
/* 1416 */         SwingUtilities.invokeLater(new Runnable()
/*      */             {
/*      */               
/*      */               public void run()
/*      */               {
/* 1421 */                 JLbsDesktopClientLoginPanel.this.m_Context.showMessage("", message);
/*      */               }
/*      */             });
/*      */       } 
/*      */     } 
/*      */     
/* 1427 */     this.m_Context.setSessionTimeout(3600000);
/*      */     
/* 1429 */     if (this.threadBackground != null) {
/*      */ 
/*      */       
/*      */       try {
/* 1433 */         Thread runner = this.threadBackground;
/* 1434 */         this.threadBackground = null;
/* 1435 */         runner.interrupt();
/*      */       
/*      */       }
/* 1438 */       catch (Exception exception) {}
/*      */ 
/*      */       
/* 1441 */       this.threadBackground = null;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1446 */       if (JLbsConstants.USE_LOCALIZATION_SERVICES) {
/* 1447 */         this.m_Context.startLoadingLocalizationCache();
/*      */       }
/* 1449 */       setProgressIndex(60);
/*      */       
/* 1451 */       loadJAR(this.m_JarFiles);
/*      */       
/* 1453 */       setProgressIndex(80);
/*      */       
/* 1455 */       Object obj = getMainFormInstance(userInfo);
/*      */       
/* 1457 */       setProgressIndex(90);
/*      */       
/* 1459 */       if (obj instanceof JComponent) {
/*      */         
/* 1461 */         this.m_Context.replaceContent((JComponent)obj);
/* 1462 */         if (obj instanceof IClientDataConsumer)
/*      */         {
/* 1464 */           if (obj instanceof ITokenProcessor) {
/*      */             
/* 1466 */             if (((ITokenProcessor)obj).isShowWithProduct()) {
/* 1467 */               ((IClientDataConsumer)obj).initializeData(userInfo, null);
/*      */             }
/*      */           } else {
/* 1470 */             ((IClientDataConsumer)obj).initializeData(userInfo, null);
/*      */           } 
/*      */         }
/*      */       } else {
/*      */         
/* 1475 */         this.m_Context.showMessage("LoginForm!", "Could not initialize the MainForm!");
/*      */       } 
/*      */       
/* 1478 */       this.m_Context.setCustomizationResourceList();
/*      */       
/* 1480 */       setProgressIndex(100);
/*      */     }
/* 1482 */     catch (Exception ex) {
/*      */       
/* 1484 */       System.out.println("Exception in LoginForm while creating the MainForm! " + ex.getMessage() + " - " + ex.getCause());
/* 1485 */       ex.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareExitJavascript() {
/*      */     try {
/* 1493 */       JApplet baseApplet = getBaseApplet();
/* 1494 */       if (baseApplet != null) {
/*      */         
/* 1496 */         Class<?> c = Class.forName("netscape.javascript.JSObject");
/* 1497 */         Method m = c.getMethod("getWindow", new Class[] { Applet.class });
/* 1498 */         Object window = m.invoke(null, new Object[] { baseApplet });
/* 1499 */         if (window != null)
/*      */         {
/* 1501 */           m = c.getMethod("eval", new Class[] { String.class });
/* 1502 */           String message = this.m_Context.getLocalizationService().getItem(-1000, 33);
/* 1503 */           if (StringUtil.isEmpty(message))
/*      */           {
/* 1505 */             message = "If you close the application using this button you may loose your unsaved changes. To be able to save your unsaved changes, you can use the logout button from the toolbar. Do you still want to leave?";
/*      */           }
/*      */ 
/*      */           
/* 1509 */           m.invoke(window, new Object[] { " $(window).bind('beforeunload', function() {return '" + message + "';" + "});" });
/*      */         }
/*      */       
/*      */       } 
/* 1513 */     } catch (Exception e) {
/*      */       
/* 1515 */       if (JLbsConstants.DEBUG) {
/* 1516 */         e.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Object getMainFormInstance(UserInfo userInfo) throws Exception {
/* 1522 */     return this.m_Context.createInstance(this.m_MainForm);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent event) {
/* 1528 */     Object evtSource = event.getSource();
/* 1529 */     if (evtSource == this.btnCancel) {
/*      */       
/* 1531 */       doCancel();
/*      */     }
/* 1533 */     else if (evtSource == this.btnOk && this.m_Context != null) {
/*      */       
/* 1535 */       doOKBase();
/*      */     }
/* 1537 */     else if (evtSource == this.comboLanguage && this.m_Context != null) {
/*      */       
/* 1539 */       this.m_SelectedLanguage = ((JLbsStringListItem)this.comboLanguage.getSelectedItem()).Tag;
/* 1540 */       updateLanguage();
/*      */       
/* 1542 */       if (this.m_SelectedCulture.getFont() != null) {
/*      */         
/* 1544 */         JLbsConstants.APP_FONT = this.m_SelectedCulture.getFont().getName();
/* 1545 */         JLbsConstants.APP_FONT_SIZE = this.m_SelectedCulture.getFont().getSize();
/*      */       }
/*      */       else {
/*      */         
/* 1549 */         JLbsConstants.APP_FONT = JLbsConstants.APP_FONT_DEFAULT;
/* 1550 */         JLbsConstants.APP_FONT_SIZE = JLbsConstants.APP_SIZE_DEFAULT;
/*      */       } 
/* 1552 */       UIManager.put("ToolTip.font", new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/* 1553 */       UIManager.put("Button.font", new FontUIResource(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE - 1));
/*      */       
/* 1555 */       this.m_Context.changeTheme("com.lbs.laf.mac.LookAndFeel");
/*      */       
/* 1557 */       applyComponentOrientation(this.m_SelectedCulture.getComponentOrientation());
/* 1558 */       applyComponentFont(this.m_SelectedCulture.getFont());
/*      */     
/*      */     }
/* 1561 */     else if (evtSource == this.btnLocaleTimeZone) {
/*      */ 
/*      */       
/* 1564 */       TimeZoneSettingsDialog dialog = new TimeZoneSettingsDialog();
/* 1565 */       String timeZone = this.m_SelectedTimeZone;
/* 1566 */       if (StringUtil.isEmpty(timeZone))
/*      */       {
/* 1568 */         timeZone = TimeZone.getDefault().getID();
/*      */       }
/* 1570 */       dialog.setSelectedTimeZone(timeZone);
/* 1571 */       dialog.setModal(true);
/* 1572 */       dialog.show();
/* 1573 */       if (dialog.m_Ok) {
/* 1574 */         this.m_SelectedTimeZone = dialog.m_SelectedTimeZone;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void applyComponentFont(Font font) {
/* 1580 */     if (font == null) {
/* 1581 */       font = new Font(JLbsConstants.APP_FONT_DEFAULT, JLbsConstants.APP_STYLE_DEFAULT, JLbsConstants.APP_SIZE_DEFAULT);
/*      */     }
/* 1583 */     if (this.lblHeader != null)
/* 1584 */       this.lblHeader.setFont(font); 
/* 1585 */     if (this.lblUsername != null)
/* 1586 */       this.lblUsername.setFont(font); 
/* 1587 */     if (this.lblPassword != null)
/* 1588 */       this.lblPassword.setFont(font); 
/* 1589 */     if (this.lblLanguage != null)
/* 1590 */       this.lblLanguage.setFont(font); 
/* 1591 */     if (this.lblVersion != null)
/* 1592 */       this.lblVersion.setFont(font); 
/* 1593 */     if (this.lblLinkAdvanced != null)
/* 1594 */       this.lblLinkAdvanced.setFont(font); 
/* 1595 */     if (this.lblRemember != null)
/* 1596 */       this.lblRemember.setFont(font); 
/* 1597 */     if (this.editUsername != null)
/* 1598 */       this.editUsername.setFont(font); 
/* 1599 */     if (this.editPassword != null)
/* 1600 */       this.editPassword.setFont(new Font(JLbsConstants.APP_FONT_DEFAULT, JLbsConstants.APP_STYLE_DEFAULT, 
/* 1601 */             JLbsConstants.APP_SIZE_DEFAULT)); 
/* 1602 */     if (this.capsLockTip != null)
/* 1603 */       this.capsLockTip.setFont(font); 
/* 1604 */     if (this.comboLanguage != null)
/* 1605 */       this.comboLanguage.setFont(font); 
/* 1606 */     if (this.btnOk != null)
/* 1607 */       this.btnOk.setFont(new Font(font.getFontName(), 1, font.getSize() - 1)); 
/* 1608 */     if (this.btnCancel != null)
/* 1609 */       this.btnCancel.setFont(new Font(font.getFontName(), 1, font.getSize() - 1)); 
/* 1610 */     if (this.btnLocaleTimeZone != null)
/* 1611 */       this.btnLocaleTimeZone.setFont(font); 
/* 1612 */     if (this.chk != null) {
/* 1613 */       this.chk.setFont(font);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/* 1619 */     if (e.getModifiers() != 0) {
/*      */       return;
/*      */     }
/* 1622 */     int key = e.getKeyCode();
/*      */     
/* 1624 */     switch (key) {
/*      */       
/*      */       case 27:
/* 1627 */         System.out.println("Escape");
/* 1628 */         doCancel();
/*      */         break;
/*      */       
/*      */       case 10:
/* 1632 */         System.out.println("Enter");
/* 1633 */         doOKBase();
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
/* 1650 */     this.m_IniLanguage = iniLanguage;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getIniLanguage() {
/* 1655 */     return this.m_IniLanguage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setShowVersionText(boolean showVersionText) {
/* 1660 */     this.m_ShowVersionText = showVersionText;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isShowVersionText() {
/* 1665 */     return this.m_ShowVersionText;
/*      */   }
/*      */   
/*      */   class ComboBoxRenderer
/*      */     extends JLabel
/*      */     implements ListCellRenderer {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     public ComboBoxRenderer() {
/* 1674 */       setOpaque(true);
/*      */       
/* 1676 */       setVerticalAlignment(0);
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
/* 1701 */       if (value == null) {
/* 1702 */         return this;
/*      */       }
/* 1704 */       if (isSelected) {
/*      */         
/* 1706 */         setBackground(list.getSelectionBackground());
/* 1707 */         setForeground(list.getSelectionForeground());
/*      */       }
/*      */       else {
/*      */         
/* 1711 */         setBackground(list.getBackground());
/* 1712 */         setForeground(list.getForeground());
/*      */       } 
/*      */       
/* 1715 */       ImageIcon icon = ((JLbsDesktopClientLoginPanel.LanguageComboItemWithImage)value).getIcon();
/* 1716 */       String text = ((JLbsDesktopClientLoginPanel.LanguageComboItemWithImage)value).getValue();
/* 1717 */       String tooltip = ((JLbsDesktopClientLoginPanel.LanguageComboItemWithImage)value).getTooltipText();
/* 1718 */       Font font = ((JLbsDesktopClientLoginPanel.LanguageComboItemWithImage)value).getFont();
/*      */       
/* 1720 */       if (icon != null) {
/* 1721 */         setIcon(icon);
/*      */       }
/* 1723 */       setText(text);
/*      */       
/* 1725 */       if (font == null) {
/* 1726 */         setFont(list.getFont());
/*      */       } else {
/* 1728 */         setFont(font);
/*      */       } 
/* 1730 */       setToolTipText(tooltip);
/*      */       
/* 1732 */       return this;
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
/* 1743 */       super(s);
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItem(String s, int iTag) {
/* 1748 */       super(s, iTag);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1754 */       return this.Value;
/*      */     }
/*      */ 
/*      */     
/*      */     public Font getFont() {
/* 1759 */       return this.m_Font;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFont(Font font) {
/* 1764 */       this.m_Font = font;
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
/* 1778 */       super(s);
/* 1779 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public Font getFont() {
/* 1784 */       return this.m_Font;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFont(Font font) {
/* 1789 */       this.m_Font = font;
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItemWithImage(String s, int iTag, ImageIcon icon) {
/* 1794 */       super(s, iTag);
/* 1795 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public LanguageComboItemWithImage(String s, int iTag) {
/* 1800 */       super(s, iTag);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1806 */       return this.Value;
/*      */     }
/*      */ 
/*      */     
/*      */     public ImageIcon getIcon() {
/* 1811 */       return this.m_icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setIcon(ImageIcon icon) {
/* 1816 */       this.m_icon = icon;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setLanguageID(int languageID) {
/* 1821 */       this.m_LanguageID = languageID;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getTooltipText() {
/* 1826 */       if (JLbsDesktopClientLoginPanel.this.m_SelectedCulture != null)
/*      */       {
/* 1828 */         return JLbsDesktopClientLoginPanel.this.m_SelectedCulture.getLanguageName(this.m_LanguageID);
/*      */       }
/* 1830 */       return null;
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
/* 1849 */       JPanel contentPane = new JPanel();
/*      */       
/* 1851 */       JLabel timeZoneLabel = new JLabel();
/* 1852 */       timeZoneLabel.setText(getResource(1));
/* 1853 */       this.m_TimeZoneCombo = new JLbsComboBox();
/* 1854 */       this.m_TimeZoneCombo.setItems(JLbsCultureInfoBase.getTimeZones());
/*      */       
/* 1856 */       JPanel panel = new JPanel();
/* 1857 */       panel.add(timeZoneLabel);
/* 1858 */       panel.add((Component)this.m_TimeZoneCombo);
/*      */       
/* 1860 */       JPanel buttonPanel = new JPanel();
/* 1861 */       this.m_OkButton = new JButton(getResource(2));
/* 1862 */       this.m_OkButton.addActionListener(this);
/* 1863 */       this.m_CancelButton = new JButton(getResource(3));
/* 1864 */       this.m_CancelButton.addActionListener(this);
/* 1865 */       buttonPanel.add(this.m_OkButton);
/* 1866 */       buttonPanel.add(this.m_CancelButton);
/*      */       
/* 1868 */       contentPane.setLayout(new BorderLayout());
/* 1869 */       contentPane.add(panel, "Center");
/* 1870 */       contentPane.add(buttonPanel, "South");
/*      */       
/* 1872 */       setContentPane(contentPane);
/* 1873 */       setSize(350, 120);
/* 1874 */       centerScreen();
/* 1875 */       setTitle(getResource(1));
/*      */     }
/*      */ 
/*      */     
/*      */     public void setSelectedTimeZone(String selectedTimeZone) {
/* 1880 */       selectedTimeZone = searchTimeZoneValue(selectedTimeZone);
/* 1881 */       this.m_SelectedTimeZone = selectedTimeZone;
/* 1882 */       if (this.m_TimeZoneCombo != null)
/*      */       {
/* 1884 */         this.m_TimeZoneCombo.setSelectedItemValue(this.m_SelectedTimeZone);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private String searchTimeZoneValue(String selectedTimeZone) {
/* 1890 */       JLbsStringList list = new JLbsStringList(this.m_TimeZoneCombo.getItemsSList());
/* 1891 */       if (list != null)
/*      */       {
/* 1893 */         for (int i = 0; i < list.size(); i++) {
/*      */           
/* 1895 */           String listValue = list.getValueAt(i);
/* 1896 */           if (listValue.indexOf(selectedTimeZone) > 0)
/*      */           {
/* 1898 */             return listValue;
/*      */           }
/*      */         } 
/*      */       }
/* 1902 */       return selectedTimeZone;
/*      */     }
/*      */ 
/*      */     
/*      */     private String getResource(int resTag) {
/* 1907 */       String resource = "";
/* 1908 */       if (JLbsDesktopClientLoginPanel.this.m_SelectedCulture != null)
/*      */       {
/* 1910 */         switch (resTag) {
/*      */           
/*      */           case 1:
/* 1913 */             resource = JLbsDesktopClientLoginPanel.this.m_SelectedCulture.getTimeZone();
/*      */             break;
/*      */           case 2:
/* 1916 */             resource = JLbsDesktopClientLoginPanel.this.m_SelectedCulture.getOK();
/*      */             break;
/*      */           case 3:
/* 1919 */             resource = JLbsDesktopClientLoginPanel.this.m_SelectedCulture.getCancel();
/*      */             break;
/*      */         } 
/*      */       }
/* 1923 */       if (StringUtil.isEmpty(resource))
/*      */       {
/* 1925 */         switch (resTag) {
/*      */           
/*      */           case 1:
/* 1928 */             resource = "Time Zone";
/*      */             break;
/*      */           case 2:
/* 1931 */             resource = "Ok";
/*      */             break;
/*      */           case 3:
/* 1934 */             resource = "Cancel";
/*      */             break;
/*      */         } 
/*      */       }
/* 1938 */       return resource;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1944 */       if (e.getSource() == this.m_OkButton) {
/*      */         
/* 1946 */         this.m_SelectedTimeZone = String.valueOf(this.m_TimeZoneCombo.getSelectedItemValue());
/* 1947 */         this.m_Ok = true;
/* 1948 */         dispose();
/*      */       }
/* 1950 */       else if (e.getSource() == this.m_CancelButton) {
/*      */         
/* 1952 */         dispose();
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
/* 1969 */     return ldapUserInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLdapUserInfo(UserInfo userInfo) {
/* 1974 */     ldapUserInfo = userInfo;
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
/*      */   protected abstract void createDSignatureComponents();
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


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsDesktopClientLoginPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */