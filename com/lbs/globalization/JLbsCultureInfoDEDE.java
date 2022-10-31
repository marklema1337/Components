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
/*     */ 
/*     */ public class JLbsCultureInfoDEDE
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getLanguagePrefix() {
/*  19 */     return "DEDE";
/*     */   }
/*     */   
/*  22 */   public static final String[] MONTHNAMES = new String[] { "Null", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", 
/*  23 */       "September", "Oktober", "November", "Dezember" };
/*     */   
/*  25 */   public static final String[] DAYNAMES = new String[] { "Null", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", 
/*  26 */       "Samstag" };
/*     */   
/*  28 */   public static final String[] SHORTDAYNAMES = new String[] { "Nu", "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" };
/*     */   
/*  30 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Null", "[1] Ein", "[2] Zwei", "[3] Drei", "[4] Vier", "[5] Fünf", 
/*  31 */       "[6] Sechs", "[7] Sieben", "[8] Acht", "[9] Neun", "[10] Zehn", "[11] Elf", "[12] Zwölf", "[13] Dreizehn", 
/*  32 */       "[14] Vierzehn", "[15] Fünfzehn", "[16] Sechzehn", "[17] Siebzehn", "[18] Achtzehn", "[19] Neunzehn", "[20] Zwanzig", 
/*  33 */       "[30] Dreizig", "[40] Vierzig", "[50] Fünfzig", "[60] Sechzig", "[70] Siebzig", "[80] Achtzig", "[90] Neunzig" };
/*  34 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Tausend|", "[2] ~|Millionen|", "[3] ~|Milliarden|", 
/*  35 */       "[4] ~|Trillionen|", "[12001] EineMillion|", "[13001] EineMilliarde|", "[14001] EineTrillion|" };
/*  36 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Hundert", "[2] #|und|~", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  40 */     return "(S)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  45 */     return "(H)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  50 */     return (iEra > 0) ? 
/*  51 */       "AD" : 
/*  52 */       "BC";
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
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/*  72 */     return getMonthFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iMonth, int calendarType) {
/*  77 */     return getDayFullName(iMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iMonth, int calendarType) {
/*  82 */     return getDayShortName(iMonth);
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
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  97 */     if (iDay > 0 && iDay <= 7)
/*  98 */       return DAYNAMES[iDay]; 
/*  99 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 104 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 109 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 114 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String customizeSpelled(String spellN) {
/* 119 */     System.out.println(spellN);
/*     */     
/* 121 */     if (spellN != null && spellN.length() >= 3 && spellN.substring(spellN.length() - 3).compareTo("Ein") == 0)
/*     */     {
/* 123 */       spellN = String.valueOf(spellN) + "s";
/*     */     }
/*     */     
/* 126 */     return spellN;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 131 */     return "Ja";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 136 */     return "Nein";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 141 */     return "OK";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 146 */     return "Abbrechen";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 151 */     return "Speichern";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 156 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 159 */         return "Deutsch";
/*     */       case 102:
/* 161 */         return "Anwender";
/*     */       case 103:
/* 163 */         return "Kennwort";
/*     */ 
/*     */       
/*     */       case 104:
/* 167 */         return "Firma";
/*     */       case 105:
/* 169 */         return "Periode";
/*     */       case 106:
/* 171 */         return "Sprache";
/*     */       case 107:
/* 173 */         return "&OK";
/*     */       case 108:
/* 175 */         return "&Abbrechen";
/*     */       case 109:
/* 177 */         return "Mandant und Periode";
/*     */       case 110:
/* 179 */         return "Hauptorganisation";
/*     */       case 111:
/* 181 */         return "Caps Lock ist auf.";
/*     */       case 1000:
/* 183 */         return "Fehler";
/*     */       case 1001:
/* 185 */         return "Ungültiger Benutzername und/oder ungültiges Passwort. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1002:
/* 187 */         return "Sie haben keine administrative Berechtigung. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1003:
/* 189 */         return "Passen Sie die Firmendatenverzeichnisse an neue Version an. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1004:
/* 191 */         return "Sie haben keine Berechtigung für diese Firma. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1005:
/* 193 */         return "Die Periode ist nicht vorhanden. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1006:
/* 195 */         return "Keine aktive Periode. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1007:
/* 197 */         return "Die Systemtabellen und die Applikation stimmen miteinander nicht. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1008:
/* 199 */         return "Firma ist nicht vorhanden. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */       case 1009:
/* 201 */         return "Die Systemtabellen sind nicht vorhanden. Bitte berichten Sie diesen Fall Ihren Systemverwalter.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1013:
/* 209 */         return "Ihr Kennwort ist abgelaufen. Bitte geben Sie ein neues Kennwort ein.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1016:
/* 219 */         return "Bitte wählen Sie einen Mandanten!";
/*     */       case 1017:
/* 221 */         return "Möglicherweise sind die Systemtabellen veraltet. Bitte aktualisieren und erneut versuchen.";
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
/*     */       case 1039:
/* 249 */         return "Benutzeranzahl wurde überschritten. Sie können sich nicht ins System einloggen.";
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
/*     */       case 118:
/* 273 */         return "Info";
/*     */ 
/*     */       
/*     */       case 1038:
/* 277 */         return "Prozessverwaltungsdatenbank muss aktualisiert werden. Bitte wenden Sie sich an den Systemadministrator ";
/*     */       case 120:
/* 279 */         return "Erweitert";
/*     */       case 1040:
/* 281 */         return "Sicherheitsfrage falsch beantwortet. Bitte versuchen Sie es erneut.";
/*     */       case 1041:
/* 283 */         return "Kennwort muss eingegeben werden.";
/*     */       case 1064:
/* 285 */         return "Sie haben keine gültige Lizenz für die Menüdefinition. Sie können sich nicht einloggen.";
/*     */       case 1065:
/* 287 */         return "Sie haben keine gültige Personalnummer im System definiert.";
/*     */       case 1066:
/* 289 */         return "Ihre eletronische Unterschrift ist nicht gültig.";
/*     */       case 1067:
/* 291 */         return "Ihre eletronische Unterschrift ist nicht im Konform.";
/*     */       case 1068:
/* 293 */         return "Ihr Benutzer hat keine gültige Lizenzdefinition.";
/*     */       case 1043:
/* 295 */         return "Ihre Lizenz nicht unterstützt Anmeldung für diese Art von Benutzer. Sie können nicht in das System einloggen!";
/*     */     } 
/*     */     
/* 298 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 304 */     return super.getLanguageName(language);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 309 */     return 20;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoDEDE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */