/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.BusinessObjectEvent;
/*     */ import com.lbs.data.objects.BusinessObjects;
/*     */ import com.lbs.data.objects.ObjectValueManager;
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.ArrayList;
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
/*     */ public class BusinessObjectRecoverer
/*     */ {
/*     */   public static ArrayList getObjectsToRecover() {
/*  28 */     return getObjectsToRecover(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList getObjectsToRecover(String formName) {
/*  33 */     ArrayList<BusinessObjectChangeLog> list = new ArrayList();
/*     */ 
/*     */     
/*     */     try {
/*  37 */       String dirPath = JLbsClientFS.getFullPath("BODrafts");
/*  38 */       File logDirectory = new File(dirPath);
/*  39 */       File[] contents = logDirectory.listFiles(getFileFilter(formName));
/*  40 */       if (contents != null)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  45 */         for (int i = 0; i < contents.length; i++) {
/*     */           
/*  47 */           File file = contents[i];
/*     */           
/*  49 */           try(FileInputStream fin = new FileInputStream(file); 
/*  50 */               ObjectInputStream oin = new ObjectInputStream(fin)) {
/*     */             
/*  52 */             Object obj = oin.readObject();
/*  53 */             if (obj instanceof BusinessObjectChangeHeader)
/*     */             {
/*  55 */               BusinessObjectChangeLog log = new BusinessObjectChangeLog((BusinessObjectChangeHeader)obj);
/*  56 */               boolean loop = true;
/*  57 */               while (loop) {
/*     */ 
/*     */                 
/*     */                 try {
/*  61 */                   obj = oin.readObject();
/*  62 */                   if (obj instanceof BusinessObjectEvent) {
/*  63 */                     log.addToChangeEvents((BusinessObjectEvent)obj);
/*     */                   }
/*  65 */                 } catch (Exception e1) {
/*     */                   
/*  67 */                   loop = false;
/*     */                 } 
/*     */               } 
/*  70 */               list.add(log);
/*     */             }
/*     */           
/*  73 */           } catch (Exception e) {
/*     */             
/*  75 */             LbsConsole.getLogger("Data.Client.BORecoverer").error(null, e);
/*     */           }
/*     */         
/*     */         } 
/*     */       }
/*  80 */     } catch (Exception e) {
/*     */       
/*  82 */       LbsConsole.getLogger("Data.Client.BORecoverer").error(null, e);
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   private static FilenameFilter getFileFilter(final String formName) {
/*  91 */     if (StringUtil.isEmpty(formName)) {
/*  92 */       return null;
/*     */     }
/*  94 */     FilenameFilter filter = new FilenameFilter()
/*     */       {
/*     */ 
/*     */         
/*     */         public boolean accept(File dir, String name)
/*     */         {
/* 100 */           return name.startsWith(formName + "_");
/*     */         }
/*     */       };
/* 103 */     return filter;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BasicBusinessObject recoverBusinessObjectFromLog(BusinessObjectChangeLog log) {
/* 108 */     BasicBusinessObject baseObj = log.getHeader().getInitialObject();
/*     */     
/*     */     try {
/* 111 */       if (baseObj != null)
/*     */       {
/* 113 */         BasicBusinessObject obj = (BasicBusinessObject)ObjectUtil.createCopy(baseObj, true);
/* 114 */         ArrayList<BusinessObjectEvent> changeEvents = log.getChangeEvents();
/*     */ 
/*     */         
/* 117 */         for (int i = 0; i < changeEvents.size(); i++) {
/*     */           Object member; BusinessObjects coll; BusinessObject value; int idx;
/* 119 */           BusinessObjectEvent event = changeEvents.get(i);
/* 120 */           switch (event.getReason()) {
/*     */             
/*     */             case 1:
/* 123 */               ObjectValueManager.setMemberValue(obj, event.getMemberName(), event.getValue());
/*     */               break;
/*     */             case 2:
/* 126 */               member = ObjectValueManager.getMemberValue(obj, event.getMemberName());
/* 127 */               event.invoke(member);
/*     */               break;
/*     */             case 4:
/* 130 */               coll = (BusinessObjects)ObjectValueManager.getMemberValue(obj, event.getMemberName());
/* 131 */               value = (BusinessObject)event.getValue();
/* 132 */               if (value != null) {
/*     */                 
/* 134 */                 int j = event.getItemIndex();
/* 135 */                 if (j >= 0) {
/* 136 */                   coll.add(j, value); break;
/*     */                 } 
/* 138 */                 coll.add((SimpleBusinessObject)value);
/*     */               } 
/*     */               break;
/*     */             case 5:
/* 142 */               coll = (BusinessObjects)ObjectValueManager.getMemberValue(obj, event.getMemberName());
/* 143 */               idx = event.getItemIndex();
/* 144 */               if (idx >= 0) {
/* 145 */                 coll.remove(idx);
/*     */                 break;
/*     */               } 
/* 148 */               value = (BusinessObject)event.getValue();
/* 149 */               if (value != null) {
/* 150 */                 coll.remove(value); break;
/*     */               } 
/* 152 */               LbsConsole.getLogger("Data.Client.BORecoverer").trace("AADM: List item cannot be removed, list name:'" + event
/* 153 */                   .getMemberName() + "'");
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/* 158 */         return obj;
/*     */       }
/*     */     
/* 161 */     } catch (Exception e) {
/*     */       
/* 163 */       LbsConsole.getLogger("Data.Client.BORecoverer").error(null, e);
/*     */     } 
/* 165 */     return baseObj;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\BusinessObjectRecoverer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */