/*      */ package com.lbs.util;
/*      */ 
/*      */ import com.lbs.dialogs.LbsObjectFactory;
/*      */ import com.lbs.dialogs.interfaces.ILbsPathConverter;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileWriter;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Locale;
/*      */ import java.util.zip.CRC32;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipFile;
/*      */ import java.util.zip.ZipOutputStream;
/*      */ import javax.swing.text.BadLocationException;
/*      */ import javax.swing.text.Document;
/*      */ import javax.swing.text.EditorKit;
/*      */ import javax.swing.text.html.HTMLEditorKit;
/*      */ import javax.swing.text.rtf.RTFEditorKit;
/*      */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*      */ import org.apache.commons.compress.archivers.zip.ZipFile;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsFileUtil
/*      */ {
/*   54 */   private static final boolean IS_WINDOWS = (File.separatorChar == '\\');
/*      */   
/*      */   private static String ms_RootDir;
/*      */   public static final char m_FileSeparator = '/';
/*      */   
/*      */   public static InputStream tryToOpenFile(String path) {
/*   60 */     ZipFile zipFile = null;
/*      */ 
/*      */     
/*   63 */     try { File file = new File(path);
/*   64 */       if (file.exists())
/*   65 */         return new FileInputStream(file); 
/*   66 */       File parentFile = file.getParentFile();
/*   67 */       String extractedPath = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */        }
/*      */     
/*   95 */     catch (Exception exception)
/*      */     
/*      */     {  }
/*      */     
/*      */     finally
/*      */     
/*  101 */     { if (zipFile != null)
/*      */         
/*      */         try {
/*      */           
/*  105 */           zipFile.close();
/*      */         }
/*  107 */         catch (IOException iOException) {}  }  if (zipFile != null) try { zipFile.close(); } catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  112 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getFileName(String filePath) {
/*  117 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  118 */       return filePath;
/*      */     }
/*  120 */     int slashIndex = Math.max(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')), 
/*  121 */         filePath.lastIndexOf(File.separatorChar)) + 1;
/*      */     
/*  123 */     if (slashIndex < filePath.length())
/*  124 */       return filePath.substring(slashIndex); 
/*  125 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public static void appendLineToTextFile(File file, String line) {
/*  130 */     PrintWriter out = null;
/*      */     
/*      */     try {
/*  133 */       out = new PrintWriter(new BufferedWriter(new FileWriter(file, file.exists())));
/*  134 */       out.println(line);
/*      */     }
/*  136 */     catch (IOException iOException) {
/*      */ 
/*      */     
/*      */     } finally {
/*      */       
/*  141 */       if (out != null) {
/*  142 */         out.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String getFileName(String filePath, boolean extractExtension) {
/*  148 */     String fileName = getFileName(filePath);
/*      */     
/*  150 */     if (JLbsStringUtil.isEmpty(fileName) || !extractExtension) {
/*  151 */       return fileName;
/*      */     }
/*  153 */     String extension = getExtension(fileName);
/*      */     
/*  155 */     int idx = -1;
/*  156 */     if (JLbsStringUtil.isEmpty(extension) || (idx = fileName.lastIndexOf(extension)) <= 0) {
/*  157 */       return fileName;
/*      */     }
/*  159 */     return fileName.substring(0, idx - 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getFileDir(String filePath) {
/*  164 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  165 */       return filePath;
/*      */     }
/*  167 */     String fileName = getFileName(filePath);
/*      */     
/*  169 */     return ensurePath(filePath.substring(0, filePath.lastIndexOf(fileName)));
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getRefinedFileDir(String filePath) {
/*  174 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  175 */       return filePath;
/*      */     }
/*  177 */     String fileName = getFileName(filePath);
/*  178 */     fileName = ensurePath(filePath.substring(0, filePath.lastIndexOf(fileName)));
/*  179 */     fileName = refinePath(fileName);
/*  180 */     return fileName;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String filterFileExtension(String filePath) {
/*  185 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  186 */       return filePath;
/*      */     }
/*  188 */     String ext = getExtension(filePath);
/*      */     
/*  190 */     if (JLbsStringUtil.isEmpty(ext)) {
/*  191 */       return ext;
/*      */     }
/*  193 */     return filePath.substring(0, filePath.indexOf("." + ext));
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte[] readFile(String path) throws IOException {
/*  198 */     return readFile(new FileInputStream(path));
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte[] readFile(InputStream stream) throws IOException {
/*  203 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream(stream.available());
/*  204 */     transferData(stream, outStream);
/*  205 */     return outStream.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void transferData(InputStream stream, OutputStream outStream) throws IOException {
/*  210 */     byte[] buffer = new byte[4096];
/*      */     int read;
/*  212 */     while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/*  213 */       outStream.write(buffer, 0, read); 
/*  214 */     stream.close();
/*      */   }
/*      */ 
/*      */   
/*      */   public static File write2File(String filePath, byte[] data) {
/*  219 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  220 */       return null;
/*      */     }
/*  222 */     File f = new File(filePath);
/*  223 */     FileOutputStream stream = null;
/*      */     
/*      */     try {
/*  226 */       f.createNewFile();
/*  227 */       stream = (FileOutputStream)LbsObjectFactory.createObject(FileOutputStream.class, new Object[] { f });
/*  228 */       stream.write(data);
/*  229 */       stream.flush();
/*      */     }
/*  231 */     catch (IOException e) {
/*      */       
/*  233 */       e.printStackTrace();
/*  234 */       return null;
/*      */     }
/*      */     finally {
/*      */       
/*  238 */       if (stream != null) {
/*      */         
/*      */         try {
/*      */           
/*  242 */           stream.close();
/*      */         }
/*  244 */         catch (IOException iOException) {}
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  249 */     return f;
/*      */   }
/*      */ 
/*      */   
/*      */   public static File write2File(String filePath, ByteArrayOutputStream baos) {
/*  254 */     if (JLbsStringUtil.isEmpty(filePath)) {
/*  255 */       return null;
/*      */     }
/*  257 */     File f = new File(filePath);
/*  258 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  261 */       fos = new FileOutputStream(f);
/*  262 */       baos.writeTo(fos);
/*      */     }
/*  264 */     catch (IOException ioe) {
/*      */       
/*  266 */       ioe.printStackTrace();
/*  267 */       return null;
/*      */     }
/*      */     finally {
/*      */       
/*  271 */       if (fos != null) {
/*      */         
/*      */         try {
/*  274 */           fos.close();
/*      */         }
/*  276 */         catch (IOException iOException) {}
/*      */       }
/*      */     } 
/*      */     
/*  280 */     return f;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String readTextFile(String path) throws IOException {
/*  285 */     return readTextFile(path, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String readTextFile(String path, String encoding) throws IOException {
/*  290 */     byte[] data = readFile(path);
/*  291 */     if (data != null && data.length > 0)
/*  292 */       return (encoding != null) ? 
/*  293 */         new String(data, encoding) : 
/*  294 */         JLbsStringUtil.getString(data); 
/*  295 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static File getJarFile(String path) {
/*  300 */     File file = new File(String.valueOf(path) + ".jar");
/*  301 */     if (file.exists())
/*  302 */       return file; 
/*  303 */     file = new File(String.valueOf(path) + ".zip");
/*  304 */     if (file.exists())
/*  305 */       return file; 
/*  306 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String changeExtension(String filePath, String newExt) {
/*  311 */     if (!JLbsStringUtil.isEmpty(filePath)) {
/*      */       
/*  313 */       int dotIndex = filePath.lastIndexOf('.');
/*  314 */       String fileName = filePath;
/*  315 */       if (dotIndex > 0) {
/*      */         
/*  317 */         int slashIndex = Math.max(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')), 
/*  318 */             filePath.lastIndexOf(File.separatorChar));
/*  319 */         fileName = (dotIndex < slashIndex) ? 
/*  320 */           filePath : 
/*  321 */           filePath.substring(0, dotIndex);
/*      */       } 
/*  323 */       return String.valueOf(fileName) + ((newExt != null) ? 
/*  324 */         newExt : 
/*  325 */         "");
/*      */     } 
/*  327 */     return filePath;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getExtension(String filePath) {
/*  332 */     if (!JLbsStringUtil.isEmpty(filePath)) {
/*      */       
/*  334 */       int dotIndex = filePath.lastIndexOf('.');
/*  335 */       if (dotIndex > 0) {
/*      */         
/*  337 */         int slashIndex = Math.max(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')), 
/*  338 */             filePath.lastIndexOf(File.separatorChar));
/*  339 */         return (dotIndex < slashIndex) ? 
/*  340 */           null : 
/*  341 */           filePath.substring(dotIndex + 1);
/*      */       } 
/*      */     } 
/*  344 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String ensurePath(String path) {
/*  349 */     return ensurePath(path, File.separatorChar);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String ensureFileName(String path) {
/*  354 */     if (!JLbsStringUtil.isEmpty(path) && path.startsWith(File.separator))
/*  355 */       return path.substring(1); 
/*  356 */     return path;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String ensurePath(String path, char separator) {
/*  361 */     String result = (path != null) ? 
/*  362 */       path : 
/*  363 */       "";
/*  364 */     if (result.lastIndexOf(separator) != result.length() - 1)
/*  365 */       result = String.valueOf(result) + separator; 
/*  366 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean makeDirectory(String directory) {
/*  371 */     return makeDirectory(null, directory);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean makeDirectory(String rootDirectory, String directory) {
/*  376 */     if (directory == null || directory.length() == 0)
/*  377 */       return false; 
/*  378 */     if (File.separatorChar != '/')
/*  379 */       directory = directory.replace(File.separatorChar, '/'); 
/*  380 */     String[] tokens = directory.split("/");
/*      */     
/*      */     try {
/*  383 */       int i = 0;
/*  384 */       String path = (rootDirectory == null) ? 
/*  385 */         "" : 
/*  386 */         rootDirectory;
/*  387 */       if (path.length() > 0 && !path.endsWith(File.separator))
/*  388 */         path = String.valueOf(path) + File.separator; 
/*  389 */       if (tokens.length > 1 && tokens[0].indexOf(":") >= 0) {
/*      */         
/*  391 */         path = String.valueOf(path) + tokens[0] + File.separator;
/*  392 */         i = 1;
/*      */       
/*      */       }
/*  395 */       else if (tokens.length > 1 && tokens[0] != null && tokens[0].length() == 0) {
/*      */         
/*  397 */         path = String.valueOf(path) + tokens[0] + File.separator;
/*  398 */         i = 1;
/*      */       } 
/*  400 */       StringBuilder pathBuff = new StringBuilder(path);
/*  401 */       for (; i < tokens.length; i++) {
/*      */         
/*  403 */         pathBuff.append(tokens[i]);
/*      */         
/*  405 */         File file = new File(pathBuff.toString());
/*  406 */         if (!file.isDirectory() && !file.mkdir())
/*  407 */           return false; 
/*  408 */         pathBuff.append(File.separator);
/*      */       } 
/*      */       
/*  411 */       path = pathBuff.toString();
/*  412 */       return true;
/*      */     }
/*  414 */     catch (Exception exception) {
/*      */ 
/*      */       
/*  417 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getFileTimeStamp(String filePath) {
/*      */     try {
/*  424 */       File file = new File(filePath);
/*  425 */       return file.lastModified();
/*      */     }
/*  427 */     catch (Exception e) {
/*      */       
/*  429 */       if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL >= 5) {
/*  430 */         e.printStackTrace();
/*      */       }
/*  432 */       return 0L;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String normalizeFilePath(String path) {
/*  437 */     if (path == null || File.separatorChar == '/')
/*  438 */       return path; 
/*  439 */     StringBuilder buffer = new StringBuilder(path);
/*  440 */     for (int i = 0; i < buffer.length(); i++) {
/*  441 */       if (buffer.charAt(i) == '/')
/*  442 */         buffer.setCharAt(i, File.separatorChar); 
/*  443 */     }  return buffer.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String ensureFilePathWithSystem(String path) {
/*  448 */     if (path == null)
/*  449 */       return path; 
/*  450 */     StringBuilder buffer = new StringBuilder(path);
/*  451 */     for (int i = 0; i < buffer.length(); i++) {
/*  452 */       if (buffer.charAt(i) == '/' || buffer.charAt(i) == '\\')
/*  453 */         buffer.setCharAt(i, File.separatorChar); 
/*  454 */     }  return buffer.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String appendPath(String path, String[] dirs) {
/*  459 */     path = ensurePath(path);
/*  460 */     for (int i = 0; i < dirs.length; i++) {
/*      */       
/*  462 */       path = String.valueOf(path) + ensureFileName(dirs[i]);
/*  463 */       if (i < dirs.length - 1)
/*  464 */         path = ensurePath(path); 
/*      */     } 
/*  466 */     return path;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String appendPath(String path, String fileName) {
/*  471 */     if (!JLbsStringUtil.isEmpty(path)) {
/*      */       
/*  473 */       if (path.lastIndexOf('\\') != path.length() - 1) {
/*      */         
/*  475 */         if (path.lastIndexOf('/') == path.length() - 1)
/*      */         {
/*  477 */           return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*      */         }
/*  479 */         if (!JLbsStringUtil.isEmpty(fileName) && fileName.indexOf('\\') == 0)
/*      */         {
/*  481 */           return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*      */         }
/*      */ 
/*      */         
/*  485 */         return String.valueOf(ensurePath(path, '/')) + ensureFileName(fileName);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  490 */       return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*      */     } 
/*      */ 
/*      */     
/*  494 */     return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String appendPath(String path, String fileName, char separator) {
/*  499 */     return String.valueOf(ensurePath(path, separator)) + ensureFileName(fileName);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getUserDirectory() {
/*  504 */     return System.getProperty("user.dir");
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getDocumentDirectory() {
/*  509 */     return String.valueOf(System.getProperty("user.home")) + File.separator + "Documents" + File.separator;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getClientRootDirectory(String relPath) {
/*  514 */     return appendPath(getClientRootDirectory(), relPath);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getClientRootDirectory() {
/*  519 */     if (ms_RootDir != null && ms_RootDir.length() > 2)
/*  520 */       return ms_RootDir; 
/*  521 */     String path = getTempDirectory();
/*  522 */     if (path == null || path.length() == 0)
/*  523 */       path = (new File(".")).getAbsolutePath(); 
/*  524 */     path = appendPath(path, "LFS");
/*  525 */     String user = getSystemUserFolderName();
/*  526 */     if (!JLbsStringUtil.isEmpty(user)) {
/*  527 */       path = appendPath(path, user);
/*      */     }
/*      */     try {
/*  530 */       File dir = new File(path);
/*  531 */       if (!dir.exists()) {
/*  532 */         makeDirectory(path);
/*      */       }
/*  534 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  537 */     return path;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getTempDirectory() {
/*  542 */     return System.getProperty("java.io.tmpdir");
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getSystemUserFolderName() {
/*  547 */     String user = System.getProperty("user.name");
/*  548 */     if (user == null || user.length() <= 0) {
/*  549 */       user = JLbsConstants.SYSUSER_NOTFOUND_FOLDER;
/*      */     }
/*  551 */     return user;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean ensurePathExists(File file) {
/*  556 */     if (file == null)
/*  557 */       return true; 
/*  558 */     if (file.exists() && file.isDirectory())
/*  559 */       return true; 
/*  560 */     if (!ensurePathExists(file.getParentFile()))
/*  561 */       return false; 
/*  562 */     return file.mkdir();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFile(String sourceName, String destName) throws IOException {
/*  568 */     FileChannel in = null, out = null;
/*  569 */     FileInputStream fis = null;
/*  570 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  573 */       fis = new FileInputStream(sourceName);
/*  574 */       in = fis.getChannel();
/*  575 */       File destFile = new File(destName);
/*  576 */       fos = new FileOutputStream(destFile);
/*  577 */       out = fos.getChannel();
/*  578 */       out.transferFrom(in, 0L, in.size());
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  585 */         if (fis != null) {
/*  586 */           fis.close();
/*      */         }
/*  588 */       } catch (IOException ex) {
/*      */         
/*  590 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  594 */         if (fos != null) {
/*  595 */           fos.close();
/*      */         }
/*  597 */       } catch (IOException ex) {
/*      */         
/*  599 */         ex.printStackTrace();
/*      */       } 
/*  601 */       if (in != null)
/*  602 */         in.close(); 
/*  603 */       if (out != null) {
/*  604 */         out.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void copyFile(File source, String destName) throws IOException {
/*  610 */     FileChannel in = null, out = null;
/*  611 */     FileInputStream fis = null;
/*  612 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  615 */       fis = new FileInputStream(source);
/*  616 */       in = fis.getChannel();
/*  617 */       File destFile = new File(destName);
/*  618 */       fos = new FileOutputStream(destFile);
/*  619 */       out = fos.getChannel();
/*  620 */       out.transferFrom(in, 0L, in.size());
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  627 */         if (fis != null) {
/*  628 */           fis.close();
/*      */         }
/*  630 */       } catch (IOException ex) {
/*      */         
/*  632 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  636 */         if (fos != null) {
/*  637 */           fos.close();
/*      */         }
/*  639 */       } catch (IOException ex) {
/*      */         
/*  641 */         ex.printStackTrace();
/*      */       } 
/*  643 */       if (in != null)
/*  644 */         in.close(); 
/*  645 */       if (out != null) {
/*  646 */         out.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean deleteFile(String filePath) {
/*      */     try {
/*  654 */       File fileToDelete = new File(filePath);
/*  655 */       return fileToDelete.delete();
/*      */     }
/*  657 */     catch (Exception e) {
/*      */       
/*  659 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean deleteDirectory(String filePath) {
/*  665 */     File fileToDelete = new File(filePath);
/*  666 */     if (fileToDelete.exists()) {
/*      */       
/*  668 */       String[] filePaths = getFileListUnderDirectory(filePath);
/*  669 */       for (int i = 0; i < filePaths.length; i++) {
/*      */         
/*  671 */         String innerFilePath = String.valueOf(filePath) + File.separator + filePaths[i];
/*  672 */         File innerFile = new File(innerFilePath);
/*      */         
/*  674 */         if (innerFile.exists())
/*      */         {
/*      */           
/*  677 */           if (innerFile.isDirectory()) {
/*  678 */             deleteDirectory(innerFilePath);
/*      */           } else {
/*  680 */             innerFile.delete();
/*      */           }  } 
/*      */       } 
/*  683 */     }  return fileToDelete.delete();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFile(File source, File dest) throws IOException {
/*  689 */     FileChannel in = null, out = null;
/*  690 */     FileInputStream fis = null;
/*  691 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  694 */       fis = new FileInputStream(source);
/*  695 */       in = fis.getChannel();
/*  696 */       fos = new FileOutputStream(dest);
/*  697 */       out = fos.getChannel();
/*  698 */       out.transferFrom(in, 0L, in.size());
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  705 */         if (fis != null) {
/*  706 */           fis.close();
/*      */         }
/*  708 */       } catch (IOException ex) {
/*      */         
/*  710 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  714 */         if (fos != null) {
/*  715 */           fos.close();
/*      */         }
/*  717 */       } catch (IOException ex) {
/*      */         
/*  719 */         ex.printStackTrace();
/*      */       } 
/*  721 */       if (in != null)
/*  722 */         in.close(); 
/*  723 */       if (out != null) {
/*  724 */         out.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String[] getFileListUnderDirectory(String directoryPath) {
/*  730 */     File dir = new File(directoryPath);
/*  731 */     if (!dir.exists())
/*  732 */       return null; 
/*  733 */     return dir.list();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getFolderListUnderDirectory(String directoryPath) {
/*  738 */     File dir = new File(directoryPath);
/*  739 */     if (!dir.exists()) {
/*  740 */       return null;
/*      */     }
/*  742 */     FileFilter filter = new FileFilter()
/*      */       {
/*      */         public boolean accept(File pathname)
/*      */         {
/*  746 */           return pathname.isDirectory();
/*      */         }
/*      */       };
/*      */     
/*  750 */     File[] files = dir.listFiles(filter);
/*  751 */     if (files == null)
/*  752 */       return null; 
/*  753 */     String[] arr = new String[files.length];
/*  754 */     for (int i = 0; i < arr.length; i++)
/*      */     {
/*  756 */       arr[i] = files[i].getName();
/*      */     }
/*  758 */     return arr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] getFileListUnderDirectory(String directoryPath, String filterText, boolean isPrefix) {
/*  767 */     return getFileListUnderDirectory(directoryPath, filterText, isPrefix, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] getFileListUnderDirectory(String directoryPath, String filterText, final boolean isPrefix, final boolean caseSensitive) {
/*  773 */     File dir = new File(directoryPath);
/*  774 */     if (!dir.exists())
/*  775 */       return null; 
/*  776 */     final String filterStr = caseSensitive ? 
/*  777 */       filterText : 
/*  778 */       filterText.toLowerCase(Locale.UK);
/*  779 */     FilenameFilter filter = new FilenameFilter()
/*      */       {
/*      */         public boolean accept(File dir, String name)
/*      */         {
/*  783 */           if (!caseSensitive)
/*  784 */             name = name.toLowerCase(Locale.UK); 
/*  785 */           if (isPrefix) {
/*  786 */             return name.startsWith(filterStr);
/*      */           }
/*  788 */           return name.endsWith(filterStr);
/*      */         }
/*      */       };
/*      */     
/*  792 */     String[] children = dir.list(filter);
/*  793 */     return children;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getFolderListUnderDirectory(String directoryPath, final String filterText, final boolean isPrefix) {
/*  798 */     File dir = new File(directoryPath);
/*  799 */     if (!dir.exists())
/*  800 */       return null; 
/*  801 */     FileFilter filter = new FileFilter()
/*      */       {
/*      */         public boolean accept(File pathname)
/*      */         {
/*  805 */           if (isPrefix) {
/*  806 */             return (pathname.isDirectory() && pathname.getName().startsWith(filterText));
/*      */           }
/*  808 */           return (pathname.isDirectory() && pathname.getName().endsWith(filterText));
/*      */         }
/*      */       };
/*      */     
/*  812 */     File[] files = dir.listFiles(filter);
/*  813 */     if (files == null)
/*  814 */       return null; 
/*  815 */     String[] arr = new String[files.length];
/*  816 */     for (int i = 0; i < arr.length; i++)
/*      */     {
/*  818 */       arr[i] = files[i].getName();
/*      */     }
/*  820 */     return arr;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getExcludedFolderListUnderDirectory(String directoryPath, final String filterText, final boolean isPrefix) {
/*  825 */     File dir = new File(directoryPath);
/*  826 */     if (!dir.exists())
/*  827 */       return null; 
/*  828 */     FilenameFilter filter = new FilenameFilter()
/*      */       {
/*      */         public boolean accept(File dir, String name)
/*      */         {
/*  832 */           if (isPrefix) {
/*  833 */             return (dir.isDirectory() && !name.startsWith(filterText));
/*      */           }
/*  835 */           return (dir.isDirectory() && !name.endsWith(filterText));
/*      */         }
/*      */       };
/*      */     
/*  839 */     String[] children = dir.list(filter);
/*  840 */     return children;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] getExcludedFolderListUnderDirectory(String directoryPath, final String filterText, final boolean isPrefix, final int folderNameLength) {
/*  846 */     File dir = new File(directoryPath);
/*  847 */     if (!dir.exists())
/*  848 */       return null; 
/*  849 */     FilenameFilter filter = new FilenameFilter()
/*      */       {
/*      */         public boolean accept(File dir, String name)
/*      */         {
/*  853 */           if (isPrefix) {
/*  854 */             return (dir.isDirectory() && !name.startsWith(filterText) && name.length() == folderNameLength);
/*      */           }
/*  856 */           return (dir.isDirectory() && !name.endsWith(filterText) && name.length() == folderNameLength);
/*      */         }
/*      */       };
/*      */     
/*  860 */     String[] children = dir.list(filter);
/*  861 */     return children;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getFileListUnderDirectory(String directoryPath, final String excludedFilePrefix) {
/*  866 */     File dir = new File(directoryPath);
/*  867 */     if (!dir.exists())
/*  868 */       return null; 
/*  869 */     FilenameFilter filter = new FilenameFilter()
/*      */       {
/*      */         public boolean accept(File dir, String name)
/*      */         {
/*  873 */           return !name.startsWith(excludedFilePrefix);
/*      */         }
/*      */       };
/*      */     
/*  877 */     String[] children = dir.list(filter);
/*  878 */     return children;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getFolderListUnderDirectory(String directoryPath, final String excludedFilePrefix) {
/*  883 */     File dir = new File(directoryPath);
/*  884 */     if (!dir.exists())
/*  885 */       return null; 
/*  886 */     FileFilter filter = new FileFilter()
/*      */       {
/*      */         public boolean accept(File pathname)
/*      */         {
/*  890 */           return (pathname.isDirectory() && !pathname.getName().startsWith(excludedFilePrefix));
/*      */         }
/*      */       };
/*      */     
/*  894 */     File[] files = dir.listFiles(filter);
/*  895 */     if (files == null)
/*  896 */       return null; 
/*  897 */     String[] arr = new String[files.length];
/*  898 */     for (int i = 0; i < arr.length; i++)
/*      */     {
/*  900 */       arr[i] = files[i].getName();
/*      */     }
/*  902 */     return arr;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void openFile(String absolutePath) throws IOException {
/*  907 */     if (System.getProperty("os.name").startsWith("Windows"))
/*      */     {
/*  909 */       Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + absolutePath);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static String refinePath(String path) {
/*  915 */     if (path == null)
/*  916 */       return null; 
/*  917 */     path = path.replace('/', File.separatorChar);
/*  918 */     path = path.replace('\\', File.separatorChar);
/*  919 */     return path;
/*      */   }
/*      */ 
/*      */   
/*      */   public static BufferedReader getContentsReader(File file) throws IOException {
/*  924 */     BufferedReader br = null;
/*  925 */     InputStream in = new FileInputStream(file);
/*      */     
/*  927 */     byte[] buf = new byte[10];
/*  928 */     int l = in.read(buf);
/*  929 */     in.close();
/*      */     
/*  931 */     in = new FileInputStream(file);
/*      */     
/*  933 */     br = createBufferedReader(in, buf, l);
/*      */     
/*  935 */     return br;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedReader createBufferedReader(InputStream in, byte[] buf, int l) throws IOException, UnsupportedEncodingException {
/*  942 */     String encoding = "utf-8";
/*  943 */     if (l > 1)
/*      */     {
/*  945 */       if (buf[0] == -1 && buf[1] == -2) {
/*  946 */         encoding = "unicode";
/*  947 */       } else if (buf[0] == -2 && buf[1] == -1) {
/*  948 */         encoding = "unicode";
/*  949 */       } else if (buf[0] == -17 && buf[1] == -69 && buf.length > 2 && buf[2] == -65) {
/*  950 */         in.skip(3L);
/*      */       } 
/*      */     }
/*  953 */     InputStreamReader reader = new InputStreamReader(in, encoding);
/*  954 */     BufferedReader br = new BufferedReader(reader);
/*  955 */     return br;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void zip(File sourceDir, File target, boolean compress) throws FileNotFoundException, IOException {
/*  960 */     zip(sourceDir.listFiles(), sourceDir, target, compress);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void zip(File[] files, File sourceDir, File target, boolean compress) throws FileNotFoundException, IOException {
/*  965 */     zip(files, sourceDir, target, compress, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void zip(File[] files, File sourceDir, File target, boolean compress, String[] excluded) throws FileNotFoundException, IOException {
/*  972 */     if (!target.getParentFile().exists()) {
/*  973 */       target.getParentFile().mkdirs();
/*      */     }
/*  975 */     ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));
/*  976 */     if (compress) {
/*  977 */       out.setLevel(8);
/*      */     } else {
/*  979 */       out.setLevel(0);
/*      */     } 
/*  981 */     CRC32 crc = new CRC32();
/*  982 */     byte[] buffer = new byte[1048576];
/*      */     
/*  984 */     int sourceDirLength = sourceDir.getAbsolutePath().length() + 1;
/*  985 */     for (int i = 0; i < files.length; i++) {
/*      */       
/*  987 */       File file = files[i];
/*  988 */       addFile(file, out, crc, sourceDirLength, buffer, excluded);
/*      */     } 
/*  990 */     out.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void addFile(File file, ZipOutputStream out, CRC32 crc, int sourceDirLength, byte[] buffer, String[] excluded) throws FileNotFoundException, IOException {
/* 1008 */     if (file.isDirectory()) {
/*      */       
/* 1010 */       File[] fileNames = file.listFiles();
/* 1011 */       for (int i = 0; i < fileNames.length; i++) {
/* 1012 */         addFile(fileNames[i], out, crc, sourceDirLength, buffer, excluded);
/*      */       }
/*      */     } else {
/*      */       
/* 1016 */       if (excluded != null) {
/* 1017 */         byte b; int i; String[] arrayOfString; for (i = (arrayOfString = excluded).length, b = 0; b < i; ) { String exc = arrayOfString[b];
/* 1018 */           if (exc.equals(file.getName()))
/*      */             return;  b++; } 
/* 1020 */       }  String entryName = file.getAbsolutePath().substring(sourceDirLength);
/* 1021 */       if (IS_WINDOWS)
/* 1022 */         entryName = JLbsStringUtil.replace(entryName, '\\', '/'); 
/* 1023 */       ZipEntry entry = new ZipEntry(entryName);
/*      */       
/* 1025 */       FileInputStream in = new FileInputStream(file);
/* 1026 */       add(entry, in, out, crc, buffer);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String replaceTurkishCharacters(String s, boolean replaceSeparator) {
/* 1032 */     s = s.replace(' ', '_');
/* 1033 */     if (replaceSeparator)
/* 1034 */       s = s.replace('/', '-'); 
/* 1035 */     s = s.replace('ş', 's');
/* 1036 */     s = s.replace('Ş', 'S');
/* 1037 */     s = s.replace('ı', 'i');
/* 1038 */     s = s.replace('İ', 'I');
/* 1039 */     s = s.replace('ç', 'c');
/* 1040 */     s = s.replace('Ç', 'C');
/* 1041 */     s = s.replace('ğ', 'g');
/* 1042 */     s = s.replace('Ğ', 'G');
/* 1043 */     s = s.replace('ö', 'o');
/* 1044 */     s = s.replace('Ö', 'O');
/* 1045 */     s = s.replace('ü', 'u');
/* 1046 */     s = s.replace('Ü', 'U');
/* 1047 */     s = s.replace('ı', 'i');
/* 1048 */     s = s.replace('İ', 'I');
/* 1049 */     s = s.replace('ş', 's');
/* 1050 */     s = s.replace('Ş', 'S');
/* 1051 */     s = s.replace('ğ', 'g');
/* 1052 */     s = s.replace('Ğ', 'G');
/* 1053 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void add(ZipEntry entry, InputStream in, ZipOutputStream out, CRC32 crc, byte[] buffer) throws IOException {
/* 1066 */     out.putNextEntry(entry);
/*      */     
/* 1068 */     long size = 0L; int read;
/* 1069 */     while ((read = in.read(buffer)) != -1) {
/*      */       
/* 1071 */       crc.update(buffer, 0, read);
/* 1072 */       out.write(buffer, 0, read);
/* 1073 */       size += read;
/*      */     } 
/* 1075 */     entry.setCrc(crc.getValue());
/* 1076 */     entry.setSize(size);
/* 1077 */     in.close();
/* 1078 */     out.closeEntry();
/* 1079 */     crc.reset();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void unzip(File zipFile, File targetDir) throws FileNotFoundException, IOException {
/* 1084 */     if (!targetDir.exists()) {
/* 1085 */       targetDir.mkdirs();
/*      */     }
/* 1087 */     String targetPath = String.valueOf(targetDir.getAbsolutePath()) + File.separatorChar;
/* 1088 */     byte[] buffer = new byte[1048576];
/* 1089 */     ZipFile input = null;
/*      */     
/*      */     try {
/* 1092 */       input = new ZipFile(zipFile, "UTF-8");
/* 1093 */       Enumeration<ZipArchiveEntry> enumeration = input.getEntries();
/* 1094 */       while (enumeration.hasMoreElements()) {
/*      */         
/* 1096 */         ZipArchiveEntry entry = enumeration.nextElement();
/* 1097 */         if (!entry.isDirectory())
/*      */         {
/*      */           
/* 1100 */           if (entry.getName().indexOf("package cache") == -1)
/*      */           {
/* 1102 */             String path = String.valueOf(targetPath) + entry.getName();
/* 1103 */             File file = new File(path);
/* 1104 */             if (!file.getParentFile().exists())
/*      */             {
/* 1106 */               file.getParentFile().mkdirs();
/*      */             }
/* 1108 */             FileOutputStream out = new FileOutputStream(file);
/* 1109 */             InputStream in = input.getInputStream(entry);
/*      */             int read;
/* 1111 */             while ((read = in.read(buffer)) != -1)
/*      */             {
/* 1113 */               out.write(buffer, 0, read);
/*      */             }
/* 1115 */             in.close();
/* 1116 */             out.close();
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/* 1123 */       if (input != null) {
/* 1124 */         input.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setRootDirectory(String rootPath) {
/* 1134 */     ms_RootDir = rootPath;
/*      */     
/*      */     try {
/* 1137 */       File dir = new File(ms_RootDir);
/* 1138 */       if (!dir.exists()) {
/* 1139 */         makeDirectory(ms_RootDir);
/*      */       }
/* 1141 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFileAbsolutePath(String basePath, String fileName) {
/* 1148 */     String result = fileName;
/* 1149 */     if (JLbsStringUtil.isEmpty(basePath))
/* 1150 */       return result; 
/* 1151 */     File path = new File(basePath);
/* 1152 */     File resFile = new File(path, fileName);
/*      */     
/*      */     try {
/* 1155 */       result = resFile.getCanonicalPath();
/*      */     }
/* 1157 */     catch (IOException e) {
/*      */       
/* 1159 */       System.err.println(e.getMessage());
/*      */     } 
/* 1161 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areFilesEqual(File first, File second) {
/* 1166 */     boolean retval = false;
/*      */ 
/*      */     
/*      */     try {
/* 1170 */       if (first.exists() && second.exists() && first.isFile() && second.isFile())
/*      */       {
/* 1172 */         if (first.getCanonicalPath().equals(second.getCanonicalPath())) {
/*      */           
/* 1174 */           retval = true;
/*      */         }
/*      */         else {
/*      */           
/* 1178 */           FileInputStream firstInput = null;
/* 1179 */           FileInputStream secondInput = null;
/* 1180 */           BufferedInputStream bufFirstInput = null;
/* 1181 */           BufferedInputStream bufSecondInput = null;
/*      */ 
/*      */           
/*      */           try {
/* 1185 */             firstInput = new FileInputStream(first);
/* 1186 */             secondInput = new FileInputStream(second);
/* 1187 */             bufFirstInput = new BufferedInputStream(firstInput);
/* 1188 */             bufSecondInput = new BufferedInputStream(secondInput);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             while (true) {
/* 1195 */               int firstByte = bufFirstInput.read();
/* 1196 */               int secondByte = bufSecondInput.read();
/* 1197 */               if (firstByte != secondByte) {
/*      */                 break;
/*      */               }
/*      */               
/* 1201 */               if (firstByte < 0 && secondByte < 0) {
/*      */                 
/* 1203 */                 retval = true;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } finally {
/*      */             try {
/* 1213 */               if (firstInput != null)
/*      */               {
/* 1215 */                 firstInput.close();
/*      */               }
/*      */             }
/*      */             finally {
/*      */               
/* 1220 */               if (secondInput != null)
/*      */               {
/* 1222 */                 secondInput.close();
/*      */               }
/*      */             } 
/*      */             
/*      */             try {
/* 1227 */               if (bufFirstInput != null)
/*      */               {
/* 1229 */                 bufFirstInput.close();
/*      */               }
/*      */             }
/*      */             finally {
/*      */               
/* 1234 */               if (bufSecondInput != null)
/*      */               {
/* 1236 */                 bufSecondInput.close();
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       }
/* 1243 */     } catch (Exception e) {
/*      */       
/* 1245 */       e.printStackTrace();
/* 1246 */       return false;
/*      */     } 
/*      */     
/* 1249 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String rtfToHtml(Reader rtf) throws IOException {
/* 1254 */     RTFEditorKit kitRtf = new RTFEditorKit();
/* 1255 */     Document document = kitRtf.createDefaultDocument();
/*      */     
/*      */     try {
/* 1258 */       kitRtf.read(rtf, document, 0);
/* 1259 */       kitRtf = null;
/* 1260 */       EditorKit kitHtml = new HTMLEditorKit();
/* 1261 */       Writer writer = new StringWriter();
/* 1262 */       kitHtml.write(writer, document, 0, document.getLength());
/* 1263 */       return writer.toString();
/*      */     }
/* 1265 */     catch (BadLocationException e) {
/*      */       
/* 1267 */       e.printStackTrace();
/*      */       
/* 1269 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static byte[] htmlToRtf(Reader html) throws IOException {
/* 1274 */     EditorKit kitHtml = new HTMLEditorKit();
/* 1275 */     Document document = kitHtml.createDefaultDocument();
/*      */     
/*      */     try {
/* 1278 */       kitHtml.read(html, document, 0);
/* 1279 */       kitHtml = null;
/* 1280 */       RTFEditorKit kitRtf = new RTFEditorKit();
/* 1281 */       ByteArrayOutputStream writer = new ByteArrayOutputStream();
/* 1282 */       kitRtf.write(writer, document, 0, document.getLength());
/* 1283 */       return writer.toByteArray();
/*      */     }
/* 1285 */     catch (BadLocationException e) {
/*      */       
/* 1287 */       e.printStackTrace();
/*      */       
/* 1289 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static byte[] downloadFile(String urlPath) throws IOException {
/* 1294 */     URL url = new URL(urlPath);
/* 1295 */     URLConnection conn = url.openConnection();
/* 1296 */     if (conn instanceof HttpURLConnection) {
/*      */       
/* 1298 */       HttpURLConnection http = (HttpURLConnection)conn;
/* 1299 */       http.setRequestMethod("GET");
/* 1300 */       http.setUseCaches(true);
/* 1301 */       http.setAllowUserInteraction(false);
/* 1302 */       HttpURLConnection.setFollowRedirects(true);
/* 1303 */       http.setDoOutput(false);
/* 1304 */       http.setDoInput(true);
/* 1305 */       http.connect();
/* 1306 */       InputStream inStream = http.getInputStream();
/* 1307 */       int respCode = http.getResponseCode();
/* 1308 */       if (respCode >= 200 && respCode < 300)
/*      */       {
/* 1310 */         return readFile(inStream);
/*      */       }
/*      */     } 
/* 1313 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String toDisplayPath(String pathname) {
/* 1318 */     ILbsPathConverter converter = (ILbsPathConverter)LbsObjectFactory.createObject(ILbsPathConverter.class, new Object[0]);
/* 1319 */     if (converter == null) {
/* 1320 */       return pathname;
/*      */     }
/* 1322 */     return converter.toDisplayPath(pathname);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String fromDisplayPath(String pathname) {
/* 1327 */     ILbsPathConverter converter = (ILbsPathConverter)LbsObjectFactory.createObject(ILbsPathConverter.class, new Object[0]);
/* 1328 */     if (converter == null) {
/* 1329 */       return pathname;
/*      */     }
/* 1331 */     return converter.fromDisplayPath(pathname);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsFileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */