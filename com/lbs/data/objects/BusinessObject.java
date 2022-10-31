/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.platform.interfaces.IDataSerializer;
/*     */ import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
/*     */ import com.lbs.util.ConversionDataLoss;
/*     */ import com.lbs.util.ConversionNotSupportedException;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.ValueConverter;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
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
/*     */ public abstract class BusinessObject
/*     */   extends SimpleBusinessObject
/*     */   implements Serializable, Cloneable, ILbsXStreamListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  42 */   protected static Hashtable<String, Integer> ms_PropertySizes = new Hashtable<>();
/*     */   @XStreamOmitField
/*     */   protected ObjectValueBag m_InitialValues;
/*     */   @XStreamOmitField
/*     */   protected ObjectValueBag m_ValidatedValues;
/*     */   @XStreamAlias("ObjectName")
/*  48 */   protected String m_ObjectName = null;
/*     */   @XStreamAlias("LoadingState")
/*  50 */   public int m_LoadingState = 3;
/*     */   
/*     */   @XStreamAlias("SetValue")
/*     */   public String m_SetValue;
/*     */   
/*     */   @XStreamAlias("SchemaTableName")
/*     */   protected String m_SchemaTableName;
/*     */   
/*     */   @XStreamOmitField
/*     */   protected IParameter m_Parameter;
/*     */   
/*     */   protected Object getClassInstance(String className) {
/*  62 */     if (StringUtil.isEmpty(className)) {
/*  63 */       return null;
/*     */     }
/*     */     try {
/*  66 */       Class<?> clazz = Class.forName(className);
/*  67 */       return clazz.newInstance();
/*     */     }
/*  69 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  72 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ILbsBOXMLSerializer getSerializer(String className) {
/*  77 */     Object instance = getClassInstance(className);
/*  78 */     if (instance instanceof ILbsBOXMLSerializer)
/*  79 */       return (ILbsBOXMLSerializer)instance; 
/*  80 */     return getSerializer();
/*     */   }
/*     */ 
/*     */   
/*     */   protected IDataSerializer getDataSerializer(String className) {
/*  85 */     Object instance = getClassInstance(className);
/*  86 */     if (instance instanceof IDataSerializer)
/*  87 */       return (IDataSerializer)instance; 
/*  88 */     return getDataSerializer();
/*     */   }
/*     */ 
/*     */   
/*     */   protected SimpleBusinessObject getExchangeObject(String className) {
/*  93 */     Object instance = getClassInstance(className);
/*  94 */     if (instance instanceof SimpleBusinessObject)
/*  95 */       return (SimpleBusinessObject)instance; 
/*  96 */     return getExchangeObject();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setInternalValue(String columnName, String propertyName, Object value) {
/* 101 */     this.m_InitialValues.setValue(columnName, propertyName, value);
/* 102 */     this.m_ValidatedValues.setValue(columnName, propertyName, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public IParameter _getParameter() {
/* 107 */     return this.m_Parameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setParameter(IParameter parameter) {
/* 112 */     this.m_Parameter = parameter;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object[] createPropertyValues() {
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getAuthFields() {
/* 122 */     JLbsStringList list = new JLbsStringList();
/* 123 */     int authId = JLbsConstants.DUMMY_AUTH_ID;
/* 124 */     if (this instanceof IModeOwner) {
/*     */       
/* 126 */       ObjectMode objectMode = ((IModeOwner)this).getObjectMode();
/* 127 */       if (objectMode != null)
/* 128 */         authId = objectMode.getAuthId(); 
/*     */     } 
/* 130 */     fillAuthFields(list, "", authId);
/* 131 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAuthFields(JLbsStringList list, String prefix, int authID) {}
/*     */ 
/*     */   
/*     */   protected String getFieldName(String prefix, String propName) {
/* 140 */     if (StringUtil.isEmpty(prefix))
/* 141 */       return propName; 
/* 142 */     return prefix + "." + propName;
/*     */   }
/*     */   
/* 145 */   protected transient IObjectLoadingHandler m_LoadingHandler = null;
/*     */ 
/*     */   
/*     */   public BusinessObject() {
/* 149 */     initializePropertyValues();
/* 150 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/* 157 */     super.afterXStreamDeserialization();
/* 158 */     if (this.m_InitialValues == null)
/* 159 */       this.m_InitialValues = new ObjectValueBag(); 
/* 160 */     if (this.m_ValidatedValues == null)
/* 161 */       this.m_ValidatedValues = new ObjectValueBag(); 
/* 162 */     if (this.m_UniqueIdentifier == null) {
/* 163 */       this.m_UniqueIdentifier = new BusinessObjectIdentifier(this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected BusinessObject(BusinessObject object) {
/* 168 */     this();
/* 169 */     copyPropertyValues(object);
/* 170 */     this.m_InitialValues = object.m_InitialValues;
/* 171 */     this.m_ValidatedValues = object.m_ValidatedValues;
/* 172 */     this.m_Parameter = object.m_Parameter;
/* 173 */     this.m_SetValue = object.m_SetValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializePropertyValues() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void copyPropertyValues(BusinessObject from) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkState() {
/* 188 */     if (this.m_State == 1)
/*     */     {
/* 190 */       if (this.m_InitialValues.hasChanged(this)) {
/* 191 */         this.m_State = 2;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearAllInitialValues() {
/* 197 */     clearInitialValues();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearInitialValues() {
/* 203 */     super.clearInitialValues();
/* 204 */     this.m_InitialValues.clear(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectValueBag getInitialValues() {
/* 213 */     return this.m_InitialValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract int getAutoIncrementValue();
/*     */   
/*     */   public abstract void setAutoIncrementValue(int paramInt);
/*     */   
/*     */   public abstract String getPrimaryIndexName();
/*     */   
/*     */   public abstract BusinessObjectIndex[] getIndexes();
/*     */   
/*     */   public void createLinkedObjects() {
/* 226 */     createLinkedObjects(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createLinkedObjects(int maxLevel) {
/* 231 */     createLinkedObjects(0, maxLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createLinkedObjects(int level, int maxLevel) {}
/*     */ 
/*     */   
/*     */   public boolean hasChanges() {
/* 240 */     checkState();
/*     */     
/* 242 */     return (this.m_State != 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChangedProperties() {
/* 247 */     return this.m_InitialValues.hasChanged(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 256 */     BusinessObject obj = (BusinessObject)super.clone();
/*     */     
/* 258 */     obj.m_InitialValues = (ObjectValueBag)ObjectUtil.createCopy(this.m_InitialValues);
/* 259 */     obj.m_UniqueIdentifier = new BusinessObjectIdentifier(obj);
/* 260 */     obj.m_ValidatedValues = (ObjectValueBag)ObjectUtil.createCopy(this.m_ValidatedValues);
/* 261 */     obj.m_Parameter = (IParameter)ObjectUtil.createCopy(this.m_Parameter);
/* 262 */     obj.m_SetValue = this.m_SetValue;
/*     */     
/* 264 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BusinessObject cloneBO() {
/*     */     try {
/* 271 */       return (BusinessObject)clone();
/*     */     }
/* 273 */     catch (CloneNotSupportedException e) {
/*     */       
/* 275 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean compareObjects(BusinessObject obj1, BusinessObject obj2) {
/* 281 */     if (obj1 == null && obj2 == null) {
/* 282 */       return true;
/*     */     }
/* 284 */     if (obj1 == null || obj2 == null) {
/* 285 */       return false;
/*     */     }
/* 287 */     if (!obj1.getClass().equals(obj2.getClass())) {
/* 288 */       return false;
/*     */     }
/* 290 */     if (obj1._getState() == obj2._getState()) {
/*     */       
/* 292 */       if (obj1._getState() != 0) {
/* 293 */         return (obj1.getAutoIncrementValue() == obj2.getAutoIncrementValue());
/*     */       }
/* 295 */       return (obj1 == obj2);
/*     */     } 
/*     */     
/* 298 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 304 */     if (obj instanceof BusinessObject) {
/* 305 */       return compareObjects(this, (BusinessObject)obj);
/*     */     }
/* 307 */     if (obj instanceof BusinessObjectIdentifier) {
/* 308 */       return ((BusinessObjectIdentifier)obj).equals(this);
/*     */     }
/* 310 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 316 */     String hashStr = getClass().getName() + "," + getAutoIncrementValue();
/* 317 */     return hashStr.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getPropertySize(String name, Hashtable<String, Integer> propertySizes) {
/* 322 */     if (propertySizes == null)
/* 323 */       return -1; 
/* 324 */     Object size = propertySizes.get(name);
/* 325 */     if (size instanceof Integer)
/* 326 */       return ((Integer)size).intValue(); 
/* 327 */     if (size == null) {
/*     */       
/* 329 */       size = propertySizes.get(getPropertyNameIgnoredCase(name));
/* 330 */       if (size instanceof Integer)
/* 331 */         return ((Integer)size).intValue(); 
/*     */     } 
/* 333 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getPropertyNameIgnoredCase(String name) {
/* 338 */     for (String propertyName : getPropertyNames()) {
/*     */       
/* 340 */       if (propertyName.equalsIgnoreCase(name))
/* 341 */         return propertyName; 
/*     */     } 
/* 343 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPropertySize(String name) {
/* 348 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public BusinessObject cloneSingle() {
/* 353 */     Class<? extends BusinessObject> objClass = (Class)getClass();
/* 354 */     BusinessObject obj = null;
/*     */     
/*     */     try {
/* 357 */       obj = objClass.newInstance();
/* 358 */       obj.copyPropertyValues(this);
/* 359 */       obj.m_State = this.m_State;
/* 360 */       obj.m_UniqueIdentifier = new BusinessObjectIdentifier(obj);
/* 361 */       obj.m_Parameter = (IParameter)ObjectUtil.createCopy(this.m_Parameter);
/* 362 */       obj.m_SetValue = this.m_SetValue;
/*     */     }
/* 364 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 368 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLockIdTemplate() {
/* 377 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(int state, boolean resetKey) {
/* 382 */     changeState(state, resetKey, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(int state, boolean resetKey, boolean allObjects) {
/* 387 */     _setState(state);
/*     */     
/* 389 */     if (this.m_State == 0) {
/* 390 */       this.m_InitialValues.clear(this);
/*     */     }
/* 392 */     if (resetKey) {
/*     */       
/* 394 */       setAutoIncrementValue(0);
/* 395 */       resetUId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetUId() {
/* 401 */     if (JLbsConstants.isResetUUIDs()) {
/* 402 */       setUId("");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetPrimaryKeys() {
/* 408 */     resetExtensionBusinessObjectsPrimaryKeys();
/* 409 */     changeState(0, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetExtensionBusinessObjectsPrimaryKeys() {
/* 414 */     ObjectExtensions extensions = getExtensions();
/* 415 */     if (extensions == null || extensions.m_List == null || extensions.m_List.size() == 0) {
/*     */       return;
/*     */     }
/* 418 */     for (int i = 0; i < extensions.m_List.size(); i++) {
/*     */       
/* 420 */       SimpleBusinessObject custObj = extensions.m_List.get(i);
/* 421 */       custObj.resetPrimaryKeys();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSecondaryKeyValues() {
/* 427 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String _getObjectName() {
/* 433 */     if (this.m_ObjectName == null) {
/*     */       
/* 435 */       this.m_ObjectName = getClass().getName();
/* 436 */       int pos = this.m_ObjectName.lastIndexOf('.');
/*     */       
/* 438 */       if (pos != -1) {
/* 439 */         this.m_ObjectName = this.m_ObjectName.substring(pos + 1);
/*     */       }
/*     */     } 
/* 442 */     return this.m_ObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String _getCustomization() {
/* 448 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object getProperty(String name, Class<?> dstClass) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 453 */     Object value = ObjectValueManager.getMemberValue(this, name);
/*     */     
/* 455 */     value = ValueConverter.convert(dstClass, value);
/*     */     
/* 457 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 466 */     return ObjectValueManager.getMemberValue(this, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String name, boolean silent) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 474 */     return ObjectValueManager.getMemberValue(this, name, silent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 483 */     Boolean b = (Boolean)getProperty(name, Boolean.class);
/* 484 */     return b.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 493 */     Byte b = (Byte)getProperty(name, Byte.class);
/* 494 */     return b.byteValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 503 */     Character c = (Character)getProperty(name, Character.class);
/* 504 */     return c.charValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 513 */     Double d = (Double)getProperty(name, Double.class);
/* 514 */     return d.doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 523 */     Float f = (Float)getProperty(name, Float.class);
/* 524 */     return f.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 533 */     Integer i = (Integer)getProperty(name, Integer.class);
/* 534 */     return i.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLockId() {
/* 543 */     return super.getLockId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 552 */     return super.getLong(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSchema() {
/* 561 */     return super.getSchema();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 570 */     return super.getShort(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, boolean value) {
/* 579 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, byte value) {
/* 588 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, char value) {
/* 597 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, double value) {
/* 606 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, float value) {
/* 615 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, int value) {
/* 624 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, long value) {
/* 633 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, Object value) {
/* 642 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, short value) {
/* 651 */     super.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialValuesEnabled(boolean enabled) {
/* 657 */     this.m_InitialValues.setEnabled(enabled);
/* 658 */     super.setInitialValuesEnabled(enabled);
/*     */     
/* 660 */     ObjectExtensions extensions = getExtensions();
/* 661 */     if (extensions == null || extensions.m_List == null || extensions.m_List.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 665 */     for (int i = 0; i < extensions.m_List.size(); i++) {
/*     */       
/* 667 */       SimpleBusinessObject custObj = extensions.m_List.get(i);
/* 668 */       custObj.setInitialValuesEnabled(enabled);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChangedSinceValidation() {
/* 674 */     if (this.m_State == 0) {
/* 675 */       return true;
/*     */     }
/* 677 */     if (this.m_ValidatedValues != null) {
/* 678 */       return this.m_ValidatedValues.hasChanged(this);
/*     */     }
/* 680 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearValidatedValues() {
/* 685 */     if (this.m_ValidatedValues != null) {
/* 686 */       this.m_ValidatedValues.clear(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void assign(Object obj) {
/* 692 */     super.assign(obj);
/* 693 */     if (obj instanceof BusinessObject) {
/*     */       
/* 695 */       BusinessObject src = (BusinessObject)obj;
/* 696 */       this.m_InitialValues = src.m_InitialValues;
/* 697 */       copyPropertyValues(src);
/* 698 */       this.m_ObjectName = src.m_ObjectName;
/* 699 */       this.m_ValidatedValues = src.m_ValidatedValues;
/* 700 */       this.m_Parameter = src.m_Parameter;
/* 701 */       this.m_SetValue = src.m_SetValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int _getLoadingState() {
/* 707 */     return this.m_LoadingState;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void _setLoadingState(int loadingState) {
/* 712 */     this.m_LoadingState = loadingState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 718 */     super.writeExternal(out);
/*     */ 
/*     */     
/* 721 */     out.writeObject(new Boolean(true));
/*     */ 
/*     */ 
/*     */     
/* 725 */     out.writeInt(2);
/*     */     
/* 727 */     out.writeObject(getPropertyValuesAsMap());
/*     */     
/* 729 */     this.m_InitialValues.writeExternal(out);
/* 730 */     this.m_ValidatedValues.writeExternal(out);
/*     */     
/* 732 */     out.writeObject(this.m_ObjectName);
/* 733 */     out.writeInt(this.m_LoadingState);
/* 734 */     out.writeObject(this.m_Parameter);
/* 735 */     out.writeObject(this.m_SetValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 742 */     super.readExternal(in);
/*     */ 
/*     */     
/*     */     try {
/* 746 */       Object newVersion = in.readObject();
/* 747 */       if (newVersion instanceof Boolean) {
/*     */         boolean wasEnabled; Map<String, Object> map;
/* 749 */         int version = in.readInt();
/* 750 */         switch (version) {
/*     */           
/*     */           case 1:
/* 753 */             readPropertyValues(in);
/* 754 */             this.m_InitialValues.readExternal(in);
/* 755 */             this.m_ValidatedValues.readExternal(in);
/*     */             
/* 757 */             this.m_ObjectName = (String)in.readObject();
/* 758 */             this.m_LoadingState = in.readInt();
/* 759 */             this.m_Parameter = (IParameter)in.readObject();
/*     */             
/* 761 */             this.m_UniqueIdentifier.update(this);
/*     */             break;
/*     */           case 2:
/* 764 */             wasEnabled = this.m_InitialValues.isEnabled();
/* 765 */             this.m_InitialValues.setEnabled(false);
/* 766 */             map = (Map<String, Object>)in.readObject();
/* 767 */             for (Map.Entry<String, Object> prop : map.entrySet())
/*     */             {
/* 769 */               setPropertyByName(prop.getKey(), map.get(prop.getKey())); } 
/* 770 */             readOldValues().clear();
/* 771 */             readNewValues().clear();
/*     */ 
/*     */             
/* 774 */             this.m_InitialValues.setEnabled(wasEnabled);
/* 775 */             this.m_InitialValues.readExternal(in);
/* 776 */             this.m_ValidatedValues.readExternal(in);
/*     */             
/* 778 */             this.m_ObjectName = (String)in.readObject();
/* 779 */             this.m_LoadingState = in.readInt();
/* 780 */             this.m_Parameter = (IParameter)in.readObject();
/* 781 */             this.m_SetValue = (String)in.readObject();
/*     */             
/* 783 */             this.m_UniqueIdentifier.update(this);
/*     */             break;
/*     */         } 
/*     */       
/*     */       } else {
/* 788 */         readOldVersioned(in);
/*     */       } 
/* 790 */     } catch (Exception e) {
/*     */       
/* 792 */       readOldVersioned(in);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getPropertyNames() {
/* 798 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void readPropertyValues(ObjectInput in) throws IOException, ClassNotFoundException {
/* 803 */     Object[] valuesArr = ExternalizationUtil.readArray(in);
/* 804 */     String[] propNames = getPropertyNames();
/* 805 */     for (int i = 0; i < propNames.length; i++) {
/* 806 */       if (i < valuesArr.length) {
/* 807 */         setPropertyByName(propNames[i], valuesArr[i]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readOldVersioned(ObjectInput in) throws IOException, ClassNotFoundException {
/* 813 */     readPropertyValues(in);
/* 814 */     ExternalizationUtil.readHashtable(new Hashtable<>(), in);
/*     */     
/* 816 */     this.m_InitialValues.readExternal(in);
/* 817 */     this.m_ValidatedValues.readExternal(in);
/*     */     
/* 819 */     this.m_ObjectName = (String)in.readObject();
/* 820 */     this.m_LoadingState = in.readInt();
/*     */     
/* 822 */     this.m_UniqueIdentifier.update(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setPropertyByIndex(int index, Object value) {
/* 827 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getPropertyByIndex(int index) {
/* 832 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> getPropertyValuesAsMap() {
/* 837 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setPropertyByName(String name, Object value) {
/* 842 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getPropertyByName(String name) {
/* 847 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getPropertyTypeByName(String name) {
/* 852 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String setValue) {
/* 857 */     this.m_SetValue = setValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/* 862 */     return this.m_SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSchemaTableName() {
/* 867 */     return this.m_SchemaTableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullJson() {
/* 872 */     String[] propertyNames = getPropertyNames();
/*     */     
/* 874 */     StringBuilder buff = new StringBuilder();
/* 875 */     if (propertyNames != null) {
/*     */       
/* 877 */       for (int i = 0; i < propertyNames.length; i++) {
/*     */ 
/*     */         
/* 880 */         Object val = getPropertyByName(propertyNames[i]);
/* 881 */         if (val != null && RttiUtil.isSimpleClassValueOrWrapper(val.getClass())) {
/*     */           
/* 883 */           buff.append("\"");
/* 884 */           buff.append(propertyNames[i]);
/* 885 */           buff.append("\":");
/* 886 */           if (val instanceof String) {
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
/* 902 */             buff.append("\"");
/* 903 */             buff.append(escapeQuoteCharacter((String)val));
/* 904 */             buff.append("\"");
/*     */           }
/* 906 */           else if (val instanceof java.util.Date || val instanceof java.util.Calendar) {
/*     */             
/* 908 */             buff.append("\"");
/* 909 */             buff.append(val);
/* 910 */             buff.append("\"");
/*     */           } else {
/*     */             
/* 913 */             buff.append(val);
/*     */           } 
/* 915 */           buff.append(",");
/*     */         } 
/*     */       } 
/*     */       
/* 919 */       String data = buff.toString();
/* 920 */       int firstIndex = data.indexOf(",");
/* 921 */       if (firstIndex == 0)
/* 922 */         data = data.substring(1); 
/* 923 */       int lastIndex = data.lastIndexOf(",");
/* 924 */       if (lastIndex == data.length() - 1)
/* 925 */         data = data.substring(0, data.length() - 1); 
/* 926 */       return data;
/*     */     } 
/*     */ 
/*     */     
/* 930 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String escapeQuoteCharacter(String val) {
/* 935 */     if (val == null)
/*     */     {
/* 937 */       return null;
/*     */     }
/* 939 */     StringWriter writer = new StringWriter(val.length() * 2);
/* 940 */     int sz = val.length();
/* 941 */     for (int i = 0; i < sz; i++) {
/*     */       
/* 943 */       char ch = val.charAt(i);
/* 944 */       switch (ch) {
/*     */         
/*     */         case '"':
/* 947 */           writer.write(92);
/* 948 */           writer.write(34);
/*     */           break;
/*     */         case '\\':
/* 951 */           writer.write(92);
/* 952 */           writer.write(92);
/*     */           break;
/*     */         case '\b':
/* 955 */           writer.write(92);
/* 956 */           writer.write(98);
/*     */           break;
/*     */         case '\n':
/* 959 */           writer.write(92);
/* 960 */           writer.write(110);
/*     */           break;
/*     */         case '\t':
/* 963 */           writer.write(92);
/* 964 */           writer.write(116);
/*     */           break;
/*     */         case '\f':
/* 967 */           writer.write(92);
/* 968 */           writer.write(102);
/*     */           break;
/*     */         case '\r':
/* 971 */           writer.write(92);
/* 972 */           writer.write(114);
/*     */           break;
/*     */         default:
/* 975 */           writer.write(ch);
/*     */           break;
/*     */       } 
/*     */     } 
/* 979 */     return writer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getPropertyTypeByNameEx(String memberName) throws NoSuchFieldException {
/* 984 */     String[] parts = StringUtil.split(memberName, '.');
/*     */     
/* 986 */     if (parts == null) {
/* 987 */       throw new NoSuchFieldException("Property name is null or invalid!");
/*     */     }
/* 989 */     if (parts.length == 1) {
/* 990 */       return getPropertyTypeByName(memberName);
/*     */     }
/* 992 */     return ObjectValueManager.getMemberClass(getClass(), memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectValueBag readInitialValues() {
/* 997 */     return this.m_InitialValues;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */