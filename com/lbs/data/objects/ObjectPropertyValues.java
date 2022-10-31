/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Enumeration;
/*     */ import java.util.concurrent.CopyOnWriteArraySet;
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
/*     */ public class ObjectPropertyValues
/*     */   extends ObjectPropertyList
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected ObjectPropertyList m_InitialValues;
/*     */   protected boolean m_Enabled;
/*  24 */   protected transient CopyOnWriteArraySet<IBusinessObjectChangeListener> m_ChangeListeners = new CopyOnWriteArraySet<>();
/*     */ 
/*     */   
/*     */   public ObjectPropertyValues() {
/*  28 */     this.m_InitialValues = new ObjectPropertyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  33 */     return this.m_Enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/*  38 */     this.m_Enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(String name, Object value, boolean localCopy) {
/*  46 */     if (this.m_InitialValues != null && this.m_Enabled) {
/*     */       
/*  48 */       Object initialValue = this.m_InitialValues.m_Items.get(name);
/*     */       
/*  50 */       if (initialValue == null) {
/*     */         
/*  52 */         Object prevValue = getProperty(name);
/*  53 */         this.m_InitialValues.set(name, prevValue);
/*     */       } 
/*     */     } 
/*     */     
/*  57 */     super.setProperty(name, value, localCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChanged() {
/*  62 */     if (this.m_InitialValues == null) {
/*  63 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  67 */     Enumeration<String> propNames = this.m_InitialValues.properties();
/*     */     
/*  69 */     while (propNames.hasMoreElements()) {
/*     */       
/*  71 */       String key = propNames.nextElement();
/*  72 */       Object value = this.m_InitialValues.getProperty(key);
/*     */       
/*  74 */       Object propVal = getProperty(key);
/*     */       
/*  76 */       if (!ObjectValueManager.compareMemberValues(propVal, value))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  81 */         return true;
/*     */       }
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectPropertyList getInitialValues() {
/*  89 */     return this.m_InitialValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener) {
/*  94 */     this.m_ChangeListeners.remove(changeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/*  99 */     this.m_ChangeListeners.remove(new BusinessObjectMemberListener(changeListener, memberName));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener) {
/* 104 */     this.m_ChangeListeners.add(changeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/* 109 */     this.m_ChangeListeners.add(new BusinessObjectMemberListener(changeListener, memberName));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 114 */     super.writeExternal(out);
/* 115 */     out.writeBoolean(this.m_Enabled);
/* 116 */     out.writeBoolean(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 121 */     super.readExternal(in);
/* 122 */     this.m_Enabled = in.readBoolean();
/* 123 */     if (in.readBoolean() && this.m_InitialValues != null) {
/* 124 */       this.m_InitialValues.readExternal(in);
/*     */     }
/* 126 */     this.m_ChangeListeners = new CopyOnWriteArraySet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out, boolean bExternalizeInitialValues) throws IOException {
/* 135 */     super.writeExternal(out);
/* 136 */     out.writeBoolean(this.m_Enabled);
/* 137 */     out.writeBoolean(bExternalizeInitialValues);
/* 138 */     if (this.m_InitialValues != null && bExternalizeInitialValues)
/* 139 */       this.m_InitialValues.writeExternal(out); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectPropertyValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */