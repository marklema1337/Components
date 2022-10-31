/*     */ package com.lbs.data.expression;
/*     */ 
/*     */ import com.lbs.data.DataConstants;
/*     */ import com.lbs.data.database.DBCommandException;
/*     */ import com.lbs.data.database.DBEntity;
/*     */ import com.lbs.data.query.select.SelectQueryColumn;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.util.Calendar;
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
/*     */ public class QueryItemGenerator
/*     */   implements IQueryItemGenerator
/*     */ {
/*     */   public String generate(IQueryItemGenerator descentGenerator, QueryItem item, QueryItemParams itemParams) throws DBCommandException {
/*     */     String main;
/*     */     QueryItem commandItem;
/*     */     String param1, param2;
/*     */     Calendar date;
/*     */     QueryItem newItem;
/*     */     String str;
/*     */     StringBuilder sb;
/*     */     int cnt, i;
/*  33 */     String subStr = null;
/*  34 */     switch (item.m_Type) {
/*     */       
/*     */       case 0:
/*  37 */         return (String)item.m_Value;
/*     */       
/*     */       case 1:
/*  40 */         if (itemParams != null && itemParams.isQualifyColumns()) {
/*  41 */           return processBinary(descentGenerator, item, ".", false, itemParams);
/*     */         }
/*     */         
/*  44 */         return processSubItem(descentGenerator, item, 1, itemParams);
/*     */       
/*     */       case 102:
/*  47 */         return processSubItem(descentGenerator, item, 0, itemParams);
/*     */       
/*     */       case 2:
/*  50 */         return processBinary(descentGenerator, item, " ", false, itemParams);
/*     */       
/*     */       case 90:
/*  53 */         return "$P(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 92:
/*  55 */         return "$V(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 91:
/*  57 */         return "$Q(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 94:
/*  59 */         return processValue(descentGenerator, item, itemParams);
/*     */       
/*     */       case 93:
/*  62 */         return "$W(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       
/*     */       case 4:
/*  65 */         return processBinary(descentGenerator, item, " AS ", false, itemParams);
/*     */       
/*     */       case 10:
/*  68 */         return "$T(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 17:
/*  70 */         return "$T(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 16:
/*  72 */         return "$T(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       
/*     */       case 11:
/*  75 */         return "$I(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 12:
/*  77 */         return "$F(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 19:
/*  79 */         return processSubItem(descentGenerator, item, 0, itemParams);
/*     */       case 14:
/*  81 */         return "$A(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       case 15:
/*  83 */         return "$B(" + processValue(descentGenerator, item, itemParams) + ")";
/*     */       
/*     */       case 20:
/*  86 */         main = processSubItem(descentGenerator, item, 0, itemParams);
/*     */         
/*  88 */         if (item.getSubItems().size() > 1 && JLbsConstants.DB_INDEX_HINTS_ENABLED) {
/*     */           
/*  90 */           String hint = processSubItem(descentGenerator, item, 1, itemParams);
/*  91 */           String value = "INDEX(" + hint + ")";
/*  92 */           return main + (isDisabledNoLock(itemParams) ? value : (" WITH(" + 
/*     */             
/*  94 */             checkForNoLock(item, "NOLOCK") + ", " + value + ")"));
/*     */         } 
/*  96 */         return main + (isDisabledNoLock(itemParams) ? "" : (" WITH(" + 
/*     */           
/*  98 */           checkForNoLock(item, "NOLOCK") + ")"));
/*     */       
/*     */       case 27:
/* 101 */         return processSubItem(descentGenerator, item, 0, itemParams);
/*     */       
/*     */       case 22:
/* 104 */         return " INNER JOIN " + processBinary(descentGenerator, item, " ON ", false, itemParams);
/*     */       
/*     */       case 23:
/* 107 */         return " LEFT OUTER JOIN " + processBinary(descentGenerator, item, " ON ", false, itemParams);
/*     */       
/*     */       case 25:
/* 110 */         return " RIGHT OUTER JOIN " + processBinary(descentGenerator, item, " ON ", false, itemParams);
/*     */       
/*     */       case 21:
/* 113 */         if (DataConstants.NATURAL_JOINS) {
/* 114 */           return " JOIN " + processBinary(descentGenerator, item, " ON ", false, itemParams);
/*     */         }
/* 116 */         return ", " + processSubItem(descentGenerator, item, 0, itemParams);
/*     */       
/*     */       case 26:
/* 119 */         commandItem = (QueryItem)item.getValue();
/* 120 */         return "(" + generate(descentGenerator, commandItem, itemParams) + ")";
/*     */       
/*     */       case 501:
/* 123 */         return processFunctionCall(descentGenerator, item, "STRLEN", itemParams);
/*     */       
/*     */       case 502:
/* 126 */         return processFunctionCall(descentGenerator, item, "TODATE", itemParams);
/*     */       
/*     */       case 517:
/* 129 */         return processFunctionCall(descentGenerator, item, "TRUNCDATE", itemParams);
/*     */       
/*     */       case 503:
/* 132 */         return processFunctionCall(descentGenerator, item, "DATEDIFF", itemParams);
/*     */       
/*     */       case 519:
/* 135 */         return processFunctionCall(descentGenerator, item, (String)item.getValue(), itemParams);
/*     */       
/*     */       case 515:
/* 138 */         param1 = processSubItem(descentGenerator, item, 0, itemParams);
/* 139 */         param2 = removeQuotes(param1);
/* 140 */         return param2;
/*     */       
/*     */       case 504:
/* 143 */         return processBinary(descentGenerator, item, " LIKE ", true, itemParams);
/*     */       
/*     */       case 518:
/* 146 */         return processBinary(descentGenerator, item, " NOT LIKE ", true, itemParams);
/*     */       
/*     */       case 508:
/* 149 */         return processFunctionCall(descentGenerator, item, "REPLACE", itemParams);
/*     */       
/*     */       case 509:
/* 152 */         return processFunctionCall(descentGenerator, item, "ABS", itemParams);
/*     */       
/*     */       case 510:
/* 155 */         return processFunctionCall(descentGenerator, item, "CONCAT", itemParams);
/*     */       
/*     */       case 521:
/* 158 */         return processFunctionCall(descentGenerator, item, "U_IFTHENELSE", itemParams);
/*     */       
/*     */       case 511:
/*     */       case 520:
/* 162 */         return processFunctionCall(descentGenerator, item, "COALESCE", itemParams);
/*     */       
/*     */       case 512:
/* 165 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 166 */         return "DAY(" + subStr + ")";
/*     */       
/*     */       case 513:
/* 169 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 170 */         return "MONTH(" + subStr + ")";
/*     */       
/*     */       case 514:
/* 173 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 174 */         return "YEAR(" + subStr + ")";
/*     */       
/*     */       case 516:
/* 177 */         return "CURRENT_DATE";
/*     */       
/*     */       case 234:
/* 180 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 181 */         return subStr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 50:
/* 188 */         return processUnary(descentGenerator, item, " AVG", false, itemParams);
/*     */       case 51:
/* 190 */         return processUnary(descentGenerator, item, " SUM", false, itemParams);
/*     */       case 52:
/* 192 */         return processUnary(descentGenerator, item, " MAX", false, itemParams);
/*     */       case 53:
/* 194 */         return processUnary(descentGenerator, item, " MIN", false, itemParams);
/*     */       case 54:
/*     */       case 59:
/* 197 */         return processUnary(descentGenerator, item, " COUNT", false, itemParams);
/*     */       case 56:
/* 199 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 200 */         return "COUNT(DISTINCT " + subStr + ")";
/*     */       case 57:
/* 202 */         subStr = processSubItem(descentGenerator, item, 0, itemParams);
/* 203 */         return subStr;
/*     */       case 55:
/* 205 */         return "COUNT(*)";
/*     */       case 58:
/* 207 */         return "NULL";
/*     */       case 70:
/* 209 */         return processNary(descentGenerator, item, " AND ", true, itemParams);
/*     */       case 71:
/* 211 */         return processNary(descentGenerator, item, " OR ", true, itemParams);
/*     */       case 72:
/* 213 */         return "(NOT (" + processSubItem(descentGenerator, item, 0, itemParams) + "))";
/*     */       
/*     */       case 40:
/* 216 */         return processBinary(descentGenerator, item, " = ", true, itemParams);
/*     */       case 41:
/* 218 */         return processBinary(descentGenerator, item, " < ", true, itemParams);
/*     */       case 42:
/* 220 */         return processBinary(descentGenerator, item, " <= ", true, itemParams);
/*     */       case 43:
/* 222 */         return processBinary(descentGenerator, item, " > ", true, itemParams);
/*     */       case 44:
/* 224 */         return processBinary(descentGenerator, item, " >= ", true, itemParams);
/*     */       case 45:
/* 226 */         return processBinary(descentGenerator, item, " <> ", true, itemParams);
/*     */       
/*     */       case 46:
/* 229 */         return "(" + processSubItem(descentGenerator, item, 0, itemParams) + " IN " + 
/* 230 */           processSubItem(descentGenerator, item, 1, itemParams) + ")";
/*     */       case 48:
/* 232 */         return "(" + processSubItem(descentGenerator, item, 0, itemParams) + " NOT IN " + 
/* 233 */           processSubItem(descentGenerator, item, 1, itemParams) + ")";
/*     */       
/*     */       case 47:
/* 236 */         return "(" + processSubItem(descentGenerator, item, 0, itemParams) + " IS " + 
/* 237 */           processSubItem(descentGenerator, item, 1, itemParams) + ")";
/*     */       
/*     */       case 100:
/* 240 */         return processList(descentGenerator, item, 0, true, itemParams);
/*     */       
/*     */       case 60:
/* 243 */         return processSubItem(descentGenerator, item, 0, itemParams) + " ASC";
/*     */       case 61:
/* 245 */         return processSubItem(descentGenerator, item, 0, itemParams) + " DESC";
/*     */       
/*     */       case 80:
/* 248 */         return "\"" + item.m_Value + "\"";
/*     */       
/*     */       case 81:
/* 251 */         date = (Calendar)item.m_Value;
/* 252 */         return "'" + StringUtil.toCanonicalString(date) + "'";
/*     */       
/*     */       case 82:
/*     */       case 83:
/* 256 */         return item.m_Value.toString();
/*     */       
/*     */       case 84:
/* 259 */         return "NULL";
/*     */       
/*     */       case 110:
/* 262 */         return processNary(descentGenerator, item, " + ", true, itemParams);
/*     */       
/*     */       case 111:
/* 265 */         return processNary(descentGenerator, item, " - ", true, itemParams);
/*     */       
/*     */       case 112:
/* 268 */         return processNary(descentGenerator, item, " / ", true, itemParams);
/*     */       
/*     */       case 113:
/* 271 */         return processNary(descentGenerator, item, " * ", true, itemParams);
/*     */       
/*     */       case 114:
/* 274 */         return processBinary(descentGenerator, item, " % ", true, itemParams);
/*     */       
/*     */       case 115:
/* 277 */         return "-" + processSubItem(descentGenerator, item, 0, itemParams);
/*     */       
/*     */       case 116:
/* 280 */         return processBinary(descentGenerator, item, " & ", true, itemParams);
/*     */       
/*     */       case 121:
/* 283 */         newItem = new QueryItem(81, new GregorianCalendar(1901, 0, 1));
/* 284 */         return generate(descentGenerator, new QueryItem(502, newItem, null), itemParams);
/*     */       
/*     */       case 120:
/* 287 */         newItem = new QueryItem(81, new GregorianCalendar(2199, 11, 31));
/* 288 */         return generate(descentGenerator, new QueryItem(502, newItem, null), itemParams);
/*     */       
/*     */       case 122:
/* 291 */         newItem = new QueryItem(81, new GregorianCalendar());
/* 292 */         return generate(descentGenerator, new QueryItem(502, newItem, null), itemParams);
/*     */       
/*     */       case 8:
/* 295 */         str = processSubItem(descentGenerator, item, 0, itemParams);
/* 296 */         return processSubItem(descentGenerator, item, 1, itemParams) + " AS \n" + str;
/*     */       
/*     */       case 5:
/* 299 */         sb = new StringBuilder();
/*     */ 
/*     */ 
/*     */         
/* 303 */         sb.append(" CASE ");
/* 304 */         str = processSubItem(descentGenerator, item, 0, itemParams);
/* 305 */         sb.append(str + "\n");
/* 306 */         cnt = item.getSubItems().size();
/* 307 */         for (i = 1; i < cnt; i++) {
/*     */           
/* 309 */           newItem = item.getSubItems().get(i);
/* 310 */           sb.append("\t" + generate(descentGenerator, newItem, itemParams) + "\n");
/*     */         } 
/* 312 */         sb.append(" END\n");
/*     */         
/* 314 */         return sb.toString();
/*     */       
/*     */       case 6:
/* 317 */         return "WHEN " + processSubItem(descentGenerator, item, 0, itemParams) + " THEN " + 
/* 318 */           processSubItem(descentGenerator, item, 1, itemParams);
/*     */       
/*     */       case 7:
/* 321 */         return "ELSE " + processSubItem(descentGenerator, item, 0, itemParams);
/*     */       
/*     */       case 9:
/* 324 */         return processBinary(descentGenerator, item, " = ", false, itemParams);
/*     */       
/*     */       case 238:
/*     */       case 239:
/* 328 */         return processUnionSelect(descentGenerator, item);
/*     */       
/*     */       case 200:
/* 331 */         return processSelect(descentGenerator, item);
/*     */       
/*     */       case 202:
/* 334 */         return processUpdate(descentGenerator, item, itemParams);
/*     */       
/*     */       case 201:
/* 337 */         return processInsert(descentGenerator, item, itemParams);
/*     */       
/*     */       case 203:
/* 340 */         return processDelete(descentGenerator, item, itemParams);
/*     */     } 
/*     */     
/* 343 */     return (item.m_Value == null) ? "" : item.m_Value
/*     */       
/* 345 */       .toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String checkForNoLock(QueryItem item, String noLock) {
/* 350 */     return noLock;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isDisabledNoLock(QueryItemParams itemParams) {
/* 355 */     return (itemParams != null && itemParams.isDisableNoLock());
/*     */   }
/*     */ 
/*     */   
/*     */   private String removeQuotes(String param1) {
/* 360 */     String param2 = "";
/* 361 */     if (!StringUtil.isEmpty(param1))
/*     */     {
/* 363 */       if (param1.charAt(0) == '\'') {
/*     */ 
/*     */         
/* 366 */         param2 = param1.substring(param1.indexOf('\'') + 1);
/* 367 */         int l = param2.length();
/* 368 */         param2 = param2.substring(0, l - 1);
/*     */       } 
/*     */     }
/* 371 */     return param2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String processValue(IQueryItemGenerator descentGenerator, QueryItem item, QueryItemParams itemParams) throws DBCommandException {
/* 378 */     Object value = item.m_Value;
/* 379 */     if (value instanceof QueryItem) {
/* 380 */       return descentGenerator.generate(descentGenerator, (QueryItem)value, itemParams);
/*     */     }
/* 382 */     return value.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processSubItem(IQueryItemGenerator descentGenerator, QueryItem item, int index, QueryItemParams itemParams) throws DBCommandException {
/* 388 */     if (item.m_SubItems == null || index >= item.m_SubItems.size()) {
/* 389 */       return "";
/*     */     }
/* 391 */     QueryItem subItem = item.m_SubItems.get(index);
/* 392 */     return descentGenerator.generate(descentGenerator, subItem, itemParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processUnary(IQueryItemGenerator descentGenerator, QueryItem item, String op, boolean parans, QueryItemParams itemParams) throws DBCommandException {
/* 398 */     QueryItem lItem = item.m_SubItems.get(0);
/*     */     
/* 400 */     String str = op + "(" + descentGenerator.generate(descentGenerator, lItem, itemParams) + ")";
/*     */     
/* 402 */     if (parans) {
/* 403 */       str = "(" + str + ")";
/*     */     }
/* 405 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getEscapeString() {
/* 410 */     return "\"";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String processBinary(IQueryItemGenerator descentGenerator, QueryItem item, String op, boolean parans, QueryItemParams itemParams) throws DBCommandException {
/* 416 */     QueryItem lItem = item.m_SubItems.get(0);
/* 417 */     QueryItem rItem = item.m_SubItems.get(1);
/*     */     
/* 419 */     String str = null;
/*     */     
/* 421 */     if (item.getType() == 4 || item.getType() == 1) {
/*     */ 
/*     */       
/* 424 */       str = descentGenerator.generate(descentGenerator, lItem, itemParams) + op + getEscapeString() + descentGenerator.generate(descentGenerator, rItem, itemParams) + getEscapeString();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 429 */       str = descentGenerator.generate(descentGenerator, lItem, itemParams) + op + descentGenerator.generate(descentGenerator, rItem, itemParams);
/*     */     } 
/*     */     
/* 432 */     if (parans) {
/* 433 */       str = "(" + str + ")";
/*     */     }
/* 435 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processTriary(IQueryItemGenerator descentGenerator, QueryItem item, String op1, String op2, boolean parans, QueryItemParams itemParams) throws DBCommandException {
/* 441 */     QueryItem item1 = item.m_SubItems.get(0);
/* 442 */     QueryItem item2 = item.m_SubItems.get(1);
/* 443 */     QueryItem item3 = item.m_SubItems.get(2);
/*     */ 
/*     */ 
/*     */     
/* 447 */     String str = descentGenerator.generate(descentGenerator, item1, itemParams) + op1 + descentGenerator.generate(descentGenerator, item2, itemParams) + op2 + descentGenerator.generate(descentGenerator, item3, itemParams);
/*     */     
/* 449 */     if (parans) {
/* 450 */       str = "(" + str + ")";
/*     */     }
/* 452 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processNary(IQueryItemGenerator descentGenerator, QueryItem item, String op, boolean parans, QueryItemParams itemParams) throws DBCommandException {
/* 458 */     int cnt = item.m_SubItems.size();
/*     */     
/* 460 */     if (cnt == 1) {
/* 461 */       return descentGenerator.generate(descentGenerator, item.m_SubItems.get(0), itemParams);
/*     */     }
/* 463 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */     
/* 466 */     if (parans) {
/* 467 */       sb.append("(");
/*     */     }
/* 469 */     for (int i = 0; i < cnt; i++) {
/*     */       
/* 471 */       QueryItem curItem = item.m_SubItems.get(i);
/* 472 */       sb.append(descentGenerator.generate(descentGenerator, curItem, itemParams));
/*     */       
/* 474 */       if (i < cnt - 1) {
/* 475 */         sb.append(op);
/*     */       }
/*     */     } 
/* 478 */     if (parans) {
/* 479 */       sb.append(")");
/*     */     }
/* 481 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processFunctionCall(IQueryItemGenerator descentGenerator, QueryItem item, String funcName, QueryItemParams itemParams) throws DBCommandException {
/* 487 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 489 */     if (item.getType() == 519) {
/*     */       
/* 491 */       DBEntity funcEntity = new DBEntity(9);
/* 492 */       funcEntity.setPhysicalName(funcName);
/* 493 */       QueryItem functionItem = new QueryItem(101, funcEntity);
/* 494 */       sb.append(descentGenerator.generate(descentGenerator, functionItem, itemParams));
/*     */     } else {
/*     */       
/* 497 */       sb.append(funcName);
/* 498 */     }  sb.append(processList(descentGenerator, item, 0, true, itemParams));
/*     */     
/* 500 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String processList(IQueryItemGenerator descentGenerator, QueryItem item, int startIndex, boolean parans, QueryItemParams itemParams) throws DBCommandException {
/* 506 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 508 */     if (parans) {
/* 509 */       sb.append("(");
/*     */     }
/* 511 */     int cnt = item.m_SubItems.size();
/*     */ 
/*     */     
/* 514 */     for (int i = startIndex; i < cnt; i++) {
/*     */       
/* 516 */       QueryItem curItem = item.m_SubItems.get(i);
/* 517 */       sb.append(descentGenerator.generate(descentGenerator, curItem, itemParams));
/*     */       
/* 519 */       if (i < cnt - 1) {
/* 520 */         sb.append(", ");
/*     */       }
/*     */     } 
/* 523 */     if (parans) {
/* 524 */       sb.append(")");
/*     */     }
/* 526 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String addToClause(String clause, QueryItem item, IQueryItemGenerator itemGen, String separator, boolean paranthesis, QueryItemParams itemParams) throws DBCommandException {
/*     */     String entity;
/* 534 */     if (item != null && item.getValue() instanceof SelectQueryColumn) {
/*     */       
/* 536 */       SelectQueryColumn column = (SelectQueryColumn)item.getValue();
/* 537 */       if (!JLbsStringUtil.isEmpty(column.getExpressionValue())) {
/* 538 */         entity = itemGen.generate(itemGen, column.getExpression(), itemParams);
/*     */       } else {
/* 540 */         entity = itemGen.generate(itemGen, item, itemParams);
/*     */       } 
/*     */     } else {
/* 543 */       entity = itemGen.generate(itemGen, item, itemParams);
/*     */     } 
/* 545 */     if (StringUtil.isWhiteSpace(clause)) {
/* 546 */       clause = entity;
/*     */     } else {
/*     */       
/* 549 */       if (paranthesis) {
/* 550 */         clause = "(" + clause + ")";
/*     */       }
/* 552 */       if (!StringUtil.isWhiteSpace(entity)) {
/* 553 */         clause = clause + separator + entity;
/*     */       }
/*     */     } 
/* 556 */     return clause;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String generateClause(IQueryItemGenerator itemGen, QueryItem item, String separator, boolean paranthesis, QueryItemParams itemParams) throws DBCommandException {
/* 562 */     String clause = "";
/*     */     
/* 564 */     if (item == null) {
/* 565 */       return clause;
/*     */     }
/* 567 */     QueryItemList list = item.getSubItems();
/*     */     
/* 569 */     if (list == null) {
/* 570 */       return clause;
/*     */     }
/* 572 */     for (int i = 0; i < list.size(); i++) {
/* 573 */       clause = addToClause(clause, list.get(i), itemGen, separator, paranthesis, itemParams);
/*     */     }
/* 575 */     return clause;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlFrom(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 581 */     sb.append("\nFROM\n\t");
/* 582 */     QueryItem qryItem = item.getSubItems().getByType(222);
/*     */     
/* 584 */     sb.append(generateClause(descentGenerator, qryItem, "", false, itemParams));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlWhere(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 590 */     QueryItem qryItem = item.getSubItems().getByType(223);
/* 591 */     String where = generateClause(descentGenerator, qryItem, " AND ", false, itemParams);
/*     */     
/* 593 */     if (!StringUtil.isWhiteSpace(where)) {
/*     */       
/* 595 */       sb.append("\nWHERE\n\t");
/* 596 */       sb.append(where);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlOrderBy(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 603 */     QueryItem qryItem = item.getSubItems().getByType(225);
/* 604 */     String clause = generateClause(descentGenerator, qryItem, ", ", false, itemParams);
/* 605 */     if (!StringUtil.isWhiteSpace(clause)) {
/*     */       
/* 607 */       sb.append("\nORDER BY\n\t");
/* 608 */       sb.append(clause);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlSelectInitialize(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlSelectFinalize(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 620 */     if (itemParams.isRowCountQuery()) {
/*     */       
/* 622 */       sb.insert(0, "SELECT COUNT(*) FROM ( ");
/* 623 */       sb.append(" ) COUNT_SQL");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlGroupBy(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 630 */     QueryItem qryItem = item.getSubItems().getByType(224);
/* 631 */     String clause = generateClause(descentGenerator, qryItem, ", ", false, itemParams);
/* 632 */     if (!StringUtil.isWhiteSpace(clause)) {
/*     */       
/* 634 */       sb.append("\nGROUP BY\n\t");
/* 635 */       sb.append(clause);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlSelectDecoration(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendSqlSelect(IQueryItemGenerator descentGenerator, QueryItem item, StringBuilder sb, QueryItemParams itemParams) throws DBCommandException {
/* 648 */     QueryItem qryItem = item.getSubItems().getByType(221);
/* 649 */     String clause = generateClause(descentGenerator, qryItem, ", ", false, itemParams);
/*     */     
/* 651 */     sb.append("SELECT ");
/* 652 */     appendSqlSelectDecoration(descentGenerator, item, sb, itemParams);
/*     */     
/* 654 */     if (itemParams != null && itemParams.m_Distinct) {
/* 655 */       sb.append("DISTINCT ");
/*     */     }
/* 657 */     if (!StringUtil.isWhiteSpace(clause)) {
/*     */       
/* 659 */       sb.append("\n\t");
/* 660 */       sb.append(clause);
/*     */     } else {
/*     */       
/* 663 */       sb.append("*");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int getSqlRowLimit(QueryItem item) {
/* 668 */     QueryItem qryItem = item.getSubItems().getByType(229);
/* 669 */     if (qryItem != null) {
/* 670 */       return ((Integer)qryItem.getValue()).intValue();
/*     */     }
/* 672 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSqlStartRow(QueryItem item) {
/* 677 */     QueryItem qryItem = item.getSubItems().getByType(240);
/* 678 */     if (qryItem != null) {
/* 679 */       return ((Integer)qryItem.getValue()).intValue();
/*     */     }
/* 681 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isDistinct(QueryItem item) {
/* 686 */     QueryItem optItem = item.getSubItems().getByType(230);
/*     */     
/* 688 */     QueryItem distItem = null;
/*     */     
/* 690 */     if (optItem != null && optItem.getSubItems() != null) {
/* 691 */       distItem = optItem.getSubItems().getByType(231);
/*     */     }
/* 693 */     if (distItem != null) {
/* 694 */       return ((Boolean)distItem.getValue()).booleanValue();
/*     */     }
/* 696 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isRowCountQuery(QueryItem item) {
/* 701 */     QueryItem optItem = item.getSubItems().getByType(230);
/*     */     
/* 703 */     QueryItem rowCountItem = null;
/*     */     
/* 705 */     if (optItem != null && optItem.getSubItems() != null) {
/* 706 */       rowCountItem = optItem.getSubItems().getByType(236);
/*     */     }
/* 708 */     if (rowCountItem != null) {
/* 709 */       return ((Boolean)rowCountItem.getValue()).booleanValue();
/*     */     }
/* 711 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isDisableNoLock(QueryItem item) {
/* 716 */     QueryItem optItem = item.getSubItems().getByType(230);
/*     */     
/* 718 */     QueryItem disableNoLockItem = null;
/*     */     
/* 720 */     if (optItem != null && optItem.getSubItems() != null) {
/* 721 */       disableNoLockItem = optItem.getSubItems().getByType(237);
/*     */     }
/* 723 */     if (disableNoLockItem != null) {
/* 724 */       return ((Boolean)disableNoLockItem.getValue()).booleanValue();
/*     */     }
/* 726 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected QueryItem getMainTableItem(QueryItem item) {
/* 731 */     QueryItem qryItem = item.getSubItems().getByType(220);
/*     */     
/* 733 */     if (qryItem == null) {
/* 734 */       return null;
/*     */     }
/* 736 */     return (QueryItem)qryItem.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getMainTableName(QueryItem item, IQueryItemGenerator descentGenerator, QueryItemParams itemParams) throws DBCommandException {
/* 742 */     QueryItem qryItem = getMainTableItem(item);
/*     */     
/* 744 */     if (qryItem == null) {
/* 745 */       return null;
/*     */     }
/* 747 */     return descentGenerator.generate(descentGenerator, qryItem, itemParams);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String processUnionSelect(IQueryItemGenerator descentGenerator, QueryItem item) throws DBCommandException {
/* 752 */     QueryItemParams itemParams = processOptions(item);
/*     */     
/* 754 */     StringBuilder sb = new StringBuilder();
/* 755 */     appendSqlSelectInitialize(descentGenerator, item, sb, itemParams);
/*     */     
/* 757 */     String unionOp = "UNION";
/* 758 */     switch (item.getType()) {
/*     */       
/*     */       case 239:
/* 761 */         unionOp = "UNION ALL";
/*     */         break;
/*     */       case 238:
/* 764 */         unionOp = "UNION";
/*     */         break;
/*     */     } 
/*     */     
/* 768 */     QueryItemList subItems = item.getSubItems();
/* 769 */     for (int i = 0; i < subItems.size(); i++) {
/*     */       
/* 771 */       String subResult = processSubItem(descentGenerator, item, i, itemParams);
/* 772 */       if (subResult.startsWith("(") && subResult.endsWith(")")) {
/* 773 */         sb.append(subResult);
/*     */       } else {
/* 775 */         sb.append("(").append(subResult).append(")");
/* 776 */       }  if (i < subItems.size() - 1)
/*     */       {
/* 778 */         sb.append(" ").append(unionOp).append(" ");
/*     */       }
/*     */     } 
/* 781 */     appendSqlSelectFinalize(descentGenerator, item, sb, itemParams);
/*     */     
/* 783 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String processSelect(IQueryItemGenerator descentGenerator, QueryItem item) throws DBCommandException {
/* 788 */     QueryItemParams itemParams = processOptions(item);
/*     */     
/* 790 */     StringBuilder sb = new StringBuilder();
/* 791 */     appendSqlSelectInitialize(descentGenerator, item, sb, itemParams);
/* 792 */     appendSqlSelect(descentGenerator, item, sb, itemParams);
/* 793 */     appendSqlFrom(descentGenerator, item, sb, itemParams);
/* 794 */     appendSqlWhere(descentGenerator, item, sb, itemParams);
/* 795 */     appendSqlGroupBy(descentGenerator, item, sb, itemParams);
/* 796 */     appendSqlOrderBy(descentGenerator, item, sb, itemParams);
/* 797 */     appendSqlSelectFinalize(descentGenerator, item, sb, itemParams);
/*     */     
/* 799 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected QueryItemParams processOptions(QueryItem item) throws DBCommandException {
/* 804 */     QueryItemParams itemParams = new QueryItemParams();
/* 805 */     itemParams.setRowCount(getSqlRowLimit(item));
/* 806 */     itemParams.setStartRow(getSqlStartRow(item));
/* 807 */     itemParams.setDistinct(isDistinct(item));
/* 808 */     itemParams.setRowCountQuery(isRowCountQuery(item));
/* 809 */     itemParams.setDisableNoLock(isDisableNoLock(item));
/* 810 */     return itemParams;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String processUpdate(IQueryItemGenerator descentGenerator, QueryItem item, QueryItemParams itemParams) throws DBCommandException {
/* 819 */     if (itemParams == null) {
/* 820 */       itemParams = new QueryItemParams();
/*     */     }
/*     */     
/* 823 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 825 */     sb.append("UPDATE ");
/* 826 */     String tableName = getMainTableName(item, descentGenerator, itemParams);
/* 827 */     sb.append(tableName);
/*     */     
/* 829 */     sb.append("\nSET\n\t");
/* 830 */     QueryItem qryItem = item.getSubItems().getByType(226);
/* 831 */     sb.append(generateClause(descentGenerator, qryItem, ", ", false, itemParams));
/*     */     
/* 833 */     appendSqlFrom(descentGenerator, item, sb, itemParams);
/* 834 */     appendSqlWhere(descentGenerator, item, sb, itemParams);
/*     */     
/* 836 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String processInsert(IQueryItemGenerator descentGenerator, QueryItem item, QueryItemParams itemParams) throws DBCommandException {
/* 843 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 845 */     sb.append("INSERT INTO ");
/* 846 */     String tableName = getMainTableName(item, descentGenerator, itemParams);
/* 847 */     sb.append(tableName);
/*     */     
/* 849 */     QueryItem qryItem = item.getSubItems().getByType(227);
/* 850 */     sb.append("\n\t(" + generateClause(descentGenerator, qryItem, ", ", false, itemParams) + ")");
/*     */     
/* 852 */     qryItem = item.getSubItems().getByType(228);
/* 853 */     sb.append("\n\tVALUES (" + generateClause(descentGenerator, qryItem, ", ", false, itemParams) + ")");
/*     */     
/* 855 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String processDelete(IQueryItemGenerator descentGenerator, QueryItem item, QueryItemParams itemParams) throws DBCommandException {
/* 864 */     if (itemParams == null) {
/* 865 */       itemParams = new QueryItemParams();
/*     */     }
/* 867 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 869 */     sb.append("DELETE ");
/* 870 */     String tableName = getMainTableName(item, descentGenerator, itemParams);
/* 871 */     sb.append(tableName);
/*     */     
/* 873 */     appendSqlFrom(descentGenerator, item, sb, itemParams);
/* 874 */     appendSqlWhere(descentGenerator, item, sb, itemParams);
/*     */     
/* 876 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItemGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */