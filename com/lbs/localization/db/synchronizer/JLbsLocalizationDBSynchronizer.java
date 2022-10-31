/*      */ package com.lbs.localization.db.synchronizer;
/*      */ 
/*      */ import com.lbs.controls.JLbsButton;
/*      */ import com.lbs.controls.JLbsCheckBox;
/*      */ import com.lbs.controls.JLbsComboBox;
/*      */ import com.lbs.controls.JLbsComboEdit;
/*      */ import com.lbs.controls.JLbsGridLayout;
/*      */ import com.lbs.controls.JLbsLabel;
/*      */ import com.lbs.controls.JLbsListBox;
/*      */ import com.lbs.controls.groupbox.JLbsCheckBoxGroup;
/*      */ import com.lbs.controls.groupbox.JLbsGroupBox;
/*      */ import com.lbs.controls.groupbox.JLbsRadioButtonGroup;
/*      */ import com.lbs.controls.maskededit.JLbsPasswordField;
/*      */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*      */ import com.lbs.localization.ILocalizationConstants;
/*      */ import com.lbs.localization.JLbsLocalizationUtil;
/*      */ import com.lbs.localization.LbsLocalizationConfig;
/*      */ import com.lbs.localization.LuceneLocalizationServices;
/*      */ import com.lbs.util.JLbsConvertUtil;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import com.lbs.util.JLbsDialog;
/*      */ import com.lbs.util.JLbsFileUtil;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Properties;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.filechooser.FileFilter;
/*      */ import org.apache.tools.ant.Project;
/*      */ import org.apache.tools.ant.taskdefs.Expand;
/*      */ import org.apache.tools.ant.taskdefs.Zip;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsLocalizationDBSynchronizer
/*      */   extends JLbsDialog
/*      */   implements ILocalizationConstants, ActionListener, MouseListener
/*      */ {
/*      */   private static final long serialVersionUID = -7715824770353164974L;
/*   73 */   private static String[] ms_Labels = new String[] { "Server ID", 
/*   74 */       "Username", 
/*   75 */       "Password", 
/*   76 */       "Output Directory", 
/*   77 */       "Test Connection", 
/*   78 */       "Operation Type", 
/*   79 */       "Create", 
/*   80 */       "Synchronize", 
/*   81 */       "Output & Language Options", 
/*   82 */       "Resource Groups", 
/*   83 */       "Console", 
/*   84 */       "Start", 
/*   85 */       "Localization DB Synchronizer", 
/*   86 */       "DB Name", 
/*   87 */       "Operation Type", 
/*   88 */       "Connection Info", 
/*   89 */       "Language", 
/*   90 */       "Console", 
/*   91 */       "Use Default Credentials" };
/*      */ 
/*      */   
/*   94 */   private static JLbsStringList ms_OperationTypeList = new JLbsStringList(String.valueOf(ms_Labels[6]) + "~" + '\001' + "|" + 
/*   95 */       ms_Labels[7] + "~" + '\002');
/*      */   
/*      */   private JLbsTextEdit m_TextEditServerID;
/*      */   
/*      */   private JLbsTextEdit m_TextEditDBName;
/*      */   
/*      */   private JLbsTextEdit m_TextEditUsername;
/*      */   
/*      */   private JLbsPasswordField m_PasswordField;
/*      */   
/*      */   private JLbsComboEdit m_ComboEditOutputDir;
/*      */   
/*      */   private JLbsButton m_ButtonTestConnection;
/*      */   
/*      */   private JLbsButton m_ButtonStartOperation;
/*      */   
/*      */   private JLbsRadioButtonGroup m_RadioBtnGrpOpType;
/*      */   
/*      */   private JLbsCheckBox m_CheckBoxDefaultUserName;
/*      */   
/*      */   private JLbsCheckBoxGroup m_CheckBoxGrpResourceGroup;
/*      */   
/*      */   private JLbsComboBox m_ComboBoxLanguage;
/*      */   
/*      */   private JLbsLabel m_LabelLanguage;
/*      */   
/*      */   private JLbsListBox m_ListBoxConsole;
/*      */   
/*      */   private String m_LastSelectedDBDir;
/*      */   
/*      */   private static final String COMMAND_CONNECTION_TEST = "TestConnection";
/*      */   
/*      */   private static final String COMMAND_OPERATION_START = "StartOperation";
/*      */   
/*      */   private static final int INTERVAL_INSERTION_RESOURCE = 10;
/*      */   
/*      */   private static final int INTERVAL_INSERTION_RESOURCE_ITEM = 100;
/*      */   
/*      */   private static final int INTERVAL_LOG_INFO = 50;
/*      */   
/*      */   private static final String WARNING_FORM_CONTENT_NOT_APPLICABLE = "Current form content is not valid! Please fill all of the required fields.";
/*      */   
/*      */   private static final String WARNING_CONNECTION_FAILED = "Connection failed!";
/*      */   private static final String MESSAGE_CONNECTION_SUCCEEDED = "Connection succeeded.";
/*      */   private static final String RESOURCE_DB_SERVER_ID = "172.16.1.55";
/*      */   private static final String RESOURCE_DB_NAME = "RESEDIT_J";
/*      */   public static final String DIR_PREFIX_EXTRACTION = "EmbeddedDB_";
/*      */   ImporterInfo m_ImporterInfo;
/*      */   ImportMonitor m_ImportMonitor;
/*      */   private Connection m_ResourceDBConnection;
/*      */   private String m_ServerName;
/*      */   private String m_DBName;
/*      */   private String m_UserName;
/*      */   private String m_Password;
/*      */   private HashMap<String, ArrayList<Integer>> m_ResourceList;
/*      */   private String m_Lang;
/*      */   
/*      */   static {
/*  153 */     LuceneLocalizationServices.disableOracleLogging();
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsLocalizationDBSynchronizer(boolean userInterface) {
/*  158 */     LuceneLocalizationServices.disableOracleLogging();
/*  159 */     cleanTempDirectories();
/*  160 */     if (userInterface)
/*  161 */       initializeUI(); 
/*  162 */     this.m_ImporterInfo = new ImporterInfo();
/*  163 */     this.m_ImportMonitor = new ImportMonitor(this.m_ListBoxConsole);
/*  164 */     if (this.m_TextEditUsername != null)
/*  165 */       this.m_TextEditUsername.requestFocus(); 
/*  166 */     if (userInterface) {
/*      */       
/*  168 */       setDefaultCloseOperation(2);
/*  169 */       setModal(true);
/*  170 */       show();
/*  171 */       System.exit(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void cleanTempDirectories() {
/*  177 */     String tmpDir = JLbsFileUtil.getTempDirectory();
/*  178 */     String[] fileList = JLbsFileUtil.getFileListUnderDirectory(tmpDir);
/*      */     
/*  180 */     for (int i = 0; i < fileList.length; i++) {
/*      */       
/*  182 */       if (fileList[i].startsWith("EmbeddedDB_")) {
/*      */         
/*  184 */         String fullPath = String.valueOf(tmpDir) + fileList[i];
/*  185 */         File tmpFile = new File(fullPath);
/*  186 */         if (tmpFile.exists() && tmpFile.isDirectory()) {
/*  187 */           JLbsFileUtil.deleteDirectory(fullPath);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void initializeUI() {
/*      */     try {
/*  196 */       UIManager.setLookAndFeel("com.lbs.laf.mac.LookAndFeel");
/*      */     }
/*  198 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  203 */     setTitle(ms_Labels[12]);
/*      */ 
/*      */     
/*  206 */     JPanel pnlMain = new JPanel();
/*  207 */     pnlMain.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  208 */     pnlMain.setLayout((LayoutManager)new JLbsGridLayout(3, 1));
/*      */ 
/*      */     
/*  211 */     JPanel pnlHeader = new JPanel();
/*  212 */     pnlHeader.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  213 */     pnlHeader.setLayout((LayoutManager)new JLbsGridLayout(1, 1));
/*      */     
/*  215 */     JLbsGroupBox grpConnection = createGroupBox(ms_Labels[15]);
/*  216 */     grpConnection.setLayout((LayoutManager)new JLbsGridLayout(2, 7));
/*      */ 
/*      */     
/*  219 */     grpConnection.add((Component)createLabel(ms_Labels[0]));
/*  220 */     grpConnection.add((Component)(this.m_TextEditServerID = createTextEdit("172.16.1.55")));
/*      */     
/*  222 */     grpConnection.add((Component)createLabel(""));
/*      */     
/*  224 */     grpConnection.add((Component)createLabel(ms_Labels[1]));
/*  225 */     grpConnection.add((Component)(this.m_TextEditUsername = createTextEdit("")));
/*      */     
/*  227 */     grpConnection.add((Component)createLabel(""));
/*  228 */     grpConnection.add((Component)(this.m_CheckBoxDefaultUserName = createCheckBox(ms_Labels[18])));
/*  229 */     this.m_CheckBoxDefaultUserName.addActionListener(this);
/*      */ 
/*      */     
/*  232 */     grpConnection.add((Component)createLabel(ms_Labels[13]));
/*  233 */     grpConnection.add((Component)(this.m_TextEditDBName = createTextEdit("RESEDIT_J")));
/*      */     
/*  235 */     grpConnection.add((Component)createLabel(""));
/*      */     
/*  237 */     grpConnection.add((Component)createLabel(ms_Labels[2]));
/*  238 */     grpConnection.add((Component)(this.m_PasswordField = createPasswordField()));
/*      */     
/*  240 */     grpConnection.add((Component)createLabel(""));
/*      */     
/*  242 */     grpConnection.add((Component)(this.m_ButtonTestConnection = createButton(ms_Labels[4], "TestConnection")));
/*  243 */     this.m_ButtonTestConnection.addMouseListener(this);
/*      */     
/*  245 */     pnlHeader.add((Component)grpConnection);
/*      */ 
/*      */     
/*  248 */     JPanel pnlContent = new JPanel();
/*  249 */     pnlContent.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  250 */     pnlContent.setLayout((LayoutManager)new JLbsGridLayout(3, 1));
/*      */ 
/*      */     
/*  253 */     pnlContent.add((Component)(this.m_RadioBtnGrpOpType = createRadioBtnGrp(ms_Labels[14], ms_OperationTypeList, 1)));
/*      */ 
/*      */     
/*  256 */     pnlContent.add((Component)(this.m_CheckBoxGrpResourceGroup = createCheckBoxGrp(ms_Labels[9], getResGroupList(), 4)));
/*      */ 
/*      */     
/*  259 */     JPanel pnlLanguage = new JPanel();
/*  260 */     pnlLanguage.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  261 */     pnlLanguage.setLayout((LayoutManager)new JLbsGridLayout(1, 1));
/*      */     
/*  263 */     JLbsGroupBox grpLanguage = createGroupBox(ms_Labels[8]);
/*  264 */     grpLanguage.setLayout((LayoutManager)new JLbsGridLayout(1, 7));
/*  265 */     grpLanguage.add((Component)createLabel(ms_Labels[3]));
/*  266 */     grpLanguage.add((Component)(this.m_ComboEditOutputDir = new JLbsComboEdit()));
/*  267 */     this.m_ComboEditOutputDir.getEditControl().setEditable(false);
/*  268 */     this.m_ComboEditOutputDir.setActionListener(this);
/*  269 */     grpLanguage.add((Component)createLabel(""));
/*  270 */     grpLanguage.add((Component)(this.m_LabelLanguage = createLabel(ms_Labels[16])));
/*  271 */     grpLanguage.add((Component)(this.m_ComboBoxLanguage = createComboBox(ms_Labels[8], getLanguageList())));
/*  272 */     grpLanguage.add((Component)createLabel(""));
/*  273 */     grpLanguage.add((Component)(this.m_ButtonStartOperation = createButton(ms_Labels[11], "StartOperation")));
/*  274 */     this.m_ButtonStartOperation.addMouseListener(this);
/*  275 */     pnlLanguage.add((Component)grpLanguage);
/*      */     
/*  277 */     pnlContent.add(pnlLanguage);
/*      */ 
/*      */     
/*  280 */     JPanel pnlConsole = new JPanel();
/*  281 */     pnlConsole.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  282 */     pnlConsole.setLayout((LayoutManager)new JLbsGridLayout(1, 1));
/*      */     
/*  284 */     JLbsGroupBox grpConsole = createGroupBox(ms_Labels[17]);
/*  285 */     grpConsole.setBorder(new EmptyBorder(4, 2, 4, 2));
/*  286 */     grpConsole.setLayout((LayoutManager)new JLbsGridLayout(1, 3));
/*  287 */     JScrollPane paneConsole = new JScrollPane();
/*  288 */     paneConsole.setPreferredSize(new Dimension(400, 80));
/*  289 */     paneConsole.getViewport().add((Component)(this.m_ListBoxConsole = createListBox()));
/*  290 */     paneConsole.setBorder((Border)null);
/*  291 */     grpConsole.add(paneConsole);
/*  292 */     pnlConsole.add((Component)grpConsole);
/*      */     
/*  294 */     pnlMain.add(pnlHeader);
/*  295 */     pnlMain.add(pnlContent);
/*  296 */     pnlMain.add(pnlConsole);
/*      */     
/*  298 */     performOpTypeRelatedActions();
/*  299 */     performCredentialRelatedActions();
/*  300 */     setContentPane(pnlMain);
/*  301 */     setResizable(false);
/*  302 */     pack();
/*  303 */     centerScreen();
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsStringList getLanguageList() {
/*  308 */     JLbsStringList langList = new JLbsStringList();
/*  309 */     Enumeration<String> langEnum = JLbsLocalizationUtil.ms_LangMaps.keys();
/*      */     
/*  311 */     while (langEnum.hasMoreElements()) {
/*      */       
/*  313 */       String lang = langEnum.nextElement();
/*  314 */       langList.add(lang);
/*      */     } 
/*      */     
/*  317 */     return langList;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsStringList getResGroupList() {
/*  322 */     JLbsStringList resGroupList = new JLbsStringList();
/*  323 */     Enumeration<String> resGrpEnum = JLbsLocalizationUtil.ms_GrpMaps.keys();
/*  324 */     int tagCounter = 0;
/*  325 */     while (resGrpEnum.hasMoreElements()) {
/*      */       
/*  327 */       String group = resGrpEnum.nextElement();
/*  328 */       resGroupList.add(group, ++tagCounter);
/*      */     } 
/*      */     
/*  331 */     return resGroupList;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsLabel createLabel(String caption) {
/*  336 */     JLbsLabel label = new JLbsLabel();
/*  337 */     label.setText(caption);
/*  338 */     return label;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsTextEdit createTextEdit(String text) {
/*  343 */     JLbsTextEdit textEdit = new JLbsTextEdit();
/*  344 */     textEdit.setPreferredSize(new Dimension(100, 20));
/*  345 */     textEdit.setText(text);
/*  346 */     return textEdit;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsPasswordField createPasswordField() {
/*  351 */     JLbsPasswordField passwordField = new JLbsPasswordField();
/*  352 */     passwordField.setPreferredSize(new Dimension(100, 20));
/*  353 */     return passwordField;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsButton createButton(String caption, String actionCommand) {
/*  358 */     JLbsButton button = new JLbsButton();
/*  359 */     button.addActionListener(this);
/*  360 */     button.setText(caption);
/*  361 */     button.setActionCommand(actionCommand);
/*  362 */     return button;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsComboBox createComboBox(String caption, JLbsStringList comboList) {
/*  367 */     JLbsComboBox comboBox = new JLbsComboBox(comboList);
/*  368 */     return comboBox;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsRadioButtonGroup createRadioBtnGrp(String caption, JLbsStringList groupList, int selectedTag) {
/*  373 */     JLbsRadioButtonGroup rdButtonGroup = new JLbsRadioButtonGroup()
/*      */       {
/*      */         private static final long serialVersionUID = -7596164572504480874L;
/*      */ 
/*      */ 
/*      */         
/*      */         public void actionPerformed(ActionEvent e) {
/*  380 */           super.actionPerformed(e);
/*  381 */           JLbsLocalizationDBSynchronizer.this.performOpTypeRelatedActions();
/*      */         }
/*      */       };
/*  384 */     rdButtonGroup.setText(caption);
/*  385 */     rdButtonGroup.setItems(groupList);
/*  386 */     if (selectedTag >= 0)
/*  387 */       rdButtonGroup.setSelectedItemByTag(selectedTag); 
/*  388 */     return rdButtonGroup;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsCheckBox createCheckBox(String caption) {
/*  393 */     JLbsCheckBox cBox = new JLbsCheckBox();
/*  394 */     cBox.setText(caption);
/*  395 */     cBox.setSelected(true);
/*  396 */     return cBox;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsCheckBoxGroup createCheckBoxGrp(String caption, JLbsStringList groupList, int columnCount) {
/*  401 */     JLbsCheckBoxGroup cBoxGroup = new JLbsCheckBoxGroup();
/*  402 */     cBoxGroup.setText(caption);
/*  403 */     cBoxGroup.setItems(groupList);
/*  404 */     cBoxGroup.setColumnCount(columnCount);
/*  405 */     return cBoxGroup;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsGroupBox createGroupBox(String caption) {
/*  410 */     JLbsGroupBox groupBox = new JLbsGroupBox();
/*  411 */     groupBox.setText(caption);
/*  412 */     return groupBox;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsListBox createListBox() {
/*  417 */     JLbsListBox listBox = new JLbsListBox();
/*  418 */     listBox.setBackground(Color.LIGHT_GRAY);
/*  419 */     return listBox;
/*      */   }
/*      */ 
/*      */   
/*      */   private void performOpTypeRelatedActions() {
/*  424 */     switch (this.m_RadioBtnGrpOpType.getSelectedItemTag()) {
/*      */       
/*      */       case 1:
/*  427 */         this.m_CheckBoxGrpResourceGroup.setSelectedItemArray(new int[] { 1, 2, 3, 4 });
/*  428 */         this.m_CheckBoxGrpResourceGroup.setEnabled(false);
/*  429 */         this.m_ComboBoxLanguage.setVisible(true);
/*  430 */         this.m_LabelLanguage.setVisible(true);
/*  431 */         this.m_ComboEditOutputDir.getEditControl().setText("");
/*      */         break;
/*      */       
/*      */       case 2:
/*  435 */         this.m_CheckBoxGrpResourceGroup.setEnabled(true);
/*  436 */         this.m_ComboBoxLanguage.setVisible(false);
/*  437 */         this.m_LabelLanguage.setVisible(false);
/*  438 */         this.m_ComboEditOutputDir.getEditControl().setText("");
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void performCredentialRelatedActions() {
/*  448 */     boolean defaultCred = this.m_CheckBoxDefaultUserName.isSelected();
/*      */     
/*  450 */     if (defaultCred) {
/*      */       
/*  452 */       this.m_TextEditUsername.setText("");
/*  453 */       this.m_TextEditUsername.setEnabled(false);
/*  454 */       this.m_PasswordField.setText("");
/*  455 */       this.m_PasswordField.setEnabled(false);
/*      */     }
/*      */     else {
/*      */       
/*  459 */       this.m_TextEditUsername.setEnabled(true);
/*  460 */       this.m_PasswordField.setEnabled(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize(String serverName, String dbName, String userName, String password, String resourceList) throws Exception {
/*  467 */     this.m_ServerName = serverName;
/*  468 */     this.m_DBName = dbName;
/*  469 */     this.m_UserName = userName;
/*  470 */     this.m_Password = password;
/*  471 */     this.m_ResourceDBConnection = getResourceDBConnection(this.m_ServerName, this.m_DBName, this.m_UserName, this.m_Password);
/*      */     
/*  473 */     if (resourceList != null) {
/*      */       
/*  475 */       this.m_ResourceList = new HashMap<>();
/*  476 */       String[] groups = JLbsStringUtil.tokenize(resourceList, ";");
/*  477 */       if (groups != null) {
/*  478 */         for (int i = 0; i < groups.length; i++) {
/*      */           
/*  480 */           String[] group = JLbsStringUtil.tokenize(groups[i], ":");
/*  481 */           if (group != null && group.length == 2) {
/*      */             
/*  483 */             String groupName = group[0];
/*  484 */             String[] resList = JLbsStringUtil.tokenize(group[1], ",");
/*      */             
/*  486 */             ArrayList<Integer> resNumbers = new ArrayList<>();
/*  487 */             for (int j = 0; j < resList.length; j++)
/*  488 */               resNumbers.add(Integer.valueOf(resList[j])); 
/*  489 */             this.m_ResourceList.put(groupName, resNumbers);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private Connection getResourceDBConnection(String serverName, String dbName, String userName, String password) throws Exception {
/*  497 */     String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
/*  498 */     Class.forName(driver).newInstance();
/*      */     
/*  500 */     String url = "jdbc:sqlserver://" + serverName;
/*      */     
/*  502 */     Properties props = new Properties();
/*  503 */     props.setProperty("database", dbName);
/*  504 */     props.setProperty("user", userName);
/*  505 */     props.setProperty("password", password);
/*      */     
/*  507 */     return DriverManager.getConnection(url, props);
/*      */   }
/*      */   
/*      */   protected static Connection getEmbeddedConnection(String dbCreationDir, String dbIdentifier, boolean create) {
/*      */     String url;
/*  512 */     Connection embeddedConnection = null;
/*      */     
/*  514 */     String driver = "org.apache.derby.jdbc.EmbeddedDriver";
/*      */     
/*      */     try {
/*  517 */       Class.forName(driver).newInstance();
/*      */     }
/*  519 */     catch (Exception e) {
/*      */       
/*  521 */       e.printStackTrace();
/*  522 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  526 */     if (create) {
/*  527 */       url = "jdbc:derby:" + dbCreationDir + File.separator + dbIdentifier + ";create=true";
/*      */     } else {
/*  529 */       url = "jdbc:derby:" + dbCreationDir + File.separator + dbIdentifier;
/*      */     } 
/*      */     
/*      */     try {
/*  533 */       embeddedConnection = DriverManager.getConnection(url);
/*      */     }
/*  535 */     catch (Exception e) {
/*      */       
/*  537 */       e.printStackTrace();
/*  538 */       return null;
/*      */     } 
/*      */     
/*  541 */     return embeddedConnection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Connection getEmbeddedConnectionFromZip(String fullDBPath, String dbIdentifier) {
/*      */     try {
/*  549 */       String driver = "org.apache.derby.jdbc.EmbeddedDriver";
/*  550 */       Class.forName(driver).newInstance();
/*  551 */       String url = "jdbc:derby:jar:(" + fullDBPath + ")" + dbIdentifier;
/*  552 */       Connection embeddedConnection = DriverManager.getConnection(url);
/*      */       
/*  554 */       return embeddedConnection;
/*      */     }
/*  556 */     catch (Exception e) {
/*      */       
/*  558 */       e.printStackTrace();
/*  559 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean createTablesAndIndexes(Connection embeddedConnection) {
/*  565 */     Statement stmt = null;
/*      */     
/*      */     try {
/*  568 */       if (embeddedConnection == null) {
/*  569 */         return false;
/*      */       }
/*  571 */       stmt = embeddedConnection.createStatement();
/*      */       
/*  573 */       stmt.execute("CREATE TABLE RESOURCE (RESOURCENUMBER INT PRIMARY KEY, DESCRIPTION VARCHAR(64), OWNERPRODUCT INT, SYNCHTIME TIMESTAMP)");
/*  574 */       stmt.execute("CREATE TABLE RESOURCE_ITEM (RESOURCEREF INT NOT NULL, ORDERNR INT NOT NULL, TAG INT NOT NULL, PREFIXSTR VARCHAR(900), OWNERPRODUCT INT, RESOURCESTR VARCHAR(900))");
/*  575 */       stmt.execute("CREATE TABLE SYNCHRONIZATION (SYNCHTIME TIMESTAMP NOT NULL)");
/*  576 */       stmt.execute("CREATE TABLE CHANGE_SET (RESOURCEREF INT NOT NULL, OPTYPE INT NOT NULL)");
/*  577 */       stmt.execute("CREATE TABLE HELP_CONTENTS (ID INT PRIMARY KEY, DOCNAME VARCHAR(100) NOT NULL, DOCTITLE VARCHAR(100) NOT NULL, DOCTYPE INT NOT NULL, DOCBODY VARCHAR(4000) NOT NULL, SYNCHTIME TIMESTAMP)");
/*  578 */       stmt.execute("CREATE TABLE MESSAGE (ID INT PRIMARY KEY, CONS_ID VARCHAR(100) NOT NULL, MODULE VARCHAR(5) NOT NULL, MTYPE INT NOT NULL, LISTID INT NOT NULL, STRTAG INT NOT NULL, RESGROUP VARCHAR(10) NOT NULL, BUTTONS INT NOT NULL, DEF_BUTTON INT NOT NULL, SYNCHTIME TIMESTAMP)");
/*      */       
/*  580 */       stmt.execute("CREATE UNIQUE INDEX IDX_RESOURCE_ITEM_01 ON RESOURCE_ITEM (RESOURCEREF, TAG)");
/*  581 */       stmt.execute("CREATE INDEX IDX_RESOURCE_ITEM_03 ON RESOURCE_ITEM (ORDERNR)");
/*  582 */       stmt.execute("CREATE INDEX IDX_RESOURCE_ITEM_04 ON RESOURCE_ITEM (TAG)");
/*  583 */       stmt.execute("CREATE UNIQUE INDEX IDX_MESSAGE_01 ON MESSAGE (CONS_ID)");
/*      */       
/*  585 */       stmt.execute("CREATE TRIGGER TRG_RESOURCE_DELETE AFTER DELETE ON RESOURCE REFERENCING OLD AS OLD FOR EACH ROW MODE DB2SQL DELETE FROM RESOURCE_ITEM WHERE RESOURCEREF = OLD.RESOURCENUMBER");
/*  586 */       stmt.execute("CREATE TRIGGER TRG_RESOURCE_UPDATE AFTER UPDATE OF RESOURCENUMBER ON RESOURCE REFERENCING OLD AS OLD NEW AS NEW FOR EACH ROW MODE DB2SQL UPDATE RESOURCE_ITEM SET RESOURCEREF = NEW.RESOURCENUMBER WHERE RESOURCEREF = OLD.RESOURCENUMBER");
/*      */       
/*  588 */       return true;
/*      */     }
/*  590 */     catch (Exception e) {
/*      */       
/*  592 */       e.printStackTrace();
/*  593 */       return false;
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  599 */         if (stmt != null)
/*  600 */           stmt.close(); 
/*  601 */         if (embeddedConnection != null) {
/*  602 */           embeddedConnection.close();
/*      */         }
/*  604 */       } catch (Exception ex) {
/*      */         
/*  606 */         ex.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Timestamp setSynchronizationTime(Connection embeddedConnection, boolean synchronize) {
/*  613 */     ResultSet rsServerTime = executeQuery(this.m_ResourceDBConnection, "SELECT GETDATE()");
/*      */     
/*      */     try {
/*  616 */       rsServerTime.next();
/*  617 */       Timestamp serverTime = rsServerTime.getTimestamp(1);
/*  618 */       if (synchronize)
/*  619 */         execute(embeddedConnection, "DELETE FROM SYNCHRONIZATION"); 
/*  620 */       execute(embeddedConnection, "INSERT INTO SYNCHRONIZATION VALUES %valueList%".replaceAll("%valueList%", createTimeValStr(serverTime)));
/*  621 */       return serverTime;
/*      */     }
/*  623 */     catch (SQLException e) {
/*      */       
/*  625 */       e.printStackTrace();
/*  626 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected Timestamp getLastSynchronizationTime(Connection embeddedConnection) {
/*  632 */     ResultSet rsServerTime = executeQuery(embeddedConnection, "SELECT SYNCHTIME FROM SYNCHRONIZATION");
/*      */     
/*      */     try {
/*  635 */       rsServerTime.next();
/*  636 */       Timestamp serverTime = rsServerTime.getTimestamp(1);
/*  637 */       return serverTime;
/*      */     }
/*  639 */     catch (SQLException e) {
/*      */       
/*  641 */       e.printStackTrace();
/*  642 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importResources(Connection connection, String tableName, String resGroup, Timestamp serverTime) {
/*  648 */     log("Import started for group " + resGroup + " : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  649 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%'".replaceAll("%resGrp%", resGroup));
/*      */     
/*  651 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  655 */         StringBuilder valueBuffer = new StringBuilder();
/*  656 */         int counter = 0, valueCounter = 0;
/*      */         
/*  658 */         int resGroupBase = JLbsLocalizationUtil.getResGroupBase(resGroup);
/*      */         
/*  660 */         while (resources.next()) {
/*      */           
/*  662 */           int resNumber = resources.getInt("RESOURCENR");
/*  663 */           String desc = resources.getString("DESCRIPTION");
/*  664 */           int ownerProduct = resources.getInt("OWNERPRODUCT");
/*  665 */           int orgResNumber = resNumber;
/*      */ 
/*      */           
/*  668 */           if (resNumber == -1002 && resGroup.equalsIgnoreCase("HR")) {
/*      */             continue;
/*      */           }
/*  671 */           if (!isIncludedResource(resGroup, resNumber)) {
/*      */             continue;
/*      */           }
/*  674 */           resNumber = JLbsLocalizationUtil.getDBResNumber(resNumber, resGroupBase);
/*      */           
/*  676 */           importResourceItems(connection, tableName, orgResNumber, resNumber, resGroup, false);
/*      */           
/*  678 */           if (valueCounter > 0)
/*  679 */             valueBuffer.append(", "); 
/*  680 */           valueCounter++;
/*  681 */           counter++;
/*      */           
/*  683 */           valueBuffer.append(createResValStr(resNumber, desc, ownerProduct, serverTime));
/*      */           
/*  685 */           if (valueCounter % 10 == 0) {
/*      */             
/*  687 */             valueCounter = 0;
/*  688 */             execute(connection, "INSERT INTO RESOURCE VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*  689 */             log("Finished import of \"" + counter + "\" resources : " + 
/*  690 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  691 */             valueBuffer = new StringBuilder();
/*      */           } 
/*      */         } 
/*      */         
/*  695 */         if (counter > 0 && valueCounter > 0) {
/*  696 */           execute(connection, "INSERT INTO RESOURCE VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*      */         }
/*  698 */         resources.close();
/*      */       
/*      */       }
/*  701 */       catch (SQLException e1) {
/*      */         
/*  703 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*  706 */     log("Import completed for group " + resGroup + " : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void importHelpDocuments(Connection connection, Timestamp serverTime) {
/*  711 */     log("Import started for help documents : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  712 */     ResultSet helpDocs = executeQuery(this.m_ResourceDBConnection, "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY FROM RE_HELPDOCS H");
/*      */     
/*  714 */     if (helpDocs != null) {
/*      */       
/*      */       try {
/*      */         
/*  718 */         StringBuilder valueBuffer = new StringBuilder();
/*  719 */         int counter = 0, valueCounter = 0;
/*      */         
/*  721 */         while (helpDocs.next()) {
/*      */           
/*  723 */           int id = helpDocs.getInt("ID");
/*  724 */           String docName = helpDocs.getString("DOCNAME");
/*  725 */           String docTitle = helpDocs.getString("DOCTITLE");
/*  726 */           String docBody = helpDocs.getString("DOCBODY");
/*  727 */           int docType = helpDocs.getInt("DOCTYPE");
/*      */           
/*  729 */           if (valueCounter > 0)
/*  730 */             valueBuffer.append(", "); 
/*  731 */           valueCounter++;
/*  732 */           counter++;
/*      */           
/*  734 */           valueBuffer.append(createHelpDocStr(id, docName, docTitle, docType, docBody, serverTime));
/*      */           
/*  736 */           if (valueCounter % 10 == 0) {
/*      */             
/*  738 */             valueCounter = 0;
/*  739 */             execute(connection, "INSERT INTO HELP_CONTENTS VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*  740 */             log("Finished import of \"" + counter + "\" help documents : " + 
/*  741 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  742 */             valueBuffer = new StringBuilder();
/*      */           } 
/*      */         } 
/*      */         
/*  746 */         if (counter > 0 && valueCounter > 0) {
/*  747 */           execute(connection, "INSERT INTO HELP_CONTENTS VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*      */         }
/*  749 */         helpDocs.close();
/*      */       
/*      */       }
/*  752 */       catch (SQLException e1) {
/*      */         
/*  754 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*  757 */     log("Import completed for help documents : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void importMessages(Connection connection, Timestamp serverTime) {
/*  762 */     log("Import started for messages : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  763 */     ResultSet messages = executeQuery(this.m_ResourceDBConnection, "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON FROM RE_MESSAGES M");
/*      */     
/*  765 */     if (messages != null) {
/*      */       
/*      */       try {
/*      */         
/*  769 */         StringBuilder valueBuffer = new StringBuilder();
/*  770 */         int counter = 0, valueCounter = 0;
/*      */         
/*  772 */         while (messages.next()) {
/*      */           
/*  774 */           int key = messages.getInt("ID");
/*  775 */           String id = messages.getString("CONS_ID");
/*  776 */           String module = messages.getString("MODULE");
/*  777 */           int type = messages.getInt("MTYPE");
/*  778 */           int listId = messages.getInt("LISTID");
/*  779 */           int strTag = messages.getInt("STRTAG");
/*  780 */           String resGroup = messages.getString("RESGROUP");
/*  781 */           int buttons = messages.getInt("BUTTONS");
/*  782 */           int defButton = messages.getInt("DEF_BUTTON");
/*      */           
/*  784 */           if (valueCounter > 0)
/*  785 */             valueBuffer.append(", "); 
/*  786 */           valueCounter++;
/*  787 */           counter++;
/*      */           
/*  789 */           valueBuffer.append(createMessageStr(key, id, module, type, listId, strTag, resGroup, buttons, defButton, 
/*  790 */                 serverTime));
/*      */           
/*  792 */           if (valueCounter % 10 == 0) {
/*      */             
/*  794 */             valueCounter = 0;
/*  795 */             execute(connection, "INSERT INTO MESSAGE VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*  796 */             log("Finished import of \"" + counter + "\" messages : " + 
/*  797 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  798 */             valueBuffer = new StringBuilder();
/*      */           } 
/*      */         } 
/*      */         
/*  802 */         if (counter > 0 && valueCounter > 0) {
/*  803 */           execute(connection, "INSERT INTO MESSAGE VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*      */         }
/*  805 */         messages.close();
/*      */       
/*      */       }
/*  808 */       catch (SQLException e1) {
/*      */         
/*  810 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*  813 */     log("Import completed for messages : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isIncludedResource(String resGroup, int resNumber) {
/*  823 */     if (this.m_ResourceList == null) {
/*  824 */       return true;
/*      */     }
/*  826 */     if (this.m_ResourceList.get(resGroup) != null) {
/*      */       
/*  828 */       ArrayList<Integer> resList = this.m_ResourceList.get(resGroup);
/*  829 */       return (resList.indexOf(Integer.valueOf(resNumber)) != -1);
/*      */     } 
/*  831 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void synchronizeResources(Connection connection, String tableName, String resGroup, String lastSynchTime, Timestamp serverTime) {
/*  837 */     log("Synchronization started for group " + resGroup + " : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  838 */     ResultSet resources = executeQuery(
/*  839 */         this.m_ResourceDBConnection, 
/*  840 */         "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.MODIFIEDON, R.AUTOMODIFIEDON, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%' AND (R.MODIFIEDON > '%synchTime%' OR R.AUTOMODIFIEDON > '%synchTime%') ORDER BY R.RESOURCENR".replaceAll("%resGrp%", resGroup).replaceAll("%synchTime%", 
/*  841 */           lastSynchTime));
/*      */     
/*  843 */     int globalCounter = 0;
/*  844 */     int internalCounter = 0;
/*  845 */     StringBuilder logBuffer = new StringBuilder();
/*  846 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  850 */         int resGroupBase = JLbsLocalizationUtil.getResGroupBase(resGroup);
/*      */         
/*  852 */         while (resources.next()) {
/*      */           
/*  854 */           int resNumber = resources.getInt("RESOURCENR");
/*  855 */           String desc = resources.getString("DESCRIPTION");
/*  856 */           int ownerProduct = resources.getInt("OWNERPRODUCT");
/*  857 */           int orgResNumber = resNumber;
/*      */ 
/*      */           
/*  860 */           if (resNumber == -1002 && resGroup.equalsIgnoreCase("HR")) {
/*      */             continue;
/*      */           }
/*  863 */           if (!isIncludedResource(resGroup, resNumber)) {
/*      */             continue;
/*      */           }
/*  866 */           resNumber = JLbsLocalizationUtil.getDBResNumber(resNumber, resGroupBase);
/*      */           
/*  868 */           execute(connection, "DELETE FROM RESOURCE WHERE RESOURCENUMBER IN (%valueList%)".replaceAll("%valueList%", String.valueOf(resNumber)));
/*  869 */           importResourceItems(connection, tableName, orgResNumber, resNumber, resGroup, true);
/*  870 */           execute(connection, 
/*  871 */               "INSERT INTO RESOURCE VALUES %valueList%".replaceAll("%valueList%", 
/*  872 */                 createResValStr(resNumber, desc, ownerProduct, serverTime)));
/*      */           
/*  874 */           globalCounter++;
/*  875 */           internalCounter++;
/*  876 */           if (internalCounter > 1) {
/*  877 */             logBuffer.append(", ");
/*      */           }
/*  879 */           logBuffer.append(orgResNumber);
/*      */           
/*  881 */           if (internalCounter % 50 == 0) {
/*      */             
/*  883 */             log("Finished synchronization of resources : \"" + logBuffer.toString() + "\"");
/*  884 */             logBuffer = new StringBuilder();
/*  885 */             internalCounter = 0;
/*      */           } 
/*      */         } 
/*      */         
/*  889 */         resources.close();
/*      */       
/*      */       }
/*  892 */       catch (SQLException e1) {
/*      */         
/*  894 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/*  898 */     if (internalCounter > 0) {
/*  899 */       log("Finished synchronization of resources : \"" + logBuffer.toString() + "\"");
/*      */     }
/*  901 */     log("Synchronization completed for group " + resGroup + " : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  902 */     log(String.valueOf(globalCounter) + " resources synchronized.");
/*      */   }
/*      */ 
/*      */   
/*      */   private void synchronizeHelpDocuments(Connection connection, String lastSynchTime, Timestamp serverTime) {
/*  907 */     log("Synchronization started for help documents : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  908 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, 
/*  909 */         "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY, H.MODIFIEDON FROM RE_HELPDOCS H WHERE H.MODIFIEDON > '%synchTime%' ORDER BY H.ID".replaceAll("%synchTime%", lastSynchTime));
/*      */     
/*  911 */     int globalCounter = 0;
/*  912 */     int internalCounter = 0;
/*  913 */     StringBuilder logBuffer = new StringBuilder();
/*  914 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  918 */         while (resources.next()) {
/*      */           
/*  920 */           int id = resources.getInt("ID");
/*  921 */           String docName = resources.getString("DOCNAME");
/*  922 */           String docTitle = resources.getString("DOCTITLE");
/*  923 */           String docBody = resources.getString("DOCBODY");
/*  924 */           int docType = resources.getInt("DOCTYPE");
/*      */           
/*  926 */           execute(connection, "DELETE FROM HELP_CONTENTS WHERE ID IN (%valueList%)".replaceAll("%valueList%", String.valueOf(id)));
/*  927 */           execute(connection, 
/*  928 */               "INSERT INTO HELP_CONTENTS VALUES %valueList%".replaceAll("%valueList%", 
/*  929 */                 createHelpDocStr(id, docName, docTitle, docType, docBody, serverTime)));
/*      */           
/*  931 */           globalCounter++;
/*  932 */           internalCounter++;
/*  933 */           if (internalCounter > 1) {
/*  934 */             logBuffer.append(", ");
/*      */           }
/*  936 */           logBuffer.append(id);
/*      */           
/*  938 */           if (internalCounter % 50 == 0) {
/*      */             
/*  940 */             log("Finished synchronization of help documents : \"" + logBuffer.toString() + "\"");
/*  941 */             logBuffer = new StringBuilder();
/*  942 */             internalCounter = 0;
/*      */           } 
/*      */         } 
/*      */         
/*  946 */         resources.close();
/*      */       
/*      */       }
/*  949 */       catch (SQLException e1) {
/*      */         
/*  951 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/*  955 */     if (internalCounter > 0) {
/*  956 */       log("Finished synchronization of help documents : \"" + logBuffer.toString() + "\"");
/*      */     }
/*  958 */     log("Synchronization completed for help documents : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  959 */     log(String.valueOf(globalCounter) + " help documents synchronized.");
/*      */   }
/*      */ 
/*      */   
/*      */   private void synchronizeMessages(Connection connection, String lastSynchTime, Timestamp serverTime) {
/*  964 */     log("Synchronization started for messages : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  965 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, 
/*  966 */         "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON, M.MODIFIEDON FROM RE_MESSAGES M WHERE M.MODIFIEDON > '%synchTime%' ORDER BY M.ID".replaceAll("%synchTime%", lastSynchTime));
/*      */     
/*  968 */     int globalCounter = 0;
/*  969 */     int internalCounter = 0;
/*  970 */     StringBuilder logBuffer = new StringBuilder();
/*  971 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  975 */         while (resources.next()) {
/*      */           
/*  977 */           int key = resources.getInt("ID");
/*  978 */           String id = resources.getString("CONS_ID");
/*  979 */           String module = resources.getString("MODULE");
/*  980 */           int type = resources.getInt("MTYPE");
/*  981 */           int listId = resources.getInt("LISTID");
/*  982 */           int strTag = resources.getInt("STRTAG");
/*  983 */           String resGroup = resources.getString("RESGROUP");
/*  984 */           int buttons = resources.getInt("BUTTONS");
/*  985 */           int defButton = resources.getInt("DEF_BUTTON");
/*      */           
/*  987 */           execute(connection, "DELETE FROM MESSAGE WHERE ID IN (%valueList%)".replaceAll("%valueList%", String.valueOf(key)));
/*  988 */           execute(connection, "INSERT INTO MESSAGE VALUES %valueList%".replaceAll("%valueList%", 
/*  989 */                 createMessageStr(key, id, module, type, listId, strTag, resGroup, buttons, defButton, serverTime)));
/*      */           
/*  991 */           globalCounter++;
/*  992 */           internalCounter++;
/*  993 */           if (internalCounter > 1) {
/*  994 */             logBuffer.append(", ");
/*      */           }
/*  996 */           logBuffer.append(id);
/*      */           
/*  998 */           if (internalCounter % 50 == 0) {
/*      */             
/* 1000 */             log("Finished synchronization of messages : \"" + logBuffer.toString() + "\"");
/* 1001 */             logBuffer = new StringBuilder();
/* 1002 */             internalCounter = 0;
/*      */           } 
/*      */         } 
/*      */         
/* 1006 */         resources.close();
/*      */       
/*      */       }
/* 1009 */       catch (SQLException e1) {
/*      */         
/* 1011 */         e1.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/* 1015 */     if (internalCounter > 0) {
/* 1016 */       log("Finished synchronization of messages : \"" + logBuffer.toString() + "\"");
/*      */     }
/* 1018 */     log("Synchronization completed for messages : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1019 */     log(String.valueOf(globalCounter) + " messages synchronized.");
/*      */   }
/*      */ 
/*      */   
/*      */   private void log(String message) {
/* 1024 */     if (this.m_ImportMonitor != null) {
/* 1025 */       this.m_ImportMonitor.updateStatus(message, 4, 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void importResourceItems(Connection connection, String tableName, int orgResNumber, int resNumber, String resGroup, boolean synchronize) {
/* 1031 */     if (connection != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1038 */       String resItemSQLStmt = "SELECT DISTINCT PREFIXSTR = ISNULL(RI.PREFIXSTR, ''),  RESSTR =  CASE \tR.RESOURCETYPE \tWHEN 2 THEN ISNULL(STD.RESOURCESTR COLLATE DATABASE_DEFAULT, '') \tELSE ISNULL(%table%.RESOURCESTR, %ReplaceTablePrefs%) END,  RI.ORDERNR,  RI.TAGNR, RI.OWNERPRODUCT FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% ON (RI.ID = %table%.RESOURCEITEMREF) %ReplaceTablePrefsJoin%WHERE R.RESOURCEGROUP = '%resGrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resNr% ORDER BY RI.ORDERNR".replaceAll("%table%", tableName)
/* 1039 */         .replaceAll("%resNr%", String.valueOf(orgResNumber)).replaceAll("%resGrp%", resGroup);
/*      */       
/* 1041 */       resItemSQLStmt = addPreferedTableOptions(tableName, resItemSQLStmt);
/*      */       
/* 1043 */       ResultSet resourceItems = executeQuery(this.m_ResourceDBConnection, resItemSQLStmt);
/*      */ 
/*      */       
/*      */       try {
/* 1047 */         StringBuilder valueBuffer = new StringBuilder();
/* 1048 */         int valueCounter = 0;
/*      */         
/* 1050 */         while (resourceItems.next()) {
/*      */           
/* 1052 */           int orderNr = resourceItems.getInt("ORDERNR");
/* 1053 */           int tagNr = resourceItems.getInt("TAGNR");
/* 1054 */           String resStr = resourceItems.getString("RESSTR");
/* 1055 */           String resPrefix = resourceItems.getString("PREFIXSTR");
/* 1056 */           int ownerProduct = resourceItems.getInt("OWNERPRODUCT");
/*      */           
/* 1058 */           if (valueCounter > 0)
/* 1059 */             valueBuffer.append(", "); 
/* 1060 */           valueCounter++;
/*      */           
/* 1062 */           valueBuffer.append(createResItemValStr(resNumber, orderNr, tagNr, resPrefix, ownerProduct, resStr));
/* 1063 */           if (valueCounter % 100 == 0) {
/*      */             
/* 1065 */             execute(connection, "INSERT INTO RESOURCE_ITEM VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/* 1066 */             valueBuffer = new StringBuilder();
/* 1067 */             valueCounter = 0;
/*      */           } 
/*      */         } 
/*      */         
/* 1071 */         if (valueCounter > 0) {
/* 1072 */           execute(connection, "INSERT INTO RESOURCE_ITEM VALUES %valueList%".replaceAll("%valueList%", valueBuffer.toString()));
/*      */         }
/* 1074 */       } catch (Exception e) {
/*      */         
/* 1076 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String addPreferedTableOptions(String tableName, String resItemSQLStmt) {
/* 1083 */     String[] replacementTableList = getPreferedReplacementTableList(tableName);
/*      */     
/* 1085 */     if (replacementTableList != null && replacementTableList.length >= 2)
/*      */     {
/*      */       
/* 1088 */       if (replacementTableList[0] != null && !replacementTableList[0].equals(TABLE_NAME_NOSELECT) && 
/* 1089 */         replacementTableList[1] != null && !replacementTableList[1].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/* 1091 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable1%.RESOURCESTR, ISNULL(%preftable2%.RESOURCESTR, ''))");
/* 1092 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "LEFT OUTER JOIN %preftable1% ON (RI.ID = %preftable1%.RESOURCEITEMREF) LEFT OUTER JOIN %preftable2% ON (RI.ID = %preftable2%.RESOURCEITEMREF) ");
/*      */         
/* 1094 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable1%", replacementTableList[0]);
/* 1095 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable2%", replacementTableList[1]);
/*      */       
/*      */       }
/* 1098 */       else if (replacementTableList[0] != null && !replacementTableList[0].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/* 1100 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable1%.RESOURCESTR, '')");
/* 1101 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "LEFT OUTER JOIN %preftable1% ON (RI.ID = %preftable1%.RESOURCEITEMREF) ");
/* 1102 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable1%", replacementTableList[0]);
/*      */       
/*      */       }
/* 1105 */       else if (replacementTableList[1] != null && !replacementTableList[1].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/* 1107 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable2%.RESOURCESTR, '')");
/* 1108 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "LEFT OUTER JOIN %preftable2% ON (RI.ID = %preftable2%.RESOURCEITEMREF) ");
/* 1109 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable2%", replacementTableList[1]);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1114 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "''");
/* 1115 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "");
/*      */       } 
/*      */     }
/*      */     
/* 1119 */     return resItemSQLStmt;
/*      */   }
/*      */ 
/*      */   
/*      */   private String[] getPreferedReplacementTableList(String tableName) {
/* 1124 */     String[] preferred = null;
/* 1125 */     if (!JLbsStringUtil.isEmpty(this.m_Lang))
/* 1126 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(String.valueOf(tableName) + "_" + this.m_Lang); 
/* 1127 */     if (preferred == null)
/* 1128 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(tableName); 
/* 1129 */     if (preferred == null)
/* 1130 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(""); 
/* 1131 */     return preferred;
/*      */   }
/*      */ 
/*      */   
/*      */   private void execute(Connection connection, String sqlStatement) {
/* 1136 */     if (connection != null) {
/*      */       
/* 1138 */       Statement stmt = null;
/*      */       
/*      */       try {
/* 1141 */         stmt = connection.createStatement();
/* 1142 */         stmt.execute(sqlStatement);
/*      */       }
/* 1144 */       catch (Exception e) {
/*      */         
/* 1146 */         System.err.println("Problem statement : " + sqlStatement);
/* 1147 */         e.printStackTrace();
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1153 */           if (stmt != null) {
/* 1154 */             stmt.close();
/*      */           }
/* 1156 */         } catch (Exception ex) {
/*      */           
/* 1158 */           ex.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private ResultSet executeQuery(Connection connection, String sqlStatement) {
/* 1166 */     Statement stmt = null;
/* 1167 */     ResultSet resultSet = null;
/*      */     
/*      */     try {
/* 1170 */       stmt = connection.createStatement();
/* 1171 */       resultSet = stmt.executeQuery(sqlStatement);
/*      */     }
/* 1173 */     catch (Exception e) {
/*      */       
/* 1175 */       resultSet = null;
/* 1176 */       System.err.println("Problem statement : " + sqlStatement);
/* 1177 */       e.printStackTrace();
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 1183 */         if (stmt != null) {
/* 1184 */           stmt.close();
/*      */         }
/* 1186 */       } catch (Exception ex) {
/*      */         
/* 1188 */         ex.printStackTrace();
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1193 */         if (resultSet != null) {
/* 1194 */           resultSet.close();
/*      */         }
/* 1196 */       } catch (Exception ex) {
/*      */         
/* 1198 */         ex.printStackTrace();
/*      */       } 
/*      */     } 
/*      */     
/* 1202 */     return resultSet;
/*      */   }
/*      */ 
/*      */   
/*      */   private String createHelpDocStr(int id, String docName, String docTitle, int docType, String docBody, Timestamp timestamp) {
/* 1207 */     String internalDocName = (docName == null) ? 
/* 1208 */       "" : 
/* 1209 */       docName;
/* 1210 */     internalDocName = internalDocName.trim();
/* 1211 */     String internalDocTitle = (docTitle == null) ? 
/* 1212 */       "" : 
/* 1213 */       docTitle;
/* 1214 */     String internalDocBody = (docBody == null) ? 
/* 1215 */       "" : 
/* 1216 */       docBody;
/* 1217 */     return "(" + id + ", '" + internalDocName.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', '" + 
/* 1218 */       internalDocTitle.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', " + docType + ", '" + 
/* 1219 */       internalDocBody.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', '" + timestamp.toString() + "' )";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String createMessageStr(int key, String id, String module, int type, int detailListId, int stringTag, String detailResGroup, int buttons, int defButton, Timestamp timestamp) {
/* 1225 */     String internalId = (id == null) ? 
/* 1226 */       "" : 
/* 1227 */       id;
/* 1228 */     String internalModule = (module == null) ? 
/* 1229 */       "" : 
/* 1230 */       module;
/* 1231 */     String internalResGroup = (detailResGroup == null) ? 
/* 1232 */       "" : 
/* 1233 */       detailResGroup;
/* 1234 */     return "(" + key + ", '" + internalId.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', '" + 
/* 1235 */       internalModule.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', " + type + ", " + detailListId + ", " + 
/* 1236 */       stringTag + ", '" + internalResGroup.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', " + buttons + ", " + 
/* 1237 */       defButton + ", '" + timestamp.toString() + "' )";
/*      */   }
/*      */ 
/*      */   
/*      */   private String createResValStr(int resNumber, String desc, int ownerProduct, Timestamp timestamp) {
/* 1242 */     String internalDesc = (desc == null) ? 
/* 1243 */       "" : 
/* 1244 */       desc;
/* 1245 */     return "(" + resNumber + ", '" + internalDesc.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "', " + ownerProduct + 
/* 1246 */       ", '" + timestamp.toString() + "' )";
/*      */   }
/*      */ 
/*      */   
/*      */   private String createResItemValStr(int resNumber, int orderNr, int tagNr, String prefix, int ownerProduct, String resStr) {
/* 1251 */     return "(" + resNumber + ", " + orderNr + ", " + tagNr + ", '" + prefix.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + 
/* 1252 */       "', " + ownerProduct + ", '" + resStr.replaceAll("'", "''").replaceAll("\\$", "\\\\\\$") + "')";
/*      */   }
/*      */ 
/*      */   
/*      */   private String createTimeValStr(Timestamp timestamp) {
/* 1257 */     return "('" + timestamp.toString() + "')";
/*      */   }
/*      */ 
/*      */   
/*      */   private void importLangSpecificResources(String dbCreationDir, String dbIdentifier, String[] groupList) {
/* 1262 */     if (this.m_ResourceDBConnection != null) {
/*      */       
/* 1264 */       this.m_Lang = dbIdentifier;
/* 1265 */       readLocalizationConfiguration(dbCreationDir);
/* 1266 */       Object objTableName = JLbsLocalizationUtil.ms_LangMaps.get(dbIdentifier);
/* 1267 */       if (objTableName != null && objTableName instanceof String) {
/*      */         
/* 1269 */         log("Receiving connection..");
/* 1270 */         Connection embeddedConnection = getEmbeddedConnection(dbCreationDir, dbIdentifier, true);
/* 1271 */         if (embeddedConnection != null)
/*      */         {
/* 1273 */           if (createTablesAndIndexes(embeddedConnection)) {
/*      */             
/* 1275 */             Timestamp serverTime = setSynchronizationTime(embeddedConnection, false);
/* 1276 */             log("Import started : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1277 */             importMessages(embeddedConnection, serverTime);
/* 1278 */             for (int i = 0; i < groupList.length; i++) {
/*      */               
/* 1280 */               if (this.m_ResourceList == null || this.m_ResourceList.get(groupList[i]) != null)
/*      */               {
/* 1282 */                 if (!JLbsStringUtil.isEmpty(groupList[i]))
/* 1283 */                   importResources(embeddedConnection, (String)objTableName, groupList[i], serverTime);  } 
/*      */             } 
/* 1285 */             importHelpDocuments(embeddedConnection, serverTime);
/*      */             
/* 1287 */             log("Import completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1288 */             String outputPath = JLbsFileUtil.appendPath(dbCreationDir, String.valueOf(dbIdentifier) + ".zip");
/* 1289 */             compressDBFolder(dbCreationDir, dbIdentifier, outputPath);
/* 1290 */             log("Compress operation completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */             
/*      */             try {
/* 1293 */               embeddedConnection.close();
/* 1294 */               releaseEmbeddedConnection(dbCreationDir, dbIdentifier);
/*      */             }
/* 1296 */             catch (SQLException e) {
/*      */               
/* 1298 */               e.printStackTrace();
/*      */             } 
/*      */           } else {
/*      */             
/* 1302 */             log("Embedded database tables could not be created!");
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void releaseEmbeddedConnection(String dbCreationDir, String lang) {
/* 1314 */     String url = "jdbc:derby:" + JLbsFileUtil.appendPath(dbCreationDir, lang);
/* 1315 */     Connection conn = null;
/*      */     
/*      */     try {
/* 1318 */       System.out.println("Trying to release embedded DB connection for url : " + url);
/* 1319 */       conn = DriverManager.getConnection(String.valueOf(url) + ";shutdown=true");
/*      */     }
/* 1321 */     catch (SQLException e) {
/*      */ 
/*      */ 
/*      */       
/* 1325 */       System.out.println("Embedded connection released for url : " + url);
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/* 1330 */       if (conn != null) {
/*      */         
/*      */         try {
/*      */           
/* 1334 */           conn.close();
/*      */         }
/* 1336 */         catch (SQLException sQLException) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void createEmptyDatabase(String dbCreationDir, String dbIdentifier, String outputPath) throws Exception {
/* 1345 */     Connection embeddedConnection = getEmbeddedConnection(dbCreationDir, dbIdentifier, true);
/* 1346 */     if (createTablesAndIndexes(embeddedConnection)) {
/*      */       
/* 1348 */       System.out.println("DB Create operation started : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1349 */       compressDBFolder(dbCreationDir, dbIdentifier, outputPath);
/* 1350 */       System.out.println("DB Create operation completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */     } else {
/*      */       
/* 1353 */       throw new Exception("Database tables/indexes could not be created!");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void synchronizeLangSpecificResources(String outputDir, String dbFilePath, String dbIdentifier, String[] groupList) {
/* 1358 */     if (this.m_ResourceDBConnection != null) {
/*      */       
/* 1360 */       readLocalizationConfiguration(outputDir);
/* 1361 */       Object objTableName = JLbsLocalizationUtil.ms_LangMaps.get(dbIdentifier);
/* 1362 */       if (objTableName != null && objTableName instanceof String) {
/*      */         
/* 1364 */         String tmpDir = JLbsFileUtil.getTempDirectory();
/*      */         
/* 1366 */         if (!JLbsStringUtil.isEmpty(tmpDir)) {
/*      */           
/* 1368 */           log("Synchronization started : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */           
/* 1370 */           String extractionDir = String.valueOf(JLbsFileUtil.ensurePath(tmpDir)) + "EmbeddedDB_" + 
/* 1371 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()).replaceAll(":", "_");
/*      */           
/* 1373 */           extractDBFile(dbFilePath, extractionDir);
/*      */           
/* 1375 */           log("Receiving connection..");
/* 1376 */           Connection embeddedConnection = getEmbeddedConnection(extractionDir, dbIdentifier, false);
/* 1377 */           if (embeddedConnection != null) {
/*      */             
/* 1379 */             log("Connection received");
/* 1380 */             Timestamp lastSynchTime = getLastSynchronizationTime(embeddedConnection);
/* 1381 */             Timestamp serverTime = setSynchronizationTime(embeddedConnection, true);
/* 1382 */             setSynchronizationTime(embeddedConnection, true);
/*      */             
/* 1384 */             for (int i = 0; i < groupList.length; i++) {
/*      */               
/* 1386 */               if (this.m_ResourceList == null || this.m_ResourceList.get(groupList[i]) != null)
/*      */               {
/* 1388 */                 if (!JLbsStringUtil.isEmpty(groupList[i]))
/* 1389 */                   synchronizeResources(embeddedConnection, (String)objTableName, groupList[i], 
/* 1390 */                       lastSynchTime.toString(), serverTime);  } 
/*      */             } 
/* 1392 */             synchronizeHelpDocuments(embeddedConnection, lastSynchTime.toString(), serverTime);
/* 1393 */             synchronizeMessages(embeddedConnection, lastSynchTime.toString(), serverTime);
/*      */             
/* 1395 */             log("Synchronization completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1396 */             compressDBFolder(extractionDir, dbIdentifier, dbFilePath);
/* 1397 */             log("Compress operation completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */ 
/*      */             
/*      */             try {
/* 1401 */               embeddedConnection.close();
/* 1402 */               embeddedConnection = null;
/* 1403 */               releaseEmbeddedConnection(extractionDir, dbIdentifier);
/*      */             }
/* 1405 */             catch (SQLException e) {
/*      */               
/* 1407 */               e.printStackTrace();
/*      */             } 
/*      */           } else {
/*      */             
/* 1411 */             log("Connection to embedded DB failed!");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void extractDBFile(String dbFileDir, String extractionDir) {
/* 1419 */     Expand expand = new Expand();
/*      */     
/* 1421 */     File extractionFolder = new File(extractionDir);
/* 1422 */     extractionFolder.mkdirs();
/*      */     
/* 1424 */     File dbFile = new File(dbFileDir);
/*      */     
/* 1426 */     expand.setSrc(dbFile);
/* 1427 */     expand.setDest(extractionFolder);
/* 1428 */     expand.setProject(new Project());
/*      */     
/* 1430 */     expand.execute();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void compressDBFolder(String dbCreationDir, String dbIdentifier, String outputPath) {
/* 1435 */     File dbCreationFolder = new File(dbCreationDir);
/* 1436 */     File zipFile = new File(outputPath);
/*      */     
/* 1438 */     if (zipFile.exists()) {
/* 1439 */       zipFile.delete();
/*      */     }
/* 1441 */     Zip zip = new Zip();
/* 1442 */     zip.setBasedir(dbCreationFolder);
/* 1443 */     zip.setIncludes(String.valueOf(dbIdentifier) + "/**");
/* 1444 */     zip.setExcludes(String.valueOf(dbIdentifier) + "/db.lck");
/* 1445 */     zip.setDestFile(zipFile);
/* 1446 */     zip.setProject(new Project());
/*      */     
/* 1448 */     zip.execute();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) throws Exception {
/* 1457 */     if (args.length >= 5) {
/*      */       
/* 1459 */       JLbsLocalizationDBSynchronizer lclSynchronizer = new JLbsLocalizationDBSynchronizer(false);
/* 1460 */       String[] groupList = { "UN", "HR", "UNRP", "HRRP", 
/* 1461 */           "HELP", "SS" };
/*      */       
/* 1463 */       String serverID = args[0];
/* 1464 */       String dbName = args[1];
/* 1465 */       String lang = args[2];
/* 1466 */       String outputDir = args[3];
/* 1467 */       int opType = JLbsConvertUtil.str2IntDef(args[4], -1);
/*      */       
/* 1469 */       JLbsLocalizationDBSynchronizerHelper helper = new JLbsLocalizationDBSynchronizerHelper();
/*      */       
/* 1471 */       String userName = helper.getDecryptedData(DEFAULT_USERNAME);
/* 1472 */       String password = helper.getDecryptedData(DEFAULT_PASSWORD);
/* 1473 */       if (args.length > 6) {
/*      */         
/* 1475 */         userName = args[5];
/* 1476 */         password = args[6];
/*      */       } 
/*      */       
/* 1479 */       String resources = null;
/* 1480 */       if (args.length == 6)
/* 1481 */         resources = args[5]; 
/* 1482 */       if (args.length == 8) {
/* 1483 */         resources = args[7];
/*      */       }
/*      */       try {
/*      */         String dbFileDir;
/* 1487 */         lclSynchronizer.initialize(serverID, dbName, userName, password, resources);
/*      */         
/* 1489 */         switch (opType) {
/*      */           
/*      */           case 1:
/* 1492 */             lclSynchronizer.importLangSpecificResources(outputDir, lang, groupList);
/*      */             break;
/*      */           
/*      */           case 2:
/* 1496 */             dbFileDir = String.valueOf(outputDir) + File.separator + lang + ".zip";
/* 1497 */             lclSynchronizer.synchronizeLangSpecificResources(outputDir, dbFileDir, lang, groupList);
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1504 */       } catch (Exception e) {
/*      */         
/* 1506 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void synchLocalizationDB(String serverID, String dbName, String lang, String outputDir, int opType, String outerUserName, String outerPassword) {
/*      */     String str1, str2;
/* 1515 */     JLbsLocalizationDBSynchronizer lclSynchronizer = new JLbsLocalizationDBSynchronizer(false);
/* 1516 */     String[] groupList = { "UN", "HR", "UNRP", "HRRP", 
/* 1517 */         "HELP", "SS" };
/*      */     
/* 1519 */     JLbsLocalizationDBSynchronizerHelper helper = new JLbsLocalizationDBSynchronizerHelper();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1525 */       str1 = helper.getDecryptedData(DEFAULT_USERNAME);
/* 1526 */       str2 = helper.getDecryptedData(DEFAULT_PASSWORD);
/*      */     }
/* 1528 */     catch (Exception e1) {
/*      */       
/* 1530 */       str1 = "";
/* 1531 */       str2 = "";
/* 1532 */       e1.printStackTrace();
/*      */     } 
/*      */     
/* 1535 */     if (!JLbsStringUtil.isEmpty(outerUserName)) {
/* 1536 */       str1 = outerUserName;
/*      */     }
/* 1538 */     if (!JLbsStringUtil.isEmpty(outerPassword)) {
/* 1539 */       str2 = outerPassword;
/*      */     }
/*      */     try {
/*      */       String dbFileDir;
/* 1543 */       lclSynchronizer.initialize(serverID, dbName, str1, str2, (String)null);
/*      */       
/* 1545 */       switch (opType) {
/*      */         
/*      */         case 1:
/* 1548 */           lclSynchronizer.importLangSpecificResources(outputDir, lang, groupList);
/*      */           break;
/*      */         
/*      */         case 2:
/* 1552 */           dbFileDir = String.valueOf(outputDir) + File.separator + lang + ".zip";
/* 1553 */           lclSynchronizer.synchronizeLangSpecificResources(outputDir, dbFileDir, lang, groupList);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1560 */     } catch (Exception e) {
/*      */       
/* 1562 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent e) {
/* 1570 */     Object o = e.getSource();
/* 1571 */     if (o != null)
/*      */     {
/* 1573 */       if (o == this.m_ComboEditOutputDir) {
/*      */         
/* 1575 */         switch (this.m_RadioBtnGrpOpType.getSelectedItemTag()) {
/*      */           
/*      */           case 1:
/* 1578 */             setOutputDirForCreation();
/*      */             break;
/*      */           
/*      */           case 2:
/* 1582 */             setOutputDirForZip();
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1589 */       } else if (o == this.m_CheckBoxDefaultUserName) {
/* 1590 */         performCredentialRelatedActions();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent e) {
/* 1597 */     Object o = e.getSource();
/* 1598 */     if (o != null)
/*      */     {
/* 1600 */       if (o == this.m_ButtonTestConnection) {
/* 1601 */         testConnection(true);
/* 1602 */       } else if (o == this.m_ButtonStartOperation) {
/* 1603 */         startImportOperation();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean testConnection(boolean fromConnectionTest) {
/* 1609 */     if (this.m_ListBoxConsole != null) {
/* 1610 */       this.m_ListBoxConsole.clearItems();
/*      */     }
/* 1612 */     collectConnectionInfo();
/*      */     
/* 1614 */     if (this.m_ImporterInfo.isConnectionTestApplicable()) {
/*      */       Connection resDBCon;
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1620 */         resDBCon = getResourceDBConnection(this.m_ImporterInfo.getServerID(), this.m_ImporterInfo.getDBName(), 
/* 1621 */             this.m_ImporterInfo.getUsername(), this.m_ImporterInfo.getPassword());
/*      */       }
/* 1623 */       catch (Exception e) {
/*      */         
/* 1625 */         resDBCon = null;
/*      */       } 
/*      */       
/* 1628 */       if (resDBCon == null) {
/* 1629 */         this.m_ListBoxConsole.addItem("Connection failed!");
/*      */       } else {
/*      */         
/* 1632 */         if (fromConnectionTest) {
/* 1633 */           this.m_ListBoxConsole.addItem("Connection succeeded.");
/*      */         }
/*      */         try {
/* 1636 */           resDBCon.close();
/*      */         }
/* 1638 */         catch (SQLException e) {
/*      */           
/* 1640 */           e.printStackTrace();
/*      */         } 
/* 1642 */         return true;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1647 */     else if (this.m_ListBoxConsole != null) {
/* 1648 */       this.m_ListBoxConsole.addItem("Current form content is not valid! Please fill all of the required fields.");
/*      */     } 
/*      */     
/* 1651 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void startImportOperation() {
/* 1656 */     if (this.m_ListBoxConsole != null) {
/* 1657 */       this.m_ListBoxConsole.clearItems();
/*      */     }
/* 1659 */     collectOperationDetails();
/*      */     
/* 1661 */     if (this.m_ImporterInfo.isOperationApplicable() && testConnection(false)) {
/*      */       
/*      */       try
/*      */       {
/* 1665 */         initialize(this.m_ImporterInfo.getServerID(), this.m_ImporterInfo.getDBName(), this.m_ImporterInfo.getUsername(), 
/* 1666 */             this.m_ImporterInfo.getPassword(), (String)null);
/*      */         
/* 1668 */         Thread importerThread = new Thread(new Runnable()
/*      */             {
/*      */               public void run()
/*      */               {
/*      */                 String outputDir, langName;
/* 1673 */                 int[] selectedGroupArray = JLbsLocalizationDBSynchronizer.this.m_CheckBoxGrpResourceGroup.getSelectedItemArray();
/* 1674 */                 String[] groupList = new String[selectedGroupArray.length];
/*      */                 
/* 1676 */                 for (int i = 0; i < selectedGroupArray.length; i++) {
/* 1677 */                   groupList[i] = JLbsLocalizationDBSynchronizer.this.m_CheckBoxGrpResourceGroup.getItems().getValueAtTag(selectedGroupArray[i]);
/*      */                 }
/* 1679 */                 switch (JLbsLocalizationDBSynchronizer.this.m_RadioBtnGrpOpType.getSelectedItemTag()) {
/*      */                   
/*      */                   case 1:
/* 1682 */                     JLbsLocalizationDBSynchronizer.this.importLangSpecificResources(JLbsLocalizationDBSynchronizer.this.m_ImporterInfo.getOutputDir(), JLbsLocalizationDBSynchronizer.this.m_ImporterInfo.getLang(), groupList);
/*      */                     break;
/*      */                   
/*      */                   case 2:
/* 1686 */                     outputDir = JLbsLocalizationDBSynchronizer.this.m_ImporterInfo.getOutputDir();
/* 1687 */                     langName = JLbsFileUtil.getFileName(outputDir, true);
/* 1688 */                     JLbsLocalizationDBSynchronizer.this.synchronizeLangSpecificResources(String.valueOf(outputDir) + File.separator + "..", JLbsLocalizationDBSynchronizer.this.m_ImporterInfo.getOutputDir(), 
/* 1689 */                         langName, groupList);
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*      */             });
/* 1698 */         importerThread.start();
/*      */       }
/* 1700 */       catch (Exception e)
/*      */       {
/* 1702 */         e.printStackTrace();
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1707 */     else if (this.m_ListBoxConsole != null) {
/* 1708 */       this.m_ListBoxConsole.addItem("Current form content is not valid! Please fill all of the required fields.");
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
/*      */   
/*      */   public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   private void collectConnectionInfo() {
/* 1734 */     this.m_ImporterInfo.setServerID(this.m_TextEditServerID.getText());
/* 1735 */     this.m_ImporterInfo.setDBName(this.m_TextEditDBName.getText());
/*      */     
/* 1737 */     if (this.m_CheckBoxDefaultUserName.isSelected()) {
/*      */       
/*      */       try
/*      */       {
/* 1741 */         JLbsLocalizationDBSynchronizerHelper helper = new JLbsLocalizationDBSynchronizerHelper();
/* 1742 */         this.m_ImporterInfo.setUsername(helper.getDecryptedData(DEFAULT_USERNAME));
/* 1743 */         this.m_ImporterInfo.setPassword(helper.getDecryptedData(DEFAULT_PASSWORD));
/*      */       }
/* 1745 */       catch (Exception e)
/*      */       {
/* 1747 */         e.printStackTrace();
/* 1748 */         this.m_ImporterInfo.setUsername(null);
/* 1749 */         this.m_ImporterInfo.setPassword(null);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1754 */       this.m_ImporterInfo.setUsername(this.m_TextEditUsername.getText());
/* 1755 */       this.m_ImporterInfo.setPassword(this.m_PasswordField.getPasswordText());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void collectOperationDetails() {
/* 1761 */     collectConnectionInfo();
/* 1762 */     this.m_ImporterInfo.setOutputDir(this.m_ComboEditOutputDir.getEditControl().getText());
/* 1763 */     this.m_ImporterInfo.setLang((String)this.m_ComboBoxLanguage.getSelectedItemValue());
/* 1764 */     this.m_ImporterInfo.setOperationType(this.m_RadioBtnGrpOpType.getSelectedItemTag());
/*      */     
/* 1766 */     ArrayList<String> selectedGroups = new ArrayList<>();
/* 1767 */     int[] selectedGroupTags = this.m_CheckBoxGrpResourceGroup.getSelectedItemArray();
/* 1768 */     if (selectedGroupTags != null)
/*      */     {
/* 1770 */       for (int i = 0; i < selectedGroupTags.length; i++)
/* 1771 */         selectedGroups.add(this.m_CheckBoxGrpResourceGroup.getItems().getValueAtTag(selectedGroupTags[i])); 
/*      */     }
/* 1773 */     this.m_ImporterInfo.setResourceGroups(selectedGroups);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setOutputDirForCreation() {
/* 1778 */     JFileChooser fc = new JFileChooser(this.m_LastSelectedDBDir);
/* 1779 */     fc.setFileSelectionMode(1);
/* 1780 */     fc.setDialogTitle("Choose Output Directory");
/* 1781 */     fc.setMultiSelectionEnabled(false);
/* 1782 */     String outputDir = null;
/*      */     
/* 1784 */     int retVal = fc.showOpenDialog(null);
/* 1785 */     if (retVal == 0) {
/*      */       
/* 1787 */       File file = fc.getSelectedFile();
/*      */ 
/*      */       
/*      */       try {
/* 1791 */         outputDir = file.getCanonicalPath();
/* 1792 */         if (!JLbsStringUtil.isEmpty(outputDir))
/*      */         {
/* 1794 */           this.m_LastSelectedDBDir = outputDir;
/* 1795 */           this.m_ComboEditOutputDir.getEditControl().setText(outputDir);
/*      */         }
/*      */       
/* 1798 */       } catch (IOException iOException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setOutputDirForZip() {
/* 1807 */     String zipFilePath = null;
/* 1808 */     JFileChooser fc = new JFileChooser(this.m_LastSelectedDBDir);
/* 1809 */     fc.setFileFilter(new FileFilter()
/*      */         {
/*      */           
/*      */           public boolean accept(File f)
/*      */           {
/* 1814 */             if (f.isDirectory()) {
/* 1815 */               return true;
/*      */             }
/* 1817 */             String ext = JLbsFileUtil.getExtension(f.getAbsolutePath());
/* 1818 */             return JLbsStringUtil.areEqual(ext, "zip");
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public String getDescription() {
/* 1824 */             return null;
/*      */           }
/*      */         });
/*      */     
/* 1828 */     fc.setFileSelectionMode(0);
/* 1829 */     fc.setDialogTitle("Choose DB Zip File");
/* 1830 */     fc.setMultiSelectionEnabled(false);
/*      */     
/* 1832 */     int retVal = fc.showOpenDialog(null);
/* 1833 */     if (retVal == 0) {
/*      */       
/* 1835 */       File file = fc.getSelectedFile();
/*      */ 
/*      */       
/*      */       try {
/* 1839 */         zipFilePath = file.getCanonicalPath();
/*      */       }
/* 1841 */       catch (IOException iOException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1847 */     if (!JLbsStringUtil.isEmpty(zipFilePath)) {
/*      */       
/* 1849 */       this.m_LastSelectedDBDir = JLbsFileUtil.getFileDir(zipFilePath);
/* 1850 */       this.m_ComboEditOutputDir.getEditControl().setText(zipFilePath);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void readLocalizationConfiguration(String outputDir) {
/*      */     try {
/* 1858 */       log("Loading " + outputDir + File.separator + ILocalizationConstants.PREF_REPLACE_LANGS_FILE_PATH);
/* 1859 */       LbsLocalizationConfig.load(String.valueOf(outputDir) + File.separator + ILocalizationConstants.PREF_REPLACE_LANGS_FILE_PATH);
/* 1860 */       if (LbsLocalizationConfig.ms_LocalizationLanguages != null)
/*      */       {
/* 1862 */         JLbsLocalizationUtil.ms_LocalizationLanguages = LbsLocalizationConfig.ms_LocalizationLanguages;
/* 1863 */         JLbsLocalizationUtil.extractPrefLangMaps();
/* 1864 */         JLbsLocalizationUtil.extractLangMaps();
/*      */       }
/*      */       else
/*      */       {
/* 1868 */         log("LbsLocalizationConfig.xml can not be found! Continue with default preferences.");
/*      */       }
/*      */     
/* 1871 */     } catch (Exception e) {
/*      */       
/* 1873 */       log("Exception reading LbsLocalizationConfig.xml! Continue with default preferences.");
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\JLbsLocalizationDBSynchronizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */