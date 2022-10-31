/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.io.filefilter.IOFileFilter;
/*     */ import org.apache.commons.io.filefilter.WildcardFileFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*     */   public static void copyFile(String src, String dst, boolean removeDocType) {
/*  25 */     BufferedReader br = null;
/*  26 */     FileWriter writer = null;
/*     */     
/*     */     try {
/*  29 */       File srcFile = new File(src);
/*  30 */       File dstPath = new File(dst);
/*  31 */       File dstFile = new File(dstPath, srcFile.getName());
/*     */       
/*  33 */       br = getContentsReader(srcFile);
/*  34 */       writer = new FileWriter(dstFile.toString());
/*     */       
/*  36 */       while (br.ready())
/*     */       {
/*  38 */         String line = br.readLine();
/*     */         
/*  40 */         if (removeDocType && line.indexOf("DOCTYPE") < 0)
/*     */         {
/*  42 */           writer.write(line);
/*  43 */           writer.write("\n");
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  50 */     catch (IOException e) {
/*     */       
/*  52 */       LbsConsole.getLogger("Data.Client.FileUtil").error(null, e);
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/*  57 */       if (writer != null) {
/*     */         
/*     */         try {
/*  60 */           writer.close();
/*     */         }
/*  62 */         catch (IOException e) {
/*     */           
/*  64 */           LbsConsole.getLogger("Data.Client.FileUtil").error(e, e);
/*     */         } 
/*     */       }
/*  67 */       if (br != null) {
/*     */         
/*     */         try {
/*  70 */           br.close();
/*     */         }
/*  72 */         catch (IOException e) {
/*     */           
/*  74 */           LbsConsole.getLogger("Data.Client.FileUtil").error(e, e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getDocTypeLine(String file) {
/*  81 */     File srcFile = new File(file);
/*     */     
/*  83 */     if (!srcFile.exists()) {
/*  84 */       return "";
/*     */     }
/*  86 */     BufferedReader br = getContentsReader(srcFile);
/*     */     
/*     */     try {
/*  89 */       while (br.ready()) {
/*     */         
/*  91 */         String line = br.readLine();
/*     */         
/*  93 */         if (line != null) {
/*     */           
/*  95 */           boolean docType = (line.indexOf("DOCTYPE") >= 0);
/*  96 */           if (docType) {
/*  97 */             return line;
/*     */           }
/*     */         } 
/*     */       } 
/* 101 */       return null;
/*     */     }
/* 103 */     catch (IOException e) {
/*     */       
/* 105 */       LbsConsole.getLogger("Data.Client.FileUtil").error(null, e);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 112 */         br.close();
/*     */       }
/* 114 */       catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(String src, boolean removeDocType) {
/* 124 */     return getFileContents(src, removeDocType, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(String src, boolean removeDocType, String[] docTypeLine) {
/* 129 */     File srcFile = new File(src);
/*     */     
/* 131 */     if (!srcFile.exists()) {
/* 132 */       return "";
/*     */     }
/* 134 */     BufferedReader br = getContentsReader(srcFile);
/* 135 */     return getFileContents(br, removeDocType, docTypeLine);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(BufferedReader br, boolean removeDocType) {
/* 140 */     return getFileContents(br, removeDocType, (String[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFileContents(BufferedReader br, boolean removeDocType, String[] docTypeLine) {
/*     */     try {
/* 147 */       StringWriter writer = new StringWriter();
/*     */       
/* 149 */       while (br.ready()) {
/*     */         
/* 151 */         String line = br.readLine();
/* 152 */         if (line == null) {
/*     */           break;
/*     */         }
/* 155 */         if (!removeDocType) {
/*     */           
/* 157 */           writer.write(line);
/* 158 */           writer.write("\n");
/*     */           
/*     */           continue;
/*     */         } 
/* 162 */         boolean docType = (line.indexOf("DOCTYPE") >= 0);
/*     */         
/* 164 */         if (!docType) {
/*     */           
/* 166 */           writer.write(line);
/* 167 */           writer.write("\n");
/*     */         } 
/*     */         
/* 170 */         if (docType && docTypeLine != null && docTypeLine.length >= 1)
/*     */         {
/* 172 */           docTypeLine[0] = line;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 177 */       String s = writer.getBuffer().toString();
/* 178 */       writer.close();
/* 179 */       return s;
/*     */     }
/* 181 */     catch (IOException e) {
/*     */       
/* 183 */       LbsConsole.getLogger("Data.Client.FileUtil").error(null, e);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 190 */         br.close();
/*     */       }
/* 192 */       catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 197 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyFile(String src, String dst) {
/* 203 */     FileOutputStream out = null;
/* 204 */     FileInputStream in = null;
/*     */ 
/*     */     
/*     */     try {
/* 208 */       File dstPath = new File(dst);
/*     */       
/* 210 */       if (!dstPath.getParentFile().exists()) {
/* 211 */         dstPath.getParentFile().mkdirs();
/*     */       }
/*     */ 
/*     */       
/* 215 */       in = new FileInputStream(src);
/* 216 */       out = new FileOutputStream(dst);
/*     */       
/* 218 */       int chunk = 1024;
/* 219 */       byte[] buf = new byte[chunk];
/* 220 */       int length = chunk;
/* 221 */       while (length == chunk) {
/*     */         
/* 223 */         length = in.read(buf);
/* 224 */         if (length > 0) {
/* 225 */           out.write(buf, 0, length);
/*     */         }
/*     */       } 
/*     */       
/* 229 */       out.flush();
/*     */     
/*     */     }
/* 232 */     catch (IOException e) {
/*     */       
/* 234 */       LbsConsole.getLogger("Data.Client.FileUtil").error(null, e);
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 239 */       if (in != null) {
/*     */         
/*     */         try {
/* 242 */           in.close();
/*     */         }
/* 244 */         catch (IOException e) {
/*     */           
/* 246 */           LbsConsole.getLogger("Data.Client.FileUtil").error(e, e);
/*     */         } 
/*     */       }
/* 249 */       if (out != null) {
/*     */         
/*     */         try {
/* 252 */           out.close();
/*     */         }
/* 254 */         catch (IOException e) {
/*     */           
/* 256 */           LbsConsole.getLogger("Data.Client.FileUtil").error(e, e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedReader getContentsReader(File file) {
/*     */     try {
/* 265 */       return JLbsFileUtil.getContentsReader(file);
/*     */     }
/* 267 */     catch (IOException e) {
/*     */       
/* 269 */       LbsConsole.getLogger(FileUtil.class).error(e.getMessage(), e);
/* 270 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedReader createBufferedReader(InputStream in, byte[] buf, int l) throws IOException, UnsupportedEncodingException {
/* 277 */     return JLbsFileUtil.createBufferedReader(in, buf, l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rmdir(File path) {
/*     */     try {
/* 284 */       if (path.exists()) {
/* 285 */         delrec(path);
/*     */       }
/* 287 */     } catch (Exception e) {
/*     */       
/* 289 */       LbsConsole.getLogger("Data.Client.FileUtil").error(null, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void delrec(File f) throws IOException {
/* 297 */     if (f.isDirectory()) {
/*     */       
/* 299 */       File[] fs = f.listFiles();
/* 300 */       for (int i = 0; i < fs.length; i++)
/*     */       {
/* 302 */         delrec(fs[i]);
/*     */       }
/*     */     } 
/* 305 */     if (!f.delete())
/*     */     {
/* 307 */       throw new IOException("cannot delete " + f.getPath());
/*     */     }
/*     */   }
/*     */   
/*     */   public static String findLatestVersion(File folder, String fileNameWithExtension) {
/* 312 */     String extension = "." + FilenameUtils.getExtension(fileNameWithExtension);
/* 313 */     String baseJar = FilenameUtils.getBaseName(fileNameWithExtension);
/* 314 */     Collection<File> list = FileUtils.listFiles(folder, (IOFileFilter)new WildcardFileFilter(baseJar + "*" + extension), null);
/* 315 */     String latest = "";
/* 316 */     for (File cand : list) {
/* 317 */       String base = FilenameUtils.getBaseName(cand.getName());
/* 318 */       if (base.indexOf("-") > -1) {
/* 319 */         int lIODash = base.lastIndexOf("-");
/* 320 */         if (!baseJar.equals(base.substring(0, lIODash)) && list.size() > 1)
/*     */           continue; 
/* 322 */         String t = StringUtil.replaceAll(base.substring(lIODash + 1).replaceAll("b", "0").replaceAll("M", "0"), ".", "9");
/* 323 */         long candVer = Long.parseLong(t);
/* 324 */         if (candVer > Integer.parseInt(latest.isEmpty() ? "0" : StringUtil.replaceAll(latest.replaceAll("b", "0").replaceAll("M", "0"), ".", "9")))
/* 325 */           latest = base.substring(base.lastIndexOf("-") + 1); 
/*     */       } 
/*     */     } 
/* 328 */     return baseJar + (latest.isEmpty() ? "" : ("-" + latest)) + extension;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeFileTo(File in, OutputStream out) throws Exception {
/* 333 */     byte[] b = new byte[2048];
/* 334 */     int read = -1;
/* 335 */     FileInputStream fin = null;
/*     */     
/*     */     try {
/* 338 */       fin = new FileInputStream(in);
/* 339 */       while ((read = fin.read(b)) > 0) {
/* 340 */         out.write(b, 0, read);
/*     */       }
/*     */     } finally {
/*     */       
/* 344 */       if (fin != null) {
/* 345 */         fin.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void copyDirectoryTree(String src, String dest) {
/* 351 */     File srcDir = new File(src);
/* 352 */     File dstDir = new File(dest);
/*     */     
/* 354 */     if (srcDir.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 358 */         FileUtils.copyDirectory(srcDir, dstDir);
/*     */       }
/* 360 */       catch (IOException e) {
/*     */         
/* 362 */         LbsConsole.getLogger(FileUtil.class).error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copyFileToDirectory(File source, File dest) {
/* 369 */     if (source.exists() && dest.exists())
/*     */       
/*     */       try {
/*     */         
/* 373 */         FileUtils.copyFileToDirectory(source, dest);
/*     */       }
/* 375 */       catch (IOException e) {
/*     */         
/* 377 */         LbsConsole.getLogger(FileUtil.class).error(e.getMessage(), e);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\FileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */