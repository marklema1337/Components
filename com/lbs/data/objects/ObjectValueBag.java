/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.console.LbsLevel;
/*     */ import com.lbs.data.multilang.BusinessObjectModification;
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
/*     */ public class ObjectValueBag
/*     */   extends ObjectPropertyList
/*     */   implements Serializable, Cloneable, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected boolean m_Enabled = true;
/*  26 */   private static transient LbsConsole ms_Logger = null;
/*     */ 
/*     */   
/*     */   private static LbsConsole getLogger() {
/*  30 */     if (ms_Logger == null)
/*     */     {
/*  32 */       synchronized (ObjectValueBag.class) {
/*     */         
/*  34 */         ms_Logger = LbsConsole.getLogger("ObjectValueBag");
/*     */       } 
/*     */     }
/*  37 */     return ms_Logger;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addPropertyValue(String columnName, String propertyName, Object value, boolean copy) {
/*  43 */     if (!this.m_Enabled) {
/*     */       return;
/*     */     }
/*  46 */     if (containsProperty(columnName)) {
/*     */       return;
/*     */     }
/*  49 */     Object o = null;
/*     */     
/*  51 */     if (copy) {
/*  52 */       o = ObjectUtil.createCopy(value);
/*     */     } else {
/*  54 */       o = value;
/*     */     } 
/*  56 */     ObjectValueEntry entry = new ObjectValueEntry(propertyName, o);
/*     */     
/*  58 */     addProperty(columnName, entry, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, Object value) {
/*  63 */     addPropertyValue(columnName, propertyName, value, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, boolean value) {
/*  68 */     addPropertyValue(columnName, propertyName, new Boolean(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, char value) {
/*  73 */     addPropertyValue(columnName, propertyName, new Character(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, byte value) {
/*  78 */     addPropertyValue(columnName, propertyName, Byte.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, short value) {
/*  83 */     addPropertyValue(columnName, propertyName, Short.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, int value) {
/*  88 */     addPropertyValue(columnName, propertyName, Integer.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, long value) {
/*  93 */     addPropertyValue(columnName, propertyName, Long.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, double value) {
/*  98 */     addPropertyValue(columnName, propertyName, new Double(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String columnName, String propertyName, float value) {
/* 103 */     addPropertyValue(columnName, propertyName, new Float(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue(String columnName) {
/* 108 */     if (containsProperty(columnName)) {
/*     */       
/* 110 */       ObjectValueEntry entry = (ObjectValueEntry)super.getValue(columnName);
/*     */       
/* 112 */       if (entry != null) {
/* 113 */         return entry.Value;
/*     */       }
/*     */     } 
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChanged(BusinessObject bagOwner) {
/* 123 */     Enumeration<String> propNames = properties();
/*     */     
/* 125 */     while (propNames.hasMoreElements()) {
/*     */       
/* 127 */       Object key = propNames.nextElement();
/* 128 */       ObjectValueEntry entry = (ObjectValueEntry)super.getValue((String)key);
/*     */       
/* 130 */       if (entry == null) {
/*     */         continue;
/*     */       }
/* 133 */       if (!StringUtil.isEmpty(entry.PropertyName)) {
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 139 */           Object propVal = ObjectValueManager.getMemberValue(bagOwner, entry.PropertyName);
/* 140 */           if (!ObjectValueManager.compareMemberValues(propVal, entry.Value))
/*     */           {
/* 142 */             if (getLogger().isEnabledFor2(LbsLevel.TRACE))
/* 143 */               getLogger().trace("CHANGE : property '" + entry.PropertyName + "' : " + propVal + " <> " + entry.Value); 
/* 144 */             return true;
/*     */           }
/*     */         
/* 147 */         } catch (Exception e) {
/*     */           
/* 149 */           getLogger().error(null, e);
/* 150 */           return false;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 155 */     if (BusinessObjectModification.hasChanged(bagOwner)) {
/* 156 */       return true;
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(BasicBusinessObject obj) {
/* 164 */     clear();
/* 165 */     if (obj instanceof BusinessObject) {
/* 166 */       BusinessObjectModification.clearChangedMultilangValues((BusinessObject)obj);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 175 */     return this.m_Enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 184 */     this.m_Enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 192 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectValueBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */