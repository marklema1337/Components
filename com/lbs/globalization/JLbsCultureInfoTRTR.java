/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import com.lbs.util.CalendarCultureUtil;
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
/*     */ public class JLbsCultureInfoTRTR
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getLanguagePrefix() {
/*  21 */     return "TRTR";
/*     */   }
/*     */   
/*  24 */   public static final String[] MONTHNAMES = new String[] { "Geçersiz", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", 
/*  25 */       "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık" };
/*     */   
/*  27 */   public static final String[] DAYNAMES = new String[] { "Geçersiz", "Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi" };
/*     */   
/*  29 */   public static final String[] SHORTDAYNAMES = new String[] { "GÇS", "Paz", "Pzt", "Sal", "Çar", "Per", "Cum", "Cmt" };
/*     */   
/*  31 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Sıfır", "[1] Bir", "[2] İki", "[3] Üç", "[4] Dört", "[5] Beş", "[6] Altı", 
/*  32 */       "[7] Yedi", "[8] Sekiz", "[9] Dokuz", "[10] On", "[20] Yirmi", "[30] Otuz", "[40] Kırk", "[50] Elli", "[60] Altmış", 
/*  33 */       "[70] Yetmiş", "[80] Seksen", "[90] Doksan", "[100] Yüz" };
/*  34 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Bin|", "[2] ~|Milyon|", "[3] ~|Milyar|", "[4] ~|Trilyon|", 
/*  35 */       "[11001] Bin|" };
/*  36 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Yüz", "[2] ~|#", "[3] ~|#" };
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
/*     */   
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/*  73 */     switch (calendarType) {
/*     */       
/*     */       case 1:
/*  76 */         return CalendarCultureUtil.getPersianTurkishMonthFullName(iMonth);
/*     */       case 0:
/*     */       case 2:
/*     */       case 3:
/*  80 */         return getMonthFullName(iMonth);
/*     */     } 
/*  82 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  87 */     if (iDay > 0 && iDay <= 7)
/*  88 */       return SHORTDAYNAMES[iDay]; 
/*  89 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay, int calendarType) {
/*  95 */     switch (calendarType) {
/*     */       
/*     */       case 1:
/*  98 */         return CalendarCultureUtil.getPersianTurkishDayFullName(iDay);
/*     */       case 0:
/*     */       case 2:
/*     */       case 3:
/* 102 */         return getDayFullName(iDay);
/*     */     } 
/* 104 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay, int calendarType) {
/* 110 */     switch (calendarType) {
/*     */       
/*     */       case 1:
/* 113 */         return CalendarCultureUtil.getPersianTurkishDayShortName(iDay);
/*     */       case 0:
/*     */       case 2:
/*     */       case 3:
/* 117 */         return getDayShortName(iDay);
/*     */     } 
/* 119 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/* 129 */     if (iDay > 0 && iDay <= 7)
/* 130 */       return DAYNAMES[iDay]; 
/* 131 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 136 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 141 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 146 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 151 */     return "Evet";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 156 */     return "Hayır";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 161 */     return "Tamam";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 166 */     return "Vazgeç";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 171 */     return "Kaydet";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 176 */     return "Saat Dilimi";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 181 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 184 */         return "Türkçe";
/*     */       case 102:
/* 186 */         return "Kullanıcı Adı";
/*     */       case 103:
/* 188 */         return "Şifre";
/*     */       case 119:
/* 190 */         return "Farklı Kullanıcı Adına Giriş";
/*     */       case 104:
/* 192 */         return "Kurum";
/*     */       case 105:
/* 194 */         return "Dönem";
/*     */       case 106:
/* 196 */         return "Dil";
/*     */       case 107:
/* 198 */         return "&Tamam";
/*     */       case 108:
/* 200 */         return "&Vazgeç";
/*     */       case 109:
/* 202 */         return "Kurum ve Dönem";
/*     */       case 110:
/* 204 */         return "Bağlı Kurum";
/*     */       case 111:
/* 206 */         return "Caps Lock tuşu açık.";
/*     */       case 1000:
/* 208 */         return "Hata";
/*     */       case 1001:
/* 210 */         return "Geçersiz kullanıcı adı ve/veya şifre. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1002:
/* 212 */         return "Bu işlemi yapabilmek için 'yönetici' yetkisine sahip olmanız gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1003:
/* 214 */         return "Kurum tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1004:
/* 216 */         return "Bu kurumu kullanma yetkiniz yok. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1005:
/* 218 */         return "Belirlenen dönem bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1006:
/* 220 */         return "Güncel dönem bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1007:
/* 222 */         return "Sistem tablolarının ve uygulamanın sürümleri farklı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1008:
/* 224 */         return "Belirlenen kurum bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1009:
/* 226 */         return "Sistem tabloları bulunamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1010:
/* 228 */         return "Sisteme giriş yapılamadı. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1011:
/* 230 */         return "Kullanıcı hesabı bloke edilmiş. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1012:
/* 232 */         return "Geçersiz IP numarası. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1013:
/* 234 */         return "Şifrenizin kullanım süresi dolmuş. Lütfen şifrenizi güncelleyiniz.";
/*     */       case 1014:
/* 236 */         return "Veritabanı bağlantı hatası. Veritabanı ayarlarını kontrol ediniz.";
/*     */       case 1015:
/* 238 */         return "Dönem tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1036:
/* 240 */         return "Dönem veritabanı araçlarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1035:
/* 242 */         return "Kurum veritabanı araçlarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1016:
/* 244 */         return "Lütfen çalışmak istediğiniz kurumu belirtin!";
/*     */       case 1017:
/* 246 */         return "Sistem tablolarının sürümü eski olabilir. Lütfen güncelleyip tekrar deneyiniz.";
/*     */       case 1018:
/* 248 */         return "Sisteme Giriş Hatası";
/*     */       case 1019:
/* 250 */         return "Kanal yaratılamadı.";
/*     */       case 1020:
/* 252 */         return "Şu adrese bağlanılamadı: ";
/*     */       case 1021:
/* 254 */         return "Bu geçerli bir bağlantı adresi değil: ";
/*     */       case 1022:
/* 256 */         return "İstemci Hatası:\n";
/*     */       case 1023:
/* 258 */         return "Sunucu Hatası:\n";
/*     */       case 1024:
/* 260 */         return "Kodu şu olan bir hata oluştu:\n";
/*     */       case 1025:
/* 262 */         return "Uygulamayı kullanmak için geçerli bir lisansınız bulunmuyor.\nLütfen geçerli bir lisans kurulumu yapınız!";
/*     */       case 1026:
/* 264 */         return "Lisansınız bu uygulama dilini içermiyor!";
/*     */       case 1027:
/* 266 */         return "Lisansınızın kullanım süresi dolmuştur!";
/*     */       case 1028:
/* 268 */         return "Lisansınız uygulamanın bu işletim sisteminde çalışmasını desteklemiyor! Lütfen desteklenen işletim sistemleri için lisans anlaşmanıza bakınız!";
/*     */       case 1029:
/* 270 */         return "Lisansınız bu veri tabanını içermiyor!";
/*     */       case 1039:
/* 272 */         return "Lisans kullanıcı sayısı kapasiteniz dolmuştur. Sisteme giriş yapamazsınız!";
/*     */       case 1030:
/* 274 */         return "Yeni oturum oluşturulamadı! Kullanıcı girişi engellenmiştir!";
/*     */       case 1031:
/* 276 */         return "Uyarlama tablolarının güncellenmesi gerekiyor. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1032:
/* 278 */         return "Şu anda başka bir \"Logo Report Viewer\" uygulaması çalışmaktadır. Lütfen başka bir rapor görüntülemek için \"Logo Report Viewer\" üzerindeki \"Aç\" düğmesini kullanınız.";
/*     */       case 1033:
/* 280 */         return "İstemci uyumluluk hatası. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 1034:
/* 282 */         return "Uyarlama paketi geçerli değil. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 112:
/* 284 */         return "Giriş bilgilerimi unuttum";
/*     */       case 113:
/* 286 */         return "E-Mail adresi";
/*     */       case 114:
/* 288 */         return "Lütfen geçerli bir mail adresi giriniz.";
/*     */       case 115:
/* 290 */         return "Giriş bilgileriniz mail adresinize gönderilmiştir.";
/*     */       case 116:
/* 292 */         return "Girdiğiniz mail adresi sistemimizde kayıtlı değildir. Lütfen tekrar deneyin.";
/*     */       case 117:
/* 294 */         return "Beklenmeyen bir hata oluştu. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 118:
/* 296 */         return "Bilgi";
/*     */       case 1037:
/* 298 */         return "Diğer kullanıcı bilgileri geçersiz.";
/*     */       case 1038:
/* 300 */         return "Süreç yönetimi veritabanı tanımları güncel değildir. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 120:
/* 302 */         return "Gelişmiş";
/*     */       case 1040:
/* 304 */         return "Güvenlik sorusu yanlış cevaplandı. Lütfen tekrar deneyin";
/*     */       case 1041:
/* 306 */         return "Şifre alanı boş olamaz";
/*     */       case 1064:
/* 308 */         return "Lisans paketinizde menüye ait bir tanım bulunmuyor. Sisteme giriş yapamazsınız!";
/*     */       case 1065:
/* 310 */         return "Sistemde T.C. Kimlik No tanımlı değil.";
/*     */       case 1066:
/* 312 */         return "Elektronik imza doğrulanamadı!";
/*     */       case 1067:
/* 314 */         return "Elektronik imza özet değeri geçersiz!";
/*     */       case 1070:
/* 316 */         return "Oauth Token geçersiz!";
/*     */       case 1068:
/* 318 */         return "Kullanıcı tanımınıza takılı bir lisans tanımı bulunamamıştır!";
/*     */       case 1042:
/* 320 */         return "Şifreniz geçici durumdadır, güncellenmesi gerekmektedir!";
/*     */       case 1069:
/* 322 */         return "Seçilen dil için, süreç yönetimi veritabanı tanımları güncelleme işlemi devam ediyor. Lütfen bir müddet bekleyip tekrar deneyiniz.";
/*     */       case 1043:
/* 324 */         return "Lisansınız bu kullanıcı tipi için girişi desteklemiyor. Sisteme giriş yapamazsınız!";
/*     */       case 1044:
/* 326 */         return "Sistem bu kullanıcı tipi için girişi desteklemiyor. Sisteme giriş yapamazsınız!";
/*     */       case 1045:
/* 328 */         return "Server bakım modunda olduğundan giriş yapamazsınız.\nLütfen sistem yöneticinizle görüşün.";
/*     */       case 1046:
/* 330 */         return "Sistem Yöneticiniz lisans kapsamında değişlik yapmıştır, ürünü kullanabilmek için lisans bilgilerinizin güncellenmesi gerekmektedir. \nSistem Yöneticiniz ile iletişime geçiciniz.";
/*     */       case 121:
/* 332 */         return "Güvenlik Kodu Geçerli Değil. Lütfen Tekrar Deneyiniz!!!";
/*     */       case 122:
/* 334 */         return "Beklenmeyen bir hata oluştu. Lütfen sistem yöneticinizle görüşün.";
/*     */       case 123:
/* 336 */         return "Güvenlik Kodunun Süresi Dolmuştur. Lütfen Tekrar Deneyiniz!!!";
/*     */       case 124:
/* 338 */         return "Mail/SMS Hesabınıza Gönderilen Kodu Giriniz";
/*     */       case 125:
/* 340 */         return "Güvenlik Kodu";
/*     */       case 126:
/* 342 */         return "Giriş Yapılıyor. Lütfen Bekleyiniz...";
/*     */       case 1071:
/* 344 */         return "Geçersiz kullanıcı adı veya şifre!";
/*     */       case 1072:
/* 346 */         return "Aynı kullanıcı birden fazla oturum açamaz!";
/*     */       case 1073:
/* 348 */         return "Aynı kullanıcı oturum sayısından fazla oturum açamaz!";
/*     */       case 1074:
/* 350 */         return "Uygulama sizin için hazırlanıyor!";
/*     */       case 2001:
/* 352 */         return "<br><br><center><b>j-Platform ile tüm iş süreçlerinizi ofis dışından da takip edebilir, istediğiniz bilgilere herhangi bir web\r\ntarayıcısı ile her yerden her an ulaşabilirsiniz!</b></center>\r\n<br>Rekabetçi bir iş ortamında büyümek, hem şirket içerisinde hem de bayiler ve iletişimde olunan tüm kanallarla\r\ndoğru ve güncel bilgileri güvenli biçimde paylaşmayı gerektirir. Hiç bir işletmenin yapısı ve iş yapış şeklinin aynı\r\nolamayacağından yola çıkarak tasarlanan, şirketlerin ihtiyaçlarına göre şekillendirilebilecek esnek yapıdaki j-Platform,\r\nçok sayıda konfigürasyon seçeneği ile iş süreçlerinizi tam olarak modelleyebilmenizi sağlar. İşletmenizin ihtiyacına\r\ngöre istediğiniz fonksiyonu j-Platform'a dahil edebilir, kullanıcı sayılarını istediğiniz gibi artırabilirsiniz. İş\r\nuygulamaları için yapacağınız ilk yatırım maliyetlerini düşürmeyi hedefleyerek geliştirilen j-Platform, esnek lisanslama\r\nseçenekleriyle işletmenizin ihtiyaçlarına hızla cevap verir.\r\n<br>\r\n<br>Logo'nun İnsan Kaynakları, Logo Mind İş Analitiği, Logo CRM Çözümleri gibi farklı uygulamalarıyla tam entegre\r\nşekilde çalışabilen j-Platform ile kurumsal süreçlerinizi iyileştirerek iş verimliliğinizi artırabilirsiniz.\r\n<br>\r\n<br>Ayrıca Logo Çözüm Ortakları'nın geliştirmiş olduğu uyarlamalar hızlı ve ekonomik çözümler sunar. İşletmenize\r\nve sektörünüze özel bu uygulamalarla ERP fonksiyonlarınızı zengileştirebilirsiniz .";
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
/* 367 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 372 */     switch (language) {
/*     */       
/*     */       case 34:
/* 375 */         return "Arnavutça";
/*     */       case 32:
/* 377 */         return "Arapça (JO)";
/*     */       case 24:
/* 379 */         return "Arapça (SA)";
/*     */       case 38:
/* 381 */         return "Arapça (EG)";
/*     */       case 29:
/* 383 */         return "Azerbaycanca";
/*     */       case 28:
/* 385 */         return "Bulgarca";
/*     */       case 17:
/* 387 */         return "Çince";
/*     */       case 27:
/* 389 */         return "Hırvatça";
/*     */       case 9:
/* 391 */         return "Çekçe";
/*     */       case 10:
/* 393 */         return "Danca";
/*     */       case 11:
/* 395 */         return "Hollandaca";
/*     */       case 2:
/* 397 */         return "İngilizce";
/*     */       case 40:
/* 399 */         return "İngilizce (IN)";
/*     */       case 12:
/* 401 */         return "Estonyaca";
/*     */       case 30:
/* 403 */         return "Farsça";
/*     */       case 13:
/* 405 */         return "Fince";
/*     */       case 3:
/* 407 */         return "Fransızca";
/*     */       case 4:
/* 409 */         return "Almanca";
/*     */       case 37:
/* 411 */         return "Gürcüce";
/*     */       case 23:
/* 413 */         return "Yunanca";
/*     */       case 14:
/* 415 */         return "İbranice";
/*     */       case 15:
/* 417 */         return "Macarca";
/*     */       case 16:
/* 419 */         return "İzlandaca";
/*     */       case 6:
/* 421 */         return "İtalyanca";
/*     */       case 18:
/* 423 */         return "Japonca";
/*     */       case 19:
/* 425 */         return "Korece";
/*     */       case 20:
/* 427 */         return "Norveççe";
/*     */       case 8:
/* 429 */         return "Lehçe - Polca";
/*     */       case 21:
/* 431 */         return "Portekizce";
/*     */       case 25:
/* 433 */         return "Rumence";
/*     */       case 5:
/* 435 */         return "Rusça";
/*     */       case 26:
/* 437 */         return "Slovence";
/*     */       case 7:
/* 439 */         return "İspanyolca";
/*     */       case 22:
/* 441 */         return "İsveççe";
/*     */       case 35:
/* 443 */         return "Tay Dili";
/*     */       case 33:
/* 445 */         return "Türkmence";
/*     */       case 31:
/* 447 */         return "Türkçe (RTL)";
/*     */       case 1:
/* 449 */         return "Türkçe";
/*     */       case 36:
/* 451 */         return "Vietnamca";
/*     */     } 
/*     */     
/* 454 */     return "Bilinmeyen dil";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 459 */     return 160;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoTRTR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */