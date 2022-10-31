/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsIniProperties
/*     */   extends Properties
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   private static final Pattern SECTION_PATTERN = Pattern.compile("^\\s*\\[([^\\]]*)\\].*$");
/*  30 */   private static final Pattern ENTRY_PATTERN = Pattern.compile("^([^=]*)=(.*)$");
/*     */ 
/*     */   
/*     */   public void load(InputStream stream) throws IOException {
/*  34 */     String sectionName = null;
/*  35 */     BufferedReader in = new BufferedReader(new InputStreamReader(stream));
/*     */     
/*     */     while (true) {
/*  38 */       String line = in.readLine();
/*  39 */       if (line == null)
/*     */         break; 
/*  41 */       int iCommentIndex = line.indexOf(';');
/*  42 */       if (iCommentIndex >= 0)
/*  43 */         line = line.substring(0, iCommentIndex); 
/*  44 */       Matcher matcher = SECTION_PATTERN.matcher(line);
/*  45 */       if (matcher.matches()) {
/*  46 */         sectionName = matcher.group(1).trim();
/*     */         continue;
/*     */       } 
/*  49 */       matcher = ENTRY_PATTERN.matcher(line);
/*  50 */       if (matcher.matches()) {
/*     */         
/*  52 */         String name = matcher.group(1).trim();
/*  53 */         String value = matcher.group(2).trim();
/*  54 */         if (!JLbsStringUtil.isEmpty(sectionName)) {
/*  55 */           put(String.valueOf(sectionName) + "." + name, value); continue;
/*     */         } 
/*  57 */         put(name, value);
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     in.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(InputStream stream, String charSet) throws IOException {
/*  66 */     String sectionName = null;
/*  67 */     BufferedReader in = new BufferedReader(new InputStreamReader(stream, charSet));
/*     */     
/*     */     while (true) {
/*  70 */       String line = in.readLine();
/*  71 */       if (line == null)
/*     */         break; 
/*  73 */       int iCommentIndex = line.indexOf(';');
/*  74 */       if (iCommentIndex >= 0)
/*  75 */         line = line.substring(0, iCommentIndex); 
/*  76 */       Matcher matcher = SECTION_PATTERN.matcher(line);
/*  77 */       if (matcher.matches()) {
/*  78 */         sectionName = matcher.group(1).trim();
/*     */         continue;
/*     */       } 
/*  81 */       matcher = ENTRY_PATTERN.matcher(line);
/*  82 */       if (matcher.matches()) {
/*     */         
/*  84 */         String name = matcher.group(1).trim();
/*  85 */         String value = matcher.group(2).trim();
/*  86 */         if (!JLbsStringUtil.isEmpty(sectionName)) {
/*  87 */           put(String.valueOf(sectionName) + "." + name, value); continue;
/*     */         } 
/*  89 */         put(name, value);
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     in.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void store(OutputStream out, String header) throws IOException {
/*  98 */     store(out, header, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void store(OutputStream out, String header, String encoding) throws IOException {
/* 103 */     Hashtable grouped = groupProperties();
/*     */     
/* 105 */     PrintWriter writer = null;
/* 106 */     if (encoding == null) {
/* 107 */       writer = new PrintWriter(out);
/*     */     } else {
/* 109 */       writer = new PrintWriter(new OutputStreamWriter(out, encoding));
/* 110 */     }  if (grouped != null) {
/*     */       
/* 112 */       Enumeration enm = grouped.keys();
/* 113 */       while (enm.hasMoreElements()) {
/*     */         
/* 115 */         Object keyObj = enm.nextElement();
/* 116 */         if (keyObj instanceof String) {
/*     */           
/* 118 */           String key = (String)keyObj;
/* 119 */           ArrayList<IniPropertyTuple> list = (ArrayList)grouped.get(keyObj);
/* 120 */           if (list != null && list.size() > 0) {
/*     */             
/* 122 */             writer.println();
/* 123 */             writer.println("[" + key + "]");
/* 124 */             for (int i = 0; i < list.size(); i++) {
/*     */               
/* 126 */               IniPropertyTuple tuple = list.get(i);
/* 127 */               if (tuple != null && tuple.Name != null)
/* 128 */                 writer.println(String.valueOf(tuple.Name) + "=" + tuple.Value); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 134 */     writer.close();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Hashtable groupProperties() {
/* 139 */     Hashtable<Object, Object> result = new Hashtable<>();
/* 140 */     Enumeration<Object> keylist = keys();
/* 141 */     while (keylist.hasMoreElements()) {
/*     */       
/* 143 */       Object obj = keylist.nextElement();
/* 144 */       if (obj != null && obj instanceof String) {
/*     */         String groupName, entryName; ArrayList<IniPropertyTuple> list;
/* 146 */         String key = (String)obj;
/* 147 */         int iDotIndex = key.indexOf('.');
/*     */ 
/*     */         
/* 150 */         if (iDotIndex > 0) {
/*     */           
/* 152 */           groupName = key.substring(0, iDotIndex);
/* 153 */           entryName = (iDotIndex < key.length()) ? 
/* 154 */             key.substring(iDotIndex + 1) : 
/* 155 */             "";
/*     */         }
/*     */         else {
/*     */           
/* 159 */           groupName = "";
/* 160 */           entryName = key;
/*     */         } 
/* 162 */         Object groupObj = result.get(groupName);
/*     */         
/* 164 */         if (groupObj instanceof ArrayList) {
/* 165 */           list = (ArrayList)groupObj;
/*     */         } else {
/*     */           
/* 168 */           list = new ArrayList();
/* 169 */           result.put(groupName, list);
/*     */         } 
/* 171 */         list.add(new IniPropertyTuple(entryName, getProperty(key)));
/*     */       } 
/*     */     } 
/* 174 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] toByteArray() throws IOException {
/* 179 */     return toByteArray((String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toByteArray(String encoding) throws IOException {
/* 185 */     ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 186 */     store(stream, (String)null, encoding);
/* 187 */     return stream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load(byte[] data) {
/*     */     try {
/* 194 */       ByteArrayInputStream stream = new ByteArrayInputStream(data);
/* 195 */       load(stream);
/* 196 */       return true;
/*     */     }
/* 198 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 201 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load(byte[] data, String charSet) {
/*     */     try {
/* 208 */       ByteArrayInputStream stream = new ByteArrayInputStream(data);
/* 209 */       load(stream, charSet);
/* 210 */       return true;
/*     */     }
/* 212 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 215 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsIniProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */