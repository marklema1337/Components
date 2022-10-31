/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class ExternalizationUtil
/*     */ {
/*     */   public static void writeExternal(HashMap orgHash, ObjectOutput out) throws IOException {
/*  30 */     if (orgHash != null) {
/*  31 */       writeExternalMap(orgHash, out);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void writeExternal(Hashtable orgHash, ObjectOutput out) throws IOException {
/*  36 */     if (orgHash != null) {
/*  37 */       writeExternalMap(orgHash, out);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void writeExternalMap(Map hash, ObjectOutput out) throws IOException {
/*  42 */     synchronized (hash) {
/*     */       
/*  44 */       out.writeInt(hash.size());
/*  45 */       for (Iterator e = hash.entrySet().iterator(); e.hasNext(); ) {
/*     */         
/*  47 */         Object key = e.next();
/*  48 */         out.writeObject(((Map.Entry)key).getKey());
/*  49 */         out.writeObject(((Map.Entry)key).getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void readExternal(Map<Object, Object> hash, ObjectInput in) throws IOException, ClassNotFoundException {
/*  56 */     int size = in.readInt();
/*  57 */     for (; size > 0; size--)
/*     */     {
/*  59 */       hash.put(in.readObject(), in.readObject());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeExternal(Vector orgHash, ObjectOutput out) throws IOException {
/*  65 */     writeExternalList(orgHash, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeExternal(ArrayList orgHash, ObjectOutput out) throws IOException {
/*  70 */     writeExternalList(orgHash, out);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeExternalList(List list, ObjectOutput out) throws IOException {
/*  75 */     if (list == null) {
/*     */       
/*  77 */       out.writeInt(-1);
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     out.writeInt(list.size());
/*  82 */     for (Iterator i = list.iterator(); i.hasNext(); ) {
/*     */       
/*  84 */       Object item = i.next();
/*  85 */       out.writeObject(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void readExternal(List<Object> list, ObjectInput in) throws IOException, ClassNotFoundException {
/*  91 */     int size = in.readInt();
/*  92 */     for (; size > 0; size--)
/*     */     {
/*  94 */       list.add(in.readObject());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static List readExternal(List<Object> list, ObjectInput in, Class<List> listClass) throws IOException, ClassNotFoundException {
/* 100 */     int size = in.readInt();
/* 101 */     if (size == -1) {
/* 102 */       return null;
/*     */     }
/* 104 */     if (list == null) {
/*     */       
/*     */       try {
/*     */         
/* 108 */         list = listClass.newInstance();
/*     */       }
/* 110 */       catch (InstantiationException e) {
/*     */         
/* 112 */         e.printStackTrace();
/*     */       }
/* 114 */       catch (IllegalAccessException e) {
/*     */         
/* 116 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 120 */     for (; size > 0; size--)
/*     */     {
/* 122 */       list.add(in.readObject());
/*     */     }
/*     */     
/* 125 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeExternal(Object[] array, ObjectOutput out) throws IOException {
/* 130 */     if (array == null) {
/*     */       
/* 132 */       out.writeInt(-1);
/*     */       return;
/*     */     } 
/* 135 */     out.writeInt(array.length);
/* 136 */     for (int i = 0; i < array.length; i++)
/*     */     {
/* 138 */       out.writeObject(array[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] readArray(ObjectInput in) throws IOException, ClassNotFoundException {
/* 144 */     int size = in.readInt();
/* 145 */     if (size == -1) {
/* 146 */       return null;
/*     */     }
/* 148 */     Object[] array = new Object[size];
/* 149 */     for (int i = 0; i < size; i++)
/*     */     {
/* 151 */       array[i] = in.readObject();
/*     */     }
/*     */     
/* 154 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] readArray(ObjectInput in, Object[] arr) throws IOException, ClassNotFoundException {
/* 159 */     int size = in.readInt();
/* 160 */     if (size == -1 && arr == null) {
/* 161 */       return arr;
/*     */     }
/* 163 */     if (arr.length < size)
/*     */     {
/* 165 */       arr = Arrays.copyOf(arr, size);
/*     */     }
/* 167 */     for (int i = 0; i < size; i++)
/*     */     {
/* 169 */       arr[i] = in.readObject();
/*     */     }
/*     */     
/* 172 */     return arr;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeStringArray(String[] array, ObjectOutput out) throws IOException {
/* 177 */     if (array == null) {
/*     */       
/* 179 */       out.writeInt(-1);
/*     */       
/*     */       return;
/*     */     } 
/* 183 */     out.writeInt(array.length);
/* 184 */     for (int i = 0; i < array.length; i++)
/*     */     {
/* 186 */       out.writeObject(array[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] readStringArray(ObjectInput in) throws IOException, ClassNotFoundException {
/* 192 */     int size = in.readInt();
/* 193 */     if (size == -1) {
/* 194 */       return null;
/*     */     }
/* 196 */     String[] array = new String[size];
/* 197 */     for (int i = 0; i < size; i++)
/*     */     {
/* 199 */       array[i] = (String)in.readObject();
/*     */     }
/*     */     
/* 202 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeBooleanArray(boolean[] array, ObjectOutput out) throws IOException {
/* 207 */     if (array == null) {
/*     */       
/* 209 */       out.writeInt(-1);
/*     */       
/*     */       return;
/*     */     } 
/* 213 */     out.writeInt(array.length);
/* 214 */     for (int i = 0; i < array.length; i++)
/*     */     {
/* 216 */       out.writeBoolean(array[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean[] readBooleanArray(ObjectInput in) throws IOException, ClassNotFoundException {
/* 222 */     int size = in.readInt();
/* 223 */     if (size == -1) {
/* 224 */       return null;
/*     */     }
/* 226 */     boolean[] array = new boolean[size];
/* 227 */     for (int i = 0; i < size; i++)
/*     */     {
/* 229 */       array[i] = in.readBoolean();
/*     */     }
/*     */     
/* 232 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeHashtable(Hashtable hash, ObjectOutput out) throws IOException {
/* 237 */     if (hash == null) {
/*     */       
/* 239 */       out.writeInt(-1);
/*     */       
/*     */       return;
/*     */     } 
/* 243 */     out.writeInt(hash.size());
/* 244 */     for (Enumeration e = hash.keys(); e.hasMoreElements(); ) {
/*     */       
/* 246 */       Object key = e.nextElement();
/* 247 */       out.writeObject(key);
/* 248 */       out.writeObject(hash.get(key));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable readHashtable(Hashtable<Object, Object> hash, ObjectInput in) throws IOException, ClassNotFoundException {
/* 254 */     int size = in.readInt();
/* 255 */     if (size == -1) {
/* 256 */       return null;
/*     */     }
/* 258 */     if (hash == null) {
/* 259 */       hash = new Hashtable<>();
/*     */     }
/* 261 */     for (; size > 0; size--)
/*     */     {
/* 263 */       hash.put(in.readObject(), in.readObject());
/*     */     }
/*     */     
/* 266 */     return hash;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ExternalizationUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */