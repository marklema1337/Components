/*     */ package com.lbs.juel.misc;
/*     */ 
/*     */ import com.lbs.javax.el.ELException;
/*     */ import java.beans.PropertyEditor;
/*     */ import java.beans.PropertyEditorManager;
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
/*     */ public class TypeConverterImpl
/*     */   implements TypeConverter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected Boolean coerceToBoolean(Object value) {
/*  34 */     if (value == null || "".equals(value))
/*     */     {
/*  36 */       return Boolean.FALSE;
/*     */     }
/*  38 */     if (value instanceof Boolean)
/*     */     {
/*  40 */       return (Boolean)value;
/*     */     }
/*  42 */     if (value instanceof String)
/*     */     {
/*  44 */       return Boolean.valueOf((String)value);
/*     */     }
/*  46 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Boolean.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Character coerceToCharacter(Object value) {
/*  51 */     if (value == null || "".equals(value))
/*     */     {
/*  53 */       return Character.valueOf(false);
/*     */     }
/*  55 */     if (value instanceof Character)
/*     */     {
/*  57 */       return (Character)value;
/*     */     }
/*  59 */     if (value instanceof Number)
/*     */     {
/*  61 */       return Character.valueOf((char)((Number)value).shortValue());
/*     */     }
/*  63 */     if (value instanceof String)
/*     */     {
/*  65 */       return Character.valueOf(((String)value).charAt(0));
/*     */     }
/*  67 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Character.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BigDecimal coerceToBigDecimal(Object value) {
/*  72 */     if (value == null || "".equals(value))
/*     */     {
/*  74 */       return BigDecimal.valueOf(0L);
/*     */     }
/*  76 */     if (value instanceof BigDecimal)
/*     */     {
/*  78 */       return (BigDecimal)value;
/*     */     }
/*  80 */     if (value instanceof BigInteger)
/*     */     {
/*  82 */       return new BigDecimal((BigInteger)value);
/*     */     }
/*  84 */     if (value instanceof Number)
/*     */     {
/*  86 */       return new BigDecimal(((Number)value).doubleValue());
/*     */     }
/*  88 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/*  92 */         return new BigDecimal((String)value);
/*     */       }
/*  94 */       catch (NumberFormatException e) {
/*     */         
/*  96 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, BigDecimal.class }));
/*     */       } 
/*     */     }
/*  99 */     if (value instanceof Character)
/*     */     {
/* 101 */       return new BigDecimal((short)((Character)value).charValue());
/*     */     }
/* 103 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), BigDecimal.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BigInteger coerceToBigInteger(Object value) {
/* 108 */     if (value == null || "".equals(value))
/*     */     {
/* 110 */       return BigInteger.valueOf(0L);
/*     */     }
/* 112 */     if (value instanceof BigInteger)
/*     */     {
/* 114 */       return (BigInteger)value;
/*     */     }
/* 116 */     if (value instanceof BigDecimal)
/*     */     {
/* 118 */       return ((BigDecimal)value).toBigInteger();
/*     */     }
/* 120 */     if (value instanceof Number)
/*     */     {
/* 122 */       return BigInteger.valueOf(((Number)value).longValue());
/*     */     }
/* 124 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 128 */         return new BigInteger((String)value);
/*     */       }
/* 130 */       catch (NumberFormatException e) {
/*     */         
/* 132 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, BigInteger.class }));
/*     */       } 
/*     */     }
/* 135 */     if (value instanceof Character)
/*     */     {
/* 137 */       return BigInteger.valueOf((short)((Character)value).charValue());
/*     */     }
/* 139 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), BigInteger.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Double coerceToDouble(Object value) {
/* 144 */     if (value == null || "".equals(value))
/*     */     {
/* 146 */       return Double.valueOf(0.0D);
/*     */     }
/* 148 */     if (value instanceof Double)
/*     */     {
/* 150 */       return (Double)value;
/*     */     }
/* 152 */     if (value instanceof Number)
/*     */     {
/* 154 */       return Double.valueOf(((Number)value).doubleValue());
/*     */     }
/* 156 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 160 */         return Double.valueOf((String)value);
/*     */       }
/* 162 */       catch (NumberFormatException e) {
/*     */         
/* 164 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Double.class }));
/*     */       } 
/*     */     }
/* 167 */     if (value instanceof Character)
/*     */     {
/* 169 */       return Double.valueOf((short)((Character)value).charValue());
/*     */     }
/* 171 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Double.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Float coerceToFloat(Object value) {
/* 176 */     if (value == null || "".equals(value))
/*     */     {
/* 178 */       return Float.valueOf(0.0F);
/*     */     }
/* 180 */     if (value instanceof Float)
/*     */     {
/* 182 */       return (Float)value;
/*     */     }
/* 184 */     if (value instanceof Number)
/*     */     {
/* 186 */       return Float.valueOf(((Number)value).floatValue());
/*     */     }
/* 188 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 192 */         return Float.valueOf((String)value);
/*     */       }
/* 194 */       catch (NumberFormatException e) {
/*     */         
/* 196 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Float.class }));
/*     */       } 
/*     */     }
/* 199 */     if (value instanceof Character)
/*     */     {
/* 201 */       return Float.valueOf((short)((Character)value).charValue());
/*     */     }
/* 203 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Float.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Long coerceToLong(Object value) {
/* 208 */     if (value == null || "".equals(value))
/*     */     {
/* 210 */       return Long.valueOf(0L);
/*     */     }
/* 212 */     if (value instanceof Long)
/*     */     {
/* 214 */       return (Long)value;
/*     */     }
/* 216 */     if (value instanceof Number)
/*     */     {
/* 218 */       return Long.valueOf(((Number)value).longValue());
/*     */     }
/* 220 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 224 */         return Long.valueOf((String)value);
/*     */       }
/* 226 */       catch (NumberFormatException e) {
/*     */         
/* 228 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Long.class }));
/*     */       } 
/*     */     }
/* 231 */     if (value instanceof Character)
/*     */     {
/* 233 */       return Long.valueOf((short)((Character)value).charValue());
/*     */     }
/* 235 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Long.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer coerceToInteger(Object value) {
/* 240 */     if (value == null || "".equals(value))
/*     */     {
/* 242 */       return Integer.valueOf(0);
/*     */     }
/* 244 */     if (value instanceof Integer)
/*     */     {
/* 246 */       return (Integer)value;
/*     */     }
/* 248 */     if (value instanceof Number)
/*     */     {
/* 250 */       return Integer.valueOf(((Number)value).intValue());
/*     */     }
/* 252 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 256 */         return Integer.valueOf((String)value);
/*     */       }
/* 258 */       catch (NumberFormatException e) {
/*     */         
/* 260 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Integer.class }));
/*     */       } 
/*     */     }
/* 263 */     if (value instanceof Character)
/*     */     {
/* 265 */       return Integer.valueOf((short)((Character)value).charValue());
/*     */     }
/* 267 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Integer.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Short coerceToShort(Object value) {
/* 272 */     if (value == null || "".equals(value))
/*     */     {
/* 274 */       return Short.valueOf((short)0);
/*     */     }
/* 276 */     if (value instanceof Short)
/*     */     {
/* 278 */       return (Short)value;
/*     */     }
/* 280 */     if (value instanceof Number)
/*     */     {
/* 282 */       return Short.valueOf(((Number)value).shortValue());
/*     */     }
/* 284 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 288 */         return Short.valueOf((String)value);
/*     */       }
/* 290 */       catch (NumberFormatException e) {
/*     */         
/* 292 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Short.class }));
/*     */       } 
/*     */     }
/* 295 */     if (value instanceof Character)
/*     */     {
/* 297 */       return Short.valueOf((short)((Character)value).charValue());
/*     */     }
/* 299 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Short.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Byte coerceToByte(Object value) {
/* 304 */     if (value == null || "".equals(value))
/*     */     {
/* 306 */       return Byte.valueOf((byte)0);
/*     */     }
/* 308 */     if (value instanceof Byte)
/*     */     {
/* 310 */       return (Byte)value;
/*     */     }
/* 312 */     if (value instanceof Number)
/*     */     {
/* 314 */       return Byte.valueOf(((Number)value).byteValue());
/*     */     }
/* 316 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 320 */         return Byte.valueOf((String)value);
/*     */       }
/* 322 */       catch (NumberFormatException e) {
/*     */         
/* 324 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, Byte.class }));
/*     */       } 
/*     */     }
/* 327 */     if (value instanceof Character)
/*     */     {
/* 329 */       return Byte.valueOf(Short.valueOf((short)((Character)value).charValue()).byteValue());
/*     */     }
/* 331 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), Byte.class }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String coerceToString(Object value) {
/* 336 */     if (value == null)
/*     */     {
/* 338 */       return "";
/*     */     }
/* 340 */     if (value instanceof String)
/*     */     {
/* 342 */       return (String)value;
/*     */     }
/* 344 */     if (value instanceof Enum)
/*     */     {
/* 346 */       return ((Enum)value).name();
/*     */     }
/* 348 */     return value.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected <T extends Enum<T>> T coerceToEnum(Object value, Class<T> type) {
/* 354 */     if (value == null || "".equals(value))
/*     */     {
/* 356 */       return null;
/*     */     }
/* 358 */     if (type.isInstance(value))
/*     */     {
/* 360 */       return (T)value;
/*     */     }
/* 362 */     if (value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 366 */         return Enum.valueOf(type, (String)value);
/*     */       }
/* 368 */       catch (IllegalArgumentException e) {
/*     */         
/* 370 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, type }));
/*     */       } 
/*     */     }
/* 373 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), type }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object coerceStringToType(String value, Class<?> type) {
/* 378 */     PropertyEditor editor = PropertyEditorManager.findEditor(type);
/* 379 */     if (editor == null) {
/*     */       
/* 381 */       if ("".equals(value))
/*     */       {
/* 383 */         return null;
/*     */       }
/* 385 */       throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { String.class, type }));
/*     */     } 
/*     */ 
/*     */     
/* 389 */     if ("".equals(value)) {
/*     */ 
/*     */       
/*     */       try {
/* 393 */         editor.setAsText(value);
/*     */       }
/* 395 */       catch (IllegalArgumentException e) {
/*     */         
/* 397 */         return null;
/*     */       } 
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 404 */         editor.setAsText(value);
/*     */       }
/* 406 */       catch (IllegalArgumentException e) {
/*     */         
/* 408 */         throw new ELException(LocalMessages.get("error.coerce.value", new Object[] { value, type }));
/*     */       } 
/*     */     } 
/* 411 */     return editor.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object coerceToType(Object value, Class<?> type) {
/* 418 */     if (type == String.class)
/*     */     {
/* 420 */       return coerceToString(value);
/*     */     }
/* 422 */     if (type == Long.class || type == long.class)
/*     */     {
/* 424 */       return coerceToLong(value);
/*     */     }
/* 426 */     if (type == Double.class || type == double.class)
/*     */     {
/* 428 */       return coerceToDouble(value);
/*     */     }
/* 430 */     if (type == Boolean.class || type == boolean.class)
/*     */     {
/* 432 */       return coerceToBoolean(value);
/*     */     }
/* 434 */     if (type == Integer.class || type == int.class)
/*     */     {
/* 436 */       return coerceToInteger(value);
/*     */     }
/* 438 */     if (type == Float.class || type == float.class)
/*     */     {
/* 440 */       return coerceToFloat(value);
/*     */     }
/* 442 */     if (type == Short.class || type == short.class)
/*     */     {
/* 444 */       return coerceToShort(value);
/*     */     }
/* 446 */     if (type == Byte.class || type == byte.class)
/*     */     {
/* 448 */       return coerceToByte(value);
/*     */     }
/* 450 */     if (type == Character.class || type == char.class)
/*     */     {
/* 452 */       return coerceToCharacter(value);
/*     */     }
/* 454 */     if (type == BigDecimal.class)
/*     */     {
/* 456 */       return coerceToBigDecimal(value);
/*     */     }
/* 458 */     if (type == BigInteger.class)
/*     */     {
/* 460 */       return coerceToBigInteger(value);
/*     */     }
/* 462 */     if (type.getSuperclass() == Enum.class)
/*     */     {
/* 464 */       return coerceToEnum(value, type);
/*     */     }
/* 466 */     if (value == null || value.getClass() == type || type.isInstance(value))
/*     */     {
/* 468 */       return value;
/*     */     }
/* 470 */     if (value instanceof String)
/*     */     {
/* 472 */       return coerceStringToType((String)value, type);
/*     */     }
/* 474 */     throw new ELException(LocalMessages.get("error.coerce.type", new Object[] { value.getClass(), type }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 480 */     return (obj != null && obj.getClass().equals(getClass()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 486 */     return getClass().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T convert(Object value, Class<T> type) throws ELException {
/* 492 */     return (T)coerceToType(value, type);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\misc\TypeConverterImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */