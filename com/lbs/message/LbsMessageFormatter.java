/*     */ package com.lbs.message;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.datedit.JLbsDateFormatter;
/*     */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*     */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class LbsMessageFormatter
/*     */ {
/*  30 */   private static Vector ms_MessageFormatSpecs = new Vector();
/*  31 */   private static Hashtable ms_ComplexFormatSpecs = new Hashtable<>();
/*     */ 
/*     */   
/*     */   static {
/*  35 */     ms_MessageFormatSpecs.add(new BasicOldFormatSpec());
/*  36 */     ms_MessageFormatSpecs.add(new ComplexFormatSpec());
/*     */     
/*  38 */     ms_ComplexFormatSpecs.put("numeric", new NumericFormatSpec());
/*  39 */     ms_ComplexFormatSpecs.put("date", new DateFormatSpec());
/*  40 */     ms_ComplexFormatSpecs.put("time", new TimeFormatSpec());
/*  41 */     ms_ComplexFormatSpecs.put("datetime", new DateTimeFormatSpec());
/*  42 */     ms_ComplexFormatSpecs.put("string", new StringFormatSpec());
/*     */   }
/*     */   
/*  45 */   private Vector m_FormatSpecs = new Vector();
/*     */   
/*     */   private final String m_Message;
/*     */ 
/*     */   
/*     */   public LbsMessageFormatter(String message) {
/*  51 */     this.m_FormatSpecs = new Vector();
/*  52 */     this.m_Message = message;
/*  53 */     parseMessage(message);
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseMessage(String message) {
/*  58 */     for (int i = 0; i < ms_MessageFormatSpecs.size(); i++)
/*     */     {
/*  60 */       ((MessageFormatSpec)ms_MessageFormatSpecs.get(i)).findFormatSpecs(message, this.m_FormatSpecs);
/*     */     }
/*  62 */     Collections.sort(this.m_FormatSpecs, new Comparator()
/*     */         {
/*     */ 
/*     */           
/*     */           public int compare(Object o1, Object o2)
/*     */           {
/*  68 */             if (o1 instanceof LbsMessageFormatter.MessageFormatSpec && o2 instanceof LbsMessageFormatter.MessageFormatSpec) {
/*     */               
/*  70 */               LbsMessageFormatter.MessageFormatSpec format1 = (LbsMessageFormatter.MessageFormatSpec)o1;
/*  71 */               LbsMessageFormatter.MessageFormatSpec format2 = (LbsMessageFormatter.MessageFormatSpec)o2;
/*  72 */               return format1.m_StartPosition - format2.m_StartPosition;
/*     */             } 
/*  74 */             return 0;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public String format(ILbsCultureInfo culture, Object[] messageFormatArguments) {
/*  81 */     if (JLbsStringUtil.isEmpty(this.m_Message))
/*  82 */       return this.m_Message; 
/*  83 */     StringBuilder result = new StringBuilder();
/*     */     
/*  85 */     int idx = 0;
/*  86 */     for (int i = 0; i < this.m_FormatSpecs.size(); i++) {
/*     */       
/*  88 */       MessageFormatSpec format = this.m_FormatSpecs.get(i);
/*  89 */       result.append(this.m_Message.substring(idx, format.m_StartPosition));
/*  90 */       result.append(format.formatArgument(culture, messageFormatArguments));
/*  91 */       idx = format.m_EndPosition;
/*     */     } 
/*     */     
/*  94 */     if (idx < this.m_Message.length()) {
/*     */       
/*  96 */       result.append(this.m_Message.substring(idx));
/*     */       
/*  98 */       if (this.m_FormatSpecs.size() == 0 && messageFormatArguments.length == 2 && messageFormatArguments[0] instanceof String && 
/*  99 */         "EmulExtra".equals(messageFormatArguments[0])) {
/* 100 */         result.append(" " + messageFormatArguments[1]);
/*     */       }
/*     */     } 
/* 103 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String format(String message, Object[] messageFormatArguments, ILbsCultureInfo culture) {
/* 108 */     return (new LbsMessageFormatter(message)).format(culture, messageFormatArguments);
/*     */   }
/*     */ 
/*     */   
/*     */   private static abstract class MessageFormatSpec
/*     */   {
/*     */     protected int m_StartPosition;
/*     */     
/*     */     protected int m_EndPosition;
/*     */     
/*     */     protected int m_Index;
/*     */ 
/*     */     
/*     */     public MessageFormatSpec() {}
/*     */     
/*     */     public MessageFormatSpec(int startPosition, int endPosition, int index) {
/* 124 */       this.m_StartPosition = startPosition;
/* 125 */       this.m_EndPosition = endPosition;
/* 126 */       this.m_Index = index;
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract void findFormatSpecs(String param1String, Vector param1Vector);
/*     */ 
/*     */     
/*     */     public abstract String formatArgument(ILbsCultureInfo param1ILbsCultureInfo, Object[] param1ArrayOfObject);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class BasicOldFormatSpec
/*     */     extends MessageFormatSpec
/*     */   {
/*     */     public BasicOldFormatSpec() {}
/*     */ 
/*     */     
/*     */     public BasicOldFormatSpec(int startPosition, int endPosition, int index) {
/* 144 */       super(startPosition, endPosition, index);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void findFormatSpecs(String message, Vector<BasicOldFormatSpec> formatSpecs) {
/* 150 */       if (message == null)
/*     */         return; 
/* 152 */       int startIdx = message.indexOf("~");
/* 153 */       while (startIdx >= 0) {
/*     */         
/* 155 */         String idx = "";
/* 156 */         StringBuilder idxBuff = new StringBuilder();
/* 157 */         int endIdx = startIdx + 1;
/* 158 */         if (message.length() <= endIdx)
/*     */           break; 
/* 160 */         char ch = message.charAt(endIdx);
/* 161 */         while (Character.isDigit(ch)) {
/*     */           
/* 163 */           idxBuff.append(ch);
/* 164 */           endIdx++;
/* 165 */           if (endIdx == message.length())
/*     */             break; 
/* 167 */           ch = message.charAt(endIdx);
/*     */         } 
/* 169 */         idx = idxBuff.toString();
/* 170 */         if (endIdx > startIdx && !JLbsStringUtil.isEmpty(idx))
/* 171 */           formatSpecs.add(new BasicOldFormatSpec(startIdx, endIdx, Integer.parseInt(idx))); 
/* 172 */         startIdx = message.indexOf("~", endIdx);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String formatArgument(ILbsCultureInfo culture, Object[] messageFormatArguments) {
/* 179 */       if (messageFormatArguments == null || messageFormatArguments.length <= this.m_Index || this.m_Index < 0)
/* 180 */         return ""; 
/* 181 */       Object arg = messageFormatArguments[this.m_Index];
/* 182 */       return String.valueOf(arg);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ComplexFormatSpec
/*     */     extends MessageFormatSpec
/*     */   {
/*     */     private String m_PropertyName;
/*     */     
/*     */     private LbsMessageFormatter.FormatSpec m_Format;
/*     */ 
/*     */     
/*     */     public ComplexFormatSpec() {}
/*     */ 
/*     */     
/*     */     public ComplexFormatSpec(String propertyName, LbsMessageFormatter.FormatSpec format) {
/* 199 */       this.m_PropertyName = propertyName;
/* 200 */       this.m_Format = format;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ComplexFormatSpec(int startPosition, int endPosition, int index, String propertyName, LbsMessageFormatter.FormatSpec format) {
/* 206 */       super(startPosition, endPosition, index);
/* 207 */       this.m_PropertyName = propertyName;
/* 208 */       this.m_Format = format;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void findFormatSpecs(String message, Vector<ComplexFormatSpec> formatSpecs) {
/* 214 */       if (message == null)
/*     */         return; 
/* 216 */       int startIdx = message.indexOf("{");
/* 217 */       Pattern pattern = Pattern.compile(",");
/* 218 */       while (startIdx >= 0) {
/*     */         
/* 220 */         this.m_PropertyName = null;
/* 221 */         int endIdx = message.indexOf("}", startIdx);
/* 222 */         String spec = message.substring(startIdx + 1, endIdx);
/* 223 */         String[] parts = pattern.split(spec);
/* 224 */         int length = parts.length;
/* 225 */         String format = null, id = "string";
/* 226 */         int index = -1;
/* 227 */         if (length > 0)
/* 228 */           index = processIndexPart(parts[0].trim()); 
/* 229 */         if (length > 1)
/* 230 */           id = parts[1].trim(); 
/* 231 */         if (length > 2)
/* 232 */           format = parts[2].trim(); 
/* 233 */         LbsMessageFormatter.FormatSpec formatSpec = (LbsMessageFormatter.FormatSpec)LbsMessageFormatter.ms_ComplexFormatSpecs.get(id);
/* 234 */         if (formatSpec != null) {
/*     */           
/* 236 */           formatSpec = formatSpec.createInstance(format);
/* 237 */           formatSpecs.add(new ComplexFormatSpec(startIdx, endIdx + 1, index, this.m_PropertyName, formatSpec));
/*     */         } 
/* 239 */         startIdx = message.indexOf("{", endIdx);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private int processIndexPart(String part) {
/* 246 */       int idx = part.indexOf('.');
/* 247 */       if (idx > 0) {
/*     */         
/* 249 */         this.m_PropertyName = part.substring(idx + 1);
/* 250 */         part = part.substring(0, idx);
/*     */       } 
/* 252 */       int index = Integer.parseInt(part);
/* 253 */       return index;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String formatArgument(ILbsCultureInfo culture, Object[] messageFormatArguments) {
/* 259 */       if (messageFormatArguments == null || messageFormatArguments.length <= this.m_Index || this.m_Index < 0)
/* 260 */         return ""; 
/* 261 */       Object arg = messageFormatArguments[this.m_Index];
/*     */       
/*     */       try {
/* 264 */         if (!JLbsStringUtil.isEmpty(this.m_PropertyName)) {
/* 265 */           arg = JLbsComponentHelper.getPropertyValue(arg, this.m_PropertyName);
/*     */         }
/* 267 */       } catch (Exception e) {
/*     */         
/* 269 */         e.printStackTrace();
/*     */         
/*     */         try {
/* 272 */           if (!JLbsStringUtil.isEmpty(this.m_PropertyName)) {
/* 273 */             arg = RttiUtil.getProperty(arg, this.m_PropertyName);
/*     */           }
/* 275 */         } catch (Exception e1) {
/*     */           
/* 277 */           e1.printStackTrace();
/*     */         } 
/*     */       } 
/* 280 */       return this.m_Format.format(culture, arg);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static abstract class FormatSpec
/*     */   {
/*     */     protected String m_Format;
/*     */ 
/*     */ 
/*     */     
/*     */     public FormatSpec() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public FormatSpec(String format) {
/* 297 */       this.m_Format = format;
/*     */     }
/*     */ 
/*     */     
/*     */     protected abstract String format(ILbsCultureInfo param1ILbsCultureInfo, Object param1Object);
/*     */ 
/*     */     
/*     */     protected abstract FormatSpec createInstance(String param1String);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class NumericFormatSpec
/*     */     extends FormatSpec
/*     */   {
/*     */     public NumericFormatSpec() {}
/*     */     
/*     */     public NumericFormatSpec(String format) {
/* 314 */       super(format);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected String format(ILbsCultureInfo culture, Object value) {
/* 320 */       Number number = null;
/* 321 */       if (value instanceof Number) {
/* 322 */         number = (Number)value;
/*     */       } else {
/* 324 */         return String.valueOf(value);
/* 325 */       }  int format = -1;
/*     */       
/*     */       try {
/* 328 */         format = Integer.parseInt(this.m_Format);
/*     */       }
/* 330 */       catch (Exception exception) {}
/*     */ 
/*     */       
/* 333 */       if (format <= 0 || format > 22)
/*     */       {
/* 335 */         if (JLbsStringUtil.isEmpty(this.m_Format)) {
/* 336 */           format = 1;
/*     */         } else {
/* 338 */           format = -1;
/*     */         }  } 
/* 340 */       if (format > 0)
/* 341 */         return (new JLbsRealNumberFormatter(format)).formatNumber(number); 
/* 342 */       if (!JLbsStringUtil.isEmpty(this.m_Format))
/* 343 */         return (new JLbsRealNumberFormatter(this.m_Format)).formatNumber(number); 
/* 344 */       return String.valueOf(value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected LbsMessageFormatter.FormatSpec createInstance(String format) {
/* 350 */       return new NumericFormatSpec(format);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class DateFormatSpec
/*     */     extends FormatSpec
/*     */   {
/*     */     public DateFormatSpec() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public DateFormatSpec(String format) {
/* 364 */       super(format);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected String format(ILbsCultureInfo culture, Object value) {
/* 370 */       Date date = null;
/* 371 */       JLbsTimeDuration duration = null;
/* 372 */       if (value instanceof Date) {
/* 373 */         date = (Date)value;
/* 374 */       } else if (value instanceof Calendar) {
/* 375 */         date = ((Calendar)value).getTime();
/* 376 */       } else if (value instanceof JLbsTimeDuration) {
/* 377 */         duration = (JLbsTimeDuration)value;
/*     */       } else {
/* 379 */         return String.valueOf(value);
/* 380 */       }  int format = -1;
/*     */       
/*     */       try {
/* 383 */         format = Integer.parseInt(this.m_Format);
/*     */       }
/* 385 */       catch (Exception exception) {}
/*     */ 
/*     */       
/* 388 */       if (format <= 0 || format > 26)
/*     */       {
/* 390 */         if (JLbsStringUtil.isEmpty(this.m_Format)) {
/* 391 */           format = 0;
/*     */         } else {
/* 393 */           format = -1;
/*     */         }  } 
/* 395 */       JLbsDateFormatter formatter = null;
/* 396 */       if (format > 0) {
/* 397 */         formatter = new JLbsDateFormatter(format, culture);
/* 398 */       } else if (!JLbsStringUtil.isEmpty(this.m_Format)) {
/* 399 */         formatter = new JLbsDateFormatter(this.m_Format, culture);
/* 400 */       }  if (formatter != null) {
/*     */         
/* 402 */         if (date != null)
/* 403 */           return formatter.format(date); 
/* 404 */         if (duration != null)
/* 405 */           return formatter.format(duration); 
/*     */       } 
/* 407 */       return String.valueOf(value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected LbsMessageFormatter.FormatSpec createInstance(String format) {
/* 413 */       return new DateFormatSpec(format);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class TimeFormatSpec
/*     */     extends FormatSpec
/*     */   {
/*     */     public TimeFormatSpec() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public TimeFormatSpec(String format) {
/* 427 */       super(format);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected String format(ILbsCultureInfo culture, Object value) {
/* 433 */       Date date = null;
/* 434 */       JLbsTimeDuration duration = null;
/* 435 */       if (value instanceof Date) {
/* 436 */         date = (Date)value;
/* 437 */       } else if (value instanceof Calendar) {
/* 438 */         date = ((Calendar)value).getTime();
/* 439 */       } else if (value instanceof JLbsTimeDuration) {
/* 440 */         duration = (JLbsTimeDuration)value;
/*     */       } else {
/* 442 */         return String.valueOf(value);
/* 443 */       }  int format = -1;
/*     */       
/*     */       try {
/* 446 */         format = Integer.parseInt(this.m_Format);
/*     */       }
/* 448 */       catch (Exception exception) {}
/*     */ 
/*     */       
/* 451 */       if (format <= 0 || format > 26)
/*     */       {
/* 453 */         if (JLbsStringUtil.isEmpty(this.m_Format)) {
/* 454 */           format = 21;
/*     */         } else {
/* 456 */           format = -1;
/*     */         }  } 
/* 458 */       JLbsDateFormatter formatter = null;
/* 459 */       if (format > 0) {
/* 460 */         formatter = new JLbsDateFormatter(format, culture);
/* 461 */       } else if (!JLbsStringUtil.isEmpty(this.m_Format)) {
/* 462 */         formatter = new JLbsDateFormatter(this.m_Format, culture);
/* 463 */       }  if (formatter != null) {
/*     */         
/* 465 */         if (date != null)
/* 466 */           return formatter.format(date); 
/* 467 */         if (duration != null)
/* 468 */           return formatter.format(duration); 
/*     */       } 
/* 470 */       return String.valueOf(value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected LbsMessageFormatter.FormatSpec createInstance(String format) {
/* 476 */       return new TimeFormatSpec(format);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class StringFormatSpec
/*     */     extends FormatSpec
/*     */   {
/*     */     public StringFormatSpec() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public StringFormatSpec(String format) {
/* 490 */       super(format);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected String format(ILbsCultureInfo culture, Object value) {
/* 496 */       return String.valueOf(value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected LbsMessageFormatter.FormatSpec createInstance(String format) {
/* 502 */       return new StringFormatSpec(format);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class DateTimeFormatSpec
/*     */     extends FormatSpec
/*     */   {
/*     */     private LbsMessageFormatter.DateFormatSpec m_DateFormat;
/*     */     
/*     */     private LbsMessageFormatter.TimeFormatSpec m_TimeFormat;
/*     */ 
/*     */     
/*     */     public DateTimeFormatSpec() {}
/*     */ 
/*     */     
/*     */     public DateTimeFormatSpec(LbsMessageFormatter.DateFormatSpec dateFormat, LbsMessageFormatter.TimeFormatSpec timeFormat) {
/* 520 */       this.m_DateFormat = dateFormat;
/* 521 */       this.m_TimeFormat = timeFormat;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected LbsMessageFormatter.FormatSpec createInstance(String format) {
/* 527 */       String[] parts = null;
/* 528 */       if (!JLbsStringUtil.isEmpty(format))
/*     */       {
/* 530 */         parts = format.split("-");
/*     */       }
/* 532 */       String dateFormat = null, timeFormat = null;
/* 533 */       if (parts != null && parts.length == 2) {
/*     */         
/* 535 */         dateFormat = parts[0].trim();
/* 536 */         timeFormat = parts[1].trim();
/*     */       } 
/* 538 */       if (JLbsStringUtil.isEmpty(dateFormat))
/* 539 */         dateFormat = "0"; 
/* 540 */       if (JLbsStringUtil.isEmpty(timeFormat))
/* 541 */         timeFormat = "21"; 
/* 542 */       return new DateTimeFormatSpec(new LbsMessageFormatter.DateFormatSpec(dateFormat), new LbsMessageFormatter.TimeFormatSpec(timeFormat));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected String format(ILbsCultureInfo culture, Object value) {
/* 548 */       return String.valueOf(this.m_DateFormat.format(culture, value)) + " " + this.m_TimeFormat.format(culture, value);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class TestObject
/*     */   {
/*     */     public String getCode() {
/* 602 */       return "MyCode";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 607 */       return "MyDescription";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\LbsMessageFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */