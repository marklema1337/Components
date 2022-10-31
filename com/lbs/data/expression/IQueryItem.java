package com.lbs.data.expression;

public interface IQueryItem {
  public static final int ITEM_LIST = 100;
  
  public static final int DB_ENTITY = 101;
  
  public static final int RUNTIME_COLUMN_BINDING = 102;
  
  public static final int LITERAL = 0;
  
  public static final int QUALIFY = 1;
  
  public static final int TABLE_ALIAS = 2;
  
  public static final int COLUMN_ALIAS = 4;
  
  public static final int COLUMN_CASE = 5;
  
  public static final int CASE_IF = 6;
  
  public static final int CASE_ELSE = 7;
  
  public static final int CASE_ALIAS = 8;
  
  public static final int ASSIGN = 9;
  
  public static final int NAME_TABLE = 10;
  
  public static final int NAME_INDEX = 11;
  
  public static final int NAME_COLUMN = 12;
  
  public static final int NAME_STORED_PROC = 13;
  
  public static final int NAME_SEQUENCE_TABLE = 14;
  
  public static final int NAME_SEQUENCE_TRIGER = 15;
  
  public static final int NAME_GENERIC = 16;
  
  public static final int NAME_TABLE_ALIAS = 17;
  
  public static final int NAME_COLUMN_ALIAS = 18;
  
  public static final int NAME_UUID = 19;
  
  public static final int MAIN_TABLE = 20;
  
  public static final int JOIN_NATURAL = 21;
  
  public static final int JOIN_LEFT_INNER = 22;
  
  public static final int JOIN_LEFT_OUTER = 23;
  
  public static final int JOIN_RIGHT_OUTER = 25;
  
  public static final int JOIN_QUERY = 26;
  
  public static final int LINK_TABLE = 27;
  
  public static final int FUNC_STRLEN = 501;
  
  public static final int FUNC_TODATE = 502;
  
  public static final int FUNC_DATEDIFF = 503;
  
  public static final int FUNC_LIKE = 504;
  
  public static final int FUNC_SUBSTR = 505;
  
  public static final int FUNC_CASE = 506;
  
  public static final int FUNC_TOSTR = 507;
  
  public static final int FUNC_REPLACE = 508;
  
  public static final int FUNC_ABS = 509;
  
  public static final int FUNC_CONCAT = 510;
  
  public static final int FUNC_COALESCE = 511;
  
  public static final int FUNC_GETDAY = 512;
  
  public static final int FUNC_GETMONTH = 513;
  
  public static final int FUNC_GETYEAR = 514;
  
  public static final int FUNC_NATIVE = 515;
  
  public static final int FUNC_CURDATE = 516;
  
  public static final int FUNC_TRUNCDATE = 517;
  
  public static final int FUNC_NOT_LIKE = 518;
  
  public static final int FUNC_LDS = 519;
  
  public static final int FUNC_ISNULL = 520;
  
  public static final int FUNC_U_IFTHENELSE = 521;
  
  public static final int OP_EQ = 40;
  
  public static final int OP_LT = 41;
  
  public static final int OP_LE = 42;
  
  public static final int OP_GT = 43;
  
  public static final int OP_GE = 44;
  
  public static final int OP_NEQ = 45;
  
  public static final int OP_IN = 46;
  
  public static final int OP_NOT_IN = 48;
  
  public static final int OP_IS = 47;
  
  public static final int AGG_AVG = 50;
  
  public static final int AGG_SUM = 51;
  
  public static final int AGG_MAX = 52;
  
  public static final int AGG_MIN = 53;
  
  public static final int AGG_COUNT = 54;
  
  public static final int AGG_COUNT_ROWS = 55;
  
  public static final int AGG_COUNT_DISTINCT = 56;
  
  public static final int AGG_NATIVE_AGG = 57;
  
  public static final int AGG_WITH_OVER = 58;
  
  public static final int AGG_COUNT_BIG = 59;
  
  public static final int ORDER_ASC = 60;
  
  public static final int ORDER_DESC = 61;
  
  public static final int LOG_AND = 70;
  
  public static final int LOG_OR = 71;
  
  public static final int LOG_NOT = 72;
  
  public static final int VALUE_STRING = 80;
  
  public static final int VALUE_DATE = 81;
  
  public static final int VALUE_INTEGER = 82;
  
  public static final int VALUE_NUMBER = 83;
  
  public static final int VALUE_NULL = 84;
  
  public static final int VALUE_OBJECT = 85;
  
  public static final int QUERY_PARAMETER = 90;
  
  public static final int QUERY_TERM = 91;
  
  public static final int QUERY_VARIABLE = 92;
  
  public static final int INNER_QUERY = 93;
  
  public static final int SQL_PARAM = 94;
  
  public static final int ARITH_PLUS = 110;
  
  public static final int ARITH_MINUS = 111;
  
  public static final int ARITH_DIV = 112;
  
  public static final int ARITH_MUL = 113;
  
  public static final int ARITH_MOD = 114;
  
  public static final int UNARY_MINUS = 115;
  
  public static final int BITWISE_AND = 116;
  
  public static final int CONST_MAXDATE = 120;
  
  public static final int CONST_MINDATE = 121;
  
  public static final int CONST_TODAY = 122;
  
  public static final int QRY_SELECT = 200;
  
  public static final int QRY_INSERT = 201;
  
  public static final int QRY_UPDATE = 202;
  
  public static final int QRY_DELETE = 203;
  
  public static final int QRY_MAIN_TABLE = 220;
  
  public static final int QRY_COLUMNS = 221;
  
  public static final int QRY_FROM = 222;
  
  public static final int QRY_WHERE = 223;
  
  public static final int QRY_GROUP_BY = 224;
  
  public static final int QRY_ORDER_BY = 225;
  
  public static final int QRY_COLUMN_UPDATES = 226;
  
  public static final int QRY_COLUMN_NAMES = 227;
  
  public static final int QRY_COLUMN_VALUES = 228;
  
  public static final int QRY_ROW_LIMIT = 229;
  
  public static final int QRY_OPTIONS = 230;
  
  public static final int QRY_DISTINCT = 231;
  
  public static final int QRY_ROWNUM_SAFE = 232;
  
  public static final int QRY_ORDER_INDEXES = 233;
  
  public static final int QRY_GROUP_ITEM = 234;
  
  public static final int QRY_HINT_ITEM = 235;
  
  public static final int QRY_ROW_COUNT = 236;
  
  public static final int QRY_DISABLE_NOLOCK = 237;
  
  public static final int QRY_UNION = 238;
  
  public static final int QRY_UNION_ALL = 239;
  
  public static final int QRY_ROW_START = 240;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\IQueryItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */