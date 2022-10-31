/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import java.awt.ComponentOrientation;
/*    */ 
/*    */ 
/*    */ public class JLbsCultureInfoARBase
/*    */   extends JLbsDefaultCultureInfo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 10 */   public static final String[] MONTHNAMES_AR = new String[] { "غير ساري", "يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", 
/* 11 */       "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر" };
/*    */   
/* 13 */   public static final String[] DAYNAMES_AR = new String[] { "غير ساري", "السبت", "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", 
/* 14 */       "الجمعة" };
/*    */   
/* 16 */   public static final String[] SHORTDAYNAMES_AR = new String[] { "غير ساري", "سبت", "أحد", "اثنين", "الثلاثاء", "الأربعاء", "خميس", "الجمعة" };
/*    */   
/* 18 */   protected static final String[] NUMBERNAMES_AR = new String[] { "[صفر [0", "[1]  واحد", "[2] اثنان", "[3] ثلاثة", "[4] أربعة", "[5] خمسة", 
/* 19 */       "[6] ستة", "[7] سبعة", "[8] ثمانية", "[9] تسعة", "[10] عشرة", "[11] أحد عشر", "[12] اثنا عشر ", "[13] ثلاثة عشر ", 
/* 20 */       "[14] أربعة عشر ", "[15] خمسة عشر ", "[16] ستة عشر ", "[17]  سبعة عشر ", "[18] ثمانية عشر ", "[19] تسعة عشر ", 
/* 21 */       "[20] عشرون", "[30] ثلاثون", "[40] أربعون", "[50] خمسون", "[60] ستون", "[70] سبعون", "[80] ثمانون", "[90] تسعون" };
/* 22 */   protected static final String[] BASECOMBINATIONS_AR = new String[] { "[1] ~|مائة", "[2] #|و|~", "[3] ~|#" };
/*    */   
/* 24 */   protected static final String[] GROUPNAMES_AR = new String[] { "[0] ~", "[1] ~|ألف|", "[2] ~|مليون|", "[3] ~|بليون|", "[4] ~|تريليون|" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMonthFullName(int iMonth) {
/* 31 */     if (iMonth > 0 && iMonth <= 12)
/* 32 */       return MONTHNAMES_AR[iMonth]; 
/* 33 */     return MONTHNAMES_AR[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDayFullName(int iDay) {
/* 41 */     if (iDay > 0 && iDay <= 7)
/* 42 */       return DAYNAMES_AR[iDay]; 
/* 43 */     return DAYNAMES_AR[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDayShortName(int iDay) {
/* 48 */     if (iDay > 0 && iDay <= 7)
/* 49 */       return SHORTDAYNAMES_AR[iDay]; 
/* 50 */     return "XxX";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getNumberNames() {
/* 55 */     return NUMBERNAMES_AR;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String[] getFormatStrings() {
/* 60 */     return DEFAULTFORMATS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getBaseCombinations() {
/* 66 */     return BASECOMBINATIONS_AR;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getGroupNames() {
/* 71 */     return GROUPNAMES_AR;
/*    */   }
/*    */ 
/*    */   
/*    */   public ComponentOrientation getComponentOrientation() {
/* 76 */     return ComponentOrientation.RIGHT_TO_LEFT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoARBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */