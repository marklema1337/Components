/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ 
/*     */ public class JLbsCultureInfoENIN
/*     */   extends JLbsCultureInfoENUS
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  12 */   public static final String[] DEFAULTFORMATS = new String[] { "#,##,##0.##", 
/*  13 */       "%", 
/*  14 */       "₹", 
/*  15 */       "DB", 
/*  16 */       "CR", 
/*  17 */       "dd/MM/yyyy", 
/*  18 */       "HH:mm:ss", 
/*  19 */       "AD", "BC", 
/*  20 */       "PM", "AM" };
/*     */ 
/*     */ 
/*     */   
/*  24 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Thousand|", "[2] ~|Lakh|", "[3] ~|Crore|", "[4] ~|Trillion|" };
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getFormatStrings() {
/*  29 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  34 */     return "ENIN";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/*  39 */     if (cultureResID == 101)
/*  40 */       return "English (IN)"; 
/*  41 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  46 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultNumberFormatIndex() {
/*  53 */     return 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultCurrencyFormatIndex() {
/*  60 */     return 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNumberSpelled(BigDecimal n, boolean fracPart) {
/*  67 */     String[] numbers = getNumberNames();
/*  68 */     String[] groups = getGroupNames();
/*  69 */     String[] basecombs = getBaseCombinations();
/*     */     
/*  71 */     BigInteger intN = n.toBigInteger().abs();
/*  72 */     if (fracPart) {
/*  73 */       intN = new BigInteger(getFraction(n, false));
/*     */     }
/*  75 */     String result = "";
/*  76 */     int group = 0;
/*  77 */     boolean threeDigit = true;
/*     */     
/*  79 */     BigInteger div1000 = intN.divide(new BigInteger("1000"));
/*  80 */     BigInteger lowGrp = intN.subtract(div1000.multiply(new BigInteger("1000")));
/*  81 */     BigInteger div100 = div1000.divide(new BigInteger("100"));
/*  82 */     BigInteger lowGrp2 = div1000.subtract(div100.multiply(new BigInteger("100")));
/*  83 */     String lowS = "";
/*  84 */     while (intN.compareTo(BigInteger.ONE) >= 0) {
/*     */       
/*  86 */       if (threeDigit)
/*     */       {
/*  88 */         if (lowGrp.compareTo(BigInteger.ZERO) != 0) {
/*     */           
/*  90 */           lowS = getEquivalent(groups, 10000 + group * 1000 + lowGrp.intValue());
/*  91 */           if (lowS.compareTo("") == 0) {
/*     */             
/*  93 */             String grpMask = getEquivalent(groups, 20000 + group * 1000 + lowGrp.mod(new BigInteger("10")).intValue());
/*  94 */             if (grpMask.compareTo("") == 0)
/*  95 */               grpMask = getEquivalent(groups, group); 
/*  96 */             if (grpMask.compareTo("") != 0) {
/*     */               
/*  98 */               String lowSpell = getEquivalent(numbers, lowGrp.intValue());
/*  99 */               if (lowSpell.compareTo("") == 0) {
/*     */                 
/* 101 */                 int hundr = lowGrp.intValue() / 100;
/* 102 */                 int ten = lowGrp.intValue() % 100;
/* 103 */                 int base = lowGrp.intValue() % 10;
/*     */                 
/* 105 */                 String hundrS = "";
/*     */                 
/* 107 */                 if (hundr != 0) {
/*     */                   
/* 109 */                   hundrS = getEquivalent(numbers, hundr * 100);
/* 110 */                   if (hundrS.compareTo("") == 0) {
/* 111 */                     hundrS = getEquivalent(basecombs, 1).replaceFirst("~", getEquivalent(numbers, hundr));
/*     */                   }
/*     */                 } 
/* 114 */                 String tenS = "";
/* 115 */                 if (ten != 0) {
/*     */                   
/* 117 */                   tenS = getEquivalent(numbers, ten);
/* 118 */                   if (tenS.compareTo("") == 0) {
/*     */                     
/* 120 */                     String baseS = getEquivalent(numbers, base);
/* 121 */                     String s = getEquivalent(basecombs, 2).replaceFirst("~", getEquivalent(numbers, ten - base));
/* 122 */                     tenS = s.replaceFirst("#", baseS);
/*     */                   } 
/*     */                 } 
/*     */                 
/* 126 */                 if (hundrS.compareTo("") != 0 && tenS.compareTo("") != 0) {
/*     */                   
/* 128 */                   String s = getEquivalent(basecombs, 3).replaceFirst("~", hundrS);
/* 129 */                   lowSpell = s.replaceFirst("#", tenS);
/*     */                 } else {
/*     */                   
/* 132 */                   lowSpell = String.valueOf(hundrS) + tenS;
/*     */                 } 
/*     */               } 
/* 135 */               lowS = grpMask.replaceFirst("~", lowSpell);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 142 */       if (!threeDigit)
/*     */       {
/* 144 */         if (lowGrp2.compareTo(BigInteger.ZERO) != 0) {
/*     */           
/* 146 */           lowS = getEquivalent(groups, 10000 + group * 1000 + lowGrp2.intValue());
/* 147 */           if (lowS.compareTo("") == 0) {
/*     */             
/* 149 */             String grpMask = getEquivalent(groups, 20000 + group * 1000 + lowGrp2.mod(new BigInteger("10")).intValue());
/* 150 */             if (grpMask.compareTo("") == 0)
/* 151 */               grpMask = getEquivalent(groups, group); 
/* 152 */             if (grpMask.compareTo("") != 0) {
/*     */               
/* 154 */               String lowSpell = getEquivalent(numbers, lowGrp2.intValue());
/* 155 */               if (lowSpell.compareTo("") == 0) {
/*     */                 
/* 157 */                 int ten = lowGrp2.intValue() % 100;
/* 158 */                 int base = lowGrp2.intValue() % 10;
/*     */ 
/*     */                 
/* 161 */                 String tenS = "";
/* 162 */                 if (ten != 0) {
/*     */                   
/* 164 */                   tenS = getEquivalent(numbers, ten);
/* 165 */                   if (tenS.compareTo("") == 0) {
/*     */                     
/* 167 */                     String baseS = getEquivalent(numbers, base);
/* 168 */                     String s = getEquivalent(basecombs, 2).replaceFirst("~", getEquivalent(numbers, ten - base));
/* 169 */                     tenS = s.replaceFirst("#", baseS);
/*     */                   } 
/*     */                 } 
/*     */                 
/* 173 */                 if (tenS.compareTo("") != 0) {
/* 174 */                   lowSpell = tenS;
/*     */                 }
/*     */               } 
/* 177 */               lowS = grpMask.replaceFirst("~", lowSpell);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 184 */       if (threeDigit) {
/*     */         
/* 186 */         intN = div1000;
/* 187 */         div100 = div1000.divide(new BigInteger("100"));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 192 */         intN = div100;
/* 193 */         div100 = div100.divide(new BigInteger("100"));
/* 194 */         lowGrp2 = intN.subtract(div100.multiply(new BigInteger("100")));
/*     */       } 
/*     */       
/* 197 */       threeDigit = false;
/* 198 */       result = lowS.concat(result);
/* 199 */       group++;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (JLbsStringUtil.isEmpty(result)) {
/* 204 */       result = getEquivalent(numbers, 0);
/*     */     }
/* 206 */     return customizeSpelled(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 212 */     return 28;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoENIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */