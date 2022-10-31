/*     */ package com.lbs.util.xml;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
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
/*     */ public class XMLEncoder
/*     */ {
/*     */   public static final XMLEncoder getEncoder(String encoding) throws IllegalArgumentException, UnsupportedEncodingException {
/*  91 */     return new XMLEncoder(encoding);
/*     */   }
/*     */   
/*  94 */   private static final char[] DECLARATION_START = "<?xml version=\"1.0\" encoding=\"".toCharArray();
/*  95 */   private static final int DECLARATION_START_LENGTH = DECLARATION_START.length;
/*     */   
/*  97 */   private static final char[] DECLARATION_END = "\"?>".toCharArray();
/*  98 */   private static final int DECLARATION_END_LENGTH = DECLARATION_END.length;
/*  99 */   private static final char[] ESC_GREATER_THAN = new char[] { '&', 'g', 't', ';' };
/* 100 */   private static final char[] ESC_LESS_THAN = new char[] { '&', 'l', 't', ';' };
/* 101 */   private static final char[] ESC_AMPERSAND = new char[] { '&', 'a', 'm', 'p', ';' };
/* 102 */   private static final char[] ESC_APOSTROPHE = new char[] { '&', 'a', 'p', 'o', 's', ';' };
/* 103 */   private static final char[] ESC_QUOTE = new char[] { '&', 'q', 'u', 'o', 't', ';' };
/* 104 */   private static final char[] AMPERSAND_HASH = new char[] { '&', '#' };
/* 105 */   private static final char[] EQUALS_APOSTROPHE = new char[] { '=', '\'' };
/* 106 */   private static final char[] EQUALS_QUOTE = new char[] { '=', '"' };
/*     */   
/*     */   private final String m_Encoding;
/*     */   
/*     */   private final char[] m_EncodingCharArray;
/*     */   
/*     */   private final boolean m_SevenBitEncoding;
/*     */   
/*     */   public XMLEncoder(String encoding) throws IllegalArgumentException, UnsupportedEncodingException {
/* 115 */     if (encoding == null)
/*     */     {
/* 117 */       throw new IllegalArgumentException("encoding == null");
/*     */     }
/* 119 */     String ucEncoding = encoding.toUpperCase();
/* 120 */     if (ucEncoding.equals("UTF-8") || ucEncoding.equals("UTF-16")) {
/*     */       
/* 122 */       this.m_SevenBitEncoding = false;
/*     */     }
/* 124 */     else if (ucEncoding.equals("US-ASCII") || ucEncoding.equals("ASCII") || ucEncoding.startsWith("ISO-8859-")) {
/*     */       
/* 126 */       this.m_SevenBitEncoding = true;
/*     */     }
/*     */     else {
/*     */       
/* 130 */       throw new UnsupportedEncodingException(encoding);
/*     */     } 
/* 132 */     this.m_Encoding = encoding;
/* 133 */     this.m_EncodingCharArray = encoding.toCharArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncoding() {
/* 138 */     return this.m_Encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public void declaration(Writer out) throws NullPointerException, IOException {
/* 143 */     out.write(DECLARATION_START, 0, DECLARATION_START_LENGTH);
/* 144 */     out.write(this.m_EncodingCharArray);
/* 145 */     out.write(DECLARATION_END, 0, DECLARATION_END_LENGTH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void text(Writer out, String text, boolean escapeAmpersands) throws NullPointerException, InvalidXMLException, IOException {
/* 152 */     text(out, text.toCharArray(), 0, text.length(), escapeAmpersands);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void text(Writer out, char[] ch, int start, int length, boolean escapeAmpersands) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException, IOException {
/* 159 */     int end = start + length;
/* 160 */     int lastEscaped = start;
/* 161 */     for (int i = start; i < end; i++) {
/*     */       
/* 163 */       int c = ch[i];
/*     */       
/* 165 */       if ((c < 63 || c > 127) && (c < 39 || c > 59) && (c < 32 || c > 37) && (c != 38 || escapeAmpersands) && (c <= 127 || this.m_SevenBitEncoding) && c != 10 && c != 13 && c != 61 && c != 9) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 172 */         out.write(ch, lastEscaped, i - lastEscaped);
/* 173 */         if (c == 60) {
/*     */           
/* 175 */           out.write(ESC_LESS_THAN, 0, 4);
/*     */         }
/* 177 */         else if (c == 62) {
/*     */           
/* 179 */           out.write(ESC_GREATER_THAN, 0, 4);
/*     */         }
/* 181 */         else if (c == 38) {
/*     */           
/* 183 */           out.write(ESC_AMPERSAND, 0, 5);
/*     */         }
/* 185 */         else if (c > 127) {
/*     */           
/* 187 */           out.write(AMPERSAND_HASH, 0, 2);
/* 188 */           out.write(Integer.toString(c));
/* 189 */           out.write(59);
/*     */         }
/*     */         else {
/*     */           
/* 193 */           throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid.");
/*     */         } 
/* 195 */         lastEscaped = i + 1;
/*     */       } 
/*     */     } 
/* 198 */     out.write(ch, lastEscaped, end - lastEscaped);
/*     */   }
/*     */ 
/*     */   
/*     */   public void text(Writer out, char c) throws InvalidXMLException, IOException {
/* 203 */     if ((c >= '?' && c <= '') || (c >= '\'' && c <= ';') || (c >= ' ' && c <= '%') || c == '&' || (c > '' && !this.m_SevenBitEncoding) || c == '\n' || c == '\r' || c == '=' || c == '\t') {
/*     */ 
/*     */       
/* 206 */       out.write(c);
/*     */ 
/*     */     
/*     */     }
/* 210 */     else if (c == '<') {
/*     */       
/* 212 */       out.write(ESC_LESS_THAN, 0, 4);
/*     */     }
/* 214 */     else if (c == '>') {
/*     */       
/* 216 */       out.write(ESC_GREATER_THAN, 0, 4);
/*     */     }
/* 218 */     else if (c > '') {
/*     */       
/* 220 */       out.write(AMPERSAND_HASH, 0, 2);
/* 221 */       out.write(Integer.toString(c));
/* 222 */       out.write(59);
/*     */     }
/*     */     else {
/*     */       
/* 226 */       throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void text(Writer out, char c, boolean escapeAmpersands) throws InvalidXMLException, IOException {
/* 233 */     if ((c >= '?' && c <= '') || (c >= '\'' && c <= ';') || (c >= ' ' && c <= '%') || (c == '&' && escapeAmpersands) || (c > '' && !this.m_SevenBitEncoding) || c == '\n' || c == '\r' || c == '=' || c == '\t') {
/*     */ 
/*     */       
/* 236 */       out.write(c);
/*     */ 
/*     */     
/*     */     }
/* 240 */     else if (c == '<') {
/*     */       
/* 242 */       out.write(ESC_LESS_THAN, 0, 4);
/*     */     }
/* 244 */     else if (c == '>') {
/*     */       
/* 246 */       out.write(ESC_GREATER_THAN, 0, 4);
/*     */     }
/* 248 */     else if (c == '&') {
/*     */       
/* 250 */       out.write(ESC_AMPERSAND, 0, 5);
/*     */     }
/* 252 */     else if (c > '') {
/*     */       
/* 254 */       out.write(AMPERSAND_HASH, 0, 2);
/* 255 */       out.write(Integer.toString(c));
/* 256 */       out.write(59);
/*     */     }
/*     */     else {
/*     */       
/* 260 */       throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whitespace(Writer out, String s) throws NullPointerException, InvalidXMLException, IOException {
/* 268 */     char[] ch = s.toCharArray();
/* 269 */     int length = ch.length;
/* 270 */     whitespace(out, ch, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whitespace(Writer out, char[] ch, int start, int length) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException, IOException {
/* 277 */     XMLChecker.checkS(ch, start, length);
/* 278 */     out.write(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attribute(Writer out, String name, String value, char quotationMark, boolean escapeAmpersands) throws NullPointerException, IOException {
/*     */     boolean useQuote;
/* 285 */     char[] ch = value.toCharArray();
/* 286 */     int length = ch.length;
/* 287 */     int start = 0;
/* 288 */     int end = start + length;
/*     */     
/* 290 */     int lastEscaped = 0;
/*     */ 
/*     */     
/* 293 */     if (quotationMark == '"') {
/*     */       
/* 295 */       useQuote = true;
/*     */     }
/* 297 */     else if (quotationMark == '\'') {
/*     */       
/* 299 */       useQuote = false;
/*     */     }
/*     */     else {
/*     */       
/* 303 */       String error = "Character 0x" + Integer.toHexString(quotationMark) + " ('" + quotationMark + "') is not a valid quotation mark.";
/*     */       
/* 305 */       throw new IllegalArgumentException(error);
/*     */     } 
/*     */     
/* 308 */     out.write(32);
/* 309 */     out.write(name);
/*     */     
/* 311 */     if (useQuote) {
/*     */       
/* 313 */       out.write(EQUALS_QUOTE, 0, 2);
/*     */     }
/*     */     else {
/*     */       
/* 317 */       out.write(EQUALS_APOSTROPHE, 0, 2);
/*     */     } 
/*     */     
/* 320 */     for (int i = start; i < end; i++) {
/*     */       
/* 322 */       int c = ch[i];
/*     */       
/* 324 */       if ((c < 63 || c > 127) && (c < 40 || c > 59) && (c < 32 || c > 37 || c == 34) && (c != 38 || escapeAmpersands) && (c <= 127 || this.m_SevenBitEncoding) && (useQuote || c != 34) && (!useQuote || c != 39) && c != 10 && c != 13 && c != 61 && c != 9) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 332 */         out.write(ch, lastEscaped, i - lastEscaped);
/* 333 */         if (c == 60) {
/*     */           
/* 335 */           out.write(ESC_LESS_THAN, 0, 4);
/*     */         }
/* 337 */         else if (c == 62) {
/*     */           
/* 339 */           out.write(ESC_GREATER_THAN, 0, 4);
/*     */         }
/* 341 */         else if (c == 34) {
/*     */           
/* 343 */           out.write(ESC_QUOTE, 0, 6);
/*     */         }
/* 345 */         else if (c == 39) {
/*     */           
/* 347 */           out.write(ESC_APOSTROPHE, 0, 6);
/*     */         }
/* 349 */         else if (c == 38) {
/*     */           
/* 351 */           out.write(ESC_AMPERSAND, 0, 5);
/*     */         }
/* 353 */         else if (c > 127) {
/*     */           
/* 355 */           out.write(AMPERSAND_HASH, 0, 2);
/* 356 */           out.write(Integer.toString(c));
/* 357 */           out.write(59);
/*     */         }
/*     */         else {
/*     */           
/* 361 */           throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid.");
/*     */         } 
/* 363 */         lastEscaped = i + 1;
/*     */       } 
/*     */     } 
/*     */     
/* 367 */     out.write(ch, lastEscaped, length - lastEscaped);
/* 368 */     out.write(quotationMark);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\XMLEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */