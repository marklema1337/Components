/*      */ package com.lbs.localization.db.synchronizer;
/*      */ 
/*      */ import com.lbs.localization.ILocalizationConstants;
/*      */ import com.lbs.localization.JLbsLocalizationUtil;
/*      */ import com.lbs.localization.LbsLocalizationConfig;
/*      */ import com.lbs.localization.LuceneConnection;
/*      */ import com.lbs.localization.LuceneLocalizationServices;
/*      */ import com.lbs.util.JLbsConvertUtil;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import com.lbs.util.JLbsFileUtil;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringListItem;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Properties;
/*      */ import java.util.UUID;
/*      */ import org.apache.lucene.document.Document;
/*      */ import org.apache.tools.ant.Project;
/*      */ import org.apache.tools.ant.taskdefs.Delete;
/*      */ import org.apache.tools.ant.taskdefs.Expand;
/*      */ import org.apache.tools.ant.taskdefs.Zip;
/*      */ import org.apache.tools.ant.util.FileUtils;
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
/*      */ public class LuceneLocalizationDBSynchronizer
/*      */   implements ILocalizationConstants
/*      */ {
/*      */   private static final int INTERVAL_INSERTION_RESOURCE = 100;
/*      */   private static final int INTERVAL_LOG_INFO = 50;
/*      */   LuceneImporterInfo m_ImporterInfo;
/*      */   private Connection m_ResourceDBConnection;
/*      */   private String m_ServerName;
/*      */   private String m_DBName;
/*      */   private String m_UserName;
/*      */   private String m_Password;
/*      */   private HashMap<String, ArrayList<Integer>> m_ResourceList;
/*      */   private String m_Lang;
/*      */   private String m_productIds;
/*      */   private static long synchTimeDiff;
/*      */   private boolean throwExceptionOnError;
/*      */   private static boolean m_writeResLog = true;
/*      */   private static final String ZIPFILE_OLD_SUFFIX = "old";
/*      */   private static final String ZIPFILE_NEW_SUFFIX = "new";
/*      */   
/*      */   static {
/*   82 */     LuceneLocalizationServices.disableOracleLogging();
/*   83 */     cleanTempDirectories();
/*      */   }
/*      */ 
/*      */   
/*      */   public LuceneLocalizationDBSynchronizer() {
/*   88 */     LuceneLocalizationServices.disableOracleLogging();
/*   89 */     this.m_ImporterInfo = new LuceneImporterInfo();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void cleanTempDirectories() {
/*   94 */     String tmpDir = JLbsFileUtil.getTempDirectory();
/*   95 */     String[] fileList = JLbsFileUtil.getFileListUnderDirectory(tmpDir);
/*      */     
/*   97 */     for (int i = 0; i < fileList.length; i++) {
/*      */       
/*   99 */       if (fileList[i].startsWith("EmbeddedDB_")) {
/*      */         
/*  101 */         String fullPath = String.valueOf(tmpDir) + fileList[i];
/*  102 */         File tmpFile = new File(fullPath);
/*  103 */         if (tmpFile.exists() && tmpFile.isDirectory()) {
/*  104 */           JLbsFileUtil.deleteDirectory(fullPath);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void initialize(String serverName, String dbName, String userName, String password, String resourceList, String productIds) throws Exception {
/*  112 */     this.m_ServerName = serverName;
/*  113 */     this.m_DBName = dbName;
/*  114 */     this.m_UserName = userName;
/*  115 */     this.m_Password = password;
/*  116 */     this.m_productIds = "," + productIds + ",";
/*  117 */     this.m_ResourceDBConnection = getResourceDBConnection(this.m_ServerName, this.m_DBName, this.m_UserName, this.m_Password);
/*      */     
/*  119 */     if (resourceList != null) {
/*      */       
/*  121 */       this.m_ResourceList = new HashMap<>();
/*  122 */       String[] groups = JLbsStringUtil.tokenize(resourceList, ";");
/*  123 */       if (groups != null) {
/*  124 */         for (int i = 0; i < groups.length; i++) {
/*      */           
/*  126 */           String[] group = JLbsStringUtil.tokenize(groups[i], ":");
/*  127 */           if (group != null && group.length == 2) {
/*      */             
/*  129 */             String groupName = group[0];
/*  130 */             String[] resList = JLbsStringUtil.tokenize(group[1], ",");
/*      */             
/*  132 */             ArrayList<Integer> resNumbers = new ArrayList<>();
/*  133 */             for (int j = 0; j < resList.length; j++)
/*  134 */               resNumbers.add(Integer.valueOf(resList[j])); 
/*  135 */             this.m_ResourceList.put(groupName, resNumbers);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private Connection getResourceDBConnection(String serverName, String dbName, String userName, String password) throws Exception {
/*  143 */     String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
/*  144 */     Class.forName(driver).newInstance();
/*      */     
/*  146 */     String url = "jdbc:sqlserver://" + serverName;
/*      */     
/*  148 */     Properties props = new Properties();
/*  149 */     props.setProperty("database", dbName);
/*  150 */     props.setProperty("user", userName);
/*  151 */     props.setProperty("password", password);
/*      */     
/*  153 */     return DriverManager.getConnection(url, props);
/*      */   }
/*      */ 
/*      */   
/*      */   public Connection getResourceDBConnection() {
/*  158 */     return this.m_ResourceDBConnection;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Timestamp getLastSynchronizationTime(LuceneConnection conn) throws Exception {
/*  163 */     File f = new File(String.valueOf(conn.getPath()) + "/synctime.txt");
/*  164 */     if (f.exists()) {
/*      */       
/*  166 */       FileReader fileReader = null;
/*      */       
/*      */       try {
/*  169 */         fileReader = new FileReader(f);
/*  170 */         String t = FileUtils.readFully(fileReader);
/*      */         
/*  172 */         long longValue = Long.valueOf(t.trim()).longValue();
/*  173 */         longValue -= synchTimeDiff;
/*      */         
/*  175 */         return new Timestamp(longValue);
/*      */       }
/*      */       finally {
/*      */         
/*  179 */         if (fileReader != null) {
/*  180 */           fileReader.close();
/*      */         }
/*      */       } 
/*      */     } 
/*  184 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void importResources(LuceneConnection connection, String tableName, String resGroup, Timestamp serverTime) {
/*  189 */     log("Import started for path " + connection.getPath() + " for group " + resGroup + " : " + 
/*  190 */         JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  191 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%'".replaceAll("%resGrp%", resGroup));
/*      */     
/*  193 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  197 */         int written = 0, count = 0;
/*  198 */         int resGroupBase = JLbsLocalizationUtil.getResGroupBase(resGroup);
/*      */         
/*  200 */         while (resources.next()) {
/*      */           
/*  202 */           int resNumber = resources.getInt("RESOURCENR");
/*  203 */           String desc = resources.getString("DESCRIPTION");
/*  204 */           int ownerProduct = resources.getInt("OWNERPRODUCT");
/*  205 */           int orgResNumber = resNumber;
/*      */ 
/*      */           
/*  208 */           if (resNumber == -1002 && resGroup.equalsIgnoreCase("HR")) {
/*      */             continue;
/*      */           }
/*  211 */           if (!isIncludedResource(resGroup, resNumber)) {
/*      */             continue;
/*      */           }
/*  214 */           resNumber = JLbsLocalizationUtil.getDBResNumber(resNumber, resGroupBase);
/*      */           
/*  216 */           JLbsStringList sl = new JLbsStringList();
/*  217 */           sl.setID(resNumber);
/*  218 */           sl.setDescription(desc);
/*  219 */           importResourceItems(sl, tableName, orgResNumber, resNumber, resGroup);
/*  220 */           if (m_writeResLog)
/*  221 */             log("Serializing list of " + resNumber + " : " + 
/*  222 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  223 */           ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  224 */           ObjectOutputStream out = new ObjectOutputStream(baos);
/*  225 */           out.writeObject(Integer.valueOf(0));
/*  226 */           out.writeObject(sl);
/*  227 */           out.flush();
/*  228 */           out.close();
/*  229 */           byte[] serialized = baos.toByteArray();
/*  230 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.resources);
/*  231 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.ResourceFields.resourceNumber, resNumber);
/*  232 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.ResourceFields.ownerProduct, ownerProduct);
/*  233 */           connection.addFieldByteArray(doc, (Enum)ILocalizationConstants.ResourceFields.contents, serialized);
/*  234 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.ResourceFields.time, serverTime);
/*  235 */           connection.add(resGroup, doc);
/*  236 */           if (m_writeResLog) {
/*  237 */             log("\tFinished : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */           }
/*  239 */           written++;
/*  240 */           count++;
/*      */           
/*  242 */           if (written % 100 == 0) {
/*      */             
/*  244 */             connection.commit();
/*  245 */             if (m_writeResLog)
/*  246 */               log("Finished import of \"" + written + "\" resources : " + 
/*  247 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  248 */             written = 0;
/*      */           } 
/*      */         } 
/*      */         
/*  252 */         if (written > 0) {
/*      */           
/*  254 */           connection.commit();
/*  255 */           if (m_writeResLog)
/*  256 */             log("Finished import of \"" + written + "\" resources : " + 
/*  257 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*      */         } 
/*  259 */         log("Import completed for path " + connection.getPath() + " for group " + resGroup + " (" + count + " items) : " + 
/*  260 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       }
/*  262 */       catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  266 */           connection.rollback();
/*      */         }
/*  268 */         catch (IOException e) {
/*      */           
/*  270 */           e.printStackTrace();
/*      */         } 
/*  272 */         e1.printStackTrace();
/*  273 */         log("Error during importing resources!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  279 */           resources.close();
/*      */         }
/*  281 */         catch (SQLException e) {
/*      */           
/*  283 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void importHelpDocuments(LuceneConnection connection, Timestamp serverTime) {
/*  291 */     log("Import started for path " + connection.getPath() + " for help documents : " + 
/*  292 */         JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  293 */     ResultSet helpDocs = executeQuery(this.m_ResourceDBConnection, "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY FROM RE_HELPDOCS H");
/*      */     
/*  295 */     if (helpDocs != null) {
/*      */       
/*      */       try {
/*      */         
/*  299 */         int written = 0, count = 0;
/*  300 */         while (helpDocs.next()) {
/*      */           
/*  302 */           int id = helpDocs.getInt("ID");
/*  303 */           String docName = helpDocs.getString("DOCNAME");
/*  304 */           String docTitle = helpDocs.getString("DOCTITLE");
/*  305 */           String docBody = helpDocs.getString("DOCBODY");
/*  306 */           int docType = helpDocs.getInt("DOCTYPE");
/*      */           
/*  308 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.helps);
/*  309 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.HelpFields.helpId, id);
/*  310 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.HelpFields.documentName, docName);
/*  311 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.HelpFields.documentTitle, docTitle);
/*  312 */           connection.addFieldText(doc, (Enum)ILocalizationConstants.HelpFields.documentBody, docBody);
/*  313 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.HelpFields.documentType, docType);
/*  314 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.HelpFields.time, serverTime);
/*  315 */           connection.add("_misc_", doc);
/*      */           
/*  317 */           written++;
/*  318 */           count++;
/*      */           
/*  320 */           if (written % 100 == 0) {
/*      */             
/*  322 */             if (m_writeResLog)
/*  323 */               log("Finished import of \"" + written + "\" help documents : " + 
/*  324 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  325 */             written = 0;
/*  326 */             connection.commit();
/*      */           } 
/*      */         } 
/*      */         
/*  330 */         if (written > 0) {
/*      */           
/*  332 */           if (m_writeResLog)
/*  333 */             log("Finished import of \"" + written + "\" help documents : " + 
/*  334 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  335 */           written = 0;
/*  336 */           connection.commit();
/*      */         } 
/*  338 */         log("Import completed for path " + connection.getPath() + " for help documents (" + count + " items) : " + 
/*  339 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       }
/*  341 */       catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  345 */           connection.rollback();
/*      */         }
/*  347 */         catch (IOException e) {
/*      */           
/*  349 */           e.printStackTrace();
/*      */         } 
/*  351 */         e1.printStackTrace();
/*  352 */         log("Error during importing help documents!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  358 */           helpDocs.close();
/*      */         }
/*  360 */         catch (SQLException e) {
/*      */           
/*  362 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void importMessages(LuceneConnection connection, Timestamp serverTime) {
/*  370 */     log("Import started for path " + connection.getPath() + " for messages : " + 
/*  371 */         JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  372 */     ResultSet messages = executeQuery(this.m_ResourceDBConnection, "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON FROM RE_MESSAGES M");
/*      */     
/*  374 */     if (messages != null) {
/*      */       
/*      */       try {
/*      */         
/*  378 */         int written = 0, count = 0;
/*  379 */         while (messages.next()) {
/*      */           
/*  381 */           int key = messages.getInt("ID");
/*  382 */           String id = messages.getString("CONS_ID");
/*  383 */           String module = messages.getString("MODULE");
/*  384 */           int type = messages.getInt("MTYPE");
/*  385 */           int listId = messages.getInt("LISTID");
/*  386 */           int strTag = messages.getInt("STRTAG");
/*  387 */           String resGroup = messages.getString("RESGROUP");
/*  388 */           int buttons = messages.getInt("BUTTONS");
/*  389 */           int defButton = messages.getInt("DEF_BUTTON");
/*      */           
/*  391 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.messages);
/*  392 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.key, key);
/*  393 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.constantId, id);
/*  394 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.module, module);
/*  395 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.type, type);
/*  396 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.listId, listId);
/*  397 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.stringTag, strTag);
/*  398 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.resourceGroup, resGroup);
/*  399 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.buttons, buttons);
/*  400 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.defaultButton, defButton);
/*  401 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.MessageFields.time, serverTime);
/*  402 */           connection.add("_misc_", doc);
/*      */           
/*  404 */           written++;
/*  405 */           count++;
/*      */           
/*  407 */           if (written % 100 == 0) {
/*      */             
/*  409 */             if (m_writeResLog)
/*  410 */               log("Finished import of \"" + written + "\" messages : " + 
/*  411 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  412 */             written = 0;
/*  413 */             connection.commit();
/*      */           } 
/*      */         } 
/*      */         
/*  417 */         if (written > 0) {
/*      */           
/*  419 */           if (m_writeResLog)
/*  420 */             log("Finished import of \"" + written + "\" messages : " + 
/*  421 */                 JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  422 */           connection.commit();
/*      */         } 
/*  424 */         log("Import completed for path " + connection.getPath() + " for messages (" + count + " items) : " + 
/*  425 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       }
/*  427 */       catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  431 */           connection.rollback();
/*      */         }
/*  433 */         catch (IOException e) {
/*      */           
/*  435 */           e.printStackTrace();
/*      */         } 
/*  437 */         e1.printStackTrace();
/*  438 */         log("Error during importing message!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  444 */           messages.close();
/*      */         }
/*  446 */         catch (SQLException e) {
/*      */           
/*  448 */           e.printStackTrace();
/*      */         } 
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
/*      */   private boolean isIncludedResource(String resGroup, int resNumber) {
/*  461 */     if (this.m_ResourceList == null) {
/*  462 */       return true;
/*      */     }
/*  464 */     if (this.m_ResourceList.get(resGroup) != null) {
/*      */       
/*  466 */       ArrayList<Integer> resList = this.m_ResourceList.get(resGroup);
/*  467 */       return (resList.indexOf(Integer.valueOf(resNumber)) != -1);
/*      */     } 
/*  469 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void synchronizeResources(LuceneConnection connection, String tableName, String resGroup, String lastSynchTime, Timestamp serverTime) {
/*  475 */     if (m_writeResLog)
/*  476 */       log("Synchronization started for path " + connection.getPath() + " for group " + resGroup + " : " + 
/*  477 */           JLbsDateUtil.dateToString(GregorianCalendar.getInstance())); 
/*  478 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, "SELECT R.ID, R.RESOURCENR, R.DESCRIPTION, R.MODIFIEDON, R.AUTOMODIFIEDON, R.OWNERPRODUCT FROM RE_RESOURCES R WHERE R.RESOURCEGROUP = '%resGrp%' AND (R.MODIFIEDON > '%synchTime%' OR R.AUTOMODIFIEDON > '%synchTime%') ORDER BY R.RESOURCENR"
/*  479 */         .replaceAll("%resGrp%", resGroup).replaceAll("%synchTime%", lastSynchTime));
/*      */     
/*  481 */     int written = 0, count = 0;
/*  482 */     StringBuilder logBuffer = new StringBuilder();
/*  483 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  487 */         int resGroupBase = JLbsLocalizationUtil.getResGroupBase(resGroup);
/*      */         
/*  489 */         while (resources.next()) {
/*      */           
/*  491 */           int resNumber = resources.getInt("RESOURCENR");
/*  492 */           int ownerProduct = resources.getInt("OWNERPRODUCT");
/*  493 */           int orgResNumber = resNumber;
/*      */ 
/*      */           
/*  496 */           if (resNumber == -1002 && resGroup.equalsIgnoreCase("HR")) {
/*      */             continue;
/*      */           }
/*  499 */           if (!isIncludedResource(resGroup, resNumber)) {
/*      */             continue;
/*      */           }
/*  502 */           resNumber = JLbsLocalizationUtil.getDBResNumber(resNumber, resGroupBase);
/*      */ 
/*      */           
/*  505 */           connection.deleteDocuments(resGroup, (Enum)ILocalizationConstants.Catalogs.resources, (Enum)ILocalizationConstants.ResourceFields.resourceNumber, Integer.valueOf(resNumber));
/*      */           
/*  507 */           String desc = resources.getString("DESCRIPTION");
/*      */           
/*  509 */           JLbsStringList sl = new JLbsStringList();
/*  510 */           sl.setID(resNumber);
/*  511 */           sl.setDescription(desc);
/*  512 */           importResourceItems(sl, tableName, orgResNumber, resNumber, resGroup);
/*  513 */           ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  514 */           ObjectOutputStream out = new ObjectOutputStream(baos);
/*  515 */           out.writeObject(Integer.valueOf(0));
/*  516 */           out.writeObject(sl);
/*  517 */           out.flush();
/*  518 */           out.close();
/*  519 */           byte[] serialized = baos.toByteArray();
/*  520 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.resources);
/*  521 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.ResourceFields.resourceNumber, resNumber);
/*  522 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.ResourceFields.ownerProduct, ownerProduct);
/*  523 */           connection.addFieldByteArray(doc, (Enum)ILocalizationConstants.ResourceFields.contents, serialized);
/*  524 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.ResourceFields.time, serverTime);
/*  525 */           connection.add(resGroup, doc);
/*      */           
/*  527 */           written++;
/*  528 */           count++;
/*  529 */           if (logBuffer.length() > 0) {
/*  530 */             logBuffer.append(", ");
/*      */           }
/*  532 */           logBuffer.append(orgResNumber);
/*      */           
/*  534 */           if (written % 50 == 0) {
/*      */             
/*  536 */             if (m_writeResLog)
/*  537 */               log("Finished synchronization of resources : \"" + logBuffer.toString() + "\""); 
/*  538 */             written = 0;
/*  539 */             connection.commit();
/*  540 */             logBuffer = new StringBuilder();
/*      */           } 
/*      */         } 
/*  543 */         if (written > 0) {
/*      */           
/*  545 */           if (m_writeResLog)
/*  546 */             log("Finished synchronization of resources : \"" + logBuffer.toString() + "\""); 
/*  547 */           connection.commit();
/*      */         } 
/*  549 */         if (m_writeResLog) {
/*  550 */           log("Synchronization completed for path " + connection.getPath() + " for group " + resGroup + " (" + count + 
/*  551 */               " items) : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */         }
/*  553 */       } catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  557 */           connection.rollback();
/*      */         }
/*  559 */         catch (IOException e) {
/*      */           
/*  561 */           e.printStackTrace();
/*      */         } 
/*  563 */         e1.printStackTrace();
/*  564 */         log("Error during synchronizing resources!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  570 */           resources.close();
/*      */         }
/*  572 */         catch (SQLException e) {
/*      */           
/*  574 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void synchronizeHelpDocuments(LuceneConnection connection, String lastSynchTime, Timestamp serverTime) {
/*  582 */     log("Synchronization started for path " + connection.getPath() + " for help documents : " + 
/*  583 */         JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  584 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, 
/*  585 */         "SELECT H.ID, H.DOCNAME, H.DOCTITLE, H.DOCTYPE, H.DOCBODY, H.MODIFIEDON FROM RE_HELPDOCS H WHERE H.MODIFIEDON > '%synchTime%' ORDER BY H.ID".replaceAll("%synchTime%", lastSynchTime));
/*      */     
/*  587 */     int written = 0, count = 0;
/*  588 */     StringBuilder logBuffer = new StringBuilder();
/*  589 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  593 */         while (resources.next()) {
/*      */           
/*  595 */           int id = resources.getInt("ID");
/*  596 */           String docName = resources.getString("DOCNAME");
/*  597 */           String docTitle = resources.getString("DOCTITLE");
/*  598 */           String docBody = resources.getString("DOCBODY");
/*  599 */           int docType = resources.getInt("DOCTYPE");
/*      */ 
/*      */           
/*  602 */           connection.deleteDocuments("_misc_", (Enum)ILocalizationConstants.Catalogs.helps, (Enum)ILocalizationConstants.HelpFields.helpId, Integer.valueOf(id));
/*      */ 
/*      */           
/*  605 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.helps);
/*  606 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.HelpFields.helpId, id);
/*  607 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.HelpFields.documentName, docName);
/*  608 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.HelpFields.documentTitle, docTitle);
/*  609 */           connection.addFieldText(doc, (Enum)ILocalizationConstants.HelpFields.documentBody, docBody);
/*  610 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.HelpFields.documentType, docType);
/*  611 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.HelpFields.time, serverTime);
/*  612 */           connection.add("_misc_", doc);
/*      */           
/*  614 */           written++;
/*  615 */           count++;
/*  616 */           if (logBuffer.length() > 0) {
/*  617 */             logBuffer.append(", ");
/*      */           }
/*  619 */           logBuffer.append(id);
/*      */           
/*  621 */           if (written % 50 == 0) {
/*      */             
/*  623 */             if (m_writeResLog)
/*  624 */               log("Finished synchronization of help documents : \"" + logBuffer.toString() + "\""); 
/*  625 */             connection.commit();
/*  626 */             logBuffer = new StringBuilder();
/*      */           } 
/*      */         } 
/*  629 */         if (written > 0) {
/*      */           
/*  631 */           if (m_writeResLog)
/*  632 */             log("Finished synchronization of help documents : \"" + logBuffer.toString() + "\""); 
/*  633 */           connection.commit();
/*      */         } 
/*  635 */         log("Synchronization completed for path " + connection.getPath() + " for help documents (" + count + " items) : " + 
/*  636 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       }
/*  638 */       catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  642 */           connection.rollback();
/*      */         }
/*  644 */         catch (IOException e) {
/*      */           
/*  646 */           e.printStackTrace();
/*      */         } 
/*  648 */         e1.printStackTrace();
/*  649 */         log("Error during synchronizing help documents!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  655 */           resources.close();
/*      */         }
/*  657 */         catch (SQLException e) {
/*      */           
/*  659 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void synchronizeMessages(LuceneConnection connection, String lastSynchTime, Timestamp serverTime) {
/*  667 */     log("Synchronization started for path " + connection.getPath() + " messages : " + 
/*  668 */         JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  669 */     ResultSet resources = executeQuery(this.m_ResourceDBConnection, 
/*  670 */         "SELECT M.ID, M.CONS_ID, M.MODULE, M.MTYPE, M.LISTID, M.STRTAG, M.RESGROUP, M.BUTTONS, M.DEF_BUTTON, M.MODIFIEDON FROM RE_MESSAGES M WHERE M.MODIFIEDON > '%synchTime%' ORDER BY M.ID".replaceAll("%synchTime%", lastSynchTime));
/*      */     
/*  672 */     StringBuilder logBuffer = new StringBuilder();
/*  673 */     if (resources != null) {
/*      */       
/*      */       try {
/*      */         
/*  677 */         int written = 0, count = 0;
/*  678 */         while (resources.next()) {
/*      */           
/*  680 */           int key = resources.getInt("ID");
/*  681 */           String id = resources.getString("CONS_ID");
/*  682 */           String module = resources.getString("MODULE");
/*  683 */           int type = resources.getInt("MTYPE");
/*  684 */           int listId = resources.getInt("LISTID");
/*  685 */           int strTag = resources.getInt("STRTAG");
/*  686 */           String resGroup = resources.getString("RESGROUP");
/*  687 */           int buttons = resources.getInt("BUTTONS");
/*  688 */           int defButton = resources.getInt("DEF_BUTTON");
/*      */ 
/*      */           
/*  691 */           connection.deleteDocuments("_misc_", (Enum)ILocalizationConstants.Catalogs.messages, (Enum)ILocalizationConstants.MessageFields.key, Integer.valueOf(key));
/*      */ 
/*      */           
/*  694 */           Document doc = connection.createDocument((Enum)ILocalizationConstants.Catalogs.messages);
/*  695 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.key, key);
/*  696 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.constantId, id);
/*  697 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.module, module);
/*  698 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.type, type);
/*  699 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.listId, listId);
/*  700 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.stringTag, strTag);
/*  701 */           connection.addFieldString(doc, (Enum)ILocalizationConstants.MessageFields.resourceGroup, resGroup);
/*  702 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.buttons, buttons);
/*  703 */           connection.addFieldInt(doc, (Enum)ILocalizationConstants.MessageFields.defaultButton, defButton);
/*  704 */           connection.addFieldTimestamp(doc, (Enum)ILocalizationConstants.MessageFields.time, serverTime);
/*  705 */           connection.add("_misc_", doc);
/*      */           
/*  707 */           written++;
/*  708 */           count++;
/*  709 */           if (logBuffer.length() > 0)
/*  710 */             logBuffer.append(", "); 
/*  711 */           logBuffer.append(id);
/*      */           
/*  713 */           if (written % 50 == 0) {
/*      */             
/*  715 */             if (m_writeResLog)
/*  716 */               log("Finished synchronization of messages : \"" + logBuffer.toString() + "\""); 
/*  717 */             logBuffer = new StringBuilder();
/*  718 */             written = 0;
/*  719 */             connection.commit();
/*      */           } 
/*      */         } 
/*  722 */         if (written > 0) {
/*      */           
/*  724 */           if (m_writeResLog)
/*  725 */             log("Finished synchronization of messages : \"" + logBuffer.toString() + "\""); 
/*  726 */           connection.commit();
/*      */         } 
/*  728 */         log("Synchronization completed for path " + connection.getPath() + " for messages (" + count + " items) : " + 
/*  729 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       }
/*  731 */       catch (Exception e1) {
/*      */ 
/*      */         
/*      */         try {
/*  735 */           connection.rollback();
/*      */         }
/*  737 */         catch (IOException e) {
/*      */           
/*  739 */           e.printStackTrace();
/*      */         } 
/*  741 */         e1.printStackTrace();
/*  742 */         log("Error during synchronizing messages!");
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  748 */           resources.close();
/*      */         }
/*  750 */         catch (SQLException e) {
/*      */           
/*  752 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void log(String message) {
/*  760 */     System.out.println(message);
/*      */   }
/*      */ 
/*      */   
/*      */   private String addPreferedTableOptions(String tableName, String resItemSQLStmt) {
/*  765 */     String[] replacementTableList = getPreferedReplacementTableList(tableName);
/*      */     
/*  767 */     if (replacementTableList != null && replacementTableList.length >= 2)
/*      */     {
/*      */       
/*  770 */       if (replacementTableList[0] != null && !replacementTableList[0].equals(TABLE_NAME_NOSELECT) && 
/*  771 */         replacementTableList[1] != null && !replacementTableList[1].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/*  773 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable1%.RESOURCESTR, ISNULL(%preftable2%.RESOURCESTR, ''))");
/*  774 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", 
/*  775 */             "LEFT OUTER JOIN %preftable1% ON (RI.ID = %preftable1%.RESOURCEITEMREF) LEFT OUTER JOIN %preftable2% ON (RI.ID = %preftable2%.RESOURCEITEMREF) ");
/*  776 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable1%", replacementTableList[0]);
/*  777 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable2%", replacementTableList[1]);
/*      */       
/*      */       }
/*  780 */       else if (replacementTableList[0] != null && !replacementTableList[0].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/*  782 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable1%.RESOURCESTR, '')");
/*  783 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "LEFT OUTER JOIN %preftable1% ON (RI.ID = %preftable1%.RESOURCEITEMREF) ");
/*  784 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable1%", replacementTableList[0]);
/*      */       
/*      */       }
/*  787 */       else if (replacementTableList[1] != null && !replacementTableList[1].equals(TABLE_NAME_NOSELECT)) {
/*      */         
/*  789 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "ISNULL(%preftable2%.RESOURCESTR, '')");
/*  790 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "LEFT OUTER JOIN %preftable2% ON (RI.ID = %preftable2%.RESOURCEITEMREF) ");
/*  791 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%preftable2%", replacementTableList[1]);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  796 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefs%", "''");
/*  797 */         resItemSQLStmt = resItemSQLStmt.replaceAll("%ReplaceTablePrefsJoin%", "");
/*      */       } 
/*      */     }
/*      */     
/*  801 */     return resItemSQLStmt;
/*      */   }
/*      */ 
/*      */   
/*      */   private String[] getPreferedReplacementTableList(String tableName) {
/*  806 */     String[] preferred = null;
/*  807 */     if (!JLbsStringUtil.isEmpty(this.m_Lang))
/*  808 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(String.valueOf(tableName) + "_" + this.m_Lang); 
/*  809 */     if (preferred == null)
/*  810 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(tableName); 
/*  811 */     if (preferred == null)
/*  812 */       preferred = (String[])JLbsLocalizationUtil.ms_PrefLangMaps.get(""); 
/*  813 */     return preferred;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ResultSet executeQuery(Connection connection, String sqlStatement) {
/*      */     ResultSet resultSet;
/*      */     try {
/*  822 */       Statement stmt = connection.createStatement();
/*  823 */       resultSet = stmt.executeQuery(sqlStatement);
/*      */     }
/*  825 */     catch (Exception e) {
/*      */       
/*  827 */       resultSet = null;
/*  828 */       System.err.println("Problem statement : " + sqlStatement);
/*  829 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  832 */     return resultSet;
/*      */   }
/*      */ 
/*      */   
/*      */   private void importLangSpecificResources(String dbCreationDir, String dbIdentifier, String[] groupList) {
/*  837 */     if (this.m_ResourceDBConnection != null) {
/*      */       
/*  839 */       this.m_Lang = dbIdentifier;
/*  840 */       readLocalizationConfiguration(dbCreationDir);
/*  841 */       Object objTableName = JLbsLocalizationUtil.ms_LangMaps.get(dbIdentifier);
/*  842 */       if (objTableName != null && objTableName instanceof String) {
/*      */         
/*  844 */         LuceneConnection connection = new LuceneConnection(dbCreationDir, dbIdentifier);
/*  845 */         if (connection != null)
/*      */         {
/*  847 */           if (ensureLuceneIndexes(connection)) {
/*      */ 
/*      */             
/*      */             try {
/*  851 */               Timestamp serverTime = getSqlServerTime();
/*  852 */               log("Import started for path " + connection.getPath() + " : " + 
/*  853 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  854 */               importMessages(connection, serverTime);
/*  855 */               for (int i = 0; i < groupList.length; i++) {
/*      */                 
/*  857 */                 if (this.m_ResourceList == null || this.m_ResourceList.get(groupList[i]) != null)
/*      */                 {
/*  859 */                   if (!JLbsStringUtil.isEmpty(groupList[i]))
/*  860 */                     importResources(connection, (String)objTableName, groupList[i], serverTime);  } 
/*      */               } 
/*  862 */               importHelpDocuments(connection, serverTime);
/*      */               
/*  864 */               log("Import completed for path " + connection.getPath() + " : " + 
/*  865 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*  866 */               connection.close();
/*  867 */               String outputPath = JLbsFileUtil.appendPath(dbCreationDir, String.valueOf(dbIdentifier) + ".zip");
/*  868 */               compressDBFolder(dbCreationDir, dbIdentifier, outputPath, serverTime);
/*  869 */               log("Compress operation completed for path " + connection.getPath() + " : " + 
/*  870 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */             }
/*  872 */             catch (Exception e) {
/*      */               
/*  874 */               e.printStackTrace();
/*  875 */               if (this.throwExceptionOnError)
/*      */               {
/*  877 */                 throw new RuntimeException(e);
/*      */               }
/*      */             } 
/*      */           } else {
/*      */             
/*  882 */             log("Embedded database tables could not be created!");
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void synchronizeLangSpecificResources(String outputDir, String dbFilePath, String dbIdentifier, String[] groupList) throws Exception {
/*  891 */     if (this.m_ResourceDBConnection != null) {
/*      */       
/*  893 */       readLocalizationConfiguration(outputDir);
/*  894 */       Object objTableName = JLbsLocalizationUtil.ms_LangMaps.get(dbIdentifier);
/*  895 */       if (objTableName != null && objTableName instanceof String) {
/*      */         
/*  897 */         String tmpDir = JLbsFileUtil.getTempDirectory();
/*      */         
/*  899 */         if (!JLbsStringUtil.isEmpty(tmpDir)) {
/*      */           
/*  901 */           String extractionDir = String.valueOf(JLbsFileUtil.ensurePath(tmpDir)) + "EmbeddedDB_" + 
/*  902 */             JLbsDateUtil.dateToString(GregorianCalendar.getInstance()).replaceAll(":", "_");
/*      */           
/*  904 */           extractionDir = String.valueOf(extractionDir) + " " + UUID.randomUUID().toString();
/*      */           
/*  906 */           String path = String.valueOf(extractionDir) + "/" + dbIdentifier;
/*  907 */           log("Synchronization started for path " + path + " : " + 
/*  908 */               JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */ 
/*      */           
/*      */           try {
/*  912 */             extractDBFile(dbFilePath, extractionDir);
/*      */             
/*  914 */             LuceneConnection luceneConnection = new LuceneConnection(extractionDir, dbIdentifier);
/*  915 */             if (luceneConnection != null)
/*      */             {
/*      */               
/*  918 */               Timestamp lastSynchTime = getLastSynchronizationTime(luceneConnection);
/*  919 */               Timestamp serverTime = getSqlServerTime();
/*      */               
/*  921 */               for (int i = 0; i < groupList.length; i++) {
/*      */                 
/*  923 */                 if (this.m_ResourceList == null || this.m_ResourceList.get(groupList[i]) != null)
/*      */                 {
/*  925 */                   if (!JLbsStringUtil.isEmpty(groupList[i]))
/*  926 */                     synchronizeResources(luceneConnection, (String)objTableName, groupList[i], 
/*  927 */                         lastSynchTime.toString(), serverTime);  } 
/*      */               } 
/*  929 */               synchronizeHelpDocuments(luceneConnection, lastSynchTime.toString(), serverTime);
/*  930 */               synchronizeMessages(luceneConnection, lastSynchTime.toString(), serverTime);
/*      */               
/*  932 */               luceneConnection.close();
/*  933 */               log("Synchronization completed for path " + path + " : " + 
/*  934 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */               
/*  936 */               compressDBFolder(extractionDir, dbIdentifier, dbFilePath, serverTime);
/*  937 */               luceneConnection = null;
/*  938 */               log("Compress operation completed for path " + path + " : " + 
/*  939 */                   JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */             }
/*      */           
/*      */           } finally {
/*      */             
/*  944 */             JLbsFileUtil.deleteDirectory(extractionDir);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void extractDBFile(String dbFileDir, String extractionDir) {
/*  953 */     Expand expand = new Expand();
/*      */     
/*  955 */     File extractionFolder = new File(extractionDir);
/*  956 */     extractionFolder.mkdirs();
/*      */     
/*  958 */     File dbFile = new File(dbFileDir);
/*  959 */     String oldFileName = dbFileDir.substring(0, dbFileDir.lastIndexOf(".zip"));
/*  960 */     oldFileName = String.valueOf(oldFileName) + ".old.zip";
/*  961 */     File oldZipFile = new File(oldFileName);
/*  962 */     if (dbFile.exists()) {
/*      */       
/*  964 */       expand.setSrc(dbFile);
/*  965 */       expand.setDest(extractionFolder);
/*  966 */       expand.setProject(new Project());
/*  967 */       expand.execute();
/*      */       
/*  969 */       if (oldZipFile.exists()) {
/*  970 */         oldZipFile.delete();
/*      */       }
/*  972 */     } else if (oldZipFile.exists()) {
/*      */       
/*  974 */       expand.setSrc(oldZipFile);
/*  975 */       expand.setDest(extractionFolder);
/*  976 */       expand.setProject(new Project());
/*  977 */       expand.execute();
/*      */     } else {
/*      */       
/*  980 */       throw new RuntimeException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void compressDBFolder(String dbCreationDir, String dbIdentifier, String outputPath, Timestamp serverTime) throws IOException {
/*  986 */     File dbCreationFolder = new File(dbCreationDir);
/*  987 */     String newFileName = outputPath.substring(0, outputPath.lastIndexOf(".zip"));
/*  988 */     newFileName = String.valueOf(newFileName) + ".new.zip";
/*  989 */     File zipFile = new File(newFileName);
/*      */     
/*  991 */     if (zipFile.exists()) {
/*  992 */       zipFile.delete();
/*      */     }
/*  994 */     (new File(String.valueOf(dbCreationFolder.toString()) + "/" + dbIdentifier)).mkdirs();
/*      */     
/*  996 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  999 */       fos = new FileOutputStream(String.valueOf(dbCreationFolder.toString()) + "/" + dbIdentifier + "/synctime.txt");
/* 1000 */       fos.write(String.valueOf(serverTime.getTime()).getBytes());
/*      */     }
/*      */     finally {
/*      */       
/* 1004 */       if (fos != null) {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 1010 */           fos.flush();
/*      */         }
/* 1012 */         catch (IOException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1017 */           e.printStackTrace();
/*      */         } 
/*      */         
/*      */         try {
/* 1021 */           fos.close();
/*      */         }
/* 1023 */         catch (IOException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1028 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1037 */     Zip zip = new Zip();
/* 1038 */     zip.setBasedir(dbCreationFolder);
/* 1039 */     zip.setIncludes(String.valueOf(dbIdentifier) + "/**");
/* 1040 */     zip.setDestFile(zipFile);
/* 1041 */     zip.setProject(new Project());
/*      */     
/* 1043 */     zip.execute();
/*      */     
/* 1045 */     File oldZipFile = new File(outputPath);
/* 1046 */     if (oldZipFile.exists()) {
/*      */       
/* 1048 */       String oldFileName = outputPath.substring(0, outputPath.lastIndexOf(".zip"));
/* 1049 */       oldFileName = String.valueOf(oldFileName) + ".old.zip";
/* 1050 */       File zipOldFile = new File(oldFileName);
/* 1051 */       if (zipOldFile.exists())
/* 1052 */         zipOldFile.delete(); 
/* 1053 */       if (!oldZipFile.renameTo(zipOldFile)) {
/*      */         
/* 1055 */         zip.setDestFile(oldZipFile);
/* 1056 */         zip.execute();
/* 1057 */         zipFile.delete();
/*      */       } 
/*      */     } 
/*      */     
/* 1061 */     if (zipFile.exists()) {
/* 1062 */       zipFile.renameTo(new File(outputPath));
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
/*      */   public static void main(String[] args) throws Exception {
/* 1081 */     String a = "filename";
/* 1082 */     Locale.getDefault();
/* 1083 */     Locale.setDefault(new Locale("tr", "TR"));
/* 1084 */     a.toUpperCase();
/* 1085 */     System.out.println(UUID.randomUUID().toString());
/* 1086 */     if (args.length >= 6) {
/*      */       
/* 1088 */       LuceneLocalizationDBSynchronizer lclSynchronizer = new LuceneLocalizationDBSynchronizer();
/* 1089 */       String[] groupList = { "UN", "HR", "UNRP", "HRRP", 
/* 1090 */           "HELP", "SS" };
/*      */       
/* 1092 */       String serverID = args[0];
/* 1093 */       if (args[1] != null && args[1].startsWith("RESEDIT_P"))
/* 1094 */         groupList = new String[] { "PLATFORM", "GRC", "GRCREP", 
/* 1095 */             "HR", "HRREP", "ERP", "ERPREP", 
/* 1096 */             "SOHO", "SOHOREP" }; 
/* 1097 */       String dbName = args[1];
/* 1098 */       String lang = args[2];
/* 1099 */       String outputDir = args[3];
/* 1100 */       int opType = JLbsConvertUtil.str2IntDef(args[4], -1);
/* 1101 */       String productIds = args[5];
/*      */       
/* 1103 */       JLbsLocalizationDBSynchronizerHelper helper = new JLbsLocalizationDBSynchronizerHelper();
/*      */       
/* 1105 */       String userName = helper.getDecryptedData(DEFAULT_USERNAME);
/* 1106 */       String password = helper.getDecryptedData(DEFAULT_PASSWORD);
/* 1107 */       if (args.length > 7) {
/*      */         
/* 1109 */         userName = args[6];
/* 1110 */         password = args[7];
/*      */       } 
/*      */       
/* 1113 */       String resources = null;
/* 1114 */       if (args.length == 7)
/* 1115 */         resources = args[6]; 
/* 1116 */       if (args.length == 9)
/* 1117 */         resources = args[8]; 
/* 1118 */       if (args.length == 10) {
/*      */         
/* 1120 */         resources = args[8];
/* 1121 */         m_writeResLog = Boolean.valueOf(args[9]).booleanValue();
/*      */       } 
/*      */       
/* 1124 */       boolean throwExceptionOnError = false;
/* 1125 */       if (args.length == 11)
/*      */       {
/* 1127 */         throwExceptionOnError = Boolean.parseBoolean(args[10]);
/*      */       }
/*      */       try {
/*      */         String dbFileDir;
/* 1131 */         lclSynchronizer.initialize(serverID, dbName, userName, password, resources, productIds);
/* 1132 */         lclSynchronizer.setThrowExceptionOnError(throwExceptionOnError);
/*      */         
/* 1134 */         switch (opType) {
/*      */           
/*      */           case 1:
/* 1137 */             lclSynchronizer.importLangSpecificResources(outputDir, lang, groupList);
/*      */             break;
/*      */           
/*      */           case 2:
/* 1141 */             dbFileDir = String.valueOf(outputDir) + File.separator + lang + ".zip";
/*      */             
/*      */             try {
/* 1144 */               lclSynchronizer.synchronizeLangSpecificResources(outputDir, dbFileDir, lang, groupList);
/* 1145 */               extractDBFile(dbFileDir, outputDir);
/*      */             }
/* 1147 */             catch (Exception e) {
/*      */               
/* 1149 */               e.printStackTrace();
/* 1150 */               File zip = null;
/* 1151 */               if ((zip = new File(dbFileDir)).exists())
/*      */               {
/* 1153 */                 if (!zip.delete())
/* 1154 */                   System.err.println("Unable to delete zip file"); 
/*      */               }
/* 1156 */               if (!JLbsFileUtil.deleteDirectory(String.valueOf(outputDir) + File.separator + lang))
/* 1157 */                 System.err.println("Unable to delete folder"); 
/* 1158 */               lclSynchronizer.importLangSpecificResources(outputDir, lang, groupList);
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1166 */       } catch (Exception e) {
/*      */         
/* 1168 */         e.printStackTrace();
/*      */       }
/*      */       finally {
/*      */         
/* 1172 */         if (lclSynchronizer.getResourceDBConnection() != null) {
/* 1173 */           lclSynchronizer.getResourceDBConnection().close();
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 1178 */       System.err.println("Invalid arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void readLocalizationConfiguration(String outputDir) {
/*      */     try {
/* 1185 */       log("Loading " + outputDir + File.separator + ILocalizationConstants.PREF_REPLACE_LANGS_FILE_PATH);
/* 1186 */       LbsLocalizationConfig.load(String.valueOf(outputDir) + File.separator + ILocalizationConstants.PREF_REPLACE_LANGS_FILE_PATH);
/* 1187 */       if (LbsLocalizationConfig.ms_LocalizationLanguages != null)
/*      */       {
/* 1189 */         JLbsLocalizationUtil.ms_LocalizationLanguages = LbsLocalizationConfig.ms_LocalizationLanguages;
/* 1190 */         JLbsLocalizationUtil.extractPrefLangMaps();
/* 1191 */         JLbsLocalizationUtil.extractLangMaps();
/*      */       }
/*      */       else
/*      */       {
/* 1195 */         log("LbsLocalizationConfig.xml can not be found! Continue with default preferences.");
/* 1196 */         JLbsLocalizationUtil.touch();
/*      */       }
/*      */     
/* 1199 */     } catch (Exception e) {
/*      */       
/* 1201 */       e.printStackTrace();
/* 1202 */       log("Exception reading LbsLocalizationConfig.xml! Continue with default preferences.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean ensureLuceneIndexes(LuceneConnection conn) {
/* 1209 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private Timestamp getSqlServerTime() throws SQLException {
/* 1214 */     ResultSet rsServerTime = executeQuery(this.m_ResourceDBConnection, "SELECT GETDATE()");
/*      */     
/*      */     try {
/* 1217 */       rsServerTime.next();
/* 1218 */       Timestamp serverTime = rsServerTime.getTimestamp(1);
/* 1219 */       return serverTime;
/*      */     }
/* 1221 */     catch (SQLException e) {
/*      */       
/* 1223 */       e.printStackTrace();
/* 1224 */       return null;
/*      */     }
/*      */     finally {
/*      */       
/* 1228 */       rsServerTime.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void createEmptyDatabase(String dbCreationDir, String dbIdentifier, String outputPath) throws Exception {
/* 1234 */     LuceneConnection connection = new LuceneConnection(dbCreationDir, dbIdentifier);
/* 1235 */     if (ensureLuceneIndexes(connection)) {
/*      */       
/* 1237 */       System.out.println("DB Create operation started : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/* 1238 */       connection.close();
/* 1239 */       compressDBFolder(dbCreationDir, dbIdentifier, outputPath, new Timestamp(1L));
/* 1240 */       Delete del = new Delete();
/* 1241 */       del.setDir(new File(String.valueOf(dbCreationDir) + "/" + dbIdentifier));
/* 1242 */       if (del.getProject() != null) {
/*      */         
/* 1244 */         del.execute();
/* 1245 */         System.out.println("DB Create operation completed : " + JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */       } 
/*      */     } else {
/*      */       
/* 1249 */       throw new Exception("Database tables/indexes could not be created!");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void importResourceItems(JLbsStringList strList, String tableName, int orgResNumber, int resNumber, String resGroup) {
/* 1254 */     if (strList != null) {
/*      */       
/* 1256 */       String resItemSQLStmt = "SELECT DISTINCT PREFIXSTR = ISNULL(RI.PREFIXSTR, ''),  RESSTR =  CASE \tR.RESOURCETYPE \tWHEN 2 THEN ISNULL(STD.RESOURCESTR COLLATE DATABASE_DEFAULT, '') \tELSE ISNULL(%table%.RESOURCESTR, %ReplaceTablePrefs%) END,  RI.ORDERNR,  RI.TAGNR, RI.OWNERPRODUCT FROM RE_RESOURCES R, RE_RESOURCEITEMS RI LEFT OUTER JOIN RE_STANDARD STD ON (RI.ID = STD.RESOURCEITEMREF) LEFT OUTER JOIN %table% ON (RI.ID = %table%.RESOURCEITEMREF) %ReplaceTablePrefsJoin%WHERE R.RESOURCEGROUP = '%resGrp%' AND R.ID = RI.RESOURCEREF AND R.RESOURCENR = %resNr% ORDER BY RI.ORDERNR".replaceAll("%table%", tableName)
/* 1257 */         .replaceAll("%resNr%", String.valueOf(orgResNumber)).replaceAll("%resGrp%", resGroup);
/* 1258 */       resItemSQLStmt = addPreferedTableOptions(tableName, resItemSQLStmt);
/* 1259 */       ResultSet resourceItems = executeQuery(this.m_ResourceDBConnection, resItemSQLStmt);
/*      */       
/*      */       try {
/* 1262 */         int count = 0;
/* 1263 */         while (resourceItems.next()) {
/*      */ 
/*      */           
/* 1266 */           int tagNr = resourceItems.getInt("TAGNR");
/* 1267 */           String resStr = resourceItems.getString("RESSTR");
/* 1268 */           String resPrefix = resourceItems.getString("PREFIXSTR");
/* 1269 */           String ownerProduct = "," + String.valueOf(resourceItems.getInt("OWNERPRODUCT")) + ",";
/*      */           
/* 1271 */           if (this.m_productIds.indexOf(ownerProduct) > -1) {
/*      */             
/* 1273 */             JLbsStringListItem item = new JLbsStringListItem();
/* 1274 */             item.setTag(tagNr);
/* 1275 */             item.setValue(
/*      */                 
/* 1277 */                 String.valueOf((resPrefix != null && resPrefix.length() == 0) ? "" : resPrefix) + resStr);
/* 1278 */             strList.addItem(item);
/*      */             
/* 1280 */             count++;
/*      */           } 
/*      */         } 
/*      */         
/* 1284 */         if (m_writeResLog) {
/* 1285 */           log("Synchronization completed for resource items of " + resNumber + " (" + count + " items) : " + 
/* 1286 */               JLbsDateUtil.dateToString(GregorianCalendar.getInstance()));
/*      */         }
/* 1288 */       } catch (Exception e) {
/*      */         
/* 1290 */         e.printStackTrace();
/*      */       } finally {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1296 */           resourceItems.close();
/*      */         }
/* 1298 */         catch (SQLException e) {
/*      */           
/* 1300 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setThrowExceptionOnError(boolean throwExceptionOnError) {
/* 1308 */     this.throwExceptionOnError = throwExceptionOnError;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setSynchTimeDiff(long diff) {
/* 1313 */     synchTimeDiff = diff;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setWriteResLog(boolean m_writeResLog) {
/* 1318 */     LuceneLocalizationDBSynchronizer.m_writeResLog = m_writeResLog;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\LuceneLocalizationDBSynchronizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */