/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidSessionException
/*    */   extends SessionTimeoutException
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int ERR_INVALID_LICENCE = 0;
/*    */   public static final int ERR_INVALID_LICENCE2 = 1;
/*    */   public static final int ERR_INVALID_LANG_TO_LICENCE = 2;
/*    */   public static final int ERR_TIME_EXPIRED_TO_LICENCE = 3;
/*    */   public static final int ERR_INVALID_OS_TO_LICENCE = 4;
/*    */   public static final int ERR_NO_LOGIN_SUPPORT = 5;
/*    */   public static final int ERR_CAPACITY_EXCEEDED = 6;
/*    */   public static final int ERR_MENU_FUNCTIONALITY = 7;
/*    */   public static final int ERR_COMPANY_NOT_FOUND = 8;
/*    */   public static final int ERR_NO_LICENCE_FOUND = 9;
/*    */   public static final int ERR_NO_CAPACITY_FOR_USERTYPE = 10;
/*    */   public static final int ERR_SERVER_MAINTENANCE_MODE = 11;
/*    */   public static final int ERR_SAAS_PACKAGE_DOWNGRADED = 12;
/* 30 */   private int m_Type = 0;
/*    */ 
/*    */   
/*    */   public InvalidSessionException(String msg, int type) {
/* 34 */     super(msg);
/* 35 */     setType(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(int type) {
/* 40 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 45 */     return this.m_Type;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\InvalidSessionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */