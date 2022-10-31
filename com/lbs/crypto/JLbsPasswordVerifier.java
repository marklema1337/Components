/*    */ package com.lbs.crypto;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsPasswordVerifier
/*    */ {
/*    */   public static final int STRONG_PASSWORD = 1;
/*    */   public static final int WEAK_PASSWORD = 2;
/*    */   
/*    */   public boolean verifyPassword(String password, int level) {
/* 28 */     boolean isValid = false;
/*    */     
/* 30 */     if (level == 2) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 36 */       if (password.length() >= 3)
/*    */       {
/* 38 */         isValid = true;
/*    */       }
/*    */     }
/* 41 */     else if (level == 1) {
/*    */       
/* 43 */       boolean haveUpChar = false;
/* 44 */       boolean haveLowChar = false;
/* 45 */       boolean haveDigit = false;
/* 46 */       boolean havePunctuation = false;
/* 47 */       int alphaNumericCharCount = 0;
/*    */       
/* 49 */       for (int i = 0; i < password.length(); i++) {
/*    */         
/* 51 */         char token = password.charAt(i);
/* 52 */         if (Character.isUpperCase(token))
/*    */         {
/* 54 */           haveUpChar = true;
/*    */         }
/* 56 */         if (Character.isLowerCase(token))
/*    */         {
/* 58 */           haveLowChar = true;
/*    */         }
/* 60 */         if (Character.isDigit(token))
/*    */         {
/* 62 */           haveDigit = true;
/*    */         }
/* 64 */         if (Character.isLetter(token))
/*    */         {
/* 66 */           alphaNumericCharCount++;
/*    */         }
/* 68 */         if (!Character.isLetterOrDigit(token))
/*    */         {
/* 70 */           havePunctuation = true;
/*    */         }
/*    */       } 
/* 73 */       if (haveUpChar && haveLowChar && haveDigit && havePunctuation && alphaNumericCharCount >= 8)
/*    */       {
/* 75 */         isValid = true;
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 80 */       return false;
/*    */     } 
/* 82 */     return isValid;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsPasswordVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */