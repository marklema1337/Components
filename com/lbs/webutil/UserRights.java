/*    */ package com.lbs.webutil;
/*    */ 
/*    */ import com.lbs.transport.IUserRights;
/*    */ import com.lbs.transport.UserInfo;
/*    */ import java.io.Serializable;
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
/*    */ public class UserRights
/*    */   implements IUserRights, Serializable
/*    */ {
/*    */   private UserInfo m_UserInfo;
/*    */   
/*    */   public UserRights(UserInfo user) {
/* 23 */     this.m_UserInfo = user;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean allowedToDo(int iAuthCode) {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean allowedToDo(String sAuthCode) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public UserInfo getUser() {
/* 48 */     return this.m_UserInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\webutil\UserRights.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */