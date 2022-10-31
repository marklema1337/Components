/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import com.lbs.transport.TransportClient;
/*    */ import com.lbs.util.JLbsStringUtil;
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
/*    */ 
/*    */ public class JLbsCustClassLoaderDelegate
/*    */   extends JLbsClassLoaderDelegate
/*    */ {
/*    */   private String m_CustGUID;
/*    */   
/*    */   public JLbsCustClassLoaderDelegate(String rootURI, TransportClient transClient, String custGUID) {
/* 26 */     super(rootURI, transClient);
/* 27 */     this.m_CustGUID = custGUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCustID() {
/* 32 */     return this.m_CustGUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustID(String custID) {
/* 37 */     this.m_CustGUID = custID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] findLocalClass(String name) {
/* 43 */     byte[] result = super.findLocalClass(name);
/* 44 */     if (result == null)
/*    */     {
/* 46 */       if (!name.startsWith("{") && !JLbsStringUtil.isEmpty(this.m_CustGUID)) {
/*    */         
/* 48 */         name = String.valueOf(this.m_CustGUID) + "." + name;
/* 49 */         result = super.findLocalClass(name);
/*    */       } 
/*    */     }
/* 52 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getGUID() {
/* 59 */     return this.m_CustGUID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsCustClassLoaderDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */