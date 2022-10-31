/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.cache.ICacheConstants;
/*     */ import com.lbs.cache.IVersionSource;
/*     */ import com.lbs.cache.JLbsCacheItem;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.customization.CustomizationGUID;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.localization.cache.JLbsLocalizationCache;
/*     */ import com.lbs.localization.cache.JLbsMessageCache;
/*     */ import com.lbs.localization.cache.JLbsMessageConstantIdsCache;
/*     */ import com.lbs.message.JLbsMessage;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.util.ArrayUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.collections4.iterators.IteratorEnumeration;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class ClientLocalizationServices
/*     */   implements ILocalizationServices, ILocalizationConstants, ICacheConstants
/*     */ {
/*     */   private final IClientContext m_ClientContext;
/*     */   private String m_ContextLanguage;
/*     */   private static Map<String, JLbsLocalizationCache> ms_CacheList;
/*     */   private static Map<String, JLbsMessageCache> ms_MessageCacheList;
/*     */   private static Map<String, JLbsMessageConstantIdsCache> ms_MessageConstantIdsCacheList;
/*     */   private static Map<String, String> ms_FailedListIDs;
/*     */   private static Map<String, String> ms_FailedMessageListIDs;
/*     */   private static Map<String, String> ms_FailedMessageConstantIDs;
/*     */   private static Map<String, JLbsCultureInfoBase> ms_CultureInfoList;
/*  51 */   private static final transient LbsConsole ms_Logger = LbsConsole.getLogger("LocalizationServices.Client");
/*     */ 
/*     */   
/*     */   static {
/*  55 */     ms_CacheList = new ConcurrentHashMap<>();
/*  56 */     ms_FailedListIDs = new ConcurrentHashMap<>();
/*  57 */     ms_MessageCacheList = new ConcurrentHashMap<>();
/*  58 */     ms_FailedMessageListIDs = new ConcurrentHashMap<>();
/*  59 */     ms_MessageConstantIdsCacheList = new ConcurrentHashMap<>();
/*  60 */     ms_FailedMessageConstantIDs = new ConcurrentHashMap<>();
/*  61 */     ms_CultureInfoList = new ConcurrentHashMap<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public ClientLocalizationServices(IClientContext clientContext, IVersionSource versionSource) {
/*  66 */     this.m_ClientContext = clientContext;
/*  67 */     setContextLanguage();
/*     */     
/*  69 */     if (!cacheInitializedForLang(this.m_ContextLanguage))
/*     */     {
/*  71 */       synchronized (ms_CacheList) {
/*     */         
/*  73 */         JLbsLocalizationCache localizationCache = new JLbsLocalizationCache(this.m_ContextLanguage, versionSource);
/*  74 */         ms_CacheList.put(this.m_ContextLanguage, localizationCache);
/*     */       } 
/*     */     }
/*     */     
/*  78 */     if (!messageCacheInitializedForLang(this.m_ContextLanguage))
/*     */     {
/*  80 */       synchronized (ms_MessageCacheList) {
/*     */         
/*  82 */         JLbsMessageCache messageCache = new JLbsMessageCache(this.m_ContextLanguage, versionSource);
/*  83 */         ms_MessageCacheList.put(this.m_ContextLanguage, messageCache);
/*     */       } 
/*     */     }
/*     */     
/*  87 */     if (!messageIdCacheInitializedForLang(this.m_ContextLanguage))
/*     */     {
/*  89 */       synchronized (ms_MessageConstantIdsCacheList) {
/*     */         
/*  91 */         JLbsMessageConstantIdsCache messageCache = new JLbsMessageConstantIdsCache(this.m_ContextLanguage, versionSource);
/*  92 */         ms_MessageConstantIdsCacheList.put(this.m_ContextLanguage, messageCache);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCultureInfoBase getCulture() throws Exception {
/* 100 */     if (ms_CultureInfoList == null) {
/* 101 */       ms_CultureInfoList = new Hashtable<>();
/*     */     }
/* 103 */     String className = "com.lbs.globalization.JLbsCultureInfo" + this.m_ContextLanguage;
/* 104 */     JLbsCultureInfoBase cInfo = ms_CultureInfoList.get(className);
/* 105 */     if (cInfo == null) {
/*     */       
/* 107 */       cInfo = (JLbsCultureInfoBase)Class.forName(className).newInstance();
/* 108 */       ms_CultureInfoList.put(className, cInfo);
/*     */     } 
/* 110 */     return cInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean cacheInitializedForLang(String langPrefix) {
/* 115 */     Object objLocalizationCache = ms_CacheList.get(langPrefix);
/* 116 */     return objLocalizationCache instanceof JLbsLocalizationCache;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean messageCacheInitializedForLang(String langPrefix) {
/* 121 */     Object objMessageCache = ms_MessageCacheList.get(langPrefix);
/* 122 */     return objMessageCache instanceof JLbsMessageCache;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean messageIdCacheInitializedForLang(String langPrefix) {
/* 127 */     Object objMessageIdCache = ms_MessageConstantIdsCacheList.get(langPrefix);
/* 128 */     return objMessageIdCache instanceof JLbsMessageConstantIdsCache;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsLocalizationCache getLocalizationCache(String langPrefix) {
/* 133 */     Object objLocalizationCache = ms_CacheList.get(langPrefix);
/*     */     
/* 135 */     if (objLocalizationCache instanceof JLbsLocalizationCache) {
/* 136 */       return (JLbsLocalizationCache)objLocalizationCache;
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsMessageCache getMessageCache(String langPrefix) {
/* 143 */     Object objMessageCache = ms_MessageCacheList.get(langPrefix);
/*     */     
/* 145 */     if (objMessageCache instanceof JLbsMessageCache) {
/* 146 */       return (JLbsMessageCache)objMessageCache;
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsMessageConstantIdsCache getMessageConstantIdsCache(String langPrefix) {
/* 153 */     Object objMessageIdCache = ms_MessageConstantIdsCacheList.get(langPrefix);
/*     */     
/* 155 */     if (objMessageIdCache instanceof JLbsMessageConstantIdsCache) {
/* 156 */       return (JLbsMessageConstantIdsCache)objMessageIdCache;
/*     */     }
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(int listID, int stringTag) {
/* 164 */     return getItem(this.m_ContextLanguage, listID, stringTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(String langPrefix, int listID, int stringTag) {
/* 170 */     return getItem(langPrefix, listID, stringTag, "UN");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(int listID, int stringTag, String group) {
/* 176 */     return getItem(this.m_ContextLanguage, listID, stringTag, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(String langPrefix, int listID, int stringTag, String group) {
/* 182 */     return getItem(null, langPrefix, listID, stringTag, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(int listID) {
/* 188 */     return getList(this.m_ContextLanguage, listID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(String langPrefix, int listID) {
/* 194 */     return getList(langPrefix, listID, "UN");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(int listID, String group) {
/* 200 */     return getList(this.m_ContextLanguage, listID, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(String langPrefix, int listID, String group) {
/* 206 */     return getList(null, langPrefix, listID, group);
/*     */   }
/*     */ 
/*     */   
/*     */   private String createKeyForFailedList(CustomizationGUID custGUID, String langPrefix, int listID, String group) {
/* 211 */     String custExtension = "";
/*     */     
/* 213 */     if (custGUID != null && !JLbsStringUtil.isEmpty(custGUID.getIdentifier())) {
/* 214 */       custExtension = custGUID.getIdentifier();
/*     */     }
/* 216 */     return String.valueOf(langPrefix) + "_" + group + "_" + listID + "_" + custExtension;
/*     */   }
/*     */ 
/*     */   
/*     */   private String createKeyForFailedMessage(String langPrefix, String messageID) {
/* 221 */     return String.valueOf(langPrefix) + "_" + messageID;
/*     */   }
/*     */ 
/*     */   
/*     */   private String createKeyForFailedMessageConstantIds(String langPrefix, String module) {
/* 226 */     return String.valueOf(langPrefix) + "_" + module;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsMessage getMessageFromCache(JLbsMessageCache messageCache, String messageConstantId) {
/* 231 */     if (ms_Logger.isTraceEnabled()) {
/* 232 */       ms_Logger.trace("Trying to get message from cache for language '" + this.m_ContextLanguage + "', message id : " + 
/* 233 */           messageConstantId);
/*     */     }
/* 235 */     JLbsMessage message = messageCache.get(messageConstantId);
/* 236 */     if (message != null) {
/*     */       
/* 238 */       if (ms_Logger.isTraceEnabled())
/* 239 */         ms_Logger.trace("Found message in cache for language '" + this.m_ContextLanguage + "', message id : " + 
/* 240 */             messageConstantId); 
/* 241 */       return message;
/*     */     } 
/*     */     
/* 244 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void putMessageIntoCache(JLbsMessageCache messageCache, JLbsMessage message) {
/* 249 */     if (message != null) {
/*     */       
/* 251 */       if (ms_Logger.isTraceEnabled())
/* 252 */         ms_Logger.trace("Putting message to cache for language '" + this.m_ContextLanguage + "', message id : " + 
/* 253 */             message.getId()); 
/* 254 */       synchronized (messageCache) {
/*     */         
/* 256 */         messageCache.put(message.getId(), message);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] getMessageConstantIdsFromCache(JLbsMessageConstantIdsCache messageCache, String module) {
/* 263 */     if (ms_Logger.isTraceEnabled()) {
/* 264 */       ms_Logger.trace("Trying to get message ids from cache for language '" + this.m_ContextLanguage + "', module : " + module);
/*     */     }
/* 266 */     String[] messageIds = messageCache.get(module);
/* 267 */     if (messageIds != null) {
/*     */       
/* 269 */       if (ms_Logger.isTraceEnabled())
/* 270 */         ms_Logger.trace("Found message ids in cache for language '" + this.m_ContextLanguage + "', module : " + module); 
/* 271 */       return messageIds;
/*     */     } 
/*     */     
/* 274 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void putMessageConstantIdsIntoCache(JLbsMessageConstantIdsCache messageCache, String module, String[] messageIds) {
/* 279 */     if (messageIds != null) {
/*     */       
/* 281 */       if (ms_Logger.isTraceEnabled())
/* 282 */         ms_Logger.trace("Putting message ids to cache for language '" + this.m_ContextLanguage + "', module : " + module); 
/* 283 */       synchronized (messageCache) {
/*     */         
/* 285 */         messageCache.put(module, messageIds);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private JLbsStringList getListFromCache(JLbsLocalizationCache localizationCache, CustomizationGUID custGUID, int listID, String group) {
/* 293 */     if (ms_Logger.isTraceEnabled())
/* 294 */       ms_Logger.trace("Trying to get list from cache for language '" + this.m_ContextLanguage + "', list id : " + listID); 
/* 295 */     if (custGUID != null && !JLbsStringUtil.isEmpty(custGUID.getIdentifier())) {
/* 296 */       group = String.valueOf(group) + "_" + custGUID.getIdentifier();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (!JLbsStringUtil.isEmpty(group)) {
/* 302 */       listID = JLbsLocalizationUtil.getDBResNumber(listID, group);
/*     */     }
/* 304 */     JLbsStringList objList = localizationCache.get(Integer.valueOf(listID), group);
/*     */     
/* 306 */     if (objList != null) {
/*     */       
/* 308 */       if (JLbsConstants.LOGLEVEL > 7)
/* 309 */         ms_Logger.trace("Found list in cache for language '" + this.m_ContextLanguage + "', list id : " + listID + ", list : " + 
/* 310 */             objList); 
/* 311 */       return objList;
/*     */     } 
/*     */ 
/*     */     
/* 315 */     if (this instanceof ClientReportingLocalizationServices && group.equalsIgnoreCase("UNRP")) {
/*     */       
/* 317 */       objList = localizationCache.get(Integer.valueOf(listID), "HRRP");
/*     */       
/* 319 */       if (objList != null) {
/* 320 */         return objList;
/*     */       }
/*     */     } 
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void putListIntoCache(JLbsLocalizationCache localizationCache, CustomizationGUID custGUID, JLbsStringList list, String group) {
/* 329 */     ms_Logger.trace("Putting list to cache for language '" + this.m_ContextLanguage + "', list : " + list);
/* 330 */     synchronized (localizationCache) {
/*     */       
/* 332 */       if (custGUID != null && !JLbsStringUtil.isEmpty(custGUID.getIdentifier())) {
/* 333 */         group = String.valueOf(group) + "_" + custGUID.getIdentifier();
/*     */       }
/* 335 */       localizationCache.put(Integer.valueOf(list.getID()), list, group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setContextLanguage() {
/*     */     try {
/* 343 */       Object varLang = this.m_ClientContext.getVariable("CLI-LANG");
/* 344 */       this.m_ContextLanguage = (varLang != null) ? 
/* 345 */         varLang.toString() : 
/* 346 */         LuceneLocalizationServices.LANG_NAME_DEFAULT;
/*     */     }
/* 348 */     catch (Exception e) {
/*     */       
/* 350 */       ms_Logger.error("Exception occurred while getting language from context", e);
/* 351 */       this.m_ContextLanguage = LuceneLocalizationServices.LANG_NAME_DEFAULT;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByEndID(int endListID) {
/* 358 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByEndID(int endListID, String group) {
/* 364 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByEndID(String langPrefix, int endListID, String group) {
/* 370 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByRange(int startListID, int endListID) {
/* 376 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByRange(int startListID, int endListID, String group) {
/* 382 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByRange(String langPrefix, int startListID, int endListID, String group) {
/* 388 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByStartID(int startListID) {
/* 394 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByStartID(int startListID, String group) {
/* 400 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByStartID(String langPrefix, int startListID, String group) {
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByEndID(int endListID) {
/* 412 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByEndID(int endListID, String group) {
/* 418 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByEndID(String langPrefix, int endListID, String group) {
/* 424 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByRange(int startListID, int endListID) {
/* 430 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByRange(int startListID, int endListID, String group) {
/* 436 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByRange(String langPrefix, int startListID, int endListID, String group) {
/* 442 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByStartID(int startListID) {
/* 448 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByStartID(int startListID, String group) {
/* 454 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListsByStartID(String langPrefix, int startListID, String group) {
/* 460 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxListID(String langPrefix) {
/* 466 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxListID(String langPrefix, String group) {
/* 472 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinListID(String langPrefix) {
/* 478 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinListID(String langPrefix, String group) {
/* 484 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByIDSet(int[] idList) {
/* 490 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByIDSet(int[] idList, String group) {
/* 496 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getListHeadersByIDSet(String langPrefix, int[] idList, String group) {
/* 502 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<JLbsStringList> getListsByIDSet(int[] idList) {
/* 508 */     return getListsByIDSet(idList, "UN");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<JLbsStringList> getListsByIDSet(int[] idList, String group) {
/* 514 */     return getListsByIDSet(this.m_ContextLanguage, idList, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<JLbsStringList> getListsByIDSet(String langPrefix, int[] idList, String group) {
/* 520 */     ArrayList<JLbsStringList> listCollection = new ArrayList<>();
/* 521 */     ArrayList<JLbsStringList> finalCollection = new ArrayList<>();
/* 522 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES)
/* 523 */       return listCollection; 
/* 524 */     JLbsLocalizationCache localizationCache = getLocalizationCache(langPrefix);
/*     */ 
/*     */     
/* 527 */     idList = getMissingIDListAndPopulate(localizationCache, idList, group, finalCollection);
/*     */     
/* 529 */     if (idList.length > 0) {
/*     */ 
/*     */       
/*     */       try {
/* 533 */         ms_Logger.info("Performing remote method call for list set : [" + langPrefix + ", " + group + ", " + 
/* 534 */             ArrayUtil.getCommaSeperatedString(idList) + "]");
/* 535 */         RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getListsByIDSet", 
/* 536 */             new Object[] { langPrefix, idList, group });
/*     */         
/* 538 */         listCollection = (ArrayList<JLbsStringList>)resp.Result;
/* 539 */         finalCollection.addAll(listCollection);
/*     */       }
/* 541 */       catch (Exception e) {
/*     */         
/* 543 */         ms_Logger.error(e);
/*     */       } 
/*     */       
/* 546 */       if (localizationCache != null)
/*     */       {
/* 548 */         for (int i = 0; i < listCollection.size(); i++) {
/*     */           
/* 550 */           if (listCollection.get(i) instanceof JLbsStringList) {
/* 551 */             putListIntoCache(localizationCache, null, listCollection.get(i), group);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 557 */     return finalCollection;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] getMissingIDListAndPopulate(JLbsLocalizationCache localizationCache, int[] idList, String group, ArrayList<JLbsStringList> finalCollection) {
/* 563 */     if (localizationCache == null) {
/* 564 */       return idList;
/*     */     }
/* 566 */     ArrayList<Integer> missingIDList = new ArrayList<>();
/*     */     
/* 568 */     for (int i = 0; i < idList.length; i++) {
/*     */       
/* 570 */       JLbsStringList list = getListFromCache(localizationCache, null, idList[i], group);
/* 571 */       if (list == null) {
/* 572 */         missingIDList.add(Integer.valueOf(idList[i]));
/*     */       } else {
/* 574 */         finalCollection.add(list);
/*     */       } 
/*     */     } 
/* 577 */     int[] missingList = new int[missingIDList.size()];
/* 578 */     for (int j = 0; j < missingIDList.size(); j++) {
/* 579 */       missingList[j] = ((Integer)missingIDList.get(j)).intValue();
/*     */     }
/* 581 */     return missingList;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration getKeys() {
/* 586 */     return getKeys("DefaultGroup");
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration getKeys(String group) {
/* 591 */     return getKeys(this.m_ContextLanguage, group);
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
/*     */   public Enumeration getKeys(String langPrefix, String group) {
/*     */     Enumeration keyEnum;
/* 606 */     JLbsLocalizationCache localizationCache = getLocalizationCache(langPrefix);
/*     */     
/* 608 */     if (localizationCache == null) {
/* 609 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 613 */       this.m_ClientContext.loadJAR("commons-collections.jar", false, false);
/* 614 */       IteratorEnumeration iteratorEnumeration = new IteratorEnumeration(localizationCache.getIterator(group));
/*     */     }
/* 616 */     catch (Exception e) {
/*     */       
/* 618 */       keyEnum = null;
/* 619 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 622 */     return keyEnum;
/*     */   }
/*     */ 
/*     */   
/*     */   public Element exportCacheContentToXMLElement(Document document) {
/* 627 */     return exportCacheContentToXMLElement(this.m_ContextLanguage, document);
/*     */   }
/*     */ 
/*     */   
/*     */   public Element exportCacheContentToXMLElement(String langPrefix, Document document) {
/* 632 */     if (document == null) {
/* 633 */       return null;
/*     */     }
/* 635 */     Element cacheElement = document.createElement("LocalizationCache");
/*     */ 
/*     */     
/*     */     try {
/* 639 */       this.m_ClientContext.loadJAR("commons-collections.jar", false, false);
/*     */     }
/* 641 */     catch (Exception e) {
/*     */       
/* 643 */       return cacheElement;
/*     */     } 
/*     */     
/* 646 */     JLbsLocalizationCache localizationCache = getLocalizationCache(langPrefix);
/* 647 */     if (localizationCache == null) {
/* 648 */       return cacheElement;
/*     */     }
/* 650 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "UN", document));
/* 651 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "HR", document));
/* 652 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "UNRP", document));
/* 653 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "HRRP", document));
/* 654 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "HELP", document));
/* 655 */     cacheElement.appendChild(exportGroupContentToXMLElement(localizationCache, "SS", document));
/*     */     
/* 657 */     return cacheElement;
/*     */   }
/*     */ 
/*     */   
/*     */   private Element exportGroupContentToXMLElement(JLbsLocalizationCache localizationCache, String group, Document document) {
/* 662 */     Element groupElement = document.createElement(group);
/* 663 */     IteratorEnumeration iteratorEnumeration = new IteratorEnumeration(localizationCache.getIterator(group));
/*     */     
/* 665 */     while (iteratorEnumeration.hasMoreElements()) {
/*     */       
/* 667 */       Object objKey = iteratorEnumeration.nextElement();
/* 668 */       if (objKey instanceof Integer) {
/*     */         
/* 670 */         JLbsCacheItem item = new JLbsCacheItem(objKey);
/* 671 */         groupElement.appendChild(item.exportToXMLElement(document));
/*     */       } 
/*     */     } 
/*     */     
/* 675 */     return groupElement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(CustomizationGUID custGUID, String langPrefix, int listID, int stringTag) {
/* 681 */     return getItem(custGUID, langPrefix, listID, stringTag, "UN");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(CustomizationGUID custGUID, int listID, int stringTag, String group) {
/* 687 */     return getItem(custGUID, this.m_ContextLanguage, listID, stringTag, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(CustomizationGUID custGUID, int listID, int stringTag) {
/* 693 */     return getItem(custGUID, this.m_ContextLanguage, listID, stringTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(CustomizationGUID custGUID, String langPrefix, int listID, String group) {
/* 699 */     if (!JLbsConstants.USE_LOCALIZATION_SERVICES)
/* 700 */       return new JLbsStringList(); 
/* 701 */     if (ms_FailedListIDs.containsKey(createKeyForFailedList(custGUID, langPrefix, listID, group))) {
/*     */       
/* 703 */       ms_Logger.debug("Failed list id list contains list " + listID + " for language '" + langPrefix + "', group : " + group);
/* 704 */       return new JLbsStringList();
/*     */     } 
/*     */     
/* 707 */     JLbsStringList list = null;
/* 708 */     JLbsLocalizationCache localizationCache = getLocalizationCache(langPrefix);
/*     */     
/* 710 */     if (localizationCache != null) {
/*     */ 
/*     */       
/* 713 */       list = getListFromCache(localizationCache, custGUID, listID, group);
/* 714 */       if (list != null) {
/* 715 */         return list;
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 720 */       String logMessage = "Performing remote method call for list : [" + langPrefix + ", " + group + ", " + listID + "]";
/* 721 */       if (custGUID != null && !JLbsStringUtil.isEmpty(custGUID.getIdentifier()))
/* 722 */         logMessage = String.valueOf(logMessage) + " . Customization GUID : " + custGUID.getIdentifier(); 
/* 723 */       ms_Logger.info(logMessage);
/* 724 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getList", 
/* 725 */           new Object[] { custGUID, langPrefix, Integer.valueOf(listID), group });
/*     */       
/* 727 */       list = (JLbsStringList)resp.Result;
/*     */     }
/* 729 */     catch (Exception e) {
/*     */       
/* 731 */       ms_Logger.error(e);
/* 732 */       list = null;
/*     */     } 
/*     */ 
/*     */     
/* 736 */     if (list == null || list.getID() == 0) {
/*     */       
/* 738 */       ms_Logger.debug("Could not find list with id " + listID + " for language '" + langPrefix + "', group : " + group);
/* 739 */       ms_FailedListIDs.put(createKeyForFailedList(custGUID, langPrefix, listID, group), "");
/*     */     } 
/*     */     
/* 742 */     if (list != null && localizationCache != null) {
/* 743 */       putListIntoCache(localizationCache, custGUID, list, group);
/*     */     }
/* 745 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessage getMessage(String langPrefix, String messageConstantId) {
/* 752 */     if (ms_FailedMessageListIDs.containsKey(createKeyForFailedMessage(langPrefix, messageConstantId))) {
/*     */       
/* 754 */       if (ms_Logger.isErrorEnabled())
/* 755 */         ms_Logger.error("Failed messages list contains message id " + messageConstantId + " for language '" + langPrefix + 
/* 756 */             "'"); 
/* 757 */       return null;
/*     */     } 
/*     */     
/* 760 */     JLbsMessage message = null;
/* 761 */     JLbsMessageCache messageCache = getMessageCache(langPrefix);
/*     */     
/* 763 */     if (messageCache != null) {
/*     */ 
/*     */       
/* 766 */       message = getMessageFromCache(messageCache, messageConstantId);
/* 767 */       if (message != null) {
/* 768 */         return message;
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 773 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getMessage", 
/* 774 */           new Object[] { langPrefix, messageConstantId });
/* 775 */       if (resp.Result instanceof JLbsMessage) {
/* 776 */         message = (JLbsMessage)resp.Result;
/*     */       }
/* 778 */     } catch (Exception e) {
/*     */       
/* 780 */       if (ms_Logger.isErrorEnabled()) {
/* 781 */         ms_Logger.error(e.getMessage(), e);
/*     */       }
/*     */     } 
/* 784 */     if (message == null || JLbsStringUtil.isEmpty(message.getId())) {
/*     */       
/* 786 */       ms_Logger.error("Could not find message with id " + messageConstantId + " for language '" + langPrefix + "'");
/* 787 */       ms_FailedMessageListIDs.put(createKeyForFailedMessage(langPrefix, messageConstantId), "");
/*     */     } 
/*     */     
/* 790 */     if (message != null && messageCache != null) {
/* 791 */       putMessageIntoCache(messageCache, message);
/*     */     }
/* 793 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessage getMessage(String messageConstantId) {
/* 799 */     return getMessage(this.m_ContextLanguage, messageConstantId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getMessageConstantIds(String langPrefix, String module) {
/* 805 */     if (ms_FailedMessageConstantIDs.containsKey(createKeyForFailedMessageConstantIds(langPrefix, module))) {
/*     */       
/* 807 */       if (ms_Logger.isErrorEnabled())
/* 808 */         ms_Logger.error("Failed message constants ids list contains message ids for module '" + module + "' and language '" + 
/* 809 */             langPrefix + "'"); 
/* 810 */       return null;
/*     */     } 
/*     */     
/* 813 */     String[] messageConstantIds = null;
/* 814 */     JLbsMessageConstantIdsCache messageIdCache = getMessageConstantIdsCache(langPrefix);
/*     */     
/* 816 */     if (messageIdCache != null) {
/*     */ 
/*     */       
/* 819 */       messageConstantIds = getMessageConstantIdsFromCache(messageIdCache, module);
/* 820 */       if (messageConstantIds != null) {
/* 821 */         return messageConstantIds;
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 826 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", 
/* 827 */           "getMessageConstantIds", new Object[] { langPrefix, module });
/* 828 */       if (resp.Result instanceof String[]) {
/* 829 */         messageConstantIds = (String[])resp.Result;
/*     */       }
/* 831 */     } catch (Exception e) {
/*     */       
/* 833 */       if (ms_Logger.isErrorEnabled()) {
/* 834 */         ms_Logger.error(e.getMessage(), e);
/*     */       }
/*     */     } 
/* 837 */     if (messageConstantIds == null) {
/*     */       
/* 839 */       ms_Logger.error("Could not find message constant ids with module " + module + " for language '" + langPrefix + "'");
/* 840 */       ms_FailedMessageConstantIDs.put(createKeyForFailedMessageConstantIds(langPrefix, module), "");
/*     */     } 
/*     */     
/* 843 */     if (messageConstantIds != null && messageIdCache != null) {
/* 844 */       putMessageConstantIdsIntoCache(messageIdCache, module, messageConstantIds);
/*     */     }
/* 846 */     return messageConstantIds;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getMessageConstantIds(String module) {
/* 852 */     return getMessageConstantIds(this.m_ContextLanguage, module);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getAllHelpContents(String langPrefix) {
/*     */     try {
/* 860 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getAllHelpContents", 
/* 861 */           new Object[] { langPrefix });
/* 862 */       if (resp.Result instanceof ArrayList) {
/* 863 */         return (ArrayList)resp.Result;
/*     */       }
/* 865 */     } catch (Exception e) {
/*     */       
/* 867 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/* 869 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getAllHelpContents() {
/* 875 */     return getAllHelpContents(this.m_ContextLanguage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableHelpContent getHelpContent(String helpDocName, String langPrefix) {
/*     */     try {
/* 883 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getHelpContent", 
/* 884 */           new Object[] { helpDocName, langPrefix });
/* 885 */       if (resp.Result instanceof LbsLocalizableHelpContent) {
/* 886 */         return (LbsLocalizableHelpContent)resp.Result;
/*     */       }
/* 888 */     } catch (Exception e) {
/*     */       
/* 890 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/* 892 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableHelpContent getHelpContent(String helpDocName) {
/* 898 */     return getHelpContent(helpDocName, this.m_ContextLanguage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getHelpContents(int[] docTypes) {
/* 904 */     return getHelpContents(this.m_ContextLanguage, docTypes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getHelpContents(String langPrefix, int[] docTypes) {
/*     */     try {
/* 912 */       RemoteMethodResponse resp = this.m_ClientContext.callRemoteMethod("LSW", "getHelpContents", 
/* 913 */           new Object[] { docTypes });
/* 914 */       if (resp.Result instanceof ArrayList) {
/* 915 */         return (ArrayList)resp.Result;
/*     */       }
/* 917 */     } catch (Exception e) {
/*     */       
/* 919 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/* 921 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(CustomizationGUID custGUID, String langPrefix, int listID) {
/* 927 */     return getList(custGUID, langPrefix, listID, "UN");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(CustomizationGUID custGUID, int listID, String group) {
/* 933 */     return getList(custGUID, this.m_ContextLanguage, listID, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(CustomizationGUID custGUID, int listID) {
/* 939 */     return getList(custGUID, this.m_ContextLanguage, listID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItem(CustomizationGUID custGUID, String langPrefix, int listID, int stringTag, String group) {
/* 945 */     JLbsStringList list = getList(custGUID, langPrefix, listID, group);
/*     */     
/* 947 */     if (list != null) {
/* 948 */       return list.getValueAtTag(stringTag);
/*     */     }
/* 950 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguage(String language) {
/* 957 */     this.m_ContextLanguage = language;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getList(int listID, int[] tagList) {
/* 963 */     JLbsStringList list = getList(this.m_ContextLanguage, listID, "UN");
/* 964 */     JLbsStringList newList = new JLbsStringList();
/* 965 */     for (int i = 0; i < tagList.length; i++) {
/*     */       
/* 967 */       if (list.getValueAtTag(tagList[i]) != null)
/* 968 */         newList.add(list.getValueAtTag(tagList[i]), tagList[i]); 
/*     */     } 
/* 970 */     return newList;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\ClientLocalizationServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */