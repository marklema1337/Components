/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import java.util.Enumeration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectCopyEnvironment
/*     */ {
/*  24 */   protected Hashtable m_Hashtable = new Hashtable<>();
/*     */   protected IObjectCopyListener m_Listener;
/*     */   protected Hashtable m_HandledClasses;
/*     */   
/*     */   protected Hashtable getHandledClasses() {
/*  29 */     if (this.m_HandledClasses == null) {
/*  30 */       this.m_HandledClasses = new Hashtable<>();
/*     */     }
/*  32 */     return this.m_HandledClasses;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(Object src, Object dst) {
/*  37 */     if (src == null || dst == null) {
/*     */       return;
/*     */     }
/*     */     
/*  41 */     this.m_Hashtable.put(src, dst);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(Object src) {
/*  46 */     Object obj = this.m_Hashtable.get(src);
/*     */     
/*  48 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public IObjectCopyListener getListener() {
/*  53 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListener(IObjectCopyListener listener) {
/*  58 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object src) {
/*  63 */     return this.m_Hashtable.containsKey(src);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addHandled(Class<?> clazz) {
/*  68 */     getHandledClasses().put(clazz, new Boolean(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHandled(Class clazz) {
/*  73 */     String className = clazz.getName();
/*     */     
/*  75 */     if (!className.startsWith("com.lbs")) {
/*  76 */       return true;
/*     */     }
/*  78 */     if (this.m_HandledClasses == null) {
/*  79 */       return true;
/*     */     }
/*  81 */     return this.m_HandledClasses.contains(clazz);
/*     */   }
/*     */ 
/*     */   
/*     */   public BusinessObject getKey(Class objClass, int lRef) {
/*  86 */     Enumeration keys = this.m_Hashtable.keys();
/*     */ 
/*     */     
/*  89 */     while (keys.hasMoreElements()) {
/*     */       
/*  91 */       Object obj = keys.nextElement();
/*     */       
/*  93 */       if (obj instanceof BusinessObject) {
/*     */         
/*  95 */         BusinessObject bObj = (BusinessObject)obj;
/*     */         
/*  97 */         if (bObj.getClass().equals(objClass) && bObj.getUniqueIdentifier().getSimpleKey() == lRef) {
/*  98 */           return bObj;
/*     */         }
/*     */       } 
/*     */     } 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Class objClass, int key) {
/* 107 */     Enumeration keys = this.m_Hashtable.keys();
/*     */ 
/*     */     
/* 110 */     while (keys.hasMoreElements()) {
/*     */       
/* 112 */       Object obj = keys.nextElement();
/*     */       
/* 114 */       if (obj instanceof BusinessObject) {
/*     */         
/* 116 */         BusinessObject bObj = (BusinessObject)obj;
/*     */         
/* 118 */         if (bObj.getClass().equals(objClass) && bObj.getUniqueIdentifier().getSimpleKey() == key) {
/* 119 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ObjectCopyEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */