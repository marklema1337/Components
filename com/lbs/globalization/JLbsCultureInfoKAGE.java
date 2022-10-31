/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import java.awt.Font;
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
/*     */ public class JLbsCultureInfoKAGE
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   public static final String[] MONTHNAMES = new String[] { "არასწორი", "იანვარი", "თებერვალი", "მარტი", "აპრილი", "მაისი", "ივნისი", "ივლისი", 
/*  19 */       "აგვისტო", "სექტემბერი", "ოქტომბერი", "ნოემბერი", "დეკემბერი" };
/*     */   
/*  21 */   public static final String[] DAYNAMES = new String[] { "არასწორი", "კვირა", "ორშაბათი", "სამშაბათი", "ოთხშაბათი", "ხუთშაბათი", "პარასკევი", 
/*  22 */       "შაბათი" };
/*     */   
/*  24 */   public static final String[] SHORTDAYNAMES = new String[] { "არა", "კვი", "ორშ", "სამ", "ოთხ", "ხუთ", "პარ", "შაბ" };
/*     */   
/*  26 */   protected static final String[] NUMBERNAMES = new String[] { "[0] ნულოვანი", "[1] ერთი", "[2] ორი", "[3] სამი", "[4] ოთხი", "[5] ხუთი", 
/*  27 */       "[6] ექვსი", "[7] შვიდი", "[8] რვა", "[9] ცხრა", "[10] ათი", "[20] ოცი", "[30] ოცდაათი", "[40] ორმოცი", 
/*  28 */       "[50] ორმოცდაათი", "[60] სამოცი", "[70] სამოცდაათი", "[80] ოთხმოცი", "[90] ოთხმოცდაათი", "[100] ასი" };
/*     */   
/*  30 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|ათასი|", "[2] ~|მილიონი|", "[3] ~|მილიარდი|", 
/*  31 */       "[4] ~|ტრილიონი|" };
/*     */   
/*  33 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|ასი", "[2] ~|#", "[3] ~|#" };
/*     */   
/*  35 */   public static String ms_FontName = "Sylfaen";
/*     */   
/*  37 */   public static int ms_FontStyle = 0;
/*     */   
/*  39 */   public static int ms_FontSize = 13;
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  43 */     return ILbsCultureConstants.LANGUAGEPREFIXES[37];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  48 */     return "(ვ)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  53 */     return "(მ)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  58 */     return (iEra > 0) ? 
/*  59 */       "BC" : 
/*  60 */       "AD";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  65 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  73 */     if (iMonth > 0 && iMonth <= 12)
/*  74 */       return MONTHNAMES[iMonth]; 
/*  75 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  80 */     if (iDay > 0 && iDay <= 7)
/*  81 */       return SHORTDAYNAMES[iDay]; 
/*  82 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  90 */     if (iDay > 0 && iDay <= 7)
/*  91 */       return DAYNAMES[iDay]; 
/*  92 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/*  97 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 102 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 107 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 112 */     return "დიახ";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 117 */     return "არარის";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 122 */     return "OK";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 127 */     return "უარი";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 132 */     return "შენახვა";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 137 */     return "დროის ზონა";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 142 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 145 */         return "საქართველოს";
/*     */       case 102:
/* 147 */         return "ნიკი";
/*     */       case 103:
/* 149 */         return "პაროლი";
/*     */       case 119:
/* 151 */         return "შესვლა როგორც სხვადასხვა მომხმარებლის";
/*     */       case 120:
/* 153 */         return "დამატებითი ფუნქცია";
/*     */       case 104:
/* 155 */         return "კომპანია";
/*     */       case 105:
/* 157 */         return "პერიოდი";
/*     */       case 106:
/* 159 */         return "ენა";
/*     */       case 107:
/* 161 */         return "&OK";
/*     */       case 108:
/* 163 */         return "&უარი";
/*     */       case 109:
/* 165 */         return "კომპანია და პერიოდი";
/*     */       case 110:
/* 167 */         return "შვილობილი";
/*     */       case 111:
/* 169 */         return "Caps Lock არის.";
/*     */       
/*     */       case 1000:
/* 172 */         return "შეცდომა";
/*     */       case 1001:
/* 174 */         return "არასწორი სახელი და / ან პაროლი. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1002:
/* 176 */         return "თქვენ არ ადმინისტრაციული შეღავათები. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1003:
/* 178 */         return "კომპანია მაგიდები უნდა განახლდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1004:
/* 180 */         return "თქვენ არ გაქვთ ამ ფუნქციის გამოყენების კომპანია. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1005:
/* 182 */         return "მითითებულ პერიოდში ვერ იქნა ნაპოვნი. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1006:
/* 184 */         return "ზოგადი პერიოდში ვერ იქნა ნაპოვნი. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1007:
/* 186 */         return "სისტემის მაგიდა და განაცხადის სხვადასხვა ვერსია. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1008:
/* 188 */         return "მითითებული კომპანია ვერ მოიძებნა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1009:
/* 190 */         return "სისტემის მაგიდები ვერ მოიძებნა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1010:
/* 192 */         return "მომხმარებელი არ შეიძლება შესული სისტემაში, გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1011:
/* 194 */         return "მომხმარებელი ანგარიში დაბლოკა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1012:
/* 196 */         return "არასწორი IP არ. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1013:
/* 198 */         return "თქვენი პაროლი გაუვიდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1014:
/* 200 */         return "მონაცემთა ბაზის კავშირი შეცდომა. შეამოწმეთ მონაცემთა ბაზის პარამეტრები.";
/*     */       case 1015:
/* 202 */         return "პერიოდი მაგიდები უნდა განახლდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1036:
/* 204 */         return "პერიოდი იწვევს უნდა განახლდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1035:
/* 206 */         return "კომპანია იწვევს უნდა განახლდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1016:
/* 208 */         return "გთხოვთ აირჩიოთ კომპანია გსურთ მუშაობა!";
/*     */       case 1017:
/* 210 */         return "ვერსია სისტემის ჩამონათვალმა შესაძლებელია იყოს ძველი. გთხოვთ განაახლოთ და სცადეთ კიდევ ერთხელ.";
/*     */       case 1018:
/* 212 */         return "შესვლა ვერ მოხერხდა";
/*     */       case 1019:
/* 214 */         return "არხი პროვაიდერი ვერ შექმნა არხი";
/*     */       case 1020:
/* 216 */         return "დაკავშირება ვერ ხერხდება";
/*     */       case 1021:
/* 218 */         return "მისამართი არ არის სწორი შესვლა URI: ";
/*     */       case 1022:
/* 220 */         return "შიდა გამონაკლისი:\n";
/*     */       case 1023:
/* 222 */         return "დისტანციური გამონაკლისი:\n";
/*     */       case 1024:
/* 224 */         return "გამონაკლისი საკითხი დააყენა კოდი";
/*     */       case 1025:
/* 226 */         return "თქვენ არ მოქმედებს ლიცენზია დაყენებული.\nგთხოვთ დააყენოთ სწორი ლიცენზია!";
/*     */       case 1026:
/* 228 */         return "შენი ლიცენზია არ შეიცავს ამ განაცხადის ენა!";
/*     */       case 1027:
/* 230 */         return "შენი ლიცენზიის ვადა გაუვიდა?";
/*     */       case 1028:
/* 232 */         return "ოპერაციული სისტემა არ არის მხარდაჭერილი თქვენი ლიცენზია! გთხოვთ მიმართეთ თქვენს ლიცენზიის ხელშეკრულება თავსებადი ოპერაციული სისტემები!";
/*     */       case 1029:
/* 234 */         return "შენი ლიცენზია არ შეიცავს ამ მონაცემთა ბაზების მართვის სისტემა!";
/*     */       case 1030:
/* 236 */         return "ვერ ვქმნი ახალ სესია! მომხმარებელი არ შემიძლია შემოსვლა ამ კვანძის!";
/*     */       case 1031:
/* 238 */         return "დაკონფიგურირება მაგიდები უნდა განახლდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1032:
/* 240 */         return "ანგარიშის მაყურებელს უკვე არის გაშვებული. გთხოვთ გამოიყენოთ ღია ღილაკს ანგარიში მაყურებელს სანახავად მეორე ანგარიშს.";
/*     */       case 1033:
/* 242 */         return "კლიენტი თავსებადობა შეცდომა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1034:
/* 244 */         return "დაკონფიგურირება პაკეტი არ არის სწორი. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 112:
/* 246 */         return "დაგავიწყდა პაროლი?";
/*     */       case 113:
/* 248 */         return "ელექტრონული მისამართი";
/*     */       case 114:
/* 250 */         return "არასწორი ელ მისამართზე.";
/*     */       case 115:
/* 252 */         return "შენი შესვლა ინფორმაცია გაიგზავნა თქვენი ელექტრონული ფოსტის მისამართი.";
/*     */       case 116:
/* 254 */         return "ფოსტის მისამართი მითითებული არ ნაპოვნი ჩვენს სისტემაში ჩანაწერი. \nგთხოვთ შეამოწმოთ გვერდის მისამართი თქვენს მიერ მითითებული.";
/*     */       case 117:
/* 256 */         return "მოულოდნელი გამონაკლისი მოხდა. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 118:
/* 258 */         return "ინფორმაცია";
/*     */       case 1037:
/* 260 */         return "არასწორი სხვადასხვა სახელის და / ან პაროლი. გთხოვთ დაუკავშირდეთ სისტემის ადმინისტრატორს.";
/*     */       case 1040:
/* 262 */         return "უშიშროების ი არასწორად. გთხოვთ კიდევ ცადოთ.";
/*     */     } 
/* 264 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 269 */     switch (language) {
/*     */       
/*     */       case 34:
/* 272 */         return "ალბანეთის";
/*     */       case 32:
/* 274 */         return "რაბთა (JO)";
/*     */       case 24:
/* 276 */         return "რაბთა (SA)";
/*     */       case 38:
/* 278 */         return "რაბთა (EG)";
/*     */       case 29:
/* 280 */         return "ზერბაიჯანული";
/*     */       case 28:
/* 282 */         return "ბულგარეთის";
/*     */       case 17:
/* 284 */         return "ჩინურ";
/*     */       case 27:
/* 286 */         return "ხორვატიის";
/*     */       case 9:
/* 288 */         return "ჩეხეთის";
/*     */       case 10:
/* 290 */         return "დანიის";
/*     */       case 11:
/* 292 */         return "ჰოლანდიის";
/*     */       case 2:
/* 294 */         return "English";
/*     */       case 40:
/* 296 */         return "English (IN)";
/*     */       case 12:
/* 298 */         return "ესტონეთის";
/*     */       case 30:
/* 300 */         return "სპარსეთის";
/*     */       case 13:
/* 302 */         return "ფინეთის";
/*     */       case 3:
/* 304 */         return "საფრანგეთის";
/*     */       case 4:
/* 306 */         return "გერმანიის";
/*     */       case 37:
/* 308 */         return "საქართველოს";
/*     */       case 23:
/* 310 */         return "ბერძნულ";
/*     */       case 14:
/* 312 */         return "ებრაულ";
/*     */       case 15:
/* 314 */         return "უნგრეთის";
/*     */       case 16:
/* 316 */         return "ისლანდიის";
/*     */       case 6:
/* 318 */         return "იტალიურ";
/*     */       case 18:
/* 320 */         return "იაპონიის";
/*     */       case 19:
/* 322 */         return "კორეის";
/*     */       case 20:
/* 324 */         return "ნორვეგიის";
/*     */       case 8:
/* 326 */         return "პოლონეთის";
/*     */       case 21:
/* 328 */         return "პორტუგალური";
/*     */       case 25:
/* 330 */         return "რუმინული";
/*     */       case 5:
/* 332 */         return "რუსულ";
/*     */       case 26:
/* 334 */         return "სლოვენიის";
/*     */       case 7:
/* 336 */         return "ესპანელი";
/*     */       case 22:
/* 338 */         return "შვედურ";
/*     */       case 35:
/* 340 */         return "ტაილანდური";
/*     */       case 33:
/* 342 */         return "თურქულენოვანი";
/*     */       case 31:
/* 344 */         return "თურქი (RTL)";
/*     */       case 1:
/* 346 */         return "თურქი";
/*     */       case 36:
/* 348 */         return "ვიეტნამის";
/*     */     } 
/*     */     
/* 351 */     return "უცნობია ენა";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 356 */     return 27;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 362 */     return new Font(ms_FontName, ms_FontStyle, ms_FontSize);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoKAGE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */