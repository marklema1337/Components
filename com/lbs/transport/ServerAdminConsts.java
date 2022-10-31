/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.util.JLbsConstants;
/*    */ import java.awt.Font;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerAdminConsts
/*    */ {
/*    */   public static final int SRV_SIGN_OK = 2632002;
/*    */   public static final int SRV_SIGN_FAIL = 2511977;
/*    */   public static final int SRV_OP_FAILED = -101;
/*    */   public static final int SRV_OP_SUCCESS = 0;
/*    */   public static final int DB_CONNECTION_FAILED = -202;
/*    */   public static final int SRV_OP_LOGIN = 100;
/*    */   public static final int SRV_OP_LOGOUT = 101;
/*    */   public static final int SRV_OP_SAVE_LOGIN = 102;
/*    */   public static final int SRV_OP_GET_RESOURCE = 103;
/*    */   public static final int SRV_OP_JAR_DOWNLOAD = 104;
/*    */   public static final int SRV_OP_DOWNLOAD_BIN = 105;
/*    */   public static final int SRV_OP_CONNECTION_TEST = 106;
/*    */   public static final int SRV_OP_UPG_TABLES = 107;
/*    */   public static final int SRV_OP_CHK_TABLES = 108;
/*    */   public static final int SRV_OP_APP_VERSION = 109;
/*    */   public static final int SRV_OP_EDIT = 110;
/*    */   public static final int SRV_OP_SAVE = 111;
/*    */   public static final int SRV_OP_UPG_SYS_REF_COUNT = 112;
/*    */   public static final int SRV_OP_DROP_SYS_REF_COUNT = 113;
/*    */   public static final int SRV_OP_REB_CLI_OP_PACKETS = 114;
/*    */   public static final int SRV_OP_RELOAD_QUERIES = 115;
/*    */   public static final int SRV_OP_RELOAD_QD_FORMS = 116;
/*    */   public static final int SRV_OP_APPLY_USER_CHANGES = 117;
/*    */   public static final int SRV_OP_APPLY_EAR_DIFF = 118;
/*    */   public static final int SRV_OP_SERVER_CONFIG_EDITOR_GET = 119;
/*    */   public static final int SRV_OP_SERVER_CONFIG_EDITOR_SAVE = 120;
/*    */   public static final int SRV_OP_SERVER_CONFIG_EDITOR_LOAD = 121;
/*    */   public static final int SRV_OP_GET_JTDS_VALUE = 122;
/*    */   public static final int SRV_OP_GET_TABLE_UPGRADE_SATATUS = 123;
/*    */   public static final String CONFIG_SIGN = "LBS JAVA UNITY CONFIG FILE v.0.0.1 sign";
/*    */   public static final String ms_ConfigDefinitions = "config-definitions";
/*    */   public static final String ms_UserNameAttribute = "user-name";
/*    */   public static final String ms_PasswordAttribute = "password";
/*    */   public static final String ms_LanguageNrAttribute = "language-nr";
/*    */   public static final String ms_ConfigSignAttribute = "config-sign";
/*    */   public static final int LNG_TURKISH_TR = 0;
/*    */   public static final int LNG_ENGLISH_US = 1;
/*    */   public static final int LNG_GERMAN_DE = 2;
/*    */   public static final int LNG_FARSI_IR = 3;
/*    */   public static final int LNG_ARABIC_JO = 4;
/*    */   public static final int LNG_RUSSIAN_RU = 5;
/*    */   public static final int CMD_OPEN_DB_EDITOR = 1;
/*    */   public static final int CMD_UPGRADE_SYS_TABLES = 2;
/*    */   public static final int CMD_CHECK_SYS_TABLES = 3;
/*    */   public static final int CMD_UPGRADE_SYS_REF_COUNT = 4;
/*    */   public static final int CMD_DROP_SYS_REF_COUNT = 5;
/*    */   public static final int CMD_RELOAD_QUERIES = 6;
/*    */   public static final int CMD_REBUILD_OPT_PACKAGES = 7;
/*    */   public static final int CMD_RELOAD_QUERY_DIS_FORMS = 8;
/*    */   public static final int CMD_APPLY_EAR_DIFF = 9;
/*    */   public static final int CMD_APPLY_USER_CHANGES = 10;
/*    */   public static final int CMD_OPEN_DOMAIN_EDITOR = 11;
/*    */   public static final String LERROR = "!_LERROR_!";
/*    */   public static final int LERRORINT = -15798235;
/*    */   
/*    */   public static Font getFontSmall() {
/* 86 */     return new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE - 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ServerAdminConsts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */