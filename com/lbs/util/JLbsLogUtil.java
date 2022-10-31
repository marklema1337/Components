/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
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
/*     */ public class JLbsLogUtil
/*     */ {
/*  30 */   private static Vector ms_Processed = new Vector();
/*  31 */   private static Vector ms_IgnoredClasses = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void printObject(Object o) {
/*  39 */     printObject(o, System.out, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void printObject(Object o, File outputFile) throws Exception {
/*  49 */     if (!outputFile.exists())
/*  50 */       outputFile.createNewFile(); 
/*  51 */     PrintStream out = new PrintStream(new FileOutputStream(outputFile));
/*  52 */     printObject(o, out, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void printObject(Object o, PrintStream out, boolean closeStreamWhenDone) {
/*  61 */     ms_Processed.clear();
/*     */     
/*     */     try {
/*  64 */       printObject(o, out, "");
/*     */     }
/*     */     finally {
/*     */       
/*  68 */       if (out != null) {
/*     */         
/*  70 */         out.flush();
/*  71 */         if (closeStreamWhenDone) {
/*  72 */           out.close();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void printObject(Object o, PrintStream out, String prefix) {
/*  79 */     if (o == null) {
/*     */       
/*  81 */       out.println(String.valueOf(prefix) + "null");
/*     */       return;
/*     */     } 
/*  84 */     if (ms_IgnoredClasses.contains(o.getClass()))
/*     */       return; 
/*  86 */     if (ms_Processed.contains(o))
/*     */       return; 
/*  88 */     ms_Processed.add(o);
/*  89 */     Class<?> clazz = o.getClass();
/*  90 */     if (RttiUtil.isSimpleClassValueOrWrapper(clazz)) {
/*     */       
/*  92 */       out.println(String.valueOf(prefix) + o.getClass().getName() + " = " + o);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  97 */     out.println(String.valueOf(prefix) + o.getClass().getName());
/*  98 */     Field[] fields = clazz.getDeclaredFields();
/*  99 */     out.println(String.valueOf(prefix) + "Fields:");
/* 100 */     for (int i = 0; i < fields.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 104 */         if (!Modifier.isFinal(fields[i].getModifiers())) {
/*     */           
/* 106 */           out.println(String.valueOf(prefix) + " " + fields[i].getName() + " = ");
/* 107 */           boolean val = fields[i].isAccessible();
/* 108 */           fields[i].setAccessible(true);
/*     */           
/*     */           try {
/* 111 */             printObject(fields[i].get(o), out, String.valueOf(prefix) + "   ");
/*     */           }
/*     */           finally {
/*     */             
/* 115 */             fields[i].setAccessible(val);
/*     */           } 
/*     */         } 
/* 118 */       } catch (IllegalArgumentException e) {
/*     */         
/* 120 */         e.printStackTrace();
/*     */       }
/* 122 */       catch (IllegalAccessException e) {
/*     */         
/* 124 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 127 */     if (clazz.isArray()) {
/*     */       
/* 129 */       if (o instanceof byte[])
/*     */         
/*     */         try {
/*     */           
/* 133 */           Object val = deserializeObjectPlain((byte[])o);
/* 134 */           printObject(val, out, prefix);
/*     */           
/*     */           return;
/* 137 */         } catch (Exception exception) {
/*     */ 
/*     */           
/* 140 */           out.println(String.valueOf(prefix) + " Array Content: " + o);
/*     */           return;
/*     */         }  
/* 143 */       out.println(String.valueOf(prefix) + " Array Content:");
/* 144 */       int size = Array.getLength(o);
/* 145 */       for (int j = 0; j < size; j++)
/*     */       {
/* 147 */         printObject(Array.get(o, j), out, String.valueOf(prefix) + "  ");
/*     */       }
/*     */     } 
/* 150 */     if (o instanceof Collection) {
/*     */       
/* 152 */       Collection coll = (Collection)o;
/* 153 */       out.println(String.valueOf(prefix) + " Collection Content:");
/* 154 */       for (Iterator iterator = coll.iterator(); iterator.hasNext();)
/*     */       {
/* 156 */         printObject(iterator.next(), out, String.valueOf(prefix) + "  ");
/*     */       }
/*     */     } 
/* 159 */     if (o instanceof Map) {
/*     */       
/* 161 */       Map map = (Map)o;
/* 162 */       Set keys = map.keySet();
/* 163 */       out.println(String.valueOf(prefix) + " Map Content:");
/* 164 */       for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
/*     */         
/* 166 */         Object key = iterator.next();
/* 167 */         out.println(String.valueOf(prefix) + " key = ");
/* 168 */         printObject(key, out, String.valueOf(prefix) + "  ");
/* 169 */         out.println(String.valueOf(prefix) + " value = ");
/* 170 */         printObject(map.get(key), out, String.valueOf(prefix) + "  ");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object deserializeObjectPlain(byte[] o) {
/* 178 */     ObjectInputStream stream = null;
/*     */     
/*     */     try {
/* 181 */       stream = new ObjectInputStream(new ByteArrayInputStream(o));
/* 182 */       return stream.readObject();
/*     */     }
/* 184 */     catch (Exception exception) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/* 189 */       if (stream != null) {
/*     */         
/*     */         try {
/* 192 */           stream.close();
/*     */         }
/* 194 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */     
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addIgnoredClass(Class<?> clazz) {
/* 203 */     if (!ms_IgnoredClasses.contains(clazz))
/* 204 */       ms_IgnoredClasses.add(clazz); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsLogUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */