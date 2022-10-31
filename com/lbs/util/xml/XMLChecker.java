/*     */ package com.lbs.util.xml;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class XMLChecker
/*     */ {
/*     */   public static final void checkS(String s) throws NullPointerException {
/*   8 */     checkS(s.toCharArray(), 0, s.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void checkS(char[] ch, int start, int length) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException {
/*  15 */     for (int i = start; i < length; i++) {
/*     */       
/*  17 */       int c = ch[i];
/*     */       
/*  19 */       if (c != 32 && c != 9 && c != 13 && c != 10)
/*     */       {
/*  21 */         throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid for the 'S' production (white space).");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isName(String s) throws NullPointerException {
/*     */     try {
/*  31 */       checkName(s);
/*  32 */       return true;
/*     */     }
/*  34 */     catch (InvalidXMLException exception) {
/*     */       
/*  36 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void checkName(String s) throws NullPointerException, InvalidXMLException {
/*  42 */     checkName(s.toCharArray(), 0, s.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void checkName(char[] ch, int start, int length) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException {
/*  49 */     if (length < 1)
/*     */     {
/*  51 */       throw new InvalidXMLException("An empty string does not match the 'Name' production.");
/*     */     }
/*     */     
/*  54 */     int i = start;
/*  55 */     char c = ch[i];
/*  56 */     if (c != '_' && c != ':' && !isLetter(c))
/*     */     {
/*  58 */       throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is invalid as a starting character in the 'Name' production.");
/*     */     }
/*     */ 
/*     */     
/*  62 */     for (; ++i < length; i++) {
/*     */       
/*  64 */       c = ch[i];
/*     */       
/*  66 */       if (!isNameChar(c))
/*     */       {
/*  68 */         throw new InvalidXMLException("The character 0x" + Integer.toHexString(c) + " is not valid for the 'Name' production.");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isSystemLiteral(String s) throws NullPointerException {
/*     */     try {
/*  78 */       checkSystemLiteral(s);
/*  79 */       return true;
/*     */     }
/*  81 */     catch (InvalidXMLException exception) {
/*     */       
/*  83 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void checkSystemLiteral(String s) throws NullPointerException, InvalidXMLException {
/*  89 */     checkSystemLiteral(s.toCharArray(), 0, s.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void checkSystemLiteral(char[] ch, int start, int length) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException {
/*  97 */     if (length < 3)
/*     */     {
/*  99 */       throw new InvalidXMLException("Minimum length for the 'SystemLiteral' production is 3 characters.");
/*     */     }
/*     */     
/* 102 */     int lastIndex = start + length - 1;
/* 103 */     char firstChar = ch[0];
/* 104 */     char lastChar = ch[lastIndex];
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (firstChar == '\'') {
/*     */       
/* 110 */       if (lastChar != '\'')
/*     */       {
/* 112 */         throw new InvalidXMLException("First character is '\\'', but the last character is 0x" + 
/* 113 */             Integer.toHexString(lastChar) + '.');
/*     */       }
/* 115 */       String otherAllowedChars = "-()+,./:=?;!*#@$_%";
/*     */ 
/*     */     
/*     */     }
/* 119 */     else if (firstChar == '"') {
/*     */       
/* 121 */       if (lastChar != '"')
/*     */       {
/* 123 */         throw new InvalidXMLException("First character is '\"', but the last character is 0x" + 
/* 124 */             Integer.toHexString(lastChar) + '.');
/*     */       }
/* 126 */       String otherAllowedChars = "-'()+,./:=?;!*#@$_%";
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 132 */       throw new InvalidXMLException("First char must either be '\\'' or '\"' instead of 0x" + 
/* 133 */           Integer.toHexString(firstChar) + '.');
/*     */     } 
/*     */ 
/*     */     
/* 137 */     for (int i = 1; i < length - 1; i++) {
/*     */       
/* 139 */       char c = ch[i];
/*     */       
/* 141 */       if (c == firstChar) {
/*     */         
/* 143 */         if (firstChar == '\'')
/*     */         {
/* 145 */           throw new InvalidXMLException("Found '\\'' at position " + i + '.');
/*     */         }
/*     */ 
/*     */         
/* 149 */         throw new InvalidXMLException("Found '\"' at position " + i + '.');
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isPubidLiteral(String s) throws NullPointerException {
/*     */     try {
/* 159 */       checkPubidLiteral(s);
/* 160 */       return true;
/*     */     }
/* 162 */     catch (InvalidXMLException exception) {
/*     */       
/* 164 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void checkPubidLiteral(String s) throws NullPointerException, InvalidXMLException {
/* 170 */     checkPubidLiteral(s.toCharArray(), 0, s.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void checkPubidLiteral(char[] ch, int start, int length) throws NullPointerException, IndexOutOfBoundsException, InvalidXMLException {
/*     */     String otherAllowedChars;
/* 178 */     if (length < 3)
/*     */     {
/* 180 */       throw new InvalidXMLException("Minimum length for the 'PubidLiteral' production is 3 characters.");
/*     */     }
/*     */     
/* 183 */     int lastIndex = start + length - 1;
/* 184 */     char firstChar = ch[0];
/* 185 */     char lastChar = ch[lastIndex];
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (firstChar == '\'') {
/*     */       
/* 191 */       if (lastChar != '\'')
/*     */       {
/* 193 */         throw new InvalidXMLException("First character is '\\'', but the last character is 0x" + 
/* 194 */             Integer.toHexString(lastChar) + '.');
/*     */       }
/* 196 */       otherAllowedChars = "-()+,./:=?;!*#@$_%";
/*     */ 
/*     */     
/*     */     }
/* 200 */     else if (firstChar == '"') {
/*     */       
/* 202 */       if (lastChar != '"')
/*     */       {
/* 204 */         throw new InvalidXMLException("First character is '\"', but the last character is 0x" + 
/* 205 */             Integer.toHexString(lastChar) + '.');
/*     */       }
/* 207 */       otherAllowedChars = "-'()+,./:=?;!*#@$_%";
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 213 */       throw new InvalidXMLException("First char must either be '\\'' or '\"' instead of 0x" + 
/* 214 */           Integer.toHexString(firstChar) + '.');
/*     */     } 
/*     */ 
/*     */     
/* 218 */     for (int i = 1; i < length - 1; i++) {
/*     */       
/* 220 */       char c = ch[i];
/*     */       
/* 222 */       if (c != ' ' && c != '\r' && c != '\n' && !isLetter(c) && !isDigit(c) && otherAllowedChars.indexOf(c) < 0)
/*     */       {
/*     */         
/* 225 */         throw new InvalidXMLException("The character '" + c + "' (0x" + Integer.toHexString(c) + ") is not valid for the 'PubidLiteral' production.");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean isNameChar(char c) {
/* 233 */     return (c == '.' || c == '-' || c == '_' || c == ':' || isDigit(c) || isLetter(c) || isCombiningChar(c) || isExtender(c));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isLetter(char c) {
/* 238 */     return (isBaseChar(c) || isIdeographic(c));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isBaseChar(char c) {
/* 243 */     int n = c;
/* 244 */     return ((n >= 65 && n <= 90) || (n >= 97 && n <= 122) || (n >= 192 && n <= 214) || (n >= 216 && n <= 246) || (n >= 248 && n <= 255) || (n >= 256 && n <= 305) || (n >= 308 && n <= 318) || (n >= 321 && n <= 328) || (n >= 330 && n <= 382) || (n >= 384 && n <= 451) || (n >= 461 && n <= 496) || (n >= 500 && n <= 501) || (n >= 506 && n <= 535) || (n >= 592 && n <= 680) || (n >= 699 && n <= 705) || n == 902 || (n >= 904 && n <= 906) || n == 908 || (n >= 910 && n <= 929) || (n >= 931 && n <= 974) || (n >= 976 && n <= 982) || n == 986 || n == 988 || n == 990 || n == 992 || (n >= 994 && n <= 1011) || (n >= 1025 && n <= 1036) || (n >= 1038 && n <= 1103) || (n >= 1105 && n <= 1116) || (n >= 1118 && n <= 1153) || (n >= 1168 && n <= 1220) || (n >= 1223 && n <= 1224) || (n >= 1227 && n <= 1228) || (n >= 1232 && n <= 1259) || (n >= 1262 && n <= 1269) || (n >= 1272 && n <= 1273) || (n >= 1329 && n <= 1366) || n == 1369 || (n >= 1377 && n <= 1414) || (n >= 1488 && n <= 1514) || (n >= 1520 && n <= 1522) || (n >= 1569 && n <= 1594) || (n >= 1601 && n <= 1610) || (n >= 1649 && n <= 1719) || (n >= 1722 && n <= 1726) || (n >= 1728 && n <= 1742) || (n >= 1744 && n <= 1747) || n == 1749 || (n >= 1765 && n <= 1766) || (n >= 2309 && n <= 2361) || n == 2365 || (n >= 2392 && n <= 2401) || (n >= 2437 && n <= 2444) || (n >= 2447 && n <= 2448) || (n >= 2451 && n <= 2472) || (n >= 2474 && n <= 2480) || n == 2482 || (n >= 2486 && n <= 2489) || (n >= 2524 && n <= 2525) || (n >= 2527 && n <= 2529) || (n >= 2544 && n <= 2545) || (n >= 2565 && n <= 2570) || (n >= 2575 && n <= 2576) || (n >= 2579 && n <= 2600) || (n >= 2602 && n <= 2608) || (n >= 2610 && n <= 2611) || (n >= 2613 && n <= 2614) || (n >= 2616 && n <= 2617) || (n >= 2649 && n <= 2652) || n == 2654 || (n >= 2674 && n <= 2676) || (n >= 2693 && n <= 2699) || n == 2701 || (n >= 2703 && n <= 2705) || (n >= 2707 && n <= 2728) || (n >= 2730 && n <= 2736) || (n >= 2738 && n <= 2739) || (n >= 2741 && n <= 2745) || n == 2749 || n == 2784 || (n >= 2821 && n <= 2828) || (n >= 2831 && n <= 2832) || (n >= 2835 && n <= 2856) || (n >= 2858 && n <= 2864) || (n >= 2866 && n <= 2867) || (n >= 2870 && n <= 2873) || n == 2877 || (n >= 2908 && n <= 2909) || (n >= 2911 && n <= 2913) || (n >= 2949 && n <= 2954) || (n >= 2958 && n <= 2960) || (n >= 2962 && n <= 2965) || (n >= 2969 && n <= 2970) || n == 2972 || (n >= 2974 && n <= 2975) || (n >= 2979 && n <= 2980) || (n >= 2984 && n <= 2986) || (n >= 2990 && n <= 2997) || (n >= 2999 && n <= 3001) || (n >= 3077 && n <= 3084) || (n >= 3086 && n <= 3088) || (n >= 3090 && n <= 3112) || (n >= 3114 && n <= 3123) || (n >= 3125 && n <= 3129) || (n >= 3168 && n <= 3169) || (n >= 3205 && n <= 3212) || (n >= 3214 && n <= 3216) || (n >= 3218 && n <= 3240) || (n >= 3242 && n <= 3251) || (n >= 3253 && n <= 3257) || n == 3294 || (n >= 3296 && n <= 3297) || (n >= 3333 && n <= 3340) || (n >= 3342 && n <= 3344) || (n >= 3346 && n <= 3368) || (n >= 3370 && n <= 3385) || (n >= 3424 && n <= 3425) || (n >= 3585 && n <= 3630) || n == 3632 || (n >= 3634 && n <= 3635) || (n >= 3648 && n <= 3653) || (n >= 3713 && n <= 3714) || n == 3716 || (n >= 3719 && n <= 3720) || n == 3722 || n == 3725 || (n >= 3732 && n <= 3735) || (n >= 3737 && n <= 3743) || (n >= 3745 && n <= 3747) || n == 3749 || n == 3751 || (n >= 3754 && n <= 3755) || (n >= 3757 && n <= 3758) || n == 3760 || (n >= 3762 && n <= 3763) || n == 3773 || (n >= 3776 && n <= 3780) || (n >= 3904 && n <= 3911) || (n >= 3913 && n <= 3945) || (n >= 4256 && n <= 4293) || (n >= 4304 && n <= 4342) || n == 4352 || (n >= 4354 && n <= 4355) || (n >= 4357 && n <= 4359) || n == 4361 || (n >= 4363 && n <= 4364) || (n >= 4366 && n <= 4370) || n == 4412 || n == 4414 || n == 4416 || n == 4428 || n == 4430 || n == 4432 || (n >= 4436 && n <= 4437) || n == 4441 || (n >= 4447 && n <= 4449) || n == 4451 || n == 4453 || n == 4455 || n == 4457 || (n >= 4461 && n <= 4462) || (n >= 4466 && n <= 4467) || n == 4469 || n == 4510 || n == 4520 || n == 4523 || (n >= 4526 && n <= 4527) || (n >= 4535 && n <= 4536) || n == 4538 || (n >= 4540 && n <= 4546) || n == 4587 || n == 4592 || n == 4601 || (n >= 7680 && n <= 7835) || (n >= 7840 && n <= 7929) || (n >= 7936 && n <= 7957) || (n >= 7960 && n <= 7965) || (n >= 7968 && n <= 8005) || (n >= 8008 && n <= 8013) || (n >= 8016 && n <= 8023) || n == 8025 || n == 8027 || n == 8029 || (n >= 8031 && n <= 8061) || (n >= 8064 && n <= 8116) || (n >= 8118 && n <= 8124) || n == 8126 || (n >= 8130 && n <= 8132) || (n >= 8134 && n <= 8140) || (n >= 8144 && n <= 8147) || (n >= 8150 && n <= 8155) || (n >= 8160 && n <= 8172) || (n >= 8178 && n <= 8180) || (n >= 8182 && n <= 8188) || n == 8486 || (n >= 8490 && n <= 8491) || n == 8494 || (n >= 8576 && n <= 8578) || (n >= 12353 && n <= 12436) || (n >= 12449 && n <= 12538) || (n >= 12549 && n <= 12588) || (n >= 44032 && n <= 55203));
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
/*     */   private static final boolean isIdeographic(char c) {
/* 303 */     int n = c;
/* 304 */     return ((n >= 19968 && n <= 40869) || n == 12295 || (n >= 12321 && n <= 12329));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean isCombiningChar(char c) {
/* 309 */     int n = c;
/* 310 */     return ((n >= 768 && n <= 837) || (n >= 864 && n <= 865) || (n >= 1155 && n <= 1158) || (n >= 1425 && n <= 1441) || (n >= 1443 && n <= 1465) || (n >= 1467 && n <= 1469) || n == 1471 || (n >= 1473 && n <= 1474) || n == 1476 || (n >= 1611 && n <= 1618) || n == 1648 || (n >= 1750 && n <= 1756) || (n >= 1757 && n <= 1759) || (n >= 1760 && n <= 1764) || (n >= 1767 && n <= 1768) || (n >= 1770 && n <= 1773) || (n >= 2305 && n <= 2307) || n == 2364 || (n >= 2366 && n <= 2380) || n == 2381 || (n >= 2385 && n <= 2388) || (n >= 2402 && n <= 2403) || (n >= 2433 && n <= 2435) || n == 2492 || n == 2494 || n == 2495 || (n >= 2496 && n <= 2500) || (n >= 2503 && n <= 2504) || (n >= 2507 && n <= 2509) || n == 2519 || (n >= 2530 && n <= 2531) || n == 2562 || n == 2620 || n == 2622 || n == 2623 || (n >= 2624 && n <= 2626) || (n >= 2631 && n <= 2632) || (n >= 2635 && n <= 2637) || (n >= 2672 && n <= 2673) || (n >= 2689 && n <= 2691) || n == 2748 || (n >= 2750 && n <= 2757) || (n >= 2759 && n <= 2761) || (n >= 2763 && n <= 2765) || (n >= 2817 && n <= 2819) || n == 2876 || (n >= 2878 && n <= 2883) || (n >= 2887 && n <= 2888) || (n >= 2891 && n <= 2893) || (n >= 2902 && n <= 2903) || (n >= 2946 && n <= 2947) || (n >= 3006 && n <= 3010) || (n >= 3014 && n <= 3016) || (n >= 3018 && n <= 3021) || n == 3031 || (n >= 3073 && n <= 3075) || (n >= 3134 && n <= 3140) || (n >= 3142 && n <= 3144) || (n >= 3146 && n <= 3149) || (n >= 3157 && n <= 3158) || (n >= 3202 && n <= 3203) || (n >= 3262 && n <= 3268) || (n >= 3270 && n <= 3272) || (n >= 3274 && n <= 3277) || (n >= 3285 && n <= 3286) || (n >= 3330 && n <= 3331) || (n >= 3390 && n <= 3395) || (n >= 3398 && n <= 3400) || (n >= 3402 && n <= 3405) || n == 3415 || n == 3633 || (n >= 3636 && n <= 3642) || (n >= 3655 && n <= 3662) || n == 3761 || (n >= 3764 && n <= 3769) || (n >= 3771 && n <= 3772) || (n >= 3784 && n <= 3789) || (n >= 3864 && n <= 3865) || n == 3893 || n == 3895 || n == 3897 || n == 3902 || n == 3903 || (n >= 3953 && n <= 3972) || (n >= 3974 && n <= 3979) || (n >= 3984 && n <= 3989) || n == 3991 || (n >= 3993 && n <= 4013) || (n >= 4017 && n <= 4023) || n == 4025 || (n >= 8400 && n <= 8412) || n == 8417 || (n >= 12330 && n <= 12335) || n == 12441 || n == 12442);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean isDigit(char c) {
/* 339 */     int n = c;
/* 340 */     return ((n >= 48 && n <= 57) || (n >= 1632 && n <= 1641) || (n >= 1776 && n <= 1785) || (n >= 2406 && n <= 2415) || (n >= 2534 && n <= 2543) || (n >= 2662 && n <= 2671) || (n >= 2790 && n <= 2799) || (n >= 2918 && n <= 2927) || (n >= 3047 && n <= 3055) || (n >= 3174 && n <= 3183) || (n >= 3302 && n <= 3311) || (n >= 3430 && n <= 3439) || (n >= 3664 && n <= 3673) || (n >= 3792 && n <= 3801) || (n >= 3872 && n <= 3881));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean isExtender(char c) {
/* 349 */     int n = c;
/* 350 */     return (n == 183 || n == 720 || n == 721 || n == 903 || n == 1600 || n == 3654 || n == 3782 || n == 12293 || (n >= 12337 && n <= 12341) || (n >= 12445 && n <= 12446) || (n >= 12540 && n <= 12542));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\XMLChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */