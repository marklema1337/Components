/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class DBFieldHelper
/*     */   implements IDBField
/*     */ {
/*     */   public static Class DbToJavaType(int dbType) {
/*  22 */     switch (dbType) {
/*     */       
/*     */       case 1:
/*  25 */         return byte.class;
/*     */       case 2:
/*  27 */         return int.class;
/*     */       case 3:
/*  29 */         return int.class;
/*     */       case 4:
/*  31 */         return long.class;
/*     */       case 5:
/*  33 */         return float.class;
/*     */       case 6:
/*  35 */         return double.class;
/*     */       case 7:
/*  37 */         return BigDecimal.class;
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*  41 */         return Calendar.class;
/*     */       case 12:
/*     */       case 13:
/*  44 */         return (new byte[0]).getClass();
/*     */       case 14:
/*  46 */         return String.class;
/*     */       case 16:
/*  48 */         return Timestamp.class;
/*     */     } 
/*     */     
/*  51 */     return String.class;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int DbToJdbcType(int dbType) {
/*  57 */     switch (dbType) {
/*     */       
/*     */       case 1:
/*  60 */         return -6;
/*     */       case 2:
/*  62 */         return 5;
/*     */       case 3:
/*  64 */         return 4;
/*     */       case 4:
/*  66 */         return -5;
/*     */       case 5:
/*  68 */         return 6;
/*     */       case 6:
/*  70 */         return 8;
/*     */       case 7:
/*  72 */         return 2;
/*     */       case 8:
/*  74 */         return 91;
/*     */       case 9:
/*  76 */         return 92;
/*     */       case 10:
/*  78 */         return 93;
/*     */       case 12:
/*  80 */         return -2;
/*     */       case 13:
/*  82 */         return 2004;
/*     */       case 14:
/*  84 */         return 2005;
/*     */       case 16:
/*  86 */         return 2014;
/*     */     } 
/*     */     
/*  89 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int JdbcToDbType(int jdbcType) {
/*  95 */     switch (jdbcType) {
/*     */       
/*     */       case -6:
/*  98 */         return 1;
/*     */       case 5:
/* 100 */         return 2;
/*     */       
/*     */       case 4:
/* 103 */         return 3;
/*     */       
/*     */       case -5:
/* 106 */         return 4;
/*     */       
/*     */       case 6:
/* 109 */         return 5;
/*     */       
/*     */       case 8:
/* 112 */         return 6;
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 116 */         return 7;
/*     */       
/*     */       case 91:
/* 119 */         return 8;
/*     */       
/*     */       case 92:
/* 122 */         return 9;
/*     */       
/*     */       case 93:
/* 125 */         return 10;
/*     */       
/*     */       case -2:
/* 128 */         return 12;
/*     */       
/*     */       case -4:
/*     */       case 2004:
/* 132 */         return 13;
/*     */       
/*     */       case -1:
/*     */       case 2005:
/* 136 */         return 14;
/*     */       case 2013:
/* 138 */         return 16;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     return 11;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseFieldLBSType(String value) {
/* 148 */     if (value.equalsIgnoreCase("lbs_logicalref")) {
/* 149 */       return 1;
/*     */     }
/* 151 */     if (value.equalsIgnoreCase("lbs_code")) {
/* 152 */       return 2;
/*     */     }
/* 154 */     if (value.equalsIgnoreCase("lbs_description")) {
/* 155 */       return 3;
/*     */     }
/* 157 */     if (value.equalsIgnoreCase("lbs_enumeration")) {
/* 158 */       return 4;
/*     */     }
/* 160 */     if (value.equalsIgnoreCase("lbs_auxcode")) {
/* 161 */       return 5;
/*     */     }
/* 163 */     if (value.equalsIgnoreCase("lbs_groupcode")) {
/* 164 */       return 6;
/*     */     }
/* 166 */     if (value.equalsIgnoreCase("lbs_slipnr")) {
/* 167 */       return 7;
/*     */     }
/* 169 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseFieldType(String value) {
/* 174 */     if (value.equalsIgnoreCase("default")) {
/* 175 */       return 0;
/*     */     }
/* 177 */     if (value.equalsIgnoreCase("byte")) {
/* 178 */       return 1;
/*     */     }
/* 180 */     if (value.equalsIgnoreCase("smallint")) {
/* 181 */       return 2;
/*     */     }
/* 183 */     if (value.equalsIgnoreCase("integer")) {
/* 184 */       return 3;
/*     */     }
/* 186 */     if (value.equalsIgnoreCase("int64")) {
/* 187 */       return 4;
/*     */     }
/* 189 */     if (value.equalsIgnoreCase("float")) {
/* 190 */       return 5;
/*     */     }
/* 192 */     if (value.equalsIgnoreCase("double")) {
/* 193 */       return 6;
/*     */     }
/* 195 */     if (value.equalsIgnoreCase("numeric")) {
/* 196 */       return 7;
/*     */     }
/* 198 */     if (value.equalsIgnoreCase("date"))
/*     */     {
/* 200 */       return 10;
/*     */     }
/* 202 */     if (value.equalsIgnoreCase("time"))
/*     */     {
/* 204 */       return 10;
/*     */     }
/* 206 */     if (value.equalsIgnoreCase("datetime")) {
/* 207 */       return 10;
/*     */     }
/* 209 */     if (value.equalsIgnoreCase("string")) {
/* 210 */       return 11;
/*     */     }
/* 212 */     if (value.equalsIgnoreCase("binary")) {
/* 213 */       return 12;
/*     */     }
/* 215 */     if (value.equalsIgnoreCase("blob")) {
/* 216 */       return 13;
/*     */     }
/* 218 */     if (value.equalsIgnoreCase("clob"))
/* 219 */       return 14; 
/* 220 */     if (value.equalsIgnoreCase("timestamp")) {
/* 221 */       return 16;
/*     */     }
/* 223 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseFieldSubType(String value) {
/* 228 */     if (value.equalsIgnoreCase("date")) {
/* 229 */       return 8;
/*     */     }
/* 231 */     if (value.equalsIgnoreCase("time")) {
/* 232 */       return 9;
/*     */     }
/* 234 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String unparseFieldType(int type) {
/* 239 */     switch (type) {
/*     */       
/*     */       case 1:
/* 242 */         return "byte";
/*     */       case 2:
/* 244 */         return "smallint";
/*     */       case 3:
/* 246 */         return "integer";
/*     */       case 4:
/* 248 */         return "int64";
/*     */       case 5:
/* 250 */         return "float";
/*     */       case 6:
/* 252 */         return "double";
/*     */       case 7:
/* 254 */         return "numeric";
/*     */       case 8:
/* 256 */         return "date";
/*     */       case 9:
/* 258 */         return "time";
/*     */       case 10:
/* 260 */         return "datetime";
/*     */       case 12:
/* 262 */         return "binary";
/*     */       case 13:
/* 264 */         return "blob";
/*     */       case 14:
/* 266 */         return "clob";
/*     */       case 16:
/* 268 */         return "timestamp";
/*     */     } 
/*     */     
/* 271 */     return "string";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String unparseFieldLbsType(int lbsType) {
/* 277 */     switch (lbsType) {
/*     */       
/*     */       case 0:
/* 280 */         return null;
/*     */       case 5:
/* 282 */         return "lbs_auxcode";
/*     */       case 2:
/* 284 */         return "lbs_code";
/*     */       case 3:
/* 286 */         return "lbs_description";
/*     */       case 4:
/* 288 */         return "lbs_enumeration";
/*     */       case 6:
/* 290 */         return "lbs_groupcode";
/*     */       case 1:
/* 292 */         return "lbs_logicalref";
/*     */       case 7:
/* 294 */         return "lbs_slipnr";
/*     */     } 
/* 296 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String unparseAccessType(int type) {
/* 302 */     switch (type) {
/*     */       
/*     */       case 1:
/* 305 */         return "Byte";
/*     */       case 3:
/* 307 */         return "Integer";
/*     */       case 5:
/* 309 */         return "Float";
/*     */       case 6:
/* 311 */         return "Double";
/*     */       case 7:
/* 313 */         return "BigDecimal";
/*     */       case 10:
/* 315 */         return "Calendar";
/*     */       case 12:
/* 317 */         return "byte-array";
/*     */       case 11:
/* 319 */         return "String";
/*     */       case 16:
/* 321 */         return "Timestamp";
/*     */     } 
/*     */     
/* 324 */     return "default";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean printSize(int lbsType) {
/* 330 */     switch (lbsType) {
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 5:
/*     */       case 6:
/* 336 */         return false;
/*     */     } 
/* 338 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSizeRequired(int type) {
/* 344 */     switch (type) {
/*     */       
/*     */       case 11:
/*     */       case 12:
/* 348 */         return true;
/*     */     } 
/*     */     
/* 351 */     return false;
/*     */   }
/*     */   public static Object getMaximumValue(int type, int size) {
/*     */     StringBuilder buffer;
/*     */     int i;
/* 356 */     switch (type) {
/*     */       
/*     */       case 1:
/* 359 */         return Byte.valueOf(127);
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 363 */         return Integer.valueOf(2147483647);
/*     */       
/*     */       case 5:
/* 366 */         return new Float(Float.MAX_VALUE);
/*     */       case 6:
/* 368 */         return new Double(Double.MAX_VALUE);
/*     */       case 7:
/* 370 */         return new BigDecimal(Double.MAX_VALUE);
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 374 */         return new GregorianCalendar(2100, 0, 1);
/*     */       
/*     */       case 16:
/* 377 */         return new Timestamp(2147483648L);
/*     */       
/*     */       case 12:
/*     */       case 13:
/* 381 */         return null;
/*     */       
/*     */       case 14:
/* 384 */         return "ZZZZZ";
/*     */ 
/*     */       
/*     */       case 11:
/* 388 */         buffer = new StringBuilder();
/* 389 */         buffer.append("Z");
/* 390 */         for (i = 1; i < size; i++) {
/* 391 */           buffer.append("Z");
/*     */         }
/* 393 */         return buffer.toString();
/*     */     } 
/*     */     
/* 396 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getMinimumValue(int type) {
/* 401 */     switch (type) {
/*     */       
/*     */       case 1:
/* 404 */         return Byte.valueOf(-128);
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 408 */         return Integer.valueOf(-2147483648);
/*     */       
/*     */       case 5:
/* 411 */         return new Float(Float.MIN_VALUE);
/*     */       case 6:
/* 413 */         return new Double(Double.MIN_VALUE);
/*     */       case 7:
/* 415 */         return new BigDecimal(Double.MIN_VALUE);
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 419 */         return new GregorianCalendar(1900, 0, 1);
/*     */       case 16:
/* 421 */         return new Timestamp(0L);
/*     */       
/*     */       case 12:
/*     */       case 13:
/* 425 */         return null;
/*     */       
/*     */       case 14:
/* 428 */         return "";
/*     */       
/*     */       case 11:
/* 431 */         return "";
/*     */     } 
/*     */     
/* 434 */     return null;
/*     */   }
/*     */   public static Object getDefaultValue(int type, boolean allowsNulls) {
/*     */     Date d;
/*     */     long date;
/* 439 */     if (allowsNulls) {
/* 440 */       return null;
/*     */     }
/* 442 */     switch (type) {
/*     */       
/*     */       case 1:
/* 445 */         return Byte.valueOf((byte)0);
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 449 */         return Integer.valueOf(0);
/*     */       
/*     */       case 4:
/* 452 */         return Long.valueOf(0L);
/*     */       
/*     */       case 5:
/* 455 */         return new Float(0.0F);
/*     */       
/*     */       case 6:
/* 458 */         return new Double(0.0D);
/*     */       
/*     */       case 7:
/* 461 */         return new BigDecimal(0);
/*     */       
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 466 */         return new GregorianCalendar(1900, 0, 1);
/*     */       case 16:
/* 468 */         d = new Date();
/* 469 */         date = d.getTime();
/* 470 */         return new Timestamp(date);
/*     */       
/*     */       case 12:
/*     */       case 13:
/* 474 */         return null;
/*     */       
/*     */       case 11:
/*     */       case 14:
/* 478 */         return "";
/*     */     } 
/*     */     
/* 481 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBFieldHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */