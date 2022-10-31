/*    */ package com.lbs.crypto;
/*    */ 
/*    */ import com.lbs.channel.ICryptor;
/*    */ import com.lbs.transport.CryptorException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SymetricCryptorBase
/*    */   implements ICryptor
/*    */ {
/*    */   public Object getParam(int iParamStyle) throws CryptorException {
/* 16 */     switch (iParamStyle) {
/*    */       
/*    */       case 4:
/* 19 */         return Integer.valueOf(0);
/*    */     } 
/* 21 */     throw new CryptorException("Return of this parameter is not supported, code:" + iParamStyle);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setParam(int iParamStyle, Object param) {
/* 26 */     switch (iParamStyle) {
/*    */       
/*    */       case 0:
/* 29 */         if (param instanceof byte[]) {
/* 30 */           setPassword((byte[])param);
/* 31 */         } else if (param instanceof String) {
/* 32 */           setPassword((String)param);
/*    */         } else {
/* 34 */           setPassword(param.toString());
/* 35 */         }  return true;
/*    */     } 
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   protected void setPassword(byte[] pass) {}
/*    */   
/*    */   protected void setPassword(String pass) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\SymetricCryptorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */