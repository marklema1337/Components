/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConvertableValue
/*     */   implements IConvertable
/*     */ {
/*  19 */   ILbsCultureInfo m_CultureInfo = null;
/*  20 */   Object m_Value = null;
/*     */ 
/*     */   
/*     */   public ConvertableValue(Object value) {
/*  24 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(Object value, ILbsCultureInfo cultureInfo) {
/*  29 */     this.m_Value = value;
/*  30 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(boolean value) {
/*  35 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(boolean value, ILbsCultureInfo cultureInfo) {
/*  40 */     this.m_Value = new Boolean(value);
/*  41 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(char value) {
/*  46 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(char value, ILbsCultureInfo cultureInfo) {
/*  51 */     this.m_Value = new Character(value);
/*  52 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(byte value) {
/*  57 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(byte value, ILbsCultureInfo cultureInfo) {
/*  62 */     this.m_Value = Byte.valueOf(value);
/*  63 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(int value) {
/*  68 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(int value, ILbsCultureInfo cultureInfo) {
/*  73 */     this.m_Value = Integer.valueOf(value);
/*  74 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(long value) {
/*  79 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(long value, ILbsCultureInfo cultureInfo) {
/*  84 */     this.m_Value = Long.valueOf(value);
/*  85 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(float value) {
/*  90 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(float value, ILbsCultureInfo cultureInfo) {
/*  95 */     this.m_Value = new Float(value);
/*  96 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(double value) {
/* 101 */     this(value, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConvertableValue(double value, ILbsCultureInfo cultureInfo) {
/* 106 */     this.m_Value = new Double(value);
/* 107 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCultureInfo(Object cultureInfo) {
/* 115 */     if (cultureInfo instanceof ILbsCultureInfo)
/*     */     {
/* 117 */       this.m_CultureInfo = (ILbsCultureInfo)cultureInfo;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/* 126 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean() throws ConversionNotSupportedException, ConversionDataLoss {
/* 134 */     if (this.m_Value != null) {
/*     */       
/* 136 */       Boolean b = (Boolean)ValueConverter.convert(this.m_Value.getClass(), Boolean.class, this.m_Value, this.m_CultureInfo);
/* 137 */       return b.booleanValue();
/*     */     } 
/*     */     
/* 140 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte() throws ConversionNotSupportedException, ConversionDataLoss {
/* 148 */     if (this.m_Value != null) {
/*     */       
/* 150 */       Byte b = (Byte)ValueConverter.convert(this.m_Value.getClass(), Byte.class, this.m_Value, this.m_CultureInfo);
/* 151 */       return b.byteValue();
/*     */     } 
/*     */     
/* 154 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar() throws ConversionNotSupportedException, ConversionDataLoss {
/* 163 */     if (this.m_Value != null) {
/*     */ 
/*     */       
/* 166 */       Character c = (Character)ValueConverter.convert(this.m_Value.getClass(), Character.class, this.m_Value, this.m_CultureInfo);
/* 167 */       return c.charValue();
/*     */     } 
/*     */     
/* 170 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt() throws ConversionNotSupportedException, ConversionDataLoss {
/* 179 */     if (this.m_Value != null) {
/*     */       
/* 181 */       Integer i = (Integer)ValueConverter.convert(this.m_Value.getClass(), Integer.class, this.m_Value, this.m_CultureInfo);
/* 182 */       return i.intValue();
/*     */     } 
/*     */     
/* 185 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong() throws ConversionNotSupportedException, ConversionDataLoss {
/* 194 */     if (this.m_Value != null) {
/*     */       
/* 196 */       Long l = (Long)ValueConverter.convert(this.m_Value.getClass(), Long.class, this.m_Value, this.m_CultureInfo);
/* 197 */       return l.longValue();
/*     */     } 
/*     */     
/* 200 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort() throws ConversionNotSupportedException, ConversionDataLoss {
/* 209 */     if (this.m_Value != null) {
/*     */       
/* 211 */       Short s = (Short)ValueConverter.convert(this.m_Value.getClass(), Short.class, this.m_Value, this.m_CultureInfo);
/* 212 */       return s.shortValue();
/*     */     } 
/*     */     
/* 215 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat() throws ConversionNotSupportedException, ConversionDataLoss {
/* 224 */     if (this.m_Value != null) {
/*     */       
/* 226 */       Float f = (Float)ValueConverter.convert(this.m_Value.getClass(), Float.class, this.m_Value, this.m_CultureInfo);
/* 227 */       return f.floatValue();
/*     */     } 
/*     */     
/* 230 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble() throws ConversionNotSupportedException, ConversionDataLoss {
/* 239 */     if (this.m_Value != null) {
/*     */       
/* 241 */       Double d = (Double)ValueConverter.convert(this.m_Value.getClass(), Double.class, this.m_Value, this.m_CultureInfo);
/* 242 */       return d.doubleValue();
/*     */     } 
/*     */     
/* 245 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigInteger getBigInteger() throws ConversionNotSupportedException, ConversionDataLoss {
/* 254 */     if (this.m_Value != null) {
/*     */ 
/*     */       
/* 257 */       BigInteger bi = (BigInteger)ValueConverter.convert(this.m_Value.getClass(), BigInteger.class, this.m_Value, this.m_CultureInfo);
/* 258 */       return bi;
/*     */     } 
/*     */     
/* 261 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getBigDecimal() throws ConversionNotSupportedException, ConversionDataLoss {
/* 270 */     if (this.m_Value != null) {
/*     */ 
/*     */       
/* 273 */       BigDecimal bd = (BigDecimal)ValueConverter.convert(this.m_Value.getClass(), BigDecimal.class, this.m_Value, this.m_CultureInfo);
/* 274 */       return bd;
/*     */     } 
/*     */     
/* 277 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() throws ConversionNotSupportedException, ConversionDataLoss {
/* 286 */     if (this.m_Value != null) {
/*     */       
/* 288 */       String s = (String)ValueConverter.convert(this.m_Value.getClass(), String.class, this.m_Value, this.m_CultureInfo);
/* 289 */       return s;
/*     */     } 
/*     */     
/* 292 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() throws ConversionNotSupportedException, ConversionDataLoss {
/* 301 */     if (this.m_Value != null) {
/*     */       
/* 303 */       Calendar c = (Calendar)ValueConverter.convert(this.m_Value.getClass(), Calendar.class, this.m_Value, this.m_CultureInfo);
/* 304 */       return c;
/*     */     } 
/*     */     
/* 307 */     throw new ConversionNotSupportedException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ConvertableValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */