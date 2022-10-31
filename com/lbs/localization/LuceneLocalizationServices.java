/*      */ package com.lbs.localization;
/*      */ 
/*      */ import com.lbs.controls.ILogger;
/*      */ import com.lbs.controls.JLbsComponentHelper;
/*      */ import com.lbs.customization.CustomizationGUID;
/*      */ import com.lbs.globalization.JLbsCultureInfoBase;
/*      */ import com.lbs.message.JLbsInfoMessage;
/*      */ import com.lbs.message.JLbsMessResource;
/*      */ import com.lbs.message.JLbsMessage;
/*      */ import com.lbs.message.JLbsSelectionMessage;
/*      */ import com.lbs.message.JLbsWarningMessage;
/*      */ import com.lbs.message.JlbsErrorMessage;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsFileUtil;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringListItem;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Timer;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.apache.lucene.document.Document;
/*      */ import org.apache.lucene.document.IntPoint;
/*      */ import org.apache.lucene.index.Term;
/*      */ import org.apache.lucene.search.BooleanClause;
/*      */ import org.apache.lucene.search.BooleanQuery;
/*      */ import org.apache.lucene.search.Query;
/*      */ import org.apache.lucene.search.TermQuery;
/*      */ import org.apache.lucene.util.BytesRefBuilder;
/*      */ import org.apache.lucene.util.NumericUtils;
/*      */ import org.apache.tools.ant.util.FileUtils;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LuceneLocalizationServices
/*      */   implements ILocalizationServices, ILocalizationConstants
/*      */ {
/*   67 */   public static String LANG_NAME_DEFAULT = "ENUS";
/*      */   
/*      */   public static boolean TRACE_FORM_LOCALIZATION = false;
/*      */   
/*      */   private static Hashtable<String, Integer> ms_FormNames;
/*      */   
/*   73 */   private static int ms_FormCounter = 0;
/*      */   
/*      */   private static Hashtable<Integer, ArrayList<Integer>> ms_UtilizedListsUN;
/*      */   
/*      */   private static Hashtable<Integer, ArrayList<Integer>> ms_UtilizedListsHR;
/*      */   
/*      */   protected static Hashtable<String, LuceneConnection> ms_LuceneConnections;
/*      */   
/*      */   private static Hashtable<String, String> ms_FailedLanguages;
/*      */   
/*      */   private static ArrayList<String> ms_SupportedLanguages;
/*      */   
/*      */   private static ArrayList<Integer> ms_ResourceProducts;
/*      */   
/*      */   private String m_LocalizationDBDirectory;
/*      */   
/*      */   protected static Timer ms_Timer;
/*   90 */   public static long TIME_OUT_CHECK_INTERVAL_IN_MILLIS = 5400000L;
/*   91 */   public static long TIME_OUT_INTERVAL_IN_MILLIS = 3600000L;
/*      */ 
/*      */   
/*   94 */   public static int LRU_RESOURCE_CACHE_SIZE = 200;
/*   95 */   public static int LRU_RESOURCE_CACHE_LIST_CONTENT_LENGTH = 100;
/*      */   
/*      */   public static boolean CACHE_LRU_RESOURCE_CONTENT = true;
/*      */   
/*      */   protected Hashtable<String, Map<Integer, JLbsStringList>> ms_LRUListCaches;
/*      */   protected String m_Language;
/*  101 */   protected String m_DbVersion = null;
/*      */   
/*      */   private LuceneConnection reservedConnection;
/*      */   
/*  105 */   protected static ILogger ms_Logger = null;
/*      */   
/*  107 */   private String m_ConnectionPrefix = null;
/*      */ 
/*      */   
/*      */   public LuceneConnection getReservedConnection() {
/*  111 */     return this.reservedConnection; }
/*      */   public LuceneLocalizationServices() { this.m_Language = LANG_NAME_DEFAULT; this.m_DbVersion = null; this.ms_LRUListCaches = new Hashtable<>(); initializeLogger(); }
/*      */   public LuceneLocalizationServices(String langPrefix) { this(); if (!JLbsStringUtil.isEmpty(langPrefix)) this.m_Language = langPrefix;  }
/*      */   public LuceneLocalizationServices(String langPrefix, String dbVersion) { this(); if (!JLbsStringUtil.isEmpty(langPrefix))
/*      */       this.m_Language = langPrefix;  if (!JLbsStringUtil.isEmpty(dbVersion))
/*  116 */       this.m_DbVersion = dbVersion;  } public void setReservedConnection(LuceneConnection reservedConnection) { this.reservedConnection = reservedConnection; }
/*      */   public LuceneLocalizationServices(String langPrefix, String dbVersion, String connectionPrefix) { this(); if (!JLbsStringUtil.isEmpty(langPrefix)) this.m_Language = langPrefix;  if (!JLbsStringUtil.isEmpty(dbVersion))
/*      */       this.m_DbVersion = dbVersion;  if (!JLbsStringUtil.isEmpty(connectionPrefix))
/*      */       this.m_ConnectionPrefix = connectionPrefix;  }
/*      */   protected synchronized void initializeLogger() { if (ms_Logger == null)
/*  121 */       ms_Logger = JLbsComponentHelper.getLogger(LuceneLocalizationServices.class.getName());  } static { ms_LuceneConnections = new Hashtable<>();
/*  122 */     ms_FailedLanguages = new Hashtable<>();
/*  123 */     ms_SupportedLanguages = new ArrayList<>();
/*  124 */     initializeTraceLists();
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
/*  271 */     disableOracleLogging(); }
/*      */   public JLbsCultureInfoBase getCulture() throws Exception { if (JLbsStringUtil.isEmpty(this.m_Language))
/*      */       this.m_Language = LANG_NAME_DEFAULT; 
/*      */     String className = "com.lbs.globalization.JLbsCultureInfo" + this.m_Language;
/*      */     return (JLbsCultureInfoBase)Class.forName(className).newInstance(); }
/*      */   protected String getLocalizationDBDirectory() { return this.m_LocalizationDBDirectory; }
/*      */   protected String getSListDirectory(String langPrefix) { return null; }
/*      */   public String getItem(int listID, int stringTag) { return getItem(this.m_Language, listID, stringTag, "UN"); }
/*  279 */   public static void disableOracleLogging() { Logger logger = Logger.getLogger("oracle.jdbc");
/*  280 */     logger.setLevel(Level.OFF); } public String getItem(String langPrefix, int listID, int stringTag) { return getItem(langPrefix, listID, stringTag, "UN"); }
/*      */   public String getItem(int listID, int stringTag, String group) { return getItem(this.m_Language, listID, stringTag, group); }
/*      */   public String getItem(String langPrefix, int listID, int stringTag, String group) { return getItem(null, langPrefix, listID, stringTag, group); }
/*      */   public JLbsStringList getList(int listID, int[] tagList) { JLbsStringList list = getList(this.m_Language, listID, "UN"); JLbsStringList newList = new JLbsStringList(); for (int i = 0; i < tagList.length; i++) { if (list.getValueAtTag(tagList[i]) != null)
/*      */         newList.add(list.getValueAtTag(tagList[i]), tagList[i]);  }
/*      */      return newList; }
/*      */   public JLbsStringList getList(int listID) { return getList(this.m_Language, listID, "UN"); }
/*  287 */   private static void initializeDriver() { try { Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); }
/*      */     
/*  289 */     catch (ClassNotFoundException classNotFoundException) {} } public JLbsStringList getList(String langPrefix, int listID) { return getList(langPrefix, listID, "UN"); }
/*      */   public JLbsStringList getList(int listID, String group) { return getList(this.m_Language, listID, group); }
/*      */   public JLbsStringList getList(int listID, String group, String formName) { return getList(this.m_Language, listID, group, formName); }
/*      */   private JLbsStringList getList(String langPrefix, int listID, String group, String formName) { JLbsStringList list = getList(langPrefix, listID, group); if (list != null && TRACE_FORM_LOCALIZATION)
/*      */       traceList(listID, group, formName); 
/*      */     return list; }
/*      */   public static void initializeTraceLists() { ms_FormNames = new Hashtable<>();
/*      */     ms_UtilizedListsUN = new Hashtable<>();
/*      */     ms_UtilizedListsHR = new Hashtable<>(); }
/*  298 */   public static void saveTraceContent(String mainResourcePath) { if (TRACE_FORM_LOCALIZATION) {
/*      */       
/*  300 */       File mainResouceDir = new File(mainResourcePath);
/*  301 */       mainResouceDir.mkdir();
/*      */ 
/*      */       
/*      */       try {
/*  305 */         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  306 */         DocumentBuilder parser = factory.newDocumentBuilder();
/*  307 */         Document document = parser.newDocument();
/*      */         
/*  309 */         Element rootElement = document.createElement("LocalizationTrace");
/*  310 */         rootElement.appendChild(createFormsElement(document));
/*  311 */         rootElement.appendChild(createGroupsElement(document));
/*  312 */         document.appendChild(rootElement);
/*      */ 
/*      */         
/*  315 */         Source source = new DOMSource(document);
/*  316 */         File file = new File(JLbsFileUtil.appendPath(mainResourcePath, "LocalizationTrace.xml"));
/*  317 */         Result result = new StreamResult(file);
/*  318 */         Transformer xformer = TransformerFactory.newInstance().newTransformer();
/*  319 */         xformer.transform(source, result);
/*      */       }
/*  321 */       catch (Exception e) {
/*      */         
/*  323 */         e.printStackTrace();
/*      */       } 
/*      */     }  }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Element createGroupsElement(Document document) {
/*  330 */     Element groupsElement = document.createElement("Groups");
/*  331 */     groupsElement.appendChild(createUNGroupElement(document));
/*  332 */     groupsElement.appendChild(createHRGroupElement(document));
/*  333 */     return groupsElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Element createUNGroupElement(Document document) {
/*  338 */     Element unGroupElement = document.createElement("UN");
/*      */     
/*  340 */     Enumeration<Integer> enumLists = ms_UtilizedListsUN.keys();
/*      */     
/*  342 */     if (enumLists != null)
/*      */     {
/*  344 */       while (enumLists.hasMoreElements()) {
/*      */         
/*  346 */         Object listID = enumLists.nextElement();
/*  347 */         if (listID instanceof Integer) {
/*      */           
/*  349 */           Object<Integer> usedForms = (Object<Integer>)ms_UtilizedListsUN.get(listID);
/*  350 */           if (usedForms instanceof ArrayList) {
/*  351 */             unGroupElement
/*  352 */               .appendChild(createListElement(document, ((Integer)listID).intValue(), (ArrayList)usedForms));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  357 */     return unGroupElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Element createHRGroupElement(Document document) {
/*  362 */     Element hrGroupElement = document.createElement("HR");
/*      */     
/*  364 */     Enumeration<Integer> enumLists = ms_UtilizedListsHR.keys();
/*      */     
/*  366 */     if (enumLists != null)
/*      */     {
/*  368 */       while (enumLists.hasMoreElements()) {
/*      */         
/*  370 */         Object listID = enumLists.nextElement();
/*  371 */         if (listID instanceof Integer) {
/*      */           
/*  373 */           Object<Integer> usedForms = (Object<Integer>)ms_UtilizedListsHR.get(listID);
/*  374 */           if (usedForms instanceof ArrayList) {
/*  375 */             hrGroupElement
/*  376 */               .appendChild(createListElement(document, ((Integer)listID).intValue(), (ArrayList)usedForms));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  381 */     return hrGroupElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Element createListElement(Document document, int listID, ArrayList usedForms) {
/*  386 */     Element listElement = document.createElement("List");
/*  387 */     listElement.setAttribute("id", String.valueOf(listID));
/*  388 */     listElement.setAttribute("usedForms", usedForms.toString());
/*  389 */     return listElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Element createFormsElement(Document document) {
/*  394 */     Element formsElement = document.createElement("Forms");
/*      */     
/*  396 */     Enumeration<String> enumForms = ms_FormNames.keys();
/*      */     
/*  398 */     if (enumForms != null)
/*      */     {
/*  400 */       while (enumForms.hasMoreElements()) {
/*      */         
/*  402 */         Object formName = enumForms.nextElement();
/*  403 */         if (formName instanceof String) {
/*      */           
/*  405 */           Object formID = ms_FormNames.get(formName);
/*  406 */           if (formID instanceof Integer) {
/*  407 */             formsElement.appendChild(createFormElement(document, (String)formName, ((Integer)formID).intValue()));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  412 */     return formsElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Element createFormElement(Document document, String formName, int formID) {
/*  417 */     Element formElement = document.createElement("Form");
/*  418 */     formElement.setAttribute("name", formName);
/*  419 */     formElement.setAttribute("id", String.valueOf(formID));
/*  420 */     return formElement;
/*      */   }
/*      */ 
/*      */   
/*      */   private void traceList(int listID, String group, String formName) {
/*  425 */     if (group.equalsIgnoreCase("UN")) {
/*  426 */       addToUNTraceList(listID, formName);
/*  427 */     } else if (group.equalsIgnoreCase("HR")) {
/*  428 */       addToHRTraceList(listID, formName);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void addToUNTraceList(int listID, String formName) {
/*  433 */     Integer formID = getFormID(formName);
/*  434 */     if (formID != null)
/*      */     {
/*  436 */       if (ms_UtilizedListsUN.containsKey(Integer.valueOf(listID))) {
/*      */         
/*  438 */         Object<Integer> formList = (Object<Integer>)ms_UtilizedListsUN.get(Integer.valueOf(listID));
/*  439 */         if (formList instanceof ArrayList && !((ArrayList)formList).contains(formID)) {
/*  440 */           ((ArrayList<Integer>)formList).add(formID);
/*      */         }
/*      */       } else {
/*      */         
/*  444 */         ArrayList<Integer> formList = new ArrayList<>();
/*  445 */         formList.add(formID);
/*  446 */         ms_UtilizedListsUN.put(Integer.valueOf(listID), formList);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void addToHRTraceList(int listID, String formName) {
/*  453 */     Integer formID = getFormID(formName);
/*  454 */     if (formID != null)
/*      */     {
/*  456 */       if (ms_UtilizedListsHR.containsKey(Integer.valueOf(listID))) {
/*      */         
/*  458 */         Object<Integer> formList = (Object<Integer>)ms_UtilizedListsHR.get(Integer.valueOf(listID));
/*  459 */         if (formList instanceof ArrayList && !((ArrayList)formList).contains(formID)) {
/*  460 */           ((ArrayList<Integer>)formList).add(formID);
/*      */         }
/*      */       } else {
/*      */         
/*  464 */         ArrayList<Integer> formList = new ArrayList<>();
/*  465 */         formList.add(formID);
/*  466 */         ms_UtilizedListsHR.put(Integer.valueOf(listID), formList);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private Integer getFormID(String formName) {
/*      */     Integer formID;
/*  474 */     if (ms_FormNames.containsKey(formName)) {
/*  475 */       formID = ms_FormNames.get(formName);
/*      */     } else {
/*      */       
/*  478 */       formID = Integer.valueOf(++ms_FormCounter);
/*  479 */       ms_FormNames.put(formName, formID);
/*      */     } 
/*      */     
/*  482 */     return formID;
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(String langPrefix, Integer listID, String group) {
/*  487 */     return getList(langPrefix, listID.intValue(), group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(String langPrefix, int listID, String group) throws RuntimeException {
/*  493 */     return getList((CustomizationGUID)null, langPrefix, listID, group);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isInconsistent(String langPrefix, JLbsStringList list) {
/*  498 */     if ((list == null || list.size() == 0) && !JLbsStringUtil.isEmpty(langPrefix) && 
/*  499 */       !langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT))
/*  500 */       return true; 
/*  501 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean replaceListItems(String langPrefix) {
/*  506 */     if (!JLbsStringUtil.isEmpty(langPrefix) && !langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT))
/*  507 */       return true; 
/*  508 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void traverseStringListForItemReplacement(CustomizationGUID custGUID, JLbsStringList originalList, int listID, String group) {
/*  514 */     if (originalList != null) {
/*      */       
/*  516 */       JLbsStringList replacementList = null;
/*  517 */       for (int i = 0; i < originalList.size(); i++) {
/*      */         
/*  519 */         JLbsStringListItem originalListItem = originalList.getItemAt(i);
/*  520 */         if (JLbsStringUtil.isEmpty(originalListItem.getValue())) {
/*      */           
/*  522 */           if (replacementList == null) {
/*  523 */             replacementList = getList(custGUID, LANG_NAME_DEFAULT, listID, group);
/*      */           }
/*  525 */           if (replacementList != null) {
/*      */             
/*  527 */             JLbsStringListItem replacementItem = replacementList.getItem(originalListItem.getTag());
/*  528 */             if (replacementItem != null) {
/*  529 */               originalListItem.setValue(replacementItem.getValue());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getHashKeyForLanguage(String langPrefix, CustomizationGUID custGUID) {
/*  538 */     String custExtension = "";
/*      */     
/*  540 */     if (custGUID != null && !JLbsStringUtil.isEmpty(custGUID.getIdentifier())) {
/*  541 */       custExtension = custGUID.getIdentifier();
/*      */     }
/*  543 */     String dbDirectoryExtension = JLbsStringUtil.isEmpty(this.m_LocalizationDBDirectory) ? 
/*  544 */       "" : 
/*  545 */       this.m_LocalizationDBDirectory;
/*      */     
/*  547 */     return String.valueOf(langPrefix) + "_" + dbDirectoryExtension + "_" + custExtension;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LuceneConnection getLuceneConnection(String langPrefix, CustomizationGUID custGUID, boolean forManagement) throws RuntimeException {
/*  553 */     if (this.reservedConnection != null)
/*  554 */       return this.reservedConnection; 
/*  555 */     if (JLbsStringUtil.isEmpty(langPrefix))
/*  556 */       langPrefix = LANG_NAME_DEFAULT; 
/*  557 */     if (ms_FailedLanguages.containsKey(getHashKeyForLanguage(langPrefix, custGUID))) {
/*      */       
/*  559 */       ms_Logger.error("Embedded connection for language '" + langPrefix + "' is in failed languages list, returning null!");
/*  560 */       return null;
/*      */     } 
/*      */     
/*  563 */     LuceneConnection luceneConnection = null;
/*  564 */     String poolKey = getPoolKey(langPrefix, custGUID);
/*  565 */     if (ms_LuceneConnections.get(poolKey) == null) {
/*      */       
/*  567 */       ms_Logger.debug("Creating new lucene connection for language/customization : " + poolKey);
/*  568 */       luceneConnection = internalGetConnection(langPrefix, custGUID, forManagement);
/*  569 */       synchronized (ms_LuceneConnections) {
/*      */         
/*  571 */         ms_LuceneConnections.put(poolKey, luceneConnection);
/*      */       } 
/*      */     } else {
/*      */       
/*  575 */       luceneConnection = ms_LuceneConnections.get(poolKey);
/*      */     } 
/*  577 */     if (luceneConnection == null) {
/*      */       
/*  579 */       ms_Logger.trace("embedded connection is null!!!!");
/*  580 */       return null;
/*      */     } 
/*      */     
/*  583 */     return luceneConnection;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getPoolKey(String langPrefix, CustomizationGUID custGUID) {
/*  588 */     StringBuilder buffer = new StringBuilder();
/*  589 */     buffer.append(langPrefix);
/*  590 */     buffer.append((custGUID != null) ? (
/*  591 */         "." + custGUID.getIdentifier()) : 
/*  592 */         "");
/*  593 */     buffer.append((this.m_DbVersion != null) ? (
/*  594 */         "." + this.m_DbVersion) : 
/*  595 */         "");
/*  596 */     buffer.append((this.m_ConnectionPrefix != null) ? (
/*  597 */         "." + this.m_ConnectionPrefix) : 
/*  598 */         "");
/*  599 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   protected Map<Integer, JLbsStringList> getLRUListMap(String url) {
/*  604 */     if (this.ms_LRUListCaches.get(url) == null) {
/*      */       
/*  606 */       Map<Integer, JLbsStringList> lruListMap = new LinkedHashMap<Integer, JLbsStringList>(LRU_RESOURCE_CACHE_SIZE)
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean removeEldestEntry(Map.Entry<Integer, JLbsStringList> eldest) {
/*  613 */             return (size() > LuceneLocalizationServices.LRU_RESOURCE_CACHE_SIZE);
/*      */           }
/*      */         };
/*  616 */       this.ms_LRUListCaches.put(url, lruListMap);
/*      */ 
/*      */       
/*  619 */       lruListMap = Collections.synchronizedMap(lruListMap);
/*  620 */       return lruListMap;
/*      */     } 
/*      */     
/*  623 */     return this.ms_LRUListCaches.get(url);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LuceneConnection internalGetConnection(String langPrefix, CustomizationGUID custGUID, boolean forManagement) throws RuntimeException {
/*  631 */     String dbDirectory = getLocalizationDBDirectory();
/*  632 */     if (custGUID != null) {
/*      */       
/*  634 */       dbDirectory = String.valueOf(dbDirectory) + "/Customization";
/*  635 */       langPrefix = String.valueOf(langPrefix) + "." + custGUID.getIdentifier();
/*      */     } 
/*  637 */     LuceneConnection luceneConnection = new LuceneConnection(dbDirectory, langPrefix);
/*      */     
/*  639 */     if (JLbsConstants.DEBUG)
/*  640 */       ms_Logger.debug("Created Lucene connection for language/customization : " + langPrefix + ((custGUID != null) ? (
/*  641 */           " [" + custGUID.getIdentifier() + "]") : 
/*  642 */           "")); 
/*  643 */     return luceneConnection;
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
/*      */   public Timestamp getLastSynchronizationTime() {
/*  684 */     return getLastSynchronizationTime(this.m_Language);
/*      */   }
/*      */ 
/*      */   
/*      */   public Timestamp getLastSynchronizationTime(String langPrefix) {
/*  689 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, true);
/*  690 */     File f = new File(String.valueOf(luceneConnection.getPath()) + "/synctime.txt");
/*  691 */     if (f.exists()) {
/*      */       
/*      */       try {
/*      */         
/*  695 */         String t = FileUtils.readFully(new FileReader(f));
/*  696 */         return new Timestamp(Long.valueOf(t.trim()).longValue());
/*      */       }
/*  698 */       catch (Exception e) {
/*      */         
/*  700 */         e.printStackTrace();
/*  701 */         return null;
/*      */       } 
/*      */     }
/*      */     
/*  705 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void closeResultSet(ResultSet rs) {
/*  710 */     if (rs != null) {
/*      */       
/*      */       try {
/*      */         
/*  714 */         rs.close();
/*      */       }
/*  716 */       catch (Exception e) {
/*      */         
/*  718 */         ms_Logger.error(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void rollback(String langPrefix) {
/*  725 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*      */     
/*      */     try {
/*  728 */       luceneConnection.rollback();
/*      */     }
/*  730 */     catch (Exception e) {
/*      */       
/*  732 */       ms_Logger.error("Exception occurred while rollbacking for language '" + langPrefix + "'", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void commit(String langPrefix) {
/*  738 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*      */     
/*      */     try {
/*  741 */       luceneConnection.commit();
/*      */     }
/*  743 */     catch (Exception e) {
/*      */       
/*  745 */       ms_Logger.error("Exception occurred while rollbacking for language '" + langPrefix + "'", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLocalizationDBDirectory(String localizationDBDirectory) {
/*  751 */     this.m_LocalizationDBDirectory = localizationDBDirectory;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLanguage() {
/*  756 */     return this.m_Language;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLanguage(String language) {
/*  762 */     this.m_Language = language;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByRange(int startListID, int endListID) {
/*  768 */     return getListHeadersByRange(startListID, endListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByRange(int startListID, int endListID, String group) {
/*  774 */     return getListHeadersByRange(this.m_Language, startListID, endListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByRange(String langPrefix, int startListID, int endListID, String group) {
/*  781 */     ms_Logger.trace("Getting list headers by range for language '" + langPrefix + "', startListID:" + startListID + 
/*  782 */         " , endListID:" + endListID + ", group:" + group);
/*  783 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/*  784 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*  785 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*  787 */       List<Document> docs = null;
/*  788 */       Query query = getQueryForRange(startListID, endListID, group);
/*      */       
/*      */       try {
/*  791 */         docs = luceneConnection.executeQuery(group, query);
/*  792 */         listCollection = traverseResultSetForHeaders(group, listCollection, docs);
/*      */       }
/*  794 */       catch (IOException e) {
/*      */         
/*  796 */         e.printStackTrace();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  801 */       ms_Logger.error("Error while getting list headers by range for language '" + langPrefix + "', startListID:" + 
/*  802 */           startListID + " , endListID:" + endListID + ", group:" + group);
/*      */     } 
/*  804 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getListHeaderByID(String langPrefix, int listID, String group, boolean useResourceProducts) {
/*  810 */     ms_Logger.trace("Getting list headers by range for language '" + langPrefix + "', listID:" + listID + " , group:" + group);
/*  811 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/*  812 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*  813 */     List<Document> docs = null;
/*  814 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*  816 */       Query query = getQueryForID(listID, group, useResourceProducts);
/*      */       
/*      */       try {
/*  819 */         docs = luceneConnection.executeQuery(group, query);
/*  820 */         listCollection = traverseResultSetForHeaders(group, listCollection, docs);
/*      */       }
/*  822 */       catch (IOException e) {
/*      */         
/*  824 */         e.printStackTrace();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  829 */       ms_Logger.error("Error while getting list headers by range for language '" + langPrefix + "', listID:" + listID + 
/*  830 */           " , group:" + group);
/*      */     } 
/*  832 */     return listCollection.isEmpty() ? 
/*  833 */       null : 
/*  834 */       listCollection.get(0);
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
/*      */   private boolean ensureConnection(LuceneConnection luceneConnection) {
/*  870 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByIDSet(int[] idList) {
/*  876 */     return getListHeadersByIDSet(idList, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByIDSet(int[] idList, String group) {
/*  882 */     return getListHeadersByIDSet(this.m_Language, idList, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByIDSet(String langPrefix, int[] idList, String group) {
/*  888 */     ms_Logger.trace("Getting list headers by id set for language '" + langPrefix + "', idlist:" + Arrays.toString(idList) + 
/*  889 */         ", group:" + group);
/*  890 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/*  891 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*  892 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*  894 */       List<Document> lists = null;
/*  895 */       BooleanQuery booleanQuery = getQueryForIDSet(idList, group);
/*  896 */       if (booleanQuery == null) {
/*  897 */         return listCollection;
/*      */       }
/*      */       try {
/*  900 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*  901 */         listCollection = traverseResultSetForHeaders(group, listCollection, lists);
/*      */       }
/*  903 */       catch (IOException e) {
/*      */         
/*  905 */         e.printStackTrace();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  910 */       ms_Logger.error("Error while getting list headers by id set for language '" + langPrefix + "', idlist:" + 
/*  911 */           Arrays.toString(idList) + ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/*  914 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByStartID(int startListID) {
/*  920 */     return getListHeadersByStartID(startListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByStartID(int startListID, String group) {
/*  926 */     return getListHeadersByStartID(this.m_Language, startListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByStartID(String langPrefix, int startListID, String group) {
/*  932 */     ms_Logger.trace("Getting list headers by start id for language '" + langPrefix + "', startListID:" + startListID + 
/*  933 */         ", group:" + group);
/*  934 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/*  935 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*  936 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*  938 */       List<Document> lists = null;
/*  939 */       BooleanQuery booleanQuery = getQueryForStartLimit(startListID, group);
/*      */       
/*      */       try {
/*  942 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*      */       }
/*  944 */       catch (IOException e) {
/*      */         
/*  946 */         e.printStackTrace();
/*      */       } 
/*  948 */       listCollection = traverseResultSetForHeaders(group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/*  952 */       ms_Logger.error("Error while getting list headers by start id for language '" + langPrefix + "', startListID:" + 
/*  953 */           startListID + ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/*  956 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByEndID(int endListID) {
/*  962 */     return getListHeadersByEndID(endListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByEndID(int endListID, String group) {
/*  968 */     return getListHeadersByEndID(this.m_Language, endListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListHeadersByEndID(String langPrefix, int endListID, String group) {
/*  974 */     ms_Logger.trace(
/*  975 */         "Getting list headers by end id for language '" + langPrefix + "', endListID:" + endListID + ", group:" + group);
/*  976 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/*  977 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/*  978 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*  980 */       List<Document> lists = null;
/*  981 */       BooleanQuery booleanQuery = getQueryForEndLimit(endListID, group);
/*      */       
/*      */       try {
/*  984 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*      */       }
/*  986 */       catch (IOException e) {
/*      */         
/*  988 */         e.printStackTrace();
/*      */       } 
/*  990 */       listCollection = traverseResultSetForHeaders(group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/*  994 */       ms_Logger.error("Error while getting list headers by end id for language '" + langPrefix + "', endListID:" + endListID + 
/*  995 */           ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/*  998 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByRange(int startListID, int endListID) {
/* 1004 */     return getListsByRange(startListID, endListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByRange(int startListID, int endListID, String group) {
/* 1010 */     return getListsByRange(this.m_Language, startListID, endListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByRange(String langPrefix, int startListID, int endListID, String group) {
/* 1017 */     ms_Logger.trace("Getting lists by range for language '" + langPrefix + "', startListID:" + startListID + ", endListID:" + 
/* 1018 */         endListID + ", group:" + group);
/* 1019 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/* 1020 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1021 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1023 */       List<Document> lists = null;
/* 1024 */       Query query = getQueryForRange(startListID, endListID, group);
/*      */       
/*      */       try {
/* 1027 */         lists = luceneConnection.executeQuery(group, query);
/*      */       }
/* 1029 */       catch (IOException e) {
/*      */         
/* 1031 */         e.printStackTrace();
/*      */       } 
/* 1033 */       listCollection = traverseResultSetForContent(langPrefix, group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/* 1037 */       ms_Logger.error("Error while getting lists by range for language '" + langPrefix + "', startListID:" + startListID + 
/* 1038 */           ", endListID:" + endListID + ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1041 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByIDSet(int[] idList) {
/* 1047 */     return getListsByIDSet(idList, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByIDSet(int[] idList, String group) {
/* 1053 */     return getListsByIDSet(this.m_Language, idList, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByIDSet(String langPrefix, int[] idList, String group) {
/* 1059 */     ms_Logger.trace("Getting lists by id set for language '" + langPrefix + "', idList:" + Arrays.toString(idList) + ", group:" + 
/* 1060 */         group);
/* 1061 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/* 1062 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1063 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1065 */       List<Document> lists = null;
/* 1066 */       BooleanQuery booleanQuery = getQueryForIDSet(idList, group);
/* 1067 */       if (booleanQuery == null) {
/* 1068 */         return listCollection;
/*      */       }
/*      */       try {
/* 1071 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*      */       }
/* 1073 */       catch (IOException e) {
/*      */         
/* 1075 */         e.printStackTrace();
/*      */       } 
/* 1077 */       listCollection = traverseResultSetForContent(langPrefix, group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/* 1081 */       ms_Logger.error("Error while getting lists by id set for language '" + langPrefix + "', idList:" + 
/* 1082 */           Arrays.toString(idList) + ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1085 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByStartID(int startListID) {
/* 1091 */     return getListsByStartID(startListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByStartID(int startListID, String group) {
/* 1097 */     return getListsByStartID(this.m_Language, startListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByStartID(String langPrefix, int startListID, String group) {
/* 1103 */     ms_Logger.trace(
/* 1104 */         "Getting lists by start id for language '" + langPrefix + "', startListID:" + startListID + ", group:" + group);
/* 1105 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/* 1106 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1107 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1109 */       List<Document> lists = null;
/* 1110 */       BooleanQuery booleanQuery = getQueryForStartLimit(startListID, group);
/*      */       
/*      */       try {
/* 1113 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*      */       }
/* 1115 */       catch (IOException e) {
/*      */         
/* 1117 */         e.printStackTrace();
/*      */       } 
/* 1119 */       listCollection = traverseResultSetForContent(langPrefix, group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/* 1123 */       ms_Logger.error("Error while getting lists by start id for language '" + langPrefix + "', startListID:" + startListID + 
/* 1124 */           ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1127 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByEndID(int endListID) {
/* 1133 */     return getListsByEndID(endListID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByEndID(int endListID, String group) {
/* 1139 */     return getListsByEndID(this.m_Language, endListID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<JLbsStringList> getListsByEndID(String langPrefix, int endListID, String group) {
/* 1145 */     ms_Logger.trace("Getting lists by end id for language '" + langPrefix + "', endListID:" + endListID + ", group:" + group);
/* 1146 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/* 1147 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1148 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1150 */       List<Document> lists = null;
/* 1151 */       BooleanQuery booleanQuery = getQueryForEndLimit(endListID, group);
/*      */       
/*      */       try {
/* 1154 */         lists = luceneConnection.executeQuery(group, (Query)booleanQuery);
/*      */       }
/* 1156 */       catch (IOException e) {
/*      */         
/* 1158 */         e.printStackTrace();
/*      */       } 
/* 1160 */       listCollection = traverseResultSetForContent(langPrefix, group, listCollection, lists);
/*      */     }
/*      */     else {
/*      */       
/* 1164 */       ms_Logger.error("Error while getting lists by end id for language '" + langPrefix + "', endListID:" + endListID + 
/* 1165 */           ", group:" + group + "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1168 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMinListID(String langPrefix) {
/* 1174 */     return getMinListID(langPrefix, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMinListID(String langPrefix, String group) {
/* 1180 */     ms_Logger.trace("Getting min list id for language '" + langPrefix + "', group:" + group);
/* 1181 */     int minDBResNumber = 0;
/* 1182 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1183 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1185 */       Query query = getQueryForMinListD(group);
/*      */       
/*      */       try {
/* 1188 */         minDBResNumber = luceneConnection.executeMinQuery(group, query, ILocalizationConstants.ResourceFields.resourceNumber).intValue();
/*      */       }
/* 1190 */       catch (IOException e) {
/*      */         
/* 1192 */         e.printStackTrace();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1197 */       ms_Logger.error("Error while getting min list id for language '" + langPrefix + "', group:" + group + 
/* 1198 */           "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1201 */     return (minDBResNumber == 0 || minDBResNumber == Integer.MAX_VALUE) ? 
/* 1202 */       minDBResNumber : 
/* 1203 */       JLbsLocalizationUtil.getListID(minDBResNumber, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxListID(String langPrefix) {
/* 1209 */     return getMaxListID(langPrefix, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxListID(String langPrefix, String group) {
/* 1215 */     ms_Logger.trace("Getting max list id for language '" + langPrefix + "', group:" + group);
/* 1216 */     int maxDBResNumber = 0;
/* 1217 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1218 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1220 */       Query query = getQueryForMaxListD(group);
/*      */       
/*      */       try {
/* 1223 */         maxDBResNumber = luceneConnection.executeMaxQuery(group, query, ILocalizationConstants.ResourceFields.resourceNumber).intValue();
/*      */       }
/* 1225 */       catch (IOException e) {
/*      */         
/* 1227 */         e.printStackTrace();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1232 */       ms_Logger.error("Error while getting max list id for language '" + langPrefix + "', group:" + group + 
/* 1233 */           "! Embedded connection is not connected");
/*      */     } 
/*      */     
/* 1236 */     return (maxDBResNumber == 0 || maxDBResNumber == Integer.MIN_VALUE) ? 
/* 1237 */       maxDBResNumber : 
/* 1238 */       JLbsLocalizationUtil.getListID(maxDBResNumber, group);
/*      */   }
/*      */ 
/*      */   
/*      */   private Query getQueryForMinListD(String group) {
/* 1243 */     BooleanQuery query = createResourceCatalogQuery(false);
/*      */     
/* 1245 */     if (JLbsStringUtil.isEmpty(group)) {
/* 1246 */       return (Query)query;
/*      */     }
/* 1248 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1249 */     int posResNrStart = resGrpBase;
/* 1250 */     int posResNrEnd = posResNrStart + 1000000;
/* 1251 */     int negResNrStart = -resGrpBase - 1000000;
/* 1252 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1260 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1262 */     return (Query)query;
/*      */   }
/*      */ 
/*      */   
/*      */   private Query getQueryForMaxListD(String group) {
/* 1267 */     BooleanQuery query = createResourceCatalogQuery(false);
/*      */     
/* 1269 */     if (JLbsStringUtil.isEmpty(group)) {
/* 1270 */       return (Query)query;
/*      */     }
/* 1272 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1273 */     int posResNrStart = resGrpBase;
/* 1274 */     int posResNrEnd = posResNrStart + 1000000;
/* 1275 */     int negResNrStart = -resGrpBase - 1000000;
/* 1276 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1283 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1285 */     return (Query)query;
/*      */   }
/*      */ 
/*      */   
/*      */   private BooleanQuery getQueryForStartLimit(int startListID, String group) {
/* 1290 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1291 */     int dbStartResNumber = JLbsLocalizationUtil.getDBResNumber(startListID, resGrpBase);
/* 1292 */     int posResNrStart = resGrpBase;
/* 1293 */     int posResNrEnd = posResNrStart + 1000000;
/* 1294 */     int negResNrStart = -resGrpBase - 1000000;
/* 1295 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1304 */     Query resNo1 = IntPoint.newRangeQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), dbStartResNumber, 2147483647);
/*      */ 
/*      */     
/* 1307 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 1308 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1309 */       .add(resNo1, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */     
/* 1312 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1314 */     return query;
/*      */   }
/*      */ 
/*      */   
/*      */   private BooleanQuery getQueryForEndLimit(int endListID, String group) {
/* 1319 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1320 */     int dbEndResNumber = JLbsLocalizationUtil.getDBResNumber(endListID, resGrpBase);
/* 1321 */     int posResNrStart = resGrpBase;
/* 1322 */     int posResNrEnd = posResNrStart + 1000000;
/* 1323 */     int negResNrStart = -resGrpBase - 1000000;
/* 1324 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1333 */     Query resNo1 = IntPoint.newRangeQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), -2147483648, dbEndResNumber);
/*      */ 
/*      */     
/* 1336 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 1337 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1338 */       .add(resNo1, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */     
/* 1341 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1343 */     return query;
/*      */   }
/*      */ 
/*      */   
/*      */   private Query getQueryForID(int listID, String group, boolean useResourceProducts) {
/* 1348 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1349 */     int dbResNumber = JLbsLocalizationUtil.getDBResNumber(listID, resGrpBase);
/* 1350 */     int posResNrStart = resGrpBase;
/* 1351 */     int posResNrEnd = posResNrStart + 1000000;
/* 1352 */     int negResNrStart = -resGrpBase - 1000000;
/* 1353 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1361 */     BytesRefBuilder brb = new BytesRefBuilder();
/* 1362 */     brb.grow(4);
/* 1363 */     brb.setLength(4);
/* 1364 */     Query exactQuery = IntPoint.newExactQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), dbResNumber);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1369 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 1370 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1371 */       .add(exactQuery, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */ 
/*      */     
/* 1375 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1377 */     return (Query)query;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private BooleanQuery addPosNegResourceRangeFilters(BooleanQuery query, int posResNrStart, int posResNrEnd, int negResNrStart, int negResNrEnd) {
/* 1384 */     Query resNoP = IntPoint.newRangeQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), posResNrStart, posResNrEnd);
/*      */ 
/*      */ 
/*      */     
/* 1388 */     Query resNoN = IntPoint.newRangeQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), negResNrStart, negResNrEnd);
/*      */ 
/*      */ 
/*      */     
/* 1392 */     BooleanQuery resNoORs = (new BooleanQuery.Builder()).add(resNoP, BooleanClause.Occur.SHOULD)
/* 1393 */       .add(resNoN, BooleanClause.Occur.SHOULD).build();
/* 1394 */     BooleanQuery.Builder builder = new BooleanQuery.Builder();
/* 1395 */     for (int i = 0; i < query.clauses().size(); i++)
/*      */     {
/* 1397 */       builder.add(query.clauses().get(i));
/*      */     }
/* 1399 */     BooleanClause clause = new BooleanClause((Query)resNoORs, BooleanClause.Occur.MUST);
/* 1400 */     builder.add(clause);
/* 1401 */     query = builder.build();
/* 1402 */     return query;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Query getQueryForRange(int startListID, int endListID, String group) {
/* 1408 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1409 */     int dbStartResNumber = JLbsLocalizationUtil.getDBResNumber(startListID, resGrpBase);
/* 1410 */     int dbEndResNumber = JLbsLocalizationUtil.getDBResNumber(endListID, resGrpBase);
/* 1411 */     int posResNrStart = resGrpBase;
/* 1412 */     int posResNrEnd = posResNrStart + 1000000;
/* 1413 */     int negResNrStart = -resGrpBase - 1000000;
/* 1414 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1422 */     Query resNo1 = IntPoint.newRangeQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), dbStartResNumber, dbEndResNumber);
/*      */ 
/*      */ 
/*      */     
/* 1426 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 1427 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1428 */       .add(resNo1, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */ 
/*      */     
/* 1432 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1434 */     return (Query)query;
/*      */   }
/*      */ 
/*      */   
/*      */   private BooleanQuery getQueryForIDSet(int[] idList, String group) {
/* 1439 */     List<Integer> inList = getInList(idList, group);
/* 1440 */     if (inList.isEmpty())
/* 1441 */       return null; 
/* 1442 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1443 */     int posResNrStart = resGrpBase;
/* 1444 */     int posResNrEnd = posResNrStart + 1000000;
/* 1445 */     int negResNrStart = -resGrpBase - 1000000;
/* 1446 */     int negResNrEnd = -resGrpBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1455 */     BooleanQuery.Builder builder = new BooleanQuery.Builder();
/* 1456 */     for (Integer resNo : inList) {
/*      */       
/* 1458 */       BytesRefBuilder brb = new BytesRefBuilder();
/* 1459 */       brb.grow(4);
/* 1460 */       brb.setLength(4);
/* 1461 */       Query inFilter = IntPoint.newExactQuery(ILocalizationConstants.ResourceFields.resourceNumber.toString(), resNo.intValue());
/* 1462 */       NumericUtils.intToSortableBytes(resNo.intValue(), brb.bytes(), 0);
/*      */ 
/*      */ 
/*      */       
/* 1466 */       BooleanClause clause = new BooleanClause(inFilter, BooleanClause.Occur.SHOULD);
/* 1467 */       builder.add(clause);
/*      */     } 
/*      */     
/* 1470 */     BooleanQuery resNo1 = builder.build();
/*      */     
/* 1472 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 1473 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1474 */       .add((Query)resNo1, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */     
/* 1477 */     query = addPosNegResourceRangeFilters(query, posResNrStart, posResNrEnd, negResNrStart, negResNrEnd);
/*      */     
/* 1479 */     return query;
/*      */   }
/*      */ 
/*      */   
/*      */   private List<Integer> getInList(int[] idList, String group) {
/* 1484 */     if (idList == null || idList.length == 0) {
/* 1485 */       return null;
/*      */     }
/* 1487 */     int resGrpBase = JLbsLocalizationUtil.getResGroupBase(group);
/* 1488 */     List<Integer> list = new ArrayList<>();
/* 1489 */     for (int i = 0; i < idList.length; i++)
/* 1490 */       list.add(Integer.valueOf(JLbsLocalizationUtil.getDBResNumber(idList[i], resGrpBase))); 
/* 1491 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ArrayList<JLbsStringList> traverseResultSetForHeaders(String group, ArrayList<JLbsStringList> listCollection, List<Document> lists) {
/* 1497 */     if (lists != null) {
/*      */       
/*      */       try {
/*      */         
/* 1501 */         for (Document doc : lists) {
/*      */           
/* 1503 */           JLbsStringList list = deserializeStringList(doc);
/* 1504 */           if (list != null) {
/* 1505 */             listCollection.add(list);
/*      */           }
/*      */         } 
/* 1508 */       } catch (Exception e) {
/*      */         
/* 1510 */         listCollection = null;
/* 1511 */         ms_Logger.error(e);
/*      */       } 
/*      */     }
/* 1514 */     return listCollection;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static JLbsStringList deserializeStringList(Document doc) {
/* 1519 */     byte[] serialized = (doc.getField(ILocalizationConstants.ResourceFields.contents.toString()).binaryValue()).bytes;
/* 1520 */     ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
/*      */     
/*      */     try {
/* 1523 */       ObjectInputStream in = new ObjectInputStream(bais);
/* 1524 */       in.readObject();
/* 1525 */       JLbsStringList list = (JLbsStringList)in.readObject();
/* 1526 */       in.close();
/* 1527 */       bais.close();
/* 1528 */       return list;
/*      */     }
/* 1530 */     catch (Exception e) {
/*      */       
/* 1532 */       e.printStackTrace();
/* 1533 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ArrayList<JLbsStringList> traverseResultSetForContent(String langPrefix, String group, ArrayList<JLbsStringList> listCollection, List<Document> lists) {
/* 1540 */     if (lists != null) {
/*      */       
/*      */       try {
/*      */         
/* 1544 */         for (Document doc : lists) {
/*      */           
/* 1546 */           JLbsStringList list = deserializeStringList(doc);
/* 1547 */           if (list != null) {
/* 1548 */             listCollection.add(list);
/*      */           }
/*      */         } 
/* 1551 */       } catch (Exception e) {
/*      */         
/* 1553 */         ms_Logger.error(e);
/*      */       } 
/*      */     }
/* 1556 */     return listCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSupportedLanguageList(String[] langList) {
/* 1566 */     synchronized (ms_SupportedLanguages) {
/*      */       
/* 1568 */       if (langList != null) {
/*      */         
/* 1570 */         ms_SupportedLanguages = new ArrayList<>();
/* 1571 */         Arrays.sort((Object[])langList);
/*      */         
/* 1573 */         for (int i = 0; i < langList.length; i++) {
/*      */           
/* 1575 */           LuceneConnection embeddedConnection = getLuceneConnection(langList[i], null, false);
/* 1576 */           if (ensureConnection(embeddedConnection)) {
/* 1577 */             ms_SupportedLanguages.add(langList[i]);
/*      */           }
/*      */         } 
/* 1580 */         if (ms_SupportedLanguages.size() == 0)
/* 1581 */           ms_SupportedLanguages.add("NEUT"); 
/*      */       } 
/*      */     } 
/* 1584 */     ms_Logger.trace("Supported language list for localization services : " + ms_SupportedLanguages);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ArrayList<String> getSupportedLanguages() {
/* 1589 */     synchronized (ms_SupportedLanguages) {
/*      */       
/* 1591 */       return ms_SupportedLanguages;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void releaseEmbeddedConnections(String resourceDBDirectory) {
/* 1597 */     ms_Logger.trace("Releasing embedded connections for resource directory : " + resourceDBDirectory);
/* 1598 */     if (ms_SupportedLanguages != null)
/*      */     {
/* 1600 */       for (int i = 0; i < ms_SupportedLanguages.size(); i++) {
/*      */         
/* 1602 */         String lang = ms_SupportedLanguages.get(i);
/* 1603 */         LuceneConnection conn = ms_LuceneConnections.get(lang);
/* 1604 */         if (conn != null && conn.getFolder().equals(resourceDBDirectory)) {
/*      */           
/* 1606 */           ms_Logger.debug("Trying to release lucene connection for url : " + conn.getPath());
/*      */           
/*      */           try {
/* 1609 */             conn.close();
/*      */           }
/* 1611 */           catch (IOException e) {
/*      */             
/* 1613 */             ms_Logger.debug("Error during release lucene connection for url : " + conn.getPath(), e);
/* 1614 */             e.printStackTrace();
/*      */           } 
/* 1616 */           ms_LuceneConnections.remove(lang);
/*      */         } 
/*      */       } 
/*      */     }
/* 1620 */     stopTimer();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void releaseAllEmbeddedConnections() {
/* 1626 */     for (String key : ms_LuceneConnections.keySet()) {
/*      */       
/* 1628 */       LuceneConnection conn = ms_LuceneConnections.get(key);
/*      */       
/*      */       try {
/* 1631 */         conn.close();
/*      */       }
/* 1633 */       catch (IOException e) {
/*      */         
/* 1635 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/* 1638 */     ms_LuceneConnections.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean releaseEmbeddedConnection(String langPrefix, CustomizationGUID custGUID) {
/*      */     try {
/* 1646 */       String poolKey = getPoolKey(langPrefix, custGUID);
/* 1647 */       LuceneConnection luceneConnection = ms_LuceneConnections.get(poolKey);
/* 1648 */       if (luceneConnection != null) {
/*      */         
/* 1650 */         luceneConnection.close();
/* 1651 */         ms_LuceneConnections.remove(poolKey);
/*      */       } 
/* 1653 */       return true;
/*      */     }
/* 1655 */     catch (Exception e) {
/*      */       
/* 1657 */       e.printStackTrace();
/* 1658 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static synchronized void stopTimer() {
/* 1664 */     if (ms_Timer != null) {
/*      */       
/* 1666 */       ms_Timer.cancel();
/* 1667 */       ms_Timer = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItem(CustomizationGUID custGUID, String langPrefix, int listID, int stringTag, String group) {
/* 1675 */     JLbsStringList list = getList(custGUID, langPrefix, listID, group);
/*      */     
/* 1677 */     if (list != null) {
/* 1678 */       return list.getValueAtTag(stringTag);
/*      */     }
/* 1680 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItem(CustomizationGUID custGUID, String langPrefix, int listID, int stringTag) {
/* 1686 */     return getItem(custGUID, langPrefix, listID, stringTag, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItem(CustomizationGUID custGUID, int listID, int stringTag, String group) {
/* 1692 */     return getItem(custGUID, this.m_Language, listID, stringTag, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItem(CustomizationGUID custGUID, int listID, int stringTag) {
/* 1698 */     return getItem(custGUID, this.m_Language, listID, stringTag, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(CustomizationGUID custGUID, String langPrefix, int listID, String group) throws RuntimeException {
/* 1705 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 1707 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 1708 */       return null;
/*      */     } 
/*      */     
/* 1711 */     int dbResNumber = JLbsLocalizationUtil.getDBResNumber(listID, group);
/* 1712 */     JLbsStringList list = getListFromCache(langPrefix, custGUID, dbResNumber);
/*      */     
/* 1714 */     if (list != null) {
/*      */       
/* 1716 */       if (JLbsConstants.LOGLEVEL > 7)
/* 1717 */         ms_Logger.trace("Found list in cache for language : " + langPrefix + ", listId : " + listID + ", group : " + group + 
/* 1718 */             ". Returning list : " + list); 
/* 1719 */       return list;
/*      */     } 
/*      */     
/* 1722 */     ms_Logger.trace("Trying to get list for language '" + langPrefix + "', listId : " + listID + ", group : " + group);
/*      */     
/* 1724 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, custGUID, false);
/* 1725 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1727 */       list = getListHeaderByID(langPrefix, listID, group, true);
/*      */       
/* 1729 */       if (isInconsistent(langPrefix, list)) {
/*      */         
/* 1731 */         ms_Logger.trace("List is inconsistent for language '" + langPrefix + "', list id : " + listID + 
/* 1732 */             ". Returning list for default language!");
/* 1733 */         list = getList(custGUID, LANG_NAME_DEFAULT, listID, group);
/*      */       } 
/*      */ 
/*      */       
/* 1737 */       if (replaceListItems(langPrefix)) {
/* 1738 */         traverseStringListForItemReplacement(custGUID, list, listID, group);
/*      */       }
/* 1740 */       putListIntoLRUCache(langPrefix, custGUID, list, dbResNumber);
/* 1741 */       return (list == null) ? 
/* 1742 */         new JLbsStringList() : 
/* 1743 */         list;
/*      */     } 
/* 1745 */     if (!langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 1747 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 1748 */           "' is not connected. Returning list for default language, list id : " + listID + ", group : " + group);
/* 1749 */       return getList(custGUID, LANG_NAME_DEFAULT, listID, group);
/*      */     } 
/*      */     
/* 1752 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessage getMessage(String messageConstantId) {
/* 1758 */     return getMessage(this.m_Language, messageConstantId);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessage getMessage(String langPrefix, String messageConstantId) {
/* 1764 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 1766 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 1767 */       return null;
/*      */     } 
/*      */     
/* 1770 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1771 */     ms_Logger.trace("Trying to get message for language '" + langPrefix + "', messageId : " + messageConstantId);
/* 1772 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1774 */       ms_Logger.trace("Lucene connection exists for language '" + langPrefix + "', messageId : " + messageConstantId);
/* 1775 */       JLbsMessage message = null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1780 */       TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.messages.toString()));
/*      */ 
/*      */       
/* 1783 */       Term messId = new Term(ILocalizationConstants.MessageFields.constantId.toString(), messageConstantId);
/*      */ 
/*      */       
/* 1786 */       BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1787 */         .add((Query)new TermQuery(messId), BooleanClause.Occur.MUST).build();
/*      */       
/*      */       try {
/*      */         JLbsWarningMessage jLbsWarningMessage;
/* 1791 */         List<Document> list = luceneConnection.executeQuery("_misc_", (Query)query);
/* 1792 */         if (list == null || list.isEmpty()) {
/*      */           
/* 1794 */           ms_Logger.trace(
/* 1795 */               "Message result set is null for language '" + langPrefix + "', message id : " + messageConstantId);
/* 1796 */           return null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1802 */           Document doc = list.get(0);
/* 1803 */           if (doc != null) {
/*      */             JlbsErrorMessage jlbsErrorMessage; JLbsInfoMessage jLbsInfoMessage; JLbsSelectionMessage jLbsSelectionMessage;
/* 1805 */             String consId = doc.get(ILocalizationConstants.MessageFields.constantId.toString());
/* 1806 */             String module = doc.get(ILocalizationConstants.MessageFields.module.toString());
/* 1807 */             int type = doc.getField(ILocalizationConstants.MessageFields.type.toString()).numericValue().intValue();
/* 1808 */             int listId = doc.getField(ILocalizationConstants.MessageFields.listId.toString()).numericValue().intValue();
/* 1809 */             int strTag = doc.getField(ILocalizationConstants.MessageFields.stringTag.toString()).numericValue().intValue();
/* 1810 */             int buttons = doc.getField(ILocalizationConstants.MessageFields.buttons.toString()).numericValue().intValue();
/* 1811 */             int defButton = doc.getField(ILocalizationConstants.MessageFields.defaultButton.toString()).numericValue().intValue();
/* 1812 */             String resGroup = doc.get(ILocalizationConstants.MessageFields.resourceGroup.toString());
/*      */             
/* 1814 */             JLbsMessResource mainMessage = null, title = null;
/* 1815 */             mainMessage = new JLbsMessResource(resGroup, listId, strTag);
/* 1816 */             switch (type) {
/*      */               
/*      */               case 1:
/* 1819 */                 jlbsErrorMessage = new JlbsErrorMessage(consId, module, mainMessage, title);
/*      */                 break;
/*      */               case 2:
/* 1822 */                 jLbsInfoMessage = new JLbsInfoMessage(consId, module, mainMessage, title);
/*      */                 break;
/*      */               case 4:
/* 1825 */                 jLbsSelectionMessage = new JLbsSelectionMessage(consId, module, title, mainMessage, buttons, defButton);
/*      */                 break;
/*      */               case 3:
/* 1828 */                 jLbsWarningMessage = new JLbsWarningMessage(consId, module, mainMessage, title);
/*      */                 break;
/*      */             } 
/*      */           
/*      */           } 
/* 1833 */         } catch (Exception e) {
/*      */           
/* 1835 */           ms_Logger.error(e);
/*      */         } 
/* 1837 */         return (JLbsMessage)jLbsWarningMessage;
/*      */       
/*      */       }
/* 1840 */       catch (IOException e1) {
/*      */         
/* 1842 */         ms_Logger.error(e1);
/*      */       }
/*      */     
/* 1845 */     } else if (!this.m_Language.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 1847 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 1848 */           "' is not connected. Returning message for default language, message id : " + messageConstantId);
/* 1849 */       return getMessage(LANG_NAME_DEFAULT, messageConstantId);
/*      */     } 
/*      */     
/* 1852 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getMessageConstantIds(String langPrefix, String module) {
/* 1858 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 1860 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 1861 */       return null;
/*      */     } 
/*      */     
/* 1864 */     ms_Logger.trace("Trying to get message constant ids for language '" + langPrefix + "', module : " + module);
/* 1865 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1866 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1868 */       ms_Logger.trace("Embedded connection exists for language '" + langPrefix + "', module : " + module);
/* 1869 */       ArrayList<String> list = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1874 */       TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.messages.toString()));
/*      */ 
/*      */       
/* 1877 */       Term mdl = new Term(ILocalizationConstants.MessageFields.module.toString(), module);
/*      */ 
/*      */       
/* 1880 */       BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 1881 */         .add((Query)new TermQuery(mdl), BooleanClause.Occur.MUST).build();
/*      */ 
/*      */       
/*      */       try {
/* 1885 */         List<Document> documents = luceneConnection.executeQuery("_misc_", (Query)query);
/* 1886 */         if (documents == null || documents.isEmpty()) {
/* 1887 */           return null;
/*      */         }
/*      */         
/* 1890 */         for (Document doc : documents)
/* 1891 */           list.add(doc.get(ILocalizationConstants.MessageFields.constantId.toString())); 
/* 1892 */         return list.<String>toArray(new String[list.size()]);
/*      */       
/*      */       }
/* 1895 */       catch (IOException e) {
/*      */         
/* 1897 */         ms_Logger.error(e);
/*      */       }
/*      */     
/* 1900 */     } else if (!langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 1902 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 1903 */           "' is not connected. Returnign message constant ids in default language");
/* 1904 */       return getMessageConstantIds(LANG_NAME_DEFAULT, module);
/*      */     } 
/*      */     
/* 1907 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getMessageConstantIds(String module) {
/* 1913 */     return getMessageConstantIds(this.m_Language, module);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<LbsLocalizableHelpContent> getAllHelpContents(String langPrefix) {
/* 1919 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 1921 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 1922 */       return null;
/*      */     } 
/*      */     
/* 1925 */     ms_Logger.trace("Trying to get all help contents for language '" + langPrefix + "'");
/* 1926 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1927 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1929 */       ms_Logger.trace("Embedded connection for language '" + langPrefix + "' exists for all help contents.");
/*      */ 
/*      */       
/* 1932 */       TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.helps.toString()));
/*      */       
/* 1934 */       BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */       
/*      */       try {
/* 1938 */         List<Document> documents = luceneConnection.executeQuery("_misc_", (Query)query);
/* 1939 */         ArrayList<LbsLocalizableHelpContent> list = new ArrayList<>();
/* 1940 */         if (documents == null || documents.isEmpty()) {
/* 1941 */           return null;
/*      */         }
/* 1943 */         for (Document doc : documents) {
/*      */           
/* 1945 */           int id = doc.getField(ILocalizationConstants.HelpFields.helpId.toString()).numericValue().intValue();
/* 1946 */           String docName = doc.get(ILocalizationConstants.HelpFields.documentName.toString());
/* 1947 */           String docTitle = doc.get(ILocalizationConstants.HelpFields.documentTitle.toString());
/* 1948 */           String docBody = doc.get(ILocalizationConstants.HelpFields.documentBody.toString());
/* 1949 */           int docType = doc.getField(ILocalizationConstants.HelpFields.documentType.toString()).numericValue().intValue();
/* 1950 */           list.add(new LbsLocalizableHelpContent(id, docName, docTitle, docType, docBody));
/*      */         } 
/* 1952 */         return list;
/*      */       }
/* 1954 */       catch (Exception e) {
/*      */         
/* 1956 */         ms_Logger.error(e);
/*      */       }
/*      */     
/* 1959 */     } else if (!langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 1961 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 1962 */           "' is not connected. Returning all help contents in default language!");
/* 1963 */       return getAllHelpContents(LANG_NAME_DEFAULT);
/*      */     } 
/*      */     
/* 1966 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<LbsLocalizableHelpContent> getAllHelpContents() {
/* 1972 */     return getAllHelpContents(this.m_Language);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LbsLocalizableHelpContent getHelpContent(String helpDocName, String langPrefix) {
/* 1978 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 1980 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 1981 */       return null;
/*      */     } 
/*      */     
/* 1984 */     ms_Logger.trace("Trying to get help content for language '" + langPrefix + "', helpDocName : " + helpDocName);
/* 1985 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 1986 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 1988 */       ms_Logger.trace(
/* 1989 */           "Embedded connection for language '" + langPrefix + "' exists for help content, helpDocName : " + helpDocName);
/*      */ 
/*      */ 
/*      */       
/* 1993 */       TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.helps.toString()));
/*      */       
/* 1995 */       BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */       
/*      */       try {
/* 1999 */         List<Document> documents = luceneConnection.executeQuery("_misc_", (Query)query);
/* 2000 */         if (documents == null || documents.isEmpty()) {
/* 2001 */           return null;
/*      */         }
/*      */         
/* 2004 */         Document doc = documents.get(0);
/* 2005 */         int id = doc.getField(ILocalizationConstants.HelpFields.helpId.toString()).numericValue().intValue();
/* 2006 */         String docName = doc.get(ILocalizationConstants.HelpFields.documentName.toString());
/* 2007 */         String docTitle = doc.get(ILocalizationConstants.HelpFields.documentTitle.toString());
/* 2008 */         String docBody = doc.get(ILocalizationConstants.HelpFields.documentBody.toString());
/* 2009 */         int docType = doc.getField(ILocalizationConstants.HelpFields.documentType.toString()).numericValue().intValue();
/* 2010 */         return new LbsLocalizableHelpContent(id, docName, docTitle, docType, docBody);
/*      */       
/*      */       }
/* 2013 */       catch (Exception e) {
/*      */         
/* 2015 */         ms_Logger.error(e);
/*      */       }
/*      */     
/* 2018 */     } else if (!langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 2020 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 2021 */           "' is not connected. Returning help content in default language!");
/* 2022 */       return getHelpContent(helpDocName, LANG_NAME_DEFAULT);
/*      */     } 
/* 2024 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LbsLocalizableHelpContent getHelpContent(String helpDocName) {
/* 2030 */     return getHelpContent(helpDocName, this.m_Language);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<LbsLocalizableHelpContent> getHelpContents(int[] docTypes) {
/* 2036 */     return getHelpContents(this.m_Language, docTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList<LbsLocalizableHelpContent> getHelpContents(String langPrefix, int[] docTypes) {
/* 2042 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES) {
/*      */       
/* 2044 */       ms_Logger.trace("Since JLbsConstants.USE_LOCALIZATION_SERVICES is set to false, returning null!");
/* 2045 */       return null;
/*      */     } 
/*      */     
/* 2048 */     ms_Logger.trace("Trying to get help content for language '" + langPrefix + "', docTypes : " + Arrays.toString(docTypes));
/* 2049 */     LuceneConnection luceneConnection = getLuceneConnection(langPrefix, null, false);
/* 2050 */     if (ensureConnection(luceneConnection)) {
/*      */       
/* 2052 */       ms_Logger.trace("Embedded connection for language '" + langPrefix + "' exists for help content, docTypes : " + 
/* 2053 */           Arrays.toString(docTypes));
/*      */       
/* 2055 */       TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.helps.toString()));
/*      */ 
/*      */       
/* 2058 */       BooleanQuery.Builder builder = new BooleanQuery.Builder();
/* 2059 */       for (int i = 0; i < docTypes.length; i++) {
/*      */         
/* 2061 */         BytesRefBuilder brb = new BytesRefBuilder();
/* 2062 */         brb.grow(4);
/* 2063 */         brb.setLength(4);
/* 2064 */         Query exactQuery = IntPoint.newExactQuery(ILocalizationConstants.HelpFields.documentType.toString(), docTypes[i]);
/*      */ 
/*      */ 
/*      */         
/* 2068 */         BooleanClause clause = new BooleanClause(exactQuery, BooleanClause.Occur.SHOULD);
/* 2069 */         builder.add(clause);
/*      */       } 
/*      */       
/* 2072 */       BooleanQuery ins = builder.build();
/* 2073 */       BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 2074 */         .add((Query)ins, BooleanClause.Occur.MUST).build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2083 */         List<Document> documents = luceneConnection.executeQuery("_misc_", (Query)query);
/* 2084 */         if (documents == null || documents.isEmpty())
/* 2085 */           return null; 
/* 2086 */         ArrayList<LbsLocalizableHelpContent> list = new ArrayList<>();
/* 2087 */         for (Document doc : documents) {
/*      */           
/* 2089 */           int id = doc.getField(ILocalizationConstants.HelpFields.helpId.toString()).numericValue().intValue();
/* 2090 */           String docName = doc.get(ILocalizationConstants.HelpFields.documentName.toString());
/* 2091 */           String docTitle = doc.get(ILocalizationConstants.HelpFields.documentTitle.toString());
/* 2092 */           String docBody = doc.get(ILocalizationConstants.HelpFields.documentBody.toString());
/* 2093 */           int docType = doc.getField(ILocalizationConstants.HelpFields.documentType.toString()).numericValue().intValue();
/* 2094 */           list.add(new LbsLocalizableHelpContent(id, docName, docTitle, docType, docBody));
/*      */         } 
/* 2096 */         return list;
/*      */       }
/* 2098 */       catch (Exception e) {
/*      */         
/* 2100 */         ms_Logger.error(e);
/*      */       }
/*      */     
/* 2103 */     } else if (!langPrefix.equalsIgnoreCase(LANG_NAME_DEFAULT)) {
/*      */       
/* 2105 */       ms_Logger.error("Embedded connection for language '" + langPrefix + 
/* 2106 */           "' is not connected. Returning help content in default language!");
/* 2107 */       return getHelpContents(LANG_NAME_DEFAULT, docTypes);
/*      */     } 
/* 2109 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private JLbsStringList getListFromCache(String langPrefix, CustomizationGUID custGUID, int dbResNumber) {
/* 2114 */     if (!CACHE_LRU_RESOURCE_CONTENT) {
/*      */       
/* 2116 */       ms_Logger.trace("CACHE_LRU_RESOURCE_CONTENT is set to false, LocalizationServices in memory cache is disabled!");
/* 2117 */       return null;
/*      */     } 
/* 2119 */     LuceneConnection url = getLuceneConnection(langPrefix, custGUID, false);
/* 2120 */     Map<Integer, JLbsStringList> lruListMap = getLRUListMap(url.getPath());
/* 2121 */     if (lruListMap == null) {
/* 2122 */       return null;
/*      */     }
/* 2124 */     Object objStringList = lruListMap.get(Integer.valueOf(dbResNumber));
/* 2125 */     return (JLbsStringList)objStringList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void putListIntoLRUCache(String langPrefix, CustomizationGUID custGUID, JLbsStringList list, int dbResNumber) {
/* 2131 */     if (!CACHE_LRU_RESOURCE_CONTENT || list == null || list.size() > LRU_RESOURCE_CACHE_LIST_CONTENT_LENGTH) {
/*      */       return;
/*      */     }
/* 2134 */     LuceneConnection url = getLuceneConnection(langPrefix, custGUID, false);
/* 2135 */     Map<Integer, JLbsStringList> lruListMap = getLRUListMap(url.getPath());
/* 2136 */     if (lruListMap == null)
/*      */       return; 
/* 2138 */     lruListMap.put(Integer.valueOf(dbResNumber), list);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(CustomizationGUID custGUID, String langPrefix, int listID) {
/* 2144 */     return getList(custGUID, langPrefix, listID, "UN");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(CustomizationGUID custGUID, int listID, String group) {
/* 2150 */     return getList(custGUID, this.m_Language, listID, group);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsStringList getList(CustomizationGUID custGUID, int listID) {
/* 2156 */     return getList(custGUID, this.m_Language, listID, "UN");
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
/*      */   public int getResourceCount(String group, int startListID, int endListID) {
/* 2169 */     Query query = getQueryForRange(startListID, endListID, group);
/* 2170 */     LuceneConnection luceneConnection = getLuceneConnection(this.m_Language, null, true);
/* 2171 */     if (ensureConnection(luceneConnection)) {
/*      */       
/*      */       try {
/*      */         
/* 2175 */         return luceneConnection.executeCountQuery(group, query);
/*      */       }
/* 2177 */       catch (IOException|NullPointerException e) {
/*      */         
/* 2179 */         e.printStackTrace();
/*      */       } 
/*      */     }
/* 2182 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setResourceProducts(ArrayList<Integer> resourceProducts) {
/* 2187 */     ms_ResourceProducts = resourceProducts;
/*      */   }
/*      */ 
/*      */   
/*      */   private BooleanQuery createResourceCatalogQuery(boolean useResourceProducts) {
/* 2192 */     TermQuery contentFilter = new TermQuery(new Term("#_ctlg_#", ILocalizationConstants.Catalogs.resources.toString()));
/* 2193 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST).build();
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
/* 2209 */     return query;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean releaseEmbeddedConnectionForDefaultLang() {
/* 2214 */     return releaseEmbeddedConnection(LANG_NAME_DEFAULT, null);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LuceneLocalizationServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */