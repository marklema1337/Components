/*     */ package com.lbs.data.locks;
/*     */ 
/*     */ import com.lbs.data.factory.FactoryParams;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
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
/*     */ public class JLbsClientLockManager
/*     */ {
/*     */   public static boolean CLIENT_LOCKS = false;
/*     */   
/*     */   protected static String getClientLockID(BusinessObject obj) {
/*  26 */     String template = obj.getLockIdTemplate();
/*  27 */     int idx = template.indexOf("-$V(key)");
/*  28 */     if (idx != -1) {
/*  29 */       template = template.substring(0, idx) + obj._getKey();
/*     */     }
/*  31 */     return template;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static FactoryParams getFactoryParams(Object[] callParams, int index) {
/*  36 */     if (callParams == null) {
/*  37 */       return null;
/*     */     }
/*  39 */     if (index != -1)
/*     */     {
/*  41 */       if (callParams.length > index && callParams[index] instanceof FactoryParams) {
/*  42 */         return (FactoryParams)callParams[index];
/*     */       }
/*     */     }
/*  45 */     for (int i = 0; i < callParams.length; i++) {
/*     */       
/*  47 */       if (callParams[i] instanceof FactoryParams) {
/*  48 */         return (FactoryParams)callParams[i];
/*     */       }
/*     */     } 
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object preProcessParams(Object[] callParams, int index, BasicBusinessObject obj) {
/*  56 */     if (!CLIENT_LOCKS) {
/*  57 */       return null;
/*     */     }
/*  59 */     FactoryParams params = getFactoryParams(callParams, index);
/*  60 */     return preProcessParams(params, (BusinessObject)obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void postProcessParams(Object[] callParams, int index, BasicBusinessObject obj) {
/*  65 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/*  68 */     FactoryParams params = getFactoryParams(callParams, index);
/*  69 */     postProcessParams(params, (BusinessObject)obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void undoProcessParams(Object[] callParams, int index, BasicBusinessObject obj, Object undoInfo) {
/*  74 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/*  77 */     FactoryParams params = getFactoryParams(callParams, index);
/*  78 */     undoProcessParams(params, (BusinessObject)obj, undoInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object preProcessParams(Object[] callParams, int index, Object[] objs) {
/*  83 */     if (!CLIENT_LOCKS) {
/*  84 */       return null;
/*     */     }
/*  86 */     Object[] undoInfos = new Object[objs.length];
/*  87 */     for (int i = 0; i < objs.length; i++) {
/*  88 */       undoInfos[i] = preProcessParams(callParams, index, (BasicBusinessObject)objs[i]);
/*     */     }
/*  90 */     return undoInfos;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void postProcessParams(Object[] callParams, int index, Object[] objs) {
/*  95 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/*  98 */     for (int i = 0; i < objs.length; i++) {
/*  99 */       postProcessParams(callParams, index, (BasicBusinessObject)objs[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void undoProcessParams(Object[] callParams, int index, BasicBusinessObject[] objs, Object undoInfo) {
/* 104 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/* 107 */     Object[] arrInfo = null;
/* 108 */     if (undoInfo instanceof Object[]) {
/* 109 */       arrInfo = (Object[])undoInfo;
/*     */     }
/* 111 */     for (int i = 0; i < objs.length; i++) {
/* 112 */       undoProcessParams(callParams, index, objs[i], arrInfo[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Object preProcessParams(FactoryParams params, BusinessObject obj) {
/* 117 */     if (!CLIENT_LOCKS) {
/* 118 */       return null;
/*     */     }
/* 120 */     if (params.getRequestLock()) {
/*     */       
/* 122 */       String lockID = getClientLockID(obj);
/*     */       
/* 124 */       JLbsClientLockManagerFieldHolder.getInstance(); synchronized (JLbsClientLockManagerFieldHolder.m_Locks) {
/*     */         
/* 126 */         JLbsClientLockManagerFieldHolder.getInstance(); if (JLbsClientLockManagerFieldHolder.m_Locks.containsKey(lockID)) {
/*     */           
/* 128 */           JLbsClientLockManagerFieldHolder.getInstance(); Integer refCount = (Integer)JLbsClientLockManagerFieldHolder.m_Locks.get(lockID);
/* 129 */           JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.put(lockID, Integer.valueOf(refCount.intValue() + 1));
/*     */           
/* 131 */           params.setRequestLock(false);
/*     */         }
/*     */         else {
/*     */           
/* 135 */           JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.put(lockID, Integer.valueOf(1));
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       return new Boolean(true);
/*     */     } 
/*     */     
/* 142 */     return new Boolean(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void undoProcessParams(FactoryParams params, BusinessObject obj, Object undoInfo) {
/* 147 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/* 150 */     if (undoInfo instanceof Boolean) {
/*     */       
/* 152 */       Boolean ok = (Boolean)undoInfo;
/* 153 */       if (ok.booleanValue()) {
/*     */         
/* 155 */         String lockID = getClientLockID(obj);
/* 156 */         JLbsClientLockManagerFieldHolder.getInstance(); synchronized (JLbsClientLockManagerFieldHolder.m_Locks) {
/*     */           
/* 158 */           JLbsClientLockManagerFieldHolder.getInstance(); if (JLbsClientLockManagerFieldHolder.m_Locks.containsKey(lockID)) {
/*     */             
/* 160 */             JLbsClientLockManagerFieldHolder.getInstance(); Integer refCount = (Integer)JLbsClientLockManagerFieldHolder.m_Locks.get(lockID);
/* 161 */             refCount = Integer.valueOf(refCount.intValue() - 1);
/*     */             
/* 163 */             if (refCount.intValue() > 0) {
/* 164 */               JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.put(lockID, refCount);
/*     */             } else {
/* 166 */               JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.remove(lockID);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void postProcessParams(FactoryParams params, BusinessObject obj) {
/* 175 */     if (!CLIENT_LOCKS) {
/*     */       return;
/*     */     }
/* 178 */     if (params.getReleaseLock()) {
/*     */       
/* 180 */       String lockID = getClientLockID(obj);
/*     */       
/* 182 */       JLbsClientLockManagerFieldHolder.getInstance(); synchronized (JLbsClientLockManagerFieldHolder.m_Locks) {
/*     */         
/* 184 */         JLbsClientLockManagerFieldHolder.getInstance(); if (JLbsClientLockManagerFieldHolder.m_Locks.containsKey(lockID)) {
/*     */           
/* 186 */           JLbsClientLockManagerFieldHolder.getInstance(); Integer refCount = (Integer)JLbsClientLockManagerFieldHolder.m_Locks.get(lockID);
/* 187 */           refCount = Integer.valueOf(refCount.intValue() - 1);
/*     */           
/* 189 */           if (refCount.intValue() > 0) {
/*     */             
/* 191 */             JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.put(lockID, refCount);
/* 192 */             params.setReleaseLock(false);
/*     */           }
/*     */           else {
/*     */             
/* 196 */             JLbsClientLockManagerFieldHolder.getInstance(); JLbsClientLockManagerFieldHolder.m_Locks.remove(lockID);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class JLbsClientLockManagerFieldHolder
/*     */   {
/* 205 */     protected static Hashtable m_Locks = new Hashtable<>();
/*     */ 
/*     */     
/*     */     private static JLbsClientLockManagerFieldHolder getInstance() {
/* 209 */       return (JLbsClientLockManagerFieldHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsClientLockManagerFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\locks\JLbsClientLockManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */