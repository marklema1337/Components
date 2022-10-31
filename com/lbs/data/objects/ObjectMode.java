/*    */ package com.lbs.data.objects;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjectMode
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_AuthId;
/*    */   private int m_ID;
/*    */   
/*    */   public ObjectMode(int id, int authId) {
/* 24 */     this.m_AuthId = authId;
/* 25 */     this.m_ID = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAuthId() {
/* 30 */     return this.m_AuthId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAuthId(int authId) {
/* 35 */     this.m_AuthId = authId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getID() {
/* 40 */     return this.m_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setID(int authMode) {
/* 45 */     this.m_ID = authMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */