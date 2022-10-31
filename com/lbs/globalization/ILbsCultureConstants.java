/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import java.util.Locale;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ILbsCultureConstants
/*    */ {
/* 16 */   public static final String[] DEFAULTFORMATS = new String[] { ",##0.##", 
/* 17 */       "%", 
/* 18 */       "$", 
/* 19 */       "DB", 
/* 20 */       "CR", 
/* 21 */       "dd/MM/yyyy", 
/* 22 */       "HH:mm:ss", 
/* 23 */       "AD", "BC", 
/* 24 */       "PM", "AM" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public static final Locale[] LANGUAGELOCALES = new Locale[] { Locale.US, new Locale("tr", "TR"), Locale.US, Locale.FRANCE, Locale.GERMANY, 
/* 31 */       new Locale("ru", "RU"), Locale.ITALY, new Locale("es", "ES"), new Locale("pl", "PL"), new Locale("cs", "CZ"), 
/* 32 */       new Locale("da", "DK"), new Locale("nl", "NL"), new Locale("et", "EE"), new Locale("fi", "FI"), new Locale("iw", "IL"), 
/* 33 */       new Locale("hu", "HU"), new Locale("is", "IS"), Locale.CHINA, Locale.JAPANESE, Locale.KOREAN, new Locale("no", "NO"), 
/* 34 */       new Locale("pt", "PT"), new Locale("sv", "SE"), new Locale("el", "GR"), new Locale("ar", "SA"), new Locale("ro", "RO"), 
/* 35 */       new Locale("sl", "SI"), new Locale("hr", "HR"), new Locale("bg", "BG"), new Locale("az", "AZ"), new Locale("fa", "IR"), 
/* 36 */       new Locale("tr", "TR"), new Locale("ar", "JO"), new Locale("tk", "TM"), new Locale("sq", "AL"), new Locale("th", "THA"), 
/* 37 */       new Locale("vi", "VN"), new Locale("ka", "GE"), new Locale("ar", "EG"), Locale.US, new Locale("en", "IN") };
/*    */   
/* 39 */   public static final String[] LANGUAGEPREFIXES = new String[] { "ENUS", "TRTR", "ENUS", "FRFR", "DEDE", "RURU", "ITIT", "ESES", "PLPL", 
/* 40 */       "CSCZ", "DADK", "NLNL", "ETEE", "FIFI", "IWIL", "HUHU", "ISIS", "ZHCN", "JAJP", "KOKR", "NONO", "PTPT", "SVSE", "ELGR", 
/* 41 */       "ARSA", "RORO", "SLSI", "HRHR", "BGBG", "AZAZ", "FAIR", "TRTR", "ARJO", "TKTM", "SQAL", "THTH", "VIVN", "KAGE", "AREG", "NEUT", "ENIN" };
/*    */   
/* 43 */   public static final String[] LANGUAGENAMES = new String[] { "Default (ENUS)", "Turkish (TRTR)", "English (ENUS)", "French (FR)", 
/* 44 */       "German (DEDE)", "Russian (RU)", "Italian (IT)", "Spanish (ES)", "Polish (PL)", "Czech (CZ)", "Danish (DADK)", 
/* 45 */       "Dutch (NLNL)", "Estonian (ETEE)", "Finnish (FI)", "Hebrew (IWIL)", "Hungarian (HU)", "Icelandic (IS)", 
/* 46 */       "Chinese (ZHCN)", "Japanese (JAJP)", "Korean (KOKR)", "Norwegian (NO)", "Portuguese (PT)", "Swedish (SVSE)", 
/* 47 */       "Greek (ELGR)", "Arabic (ARSA)", "Romanian (RO)", "Slovenian (SLSI)", "Croatian (HR)", "Bulgarian (BG)", 
/* 48 */       "Azerbaijani (AZ)", "Farsi (FAIR)", "Turkish (TRTR)", "Arabic (ARJO)", "Turkic (TKTM)", "Albanian (ALKV)", 
/* 49 */       "Thai (TH)", "Vietnamiese (VIVN)", "Georgian (KAGE)", "Arabic (AREG)", "(NEUT)", "English (ENIN)" };
/*    */   public static final int LNG_TURKISH_TR = 1;
/*    */   public static final int LNG_ENGLISH_US = 2;
/*    */   public static final int LNG_FRENCH_FR = 3;
/*    */   public static final int LNG_GERMAN_DE = 4;
/*    */   public static final int LNG_RUSSIAN_RU = 5;
/*    */   public static final int LNG_ITALIAN_IT = 6;
/*    */   public static final int LNG_SPANISH_ES = 7;
/*    */   public static final int LNG_POLISH_PL = 8;
/*    */   public static final int LNG_CZECH_CZ = 9;
/*    */   public static final int LNG_DANISH_DK = 10;
/*    */   public static final int LNG_DUTCH_NL = 11;
/*    */   public static final int LNG_ESTONIAN_EE = 12;
/*    */   public static final int LNG_FINNISH_FI = 13;
/*    */   public static final int LNG_HEBREW_IL = 14;
/*    */   public static final int LNG_HUNGARIAN_HU = 15;
/*    */   public static final int LNG_ICELANDIC_IS = 16;
/*    */   public static final int LNG_CHINESE_CN = 17;
/*    */   public static final int LNG_JAPANESE_JP = 18;
/*    */   public static final int LNG_KOREAN_KR = 19;
/*    */   public static final int LNG_NORWEGIAN_NO = 20;
/*    */   public static final int LNG_PORTUGUESE_PT = 21;
/*    */   public static final int LNG_SWEDISH_SE = 22;
/*    */   public static final int LNG_GREEK_GR = 23;
/*    */   public static final int LNG_ARABIC_SA = 24;
/*    */   public static final int LNG_ROMANIAN_RO = 25;
/*    */   public static final int LNG_SLOVENIAN_SI = 26;
/*    */   public static final int LNG_CROATIAN_HR = 27;
/*    */   public static final int LNG_BULGARIAN_BG = 28;
/*    */   public static final int LNG_AZERBAIJANI_AZ = 29;
/*    */   public static final int LNG_FARSI_IR = 30;
/*    */   public static final int LNG_TURKISH_RL = 31;
/*    */   public static final int LNG_ARABIC_JO = 32;
/*    */   public static final int LNG_TURKIC_TM = 33;
/*    */   public static final int LNG_ALBANIAN_AL = 34;
/*    */   public static final int LNG_THAI_TH = 35;
/*    */   public static final int LNG_VIETNAMIESE_VN = 36;
/*    */   public static final int LNG_GEORGIAN_GE = 37;
/*    */   public static final int LNG_ARABIC_EG = 38;
/*    */   public static final int LNG_NEUT = 39;
/*    */   public static final int LNG_ENGLISH_IN = 40;
/*    */   public static final int TRO_LEFTTORIGHT = 0;
/*    */   public static final int TRO_RIGHTTOLEFT = 1;
/*    */   public static final int CAL_GREGORIAN = 0;
/*    */   public static final int CAL_PERSIAN = 1;
/*    */   public static final int CAL_ARABIC = 2;
/*    */   public static final int CAL_BUDDHIST = 3;
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\ILbsCultureConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */