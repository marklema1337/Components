/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import com.lbs.interfaces.ILbsDisposeableClassLoader;
/*    */ import com.lbs.start.ILbsClassLoaderDelegate;
/*    */ import com.lbs.transport.TransportClient;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
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
/*    */ public class JLbsCustRemoteClassLoader
/*    */   extends ClassLoader
/*    */   implements ILbsDisposeableClassLoader
/*    */ {
/*    */   private final ILbsClassLoaderDelegate m_Delegate;
/* 23 */   private static Hashtable ms_ClassLoaders = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public JLbsCustRemoteClassLoader(ClassLoader parent, String rootURI, TransportClient transClient, String custGUID) {
/* 27 */     super(parent);
/* 28 */     this.m_Delegate = new JLbsCustClassLoaderDelegate(rootURI, transClient, custGUID);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class findClass(String name) throws ClassNotFoundException {
/*    */     try {
/* 36 */       byte[] retData = this.m_Delegate.findLocalClass(name);
/* 37 */       if (retData != null) {
/* 38 */         return defineClass(name, retData, 0, retData.length, this.m_Delegate.getProtectionDomain(name));
/*    */       }
/* 40 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 43 */     return super.findClass(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected URL findResource(String name) {
/* 49 */     return this.m_Delegate.findResourceURL(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public URL getResource(String name) {
/* 55 */     return this.m_Delegate.findResourceURL(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getResourceAsStream(String name) {
/* 61 */     return this.m_Delegate.findLocalResource(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ClassLoader getClassLoader(String custID) {
/* 69 */     return (ClassLoader)ms_ClassLoaders.get(custID);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addClassLoader(String custID, ClassLoader clsLoader) {
/* 75 */     ms_ClassLoaders.put(custID, clsLoader);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsCustRemoteClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */