/*     */ package com.lbs.localization;public interface ILocalizationConstants {
/*     */   public static final String RESGROUP_MISC = "_misc_";
/*     */   public static final int INTERVAL_INSERTION_RESOURCE = 10;
/*     */   public static final int INTERVAL_INSERTION_RESOURCE_ITEM = 100;
/*     */   public static final int INTERVAL_SYNCHRONIZATION_RESOURCE = 1;
/*     */   public static final int CHANGE_TYPE_UPDATE = 1;
/*     */   public static final int CHANGE_TYPE_DELETE = 2;
/*     */   public static final int OPERATION_TYPE_CREATE = 1;
/*     */   public static final int OPERATION_TYPE_SYNCHRONIZE = 2;
/*     */   public static final int SYNCH_TYPE_NONE = 0;
/*     */   public static final int SYNCH_TYPE_REMOTE_TO_LOCAL = 1;
/*     */   public static final int SYNCH_TYPE_LOCAL_TO_REMOTE = 2;
/*     */   public static final int EXISTANCE_TYPE_LOCAL = 0;
/*     */   public static final int EXISTANCE_TYPE_REMOTE = 1;
/*     */   public static final int EXISTANCE_TYPE_BOTH = 2;
/*     */   
/*     */   public enum HelpFields {
/*  18 */     helpId, documentName, documentTitle, documentBody, documentType, time;
/*     */   }
/*     */   
/*     */   public enum MessageFields {
/*  22 */     key, constantId, module, type, listId, stringTag, resourceGroup, buttons, defaultButton, time;
/*     */   }
/*     */   
/*     */   public enum ResourceFields {
/*  26 */     resourceNumber, ownerProduct, contents, time, description;
/*     */   }
/*     */   
/*     */   public enum Catalogs {
/*  30 */     resources, messages, helps;
/*     */   }
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
/*  54 */   public static final byte[] DEFAULT_USERNAME = new byte[] { -84, -19, 5, 116, 8, 82, 69, 65, 68, 79, 78, 76, 89 };
/*  55 */   public static final byte[] DEFAULT_PASSWORD = new byte[] { -84, -19, 5, 116, 9, 82, 79, 50, 54, 50, 76, 79, 71, 79 };
/*     */   
/*     */   public static final int RES_GRP_TYPE_GNRL = 0;
/*     */   
/*     */   public static final int RES_GRP_TYPE_UN = 1;
/*     */   
/*     */   public static final int RES_GRP_TYPE_UNRP = 2;
/*     */   
/*     */   public static final int RES_GRP_TYPE_HR = 3;
/*     */   
/*     */   public static final int RES_GRP_TYPE_HRRP = 4;
/*     */   
/*     */   public static final int RES_GRP_TYPE_HELP = 5;
/*     */   public static final int RES_GRP_TYPE_SS = 6;
/*     */   public static final int RES_MULTIPLICITY_FACTOR = 1000000;
/*     */   public static final String RES_GRP_NAME_UN = "UN";
/*     */   public static final String RES_GRP_NAME_UNRP = "UNRP";
/*     */   public static final String RES_GRP_NAME_HR = "HR";
/*     */   public static final String RES_GRP_NAME_HRRP = "HRRP";
/*     */   public static final String RES_GRP_NAME_HELP = "HELP";
/*     */   public static final String RES_GRP_NAME_SS = "SS";
/*     */   public static final String RES_DB_NAME_PAAS = "RESEDIT_P";
/*     */   public static final String RES_GRP_NAME_PAAS_PLAT = "PLATFORM";
/*     */   public static final String RES_GRP_NAME_PAAS_GRC = "GRC";
/*     */   public static final String RES_GRP_NAME_PAAS_GRCREP = "GRCREP";
/*     */   public static final String RES_GRP_NAME_PAAS_HR = "HR";
/*     */   public static final String RES_GRP_NAME_PAAS_HRREP = "HRREP";
/*     */   public static final String RES_GRP_NAME_PAAS_ERP = "ERP";
/*     */   public static final String RES_GRP_NAME_PAAS_ERPREP = "ERPREP";
/*     */   public static final String RES_GRP_NAME_PAAS_SOHO = "SOHO";
/*     */   public static final String RES_GRP_NAME_PAAS_SOHOREP = "SOHOREP";
/*     */   public static final String TABLE_NAME_AREG = "RE_ARABICEG";
/*     */   public static final String TABLE_NAME_ARJO = "RE_ARABICJO";
/*     */   public static final String TABLE_NAME_ARSA = "RE_ARABICSA";
/*     */   public static final String TABLE_NAME_AZAZ = "RE_AZERBAIJANIAZ";
/*     */   public static final String TABLE_NAME_BGBG = "RE_BULGARIANBG";
/*     */   public static final String TABLE_NAME_DEDE = "RE_GERMANDE";
/*     */   public static final String TABLE_NAME_ENUS = "RE_ENGLISHUS";
/*     */   public static final String TABLE_NAME_FAIR = "RE_PERSIANIR";
/*     */   public static final String TABLE_NAME_FRFR = "RE_FRENCHFR";
/*     */   public static final String TABLE_NAME_KAGE = "RE_GEORGIANGE";
/*     */   public static final String TABLE_NAME_RURU = "RE_RUSSIANRU";
/*     */   public static final String TABLE_NAME_SQAL = "RE_ALBANIANKV";
/*     */   public static final String TABLE_NAME_THTH = "RE_THAITH";
/*     */   public static final String TABLE_NAME_TKTM = "RE_TURKMENTM";
/*     */   public static final String TABLE_NAME_TRTR = "RE_TURKISHTR";
/*     */   public static final String TABLE_NAME_VIVN = "RE_ENGLISHUS";
/*     */   public static final String TABLE_NAME_ZHCN = "RE_CHINESECN";
/*     */   public static final String TABLE_NAME_DEFAULT = "";
/* 104 */   public static final String TABLE_NAME_NOSELECT = null;
/*     */   
/*     */   public static final String LANG_NAME_AREG = "AREG";
/*     */   
/*     */   public static final String LANG_NAME_ARJO = "ARJO";
/*     */   
/*     */   public static final String LANG_NAME_ARSA = "ARSA";
/*     */   
/*     */   public static final String LANG_NAME_AZAZ = "AZAZ";
/*     */   
/*     */   public static final String LANG_NAME_BGBG = "BGBG";
/*     */   
/*     */   public static final String LANG_NAME_DEDE = "DEDE";
/*     */   
/*     */   public static final String LANG_NAME_ENUS = "ENUS";
/*     */   
/*     */   public static final String LANG_NAME_FAIR = "FAIR";
/*     */   
/*     */   public static final String LANG_NAME_FRFR = "FRFR";
/*     */   
/*     */   public static final String LANG_NAME_KAGE = "KAGE";
/*     */   
/*     */   public static final String LANG_NAME_RURU = "RURU";
/*     */   
/*     */   public static final String LANG_NAME_SQAL = "SQAL";
/*     */   
/*     */   public static final String LANG_NAME_THTH = "THTH";
/*     */   
/*     */   public static final String LANG_NAME_TKTM = "TKTM";
/*     */   
/*     */   public static final String LANG_NAME_TRTR = "TRTR";
/*     */   
/*     */   public static final String LANG_NAME_VIVN = "VIVN";
/*     */   
/*     */   public static final String LANG_NAME_ZHCN = "ZHCN";
/*     */   
/*     */   public static final String LANG_NAME_NEUT = "NEUT";
/*     */   
/*     */   public static final String LANG_NAME_ENIN = "ENIN";
/*     */   
/*     */   public static final String LANG_NAME_DEFAULT = "";
/*     */   
/*     */   public static final String DIR_LANG_ARABIC = "Arabic";
/*     */   
/*     */   public static final String DIR_LANG_FARSI = "Farsi";
/*     */   
/*     */   public static final String DIR_CUSTOMIZATION = "Customization";
/*     */   
/*     */   public static final String XML_FILE_NAME_LOCALIZATION_TRACE = "LocalizationTrace.xml";
/*     */   
/*     */   public static final String XML_ELEMENT_LOCALIZATION_TRACE = "LocalizationTrace";
/*     */   
/*     */   public static final String XML_ELEMENT_FORMS = "Forms";
/*     */   
/*     */   public static final String XML_ELEMENT_FORM = "Form";
/*     */   
/*     */   public static final String XML_ELEMENT_GROUPS = "Groups";
/*     */   
/*     */   public static final String XML_ELEMENT_GROUP = "Group";
/*     */   
/*     */   public static final String XML_ELEMENT_LIST = "List";
/*     */   
/*     */   public static final String XML_ATTRIBUTE_ID = "id";
/*     */   
/*     */   public static final String XML_ATTRIBUTE_FORMS = "usedForms";
/*     */   
/*     */   public static final String XML_ATTRIBUTE_NAME = "name";
/*     */   
/*     */   public static final String RES_IMPORTER_VERSION = "1.0";
/*     */   
/*     */   public static final String DIR_PREFIX_EXTRACTION = "EmbeddedDB_";
/*     */   public static final String REPLACE_VALUE_LIST = "%valueList%";
/*     */   public static final String REPLACE_TABLE = "%table%";
/*     */   public static final String REPLACE_RESNR = "%resNr%";
/*     */   public static final String REPLACE_OWNER = "%ownerProd%";
/*     */   public static final String REPLACE_NEW_RESNR = "%newResNr%";
/*     */   public static final String REPLACE_RESGRP = "%resGrp%";
/*     */   public static final String REPLACE_SYNCH_TIME = "%synchTime%";
/*     */   public static final String REPLACE_RESNR_START = "%resNrStart%";
/*     */   public static final String REPLACE_RESNR_END = "%resNrEnd%";
/*     */   public static final String REPLACE_POSITIVE_RESNR_START = "%posResNrStart%";
/*     */   public static final String REPLACE_POSITIVE_RESNR_END = "%posResNrEnd%";
/*     */   public static final String REPLACE_NEGATIVE_RESNR_START = "%negResNrStart%";
/*     */   public static final String REPLACE_NEGATIVE_RESNR_END = "%negResNrEnd%";
/*     */   public static final String REPLACE_IN_LIST = "%inList%";
/*     */   public static final String REPLACE_DESCRIPTION = "%desc%";
/*     */   public static final String REPLACE_ORDERNR = "%orderNr%";
/*     */   public static final String REPLACE_TAG = "%tag%";
/*     */   public static final String REPLACE_NEW_TAG = "%oldTag%";
/*     */   public static final String REPLACE_RESOURCE_STRING = "%resStr%";
/*     */   public static final String REPLACE_RESOURCE_REF = "%resRef%";
/*     */   public static final String REPLACE_CHANGE_TYPE = "%chgType%";
/*     */   public static final String REPLACE_MODIFIED_BY = "%mdfdBy%";
/*     */   public static final String REPLACE_MODIFIED_ON = "%mdfdOn%";
/*     */   public static final String REPLACE_AUTO_MODIFIED_ON = "%autoMdfdOn%";
/*     */   public static final String REPLACE_RESOURCE_TYPE = "%resType%";
/*     */   public static final String REPLACE_RESOURCE_CASE = "%resCase%";
/*     */   public static final String REPLACE_INFO = "%info%";
/*     */   public static final String REPLACE_CREATED_BY = "%crtdBy%";
/*     */   public static final String REPLACE_CREATED_ON = "%crtdOn%";
/*     */   public static final String REPLACE_VERSION = "%vrsn%";
/*     */   public static final String REPLACE_REQUESTED = "%reqstd%";
/*     */   public static final String REPLACE_ACTIVE = "%actv%";
/*     */   public static final String REPLACE_TR_KEY = "%trKey%";
/*     */   public static final String REPLACE_RES_ITEM_REF = "%resItmRef%";
/*     */   public static final String REPLACE_LOCALIZED_RES_ITEM_REF = "%locResItmRef%";
/*     */   public static final String REPLACE_USER_NAME = "%usrName%";
/*     */   public static final String REPLACE_PREF_TABLE_1 = "%preftable1%";
/*     */   public static final String REPLACE_PREF_TABLE_2 = "%preftable2%";
/*     */   public static final String REPLACE_TABLE_PREFS_ISNULL_INITIAL = "%ReplaceTablePrefs%";
/*     */   public static final String REPLACE_TABLE_PREFS_JOIN_INITIAL = "%ReplaceTablePrefsJoin%";
/*     */   public static final String COLUMN_NAME_DESC = "DESCRIPTION";
/*     */   public static final String COLUMN_NAME_RES_NR = "RESOURCENR";
/*     */   public static final String COLUMN_NAME_RES_GRP = "RESOURCEGROUP";
/*     */   public static final String COLUMN_NAME_ORDER_NR = "ORDERNR";
/*     */   public static final String COLUMN_NAME_TAG_NR = "TAGNR";
/*     */   public static final String COLUMN_NAME_RESSTR = "RESSTR";
/*     */   public static final String COLUMN_NAME_MODIFIED_ON = "MODIFIEDON";
/*     */   public static final String COLUMN_NAME_AUTO_MODIFIED_ON = "AUTOMODIFIEDON";
/*     */   public static final String COLUMN_NAME_RES_PREFIX = "PREFIXSTR";
/*     */   public static final String COLUMN_NAME_OWNER_PRODUCT = "OWNERPRODUCT";
/*     */   public static final String COLUMN_NAME_HELP_ID = "ID";
/*     */   public static final String COLUMN_NAME_HELP_DOCNAME = "DOCNAME";
/*     */   public static final String COLUMN_NAME_HELP_DOCTITLE = "DOCTITLE";
/*     */   public static final String COLUMN_NAME_HELP_DOCTYPE = "DOCTYPE";
/*     */   public static final String COLUMN_NAME_HELP_DOCBODY = "DOCBODY";
/*     */   public static final String COLUMN_NAME_MESS_ID = "ID";
/*     */   public static final String COLUMN_NAME_MESS_CONS_ID = "CONS_ID";
/*     */   public static final String COLUMN_NAME_MESS_MODULE = "MODULE";
/*     */   public static final String COLUMN_NAME_MESS_TYPE = "MTYPE";
/*     */   public static final String COLUMN_NAME_MESS_LISTID = "LISTID";
/*     */   public static final String COLUMN_NAME_MESS_STRTAG = "STRTAG";
/*     */   public static final String COLUMN_NAME_MESS_RESGROUP = "RESGROUP";
/*     */   public static final String COLUMN_NAME_MESS_BUTTONS = "BUTTONS";
/*     */   public static final String COLUMN_NAME_MESS_DEF_BUTTON = "DEF_BUTTON";
/*     */   public static final String TABLE_NAME_RESOURCE = "RESOURCE";
/*     */   public static final String TABLE_NAME_RESOURCE_ITEM = "RESOURCE_ITEM";
/*     */   public static final String TABLE_NAME_SYNCHRONIZATION = "SYNCHRONIZATION";
/*     */   public static final String TABLE_NAME_CHANGE_SET = "CHANGE_SET";
/*     */   public static final String TABLE_NAME_HELP = "HELP_CONTENTS";
/*     */   public static final String TABLE_NAME_MESSAGE = "MESSAGE";
/*     */   public static final String PREF_REPLACE_LANGS_FILE_NAME = "LbsLocalizationConfig.xml";
/* 246 */   public static final String PREF_REPLACE_LANGS_FILE_PATH = ".." + File.separator + ".." + File.separator + "Config" + 
/* 247 */     File.separator + 
/* 248 */     "System" + File.separator + "LbsLocalizationConfig.xml";
/*     */   public static final String SQL_CREATE_TABLE_RESOURCE = "CREATE TABLE RESOURCE (RESOURCENUMBER INT PRIMARY KEY, DESCRIPTION VARCHAR(64), OWNERPRODUCT INT, SYNCHTIME TIMESTAMP)";
/*     */   public static final String SQL_CREATE_TABLE_RES_ITEM = "CREATE TABLE RESOURCE_ITEM (RESOURCEREF INT NOT NULL, ORDERNR INT NOT NULL, TAG INT NOT NULL, PREFIXSTR VARCHAR(900), OWNERPRODUCT INT, RESOURCESTR VARCHAR(900))";
/*     */   public static final String SQL_CREATE_TABLE_SYNCHRONIZATION = "CREATE TABLE SYNCHRONIZATION (SYNCHTIME TIMESTAMP NOT NULL)";
/*     */   public static final String SQL_CREATE_TABLE_CHANGE_SET = "CREATE TABLE CHANGE_SET (RESOURCEREF INT NOT NULL, OPTYPE INT NOT NULL)";
/*     */   public static final String SQL_CREATE_TABLE_HELP = "CREATE TABLE HELP_CONTENTS (ID INT PRIMARY KEY, DOCNAME VARCHAR(100) NOT NULL, DOCTITLE VARCHAR(100) NOT NULL, DOCTYPE INT NOT NULL, DOCBODY VARCHAR(4000) NOT NULL, SYNCHTIME TIMESTAMP)";
/*     */   public static final String SQL_CREATE_TABLE_MESSAGE = "CREATE TABLE MESSAGE (ID INT PRIMARY KEY, CONS_ID VARCHAR(100) NOT NULL, MODULE VARCHAR(5) NOT NULL, MTYPE INT NOT NULL, LISTID INT NOT NULL, STRTAG INT NOT NULL, RESGROUP VARCHAR(10) NOT NULL, BUTTONS INT NOT NULL, DEF_BUTTON INT NOT NULL, SYNCHTIME TIMESTAMP)";
/*     */   public static final String SQL_CREATE_TRIGGER_DELETE_RESOURCE = "CREATE TRIGGER TRG_RESOURCE_DELETE AFTER DELETE ON RESOURCE REFERENCING OLD AS OLD FOR EACH ROW MODE DB2SQL DELETE FROM RESOURCE_ITEM WHERE RESOURCEREF = OLD.RESOURCENUMBER";
/*     */   public static final String SQL_CREATE_TRIGGER_UPDATE_RESOURCE = "CREATE TRIGGER TRG_RESOURCE_UPDATE AFTER UPDATE OF RESOURCENUMBER ON RESOURCE REFERENCING OLD AS OLD NEW AS NEW FOR EACH ROW MODE DB2SQL UPDATE RESOURCE_ITEM SET RESOURCEREF = NEW.RESOURCENUMBER WHERE RESOURCEREF = OLD.RESOURCENUMBER";
/*     */   public static final String SQL_CREATE_INDEX_RESOURCE_ITEM_01 = "CREATE UNIQUE INDEX IDX_RESOURCE_ITEM_01 ON RESOURCE_ITEM (RESOURCEREF, TAG)";
/*     */   public static final String SQL_CREATE_INDEX_RESOURCE_ITEM_02 = "CREATE INDEX IDX_RESOURCE_ITEM_02 ON RESOURCE_ITEM (RESOURCEREF)";
/*     */   public static final String SQL_CREATE_INDEX_RESOURCE_ITEM_03 = "CREATE INDEX IDX_RESOURCE_ITEM_03 ON RESOURCE_ITEM (ORDERNR)";
/*     */   public static final String SQL_CREATE_INDEX_RESOURCE_ITEM_04 = "CREATE INDEX IDX_RESOURCE_ITEM_04 ON RESOURCE_ITEM (TAG)";
/*     */   public static final String SQL_CREATE_INDEX_MESSAGE_01 = "CREATE UNIQUE INDEX IDX_MESSAGE_01 ON MESSAGE (CONS_ID)";
/*     */   public static final String SQL_INSERT_HELP = "INSERT INTO HELP_CONTENTS VALUES %valueList%";
/*     */   public static final String SQL_INSERT_MESSAGE = "INSERT INTO MESSAGE VALUES %valueList%";
/*     */   public static final String SQL_INSERT_RESOURCE = "INSERT INTO RESOURCE VALUES %valueList%";
/*     */   public static final String SQL_INSERT_RESOURCE_ITEM = "INSERT INTO RESOURCE_ITEM VALUES %valueList%";
/*     */   public static final String SQL_INSERT_SYNC_TIME = "INSERT INTO SYNCHRONIZATION VALUES %valueList%";
/*     */   public static final String SQL_INSERT_RESOURCE_CHANGE_BY_RES_REF = "INSERT INTO CHANGE_SET VALUES %valueList%";
/*     */   public static final String SQL_DELETE_HELP_IN = "DELETE FROM HELP_CONTENTS WHERE ID IN (%valueList%)";
/*     */   public static final String SQL_DELETE_MESSAGE_IN = "DELETE FROM MESSAGE WHERE ID IN (%valueList%)";
/*     */   public static final String SQL_DELETE_RESOURCE_IN = "DELETE FROM RESOURCE WHERE RESOURCENUMBER IN (%valueList%)";
/*     */   public static final String SQL_DELETE_RESOURCE = "DELETE FROM RESOURCE WHERE RESOURCENUMBER = %resNr%";
/*     */   public static final String SQL_DELETE_RESOURCE_ITEM = "DELETE FROM RESOURCE_ITEM WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_DELETE_RESOURCE_ITEM_BY_TAG = "DELETE FROM RESOURCE_ITEM WHERE RESOURCEREF = %resNr% AND TAG = %tag%";
/*     */   public static final String SQL_DELETE_SYNC_TIME = "DELETE FROM SYNCHRONIZATION";
/*     */   public static final String SQL_DELETE_CHANGE = "DELETE FROM CHANGE_SET WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_UPDATE_RESOURCE = "UPDATE RESOURCE SET RESOURCENUMBER = %newResNr%, DESCRIPTION = '%desc%' WHERE RESOURCENUMBER = %resNr%";
/*     */   public static final String SQL_UPDATE_RESOURCE_ITEM_RESOURCE_REF = "UPDATE RESOURCE_ITEM SET RESOURCEREF = %newResNr% WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_UPDATE_RESOURCE_ITEM = "UPDATE RESOURCE_ITEM SET ORDERNR = %orderNr%, TAG = %oldTag%, RESOURCESTR = '%resStr%' WHERE RESOURCEREF = %resRef% AND TAG = %tag%";
/*     */   public static final String SQL_UPDATE_RESOURCE_CHANGE_BY_RES_REF = "UPDATE CHANGE_SET SET OPTYPE = %chgType% WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_SELECT_RESOURCE_MAX_ID = "SELECT MAX(RESOURCENUMBER) FROM RESOURCE";
/*     */   public static final String SQL_SELECT_RESOURCE_MAX_ID_BY_GROUP = "SELECT MAX(RESOURCENUMBER) FROM RESOURCE WHERE (RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)";
/*     */   public static final String SQL_SELECT_RESOURCE_MIN_ID = "SELECT MIN(RESOURCENUMBER) FROM RESOURCE";
/*     */   public static final String SQL_SELECT_RESOURCE_MIN_ID_BY_GROUP = "SELECT MIN(RESOURCENUMBER) FROM RESOURCE WHERE (RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)";
/*     */   public static final String SQL_SELECT_RESOURCE_HEADER_BY_START_LIMIT = "SELECT RESOURCENUMBER, DESCRIPTION FROM RESOURCE WHERE RESOURCENUMBER >= %resNrStart% AND ((RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)) ORDER BY RESOURCENUMBER";
/*     */   public static final String SQL_SELECT_RESOURCE_HEADER_BY_END_LIMIT = "SELECT RESOURCENUMBER, DESCRIPTION FROM RESOURCE WHERE RESOURCENUMBER <= %resNrEnd% AND ((RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)) ORDER BY RESOURCENUMBER";
/*     */   public static final String SQL_SELECT_RESOURCE_HEADER_BY_RANGE = "SELECT RESOURCENUMBER, DESCRIPTION FROM RESOURCE WHERE RESOURCENUMBER >= %resNrStart% AND RESOURCENUMBER <= %resNrEnd% AND ((RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)) ORDER BY RESOURCENUMBER";
/*     */   public static final String SQL_SELECT_RESOURCE_HEADER_BY_ID_SET = "SELECT RESOURCENUMBER, DESCRIPTION FROM RESOURCE WHERE RESOURCENUMBER IN (%inList%) AND ((RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%)) ORDER BY RESOURCENUMBER";
/*     */   public static final String SQL_SELECT_LAST_SYNC_TIME = "SELECT SYNCHTIME FROM SYNCHRONIZATION";
/*     */   public static final String SQL_SELECT_MESSAGE_BY_ID = "SELECT ID, CONS_ID, MODULE, MTYPE, LISTID, STRTAG, RESGROUP, BUTTONS, DEF_BUTTON FROM MESSAGE WHERE CONS_ID = '%desc%'";
/*     */   public static final String SQL_SELECT_MESSAGE_BY_MODULE = "SELECT ID, CONS_ID FROM MESSAGE WHERE MODULE = '%desc%'";
/*     */   public static final String SQL_SELECT_HELP_ALL = "SELECT ID, TRIM(DOCNAME) AS DOCNAME, DOCTITLE, DOCTYPE, DOCBODY FROM HELP_CONTENTS";
/*     */   public static final String SQL_SELECT_HELP_BY_DOCNAME = "SELECT ID, TRIM(DOCNAME) AS DOCNAME, DOCTITLE, DOCTYPE, DOCBODY FROM HELP_CONTENTS WHERE DOCNAME = '%desc%'";
/*     */   public static final String SQL_SELECT_HELP_BY_DOCTYPE = "SELECT ID, TRIM(DOCNAME) AS DOCNAME, DOCTITLE, DOCTYPE, DOCBODY FROM HELP_CONTENTS WHERE DOCTYPE IN (%inList%)";
/*     */   public static final String SQL_SELECT_RESOURCE_HEADER_BY_RES_REF = "SELECT RESOURCENUMBER, DESCRIPTION, OWNERPRODUCT FROM RESOURCE WHERE RESOURCENUMBER = %resNr%";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM_BY_RES_REF = "SELECT ORDERNR, TAG, PREFIXSTR, RESOURCESTR, OWNERPRODUCT FROM RESOURCE_ITEM WHERE RESOURCEREF = %resNr% ORDER BY ORDERNR, TAG";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM_BY_RES_REF_OWNER = "SELECT ORDERNR, TAG, PREFIXSTR, RESOURCESTR, OWNERPRODUCT FROM RESOURCE_ITEM WHERE RESOURCEREF = %resNr% AND OWNERPRODUCT IN (%ownerProd%) ORDER BY ORDERNR, TAG";
/*     */   public static final String SQL_SELECT_RESOURCE_CHANGE_BY_RES_REF = "SELECT COUNT(*) FROM CHANGE_SET WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_SELECT_RESOURCE_SYNCH_TIME = "SELECT SYNCHTIME FROM RESOURCE WHERE RESOURCENUMBER = %resNr%";
/*     */   public static final String SQL_SELECT_RESOURCE_CHANGE = "SELECT * FROM CHANGE_SET ORDER BY RESOURCEREF";
/*     */   public static final String SQL_SELECT_RESOURCE_COUNT = "SELECT COUNT(*) FROM RESOURCE";
/*     */   public static final String SQL_SELECT_RESOURCE_COUNT_BY_GROUP = "SELECT COUNT(*) FROM RESOURCE WHERE RESOURCENUMBER >= %resNrStart% AND RESOURCENUMBER <= %resNrEnd% AND ((RESOURCENUMBER >= %posResNrStart% AND RESOURCENUMBER <= %posResNrEnd%) OR (RESOURCENUMBER >= %negResNrStart% AND RESOURCENUMBER <= %negResNrEnd%))";
/*     */   public static final String SQL_INSERT_RESOURCE_INTO_REMOTE_DB = "INSERT INTO RE_RESOURCES (RESOURCENR, DESCRIPTION, RESOURCEGROUP, RESOURCETYPE, RESOURCECASE, SLIST, CREATEDBY, CREATEDON, MODIFIEDBY, MODIFIEDON, AUTOMODIFIEDON, VERSION, REQUESTED,ACTIVE) VALUES %valueList%";
/*     */   public static final String SQL_INSERT_RESOURCE_ITEM_INTO_REMOTE_DB = "INSERT INTO RE_RESOURCEITEMS (RESOURCEREF, ORDERNR, TAGNR, RESOURCETYPE, RESOURCECASE, INFO, CREATEDBY, CREATEDON, MODIFIEDBY, MODIFIEDON, AUTOMODIFIEDON, VERSION, REQUESTED,ACTIVE) VALUES %valueList%";
/*     */   public static final String SQL_INSERT_LOCALIZED_RESOURCE_ITEM_INTO_REMOTE_DB = "INSERT INTO %table% (RESOURCEITEMREF, RESOURCESTR, TRKEY, INFO, CREATEDBY, CREATEDON, MODIFIEDBY, MODIFIEDON, VERSION) VALUES %valueList%";
/*     */   public static final String SQL_UPDATE_RESOURCE_ON_REMOTE_DB = "UPDATE RE_RESOURCES SET DESCRIPTION = '%desc%', MODIFIEDBY = %mdfdBy%, MODIFIEDON = '%mdfdOn%' WHERE ID = %resRef%";
/*     */   public static final String SQL_DELETE_RESOURCE_ON_REMOTE_DB = "DELETE FROM RE_RESOURCES WHERE RESOURCENR = %resNr% AND RESOURCEGROUP = '%resGrp%'";
/*     */   public static final String SQL_UPDATE_RESOURCE_ITEM_ON_REMOTE_DB = "UPDATE RE_RESOURCEITEMS SET RESOURCETYPE = %resType%, RESOURCECASE = %resCase%, INFO = %info%, CREATEDBY = %crtdBy%, CREATEDON = '%crtdOn%', MODIFIEDBY = %mdfdBy%, MODIFIEDON = '%mdfdOn%', AUTOMODIFIEDON = '%autoMdfdOn%', VERSION = %vrsn%, REQUESTED = %reqstd%, ACTIVE = %actv% WHERE RESOURCEREF = %resRef% AND ORDERNR = %orderNr% AND TAGNR = %tag%";
/*     */   public static final String SQL_UPDATE_RESOURCE_ITEM_ON_REMOTE_DB_SIMPLE = "UPDATE RE_RESOURCEITEMS SET MODIFIEDBY = %mdfdBy%, MODIFIEDON = '%mdfdOn%', AUTOMODIFIEDON = '%autoMdfdOn%' WHERE ID = %resItmRef%";
/*     */   public static final String SQL_UPDATE_LOCALIZED_RESOURCE_ITEM_ON_REMOTE_DB = "UPDATE %table% SET RESOURCESTR = '%resStr%', TRKEY = '%trKey%', INFO = %info%, CREATEDBY = %crtdBy%, CREATEDON = '%crtdOn%', MODIFIEDBY = %mdfdBy%, MODIFIEDON = '%mdfdOn%', VERSION = %vrsn% WHERE ID = %locResItmRef%";
/*     */   public static final String SQL_UPDATE_LOCALIZED_RESOURCE_ITEM_ON_REMOTE_DB_SIMPLE = "UPDATE %table% SET RESOURCESTR = '%resStr%' WHERE ID = %locResItmRef%";
/*     */   public static final String SQL_DELETE_RESOURCE_ITEM_ON_REMOTE_DB_BY_RESOURCE_REF = "DELETE FROM RE_RESOURCEITEMS WHERE RESOURCEREF = %resRef%";
/*     */   public static final String SQL_DELETE_RESOURCE_ITEM_ON_REMOTE_DB_BY_ITEM_ID = "DELETE FROM RE_RESOURCEITEMS WHERE ID = %resItmRef%";
/*     */   public static final String SQL_SELECT_USER = "SELECT * FROM RE_USERS USR WHERE USR.USERNAME = '%usrName%'";
/*     */   public static final String SQL_SELECT_HELP = "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY FROM RE_HELPDOCS H";
/*     */   public static final String SQL_SELECT_MESSAGE = "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON FROM RE_MESSAGES M";
/*     */   public static final String SQL_SELECT_RESOURCE = "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%'";
/*     */   public static final String SQL_SELECT_RESOURCE_BY_RES_NUMBER = "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.MODIFIEDON, R.AUTOMODIFIEDON FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%' AND R.RESOURCENR = %resNr%";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM_BY_ID = "SELECT ID FROM RE_RESOURCEITEMS WHERE RESOURCEREF = %resNr% AND ORDERNR = %orderNr% AND TAGNR = %tag%";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM_BY_RESOURCEREF = "SELECT ID FROM RE_RESOURCEITEMS WHERE RESOURCEREF = %resNr%";
/*     */   public static final String SQL_SELECT_LOCALIZED_RESOURCE_ITEM_BY_ID = "SELECT ID FROM %table% WHERE RESOURCEITEMREF = %resItmRef%";
/*     */   public static final String SQL_SELECT_HELP_BY_SYNCH_TIME = "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY, H.MODIFIEDON FROM RE_HELPDOCS H WHERE H.MODIFIEDON > '%synchTime%' ORDER BY H.ID";
/*     */   public static final String SQL_SELECT_RESOURCE_NUMBER_BY_SYNCH_TIME = "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.MODIFIEDON, R.AUTOMODIFIEDON, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%' AND (R.MODIFIEDON > '%synchTime%' OR R.AUTOMODIFIEDON > '%synchTime%') ORDER BY R.RESOURCENR";
/*     */   public static final String SQL_SELECT_MESSAGE_BY_SYNCH_TIME = "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON, M.MODIFIEDON FROM RE_MESSAGES M WHERE M.MODIFIEDON > '%synchTime%' ORDER BY M.ID";
/*     */   public static final String SQL_SELECT_RESOURCE_NUMBER_BY_SYNCH_TIME_ALL_GROUPS = "SELECT R.RESOURCEGROUP, R.ID, R.RESOURCENR, R.DESCRIPTION, R.MODIFIEDON, R.AUTOMODIFIEDON FROM RE_RESOURCES R WHERE (R.MODIFIEDON > '%synchTime%' OR R.AUTOMODIFIEDON > '%synchTime%') ORDER BY R.RESOURCEGROUP, R.RESOURCENR";
/*     */   public static final String REPLACE_TABLE_PREF1_PREF2_ISNULL = "ISNULL(%preftable1%.RESOURCESTR, ISNULL(%preftable2%.RESOURCESTR, ''))";
/*     */   public static final String REPLACE_TABLE_PREF1_ISNULL = "ISNULL(%preftable1%.RESOURCESTR, '')";
/*     */   public static final String REPLACE_TABLE_PREF2_ISNULL = "ISNULL(%preftable2%.RESOURCESTR, '')";
/*     */   public static final String REPLACE_TABLE_PREF1_JOIN = "LEFT OUTER JOIN %preftable1% ON (RI.ID = %preftable1%.RESOURCEITEMREF) ";
/*     */   public static final String REPLACE_TABLE_PREF2_JOIN = "LEFT OUTER JOIN %preftable2% ON (RI.ID = %preftable2%.RESOURCEITEMREF) ";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM_PREVIOUS = "SELECT DISTINCT RI.ORDERNR, RI.TAGNR, ISNULL(STD.RESOURCESTR COLLATE DATABASE_DEFAULT, '') + ISNULL(%table%.RESOURCESTR, %ReplaceTablePrefs%) AS RESSTR FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% ON (RI.ID = %table%.RESOURCEITEMREF) %ReplaceTablePrefsJoin%WHERE R.RESOURCEGROUP = '%resGrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resNr% ORDER BY RI.ORDERNR";
/*     */   public static final String SQL_SELECT_RESOURCE_ITEM = "SELECT DISTINCT PREFIXSTR = ISNULL(RI.PREFIXSTR, ''),  RESSTR =  CASE \tR.RESOURCETYPE \tWHEN 2 THEN ISNULL(STD.RESOURCESTR COLLATE DATABASE_DEFAULT, '') \tELSE ISNULL(%table%.RESOURCESTR, %ReplaceTablePrefs%) END,  RI.ORDERNR,  RI.TAGNR, RI.OWNERPRODUCT FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% ON (RI.ID = %table%.RESOURCEITEMREF) %ReplaceTablePrefsJoin%WHERE R.RESOURCEGROUP = '%resGrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resNr% ORDER BY RI.ORDERNR";
/*     */   public static final String SQL_SELECT_SERVER_TIME = "SELECT GETDATE()";
/*     */   public static final String DEFAULT_RES_GROUP = "UN";
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\ILocalizationConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */