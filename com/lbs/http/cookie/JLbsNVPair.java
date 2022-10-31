/*    */ package com.lbs.http.cookie;
/*    */ 
/*    */ 
/*    */ public final class JLbsNVPair
/*    */ {
/*    */   private String m_Name;
/*    */   private String m_Value;
/*    */   
/*    */   public JLbsNVPair(String name, String value) {
/* 10 */     this.m_Name = name;
/* 11 */     this.m_Value = value;
/*    */   }
/*    */   
/*    */   public JLbsNVPair(JLbsNVPair p) {
/* 15 */     this(p.m_Name, p.m_Value);
/*    */   }
/*    */   
/*    */   public final String getName() {
/* 19 */     return this.m_Name;
/*    */   }
/*    */   
/*    */   public final String getValue() {
/* 23 */     return this.m_Value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 27 */     return "NameValuePair(name=" + this.m_Name + ", value=" + this.m_Value + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\cookie\JLbsNVPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */