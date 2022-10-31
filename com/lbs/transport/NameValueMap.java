/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.util.JLbsNameValueMap;
/*    */ import java.util.Hashtable;
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
/*    */ public class NameValueMap
/*    */   extends JLbsNameValueMap
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public NameValueMap cloneMap() throws CloneNotSupportedException {
/* 23 */     NameValueMap result = new NameValueMap();
/* 24 */     if (this.m_Table != null)
/* 25 */       result.m_Table = (Hashtable)this.m_Table.clone(); 
/* 26 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\NameValueMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */