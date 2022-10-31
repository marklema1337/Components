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
/*     */ public class JLbsCultureInfoTKTM
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  16 */   public static final String[] MONTHNAMES = new String[] { "Ýalňyş", "Ýanwar", "Fewral", "Mart", "Aprel", "Maý", "Iýun", "Iýul", "Awgust", 
/*  17 */       "Sentýabr", "Oktýabr", "Noýabr", "Dekabr" };
/*     */   
/*  19 */   public static final String[] DAYNAMES = new String[] { "Ýalňyş", "Yekşenbe", "Duuşenbe", "Sişenbe", "Çarşenbe", "Penşenbe", "Annna", 
/*  20 */       "Şenbe" };
/*     */   
/*  22 */   public static final String[] SHORTDAYNAMES = new String[] { "YLN", "Yek", "Duu", "Siş", "Çar", "Pen", "Ann", "Şen" };
/*     */   
/*  24 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Nol", "[1] Bir", "[2] İki", "[3] Üç", "[4] Dört", "[5] Bäş", "[6] Alty", 
/*  25 */       "[7] Ýedi", "[8] Sekiz", "[9] Dokuz", "[10] On", "[20] Ýigrimi", "[30] Otuz", "[40] Kyrk", "[50] Elli", "[60] Altmyş", 
/*  26 */       "[70] Ýetmiş", "[80] Segsen", "[90] Togsan", "[100] Ýüz" };
/*     */   
/*  28 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Müň|", "[2] ~|Million|", "[3] ~|Milliard|", "[4] ~|Trilyon|", 
/*  29 */       "[11001] Müň|" };
/*     */   
/*  31 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Ýüz", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  35 */     return ILbsCultureConstants.LANGUAGEPREFIXES[33];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  40 */     return "(B)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  45 */     return "(A)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  50 */     return (iEra > 0) ? 
/*  51 */       "MS" : 
/*  52 */       "MÖ";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  57 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  65 */     if (iMonth > 0 && iMonth <= 12)
/*  66 */       return MONTHNAMES[iMonth]; 
/*  67 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  72 */     if (iDay > 0 && iDay <= 7)
/*  73 */       return SHORTDAYNAMES[iDay]; 
/*  74 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  82 */     if (iDay > 0 && iDay <= 7)
/*  83 */       return DAYNAMES[iDay]; 
/*  84 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/*  89 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  94 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/*  99 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 104 */     return "Hawa";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 109 */     return "Yok";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 114 */     return "Bolýar";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 119 */     return "Vazgeç";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 124 */     return "Sakla";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 129 */     return "Sagat Guşaklygy  ";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 134 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 137 */         return "Türkmençe";
/*     */       case 102:
/* 139 */         return "Kullanıcı Adı";
/*     */       case 103:
/* 141 */         return "Şifre";
/*     */       case 119:
/* 143 */         return "Farklı Kullanıcı Adına Giriş";
/*     */       case 120:
/* 145 */         return "Gelişmiş";
/*     */       case 104:
/* 147 */         return "Kurum";
/*     */       case 105:
/* 149 */         return "Dönem";
/*     */       case 106:
/* 151 */         return "Dil";
/*     */       case 107:
/* 153 */         return "&Tamam";
/*     */       case 108:
/* 155 */         return "&Vazgeç";
/*     */       case 109:
/* 157 */         return "Kurum ve Dönem";
/*     */       case 110:
/* 159 */         return "Bağlı Kurum";
/*     */       case 111:
/* 161 */         return "Caps Lock tuşu açık.";
/*     */       
/*     */       case 1000:
/* 164 */         return "Hata";
/*     */       case 1001:
/* 166 */         return "Geçersiz kullanıcı adı ve/veya şifre. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1002:
/* 168 */         return "Bu işlemi yapabilmek için 'yönetici' yetkisine sahip olmanız gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1003:
/* 170 */         return "Kurum tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1004:
/* 172 */         return "Bu kurumu kullanma yetkiniz yok. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1005:
/* 174 */         return "Belirlenen dönem bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1006:
/* 176 */         return "Güncel dönem bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1007:
/* 178 */         return "Sistem tablolarının ve uygulamanın sürümleri farklı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1008:
/* 180 */         return "Belirlenen kurum bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1009:
/* 182 */         return "Sistem tabloları bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1010:
/* 184 */         return "Sisteme giriş yapılamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1011:
/* 186 */         return "Kullanıcı hesabı bloke edilmiş. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1012:
/* 188 */         return "Geçersiz IP numarası. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1013:
/* 190 */         return "Şifrenizin kullanım süresi dolmuş. Lütfen şifrenizi güncelleyiniz.";
/*     */       case 1014:
/* 192 */         return "Veritabanı bağlantı hatası. Veritabanı ayarlarını kontrol ediniz.";
/*     */       case 1015:
/* 194 */         return "Dönem tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1036:
/* 196 */         return "Dönem veritabanı araçlarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1035:
/* 198 */         return "Kurum veritabanı araçlarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1016:
/* 200 */         return "Lütfen çalışmak istediğiniz kurumu belirtin!";
/*     */       case 1017:
/* 202 */         return "Sistem tablolarının sürümü eski olabilir. Lütfen güncelleyip tekrar deneyiniz.";
/*     */       case 1018:
/* 204 */         return "Sisteme Giriş Hatası";
/*     */       case 1019:
/* 206 */         return "Kanal yaratılamadı.";
/*     */       case 1020:
/* 208 */         return "Şu adrese bağlanılamadı: ";
/*     */       case 1021:
/* 210 */         return "Bu geçerli bir bağlantı adresi değil: ";
/*     */       case 1022:
/* 212 */         return "İstemci Hatası:\n";
/*     */       case 1023:
/* 214 */         return "Sunucu Hatası:\n";
/*     */       case 1024:
/* 216 */         return "Kodu şu olan bir hata oluştu:\n";
/*     */       case 1025:
/* 218 */         return "Uygulamayı kullanmak için geçerli bir lisansınız bulunmuyor.\nLütfen geçerli bir lisans kurulumu yapınız!";
/*     */       case 1026:
/* 220 */         return "Lisansınız bu uygulama dilini içermiyor!";
/*     */       case 1027:
/* 222 */         return "Lisansınızın kullanım süresi dolmuştur!";
/*     */       case 1028:
/* 224 */         return "Lisansınız uygulamanın bu işletim sisteminde çalışmasını desteklemiyor! Lütfen desteklenen işletim sistemleri için lisans anlaşmanıza bakınız!";
/*     */       case 1029:
/* 226 */         return "Lisansınız bu veri tabanını içermiyor!";
/*     */       case 1030:
/* 228 */         return "Yeni oturum yaratılamadı! Kullanıcı girişi engellenmiştir!";
/*     */       case 1031:
/* 230 */         return "Uyarlama tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1032:
/* 232 */         return "Şu anda başka bir \"Logo Report Viewer\" uygulaması çalışmaktadır. Lütfen başka bir rapor görüntülemek için \"Logo Report Viewer\" üzerindeki \"Aç\" düğmesini kullanınız.";
/*     */       case 1033:
/* 234 */         return "İstemci uyumluluk hatası. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1034:
/* 236 */         return "Uyarlama paketi geçerli değil. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 112:
/* 238 */         return "Giriş bilgilerimi unuttum";
/*     */       case 113:
/* 240 */         return "E-Mail adresi";
/*     */       case 114:
/* 242 */         return "Lütfen geçerli bir mail adresi giriniz.";
/*     */       case 115:
/* 244 */         return "Giriş bilgileriniz mail adresinize gönderilmiştir.";
/*     */       case 116:
/* 246 */         return "Girdiğiniz mail adresi sistemimizde kayıtlı değildir. Lütfen tekrar deneyin.";
/*     */       case 117:
/* 248 */         return "Beklenmeyen bir hata oluştu. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 118:
/* 250 */         return "Bilgi";
/*     */       case 1037:
/* 252 */         return "Diğer kullanıcı bilgileri geçersiz.";
/*     */       case 1040:
/* 254 */         return "Güvenlik sorusu yanlış cevaplandı. Lütfen tekrar deneyin";
/*     */     } 
/* 256 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 261 */     switch (language) {
/*     */       
/*     */       case 34:
/* 264 */         return "Arnavutça";
/*     */       case 32:
/* 266 */         return "Arapça (JO)";
/*     */       case 24:
/* 268 */         return "Arapça (SA)";
/*     */       case 38:
/* 270 */         return "Arapça (EG)";
/*     */       case 29:
/* 272 */         return "Azerbaycanca";
/*     */       case 28:
/* 274 */         return "Bulgarca";
/*     */       case 17:
/* 276 */         return "Çince";
/*     */       case 27:
/* 278 */         return "Hırvatça";
/*     */       case 9:
/* 280 */         return "Çekçe";
/*     */       case 10:
/* 282 */         return "Danca";
/*     */       case 11:
/* 284 */         return "Hollandaca";
/*     */       case 2:
/* 286 */         return "İngilizce";
/*     */       case 40:
/* 288 */         return "İngilizce (IN)";
/*     */       case 12:
/* 290 */         return "Estonyaca";
/*     */       case 30:
/* 292 */         return "Farsça";
/*     */       case 13:
/* 294 */         return "Fince";
/*     */       case 3:
/* 296 */         return "Fransızca";
/*     */       case 4:
/* 298 */         return "Almanca";
/*     */       case 37:
/* 300 */         return "Gürcüce";
/*     */       case 23:
/* 302 */         return "Yunanca";
/*     */       case 14:
/* 304 */         return "İbranice";
/*     */       case 15:
/* 306 */         return "Macarca";
/*     */       case 16:
/* 308 */         return "İzlandaca";
/*     */       case 6:
/* 310 */         return "İtalyanca";
/*     */       case 18:
/* 312 */         return "Japonca";
/*     */       case 19:
/* 314 */         return "Korece";
/*     */       case 20:
/* 316 */         return "Norveççe";
/*     */       case 8:
/* 318 */         return "Lehçe - Polca";
/*     */       case 21:
/* 320 */         return "Portekizce";
/*     */       case 25:
/* 322 */         return "Rumence";
/*     */       case 5:
/* 324 */         return "Rusça";
/*     */       case 26:
/* 326 */         return "Slovence";
/*     */       case 7:
/* 328 */         return "İspanyolca";
/*     */       case 22:
/* 330 */         return "İsveççe";
/*     */       case 35:
/* 332 */         return "Tay Dili";
/*     */       case 33:
/* 334 */         return "Türkmençe";
/*     */       case 31:
/* 336 */         return "Türkçe (RTL)";
/*     */       case 1:
/* 338 */         return "Türkçe";
/*     */       case 36:
/* 340 */         return "Vietnamca";
/*     */     } 
/* 342 */     return "Bilinmeyen dil";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 347 */     return 107;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoTKTM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */