/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.DBNull;
/*     */ import com.lbs.util.ConversionDataLoss;
/*     */ import com.lbs.util.ConversionNotSupportedException;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.ValueConverter;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
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
/*     */ public class ObjectPropertyList
/*     */   implements Serializable, Cloneable, IPropertyValues, Externalizable, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  40 */   protected Hashtable<String, Object> m_Items = new Hashtable<>(25);
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  45 */     this.m_Items.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear(BasicBusinessObject obj) {
/*  50 */     this.m_Items.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addProperty(String name, Object value, boolean localCopy) {
/*  55 */     if (this.m_Items.contains(name)) {
/*     */       return;
/*     */     }
/*  58 */     putValue(name, value, localCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   private void putValue(String name, Object value, boolean localCopy) {
/*  63 */     Object o = value;
/*     */     
/*  65 */     if (localCopy) {
/*  66 */       o = ObjectUtil.createCopy(value);
/*     */     }
/*  68 */     if (o == null) {
/*  69 */       o = new DBNull();
/*     */     }
/*  71 */     this.m_Items.put(name, o);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setProperty(String name, Object value, boolean localCopy) {
/*  76 */     if (this.m_Items.containsKey(name)) {
/*  77 */       this.m_Items.remove(name);
/*     */     }
/*  79 */     putValue(name, value, localCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getProperty(String name) {
/*  84 */     Object value = this.m_Items.get(name);
/*  85 */     if (value == null)
/*  86 */       value = this.m_Items.get(name.toUpperCase(Locale.US)); 
/*  87 */     if (value instanceof DBNull)
/*  88 */       value = null; 
/*  89 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeProperty(String name) {
/*  94 */     if (this.m_Items.containsKey(name)) {
/*  95 */       this.m_Items.remove(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containsProperty(String name) {
/* 100 */     return this.m_Items.containsKey(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String name, Object value) {
/* 105 */     setValue(name, value, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String name, Object value, boolean copyValue) {
/* 110 */     setProperty(name, value, copyValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, Object value) {
/* 115 */     setProperty(name, value, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, boolean value) {
/* 120 */     setProperty(name, new Boolean(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, char value) {
/* 125 */     setProperty(name, new Character(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, byte value) {
/* 130 */     setProperty(name, Byte.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, short value) {
/* 135 */     setProperty(name, Short.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, int value) {
/* 140 */     setProperty(name, Integer.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, long value) {
/* 145 */     setProperty(name, Long.valueOf(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, double value) {
/* 150 */     setProperty(name, new Double(value), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, float value) {
/* 155 */     setProperty(name, new Float(value), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(String name) {
/*     */     try {
/* 162 */       return get(name);
/*     */     }
/* 164 */     catch (ConversionNotSupportedException e) {
/*     */       
/* 166 */       LbsConsole.getLogger("Data.Client.ObjectPropList").error(null, (Throwable)e);
/*     */     
/*     */     }
/* 169 */     catch (ConversionDataLoss e) {
/*     */       
/* 171 */       LbsConsole.getLogger("Data.Client.ObjectPropList").error(null, (Throwable)e);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<String> properties() {
/* 180 */     return this.m_Items.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 190 */     ObjectPropertyList list = (ObjectPropertyList)super.clone();
/* 191 */     list.m_Items = (Hashtable<String, Object>)this.m_Items.clone();
/* 192 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(int maxCount) {
/* 197 */     StringBuilder sb = new StringBuilder();
/* 198 */     sb.append("[");
/*     */     
/* 200 */     Enumeration<String> propEnum = properties();
/* 201 */     boolean comma = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     int count = 0;
/* 207 */     while (propEnum.hasMoreElements()) {
/*     */       
/* 209 */       if (maxCount > 0 && count > maxCount) {
/*     */         break;
/*     */       }
/* 212 */       String propName = propEnum.nextElement();
/* 213 */       Object propValue = getValue(propName);
/*     */       
/* 215 */       if (propValue instanceof Calendar) {
/* 216 */         propValue = StringUtil.toCanonicalString((Calendar)propValue);
/*     */       }
/* 218 */       if (comma) {
/* 219 */         sb.append(", ");
/*     */       } else {
/* 221 */         comma = true;
/*     */       } 
/* 223 */       sb.append(propName + " = " + propValue);
/* 224 */       count++;
/*     */     } 
/* 226 */     sb.append("]");
/*     */     
/* 228 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 234 */     return toString(-1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 242 */     return getProperty(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 250 */     Object value = getProperty(name);
/* 251 */     Boolean b = (Boolean)ValueConverter.convert(Boolean.class, value);
/* 252 */     return b.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 260 */     Object value = getProperty(name);
/* 261 */     Character c = (Character)ValueConverter.convert(Character.class, value);
/* 262 */     return c.charValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 270 */     Object value = getProperty(name);
/* 271 */     Byte b = (Byte)ValueConverter.convert(Byte.class, value);
/* 272 */     return b.byteValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 280 */     Object value = getProperty(name);
/* 281 */     Short s = (Short)ValueConverter.convert(Short.class, value);
/* 282 */     return s.shortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 290 */     Object value = getProperty(name);
/* 291 */     if (value instanceof Integer)
/* 292 */       return ((Integer)value).intValue(); 
/* 293 */     Integer i = (Integer)ValueConverter.convert(Integer.class, value);
/* 294 */     return i.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 302 */     Object value = getProperty(name);
/* 303 */     Long l = (Long)ValueConverter.convert(Long.class, value);
/* 304 */     return l.longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 312 */     Object value = getProperty(name);
/* 313 */     Double d = (Double)ValueConverter.convert(Double.class, value);
/* 314 */     return d.doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String name) throws ConversionNotSupportedException, ConversionDataLoss {
/* 322 */     Object value = getProperty(name);
/* 323 */     Float f = (Float)ValueConverter.convert(Float.class, value);
/* 324 */     return f.floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getBigDecimal(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 329 */     Object value = getProperty(name);
/* 330 */     BigDecimal bd = (BigDecimal)ValueConverter.convert(BigDecimal.class, value);
/* 331 */     return bd;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getByteArray(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 336 */     Object value = getProperty(name);
/* 337 */     byte[] ba = (byte[])ValueConverter.convert(byte[].class, value);
/* 338 */     return ba;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 343 */     Object value = getProperty(name);
/* 344 */     String s = (String)ValueConverter.convert(String.class, value);
/* 345 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar(String name) throws ConversionNotSupportedException, ConversionDataLoss, Exception {
/* 350 */     Object value = getProperty(name);
/* 351 */     Calendar c = (Calendar)ValueConverter.convert(Calendar.class, value);
/* 352 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 357 */     ExternalizationUtil.writeExternal(this.m_Items, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 362 */     ExternalizationUtil.readExternal(this.m_Items, in);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectPropertyList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */