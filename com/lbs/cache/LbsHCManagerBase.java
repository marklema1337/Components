/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.hazelcast.config.Config;
/*     */ import com.hazelcast.config.XmlConfigBuilder;
/*     */ import com.hazelcast.core.Hazelcast;
/*     */ import com.hazelcast.core.HazelcastInstance;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.FileNotFoundException;
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
/*     */ public class LbsHCManagerBase
/*     */   implements ICacheManager
/*     */ {
/*  30 */   protected static String[] SESSION_FORBIDDEN_VARS = new String[] { ":", "\\*", "\\?", "\"", "<", ">", "\\|", "/", "\\\\" };
/*  31 */   protected static String[] SESSION_SUBST_VARS = new String[] { "_col_", "_ast_", "_que_", "_quo_", "_ltn_", "_gtn_", "_ver_", 
/*  32 */       "_sla_", "_bsl_" };
/*     */   
/*     */   protected String m_SessionCode;
/*     */   protected int m_ContextIndex;
/*     */   protected String m_InstanceId;
/*  37 */   HazelcastInstance cache = null;
/*     */   
/*     */   protected static IClientContext m_ClientContext;
/*     */ 
/*     */   
/*     */   protected static String getSpecializedSessionCode(String orgSessionCode) {
/*  43 */     if (orgSessionCode == null) {
/*  44 */       return "NL_SES";
/*     */     }
/*  46 */     String speSessionCode = orgSessionCode;
/*     */     
/*  48 */     for (int i = 0; i < SESSION_FORBIDDEN_VARS.length; i++) {
/*  49 */       speSessionCode = speSessionCode.replaceAll(SESSION_FORBIDDEN_VARS[i], SESSION_SUBST_VARS[i]);
/*     */     }
/*  51 */     return speSessionCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HazelcastInstance createCache(String name, String instanceName) {
/*     */     try {
/*  60 */       Config cfg = (new XmlConfigBuilder("D:/projects/Unity_GIT_Set/jaf/LbsTransport/com/lbs/cache/hazelcast.xml")).build();
/*     */       
/*  62 */       this.cache = Hazelcast.newHazelcastInstance(cfg);
/*     */     }
/*  64 */     catch (FileNotFoundException e) {
/*     */       
/*  66 */       LbsConsole.getLogger(getClass()).error(e, e);
/*     */     } 
/*     */ 
/*     */     
/*  70 */     System.out.println("Created cache : " + instanceName + " by thread " + Thread.currentThread().getName());
/*  71 */     return this.cache;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, V> ICachedHashTable<T, V> getCachedHashTable(String name, Class<T> keyClass, Class<V> valueClass, boolean domainAware) {
/*  77 */     return new LbsHCHashTable<>(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, domainAware);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> ICachedList<T> getCachedList(String name, Class<T> valueClass, boolean domainAware) {
/*  83 */     return (ICachedList<T>)new LbsHCList(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, null, domainAware);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> ICachedIdentifiableList<T> getCachedList(String name, Class<T> valueClass, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/*  90 */     return new LbsHCList<>(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, itemIdentifierProvider, domainAware);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IClientContext getClientContext() {
/*  95 */     return m_ClientContext;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setClientContext(IClientContext context) {
/* 100 */     m_ClientContext = context;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getEHCacheManager() {
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEHCacheDisabled() {
/* 112 */     return JLbsConstants.DISABLE_EH_CACHE;
/*     */   }
/*     */   
/*     */   public void releaseCachedHashTable(ICachedHashTable<?, ?> table) {}
/*     */   
/*     */   public void releaseCachedList(ICachedList<?> list) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsHCManagerBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */