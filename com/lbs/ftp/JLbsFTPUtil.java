/*     */ package com.lbs.ftp;
/*     */ 
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.commons.net.ftp.FTPClient;
/*     */ import org.apache.commons.net.ftp.FTPFile;
/*     */ import org.apache.commons.vfs2.FileObject;
/*     */ import org.apache.commons.vfs2.FileSystemOptions;
/*     */ import org.apache.commons.vfs2.Selectors;
/*     */ import org.apache.commons.vfs2.impl.StandardFileSystemManager;
/*     */ import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
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
/*     */ public class JLbsFTPUtil
/*     */ {
/*     */   private static final String FTP_PREFIX = "ftp://";
/*     */   private static final String SECURE_FTP_PREFIX = "sftp://";
/*     */   private static final int EOS = 65536;
/*     */   private static final char INT_SPACE = ' ';
/*     */   
/*     */   public static boolean downloadFilesFromSecureFTP(String serverAddress, String userId, String password, String remoteDirectory, String localDirectory) throws LbsLocalizableException {
/*  42 */     StandardFileSystemManager manager = new StandardFileSystemManager();
/*     */     
/*     */     try {
/*  45 */       manager.init();
/*     */       
/*  47 */       FileSystemOptions opts = new FileSystemOptions();
/*  48 */       SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
/*  49 */       SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
/*  50 */       SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, Integer.valueOf(10000));
/*     */       
/*  52 */       String sftpUri = "sftp://" + URLEncoder.encode(userId) + ":" + URLEncoder.encode(password) + "@" + serverAddress + "/" + remoteDirectory;
/*     */       
/*  54 */       File file = new File(localDirectory);
/*  55 */       FileObject localFile = manager.resolveFile(file.getAbsolutePath());
/*     */       
/*  57 */       FileObject remoteFile = manager.resolveFile(sftpUri, opts);
/*     */       
/*  59 */       localFile.copyFrom(remoteFile, Selectors.SELECT_CHILDREN);
/*  60 */       System.out.println("File download successful");
/*     */     }
/*  62 */     catch (Exception ex) {
/*     */       
/*  64 */       throwLocalizableException(ex);
/*     */     }
/*     */     finally {
/*     */       
/*  68 */       manager.close();
/*     */     } 
/*     */     
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean uploadFileToSFTP(String serverAddress, String userId, String password, String remoteDirectory, String localDirectory, String localFileToFTP) throws LbsLocalizableException {
/*  77 */     StandardFileSystemManager manager = new StandardFileSystemManager();
/*     */     
/*     */     try {
/*  80 */       String filepath = String.valueOf(localDirectory) + localFileToFTP;
/*  81 */       File file = new File(filepath);
/*  82 */       if (!file.exists()) {
/*  83 */         throw new RuntimeException("Error. Local file not found");
/*     */       }
/*  85 */       manager.init();
/*     */       
/*  87 */       FileSystemOptions opts = new FileSystemOptions();
/*  88 */       SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
/*  89 */       SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
/*  90 */       SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, Integer.valueOf(10000));
/*     */       
/*  92 */       String sftpUri = "sftp://" + userId + ":" + password + "@" + serverAddress + "/" + remoteDirectory + localFileToFTP;
/*     */       
/*  94 */       FileObject localFile = manager.resolveFile(file.getAbsolutePath());
/*     */       
/*  96 */       FileObject remoteFile = manager.resolveFile(sftpUri, opts);
/*     */       
/*  98 */       remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
/*  99 */       System.out.println("File upload successful");
/*     */     
/*     */     }
/* 102 */     catch (Exception ex) {
/*     */       
/* 104 */       throwLocalizableException(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 108 */       manager.close();
/*     */     } 
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean deleteFileFromSFTP(String serverAddress, String userId, String password, String remoteDirectory, String fileToDelete) throws LbsLocalizableException {
/* 117 */     StandardFileSystemManager manager = new StandardFileSystemManager();
/*     */     
/*     */     try {
/* 120 */       manager.init();
/*     */       
/* 122 */       FileSystemOptions opts = new FileSystemOptions();
/* 123 */       SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
/* 124 */       SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
/* 125 */       SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, Integer.valueOf(10000));
/*     */       
/* 127 */       String sftpUri = "sftp://" + URLEncoder.encode(userId) + ":" + URLEncoder.encode(password) + "@" + serverAddress + "/" + remoteDirectory + "/" + fileToDelete;
/*     */       
/* 129 */       FileObject remoteFile = manager.resolveFile(sftpUri, opts);
/*     */       
/* 131 */       if (remoteFile.exists())
/*     */       {
/* 133 */         remoteFile.delete();
/* 134 */         System.out.println("File delete successful");
/*     */       }
/*     */     
/*     */     }
/* 138 */     catch (Exception ex) {
/*     */       
/* 140 */       throwLocalizableException(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 144 */       manager.close();
/*     */     } 
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<String> downloadFilesFromFTP(String server, String userId, String password, String parentDir, String currentDir, String saveDir) throws LbsLocalizableException {
/* 152 */     FTPClient ftpClient = new FTPClient();
/* 153 */     FTPFile[] subFiles = null;
/* 154 */     String dirToList = parentDir;
/*     */     
/*     */     try {
/* 157 */       ftpClient.connect(server);
/* 158 */       ftpClient.login(userId, password);
/* 159 */       ftpClient.enterLocalPassiveMode();
/* 160 */       ftpClient.setFileType(2);
/*     */       
/* 162 */       if (!"".equals(currentDir))
/*     */       {
/* 164 */         dirToList = String.valueOf(dirToList) + "/" + currentDir;
/*     */       }
/*     */       
/* 167 */       subFiles = ftpClient.listFiles(dirToList);
/*     */     }
/* 169 */     catch (Exception e) {
/*     */       
/* 171 */       throwLocalizableException(e);
/*     */     } 
/*     */     
/* 174 */     ArrayList<String> downloadedFileList = new ArrayList<>();
/*     */     
/* 176 */     if (subFiles != null && subFiles.length > 0) {
/*     */       byte b; int i; FTPFile[] arrayOfFTPFile;
/* 178 */       for (i = (arrayOfFTPFile = subFiles).length, b = 0; b < i; ) { FTPFile aFile = arrayOfFTPFile[b];
/*     */         
/* 180 */         String currentFileName = aFile.getName();
/* 181 */         downloadedFileList.add(currentFileName);
/* 182 */         if (!".".equals(currentFileName) && !"..".equals(currentFileName)) {
/*     */ 
/*     */ 
/*     */           
/* 186 */           String filePath = String.valueOf(parentDir) + "/" + currentDir + "/" + currentFileName;
/* 187 */           if ("".equals(currentDir))
/*     */           {
/* 189 */             filePath = String.valueOf(parentDir) + "/" + currentFileName;
/*     */           }
/*     */           
/* 192 */           String newDirPath = String.valueOf(saveDir) + File.separator + currentDir + File.separator + currentFileName;
/* 193 */           if ("".equals(currentDir))
/*     */           {
/* 195 */             newDirPath = String.valueOf(saveDir) + File.separator + currentFileName;
/*     */           }
/*     */           
/* 198 */           if (aFile.isDirectory()) {
/*     */ 
/*     */             
/* 201 */             File newDir = new File(newDirPath);
/* 202 */             boolean created = newDir.mkdirs();
/* 203 */             if (created) {
/*     */               
/* 205 */               System.out.println("CREATED the directory: " + newDirPath);
/*     */             }
/*     */             else {
/*     */               
/* 209 */               System.out.println("COULD NOT create the directory: " + newDirPath);
/*     */             } 
/*     */             
/* 212 */             downloadFilesFromFTP(server, userId, password, dirToList, currentFileName, saveDir);
/*     */           }
/*     */           else {
/*     */             
/* 216 */             boolean success = downloadSingleFile(ftpClient, filePath, newDirPath);
/* 217 */             if (success) {
/*     */               
/* 219 */               System.out.println("DOWNLOADED the file: " + filePath);
/*     */             }
/*     */             else {
/*     */               
/* 223 */               System.out.println("COULD NOT download the file: " + filePath);
/*     */             } 
/*     */           } 
/*     */         }  b++; }
/*     */     
/* 228 */     }  return downloadedFileList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, String savePath) throws LbsLocalizableException {
/* 234 */     File downloadFile = new File(savePath);
/*     */     
/* 236 */     File parentDir = downloadFile.getParentFile();
/* 237 */     if (!parentDir.exists())
/*     */     {
/* 239 */       parentDir.mkdir();
/*     */     }
/*     */     
/* 242 */     OutputStream outputStream = null;
/* 243 */     FileOutputStream fileOutputStream = null;
/*     */     
/*     */     try {
/* 246 */       fileOutputStream = new FileOutputStream(downloadFile);
/* 247 */       outputStream = new BufferedOutputStream(fileOutputStream);
/* 248 */       ftpClient.setFileType(2);
/* 249 */       return ftpClient.retrieveFile(remoteFilePath, outputStream);
/*     */     }
/* 251 */     catch (IOException ex) {
/*     */       
/* 253 */       throwLocalizableException(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 257 */       if (outputStream != null) {
/*     */         
/*     */         try {
/*     */           
/* 261 */           outputStream.close();
/*     */         }
/* 263 */         catch (IOException e) {
/*     */           
/* 265 */           throwLocalizableException(e);
/*     */         } 
/*     */       }
/* 268 */       if (fileOutputStream != null) {
/*     */         
/*     */         try {
/*     */           
/* 272 */           fileOutputStream.close();
/*     */         }
/* 274 */         catch (IOException e) {
/*     */           
/* 276 */           throwLocalizableException(e);
/*     */         } 
/*     */       }
/*     */     } 
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uploadFilesToFTP(FTPClient ftpClient, String remoteDirPath, String localParentDir, String remoteParentDir) throws IOException {
/* 286 */     File localDir = new File(localParentDir);
/* 287 */     File[] subFiles = localDir.listFiles();
/* 288 */     if (subFiles != null && subFiles.length > 0) {
/*     */       byte b; int i; File[] arrayOfFile;
/* 290 */       for (i = (arrayOfFile = subFiles).length, b = 0; b < i; ) { File item = arrayOfFile[b];
/*     */         
/* 292 */         String remoteFilePath = String.valueOf(remoteDirPath) + "/" + remoteParentDir + "/" + item.getName();
/* 293 */         if ("".equals(remoteParentDir))
/*     */         {
/* 295 */           remoteFilePath = String.valueOf(remoteDirPath) + "/" + item.getName();
/*     */         }
/*     */         
/* 298 */         if (item.isFile()) {
/*     */           
/* 300 */           String localFilePath = item.getAbsolutePath();
/* 301 */           uploadSingleFileToFTP(ftpClient, localFilePath, remoteFilePath);
/*     */         }
/*     */         else {
/*     */           
/* 305 */           ftpClient.makeDirectory(remoteFilePath);
/* 306 */           String parent = String.valueOf(remoteParentDir) + "/" + item.getName();
/* 307 */           if ("".equals(remoteParentDir))
/*     */           {
/* 309 */             parent = item.getName();
/*     */           }
/*     */           
/* 312 */           localParentDir = item.getAbsolutePath();
/* 313 */           uploadFilesToFTP(ftpClient, remoteDirPath, localParentDir, parent);
/*     */         } 
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean uploadSingleFileToFTP(FTPClient ftpClient, String localFilePath, String remoteFilePath) throws IOException {
/* 322 */     File localFile = new File(localFilePath);
/*     */     
/* 324 */     InputStream inputStream = new FileInputStream(localFile);
/*     */     
/*     */     try {
/* 327 */       ftpClient.setFileType(2);
/* 328 */       return ftpClient.storeFile(remoteFilePath, inputStream);
/*     */     }
/*     */     finally {
/*     */       
/* 332 */       inputStream.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeFilesFromFTP(String server, String userId, String password, String parentDir, String fileName) throws IOException {
/* 339 */     FTPClient ftpClient = new FTPClient();
/* 340 */     ftpClient.connect(server);
/* 341 */     ftpClient.login(userId, password);
/* 342 */     ftpClient.enterLocalPassiveMode();
/* 343 */     ftpClient.setFileType(2);
/*     */     
/* 345 */     String dirToList = parentDir;
/*     */     
/* 347 */     FTPFile[] subFiles = ftpClient.listFiles(dirToList);
/*     */     
/* 349 */     if (subFiles != null && subFiles.length > 0) {
/*     */       
/* 351 */       String filePath = String.valueOf(parentDir) + "/" + fileName;
/*     */       
/* 353 */       boolean deleted = ftpClient.deleteFile(filePath);
/* 354 */       if (deleted) {
/*     */         
/* 356 */         System.out.println("DELETED the file: " + filePath);
/*     */       }
/*     */       else {
/*     */         
/* 360 */         System.out.println("CANNOT delete the file: " + filePath);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String refineFTPAddress(String FTPAddress) {
/* 368 */     if (FTPAddress == null)
/* 369 */       return null; 
/* 370 */     FTPAddress = FTPAddress.replace("\\", "/");
/* 371 */     return FTPAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFTPServer(String FTPAddress) {
/* 376 */     if (FTPAddress == null) {
/* 377 */       return null;
/*     */     }
/* 379 */     FTPAddress = refineFTPAddress(FTPAddress);
/* 380 */     if (FTPAddress.startsWith("ftp://"))
/*     */     {
/* 382 */       return FTPAddress.substring(FTPAddress.indexOf("ftp://") + 6, FTPAddress.indexOf("/", 6));
/*     */     }
/* 384 */     if (FTPAddress.startsWith("sftp://"))
/*     */     {
/* 386 */       return FTPAddress.substring(FTPAddress.indexOf("sftp://") + 7, FTPAddress.indexOf("/", 7));
/*     */     }
/*     */     
/* 389 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSecureFTPServer(String FTPAddress) {
/* 394 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFTPSourceFolder(String FTPAddress) throws LbsLocalizableException {
/* 399 */     if (FTPAddress == null) {
/* 400 */       return null;
/*     */     }
/* 402 */     FTPAddress = refineFTPAddress(FTPAddress);
/* 403 */     String sourceDir = null;
/* 404 */     if (FTPAddress.startsWith("ftp://")) {
/*     */       
/* 406 */       sourceDir = FTPAddress.substring(FTPAddress.indexOf("ftp://") + 6);
/* 407 */       if (sourceDir.indexOf("/") == -1)
/* 408 */         throw new LbsLocalizableException(70021, 656, 
/* 409 */             "Format error in source file part of FTP address. Please enter according to the format in the example."); 
/* 410 */       sourceDir = sourceDir.substring(sourceDir.indexOf("/") + 1);
/*     */     }
/* 412 */     else if (FTPAddress.startsWith("sftp://")) {
/*     */       
/* 414 */       sourceDir = FTPAddress.substring(FTPAddress.indexOf("sftp://") + 7);
/* 415 */       if (sourceDir.indexOf("/") == -1)
/* 416 */         throw new LbsLocalizableException(70021, 656, 
/* 417 */             "Format error in source file part of FTP address. Please enter according to the format in the example."); 
/* 418 */       sourceDir = sourceDir.substring(sourceDir.indexOf("/") + 1);
/*     */     } 
/*     */     
/* 421 */     String lastCh = sourceDir.substring(sourceDir.length() - 1, sourceDir.length());
/* 422 */     if (equalEncodings(lastCh, "/"))
/*     */     {
/* 424 */       return sourceDir.substring(0, sourceDir.length() - 1);
/*     */     }
/*     */     
/* 427 */     return sourceDir;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFTPAddress(String FTPAddress) {
/* 432 */     return FTPAddress.startsWith("ftp://");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSecureFTPAddress(String FTPAddress) {
/* 437 */     return FTPAddress.startsWith("sftp://");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void throwLocalizableException(Throwable e) throws LbsLocalizableException {
/* 442 */     if (e instanceof LbsLocalizableException)
/*     */     {
/* 444 */       throw (LbsLocalizableException)e;
/*     */     }
/*     */ 
/*     */     
/* 448 */     throw new LbsLocalizableException(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean equalEncodings(String str1, String str2) {
/* 454 */     int len1 = str1.length();
/* 455 */     int len2 = str2.length();
/*     */ 
/*     */     
/* 458 */     for (int i1 = 0, i2 = 0; i1 < len1 || i2 < len2; ) {
/* 459 */       int c1 = (i1 >= len1) ? 65536 : str1.charAt(i1++);
/* 460 */       int c2 = (i2 >= len2) ? 65536 : str2.charAt(i2++);
/*     */ 
/*     */       
/* 463 */       if (c1 == c2) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 468 */       while (c1 <= 32 || c1 == 95 || c1 == 45) {
/* 469 */         c1 = (i1 >= len1) ? 65536 : str1.charAt(i1++);
/*     */       }
/* 471 */       while (c2 <= 32 || c2 == 95 || c2 == 45) {
/* 472 */         c2 = (i2 >= len2) ? 65536 : str2.charAt(i2++);
/*     */       }
/*     */       
/* 475 */       if (c1 != c2) {
/*     */         
/* 477 */         if (c1 == 65536 || c2 == 65536) {
/* 478 */           return false;
/*     */         }
/* 480 */         if (c1 < 127) {
/* 481 */           if (c1 <= 90 && c1 >= 65) {
/* 482 */             c1 += 32;
/*     */           }
/*     */         } else {
/* 485 */           c1 = Character.toLowerCase((char)c1);
/*     */         } 
/* 487 */         if (c2 < 127) {
/* 488 */           if (c2 <= 90 && c2 >= 65) {
/* 489 */             c2 += 32;
/*     */           }
/*     */         } else {
/* 492 */           c2 = Character.toLowerCase((char)c2);
/*     */         } 
/* 494 */         if (c1 != c2) {
/* 495 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 501 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\ftp\JLbsFTPUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */