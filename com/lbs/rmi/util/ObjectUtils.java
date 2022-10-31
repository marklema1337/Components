/*     */ package com.lbs.rmi.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
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
/*     */ public abstract class ObjectUtils
/*     */ {
/*     */   private static final int INITIAL_HASH = 7;
/*     */   private static final int MULTIPLIER = 31;
/*     */   private static final String EMPTY_STRING = "";
/*     */   private static final String NULL_STRING = "null";
/*     */   private static final String ARRAY_START = "{";
/*     */   private static final String ARRAY_END = "}";
/*     */   private static final String EMPTY_ARRAY = "{}";
/*     */   private static final String ARRAY_ELEMENT_SEPARATOR = ", ";
/*     */   
/*     */   public static boolean isCheckedException(Throwable ex) {
/*  58 */     return !(ex instanceof RuntimeException || ex instanceof Error);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCompatibleWithThrowsClause(Throwable ex, Class[] declaredExceptions) {
/*  69 */     if (!isCheckedException(ex)) {
/*  70 */       return true;
/*     */     }
/*  72 */     if (declaredExceptions != null) {
/*  73 */       for (int i = 0; i < declaredExceptions.length; i++) {
/*  74 */         if (declaredExceptions[i].isAssignableFrom(ex.getClass())) {
/*  75 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Object[] array) {
/*  89 */     return !(array != null && array.length != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsElement(Object[] array, Object element) {
/* 100 */     if (array == null) {
/* 101 */       return false;
/*     */     }
/* 103 */     for (int i = 0; i < array.length; i++) {
/* 104 */       if (nullSafeEquals(array[i], element)) {
/* 105 */         return true;
/*     */       }
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object[] addObjectToArray(Object[] array, Object obj) {
/* 119 */     Class<Object> compType = Object.class;
/* 120 */     if (array != null) {
/* 121 */       compType = (Class)array.getClass().getComponentType();
/*     */     }
/* 123 */     else if (obj != null) {
/* 124 */       compType = (Class)obj.getClass();
/*     */     } 
/* 126 */     int newArrLength = (array != null) ? (array.length + 1) : 1;
/* 127 */     Object[] newArr = (Object[])Array.newInstance(compType, newArrLength);
/* 128 */     if (array != null) {
/* 129 */       System.arraycopy(array, 0, newArr, 0, array.length);
/*     */     }
/* 131 */     newArr[newArr.length - 1] = obj;
/* 132 */     return newArr;
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
/*     */   public static Object[] toObjectArray(Object source) {
/* 145 */     if (source instanceof Object[]) {
/* 146 */       return (Object[])source;
/*     */     }
/* 148 */     if (source == null) {
/* 149 */       return new Object[0];
/*     */     }
/* 151 */     if (!source.getClass().isArray()) {
/* 152 */       throw new IllegalArgumentException("Source is not an array: " + source);
/*     */     }
/* 154 */     int length = Array.getLength(source);
/* 155 */     if (length == 0) {
/* 156 */       return new Object[0];
/*     */     }
/* 158 */     Class<?> wrapperType = Array.get(source, 0).getClass();
/* 159 */     Object[] newArray = (Object[])Array.newInstance(wrapperType, length);
/* 160 */     for (int i = 0; i < length; i++) {
/* 161 */       newArray[i] = Array.get(source, i);
/*     */     }
/* 163 */     return newArray;
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
/*     */   public static boolean nullSafeEquals(Object o1, Object o2) {
/* 183 */     if (o1 == o2) {
/* 184 */       return true;
/*     */     }
/* 186 */     if (o1 == null || o2 == null) {
/* 187 */       return false;
/*     */     }
/* 189 */     if (o1.equals(o2)) {
/* 190 */       return true;
/*     */     }
/* 192 */     if (o1.getClass().isArray() && o2.getClass().isArray()) {
/* 193 */       if (o1 instanceof Object[] && o2 instanceof Object[]) {
/* 194 */         return Arrays.equals((Object[])o1, (Object[])o2);
/*     */       }
/* 196 */       if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
/* 197 */         return Arrays.equals((boolean[])o1, (boolean[])o2);
/*     */       }
/* 199 */       if (o1 instanceof byte[] && o2 instanceof byte[]) {
/* 200 */         return Arrays.equals((byte[])o1, (byte[])o2);
/*     */       }
/* 202 */       if (o1 instanceof char[] && o2 instanceof char[]) {
/* 203 */         return Arrays.equals((char[])o1, (char[])o2);
/*     */       }
/* 205 */       if (o1 instanceof double[] && o2 instanceof double[]) {
/* 206 */         return Arrays.equals((double[])o1, (double[])o2);
/*     */       }
/* 208 */       if (o1 instanceof float[] && o2 instanceof float[]) {
/* 209 */         return Arrays.equals((float[])o1, (float[])o2);
/*     */       }
/* 211 */       if (o1 instanceof int[] && o2 instanceof int[]) {
/* 212 */         return Arrays.equals((int[])o1, (int[])o2);
/*     */       }
/* 214 */       if (o1 instanceof long[] && o2 instanceof long[]) {
/* 215 */         return Arrays.equals((long[])o1, (long[])o2);
/*     */       }
/* 217 */       if (o1 instanceof short[] && o2 instanceof short[]) {
/* 218 */         return Arrays.equals((short[])o1, (short[])o2);
/*     */       }
/*     */     } 
/* 221 */     return false;
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
/*     */   public static int nullSafeHashCode(Object obj) {
/* 241 */     if (obj == null) {
/* 242 */       return 0;
/*     */     }
/* 244 */     if (obj.getClass().isArray()) {
/* 245 */       if (obj instanceof Object[]) {
/* 246 */         return nullSafeHashCode((Object[])obj);
/*     */       }
/* 248 */       if (obj instanceof boolean[]) {
/* 249 */         return nullSafeHashCode((boolean[])obj);
/*     */       }
/* 251 */       if (obj instanceof byte[]) {
/* 252 */         return nullSafeHashCode((byte[])obj);
/*     */       }
/* 254 */       if (obj instanceof char[]) {
/* 255 */         return nullSafeHashCode((char[])obj);
/*     */       }
/* 257 */       if (obj instanceof double[]) {
/* 258 */         return nullSafeHashCode((double[])obj);
/*     */       }
/* 260 */       if (obj instanceof float[]) {
/* 261 */         return nullSafeHashCode((float[])obj);
/*     */       }
/* 263 */       if (obj instanceof int[]) {
/* 264 */         return nullSafeHashCode((int[])obj);
/*     */       }
/* 266 */       if (obj instanceof long[]) {
/* 267 */         return nullSafeHashCode((long[])obj);
/*     */       }
/* 269 */       if (obj instanceof short[]) {
/* 270 */         return nullSafeHashCode((short[])obj);
/*     */       }
/*     */     } 
/* 273 */     return obj.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(Object[] array) {
/* 281 */     if (array == null) {
/* 282 */       return 0;
/*     */     }
/* 284 */     int hash = 7;
/* 285 */     int arraySize = array.length;
/* 286 */     for (int i = 0; i < arraySize; i++) {
/* 287 */       hash = 31 * hash + nullSafeHashCode(array[i]);
/*     */     }
/* 289 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(boolean[] array) {
/* 297 */     if (array == null) {
/* 298 */       return 0;
/*     */     }
/* 300 */     int hash = 7;
/* 301 */     int arraySize = array.length;
/* 302 */     for (int i = 0; i < arraySize; i++) {
/* 303 */       hash = 31 * hash + hashCode(array[i]);
/*     */     }
/* 305 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(byte[] array) {
/* 313 */     if (array == null) {
/* 314 */       return 0;
/*     */     }
/* 316 */     int hash = 7;
/* 317 */     int arraySize = array.length;
/* 318 */     for (int i = 0; i < arraySize; i++) {
/* 319 */       hash = 31 * hash + array[i];
/*     */     }
/* 321 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(char[] array) {
/* 329 */     if (array == null) {
/* 330 */       return 0;
/*     */     }
/* 332 */     int hash = 7;
/* 333 */     int arraySize = array.length;
/* 334 */     for (int i = 0; i < arraySize; i++) {
/* 335 */       hash = 31 * hash + array[i];
/*     */     }
/* 337 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(double[] array) {
/* 345 */     if (array == null) {
/* 346 */       return 0;
/*     */     }
/* 348 */     int hash = 7;
/* 349 */     int arraySize = array.length;
/* 350 */     for (int i = 0; i < arraySize; i++) {
/* 351 */       hash = 31 * hash + hashCode(array[i]);
/*     */     }
/* 353 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(float[] array) {
/* 361 */     if (array == null) {
/* 362 */       return 0;
/*     */     }
/* 364 */     int hash = 7;
/* 365 */     int arraySize = array.length;
/* 366 */     for (int i = 0; i < arraySize; i++) {
/* 367 */       hash = 31 * hash + hashCode(array[i]);
/*     */     }
/* 369 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(int[] array) {
/* 377 */     if (array == null) {
/* 378 */       return 0;
/*     */     }
/* 380 */     int hash = 7;
/* 381 */     int arraySize = array.length;
/* 382 */     for (int i = 0; i < arraySize; i++) {
/* 383 */       hash = 31 * hash + array[i];
/*     */     }
/* 385 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(long[] array) {
/* 393 */     if (array == null) {
/* 394 */       return 0;
/*     */     }
/* 396 */     int hash = 7;
/* 397 */     int arraySize = array.length;
/* 398 */     for (int i = 0; i < arraySize; i++) {
/* 399 */       hash = 31 * hash + hashCode(array[i]);
/*     */     }
/* 401 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(short[] array) {
/* 409 */     if (array == null) {
/* 410 */       return 0;
/*     */     }
/* 412 */     int hash = 7;
/* 413 */     int arraySize = array.length;
/* 414 */     for (int i = 0; i < arraySize; i++) {
/* 415 */       hash = 31 * hash + array[i];
/*     */     }
/* 417 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(boolean bool) {
/* 425 */     return bool ? 1231 : 1237;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(double dbl) {
/* 433 */     long bits = Double.doubleToLongBits(dbl);
/* 434 */     return hashCode(bits);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(float flt) {
/* 442 */     return Float.floatToIntBits(flt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(long lng) {
/* 450 */     return (int)(lng ^ lng >>> 32L);
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
/*     */   public static String identityToString(Object obj) {
/* 465 */     if (obj == null) {
/* 466 */       return "";
/*     */     }
/* 468 */     return String.valueOf(obj.getClass().getName()) + "@" + getIdentityHexString(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIdentityHexString(Object obj) {
/* 477 */     return Integer.toHexString(System.identityHashCode(obj));
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
/*     */   public static String getDisplayString(Object obj) {
/* 490 */     if (obj == null) {
/* 491 */       return "";
/*     */     }
/* 493 */     return nullSafeToString(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String nullSafeClassName(Object obj) {
/* 503 */     return (obj != null) ? obj.getClass().getName() : "null";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String nullSafeToString(Object obj) {
/* 514 */     if (obj == null) {
/* 515 */       return "null";
/*     */     }
/* 517 */     if (obj instanceof String) {
/* 518 */       return (String)obj;
/*     */     }
/* 520 */     if (obj instanceof Object[]) {
/* 521 */       return nullSafeToString((Object[])obj);
/*     */     }
/* 523 */     if (obj instanceof boolean[]) {
/* 524 */       return nullSafeToString((boolean[])obj);
/*     */     }
/* 526 */     if (obj instanceof byte[]) {
/* 527 */       return nullSafeToString((byte[])obj);
/*     */     }
/* 529 */     if (obj instanceof char[]) {
/* 530 */       return nullSafeToString((char[])obj);
/*     */     }
/* 532 */     if (obj instanceof double[]) {
/* 533 */       return nullSafeToString((double[])obj);
/*     */     }
/* 535 */     if (obj instanceof float[]) {
/* 536 */       return nullSafeToString((float[])obj);
/*     */     }
/* 538 */     if (obj instanceof int[]) {
/* 539 */       return nullSafeToString((int[])obj);
/*     */     }
/* 541 */     if (obj instanceof long[]) {
/* 542 */       return nullSafeToString((long[])obj);
/*     */     }
/* 544 */     if (obj instanceof short[]) {
/* 545 */       return nullSafeToString((short[])obj);
/*     */     }
/* 547 */     String str = obj.toString();
/*     */ 
/*     */     
/* 550 */     return str;
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
/*     */   public static String nullSafeToString(Object[] array) {
/* 563 */     if (array == null) {
/* 564 */       return "null";
/*     */     }
/* 566 */     int length = array.length;
/* 567 */     if (length == 0) {
/* 568 */       return "{}";
/*     */     }
/* 570 */     StringBuilder buffer = new StringBuilder();
/* 571 */     for (int i = 0; i < length; i++) {
/* 572 */       if (i == 0) {
/* 573 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 576 */         buffer.append(", ");
/*     */       } 
/* 578 */       buffer.append(String.valueOf(array[i]));
/*     */     } 
/* 580 */     buffer.append("}");
/* 581 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(boolean[] array) {
/* 594 */     if (array == null) {
/* 595 */       return "null";
/*     */     }
/* 597 */     int length = array.length;
/* 598 */     if (length == 0) {
/* 599 */       return "{}";
/*     */     }
/* 601 */     StringBuilder buffer = new StringBuilder();
/* 602 */     for (int i = 0; i < length; i++) {
/* 603 */       if (i == 0) {
/* 604 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 607 */         buffer.append(", ");
/*     */       } 
/*     */       
/* 610 */       buffer.append(array[i]);
/*     */     } 
/* 612 */     buffer.append("}");
/* 613 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(byte[] array) {
/* 626 */     if (array == null) {
/* 627 */       return "null";
/*     */     }
/* 629 */     int length = array.length;
/* 630 */     if (length == 0) {
/* 631 */       return "{}";
/*     */     }
/* 633 */     StringBuilder buffer = new StringBuilder();
/* 634 */     for (int i = 0; i < length; i++) {
/* 635 */       if (i == 0) {
/* 636 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 639 */         buffer.append(", ");
/*     */       } 
/* 641 */       buffer.append(array[i]);
/*     */     } 
/* 643 */     buffer.append("}");
/* 644 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(char[] array) {
/* 657 */     if (array == null) {
/* 658 */       return "null";
/*     */     }
/* 660 */     int length = array.length;
/* 661 */     if (length == 0) {
/* 662 */       return "{}";
/*     */     }
/* 664 */     StringBuilder buffer = new StringBuilder();
/* 665 */     for (int i = 0; i < length; i++) {
/* 666 */       if (i == 0) {
/* 667 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 670 */         buffer.append(", ");
/*     */       } 
/* 672 */       buffer.append("'").append(array[i]).append("'");
/*     */     } 
/* 674 */     buffer.append("}");
/* 675 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(double[] array) {
/* 688 */     if (array == null) {
/* 689 */       return "null";
/*     */     }
/* 691 */     int length = array.length;
/* 692 */     if (length == 0) {
/* 693 */       return "{}";
/*     */     }
/* 695 */     StringBuilder buffer = new StringBuilder();
/* 696 */     for (int i = 0; i < length; i++) {
/* 697 */       if (i == 0) {
/* 698 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 701 */         buffer.append(", ");
/*     */       } 
/*     */       
/* 704 */       buffer.append(array[i]);
/*     */     } 
/* 706 */     buffer.append("}");
/* 707 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(float[] array) {
/* 720 */     if (array == null) {
/* 721 */       return "null";
/*     */     }
/* 723 */     int length = array.length;
/* 724 */     if (length == 0) {
/* 725 */       return "{}";
/*     */     }
/* 727 */     StringBuilder buffer = new StringBuilder();
/* 728 */     for (int i = 0; i < length; i++) {
/* 729 */       if (i == 0) {
/* 730 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 733 */         buffer.append(", ");
/*     */       } 
/*     */       
/* 736 */       buffer.append(array[i]);
/*     */     } 
/* 738 */     buffer.append("}");
/* 739 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(int[] array) {
/* 752 */     if (array == null) {
/* 753 */       return "null";
/*     */     }
/* 755 */     int length = array.length;
/* 756 */     if (length == 0) {
/* 757 */       return "{}";
/*     */     }
/* 759 */     StringBuilder buffer = new StringBuilder();
/* 760 */     for (int i = 0; i < length; i++) {
/* 761 */       if (i == 0) {
/* 762 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 765 */         buffer.append(", ");
/*     */       } 
/* 767 */       buffer.append(array[i]);
/*     */     } 
/* 769 */     buffer.append("}");
/* 770 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(long[] array) {
/* 783 */     if (array == null) {
/* 784 */       return "null";
/*     */     }
/* 786 */     int length = array.length;
/* 787 */     if (length == 0) {
/* 788 */       return "{}";
/*     */     }
/* 790 */     StringBuilder buffer = new StringBuilder();
/* 791 */     for (int i = 0; i < length; i++) {
/* 792 */       if (i == 0) {
/* 793 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 796 */         buffer.append(", ");
/*     */       } 
/* 798 */       buffer.append(array[i]);
/*     */     } 
/* 800 */     buffer.append("}");
/* 801 */     return buffer.toString();
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
/*     */   public static String nullSafeToString(short[] array) {
/* 814 */     if (array == null) {
/* 815 */       return "null";
/*     */     }
/* 817 */     int length = array.length;
/* 818 */     if (length == 0) {
/* 819 */       return "{}";
/*     */     }
/* 821 */     StringBuilder buffer = new StringBuilder();
/* 822 */     for (int i = 0; i < length; i++) {
/* 823 */       if (i == 0) {
/* 824 */         buffer.append("{");
/*     */       } else {
/*     */         
/* 827 */         buffer.append(", ");
/*     */       } 
/* 829 */       buffer.append(array[i]);
/*     */     } 
/* 831 */     buffer.append("}");
/* 832 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rm\\util\ObjectUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */