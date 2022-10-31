/*     */ package com.lbs.http.cookie;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsCookieHive
/*     */ {
/*     */   private static boolean ms_ShortCookie = true;
/*  21 */   private static Hashtable ms_Table = new Hashtable<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processHeaderForCookie(HashMap headers, String URIString) {
/*     */     try {
/*  27 */       Object setCookie2 = headers.get("Set-Cookie2");
/*  28 */       if (setCookie2 != null) {
/*     */         
/*  30 */         addCookie(setCookie2, URIString, true);
/*     */         return;
/*     */       } 
/*  33 */       Object setCookie = headers.get("Set-Cookie");
/*  34 */       if (setCookie != null) {
/*     */         
/*  36 */         addCookie(setCookie, URIString, false);
/*     */         
/*     */         return;
/*     */       } 
/*  40 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCookie(Object cookie, String URIString, boolean newCookie) {
/*  47 */     if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL > 5)
/*  48 */       System.out.println("Setting cookie for " + URIString + " cookie: " + cookie); 
/*  49 */     if (cookie instanceof List) {
/*     */       
/*  51 */       List list = (List)cookie;
/*  52 */       int size = list.size();
/*  53 */       for (int i = 0; i < size; i++) {
/*     */         
/*  55 */         Object o = list.get(i);
/*  56 */         if (o instanceof String) {
/*  57 */           addCookie((String)o, URIString, newCookie);
/*     */         }
/*     */       } 
/*  60 */     } else if (cookie instanceof String) {
/*  61 */       addCookie((String)cookie, URIString, newCookie);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addCookie(String cookie, String URIString, boolean newCookie) {
/*     */     try {
/*  68 */       JLbsCookie[] cookies = JLbsCookie.parse(cookie, URIString);
/*  69 */       if (cookies != null)
/*  70 */         for (int i = 0; i < cookies.length; i++) {
/*  71 */           addCookie(cookies[i]);
/*     */         } 
/*  73 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] getCookies(String URIString) {
/*     */     try {
/*  82 */       return getCookies(new URI(URIString));
/*     */     }
/*  84 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  87 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String[] processHashTable(Hashtable table, URI uri) {
/*  92 */     ArrayList<JLbsCookie> eligible = new ArrayList();
/*  93 */     synchronized (table) {
/*     */       
/*  95 */       ArrayList<JLbsCookie> expired = new ArrayList();
/*  96 */       Enumeration<JLbsCookie> elements = table.elements();
/*  97 */       while (elements.hasMoreElements()) {
/*     */         
/*  99 */         JLbsCookie cookie = elements.nextElement();
/* 100 */         if (cookie.hasExpired()) {
/* 101 */           expired.add(cookie); continue;
/* 102 */         }  if (cookie.sendWith(uri))
/* 103 */           eligible.add(cookie); 
/*     */       } 
/* 105 */       if (expired.size() > 0)
/* 106 */         for (int i = 0; i < expired.size(); i++) {
/*     */           
/* 108 */           JLbsCookie cookie = expired.get(i);
/* 109 */           table.remove(cookie.getName());
/*     */         }  
/*     */     } 
/* 112 */     if (eligible.size() > 0) {
/*     */       
/* 114 */       String[] result = new String[eligible.size()];
/*     */       
/* 116 */       for (int i = 0; i < eligible.size(); i++) {
/*     */         
/* 118 */         StringBuilder buffer = new StringBuilder();
/* 119 */         if (!ms_ShortCookie)
/* 120 */           buffer.append("$Version=1; "); 
/* 121 */         JLbsCookie cookie = eligible.get(i);
/* 122 */         buffer.append(cookie.getCookieValue());
/* 123 */         if (!ms_ShortCookie) {
/*     */           
/* 125 */           if (cookie.getPath() != null) {
/*     */             
/* 127 */             buffer.append(";$path=\"");
/* 128 */             buffer.append(cookie.getPath());
/* 129 */             buffer.append("\"");
/*     */           } 
/* 131 */           if (cookie.getDomain() != null) {
/*     */             
/* 133 */             buffer.append(";$domain=\"");
/* 134 */             buffer.append(cookie.getDomain());
/* 135 */             buffer.append("\"");
/*     */           } 
/*     */         } 
/* 138 */         result[i] = buffer.toString();
/*     */       } 
/* 140 */       return result;
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] getCookies(URI uri) {
/* 147 */     if (uri != null) {
/*     */       Hashtable table;
/* 149 */       String domain = uri.getHost();
/*     */ 
/*     */ 
/*     */       
/* 153 */       synchronized (ms_Table) {
/*     */         
/* 155 */         table = (Hashtable)ms_Table.get(domain);
/*     */       } 
/* 157 */       if (table != null)
/* 158 */         return processHashTable(table, uri); 
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public static void addCookie(JLbsCookie cookie) {
/*     */     Object o;
/* 165 */     String key = cookie.getDomain();
/*     */     
/* 167 */     synchronized (ms_Table) {
/*     */       
/* 169 */       o = ms_Table.get(key);
/*     */     } 
/* 171 */     boolean bHasTable = o instanceof Hashtable;
/* 172 */     Hashtable<String, JLbsCookie> table = bHasTable ? 
/* 173 */       (Hashtable)o : 
/* 174 */       new Hashtable<>();
/* 175 */     if (bHasTable) {
/* 176 */       synchronized (table) {
/*     */         
/* 178 */         if (table.get(cookie.getName()) != null)
/* 179 */           table.remove(cookie.getName()); 
/* 180 */         table.put(cookie.getName(), cookie);
/*     */       } 
/*     */     } else {
/* 183 */       table.put(cookie.getName(), cookie);
/* 184 */     }  if (!bHasTable)
/* 185 */       synchronized (ms_Table) {
/*     */         
/* 187 */         ms_Table.put(cookie.getDomain(), table);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\cookie\JLbsCookieHive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */