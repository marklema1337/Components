/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.TimeZone;
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
/*    */ public class JLbsInitializationData
/*    */   implements Serializable
/*    */ {
/*    */   public String LangPrefix;
/*    */   public String SelectedTimeZone;
/*    */   public transient Object LoginData;
/*    */   public TimeZone ServerTimeZone;
/*    */   
/*    */   public JLbsInitializationData(Object loginData, String langPrefix) {
/* 28 */     this.LangPrefix = null;
/* 29 */     this.SelectedTimeZone = null;
/* 30 */     this.LoginData = null;
/* 31 */     this.ServerTimeZone = null;
/*    */     this.LoginData = loginData;
/*    */     this.LangPrefix = langPrefix;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsInitializationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */