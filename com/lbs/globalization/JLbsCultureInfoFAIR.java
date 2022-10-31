/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import com.ghasemkiani.util.SimplePersianCalendar;
/*     */ import java.awt.ComponentOrientation;
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
/*     */ public class JLbsCultureInfoFAIR
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  19 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|صد", "[2] ~|#", "[3] ~|#" };
/*  20 */   protected static final String[] NUMBERNAMES = new String[] { "[0] صفر", "[1] یک", "[2] دو", "[3] سه", "[4] چهار", "[5] پنج", "[6] شش", 
/*  21 */       "[7] هفت", "[8] هشت", "[9] نه", "[10] ده", "[20] بیست", "[30] سی", "[40] چهل", "[50] پنجاه", "[60] شصت", "[70] هفتاد", 
/*  22 */       "[80] هشتاد", "[90] نود", "[100] صد" };
/*  23 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|هزار|", "[2] ~|میلیون|", "[3] ~|میلیارد|", "[4] ~|تریلیون|", 
/*  24 */       "[11001] هزار|" };
/*     */ 
/*     */   
/*  27 */   protected static final String[] SHORTDAYNAMES = new String[] { "ش", "ی", "د", "س", "چ", "پ", "ج" };
/*     */   
/*  29 */   protected static final String[] SHORTDAYNAMES2 = new String[] { "ج", "پ", "چ", "س", "د", "ی", "ش" };
/*     */   
/*  31 */   protected static final String[] LONGDAYNAMES = new String[] { "شنبه", "یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه" };
/*     */   
/*  33 */   protected static String[] m_WindowText = new String[] { "فارسی", "نام کاربر", "رمز عبور", "شرکت", "دوره", "زبان برنامه", "تائید", "انصراف", 
/*  34 */       "شرکت و دوره" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  43 */     return "FAIR";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/*  48 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  53 */     if (iDay <= 0)
/*  54 */       return "بی اعتبار"; 
/*  55 */     return SimplePersianCalendar.getPersianWeekDayName(iDay - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  60 */     if (iDay > 0 && iDay <= 7)
/*  61 */       return SHORTDAYNAMES[toCalendarPanelWeekday(iDay)]; 
/*  62 */     return "بی اعتبار";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayLongName(int iDay) {
/*  67 */     if (iDay > 0 && iDay <= 7)
/*  68 */       return LONGDAYNAMES[toCalendarPanelWeekday(iDay)]; 
/*  69 */     return "بی اعتبار";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  75 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  80 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  85 */     return SimplePersianCalendar.getPersianMonthName(iMonth - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/*  90 */     return getMonthFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iMonth, int calendarType) {
/*  95 */     return getDayFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iMonth, int calendarType) {
/* 100 */     return getDayShortName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 105 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 110 */     return ComponentOrientation.RIGHT_TO_LEFT;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/* 115 */     return "(بد)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/* 120 */     return "(بس)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/* 125 */     return (iEra > 0) ? 
/* 126 */       "ق م" : 
/* 127 */       "ب م";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 132 */     return "بلی";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 137 */     return "خیر";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 142 */     return "انصراف";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 147 */     return "تائید";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 152 */     return "خیر";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 157 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 160 */         return "فارسی";
/*     */       case 102:
/* 162 */         return "نام کاربر";
/*     */       case 103:
/* 164 */         return "رمز عبور";
/*     */       case 104:
/* 166 */         return "شرکت";
/*     */       case 105:
/* 168 */         return "دوره";
/*     */       case 106:
/* 170 */         return "زبان خارجه";
/*     */       case 107:
/* 172 */         return "تائید";
/*     */       case 108:
/* 174 */         return "انصراف";
/*     */       case 109:
/* 176 */         return "شرکت و دوره";
/*     */ 
/*     */       
/*     */       case 1000:
/* 180 */         return "خطا";
/*     */       case 1001:
/* 182 */         return "نام کاربر و یا رمز عبور اشتباه می باشد";
/*     */       case 1002:
/* 184 */         return "شما دسترسی مدیر سیستم را ندارید. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1003:
/* 186 */         return "نسخه قدیمی جداول سیستمی، لطفا به روز رسانی کنید";
/*     */       case 1004:
/* 188 */         return "شما دسترسی به این شرکت را ندارید. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1005:
/* 190 */         return "دوره مورد نظر یافت نشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1006:
/* 192 */         return "دوره فعال یافت نشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1007:
/* 194 */         return "نسجه جداول بانک اطلاعاتی با نسخه نرم افزار هماهنگ نمی باشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1008:
/* 196 */         return "جداول سیستمی یافت نشد";
/*     */       case 1009:
/* 198 */         return "جدوال شرکت یافت نشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1010:
/* 200 */         return "کاربر نمی تواند وارد سیستم شود. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1011:
/* 202 */         return "این کاربر بلوکه شده است . لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1012:
/* 204 */         return "شماره IP نا معتبر است. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1013:
/* 206 */         return "دوره اعتبار رمزتان تمام شده است. لطفاً رمز خود را بروز نماييد.";
/*     */       case 1014:
/* 208 */         return "ارتباط با بانک اطلاعاتی وجود ندارد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1015:
/* 210 */         return "جدوال دوره مالی بایستی به روز رسانی شوند. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1016:
/* 212 */         return "لطفا شرکت موردنظر را انتخاب کنید";
/*     */       case 1017:
/* 214 */         return "ممکن است نسخه جداول سیستمی قدیمی باشد، لطفا پس از به روزرسانی دوباره امتحان کنید";
/*     */       case 120:
/* 216 */         return "پیشرفته";
/*     */       case 1040:
/* 218 */         return "سوال امنیتی است اشتباه پاسخ داده شده است.لطفا دوباره سعی کنید.";
/*     */       case 1036:
/* 220 */         return "تریگر های دوره مالی بایستی به روز رسانی شوند. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1035:
/* 222 */         return "تریگرهای شرکت بایستی به روز رسانی شوند. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1018:
/* 224 */         return "ورود ناموفق!";
/*     */       case 1019:
/* 226 */         return "ایجاد کننده کانال موفق به ایجاد کانال نشد.";
/*     */       case 1020:
/* 228 */         return "عدم امکان اتصال به";
/*     */       case 1021:
/* 230 */         return "آدرس یک URL معتبر نمی باشد:";
/*     */       case 1022:
/* 232 */         return "خطای کاربر:/n";
/*     */       case 1023:
/* 234 */         return "خطای سرور: /n";
/*     */       case 1024:
/* 236 */         return "ایجاد یک استثنا در کد:/n";
/*     */       case 119:
/* 238 */         return "ورود با کاربر دیگر";
/*     */       case 111:
/* 240 */         return "Caps Lock روشن است";
/*     */       case 1025:
/* 242 */         return "شما لایسنس معتبر جهت استفاده از این نرم افزار را ندارید. N لطفاً یک لایسنس معتبر جهت نصب دریافت نمایید.";
/*     */       case 1026:
/* 244 */         return "در لایسنس شما اجازه استفاده از زبان وجود ندارد!";
/*     */       case 1027:
/* 246 */         return "لایسنس شما منقضی شده است!";
/*     */       case 1028:
/* 248 */         return "لایسنس شما از این سیستم عامل پشتیبانی نمی نماید. لطفاً به تفاهم نامه لایسنس جهت اطلاع از سیستم عامل های تحت پشتیبانی این لایسنس مراجمعه نمایید.";
/*     */       case 1029:
/* 250 */         return "لایسنس شما شامل سیستم مدیریت بانک اطلاعاتی نمی باشد.";
/*     */       case 1039:
/* 252 */         return "شما به سقف تعداد کاربر در لایسنس رسیده اید. امکان ورود به سیستم نمی باشد.";
/*     */       case 1030:
/* 254 */         return "امکان ایجاد یک session جدید نمی باشد. کاربر بلوکه شده است.";
/*     */       case 1031:
/* 256 */         return "جدوال سفارشی سازی باید به روز رسانی شوند. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1032:
/* 258 */         return "یک برنامه \"نمایشگر گزارش لوگو\" در حال استفاده است. لطفاٌ بر روی کلید \"باز نمودن\" در منوی \"نمایشگر گزارش لوگو\" کلیک نمایید تا یک گزارش دیگر را مشاهده نمایید.";
/*     */       case 1033:
/* 260 */         return "خطای سازگاری کاربر. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1034:
/* 262 */         return "پکیج سفارشی سازی معتبر نمی باشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 112:
/* 264 */         return "اطلاعات ورود به سیستم خود را فراموش نموده ام.";
/*     */       case 113:
/* 266 */         return "آدرس پست الکترونیکی";
/*     */       case 114:
/* 268 */         return "لطفاٌ یک آدرس پست الکترونیکی معتبر وارد نمایید.";
/*     */       case 115:
/* 270 */         return "اطلاعات ورود به سیستم، به آدرس پست الکترونیکی شما ارسال شد.";
/*     */       case 116:
/* 272 */         return "این آدرس پست الکترونیکی در سیستم ثبت نشده است. لطفاً مجددا تلاش نمایید.";
/*     */       case 117:
/* 274 */         return "یک خطای غیر منتظره رخ داده است. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 118:
/* 276 */         return "اطلاعات";
/*     */       case 1037:
/* 278 */         return "اطلاعات دیگر کاربر، معتبر نمی باشد.";
/*     */       case 1038:
/* 280 */         return "بانک اطلاعاتی تعاریف مدیریت فرآیند به روز نمی باشد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1041:
/* 282 */         return "رمز عبور بایستی وارد شود.";
/*     */       case 1064:
/* 284 */         return "در لایسنس شما اطلاعات منو وجود ندارد. لطفاً با مدیر سیستم تماس حاصل فرمایید.";
/*     */       case 1065:
/* 286 */         return "شماره شناسایی در سیستم تعریف نشده است!";
/*     */       case 1066:
/* 288 */         return "امضای الکترونیکی قابل شناسایی نمی باشد!";
/*     */       case 1067:
/* 290 */         return "امضای الکترونیکی وارد شده معتبر نمی باشد!";
/*     */       case 1068:
/* 292 */         return "اطلاعات لایسنس به کاربر شما اختصاص نیافته است!";
/*     */       case 1043:
/* 294 */         return "مجوز خود را برای ورود به این نوع از کاربران پشتیبانی نمی کند. شما نمی توانید به سیستم وارد شوید!";
/*     */     } 
/* 296 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 302 */     return super.getLanguageName(language);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 307 */     return 31;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoFAIR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */