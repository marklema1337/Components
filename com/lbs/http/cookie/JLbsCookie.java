/*     */ package com.lbs.http.cookie;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.Serializable;
/*     */ import java.net.ProtocolException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsCookie
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Name;
/*     */   protected String m_Value;
/*     */   protected Calendar m_Expires;
/*     */   protected String m_Domain;
/*     */   protected String m_Path;
/*     */   protected boolean m_Secure;
/*     */   
/*     */   public JLbsCookie(String name, String value, String domain, String path, Calendar expires, boolean secure) {
/*  35 */     if (name == null)
/*  36 */       throw new NullPointerException("Missing name"); 
/*  37 */     if (value == null)
/*  38 */       throw new NullPointerException("Missing value"); 
/*  39 */     if (domain == null)
/*  40 */       throw new NullPointerException("Missing domain"); 
/*  41 */     if (path == null)
/*  42 */       throw new NullPointerException("Missing path"); 
/*  43 */     this.m_Name = name;
/*  44 */     this.m_Value = value;
/*  45 */     this.m_Domain = domain.toLowerCase();
/*  46 */     this.m_Path = path;
/*  47 */     this.m_Expires = expires;
/*  48 */     this.m_Secure = secure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JLbsCookie(String URIString) throws URISyntaxException {
/*  54 */     this(new URI(URIString));
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsCookie(URI uri) {
/*  59 */     this.m_Name = null;
/*  60 */     this.m_Value = null;
/*  61 */     this.m_Expires = null;
/*  62 */     this.m_Domain = uri.getHost();
/*     */ 
/*     */     
/*  65 */     this.m_Path = uri.getPath();
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.m_Secure = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int skipSpace(char[] chars, int index) {
/*  74 */     for (; index < chars.length && Character.isSpaceChar(chars[index]); index++);
/*  75 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static JLbsCookie[] parse(String set_cookie, String URIString) throws URISyntaxException, ProtocolException {
/*  80 */     int beg = 0, end = 0;
/*  81 */     char[] buf = set_cookie.toCharArray();
/*  82 */     int len = buf.length;
/*     */     
/*  84 */     URI uri = new URI(URIString);
/*  85 */     ArrayList<JLbsCookie> cookies = new ArrayList();
/*     */     
/*     */     while (true) {
/*  88 */       beg = skipSpace(buf, beg);
/*  89 */       if (beg >= len)
/*     */         break; 
/*  91 */       if (buf[beg] == ',') {
/*     */         
/*  93 */         beg++;
/*     */         continue;
/*     */       } 
/*  96 */       JLbsCookie curr = new JLbsCookie(uri);
/*     */       
/*  98 */       end = set_cookie.indexOf('=', beg);
/*  99 */       if (end == -1)
/* 100 */         throw new ProtocolException(
/* 101 */             "Bad Set-Cookie header: " + 
/* 102 */             set_cookie + 
/* 103 */             "\nNo '=' found " + 
/* 104 */             "for token starting at " + 
/* 105 */             "position " + 
/* 106 */             beg); 
/* 107 */       curr.m_Name = set_cookie.substring(beg, end).trim();
/* 108 */       beg = skipSpace(buf, end + 1);
/* 109 */       int comma = set_cookie.indexOf(',', beg);
/* 110 */       int semic = set_cookie.indexOf(';', beg);
/* 111 */       if (comma == -1 && semic == -1) {
/* 112 */         end = len;
/*     */       }
/* 114 */       else if (comma == -1) {
/* 115 */         end = semic;
/*     */       }
/* 117 */       else if (semic == -1) {
/* 118 */         end = comma;
/*     */       
/*     */       }
/* 121 */       else if (comma > semic) {
/* 122 */         end = semic;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 127 */         int eq = set_cookie.indexOf('=', comma);
/* 128 */         if (eq > 0 && eq < semic) {
/* 129 */           end = set_cookie.lastIndexOf(',', eq);
/*     */         } else {
/* 131 */           end = semic;
/*     */         } 
/*     */       } 
/* 134 */       curr.m_Value = set_cookie.substring(beg, end).trim();
/* 135 */       beg = end;
/*     */       
/* 137 */       boolean legal = true;
/*     */ 
/*     */       
/* 140 */       while (beg < len && buf[beg] != ',') {
/*     */ 
/*     */         
/* 143 */         if (buf[beg] == ';') {
/*     */           
/* 145 */           beg = skipSpace(buf, beg + 1);
/*     */           
/*     */           continue;
/*     */         } 
/* 149 */         if (beg + 6 <= len && 
/* 150 */           set_cookie.regionMatches(true, beg, "secure", 0, 6)) {
/*     */           
/* 152 */           curr.m_Secure = true;
/* 153 */           beg += 6;
/* 154 */           beg = skipSpace(buf, beg);
/* 155 */           if (beg < len && buf[beg] == ';') {
/* 156 */             beg = skipSpace(buf, beg + 1); continue;
/*     */           } 
/* 158 */           if (beg < len && buf[beg] != ',') {
/* 159 */             throw new ProtocolException(
/* 160 */                 "Bad Set-Cookie header: " + 
/* 161 */                 set_cookie + 
/* 162 */                 "\nExpected " + 
/* 163 */                 "';' or ',' at position " + 
/* 164 */                 beg);
/*     */           }
/*     */           continue;
/*     */         } 
/* 168 */         end = set_cookie.indexOf('=', beg);
/* 169 */         if (end == -1)
/* 170 */           throw new ProtocolException(
/* 171 */               "Bad Set-Cookie header: " + 
/* 172 */               set_cookie + 
/* 173 */               "\nNo '=' found " + 
/* 174 */               "for token starting at " + 
/* 175 */               "position " + 
/* 176 */               beg); 
/* 177 */         String name = set_cookie.substring(beg, end).trim();
/* 178 */         beg = skipSpace(buf, end + 1);
/* 179 */         if (name.equalsIgnoreCase("expires")) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 184 */           if (set_cookie.charAt(beg) == '"') {
/* 185 */             beg = skipSpace(buf, beg + 1);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 191 */           int pos = beg;
/* 192 */           while (pos < len && ((
/* 193 */             buf[pos] >= 'a' && 
/* 194 */             buf[pos] <= 'z') || (
/* 195 */             buf[pos] >= 'A' && 
/* 196 */             buf[pos] <= 'Z')))
/* 197 */             pos++; 
/* 198 */           pos = skipSpace(buf, pos);
/* 199 */           if (pos < len && buf[pos] == ',' && pos > beg)
/* 200 */             beg = pos + 1; 
/*     */         } 
/* 202 */         comma = set_cookie.indexOf(',', beg);
/* 203 */         semic = set_cookie.indexOf(';', beg);
/* 204 */         if (comma == -1 && semic == -1) {
/* 205 */           end = len;
/*     */         }
/* 207 */         else if (comma == -1) {
/* 208 */           end = semic;
/*     */         }
/* 210 */         else if (semic == -1) {
/* 211 */           end = comma;
/*     */         } else {
/* 213 */           end = Math.min(comma, semic);
/* 214 */         }  String value = set_cookie.substring(beg, end).trim();
/* 215 */         legal &= curr.setAttribute(name, value, set_cookie);
/* 216 */         beg = end;
/* 217 */         if (beg < len && buf[beg] == ';')
/* 218 */           beg = skipSpace(buf, beg + 1); 
/*     */       } 
/* 220 */       if (legal) {
/* 221 */         cookies.add(curr);
/*     */         continue;
/*     */       } 
/* 224 */       if (JLbsConstants.LOGLEVEL > 4) {
/* 225 */         System.out.println("Cookie : Ignoring cookie " + curr);
/*     */       }
/*     */     } 
/* 228 */     return (cookies.size() > 0) ? cookies.<JLbsCookie>toArray(new JLbsCookie[cookies.size()]) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean setAttribute(String name, String value, String set_cookie) throws ProtocolException {
/* 237 */     if (name.equalsIgnoreCase("expires")) {
/*     */       
/* 239 */       if (value.charAt(value.length() - 1) == '"') {
/* 240 */         value = value.substring(0, value.length() - 1).trim();
/*     */       }
/*     */       try {
/* 243 */         if (value.endsWith("GMT"))
/* 244 */           value = value.substring(0, value.length() - 4); 
/* 245 */         DateFormat f = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
/* 246 */         Date date = f.parse(value);
/* 247 */         this.m_Expires = new GregorianCalendar();
/* 248 */         this.m_Expires.setTime(date);
/*     */       }
/* 250 */       catch (Exception iae) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 258 */         if (JLbsConstants.DEBUG) {
/* 259 */           System.out.println("Cookie : Bad Set-Cookie header: " + set_cookie + "\n       Invalid date `" + value + "'");
/*     */         }
/*     */       }
/*     */     
/* 263 */     } else if (name.equals("max-age")) {
/*     */       int age;
/* 265 */       if (this.m_Expires != null)
/* 266 */         return true; 
/* 267 */       if (value.charAt(0) == '"' && 
/* 268 */         value.charAt(value.length() - 1) == '"') {
/* 269 */         value = value.substring(1, value.length() - 1).trim();
/*     */       }
/*     */       
/*     */       try {
/* 273 */         age = Integer.parseInt(value);
/*     */       }
/* 275 */       catch (NumberFormatException nfe) {
/*     */         
/* 277 */         throw new ProtocolException(
/* 278 */             "Bad Set-Cookie header: " + 
/* 279 */             set_cookie + 
/* 280 */             "\nMax-Age '" + 
/* 281 */             value + 
/* 282 */             "' not a number");
/*     */       } 
/* 284 */       this.m_Expires = new GregorianCalendar();
/* 285 */       this.m_Expires.setTimeInMillis(System.currentTimeMillis() + age * 1000L);
/*     */     
/*     */     }
/* 288 */     else if (name.equalsIgnoreCase("domain")) {
/*     */ 
/*     */       
/* 291 */       if (value.length() == 0) {
/*     */         
/* 293 */         if (JLbsConstants.LOGLEVEL > 4)
/* 294 */           System.out.println("Cookie : Bad Set-Cookie header: " + set_cookie + "\n       domain is empty - ignoring domain"); 
/* 295 */         return true;
/*     */       } 
/*     */       
/* 298 */       value = value.toLowerCase();
/*     */       
/* 300 */       if (value.length() != 0 && 
/* 301 */         value.charAt(0) != '.' && 
/* 302 */         !value.equals(this.m_Domain)) {
/* 303 */         value = String.valueOf('.') + value;
/*     */       }
/* 305 */       if (!this.m_Domain.endsWith(value)) {
/*     */         
/* 307 */         if (JLbsConstants.LOGLEVEL > 4)
/* 308 */           System.out.println("Cookie : Bad Set-Cookie header: " + 
/* 309 */               set_cookie + 
/* 310 */               "\n       Current domain " + 
/* 311 */               this.m_Domain + 
/* 312 */               " does not match given parsed " + 
/* 313 */               value); 
/* 314 */         return false;
/*     */       } 
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
/* 342 */       String str = null;
/* 343 */       if (value.length() > 3)
/* 344 */         str = value.substring(value.length() - 4); 
/* 345 */       if (str == null || (
/* 346 */         !str.equalsIgnoreCase(".com") && 
/* 347 */         !str.equalsIgnoreCase(".edu") && 
/* 348 */         !str.equalsIgnoreCase(".net") && 
/* 349 */         !str.equalsIgnoreCase(".org") && 
/* 350 */         !str.equalsIgnoreCase(".gov") && 
/* 351 */         !str.equalsIgnoreCase(".mil") && 
/* 352 */         !str.equalsIgnoreCase(".int"))) {
/*     */         
/* 354 */         int dl = this.m_Domain.length(), vl = value.length();
/* 355 */         if (dl > vl && 
/* 356 */           this.m_Domain.substring(0, dl - vl).indexOf('.') != 
/* 357 */           -1) {
/*     */           
/* 359 */           if (JLbsConstants.LOGLEVEL > 4)
/* 360 */             System.out.println("Cookie : Bad Set-Cookie header: " + 
/* 361 */                 set_cookie + 
/* 362 */                 "\n       Domain attribute " + 
/* 363 */                 value + 
/* 364 */                 "is more than one level below " + 
/* 365 */                 "current domain " + 
/* 366 */                 this.m_Domain); 
/* 367 */           return false;
/*     */         } 
/*     */       } 
/* 370 */       this.m_Domain = value;
/*     */     
/*     */     }
/* 373 */     else if (name.equalsIgnoreCase("path")) {
/* 374 */       this.m_Path = value;
/*     */     } 
/*     */     
/* 377 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 382 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 387 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar expires() {
/* 392 */     return this.m_Expires;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean discard() {
/* 397 */     return (this.m_Expires == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDomain() {
/* 402 */     return this.m_Domain;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 407 */     return this.m_Path;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSecure() {
/* 412 */     return this.m_Secure;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasExpired() {
/* 417 */     return (this.m_Expires != null && this.m_Expires.getTimeInMillis() <= System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sendWith(String URIString) throws URISyntaxException {
/* 422 */     if (URIString == null) return false; 
/* 423 */     URI uri = new URI(URIString);
/* 424 */     return sendWith(uri);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sendWith(URI uri) {
/* 429 */     if (uri == null) return false; 
/* 430 */     String eff_host = uri.getHost();
/*     */ 
/*     */     
/* 433 */     return 
/* 434 */       (((this.m_Domain.charAt(0) == '.' && 
/* 435 */       eff_host.endsWith(this.m_Domain)) || (
/* 436 */       this.m_Domain.charAt(0) != '.' && 
/* 437 */       eff_host.equals(this.m_Domain))) && 
/* 438 */       uri.getPath().startsWith(this.m_Path));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 443 */     return this.m_Name.hashCode() + this.m_Path.hashCode() + this.m_Domain.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 448 */     if (obj != null && obj instanceof JLbsCookie) {
/*     */       
/* 450 */       JLbsCookie other = (JLbsCookie)obj;
/* 451 */       return 
/* 452 */         (this.m_Name.equals(other.m_Name) && 
/* 453 */         this.m_Path.equals(other.m_Path) && 
/* 454 */         this.m_Domain.equals(other.m_Domain));
/*     */     } 
/* 456 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullPath() {
/* 461 */     StringBuilder res = new StringBuilder();
/* 462 */     if (this.m_Domain != null)
/* 463 */       res.append(this.m_Domain); 
/* 464 */     if (this.m_Path != null)
/* 465 */       res.append(this.m_Path); 
/* 466 */     return res.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCookieValue() {
/* 471 */     StringBuilder res = new StringBuilder();
/* 472 */     res.append(this.m_Name);
/* 473 */     res.append("=");
/* 474 */     res.append(this.m_Value);
/* 475 */     return res.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 480 */     StringBuilder res = 
/* 481 */       new StringBuilder(this.m_Name.length() + this.m_Value.length() + 30);
/* 482 */     res.append(this.m_Name).append('=').append(this.m_Value);
/* 483 */     if (this.m_Expires != null)
/* 484 */       res.append("; expires=").append(this.m_Expires); 
/* 485 */     if (this.m_Path != null)
/* 486 */       res.append("; path=").append(this.m_Path); 
/* 487 */     if (this.m_Domain != null)
/* 488 */       res.append("; domain=").append(this.m_Domain); 
/* 489 */     if (this.m_Secure)
/* 490 */       res.append("; secure"); 
/* 491 */     return res.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\cookie\JLbsCookie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */