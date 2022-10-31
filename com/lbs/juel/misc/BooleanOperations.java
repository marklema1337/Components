/*     */ package com.lbs.juel.misc;
/*     */ 
/*     */ import com.lbs.javax.el.ELException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class BooleanOperations
/*     */ {
/*  29 */   private static final Set<Class<? extends Number>> SIMPLE_INTEGER_TYPES = new HashSet<>();
/*  30 */   private static final Set<Class<? extends Number>> SIMPLE_FLOAT_TYPES = new HashSet<>();
/*     */ 
/*     */   
/*     */   static {
/*  34 */     SIMPLE_INTEGER_TYPES.add(Byte.class);
/*  35 */     SIMPLE_INTEGER_TYPES.add(Short.class);
/*  36 */     SIMPLE_INTEGER_TYPES.add(Integer.class);
/*  37 */     SIMPLE_INTEGER_TYPES.add(Long.class);
/*  38 */     SIMPLE_FLOAT_TYPES.add(Float.class);
/*  39 */     SIMPLE_FLOAT_TYPES.add(Double.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean lt0(TypeConverter converter, Object o1, Object o2) {
/*  45 */     Class<?> t1 = o1.getClass();
/*  46 */     Class<?> t2 = o2.getClass();
/*  47 */     if (BigDecimal.class.isAssignableFrom(t1) || BigDecimal.class.isAssignableFrom(t2))
/*     */     {
/*  49 */       return (((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).compareTo(converter.<BigDecimal>convert(o2, BigDecimal.class)) < 0);
/*     */     }
/*  51 */     if (SIMPLE_FLOAT_TYPES.contains(t1) || SIMPLE_FLOAT_TYPES.contains(t2))
/*     */     {
/*  53 */       return (((Double)converter.<Double>convert(o1, Double.class)).doubleValue() < ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     }
/*  55 */     if (BigInteger.class.isAssignableFrom(t1) || BigInteger.class.isAssignableFrom(t2))
/*     */     {
/*  57 */       return (((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).compareTo(converter.<BigInteger>convert(o2, BigInteger.class)) < 0);
/*     */     }
/*  59 */     if (SIMPLE_INTEGER_TYPES.contains(t1) || SIMPLE_INTEGER_TYPES.contains(t2))
/*     */     {
/*  61 */       return (((Long)converter.<Long>convert(o1, Long.class)).longValue() < ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */     }
/*  63 */     if (t1 == String.class || t2 == String.class)
/*     */     {
/*  65 */       return (((String)converter.<String>convert(o1, String.class)).compareTo(converter.<String>convert(o2, String.class)) < 0);
/*     */     }
/*  67 */     if (o1 instanceof Comparable)
/*     */     {
/*  69 */       return (((Comparable<Object>)o1).compareTo(o2) < 0);
/*     */     }
/*  71 */     if (o2 instanceof Comparable)
/*     */     {
/*  73 */       return (((Comparable<Object>)o2).compareTo(o1) > 0);
/*     */     }
/*  75 */     throw new ELException(LocalMessages.get("error.compare.types", new Object[] { o1.getClass(), o2.getClass() }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean gt0(TypeConverter converter, Object o1, Object o2) {
/*  81 */     Class<?> t1 = o1.getClass();
/*  82 */     Class<?> t2 = o2.getClass();
/*  83 */     if (BigDecimal.class.isAssignableFrom(t1) || BigDecimal.class.isAssignableFrom(t2))
/*     */     {
/*  85 */       return (((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).compareTo(converter.<BigDecimal>convert(o2, BigDecimal.class)) > 0);
/*     */     }
/*  87 */     if (SIMPLE_FLOAT_TYPES.contains(t1) || SIMPLE_FLOAT_TYPES.contains(t2))
/*     */     {
/*  89 */       return (((Double)converter.<Double>convert(o1, Double.class)).doubleValue() > ((Double)converter.<Double>convert(o2, Double.class)).doubleValue());
/*     */     }
/*  91 */     if (BigInteger.class.isAssignableFrom(t1) || BigInteger.class.isAssignableFrom(t2))
/*     */     {
/*  93 */       return (((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).compareTo(converter.<BigInteger>convert(o2, BigInteger.class)) > 0);
/*     */     }
/*  95 */     if (SIMPLE_INTEGER_TYPES.contains(t1) || SIMPLE_INTEGER_TYPES.contains(t2))
/*     */     {
/*  97 */       return (((Long)converter.<Long>convert(o1, Long.class)).longValue() > ((Long)converter.<Long>convert(o2, Long.class)).longValue());
/*     */     }
/*  99 */     if (t1 == String.class || t2 == String.class)
/*     */     {
/* 101 */       return (((String)converter.<String>convert(o1, String.class)).compareTo(converter.<String>convert(o2, String.class)) > 0);
/*     */     }
/* 103 */     if (o1 instanceof Comparable)
/*     */     {
/* 105 */       return (((Comparable<Object>)o1).compareTo(o2) > 0);
/*     */     }
/* 107 */     if (o2 instanceof Comparable)
/*     */     {
/* 109 */       return (((Comparable<Object>)o2).compareTo(o1) < 0);
/*     */     }
/* 111 */     throw new ELException(LocalMessages.get("error.compare.types", new Object[] { o1.getClass(), o2.getClass() }));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean lt(TypeConverter converter, Object o1, Object o2) {
/* 116 */     if (o1 == o2)
/*     */     {
/* 118 */       return false;
/*     */     }
/* 120 */     if (o1 == null || o2 == null)
/*     */     {
/* 122 */       return false;
/*     */     }
/* 124 */     return lt0(converter, o1, o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean gt(TypeConverter converter, Object o1, Object o2) {
/* 129 */     if (o1 == o2)
/*     */     {
/* 131 */       return false;
/*     */     }
/* 133 */     if (o1 == null || o2 == null)
/*     */     {
/* 135 */       return false;
/*     */     }
/* 137 */     return gt0(converter, o1, o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean ge(TypeConverter converter, Object o1, Object o2) {
/* 142 */     if (o1 == o2)
/*     */     {
/* 144 */       return true;
/*     */     }
/* 146 */     if (o1 == null || o2 == null)
/*     */     {
/* 148 */       return false;
/*     */     }
/* 150 */     return !lt0(converter, o1, o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean le(TypeConverter converter, Object o1, Object o2) {
/* 155 */     if (o1 == o2)
/*     */     {
/* 157 */       return true;
/*     */     }
/* 159 */     if (o1 == null || o2 == null)
/*     */     {
/* 161 */       return false;
/*     */     }
/* 163 */     return !gt0(converter, o1, o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean eq(TypeConverter converter, Object o1, Object o2) {
/* 168 */     if (o1 == o2)
/*     */     {
/* 170 */       return true;
/*     */     }
/* 172 */     if (o1 == null || o2 == null)
/*     */     {
/* 174 */       return false;
/*     */     }
/* 176 */     Class<?> t1 = o1.getClass();
/* 177 */     Class<?> t2 = o2.getClass();
/* 178 */     if (BigDecimal.class.isAssignableFrom(t1) || BigDecimal.class.isAssignableFrom(t2))
/*     */     {
/* 180 */       return ((BigDecimal)converter.<BigDecimal>convert(o1, BigDecimal.class)).equals(converter.convert(o2, BigDecimal.class));
/*     */     }
/* 182 */     if (SIMPLE_FLOAT_TYPES.contains(t1) || SIMPLE_FLOAT_TYPES.contains(t2))
/*     */     {
/* 184 */       return ((Double)converter.<Double>convert(o1, Double.class)).equals(converter.convert(o2, Double.class));
/*     */     }
/* 186 */     if (BigInteger.class.isAssignableFrom(t1) || BigInteger.class.isAssignableFrom(t2))
/*     */     {
/* 188 */       return ((BigInteger)converter.<BigInteger>convert(o1, BigInteger.class)).equals(converter.convert(o2, BigInteger.class));
/*     */     }
/* 190 */     if (SIMPLE_INTEGER_TYPES.contains(t1) || SIMPLE_INTEGER_TYPES.contains(t2))
/*     */     {
/* 192 */       return ((Long)converter.<Long>convert(o1, Long.class)).equals(converter.convert(o2, Long.class));
/*     */     }
/* 194 */     if (t1 == Boolean.class || t2 == Boolean.class)
/*     */     {
/* 196 */       return ((Boolean)converter.<Boolean>convert(o1, Boolean.class)).equals(converter.convert(o2, Boolean.class));
/*     */     }
/* 198 */     if (o1 instanceof Enum)
/*     */     {
/* 200 */       return (o1 == converter.convert(o2, o1.getClass()));
/*     */     }
/* 202 */     if (o2 instanceof Enum)
/*     */     {
/* 204 */       return (converter.convert(o1, o2.getClass()) == o2);
/*     */     }
/* 206 */     if (t1 == String.class || t2 == String.class)
/*     */     {
/* 208 */       return ((String)converter.<String>convert(o1, String.class)).equals(converter.convert(o2, String.class));
/*     */     }
/* 210 */     return o1.equals(o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean ne(TypeConverter converter, Object o1, Object o2) {
/* 215 */     return !eq(converter, o1, o2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean empty(TypeConverter converter, Object o) {
/* 220 */     if (o == null || "".equals(o))
/*     */     {
/* 222 */       return true;
/*     */     }
/* 224 */     if (o instanceof Object[])
/*     */     {
/* 226 */       return (((Object[])o).length == 0);
/*     */     }
/* 228 */     if (o instanceof Map)
/*     */     {
/* 230 */       return ((Map)o).isEmpty();
/*     */     }
/* 232 */     if (o instanceof Collection)
/*     */     {
/* 234 */       return ((Collection)o).isEmpty();
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\misc\BooleanOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */