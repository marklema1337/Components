/*    */ package com.lbs.xui;
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
/*    */ public class BOLinkPair
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int LNKTYP_OBJECT = 0;
/*    */   public static final int LNKTYP_COLLECTION = 1;
/*    */   private String m_LinkName;
/*    */   private String m_BOName;
/*    */   private int m_Type;
/*    */   
/*    */   public BOLinkPair(String linkName, String BOName, int type) {
/* 27 */     this.m_LinkName = linkName;
/* 28 */     this.m_BOName = BOName;
/* 29 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLinkName() {
/* 34 */     return this.m_LinkName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBOName() {
/* 39 */     return this.m_BOName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 44 */     return this.m_Type;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\xui\BOLinkPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */