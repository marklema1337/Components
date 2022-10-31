/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.security.CodeSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsClientFS
/*     */ {
/*  21 */   private static final byte[] S = new byte[] { 29, 5, 97, 17, 9, 19, 75, 17, 8, 83 };
/*  22 */   protected static String ms_RootPath = null;
/*     */ 
/*     */   
/*     */   public static String appendPath(String path, String fileName) {
/*  26 */     return String.valueOf(JLbsFileUtil.ensurePath(path)) + fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCachePath() {
/*  31 */     int pos = JLbsConstants.VERSION_STR.indexOf(".", JLbsConstants.VERSION_STR.indexOf(".") + 1);
/*  32 */     return JLbsFileUtil.appendPath(ms_RootPath, "Cache_" + JLbsConstants.VERSION_STR.substring(0, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRootPath(String path) {
/*  37 */     String newPath = JLbsFileUtil.ensurePath(path);
/*  38 */     newPath = newPath.replaceAll("\\\\", "/");
/*  39 */     if (File.separatorChar != '/')
/*  40 */       newPath = newPath.replace('/', File.separatorChar); 
/*  41 */     JLbsFileUtil.makeDirectory(ms_RootPath, newPath);
/*  42 */     ms_RootPath = String.valueOf(newPath) + File.separator;
/*  43 */     if (JLbsConstants.LOGLEVEL > 4) {
/*  44 */       System.out.println("Root directory is now " + ms_RootPath);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, boolean bOverwrite, boolean bAppend, boolean bScramble) throws IOException {
/*  50 */     return saveFile(path, data, 0, (data != null) ? 
/*  51 */         data.length : 
/*  52 */         0, bOverwrite, bAppend, bScramble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, int index, int length, boolean bOverwrite, boolean bAppend, boolean bScramble) throws IOException {
/*     */     try {
/*  60 */       File file = new File(getFullPath(path));
/*  61 */       if (file.exists() && !bOverwrite)
/*  62 */         return false; 
/*  63 */       JLbsFileUtil.makeDirectory(ms_RootPath, extractPath(path));
/*  64 */       FileOutputStream stream = new FileOutputStream(file, bAppend);
/*  65 */       if (bScramble && data != null && data.length > 0) {
/*     */         
/*  67 */         byte[] temp = data;
/*  68 */         data = new byte[temp.length];
/*  69 */         for (int i = 0; i < temp.length; i++)
/*  70 */           data[i] = (byte)(temp[i] ^ S[i % 10]); 
/*     */       } 
/*  72 */       stream.write(data, index, length);
/*  73 */       stream.close();
/*  74 */       return true;
/*     */     }
/*  76 */     catch (IOException e) {
/*     */       
/*  78 */       throw e;
/*     */     }
/*  80 */     catch (Exception e) {
/*     */       
/*  82 */       System.out.println("saveFile :" + e);
/*     */       
/*  84 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, boolean bOverwrite, boolean bAppend, boolean bScramble, String charSet) throws IOException {
/*  90 */     return saveFile(path, data, 0, (data != null) ? 
/*  91 */         data.length : 
/*  92 */         0, bOverwrite, bAppend, bScramble, charSet);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, int index, int length, boolean bOverwrite, boolean bAppend, boolean bScramble, String encoding) throws IOException {
/*  98 */     FileOutputStream stream = null;
/*     */     
/*     */     try {
/* 101 */       File file = new File(getFullPath(path));
/* 102 */       if (file.exists() && !bOverwrite)
/* 103 */         return false; 
/* 104 */       JLbsFileUtil.makeDirectory(ms_RootPath, extractPath(path));
/* 105 */       stream = new FileOutputStream(file, bAppend);
/* 106 */       if (bScramble && data != null && data.length > 0) {
/*     */         
/* 108 */         byte[] temp = data;
/* 109 */         data = new byte[temp.length];
/* 110 */         for (int i = 0; i < temp.length; i++)
/* 111 */           data[i] = (byte)(temp[i] ^ S[i % 10]); 
/*     */       } 
/* 113 */       if (data != null) {
/*     */         
/* 115 */         String asString = new String(data);
/* 116 */         byte[] newData = asString.getBytes(encoding);
/* 117 */         stream.write(newData, index, newData.length);
/*     */       } 
/*     */       
/* 120 */       return true;
/*     */     }
/* 122 */     catch (IOException e) {
/*     */       
/* 124 */       throw e;
/*     */     }
/* 126 */     catch (Exception e) {
/*     */       
/* 128 */       System.out.println("saveFile :" + e);
/*     */     }
/*     */     finally {
/*     */       
/* 132 */       if (stream != null)
/* 133 */         stream.close(); 
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public static byte[] loadFile(String path, boolean bScramble) throws IOException {
/*     */     int readLen;
/* 140 */     byte[] result = null;
/* 141 */     File file = new File(getFullPath(path));
/* 142 */     if (!file.exists())
/* 143 */       return null; 
/* 144 */     FileInputStream stream = new FileInputStream(file);
/* 145 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 146 */     byte[] buffer = new byte[4096];
/*     */ 
/*     */     
/*     */     do {
/* 150 */       readLen = stream.read(buffer, 0, buffer.length);
/* 151 */       if (readLen <= 0)
/* 152 */         continue;  outStream.write(buffer, 0, readLen);
/*     */     }
/* 154 */     while (readLen > 0);
/* 155 */     stream.close();
/* 156 */     result = outStream.toByteArray();
/* 157 */     outStream.close();
/* 158 */     if (bScramble)
/* 159 */       checkBytes(result); 
/* 160 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean deleteFile(String path) {
/* 165 */     File file = new File(getFullPath(path));
/* 166 */     return file.delete();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String extractPath(String filePath) {
/* 171 */     File file = new File(filePath);
/* 172 */     return file.getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fileExists(String filePath) {
/* 177 */     File file = new File(getFullPath(filePath));
/* 178 */     return file.exists();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkBytes(byte[] data) {
/* 183 */     if (data != null)
/* 184 */       for (int i = 0; i < data.length; i++) {
/* 185 */         data[i] = (byte)(data[i] ^ S[i % 10]);
/*     */       } 
/*     */   }
/*     */   
/*     */   public static String divineClassDirectory(Class<JLbsClientFS> cls) {
/* 190 */     if (cls == null)
/* 191 */       cls = JLbsClientFS.class; 
/* 192 */     CodeSource source = cls.getProtectionDomain().getCodeSource();
/* 193 */     if (source == null)
/* 194 */       return null; 
/* 195 */     File dataDir = null;
/*     */     
/*     */     try {
/* 198 */       URI sourceURI = new URI(source.getLocation().toString());
/* 199 */       dataDir = new File(sourceURI);
/*     */     }
/* 201 */     catch (Exception e) {
/*     */       
/* 203 */       return null;
/*     */     } 
/* 205 */     if (!dataDir.isDirectory())
/* 206 */       dataDir = dataDir.getParentFile(); 
/* 207 */     return (dataDir != null) ? 
/* 208 */       dataDir.getAbsolutePath() : 
/* 209 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean makeDirectory(String path) {
/* 214 */     return JLbsFileUtil.makeDirectory(getFullPath(path));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFullPath(String path) {
/* 219 */     if (ms_RootPath == null || path == null || path.indexOf(ms_RootPath) >= 0)
/* 220 */       return path; 
/* 221 */     return JLbsFileUtil.appendPath(ms_RootPath, path);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getRootPath() {
/* 226 */     return (ms_RootPath == null) ? 
/* 227 */       null : 
/* 228 */       JLbsFileUtil.ensurePath(ms_RootPath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFullFileName(String searchDir, String fileNamePrefix) {
/* 238 */     if (JLbsStringUtil.isEmpty(ms_RootPath)) {
/* 239 */       return null;
/*     */     }
/* 241 */     String[] fileNames = JLbsFileUtil.getFileListUnderDirectory(appendPath(ms_RootPath, searchDir), fileNamePrefix, true);
/*     */     
/* 243 */     if (fileNames == null || fileNames.length != 1) {
/* 244 */       return null;
/*     */     }
/* 246 */     return fileNames[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] getFullFileNames(String searchDir, String fileNamePrefix) {
/* 251 */     if (JLbsStringUtil.isEmpty(ms_RootPath))
/* 252 */       return null; 
/* 253 */     return JLbsFileUtil.getFileListUnderDirectory(appendPath(ms_RootPath, searchDir), fileNamePrefix, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsClientFS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */