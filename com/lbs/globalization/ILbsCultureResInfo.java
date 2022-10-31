package com.lbs.globalization;

import java.awt.Font;

public interface ILbsCultureResInfo {
  public static final int RES_BASE = 100;
  
  public static final int RES_LANG_BASE = 101;
  
  public static final int RES_USERNAME = 102;
  
  public static final int RES_PASSWORD = 103;
  
  public static final int RES_COMPANY = 104;
  
  public static final int RES_PERIOD = 105;
  
  public static final int RES_LANGUAGE = 106;
  
  public static final int RES_OK = 107;
  
  public static final int RES_CANCEL = 108;
  
  public static final int RES_COMPANY_PERIOD = 109;
  
  public static final int RES_SUBCOMPANY = 110;
  
  public static final int RES_CAPSLOCK = 111;
  
  public static final int RES_REMEMBER_LOGIN_INFO = 112;
  
  public static final int RES_EMAIL_ADDRESS = 113;
  
  public static final int RES_INVALID_EMAIL_ADDRESS = 114;
  
  public static final int RES_REMEMBER_PASS_OK = 115;
  
  public static final int RES_REMEMBER_PASS_NOT_FOUND = 116;
  
  public static final int RES_REMEMBER_PASS_EXCEPTION = 117;
  
  public static final int RES_INFO = 118;
  
  public static final int RES_LOGIN_AS_DIFFERENT_USER = 119;
  
  public static final int RES_ADVANCED_PANEL = 120;
  
  public static final int RES_CONFIRMATION_NOT_VALID = 121;
  
  public static final int RES_CONFIRMATION_EXCEPTION = 122;
  
  public static final int RES_CONFIRMATION_TIMEOUT = 123;
  
  public static final int RES_CONFIRMATION_STR = 124;
  
  public static final int RES_CONFIRMATIONCODE_STR = 125;
  
  public static final int RES_LOGGING_IN = 126;
  
  public static final int RES_ERR_MSG_BASE = 1000;
  
  public static final int RES_ERR = 1000;
  
  public static final int RES_ERR_INVALID_USER = 1001;
  
  public static final int RES_ERR_INSUFFICIENT_PRIVILEGE = 1002;
  
  public static final int RES_ERR_OLD_COMPANY_VERSION = 1003;
  
  public static final int RES_ERR_INSUFFICIENT_PRIVILEGE_FOR_COMPANY = 1004;
  
  public static final int RES_ERR_PERIOD_NOT_FOUND = 1005;
  
  public static final int RES_ERR_NO_PERIOD_FOUND = 1006;
  
  public static final int RES_ERR_OLD_SYSTEM_VERSION = 1007;
  
  public static final int RES_ERR_COMPANY_NOT_FOUND = 1008;
  
  public static final int RES_ERR_NO_SYSTEM_TABLES_FOUND = 1009;
  
  public static final int RES_ERR_USER_CANNOT_BE_LOGGED_IN = 1010;
  
  public static final int RES_ERR_BLOCKED_USER_ACCOUNT = 1011;
  
  public static final int RES_ERR_INVALID_IP = 1012;
  
  public static final int RES_ERR_PASSWORD_EXPIRED = 1013;
  
  public static final int RES_ERR_DATABASE_CONNECTION = 1014;
  
  public static final int RES_ERR_OLD_PERIOD_VERSION = 1015;
  
  public static final int RES_ERR_COMPANY_NOT_SPECIFIED = 1016;
  
  public static final int RES_ERR_CAPI_MAY_OLDVERSION = 1017;
  
  public static final int RES_ERR_LOGIN_FAILED = 1018;
  
  public static final int RES_ERR_NO_CHANNEL = 1019;
  
  public static final int RES_ERR_INVALID_URI = 1020;
  
  public static final int RES_ERR_INVALID_RESP = 1021;
  
  public static final int RES_ERR_INNER_EXCEPTION = 1022;
  
  public static final int RES_ERR_REMOTE_EXCEPTION = 1023;
  
  public static final int RES_ERR_UNKNOWN_EXCEPTION = 1024;
  
  public static final int RES_ERR_LICENCE_EXCEPTION = 1025;
  
  public static final int RES_ERR_LANG_LICENCE = 1026;
  
  public static final int RES_ERR_TIMEOUT_LICENCE = 1027;
  
  public static final int RES_ERR_OS_LICENCE = 1028;
  
  public static final int RES_ERR_DB_LICENCE = 1029;
  
  public static final int RES_ERR_NO_LOGIN_SUPPORT = 1030;
  
  public static final int RES_ERR_OLD_CUST_VERSION = 1031;
  
  public static final int RES_ERR_REPORT_VIEWER_ALREADY_IN_USE = 1032;
  
  public static final int RES_ERR_CLIENT_COMPATIBILITY = 1033;
  
  public static final int RES_ERR_CUST_NOT_VALID = 1034;
  
  public static final int RES_ERR_COMPANY_TRIGGER_OLD = 1035;
  
  public static final int RES_ERR_PERIOD_TRIGGER_OLD = 1036;
  
  public static final int RES_ERR_INVALID_DIFFERENT_USER = 1037;
  
  public static final int RES_ERR_OLD_WORKFLOW_VERSION = 1038;
  
  public static final int RES_ERR_CAPACITY_EXCEEDED = 1039;
  
  public static final int RES_ERR_CAPTCHA_WRONG = 1040;
  
  public static final int RES_ERR_PASSWORD_EMPTY = 1041;
  
  public static final int RES_ERR_TEMPORARY_PASSWORD = 1042;
  
  public static final int RES_ERR_NO_CAPACITY_FOR_USERTYPE = 1043;
  
  public static final int RES_ERR_INVALID_LOGIN_FOR_USERTYPE = 1044;
  
  public static final int RES_ERR_SERVER_MAINTENANCE_MODE = 1045;
  
  public static final int RES_ERR_SAAS_PACKAGE_DOWNGRADED = 1046;
  
  public static final int RES_ERR_LIC_MENU_FUNC = 1064;
  
  public static final int RES_ERR_INVALID_TCKN = 1065;
  
  public static final int RES_ERR_INVALID_DSIGNATURE = 1066;
  
  public static final int RES_ERR_INVALID_DIGEST = 1067;
  
  public static final int RES_ERR_NO_LIC = 1068;
  
  public static final int RES_ERR_WORKFLOW_UPGRADE_IN_PROGRESS = 1069;
  
  public static final int RES_ERR_INVALID_TOKEN = 1070;
  
  public static final int RES_ERR_INVALID_NULL_USER = 1071;
  
  public static final int RES_ERR_ONE_MORE_SESSION = 1072;
  
  public static final int RES_ERR_MORE_THAN_DEFINED_SESSION = 1073;
  
  public static final int RES_SYSTEM_IS_BEING_PREPARED_MESSAGE = 1074;
  
  public static final int RES_MAINPAGE_MESSSAGE = 2001;
  
  String getCultureResStr(int paramInt);
  
  String getLanguagePrefix();
  
  Font getFont();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\ILbsCultureResInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */