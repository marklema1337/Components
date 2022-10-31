/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ 
/*    */ 
/*    */ public class JLbsCustClassLoader
/*    */   extends ClassLoader
/*    */ {
/*    */   private String m_CustGUID;
/*    */   private RemoteClassLoader m_ClassLoader;
/*    */   
/*    */   public JLbsCustClassLoader(RemoteClassLoader loader, String custID) {
/* 14 */     super(loader);
/* 15 */     this.m_ClassLoader = loader;
/* 16 */     this.m_CustGUID = custID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
/* 21 */     return super.loadClass(name, resolve);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public URL getResource(String name) {
/* 27 */     return super.getResource(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getResourceAsStream(String name) {
/* 32 */     return super.getResourceAsStream(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCustID() {
/* 37 */     return this.m_CustGUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustID(String custID) {
/* 42 */     this.m_CustGUID = custID;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoteClassLoader getClassLoader() {
/* 47 */     return this.m_ClassLoader;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClassLoader(RemoteClassLoader classLoader) {
/* 52 */     this.m_ClassLoader = classLoader;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsCustClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */