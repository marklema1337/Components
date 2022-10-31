/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
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
/*     */ 
/*     */ public class BusinessObjectChanges
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   protected ObjectPropertyList m_ChangedValues = new ObjectPropertyList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(BasicBusinessObject obj) {
/*  43 */     if (obj == null) {
/*     */       return;
/*     */     }
/*  46 */     BusinessObject bObj = (obj instanceof BusinessObject) ? (BusinessObject)obj : null;
/*     */ 
/*     */     
/*  49 */     CustomBusinessObject cObj = (obj instanceof CustomBusinessObject) ? (CustomBusinessObject)obj : null;
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (cObj == null && bObj == null) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/*  58 */       if (bObj != null) {
/*     */         
/*  60 */         bObj.getInitialValues().clear(bObj);
/*  61 */         bObj.getInitialValues().setEnabled(false);
/*     */       } 
/*     */       
/*  64 */       Enumeration<?> propEnum = this.m_ChangedValues.properties();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  69 */       while (propEnum.hasMoreElements())
/*     */       {
/*  71 */         String propName = (String)propEnum.nextElement();
/*  72 */         Object propValue = this.m_ChangedValues.getValue(propName);
/*     */ 
/*     */         
/*     */         try {
/*  76 */           if (propValue instanceof BusinessObjectChanges) {
/*     */             
/*  78 */             BusinessObjectChanges objChanges = (BusinessObjectChanges)propValue;
/*  79 */             BasicBusinessObject propObj = (BasicBusinessObject)ObjectValueManager.getMemberValue(bObj, propName);
/*     */             
/*  81 */             objChanges.apply(propObj); continue;
/*     */           } 
/*  83 */           if (propValue instanceof BusinessObjectChanges[]) {
/*     */             
/*  85 */             BusinessObjectChanges[] itemChanges = (BusinessObjectChanges[])propValue;
/*     */             
/*  87 */             BasicBusinessObjects<?> items = (BasicBusinessObjects)ObjectValueManager.getMemberValue(bObj, propName);
/*     */             
/*  89 */             if (items == null || items.size() != itemChanges.length) {
/*     */               continue;
/*     */             }
/*  92 */             for (int i = 0; i < itemChanges.length; i++)
/*  93 */               itemChanges[i].apply((BasicBusinessObject)items.itemAt(i));  continue;
/*     */           } 
/*  95 */           if (JLbsStringUtil.areEqual(propName, "!_éRECORD_WARNINGé_!")) {
/*  96 */             obj.getProperties().set("!_éRECORD_WARNINGé_!", propValue); continue;
/*     */           } 
/*  98 */           ObjectUtil.setProperty(obj, propName, propValue, -1);
/*     */         }
/* 100 */         catch (Exception e) {
/*     */           
/* 102 */           if (!StringUtil.equals(propName, "DestinationCrossRef")) {
/* 103 */             LbsConsole.getLogger("Data.Client.BOChanges")
/* 104 */               .error("BusinessObjectChanges.apply propName='" + propName + "'", e);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 112 */       if (bObj != null) {
/* 113 */         bObj.getInitialValues().setEnabled(true);
/*     */       }
/* 115 */       if (cObj != null) {
/* 116 */         cObj.getProperties().getInitialValues().clear(cObj);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 124 */       BusinessObjectChanges changes = new BusinessObjectChanges();
/* 125 */       changes.m_ChangedValues = (ObjectPropertyList)this.m_ChangedValues.clone();
/* 126 */       return changes;
/*     */     }
/* 128 */     catch (Exception e) {
/*     */       
/* 130 */       LbsConsole.getLogger("Data.Client.BusinessObjectChangess").error("[BusinessObjectChanges.clone()]", e);
/* 131 */       throw new AssertionError();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectChanges.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */