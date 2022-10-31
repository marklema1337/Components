/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import com.lbs.util.CalendarCultureUtil;
/*     */ 
/*     */ public class JLbsCultureInfoFRFR
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getLanguagePrefix() {
/*  11 */     return "FRFR";
/*     */   }
/*     */   
/*  14 */   public static final String[] MONTHNAMES = new String[] { "Invalide", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", 
/*  15 */       "Septembre", "Octobre", "Novembre", "Décembre" };
/*     */   
/*  17 */   public static final String[] DAYNAMES = new String[] { "Invalide", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
/*     */   
/*  19 */   public static final String[] SHORTDAYNAMES = new String[] { "INV", "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" };
/*     */   
/*  21 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Zéro", "[1] Un", "[2] Deux", "[3] Trois", "[4] Quatre", "[5] Cinq", 
/*  22 */       "[6] Six", "[7] Sept", "[8] Huit", "[9] Neuf", "[10] Dix", "[11] Onze", "[12] Douze", "[13] Treize", "[14] Quatorze", 
/*  23 */       "[15] Quinze", "[16] Seize", "[17] Dix-sept", "[18] Dix-huit", "[19] Dix-neuf", "[20] Vingt", "[30] Trente", 
/*  24 */       "[40] Quarante", "[50] Cinquante", "[60] Soixante", "[70] Soixante-dix", "[80] Quatre-vingts", 
/*  25 */       "[90] Quatre-vingt dix" };
/*  26 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Mille|", "[2] ~|Million|", "[3] ~|Millard|", "[4] ~|Billion|" };
/*  27 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Cent", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  31 */     return "(DB)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  36 */     return "(CR)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  41 */     return (iEra > 0) ? 
/*  42 */       "EC" : 
/*  43 */       "AEC";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  48 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  56 */     if (iMonth > 0 && iMonth <= 12)
/*  57 */       return MONTHNAMES[iMonth]; 
/*  58 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth, int calendarType) {
/*  63 */     switch (calendarType) {
/*     */       
/*     */       case 1:
/*  66 */         return CalendarCultureUtil.getPersianEnglishMonthFullName(iMonth);
/*     */       case 0:
/*     */       case 2:
/*     */       case 3:
/*  70 */         return getMonthFullName(iMonth);
/*     */     } 
/*  72 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayFullName(int day, int calendarType) {
/*  77 */     return getDayFullName(day);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int day, int calendarType) {
/*  82 */     return getDayShortName(day);
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
/*     */   public String getDayShortName(int iDay) {
/*  97 */     if (iDay > 0 && iDay <= 7)
/*  98 */       return SHORTDAYNAMES[iDay]; 
/*  99 */     return "XxX";
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
/*     */   public String getCultureResStr(int cultureResID) {
/* 119 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 122 */         return "Francais";
/*     */       case 102:
/* 124 */         return "Nom d'utilisateur";
/*     */       case 103:
/* 126 */         return "Mot de passe";
/*     */       case 119:
/* 128 */         return "Connexion comme Utilisateur Différent";
/*     */       case 104:
/* 130 */         return "Société";
/*     */       case 105:
/* 132 */         return "Période";
/*     */       case 106:
/* 134 */         return "Langue";
/*     */       case 107:
/* 136 */         return "&Bien";
/*     */       case 108:
/* 138 */         return "&Annuler";
/*     */       case 109:
/* 140 */         return "Société et Période";
/*     */       case 110:
/* 142 */         return "Filiale";
/*     */       case 111:
/* 144 */         return "Verrouillage majuscule est activé.";
/*     */       case 1000:
/* 146 */         return "Erreur";
/*     */       case 1001:
/* 148 */         return "Le nom d'utilisateur et-ou mot de passe invalide. Veuillez contacter votre administrateur système.";
/*     */       case 1002:
/* 150 */         return "Vous n'avez pas de privilèges administratifs. Veuillez contacter votre administrateur système.";
/*     */       case 1003:
/* 152 */         return "Les tables de société doivent être mis à niveau. Veuillez contacter votre administrateur système.";
/*     */       case 1004:
/* 154 */         return "Vous n'êtes pas autorisé à utiliser cette société. Veuillez contacter votre administrateur système.";
/*     */       case 1005:
/* 156 */         return "La période spécifiée est introuvable. Veuillez contacter votre administrateur système.";
/*     */       case 1006:
/* 158 */         return "La période active est introuvable. Veuillez contacter votre administrateur système.";
/*     */       case 1007:
/* 160 */         return "Les tables de système et l'application sont de version différente. Veuillez contacter votre administrateur système.";
/*     */       case 1008:
/* 162 */         return "La société spécifié n'a pas pu être trouvé. Veuillez contacter votre administrateur système.";
/*     */       case 1009:
/* 164 */         return "Les tables système ne peuvent pas être trouvées. Veuillez contacter votre administrateur système.";
/*     */       case 1010:
/* 166 */         return "L'utilisateur ne peut pas être connecté. Veuillez contacter votre administrateur système.";
/*     */       case 1011:
/* 168 */         return "Le compte d'utilisateur a été bloqué. Veuillez contacter votre administrateur système.";
/*     */       case 1012:
/* 170 */         return "Numéro IP non valide. Veuillez contacter votre administrateur système.";
/*     */       case 1013:
/* 172 */         return "Le mot de passe a expiré. Veuillez contacter votre administrateur système.";
/*     */       case 1014:
/* 174 */         return "Connexion de base de données perdue. Veuillez vérifier la configuration de votre base de données.";
/*     */       case 1015:
/* 176 */         return "Les tables de période doivent être mises à niveau. Veuillez contacter votre administrateur système.";
/*     */       case 1036:
/* 178 */         return "Les déclencheurs de période doivent être mis à niveau. Veuillez contacter votre administrateur système.";
/*     */       case 1035:
/* 180 */         return "Les déclencheurs de société doivent être mis à niveau. Veuillez contacter votre administrateur système.";
/*     */       case 1016:
/* 182 */         return "Veuillez sélectionner la société que vous souhaitez travailler!";
/*     */       case 1017:
/* 184 */         return "La version de tables système peut être ancienne. Veuillez mettre à jour et réessayez.";
/*     */       case 1018:
/* 186 */         return "Échec de la connexion.";
/*     */       case 1019:
/* 188 */         return "Le fournisseur de chaîne n'a pas pu créer une chaîne ";
/*     */       case 1020:
/* 190 */         return "Impossible de se connecter à ";
/*     */       case 1021:
/* 192 */         return "L'adresse n'est pas un URI de connexion valide : ";
/*     */       case 1022:
/* 194 */         return "Erreur Client:\n";
/*     */       case 1023:
/* 196 */         return "Erreur de Serveur:\n";
/*     */       case 1024:
/* 198 */         return "Une exception levée avec code:\n";
/*     */       case 1025:
/* 200 */         return "Vous n'avez pas de licence valide pour utiliser cet application.\n Veuillez obtenir une licence valide pour installer!";
/*     */       case 1026:
/* 202 */         return "Votre licence n'inclut pas cette langue d'application!";
/*     */       case 1027:
/* 204 */         return "Votre licence a expiré!";
/*     */       case 1028:
/* 206 */         return "Le Système d'exploitation n'est pas pris en charge par votre licence! Veuillez se référer à votre contrat de licence pour les systèmes d'exploitation pris en charge!";
/*     */       case 1029:
/* 208 */         return "Votre licence n'inclut pas ce système de gestion de données!";
/*     */       case 1039:
/* 210 */         return "La limite d'utilisateurs de licence est atteinte.  Vous ne pouvez pas vous connecter au système.!";
/*     */       case 1030:
/* 212 */         return "La nouvelle session ne peut pas être créée! La connexion d'utilisateur est bloquée!";
/*     */       case 1031:
/* 214 */         return "Les tables de Personnalisation doivent être mises à niveau. Veuillez contacter votre administrateur de système.";
/*     */       case 1032:
/* 216 */         return "Une autre application de \"Visionneuse de Rapport de Logo\" est dans l'utilisation en ce moment. Veuillez Cliquer sur le bouton de\"Ouvrir\" situé sur \"Visionneuse de Rapport de Logo\" pour afficher un autre rapport.";
/*     */       case 1033:
/* 218 */         return "Erreur de compatibilité de Client. Veuillez contacter votre administrateur système.";
/*     */       case 1034:
/* 220 */         return "Le paquet de personnalisation n'est pas valide. Veuillez contacter votre administrateur système.";
/*     */       case 112:
/* 222 */         return "J'ai oublié mes informations de connexion";
/*     */       case 113:
/* 224 */         return "Adresse E-Mail";
/*     */       case 114:
/* 226 */         return "Veuillez entrer une adresse mail valide.";
/*     */       case 115:
/* 228 */         return "Vos informations de connexion sont envoyées à votre adresse Mail.";
/*     */       case 116:
/* 230 */         return "Cette adresse E-mail n'est pas enregistrée dans notre système. Veuillez essayer de nouveau.";
/*     */       case 117:
/* 232 */         return "Une erreur inattendue est arrivée. Veuillez contacter votre administrateur de système.";
/*     */       case 118:
/* 234 */         return "Info";
/*     */       case 1037:
/* 236 */         return "Autres infos d'utilisateur ne sont pas valides.";
/*     */       case 1038:
/* 238 */         return "Les définitions de base de données de gestion de Processus ne sont pas à jour. Veuillez contacter votre administrateur système.";
/*     */       case 120:
/* 240 */         return "Avancé";
/*     */       case 1040:
/* 242 */         return "Vous avez répondu à votre question de sécurité incorrectement. Veuillez essayer de nouveau.";
/*     */       case 1041:
/* 244 */         return "Le mot de passe doit être entré.";
/*     */       case 1064:
/* 246 */         return "Il n'y a aucune définition de menu dans votre paquet de licence. Vous ne pouvez pas connecter au système!";
/*     */       case 1065:
/* 248 */         return "Le numéro d'Identité n'est pas défini dans le système!";
/*     */       case 1066:
/* 250 */         return "La signature numérique ne peut pas être vérifiée!";
/*     */       case 1067:
/* 252 */         return "Digest de la signature numérique est invalide!";
/*     */       case 1068:
/* 254 */         return "Il n'est pas de définition de licence assigné à votre utilisateur";
/*     */       case 1043:
/* 256 */         return "Votre licence ne prend pas en charge la connexion de ce type d'utilisateur. Vous ne pouvez pas vous connecter au système!";
/*     */     } 
/* 258 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 263 */     switch (language) {
/*     */       
/*     */       case 34:
/* 266 */         return "Albanais";
/*     */       case 32:
/* 268 */         return "Arabe (JO)";
/*     */       case 24:
/* 270 */         return "Arabe (SA)";
/*     */       case 38:
/* 272 */         return "Arabe (EG)";
/*     */       case 29:
/* 274 */         return "Azerbaïdjanais";
/*     */       case 28:
/* 276 */         return "Bulgare";
/*     */       case 17:
/* 278 */         return "Chinois";
/*     */       case 27:
/* 280 */         return "Croate";
/*     */       case 9:
/* 282 */         return "Tchèque";
/*     */       case 10:
/* 284 */         return "Danois";
/*     */       case 11:
/* 286 */         return "Néerlandais";
/*     */       case 2:
/* 288 */         return "Anglais";
/*     */       case 40:
/* 290 */         return "Anglais (IN)";
/*     */       case 12:
/* 292 */         return "Estonien";
/*     */       case 30:
/* 294 */         return "Persan";
/*     */       case 13:
/* 296 */         return "Finlandais";
/*     */       case 3:
/* 298 */         return "Francais";
/*     */       case 4:
/* 300 */         return "Allemand";
/*     */       case 37:
/* 302 */         return "Géorgien";
/*     */       case 23:
/* 304 */         return "Grecque";
/*     */       case 14:
/* 306 */         return "Hébreu";
/*     */       case 15:
/* 308 */         return "Hongroise";
/*     */       case 16:
/* 310 */         return "Islandais";
/*     */       case 6:
/* 312 */         return "Italien";
/*     */       case 18:
/* 314 */         return "Japonais";
/*     */       case 19:
/* 316 */         return "Coréen";
/*     */       case 20:
/* 318 */         return "Norvégien";
/*     */       case 8:
/* 320 */         return "Polonais";
/*     */       case 21:
/* 322 */         return "Portugais";
/*     */       case 25:
/* 324 */         return "Roumain";
/*     */       case 5:
/* 326 */         return "Russe";
/*     */       case 26:
/* 328 */         return "Slovène";
/*     */       case 7:
/* 330 */         return "Espagnol";
/*     */       case 22:
/* 332 */         return "Suédois";
/*     */       case 35:
/* 334 */         return "Thaï";
/*     */       case 33:
/* 336 */         return "Turkic";
/*     */       case 31:
/* 338 */         return "Turque (RTL)";
/*     */       case 1:
/* 340 */         return "Turque";
/*     */       case 36:
/* 342 */         return "Vietnamien";
/*     */     } 
/* 344 */     return "Inconnu Langue";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 349 */     return 20;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoFRFR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */