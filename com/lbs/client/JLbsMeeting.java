/*    */ package com.lbs.client;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class JLbsMeeting
/*    */   extends JLbsAppointment
/*    */ {
/*    */   public static final int SENDINGMODE_ALL = 0;
/*    */   public static final int SENDINGMODE_NONE = 1;
/*    */   public static final int SENDINGMODE_CHANGED = 2;
/*    */   private ArrayList<String> m_Recipients;
/* 12 */   private int m_SendingMode = 0;
/*    */ 
/*    */   
/*    */   public ArrayList<String> getRecipients() {
/* 16 */     if (this.m_Recipients == null)
/* 17 */       this.m_Recipients = new ArrayList<>(); 
/* 18 */     return this.m_Recipients;
/*    */   }
/*    */   
/*    */   public void setRecipients(ArrayList<String> recipients) {
/* 22 */     this.m_Recipients = recipients;
/*    */   }
/*    */   
/*    */   public int getSendingMode() {
/* 26 */     return this.m_SendingMode;
/*    */   }
/*    */   
/*    */   public void setSendingMode(int sendingMode) {
/* 30 */     this.m_SendingMode = sendingMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\JLbsMeeting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */