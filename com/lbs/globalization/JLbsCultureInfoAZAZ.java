/*     */ package com.lbs.globalization;
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
/*     */ public class JLbsCultureInfoAZAZ
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getLanguagePrefix() {
/*  18 */     return "AZAZ";
/*     */   }
/*     */   
/*  21 */   public static final String[] MONTHNAMES = new String[] { "Etibarsız", "Yanvar", "Fevral", "Mart", "Aprel", "May", "İyun", "İyul", "Avqust", 
/*  22 */       "Sentyabr", "Oktyabr", "Noyabr", "Dekabr" };
/*     */   
/*  24 */   public static final String[] DAYNAMES = new String[] { "Etibarsız", "Bazar", "Bazar Ertəsi", "Çərşənbə Axşamı", "Çərşənbə", "Cümə Axşamı", 
/*  25 */       "Cümə", "Şənbə" };
/*     */   
/*  27 */   public static final String[] SHORTDAYNAMES = new String[] { "ETS", "Baz", "Bzt", "Çax", "Çər", "Cax", "Cüm", "Şən" };
/*     */   
/*  29 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Sıfır", "[1] Bir", "[2] İki", "[3] Üç", "[4] Dörd", "[5] Beş", "[6] Altı", 
/*  30 */       "[7] Yeddi", "[8] Səkkiz", "[9] Doqquz", "[10] On", "[20] İyirmi", "[30] Otuz", "[40] Qırx", "[50] Əlli", 
/*  31 */       "[60] Altmış", "[70] Yetmiş", "[80] Səksən", "[90] Doxsan", "[100] Üz" };
/*  32 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Min|", "[2] ~|Milyon|", "[3] ~|Milyard|", "[4] ~|Trilyon|", 
/*  33 */       "[11001] Min|" };
/*  34 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Üz", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  38 */     return "(B)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  43 */     return "(A)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  48 */     return (iEra > 0) ? 
/*  49 */       "MS" : 
/*  50 */       "MÖ";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  55 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  63 */     if (iMonth > 0 && iMonth <= 12)
/*  64 */       return MONTHNAMES[iMonth]; 
/*  65 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  70 */     if (iDay > 0 && iDay <= 7)
/*  71 */       return SHORTDAYNAMES[iDay]; 
/*  72 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  80 */     if (iDay > 0 && iDay <= 7)
/*  81 */       return DAYNAMES[iDay]; 
/*  82 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/*  87 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  92 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/*  97 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 102 */     return "Bəli";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 107 */     return "Xeyr";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 112 */     return "Bəli";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 117 */     return "İmtina Et";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 122 */     return "Qeyd Et";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 127 */     return "Saat Qurşağı";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 132 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 135 */         return "Azərbaycan Dili";
/*     */       case 102:
/* 137 */         return "İstifadəçi Adı";
/*     */       case 103:
/* 139 */         return "Şifrə";
/*     */       case 119:
/* 141 */         return "Fərqli İstifadəçi Adı İlə Giriş";
/*     */       case 120:
/* 143 */         return "Qabaqcıl";
/*     */       case 104:
/* 145 */         return "Firma";
/*     */       case 105:
/* 147 */         return "Dönəm";
/*     */       case 106:
/* 149 */         return "Dil";
/*     */       case 107:
/* 151 */         return "&Bəli";
/*     */       case 108:
/* 153 */         return "&İmtina Et";
/*     */       case 109:
/* 155 */         return "Firma və Dönəm";
/*     */       case 110:
/* 157 */         return "Bağlı Firma";
/*     */       case 111:
/* 159 */         return "Caps Lock düyməsi açıqdır.";
/*     */       case 1000:
/* 161 */         return "Xəta";
/*     */       case 1001:
/* 163 */         return "İstifadəçi adı və ya şifrə düzgün deyil. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1002:
/* 165 */         return "Bu əməliyyatı etmək üçün 'Administrator' səlahiyyətiniz olmalıdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1003:
/* 167 */         return "Firma cədvəllərinin yenilənməsi lazımdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1004:
/* 169 */         return "Bu firmadan istifadə səlahiyyətiniz yoxdur. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1005:
/* 171 */         return "Seçilən dönəm tapılmadı. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1006:
/* 173 */         return "Aktiv dönəm tapılmadı. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1007:
/* 175 */         return "Sistem cədvəllərinin və proqramın versiyası fərqlidir. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1008:
/* 177 */         return "Seçilən firma tapılmadı. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1009:
/* 179 */         return "Sistem cədvəlləri tapılmadı. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1010:
/* 181 */         return "Sistemə daxil oluna bilinmədi. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1011:
/* 183 */         return "İstifadəçi adı bloklanmışdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1012:
/* 185 */         return "Qəbul edilməyən IP ünvan. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1013:
/* 187 */         return "Şifrənizin istifadə müddəti bitmişdir. Zəhmət olmasa şifrənizi yeniləyin.";
/*     */       case 1014:
/* 189 */         return "Məlumat bazasına bağlanma xətası. Məlumat bazasına bağlantı parametrlərini yoxlayın.";
/*     */       case 1015:
/* 191 */         return "Dönəm cədvəllərinin yenilənması lazımdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1036:
/* 193 */         return "Dönəm ilə bağlı məlumat bazası vasitlərinin yenilənməsi lazımdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1035:
/* 195 */         return "Firma ilə bağlı məlumat bazası vasitlərinin yenilənməsi lazımdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1016:
/* 197 */         return "Zəhmət olmasa çalışmaq istədiyiniz firmanı seçin!";
/*     */       case 1017:
/* 199 */         return "Sistem cədvəllərinin versiyası köhnə ola blər. Zəhmət olmasa cədvəlləri yeniləyib yenidən yoxlayın.";
/*     */       case 1018:
/* 201 */         return "Sistemə Giriş Xətası";
/*     */       case 1019:
/* 203 */         return "Kanal yaradıla bilinmədi.";
/*     */       case 1020:
/* 205 */         return "Bu ünvana bağlanıla bilinmədi: ";
/*     */       case 1021:
/* 207 */         return "Bu doğru bağlantı ünvanı deyil: ";
/*     */       case 1022:
/* 209 */         return "Client Xətası:\n";
/*     */       case 1023:
/* 211 */         return "Server Xətası:\n";
/*     */       case 1024:
/* 213 */         return "Kodu bu olan xəta baş verdi:\n";
/*     */       case 1025:
/* 215 */         return "Porqramın istifadəsi üçün gərəkli lisenziyanız tapılmadı.\nZəhmət olmasa düzgün lisenziya məumatları daxil edərək proqramı qurun!";
/*     */       case 1026:
/* 217 */         return "Lisenziyanız bu proqram dilinə aid deyil!";
/*     */       case 1027:
/* 219 */         return "Lisenziyanızın istifadə müddəti bitmişdir!";
/*     */       case 1028:
/* 221 */         return "Lisenziyanız proqramın bu əməliyyat sistemində işləməsini dəstəkləmir! Zəhmət olmasa dəstəklənən əməliyyat sistemləri üçün lisenziya əldə edin!";
/*     */       case 1029:
/* 223 */         return "Lisenziyanız bu məlumat bazasına aid deyil!";
/*     */       case 1030:
/* 225 */         return "Yeni istifadəçi girişi yaradıla bilinmədi! İstifadəçi girişinə qadağa qoyulmuşdur!";
/*     */       case 1031:
/* 227 */         return "Dizayn cədvəllərinin yenilənməsi lazımdır. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1032:
/* 229 */         return "İndi başqa bir proqram \"Logo Report Viewer\" işləyir. Zəhmət olmasa başqa bir hesabata baxmaq üçün \"Logo Report Viewer\" üzərindəki \"Aç\" düyməsindən istifadə edin.";
/*     */       case 1033:
/* 231 */         return "Client uyğunsuzluq xətası.  Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 1034:
/* 233 */         return "Dizayn edilmiş paket etibarsızdır.  Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 112:
/* 235 */         return "Giriş məlumatlarımı unutdum";
/*     */       case 113:
/* 237 */         return "E-Mail ünvanı";
/*     */       case 114:
/* 239 */         return "Zəhmət olmasa mövcud bir e-mail ünvanı daxil edin.";
/*     */       case 115:
/* 241 */         return "Giriş məlumatlarınız e-mail ünvanınıza göndərilmişdir.";
/*     */       case 116:
/* 243 */         return "Daxil etdiyiniz e-mail ünvanı sistemdə mövcud deyil. Zəhmət olmasa yenidən yoxlayın.";
/*     */       case 117:
/* 245 */         return "Gözlənilməyən xəta baş verdi. Zəhmət olmasa sistem Administratoru ilə əlaqə saxlayın.";
/*     */       case 118:
/* 247 */         return "Məlumat";
/*     */       case 1037:
/* 249 */         return "Digər istifadəçi məlumatları etibarsızdır.";
/*     */       case 1040:
/* 251 */         return "Təhlükəsizlik suala düzgün cavab. Daha cəhd edin.";
/*     */       case 1039:
/* 253 */         return "Lisenziya istifadə limitiniz dolmuşdur. Sistemə daxil ola bilməzsiniz!";
/*     */       case 1038:
/* 255 */         return "Proseslərin idarəolunma məlumat bazasının tanımları yenilənməyib. Sistem administratoruna müraciət edin.";
/*     */       case 1041:
/* 257 */         return "Şifrə sahəsi boş ola bilməz.";
/*     */       case 1064:
/* 259 */         return "Lisenziya paketinizdə menyu ilə baglı bir qeyd yoxdur. Sistemə daxil ola bilməzsiniz!";
/*     */       case 1065:
/* 261 */         return "Sistemdə identifikasiya N-si qeydi (şəxsiyyət qeydiyyat N-si) yoxdur";
/*     */       case 1066:
/* 263 */         return "Elektron imza təsdiqlənmədi !";
/*     */       case 1067:
/* 265 */         return "Elektron imza parametrləri xətalıdır !";
/*     */       case 1068:
/* 267 */         return "İstifadəçi adınıza uyğun  lisenziya qeydi aşkar olunmadı !";
/*     */       case 1043:
/* 269 */         return "Lisenziyanız bu istifadəçi tipi üçün girişi dəstəkləmir. Sistemə daxil ola bilməzsiniz!";
/*     */     } 
/* 271 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 276 */     switch (language) {
/*     */       
/*     */       case 34:
/* 279 */         return "Albanca";
/*     */       case 32:
/* 281 */         return "Ərəbcə (JO)";
/*     */       case 24:
/* 283 */         return "Ərəbcə (SA)";
/*     */       case 38:
/* 285 */         return "Ərəbcə (EG)";
/*     */       case 29:
/* 287 */         return "Azərbaycan dili";
/*     */       case 28:
/* 289 */         return "Bolqarca";
/*     */       case 17:
/* 291 */         return "Çincə";
/*     */       case 27:
/* 293 */         return "Xorvatca";
/*     */       case 9:
/* 295 */         return "Çex dili";
/*     */       case 10:
/* 297 */         return "Danca";
/*     */       case 11:
/* 299 */         return "Hollandaca";
/*     */       case 2:
/* 301 */         return "İngiliscə";
/*     */       case 40:
/* 303 */         return "İngiliscə (IN)";
/*     */       case 12:
/* 305 */         return "Estonyaca";
/*     */       case 30:
/* 307 */         return "Farsça";
/*     */       case 13:
/* 309 */         return "Fincə";
/*     */       case 3:
/* 311 */         return "Fransızca";
/*     */       case 4:
/* 313 */         return "Almanca";
/*     */       case 37:
/* 315 */         return "Gürcücə";
/*     */       case 23:
/* 317 */         return "Yunanca";
/*     */       case 14:
/* 319 */         return "İvrit";
/*     */       case 15:
/* 321 */         return "Macarca";
/*     */       case 16:
/* 323 */         return "İslandica";
/*     */       case 6:
/* 325 */         return "İtalyanca";
/*     */       case 18:
/* 327 */         return "Yaponca";
/*     */       case 19:
/* 329 */         return "Korece";
/*     */       case 20:
/* 331 */         return "Norveçcə";
/*     */       case 8:
/* 333 */         return "Polyak";
/*     */       case 21:
/* 335 */         return "Portuqalca";
/*     */       case 25:
/* 337 */         return "Rumınca";
/*     */       case 5:
/* 339 */         return "Rusça";
/*     */       case 26:
/* 341 */         return "Slovence";
/*     */       case 7:
/* 343 */         return "İspanca";
/*     */       case 22:
/* 345 */         return "İsveçcə";
/*     */       case 35:
/* 347 */         return "Tay Dili";
/*     */       case 33:
/* 349 */         return "Türkməncə";
/*     */       case 31:
/* 351 */         return "Türkcə (RTL)";
/*     */       case 1:
/* 353 */         return "Türkcə";
/*     */       case 36:
/* 355 */         return "Vyetnamca";
/*     */     } 
/* 357 */     return "Bilinməyən dil";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 362 */     return 21;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoAZAZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */