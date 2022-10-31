/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public final class JLbsSessionBasedCache<K, V>
/*     */   extends JLbsCache<K, V>
/*     */ {
/*  23 */   private ISessionListener m_Listener = new ISessionListener()
/*     */     {
/*     */       public void sessionCreated()
/*     */       {
/*  27 */         JLbsSessionBasedCache.this.reinitialize();
/*     */       }
/*     */ 
/*     */       
/*     */       public void sessionDestroyed() {
/*  32 */         JLbsSessionBasedCache.this.clear();
/*  33 */         JLbsSessionBasedCache.this.destroy();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public void sessionRecovered() {}
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsSessionBasedCache(JLbsCacheScope scope, JLbsSessionInfoProvider sessionInfoProvider) {
/*  51 */     this("", scope, sessionInfoProvider);
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
/*     */   public JLbsSessionBasedCache(String nameExtension, JLbsCacheScope scope, JLbsSessionInfoProvider sessionInfoProvider) {
/*  64 */     super(createNameExtension(sessionInfoProvider, nameExtension), scope);
/*  65 */     sessionInfoProvider.addInfoListener(this.m_Listener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String createNameExtension(JLbsSessionInfoProvider sessionInfoProvider, String nameExtension) {
/*  71 */     if (sessionInfoProvider == null)
/*  72 */       throw new IllegalArgumentException(
/*  73 */           "sessionInfoProvider cannot be null! Or check if you really need SessionBasedCache. See ManuallyControlledCache"); 
/*  74 */     if (StringUtil.isEmpty(sessionInfoProvider.getSessionID())) {
/*  75 */       throw new IllegalArgumentException("sessionID of sessionInfoProvider cannot be null! Or check if you really need SessionBasedCache. See ManuallyControlledCache");
/*     */     }
/*  77 */     String name = sessionInfoProvider.getSessionID();
/*     */     
/*  79 */     if (!StringUtil.isEmpty(nameExtension))
/*  80 */       name = String.valueOf(name) + "_" + nameExtension; 
/*  81 */     return name;
/*     */   }
/*     */   
/*     */   public static class JLbsSessionInfoProvider
/*     */   {
/*     */     private Vector m_SessionListeners;
/*     */     private String m_SessionID;
/*     */     private static final String NULL_SESSION = "NULL_SESSION";
/*  89 */     private static final String[] SESSION_FORBIDDEN_VARS = new String[] { ":", "\\*", "\\?", "\"", "<", ">", "\\|", "/", "\\\\" };
/*  90 */     private static final String[] SESSION_SUBST_VARS = new String[] { "_col_", "_ast_", "_que_", "_quo_", "_ltn_", "_gtn_", 
/*  91 */         "_ver_", "_sla_", "_bsl_" };
/*     */ 
/*     */     
/*     */     public JLbsSessionInfoProvider(String sessionID) {
/*  95 */       this.m_SessionID = sessionID;
/*     */     }
/*     */ 
/*     */     
/*     */     synchronized void addInfoListener(ISessionListener l) {
/* 100 */       if (this.m_SessionListeners == null)
/* 101 */         this.m_SessionListeners = new Vector(); 
/* 102 */       this.m_SessionListeners.addElement(new WeakReference<>(l));
/*     */     }
/*     */ 
/*     */     
/*     */     synchronized void removeInfoListener(ISessionListener l) {
/* 107 */       if (this.m_SessionListeners == null)
/* 108 */         this.m_SessionListeners = new Vector(); 
/* 109 */       for (int i = 0; i < this.m_SessionListeners.size(); i++) {
/*     */         
/* 111 */         Object object = ((WeakReference)this.m_SessionListeners.get(i)).get();
/* 112 */         if (object == l) {
/*     */           
/* 114 */           this.m_SessionListeners.remove(i);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void fireSessionRecovered() {
/* 122 */       Vector targets = null;
/* 123 */       synchronized (this) {
/*     */         
/* 125 */         if (this.m_SessionListeners != null && !this.m_SessionListeners.isEmpty())
/*     */         {
/*     */ 
/*     */           
/* 129 */           targets = (Vector)this.m_SessionListeners.clone();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 134 */       if (targets != null) {
/*     */         
/* 136 */         Enumeration<WeakReference<ISessionListener>> e = targets.elements();
/* 137 */         while (e.hasMoreElements()) {
/*     */           
/* 139 */           ISessionListener l = ((WeakReference<ISessionListener>)e.nextElement()).get();
/*     */ 
/*     */           
/* 142 */           if (l != null) {
/* 143 */             l.sessionRecovered();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void fireSessionCreated() {
/* 150 */       Vector targets = null;
/* 151 */       synchronized (this) {
/*     */         
/* 153 */         if (this.m_SessionListeners != null && !this.m_SessionListeners.isEmpty())
/*     */         {
/*     */ 
/*     */           
/* 157 */           targets = (Vector)this.m_SessionListeners.clone();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 162 */       if (targets != null) {
/*     */         
/* 164 */         Enumeration<WeakReference<ISessionListener>> e = targets.elements();
/* 165 */         while (e.hasMoreElements()) {
/*     */           
/* 167 */           ISessionListener l = ((WeakReference<ISessionListener>)e.nextElement()).get();
/*     */ 
/*     */           
/* 170 */           if (l != null) {
/* 171 */             l.sessionCreated();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void fireSessionDestroyed() {
/* 179 */       Vector targets = null;
/* 180 */       synchronized (this) {
/*     */         
/* 182 */         if (this.m_SessionListeners != null && !this.m_SessionListeners.isEmpty())
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 187 */           targets = (Vector)this.m_SessionListeners.clone();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 192 */       if (targets != null) {
/*     */         
/* 194 */         Enumeration<WeakReference<ISessionListener>> e = targets.elements();
/* 195 */         while (e.hasMoreElements()) {
/*     */           
/* 197 */           ISessionListener l = ((WeakReference<ISessionListener>)e.nextElement()).get();
/*     */ 
/*     */           
/* 200 */           if (l != null) {
/* 201 */             l.sessionDestroyed();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
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
/*     */     public String getSessionID() {
/* 217 */       return this.m_SessionID;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static String getEscapedSessionID(String orgSessionID) {
/* 226 */       if (StringUtil.isEmpty(orgSessionID)) {
/* 227 */         return "NULL_SESSION";
/*     */       }
/* 229 */       String speSessionCode = orgSessionID;
/*     */       
/* 231 */       for (int i = 0; i < SESSION_FORBIDDEN_VARS.length; i++) {
/* 232 */         speSessionCode = speSessionCode.replaceAll(SESSION_FORBIDDEN_VARS[i], SESSION_SUBST_VARS[i]);
/*     */       }
/* 234 */       return speSessionCode;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static String getOriginalSessionID(String escapedSessionID) {
/* 243 */       String orgSessionCode = escapedSessionID;
/*     */       
/* 245 */       for (int i = 0; i < SESSION_SUBST_VARS.length; i++) {
/* 246 */         orgSessionCode = orgSessionCode.replaceAll(SESSION_SUBST_VARS[i], SESSION_FORBIDDEN_VARS[i]);
/*     */       }
/* 248 */       return orgSessionCode;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsSessionBasedCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */