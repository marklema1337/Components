/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.transport.JLbsJarCache;
/*     */ import com.lbs.transport.StdCompressor;
/*     */ import com.lbs.transport.TransportClient;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.security.CodeSource;
/*     */ import java.security.Permission;
/*     */ import java.security.PermissionCollection;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteClassLoader
/*     */   extends ClassLoader
/*     */ {
/*     */   private String m_RootURI;
/*     */   private TransportClient m_TransClient;
/*     */   private ClassLoader m_SystemLoader;
/*     */   private Hashtable m_Resources;
/*     */   private int m_InProc;
/*  38 */   private final transient LbsConsole m_Logger = LbsConsole.getLogger("Transport.RemoteClassLdr");
/*     */ 
/*     */   
/*     */   public RemoteClassLoader(ClassLoader parent, String rootURI, TransportClient transClient) {
/*  42 */     super(parent);
/*  43 */     this.m_RootURI = rootURI;
/*     */ 
/*     */     
/*  46 */     this.m_TransClient = transClient;
/*  47 */     this.m_SystemLoader = Thread.currentThread().getContextClassLoader();
/*  48 */     this.m_InProc = 0;
/*  49 */     this.m_Resources = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
/*  60 */     synchronized (this) {
/*     */       
/*  62 */       this.m_Logger.trace("Loading class " + name + " level:" + this.m_InProc);
/*     */     } 
/*     */     
/*  65 */     String className = name;
/*  66 */     if (className.startsWith("{")) {
/*     */       
/*  68 */       int idx = className.indexOf("}.");
/*  69 */       if (idx != -1) {
/*  70 */         className = className.substring(idx + 2);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  75 */       ClassLoader loader = Thread.currentThread().getContextClassLoader();
/*  76 */       if (loader instanceof JLbsCustClassLoader && !name.startsWith("java.")) {
/*     */         
/*  78 */         String guid = ((JLbsCustClassLoader)loader).getCustID();
/*  79 */         if (!StringUtil.isEmpty(guid)) {
/*  80 */           name = String.valueOf(guid) + "." + name;
/*     */         }
/*     */       } 
/*     */     } 
/*  84 */     Class<?> result = null;
/*     */     
/*     */     try {
/*  87 */       synchronized (this) {
/*     */         
/*  89 */         if (this.m_InProc < 8) {
/*     */           
/*  91 */           this.m_InProc++;
/*     */           
/*     */           try {
/*  94 */             if (this.m_InProc == 2) {
/*     */               
/*     */               try {
/*  97 */                 return getParent().getParent().loadClass(className);
/*     */               }
/*  99 */               catch (Exception exception) {}
/*     */             }
/*     */             
/* 102 */             result = super.loadClass(className, resolve);
/*     */           
/*     */           }
/* 105 */           catch (Exception exception) {}
/*     */ 
/*     */           
/* 108 */           this.m_InProc--;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 113 */           result = findLoadedClass(className);
/* 114 */           if (result == null) {
/*     */             
/*     */             try {
/*     */               
/* 118 */               return getParent().getParent().loadClass(className);
/*     */             }
/* 120 */             catch (Exception exception) {}
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 126 */       if (result == null)
/*     */       {
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
/* 149 */         byte[] retData = JLbsJarCache.getClassEntry(name);
/* 150 */         if (retData == null)
/* 151 */           retData = getClassData("@" + name); 
/* 152 */         if (retData != null)
/*     */         {
/* 154 */           CodeSource codeSource = new CodeSource(new URL(String.valueOf(this.m_RootURI) + "?" + name), null);
/* 155 */           result = defineClass(null, retData, 0, retData.length, new ProtectionDomain(codeSource, 
/* 156 */                 RemoteClassPermissions.getDefault()));
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 165 */     catch (Exception e) {
/*     */ 
/*     */ 
/*     */       
/* 169 */       this.m_Logger.error("loadClass:" + e);
/*     */     } 
/* 171 */     if (result == null)
/* 172 */       throw new ClassNotFoundException(); 
/* 173 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getResource(String name) {
/*     */     try {
/* 180 */       return new URL(new URL(this.m_RootURI), name, new RCL_URLStreamHandler(name));
/*     */     }
/* 182 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 185 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   class RCL_URLStreamHandler
/*     */     extends URLStreamHandler {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLStreamHandler(String name) {
/* 194 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     protected URLConnection openConnection(URL u) throws IOException {
/* 199 */       return new RemoteClassLoader.RCL_URLConnection(u, this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   class RCL_URLConnection
/*     */     extends URLConnection
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLConnection(URL u, String name) {
/* 209 */       super(u);
/* 210 */       this.m_Name = name.replace('/', '.');
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void connect() throws IOException {}
/*     */ 
/*     */     
/*     */     public InputStream getInputStream() throws IOException {
/* 219 */       return RemoteClassLoader.this.getResourceAsStream(this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   public InputStream getResourceAsStream(String name) {
/*     */     byte[] cache;
/* 225 */     if (name == null) {
/* 226 */       return null;
/*     */     }
/* 228 */     synchronized (this.m_Resources) {
/*     */       
/* 230 */       cache = (byte[])this.m_Resources.get(name);
/*     */     } 
/* 232 */     if (cache != null && cache.length > 0)
/* 233 */       return new ByteArrayInputStream(cache); 
/* 234 */     byte[] data = JLbsJarCache.getEntry(name);
/* 235 */     if (data == null && !name.startsWith("META-INF/")) {
/*     */       
/* 237 */       data = getClassData("@!" + name);
/* 238 */       synchronized (this.m_Resources) {
/*     */         
/* 240 */         this.m_Resources.put(name, (data != null) ? 
/* 241 */             data : 
/* 242 */             new byte[0]);
/*     */       } 
/*     */     } 
/* 245 */     if (data != null) {
/* 246 */       return new ByteArrayInputStream(data);
/*     */     }
/*     */     try {
/* 249 */       InputStream stream = this.m_SystemLoader.getResourceAsStream(name);
/* 250 */       if (stream != null) {
/* 251 */         return stream;
/*     */       }
/* 253 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getClassData(String name) {
/* 261 */     byte[] result = null;
/* 262 */     if (name != null && name.length() > 0 && this.m_TransClient != null) {
/*     */       
/* 264 */       String className = name;
/*     */ 
/*     */       
/* 267 */       synchronized (this.m_TransClient) {
/*     */         
/* 269 */         result = this.m_TransClient.postData(String.valueOf(this.m_RootURI) + "?" + name, null, className.getBytes(), false, true);
/*     */       } 
/* 271 */       if (result != null && result.length > 4)
/*     */       {
/* 273 */         if (result[0] != -54 || result[1] != -2 || result[2] != -70 || result[3] != -66) {
/*     */           
/*     */           try {
/* 276 */             StdCompressor comp = new StdCompressor();
/* 277 */             result = comp.uncompress(result, 0, result.length);
/*     */           }
/* 279 */           catch (Exception exception) {}
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 285 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\RemoteClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */