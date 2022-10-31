/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsPasswordField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsPasswordComponentEmulator
/*    */   extends LbsTextComponentEmulator
/*    */   implements ILbsPasswordField
/*    */ {
/* 16 */   private char m_EchoChar = '*';
/* 17 */   private String m_Password = null;
/*    */ 
/*    */   
/*    */   public char getEchoChar() {
/* 21 */     return this.m_EchoChar;
/*    */   }
/*    */ 
/*    */   
/*    */   public char[] getPassword() {
/* 26 */     return getText().toCharArray();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPasswordText() {
/* 31 */     return getText();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEchoChar(char c) {
/* 36 */     this.m_EchoChar = c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPassword(String password) {
/* 41 */     this.m_Password = password;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsPasswordComponentEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */