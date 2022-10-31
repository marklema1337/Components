/*      */ package com.lbs.expcalc;
/*      */ 
/*      */ import com.lbs.util.ConvertUtil;
/*      */ import com.lbs.util.JLbsStringListEx;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.math.BigDecimal;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Hashtable;
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
/*      */ public class LbsExpCalculator
/*      */ {
/*   82 */   public static final BigDecimal EPSILON = new BigDecimal(1.0E-8D);
/*   83 */   public static final BigDecimal ONE = new BigDecimal(1.0D);
/*   84 */   public static final BigDecimal ZERO = new BigDecimal(0.0D);
/*      */   
/*      */   public static final int EXPRESSION_SCALE = 32;
/*      */   
/*      */   private static final int FN_MIN = 1;
/*      */   
/*      */   private static final int FN_MAX = 2;
/*      */   
/*      */   private static final int FN_MOD = 3;
/*      */   
/*      */   private static final int FN_LDIV = 4;
/*      */   
/*      */   private static final int FN_ABS = 5;
/*      */   
/*      */   private static final int FN_VAL = 6;
/*      */   
/*      */   private static final int FN_DATE = 7;
/*      */   
/*      */   private static final int FN_AFTER = 8;
/*      */   
/*      */   private static final int FN_DAYS = 9;
/*      */   
/*      */   private static final int FN_DAYOF = 10;
/*      */   
/*      */   private static final int FN_MONTHOF = 11;
/*      */   
/*      */   private static final int FN_YEAROF = 12;
/*      */   
/*      */   private static final int FN_WDAYOF = 13;
/*      */   
/*      */   private static final int FN_ROUND = 14;
/*      */   
/*      */   private static final int FN_TRUNC = 15;
/*      */   
/*      */   private static final int FN_ERATE = 16;
/*      */   
/*      */   private static final int FN_CRATE = 17;
/*      */   
/*      */   private static final int FN_STRPOS = 18;
/*      */   
/*      */   private static final int FN_FLOOR = 19;
/*      */   
/*      */   private static final int FN_CEIL = 20;
/*      */   
/*      */   private static final int FN_FRAC = 21;
/*      */   
/*      */   private static final int FN_EXP = 22;
/*      */   
/*      */   private static final int FN_LN = 23;
/*      */   
/*      */   private static final int FN_POWER = 24;
/*      */   
/*      */   private static final int FN_SQR = 25;
/*      */   private static final int FN_SQRT = 26;
/*      */   private static final int FN_COS = 27;
/*      */   private static final int FN_SIN = 28;
/*      */   private static final int FN_TAN = 29;
/*      */   private static final int FN_STRLEN = 30;
/*      */   private static final int FN_WEEKNUM = 31;
/*      */   private static final int FN_STR = 51;
/*      */   private static final int FN_IF = 100;
/*      */   private static final int XCE_NOTRESOLVED = 1;
/*      */   private static final int XCE_RPAREXPEC = 2;
/*      */   private static final int XCE_LPAREXPEC = 3;
/*      */   private static final int XCE_DIVBYZERO = 4;
/*      */   private static final int XCE_INVALEXP = 6;
/*      */   private static final int XCE_INVALPTY = 7;
/*      */   public static final int XTYPE_NUM = 1;
/*      */   public static final int XTYPE_STR = 2;
/*      */   public static final int XTYPE_DATE = 3;
/*      */   private static final int OP_PLUS = 1;
/*      */   private static final int OP_MINUS = 2;
/*      */   private static final int OP_MULT = 3;
/*      */   private static final int OP_DIV = 4;
/*      */   private static final int OP_GEQUAL = 5;
/*      */   private static final int OP_LEQUAL = 6;
/*      */   private static final int OP_NEQUAL = 7;
/*      */   private static final int OP_EQUAL = 8;
/*      */   private static final int OP_GREAT = 9;
/*      */   private static final int OP_LESS = 10;
/*      */   private static final int OP_AND = 11;
/*      */   private static final int OP_OR = 12;
/*      */   private static final int OP_LPARTH = 13;
/*      */   private static final int OP_RPARTH = 14;
/*      */   private static final int FWEEK_JAN1 = 1;
/*      */   private static final int FWEEK_4DAY = 2;
/*  170 */   private Hashtable functions = null;
/*  171 */   private String seperators = null;
/*  172 */   private Hashtable operators = null;
/*      */   
/*      */   public static final String paramSeperator = ";";
/*  175 */   private ILbsExpCalcAdapter parserEvents = null;
/*  176 */   private Object sender = null;
/*      */   
/*  178 */   private int fCalcErr = 0;
/*  179 */   public String fCalcErrFunc = "";
/*      */ 
/*      */   
/*      */   private int daysBetween(GregorianCalendar begDate, GregorianCalendar endDate) {
/*  183 */     GregorianCalendar c = new GregorianCalendar();
/*      */     
/*  185 */     int begY = begDate.get(1);
/*  186 */     int endY = endDate.get(1);
/*  187 */     int numDays = 0;
/*      */     
/*  189 */     for (int i = begY; i <= endY; i++) {
/*      */       
/*  191 */       int begDay = 0;
/*  192 */       int endDay = 0;
/*      */       
/*  194 */       if (i == begY) {
/*  195 */         begDay = begDate.get(6);
/*      */       } else {
/*  197 */         begDay = 0;
/*      */       } 
/*  199 */       if (i == endY) {
/*  200 */         endDay = endDate.get(6);
/*      */       } else {
/*      */         
/*  203 */         c.set(i, 11, 31);
/*  204 */         endDay = c.get(6);
/*      */       } 
/*      */       
/*  207 */       numDays += endDay - begDay;
/*      */     } 
/*      */ 
/*      */     
/*  211 */     return numDays;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getNextPart(String funcDef, int fromPos) {
/*  216 */     int p = funcDef.indexOf(";", fromPos);
/*  217 */     if (p >= 0) {
/*  218 */       return new String(funcDef.substring(fromPos, p));
/*      */     }
/*  220 */     return new String("");
/*      */   }
/*      */ 
/*      */   
/*      */   private void addFunction(String funcDef, int fNumber) {
/*  225 */     LbsFunctionInfo funcInfo = new LbsFunctionInfo();
/*  226 */     int fPos = 0;
/*      */ 
/*      */ 
/*      */     
/*  230 */     funcInfo.setNr(fNumber);
/*      */     
/*  232 */     String nPart = getNextPart(funcDef, fPos);
/*  233 */     funcInfo.setName(nPart);
/*  234 */     fPos = fPos + nPart.length() + 1;
/*      */     
/*  236 */     nPart = getNextPart(funcDef, fPos);
/*  237 */     funcInfo.setType(Integer.valueOf(nPart).intValue());
/*  238 */     fPos = fPos + nPart.length() + 1;
/*      */     
/*  240 */     nPart = getNextPart(funcDef, fPos);
/*  241 */     funcInfo.setParamCount(Integer.valueOf(nPart).intValue());
/*  242 */     fPos = fPos + nPart.length() + 1;
/*      */     
/*  244 */     int[] pTypes = new int[10];
/*      */     
/*  246 */     if (funcInfo.getParamCount() == -1) {
/*      */       
/*  248 */       nPart = getNextPart(funcDef, fPos);
/*  249 */       pTypes[0] = Integer.valueOf(nPart).intValue();
/*  250 */       fPos = fPos + nPart.length() + 1;
/*      */     } else {
/*      */       
/*  253 */       for (int i = 0; i < funcInfo.getParamCount(); i++) {
/*      */         
/*  255 */         nPart = getNextPart(funcDef, fPos);
/*  256 */         pTypes[i] = Integer.valueOf(nPart).intValue();
/*  257 */         fPos = fPos + nPart.length() + 1;
/*      */       } 
/*      */     } 
/*  260 */     funcInfo.setParamTypes(pTypes);
/*  261 */     funcInfo.setExternal(false);
/*      */     
/*  263 */     nPart = funcDef.substring(fPos, funcDef.length());
/*  264 */     funcInfo.setPStr(nPart);
/*      */     
/*  266 */     this.functions.put(funcInfo.getName(), funcInfo);
/*      */   }
/*      */ 
/*      */   
/*      */   private void initializeFunctions() {
/*  271 */     addFunction("MIN;1;-1;1;<number;number...>", 1);
/*  272 */     addFunction("MAX;1;-1;1;<number;number...>", 2);
/*  273 */     addFunction("MOD;1;2;1;1;<number;divisor>", 3);
/*  274 */     addFunction("DIV;1;2;1;1;<number;divisor>", 4);
/*  275 */     addFunction("ABS;1;1;1;<number>", 5);
/*  276 */     addFunction("VAL;1;1;2;<text>", 6);
/*  277 */     addFunction("DATE;3;3;1;1;1;<day;month;year>", 7);
/*  278 */     addFunction("AFTER;1;2;1;1;<days;date>", 8);
/*  279 */     addFunction("DAYS;1;2;3;3;<first date;last date>", 9);
/*  280 */     addFunction("DAYOF;1;1;3;<date>", 10);
/*  281 */     addFunction("MONTHOF;1;1;3;<date>", 11);
/*  282 */     addFunction("YEAROF;1;1;3;<date>", 12);
/*  283 */     addFunction("WDAYOF;1;1;3;<date>", 13);
/*  284 */     addFunction("ROUND;1;1;1;<number>", 14);
/*  285 */     addFunction("TRUNC;1;1;1;<number>", 15);
/*  286 */     addFunction("ERATE;1;2;1;1;<date;currency>", 16);
/*  287 */     addFunction("CRATE;1;4;1;1;1;1;<date;base curr.;base rate;dest. curr.>", 17);
/*  288 */     addFunction("STRPOS;1;2;2;2;<search string; string>", 18);
/*  289 */     addFunction("FLOOR;1;1;1;<number>", 19);
/*  290 */     addFunction("CEIL;1;1;1;<number>", 20);
/*  291 */     addFunction("FRAC;1;1;1;<number>", 21);
/*  292 */     addFunction("EXP;1;1;1;<number>", 22);
/*  293 */     addFunction("LN;1;1;1;<number>", 23);
/*  294 */     addFunction("POWER;1;2;1;1;<base;exponent>", 24);
/*  295 */     addFunction("SQR;1;1;1;<number>", 25);
/*  296 */     addFunction("SQRT;1;1;1;<number>", 26);
/*  297 */     addFunction("COS;1;1;1;<number>", 27);
/*  298 */     addFunction("SIN;1;1;1;<number>", 28);
/*  299 */     addFunction("TAN;1;1;1;<number>", 29);
/*  300 */     addFunction("STRLEN;1;1;2;<text>", 30);
/*  301 */     addFunction("WEEKNUM;1;3;3;1;3;<year start;first week;date>", 31);
/*  302 */     addFunction("STR;2;1;1;<number>", 51);
/*  303 */     addFunction("DATESTR;2;2;3;1;<date;format>", 52);
/*  304 */     addFunction("MONTHSTR;2;1;1;<month>", 53);
/*  305 */     addFunction("WDAYSTR;2;1;1;<weekday>", 54);
/*  306 */     addFunction("NUMSTR;2;3;1;1;1;<number;decimals;format>", 55);
/*  307 */     addFunction("TIMESTR;2;2;1;1;<time;format>", 56);
/*  308 */     addFunction("RESXSTR;2;2;1;1;<list resource; tag>", 57);
/*  309 */     addFunction("RESSTR;2;1;1;<string resource>", 58);
/*  310 */     addFunction("CRESSTR;2;2;1;1;<list id.; tag>", 59);
/*  311 */     addFunction("SUBSTR;2;3;2;1;1;<text;start;length>", 60);
/*  312 */     addFunction("UPCASE;2;1;2;<text>", 61);
/*  313 */     addFunction("LOWCASE;2;1;2;<text>", 62);
/*  314 */     addFunction("TRIMSPC;2;2;2;1;<text;option>", 63);
/*  315 */     addFunction("JUSTIFY;2;4;2;1;2;1;<text;direction;fill;length>", 64);
/*  316 */     addFunction("WRNUM;2;3;1;1;1;<language;number;part>", 65);
/*  317 */     addFunction("IF;0;3;1;0;0;<expression;value1;value2>", 100);
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsStringListEx getFunctionList() {
/*  322 */     if (this.functions == null || this.functions.size() == 0) {
/*  323 */       return null;
/*      */     }
/*  325 */     Enumeration<LbsFunctionInfo> e = this.functions.elements();
/*  326 */     if (!e.hasMoreElements()) {
/*  327 */       return null;
/*      */     }
/*  329 */     JLbsStringListEx list = new JLbsStringListEx();
/*      */     
/*  331 */     while (e.hasMoreElements()) {
/*      */       
/*  333 */       LbsFunctionInfo fInfo = e.nextElement();
/*  334 */       String fStr = fInfo.name;
/*  335 */       String fDefStr = fInfo.name;
/*      */       
/*  337 */       for (int i = 0; i < fInfo.paramCount; i++) {
/*      */         String parType;
/*  339 */         if (i == 0) {
/*      */           
/*  341 */           fStr = String.valueOf(fStr) + "(";
/*  342 */           fDefStr = String.valueOf(fDefStr) + "(";
/*      */         } 
/*      */         
/*  345 */         switch (fInfo.paramTypes[i]) {
/*      */           
/*      */           case 1:
/*  348 */             parType = "numeric";
/*      */             break;
/*      */           case 2:
/*  351 */             parType = "string";
/*      */             break;
/*      */           case 3:
/*  354 */             parType = "date";
/*      */             break;
/*      */           default:
/*  357 */             parType = "";
/*      */             break;
/*      */         } 
/*  360 */         if (i < fInfo.paramCount - 1) {
/*  361 */           fStr = String.valueOf(fStr) + "; ";
/*      */         }
/*  363 */         if (i > 0) {
/*  364 */           fDefStr = String.valueOf(fDefStr) + "; " + parType;
/*      */         } else {
/*  366 */           fDefStr = String.valueOf(fDefStr) + parType;
/*      */         } 
/*  368 */         if (i == fInfo.paramCount - 1) {
/*      */           
/*  370 */           fStr = String.valueOf(fStr) + ")";
/*  371 */           fDefStr = String.valueOf(fDefStr) + ")";
/*      */         } 
/*      */       } 
/*      */       
/*  375 */       list.add(fStr, fInfo.nr, fDefStr);
/*      */     } 
/*  377 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public LbsExpCalculator() {
/*  382 */     this.functions = new Hashtable<>();
/*  383 */     this.seperators = new String(";+*-/()<>=\000");
/*      */     
/*  385 */     this.operators = new Hashtable<>();
/*  386 */     this.operators.put(Integer.valueOf(1), new String("+"));
/*  387 */     this.operators.put(Integer.valueOf(1), new String("+"));
/*  388 */     this.operators.put(Integer.valueOf(2), new String("-"));
/*  389 */     this.operators.put(Integer.valueOf(3), new String("*"));
/*  390 */     this.operators.put(Integer.valueOf(4), new String("/"));
/*  391 */     this.operators.put(Integer.valueOf(5), new String(">="));
/*  392 */     this.operators.put(Integer.valueOf(6), new String("<="));
/*  393 */     this.operators.put(Integer.valueOf(7), new String("<>"));
/*  394 */     this.operators.put(Integer.valueOf(8), new String("="));
/*  395 */     this.operators.put(Integer.valueOf(9), new String(">"));
/*  396 */     this.operators.put(Integer.valueOf(10), new String("<"));
/*  397 */     this.operators.put(Integer.valueOf(11), new String("&"));
/*  398 */     this.operators.put(Integer.valueOf(12), new String("|"));
/*  399 */     this.operators.put(Integer.valueOf(13), new String("("));
/*  400 */     this.operators.put(Integer.valueOf(14), new String(")"));
/*      */     
/*  402 */     initializeFunctions();
/*      */   }
/*      */ 
/*      */   
/*      */   public LbsExpCalculator(ILbsExpCalcAdapter adaptor, Object senderData) {
/*  407 */     this();
/*  408 */     this.parserEvents = adaptor;
/*  409 */     this.sender = senderData;
/*      */   }
/*      */ 
/*      */   
/*      */   private String nextToken(StringBuilder inStr) {
/*  414 */     String tkStr = new String("");
/*  415 */     String tempStr = new String(inStr);
/*  416 */     clearExp(inStr);
/*  417 */     inStr.append(tempStr.trim());
/*  418 */     boolean strToken = false;
/*      */     
/*  420 */     boolean done = false;
/*  421 */     int cnt = 1;
/*  422 */     int len = inStr.length();
/*  423 */     String sSet = new String(this.seperators);
/*  424 */     if (len > 0 && inStr.charAt(0) == '"') {
/*      */       
/*  426 */       sSet = "\"";
/*  427 */       tkStr = tkStr.concat(inStr.substring(cnt - 1, cnt));
/*  428 */       cnt++;
/*  429 */       strToken = true;
/*      */     } 
/*      */ 
/*      */     
/*      */     do {
/*  434 */       if (cnt == len + 1) {
/*  435 */         done = true;
/*      */       } else {
/*      */         
/*  438 */         tkStr = tkStr.concat(inStr.substring(cnt - 1, cnt));
/*  439 */         if (sSet.indexOf(inStr.substring(cnt - 1, cnt)) != -1) {
/*  440 */           done = true;
/*      */         
/*      */         }
/*  443 */         else if (cnt < len && sSet.indexOf(inStr.substring(cnt, cnt + 1)) != -1 && inStr.substring(cnt, cnt + 1) != "\"") {
/*  444 */           done = true;
/*      */         } 
/*  446 */         cnt++;
/*      */       }
/*      */     
/*  449 */     } while (!done);
/*      */     
/*  451 */     if (strToken && done && cnt <= len && inStr.substring(cnt - 1, cnt).compareTo("\"") == 0) {
/*      */       
/*  453 */       tkStr = tkStr.concat("\"");
/*  454 */       cnt++;
/*      */     } 
/*      */     
/*  457 */     if (cnt > 0)
/*  458 */       inStr.delete(0, cnt - 1); 
/*  459 */     return tkStr;
/*      */   }
/*      */ 
/*      */   
/*      */   private LbsFunctionInfo functionToken(String tok) {
/*  464 */     if (this.functions == null) {
/*  465 */       return null;
/*      */     }
/*  467 */     tok = JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(tok));
/*      */     
/*  469 */     Object funcObj = this.functions.get(tok);
/*      */     
/*  471 */     if (funcObj == null) {
/*  472 */       return null;
/*      */     }
/*  474 */     return (LbsFunctionInfo)funcObj;
/*      */   }
/*      */ 
/*      */   
/*      */   private LbsFunctionInfo externalFunctionToken(String tok) {
/*  479 */     tok = JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(tok));
/*  480 */     if (this.parserEvents != null) {
/*  481 */       return this.parserEvents.isExternalFunction(this.sender, tok);
/*      */     }
/*  483 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private LbsExpCalcValueType parameterType(LbsFunctionInfo func, int pIndex, LbsExpCalcValueType resTy) {
/*  488 */     LbsExpCalcValueType retVal = new LbsExpCalcValueType(0);
/*  489 */     if (func.getParamCount() <= 0) {
/*  490 */       retVal.setValueType(func.getParamTypes()[0]);
/*      */     } else {
/*      */       
/*  493 */       retVal.setValueType(func.getParamTypes()[pIndex]);
/*  494 */       if (retVal.getValueType() == 0) {
/*  495 */         retVal.setValueType(resTy.getValueType());
/*      */       }
/*      */     } 
/*  498 */     return retVal;
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal truncate(BigDecimal bd) {
/*  503 */     return bd.divide(new BigDecimal(1.0D), 1);
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal round(BigDecimal bd) {
/*  508 */     return bd.divide(new BigDecimal(1.0D), 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal floor(BigDecimal bd) {
/*  513 */     return bd.divide(new BigDecimal(1.0D), 3);
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal ceil(BigDecimal bd) {
/*  518 */     return bd.divide(new BigDecimal(1.0D), 2);
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal frac(BigDecimal bd) {
/*  523 */     return bd.subtract(truncate(bd));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal exp(BigDecimal bd) {
/*  528 */     return new BigDecimal(Math.exp(bd.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal ln(BigDecimal bd) {
/*  533 */     double dVal = bd.doubleValue();
/*  534 */     if (dVal > 0.0D) {
/*  535 */       return new BigDecimal(Math.log(dVal));
/*      */     }
/*  537 */     return new BigDecimal(Math.log(dVal));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal power(BigDecimal num, BigDecimal pw) {
/*  542 */     return new BigDecimal(Math.pow(num.doubleValue(), pw.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal sqr(BigDecimal bd) {
/*  547 */     return bd.multiply(bd);
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal sqrt(BigDecimal bd) {
/*  552 */     return new BigDecimal(Math.sqrt(bd.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal cos(BigDecimal bd) {
/*  557 */     return new BigDecimal(Math.cos(bd.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal sin(BigDecimal bd) {
/*  562 */     return new BigDecimal(Math.sin(bd.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal tan(BigDecimal bd) {
/*  567 */     return new BigDecimal(Math.tan(bd.doubleValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   private GregorianCalendar firstWeek(GregorianCalendar yearBeg) {
/*  572 */     GregorianCalendar dt = new GregorianCalendar();
/*  573 */     dt.set(yearBeg.get(1), yearBeg.get(2), yearBeg.get(5), 0, 0, 0);
/*      */     
/*  575 */     while (dt.get(7) != 2) {
/*  576 */       dt.add(5, 1);
/*      */     }
/*  578 */     return dt;
/*      */   }
/*      */ 
/*      */   
/*      */   private int compareDates(GregorianCalendar date1, GregorianCalendar date2) {
/*  583 */     int y1 = date1.get(1);
/*  584 */     int m1 = date1.get(2);
/*  585 */     int d1 = date1.get(5);
/*      */     
/*  587 */     int y2 = date2.get(1);
/*  588 */     int m2 = date2.get(2);
/*  589 */     int d2 = date2.get(5);
/*      */     
/*  591 */     if (y1 > y2)
/*  592 */       return 1; 
/*  593 */     if (y1 == y2 && m1 > m2)
/*  594 */       return 1; 
/*  595 */     if (y1 == y2 && m1 == m2 && d1 > d2)
/*  596 */       return 1; 
/*  597 */     if (y1 == y2 && m1 == m2 && d1 == d2) {
/*  598 */       return 0;
/*      */     }
/*  600 */     return -1;
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
/*      */   private BigDecimal weekNum(GregorianCalendar yearBeg, int wOpt, GregorianCalendar currentDate) {
/*  628 */     int wOfs = 0;
/*  629 */     int wNum = 0;
/*  630 */     GregorianCalendar fWeek = new GregorianCalendar();
/*      */     
/*  632 */     fWeek = firstWeek(yearBeg);
/*  633 */     if (compareDates(yearBeg, fWeek) == -1)
/*      */     {
/*  635 */       if (wOpt == 1) {
/*  636 */         wOfs = 1;
/*  637 */       } else if (wOpt == 2 && daysBetween(fWeek, yearBeg) >= 3) {
/*  638 */         wOfs = 1;
/*      */       } 
/*      */     }
/*  641 */     if (compareDates(currentDate, fWeek) == -1) {
/*  642 */       wNum = wOfs;
/*      */     } else {
/*      */       
/*  645 */       int diff = daysBetween(fWeek, currentDate);
/*  646 */       if (diff < 0)
/*  647 */         diff *= -1; 
/*  648 */       wNum = diff / 7 + 1;
/*  649 */       wNum += wOfs;
/*      */     } 
/*      */     
/*  652 */     return new BigDecimal(wNum);
/*      */   }
/*      */   
/*      */   private void feedValue(LbsFunctionInfo func, int paramIndex, LbsExpCalcValue[] parArray, LbsExpCalcValue parVal, LbsExpCalcValue retVal) {
/*      */     BigDecimal rightVal;
/*  657 */     if (retVal == null) {
/*      */       return;
/*      */     }
/*  660 */     copyValueTo(parVal, parArray[paramIndex - 1]);
/*  661 */     switch (func.getNr()) {
/*      */       
/*      */       case 1:
/*  664 */         if (paramIndex == 1) {
/*  665 */           copyValueTo(parVal, retVal); break;
/*  666 */         }  if (parVal.getNumericVal().compareTo(retVal.getNumericVal()) == -1) {
/*  667 */           copyValueTo(parVal, retVal);
/*      */         }
/*      */         break;
/*      */       case 2:
/*  671 */         if (paramIndex == 1) {
/*  672 */           copyValueTo(parVal, retVal); break;
/*  673 */         }  if (parVal.getNumericVal().compareTo(retVal.getNumericVal()) == 1)
/*  674 */           copyValueTo(parVal, retVal); 
/*      */         break;
/*      */       case 3:
/*  677 */         rightVal = truncate(parArray[1].getNumericVal());
/*      */         
/*  679 */         if (paramIndex == 2 && rightVal.compareTo(new BigDecimal(0.0D)) == 1) {
/*      */           
/*  681 */           BigDecimal leftVal = truncate(parArray[0].getNumericVal());
/*  682 */           retVal.setNumericVal(new BigDecimal(leftVal.longValue() % rightVal.longValue()));
/*      */         } 
/*      */         break;
/*      */       case 4:
/*  686 */         if (paramIndex == 2 && parVal.getNumericVal().abs().compareTo(new BigDecimal(1.0D)) >= 0)
/*      */         {
/*  688 */           retVal.setNumericVal(parArray[0].getNumericVal().divide(parVal.getNumericVal(), 1));
/*      */         }
/*      */         break;
/*      */       case 5:
/*  692 */         if (paramIndex == 1)
/*  693 */           retVal.setNumericVal(parVal.getNumericVal().abs()); 
/*      */         break;
/*      */       case 6:
/*  696 */         if (paramIndex == 1)
/*  697 */           retVal.setNumericVal(new BigDecimal(parVal.getStringVal())); 
/*      */         break;
/*      */       case 7:
/*  700 */         if (paramIndex == 3) {
/*      */           
/*  702 */           GregorianCalendar c = new GregorianCalendar();
/*      */           
/*  704 */           c.set(parArray[0].getNumericVal().intValue(), parArray[1].getNumericVal().intValue() - 1, parArray[2].getNumericVal().intValue(), 0, 0, 0);
/*  705 */           retVal.setCalendar(c);
/*  706 */           retVal.setExpType(3);
/*      */         } 
/*      */         break;
/*      */       case 8:
/*  710 */         if (paramIndex == 2) {
/*      */           
/*  712 */           GregorianCalendar c = parArray[1].getCalendar();
/*  713 */           c.add(5, parArray[0].getNumericVal().intValue());
/*  714 */           retVal.setCalendar(c);
/*  715 */           retVal.setExpType(3);
/*      */         } 
/*      */         break;
/*      */       case 9:
/*  719 */         if (paramIndex == 2)
/*  720 */           retVal.setNumericVal(new BigDecimal(daysBetween(parArray[0].getCalendar(), parArray[1].getCalendar()))); 
/*      */         break;
/*      */       case 10:
/*  723 */         if (paramIndex == 1)
/*      */         {
/*  725 */           retVal.setNumericVal(new BigDecimal(parArray[0].getCalendar().get(5)));
/*      */         }
/*      */         break;
/*      */       case 11:
/*  729 */         if (paramIndex == 1)
/*  730 */           retVal.setNumericVal(new BigDecimal(parArray[0].getCalendar().get(2) + 1)); 
/*      */         break;
/*      */       case 12:
/*  733 */         if (paramIndex == 1)
/*  734 */           retVal.setNumericVal(new BigDecimal(parArray[0].getCalendar().get(1))); 
/*      */         break;
/*      */       case 13:
/*  737 */         if (paramIndex == 1) {
/*      */           
/*  739 */           int day = parArray[0].getCalendar().get(7);
/*  740 */           day--;
/*  741 */           if (day == 0)
/*  742 */             day = 7; 
/*  743 */           retVal.setNumericVal(new BigDecimal(day));
/*      */         } 
/*      */         break;
/*      */       case 14:
/*  747 */         if (paramIndex == 1)
/*      */         {
/*  749 */           retVal.setNumericVal(round(parArray[0].getNumericVal()));
/*      */         }
/*      */         break;
/*      */       case 15:
/*  753 */         if (paramIndex == 1)
/*      */         {
/*  755 */           retVal.setNumericVal(truncate(parArray[0].getNumericVal()));
/*      */         }
/*      */         break;
/*      */       case 16:
/*  759 */         if (paramIndex == 1)
/*      */         {
/*  761 */           retVal.setNumericVal(new BigDecimal(0.0D));
/*      */         }
/*      */         break;
/*      */       case 17:
/*  765 */         if (paramIndex == 1)
/*      */         {
/*  767 */           retVal.setNumericVal(new BigDecimal(0.0D));
/*      */         }
/*      */         break;
/*      */       case 18:
/*  771 */         if (paramIndex == 2) {
/*      */           
/*  773 */           String s = parArray[1].getStringVal();
/*  774 */           if (s.length() != 0 && parArray[0].getStringVal().length() != 0)
/*  775 */             retVal.setNumericVal(new BigDecimal(s.indexOf(parArray[0].getStringVal()))); 
/*      */         } 
/*      */         break;
/*      */       case 19:
/*  779 */         if (paramIndex == 1)
/*  780 */           retVal.setNumericVal(floor(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 20:
/*  783 */         if (paramIndex == 1)
/*  784 */           retVal.setNumericVal(ceil(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 21:
/*  787 */         if (paramIndex == 1)
/*  788 */           retVal.setNumericVal(frac(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 22:
/*  791 */         if (paramIndex == 1)
/*  792 */           retVal.setNumericVal(exp(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 23:
/*  795 */         if (paramIndex == 1)
/*  796 */           retVal.setNumericVal(ln(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 24:
/*  799 */         if (paramIndex == 2)
/*  800 */           retVal.setNumericVal(power(parArray[0].getNumericVal(), parArray[1].getNumericVal())); 
/*      */         break;
/*      */       case 25:
/*  803 */         if (paramIndex == 1)
/*  804 */           retVal.setNumericVal(sqr(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 26:
/*  807 */         if (paramIndex == 1)
/*  808 */           retVal.setNumericVal(sqrt(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 27:
/*  811 */         if (paramIndex == 1)
/*  812 */           retVal.setNumericVal(cos(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 28:
/*  815 */         if (paramIndex == 1)
/*  816 */           retVal.setNumericVal(sin(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 29:
/*  819 */         if (paramIndex == 1)
/*  820 */           retVal.setNumericVal(tan(parArray[0].getNumericVal())); 
/*      */         break;
/*      */       case 30:
/*  823 */         if (paramIndex == 1)
/*  824 */           retVal.setNumericVal(new BigDecimal(parVal.getStringVal().length())); 
/*      */         break;
/*      */       case 31:
/*  827 */         if (paramIndex == 3)
/*  828 */           retVal.setNumericVal(weekNum(parArray[0].getCalendar(), parArray[1].getNumericVal().intValue(), parArray[2].getCalendar())); 
/*      */         break;
/*      */       case 51:
/*  831 */         if (paramIndex == 1)
/*  832 */           retVal.setStringVal(parVal.getNumericVal().toString()); 
/*      */         break;
/*      */       case 100:
/*  835 */         if (paramIndex == 3) {
/*      */           
/*  837 */           int fncType = parArray[1].getExpType();
/*      */           
/*  839 */           if (fncType == 1) {
/*  840 */             if (truncate(parArray[0].getNumericVal()).compareTo(new BigDecimal(0.0D)) != 0)
/*  841 */             { retVal.setNumericVal(parArray[1].getNumericVal()); }
/*      */             else
/*  843 */             { retVal.setNumericVal(parArray[2].getNumericVal()); } 
/*  844 */           } else if (fncType == 2) {
/*  845 */             if (truncate(parArray[0].getNumericVal()).compareTo(new BigDecimal(0.0D)) != 0)
/*  846 */             { retVal.setStringVal(parArray[1].getStringVal()); }
/*      */             else
/*  848 */             { retVal.setStringVal(parArray[2].getStringVal()); } 
/*  849 */           }  retVal.setExpType(fncType);
/*      */         } 
/*      */         break;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void calculateUserFunction(LbsFunctionInfo func, LbsExpCalcValue[] parArray, LbsExpCalcValue funcResult) {
/*  875 */     if (this.parserEvents != null && funcResult != null) {
/*  876 */       this.parserEvents.calculateFunction(this.sender, func, parArray, funcResult);
/*      */     }
/*      */   }
/*      */   
/*      */   private void calculateFunction(LbsFunctionInfo func, LbsExpCalcValueType resTy, StringBuilder expression, LbsExpCalcValue funcResult) {
/*  881 */     LbsExpCalcValue par = new LbsExpCalcValue(0);
/*      */     
/*  883 */     LbsExpCalcValue[] parArray = new LbsExpCalcValue[10];
/*  884 */     for (int i = 0; i < 10; i++) {
/*  885 */       parArray[i] = new LbsExpCalcValue(0);
/*      */     }
/*      */     
/*  888 */     setValueToZero(par);
/*  889 */     if (funcResult != null) {
/*  890 */       setValueToZero(funcResult);
/*      */     }
/*  892 */     String tok = nextToken(expression);
/*  893 */     if (tok.compareTo("(") == 0) {
/*      */       
/*  895 */       boolean stp = false;
/*  896 */       int pIdx = 0;
/*      */ 
/*      */       
/*  899 */       while (expression.length() != 0 && !stp) {
/*      */         
/*  901 */         LbsExpCalcValueType xpTy = parameterType(func, pIdx, resTy);
/*  902 */         par.setExpType(xpTy.getValueType());
/*  903 */         calculateExpression(expression, par);
/*  904 */         if (this.fCalcErr == 0) {
/*      */           
/*  906 */           boolean typeChecked = true;
/*  907 */           if (xpTy.getValueType() != 0) {
/*  908 */             typeChecked = (xpTy.getValueType() == par.getExpType());
/*      */           }
/*  910 */           if (!func.external && !typeChecked)
/*  911 */             this.fCalcErr = 7; 
/*      */         } 
/*  913 */         if (this.fCalcErr != 0) {
/*  914 */           stp = true;
/*      */           continue;
/*      */         } 
/*  917 */         pIdx++;
/*      */         
/*  919 */         if (!func.external) {
/*  920 */           feedValue(func, pIdx, parArray, par, funcResult);
/*      */         } else {
/*      */           
/*  923 */           copyValueTo(par, parArray[pIdx - 1]);
/*  924 */           if (funcResult == null)
/*  925 */             func.paramTypes[pIdx - 1] = par.getExpType(); 
/*      */         } 
/*  927 */         if (expression.length() == 0) {
/*  928 */           stp = true;
/*      */           continue;
/*      */         } 
/*  931 */         tok = nextToken(expression);
/*  932 */         if (tok.compareTo(")") == 0 || tok.compareTo(";") != 0) {
/*  933 */           stp = true;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  938 */       if (tok.compareTo(")") != 0) {
/*      */         
/*  940 */         if (funcResult != null)
/*  941 */           setValueToZero(funcResult); 
/*  942 */         this.fCalcErr = 2;
/*      */       } 
/*  944 */       if (func.external && this.fCalcErr == 0) {
/*  945 */         calculateUserFunction(func, parArray, funcResult);
/*      */       }
/*      */     } else {
/*  948 */       this.fCalcErr = 3;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void calculateExternalFunction(LbsFunctionInfo func, StringBuilder expression, LbsExpCalcValue funcResult) {
/*  953 */     LbsExpCalcValueType vt = new LbsExpCalcValueType(func.getType());
/*  954 */     StringBuilder sb = new StringBuilder(expression.toString());
/*  955 */     calculateFunction(func, vt, expression, null);
/*      */     
/*  957 */     expression = sb;
/*  958 */     if (this.parserEvents != null) {
/*  959 */       this.parserEvents.modifyFunctionInfo(this.sender, func);
/*      */     }
/*  961 */     if (this.fCalcErr == 0) {
/*  962 */       calculateFunction(func, vt, expression, funcResult);
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean tokenIsNumeric(String tok, LbsExpCalcValue value) {
/*  967 */     tok = JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(tok));
/*      */ 
/*      */     
/*      */     try {
/*  971 */       BigDecimal bd = new BigDecimal(tok);
/*  972 */       value.setNumericVal(bd);
/*  973 */       return true;
/*      */     }
/*  975 */     catch (Exception e) {
/*      */       
/*  977 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean tokenIsStrConst(String tok, LbsExpCalcValue value) {
/*  983 */     tok = tok.trim();
/*  984 */     if (tok.length() == 0 || tok.charAt(0) != '"')
/*  985 */       return false; 
/*  986 */     if (tok.charAt(tok.length() - 1) != '"')
/*  987 */       return false; 
/*  988 */     value.setStringVal(tok.substring(1, tok.length() - 1));
/*  989 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean tokenIsNumericPar(String tok, LbsExpCalcValue value) {
/*  994 */     tok = JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(tok));
/*      */     
/*  996 */     if (this.parserEvents != null) {
/*  997 */       return this.parserEvents.calculateNumericPar(this.sender, tok, value);
/*      */     }
/*  999 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean tokenIsStringPar(String tok, LbsExpCalcValue value) {
/* 1004 */     tok = JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(tok));
/*      */     
/* 1006 */     if (this.parserEvents != null) {
/* 1007 */       return this.parserEvents.calculateStringPar(this.sender, tok, value);
/*      */     }
/* 1009 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void clearExp(StringBuilder expression) {
/* 1014 */     if (expression.length() > 0) {
/* 1015 */       expression.delete(0, expression.length() + 1);
/*      */     }
/*      */   }
/*      */   
/*      */   private LbsExpCalcOperator seekOp(StringBuilder exp) {
/* 1020 */     String tempExp = new String(exp);
/*      */     
/* 1022 */     clearExp(exp);
/* 1023 */     exp.append(tempExp.trim());
/*      */     
/* 1025 */     boolean fnd = false;
/* 1026 */     int cnt = 0;
/* 1027 */     String opName = "";
/*      */     
/* 1029 */     while (!fnd && cnt < this.operators.size()) {
/*      */       
/* 1031 */       opName = (String)this.operators.get(Integer.valueOf(cnt + 1));
/* 1032 */       if (opName != null)
/*      */       {
/* 1034 */         if (exp.indexOf(opName) == 0)
/* 1035 */           fnd = true; 
/*      */       }
/* 1037 */       if (!fnd) {
/* 1038 */         cnt++;
/*      */       }
/*      */     } 
/* 1041 */     if (fnd) {
/*      */       
/* 1043 */       exp.delete(0, opName.length());
/* 1044 */       return new LbsExpCalcOperator(cnt + 1);
/*      */     } 
/*      */     
/* 1047 */     return new LbsExpCalcOperator(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public BigDecimal numericExpression(String expression) {
/* 1052 */     LbsExpCalcValue value = new LbsExpCalcValue(1);
/* 1053 */     this.fCalcErr = 0;
/* 1054 */     StringBuilder sb = new StringBuilder(expression);
/*      */     
/* 1056 */     calculateExpression(sb, value);
/*      */     
/* 1058 */     return value.getNumericVal();
/*      */   }
/*      */ 
/*      */   
/*      */   public String stringExpression(String expression) {
/* 1063 */     LbsExpCalcValue value = new LbsExpCalcValue(2);
/* 1064 */     this.fCalcErr = 0;
/* 1065 */     StringBuilder sb = new StringBuilder(expression);
/*      */     
/* 1067 */     calculateExpression(sb, value);
/*      */     
/* 1069 */     return value.getStringVal();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar calendarExpression(String expression) {
/* 1075 */     LbsExpCalcValue value = new LbsExpCalcValue(3);
/* 1076 */     this.fCalcErr = 0;
/* 1077 */     StringBuilder sb = new StringBuilder(expression);
/*      */     
/* 1079 */     calculateExpression(sb, value);
/*      */     
/* 1081 */     return value.getCalendar();
/*      */   }
/*      */ 
/*      */   
/*      */   private void copyValueTo(LbsExpCalcValue source, LbsExpCalcValue dest) {
/* 1086 */     if (source == null || dest == null)
/*      */       return; 
/* 1088 */     dest.setExpType(source.getExpType());
/* 1089 */     dest.setNumericVal(source.getNumericVal());
/* 1090 */     dest.setStringVal(source.getStringVal());
/* 1091 */     dest.setCalendar(source.getCalendar());
/*      */   }
/*      */ 
/*      */   
/*      */   private void calculateExpression(StringBuilder expression, LbsExpCalcValue result) {
/* 1096 */     LbsExpCalcValue leftVal = new LbsExpCalcValue(0);
/* 1097 */     LbsExpCalcValue rightVal = new LbsExpCalcValue(0);
/* 1098 */     LbsExpCalcValueType leftType = new LbsExpCalcValueType(result.getExpType());
/* 1099 */     LbsExpCalcValueType rightType = new LbsExpCalcValueType(0);
/* 1100 */     LbsExpCalcOperator endOperator = new LbsExpCalcOperator(0);
/* 1101 */     LbsExpCalcOperator combineOperator = new LbsExpCalcOperator(0);
/* 1102 */     combineOperator = new LbsExpCalcOperator(0);
/*      */ 
/*      */     
/* 1105 */     boolean done = false;
/*      */     
/* 1107 */     calculateSubExpression(expression, leftVal, endOperator, leftType);
/*      */     
/* 1109 */     while (!done && this.fCalcErr == 0) {
/*      */       
/* 1111 */       if (expression.length() == 0) {
/* 1112 */         done = true; continue;
/* 1113 */       }  if (endOperator.getOperatorType() != 1 && endOperator.getOperatorType() != 2 && endOperator.getOperatorType() != 12) {
/* 1114 */         done = true;
/*      */         continue;
/*      */       } 
/* 1117 */       rightType.setValueType(leftType.getValueType());
/* 1118 */       calculateSubExpression(expression, rightVal, combineOperator, rightType);
/* 1119 */       if (this.fCalcErr == 0) {
/* 1120 */         combineValues(leftVal, leftType, rightVal, rightType, endOperator, leftVal);
/*      */       }
/* 1122 */       endOperator.setOperatorType(combineOperator.getOperatorType());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1127 */     copyValueTo(leftVal, result);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setValueToZero(LbsExpCalcValue value) {
/* 1132 */     value.setNumericVal(new BigDecimal(0.0D));
/* 1133 */     value.setStringVal("");
/*      */   }
/*      */ 
/*      */   
/*      */   private void calculateSubExpression(StringBuilder expression, LbsExpCalcValue value, LbsExpCalcOperator o, LbsExpCalcValueType vType) {
/* 1138 */     LbsExpCalcValue fRes = new LbsExpCalcValue(0);
/* 1139 */     LbsExpCalcValue rv = new LbsExpCalcValue(0);
/* 1140 */     LbsExpCalcValueType rTy = new LbsExpCalcValueType(0);
/* 1141 */     BigDecimal multiplier = new BigDecimal(0.0D);
/* 1142 */     LbsFunctionInfo tokenFunc = null;
/* 1143 */     LbsExpCalcValue newValue = new LbsExpCalcValue(0);
/*      */     
/* 1145 */     String tok = nextToken(expression);
/*      */     
/* 1147 */     if (tok == null || tok.length() == 0) {
/* 1148 */       setValueToZero(value);
/*      */     } else {
/*      */       
/* 1151 */       if (tok.compareTo("-") == 0) {
/*      */         
/* 1153 */         multiplier = multiplier.add(new BigDecimal(-1));
/* 1154 */         tok = nextToken(expression);
/*      */       } else {
/*      */         
/* 1157 */         multiplier = multiplier.add(ONE);
/*      */       } 
/* 1159 */       if (tok.compareTo("(") == 0) {
/*      */         
/* 1161 */         calculateExpression(expression, value);
/*      */         
/* 1163 */         if (this.fCalcErr != 0) {
/*      */           return;
/*      */         }
/* 1166 */         vType.setValueType(value.getExpType());
/*      */         
/* 1168 */         tok = nextToken(expression);
/*      */         
/* 1170 */         if (tok.compareTo(")") != 0) {
/*      */           
/* 1172 */           setValueToZero(value);
/* 1173 */           o.setOperatorType(0);
/* 1174 */           this.fCalcErr = 2;
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } else {
/* 1180 */         tokenFunc = functionToken(tok);
/* 1181 */         if (tokenFunc != null) {
/*      */           
/* 1183 */           if (tokenFunc.getType() == 0)
/* 1184 */             tokenFunc.setType(vType.getValueType()); 
/* 1185 */           calculateFunction(tokenFunc, vType, expression, fRes);
/* 1186 */           if (this.fCalcErr == 0)
/*      */           {
/* 1188 */             copyValueTo(fRes, value);
/* 1189 */             int fncType = tokenFunc.getType();
/* 1190 */             if (fncType == 0)
/* 1191 */               fncType = value.getExpType(); 
/* 1192 */             vType.setValueType(fncType);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1197 */           tokenFunc = externalFunctionToken(tok);
/* 1198 */           if (tokenFunc != null) {
/*      */             
/* 1200 */             if (tokenFunc.getType() == 0)
/* 1201 */               tokenFunc.setType(vType.getValueType()); 
/* 1202 */             calculateExternalFunction(tokenFunc, expression, fRes);
/* 1203 */             if (this.fCalcErr == 0)
/*      */             {
/* 1205 */               copyValueTo(fRes, value);
/* 1206 */               vType.setValueType(tokenFunc.getType());
/*      */             }
/*      */           
/* 1209 */           } else if (tokenIsNumeric(tok, value)) {
/* 1210 */             vType.setValueType(1);
/* 1211 */           } else if (tokenIsStrConst(tok, value)) {
/* 1212 */             vType.setValueType(2);
/* 1213 */           } else if (tokenIsNumericPar(tok, value)) {
/*      */             
/* 1215 */             if (value != null && value.getCalendar() != null) {
/* 1216 */               vType.setValueType(3);
/*      */             } else {
/* 1218 */               vType.setValueType(1);
/*      */             } 
/* 1220 */           } else if (tokenIsStringPar(tok, value)) {
/* 1221 */             vType.setValueType(2);
/*      */           } else {
/* 1223 */             this.fCalcErr = 1;
/* 1224 */           }  if (this.fCalcErr == 0) {
/* 1225 */             value.setExpType(vType.getValueType());
/*      */           }
/*      */         } 
/*      */       } 
/* 1229 */       if (this.fCalcErr != 0) {
/*      */         
/* 1231 */         this.fCalcErrFunc = tok;
/*      */         return;
/*      */       } 
/* 1234 */       if (vType.getValueType() == 1) {
/* 1235 */         value.setNumericVal(value.getNumericVal().multiply(multiplier));
/*      */       }
/*      */     } 
/*      */     
/* 1239 */     if (expression.length() == 0) {
/* 1240 */       o.setOperatorType(0);
/*      */     } else {
/*      */       
/* 1243 */       LbsExpCalcOperator mid = seekOp(expression);
/*      */       
/* 1245 */       if (mid.getOperatorType() == 14) {
/*      */         
/* 1247 */         String tempExp = new String(expression.toString());
/* 1248 */         clearExp(expression);
/* 1249 */         expression.append(")");
/* 1250 */         expression.append(tempExp);
/* 1251 */         o.setOperatorType(mid.getOperatorType());
/*      */       }
/* 1253 */       else if (mid.getOperatorType() != 0 && mid.getOperatorType() != 1 && mid.getOperatorType() != 2 && mid.getOperatorType() != 12) {
/*      */         
/* 1255 */         calculateSubExpression(expression, rv, o, rTy);
/* 1256 */         if (this.fCalcErr == 0) {
/*      */           
/* 1258 */           combineValues(value, vType, rv, rTy, mid, newValue);
/* 1259 */           copyValueTo(newValue, value);
/*      */         } 
/*      */       } else {
/*      */         
/* 1263 */         o.setOperatorType(mid.getOperatorType());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void combineNums(LbsExpCalcOperator operator, LbsExpCalcValue lv, LbsExpCalcValue rv, LbsExpCalcValue result) {
/* 1269 */     BigDecimal resVal = new BigDecimal(0.0D);
/* 1270 */     result.setExpType(1);
/*      */     
/* 1272 */     switch (operator.getOperatorType()) {
/*      */       
/*      */       case 1:
/* 1275 */         resVal = resVal.add(lv.getNumericVal());
/* 1276 */         resVal = resVal.add(rv.getNumericVal());
/*      */         break;
/*      */       case 2:
/* 1279 */         resVal = resVal.add(lv.getNumericVal());
/* 1280 */         resVal = resVal.subtract(rv.getNumericVal());
/*      */         break;
/*      */       case 3:
/* 1283 */         resVal = resVal.add(lv.getNumericVal());
/* 1284 */         resVal = resVal.multiply(rv.getNumericVal());
/*      */         break;
/*      */       case 4:
/* 1287 */         if (rv.getNumericVal().abs().compareTo(EPSILON) == 1) {
/*      */           
/* 1289 */           resVal = resVal.add(lv.getNumericVal());
/* 1290 */           resVal = resVal.divide(rv.getNumericVal(), 32, 0);
/*      */           break;
/*      */         } 
/* 1293 */         this.fCalcErr = 4;
/*      */         break;
/*      */       case 9:
/* 1296 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) == 1)
/* 1297 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 10:
/* 1300 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) == -1)
/* 1301 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 8:
/* 1304 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) == 0)
/* 1305 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 5:
/* 1308 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) >= 0)
/* 1309 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 6:
/* 1312 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) <= 0)
/* 1313 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 7:
/* 1316 */         if (lv.getNumericVal().compareTo(rv.getNumericVal()) != 0)
/* 1317 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 11:
/* 1320 */         if (lv.getNumericVal().abs().compareTo(EPSILON) == 1 && rv.getNumericVal().abs().compareTo(EPSILON) == 1)
/* 1321 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 12:
/* 1324 */         if (lv.getNumericVal().abs().compareTo(EPSILON) == 1 || rv.getNumericVal().abs().compareTo(EPSILON) == 1) {
/* 1325 */           resVal = resVal.add(ONE);
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1332 */     result.setNumericVal(resVal);
/*      */   }
/*      */ 
/*      */   
/*      */   private int compareStrings(LbsExpCalcValue lv, LbsExpCalcValue rv) {
/* 1337 */     return lv.getStringVal().toString().compareTo(rv.getStringVal().toString());
/*      */   }
/*      */ 
/*      */   
/*      */   private void combineStrings(LbsExpCalcOperator operator, LbsExpCalcValue lv, LbsExpCalcValue rv, LbsExpCalcValue result) {
/* 1342 */     String strVal = new String("");
/* 1343 */     BigDecimal numVal = new BigDecimal(0.0D);
/* 1344 */     result.setExpType(1);
/*      */     
/* 1346 */     switch (operator.getOperatorType()) {
/*      */       
/*      */       case 1:
/* 1349 */         strVal = strVal.concat(lv.getStringVal());
/* 1350 */         strVal = strVal.concat(rv.getStringVal());
/* 1351 */         result.setExpType(2);
/*      */         break;
/*      */       case 9:
/* 1354 */         if (compareStrings(lv, rv) == 1)
/* 1355 */           numVal = numVal.add(ONE); 
/*      */         break;
/*      */       case 10:
/* 1358 */         if (compareStrings(lv, rv) == -1)
/* 1359 */           numVal = numVal.add(ONE); 
/*      */         break;
/*      */       case 8:
/* 1362 */         if (compareStrings(lv, rv) == 0)
/* 1363 */           numVal = numVal.add(ONE); 
/*      */         break;
/*      */       case 5:
/* 1366 */         if (compareStrings(lv, rv) >= 0)
/* 1367 */           numVal = numVal.add(ONE); 
/*      */         break;
/*      */       case 6:
/* 1370 */         if (compareStrings(lv, rv) <= 0)
/* 1371 */           numVal = numVal.add(ONE); 
/*      */         break;
/*      */       case 7:
/* 1374 */         if (compareStrings(lv, rv) != 0) {
/* 1375 */           numVal = numVal.add(ONE);
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1381 */     result.setStringVal(strVal);
/* 1382 */     result.setNumericVal(numVal);
/*      */   }
/*      */ 
/*      */   
/*      */   private void combineValues(LbsExpCalcValue lv, LbsExpCalcValueType lTy, LbsExpCalcValue rv, LbsExpCalcValueType rTy, LbsExpCalcOperator operator, LbsExpCalcValue result) {
/* 1387 */     if (lTy.getValueType() == 1 && rTy.getValueType() == 1) {
/*      */       
/* 1389 */       combineNums(operator, lv, rv, result);
/* 1390 */       result.setExpType(1);
/*      */     }
/* 1392 */     else if (lTy.getValueType() == 2 && rTy.getValueType() == 2) {
/* 1393 */       combineStrings(operator, lv, rv, result);
/* 1394 */     } else if (lTy.getValueType() == 3 && rTy.getValueType() == 3) {
/* 1395 */       combineDates(operator, lv, rv, result);
/*      */     } else {
/* 1397 */       this.fCalcErr = 6;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void combineDates(LbsExpCalcOperator operator, LbsExpCalcValue lv, LbsExpCalcValue rv, LbsExpCalcValue result) {
/* 1402 */     BigDecimal resVal = new BigDecimal(0.0D);
/* 1403 */     result.setExpType(1);
/*      */     
/* 1405 */     switch (operator.getOperatorType()) {
/*      */       
/*      */       case 9:
/* 1408 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) == 1)
/* 1409 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 10:
/* 1412 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) == -1)
/* 1413 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 8:
/* 1416 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) == 0)
/* 1417 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 5:
/* 1420 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) >= 0)
/* 1421 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 6:
/* 1424 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) <= 0)
/* 1425 */           resVal = resVal.add(ONE); 
/*      */         break;
/*      */       case 7:
/* 1428 */         if (compareDates(lv.getCalendar(), rv.getCalendar()) != 0) {
/* 1429 */           resVal = resVal.add(ONE);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1434 */     result.setNumericVal(resVal);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean foundInParams(ArrayList<Integer> params, int parId) {
/* 1439 */     for (int i = 0; i < params.size(); i++) {
/*      */       
/* 1441 */       Integer paramNr = params.get(i);
/* 1442 */       if (paramNr.intValue() == parId)
/* 1443 */         return true; 
/*      */     } 
/* 1445 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean foundInParams(ArrayList<String> params, String tokenStr) {
/* 1450 */     for (int i = 0; i < params.size(); i++) {
/*      */       
/* 1452 */       String s = params.get(i);
/* 1453 */       if (s.compareTo(tokenStr) == 0)
/* 1454 */         return true; 
/*      */     } 
/* 1456 */     return false;
/*      */   }
/*      */   
/*      */   public ArrayList parameterList(String expression, String paramPrefix, boolean postfixes) {
/*      */     boolean ok;
/* 1461 */     StringBuilder sb = new StringBuilder(expression);
/* 1462 */     ArrayList<Integer> params = new ArrayList();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 1467 */       String tok = nextToken(sb);
/* 1468 */       ok = (tok != null && tok.length() != 0);
/* 1469 */       if (!ok)
/*      */         continue; 
/* 1471 */       int x = tok.indexOf(paramPrefix);
/* 1472 */       if (!postfixes) {
/*      */         
/* 1474 */         int parId = (x == 0) ? 
/* 1475 */           ConvertUtil.strToInt(tok.substring(paramPrefix.length())) : 
/* 1476 */           0;
/* 1477 */         if (parId > 0 && !foundInParams(params, parId)) {
/* 1478 */           params.add(Integer.valueOf(parId));
/*      */         }
/* 1480 */       } else if (tok.indexOf("_") != -1) {
/*      */         
/* 1482 */         if (!foundInParams(params, tok)) {
/* 1483 */           params.add(tok);
/*      */         }
/*      */       }
/*      */     
/* 1487 */     } while (ok);
/*      */     
/* 1489 */     return params;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\expcalc\LbsExpCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */