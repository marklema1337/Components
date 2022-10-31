/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import java.lang.reflect.Field;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ public class JLbsConstants
/*     */ {
/*  23 */   public static String OAUTH_SERVER_TO_SERVER_URL = "http://172.16.12.34:8080/logo/oauth2/service/token/server2server";
/*  24 */   public static String HR_SELF_SERVICE_URL = "http://localhost:32260/oauthclient/client/personList";
/*  25 */   public static String RECRUIT_PORTAL_URL = "http://localhost:32260/oauthclient/client/personList";
/*  26 */   public static String JUGNU_PORTAL = "http://www.vyapari.co.in";
/*  27 */   public static String MONGOCLIENTURI = "mongodb://localhost:27017";
/*  28 */   public static String DCTURL = "http://dct.logo-paas.com:8000";
/*  29 */   public static String CCURL = "http://dev-linux.logo-paas.com:6900";
/*  30 */   public static String IDMURL = "http://dev-linux.logo-paas.com:5100";
/*  31 */   public static String DYSURL = "http://dev-linux.logo-paas.com:8082/api/v2.0";
/*  32 */   public static String MENUSERVICEURL = "http://dev-linux.logo-paas.com:7000";
/*     */   public static final String MENUID = "fc3f318f-ba8b-3c15-82be-ce62a27712df&appId=97234717-4edc-4e16-9efe-a23fc8ba20a6";
/*     */   public static boolean DESKTOP_MODE = true;
/*     */   public static boolean SPRING_BOOT = false;
/*     */   public static boolean HR = false;
/*     */   public static final String J_HR = "J-HR";
/*     */   public static final boolean FALSE = false;
/*     */   public static final boolean GOKIDEBUG = false;
/*     */   public static final boolean TESTER_MODE = false;
/*     */   public static boolean DEBUG = false;
/*     */   public static boolean REPORTING_DEBUG = false;
/*  43 */   public static int LOGLEVEL = DEBUG ? 
/*  44 */     7 : 
/*  45 */     0;
/*     */   
/*     */   public static final String ms_HeaderMsgDigest = "MSGDGST";
/*     */   
/*     */   public static final String ms_XUIDialogs = "XUIDLG";
/*     */   
/*     */   public static final String ms_VarLanguageTag = "CLI-LANG";
/*     */   
/*     */   public static final String ms_VarCultureInfo = "CLI-CULTUREINFO";
/*     */   public static final String ms_VarCurrencies = "CLI-CURRENCIES";
/*     */   public static final String ms_VarQueryDisabledFormList = "CLI-QDFORMS";
/*     */   public static final String ms_VarControllerRegistry = "CLI-CONTROLLERREGISTRY";
/*     */   public static final String ms_SessionMessages = "CLI-MESSAGES";
/*     */   public static final String ms_Licencer = "CLI-LICENCER";
/*     */   public static final String ms_DevLicence = "CLI-DEV-LICENCE";
/*     */   public static final String ms_LicenceType = "CLI-LICENCE-TYPE";
/*     */   public static final String ms_FilterExtraListener = "CLI-FILTER-EXTRA-LISTENER";
/*     */   public static final String ms_CustQualifier = "Extensions.";
/*     */   public static final String TEMPORARY_PATH = "Temporary";
/*     */   public static final String ms_WarnDays = "CLI-WARN-DAYS";
/*     */   public static final String ms_AllowedDays = "CLI-ALLOWED-DAYS";
/*     */   public static final String ms_RevisionHistory = "CLI-REVISION_HISTORY";
/*     */   public static final String ms_DataExportRights = "CLI-DATA-EXPORT-RIGHTS";
/*     */   public static final String ms_DesktopManager = "CLI-DESKTOPMANAGER";
/*     */   public static final String BO_SOURCE_FORMNAME = "!_éXUI_FORM_NAMEé_!";
/*     */   public static final String BO_CLASSNAME = "!_éXUI_FORM_BO_CLASS_NAMEé_!";
/*     */   public static final String LOGITEM_BO_DESCRIPTION = "!_éLOGITEM_BO_DESCRIPTIONé_!";
/*     */   public static final String BO_FORM_XUIMODE = "!_éXUI_MODEé_!";
/*     */   public static final String LOGITEM_BO_REASON = "!_éLOGITEM_BO_REASONé_!";
/*     */   public static final String LOGITEM_BO_LANGUAGE = "!_éLOGITEM_BO_LANGUAGEé_!";
/*     */   public static final String LOGITEM_BO_CHANGES = "!_éLOGITEM_BO_CHANGESé_!";
/*     */   public static final String LOGITEM_BO_OLD_VALUES = "!_éLOGITEM_BO_OLD_VALUESé_!";
/*     */   public static final String LOGITEM_BO_NECESSARY_ITEM = "!_éLLOGITEM_BO_NECESSARY_ITEMé_!";
/*     */   public static final String LOGITEM_BO_CAN_BE_LOGGED_ITEMS = "!_éLOGITEM_BO_CAN_BE_LOGGED_ITEMSé_!";
/*     */   public static final String LOGITEM_STATE_CHANGE = "!_éLOGITEM_STATE_CHANGEé_!";
/*     */   public static final String LOGITEM_SPECIFIC_EXTENDED_FIELD = "!_éLOGITEM_SPECIFIC_EXTENDED_FIELDé_!";
/*     */   public static final String INVALID_SESSION = "!_éINVALID_SESSIONé_!";
/*     */   public static final String PORTAL_SESSION = "!_éPORTAL_SESSIONé_!";
/*     */   public static final String BO_SOURCE_EDITOR_FORMNAME = "!_éXUI_EDITOR_FORM_NAMEé_!";
/*     */   public static final String BO_SOURCE_EDITOR_FORMMODE = "!_éXUI_EDITOR_FORM_MODEé_!";
/*     */   public static final String BO_FORMNAME = "!_éXUI_NAMEé_!";
/*     */   public static final String RECORD_WARNING = "!_éRECORD_WARNINGé_!";
/*     */   public static final String USER_LDAP_SOURCE = "!_éUSER_LDAP_SOURCEé_!";
/*     */   public static final String USER_LABELS = "!_éUSER_LABELSé_!";
/*     */   public static final int APPID_HRSELFSERVICE = 100;
/*     */   public static final int APPID_RECRUITPORTAL = 200;
/*     */   public static final int APPID_JGUAR = 300;
/*     */   public static final int APPID_LCUSTMIZATION = 400;
/*     */   public static boolean ADD_MULTIPLE_COLUMNS = false;
/*     */   public static final String BOSTATUS_DATAFIELD = "Bostatus";
/*  95 */   public static String ms_CurrrentRestVersion = "1.0";
/*     */   
/*     */   private static String ms_BatchURI;
/*     */   
/*  99 */   public static int GRID_MAX_SELECTION_COUNT = 1000;
/*     */   
/*     */   public static boolean EnableTestOraHints = true;
/*     */   
/*     */   public static boolean DEBUG_DATA_CLIENT = false;
/*     */   
/*     */   public static boolean DEBUG_DATA_SERVER = false;
/*     */   
/*     */   public static boolean DEBUG_DATA_SHOW_SQL = false;
/*     */   
/*     */   public static boolean DEBUG_DATA_ON_ERROR_SHOW_SQL = false;
/*     */   
/*     */   public static boolean DEBUG_DATA_LOGGING_FACTORIES = false;
/*     */   
/*     */   public static boolean USE_ENTER_FOCUSTRAVERSE = true;
/*     */   
/*     */   public static boolean SKIP_LOCK = false;
/*     */   public static boolean FORCED_DB_CHUNK = false;
/*     */   public static boolean MONITOR_TABLES = false;
/*     */   public static boolean FORCED_USE_REPORTFILTER = false;
/*     */   public static boolean EASY_CUST_ENABLED = true;
/*     */   public static boolean SKIP_REPORT_COMPLETED_MESSAGE = true;
/*     */   public static boolean ms_ForceEnableUUID = false;
/*     */   private static boolean ENABLE_UUID = false;
/*     */   public static boolean DESIGN_TIME = false;
/*     */   public static final boolean PROFILING = true;
/*     */   public static boolean ENABLE_APP_CLOUD = false;
/*     */   public static boolean ENABLE_AUTOTABLEUPGRADE = false;
/*     */   public static boolean FORMPROPS_CAN_BE_RECORDED = true;
/* 128 */   public static String FORMPROPS_FOLDER = "FormProperties";
/*     */   
/*     */   public static boolean ENABLE_SAAS_DEDICATED = false;
/*     */   
/*     */   public static boolean EVENT_RECORDING = false;
/*     */   public static boolean TEST_PLAYING = false;
/*     */   public static boolean ms_inTestPlayer = false;
/*     */   public static boolean TESTPLAYER_USABILITY_CHECK = true;
/* 136 */   public static int m_CurrentBatchID = 0;
/*     */   
/*     */   public static boolean CONTROLLER_GENERATION_ON = false;
/*     */   
/*     */   public static boolean EMULATING_CONTROLLER_GENERATION_ON = false;
/*     */   public static boolean EMULATING_CONTROLLER_COMPLEX_ON = false;
/* 142 */   public static int GLOBAL_RESOURCES = -1000;
/*     */   
/* 144 */   public static String HAZELCASTSERVERID = null;
/*     */   
/*     */   public static boolean DISABLE_SESSION_WARNING = false;
/*     */   public static boolean PREF_SETTINGS_INITIALIZED = false;
/* 148 */   public static String CHAR_SET = "UTF-8";
/*     */   
/*     */   public static final int GRID_CHECKBOX_POPUP_RES = 10;
/*     */   
/*     */   public static boolean ENABLE_MULTI_COMPANY_CONTROLS = false;
/*     */   public static boolean USE_WEB_BROWSER = true;
/*     */   public static boolean PERIODLESS_CONTROLS = true;
/*     */   public static boolean HAS_LOGIN_SUPPORT = true;
/* 156 */   public static int BUFFER_SIZE = 1024;
/*     */   
/*     */   public static boolean FORCED_TRANSACTION = false;
/*     */   
/*     */   public static boolean ADVANCED_LOGIN = false;
/*     */   
/*     */   public static boolean GRID_ALWAYS_SCROLL_TOP = false;
/*     */   
/*     */   public static boolean ENABLE_NEW_XUI_DESIGN = true;
/*     */   
/*     */   public static final String ms_LangFromJSP = "LANGUAGE";
/*     */   
/*     */   public static final String ms_ParentSessionIDFromJSP = "PSESID";
/*     */   
/*     */   public static boolean REPORT_MONTH_INCREMENT = true;
/*     */   
/*     */   public static boolean ANALYZE_MUTATING_REFCOUNT = false;
/*     */   
/*     */   public static boolean ANALYZE_DOMAIN_LINKS = false;
/*     */   public static boolean XUI_RESOURCE_EDITING = false;
/*     */   public static boolean XUI_GRID_INLINE_FORMS = false;
/*     */   public static boolean XUI_MULTILANG = false;
/*     */   public static boolean ENABLE_MULTILANG_COL = false;
/*     */   public static boolean USE_LOCALIZATION_SERVICES = true;
/* 180 */   public static int REPORT_PRINTER_MINFONTSIZE = 0;
/*     */   
/*     */   public static boolean DB2TEST = false;
/*     */   
/*     */   public static boolean DISABLE_AUTO_PATCH = false;
/* 185 */   public static final Random ms_Random = new Random();
/*     */   
/*     */   public static boolean LOCK3 = false;
/*     */   
/*     */   public static final String DEFAULT_FONT_NAME = "Tahoma";
/*     */   public static final int DEFAULT_FONT_SIZE = 11;
/* 191 */   public static String APP_FONT_DEFAULT = "Tahoma";
/* 192 */   public static int APP_STYLE_DEFAULT = 0;
/* 193 */   public static int APP_SIZE_DEFAULT = 11;
/*     */   
/* 195 */   public static String APP_FONT = APP_FONT_DEFAULT;
/* 196 */   public static int APP_FONT_SIZE = APP_SIZE_DEFAULT;
/*     */   
/*     */   public static boolean ENABLE_MULTI_DOMAIN = false;
/*     */   
/*     */   public static boolean EMPTY_ORGUNIT = false;
/*     */   
/*     */   public static final String APP_DEF_LANG = "TRTR";
/*     */   
/*     */   public static boolean USE_BO_VALIDATOR = false;
/*     */   
/*     */   public static final String LICENCE_PACKAGE_STRING_SEPARATOR = "|";
/*     */   
/*     */   public static final String LICENCE_PACKAGE_DESC_EMPTY_STRING = "%empty%";
/*     */   
/*     */   public static boolean SEND_USERSTATS_ENABLED = false;
/*     */   public static final boolean USE_STREAMING_REMOTE_INVOKER = true;
/*     */   public static boolean ms_FromReportViewerDlg = false;
/* 213 */   public static int DUMMY_AUTH_ID = 999;
/*     */   
/*     */   public static boolean DB_INDEX_HINTS_ENABLED = false;
/*     */   
/*     */   public static boolean ADD_TABLES_WITH_SET_VALUES_TO_LDS_CHECK = false;
/*     */   
/*     */   public static boolean DISABLE_XUI_PROGRESS = false;
/*     */   
/*     */   public static boolean UPDATE_RIGHTS_STATE = false;
/*     */   
/*     */   public static final String ms_AppletParameters = "APPLET_PARAMETERS";
/*     */   
/*     */   public static final String ms_ShowToolBar = "SHOWTOOLBAR";
/*     */   
/*     */   public static final String ms_ShowLeftTabPages = "SHOWLEFTTABPAGES";
/*     */   
/*     */   public static final String ms_ShowRightTabPages = "SHOWRIGHTTABPAGES";
/*     */   
/*     */   public static final String ms_ShowNewsHtml = "SHOWNEWSHTML";
/*     */   
/*     */   public static final String ms_ShowStatusBar = "SHOWSTATUSBAR";
/*     */   
/*     */   public static final String ms_ShowChangeFirmMenu = "SHOWCHANGEFIRMMENU";
/*     */   
/*     */   public static final String ms_ShowChangePasswordMenu = "SHOWCHANGEPASSWORDMENU";
/*     */   
/*     */   public static final String ms_NewsHtmlPath = "NEWSHTMLPATH";
/*     */   public static final String ms_DisabledButtons = "DISABLEDBUTTONS";
/*     */   public static final String ms_IniUseDefaultFirm = "INI_USE_DEFAULT_FIRM";
/*     */   public static final String ms_IniLoginImage = "INI_LOGINIMAGE";
/*     */   public static final String ms_IniFirm = "INI_FIRM";
/*     */   public static final String ms_IniFirmName = "INI_FIRMNAME";
/*     */   public static final String ms_IniPeriod = "INI_PERIOD";
/*     */   public static final String ms_IniSubCompanty = "INI_SUBCOMPANY";
/*     */   public static final String ms_IniLanguage = "INI_LANG";
/*     */   public static final String ms_IniTitle = "INI_TITLE";
/*     */   public static final String ms_IniShowVersion = "INI_SHOWVERSION";
/*     */   public static final String ms_IniFileName = "INI_FILENAME";
/*     */   public static final String ms_IniJarFiles = "INI_JARFILES";
/*     */   public static final String ms_IniMainForm = "INI_MAINFORM";
/*     */   public static final String ms_IniWidth = "INI_WIDTH";
/*     */   public static final String ms_IniHeight = "INI_HEIGHT";
/*     */   public static final String ms_StartupForm = "STARTUPFORM";
/*     */   public static final String ms_StartupJAR = "STARTUPJAR";
/*     */   public static final String ms_WebAppName = "WEBAPPNAME";
/*     */   public static final String ms_TerminatePage = "TERMINATEPAGE";
/*     */   public static final String ms_UseCache = "USECACHE";
/*     */   public static final String ms_UseObjectSchemaCache = "USEOBJECTSCHEMACACHE";
/*     */   public static final String ms_StartChronometer = "STARTCHRONOMETER";
/*     */   public static final String ms_PrintSystemProperties = "PRINTSYSTEMPROPERTIES";
/*     */   public static final String ms_ExtUserName = "EXT_USERNAME";
/*     */   public static final String ms_ExtPassword = "EXT_PASSWORD";
/*     */   public static final String REVISION_DELIMITER = "@_@";
/* 266 */   public static final Calendar MIN_DATE = new GregorianCalendar(1900, 0, 1);
/* 267 */   public static final Timestamp MIN_TIMESTAMP = new Timestamp(0L);
/*     */   
/*     */   public static final String SKIP_SERVER_EVENTS = "SKIP_SERVER_EVENTS";
/*     */   public static final String FAST_DOCUMENT_NUMBERS = "FAST_DOCUMENT_NUMBERS";
/*     */   public static final String FAST_DOCUMENT_NUMBERS_NODE_PREFIX = "FAST_DOCUMENT_NUMBERS_NODE_PREFIX";
/*     */   public static boolean PLATFORM_SILENT = true;
/*     */   public static boolean MULTI_THREAD_SUPPORT = false;
/* 274 */   public static ConcurrentHashMap<Integer, String> TITLE_PART_0 = new ConcurrentHashMap<>();
/* 275 */   public static ConcurrentHashMap<Integer, String> TITLE_PART_1 = new ConcurrentHashMap<>();
/*     */   
/*     */   public static boolean SKIP_CACHE = false;
/*     */   
/*     */   public static boolean LPT_MODE = false;
/*     */   
/*     */   public static boolean DISABLE_PROP_LISTENER = false;
/*     */   
/*     */   public static boolean PROJECT_SET = false;
/*     */   public static boolean USE_GL_QUEUE = true;
/*     */   public static boolean EMULATING_BATCH_SYNC_START = false;
/*     */   public static boolean DATA_RECOVERY_ENABLED = true;
/* 287 */   public static long EDITOR_SAVE_PERIOD = 50000L;
/*     */   
/* 289 */   public static long STATISTICS_PERIOD = 50000L;
/*     */   
/* 291 */   public static int OPEN_WINDOW_DELAY_MIN = 3;
/*     */   
/* 293 */   public static int KEY_DISPATCHER_QUEUE_SIZE = 50;
/*     */   
/*     */   public static boolean ALLOW_INVALID_WF_LOGIN = true;
/*     */   
/*     */   public static final String EDEFTER = "EDEFTER_MODE";
/*     */   public static final String EDEFTER_ONEPIN = "EDEFTER_ONEPIN";
/* 299 */   public static final Object EDEFTER_PINANDCERTSELECTION = "EDEFTER_PINANDCERTSELECTION";
/*     */ 
/*     */   
/*     */   public static boolean VK_RIGHT_CARET_POSITION = false;
/*     */ 
/*     */   
/*     */   public static boolean SHOW_TABLE_UPGRADE_ERRORS = false;
/*     */   
/*     */   public static boolean ONLY_LOG_OBJECTS_FROM_UI = false;
/*     */   
/*     */   public static boolean REDIRECT_REPORT_TO_MAINNODE = false;
/*     */   
/*     */   public static boolean REDIRECT_BATCH_TO_MAINNODE = false;
/*     */   
/*     */   public static boolean DONT_THROW_CONTROL_NOT_ACTIVE = true;
/*     */   
/*     */   private static boolean RUNNING_IN_SERVER_SIDE = false;
/*     */   
/*     */   public static boolean ENABLE_WEB_CLIENT_CELL_ID = false;
/*     */   
/*     */   public static boolean DONT_CREATE_STD_ACCOUNTS = false;
/*     */   
/*     */   public static boolean SURVEY_MANAGEMENT = false;
/*     */   
/*     */   public static boolean WAIT_FORCONNECTION = false;
/*     */ 
/*     */   
/*     */   public static String getBatchURI() {
/* 327 */     return ms_BatchURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setBatchURI(String batchURI) {
/* 332 */     if (batchURI != null && batchURI.length() != 0 && !batchURI.endsWith("/"))
/* 333 */       batchURI = String.valueOf(batchURI) + "/"; 
/* 334 */     ms_BatchURI = batchURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public static int VERSION_MAJOR = 0;
/* 345 */   public static int VERSION_MINOR = 3;
/* 346 */   public static int VERSION_RELEASE = 5;
/* 347 */   public static int VERSION_BUGFIX = 0;
/*     */   
/* 349 */   public static int VERSION_TRIGGER = 1003;
/*     */   
/* 351 */   public static String VERSION_STR = "v" + VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_RELEASE + "." + VERSION_BUGFIX;
/* 352 */   public static String VERSION_EDEFTER = "1.0";
/*     */ 
/*     */   
/* 355 */   public static String ORG_VERSION_STR = VERSION_STR;
/* 356 */   public static String ORG_VERSION_EDEFTER = VERSION_EDEFTER;
/*     */   
/* 358 */   public static int PIVOT_CHUNK_SIZE = 500;
/* 359 */   public static int PIVOT_CHUNK_COUNT = 100;
/*     */   
/* 361 */   public static String WEB_PROJECT_NAME = "LogoERP";
/*     */   public static boolean USE_LOCAL_HELP_FILES = false;
/*     */   public static boolean SMS_SERVICE_ENABLED = false;
/* 364 */   public static String HELP_SERVER_URL = "http://logo.com.tr/jguar/";
/*     */   
/* 366 */   public static String SYSUSER_NOTFOUND_FOLDER = "lUserNotFound";
/*     */   
/*     */   public static boolean DISABLE_OLD_WF = true;
/*     */   
/*     */   public static boolean ENABLE_MONITORING_SQL = false;
/* 371 */   public static String UN_FORM_OBJECT_MAP_FILENAME = "UNFormObjectMap.fom";
/* 372 */   public static String FORM_OBJECT_MAP_FILENAME = "FormObjectMap.fom";
/*     */   
/*     */   public static boolean BATCH_COMPLETE_NOTIFICATION = true;
/*     */   
/*     */   public static boolean REPORT_COMPLETE_NOTIFICATION = true;
/*     */   
/*     */   public static boolean SERVER_SIDE_PDF_PASS = true;
/*     */   
/*     */   public static boolean HR_SERVER_SIDE_PDF_PASS = true;
/*     */   
/*     */   public static boolean FROM_TEMPLATE_SCRIPT = false;
/*     */   
/*     */   public static boolean DEBUG_RIGHT_TO_LEFT = true;
/* 385 */   public static int DOCUMENT_CATALOG_MAX_SIZE_KB = 1000;
/*     */ 
/*     */   
/*     */   public static boolean LDAP_AUTOLOGIN = true;
/*     */ 
/*     */   
/*     */   public static boolean LDAP_STORE_PASS = false;
/*     */   
/*     */   public static boolean LDAP_SERVERAUTOLOGIN = false;
/*     */   
/*     */   public static boolean OAUTH_LOGIN = false;
/*     */   
/*     */   public static boolean ENABLE_AZURE_USER_TRANSFER = false;
/*     */   
/*     */   public static boolean ENABLEONPREMIDM = false;
/*     */   
/* 401 */   public static int OAUTH_TYPE = 0;
/*     */   
/*     */   public static String TENANTID;
/*     */   
/*     */   public static String CLIENTID;
/*     */   
/*     */   public static String CLIENTSECRET;
/*     */   
/* 409 */   public static String SECURITY_INFO_ADDRESS = "http://dev-linux.logo-paas.com:5100/legacy/securityinfo";
/* 410 */   public static String STS_ADDRESS = "http://dev-linux.logo-paas.com:5100/legacy/sts/api";
/* 411 */   public static String STS_TOKEN_PARSER_ADDRESS = "http://dev-linux.logo-paas.com:5100/legacy/tokenparser";
/*     */   
/*     */   public static String CLIENTID_AYNA;
/*     */   
/*     */   public static String CLIENTSECRET_AYNA;
/*     */   
/*     */   public static String SECURITY_INFO_ADDRESS_AYNA;
/*     */   
/*     */   public static String STS_ADDRESS_AYNA;
/*     */   public static String STS_TOKEN_PARSER_ADDRESS_AYNA;
/* 421 */   public static String IS_REVISION = "IsRevision";
/*     */   
/*     */   public static final String MODIFIERKEY_PRESSED = "ModifierKeyPressed";
/*     */   
/*     */   public static final String PARENT_POPUP_TAG = "ParentPopupTag";
/*     */   
/*     */   public static final String APP_IS_CLIENT = "lbs_is_client";
/*     */   public static final String LBS_TRUE = "1";
/*     */   public static final String LBS_FALSE = "0";
/* 430 */   public static int REPORT_IDBASE = 0;
/*     */   
/*     */   public static final String PERMISSION_FORM = "XFXFPermissionForm.jfm";
/*     */   
/*     */   public static final String VIEWPANE_FORM = "ADXFViewPane.jfm";
/*     */   
/*     */   public static final String ms_ContractHasRevision = "has-revision";
/*     */   public static final String ms_ContractRevisionWithApproval = "revision-with-approval";
/*     */   public static final int DSIGNATURE_LIST = 91235;
/*     */   public static boolean BUFFERED_REPORT = true;
/*     */   public static boolean DISABLE_MULTI_THREADED_UPGRADE = false;
/*     */   public static boolean SHOW_SEARCH_ROW = false;
/*     */   public static boolean SHOW_REPORT_DIALOG = false;
/*     */   public static boolean WINDOW_MODE = false;
/* 444 */   public static int REFRESH_TIME_INTERVAL = 30;
/* 445 */   public static int MIN_REFRESH_TIME = 30;
/*     */   
/* 447 */   public static String USER_TCKN_PREF = "TCKN";
/*     */   
/*     */   public static boolean DISABLE_EH_CACHE = false;
/*     */   
/*     */   public static final boolean ENABLE_HAZELCAST = true;
/*     */   
/*     */   public static final boolean ENABLE_HAZELCAST_LOCK = true;
/*     */   public static final boolean ENABLE_HAZELCAST_SEQ = false;
/*     */   public static final boolean ENABLE_HAZELCAST_DOCNUM = true;
/*     */   public static final boolean ENABLE_HAZELCAST_USER = true;
/*     */   public static boolean ENABLE_ELASTIC = true;
/*     */   public static boolean ENABLE_DOCCONTAINER_AYNA = false;
/* 459 */   public static int HC_LOGREF_SAVE_PERIOD = 3000;
/*     */   public static boolean ENABLE_REPORT_LINKS = true;
/* 461 */   public static int REPORT_QUERY_TIMEOUT = 0;
/* 462 */   public static int TRANSACTIONAL_SERVICE_TIMEOUT = 45;
/*     */   public static final String QUERY_TIMEOUT_KEY = "ReportQueryTimeOut";
/* 464 */   public static int MAX_CUSTOMIZATION_COUNT = 100;
/*     */   
/* 466 */   public static int LIMIT_WARNING_THRESHOLD = 10;
/*     */   
/*     */   public static boolean ENABLE_MULTICOMPANY = false;
/*     */   
/*     */   public static boolean DSIG_ENABLE_TESTROOTS = false;
/*     */   
/*     */   public static boolean DOWNLOAD_NEW_VERSION = false;
/*     */   
/*     */   public static boolean HIDE_CREDENTIALS = false;
/*     */   
/* 476 */   public static int FORM_DOWNLOAD_TRESHOLD = 20;
/*     */   
/* 478 */   public static String BASE_HELP_URL = "https://docs.logo.com.tr/display/j3erpkd";
/*     */   
/* 480 */   public static String HR_HELP_URL = "https://docs.logo.com.tr/display/j3hrkd";
/*     */   
/* 482 */   public static String BASE_HELP_URL_ENUS = "https://docs.logo.com.tr/display/J3ERPENG";
/*     */   
/*     */   public static boolean ENABLE_POLARIS_HELP = false;
/*     */   
/* 486 */   public static String POLARIS_URL = "https://polaris.logo.cloud/product/j-platform";
/*     */   
/* 488 */   public static String SALESPORTAL_URL = "http://erpservisi-test.logo.cloud";
/*     */   
/* 490 */   public static String TCMB_URL = "https://tcmb.gov.tr/kurlar/";
/*     */   
/*     */   public static boolean HELP_WITH_MODULES = false;
/*     */   
/*     */   public static boolean OPTIMIZE_XUI_REQUEST = true;
/*     */   
/*     */   public static boolean OPTIMIZE_BO_READ = true;
/*     */   
/*     */   public static boolean EXCHANGE_USER_VALIDATION_WITH_USERNAME = false;
/*     */   
/* 500 */   public static String AUTH_EXCLUDED_REST_URLS = "/logo/restservices/rest/public;/logo/restservices/hr/rest/public;/logo/restservices/rest/apitestercloud";
/*     */   
/* 502 */   public static String EXTERNAL_WF_REST_URLS = "";
/*     */   
/*     */   public static boolean OPTIMIZE_BO_CLONE = true;
/*     */   
/*     */   public static boolean COMPLEX_RECINFO_FORALL = false;
/*     */   
/* 508 */   public static String ms_ProductType = null;
/*     */   
/* 510 */   public static int NOTIFY_MESSAGES_QUERY_TIMER = 30000;
/*     */   
/* 512 */   public static String WF_SUBSCRIBE_URL = "";
/* 513 */   public static String WF_LOGIN_URL = "";
/* 514 */   public static String WF_STATUS_URL = "";
/* 515 */   public static int IDP_PROVIDER_TYPE = 7;
/* 516 */   public static String WF_STATUS_BASE_URL = "";
/* 517 */   public static String WF_USERNAME = "";
/*     */   
/*     */   public static boolean DISABLE_MULTI_SESSION = false;
/*     */   
/* 521 */   public static String ACTIVE_DIRECTORY_LOGIN_URL = "";
/*     */   
/*     */   public static boolean WAIT_FOR_APPLY_PREFS = false;
/*     */   
/* 525 */   public static String PROP_VALUE_MAX_PURGATORY_SIZE = "10000";
/*     */   
/*     */   public static boolean CONTROL_LOGS_ENABLED = false;
/*     */   
/*     */   public static boolean ESERVICES_LOGS_ENABLED = true;
/*     */   
/*     */   public static boolean ENABLE_USER_STATS = false;
/*     */   
/*     */   public static boolean PRODUCTION_BETA_FEATURES = false;
/*     */   
/*     */   public static boolean LEDGER_DECLARATION_SYSTEM = true;
/*     */   
/*     */   public static boolean PAYER_DEVELOPMENT_ENABLED = false;
/*     */   
/*     */   public static boolean IMPORT_ADDRESS_DATA = true;
/*     */   
/* 541 */   public static int IMPORT_ADDRESS_DATA_OPTION = 0;
/*     */   
/*     */   public static boolean IMPORT_SYSTEM_DATA = true;
/*     */   
/*     */   public static boolean IMPORT_PAYMENT_TYPE = true;
/*     */   
/*     */   public static boolean IMPORT_TRANSFER_TYPE_CODES = true;
/*     */   
/* 549 */   public static int AUTO_CREATE_FIRM_NR = 0;
/*     */   
/*     */   public static final int LCN_OPTION_INSERT = 1;
/*     */   
/*     */   public static final int LCN_OPTION_UPDATE = 2;
/*     */   
/*     */   public static final int LCN_OPTION_DELETE = 4;
/*     */   
/*     */   public static final int LOG_DOES_NOT_HAVE_LREF = -1;
/*     */   
/*     */   public static final int LOG_LREF_BATCH_OPERATION = -2;
/*     */   
/*     */   public static final String LOG_NECESSARY_ITEM_VALUE = "ADDED";
/*     */   
/*     */   public static final int EMULATING_CONTROLLER_REGISTERABLE = 1;
/*     */   
/*     */   public static final int EMULATING_CONTROLLER_UNREGISTERABLE = 0;
/*     */   
/*     */   public static boolean ENABLE_LABEL_FILTER = false;
/*     */   
/* 569 */   public static String SWAGGER_LANGUAGE = "TRTR";
/*     */   
/*     */   public static boolean SHOW_ALL_FIELDS_ON_SWAGGER = false;
/*     */   
/*     */   public static boolean REST_VERSION_NECESSARY_FOR_SWAGGER = true;
/*     */   
/*     */   public static boolean CREATE_SWAGGER = true;
/*     */   
/*     */   public static boolean CREATE_EMULATING_WITH_JAXRS = false;
/*     */   
/*     */   public static boolean EMULATING_CONTROLLER_DEPRECATED = false;
/*     */   
/*     */   public static boolean ENABLE_DMS_MOBILE_USER = true;
/*     */   
/* 583 */   public static int PRODUCT_TYPE = 0;
/*     */   
/*     */   public static boolean ENABLE_SERVER_CLASS_INSTRUMENTATION = false;
/*     */   
/*     */   public static boolean FORCE_ORGUNIT_DBAUTH = false;
/*     */   
/* 589 */   public static ThreadLocal<Boolean> ms_ReportPreviewFullScreen = new ThreadLocal<Boolean>()
/*     */     {
/*     */       protected Boolean initialValue()
/*     */       {
/* 593 */         return Boolean.FALSE;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static void setReportPreviewFullScreen(boolean value) {
/* 599 */     ms_ReportPreviewFullScreen.set(new Boolean(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isReportPreviewFullScreen() {
/* 604 */     Boolean result = ms_ReportPreviewFullScreen.get();
/* 605 */     if (result != null)
/* 606 */       return result.booleanValue(); 
/* 607 */     return false;
/*     */   }
/*     */   
/* 610 */   public static final ThreadLocal<Boolean> ms_ReadLogEnabled = new ThreadLocal<Boolean>()
/*     */     {
/*     */       protected Boolean initialValue()
/*     */       {
/* 614 */         return Boolean.TRUE;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static boolean isReadLogEnabled() {
/* 620 */     Boolean result = ms_ReadLogEnabled.get();
/* 621 */     if (result != null)
/* 622 */       return result.booleanValue(); 
/* 623 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setReadLogEnabled(boolean value) {
/* 628 */     ms_ReadLogEnabled.set(new Boolean(value));
/*     */   }
/*     */   
/* 631 */   public static ThreadLocal<Boolean> ms_DontResetUUIDs = new ThreadLocal<Boolean>()
/*     */     {
/*     */       protected Boolean initialValue()
/*     */       {
/* 635 */         return Boolean.FALSE;
/*     */       }
/*     */     };
/*     */   
/*     */   public static Properties PRODUCT_VERSION_PROPERTIES;
/*     */   public static boolean isSpringBoot = false;
/*     */   
/*     */   public static boolean isResetUUIDs() {
/* 643 */     return !((Boolean)ms_DontResetUUIDs.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDontResetUUIDs(Boolean dontReset) {
/* 648 */     ms_DontResetUUIDs.set(dontReset);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isForceEnableUUID() {
/* 653 */     return ms_ForceEnableUUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setForceEnableUUID(boolean forceEnableUUID) {
/* 658 */     ms_ForceEnableUUID = forceEnableUUID;
/* 659 */     ENABLE_UUID = ms_ForceEnableUUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEnableUUID() {
/* 664 */     return ENABLE_UUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getProductType() {
/* 669 */     if (ms_ProductType != null) {
/* 670 */       return ms_ProductType;
/*     */     }
/*     */     try {
/* 673 */       Class<?> appInfoCls = Class.forName("com.lbs.application.info.LbsApplicationInfo");
/*     */       
/* 675 */       Object instance = appInfoCls.newInstance();
/* 676 */       if (instance != null)
/*     */       {
/* 678 */         Field field = appInfoCls.getDeclaredField("INSTALL_PRODUCT_GROUP");
/* 679 */         ms_ProductType = (String)field.get(instance);
/*     */       }
/*     */     
/* 682 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 686 */     return ms_ProductType;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkAppCloud() {
/* 691 */     return ENABLE_APP_CLOUD;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkJugnu() {
/* 696 */     return "0390".equals(getProductType());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkSAAS() {
/* 701 */     return "0601".equals(getProductType());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkSAASDedicated() {
/* 706 */     return ENABLE_SAAS_DEDICATED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkDCT() {
/* 712 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkXUIResEditForSaas() {
/* 717 */     return (checkSAAS() && checkAppCloud() && XUI_RESOURCE_EDITING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRunningServerSide(Object component) {
/* 728 */     return checkRunningServerSide(component);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean checkRunningServerSide(Object component) {
/* 733 */     return JLbsSwingUtilities.isWebClientThread();
/*     */   }
/*     */   
/*     */   public static boolean FORCE_PREF_TYPE_GENERAL = false;
/* 737 */   public static int VAADIN_TIMER_POOL_SIZE = 50;
/*     */   public static final boolean IGNORE_CUSTOM_SCHEMA_FORUPGRADE = false;
/*     */   
/*     */   public static boolean isForcePrefTypeGeneral() {
/* 741 */     return FORCE_PREF_TYPE_GENERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isIgnoreCustomSchemaForUpgrade() {
/* 748 */     return false;
/*     */   }
/*     */   
/* 751 */   public static String SERVER_ADDRESS_TEMPLATE = "{scheme}://{hostname}:{port}";
/*     */   
/* 753 */   public static String OKTA_CLIENTID = "";
/* 754 */   public static String OKTA_CLIENTSECRET = "";
/* 755 */   public static String OKTA_SCOPELIST = "";
/* 756 */   public static String OKTA_SERVICEADDRESS = "";
/* 757 */   public static String OKTA_OIDCID = "";
/* 758 */   public static String OKTA_NUMCHL_TIMEOUT = "";
/* 759 */   public static String OKTA_REDIRECT_URI = "";
/*     */   
/* 761 */   public static String EXPLICIT_PORT_NUMBER = "";
/*     */   
/* 763 */   public static String MAIL_TLS = "";
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */