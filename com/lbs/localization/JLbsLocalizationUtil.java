/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
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
/*     */ public class JLbsLocalizationUtil
/*     */   implements ILocalizationConstants
/*     */ {
/*     */   public static Hashtable<String, String> ms_LangMaps;
/*     */   public static Hashtable<String, Integer> ms_GrpMaps;
/*     */   public static Hashtable<Integer, String> ms_ReverseGrpMaps;
/*     */   public static Hashtable<String, String[]> ms_PrefLangMaps;
/*     */   public static Hashtable<String, LbsLocalizationLanguage> ms_LocalizationLanguages;
/*     */   
/*     */   static {
/*  27 */     if (ms_LocalizationLanguages == null) {
/*     */       
/*  29 */       ms_LocalizationLanguages = new Hashtable<>();
/*  30 */       ms_LocalizationLanguages.put("AREG", new LbsLocalizationLanguage("AREG", "", "RE_ARABICEG", 
/*  31 */             new String[] { "ARSA", "ENUS" }));
/*  32 */       ms_LocalizationLanguages.put("ARJO", new LbsLocalizationLanguage("ARJO", "", "RE_ARABICJO", 
/*  33 */             new String[] { "ARSA", "ENUS" }));
/*  34 */       ms_LocalizationLanguages.put("ARSA", new LbsLocalizationLanguage("ARSA", "", "RE_ARABICSA", 
/*  35 */             new String[] { "ARJO", "ENUS" }));
/*  36 */       ms_LocalizationLanguages.put("AZAZ", new LbsLocalizationLanguage("AZAZ", "", "RE_AZERBAIJANIAZ", 
/*  37 */             new String[] { "TRTR", "TKTM" }));
/*  38 */       ms_LocalizationLanguages.put("BGBG", new LbsLocalizationLanguage("BGBG", "", "RE_BULGARIANBG", 
/*  39 */             new String[] { "ENUS", "TRTR" }));
/*  40 */       ms_LocalizationLanguages.put("DEDE", new LbsLocalizationLanguage("DEDE", "", "RE_GERMANDE", 
/*  41 */             new String[] { "ENUS", "TRTR" }));
/*  42 */       ms_LocalizationLanguages.put("ENUS", new LbsLocalizationLanguage("ENUS", "", "RE_ENGLISHUS", 
/*  43 */             new String[] { "TRTR", "" }));
/*  44 */       ms_LocalizationLanguages.put("FAIR", new LbsLocalizationLanguage("FAIR", "", "RE_PERSIANIR", 
/*  45 */             new String[] { "ENUS", "TRTR" }));
/*  46 */       ms_LocalizationLanguages.put("FRFR", new LbsLocalizationLanguage("FRFR", "", "RE_FRENCHFR", 
/*  47 */             new String[] { "ENUS", "TRTR" }));
/*  48 */       ms_LocalizationLanguages.put("KAGE", new LbsLocalizationLanguage("KAGE", "", "RE_GEORGIANGE", 
/*  49 */             new String[] { "ENUS", "TRTR" }));
/*  50 */       ms_LocalizationLanguages.put("RURU", new LbsLocalizationLanguage("RURU", "", "RE_RUSSIANRU", 
/*  51 */             new String[] { "ENUS", "TRTR" }));
/*  52 */       ms_LocalizationLanguages.put("SQAL", new LbsLocalizationLanguage("SQAL", "", "RE_ALBANIANKV", 
/*  53 */             new String[] { "TRTR", "ENUS" }));
/*  54 */       ms_LocalizationLanguages.put("THTH", new LbsLocalizationLanguage("THTH", "", "RE_THAITH", 
/*  55 */             new String[] { "ENUS", "TRTR" }));
/*  56 */       ms_LocalizationLanguages.put("TKTM", new LbsLocalizationLanguage("TKTM", "", "RE_TURKMENTM", 
/*  57 */             new String[] { "TRTR", "AZAZ" }));
/*  58 */       ms_LocalizationLanguages.put("TRTR", new LbsLocalizationLanguage("TRTR", "", "RE_TURKISHTR", 
/*  59 */             new String[] { "ENUS", "AZAZ" }));
/*  60 */       ms_LocalizationLanguages.put("VIVN", new LbsLocalizationLanguage("VIVN", "", "RE_ENGLISHUS", 
/*  61 */             new String[] { "ZHCN", "TRTR" }));
/*  62 */       ms_LocalizationLanguages.put("ZHCN", new LbsLocalizationLanguage("ZHCN", "", "RE_CHINESECN", 
/*  63 */             new String[] { "ENUS", "TRTR" }));
/*  64 */       ms_LocalizationLanguages.put("", new LbsLocalizationLanguage("", "", "", 
/*  65 */             new String[] { "ENUS", "TRTR" }));
/*  66 */       ms_LocalizationLanguages.put("ENIN", new LbsLocalizationLanguage("ENIN", "", "ENUS", 
/*  67 */             new String[] { "TRTR" }));
/*     */     } 
/*     */     
/*  70 */     if (ms_LangMaps == null) {
/*     */       
/*  72 */       ms_LangMaps = new Hashtable<>();
/*  73 */       ms_LangMaps.put("AREG", "RE_ARABICEG");
/*  74 */       ms_LangMaps.put("ARJO", "RE_ARABICJO");
/*  75 */       ms_LangMaps.put("ARSA", "RE_ARABICSA");
/*  76 */       ms_LangMaps.put("AZAZ", "RE_AZERBAIJANIAZ");
/*  77 */       ms_LangMaps.put("BGBG", "RE_BULGARIANBG");
/*  78 */       ms_LangMaps.put("DEDE", "RE_GERMANDE");
/*  79 */       ms_LangMaps.put("ENUS", "RE_ENGLISHUS");
/*  80 */       ms_LangMaps.put("FAIR", "RE_PERSIANIR");
/*  81 */       ms_LangMaps.put("FRFR", "RE_FRENCHFR");
/*  82 */       ms_LangMaps.put("KAGE", "RE_GEORGIANGE");
/*  83 */       ms_LangMaps.put("RURU", "RE_RUSSIANRU");
/*  84 */       ms_LangMaps.put("SQAL", "RE_ALBANIANKV");
/*  85 */       ms_LangMaps.put("THTH", "RE_THAITH");
/*  86 */       ms_LangMaps.put("TKTM", "RE_TURKMENTM");
/*  87 */       ms_LangMaps.put("TRTR", "RE_TURKISHTR");
/*  88 */       ms_LangMaps.put("VIVN", "RE_ENGLISHUS");
/*  89 */       ms_LangMaps.put("ZHCN", "RE_CHINESECN");
/*  90 */       ms_LangMaps.put("ENIN", "RE_ENGLISHUS");
/*     */     } 
/*     */     
/*  93 */     if (ms_GrpMaps == null) {
/*     */       
/*  95 */       ms_GrpMaps = new Hashtable<>();
/*  96 */       ms_GrpMaps.put("UN", Integer.valueOf(1));
/*  97 */       ms_GrpMaps.put("UNRP", Integer.valueOf(2));
/*  98 */       ms_GrpMaps.put("HR", Integer.valueOf(3));
/*  99 */       ms_GrpMaps.put("HRRP", Integer.valueOf(4));
/* 100 */       ms_GrpMaps.put("HELP", Integer.valueOf(5));
/* 101 */       ms_GrpMaps.put("SS", Integer.valueOf(6));
/*     */     } 
/*     */     
/* 104 */     if (ms_ReverseGrpMaps == null) {
/*     */       
/* 106 */       ms_ReverseGrpMaps = new Hashtable<>();
/* 107 */       ms_ReverseGrpMaps.put(Integer.valueOf(1), "UN");
/* 108 */       ms_ReverseGrpMaps.put(Integer.valueOf(2), "UNRP");
/* 109 */       ms_ReverseGrpMaps.put(Integer.valueOf(3), "HR");
/* 110 */       ms_ReverseGrpMaps.put(Integer.valueOf(4), "HRRP");
/* 111 */       ms_ReverseGrpMaps.put(Integer.valueOf(5), "HELP");
/* 112 */       ms_ReverseGrpMaps.put(Integer.valueOf(6), "SS");
/*     */     } 
/*     */     
/* 115 */     extractPrefLangMaps();
/* 116 */     extractLangMaps();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void touch() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static void extractPrefLangMaps() {
/* 126 */     ArrayList<LbsLocalizationLanguage> values = new ArrayList<>(ms_LocalizationLanguages.values());
/*     */     
/* 128 */     if (values != null) {
/*     */       
/* 130 */       if (ms_PrefLangMaps == null) {
/* 131 */         ms_PrefLangMaps = (Hashtable)new Hashtable<>();
/*     */       }
/* 133 */       for (int i = 0; i < values.size(); i++) {
/*     */         
/* 135 */         LbsLocalizationLanguage value = values.get(i);
/* 136 */         ArrayList<String> prefLangs = value.getPreferredLanguages();
/* 137 */         String[] prefLangTableNames = new String[2];
/* 138 */         for (int j = 0; j < prefLangs.size(); j++) {
/*     */           
/* 140 */           String prefLang = prefLangs.get(j);
/* 141 */           LbsLocalizationLanguage localizationLang = ms_LocalizationLanguages.get(prefLang);
/* 142 */           if (localizationLang != null && !"".equals(localizationLang.getDatabaseTableName())) {
/* 143 */             prefLangTableNames[j] = localizationLang.getDatabaseTableName();
/*     */           } else {
/* 145 */             prefLangTableNames[j] = TABLE_NAME_NOSELECT;
/*     */           } 
/* 147 */         }  if (!ms_PrefLangMaps.containsKey(value.getDatabaseTableName())) {
/* 148 */           ms_PrefLangMaps.put(value.getDatabaseTableName(), prefLangTableNames);
/*     */         } else {
/* 150 */           ms_PrefLangMaps.put(String.valueOf(value.getDatabaseTableName()) + "_" + value.getName(), prefLangTableNames);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void extractLangMaps() {
/* 157 */     if (ms_LocalizationLanguages != null) {
/*     */       
/* 159 */       ArrayList<LbsLocalizationLanguage> values = new ArrayList<>(ms_LocalizationLanguages.values());
/* 160 */       if (values != null)
/*     */       {
/* 162 */         for (int i = 0; i < values.size(); i++) {
/*     */           
/* 164 */           LbsLocalizationLanguage value = values.get(i);
/* 165 */           ms_LangMaps.put(value.getName(), value.getDatabaseTableName());
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDBResNumber(int listID, String resGroup) {
/* 173 */     return getDBResNumber(listID, getResGroupBase(resGroup));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDBResNumber(int listID, int resGroupBase) {
/* 178 */     return (listID > 0) ? (
/* 179 */       listID + resGroupBase) : (
/* 180 */       listID - resGroupBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getListID(int dbResNumber, String resGroup) {
/* 185 */     return getListID(dbResNumber, getResGroupBase(resGroup));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getListID(int dbResNumber, int resGroupBase) {
/* 190 */     return (dbResNumber > 0) ? (
/* 191 */       dbResNumber - resGroupBase) : (
/* 192 */       dbResNumber + resGroupBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getResGroupBase(String resGroupStr) {
/* 197 */     Object objResGrp = ms_GrpMaps.get(resGroupStr);
/* 198 */     int groupBase = 0;
/*     */     
/* 200 */     if (objResGrp != null && objResGrp instanceof Integer) {
/* 201 */       groupBase = ((Integer)objResGrp).intValue();
/*     */     }
/* 203 */     return groupBase * 1000000;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getResGroup(int dbResNumber) {
/* 208 */     int base = dbResNumber / 1000000;
/* 209 */     base = Math.abs(base);
/* 210 */     Object group = ms_ReverseGrpMaps.get(Integer.valueOf(base));
/*     */     
/* 212 */     if (group instanceof String)
/* 213 */       return (String)group; 
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getResourceItem(ILocalizationServices localService, int listId, int stringTag, String defaultValue) {
/* 219 */     String resource = localService.getItem(listId, stringTag);
/* 220 */     if (JLbsStringUtil.isEmpty(resource))
/* 221 */       resource = defaultValue; 
/* 222 */     return resource;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\JLbsLocalizationUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */