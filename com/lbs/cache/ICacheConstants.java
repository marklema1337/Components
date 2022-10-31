/*    */ package com.lbs.cache;
/*    */ 
/*    */ import com.lbs.util.JLbsConstants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ICacheConstants
/*    */ {
/*    */   public static final String GROUP_NAME_DEFAULT = "DefaultGroup";
/*    */   public static final int CACHE_TYPE_ADMIN = 1;
/*    */   public static final int CACHE_TYPE_UNITY = 2;
/*    */   public static final String CACHE_NAME_ADMIN = "Admin";
/*    */   public static final String CACHE_NAME_UNITY = "Unity";
/*    */   public static final String CACHE_PREFIX_GLOBAL = "Cache_";
/*    */   public static final String XML_ELEMENT_ITEM = "CacheItem";
/*    */   public static final String XML_ELEMENT_LOCALIZATION_CACHE = "LocalizationCache";
/*    */   public static final String XML_ATTRIBUTE_KEY = "key";
/*    */   public static final String DIR_CACHE_PROFILE = "CacheProfile";
/*    */   public static final String FILE_CACHE_PROFILE_LOCALIZATION = "LocalizationCache.xml";
/*    */   public static final String PROP_NAME_MAX_PURGATORY_SIZE = "jcs.auxiliary.DC.attributes.MaxPurgatorySize";
/*    */   public static final String PROP_NAME_DISK_PATH = "jcs.auxiliary.DC.attributes.DiskPath";
/*    */   public static final String PROP_NAME_MAX_OBJECTS = "jcs.default.cacheattributes.MaxObjects";
/*    */   public static final String PROP_NAME_CACHE_FACTORY_TYPE = "factory.type";
/*    */   public static final String PROP_VALUE_DISK_PATH = "";
/* 40 */   public static final String PROP_VALUE_MAX_OBJECTS = JLbsConstants.GRID_MAX_SELECTION_COUNT;
/*    */   public static final String PROP_VALUE_CACHE_FACTORY_TYPE_RESOURCE = "0";
/*    */   public static final String PROP_VALUE_CACHE_FACTORY_TYPE_GENERAL = "1";
/*    */   public static final String FACTORY_CLASS_EHCACHE = "com.lbs.cache.EHCacheImplFactory";
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\ICacheConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */