/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.channel.IChannel;
/*     */ import com.lbs.channel.IChannelProvider;
/*     */ import com.lbs.channel.ICryptor;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.crypto.JLbsCryptoUtil;
/*     */ import com.lbs.start.JLbsContextLocator;
/*     */ import com.lbs.util.ClientSideOptimizationConfig;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
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
/*     */ public class CachedTransportClient
/*     */   extends TransportClient
/*     */   implements ITransportCache
/*     */ {
/*     */   private String m_RootURI;
/*     */   private String m_URI;
/*     */   private String m_ReadDataDigest;
/*     */   private String m_ReturnDataDigest;
/*     */   private byte[] m_ReadData;
/*     */   private String m_ReadDataVersion;
/*     */   private Hashtable m_CustomizationResourceList;
/*  37 */   private static String CLASSS_HASH = "DCLFDD";
/*     */   
/*  39 */   public static String ms_FileNameHashSeperator = "___";
/*  40 */   private static String ms_ClientVersion = null;
/*  41 */   private static String ms_OptimizationVersion = null;
/*  42 */   private static String ms_LastCalcVersions = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public CachedTransportClient(IChannelProvider channelProv, ISessionBase session, ICryptor localCryptor, ICryptor remoteCryptor) {
/*  47 */     super(channelProv, session, localCryptor, remoteCryptor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TransportClient duplicate() {
/*  53 */     return new CachedTransportClient(this.m_ChannelProv, this.m_Session, this.m_LocalCryptor, this.m_RemoteCryptor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] postData(String sUri, byte[] sendPrefix, byte[] sendData, boolean bPublicInfo) {
/*  59 */     return postData(sUri, sendPrefix, sendData, bPublicInfo, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] postData(String sUri, byte[] sendPrefix, byte[] sendData, boolean bPublicInfo, boolean isClass) {
/*  66 */     setURI(sUri);
/*  67 */     byte[] result = null;
/*  68 */     if (isClass) {
/*  69 */       result = getCachedClassData();
/*     */     } else {
/*  71 */       result = tryToReadCachedData(bPublicInfo, sUri);
/*  72 */     }  if (result == null) {
/*     */       
/*  74 */       result = super.postData(sUri, sendPrefix, sendData, bPublicInfo);
/*  75 */       if (!bPublicInfo && !JLbsConstants.SKIP_CACHE && result != null) {
/*     */         
/*  77 */         String fileNameHash = JLbsCryptoUtil.createHashString(this.m_URI.getBytes());
/*  78 */         String fileDataHash = CLASSS_HASH;
/*  79 */         if (!isClass)
/*  80 */           fileDataHash = (result.length > 0) ? 
/*  81 */             JLbsCryptoUtil.createHashString(result) : 
/*  82 */             ""; 
/*  83 */         if (isClass && sUri.indexOf("{") > 0)
/*  84 */           return result; 
/*  85 */         saveFileData(fileNameHash, fileDataHash, result, sUri);
/*     */       } 
/*     */     } 
/*  88 */     if (this.recorder != null)
/*  89 */       this.recorder.dataSent(sUri, sendPrefix, sendData, bPublicInfo, result); 
/*  90 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] getCachedClassData() {
/*  98 */     if (JLbsConstants.SKIP_CACHE) {
/*  99 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 103 */       JLbsClientFS.makeDirectory(getCacheDir());
/* 104 */       String fileNameHash = JLbsCryptoUtil.createHashString(this.m_URI.getBytes());
/* 105 */       String fullFileName = String.valueOf(fileNameHash) + ms_FileNameHashSeperator + getClientVersion() + ms_FileNameHashSeperator + 
/* 106 */         CLASSS_HASH;
/*     */       
/*     */       try {
/* 109 */         this.m_ReadData = JLbsClientFS.loadFile(JLbsFileUtil.appendPath(getCacheDir(), fullFileName), true);
/*     */       }
/* 111 */       catch (IOException e) {
/*     */         
/* 113 */         clearDataReferences();
/* 114 */         return null;
/*     */       } 
/* 116 */       return this.m_ReadData;
/*     */     }
/* 118 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 121 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getData(String sUri) {
/* 127 */     setURI(sUri);
/* 128 */     byte[] result = tryToReadCachedData(false, sUri);
/* 129 */     if (result == null) {
/* 130 */       result = super.getData(sUri);
/*     */     }
/* 132 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRootURI(String URI) {
/* 137 */     this.m_RootURI = URI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelBeforeDataSend(IChannel channel) {
/* 143 */     if (this.m_ReadDataDigest != null && this.m_ReadDataDigest.length() > 0)
/* 144 */       channel.setPostParam("MSGDGST", this.m_ReadDataDigest); 
/* 145 */     super.channelBeforeDataSend(channel);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean channelDataRead(IChannel channel, byte[] data) {
/* 151 */     this.m_ReturnDataDigest = channel.getReturnParam("MSGDGST");
/* 152 */     return super.channelDataRead(channel, data);
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
/*     */   protected void processTransportedData(byte[] data) {}
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
/*     */   private void updateCache(byte[] data) {
/* 193 */     JLbsClientFS.makeDirectory(getCacheDir());
/* 194 */     String digestURI = JLbsCryptoUtil.createHashString(this.m_URI.getBytes());
/*     */     
/* 196 */     if (JLbsStringUtil.isEmpty(digestURI)) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 201 */       if (data != null && data.length > 0)
/*     */       {
/* 203 */         String fileNameHash = digestURI;
/* 204 */         String fileDataHash = JLbsCryptoUtil.createHashString(data);
/* 205 */         String saveFileName = getSaveFileName(fileNameHash, fileDataHash);
/* 206 */         checkAndDeleteOldVersionedFile(fileNameHash, fileDataHash);
/* 207 */         saveCachedFile(JLbsFileUtil.appendPath(getCacheDir(), saveFileName), data, true, this.m_URI);
/*     */       }
/*     */     
/* 210 */     } catch (Exception e) {
/*     */       
/* 212 */       LbsConsole.getLogger("Transport.CachedTransClient").error("Cache save exception :" + e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setURI(String URI) {
/* 219 */     if (this.m_RootURI != null)
/*     */     {
/* 221 */       if (URI.indexOf(this.m_RootURI) == 0)
/* 222 */         URI = URI.substring(this.m_RootURI.length()); 
/*     */     }
/* 224 */     this.m_URI = URI;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] tryToReadCachedData(boolean bPublicInfo, String uri) {
/* 229 */     if (bPublicInfo || JLbsConstants.SKIP_CACHE)
/* 230 */       return null; 
/* 231 */     clearDataReferences();
/* 232 */     String clientVersion = getClientVersion();
/* 233 */     String digestURI = JLbsCryptoUtil.createHashString(this.m_URI.getBytes());
/* 234 */     String fileVersion = getFileVersion(digestURI);
/* 235 */     String fullFileName = JLbsClientFS.getFullFileName(getCacheDir(), digestURI);
/* 236 */     if (!JLbsStringUtil.isEmpty(fullFileName) && JLbsStringUtil.areEqualNoCase(fileVersion, clientVersion)) {
/*     */ 
/*     */       
/*     */       try {
/* 240 */         this.m_ReadData = JLbsClientFS.loadFile(JLbsFileUtil.appendPath(getCacheDir(), fullFileName), true);
/* 241 */         if (this.m_ReadData != null && !TransportUtil.checkFormDataUseful(uri, this.m_ReadData))
/*     */         {
/* 243 */           JLbsClientFS.deleteFile(JLbsFileUtil.appendPath(getCacheDir(), fullFileName));
/* 244 */           this.m_ReadData = null;
/*     */         }
/*     */       
/* 247 */       } catch (IOException e) {
/*     */         
/* 249 */         clearDataReferences();
/* 250 */         return null;
/*     */       } 
/* 252 */       this.m_ReadDataVersion = clientVersion;
/* 253 */       this.m_ReadDataDigest = (this.m_ReadData != null && this.m_ReadData.length > 0) ? 
/* 254 */         getFileDataHash(fullFileName) : 
/* 255 */         "";
/*     */       
/* 257 */       if (this.m_ReadDataDigest != null && this.m_ReadData != null && this.m_ReadDataVersion != null) {
/* 258 */         return this.m_ReadData;
/*     */       }
/*     */     } 
/* 261 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearDataReferences() {
/* 266 */     this.m_ReadDataDigest = null;
/* 267 */     this.m_ReturnDataDigest = null;
/* 268 */     this.m_ReadData = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileHash(String fileName) throws FileUpToDateException {
/* 273 */     if (JLbsStringUtil.isEmpty(fileName))
/* 274 */       return null; 
/* 275 */     String digestURI = JLbsCryptoUtil.createHashString(fileName.getBytes());
/* 276 */     if (JLbsStringUtil.isEmpty(digestURI)) {
/* 277 */       return null;
/*     */     }
/* 279 */     String clientVersion = getClientVersion();
/* 280 */     String fileVersion = getFileVersion(digestURI);
/*     */     
/* 282 */     if (JLbsStringUtil.areEqualNoCase(fileVersion, clientVersion)) {
/* 283 */       throw new FileUpToDateException(getPureFileData(fileName));
/*     */     }
/* 285 */     return getFileDataHash(digestURI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkAndDeleteOldVersionedFile(String fileNameHash, String fileDataHash) {
/* 291 */     String fullFileName = JLbsClientFS.getFullFileName(getCacheDir(), fileNameHash);
/* 292 */     if (!JLbsStringUtil.isEmpty(fullFileName) && !fullFileName.endsWith(fileDataHash)) {
/* 293 */       JLbsClientFS.deleteFile(JLbsFileUtil.appendPath(getCacheDir(), fullFileName));
/*     */     }
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
/*     */   private boolean saveCachedFile(String path, byte[] data, boolean bScramble, String fileName) throws IOException {
/* 307 */     if (data == null || data.length == 0) {
/* 308 */       return false;
/*     */     }
/* 310 */     if (!TransportUtil.checkFormDataUseful(fileName, data)) {
/* 311 */       return false;
/*     */     }
/* 313 */     return JLbsClientFS.saveFile(path, data, true, false, bScramble);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getCacheDir() {
/* 318 */     String cache = JLbsClientFS.getCachePath();
/* 319 */     return cache.substring(cache.lastIndexOf(File.separator) + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getClientVersion() {
/* 324 */     if (!JLbsStringUtil.isEmpty(ms_ClientVersion) && JLbsStringUtil.areEqualNoCase(ms_LastCalcVersions, getCurrentVersion()))
/* 325 */       return ms_ClientVersion; 
/* 326 */     String cVersion = getCurrentVersion();
/* 327 */     synchronized (CachedTransportClient.class) {
/*     */       
/* 329 */       ms_ClientVersion = JLbsCryptoUtil.createHashString(cVersion.getBytes());
/* 330 */       ms_LastCalcVersions = cVersion;
/*     */     } 
/* 332 */     return ms_ClientVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getCurrentVersion() {
/* 337 */     String cVersion = "~";
/* 338 */     if (!JLbsStringUtil.isEmpty(ClientSideOptimizationConfig.OPTIMIZATION_VERSION)) {
/* 339 */       cVersion = ClientSideOptimizationConfig.OPTIMIZATION_VERSION;
/*     */     } else {
/* 341 */       cVersion = JLbsContextLocator.getUnityVersion();
/*     */     } 
/* 343 */     if (JLbsStringUtil.isEmpty(cVersion)) {
/* 344 */       cVersion = JLbsConstants.VERSION_STR;
/*     */     }
/* 346 */     return cVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileVersion(String fileNamePrefix) {
/* 352 */     if (JLbsStringUtil.isEmpty(fileNamePrefix)) {
/* 353 */       return null;
/*     */     }
/* 355 */     String[] fileNames = JLbsClientFS.getFullFileNames(getCacheDir(), fileNamePrefix);
/*     */     
/* 357 */     if (fileNames == null || fileNames.length == 0) {
/* 358 */       return null;
/*     */     }
/* 360 */     String fileName = null;
/* 361 */     if (fileNames.length == 1) {
/* 362 */       fileName = fileNames[0];
/*     */     } else {
/*     */       
/* 365 */       String clientVersion = getClientVersion();
/* 366 */       String prefix = String.valueOf(fileNamePrefix) + ms_FileNameHashSeperator + clientVersion; byte b; int i; String[] arrayOfString;
/* 367 */       for (i = (arrayOfString = fileNames).length, b = 0; b < i; ) { String cFileName = arrayOfString[b];
/*     */         
/* 369 */         if (cFileName != null)
/*     */         {
/* 371 */           if (!cFileName.startsWith(prefix)) {
/* 372 */             JLbsClientFS.deleteFile(JLbsFileUtil.appendPath(getCacheDir(), cFileName));
/*     */           } else {
/* 374 */             fileName = cFileName;
/*     */           }  }  b++; }
/*     */     
/*     */     } 
/* 378 */     if (JLbsStringUtil.isEmpty(fileName))
/* 379 */       return null; 
/* 380 */     int dataHashIndex = fileName.lastIndexOf(ms_FileNameHashSeperator);
/* 381 */     int versionIndex = fileName.indexOf(ms_FileNameHashSeperator);
/*     */     
/* 383 */     if (dataHashIndex == -1 || versionIndex == -1) {
/* 384 */       return null;
/*     */     }
/* 386 */     return fileName.substring(versionIndex + ms_FileNameHashSeperator.length(), dataHashIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileDataHash(String fileNamePrefix) {
/* 391 */     if (JLbsStringUtil.isEmpty(fileNamePrefix)) {
/* 392 */       return null;
/*     */     }
/* 394 */     String fileName = JLbsClientFS.getFullFileName(getCacheDir(), fileNamePrefix);
/* 395 */     if (JLbsStringUtil.isEmpty(fileName)) {
/* 396 */       return null;
/*     */     }
/* 398 */     int dataHashIndex = fileName.lastIndexOf(ms_FileNameHashSeperator);
/* 399 */     if (dataHashIndex == -1) {
/* 400 */       return null;
/*     */     }
/* 402 */     return fileName.substring(dataHashIndex + ms_FileNameHashSeperator.length());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileNameHash(String fileNamePrefix) {
/* 407 */     if (JLbsStringUtil.isEmpty(fileNamePrefix)) {
/* 408 */       return null;
/*     */     }
/* 410 */     int versionIndex = fileNamePrefix.indexOf(ms_FileNameHashSeperator);
/* 411 */     if (versionIndex == -1) {
/* 412 */       return null;
/*     */     }
/* 414 */     return fileNamePrefix.substring(0, versionIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveFileData(String fileNameHash, String fileDataHash, byte[] data, String fileName) {
/*     */     try {
/* 421 */       JLbsClientFS.makeDirectory(getCacheDir());
/* 422 */       if (data != null)
/*     */       {
/* 424 */         String saveFileName = getSaveFileName(fileNameHash, fileDataHash);
/*     */         
/* 426 */         checkAndDeleteOldVersionedFile(fileNameHash, fileDataHash);
/* 427 */         return saveCachedFile(JLbsFileUtil.appendPath(getCacheDir(), saveFileName), data, true, fileName);
/*     */       }
/*     */     
/* 430 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSaveFileName(String fileNameHash, String fileDataHash) {
/* 438 */     String saveFileName = String.valueOf(fileNameHash) + ms_FileNameHashSeperator + getClientVersion() + ms_FileNameHashSeperator + 
/* 439 */       fileDataHash;
/* 440 */     return saveFileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getPureFileData(String fileName) {
/* 445 */     if (fileName == null || fileName.length() == 0) {
/* 446 */       return null;
/*     */     }
/*     */     try {
/* 449 */       String digestURI = JLbsCryptoUtil.createHashString(fileName.getBytes());
/* 450 */       if (JLbsStringUtil.isEmpty(digestURI)) {
/* 451 */         return null;
/*     */       }
/* 453 */       String fullFileName = JLbsClientFS.getFullFileName(getCacheDir(), digestURI);
/* 454 */       if (JLbsStringUtil.isEmpty(fullFileName)) {
/* 455 */         return null;
/*     */       }
/* 457 */       return JLbsClientFS.loadFile(JLbsFileUtil.appendPath(getCacheDir(), fullFileName), true);
/*     */     }
/* 459 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 462 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isCustomizationResource(String fileName) {
/* 467 */     if (this.m_CustomizationResourceList == null) {
/* 468 */       return false;
/*     */     }
/* 470 */     fileName = getFilteredFileName(fileName);
/* 471 */     if (JLbsStringUtil.isEmpty(fileName)) {
/* 472 */       return false;
/*     */     }
/* 474 */     return this.m_CustomizationResourceList.containsKey(fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomizationResourceList(Hashtable customizationResourceList) {
/* 479 */     this.m_CustomizationResourceList = customizationResourceList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addToCustomizationResourceList(String fileName) {
/* 484 */     if (this.m_CustomizationResourceList != null) {
/*     */       
/* 486 */       fileName = getFilteredFileName(fileName);
/* 487 */       if (!JLbsStringUtil.isEmpty(fileName)) {
/* 488 */         this.m_CustomizationResourceList.put(fileName, "");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getFilteredFileName(String fileName) {
/* 494 */     if (JLbsStringUtil.isEmpty(fileName)) {
/* 495 */       return null;
/*     */     }
/* 497 */     fileName = JLbsFileUtil.getFileName(fileName, true);
/* 498 */     int percIndex = fileName.indexOf("%");
/* 499 */     if (percIndex > 0) {
/* 500 */       fileName = fileName.substring(0, percIndex);
/*     */     }
/* 502 */     return fileName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\CachedTransportClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */