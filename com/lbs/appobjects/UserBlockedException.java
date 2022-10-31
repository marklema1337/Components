/*    */ package com.lbs.appobjects;
/*    */ 
/*    */ import com.lbs.remoteclient.LocalizableException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserBlockedException
/*    */   extends LocalizableException
/*    */   implements Serializable
/*    */ {
/*    */   public UserBlockedException(String s, int resTag) {
/* 16 */     super(s, resTag);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\UserBlockedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */