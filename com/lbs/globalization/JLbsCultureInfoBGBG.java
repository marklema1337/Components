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
/*     */ public class JLbsCultureInfoBGBG
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getLanguagePrefix() {
/*  17 */     return "BGBG";
/*     */   }
/*     */   
/*  20 */   public static final String[] MONTHNAMES = new String[] { "нелегален месец", "януари", "Февруари", "март", "април", "май", "юни", "юли", 
/*  21 */       "август", "септември", "октомври", "ноември", "декември" };
/*     */   
/*  23 */   public static final String[] DAYNAMES = new String[] { "нелегален ден", "неделя", "понеделник", "вторник", "сряда", "четвъртак", "петък", 
/*  24 */       "събота" };
/*     */   
/*  26 */   public static final String[] SHORTDAYNAMES = new String[] { "---", "нед", "пон", "вто", "сря", "чет", "пет", "съб" };
/*     */   
/*  28 */   protected static final String[] NUMBERNAMES = new String[] { "[0] нула", "[1] един", "[2] два", "[3] три", "[4] четири", "[5] пет", 
/*  29 */       "[6] шест", "[7] седем", "[8] осем", "[9] девет", "[10] десет", "[20] двадесет", "[30] тридесет", "[40] четиридесет", 
/*  30 */       "[50] петдесет", "[60] шестдесет", "[70] седемдесет", "[80] осемдесет", "[90] деветдесет", "[100] сто", "[200] двеста", 
/*  31 */       "[300] триста", "[400] четиристотин", "[500] петстотин?", "[600] шестстотин", "[700] седемстотин", "[800] осемстотин", 
/*  32 */       "[900] деветстотин" };
/*  33 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|хиляди|", "[2] ~ |милиона|", "[3] ~ |милиарда|", 
/*  34 */       "[4] ~ |трилиона|", "[11001] хиляда|", "[12001] един милион|", "[13001] един милиард|", "[14001] един трилион|" };
/*  35 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|сто", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  39 */     return "(B)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  44 */     return "(A)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  49 */     return (iEra > 0) ? 
/*  50 */       "СНЕ" : 
/*  51 */       "ПНЕ";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  56 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  64 */     if (iMonth > 0 && iMonth <= 12)
/*  65 */       return MONTHNAMES[iMonth]; 
/*  66 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/*  71 */     return getMonthFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay, int calendarType) {
/*  76 */     return getDayFullName(iDay);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay, int calendarType) {
/*  81 */     return getDayShortName(iDay);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  86 */     if (iDay > 0 && iDay <= 7)
/*  87 */       return SHORTDAYNAMES[iDay]; 
/*  88 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  96 */     if (iDay > 0 && iDay <= 7)
/*  97 */       return DAYNAMES[iDay]; 
/*  98 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 103 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 108 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 113 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 118 */     return "Да";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 123 */     return "Не";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 128 */     return "Приеми";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 133 */     return "Откажи";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 138 */     return "Запиши";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 143 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 146 */         return "български";
/*     */       case 102:
/* 148 */         return "Потребителско име";
/*     */       case 103:
/* 150 */         return "парола";
/*     */       case 104:
/* 152 */         return "компания";
/*     */       case 105:
/* 154 */         return "период";
/*     */       case 106:
/* 156 */         return "език";
/*     */       case 107:
/* 158 */         return "&Приеми";
/*     */       case 108:
/* 160 */         return "&Откажи";
/*     */       case 109:
/* 162 */         return "Дружеството и Период";
/*     */       case 120:
/* 164 */         return "напреднал";
/*     */       case 1040:
/* 166 */         return "Сигурност въпрос е неправилно. Моля, опитайте отново.";
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
/* 196 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 202 */     return super.getLanguageName(language);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoBGBG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */