/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.platform.interfaces.IDataSerializer;
/*     */ import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
/*     */ import com.lbs.util.ConversionDataLoss;
/*     */ import com.lbs.util.ConversionNotSupportedException;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
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
/*     */ public abstract class SimpleBusinessObject
/*     */   extends BasicBusinessObject
/*     */   implements Serializable, Cloneable, IBusinessObject, IBusinessObjectStates
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("State")
/*  35 */   protected int m_State = 0;
/*     */   
/*     */   @XStreamAlias("Locked")
/*     */   protected boolean m_Locked = false;
/*     */   
/*     */   @XStreamAlias("LockId")
/*     */   protected String m_LockId;
/*  42 */   private transient WeakReference<Object> m_Schema = null;
/*  43 */   protected transient CopyOnWriteArraySet<IBusinessObjectChangeListener> m_ChangeListeners = new CopyOnWriteArraySet<>();
/*  44 */   private transient int m_HashValue__ = 0;
/*     */ 
/*     */   
/*     */   protected boolean externalizeInitialValues() {
/*  48 */     return false;
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
/*     */   public Object getSchema() {
/*  60 */     if (this.m_Schema != null) {
/*  61 */       return this.m_Schema.get();
/*     */     }
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSchema(Object schema) {
/*  68 */     this.m_Schema = new WeakReference(schema);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkState() {
/*  73 */     if (this.m_State == 1)
/*     */     {
/*  75 */       if (this.m_Properties.hasChanged()) {
/*  76 */         this.m_State = 2;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int _getState() {
/*  82 */     checkState();
/*  83 */     return this.m_State;
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setState(int state) {
/*  88 */     this.m_State = state;
/*     */     
/*  90 */     switch (this.m_State) {
/*     */       
/*     */       case 1:
/*  93 */         setInitialValuesEnabled(true);
/*     */         break;
/*     */     } 
/*     */     
/*  97 */     doMemberChanged("_State", Integer.valueOf(state));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyPropertyName() {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSecondaryKeyValues() {
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doMemberChanged(String memberName, Object value) {
/* 112 */     doMemberChanged(memberName, value, (Object)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doMemberChanged(String memberName, Object value, Object oldValue) {
/* 117 */     if (this.m_ChangeListeners == null || this.m_ChangeListeners.size() == 0) {
/*     */       return;
/*     */     }
/* 120 */     for (IBusinessObjectChangeListener changeListener : this.m_ChangeListeners) {
/*     */       
/* 122 */       String fullMemberName = memberName;
/* 123 */       String fullName = changeListener.getMemberFullPath();
/* 124 */       if (!StringUtil.isEmpty(fullName)) {
/* 125 */         fullMemberName = fullName + "." + memberName;
/*     */       }
/* 127 */       BusinessObjectEvent e = new BusinessObjectEvent(this, fullMemberName, value, oldValue);
/* 128 */       changeListener.changed(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 134 */     return this.m_Locked;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocked(boolean locked) {
/* 139 */     this.m_Locked = locked;
/* 140 */     doMemberChanged("Locked", new Boolean(locked));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLockId() {
/* 145 */     return this.m_LockId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLockId(String lockId) {
/* 150 */     this.m_LockId = lockId;
/* 151 */     doMemberChanged("LockId", lockId);
/*     */   }
/*     */ 
/*     */   
/*     */   public int _getKey() {
/* 156 */     if (this.m_UniqueIdentifier != null)
/* 157 */       return this.m_UniqueIdentifier.getSimpleKey(); 
/* 158 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, Object value) {
/* 166 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, Object value, boolean copyValue) {
/* 171 */     this.m_Properties.setValue(name, value, copyValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, boolean value) {
/* 179 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, char value) {
/* 187 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, byte value) {
/* 195 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, short value) {
/* 203 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, int value) {
/* 211 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, long value) {
/* 219 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, double value) {
/* 227 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, float value) {
/* 235 */     this.m_Properties.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 243 */     return this.m_Properties.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 251 */     return this.m_Properties.getBoolean(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 259 */     return this.m_Properties.getChar(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 267 */     return this.m_Properties.getByte(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 275 */     return this.m_Properties.getShort(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 283 */     return this.m_Properties.getInt(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 291 */     return this.m_Properties.getLong(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 299 */     return this.m_Properties.getDouble(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 307 */     return this.m_Properties.getFloat(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getBigDecimal(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 312 */     return this.m_Properties.getBigDecimal(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getByteArray(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 317 */     return this.m_Properties.getByteArray(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 322 */     return this.m_Properties.getString(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 327 */     return this.m_Properties.getCalendar(name);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getProperty(String name) {
/* 332 */     return this.m_Properties.getProperty(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public CopyOnWriteArraySet<IBusinessObjectChangeListener> getChangeListeners() {
/* 337 */     return this.m_ChangeListeners;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsChangeListener(IBusinessObjectChangeListener changeListener) {
/* 342 */     return this.m_ChangeListeners.contains(changeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener) {
/* 347 */     removeChangeListener(changeListener, 0, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener) {
/* 352 */     addChangeListener(changeListener, 0, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/* 357 */     if (this.m_ChangeListeners == null)
/* 358 */       this.m_ChangeListeners = new CopyOnWriteArraySet<>(); 
/* 359 */     this.m_ChangeListeners.add(new BusinessObjectMemberListener(changeListener, memberName));
/* 360 */     this.m_Properties.addChangeListener(changeListener, memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/* 365 */     if (this.m_ChangeListeners == null)
/*     */       return; 
/* 367 */     this.m_ChangeListeners.remove(new BusinessObjectMemberListener(changeListener, memberName));
/* 368 */     this.m_Properties.removeChangeListener(changeListener, memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, int level, int maxLevel) {
/* 373 */     if (level > maxLevel) {
/*     */       return;
/*     */     }
/* 376 */     if (this.m_ChangeListeners == null)
/* 377 */       this.m_ChangeListeners = new CopyOnWriteArraySet<>(); 
/* 378 */     this.m_ChangeListeners.add(changeListener);
/* 379 */     this.m_Properties.addChangeListener(changeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, String memberName, int level, int maxLevel) {
/* 384 */     if (level > maxLevel) {
/*     */       return;
/*     */     }
/* 387 */     addChangeListener(changeListener, memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, int level, int maxLevel) {
/* 392 */     if (level > maxLevel || this.m_ChangeListeners == null) {
/*     */       return;
/*     */     }
/* 395 */     this.m_ChangeListeners.remove(changeListener);
/* 396 */     this.m_Properties.removeChangeListener(changeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, String memberName, int level, int maxLevel) {
/* 401 */     if (level > maxLevel) {
/*     */       return;
/*     */     }
/* 404 */     removeChangeListener(changeListener, memberName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String concatMemberNames(String parentMemberName, String memberName) {
/* 409 */     if (StringUtil.isEmpty(parentMemberName))
/* 410 */       return memberName; 
/* 411 */     return parentMemberName + "." + memberName;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int _getOptions() {
/* 416 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void assign(Object obj) {
/* 421 */     super.assign(obj);
/* 422 */     if (obj instanceof SimpleBusinessObject) {
/*     */       
/* 424 */       SimpleBusinessObject src = (SimpleBusinessObject)obj;
/* 425 */       this.m_Schema = src.m_Schema;
/* 426 */       this.m_State = src.m_State;
/* 427 */       this.m_Locked = src.m_Locked;
/* 428 */       this.m_LockId = src.m_LockId;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsBOXMLSerializer getSerializer() {
/* 434 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDataSerializer getDataSerializer() {
/* 439 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject getExchangeObject() {
/* 444 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 449 */     super.writeExternal(out);
/*     */     
/* 451 */     out.writeInt(this.m_State);
/* 452 */     out.writeBoolean(this.m_Locked);
/* 453 */     out.writeObject(this.m_LockId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 458 */     super.readExternal(in);
/*     */ 
/*     */     
/*     */     try {
/* 462 */       this.m_State = in.readInt();
/*     */     }
/* 464 */     catch (EOFException eOFException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 470 */       this.m_Locked = in.readBoolean();
/*     */     }
/* 472 */     catch (EOFException eOFException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 478 */       Object o = in.readObject();
/* 479 */       if (o instanceof String) {
/* 480 */         this.m_LockId = (String)o;
/*     */       }
/* 482 */     } catch (EOFException eOFException) {}
/*     */ 
/*     */ 
/*     */     
/* 486 */     this.m_ChangeListeners = new CopyOnWriteArraySet<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public int _getHashValue() {
/* 491 */     if (this.m_HashValue__ != 0) {
/* 492 */       return this.m_HashValue__;
/*     */     }
/*     */     try {
/* 495 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 496 */       ObjectOutputStream os = new ObjectOutputStream(bos);
/* 497 */       writeExternal(os);
/* 498 */       byte[] data = bos.toByteArray();
/* 499 */       os.close();
/* 500 */       this.m_HashValue__ = StringUtil.crc32(data);
/* 501 */       data = null;
/*     */     }
/* 503 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 506 */     return this.m_HashValue__;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 512 */     Object clone = super.clone();
/* 513 */     if (clone instanceof SimpleBusinessObject) {
/*     */       
/* 515 */       SimpleBusinessObject obj = (SimpleBusinessObject)clone;
/* 516 */       if (this.m_ChangeListeners != null)
/* 517 */         obj.m_ChangeListeners = new CopyOnWriteArraySet<>(this.m_ChangeListeners); 
/*     */     } 
/* 519 */     return clone;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\SimpleBusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */