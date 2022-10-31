/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.objects.ObjectPropertyList;
/*     */ import com.lbs.data.query.QueryBusinessObject;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryUtil
/*     */ {
/*     */   public static BigDecimal getBigDecimalProp(ObjectPropertyList props, String prop) {
/*  17 */     if (props == null) {
/*  18 */       return null;
/*     */     }
/*  20 */     Object value = props.getValue(prop);
/*     */     
/*  22 */     if (value instanceof Double)
/*  23 */       return new BigDecimal(((Double)value).doubleValue()); 
/*  24 */     if (value instanceof BigDecimal) {
/*  25 */       return (BigDecimal)value;
/*     */     }
/*  27 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal getBigDecimalProp(QueryBusinessObject data, String prop) {
/*  32 */     if (data == null || data.getProperties() == null) {
/*  33 */       return null;
/*     */     }
/*  35 */     Object value = data.getProperties().getValue(prop);
/*     */     
/*  37 */     if (value instanceof Double)
/*  38 */       return new BigDecimal(((Double)value).doubleValue()); 
/*  39 */     if (value instanceof BigDecimal) {
/*  40 */       return (BigDecimal)value;
/*     */     }
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStringProp(ObjectPropertyList props, String prop) {
/*  48 */     if (props == null) {
/*  49 */       return null;
/*     */     }
/*  51 */     Object value = props.getValue(prop);
/*     */     
/*  53 */     return (value != null) ? (String)value : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringProp(QueryBusinessObject data, String prop) {
/*  58 */     if (data == null || data.getProperties() == null) {
/*  59 */       return null;
/*     */     }
/*  61 */     Object value = data.getProperties().getValue(prop);
/*     */     
/*  63 */     return (value != null) ? (String)value : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getIntProp(ObjectPropertyList props, String prop) {
/*  69 */     if (props == null) {
/*  70 */       return 0;
/*     */     }
/*  72 */     Object value = props.getValue(prop);
/*     */     
/*  74 */     return (value != null) ? ((Number)value).intValue() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getIntProp(QueryBusinessObject data, String prop) {
/*  79 */     if (data == null || data.getProperties() == null) {
/*  80 */       return 0;
/*     */     }
/*  82 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/*     */     
/*  84 */     return (value != null) ? ((Number)value).intValue() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getIntegerProp(QueryBusinessObject data, String prop) {
/*  89 */     if (data == null || data.getProperties() == null) {
/*  90 */       return Integer.valueOf(0);
/*     */     }
/*  92 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/*     */     
/*  94 */     return (value != null) ? (Integer)value : Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getIntegerProp(ObjectPropertyList props, String prop) {
/*  99 */     if (props == null) {
/* 100 */       return Integer.valueOf(0);
/*     */     }
/* 102 */     Object value = props.getValue(prop);
/*     */     
/* 104 */     return (value != null) ? (Integer)value : Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Calendar getDateProp(ObjectPropertyList props, String prop) {
/* 110 */     if (props == null) {
/* 111 */       return null;
/*     */     }
/* 113 */     Object value = props.getValue(prop);
/*     */     
/* 115 */     return (value instanceof Calendar) ? (Calendar)value : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getDateProp(QueryBusinessObject data, String prop) {
/* 120 */     if (data == null || data.getProperties() == null) {
/* 121 */       return null;
/*     */     }
/* 123 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/*     */     
/* 125 */     return (value instanceof Calendar) ? (Calendar)value : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static char getCharProp(ObjectPropertyList props, String prop) {
/* 131 */     if (props == null) {
/* 132 */       return '0';
/*     */     }
/* 134 */     Object value = props.getValue(prop);
/*     */     
/* 136 */     return (value != null) ? (char)((Integer)value).intValue() : '0';
/*     */   }
/*     */ 
/*     */   
/*     */   public static char getCharProp(QueryBusinessObject data, String prop) {
/* 141 */     if (data == null || data.getProperties() == null) {
/* 142 */       return '0';
/*     */     }
/* 144 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/*     */     
/* 146 */     return (value != null) ? (char)((Integer)value).intValue() : '0';
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getbooleanPProp(QueryBusinessObject data, String prop) {
/* 151 */     if (data == null || data.getProperties() == null) {
/* 152 */       return false;
/*     */     }
/* 154 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/* 155 */     return (value != null) ? ((Boolean)value).booleanValue() : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Boolean getBooleanProp(ObjectPropertyList props, String prop) {
/* 160 */     if (props == null) {
/* 161 */       return new Boolean(false);
/*     */     }
/* 163 */     Object value = props.getValue(prop);
/* 164 */     if (value != null && value instanceof Number)
/* 165 */       return (((Number)value).intValue() == 1) ? Boolean.valueOf(true) : Boolean.valueOf(false); 
/* 166 */     return (value != null) ? (Boolean)value : new Boolean(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte getbytePProp(QueryBusinessObject data, String prop) {
/* 171 */     if (data == null || data.getProperties() == null) {
/* 172 */       return 0;
/*     */     }
/* 174 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/* 175 */     return (value != null) ? ((Byte)value).byteValue() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Byte getByteProp(ObjectPropertyList props, String prop) {
/* 180 */     if (props == null) {
/* 181 */       return Byte.valueOf((byte)0);
/*     */     }
/* 183 */     Object value = props.getValue(prop);
/* 184 */     return (value != null) ? (Byte)value : Byte.valueOf((byte)0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] getByteArrProp(ObjectPropertyList props, String prop) {
/* 189 */     if (props == null) {
/* 190 */       return null;
/*     */     }
/* 192 */     Object value = props.getValue(prop);
/* 193 */     return (value != null) ? (byte[])value : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getdoublePProp(QueryBusinessObject data, String prop) {
/* 198 */     if (data == null || data.getProperties() == null) {
/* 199 */       return 0.0D;
/*     */     }
/* 201 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/*     */     
/* 203 */     if (value instanceof Double) {
/* 204 */       return ((Double)value).doubleValue();
/*     */     }
/* 206 */     if (value instanceof Float) {
/* 207 */       return ((Float)value).doubleValue();
/*     */     }
/* 209 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Double getDoubleProp(ObjectPropertyList props, String prop) {
/* 214 */     if (props == null) {
/* 215 */       return new Double(0.0D);
/*     */     }
/* 217 */     Object value = props.getValue(prop);
/*     */     
/* 219 */     if (value instanceof Double) {
/* 220 */       return (Double)value;
/*     */     }
/* 222 */     if (value instanceof Float) {
/* 223 */       return new Double(((Float)value).doubleValue());
/*     */     }
/* 225 */     return new Double(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getfloatPProp(QueryBusinessObject data, String prop) {
/* 230 */     if (data == null || data.getProperties() == null) {
/* 231 */       return 0.0F;
/*     */     }
/* 233 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/* 234 */     return (value != null) ? ((Float)value).floatValue() : 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Float getFloatProp(ObjectPropertyList props, String prop) {
/* 239 */     if (props == null) {
/* 240 */       return new Float(0.0D);
/*     */     }
/* 242 */     Object value = props.getValue(prop);
/* 243 */     return (value != null) ? (Float)value : new Float(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getlongPProp(QueryBusinessObject data, String prop) {
/* 248 */     if (data == null || data.getProperties() == null) {
/* 249 */       return 0L;
/*     */     }
/* 251 */     Object value = (data == null) ? null : data.getProperties().getValue(prop);
/* 252 */     return (value != null) ? ((Long)value).longValue() : 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Long getLongProp(ObjectPropertyList props, String prop) {
/* 257 */     if (props == null) {
/* 258 */       return Long.valueOf(0L);
/*     */     }
/* 260 */     Object value = props.getValue(prop);
/* 261 */     return (value != null) ? (Long)value : Long.valueOf(0L);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\QueryUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */