/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
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
/*     */ public abstract class BasicBusinessObject
/*     */   implements Serializable, Cloneable, IBusinessObjectStates, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   public static final Calendar MIN_DATE = JLbsConstants.MIN_DATE;
/*  32 */   public static final Timestamp MIN_TIMESTAMP = JLbsConstants.MIN_TIMESTAMP;
/*  33 */   public static final BigDecimal DECIMAL_ZERO = new BigDecimal(0);
/*     */   
/*     */   public static final int SUPPORT_INITIAL_VALUES = 1;
/*     */   
/*     */   public static final int SUPPORT_EXTENSIONS = 2;
/*     */   
/*     */   public static final int NONE_MANUELSET = 0;
/*     */   public static final int CREATEDBY_MANUELSET = 1;
/*     */   public static final int CREATEDON_MANUELSET = 2;
/*     */   public static final int MODIFIEDBY_MANUELSET = 4;
/*     */   public static final int MODIFIEDON_MANUELSET = 8;
/*     */   public static final int RECSTATUS_MANUELSET = 22;
/*  45 */   private int m_RecordFieldOptions = 0;
/*     */   
/*  47 */   private int m_ServiceType = 1;
/*     */   
/*  49 */   private HashMap<String, Object> m_OldValues = new HashMap<>();
/*  50 */   private HashMap<String, Object> m_NewValues = new HashMap<>();
/*     */   
/*     */   @XStreamOmitField
/*     */   protected transient ObjectPropertyValues m_ColorProperties;
/*     */   
/*     */   @XStreamAlias("Properties")
/*     */   protected ObjectPropertyValues m_Properties;
/*     */   
/*     */   @XStreamOmitField
/*     */   protected ObjectExtensions m_Extensions;
/*     */   
/*     */   @XStreamOmitField
/*     */   protected Object m_CustomExtensions;
/*     */   
/*     */   protected transient Object m_CustomParameters;
/*     */   
/*     */   @XStreamOmitField
/*     */   protected BasicObjectIdentifier m_UniqueIdentifier;
/*     */ 
/*     */   
/*     */   protected abstract boolean externalizeInitialValues();
/*     */ 
/*     */   
/*     */   protected abstract int _getOptions();
/*     */ 
/*     */   
/*     */   public abstract int _getState();
/*     */ 
/*     */   
/*     */   public abstract void _setState(int paramInt);
/*     */ 
/*     */   
/*     */   public abstract void resetPrimaryKeys();
/*     */   
/*     */   public abstract String getLockIdTemplate();
/*     */   
/*     */   public abstract String getLockId();
/*     */   
/*     */   public abstract void setLockId(String paramString);
/*     */   
/*     */   public abstract boolean isLocked();
/*     */   
/*     */   public abstract void setLocked(boolean paramBoolean);
/*     */   
/*     */   protected abstract void checkState();
/*     */   
/*     */   public abstract String _getObjectName();
/*     */   
/*     */   public BasicBusinessObject() {
/*  99 */     int options = _getOptions();
/* 100 */     this.m_Properties = new ObjectPropertyValues();
/* 101 */     this.m_ColorProperties = new ObjectPropertyValues();
/*     */     
/* 103 */     if ((options & 0x1) != 1) {
/* 104 */       this.m_Properties.m_InitialValues = null;
/*     */     }
/* 106 */     if ((options & 0x2) == 2) {
/* 107 */       this.m_Extensions = new ObjectExtensions();
/*     */     } else {
/* 109 */       this.m_Extensions = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean accept(IBusinessObjectVisitor visitor) {
/* 114 */     return accept(visitor, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean accept(IBusinessObjectVisitor visitor, LinkObjectSettings settings) {
/* 119 */     return visitor.visit(this, settings);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/* 125 */     if (this.m_Properties == null) {
/* 126 */       this.m_Properties = new ObjectPropertyValues();
/*     */     }
/*     */   }
/*     */   
/*     */   public BasicObjectIdentifier getUniqueIdentifier() {
/* 131 */     this.m_UniqueIdentifier.update(this);
/* 132 */     return this.m_UniqueIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 141 */     Object obj = super.clone();
/*     */     
/* 143 */     ObjectUtil.deepCopy(this, obj, BasicBusinessObject.class);
/*     */     
/* 145 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectPropertyValues getProperties() {
/* 150 */     return this.m_Properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectPropertyValues getColorProperties() {
/* 155 */     if (this.m_ColorProperties == null) {
/* 156 */       this.m_ColorProperties = new ObjectPropertyValues();
/*     */     }
/* 158 */     return this.m_ColorProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColorProperty(String propName, Object propValue) {
/* 163 */     if (this.m_ColorProperties == null) {
/* 164 */       this.m_ColorProperties = new ObjectPropertyValues();
/*     */     }
/* 166 */     if (this.m_ColorProperties.containsProperty(propName)) {
/* 167 */       this.m_ColorProperties.removeProperty(propName);
/*     */     }
/* 169 */     this.m_ColorProperties.addProperty(propName, propValue, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCustomExtensions() {
/* 174 */     return this.m_CustomExtensions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomExtensions(Object object) {
/* 179 */     this.m_CustomExtensions = object;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCustomParameters() {
/* 184 */     return this.m_CustomParameters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomParameters(Object object) {
/* 189 */     this.m_CustomParameters = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getState() {
/* 198 */     return _getState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setState(int state) {
/* 207 */     _setState(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInitialValues() {
/* 212 */     if (this.m_Properties.m_InitialValues != null) {
/* 213 */       this.m_Properties.m_InitialValues.clear(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public ObjectExtensions getExtensions() {
/* 218 */     return this.m_Extensions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectExtensions(ObjectExtensions ext) {
/* 223 */     this.m_Extensions = ext;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitialValuesEnabled(boolean enabled) {
/* 228 */     if (this.m_Properties.m_InitialValues != null) {
/* 229 */       this.m_Properties.setEnabled(enabled);
/*     */     }
/*     */   }
/*     */   
/*     */   public void assign(Object obj) {
/* 234 */     if (obj instanceof BasicBusinessObject) {
/*     */       
/* 236 */       BasicBusinessObject src = (BasicBusinessObject)obj;
/* 237 */       this.m_Properties = src.m_Properties;
/* 238 */       this.m_CustomParameters = src.m_CustomParameters;
/* 239 */       this.m_CustomExtensions = src.m_CustomExtensions;
/* 240 */       this.m_UniqueIdentifier = src.m_UniqueIdentifier;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasProperty(String name) {
/* 246 */     return this.m_Properties.containsProperty(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasColorProperty(String name) {
/* 251 */     return this.m_ColorProperties.containsProperty(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUId() {
/* 256 */     if (JLbsConstants.isEnableUUID()) {
/* 257 */       return (String)this.m_Properties.getProperty("UUID");
/*     */     }
/* 259 */     return Integer.valueOf(getUniqueIdentifier().getSimpleKey()).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUId(String id) {
/* 264 */     this.m_Properties.addProperty("UUID", id, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFlowId() {
/* 269 */     return (String)this.m_Properties.getProperty("WorkFlowInstanceID");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlowId(String id) {
/* 274 */     this.m_Properties.addProperty("WorkFlowInstanceID", id, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRecStatus() {
/* 279 */     if (this.m_Properties.getProperty("RecStatus") != null)
/* 280 */       return ((Integer)this.m_Properties.getProperty("RecStatus")).intValue(); 
/* 281 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecStatus(int status) {
/* 286 */     this.m_Properties.addProperty("RecStatus", Integer.valueOf(status), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLabels() {
/* 291 */     return (String)this.m_Properties.getProperty("TE_Labels");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLabels(String labels) {
/* 296 */     this.m_Properties.addProperty("TE_Labels", labels, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int _getTELocked() {
/*     */     try {
/* 303 */       return ((Integer)this.m_Properties.getProperty("TE_Locked")).intValue();
/*     */     }
/* 305 */     catch (Exception e) {
/*     */       
/* 307 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setTELocked(int id) {
/* 313 */     this.m_Properties.addProperty("TE_Locked", Integer.valueOf(id), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 318 */     this.m_Properties.writeExternal(out, externalizeInitialValues());
/* 319 */     if (this.m_Extensions != null) {
/* 320 */       this.m_Extensions.writeExternal(out);
/*     */     }
/*     */     
/* 323 */     out.writeObject(this.m_CustomExtensions);
/* 324 */     out.writeInt(this.m_RecordFieldOptions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 329 */     this.m_Properties.readExternal(in);
/* 330 */     if (this.m_Extensions != null) {
/* 331 */       this.m_Extensions.readExternal(in);
/*     */     }
/*     */     
/* 334 */     this.m_CustomExtensions = in.readObject();
/* 335 */     this.m_RecordFieldOptions = in.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean _isRecStatusManuelSet() {
/* 340 */     return SetUtil.isOptionSet(this.m_RecordFieldOptions, 22);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setRecStatusManuelSet(boolean recStatusByManuelSet) {
/* 345 */     this.m_RecordFieldOptions = SetUtil.toggleOption(this.m_RecordFieldOptions, 22, recStatusByManuelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean _isCreatedByManuelSet() {
/* 350 */     return SetUtil.isOptionSet(this.m_RecordFieldOptions, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setCreatedByManuelSet(boolean createdByManuelSet) {
/* 355 */     this.m_RecordFieldOptions = SetUtil.toggleOption(this.m_RecordFieldOptions, 1, createdByManuelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean _isCreatedOnManuelSet() {
/* 360 */     return SetUtil.isOptionSet(this.m_RecordFieldOptions, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setCreatedOnManuelSet(boolean createdOnManuelSet) {
/* 365 */     this.m_RecordFieldOptions = SetUtil.toggleOption(this.m_RecordFieldOptions, 2, createdOnManuelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean _isModifiedByManuelSet() {
/* 370 */     return SetUtil.isOptionSet(this.m_RecordFieldOptions, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setModifiedByManuelSet(boolean modifiedByManuelSet) {
/* 375 */     this.m_RecordFieldOptions = SetUtil.toggleOption(this.m_RecordFieldOptions, 4, modifiedByManuelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean _isModifiedOnManuelSet() {
/* 380 */     return SetUtil.isOptionSet(this.m_RecordFieldOptions, 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setModifiedOnManuelSet(boolean modifiedOnManuelSet) {
/* 385 */     this.m_RecordFieldOptions = SetUtil.toggleOption(this.m_RecordFieldOptions, 8, modifiedOnManuelSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public String localizedInfo() {
/* 390 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String extendedLocalizedInfo() {
/* 395 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getServiceType() {
/* 400 */     return this.m_ServiceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServiceType(int serviceType) {
/* 405 */     this.m_ServiceType = serviceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, Object> readOldValues() {
/* 410 */     return this.m_OldValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putOldValues(HashMap<String, Object> m_OldValues) {
/* 415 */     this.m_OldValues = m_OldValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, Object> readNewValues() {
/* 420 */     return this.m_NewValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putNewValues(HashMap<String, Object> m_NewValues) {
/* 425 */     this.m_NewValues = m_NewValues;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BasicBusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */