/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsContClassGeneratorBase
/*     */   extends LbsClassGeneratorBase
/*     */ {
/*  17 */   private static ArrayList<String> ms_ReservedMethodNames = new ArrayList<>();
/*     */ 
/*     */   
/*     */   static {
/*  21 */     Class<?> clazz = Object.class;
/*  22 */     Method[] methods = clazz.getMethods();
/*  23 */     if (methods != null)
/*  24 */       for (int i = 0; i < methods.length; i++) {
/*     */         
/*  26 */         if (!ms_ReservedMethodNames.contains(methods[i].getName())) {
/*  27 */           ms_ReservedMethodNames.add(methods[i].getName());
/*     */         }
/*     */       }  
/*     */   }
/*     */   
/*     */   public static String strip(String name, NameHash methodNames, String hashSeed) {
/*  33 */     name = replaceInvalidCharacters(name);
/*  34 */     if (JLbsStringUtil.isEmpty(hashSeed)) {
/*  35 */       hashSeed = name;
/*     */     }
/*  37 */     if (methodNames != null) {
/*     */       
/*  39 */       int idx = 1;
/*  40 */       Object o = methodNames.get(hashSeed);
/*  41 */       if (o != null)
/*     */       {
/*  43 */         idx = ((Integer)o).intValue() + 1;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  49 */       if (ms_ReservedMethodNames.contains(name))
/*  50 */         idx = 2; 
/*  51 */       methodNames.put(hashSeed, Integer.valueOf(idx));
/*  52 */       if (idx > 1)
/*  53 */         name = String.valueOf(name) + "_" + idx; 
/*     */     } 
/*  55 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replaceInvalidCharacters(String name) {
/*  60 */     if (JLbsStringUtil.equals(name, "click+"))
/*  61 */       name = "clickAdd"; 
/*  62 */     if (JLbsStringUtil.equals(name, "click-"))
/*  63 */       name = "clickSubtract"; 
/*  64 */     if (JLbsStringUtil.equals(name, "click*"))
/*  65 */       name = "clickMultiply"; 
/*  66 */     if (JLbsStringUtil.equals(name, "click/"))
/*  67 */       name = "clickDivide"; 
/*  68 */     if (JLbsStringUtil.equals(name, "click="))
/*  69 */       name = "clickEquals"; 
/*  70 */     if (JLbsStringUtil.equals(name, "click~"))
/*  71 */       name = "clickTilda"; 
/*  72 */     if (JLbsStringUtil.equals(name, "click|"))
/*  73 */       name = "clickOr"; 
/*  74 */     if (JLbsStringUtil.equals(name, "click%")) {
/*  75 */       name = "clickPercent";
/*     */     }
/*  77 */     name = JLbsStringUtil.replaceAll(name, "%", "Percent");
/*     */     
/*  79 */     char[] chars = new char[name.length()];
/*     */     
/*  81 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/*  83 */       char ch = name.charAt(i);
/*  84 */       switch (ch) {
/*     */         
/*     */         case '\'':
/*     */         case '(':
/*     */         case ',':
/*     */         case '-':
/*     */         case '/':
/*  91 */           ch = '_';
/*     */           break;
/*     */         case 'ı':
/*  94 */           ch = 'i';
/*     */           break;
/*     */         case 'ğ':
/*  97 */           ch = 'g';
/*     */           break;
/*     */         case 'Ğ':
/* 100 */           ch = 'G';
/*     */           break;
/*     */         case 'ü':
/* 103 */           ch = 'u';
/*     */           break;
/*     */         case 'Ü':
/* 106 */           ch = 'U';
/*     */           break;
/*     */         case 'ş':
/* 109 */           ch = 's';
/*     */           break;
/*     */         case 'Ş':
/* 112 */           ch = 'S';
/*     */           break;
/*     */         case 'İ':
/* 115 */           ch = 'I';
/*     */           break;
/*     */         case 'ö':
/* 118 */           ch = 'o';
/*     */           break;
/*     */         case 'Ö':
/* 121 */           ch = 'O';
/*     */           break;
/*     */         case 'ç':
/* 124 */           ch = 'c';
/*     */           break;
/*     */         case 'Ç':
/* 127 */           ch = 'C';
/*     */           break;
/*     */       } 
/* 130 */       if (Character.isJavaIdentifierPart(ch)) {
/* 131 */         chars[i] = ch;
/*     */       } else {
/* 133 */         chars[i] = ' ';
/*     */       } 
/*     */     } 
/* 136 */     name = new String(chars);
/* 137 */     name = JLbsStringUtil.replaceAll(name, " ", "");
/* 138 */     return name;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsContClassGeneratorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */