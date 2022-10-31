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
/*     */ public class JLbsCultureInfoSQAL
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  16 */   public static final String[] MONTHNAMES = new String[] { "Invalid", "Janar", "Shkurt", "Mars", "Prill", "Mund", "Qershor", "Korrik", "Gusht", 
/*  17 */       "Shtator", "Tetor", "Nëntor", "Dhjetor" };
/*     */   
/*  19 */   public static final String[] DAYNAMES = new String[] { "Invalid", "Treg", "E Hënë", "E Martë", "E Mërkurë", "E Enjte", "E Premte", 
/*  20 */       "E Shtunë" };
/*     */   
/*  22 */   public static final String[] SHORTDAYNAMES = new String[] { "INV", "Tre", "Hën", "Mar", "Mër", "Enj", "Pre", "Sht" };
/*     */   
/*  24 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Zero", "[1] Një", "[2] Dy", "[3] Tre", "[4] Katër", "[5] Pesë", 
/*  25 */       "[6] Gjashtë", "[7] Shtatë", "[8] Tetë", "[9] Nëntë", "[10] Dhjetë", "[20] Njëzet", "[30] Tridhjetë", "[40] Dyzetë", 
/*  26 */       "[50] Pesëdhjetë", "[60] Gjashtëdhjetë", "[70] Shtatëdhjetë", "[80] Tetëdhjetë", "[90] Nëntëdhjetë", "[100] Fytyrë" };
/*     */   
/*  28 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Mijë|", "[2] ~|Milion|", "[3] ~|Miliardë|", "[4] ~|Trillion|", 
/*  29 */       "[11001] Mijë|" };
/*     */   
/*  31 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Qind", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  35 */     return ILbsCultureConstants.LANGUAGEPREFIXES[34];
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
/*  51 */       "PK" : 
/*  52 */       "PS";
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
/* 104 */     return "Po";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 109 */     return "Jo";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 114 */     return "Në Rregull";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 119 */     return "Anuloj";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 124 */     return "Ruaj";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 129 */     return "Zona Koha";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 134 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 137 */         return "Shqiptar";
/*     */       case 102:
/* 139 */         return "Emrin";
/*     */       case 103:
/* 141 */         return "Fjalëkalimi";
/*     */       case 119:
/* 143 */         return "Identifikohem si Përdorues të Ndryshëm";
/*     */       case 120:
/* 145 */         return "I Përparuar";
/*     */       case 104:
/* 147 */         return "Kompani";
/*     */       case 105:
/* 149 */         return "Periudhë";
/*     */       case 106:
/* 151 */         return "Gjuhë";
/*     */       case 107:
/* 153 */         return "&Në Rregull";
/*     */       case 108:
/* 155 */         return "&Anuloj";
/*     */       case 109:
/* 157 */         return "Kompani dhe Periudhë";
/*     */       case 110:
/* 159 */         return "Ndihmës";
/*     */       case 111:
/* 161 */         return "Caps Lock është në.";
/*     */       
/*     */       case 1000:
/* 164 */         return "Gabim";
/*     */       case 1001:
/* 166 */         return "Pavlefshme username dhe/ose fjalëkalimi. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1002:
/* 168 */         return "Ju nuk keni privilegje administrative. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1003:
/* 170 */         return "Tavolina kompani duhet të përmirësohet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1004:
/* 172 */         return "Ju nuk jeni i lejuar për të përdorur këtë kompani. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1005:
/* 174 */         return "Periudha e specifikuar nuk mund të gjendet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1006:
/* 176 */         return "Periudha aktiv nuk mund të gjendet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1007:
/* 178 */         return "Tabelat e sistemit dhe aplikimit janë të version të ndryshme. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1008:
/* 180 */         return "Kompania e specifikuar nuk mund të gjendet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1009:
/* 182 */         return "Tabelat e sistemit nuk mund të gjendet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1010:
/* 184 */         return "Përdoruesi nuk mund të regjistrohet in Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1011:
/* 186 */         return "Llogari përdoruesi është bllokuar. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1012:
/* 188 */         return "IP e pavlefshme nr. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1013:
/* 190 */         return "Fjalëkalimi juaj ka skaduar. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1014:
/* 192 */         return "Baza e të dhënave lidhje gabim. Kontrolloni parametrat e bazës së të dhënave.";
/*     */       case 1015:
/* 194 */         return "Tavolina periudhë duhet të përmirësohet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1036:
/* 196 */         return "Shkakton periudhë duhet të përmirësohet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1035:
/* 198 */         return "Shkakton kompani duhet të përmirësohet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1016:
/* 200 */         return "Ju lutemi zgjidhni kompani që ju dëshironi për të punuar!";
/*     */       case 1017:
/* 202 */         return "Version i tabelave të sistemit mund të jetë e vjetër. Please update dhe provoni përsëri.";
/*     */       case 1018:
/* 204 */         return "Login dështuar";
/*     */       case 1019:
/* 206 */         return "Ofruesi kanal nuk ishte në gjendje për të krijuar një kanal";
/*     */       case 1020:
/* 208 */         return "Në pamundësi për t'u lidhur me";
/*     */       case 1021:
/* 210 */         return "Adresa nuk është një URI e vlefshme login: ";
/*     */       case 1022:
/* 212 */         return "Përjashtimi i brendshëm:\n";
/*     */       case 1023:
/* 214 */         return "Përjashtimi i largët:\n";
/*     */       case 1024:
/* 216 */         return "Përjashtimi i ngritur me kodin";
/*     */       case 1025:
/* 218 */         return "Ju nuk keni një licencë të vlefshme instaluar.\nJu lutem instaloni një licencë të vlefshme!";
/*     */       case 1026:
/* 220 */         return "Licenca juaj nuk përmban këtë gjuhë aplikimit!";
/*     */       case 1027:
/* 222 */         return "Licenca juaj ka skaduar!";
/*     */       case 1028:
/* 224 */         return "Sistemi operativ nuk është i mbështetur nga patentën tuaj! Ju lutem referojuni të marrëveshjes licencës për sistemet operative të mbështetur!";
/*     */       case 1029:
/* 226 */         return "Licenca juaj nuk përmban këtë sistem të menaxhimit të bazës së të dhënave!";
/*     */       case 1030:
/* 228 */         return "Në pamundësi për të krijuar sesion të ri! Përdoruesit nuk mund të identifikohem këtë nyje!";
/*     */       case 1031:
/* 230 */         return "Tavolina Përshtatje duhet të përmirësohet. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1032:
/* 232 */         return "Viewer Raporti është tashmë running. Ju lutem përdorni butonin për të hapur Viewer Raporti për të parë një raport tjetër.";
/*     */       case 1033:
/* 234 */         return "Compatibility klient gabim. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1034:
/* 236 */         return "Paketa customization nuk është e vlefshme. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 112:
/* 238 */         return "Harruat fjalëkalimin tuaj?";
/*     */       case 113:
/* 240 */         return "E-Mail adresa";
/*     */       case 114:
/* 242 */         return "Invalid adresë e-mail.";
/*     */       case 115:
/* 244 */         return "Informacioni juaj login është dërguar në e-mail adresën tuaj.";
/*     */       case 116:
/* 246 */         return "Mail që ju specifikuar nuk gjenden në regjistrat tanë sistemit. \nJu lutem kontrolloni adresën e-mail që ju të specifikuara.";
/*     */       case 117:
/* 248 */         return "Përjashtimi i papritur ka qenë e ndodhur. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 118:
/* 250 */         return "Info";
/*     */       case 1037:
/* 252 */         return "Pavlefshme Username NDRYSHËM dhe / ose fjalëkalimi. Ju lutem kontaktoni administratorin e sistemit tuaj.";
/*     */       case 1040:
/* 254 */         return "Pyetja Sigurimit është përgjigjur gabimisht. Ju lutemi provoni përsëri.";
/*     */     } 
/* 256 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 261 */     switch (language) {
/*     */       
/*     */       case 34:
/* 264 */         return "Shqiptar";
/*     */       case 32:
/* 266 */         return "Arab (JO)";
/*     */       case 24:
/* 268 */         return "Arab (SA)";
/*     */       case 38:
/* 270 */         return "Arab (EG)";
/*     */       case 29:
/* 272 */         return "Azerbaixhane";
/*     */       case 28:
/* 274 */         return "Bulgarian";
/*     */       case 17:
/* 276 */         return "Kinez";
/*     */       case 27:
/* 278 */         return "Kroat";
/*     */       case 9:
/* 280 */         return "Çek";
/*     */       case 10:
/* 282 */         return "Danez";
/*     */       case 11:
/* 284 */         return "Holandez";
/*     */       case 2:
/* 286 */         return "Anglisht";
/*     */       case 40:
/* 288 */         return "Anglisht (IN)";
/*     */       case 12:
/* 290 */         return "Estonez";
/*     */       case 30:
/* 292 */         return "Persian";
/*     */       case 13:
/* 294 */         return "Finlandisht";
/*     */       case 3:
/* 296 */         return "Frëngjisht";
/*     */       case 4:
/* 298 */         return "Gjermanisht";
/*     */       case 37:
/* 300 */         return "Gjorgjian";
/*     */       case 23:
/* 302 */         return "Greqisht";
/*     */       case 14:
/* 304 */         return "Hebrew";
/*     */       case 15:
/* 306 */         return "Hungarez";
/*     */       case 16:
/* 308 */         return "İslandez";
/*     */       case 6:
/* 310 */         return "Italisht";
/*     */       case 18:
/* 312 */         return "Japonisht";
/*     */       case 19:
/* 314 */         return "Korean";
/*     */       case 20:
/* 316 */         return "Norvegjez";
/*     */       case 8:
/* 318 */         return "Polonisht";
/*     */       case 21:
/* 320 */         return "Portugalisht";
/*     */       case 25:
/* 322 */         return "Rumun";
/*     */       case 5:
/* 324 */         return "Rusisht";
/*     */       case 26:
/* 326 */         return "Slloven";
/*     */       case 7:
/* 328 */         return "Spanjisht";
/*     */       case 22:
/* 330 */         return "Suedez";
/*     */       case 35:
/* 332 */         return "Thai";
/*     */       case 33:
/* 334 */         return "Turkoman";
/*     */       case 31:
/* 336 */         return "Turk (RTL)";
/*     */       case 1:
/* 338 */         return "Turk";
/*     */       case 36:
/* 340 */         return "Vietnamisht";
/*     */     } 
/* 342 */     return "Gjuha panjohur";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 347 */     return 61;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoSQAL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */