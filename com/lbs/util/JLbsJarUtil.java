/*      */ package com.lbs.util;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.jar.Attributes;
/*      */ import java.util.jar.JarEntry;
/*      */ import java.util.jar.JarFile;
/*      */ import java.util.jar.JarInputStream;
/*      */ import java.util.jar.JarOutputStream;
/*      */ import java.util.jar.Manifest;
/*      */ import java.util.zip.CRC32;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipInputStream;
/*      */ 
/*      */ public class JLbsJarUtil {
/*   30 */   private static FileOutputStream fos = null;
/*   31 */   private static JarOutputStream jos = null;
/*   32 */   private static int iBaseFolderLength = 0;
/*      */   private static final String LINUX_OS = "Linux";
/*      */   private static final String WINDOWS_OS = "Windows";
/*   35 */   private static final boolean IS_WINDOWS = (File.separatorChar == '\\');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void checkDirectory(String directoryName, ILbsExportStatusMonitor monitor) {
/*   44 */     File dirobject = new File(directoryName);
/*   45 */     if (dirobject.exists()) {
/*      */       
/*   47 */       if (dirobject.isDirectory()) {
/*      */         
/*   49 */         File[] fileList = dirobject.listFiles();
/*      */         
/*   51 */         for (int i = 0; i < fileList.length; i++)
/*      */         {
/*   53 */           if (fileList[i].isDirectory())
/*      */           {
/*   55 */             jarDir(fileList[i].getPath(), monitor);
/*   56 */             checkDirectory(fileList[i].getPath(), monitor);
/*      */           }
/*   58 */           else if (fileList[i].isFile())
/*      */           {
/*      */             
/*   61 */             jarFile(fileList[i].getPath(), monitor);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*   67 */       else if (monitor != null) {
/*   68 */         monitor.updateStatus(String.valueOf(directoryName) + " is not a directory.", 1, 
/*   69 */             3);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*   74 */     else if (monitor != null) {
/*   75 */       monitor.updateStatus("Directory " + directoryName + " does not exist.", 1, 
/*   76 */           3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createJarFileForDirectories(String mainDirectory, ArrayList subDirectories, String outputPath, boolean includeAll, String manifestVersion, String mainClass, String classPath, ILbsExportStatusMonitor monitor) {
/*   84 */     createJarFileForDirectories(mainDirectory, subDirectories, outputPath, includeAll, manifestVersion, mainClass, classPath, 
/*   85 */         monitor, true, false);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void createJarFileForDirectories(String mainDirectory, ArrayList subDirectories, String outputPath, boolean includeAll, String manifestVersion, String mainClass, String classPath, ILbsExportStatusMonitor monitor, boolean includeMainDirFiles, boolean deleteExistingJarFile) {
/*      */     try {
/*  107 */       File jarFile = new File(outputPath);
/*      */       
/*  109 */       if (deleteExistingJarFile && jarFile.exists()) {
/*  110 */         jarFile.delete();
/*      */       }
/*  112 */       if (!jarFile.exists()) {
/*  113 */         jarFile.createNewFile();
/*      */       }
/*  115 */       if (jarFile.exists())
/*      */       {
/*  117 */         String strBaseFolder = mainDirectory;
/*      */         
/*  119 */         if (mainDirectory.charAt(mainDirectory.length() - 1) != '\\') {
/*  120 */           strBaseFolder = String.valueOf(strBaseFolder) + File.separator;
/*      */         }
/*  122 */         iBaseFolderLength = strBaseFolder.length();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  130 */         fos = new FileOutputStream(outputPath);
/*  131 */         Manifest manifest = new Manifest();
/*  132 */         Attributes manifestAttr = manifest.getMainAttributes();
/*      */         
/*  134 */         if (manifestVersion != null) {
/*      */           
/*  136 */           manifestAttr.putValue("Manifest-Version", manifestVersion);
/*  137 */           if (mainClass != null)
/*      */           {
/*  139 */             manifestAttr.putValue("Main-Class", mainClass);
/*      */           }
/*  141 */           if (classPath != null)
/*      */           {
/*  143 */             manifestAttr.putValue("Class-Path", classPath);
/*      */           }
/*      */         } 
/*  146 */         Set<Map.Entry<Object, Object>> entries = manifestAttr.entrySet();
/*      */         
/*  148 */         if (monitor != null)
/*      */         {
/*  150 */           for (Iterator<Map.Entry<Object, Object>> i = entries.iterator(); i.hasNext();)
/*      */           {
/*  152 */             monitor.updateStatus("Manifest attribute:>> " + i.next().toString(), 5, 
/*  153 */                 1);
/*      */           }
/*      */         }
/*      */         
/*  157 */         jos = new JarOutputStream(fos, manifest);
/*      */         
/*  159 */         if (monitor != null) {
/*  160 */           monitor.updateStatus(strBaseFolder, 5, 
/*  161 */               1);
/*      */         }
/*  163 */         if (includeMainDirFiles)
/*  164 */           createFilesForJarFile(mainDirectory, subDirectories, includeAll, jarFile, monitor); 
/*  165 */         createDirectoriesForJarFile(mainDirectory, subDirectories, includeAll, jarFile, monitor);
/*      */         
/*  167 */         jos.flush();
/*  168 */         jos.close();
/*  169 */         fos.close();
/*      */       }
/*      */     
/*      */     }
/*  173 */     catch (IOException ioe) {
/*      */       
/*  175 */       if (monitor != null) {
/*  176 */         monitor.updateStatus("IOException in method createJarFileForDirectories", ioe, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void createFilesForJarFile(String directoryName, ArrayList subDirectories, boolean includeAll, File jarFile, ILbsExportStatusMonitor monitor) {
/*  184 */     File dirobject = new File(directoryName);
/*  185 */     if (dirobject.exists()) {
/*      */       
/*  187 */       if (dirobject.isDirectory()) {
/*      */         
/*  189 */         File[] fileList = dirobject.listFiles();
/*      */         
/*  191 */         for (int i = 0; i < fileList.length; i++)
/*      */         {
/*  193 */           if (!JLbsStringUtil.equals(fileList[i].getAbsolutePath(), jarFile.getAbsolutePath()))
/*      */           {
/*  195 */             if (fileList[i].isFile())
/*      */             {
/*      */               
/*  198 */               jarFile(fileList[i].getPath(), monitor);
/*      */             
/*      */             }
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*  205 */       else if (monitor != null) {
/*  206 */         monitor.updateStatus(String.valueOf(directoryName) + " is not a directory.", 1, 
/*  207 */             3);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  212 */     else if (monitor != null) {
/*  213 */       monitor.updateStatus("Directory " + directoryName + " does not exist.", 1, 
/*  214 */           3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void createDirectoriesForJarFile(String directoryName, ArrayList subDirectories, boolean includeAll, File jarFile, ILbsExportStatusMonitor monitor) {
/*  222 */     File dirobject = new File(directoryName);
/*  223 */     if (dirobject.exists()) {
/*      */       
/*  225 */       if (dirobject.isDirectory()) {
/*      */         
/*  227 */         File[] fileList = dirobject.listFiles();
/*      */         
/*  229 */         for (int i = 0; i < fileList.length; i++)
/*      */         {
/*  231 */           if (!JLbsStringUtil.equals(fileList[i].getAbsolutePath(), jarFile.getAbsolutePath()))
/*      */           {
/*  233 */             if (fileList[i].isDirectory())
/*      */             {
/*  235 */               if (includeAll || subDirectories.contains(fileList[i].getName()))
/*      */               {
/*  237 */                 jarDir(fileList[i].getPath(), monitor);
/*  238 */                 checkDirectory(fileList[i].getPath(), monitor);
/*      */               }
/*      */             
/*      */             }
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*  246 */       else if (monitor != null) {
/*  247 */         monitor.updateStatus(String.valueOf(directoryName) + " is not a directory.", 1, 
/*  248 */             3);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  253 */     else if (monitor != null) {
/*  254 */       monitor.updateStatus("Directory " + directoryName + " does not exist.", 1, 
/*  255 */           3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void jarFile(String filePath, ILbsExportStatusMonitor monitor) {
/*  262 */     FileInputStream fis = null;
/*  263 */     BufferedInputStream bis = null;
/*      */     
/*      */     try {
/*  266 */       fis = new FileInputStream(filePath);
/*  267 */       bis = new BufferedInputStream(fis);
/*      */       
/*  269 */       String fileNameEntry = filePath.substring(iBaseFolderLength).replace(File.separatorChar, '/');
/*  270 */       JarEntry fileEntry = new JarEntry(fileNameEntry);
/*  271 */       jos.putNextEntry(fileEntry);
/*  272 */       byte[] data = new byte[1024];
/*      */       int byteCount;
/*  274 */       while ((byteCount = bis.read(data, 0, 1024)) > -1)
/*      */       {
/*  276 */         jos.write(data, 0, byteCount);
/*      */       }
/*      */       
/*  279 */       if (monitor != null) {
/*  280 */         monitor.updateStatus("File compressed: " + fileNameEntry, 5, 
/*  281 */             1);
/*      */       }
/*  283 */     } catch (IOException e) {
/*      */       
/*  285 */       if (monitor != null) {
/*  286 */         monitor.updateStatus("jarFile : ", e, 1);
/*      */       }
/*      */     } finally {
/*      */       
/*  290 */       if (fis != null) {
/*      */ 
/*      */         
/*      */         try {
/*  294 */           fis.close();
/*      */         }
/*  296 */         catch (IOException iOException) {}
/*      */ 
/*      */         
/*  299 */         fis = null;
/*      */       } 
/*      */       
/*  302 */       if (bis != null) {
/*      */ 
/*      */         
/*      */         try {
/*  306 */           bis.close();
/*      */         }
/*  308 */         catch (IOException iOException) {}
/*      */ 
/*      */         
/*  311 */         bis = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void jarDir(String filePath, ILbsExportStatusMonitor monitor) {
/*      */     try {
/*  321 */       String fileDirEntry = String.valueOf(filePath.substring(iBaseFolderLength).replace(File.separatorChar, '/')) + "/";
/*  322 */       JarEntry fileEntry = new JarEntry(fileDirEntry);
/*  323 */       jos.putNextEntry(fileEntry);
/*  324 */       if (monitor != null) {
/*  325 */         monitor.updateStatus("Directory compressed: " + fileDirEntry, 5, 
/*  326 */             1);
/*      */       }
/*  328 */     } catch (IOException e) {
/*      */       
/*  330 */       if (monitor != null) {
/*  331 */         monitor.updateStatus("jarDir : ", e, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void extractJarForSpring(String jarFilePath, String destDirPath) {
/*  338 */     ByteArrayOutputStream stream = null;
/*  339 */     ZipInputStream zip = null;
/*  340 */     FileOutputStream stream1 = null;
/*  341 */     FileInputStream fis = null;
/*      */     
/*      */     try {
/*  344 */       fis = new FileInputStream(jarFilePath);
/*  345 */       zip = new ZipInputStream(fis);
/*  346 */       ZipEntry entry = zip.getNextEntry();
/*      */       
/*  348 */       while (entry != null)
/*      */       {
/*  350 */         if (entry.getName().contains("/static/") || entry.getName().contains("BOOT-INF/lib")) {
/*      */           
/*  352 */           String name = entry.getName();
/*  353 */           if (entry.getName().contains("/static/")) {
/*      */             
/*  355 */             int staticIndex = name.indexOf("/static/");
/*  356 */             if (name.length() > staticIndex + 8) {
/*      */               
/*  358 */               name = name.substring(staticIndex + 8);
/*      */             }
/*      */             else {
/*      */               
/*  362 */               entry = zip.getNextEntry();
/*      */               continue;
/*      */             } 
/*      */           } 
/*  366 */           long size = entry.getSize();
/*      */           
/*  368 */           stream = (size < 0L) ? 
/*  369 */             new ByteArrayOutputStream() : 
/*  370 */             new ByteArrayOutputStream((int)size);
/*      */           
/*  372 */           byte[] buff = new byte[8192]; int read;
/*  373 */           while ((read = zip.read(buff, 0, buff.length)) > 0) {
/*  374 */             stream.write(buff, 0, read);
/*      */           }
/*  376 */           buff = null;
/*      */           
/*  378 */           File file = new File(destDirPath, name);
/*      */           
/*  380 */           if (entry.isDirectory()) {
/*      */             
/*  382 */             file.mkdir();
/*  383 */             entry = zip.getNextEntry();
/*      */             continue;
/*      */           } 
/*  386 */           file.getParentFile().mkdirs();
/*      */           
/*  388 */           stream1 = new FileOutputStream(file, false);
/*  389 */           stream1.write(stream.toByteArray());
/*  390 */           stream1.flush();
/*      */         } 
/*      */         
/*  393 */         entry = zip.getNextEntry();
/*      */       }
/*      */     
/*      */     }
/*  397 */     catch (Exception e) {
/*      */       
/*  399 */       e.printStackTrace();
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  406 */         if (fis != null) {
/*  407 */           fis.close();
/*      */         }
/*  409 */       } catch (IOException ex) {
/*      */         
/*  411 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  415 */         if (stream != null) {
/*  416 */           stream.close();
/*      */         }
/*  418 */       } catch (IOException ex) {
/*      */         
/*  420 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  424 */         if (stream1 != null) {
/*  425 */           stream1.close();
/*      */         }
/*  427 */       } catch (IOException ex) {
/*      */         
/*  429 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  433 */         if (zip != null) {
/*  434 */           zip.close();
/*      */         }
/*  436 */       } catch (IOException iOException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void extractJarFile(String jarFilePath, String destDirPath) {
/*  444 */     ByteArrayOutputStream stream = null;
/*  445 */     ZipInputStream zip = null;
/*  446 */     FileOutputStream stream1 = null;
/*  447 */     FileInputStream fis = null;
/*      */     
/*      */     try {
/*  450 */       fis = new FileInputStream(jarFilePath);
/*  451 */       zip = new ZipInputStream(fis);
/*  452 */       ZipEntry entry = zip.getNextEntry();
/*      */       
/*  454 */       while (entry != null)
/*      */       {
/*  456 */         long size = entry.getSize();
/*      */         
/*  458 */         stream = (size < 0L) ? 
/*  459 */           new ByteArrayOutputStream() : 
/*  460 */           new ByteArrayOutputStream((int)size);
/*      */         
/*  462 */         byte[] buff = new byte[8192]; int read;
/*  463 */         while ((read = zip.read(buff, 0, buff.length)) > 0) {
/*  464 */           stream.write(buff, 0, read);
/*      */         }
/*      */         
/*  467 */         buff = null;
/*      */         
/*  469 */         File file = new File(destDirPath, entry.getName());
/*      */         
/*  471 */         if (entry.isDirectory()) {
/*      */           
/*  473 */           file.mkdir();
/*  474 */           entry = zip.getNextEntry();
/*      */           continue;
/*      */         } 
/*  477 */         file.getParentFile().mkdirs();
/*      */         
/*  479 */         stream1 = new FileOutputStream(file, false);
/*  480 */         stream1.write(stream.toByteArray());
/*  481 */         stream1.flush();
/*      */ 
/*      */         
/*  484 */         entry = zip.getNextEntry();
/*      */       }
/*      */     
/*      */     }
/*  488 */     catch (Exception e) {
/*      */       
/*  490 */       e.printStackTrace();
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  497 */         if (fis != null) {
/*  498 */           fis.close();
/*      */         }
/*  500 */       } catch (IOException ex) {
/*      */         
/*  502 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  506 */         if (stream != null) {
/*  507 */           stream.close();
/*      */         }
/*  509 */       } catch (IOException ex) {
/*      */         
/*  511 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  515 */         if (stream1 != null) {
/*  516 */           stream1.close();
/*      */         }
/*  518 */       } catch (IOException ex) {
/*      */         
/*  520 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  524 */         if (zip != null) {
/*  525 */           zip.close();
/*      */         }
/*  527 */       } catch (IOException iOException) {}
/*      */     } 
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
/*      */   public static void jar(File sourceDir, File target, boolean compress) throws FileNotFoundException, IOException {
/*  544 */     jar(sourceDir.listFiles(), sourceDir, target, compress);
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
/*      */   public static void jar(File[] files, File sourceDir, File target, boolean compress) throws FileNotFoundException, IOException {
/*  560 */     if (!target.getParentFile().exists())
/*      */     {
/*  562 */       target.getParentFile().mkdirs();
/*      */     }
/*      */     
/*  565 */     JarOutputStream out = new JarOutputStream(new FileOutputStream(target));
/*  566 */     if (compress) {
/*      */       
/*  568 */       out.setLevel(8);
/*      */     }
/*      */     else {
/*      */       
/*  572 */       out.setLevel(0);
/*      */     } 
/*      */     
/*  575 */     CRC32 crc = new CRC32();
/*  576 */     byte[] buffer = new byte[1048576];
/*      */     
/*  578 */     int sourceDirLength = sourceDir.getAbsolutePath().length() + 1;
/*  579 */     for (int i = 0; i < files.length; i++) {
/*      */       
/*  581 */       File file = files[i];
/*  582 */       addFile(file, out, crc, sourceDirLength, buffer);
/*      */     } 
/*  584 */     out.close();
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
/*      */   private static void addFile(File file, JarOutputStream out, CRC32 crc, int sourceDirLength, byte[] buffer) throws FileNotFoundException, IOException {
/*  602 */     if (file.isDirectory()) {
/*      */       
/*  604 */       File[] fileNames = file.listFiles();
/*  605 */       for (int i = 0; i < fileNames.length; i++)
/*      */       {
/*  607 */         addFile(fileNames[i], out, crc, sourceDirLength, buffer);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  612 */       String entryName = file.getAbsolutePath().substring(sourceDirLength);
/*  613 */       if (IS_WINDOWS)
/*  614 */         entryName = JLbsStringUtil.replace(entryName, '\\', '/'); 
/*  615 */       JarEntry entry = new JarEntry(entryName);
/*      */       
/*  617 */       FileInputStream in = new FileInputStream(file);
/*  618 */       add(entry, in, out, crc, buffer);
/*      */     } 
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
/*      */   private static void add(JarEntry entry, InputStream in, JarOutputStream out, CRC32 crc, byte[] buffer) throws IOException {
/*  632 */     out.putNextEntry(entry);
/*      */     
/*  634 */     long size = 0L; int read;
/*  635 */     while ((read = in.read(buffer)) != -1) {
/*      */       
/*  637 */       crc.update(buffer, 0, read);
/*  638 */       out.write(buffer, 0, read);
/*  639 */       size += read;
/*      */     } 
/*  641 */     entry.setCrc(crc.getValue());
/*  642 */     entry.setSize(size);
/*  643 */     in.close();
/*  644 */     out.closeEntry();
/*  645 */     crc.reset();
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
/*      */   public static void addToJar(File file, File jarFile, File parentDir, boolean compress) throws FileNotFoundException, IOException {
/*      */     int sourceDirLength;
/*  662 */     File tmpJarFile = File.createTempFile("tmp", ".jar", jarFile.getParentFile());
/*  663 */     JarOutputStream out = new JarOutputStream(new FileOutputStream(tmpJarFile));
/*  664 */     if (compress) {
/*      */       
/*  666 */       out.setLevel(8);
/*      */     }
/*      */     else {
/*      */       
/*  670 */       out.setLevel(0);
/*      */     } 
/*      */     
/*  673 */     JarFile inputFile = new JarFile(jarFile);
/*  674 */     JarInputStream in = new JarInputStream(new FileInputStream(jarFile));
/*  675 */     CRC32 crc = new CRC32();
/*  676 */     byte[] buffer = new byte[524288];
/*  677 */     JarEntry entry = (JarEntry)in.getNextEntry();
/*  678 */     while (entry != null) {
/*      */       
/*  680 */       InputStream entryIn = inputFile.getInputStream(entry);
/*  681 */       add(entry, entryIn, out, crc, buffer);
/*  682 */       entryIn.close();
/*  683 */       entry = (JarEntry)in.getNextEntry();
/*      */     } 
/*  685 */     in.close();
/*  686 */     inputFile.close();
/*      */ 
/*      */     
/*  689 */     if (parentDir == null) {
/*      */       
/*  691 */       sourceDirLength = file.getAbsolutePath().lastIndexOf(File.separatorChar) + 1;
/*      */     }
/*      */     else {
/*      */       
/*  695 */       sourceDirLength = file.getAbsolutePath().lastIndexOf(File.separatorChar) + 1 - parentDir.getAbsolutePath().length();
/*      */     } 
/*  697 */     addFile(file, out, crc, sourceDirLength, buffer);
/*  698 */     out.close();
/*      */ 
/*      */     
/*  701 */     if (jarFile.delete()) {
/*      */       
/*  703 */       if (!tmpJarFile.renameTo(jarFile))
/*      */       {
/*  705 */         throw new IOException("Unable to rename temporary JAR file to [" + jarFile.getAbsolutePath() + "].");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  710 */       throw new IOException("Unable to delete old JAR file [" + jarFile.getAbsolutePath() + "].");
/*      */     } 
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
/*      */   public static void unjar(File jarFile, File targetDir) throws FileNotFoundException, IOException {
/*  726 */     unjar(jarFile, targetDir, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unjar(File jarFile, File targetDir, boolean clearTargetDir) throws FileNotFoundException, IOException {
/*  732 */     if (clearTargetDir && targetDir.exists())
/*      */     {
/*  734 */       targetDir.delete();
/*      */     }
/*      */     
/*  737 */     targetDir.mkdirs();
/*      */     
/*  739 */     String targetPath = String.valueOf(targetDir.getAbsolutePath()) + File.separatorChar;
/*  740 */     byte[] buffer = new byte[1048576];
/*  741 */     JarFile input = null;
/*      */     
/*      */     try {
/*  744 */       input = new JarFile(jarFile, false, 1);
/*  745 */       Enumeration<JarEntry> enumeration = input.entries();
/*  746 */       while (enumeration.hasMoreElements()) {
/*      */         
/*  748 */         JarEntry entry = enumeration.nextElement();
/*  749 */         if (!entry.isDirectory())
/*      */         {
/*      */           
/*  752 */           if (entry.getName().indexOf("package cache") == -1) {
/*      */             
/*  754 */             String path = String.valueOf(targetPath) + entry.getName();
/*  755 */             File file = new File(path);
/*  756 */             if (!file.getParentFile().exists())
/*      */             {
/*  758 */               file.getParentFile().mkdirs();
/*      */             }
/*  760 */             FileOutputStream out = null;
/*  761 */             InputStream in = null;
/*      */             
/*      */             try {
/*  764 */               out = new FileOutputStream(file);
/*  765 */               in = input.getInputStream(entry);
/*      */               int read;
/*  767 */               while ((read = in.read(buffer)) != -1)
/*      */               {
/*  769 */                 out.write(buffer, 0, read);
/*      */               }
/*      */             } finally {
/*      */ 
/*      */               
/*      */               try {
/*      */ 
/*      */ 
/*      */                 
/*  778 */                 if (in != null) {
/*  779 */                   in.close();
/*      */                 }
/*  781 */               } catch (IOException ex) {
/*      */                 
/*  783 */                 ex.printStackTrace();
/*      */               } 
/*      */               
/*      */               try {
/*  787 */                 if (out != null) {
/*  788 */                   out.close();
/*      */                 }
/*  790 */               } catch (IOException ex) {
/*      */                 
/*  792 */                 ex.printStackTrace();
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  801 */       if (input != null) {
/*      */         
/*      */         try {
/*      */           
/*  805 */           input.close();
/*      */         }
/*  807 */         catch (Exception exception) {}
/*      */       }
/*      */     } 
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
/*      */   public static void unjar(File jarFile, String resource, File targetDir) throws FileNotFoundException, IOException {
/*  827 */     if (targetDir.exists())
/*      */     {
/*  829 */       targetDir.delete();
/*      */     }
/*      */     
/*  832 */     targetDir.mkdirs();
/*      */     
/*  834 */     String targetPath = String.valueOf(targetDir.getAbsolutePath()) + File.separatorChar;
/*  835 */     byte[] buffer = new byte[1048576];
/*  836 */     JarFile input = null;
/*      */     
/*      */     try {
/*  839 */       input = new JarFile(jarFile, false, 1);
/*  840 */       Enumeration<JarEntry> enumeration = input.entries();
/*  841 */       while (enumeration.hasMoreElements()) {
/*      */         
/*  843 */         JarEntry entry = enumeration.nextElement();
/*  844 */         if (!entry.isDirectory())
/*      */         {
/*      */           
/*  847 */           if (entry.getName().equals(resource)) {
/*      */             
/*  849 */             String path = String.valueOf(targetPath) + entry.getName();
/*  850 */             File file = new File(path);
/*  851 */             if (!file.getParentFile().exists())
/*      */             {
/*  853 */               file.getParentFile().mkdirs();
/*      */             }
/*  855 */             FileOutputStream out = null;
/*  856 */             InputStream in = null;
/*      */             
/*      */             try {
/*  859 */               out = new FileOutputStream(file);
/*  860 */               in = input.getInputStream(entry);
/*      */               int read;
/*  862 */               while ((read = in.read(buffer)) != -1)
/*      */               {
/*  864 */                 out.write(buffer, 0, read);
/*      */               }
/*      */             } finally {
/*      */ 
/*      */               
/*      */               try {
/*      */ 
/*      */ 
/*      */                 
/*  873 */                 if (in != null) {
/*  874 */                   in.close();
/*      */                 }
/*  876 */               } catch (IOException ex) {
/*      */                 
/*  878 */                 ex.printStackTrace();
/*      */               } 
/*      */               
/*      */               try {
/*  882 */                 if (out != null) {
/*  883 */                   out.close();
/*      */                 }
/*  885 */               } catch (IOException ex) {
/*      */                 
/*  887 */                 ex.printStackTrace();
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  896 */       if (input != null) {
/*      */         
/*      */         try {
/*      */           
/*  900 */           input.close();
/*      */         }
/*  902 */         catch (Exception exception) {}
/*      */       }
/*      */     } 
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
/*      */   public static String[] getPackageNames(File jarFile) throws IOException {
/*  918 */     HashMap<Object, Object> packageNames = new HashMap<>();
/*  919 */     JarFile input = null;
/*      */     
/*      */     try {
/*  922 */       input = new JarFile(jarFile, false, 1);
/*  923 */       Enumeration<JarEntry> enumeration = input.entries();
/*  924 */       while (enumeration.hasMoreElements()) {
/*      */         
/*  926 */         JarEntry entry = enumeration.nextElement();
/*  927 */         String name = entry.getName();
/*  928 */         if (name.endsWith(".class")) {
/*      */           
/*  930 */           int endPos = name.lastIndexOf('/');
/*  931 */           boolean isWindows = false;
/*  932 */           if (endPos == -1) {
/*      */             
/*  934 */             endPos = name.lastIndexOf('\\');
/*  935 */             isWindows = true;
/*      */           } 
/*  937 */           name = name.substring(0, endPos);
/*  938 */           name = name.replace('/', '.');
/*  939 */           if (isWindows)
/*      */           {
/*  941 */             name = name.replace('\\', '.');
/*      */           }
/*  943 */           packageNames.put(name, name);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  951 */         if (input != null) {
/*  952 */           input.close();
/*      */         }
/*  954 */       } catch (Exception exception) {}
/*      */     } 
/*      */ 
/*      */     
/*  958 */     return (String[])packageNames.values().toArray((Object[])new String[packageNames.size()]);
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
/*      */   public static String getMainClassName(File jarFile) throws IOException {
/*  970 */     if (!jarFile.exists())
/*      */     {
/*  972 */       throw new IllegalArgumentException("File [" + jarFile.getAbsolutePath() + "] does not exist.");
/*      */     }
/*  974 */     JarFile input = null;
/*      */ 
/*      */     
/*  977 */     try { input = new JarFile(jarFile, false, 1);
/*  978 */       Manifest manifest = input.getManifest();
/*  979 */       if (manifest == null)
/*      */       {
/*  981 */         return null;
/*      */       }
/*  983 */       Attributes attributes = manifest.getMainAttributes();
/*      */       
/*      */        }
/*      */     
/*      */     finally
/*      */     
/*      */     { 
/*      */       
/*  991 */       if (input != null)
/*      */         
/*      */         try {
/*      */           
/*  995 */           input.close();
/*      */         }
/*  997 */         catch (Exception exception) {}  }  if (input != null) try { input.close(); } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1002 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void exec(File jarFile, ArrayList argsList, ClassLoader classLoader) throws IOException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
/* 1008 */     String[] args = (String[])argsList.toArray((Object[])new String[argsList.size()]);
/* 1009 */     exec(jarFile, args, classLoader);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void exec(File jarFile, String[] args, ClassLoader classLoader) throws IOException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
/* 1015 */     String mainClassName = getMainClassName(jarFile);
/* 1016 */     if (mainClassName == null)
/*      */     {
/* 1018 */       throw new ClassNotFoundException("Unable to extract name of Main-Class of " + jarFile.getAbsolutePath());
/*      */     }
/* 1020 */     Class<?> mainClass = classLoader.loadClass(mainClassName);
/* 1021 */     Method mainMethod = mainClass.getMethod("main", new Class[] { String[].class });
/* 1022 */     mainMethod.invoke(null, new Object[] { args });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unjar(File jarFile, File targetDir, String startWithResource) throws FileNotFoundException, IOException {
/* 1028 */     if (targetDir.exists())
/*      */     {
/* 1030 */       targetDir.delete();
/*      */     }
/*      */     
/* 1033 */     targetDir.mkdirs();
/*      */     
/* 1035 */     String targetPath = String.valueOf(targetDir.getAbsolutePath()) + File.separatorChar;
/* 1036 */     byte[] buffer = new byte[1048576];
/* 1037 */     JarFile input = null;
/*      */     
/*      */     try {
/* 1040 */       input = new JarFile(jarFile, false, 1);
/* 1041 */       Enumeration<JarEntry> enumeration = input.entries();
/* 1042 */       while (enumeration.hasMoreElements())
/*      */       {
/* 1044 */         JarEntry entry = enumeration.nextElement();
/* 1045 */         if (!entry.isDirectory())
/*      */         {
/*      */           
/* 1048 */           if (entry.getName().startsWith(startWithResource))
/*      */           {
/* 1050 */             String path = String.valueOf(targetPath) + entry.getName();
/* 1051 */             File file = new File(path);
/* 1052 */             if (!file.getParentFile().exists())
/*      */             {
/* 1054 */               file.getParentFile().mkdirs();
/*      */             }
/* 1056 */             FileOutputStream out = null;
/* 1057 */             InputStream in = null;
/*      */             
/*      */             try {
/* 1060 */               out = new FileOutputStream(file);
/* 1061 */               in = input.getInputStream(entry);
/*      */               int read;
/* 1063 */               while ((read = in.read(buffer)) != -1)
/*      */               {
/* 1065 */                 out.write(buffer, 0, read);
/*      */               }
/*      */             } finally {
/*      */ 
/*      */               
/*      */               try {
/*      */ 
/*      */                 
/* 1073 */                 if (out != null) {
/* 1074 */                   out.close();
/*      */                 }
/* 1076 */               } catch (IOException ex) {
/*      */                 
/* 1078 */                 ex.printStackTrace();
/*      */               } 
/*      */               
/*      */               try {
/* 1082 */                 if (in != null)
/*      */                 {
/* 1084 */                   in.close();
/*      */                 }
/*      */               }
/* 1087 */               catch (Exception exception) {}
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 1097 */       if (input != null) {
/*      */         
/*      */         try {
/*      */           
/* 1101 */           input.close();
/*      */         }
/* 1103 */         catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1113 */     extractJarFile("D:\\Temp\\ResourceDBs\\ENUS.jar", String.valueOf(JLbsFileUtil.getTempDirectory()) + File.separator + "JarDir");
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsJarUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */