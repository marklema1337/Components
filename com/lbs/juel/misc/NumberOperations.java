/*     */ package com.lbs.juel.misc;
/*     */ 
/*     */ import com.lbs.javax.el.ELException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
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
/*     */ public class NumberOperations
/*     */ {
/*  30 */   private static final Long LONG_ZERO = Long.valueOf(0L);
/*     */ 
/*     */   
/*     */   private static final boolean isDotEe(String value) {
/*  34 */     int length = value.length();
/*  35 */     for (int i = 0; i < length; i++) {
/*     */       
/*  37 */       switch (value.charAt(i)) {
/*     */         
/*     */         case '.':
/*     */         case 'E':
/*     */         case 'e':
/*  42 */           return true;
/*     */       } 
/*     */     } 
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isDotEe(Object value) {
/*  50 */     return (value instanceof String && isDotEe((String)value));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isFloatOrDouble(Object value) {
/*  55 */     return !(!(value instanceof Float) && !(value instanceof Double));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isFloatOrDoubleOrDotEe(Object value) {
/*  60 */     return !(!isFloatOrDouble(value) && !isDotEe(value));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isBigDecimalOrBigInteger(Object value) {
/*  65 */     return !(!(value instanceof BigDecimal) && !(value instanceof BigInteger));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isBigDecimalOrFloatOrDoubleOrDotEe(Object value) {
/*  70 */     return !(!(value instanceof BigDecimal) && !isFloatOrDoubleOrDotEe(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number add(TypeConverter converter, Object o1, Object o2) {
/*  75 */     if (o1 == null && o2 == null)
/*     */     {
/*  77 */       return LONG_ZERO;
/*     */     }
/*  79 */     if (o1 instanceof BigDecimal || o2 instanceof BigDecimal)
/*     */     {
/*  81 */       return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).add(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */     }
/*  83 */     if (isFloatOrDoubleOrDotEe(o1) || isFloatOrDoubleOrDotEe(o2)) {
/*     */       
/*  85 */       if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */       {
/*  87 */         return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).add(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */       }
/*  89 */       return Double.valueOf(((Double)converter.<Double>convert(o1, Double.class)).doubleValue() + ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     } 
/*  91 */     if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */     {
/*  93 */       return ((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).add(converter.<BigInteger>convert(o2, BigInteger.class));
/*     */     }
/*  95 */     return Long.valueOf(((Long)converter.<Long>convert(o1, Long.class)).longValue() + ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number sub(TypeConverter converter, Object o1, Object o2) {
/* 100 */     if (o1 == null && o2 == null)
/*     */     {
/* 102 */       return LONG_ZERO;
/*     */     }
/* 104 */     if (o1 instanceof BigDecimal || o2 instanceof BigDecimal)
/*     */     {
/* 106 */       return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).subtract(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */     }
/* 108 */     if (isFloatOrDoubleOrDotEe(o1) || isFloatOrDoubleOrDotEe(o2)) {
/*     */       
/* 110 */       if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */       {
/* 112 */         return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).subtract(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */       }
/* 114 */       return Double.valueOf(((Double)converter.<Double>convert(o1, Double.class)).doubleValue() - ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     } 
/* 116 */     if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */     {
/* 118 */       return ((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).subtract(converter.<BigInteger>convert(o2, BigInteger.class));
/*     */     }
/* 120 */     return Long.valueOf(((Long)converter.<Long>convert(o1, Long.class)).longValue() - ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number mul(TypeConverter converter, Object o1, Object o2) {
/* 125 */     if (o1 == null && o2 == null)
/*     */     {
/* 127 */       return LONG_ZERO;
/*     */     }
/* 129 */     if (o1 instanceof BigDecimal || o2 instanceof BigDecimal)
/*     */     {
/* 131 */       return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).multiply(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */     }
/* 133 */     if (isFloatOrDoubleOrDotEe(o1) || isFloatOrDoubleOrDotEe(o2)) {
/*     */       
/* 135 */       if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */       {
/* 137 */         return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).multiply(converter.<BigDecimal>convert(o2, BigDecimal.class));
/*     */       }
/* 139 */       return Double.valueOf(((Double)converter.<Double>convert(o1, Double.class)).doubleValue() * ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     } 
/* 141 */     if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */     {
/* 143 */       return ((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).multiply(converter.<BigInteger>convert(o2, BigInteger.class));
/*     */     }
/* 145 */     return Long.valueOf(((Long)converter.<Long>convert(o1, Long.class)).longValue() * ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number div(TypeConverter converter, Object o1, Object o2) {
/* 150 */     if (o1 == null && o2 == null)
/*     */     {
/* 152 */       return LONG_ZERO;
/*     */     }
/* 154 */     if (isBigDecimalOrBigInteger(o1) || isBigDecimalOrBigInteger(o2))
/*     */     {
/* 156 */       return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class))
/* 157 */         .divide(converter.<BigDecimal>convert(o2, BigDecimal.class), 4);
/*     */     }
/* 159 */     return Double.valueOf(((Double)converter.<Double>convert(o1, Double.class)).doubleValue() / ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number mod(TypeConverter converter, Object o1, Object o2) {
/* 164 */     if (o1 == null && o2 == null)
/*     */     {
/* 166 */       return LONG_ZERO;
/*     */     }
/* 168 */     if (isBigDecimalOrFloatOrDoubleOrDotEe(o1) || isBigDecimalOrFloatOrDoubleOrDotEe(o2))
/*     */     {
/* 170 */       return Double.valueOf(((Double)converter.<Double>convert(o1, Double.class)).doubleValue() % ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     }
/* 172 */     if (o1 instanceof BigInteger || o2 instanceof BigInteger)
/*     */     {
/* 174 */       return ((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).remainder(converter.<BigInteger>convert(o2, BigInteger.class));
/*     */     }
/* 176 */     return Long.valueOf(((Long)converter.<Long>convert(o1, Long.class)).longValue() % ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Number neg(TypeConverter converter, Object value) {
/* 181 */     if (value == null)
/*     */     {
/* 183 */       return LONG_ZERO;
/*     */     }
/* 185 */     if (value instanceof BigDecimal)
/*     */     {
/* 187 */       return ((BigDecimal)value).negate();
/*     */     }
/* 189 */     if (value instanceof BigInteger)
/*     */     {
/* 191 */       return ((BigInteger)value).negate();
/*     */     }
/* 193 */     if (value instanceof Double)
/*     */     {
/* 195 */       return Double.valueOf(-((Double)value).doubleValue());
/*     */     }
/* 197 */     if (value instanceof Float)
/*     */     {
/* 199 */       return Float.valueOf(-((Float)value).floatValue());
/*     */     }
/* 201 */     if (value instanceof String) {
/*     */       
/* 203 */       if (isDotEe((String)value))
/*     */       {
/* 205 */         return Double.valueOf(-((Double)converter.convert(value, (Class)Double.class)).doubleValue());
/*     */       }
/* 207 */       return Long.valueOf(-((Long)converter.convert(value, (Class)Long.class)).longValue());
/*     */     } 
/* 209 */     if (value instanceof Long)
/*     */     {
/* 211 */       return Long.valueOf(-((Long)value).longValue());
/*     */     }
/* 213 */     if (value instanceof Integer)
/*     */     {
/* 215 */       return Integer.valueOf(-((Integer)value).intValue());
/*     */     }
/* 217 */     if (value instanceof Short)
/*     */     {
/* 219 */       return Short.valueOf((short)-((Short)value).shortValue());
/*     */     }
/* 221 */     if (value instanceof Byte)
/*     */     {
/* 223 */       return Byte.valueOf((byte)-((Byte)value).byteValue());
/*     */     }
/* 225 */     throw new ELException(LocalMessages.get("error.negate", new Object[] { value.getClass() }));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\misc\NumberOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */