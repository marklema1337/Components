/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
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
/*     */ public class JLbsCurrenciesBase
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1456275220823848953L;
/*     */   protected ArrayList m_Currencies;
/*     */   private JLbsCurrencyInfo m_defaultCurrency;
/*  25 */   private JLbsCurrencyInfo[] m_contextCurrs = new JLbsCurrencyInfo[5];
/*  26 */   private int m_RateDecimals = 5;
/*     */   
/*  28 */   public static final BigDecimal ONE = new BigDecimal(1.0D);
/*  29 */   public static final BigDecimal EPSILON = new BigDecimal(1.0E-10D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCurrenciesBase() {
/*  39 */     this.m_Currencies = new ArrayList();
/*     */     
/*  41 */     createCurrencyTable();
/*     */     
/*  43 */     this.m_defaultCurrency = getCurrencyInfo("USD");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getCurrencies() {
/*  49 */     return this.m_Currencies;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyCount() {
/*  54 */     return this.m_Currencies.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createCurrencyTable() {
/*  62 */     addCurrency(1, "USD", 2, 1.0D, false, 0.0D, 1, "$", "cent", 2, false, false);
/*  63 */     addCurrency(2, "DEM", 2, 1.0D, true, 1.95583D, 1, "DM", "Pfennig", 2, false, false);
/*  64 */     addCurrency(3, "AUD", 2, 1.0D, false, 0.0D, 1, "", "cent", 2, false, false);
/*  65 */     addCurrency(4, "ATS", 2, 1.0D, true, 13.7603D, 1, "", "Groschen", 2, false, false);
/*  66 */     addCurrency(5, "BEF", 2, 1.0D, true, 40.3399D, 50, "BF", "centimes", 1, false, false);
/*  67 */     addCurrency(6, "DKK", 2, 1.0D, false, 0.0D, 1, "DK", "øre", 2, false, false);
/*  68 */     addCurrency(7, "FIM", 2, 1.0D, true, 5.94573D, 1, "", "penni", 2, false, false);
/*  69 */     addCurrency(8, "FRF", 2, 1.0D, true, 6.55957D, 1, "FF", "centimes", 2, false, false);
/*  70 */     addCurrency(9, "NLG", 2, 1.0D, true, 2.20371D, 1, "NG", "cent", 2, false, false);
/*  71 */     addCurrency(10, "SEK", 2, 1.0D, false, 0.0D, 1, "SK", "öre", 2, false, false);
/*  72 */     addCurrency(11, "CHF", 2, 1.0D, false, 0.0D, 1, "SF", "", 2, false, false);
/*  73 */     addCurrency(12, "ITL", 0, 100.0D, true, 1936.27D, 0, "IL", "", 1, false, false);
/*  74 */     addCurrency(13, "JPY", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  75 */     addCurrency(14, "CAD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  76 */     addCurrency(15, "KWD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  77 */     addCurrency(16, "NOK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  78 */     addCurrency(17, "GBP", 2, 1.0D, false, 0.0D, 1, "£", "penny", 2, false, false);
/*  79 */     addCurrency(18, "SAR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  80 */     addCurrency(19, "XEU", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  81 */     addCurrency(20, "EUR", 2, 1.0D, false, 0.0D, 1, "Eur", "cent", 2, false, false);
/*  82 */     addCurrency(21, "AZM", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  83 */     addCurrency(22, "BRL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  84 */     addCurrency(23, "BLV", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  85 */     addCurrency(24, "CZK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  86 */     addCurrency(25, "CNY", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  87 */     addCurrency(26, "EKR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  88 */     addCurrency(27, "GMS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  89 */     addCurrency(28, "INR", 2, 1.0D, false, 0.0D, 1, "Rupees", "paise", 2, false, false);
/*  90 */     addCurrency(29, "HKD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  91 */     addCurrency(30, "IQD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  92 */     addCurrency(31, "IRR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  93 */     addCurrency(32, "IEP", 2, 1.0D, true, 0.787564D, 1, "", "penny", 2, false, false);
/*  94 */     addCurrency(33, "ESP", 2, 1.0D, true, 166.386D, 0, "", "", 2, false, false);
/*  95 */     addCurrency(34, "ILS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  96 */     addCurrency(35, "ISK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  97 */     addCurrency(36, "CYP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  98 */     addCurrency(37, "KGS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*  99 */     addCurrency(38, "LTL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 100 */     addCurrency(39, "LBD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 101 */     addCurrency(40, "LBP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 102 */     addCurrency(41, "LVL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 103 */     addCurrency(42, "LXF", 2, 1.0D, true, 40.3399D, 50, "", "centimes", 1, false, false);
/* 104 */     addCurrency(43, "HNL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 105 */     addCurrency(44, "MYR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 106 */     addCurrency(45, "MXP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 107 */     addCurrency(46, "EGP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 108 */     addCurrency(47, "BBD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 109 */     addCurrency(48, "PLN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 110 */     addCurrency(49, "PTE", 2, 1.0D, true, 200.482D, 10, "", "centaro", 2, false, false);
/* 111 */     addCurrency(50, "RML", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 112 */     addCurrency(51, "RUB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 113 */     addCurrency(52, "TWD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*     */     
/* 115 */     addCurrency(54, "JOD", 3, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 116 */     addCurrency(55, "GRD", 2, 1.0D, false, 0.0D, 0, "", "", 2, false, false);
/* 117 */     addCurrency(56, "ARA", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 118 */     addCurrency(57, "LAK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 119 */     addCurrency(58, "ADP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 120 */     addCurrency(59, "AED", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 121 */     addCurrency(60, "AFA", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 122 */     addCurrency(61, "ALL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 123 */     addCurrency(62, "ANG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 124 */     addCurrency(63, "AON", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 125 */     addCurrency(64, "BDT", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 126 */     addCurrency(65, "BHD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 127 */     addCurrency(66, "BIF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 128 */     addCurrency(67, "BMD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 129 */     addCurrency(68, "BND", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 130 */     addCurrency(69, "BOB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 131 */     addCurrency(70, "BSD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 132 */     addCurrency(71, "BTN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 133 */     addCurrency(72, "BWP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 134 */     addCurrency(73, "BZD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 135 */     addCurrency(74, "CLP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 136 */     addCurrency(75, "COP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 137 */     addCurrency(76, "CRC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 138 */     addCurrency(77, "CUP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 139 */     addCurrency(78, "CVE", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 140 */     addCurrency(79, "DJF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 141 */     addCurrency(80, "DOP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 142 */     addCurrency(81, "DZD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 143 */     addCurrency(82, "ECS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 144 */     addCurrency(83, "ETB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 145 */     addCurrency(84, "FJD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 146 */     addCurrency(85, "FKP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 147 */     addCurrency(86, "GHC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 148 */     addCurrency(87, "GIP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 149 */     addCurrency(88, "GMD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 150 */     addCurrency(89, "GNF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 151 */     addCurrency(90, "GTQ", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 152 */     addCurrency(91, "GWP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 153 */     addCurrency(92, "GYD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 154 */     addCurrency(93, "HNL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 155 */     addCurrency(94, "HTG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 156 */     addCurrency(95, "IDR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 157 */     addCurrency(96, "JMD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 158 */     addCurrency(97, "KES", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 159 */     addCurrency(98, "KHR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 160 */     addCurrency(99, "KMF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 161 */     addCurrency(100, "KPW", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 162 */     addCurrency(101, "KRW", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 163 */     addCurrency(102, "KYD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 164 */     addCurrency(103, "LKR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 165 */     addCurrency(104, "LRD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 166 */     addCurrency(105, "LSL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 167 */     addCurrency(106, "MAD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 168 */     addCurrency(107, "MNT", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 169 */     addCurrency(108, "MOP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 170 */     addCurrency(109, "MRO", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 171 */     addCurrency(110, "MTL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 172 */     addCurrency(111, "MUR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 173 */     addCurrency(112, "MVR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 174 */     addCurrency(113, "MWK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 175 */     addCurrency(114, "MZM", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 176 */     addCurrency(115, "NGN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 177 */     addCurrency(116, "NIC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 178 */     addCurrency(117, "NPR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 179 */     addCurrency(118, "NZD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 180 */     addCurrency(119, "OMR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 181 */     addCurrency(120, "PAB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 182 */     addCurrency(121, "PEN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 183 */     addCurrency(122, "PGK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 184 */     addCurrency(123, "PHP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 185 */     addCurrency(124, "PKR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 186 */     addCurrency(125, "PYG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 187 */     addCurrency(126, "QAR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 188 */     addCurrency(127, "RWF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 189 */     addCurrency(128, "SBD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 190 */     addCurrency(129, "SCR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 191 */     addCurrency(130, "SDP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 192 */     addCurrency(131, "SGD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 193 */     addCurrency(132, "SHP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 194 */     addCurrency(133, "SLL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 195 */     addCurrency(134, "SOS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 196 */     addCurrency(135, "SRG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 197 */     addCurrency(136, "STD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 198 */     addCurrency(137, "SVC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 199 */     addCurrency(138, "SYP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 200 */     addCurrency(139, "SZL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 201 */     addCurrency(140, "THB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 202 */     addCurrency(141, "TND", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 203 */     addCurrency(142, "TPE", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 204 */     addCurrency(143, "TTD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 205 */     addCurrency(144, "TZS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 206 */     addCurrency(145, "UGS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 207 */     addCurrency(146, "UYP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 208 */     addCurrency(147, "VEB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 209 */     addCurrency(148, "VND", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 210 */     addCurrency(149, "WST", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 211 */     addCurrency(150, "YDD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 212 */     addCurrency(151, "YER", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 213 */     addCurrency(152, "YUD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 214 */     addCurrency(153, "ZAR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 215 */     addCurrency(154, "ZMK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 216 */     addCurrency(155, "ZWD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 217 */     addCurrency(156, "KZT", 2, 1.0D, false, 0.0D, 1, "", "tiin", 2, false, false);
/* 218 */     addCurrency(157, "UAH", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 219 */     addCurrency(158, "TMM", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 220 */     addCurrency(159, "UZS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 221 */     addCurrency(160, "TRY", 2, 1.0D, false, 0.0D, 1, "TL", "Kr", 2, false, false);
/*     */     
/* 223 */     addCurrency(161, "RON", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 224 */     addCurrency(162, "AZN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 225 */     addCurrency(163, "TJS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 226 */     addCurrency(164, "MKD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*     */     
/* 228 */     addCurrency(165, "AMD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 229 */     addCurrency(166, "AWG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 230 */     addCurrency(167, "BAM", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 231 */     addCurrency(168, "BOV", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 232 */     addCurrency(169, "BYR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 233 */     addCurrency(170, "CDF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 234 */     addCurrency(171, "CHE", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 235 */     addCurrency(172, "CHW", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 236 */     addCurrency(173, "CLF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 237 */     addCurrency(174, "COU", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 238 */     addCurrency(175, "CUC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 239 */     addCurrency(176, "ERN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 240 */     addCurrency(177, "HRK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 241 */     addCurrency(178, "MDL", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 242 */     addCurrency(179, "MGA", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 243 */     addCurrency(180, "MMK", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 244 */     addCurrency(181, "MXV", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 245 */     addCurrency(182, "NAD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 246 */     addCurrency(183, "RSD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 247 */     addCurrency(184, "TOP", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 248 */     addCurrency(185, "USN", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 249 */     addCurrency(186, "USS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 250 */     addCurrency(187, "UYI", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 251 */     addCurrency(188, "VUV", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 252 */     addCurrency(189, "XAF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 253 */     addCurrency(190, "XAG", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 254 */     addCurrency(191, "XAU", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 255 */     addCurrency(192, "XBA", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 256 */     addCurrency(193, "XBB", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 257 */     addCurrency(194, "XBC", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 258 */     addCurrency(195, "XBD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 259 */     addCurrency(196, "XCD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 260 */     addCurrency(197, "XDR", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 261 */     addCurrency(198, "XFU", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 262 */     addCurrency(199, "XOF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 263 */     addCurrency(200, "XPD", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 264 */     addCurrency(201, "XPF", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 265 */     addCurrency(202, "XPT", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/* 266 */     addCurrency(203, "XTS", 2, 1.0D, false, 0.0D, 1, "", "", 2, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCurrency(JLbsCurrencyInfo info) {
/* 272 */     this.m_Currencies.add(info);
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
/*     */   private void addCurrency(int curIndex, String code, int decimals, double coef, boolean EUROIndexed, double EURORate, int smallCoin, String shortCode, String decCode, int rounding, boolean triangulation, boolean invertRate) {
/* 294 */     JLbsCurrencyInfo info = new JLbsCurrencyInfo(curIndex, code, decCode, shortCode, decimals, coef, invertRate, EUROIndexed, 
/* 295 */         EURORate, smallCoin, rounding, triangulation);
/*     */     
/* 297 */     this.m_Currencies.add(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrencyInfo getCurrencyInfoAt(int order) {
/* 302 */     return this.m_Currencies.get(order);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrencyInfo getCurrencyInfo(int index) {
/* 307 */     for (int i = 0; i < this.m_Currencies.size(); i++) {
/*     */       
/* 309 */       JLbsCurrencyInfo info = this.m_Currencies.get(i);
/* 310 */       if (info.m_currency == index) {
/* 311 */         return info;
/*     */       }
/*     */     } 
/* 314 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCurrencySymbol(int index) {
/* 319 */     for (int i = 0; i < this.m_Currencies.size(); i++) {
/*     */       
/* 321 */       JLbsCurrencyInfo info = this.m_Currencies.get(i);
/* 322 */       if (info.m_currency == index)
/* 323 */         return info.m_shortCode; 
/*     */     } 
/* 325 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCurrencyInfo getCurrencyInfo(String code) {
/* 331 */     for (int i = 0; i < this.m_Currencies.size(); i++) {
/*     */       
/* 333 */       JLbsCurrencyInfo info = this.m_Currencies.get(i);
/* 334 */       if (info.m_code.compareTo(code) == 0) {
/* 335 */         return info;
/*     */       }
/*     */     } 
/* 338 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCurrencyCode(int index) {
/* 343 */     JLbsCurrencyInfo cInfo = getCurrencyInfo(index);
/*     */     
/* 345 */     if (cInfo == null) {
/* 346 */       return "";
/*     */     }
/* 348 */     return cInfo.m_code;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrencyInfo getDefaultCurrency() {
/* 353 */     return this.m_defaultCurrency;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultCurrency(int index) {
/* 358 */     JLbsCurrencyInfo info = getCurrencyInfo(index);
/*     */     
/* 360 */     if (info != null) {
/* 361 */       this.m_defaultCurrency = info;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDefaultCurrency(String code) {
/* 366 */     JLbsCurrencyInfo info = getCurrencyInfo(code);
/*     */     
/* 368 */     if (info != null) {
/* 369 */       this.m_defaultCurrency = info;
/*     */     }
/*     */   }
/*     */   
/*     */   public void createContextCurrency(int ctxN, int currIndex) {
/* 374 */     JLbsCurrencyInfo info = getCurrencyInfo(currIndex);
/*     */     
/* 376 */     if (info != null && ctxN > 0 && ctxN < this.m_contextCurrs.length + 1) {
/* 377 */       this.m_contextCurrs[ctxN - 1] = info;
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsCurrencyInfo getContextCurrency(int ctxN) {
/* 382 */     if (ctxN == 0) {
/* 383 */       return this.m_defaultCurrency;
/*     */     }
/* 385 */     if (ctxN < 0 || ctxN > this.m_contextCurrs.length) {
/* 386 */       return null;
/*     */     }
/* 388 */     return this.m_contextCurrs[ctxN - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrencyInfo[] getContextCurrencies() {
/* 393 */     return this.m_contextCurrs;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getSmallestUnit(int currIndex) {
/* 398 */     JLbsCurrencyInfo curr = this.m_defaultCurrency;
/* 399 */     if (currIndex != 0 && currIndex != curr.m_currency) {
/* 400 */       curr = getCurrencyInfo(currIndex);
/*     */     }
/* 402 */     int decimals = Math.min(curr.m_decimals, 5);
/* 403 */     BigDecimal smallest = ONE;
/* 404 */     return (decimals > 0) ? 
/* 405 */       smallest.movePointLeft(decimals) : 
/* 406 */       smallest;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal roundToDecimals(BigDecimal number, int decimalPts, int halfOption) {
/* 411 */     return number.divide(ONE, decimalPts, halfOption);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal roundToDecimals(BigDecimal number, int decimalPts) {
/* 416 */     return number.divide(ONE, decimalPts, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal roundToWhole(BigDecimal number) {
/* 421 */     return roundToDecimals(number, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal roundToWhole(BigDecimal number, int halfOption) {
/* 426 */     return roundToDecimals(number, 0, halfOption);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal fractionOf(BigDecimal number) {
/* 431 */     return number.subtract(new BigDecimal(number.toBigInteger()));
/*     */   }
/*     */   
/*     */   public static BigDecimal roundMFigure(int method, int smallestCoin, int decimalPts, BigDecimal amount) {
/*     */     BigDecimal coinInFracs;
/* 436 */     switch (method) {
/*     */       
/*     */       case 1:
/* 439 */         return roundToWhole(amount);
/*     */       
/*     */       case 2:
/* 442 */         return roundToDecimals(amount, decimalPts);
/*     */       
/*     */       case 3:
/* 445 */         if (smallestCoin == 0) {
/* 446 */           return roundToWhole(amount);
/*     */         }
/*     */         
/* 449 */         coinInFracs = (new BigDecimal(smallestCoin)).movePointLeft(decimalPts);
/*     */         
/* 451 */         return roundToDecimals(amount.divide(coinInFracs, 0, 4).multiply(coinInFracs), 
/* 452 */             decimalPts);
/*     */       
/*     */       case 4:
/* 455 */         return roundToWhole(amount, 5);
/*     */       
/*     */       case 5:
/* 458 */         return roundToDecimals(amount, decimalPts, 5);
/*     */     } 
/*     */     
/* 461 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double roundMFigure(int method, int smallestCoin, int decimalPts, double amount) {
/* 466 */     return roundMFigure(method, smallestCoin, decimalPts, new BigDecimal(amount)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundLocalVAT(int method, BigDecimal VATAmount) {
/* 471 */     return roundVAT(method, this.m_defaultCurrency, VATAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal roundCurrencyVAT(int method, int currIndex, BigDecimal VATAmount) {
/* 477 */     JLbsCurrencyInfo curr = this.m_defaultCurrency;
/* 478 */     if (currIndex != 0 && currIndex != curr.m_currency)
/* 479 */       curr = getCurrencyInfo(currIndex); 
/* 480 */     if (curr == null)
/*     */     {
/* 482 */       curr = this.m_defaultCurrency;
/*     */     }
/* 484 */     return roundVAT(method, curr, VATAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundVAT(int method, JLbsCurrencyInfo curr, BigDecimal VATAmount) {
/* 489 */     int sCoin = 1;
/* 490 */     int decPt = 2;
/* 491 */     if (curr != null) {
/*     */       
/* 493 */       sCoin = curr.m_smallestCoin;
/* 494 */       decPt = curr.m_decimals;
/*     */     } 
/* 496 */     return roundMFigure(method, sCoin, decPt, VATAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double roundLocalVAT(int method, double VATAmount) {
/* 501 */     return roundLocalVAT(method, new BigDecimal(VATAmount)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundCurrencyFigure(JLbsCurrencyInfo curr, BigDecimal amount) {
/* 506 */     if (curr != null && curr.m_rounding != 0) {
/* 507 */       return roundMFigure(curr.m_rounding, curr.m_smallestCoin, curr.m_decimals, amount);
/*     */     }
/* 509 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundCurrencyFigure(int currIndex, BigDecimal amount) {
/* 514 */     JLbsCurrencyInfo curr = this.m_defaultCurrency;
/* 515 */     if (currIndex != 0 && currIndex != curr.m_currency) {
/* 516 */       curr = getCurrencyInfo(currIndex);
/*     */     }
/* 518 */     return roundCurrencyFigure(curr, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double roundCurrencyFigure(int currIndex, double amount) {
/* 523 */     return roundCurrencyFigure(currIndex, new BigDecimal(amount)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundCurrencyFigureContext(int ctxN, BigDecimal amount) {
/* 528 */     JLbsCurrencyInfo info = getContextCurrency(ctxN);
/* 529 */     return roundCurrencyFigure(info, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double roundCurrencyFigureContext(int ctxN, double amount) {
/* 534 */     JLbsCurrencyInfo info = getContextCurrency(ctxN);
/* 535 */     return roundCurrencyFigure(info, new BigDecimal(amount)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal roundRate(BigDecimal rate) {
/* 540 */     return roundToDecimals(rate, this.m_RateDecimals);
/*     */   }
/*     */ 
/*     */   
/*     */   public double roundRate(double rate) {
/* 545 */     return roundRate(new BigDecimal(rate)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal NCUToEuro(JLbsCurrencyInfo ncDef, BigDecimal amount) {
/* 550 */     if (amount.compareTo(EPSILON) > 0 && ncDef != null && ncDef.m_EURORate > 0.0D) {
/* 551 */       return roundToDecimals(amount.divide(new BigDecimal(ncDef.m_EURORate), 8), 4);
/*     */     }
/* 553 */     return new BigDecimal(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal EuroToNCU(JLbsCurrencyInfo ncDef, BigDecimal euroAmount) {
/* 558 */     if (euroAmount.compareTo(EPSILON) > 0 && ncDef != null && ncDef.m_EURORate > 0.0D) {
/* 559 */       return roundToDecimals(euroAmount.multiply(new BigDecimal(ncDef.m_EURORate)), 2);
/*     */     }
/* 561 */     return new BigDecimal(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal NCUToNCU(JLbsCurrencyInfo srcDef, JLbsCurrencyInfo dstDef, BigDecimal amount) {
/* 566 */     return EuroToNCU(dstDef, NCUToEuro(srcDef, amount));
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal crossCalc(JLbsCurrencyInfo srcDef, JLbsCurrencyInfo dstDef, BigDecimal amount, BigDecimal rate, boolean round) {
/* 571 */     return crossCalc(srcDef, dstDef, amount, rate, round, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal crossCalc(JLbsCurrencyInfo srcDef, JLbsCurrencyInfo dstDef, BigDecimal amount, BigDecimal rate, boolean round, int divisionCount) {
/* 577 */     BigDecimal result = amount;
/* 578 */     int division = divisionCount;
/* 579 */     if (dstDef.m_invertRate)
/*     */     {
/* 581 */       division++;
/*     */     }
/* 583 */     if (division % 2 == 1 && rate.compareTo(EPSILON) > 0)
/*     */     {
/* 585 */       rate = ONE.divide(rate, 20, 4);
/*     */     }
/* 587 */     if (srcDef == null || dstDef == null)
/* 588 */     { result = amount.multiply(rate); }
/* 589 */     else if (srcDef.m_currency == 20)
/* 590 */     { if (dstDef.m_EUROIndexed)
/* 591 */       { result = EuroToNCU(dstDef, amount); }
/*     */       else
/* 593 */       { result = amount.multiply(rate); }  }
/* 594 */     else if (srcDef.m_EUROIndexed)
/* 595 */     { if (dstDef.m_currency == 20) {
/* 596 */         result = NCUToEuro(srcDef, amount);
/* 597 */       } else if (dstDef.m_EUROIndexed) {
/* 598 */         result = NCUToNCU(srcDef, dstDef, amount);
/*     */       } else {
/* 600 */         result = amount.multiply(rate);
/*     */       }  }
/* 602 */     else { result = amount.multiply(rate); }
/*     */     
/* 604 */     if (round) {
/* 605 */       return roundCurrencyFigure(dstDef, result);
/*     */     }
/* 607 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal crossCalc(int srcIdx, int dstIdx, BigDecimal amount, BigDecimal rate, boolean round) {
/* 612 */     return crossCalc(srcIdx, dstIdx, amount, rate, round, (JLbsCurrencyExtraParameter)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal crossCalc(int srcIdx, int dstIdx, BigDecimal amount, BigDecimal rate, boolean round, int divCount) {
/* 618 */     JLbsCurrencyExtraParameter extraParameter = null;
/* 619 */     return crossCalc(srcIdx, dstIdx, amount, rate, round, divCount, extraParameter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal crossCalc(int srcIdx, int dstIdx, BigDecimal amount, BigDecimal rate, boolean round, int divCount, JLbsCurrencyExtraParameter extraParameter) {
/* 625 */     if (srcIdx == dstIdx) {
/* 626 */       return amount;
/*     */     }
/* 628 */     JLbsCurrencyInfo source = (srcIdx == 0) ? 
/* 629 */       this.m_defaultCurrency : 
/* 630 */       getCurrencyInfo(srcIdx);
/* 631 */     JLbsCurrencyInfo dest = (dstIdx == 0) ? 
/* 632 */       this.m_defaultCurrency : 
/* 633 */       getCurrencyInfo(dstIdx);
/* 634 */     int oldValue = dest.m_decimals;
/* 635 */     if (extraParameter != null && extraParameter.getDecimal() > 0)
/* 636 */       dest.m_decimals = extraParameter.getDecimal(); 
/* 637 */     BigDecimal result = crossCalc(source, dest, amount, rate, round, divCount);
/* 638 */     dest.m_decimals = oldValue;
/* 639 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal fCurrToLocal(int curIdx, BigDecimal amount, BigDecimal rate, boolean round, JLbsCurrencyExtraParameter parameter) {
/* 645 */     return crossCalc(curIdx, 0, amount, rate, round, parameter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal crossCalc(int srcIdx, int dstIdx, BigDecimal amount, BigDecimal rate, boolean round, JLbsCurrencyExtraParameter parameter) {
/* 651 */     if (srcIdx == dstIdx) {
/* 652 */       return amount;
/*     */     }
/* 654 */     JLbsCurrencyInfo source = (srcIdx == 0) ? 
/* 655 */       this.m_defaultCurrency : 
/* 656 */       getCurrencyInfo(srcIdx);
/* 657 */     JLbsCurrencyInfo dest = (dstIdx == 0) ? 
/* 658 */       this.m_defaultCurrency : 
/* 659 */       getCurrencyInfo(dstIdx);
/* 660 */     int oldValue = dest.m_decimals;
/* 661 */     if (parameter != null && parameter.getDecimal() > 0)
/* 662 */       dest.m_decimals = parameter.getDecimal(); 
/* 663 */     BigDecimal result = crossCalc(source, dest, amount, rate, round);
/* 664 */     dest.m_decimals = oldValue;
/* 665 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal localToFCurr(int curIdx, BigDecimal amount, BigDecimal rate, boolean round) {
/* 670 */     return localToFCurr(curIdx, amount, rate, round, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal localToFCurr(int curIdx, BigDecimal amount, BigDecimal rate, boolean round, JLbsCurrencyExtraParameter parameter) {
/* 676 */     int divCount = 0;
/* 677 */     if (rate.compareTo(EPSILON) < 0) {
/* 678 */       rate = new BigDecimal(0);
/*     */     } else {
/*     */       
/* 681 */       divCount = 1;
/*     */     } 
/* 683 */     return crossCalc(0, curIdx, amount, rate, round, divCount, parameter);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal fCurrToLocal(int curIdx, BigDecimal amount, BigDecimal rate, boolean round) {
/* 688 */     return crossCalc(curIdx, 0, amount, rate, round);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCurrenciesBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */