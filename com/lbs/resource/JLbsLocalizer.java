/*     */ package com.lbs.resource;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.ILbsCultureResInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.util.ListResourceBundle;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
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
/*     */ public abstract class JLbsLocalizer
/*     */ {
/*     */   public static synchronized String getGlobalString(int tag) {
/*  27 */     if ((JLbsLocalizerInstanceHolder.getInstance()).ms_GlobalResources == null)
/*  28 */       (JLbsLocalizerInstanceHolder.getInstance()).ms_GlobalResources = getStringList(JLbsConstants.GLOBAL_RESOURCES); 
/*  29 */     if ((JLbsLocalizerInstanceHolder.getInstance()).ms_GlobalResources != null)
/*  30 */       return getTrimmedString((JLbsLocalizerInstanceHolder.getInstance()).ms_GlobalResources.getValueAtTag(tag)); 
/*  31 */     return null;
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
/*     */   public static String getString(int listNo, int tag) {
/*  43 */     JLbsStringList list = getStringList(listNo);
/*  44 */     if (list != null)
/*  45 */       return getTrimmedString(list.getValueAtTag(tag)); 
/*  46 */     return null;
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
/*     */   public static String getStringDef(int listNo, int tag, String defVal) {
/*  59 */     String s = getString(listNo, tag);
/*  60 */     return (s != null) ? 
/*  61 */       s : 
/*  62 */       defVal;
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
/*  74 */     if (list != null) {
/*     */       
/*  76 */       String s = getTrimmedString(list.getValueAtTag(tag));
/*  77 */       if (s != null)
/*  78 */         return s; 
/*     */     } 
/*  80 */     return defVal;
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
/*     */   public static String getStringByIndex(int listNo, int index) {
/*  92 */     JLbsStringList list = getStringList(listNo);
/*  93 */     if (list != null)
/*  94 */       return list.getValueAt(index); 
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStringByTag(int listNo, int tag) {
/* 106 */     JLbsStringList list = getStringList(listNo);
/* 107 */     if (list != null)
/* 108 */       return list.getValueAtTag(tag); 
/* 109 */     return null;
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
/*     */   public static String getStringByTagDef(int listNo, int tag, String defVal) {
/* 122 */     String s = getStringByTag(listNo, tag);
/* 123 */     return (s != null) ? 
/* 124 */       s : 
/* 125 */       defVal;
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
/*     */   public static String getStringByIndexDef(int listNo, int index, String defVal) {
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
/*     */   
/*     */   public static JLbsStringList getStringList(int listNo) {
/* 170 */     return getStringList("UN", listNo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringList(int listNo, int tag) {
/* 181 */     return getStringList("UN", listNo, tag);
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
/* 192 */     if (list != null && target != null) {
/*     */       
/* 194 */       JLbsStringList slist = new JLbsStringList(list);
/* 195 */       return localizeStringArrayRaw(slist, 0, target);
/*     */     } 
/* 197 */     return false;
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
/* 208 */     if (list != null && target != null) {
/*     */       
/* 210 */       JLbsStringList slist = new JLbsStringList(list);
/* 211 */       return localizeStringArray(slist, 0, target);
/*     */     } 
/* 213 */     return false;
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
/* 224 */     if (list != null && target != null) {
/*     */       
/* 226 */       JLbsStringList slist = new JLbsStringList(list);
/* 227 */       return localizeObjectArray(slist, 0, target);
/*     */     } 
/* 229 */     return false;
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
/* 241 */     if (list != null && target != null) {
/*     */       
/* 243 */       int index = 0;
/* 244 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 246 */         if (target[index] != null)
/* 247 */           target[index] = getTrimmedString(list.getValueAt(i)); 
/* 248 */         index++;
/*     */       } 
/* 250 */       return true;
/*     */     } 
/* 252 */     return false;
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
/* 264 */     if (list != null && target != null) {
/*     */       
/* 266 */       int index = 0;
/* 267 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 269 */         if (target[index] != null) {
/*     */           
/* 271 */           JLbsStringListItem itm = list.getItemAt(i);
/* 272 */           if (itm != null) {
/*     */             
/* 274 */             String s = getTrimmedString(itm.Value);
/* 275 */             if (itm.Tag != 0)
/* 276 */               s = String.valueOf(s) + "~" + itm.Tag; 
/* 277 */             target[index] = s;
/*     */           } 
/*     */         } 
/* 280 */         index++;
/*     */       } 
/* 282 */       return true;
/*     */     } 
/* 284 */     return false;
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
/* 296 */     if (list != null && target != null) {
/*     */       
/* 298 */       int index = 0;
/* 299 */       for (int i = startIndex; i < list.size() && index < target.length; i++) {
/*     */         
/* 301 */         if (target[index] != null)
/* 302 */           target[index] = getTrimmedString(list.getValueAt(i)); 
/* 303 */         index++;
/*     */       } 
/* 305 */       return true;
/*     */     } 
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTrimmedString(String s) {
/*     */     int index;
/* 313 */     if (s != null && (index = s.indexOf('\\')) >= 0) {
/*     */       
/* 315 */       StringBuilder buffer = new StringBuilder();
/* 316 */       if (index > 0)
/* 317 */         buffer.append(s.substring(0, index)); 
/* 318 */       int sLen = s.length();
/* 319 */       boolean prevSlash = true;
/* 320 */       for (int i = index + 1; i < sLen; i++) {
/*     */         
/* 322 */         char ch = s.charAt(i);
/* 323 */         switch (ch) {
/*     */           
/*     */           case '\\':
/* 326 */             if (!prevSlash) {
/*     */               
/* 328 */               prevSlash = true;
/*     */               break;
/*     */             } 
/*     */           
/*     */           case 't':
/* 333 */             if (prevSlash) {
/*     */               
/* 335 */               buffer.append('\t');
/* 336 */               prevSlash = false;
/*     */               break;
/*     */             } 
/*     */           case 'n':
/* 340 */             if (prevSlash) {
/*     */               
/* 342 */               buffer.append('\n');
/* 343 */               prevSlash = false;
/*     */               break;
/*     */             } 
/*     */           
/*     */           default:
/* 348 */             buffer.append(ch); break;
/*     */         } 
/* 350 */       }  if (prevSlash)
/* 351 */         buffer.append('\\'); 
/* 352 */       return buffer.toString();
/*     */     } 
/* 354 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringAsList(int listno, int tag) {
/* 365 */     String s = getString(listno, tag);
/* 366 */     if (s != null)
/* 367 */       return new JLbsStringList(s); 
/* 368 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsCultureInfo getCultureInfo() {
/* 373 */     return (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 378 */     (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsCultureResInfo getCultureResInfo() {
/* 383 */     return (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureResInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCultureInfoRes(ILbsCultureResInfo cultureInfoRes) {
/* 388 */     (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureResInfo = cultureInfoRes;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCurrencyBase(JLbsCurrenciesBase currencyBase) {
/* 393 */     (JLbsLocalizerInstanceHolder.getInstance()).ms_CurrencyBase = currencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsCurrenciesBase getCurrencyBase() {
/* 398 */     return (JLbsLocalizerInstanceHolder.getInstance()).ms_CurrencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setGlobalResources(JLbsStringList globalResources) {
/* 403 */     (JLbsLocalizerInstanceHolder.getInstance()).ms_GlobalResources = globalResources;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILocalizationServices getLocalizationService() {
/* 408 */     return (JLbsLocalizerInstanceHolder.getInstance()).ms_LocalizationService;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setLocalizationService(ILocalizationServices localizationServices) {
/* 413 */     synchronized (JLbsLocalizer.class) {
/*     */       
/* 415 */       (JLbsLocalizerInstanceHolder.getInstance()).ms_LocalizationService = localizationServices;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILocalizationServices getReportingLocalizationService() {
/* 421 */     return (JLbsLocalizerInstanceHolder.getInstance()).ms_ReportingLocalizationService;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setReportingLocalizationService(ILocalizationServices localizationServices) {
/* 426 */     synchronized (JLbsLocalizer.class) {
/*     */       
/* 428 */       (JLbsLocalizerInstanceHolder.getInstance()).ms_ReportingLocalizationService = localizationServices;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(int listID, int stringTag, boolean forReporting) {
/* 438 */     JLbsStringList list = getStringList(listID, forReporting);
/* 439 */     if (list != null)
/* 440 */       return getTrimmedString(list.getValueAtTag(stringTag)); 
/* 441 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringAsList(int listID, int stringTag, boolean forReporting) {
/* 446 */     String s = getString(listID, stringTag, forReporting);
/* 447 */     if (s != null)
/* 448 */       return new JLbsStringList(s); 
/* 449 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByIndex(int listID, int index, boolean forReporting) {
/* 454 */     JLbsStringList list = getStringList(listID, forReporting);
/* 455 */     if (list != null)
/* 456 */       return list.getValueAt(index); 
/* 457 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByIndexDef(int listID, int index, String defVal, boolean forReporting) {
/* 462 */     String s = getStringByIndex(listID, index, forReporting);
/* 463 */     return (s != null) ? 
/* 464 */       s : 
/* 465 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByTag(int listID, int stringTag, boolean forReporting) {
/* 470 */     JLbsStringList list = getStringList(listID, forReporting);
/* 471 */     if (list != null)
/* 472 */       return list.getValueAtTag(stringTag); 
/* 473 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByTagDef(int listID, int stringTag, String defVal, boolean forReporting) {
/* 478 */     String s = getStringByTag(listID, stringTag, forReporting);
/* 479 */     return (s != null) ? 
/* 480 */       s : 
/* 481 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringDef(int listID, int stringTag, String defVal, boolean forReporting) {
/* 486 */     String s = getString(listID, stringTag, forReporting);
/* 487 */     return (s != null) ? 
/* 488 */       s : 
/* 489 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringList(int listID, boolean forReporting) throws RuntimeException {
/* 494 */     return getStringListByGroup(listID, forReporting, "");
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringListByGroup(int listID, boolean forReporting, String group) throws RuntimeException {
/* 499 */     if (listID == 0) {
/* 500 */       return null;
/*     */     }
/* 502 */     ILocalizationServices lclService = forReporting ? 
/* 503 */       getReportingLocalizationService() : 
/* 504 */       getLocalizationService();
/* 505 */     if (lclService != null) {
/* 506 */       return lclService.getListByGroup(listID, group);
/*     */     }
/* 508 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringList(int listID, int stringTag, boolean forReporting) {
/* 513 */     JLbsStringList list = getStringList(listID, forReporting);
/* 514 */     if (list != null) {
/*     */       
/* 516 */       String s = list.getValueAtTag(stringTag);
/* 517 */       if (s != null)
/* 518 */         return new JLbsStringList(s); 
/*     */     } 
/* 520 */     return null;
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
/*     */   public static String getString(String group, int listID, int stringTag) {
/* 533 */     JLbsStringList list = getStringList(group, listID);
/* 534 */     if (list != null)
/* 535 */       return getTrimmedString(list.getValueAtTag(stringTag)); 
/* 536 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringAsList(String group, int listID, int stringTag) {
/* 541 */     String s = getString(group, listID, stringTag);
/* 542 */     if (s != null)
/* 543 */       return new JLbsStringList(s); 
/* 544 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByIndex(String group, int listID, int index) {
/* 549 */     JLbsStringList list = getStringList(group, listID);
/* 550 */     if (list != null)
/* 551 */       return list.getValueAt(index); 
/* 552 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByIndexDef(String group, int listID, int index, String defVal) {
/* 557 */     String s = getStringByIndex(group, listID, index);
/* 558 */     return (s != null) ? 
/* 559 */       s : 
/* 560 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByTag(String group, int listID, int stringTag) {
/* 565 */     JLbsStringList list = getStringList(group, listID);
/* 566 */     if (list != null)
/* 567 */       return list.getValueAtTag(stringTag); 
/* 568 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringByTagDef(String group, int listID, int stringTag, String defVal) {
/* 573 */     String s = getStringByTag(group, listID, stringTag);
/* 574 */     return (s != null) ? 
/* 575 */       s : 
/* 576 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStringDef(String group, int listID, int stringTag, String defVal) {
/* 581 */     String s = getString(group, listID, stringTag);
/* 582 */     return (s != null) ? 
/* 583 */       s : 
/* 584 */       defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringList(String group, int listID) {
/* 589 */     if (listID == 0) {
/* 590 */       return null;
/*     */     }
/* 592 */     ILocalizationServices lclService = getLocalizationService();
/* 593 */     if (lclService != null) {
/* 594 */       return lclService.getList(listID, group);
/*     */     }
/* 596 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getStringList(String group, int listID, int stringTag) {
/* 601 */     JLbsStringList list = getStringList(group, listID);
/* 602 */     if (list != null) {
/*     */       
/* 604 */       String s = list.getValueAtTag(stringTag);
/* 605 */       if (s != null)
/* 606 */         return new JLbsStringList(s); 
/*     */     } 
/* 608 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceBundle getStringListAsResBundle(String group, int listID) {
/* 613 */     if (listID == 0) {
/* 614 */       return null;
/*     */     }
/* 616 */     ILocalizationServices lclService = getLocalizationService();
/* 617 */     if (lclService != null) {
/*     */       
/* 619 */       final JLbsStringList list = lclService.getList(listID, group);
/*     */       
/* 621 */       if (list != null) {
/*     */         
/* 623 */         ResourceBundle bundle = new ListResourceBundle()
/*     */           {
/*     */             
/*     */             protected Object[][] getContents()
/*     */             {
/* 628 */               Object[][] content = new Object[list.size()][2];
/*     */               
/* 630 */               for (int i = 0; i < list.size(); i++) {
/*     */                 
/* 632 */                 JLbsStringListItem item = list.getItemAt(i);
/* 633 */                 StringBuilder sb = new StringBuilder();
/* 634 */                 sb.append(item.getTag());
/* 635 */                 sb.append("");
/* 636 */                 content[i][0] = sb.toString();
/* 637 */                 content[i][1] = item.getValue();
/*     */               } 
/* 639 */               return content;
/*     */             }
/*     */           };
/* 642 */         return bundle;
/*     */       } 
/*     */     } 
/* 645 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCultureResource(int resID) {
/* 650 */     if ((JLbsLocalizerInstanceHolder.getInstance()).ms_CultureResInfo != null)
/* 651 */       return (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureResInfo.getCultureResStr(resID); 
/* 652 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Locale getLocale() {
/* 657 */     if ((JLbsLocalizerInstanceHolder.getInstance()).ms_CultureInfo != null)
/* 658 */       return (JLbsLocalizerInstanceHolder.getInstance()).ms_CultureInfo.getLocale(); 
/* 659 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class JLbsLocalizerInstanceHolder
/*     */   {
/*     */     private ILocalizationServices ms_LocalizationService;
/*     */     
/*     */     private ILocalizationServices ms_ReportingLocalizationService;
/*     */     
/*     */     private ILbsCultureInfo ms_CultureInfo;
/*     */     
/*     */     private ILbsCultureResInfo ms_CultureResInfo;
/*     */     private JLbsCurrenciesBase ms_CurrencyBase;
/*     */     private JLbsStringList ms_GlobalResources;
/*     */     
/*     */     public static JLbsLocalizerInstanceHolder getInstance() {
/* 676 */       return (JLbsLocalizerInstanceHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsLocalizerInstanceHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\JLbsLocalizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */