/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.start.ILbsClassLoaderDelegate;
/*     */ import com.lbs.transport.TransportClient;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsRemoteClassLoader
/*     */   extends ClassLoader
/*     */ {
/*     */   private ILbsClassLoaderDelegate m_Delegate;
/*     */   
/*     */   public JLbsRemoteClassLoader(ClassLoader parent, String rootURI, TransportClient transClient) {
/*  22 */     super(parent);
/*  23 */     this.m_Delegate = new JLbsClassLoaderDelegate(rootURI, transClient);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Class findClass(String name) throws ClassNotFoundException {
/*  73 */     LbsConsole.getLogger(JLbsRemoteClassLoader.class).debug("findClass Name : " + name);
/*     */ 
/*     */     
/*     */     try {
/*  77 */       byte[] retData = this.m_Delegate.findLocalClass(name);
/*  78 */       if (retData != null) {
/*  79 */         return defineClass(name, retData, 0, retData.length, this.m_Delegate.getProtectionDomain(name));
/*     */       }
/*  81 */     } catch (Throwable e) {
/*     */       
/*  83 */       LbsConsole.getLogger(JLbsRemoteClassLoader.class).error(e.getMessage(), e);
/*     */     } 
/*  85 */     return super.findClass(name);
/*     */   }
/*     */ 
/*     */   
/*     */   protected URL findResource(String name) {
/*  90 */     System.out.println("findResource Name : " + name);
/*  91 */     return this.m_Delegate.findResourceURL(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public URL getResource(String name) {
/*  96 */     return this.m_Delegate.findResourceURL(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getResourceAsStream(String name) {
/* 101 */     return this.m_Delegate.findLocalResource(name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsRemoteClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */