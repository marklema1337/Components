/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import com.lbs.start.ILbsClassLoaderDelegate;
/*     */ import com.lbs.transport.JLbsJarCache;
/*     */ import com.lbs.transport.StdCompressor;
/*     */ import com.lbs.transport.TransportClient;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.security.AllPermission;
/*     */ import java.security.CodeSource;
/*     */ import java.security.PermissionCollection;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsClassLoaderDelegate
/*     */   implements ILbsClassLoaderDelegate
/*     */ {
/*  34 */   private HashSet<String> m_FailedClasses = new HashSet<>();
/*     */   protected static final PermissionCollection ALL_PERMISSIONS;
/*     */   
/*     */   static {
/*  38 */     AllPermission allPerm = new AllPermission();
/*  39 */     ALL_PERMISSIONS = allPerm.newPermissionCollection();
/*  40 */     if (ALL_PERMISSIONS != null) {
/*  41 */       ALL_PERMISSIONS.add(allPerm);
/*     */     }
/*     */   }
/*     */   
/*     */   private String m_RootURI;
/*     */   private TransportClient m_TransClient;
/*     */   private Hashtable m_Resources;
/*     */   
/*     */   public JLbsClassLoaderDelegate(String rootURI, TransportClient transClient) {
/*  50 */     this.m_RootURI = rootURI;
/*  51 */     this.m_TransClient = transClient;
/*  52 */     this.m_Resources = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] findLocalClass(String name) {
/*     */     try {
/*  59 */       byte[] retData = JLbsJarCache.getClassEntry(name);
/*  60 */       if (retData == null)
/*     */       {
/*     */         
/*  63 */         retData = getClassData("@" + name);
/*     */       }
/*  65 */       if (retData != null) {
/*  66 */         return retData;
/*     */       }
/*  68 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream findLocalResource(String name) {
/*  77 */     return getResourceAsStream(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public URL findResourceURL(String name) {
/*  82 */     URL url = JLbsJarCache.getResourceURL(name);
/*  83 */     if (url != null)
/*  84 */       return url; 
/*  85 */     if (name.endsWith("Axis2_sign.properties"))
/*     */       
/*     */       try {
/*  88 */         return (new File(name)).toURI().toURL();
/*     */       }
/*  90 */       catch (MalformedURLException e) {
/*     */         
/*  92 */         return getResource(name);
/*     */       }  
/*  94 */     return getResource(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public URL findLocalResourceURL(String name) {
/*  99 */     return JLbsJarCache.getResourceURL(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ProtectionDomain getProtectionDomain(String name) {
/*     */     try {
/* 106 */       URL url = null;
/* 107 */       if (url == null) {
/* 108 */         url = new URL(String.valueOf(this.m_RootURI) + "?" + name);
/*     */       }
/* 110 */       CodeSource codeSource = new CodeSource(url, null);
/* 111 */       return new ProtectionDomain(codeSource, ALL_PERMISSIONS);
/*     */     }
/* 113 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 116 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] getClassData(String name) {
/* 121 */     byte[] result = null;
/* 122 */     if (name != null && name.length() > 0 && this.m_TransClient != null) {
/*     */       
/* 124 */       if (this.m_FailedClasses.contains(name)) {
/* 125 */         return null;
/*     */       }
/* 127 */       String className = name;
/*     */       
/*     */       try {
/* 130 */         className = URLEncoder.encode(className, "UTF-8");
/*     */       }
/* 132 */       catch (UnsupportedEncodingException e) {
/*     */         
/* 134 */         System.err.println(e);
/*     */       } 
/*     */       
/* 137 */       if (!SwingUtilities.isEventDispatchThread()) {
/*     */         
/* 139 */         TransportClient transportClient = this.m_TransClient.duplicate();
/* 140 */         result = transportClient.postData(String.valueOf(this.m_RootURI) + "?" + className, null, className.getBytes(), false, true);
/*     */       }
/*     */       else {
/*     */         
/* 144 */         synchronized (this.m_TransClient) {
/*     */           
/* 146 */           result = this.m_TransClient.postData(String.valueOf(this.m_RootURI) + "?" + className, null, className.getBytes(), false, true);
/*     */         } 
/*     */       } 
/* 149 */       if (result != null && result.length > 4)
/*     */       {
/* 151 */         if (result[0] != -54 || result[1] != -2 || result[2] != -70 || result[3] != -66) {
/*     */           
/*     */           try {
/*     */             
/* 155 */             int head = result[0] & 0xFF | result[1] << 8 & 0xFF00;
/* 156 */             if (35615 == head)
/*     */             {
/* 158 */               StdCompressor comp = new StdCompressor();
/* 159 */               result = comp.uncompress(result, 0, result.length);
/*     */             }
/*     */           
/*     */           }
/* 163 */           catch (Exception exception) {}
/*     */         }
/*     */       }
/*     */       
/* 167 */       if (result != null && result.length == 0)
/* 168 */         result = null; 
/*     */     } 
/* 170 */     if (result == null)
/* 171 */       this.m_FailedClasses.add(name); 
/* 172 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getResource(String name) {
/*     */     try {
/* 179 */       return new URL(new URL(this.m_RootURI), name, new RCL_URLStreamHandler(name));
/*     */     }
/* 181 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 184 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   class RCL_URLStreamHandler
/*     */     extends URLStreamHandler {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLStreamHandler(String name) {
/* 193 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     protected URLConnection openConnection(URL u) {
/* 198 */       return new JLbsClassLoaderDelegate.RCL_URLConnection(u, this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   class RCL_URLConnection
/*     */     extends URLConnection
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLConnection(URL u, String name) {
/* 208 */       super(u);
/* 209 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void connect() {}
/*     */ 
/*     */     
/*     */     public InputStream getInputStream() {
/* 218 */       return JLbsClassLoaderDelegate.this.getResourceAsStream(this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   public InputStream getResourceAsStream(String name) {
/*     */     byte[] cache;
/* 224 */     if (name == null) {
/* 225 */       return null;
/*     */     }
/* 227 */     synchronized (this.m_Resources) {
/*     */       
/* 229 */       cache = (byte[])this.m_Resources.get(name);
/*     */     } 
/* 231 */     if (cache != null && cache.length > 0)
/* 232 */       return new ByteArrayInputStream(cache); 
/* 233 */     byte[] data = JLbsJarCache.getEntry(name);
/* 234 */     if (data == null && !name.startsWith("META-INF/")) {
/*     */ 
/*     */       
/* 237 */       data = getClassData("@!" + name);
/* 238 */       synchronized (this.m_Resources) {
/*     */         
/* 240 */         this.m_Resources.put(name, (data != null) ? 
/* 241 */             data : 
/* 242 */             new byte[0]);
/*     */       } 
/*     */     } 
/* 245 */     if (data != null)
/* 246 */       return new ByteArrayInputStream(data); 
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGUID() {
/* 252 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsClassLoaderDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */