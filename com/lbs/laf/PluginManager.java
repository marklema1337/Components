/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.transport.JLbsJarCache;
/*     */ import com.lbs.transport.JLbsJarStreamHolder;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
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
/*     */ public class PluginManager
/*     */ {
/*     */   public static final String PLUGINS_DIR = "Plugins";
/*     */   
/*     */   public static void loadPlugin(IClientContext context, String libName, long libSize, IDownloadProgressListener progressListener) throws Exception {
/*  33 */     String libPath = loadLibrary(context, libName, libSize, progressListener);
/*     */ 
/*     */     
/*     */     try {
/*  37 */       JLbsJarStreamHolder holder = new JLbsJarStreamHolder();
/*  38 */       byte[] jarData = JLbsFileUtil.readFile(libPath);
/*  39 */       holder.load(jarData, true);
/*  40 */       if (libPath != null) {
/*  41 */         holder.setPathPrefix(JLbsJarCache.getResourcePath(libPath));
/*     */       }
/*  43 */       JLbsJarCache.add(libName, holder);
/*     */     }
/*  45 */     catch (Exception e) {
/*     */       
/*  47 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPluginPath(String libName) {
/*  53 */     String filePath = JLbsFileUtil.appendPath("Plugins", libName);
/*  54 */     return JLbsFileUtil.appendPath(JLbsFileUtil.getClientRootDirectory(), filePath);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String loadLibrary(IClientContext context, String libName, long libSize, IDownloadProgressListener progressListener) throws Exception {
/*  60 */     String libPath = getPluginPath(libName);
/*     */     
/*  62 */     if (!existsPlugin(context, libName, libSize)) {
/*  63 */       downloadPlugin(context, libName, libPath, progressListener);
/*     */     }
/*  65 */     return libPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean existsPlugin(IClientContext context, String libName, long libSize) {
/*  70 */     String libPath = getPluginPath(libName);
/*     */     
/*  72 */     File file = new File(libPath);
/*  73 */     if (!file.exists()) {
/*  74 */       return false;
/*     */     }
/*  76 */     long length = file.length();
/*     */     
/*  78 */     if (libSize >= 0L) {
/*  79 */       return (length == libSize);
/*     */     }
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void downloadPlugin(IClientContext context, String libName, String localFileName, IDownloadProgressListener progressListener) {
/*  87 */     String libraryURL = context.getRootUri();
/*  88 */     libraryURL = String.valueOf(libraryURL) + "resources/Jars/" + libName;
/*     */     
/*  90 */     OutputStream out = null;
/*  91 */     URLConnection conn = null;
/*  92 */     InputStream in = null;
/*  93 */     FileOutputStream fos = null;
/*     */     
/*     */     try {
/*  96 */       URL url = new URL(libraryURL);
/*  97 */       File localFile = new File(localFileName);
/*  98 */       localFile.getParentFile().mkdirs();
/*  99 */       fos = new FileOutputStream(localFile);
/* 100 */       out = new BufferedOutputStream(fos);
/* 101 */       conn = url.openConnection();
/* 102 */       in = conn.getInputStream();
/* 103 */       byte[] buffer = new byte[1024];
/*     */       
/* 105 */       long numWritten = 0L; int numRead;
/* 106 */       while ((numRead = in.read(buffer)) != -1) {
/*     */         
/* 108 */         out.write(buffer, 0, numRead);
/* 109 */         numWritten += numRead;
/*     */         
/* 111 */         if (progressListener != null) {
/* 112 */           progressListener.reportProgress(numWritten);
/*     */         }
/*     */       } 
/* 115 */     } catch (Exception exception) {
/*     */       
/* 117 */       exception.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 123 */         if (in != null)
/*     */         {
/* 125 */           in.close();
/*     */         }
/*     */       }
/* 128 */       catch (IOException ioe) {
/*     */         
/* 130 */         ioe.printStackTrace();
/*     */       } 
/*     */       
/*     */       try {
/* 134 */         if (out != null)
/*     */         {
/* 136 */           out.close();
/*     */         }
/*     */       }
/* 139 */       catch (IOException ioe) {
/*     */         
/* 141 */         ioe.printStackTrace();
/*     */       } 
/*     */       
/*     */       try {
/* 145 */         if (fos != null)
/*     */         {
/* 147 */           fos.close();
/*     */         }
/*     */       }
/* 150 */       catch (IOException ioe) {
/*     */         
/* 152 */         ioe.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\PluginManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */