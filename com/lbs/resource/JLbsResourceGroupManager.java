/*     */ package com.lbs.resource;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarOutputStream;
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
/*     */ public class JLbsResourceGroupManager
/*     */ {
/*     */   private static Hashtable ms_ResourceGroupList;
/*     */   private static int[] ms_ResourceFileList;
/*     */   private static Connection ms_SQLServerConnection;
/*     */   private static final String FILE_EXTENSION_TXT = ".txt";
/*     */   private static final String FILE_EXTENSION_JAR = ".jar";
/*     */   private static final int GROUP_SIZE = 100;
/*     */   private static final String COLUMN_NAME_RES_NR = "RESOURCENR";
/*     */   private static final String COLUMN_NAME_RES_GRP = "RESOURCEGROUP";
/*     */   private static final String COLUMN_NAME_ORDER_NR = "ORDERNR";
/*     */   private static final String COLUMN_NAME_TAG_NR = "TAGNR";
/*     */   private static final String COLUMN_NAME_RESSTR = "RESSTR";
/*     */   public static final String COLUMN_NAME_RES_PREFIX = "PREFIXSTR";
/*     */   private static final String TABLE_NAME_TRTR = "RE_TURKISHTR";
/*     */   private static final String TABLE_NAME_ENUS = "RE_ENGLISHUS";
/*     */   private static final String TABLE_NAME_DEDE = "RE_GERMANDE";
/*     */   private static final String TABLE_NAME_FAIR = "RE_PERSIANIR";
/*     */   private static final String TABLE_NAME_ARJO = "RE_ARABICJO";
/*     */   private static final String REPLACE_LANG = "%lang%";
/*     */   private static final String REPLACE_TABLE = "%table%";
/*     */   private static final String REPLACE_RESNR = "%resnr%";
/*     */   private static final String REPLACE_RESGRP = "%resgrp%";
/*     */   private static final String REPLACE_ORDER = "%order%";
/*     */   private static final String ORDER_ASC = "ASC";
/*     */   private static final String ORDER_DESC = "DESC";
/*     */   private static final String SQL_RESOURCE_NUMBER = "SELECT R.RESOURCENR, R.RESOURCEGROUP FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% %lang% ON (RI.ID = %lang%.RESOURCEITEMREF) WHERE R.RESOURCEGROUP IN %resgrp% AND R.ID = RI.RESOURCEREF GROUP BY R.RESOURCENR, R.RESOURCEGROUP ORDER BY R.RESOURCEGROUP %order%, R.RESOURCENR";
/*     */   private static final String SQL_ALL_LANG_SPECIFIC = "SELECT DISTINCT RI.TAGNR, ISNULL(RI.PREFIXSTR, '') AS PREFIXSTR, ISNULL(STD.RESOURCESTR, '') + ISNULL(%lang%.RESOURCESTR, '') AS RESSTR FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% %lang% ON (RI.ID = %lang%.RESOURCEITEMREF) WHERE R.RESOURCEGROUP = '%resgrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resnr% ORDER BY RI.TAGNR";
/*  92 */   private static Hashtable langTableMaps = new Hashtable<>();
/*     */ 
/*     */   
/*     */   private static int getGroupNameFromID(int id) {
/*  96 */     int group = id / 100 * 100;
/*  97 */     if (id < 0 && id % 100 != 0) {
/*  98 */       group -= 100;
/*     */     }
/* 100 */     return group;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addFileIDToRelatedGroup(int id) {
/* 105 */     int groupID = getGroupNameFromID(id);
/*     */     
/* 107 */     if (ms_ResourceGroupList.containsKey(Integer.valueOf(groupID))) {
/* 108 */       addFileIDToGroup(id, (JLbsResourceGroup)ms_ResourceGroupList.get(Integer.valueOf(groupID)));
/*     */     } else {
/*     */       
/* 111 */       JLbsResourceGroup resourceGroup = new JLbsResourceGroup(Integer.toString(groupID));
/* 112 */       addFileIDToGroup(id, resourceGroup);
/* 113 */       ms_ResourceGroupList.put(Integer.valueOf(groupID), resourceGroup);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addFileIDToGroup(int id, JLbsResourceGroup group) {
/* 119 */     group.addResourceNumber(id);
/*     */   }
/*     */   
/*     */   private static int getIDFromFileName(String fileName) throws NumberFormatException, Exception {
/*     */     int id;
/* 124 */     if (fileName.endsWith(".jar")) {
/* 125 */       throw new Exception("");
/*     */     }
/* 127 */     if (!fileName.endsWith(".txt")) {
/* 128 */       throw new Exception("Warning - File does not have \"txt\" extension: " + fileName + 
/* 129 */           ". No slist based operation will be performed for this file!");
/*     */     }
/* 131 */     String pureFileName = fileName.substring(0, fileName.indexOf(".txt"));
/*     */ 
/*     */     
/*     */     try {
/* 135 */       id = Integer.parseInt(pureFileName);
/*     */     }
/* 137 */     catch (NumberFormatException e) {
/*     */       
/* 139 */       throw new NumberFormatException("Warning - File does not have a name representing an integer: " + fileName + 
/* 140 */           ". No slist based operation will be performed for this file!");
/*     */     } 
/* 142 */     return id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void traverseDirForListGeneration(String path) {
/*     */     try {
/* 149 */       String[] fileList = JLbsFileUtil.getFileListUnderDirectory(path, ".txt", false);
/* 150 */       int listLength = fileList.length;
/*     */       
/* 152 */       ms_ResourceFileList = new int[listLength];
/*     */       
/* 154 */       for (int i = 0; i < listLength; i++) {
/*     */ 
/*     */         
/*     */         try {
/* 158 */           int fileID = getIDFromFileName(fileList[i]);
/*     */           
/* 160 */           ms_ResourceFileList[i] = fileID;
/*     */         }
/* 162 */         catch (NumberFormatException nfe) {
/*     */           
/* 164 */           System.out.println(nfe.getMessage());
/*     */         }
/* 166 */         catch (Exception e) {
/*     */           
/* 168 */           if (!JLbsStringUtil.isEmpty(e.getMessage())) {
/* 169 */             System.out.println(e.getMessage());
/*     */           }
/*     */         } 
/*     */       } 
/* 173 */     } catch (NumberFormatException numberFormatException) {
/*     */ 
/*     */     
/*     */     }
/* 177 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void traverseGroupListForJarCreation(String mainPath, boolean deleteTxt) {
/* 185 */     Enumeration<JLbsResourceGroup> groupEnum = ms_ResourceGroupList.elements();
/*     */     
/* 187 */     while (groupEnum.hasMoreElements()) {
/*     */       
/* 189 */       JLbsResourceGroup resourceGroup = groupEnum.nextElement();
/* 190 */       jarGroupFiles(resourceGroup, mainPath, deleteTxt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void jarGroupFiles(JLbsResourceGroup resourceGroup, String mainPath, boolean deleteTxt) {
/* 196 */     FileOutputStream groupFOS = null;
/* 197 */     JarOutputStream groupJOS = null;
/*     */     
/*     */     try {
/* 200 */       String outputPath = String.valueOf(mainPath) + File.separatorChar + resourceGroup.getName() + ".jar";
/* 201 */       File groupJarFile = new File(outputPath);
/*     */       
/* 203 */       if (groupJarFile.exists()) {
/* 204 */         groupJarFile.delete();
/*     */       }
/* 206 */       groupJarFile.createNewFile();
/*     */       
/* 208 */       groupFOS = new FileOutputStream(outputPath);
/* 209 */       groupJOS = new JarOutputStream(groupFOS);
/*     */       
/* 211 */       FileInputStream fis = null;
/* 212 */       BufferedInputStream bis = null;
/*     */       
/* 214 */       ArrayList<Integer> fileList = resourceGroup.getResourceList();
/* 215 */       int groupSize = fileList.size();
/*     */       
/* 217 */       for (int i = 0; i < groupSize; i++) {
/*     */         
/* 219 */         String fileName = String.valueOf(((Integer)fileList.get(i)).toString()) + ".txt";
/* 220 */         String filePath = String.valueOf(mainPath) + File.separatorChar + fileName;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 226 */           fis = new FileInputStream(filePath);
/* 227 */           bis = new BufferedInputStream(fis);
/*     */ 
/*     */           
/* 230 */           JarEntry fileEntry = new JarEntry(fileName);
/* 231 */           groupJOS.putNextEntry(fileEntry);
/* 232 */           byte[] data = new byte[1024];
/*     */           int byteCount;
/* 234 */           while ((byteCount = bis.read(data, 0, 1024)) > -1) {
/* 235 */             groupJOS.write(data, 0, byteCount);
/*     */           }
/* 237 */         } catch (IOException iOException) {
/*     */ 
/*     */         
/*     */         } finally {
/*     */           
/* 242 */           if (fis != null) {
/*     */ 
/*     */             
/*     */             try {
/* 246 */               fis.close();
/*     */             }
/* 248 */             catch (IOException iOException) {}
/*     */ 
/*     */             
/* 251 */             fis = null;
/*     */           } 
/*     */           
/* 254 */           if (bis != null) {
/*     */ 
/*     */             
/*     */             try {
/* 258 */               bis.close();
/*     */             }
/* 260 */             catch (IOException iOException) {}
/*     */ 
/*     */             
/* 263 */             bis = null;
/*     */           } 
/*     */           
/* 266 */           if (deleteTxt)
/*     */           {
/* 268 */             if (JLbsFileUtil.deleteFile(filePath)) {
/* 269 */               System.out.println("Deleted file : " + filePath);
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/* 274 */       groupJOS.flush();
/* 275 */       System.out.println("Created Jar File : " + outputPath);
/*     */     }
/* 277 */     catch (IOException e) {
/*     */       
/* 279 */       System.out.println(e.getMessage());
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 285 */         if (groupJOS != null) {
/* 286 */           groupJOS.close();
/*     */         }
/* 288 */       } catch (IOException ex) {
/*     */         
/* 290 */         ex.printStackTrace();
/*     */       } 
/*     */       
/*     */       try {
/* 294 */         if (groupFOS != null) {
/* 295 */           groupFOS.close();
/*     */         }
/* 297 */       } catch (IOException ex) {
/*     */         
/* 299 */         ex.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void connectToSQLServer(String serverName, String dbName, String userName, String password) {
/* 307 */     String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
/*     */     
/*     */     try {
/* 310 */       Class.forName(driver).newInstance();
/*     */     }
/* 312 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     String url = "jdbc:sqlserver://" + serverName;
/*     */     
/* 319 */     Properties props = new Properties();
/* 320 */     props.setProperty("database", dbName);
/* 321 */     props.setProperty("user", userName);
/* 322 */     props.setProperty("password", password);
/*     */ 
/*     */     
/*     */     try {
/* 326 */       ms_SQLServerConnection = DriverManager.getConnection(url, props);
/*     */     }
/* 328 */     catch (Exception e) {
/*     */       
/* 330 */       ms_SQLServerConnection = null;
/* 331 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static ResultSet executeQuery(String sqlStatement) {
/* 337 */     ResultSet resultSet = null;
/* 338 */     Statement stmt = null;
/*     */     
/*     */     try {
/* 341 */       stmt = ms_SQLServerConnection.createStatement();
/* 342 */       resultSet = stmt.executeQuery(sqlStatement);
/*     */     }
/* 344 */     catch (Exception e) {
/*     */       
/* 346 */       resultSet = null;
/* 347 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 351 */       if (stmt != null) {
/*     */         
/*     */         try {
/*     */           
/* 355 */           stmt.close();
/*     */         }
/* 357 */         catch (SQLException sQLException) {}
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 362 */       if (resultSet != null) {
/*     */         
/*     */         try {
/*     */           
/* 366 */           resultSet.close();
/*     */         }
/* 368 */         catch (SQLException e) {
/*     */           
/* 370 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 375 */     return resultSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void populateItemList(String lang, String slistDirGeneral, String slistDirReporting, String langTable, int resNr, String langDir, String groupList, String resGroup) {
/* 382 */     String finalSQLStmt = "SELECT DISTINCT RI.TAGNR, ISNULL(RI.PREFIXSTR, '') AS PREFIXSTR, ISNULL(STD.RESOURCESTR, '') + ISNULL(%lang%.RESOURCESTR, '') AS RESSTR FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% %lang% ON (RI.ID = %lang%.RESOURCEITEMREF) WHERE R.RESOURCEGROUP = '%resgrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resnr% ORDER BY RI.TAGNR".replaceAll("%lang%", lang).replaceAll("%table%", langTable).replaceAll(
/* 383 */         "%resnr%", String.valueOf(resNr)).replaceAll("%resgrp%", resGroup);
/*     */     
/* 385 */     StringBuilder contentBuffer = new StringBuilder();
/*     */     
/*     */     try {
/* 388 */       ResultSet rsItems = executeQuery(finalSQLStmt);
/* 389 */       while (rsItems.next()) {
/*     */         
/* 391 */         int tagNr = rsItems.getInt("TAGNR");
/* 392 */         String resStr = rsItems.getString("RESSTR");
/* 393 */         String resPrefix = rsItems.getString("PREFIXSTR");
/* 394 */         contentBuffer.append(String.valueOf(resPrefix) + resStr + "~" + String.valueOf(tagNr) + "\n");
/*     */       } 
/*     */       
/* 397 */       String filePath = String.valueOf(slistDirGeneral) + File.separator + String.valueOf(resNr) + ".txt";
/* 398 */       createSListTextFile(filePath, contentBuffer);
/*     */ 
/*     */ 
/*     */       
/* 402 */       if (resGroup.equalsIgnoreCase("UNRP") || resGroup.equalsIgnoreCase("HRRP")) {
/*     */         
/* 404 */         filePath = String.valueOf(slistDirReporting) + File.separator + String.valueOf(resNr) + ".txt";
/* 405 */         createSListTextFile(filePath, contentBuffer);
/*     */       } 
/*     */       
/* 408 */       rsItems.close();
/*     */     }
/* 410 */     catch (SQLException e) {
/*     */       
/* 412 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createSListTextFile(String filePath, StringBuilder buffer) {
/*     */     try {
/* 420 */       if (buffer == null || buffer.length() == 0) {
/*     */         return;
/*     */       }
/* 423 */       File txtFile = new File(filePath);
/* 424 */       if (txtFile.exists()) {
/* 425 */         txtFile.delete();
/*     */       }
/* 427 */       FileOutputStream fos = new FileOutputStream(filePath, true);
/* 428 */       OutputStreamWriter osw = new OutputStreamWriter(fos, "Unicode");
/* 429 */       osw.write(buffer.toString());
/* 430 */       osw.close();
/* 431 */       fos.close();
/*     */       
/* 433 */       System.out.println("Created Slist File : " + filePath);
/*     */     }
/* 435 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createSListFiles(String serverName, String dbName, String userName, String password, String lang, String slistDirGeneral, String slistDirReporting, String groupList, String langDir, String order) {
/* 444 */     Object o = langTableMaps.get(lang);
/*     */     
/* 446 */     if (o != null && o instanceof String) {
/*     */       
/* 448 */       String langTable = (String)o;
/*     */       
/* 450 */       connectToSQLServer(serverName, dbName, userName, password);
/*     */       
/* 452 */       String finalSQLStmt = "SELECT R.RESOURCENR, R.RESOURCEGROUP FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% %lang% ON (RI.ID = %lang%.RESOURCEITEMREF) WHERE R.RESOURCEGROUP IN %resgrp% AND R.ID = RI.RESOURCEREF GROUP BY R.RESOURCENR, R.RESOURCEGROUP ORDER BY R.RESOURCEGROUP %order%, R.RESOURCENR".replaceAll("%lang%", lang).replaceAll("%table%", langTable)
/* 453 */         .replaceAll("%resgrp%", groupList).replaceAll("%order%", order);
/* 454 */       ResultSet rs = executeQuery(finalSQLStmt);
/*     */       
/*     */       try {
/* 457 */         while (rs.next()) {
/*     */           
/* 459 */           int resNr = rs.getInt("RESOURCENR");
/* 460 */           String resGroup = rs.getString("RESOURCEGROUP");
/* 461 */           populateItemList(lang, slistDirGeneral, slistDirReporting, langTable, resNr, langDir, groupList, resGroup);
/*     */         } 
/* 463 */         rs.close();
/*     */       
/*     */       }
/* 466 */       catch (SQLException e1) {
/*     */         
/* 468 */         e1.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createJarFiles(String sListPath, boolean deleteTxt) {
/* 476 */     ms_ResourceGroupList = new Hashtable<>();
/*     */     
/* 478 */     traverseDirForListGeneration(sListPath);
/*     */     
/* 480 */     int iSize = ms_ResourceFileList.length;
/* 481 */     for (int i = 0; i < iSize; i++) {
/* 482 */       addFileIDToRelatedGroup(ms_ResourceFileList[i]);
/*     */     }
/* 484 */     traverseGroupListForJarCreation(sListPath, deleteTxt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 490 */     if (args.length == 1) {
/*     */       
/* 492 */       String sListPath = args[0];
/* 493 */       System.err.println(sListPath);
/* 494 */       createJarFiles(sListPath, true);
/*     */     }
/* 496 */     else if (args.length == 9 || args.length == 10) {
/*     */       
/* 498 */       long start = Calendar.getInstance().getTimeInMillis();
/*     */       
/* 500 */       langTableMaps.put("TRTR", "RE_TURKISHTR");
/* 501 */       langTableMaps.put("ENUS", "RE_ENGLISHUS");
/* 502 */       langTableMaps.put("DEDE", "RE_GERMANDE");
/* 503 */       langTableMaps.put("FAIR", "RE_PERSIANIR");
/* 504 */       langTableMaps.put("ARJO", "RE_ARABICJO");
/*     */       
/* 506 */       String serverName = args[0];
/* 507 */       String dbName = args[1];
/* 508 */       String userName = args[2];
/* 509 */       String password = args[3];
/* 510 */       String langs = args[4];
/* 511 */       String groupNames = args[5];
/* 512 */       String outputDir = args[6];
/* 513 */       boolean enableJarCreation = Boolean.valueOf(args[7]).booleanValue();
/* 514 */       boolean deleteTxtAfterJarCreation = Boolean.valueOf(args[8]).booleanValue();
/*     */       
/* 516 */       String order = "";
/*     */       
/* 518 */       if (args.length == 10 && (args[9].equalsIgnoreCase("ASC") || args[9].equalsIgnoreCase("DESC"))) {
/* 519 */         order = args[9];
/*     */       }
/* 521 */       if (JLbsStringUtil.isEmpty(groupNames)) {
/* 522 */         throw new Exception("Resource group names must be entered as a semicolon-seperated list of desired values!!");
/*     */       }
/* 524 */       String resGroups = createResGroupString(groupNames);
/*     */       
/* 526 */       if (JLbsStringUtil.isEmpty(groupNames)) {
/* 527 */         throw new Exception("Resource group names can not be empty!!");
/*     */       }
/* 529 */       String[] langList = langs.split(";");
/* 530 */       for (int i = 0; i < langList.length; i++) {
/*     */         
/* 532 */         File mainOutputDir = new File(outputDir);
/*     */         
/* 534 */         if (!mainOutputDir.exists()) {
/* 535 */           throw new Exception("Output directory '" + outputDir + "' does not exist!!");
/*     */         }
/* 537 */         File firstInnerDirGeneral = new File(String.valueOf(outputDir) + File.separator + "resources");
/* 538 */         if (!firstInnerDirGeneral.exists()) {
/* 539 */           firstInnerDirGeneral.mkdir();
/*     */         }
/* 541 */         File secondInnerDirGeneral = new File(String.valueOf(outputDir) + File.separator + "resources" + File.separator + langList[i]);
/* 542 */         if (!secondInnerDirGeneral.exists()) {
/* 543 */           secondInnerDirGeneral.mkdir();
/*     */         }
/* 545 */         String sListDirGeneralStr = String.valueOf(outputDir) + File.separator + "resources" + File.separator + langList[i] + 
/* 546 */           File.separator + "slist";
/* 547 */         File sListDirGeneral = new File(sListDirGeneralStr);
/* 548 */         if (!sListDirGeneral.exists()) {
/* 549 */           sListDirGeneral.mkdir();
/*     */         }
/* 551 */         File firstInnerDirReporting = new File(String.valueOf(outputDir) + File.separator + "Reporting");
/* 552 */         if (!firstInnerDirReporting.exists()) {
/* 553 */           firstInnerDirReporting.mkdir();
/*     */         }
/* 555 */         File secondInnerDirReporting = new File(String.valueOf(outputDir) + File.separator + "Reporting" + File.separator + "slist");
/* 556 */         if (!secondInnerDirReporting.exists()) {
/* 557 */           secondInnerDirReporting.mkdir();
/*     */         }
/* 559 */         String sListDirReportingStr = String.valueOf(outputDir) + File.separator + "Reporting" + File.separator + "slist" + File.separator + 
/* 560 */           langList[i];
/* 561 */         File sListDirReporting = new File(sListDirReportingStr);
/* 562 */         if (!sListDirReporting.exists()) {
/* 563 */           sListDirReporting.mkdir();
/*     */         }
/* 565 */         String langDirName = String.valueOf(outputDir) + File.separator + langList[i];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 572 */         createSListFiles(serverName, dbName, userName, password, langList[i], sListDirGeneralStr, sListDirReportingStr, 
/* 573 */             resGroups, langDirName, order);
/*     */         
/* 575 */         if (enableJarCreation) {
/*     */           
/* 577 */           createJarFiles(sListDirGeneralStr, deleteTxtAfterJarCreation);
/* 578 */           createJarFiles(sListDirReportingStr, deleteTxtAfterJarCreation);
/*     */         } 
/*     */         
/* 581 */         long diff = (Calendar.getInstance().getTimeInMillis() - start) / 1000L;
/* 582 */         System.out.println("\nBuild completed!! Performed in " + diff + " seconds..");
/*     */       } 
/*     */     } else {
/*     */       
/* 586 */       throw new Exception(
/* 587 */           "Wrong number of arguments!! Use 1 (sListPath) or 9 (serverName, dbName, userName, password, langs [semicolon-seperated list], resource groups [semicolon-seperated list], outputDir, enableJarCreation, deleteTxtAfterJarCreation) arguments!!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String createResGroupString(String groupNames) {
/* 594 */     StringBuilder resGroupsBuff = new StringBuilder();
/* 595 */     String[] groupList = groupNames.split(";");
/* 596 */     for (int i = 0; i < groupList.length; i++) {
/*     */       
/* 598 */       if (i == 0) {
/* 599 */         resGroupsBuff.append("(");
/*     */       }
/*     */ 
/*     */       
/* 603 */       resGroupsBuff.append("'" + groupList[i] + "'");
/*     */       
/* 605 */       if (i == groupList.length - 1) {
/*     */         
/* 607 */         resGroupsBuff.append(")");
/*     */       } else {
/* 609 */         resGroupsBuff.append(",");
/*     */       } 
/*     */     } 
/* 612 */     return resGroupsBuff.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\JLbsResourceGroupManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */