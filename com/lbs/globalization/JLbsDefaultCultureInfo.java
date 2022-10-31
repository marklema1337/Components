/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import com.lbs.util.JLbsStringList;
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
/*     */ public class JLbsDefaultCultureInfo
/*     */   extends JLbsCultureInfoBase
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsDefaultCultureInfo() {
/*  22 */     JLbsStringList currencies = new JLbsStringList();
/*     */     
/*  24 */     currencies.add("USA Dollar", 1);
/*  25 */     currencies.add("German Mark", 2);
/*  26 */     currencies.add("Australia Dollar", 3);
/*  27 */     currencies.add("Austrian Schilling", 4);
/*  28 */     currencies.add("Belgian Franc", 5);
/*  29 */     currencies.add("Danish Krone", 6);
/*  30 */     currencies.add("Finnish Markka", 7);
/*  31 */     currencies.add("French Franc", 8);
/*  32 */     currencies.add("Netherlands Guilder", 9);
/*  33 */     currencies.add("Swedish Krona", 10);
/*  34 */     currencies.add("Swiss Franc", 11);
/*  35 */     currencies.add("Italian Lira", 12);
/*  36 */     currencies.add("Japanese Yen", 13);
/*  37 */     currencies.add("Canadian Dollar", 14);
/*  38 */     currencies.add("Kuwait Dinar", 15);
/*  39 */     currencies.add("Norwegian Krone", 16);
/*  40 */     currencies.add("UK Pound", 17);
/*  41 */     currencies.add("S. Arabian Riyal", 18);
/*  42 */     currencies.add("ECU", 19);
/*  43 */     currencies.add("Euro", 20);
/*  44 */     currencies.add("Azerbaijan Manat", 21);
/*  45 */     currencies.add("Brazilian Cruzeiro", 22);
/*  46 */     currencies.add("Bulgarian Lev", 23);
/*  47 */     currencies.add("Czech Koruna", 24);
/*  48 */     currencies.add("Chinese Yuan", 25);
/*  49 */     currencies.add("Estonian Kuron", 26);
/*  50 */     currencies.add("Georgian Marsilis", 27);
/*  51 */     currencies.add("Indian Rupee", 28);
/*  52 */     currencies.add("Hongkong Dollar", 29);
/*  53 */     currencies.add("Iraqi Dinnar", 30);
/*  54 */     currencies.add("Iranian Riyal", 31);
/*  55 */     currencies.add("Irish Punt", 32);
/*  56 */     currencies.add("Spanish Peseta", 33);
/*  57 */     currencies.add("Israeli Shekel", 34);
/*  58 */     currencies.add("Icelandic Krona", 35);
/*  59 */     currencies.add("Cyprus Pound", 36);
/*  60 */     currencies.add("Kyrgyzian Som", 37);
/*  61 */     currencies.add("Letonian Lats", 38);
/*  62 */     currencies.add("Libya Dinar", 39);
/*  63 */     currencies.add("Lebanese Pound", 40);
/*  64 */     currencies.add("Lituanian Lita", 41);
/*  65 */     currencies.add("Luxemburg Franc", 42);
/*  66 */     currencies.add("Hungarian Forint", 43);
/*  67 */     currencies.add("Malaysia Ringgi", 44);
/*  68 */     currencies.add("Mexican Peso", 45);
/*  69 */     currencies.add("Egyptian Pound", 46);
/*  70 */     currencies.add("Barbados Dollar", 47);
/*  71 */     currencies.add("Polish Zloty", 48);
/*  72 */     currencies.add("Portuguese Escudo", 49);
/*  73 */     currencies.add("Rumanian Leu", 50);
/*  74 */     currencies.add("Russian Ruble", 51);
/*  75 */     currencies.add("Taiwan Dollar", 52);
/*  76 */     currencies.add("Turkish Lira", 53);
/*  77 */     currencies.add("Jordanian Dinnar", 54);
/*  78 */     currencies.add("Greek Drachma", 55);
/*  79 */     currencies.add("Argentine Peso", 56);
/*  80 */     currencies.add("Lao Kip", 57);
/*  81 */     currencies.add("Andorran Peseta", 58);
/*  82 */     currencies.add("United Arab Emirates Dirham", 59);
/*  83 */     currencies.add("Afghanistan Afghani", 60);
/*  84 */     currencies.add("Albanian Lek", 61);
/*  85 */     currencies.add("Netherlands Antillian Guilder", 62);
/*  86 */     currencies.add("Angolan New Kwanza", 63);
/*  87 */     currencies.add("Bangladeshi Taka", 64);
/*  88 */     currencies.add("Bahraini Dinar", 65);
/*  89 */     currencies.add("Burundi Franc", 66);
/*  90 */     currencies.add("Bermudian Dollar", 67);
/*  91 */     currencies.add("Brunei Dollar", 68);
/*  92 */     currencies.add("Bolivian Boliviano", 69);
/*  93 */     currencies.add("Bahamian Dollar", 70);
/*  94 */     currencies.add("Bhutan Ngultrum", 71);
/*  95 */     currencies.add("Botswanian Pula", 72);
/*  96 */     currencies.add("Belize Dollar", 73);
/*  97 */     currencies.add("Chilean Peso", 74);
/*  98 */     currencies.add("Colombian Peso", 75);
/*  99 */     currencies.add("Costa Rican Colon", 76);
/* 100 */     currencies.add("Cuban Peso", 77);
/* 101 */     currencies.add("Cape Verde Escudo", 78);
/* 102 */     currencies.add("Djibouti Franc", 79);
/* 103 */     currencies.add("Dominican Peso", 80);
/* 104 */     currencies.add("Algerian Dinar", 81);
/* 105 */     currencies.add("Ecuador Sucre", 82);
/* 106 */     currencies.add("Ethiopian Birr", 83);
/* 107 */     currencies.add("Fiji Islands Dollar", 84);
/* 108 */     currencies.add("Falkland Islands Pound", 85);
/* 109 */     currencies.add("Ghanaian Cedi", 86);
/* 110 */     currencies.add("Gibraltar Pound", 87);
/* 111 */     currencies.add("Gambian Dalasi", 88);
/* 112 */     currencies.add("Guinea Franc", 89);
/* 113 */     currencies.add("Guatemalan Quetzal", 90);
/* 114 */     currencies.add("Guinea-Bissau Peso", 91);
/* 115 */     currencies.add("Guyanan Dollar", 92);
/* 116 */     currencies.add("Honduran Lempira", 93);
/* 117 */     currencies.add("Haitian Gourde", 94);
/* 118 */     currencies.add("Indonesian Rupiah", 95);
/* 119 */     currencies.add("Jamaican Dollar", 96);
/* 120 */     currencies.add("Kenyan Shilling", 97);
/* 121 */     currencies.add("Cambodian Riel", 98);
/* 122 */     currencies.add("Comoros Franc", 99);
/* 123 */     currencies.add("Korean Won (North)", 100);
/* 124 */     currencies.add("Korean Won (South)", 101);
/* 125 */     currencies.add("Cayman Islands Dollar", 102);
/* 126 */     currencies.add("Sri Lanka Rupee", 103);
/* 127 */     currencies.add("Liberian Dollar", 104);
/* 128 */     currencies.add("Lesotho Loti", 105);
/* 129 */     currencies.add("Moroccan Dirham", 106);
/* 130 */     currencies.add("Mongolian Tugrik", 107);
/* 131 */     currencies.add("Macau Pataca", 108);
/* 132 */     currencies.add("Mauritanian Ouguiya", 109);
/* 133 */     currencies.add("Maltese Lira", 110);
/* 134 */     currencies.add("Mauritius Rupee", 111);
/* 135 */     currencies.add("Maldive Rufiyaa", 112);
/* 136 */     currencies.add("Malawi Kwacha", 113);
/* 137 */     currencies.add("Mozambique Metical", 114);
/* 138 */     currencies.add("Nigerian Naira", 115);
/* 139 */     currencies.add("Nicaraguan Cordoba Oro", 116);
/* 140 */     currencies.add("Nepalese Rupee", 117);
/* 141 */     currencies.add("New Zealand Dollar", 118);
/* 142 */     currencies.add("Omani Rial", 119);
/* 143 */     currencies.add("Panamanian Balboa", 120);
/* 144 */     currencies.add("Peruvian Nuevo Sol", 121);
/* 145 */     currencies.add("Papua New Guinea Kina", 122);
/* 146 */     currencies.add("Philippine Peso", 123);
/* 147 */     currencies.add("Pakistan Rupee", 124);
/* 148 */     currencies.add("Paraguay Guarani", 125);
/* 149 */     currencies.add("Qatari Rial", 126);
/* 150 */     currencies.add("Rwanda Franc", 127);
/* 151 */     currencies.add("Solomon Islanda Dollar", 128);
/* 152 */     currencies.add("Seychelles Rupee", 129);
/* 153 */     currencies.add("Sudanese Pound", 130);
/* 154 */     currencies.add("Singapore Dollar", 131);
/* 155 */     currencies.add("St. Helena Pound", 132);
/* 156 */     currencies.add("Sierra Leone Leone", 133);
/* 157 */     currencies.add("Somali Shilling", 134);
/* 158 */     currencies.add("Suriname Guilder", 135);
/* 159 */     currencies.add("Sao Tome and Principe Dobra", 136);
/* 160 */     currencies.add("El Salvador Colon", 137);
/* 161 */     currencies.add("Syrian Pound", 138);
/* 162 */     currencies.add("Swaziland Lilangeni", 139);
/* 163 */     currencies.add("Thai Baht", 140);
/* 164 */     currencies.add("Tunusian Dinar", 141);
/* 165 */     currencies.add("East Timor Escudo", 142);
/* 166 */     currencies.add("Trinidad and Tobago Dollar", 143);
/* 167 */     currencies.add("Tanzanian Shilling", 144);
/* 168 */     currencies.add("Uganda Shilling", 145);
/* 169 */     currencies.add("Uruguayan Peso", 146);
/* 170 */     currencies.add("Venezualan Bolivar", 147);
/* 171 */     currencies.add("Vietnamese Dong", 148);
/* 172 */     currencies.add("Samoan Tala", 149);
/* 173 */     currencies.add("Yemeni Dinar", 150);
/* 174 */     currencies.add("Yemeni Rial", 151);
/* 175 */     currencies.add("Yugoslav Dinar", 152);
/* 176 */     currencies.add("South African Rand", 153);
/* 177 */     currencies.add("Zambian Kwacha", 154);
/* 178 */     currencies.add("Zimbabwe Dollar", 155);
/* 179 */     currencies.add("Kazakh Tenge", 156);
/* 180 */     currencies.add("Ukraine Hryvni", 157);
/* 181 */     currencies.add("Turkmenistan Manat", 158);
/* 182 */     currencies.add("Uzbekistan Sum", 159);
/*     */     
/* 184 */     setCurrencyDescriptions(currencies);
/*     */   }
/*     */   
/* 187 */   public static final String[] MONTHNAMES = new String[] { "Invalid", "January", "February", "March", "April", "May", "June", "July", 
/* 188 */       "August", "September", "October", "November", "December" };
/*     */   
/* 190 */   public static final String[] DAYNAMES = new String[] { "Invalid", "Sun", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
/*     */   
/* 192 */   public static final String[] SHORTDAYNAMES = new String[] { "Invalid", "Su", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
/*     */   
/* 194 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Zero", "[1] One", "[2] Two", "[3] Three", "[4] Four", "[5] Five", 
/* 195 */       "[6] Six", "[7] Seven", "[8] Eight", "[9] Nine", "[10] Ten", "[11] Eleven", "[12] Twelve", "[13] Thirteen", 
/* 196 */       "[14] Fourteen", "[15] Fifteen", "[16] Sixteen", "[17] Seventeen", "[18] Eighteen", "[19] Nineteen", "[20] Twenty", 
/* 197 */       "[30] Thirty", "[40] Fourty", "[50] Fifty", "[60] Sixty", "[70] Seventy", "[80] Eighty", "[90] Ninety" };
/* 198 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Thousand|", "[2] ~|Million|", "[3] ~|Billion|", 
/* 199 */       "[4] ~|Trillion|" };
/* 200 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Hundred", "[2] ~|#", "[3] ~|And|#" };
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/* 204 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/* 212 */     if (iMonth > 0 && iMonth <= 12)
/* 213 */       return MONTHNAMES[iMonth]; 
/* 214 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/* 219 */     return getMonthFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int day, int calendarType) {
/* 224 */     return getDayFullName(day);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int day, int calendarType) {
/* 229 */     return getDayShortName(day);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/* 237 */     if (iDay > 0 && iDay <= 7)
/* 238 */       return DAYNAMES[iDay]; 
/* 239 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/* 244 */     if (iDay > 0 && iDay <= 7)
/* 245 */       return SHORTDAYNAMES[iDay]; 
/* 246 */     return "XxX";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 251 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 256 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 261 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/* 266 */     return "ENUS";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 271 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 274 */         return "English";
/*     */       case 102:
/* 276 */         return "Username";
/*     */       case 103:
/* 278 */         return "Password";
/*     */       case 119:
/* 280 */         return "Login as Different User";
/*     */       case 104:
/* 282 */         return "Company";
/*     */       case 105:
/* 284 */         return "Period";
/*     */       case 106:
/* 286 */         return "Language";
/*     */       case 107:
/* 288 */         return "&OK";
/*     */       case 108:
/* 290 */         return "&Cancel";
/*     */       case 109:
/* 292 */         return "Company and Period";
/*     */       case 110:
/* 294 */         return "Subsidiary";
/*     */       case 111:
/* 296 */         return "Caps Lock is on.";
/*     */       case 1000:
/* 298 */         return "Error";
/*     */       case 1001:
/* 300 */         return "Invalid username and/or password. Please contact your system administrator.";
/*     */       case 1002:
/* 302 */         return "You don't have administrative priviledges. Please contact your system administrator.";
/*     */       case 1003:
/* 304 */         return "Company tables should be upgraded. Please contact your system administrator.";
/*     */       case 1004:
/* 306 */         return "You are not allowed to use this company. Please contact your system administrator.";
/*     */       case 1005:
/* 308 */         return "The specified period could not be found. Please contact your system administrator.";
/*     */       case 1006:
/* 310 */         return "Active period could not be found. Please contact your system administrator.";
/*     */       case 1007:
/* 312 */         return "The system tables and the application are of different version. Please contact your system administrator.";
/*     */       case 1008:
/* 314 */         return "The specified company could not be found. Please contact your system administrator.";
/*     */       case 1009:
/* 316 */         return "The system tables could not be found. Please contact your system administrator.";
/*     */       case 1010:
/* 318 */         return "User can not be logged in. Please contact your system administrator.";
/*     */       case 1011:
/* 320 */         return "User account has been blocked. Please contact your system administrator.";
/*     */       case 1012:
/* 322 */         return "Invalid IP number. Please contact your system administrator.";
/*     */       case 1013:
/* 324 */         return "Your password has expired. Please contact your system administrator.";
/*     */       case 1014:
/* 326 */         return "Database connection lost. Please check your database configuration.";
/*     */       case 1015:
/* 328 */         return "Period tables should be upgraded. Please contact your system administrator.";
/*     */       case 1036:
/* 330 */         return "Period triggers should be upgraded. Please contact your system administrator.";
/*     */       case 1035:
/* 332 */         return "Company triggers should be upgraded. Please contact your system administrator.";
/*     */       case 1016:
/* 334 */         return "Please select company you want to work!";
/*     */       case 1017:
/* 336 */         return "System tables' version can be old. Please update and retry.";
/*     */       case 1018:
/* 338 */         return "Login failed";
/*     */       case 1019:
/* 340 */         return "The channel provider was unable to creata a channel";
/*     */       case 1020:
/* 342 */         return "Unable to connect to ";
/*     */       case 1021:
/* 344 */         return "The address is not a valid login URI: ";
/*     */       case 1022:
/* 346 */         return "Client Error:\n";
/*     */       case 1023:
/* 348 */         return "Server Error:\n";
/*     */       case 1024:
/* 350 */         return "An exception raised with code:\n";
/*     */       case 1025:
/* 352 */         return "You don't have a valid license to use this application.\n Please get a valid license to install!";
/*     */       case 1026:
/* 354 */         return "Your licence does not include this application language!";
/*     */       case 1027:
/* 356 */         return "Your license has expired!";
/*     */       case 1028:
/* 358 */         return "Operating System is not supported by your license! Please refer to your license agreement for supported operating systems!";
/*     */       case 1029:
/* 360 */         return "Your licence does not include this database management system!";
/*     */       case 1039:
/* 362 */         return "License user limit is reached. You cannot login into the system!";
/*     */       case 1030:
/* 364 */         return "New session could not be created! User login is blocked!";
/*     */       case 1031:
/* 366 */         return "Customization tables must be upgraded. Please contact your system administrator.";
/*     */       case 1032:
/* 368 */         return "Another \"Logo Report Viewer\" application is in use right now. Please click on the \"Open\" button located on \"Logo Report Viewer\" to view another report.";
/*     */       case 1033:
/* 370 */         return "Client compatibility error. Please contact your system administrator.";
/*     */       case 1034:
/* 372 */         return "The customization package is not valid. Please contact your system administrator.";
/*     */       case 112:
/* 374 */         return "Forgot my login information";
/*     */       case 113:
/* 376 */         return "E-Mail address";
/*     */       case 114:
/* 378 */         return "Please enter a valid mail address.";
/*     */       case 115:
/* 380 */         return "Your login information is sent to your mail address.";
/*     */       case 116:
/* 382 */         return "This mail address is not registered in our system. Please try again.";
/*     */       case 117:
/* 384 */         return "An unexpected error has occurred. Please contact your system administrator.";
/*     */       case 118:
/* 386 */         return "Info";
/*     */       case 1037:
/* 388 */         return "Other user info is invalid.";
/*     */       case 1038:
/* 390 */         return "Process management database definitions are not up-to-date. Please contact your system administrator.";
/*     */       case 120:
/* 392 */         return "Advanced";
/*     */       case 1040:
/* 394 */         return "You answered your security question incorrectly. Please try again.";
/*     */       case 1041:
/* 396 */         return "Password must be entered.";
/*     */       case 1064:
/* 398 */         return "There is no menu definition in your licence package. You cannot login to system!";
/*     */       case 1065:
/* 400 */         return "Identity number is not defined in the system!";
/*     */       case 1066:
/* 402 */         return "Digital signature cannot be verified!";
/*     */       case 1067:
/* 404 */         return "Digital signature digest is invalid!";
/*     */       case 1070:
/* 406 */         return "Oauth Token is invalid!";
/*     */       case 1068:
/* 408 */         return "There is no licence definition assigned to your user!";
/*     */       case 1042:
/* 410 */         return "Your password is temporary, should be changed!";
/*     */       case 1069:
/* 412 */         return "Process Management database definitions upgrade for the selected language is still in progress, please wait a while and try again";
/*     */       case 1043:
/* 414 */         return "Your licence does not support login for this type of user. You cannot login into the system!";
/*     */       case 1044:
/* 416 */         return "System does not support login for this type of user. You cannot login into the system!";
/*     */       case 1045:
/* 418 */         return "Server is in Maintenance Mode!\nPlease contact your administrator for further details";
/*     */       case 1046:
/* 420 */         return "System Admin has made changes with your licence, to be able to use the product, your administrator have to update your licence info. \nPlease contact your administrator.";
/*     */       case 121:
/* 422 */         return "Confirmation Code is Invalid!";
/*     */       case 122:
/* 424 */         return "An unexpected error has occurred. Please contact your system administrator.";
/*     */       case 123:
/* 426 */         return "Confirmation Code is Expired. Please Try Again!";
/*     */       case 124:
/* 428 */         return "Enter The Confirmation Code, Sent to Your Mail Adress";
/*     */       case 125:
/* 430 */         return "Confirmation Code";
/*     */       case 126:
/* 432 */         return "Logging in. Please wait...";
/*     */       case 1071:
/* 434 */         return "Invalid username or password!";
/*     */       case 1072:
/* 436 */         return "The same user cannot log in more than once!";
/*     */       case 1073:
/* 438 */         return "The same user cannot log in more than session count!";
/*     */       case 1074:
/* 440 */         return "The application is being prepared for you!";
/*     */       case 2001:
/* 442 */         return "<br><br><center><b>Thanks to j-Platform, you can follow your business processes out of office and access your data anywhere through aweb browser!</b></center><br><br>Growing in a competitive business environment requires to share accurate and up-to-date information securelynot only in company, but also with your dealers and through all channels in communication network. Not one corporationis like the other regarding their organizational structures and business manners; and with that in mind, j-Platform isdesigned as flexible as to be customized in line with specific needs of companies, so you can model your businessprocesses completely with various configuration options. You can add any function your corporation needs in j-Platform, andyou can increase the number of users as you please. Developed with the intention to decrease the initial investmentcosts for business applications, j-Platform responds to the needs of your company quickly with flexible licensingalternatives.Working in full integration with various applications including Logo Human Resources, Logo Mind BusinessAnalytics, Logo CRM solutions etc., j-Platform increases the productivity of your business by means of improving yourcorporate processes.<p>Additionally, the customizations that are developed by Logo Solution Partners offer fast and economical\tsolutions. You can very well enrich your ERP functions thanks to these applications that are developed exclusively for\tyour company and sector.";
/*     */     } 
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
/* 460 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 465 */     switch (language) {
/*     */       
/*     */       case 34:
/* 468 */         return "Albanian";
/*     */       case 32:
/* 470 */         return "Arabic (JO)";
/*     */       case 24:
/* 472 */         return "Arabic (SA)";
/*     */       case 38:
/* 474 */         return "Arabic (EG)";
/*     */       case 29:
/* 476 */         return "Azerbaijani";
/*     */       case 28:
/* 478 */         return "Bulgarian";
/*     */       case 17:
/* 480 */         return "Chinese";
/*     */       case 27:
/* 482 */         return "Croatian";
/*     */       case 9:
/* 484 */         return "Czech";
/*     */       case 10:
/* 486 */         return "Danish";
/*     */       case 11:
/* 488 */         return "Dutch";
/*     */       case 2:
/* 490 */         return "English";
/*     */       case 40:
/* 492 */         return "English (IN)";
/*     */       case 12:
/* 494 */         return "Estonian";
/*     */       case 30:
/* 496 */         return "Farsi";
/*     */       case 13:
/* 498 */         return "Finnish";
/*     */       case 3:
/* 500 */         return "French";
/*     */       case 4:
/* 502 */         return "German";
/*     */       case 37:
/* 504 */         return "Georgian";
/*     */       case 23:
/* 506 */         return "Greek";
/*     */       case 14:
/* 508 */         return "Hebrew";
/*     */       case 15:
/* 510 */         return "Hungarian";
/*     */       case 16:
/* 512 */         return "Icelandic";
/*     */       case 6:
/* 514 */         return "Italian";
/*     */       case 18:
/* 516 */         return "Japanese";
/*     */       case 19:
/* 518 */         return "Korean";
/*     */       case 20:
/* 520 */         return "Norwegian";
/*     */       case 8:
/* 522 */         return "Polish";
/*     */       case 21:
/* 524 */         return "Portuguese";
/*     */       case 25:
/* 526 */         return "Romanian";
/*     */       case 5:
/* 528 */         return "Russian";
/*     */       case 26:
/* 530 */         return "Slovenian";
/*     */       case 7:
/* 532 */         return "Spanish";
/*     */       case 22:
/* 534 */         return "Swedish";
/*     */       case 35:
/* 536 */         return "Thai";
/*     */       case 33:
/* 538 */         return "Turkic";
/*     */       case 31:
/* 540 */         return "Turkish (RTL)";
/*     */       case 1:
/* 542 */         return "Turkish";
/*     */       case 36:
/* 544 */         return "Vietnamiese";
/*     */     } 
/* 546 */     return "Unknown Language";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 551 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsDefaultCultureInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */