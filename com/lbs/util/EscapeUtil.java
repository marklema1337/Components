/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ public class EscapeUtil
/*     */ {
/*     */   public static String escapeJava(String s) {
/*  11 */     if (s == null)
/*  12 */       return s; 
/*  13 */     char[] characters = s.toCharArray();
/*  14 */     StringBuilder buf = new StringBuilder();
/*  15 */     for (int i = 0; i < characters.length; i++) {
/*     */       
/*  17 */       char ch = characters[i];
/*  18 */       if (ch == '\b') {
/*  19 */         buf.append("\\b");
/*  20 */       } else if (ch == '\t') {
/*  21 */         buf.append("\\t");
/*  22 */       } else if (ch == '\n') {
/*  23 */         buf.append("\\n");
/*  24 */       } else if (ch == '\r') {
/*  25 */         buf.append("\\r");
/*  26 */       } else if (ch == '\f') {
/*  27 */         buf.append("\\f");
/*  28 */       } else if (ch == '\\') {
/*  29 */         buf.append("\\\\");
/*  30 */       } else if (ch >= '') {
/*     */         
/*  32 */         buf.append('\\');
/*  33 */         buf.append(Integer.toOctalString(ch));
/*     */       } else {
/*     */         
/*  36 */         buf.append(ch);
/*     */       } 
/*  38 */     }  return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String unescapeJava(String s) {
/*  43 */     if (s == null)
/*  44 */       return s; 
/*  45 */     char[] characters = s.toCharArray();
/*  46 */     boolean escape = false;
/*  47 */     StringBuilder buf = new StringBuilder();
/*  48 */     for (int i = 0; i < characters.length; i++) {
/*     */       
/*  50 */       char ch = characters[i];
/*  51 */       if (escape) {
/*     */         
/*  53 */         if (ch == 'b') {
/*  54 */           buf.append('\b');
/*  55 */         } else if (ch == 't') {
/*  56 */           buf.append('\t');
/*  57 */         } else if (ch == 'n') {
/*  58 */           buf.append('\n');
/*  59 */         } else if (ch == 'r') {
/*  60 */           buf.append('\r');
/*  61 */         } else if (ch == 'f') {
/*  62 */           buf.append('\f');
/*  63 */         } else if (ch == 'u') {
/*     */           
/*  65 */           if (i + 4 < characters.length)
/*     */           {
/*  67 */             int utf = Integer.parseInt(s.substring(i + 1, i + 5), 16);
/*  68 */             buf.append((char)utf);
/*  69 */             i += 4;
/*     */           }
/*     */         
/*  72 */         } else if (Character.isDigit(ch)) {
/*     */           
/*  74 */           int k = 0;
/*  75 */           for (k = 1; k < 2 && i + k < characters.length; k++) {
/*     */             
/*  77 */             if (!Character.isDigit(characters[i + k]))
/*     */               break; 
/*     */           } 
/*  80 */           int octal = Integer.parseInt(s.substring(i, i + k + 1), 8);
/*  81 */           buf.append((char)octal);
/*  82 */           i += k;
/*     */         } else {
/*     */           
/*  85 */           buf.append(ch);
/*  86 */         }  escape = false;
/*     */       }
/*  88 */       else if (ch == '\\') {
/*  89 */         escape = true;
/*     */       } else {
/*  91 */         buf.append(ch);
/*     */       } 
/*  93 */     }  return buf.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String escapeJavaString(String str) {
/* 119 */     if (str == null)
/*     */     {
/* 121 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 125 */       StringWriter writer = new StringWriter(str.length() * 2);
/* 126 */       escapeJavaStyleString(writer, str);
/* 127 */       return writer.toString();
/*     */     }
/* 129 */     catch (IOException ioe) {
/*     */ 
/*     */       
/* 132 */       ioe.printStackTrace();
/* 133 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void escapeJavaStyleString(Writer out, String str) throws IOException {
/* 139 */     if (out == null)
/*     */     {
/* 141 */       throw new IllegalArgumentException("The Writer must not be null");
/*     */     }
/* 143 */     if (str == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 148 */     int sz = str.length();
/* 149 */     for (int i = 0; i < sz; i++) {
/*     */       
/* 151 */       char ch = str.charAt(i);
/*     */ 
/*     */       
/* 154 */       if (ch > '࿿') {
/*     */         
/* 156 */         out.write("\\u" + hex(ch));
/*     */       }
/* 158 */       else if (ch > 'ÿ') {
/*     */         
/* 160 */         out.write("\\u0" + hex(ch));
/*     */       }
/* 162 */       else if (ch > '') {
/*     */         
/* 164 */         out.write("\\u00" + hex(ch));
/*     */       }
/* 166 */       else if (ch < ' ') {
/*     */         
/* 168 */         switch (ch) {
/*     */           
/*     */           case '\b':
/* 171 */             out.write(92);
/* 172 */             out.write(98);
/*     */             break;
/*     */           case '\n':
/* 175 */             out.write(92);
/* 176 */             out.write(110);
/*     */             break;
/*     */           case '\t':
/* 179 */             out.write(92);
/* 180 */             out.write(116);
/*     */             break;
/*     */           case '\f':
/* 183 */             out.write(92);
/* 184 */             out.write(102);
/*     */             break;
/*     */           case '\r':
/* 187 */             out.write(92);
/* 188 */             out.write(114);
/*     */             break;
/*     */           default:
/* 191 */             if (ch > '\017') {
/*     */               
/* 193 */               out.write("\\u00" + hex(ch));
/*     */               
/*     */               break;
/*     */             } 
/* 197 */             out.write("\\u000" + hex(ch));
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } else {
/* 204 */         switch (ch) {
/*     */           
/*     */           case '"':
/* 207 */             out.write(92);
/* 208 */             out.write(34);
/*     */             break;
/*     */           case '\\':
/* 211 */             out.write(92);
/* 212 */             out.write(92);
/*     */             break;
/*     */           
/*     */           case '/':
/* 216 */             out.write(47);
/*     */             break;
/*     */           default:
/* 219 */             out.write(ch);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String hex(char ch) {
/* 228 */     return Integer.toHexString(ch).toUpperCase();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void testString(String s) {
/* 233 */     if (s == null)
/*     */       return; 
/* 235 */     System.out.println("Input     : " + s);
/* 236 */     String normalized = unescapeJava(s);
/* 237 */     System.out.println("Unescaped : " + normalized);
/* 238 */     String escaped = escapeJava(normalized);
/* 239 */     System.out.println("Escaped   : " + escaped);
/* 240 */     String control = unescapeJava(escaped);
/* 241 */     System.out.println("Control   : " + control);
/* 242 */     if (normalized.compareTo(control) != 0)
/* 243 */       System.out.println("** Failed for input " + s); 
/* 244 */     System.out.println("--------------------------------------");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 249 */     testString("Rapor Değişkeni Tanımlama\\nSihirbazına Hoşgeldiniz");
/* 250 */     testString("Slash is \\\\ and new line is \\n triple slash \\\\\\n");
/* 251 */     System.out.println(escapeJavaString("Deneme \"ali\""));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\EscapeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */