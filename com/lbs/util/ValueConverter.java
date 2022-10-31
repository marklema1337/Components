/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.ibm.icu.text.DecimalFormat;
/*     */ import com.ibm.icu.text.DecimalFormatSymbols;
/*     */ import com.ibm.icu.text.NumberFormat;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*     */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
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
/*     */ public class ValueConverter
/*     */ {
/*     */   private static DecimalFormatSymbols ms_DecimalFormatSymbols;
/*     */   private static DecimalFormat ms_DecimalFormat;
/*  36 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(ValueConverter.class);
/*     */ 
/*     */   
/*     */   public static int compare(Class objClass, Object o1, Object o2) {
/*  40 */     boolean e1 = (o1 == null);
/*  41 */     boolean e2 = (o2 == null);
/*     */     
/*  43 */     if (e1 && e2) {
/*  44 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/*  48 */       if (e1) {
/*  49 */         o1 = convert(objClass, null);
/*     */       }
/*  51 */       if (e2) {
/*  52 */         o2 = convert(objClass, null);
/*     */       }
/*  54 */       Class<?> c1 = o1.getClass();
/*  55 */       Class<?> c2 = o2.getClass();
/*     */       
/*  57 */       if (!c1.equals(c2))
/*     */       {
/*  59 */         if (RttiUtil.isSubClassOf(c1, c2)) {
/*  60 */           o1 = convert(o2.getClass(), o1);
/*     */         } else {
/*  62 */           o2 = convert(o1.getClass(), o2);
/*     */         } 
/*     */       }
/*  65 */       if (o1 instanceof Comparable)
/*     */       {
/*  67 */         return ((Comparable<Object>)o1).compareTo(o2);
/*     */       }
/*     */       
/*  70 */       if (o1.equals(o2)) {
/*  71 */         return 0;
/*     */       }
/*  73 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("ValueConverter.compare : Not comparable - " + o1);
/*     */ 
/*     */     
/*     */     }
/*  77 */     catch (Exception e) {
/*     */       
/*  79 */       if (e1)
/*  80 */         return -1; 
/*  81 */       return 1;
/*     */     } 
/*     */     
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object convert(Class srcClass, Class dstClass, Object value, ILbsCultureInfo cultureInfo) throws ConversionNotSupportedException, ConversionDataLoss {
/*  90 */     return convert(srcClass, dstClass, value, cultureInfo, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object convert(Class<?> srcClass, Class dstClass, Object value, ILbsCultureInfo cultureInfo, JLbsRealNumberFormatter numberFormatter) throws ConversionNotSupportedException, ConversionDataLoss {
/*  96 */     boolean primitive = dstClass.isPrimitive();
/*     */     
/*  98 */     if (primitive) {
/*  99 */       dstClass = RttiUtil.valueClassToWrapper(dstClass);
/*     */     }
/* 101 */     if (srcClass == null && value != null) {
/* 102 */       srcClass = value.getClass();
/*     */     }
/* 104 */     if (srcClass != null) {
/*     */       
/* 106 */       if (srcClass.isPrimitive()) {
/* 107 */         srcClass = RttiUtil.valueClassToWrapper(srcClass);
/*     */       }
/* 109 */       if (dstClass.equals(srcClass)) {
/* 110 */         return value;
/*     */       }
/*     */     } 
/* 113 */     if (dstClass.equals(JLbsTimeDuration.class)) {
/* 114 */       return convertToTimeDuration(srcClass, value);
/*     */     }
/*     */     
/* 117 */     if (dstClass.equals(Calendar.class)) {
/* 118 */       return convertToDate(srcClass, value);
/*     */     }
/* 120 */     if (dstClass.equals(String.class)) {
/* 121 */       return convertToString(value, cultureInfo, numberFormatter);
/*     */     }
/* 123 */     if (dstClass.equals(Boolean.class)) {
/* 124 */       return convertToBoolean(srcClass, value);
/*     */     }
/* 126 */     if (dstClass.equals(Character.class)) {
/* 127 */       return convertToCharacter(srcClass, value);
/*     */     }
/* 129 */     if (srcClass != null) {
/*     */       
/* 131 */       if ((dstClass.equals(Integer.class) || dstClass.equals(Long.class)) && srcClass.equals(JLbsTimeDuration.class)) {
/* 132 */         return convertFromTimeDuration(dstClass, (JLbsTimeDuration)value);
/*     */       }
/*     */       
/* 135 */       if (dstClass.equals(Integer.class) && RttiUtil.isSubClassOf(srcClass, Calendar.class)) {
/* 136 */         return convertFromDate(dstClass, (Calendar)value);
/*     */       }
/* 138 */       if (dstClass.equals(Date.class) && RttiUtil.isSubClassOf(srcClass, Calendar.class)) {
/*     */         
/* 140 */         if (value == null)
/* 141 */           return null; 
/* 142 */         return ((Calendar)value).getTime();
/*     */       } 
/*     */       
/* 145 */       if (srcClass.equals(JSONArray.class) && dstClass.equals(int[].class)) {
/*     */         
/* 147 */         JSONArray array = (JSONArray)value;
/* 148 */         if (value != null) {
/*     */           
/* 150 */           int[] inArrayValue = new int[array.length()];
/* 151 */           for (int i = 0; i < array.length(); i++) {
/*     */ 
/*     */             
/*     */             try {
/* 155 */               inArrayValue[i] = array.getInt(i);
/*     */             }
/* 157 */             catch (JSONException e) {
/*     */               
/* 159 */               e.printStackTrace();
/*     */             } 
/*     */           } 
/* 162 */           return inArrayValue;
/*     */         } 
/* 164 */         return null;
/*     */       } 
/*     */       
/* 167 */       if (srcClass.equals(String.class)) {
/* 168 */         return convertFromString(dstClass, (String)value, cultureInfo);
/*     */       }
/* 170 */       if (RttiUtil.isSubClassOf(srcClass, Number.class) && RttiUtil.isSubClassOf(dstClass, Number.class)) {
/* 171 */         return convertToNumber(dstClass, (Number)value);
/*     */       }
/* 173 */       if (srcClass.equals(Boolean.class)) {
/* 174 */         return convertFromBoolean(dstClass, (Boolean)value, cultureInfo);
/*     */       }
/* 176 */       if (dstClass.isAssignableFrom(srcClass)) {
/* 177 */         return value;
/*     */       }
/* 179 */       if (RttiUtil.isSubClassOf(srcClass, dstClass)) {
/* 180 */         return value;
/*     */       }
/*     */     } else {
/*     */       
/* 184 */       if (RttiUtil.isSubClassOf(dstClass, Number.class)) {
/* 185 */         return convertToNumber(dstClass, Byte.valueOf((byte)0));
/*     */       }
/* 187 */       if (!primitive && value == null) {
/* 188 */         return null;
/*     */       }
/*     */     } 
/* 191 */     throw new ConversionNotSupportedException("Can not convert from '" + ((srcClass == null) ? "NULL" : srcClass
/*     */         
/* 193 */         .getName()) + "' to '" + dstClass.getName() + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object convertNull(Class dstClass, ILbsCultureInfo cultureInfo) throws ConversionNotSupportedException, ConversionDataLoss {
/* 200 */     if (dstClass.isPrimitive()) {
/* 201 */       dstClass = RttiUtil.valueClassToWrapper(dstClass);
/*     */     }
/* 203 */     if (dstClass.equals(Boolean.class)) {
/* 204 */       return new Boolean(false);
/*     */     }
/* 206 */     if (dstClass.equals(Character.class)) {
/* 207 */       return new Character(false);
/*     */     }
/* 209 */     if (canConvertToNumber(dstClass)) {
/* 210 */       return convertToNumber(dstClass, Integer.valueOf(0));
/*     */     }
/* 212 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object convert(Class dstClass, Object value) throws ConversionNotSupportedException, ConversionDataLoss {
/* 217 */     if (value == null) {
/* 218 */       return convert(null, dstClass, null);
/*     */     }
/* 220 */     return convert(value.getClass(), dstClass, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object convert(Class srcClass, Class dstClass, Object value) throws ConversionNotSupportedException, ConversionDataLoss {
/* 226 */     return convert(srcClass, dstClass, value, null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean canConvertToNumber(Class dstClass) {
/* 231 */     return RttiUtil.isSubClassOf(dstClass, Number.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Number convertToNumber(Class dstClass, Number value) {
/* 236 */     if (dstClass.equals(BigInteger.class)) {
/* 237 */       return new BigInteger(value.toString());
/*     */     }
/* 239 */     if (dstClass.equals(BigDecimal.class)) {
/* 240 */       return new BigDecimal(value.toString());
/*     */     }
/* 242 */     if (dstClass.equals(Byte.class)) {
/* 243 */       return Byte.valueOf(value.byteValue());
/*     */     }
/* 245 */     if (dstClass.equals(Short.class)) {
/* 246 */       return Short.valueOf(value.shortValue());
/*     */     }
/* 248 */     if (dstClass.equals(Integer.class)) {
/* 249 */       return Integer.valueOf(value.intValue());
/*     */     }
/* 251 */     if (dstClass.equals(Long.class)) {
/* 252 */       return Long.valueOf(value.longValue());
/*     */     }
/* 254 */     if (dstClass.equals(Float.class)) {
/* 255 */       return new Float(value.floatValue());
/*     */     }
/* 257 */     if (dstClass.equals(Double.class)) {
/* 258 */       return new Double(value.doubleValue());
/*     */     }
/* 260 */     return value;
/*     */   }
/*     */   
/*     */   protected static String convertToString(Object value, ILbsCultureInfo cultureInfo, JLbsRealNumberFormatter numberFormatter) {
/*     */     JLbsDefaultCultureInfo jLbsDefaultCultureInfo;
/* 265 */     if (value == null) {
/* 266 */       return "";
/*     */     }
/* 268 */     if (value instanceof Character) {
/*     */       
/* 270 */       Character cVal = (Character)value;
/*     */       
/* 272 */       if (cVal.charValue() == '\000') {
/* 273 */         return "";
/*     */       }
/*     */     } 
/* 276 */     if (value instanceof byte[]) {
/* 277 */       return JLbsStringUtil.getString((byte[])value);
/*     */     }
/* 279 */     if (value instanceof Calendar) {
/*     */       
/* 281 */       if (cultureInfo == null)
/* 282 */         jLbsDefaultCultureInfo = new JLbsDefaultCultureInfo(); 
/* 283 */       return jLbsDefaultCultureInfo.formatDateTime((Calendar)value, jLbsDefaultCultureInfo.getDefaultDateFormatIndex(), jLbsDefaultCultureInfo
/* 284 */           .getDefaultTimeFormatIndex(), 16, jLbsDefaultCultureInfo.getCalendarType());
/*     */     } 
/*     */     
/* 287 */     if (value instanceof Number) {
/*     */       
/* 289 */       if (numberFormatter == null)
/* 290 */         numberFormatter = new JLbsRealNumberFormatter(); 
/* 291 */       return numberFormatter.formatNumberFull((Number)value, (ILbsCultureInfo)jLbsDefaultCultureInfo, false);
/*     */     } 
/*     */     
/* 294 */     return value.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Object convertFromString(Class dstClass, String value, ILbsCultureInfo cultureInfo) throws ConversionNotSupportedException {
/* 300 */     if (value.equals("") && dstClass.equals(Character.class)) {
/* 301 */       return new Character(false);
/*     */     }
/* 303 */     if (canConvertToNumber(dstClass)) {
/*     */       
/* 305 */       BigDecimal bigD = null;
/*     */       
/*     */       try {
/* 308 */         bigD = new BigDecimal(value);
/*     */       }
/* 310 */       catch (NumberFormatException e) {
/*     */         
/* 312 */         bigD = tryToParseValue(value, cultureInfo);
/*     */       } 
/* 314 */       return convertToNumber(dstClass, bigD);
/*     */     } 
/*     */     
/* 317 */     if (dstClass.equals(Character.class)) {
/* 318 */       return new Character(value.charAt(0));
/*     */     }
/* 320 */     if (dstClass.equals(Boolean.class)) {
/* 321 */       return Boolean.valueOf(value);
/*     */     }
/* 323 */     if (dstClass.equals(byte[].class)) {
/*     */       
/*     */       try {
/*     */         
/* 327 */         return value.getBytes("utf-8");
/*     */       }
/* 329 */       catch (UnsupportedEncodingException e) {
/*     */         
/* 331 */         System.err.println(e.getMessage());
/* 332 */         return null;
/*     */       } 
/*     */     }
/*     */     
/* 336 */     if (dstClass.equals(Object.class)) {
/* 337 */       return value;
/*     */     }
/* 339 */     throw new ConversionNotSupportedException();
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
/*     */   private static BigDecimal tryToParseValue(String value, ILbsCultureInfo cultureInfo) throws ConversionNotSupportedException {
/* 351 */     if (value == null || value.trim().length() == 0)
/* 352 */       return new BigDecimal(0); 
/* 353 */     value = value.trim();
/*     */ 
/*     */     
/* 356 */     synchronized (ValueConverter.class) {
/*     */       
/* 358 */       if (ms_DecimalFormatSymbols == null) {
/*     */         
/* 360 */         if (cultureInfo != null) {
/* 361 */           ms_DecimalFormat = (DecimalFormat)NumberFormat.getInstance(cultureInfo.getLocale());
/*     */         } else {
/* 363 */           ms_DecimalFormat = (DecimalFormat)NumberFormat.getInstance();
/* 364 */         }  ms_DecimalFormat.setParseBigDecimal(true);
/* 365 */         ms_DecimalFormatSymbols = ms_DecimalFormat.getDecimalFormatSymbols();
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     BigDecimal result = null;
/*     */     
/*     */     try {
/* 372 */       char decimalSeparator = ms_DecimalFormatSymbols.getDecimalSeparator();
/* 373 */       char groupingSeparator = ms_DecimalFormatSymbols.getGroupingSeparator();
/*     */       
/* 375 */       int idx = value.indexOf(' ');
/* 376 */       if (idx >= 0) {
/* 377 */         value = value.substring(0, idx).trim();
/*     */       }
/* 379 */       int dPos = value.indexOf(decimalSeparator);
/* 380 */       int gPos = value.indexOf(groupingSeparator);
/*     */       
/* 382 */       if (dPos >= 0 && dPos < gPos) {
/*     */         
/* 384 */         value = value.replace(decimalSeparator, '~');
/* 385 */         value = value.replace(groupingSeparator, decimalSeparator);
/* 386 */         value = value.replace('~', groupingSeparator);
/*     */       } 
/* 388 */       result = (BigDecimal)ms_DecimalFormat.parse(value);
/*     */     }
/* 390 */     catch (Exception e) {
/*     */ 
/*     */       
/* 393 */       throw new ConversionNotSupportedException(e.getMessage());
/*     */     } 
/*     */     
/* 396 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Object convertFromBoolean(Class dstClass, Boolean value, ILbsCultureInfo cultureInfo) throws ConversionNotSupportedException, ConversionDataLoss {
/* 402 */     if (dstClass.equals(String.class)) {
/* 403 */       return value.toString();
/*     */     }
/* 405 */     if (value.booleanValue()) {
/* 406 */       return convert(dstClass, Integer.valueOf(1));
/*     */     }
/* 408 */     return convert(dstClass, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Boolean convertToBoolean(Class srcClass, Object value) throws ConversionNotSupportedException {
/* 413 */     if (srcClass == null || value == null) {
/* 414 */       return new Boolean(false);
/*     */     }
/* 416 */     if (canConvertToNumber(srcClass)) {
/*     */       
/* 418 */       Integer i = (Integer)convertToNumber(Integer.class, (Number)value);
/*     */       
/* 420 */       return new Boolean((i.intValue() != 0));
/*     */     } 
/* 422 */     if (value instanceof String) {
/* 423 */       return Boolean.valueOf((String)value);
/*     */     }
/* 425 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Character convertToCharacter(Class srcClass, Object value) throws ConversionNotSupportedException {
/* 430 */     if (srcClass == null || value == null) {
/* 431 */       return new Character(false);
/*     */     }
/* 433 */     if (srcClass.equals(String.class)) {
/*     */       
/* 435 */       String strVal = (String)value;
/* 436 */       if (strVal.equals("")) {
/* 437 */         return new Character(false);
/*     */       }
/* 439 */       return new Character(strVal.charAt(0));
/*     */     } 
/*     */     
/* 442 */     if (canConvertToNumber(srcClass)) {
/*     */       
/* 444 */       Integer i = (Integer)convertToNumber(Integer.class, (Number)value);
/*     */       
/* 446 */       return new Character((char)i.byteValue());
/*     */     } 
/*     */     
/* 449 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static JLbsTimeDuration convertToTimeDuration(Class srcClass, Object value) throws ConversionNotSupportedException {
/* 454 */     if (srcClass == null || value == null) {
/* 455 */       return new JLbsTimeDuration();
/*     */     }
/* 457 */     if (value instanceof Calendar) {
/*     */       
/* 459 */       Calendar cal = (Calendar)value;
/* 460 */       return new JLbsTimeDuration(cal);
/*     */     } 
/*     */     
/* 463 */     if (value instanceof Integer) {
/*     */       
/* 465 */       Integer i = (Integer)value;
/* 466 */       return new JLbsTimeDuration(i.intValue());
/*     */     } 
/*     */     
/* 469 */     if (value instanceof Long) {
/*     */       
/* 471 */       Long l = (Long)value;
/* 472 */       Calendar cal = new GregorianCalendar();
/* 473 */       cal.setTimeInMillis(l.longValue());
/* 474 */       return new JLbsTimeDuration(cal);
/*     */     } 
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
/* 486 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Object convertFromTimeDuration(Class<Calendar> dstClass, JLbsTimeDuration time) throws ConversionNotSupportedException, ConversionDataLoss {
/* 492 */     if (RttiUtil.isSubClassOf(dstClass, Calendar.class)) {
/*     */       
/*     */       try {
/*     */         
/* 496 */         Calendar cal = dstClass.newInstance();
/* 497 */         cal.setTimeInMillis(time.toCalendar().getTimeInMillis());
/* 498 */         return cal;
/*     */       }
/* 500 */       catch (Exception e) {
/*     */         
/* 502 */         throw new ConversionNotSupportedException();
/*     */       } 
/*     */     }
/*     */     
/* 506 */     if (dstClass.equals(Long.class)) {
/*     */       
/* 508 */       long timeInMillis = time.toCalendar().getTimeInMillis();
/* 509 */       return Long.valueOf(timeInMillis);
/*     */     } 
/*     */     
/* 512 */     if (dstClass.equals(Integer.class)) {
/*     */       
/* 514 */       int lbsTime = time.toInteger();
/* 515 */       return Integer.valueOf(lbsTime);
/*     */     } 
/*     */ 
/*     */     
/* 519 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Calendar convertToDate(Class srcClass, Object value) throws ConversionNotSupportedException {
/* 525 */     if (value != null && value instanceof String) {
/*     */       
/* 527 */       String strVal = (String)value;
/* 528 */       return StringUtil.toCalendar(strVal, true);
/*     */     } 
/* 530 */     if (value instanceof Calendar)
/* 531 */       return (GregorianCalendar)value; 
/* 532 */     if (value instanceof JLbsTimeDuration)
/* 533 */       return ((JLbsTimeDuration)value).toCalendar(); 
/* 534 */     if (value instanceof Date) {
/*     */       
/* 536 */       Calendar instance = GregorianCalendar.getInstance();
/* 537 */       instance.setTime((Date)value);
/* 538 */       return instance;
/*     */     } 
/*     */ 
/*     */     
/* 542 */     if (value == null) {
/* 543 */       return null;
/*     */     }
/*     */     
/* 546 */     ms_Logger.info("Value type is not Calendar,Date or TimeDuration, so method returns current date. Value:" + value);
/* 547 */     return Calendar.getInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Object convertFromDate(Class dstClass, Calendar date) throws ConversionNotSupportedException, ConversionDataLoss {
/* 556 */     int iDate = date.get(5);
/* 557 */     Integer i = Integer.valueOf(iDate);
/*     */     
/* 559 */     return convert(dstClass, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal trim(BigDecimal n) {
/* 564 */     boolean loop = true;
/* 565 */     while (loop) {
/*     */ 
/*     */       
/*     */       try {
/* 569 */         n = n.setScale(n.scale() - 1);
/*     */       }
/* 571 */       catch (ArithmeticException e) {
/*     */         
/* 573 */         loop = false;
/*     */       } 
/*     */     } 
/* 576 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 583 */       System.out.println(convert(Number.class, "11,111.11 TL"));
/* 584 */       System.out.println(convert(Number.class, "11111.11"));
/* 585 */       System.out.println(convert(Number.class, "11111,11"));
/* 586 */       System.out.println(convert(Number.class, "11.111,11"));
/*     */     }
/* 588 */     catch (ConversionNotSupportedException e) {
/*     */       
/* 590 */       e.printStackTrace();
/*     */     }
/* 592 */     catch (ConversionDataLoss e) {
/*     */       
/* 594 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ValueConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */