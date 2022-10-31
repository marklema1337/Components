/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
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
/*     */ public final class JLbsVersionBasedCache<K>
/*     */   extends JLbsCache<K, Object>
/*     */ {
/*     */   private IVersionSource m_VersionSource;
/*     */   
/*     */   public JLbsVersionBasedCache(JLbsCacheScope scope, IVersionSource versionSource) {
/*  29 */     this("", scope, versionSource);
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
/*     */   public JLbsVersionBasedCache(String nameExtension, JLbsCacheScope scope, IVersionSource versionSource) {
/*  43 */     super(createNameExtension(versionSource, nameExtension), scope);
/*  44 */     this.m_VersionSource = versionSource;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String createNameExtension(IVersionSource versionSource, String nameExtension) {
/*  49 */     String name = "";
/*  50 */     if (versionSource != null && versionSource.getName() != null)
/*     */     {
/*  52 */       name = String.valueOf(name) + versionSource.getName();
/*     */     }
/*  54 */     if (!StringUtil.isEmpty(nameExtension))
/*  55 */       name = String.valueOf(name) + "_" + nameExtension; 
/*  56 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(K key, String group) {
/*     */     try {
/*  63 */       Object object = super.get(key, group);
/*  64 */       if (object instanceof VersionedObject) {
/*  65 */         return ((VersionedObject)object).innerObject;
/*     */       }
/*  67 */       return object;
/*     */     }
/*  69 */     catch (Exception e) {
/*     */       
/*  71 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void put(K key, Object object, String group) {
/*  77 */     if (this.m_VersionSource != null)
/*     */     {
/*  79 */       object = new VersionedObject(object, this.m_VersionSource);
/*     */     }
/*  81 */     super.put(key, object, group);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onValidateCachedObject(K key, String group, Object object) {
/*  86 */     if (this.m_VersionSource != null && object instanceof VersionedObject) {
/*     */       
/*  88 */       VersionedObject versionedObject = (VersionedObject)object;
/*  89 */       if (versionedObject.version.equals(this.m_VersionSource.getVersion()))
/*  90 */         return true; 
/*  91 */       if (getCacheListener() != null) {
/*     */         
/*  93 */         boolean valid = getCacheListener().isValid(key, group, versionedObject.innerObject);
/*     */         
/*  95 */         if (valid)
/*  96 */           put(key, versionedObject.innerObject, group); 
/*  97 */         return valid;
/*     */       } 
/*     */       
/* 100 */       return false;
/*     */     } 
/*     */     
/* 103 */     return super.onValidateCachedObject(key, group, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPersistent() {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public static class VersionedObject
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     public Object innerObject;
/*     */     public String version;
/*     */     public String versionSourceName;
/*     */     
/*     */     public VersionedObject(Object innerObject, IVersionSource versionSource) {
/* 120 */       this.innerObject = innerObject;
/* 121 */       this.version = versionSource.getVersion();
/* 122 */       this.versionSourceName = versionSource.getName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsVersionBasedCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */