/*    */ package com.lbs.expcalc;
/*    */ 
/*    */ import java.math.BigDecimal;
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
/*    */ public class LbsExpCalcTestAdapter
/*    */   implements ILbsExpCalcAdapter
/*    */ {
/*    */   private static final int FUNCS_MYNUMFUNC = 1;
/*    */   
/*    */   private static int strToInt(String string) {
/*    */     try {
/* 21 */       return Integer.parseInt(string);
/*    */     }
/* 23 */     catch (Exception ex) {
/*    */       
/* 25 */       return 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean calculateNumericPar(Object sender, String pTok, LbsExpCalcValue value) {
/* 31 */     int x = pTok.indexOf("P");
/* 32 */     int parId = (x == 0) ? strToInt(pTok.substring(1)) : 0;
/* 33 */     if (parId > 0) {
/*    */       
/* 35 */       switch (parId) {
/*    */         
/*    */         case 1:
/* 38 */           value.setNumericVal(new BigDecimal(1.0D));
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 43 */           return true;
/*    */       }  return false;
/* 45 */     }  return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean calculateStringPar(Object sender, String pTok, LbsExpCalcValue value) {
/* 50 */     int x = pTok.indexOf("P");
/* 51 */     int parId = (x == 0) ? strToInt(pTok.substring(1)) : 0;
/* 52 */     if (parId > 0) {
/*    */       
/* 54 */       switch (parId) {
/*    */         
/*    */         case 101:
/* 57 */           value.setStringVal("Test");
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 62 */           return true;
/*    */       }  return false;
/* 64 */     }  return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsFunctionInfo isExternalFunction(Object sender, String token) {
/* 69 */     if (token.compareTo("MYNUMFUNC") == 0) {
/*    */       
/* 71 */       LbsFunctionInfo fInfo = new LbsFunctionInfo();
/* 72 */       fInfo.setExternal(true);
/* 73 */       fInfo.setName(token);
/* 74 */       fInfo.setNr(1);
/* 75 */       fInfo.setParamCount(3);
/* 76 */       fInfo.setParamTypes(new int[] { 1, 1, 2 });
/* 77 */       fInfo.setType(1);
/*    */       
/* 79 */       return fInfo;
/*    */     } 
/* 81 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateFunction(Object sender, LbsFunctionInfo function, LbsExpCalcValue[] params, LbsExpCalcValue fResult) {
/* 86 */     if (function == null) {
/*    */       return;
/*    */     }
/* 89 */     switch (function.getNr()) {
/*    */       case 1:
/* 91 */         fResult.setNumericVal(params[0].getNumericVal().multiply(params[1].getNumericVal()));
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void modifyFunctionInfo(Object sender, LbsFunctionInfo function) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\expcalc\LbsExpCalcTestAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */