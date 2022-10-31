/*     */ package com.lbs.interfaces;
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
/*     */ public interface ILbsDisplayFormat
/*     */ {
/*     */   public static final int ATTR_DISPLAYZERO = 1;
/*     */   public static final int ATTR_FORCEFRACTIONS = 2;
/*     */   public static final int ATTR_STRIPSPACES = 4;
/*     */   public static final int ATTR_STRINGRESOURCE = 8;
/*     */   public static final int ATTR_DISPDATETIME = 16;
/*     */   public static final int ATTR_DISPTIMEONLY = 32;
/*     */   public static final int CT_CONTEXT0 = -6;
/*     */   public static final int CT_CONTEXT1 = -5;
/*     */   public static final int CT_CONTEXT2 = -4;
/*     */   public static final int CT_CONTEXT3 = -3;
/*     */   public static final int CT_CONTEXT4 = -2;
/*     */   public static final int CT_CONTEXT5 = -1;
/*     */   public static final int CT_NONE = 0;
/*     */   public static final int CT_USD = 1;
/*     */   public static final int CT_DEM = 2;
/*     */   public static final int CT_AUD = 3;
/*     */   public static final int CT_ATS = 4;
/*     */   public static final int CT_BEF = 5;
/*     */   public static final int CT_DKK = 6;
/*     */   public static final int CT_FIM = 7;
/*     */   public static final int CT_FRF = 8;
/*     */   public static final int CT_NLG = 9;
/*     */   public static final int CT_SEK = 10;
/*     */   public static final int CT_CHF = 11;
/*     */   public static final int CT_ITL = 12;
/*     */   public static final int CT_JPY = 13;
/*     */   public static final int CT_CAD = 14;
/*     */   public static final int CT_KWD = 15;
/*     */   public static final int CT_NOK = 16;
/*     */   public static final int CT_GBP = 17;
/*     */   public static final int CT_SAR = 18;
/*     */   public static final int CT_XEU = 19;
/*     */   public static final int CT_EUR = 20;
/*     */   public static final int CT_AZM = 21;
/*     */   public static final int CT_BRL = 22;
/*     */   public static final int CT_BGL = 23;
/*     */   public static final int CT_CZK = 24;
/*     */   public static final int CT_CNY = 25;
/*     */   public static final int CT_EEK = 26;
/*     */   public static final int CT_GEL = 27;
/*     */   public static final int CT_INR = 28;
/*     */   public static final int CT_HKD = 29;
/*     */   public static final int CT_IQD = 30;
/*     */   public static final int CT_IRR = 31;
/*     */   public static final int CT_IEP = 32;
/*     */   public static final int CT_ESP = 33;
/*     */   public static final int CT_ILS = 34;
/*     */   public static final int CT_ISK = 35;
/*     */   public static final int CT_CYP = 36;
/*     */   public static final int CT_KGS = 37;
/*     */   public static final int CT_LVL = 38;
/*     */   public static final int CT_LYD = 39;
/*     */   public static final int CT_LBP = 40;
/*     */   public static final int CT_LTL = 41;
/*     */   public static final int CT_LUF = 42;
/*     */   public static final int CT_HUF = 43;
/*     */   public static final int CT_MYR = 44;
/*     */   public static final int CT_MXN = 45;
/*     */   public static final int CT_EGP = 46;
/*     */   public static final int CT_BBD = 47;
/*     */   public static final int CT_PLN = 48;
/*     */   public static final int CT_PTE = 49;
/*     */   public static final int CT_ROL = 50;
/*     */   public static final int CT_RUR = 51;
/*     */   public static final int CT_TWD = 52;
/*     */   public static final int CT_TRL = 53;
/*     */   public static final int CT_JOD = 54;
/*     */   public static final int CT_GRD = 55;
/*     */   public static final int CT_ARP = 56;
/*     */   public static final int CT_LAK = 57;
/*     */   public static final int CT_ADP = 58;
/*     */   public static final int CT_AED = 59;
/*     */   public static final int CT_AFA = 60;
/*     */   public static final int CT_ALL = 61;
/*     */   public static final int CT_ANG = 62;
/*     */   public static final int CT_AON = 63;
/*     */   public static final int CT_BDT = 64;
/*     */   public static final int CT_BHD = 65;
/*     */   public static final int CT_BIF = 66;
/*     */   public static final int CT_BMD = 67;
/*     */   public static final int CT_BND = 68;
/*     */   public static final int CT_BOB = 69;
/*     */   public static final int CT_BSD = 70;
/*     */   public static final int CT_BTN = 71;
/*     */   public static final int CT_BWP = 72;
/*     */   public static final int CT_BZD = 73;
/*     */   public static final int CT_CLP = 74;
/*     */   public static final int CT_COP = 75;
/*     */   public static final int CT_CRC = 76;
/*     */   public static final int CT_CUP = 77;
/*     */   public static final int CT_CVE = 78;
/*     */   public static final int CT_DJF = 79;
/*     */   public static final int CT_DOP = 80;
/*     */   public static final int CT_DZD = 81;
/*     */   public static final int CT_ECS = 82;
/*     */   public static final int CT_ETB = 83;
/*     */   public static final int CT_FJD = 84;
/*     */   public static final int CT_FKP = 85;
/*     */   public static final int CT_GHC = 86;
/*     */   public static final int CT_GIP = 87;
/*     */   public static final int CT_GMD = 88;
/*     */   public static final int CT_GNF = 89;
/*     */   public static final int CT_GTQ = 90;
/*     */   public static final int CT_GWP = 91;
/*     */   public static final int CT_GYD = 92;
/*     */   public static final int CT_HNL = 93;
/*     */   public static final int CT_HTG = 94;
/*     */   public static final int CT_IDR = 95;
/*     */   public static final int CT_JMD = 96;
/*     */   public static final int CT_KES = 97;
/*     */   public static final int CT_KHR = 98;
/*     */   public static final int CT_KMF = 99;
/*     */   public static final int CT_KPW = 100;
/*     */   public static final int CT_KRW = 101;
/*     */   public static final int CT_KYD = 102;
/*     */   public static final int CT_LKR = 103;
/*     */   public static final int CT_LRD = 104;
/*     */   public static final int CT_LSL = 105;
/*     */   public static final int CT_MAD = 106;
/*     */   public static final int CT_MNT = 107;
/*     */   public static final int CT_MOP = 108;
/*     */   public static final int CT_MRO = 109;
/*     */   public static final int CT_MTL = 110;
/*     */   public static final int CT_MUR = 111;
/*     */   public static final int CT_MVR = 112;
/*     */   public static final int CT_MWK = 113;
/*     */   public static final int CT_MZM = 114;
/*     */   public static final int CT_NGN = 115;
/*     */   public static final int CT_NIO = 116;
/*     */   public static final int CT_NPR = 117;
/*     */   public static final int CT_NZD = 118;
/*     */   public static final int CT_OMR = 119;
/*     */   public static final int CT_PAB = 120;
/*     */   public static final int CT_PEN = 121;
/*     */   public static final int CT_PGK = 122;
/*     */   public static final int CT_PHP = 123;
/*     */   public static final int CT_PKR = 124;
/*     */   public static final int CT_PYG = 125;
/*     */   public static final int CT_QAR = 126;
/*     */   public static final int CT_RWF = 127;
/*     */   public static final int CT_SBD = 128;
/*     */   public static final int CT_SCR = 129;
/*     */   public static final int CT_SDD = 130;
/*     */   public static final int CT_SGD = 131;
/*     */   public static final int CT_SHP = 132;
/*     */   public static final int CT_SLL = 133;
/*     */   public static final int CT_SOS = 134;
/*     */   public static final int CT_SRG = 135;
/*     */   public static final int CT_STD = 136;
/*     */   public static final int CT_SVC = 137;
/*     */   public static final int CT_SYP = 138;
/*     */   public static final int CT_SZL = 139;
/*     */   public static final int CT_THB = 140;
/*     */   public static final int CT_TND = 141;
/*     */   public static final int CT_TPE = 142;
/*     */   public static final int CT_TTD = 143;
/*     */   public static final int CT_TZS = 144;
/*     */   public static final int CT_UGX = 145;
/*     */   public static final int CT_UYU = 146;
/*     */   public static final int CT_VEB = 147;
/*     */   public static final int CT_VND = 148;
/*     */   public static final int CT_WST = 149;
/*     */   public static final int CT_YDD = 150;
/*     */   public static final int CT_YER = 151;
/*     */   public static final int CT_YUD = 152;
/*     */   public static final int CT_ZAR = 153;
/*     */   public static final int CT_ZMK = 154;
/*     */   public static final int CT_ZWD = 155;
/*     */   public static final int CT_KZT = 156;
/*     */   public static final int CT_UAH = 157;
/*     */   public static final int CT_TMM = 158;
/*     */   public static final int CT_UZS = 159;
/*     */   public static final int DATE_GREGORIAN = 0;
/*     */   public static final int DATE_PERSIAN = 1;
/*     */   public static final int DATE_FIRM = 2;
/*     */   public static final int FMT_Default = 0;
/*     */   public static final int FMT_10_31_91 = 1;
/*     */   public static final int FMT_31_10_91 = 2;
/*     */   public static final int FMT_91_10_31 = 3;
/*     */   public static final int FMT_10_31_1991 = 4;
/*     */   public static final int FMT_31_10_1991 = 5;
/*     */   public static final int FMT_1991_10_31 = 6;
/*     */   public static final int FMT_31_Eyl_91 = 7;
/*     */   public static final int FMT_31_Eylul_1991 = 8;
/*     */   public static final int FMT_Eyl_31_1991 = 9;
/*     */   public static final int FMT_Eylul_31_1991 = 10;
/*     */   public static final int FMT_Per_Eyl_31_1991 = 11;
/*     */   public static final int FMT_Persembe_Eylul_31_1991 = 12;
/*     */   public static final int FMT_Persembe_31_Eylul_1991 = 13;
/*     */   public static final int FMT_31_Eylul_1991_Persembe = 14;
/*     */   public static final int FMT_31_Eyl_1991 = 15;
/*     */   public static final int FMT_10_1991 = 16;
/*     */   public static final int FMT_10_91 = 17;
/*     */   public static final int FMT_Eylul_1991 = 18;
/*     */   public static final int FMT_Eylul_91 = 19;
/*     */   public static final int FMT_1991 = 20;
/*     */   public static final int FMT_TIME_Default = 21;
/*     */   public static final int FMT_TIME_16_46 = 22;
/*     */   public static final int FMT_TIME_16_46_58 = 23;
/*     */   public static final int FMT_TIME_16_46_58_99 = 24;
/*     */   public static final int FMT_TIME_10_46_PM = 25;
/*     */   public static final int FMT_TIME_10_46_58_PM = 26;
/* 229 */   public static final String[] FMT_FormatStrings = new String[] { "dd/MM/yyyy", "dd/MM/yy", "MM/dd/yy", "yy/MM/dd", "MM/dd/yyyy", 
/* 230 */       "dd/MM/yyyy", "yyyy/MM/dd", "dd MMM yy", "dd MMMM yyyy", "MMM dd yyyy", "MMMM dd yyyy", "EE, MMM dd yyyy", 
/* 231 */       "EEEE, MMMM dd yyyy", "EEEE, dd MMMM yyyy", "dd MMMM yyyy, EEEE", "dd MMM yyyy", "MM/yyyy", "MM/yy", "MMMM yyyy", 
/* 232 */       "MMMM yy", "yyyy", "HH:mm:ss", "HH:mm", "HH:mm:ss", "hh:mm:ss:SS", "hh:mm K", "hh:mm:ss K" };
/*     */ 
/*     */   
/* 235 */   public static final String[] FMT_EditFormatStrings = new String[] { "dd/MM/yyyy", "dd/MM/yy", "MM/dd/yy", "yy/MM/dd", "MM/dd/yyyy", 
/* 236 */       "dd/MM/yyyy", "yyyy/MM/dd", "dd/MM/yy", "dd/MM/yyyy", "MM/dd/yyyy", "MM/dd/yyyy", "MM/dd/yyyy", "MM/dd/yyyy", 
/* 237 */       "dd/MM/yyyy", "dd/MM/yyyy", "dd/MM/yyyy", "MM/yyyy", "MM/yy", "MM/yyyy", "MM/yy", "yyyy" };
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsDisplayFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */