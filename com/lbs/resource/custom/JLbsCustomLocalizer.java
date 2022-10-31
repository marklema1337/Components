/*     */ package com.lbs.resource.custom;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
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
/*     */ public class JLbsCustomLocalizer
/*     */ {
/*     */   private JLbsResourceManager m_ResourceManager;
/*     */   private ILbsCultureInfo m_CultureInfo;
/*     */   private JLbsCurrenciesBase m_CurrencyBase;
/*     */   private JLbsStringList m_GlobalResources;
/*     */   
/*     */   public JLbsCustomLocalizer(IApplicationContext context) {
/*  33 */     this.m_ResourceManager = new JLbsResourceManager(context);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGlobalString(int tag) {
/*  38 */     if (this.m_GlobalResources == null)
/*  39 */       this.m_GlobalResources = getStringList(JLbsConstants.GLOBAL_RESOURCES); 
/*  40 */     if (this.m_GlobalResources != null)
/*  41 */       return getTrimmedString(this.m_GlobalResources.getValueAtTag(tag)); 
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(int listNo, int tag) {
/*  53 */     JLbsStringList list = getStringList(listNo);
/*  54 */     if (list != null)
/*  55 */       return getTrimmedString(list.getValueAtTag(tag)); 
/*  56 */     return null;
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
/*     */   public String getStringDef(int listNo, int tag, String defVal) {
/*  68 */     String s = getString(listNo, tag);
/*  69 */     return (s != null) ? 
/*  70 */       s : 
/*  71 */       defVal;
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
/*     */   public static String getStringDef(JLbsStringList list, int tag, String defVal) {
/*  83 */     if (list != null) {
/*     */       
/*  85 */       String s = getTrimmedString(list.getValueAtTag(tag));
/*  86 */       if (s != null)
/*  87 */         return s; 
/*     */     } 
/*  89 */     return defVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringByIndex(int listNo, int index) {
/* 100 */     JLbsStringList list = getStringList(listNo);
/* 101 */     if (list != null)
/* 102 */       return list.getValueAt(index); 
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringByTag(int listNo, int tag) {
/* 108 */     JLbsStringList list = getStringList(listNo);
/* 109 */     if (list != null)
/* 110 */       return list.getValueAtTag(tag); 
/* 111 */     return null;
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
/*     */   public String getStringByTagDef(int listNo, int tag, String defVal) {
/* 123 */     String s = getStringByTag(listNo, tag);
/* 124 */     return (s != null) ? 
/* 125 */       s : 
/* 126 */       defVal;
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
/*     */   public String getStringByIndexDef(int listNo, int index, String defVal) {
/* 138 */     String s = getStringByIndex(listNo, index);
/* 139 */     return (s != null) ? 
/* 140 */       s : 
/* 141 */       defVal;
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
/*     */   public static String getStringByIndexDef(JLbsStringList list, int index, String defVal) {
/* 153 */     if (list != null) {
/*     */       
/* 155 */       String s = getTrimmedString(list.getValueAt(index));
/* 156 */       if (s != null)
/* 157 */         return s; 
/*     */     } 
/* 159 */     return defVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringList(int listNo) {
/* 169 */     if (listNo == 0)
/* 170 */       return null; 
/* 171 */     if (this.m_ResourceManager != null)
/* 172 */       return this.m_ResourceManager.getStringList(listNo); 
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringList(int listNo, int tag) {
/* 178 */     JLbsStringList list = getStringList(listNo);
/* 179 */     if (list != null) {
/*     */       
/* 181 */       String s = list.getValueAtTag(tag);
/* 182 */       if (s != null)
/* 183 */         return new JLbsStringList(s); 
/*     */     } 
/* 185 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean localizeStringArrayRaw(String list, String[] target) {
/* 196 */     if (list != null && target != null) {
/*     */       
/* 198 */       JLbsStringList slist = new JLbsStringList(list);
/* 199 */       return localizeStringArrayRaw(slist, 0, target);
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean localizeStringArray(String list, String[] target) {
/* 212 */     if (list != null && target != null) {
/*     */       
/* 214 */       JLbsStringList slist = new JLbsStringList(list);
/* 215 */       return localizeStringArray(slist, 0, target);
/*     */     } 
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean localizeObjectArray(String list, Object[] target) {
/* 228 */     if (list != null && target != null) {
/*     */       
/* 230 */       JLbsStringList slist = new JLbsStringList(list);
/* 231 */       return localizeObjectArray(slist, 0, target);
/*     */     } 
/* 233 */     return false;
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
/*     */   public static boolean localizeStringArray(JLbsStringList list, int startIndex, String[] target) {
/* 245 */     if (list != null && target != null) {
/*     */       
/* 247 */       int index = 0;
/* 248 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 250 */         if (target[index] != null)
/* 251 */           target[index] = getTrimmedString(list.getValueAt(i)); 
/* 252 */         index++;
/*     */       } 
/* 254 */       return true;
/*     */     } 
/* 256 */     return false;
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
/*     */   public static boolean localizeStringArrayRaw(JLbsStringList list, int startIndex, String[] target) {
/* 268 */     if (list != null && target != null) {
/*     */       
/* 270 */       int index = 0;
/* 271 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 273 */         if (target[index] != null) {
/*     */           
/* 275 */           JLbsStringListItem itm = list.getItemAt(i);
/* 276 */           if (itm != null) {
/*     */             
/* 278 */             String s = getTrimmedString(itm.Value);
/* 279 */             if (itm.Tag != 0)
/* 280 */               s = String.valueOf(s) + "~" + itm.Tag; 
/* 281 */             target[index] = s;
/*     */           } 
/*     */         } 
/* 284 */         index++;
/*     */       } 
/* 286 */       return true;
/*     */     } 
/* 288 */     return false;
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
/*     */   public static boolean localizeObjectArray(JLbsStringList list, int startIndex, Object[] target) {
/* 300 */     if (list != null && target != null) {
/*     */       
/* 302 */       int index = 0;
/* 303 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 305 */         if (target[index] != null)
/* 306 */           target[index] = getTrimmedString(list.getValueAt(i)); 
/* 307 */         index++;
/*     */       } 
/* 309 */       return true;
/*     */     } 
/* 311 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTrimmedString(String s) {
/*     */     int index;
/* 317 */     if (s != null && (index = s.indexOf('\\')) >= 0) {
/*     */       
/* 319 */       StringBuilder buffer = new StringBuilder();
/* 320 */       if (index > 0)
/* 321 */         buffer.append(s.substring(0, index)); 
/* 322 */       int sLen = s.length();
/* 323 */       boolean prevSlash = true;
/* 324 */       for (int i = index + 1; i < sLen; i++) {
/*     */         
/* 326 */         char ch = s.charAt(i);
/* 327 */         switch (ch) {
/*     */           
/*     */           case '\\':
/* 330 */             if (!prevSlash) {
/*     */               
/* 332 */               prevSlash = true;
/*     */               break;
/*     */             } 
/*     */           
/*     */           case 't':
/* 337 */             if (prevSlash) {
/*     */               
/* 339 */               buffer.append('\t');
/* 340 */               prevSlash = false;
/*     */               break;
/*     */             } 
/*     */           case 'n':
/* 344 */             if (prevSlash) {
/*     */               
/* 346 */               buffer.append('\n');
/* 347 */               prevSlash = false;
/*     */               break;
/*     */             } 
/*     */           
/*     */           default:
/* 352 */             buffer.append(ch); break;
/*     */         } 
/* 354 */       }  if (prevSlash)
/* 355 */         buffer.append('\\'); 
/* 356 */       return buffer.toString();
/*     */     } 
/* 358 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringAsList(int listno, int tag) {
/* 363 */     String s = getString(listno, tag);
/* 364 */     if (s != null)
/* 365 */       return new JLbsStringList(s); 
/* 366 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearCache() {
/* 371 */     if (this.m_ResourceManager != null) {
/* 372 */       this.m_ResourceManager.clearCache();
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 377 */     return this.m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 382 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrencyBase(JLbsCurrenciesBase currencyBase) {
/* 387 */     this.m_CurrencyBase = currencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrenciesBase getCurrencyBase() {
/* 392 */     return this.m_CurrencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGlobalResources(JLbsStringList globalResources) {
/* 397 */     this.m_GlobalResources = globalResources;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\custom\JLbsCustomLocalizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */