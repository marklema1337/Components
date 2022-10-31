/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.platform.interfaces.IDataSerializer;
/*     */ import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
/*     */ import com.lbs.util.ConversionDataLoss;
/*     */ import com.lbs.util.ConversionNotSupportedException;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
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
/*     */ public class CustomBusinessObject
/*     */   extends SimpleBusinessObject
/*     */   implements Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Customization;
/*     */   protected String m_ObjectName;
/*     */   protected String m_KeyPropertyName;
/*     */   protected transient Object m_Validator;
/*     */   private transient boolean m_ValidatorRequested = false;
/*     */   
/*     */   public CustomBusinessObject() {
/*  39 */     this((String)null, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomBusinessObject(String customization, String objectName) {
/*  44 */     this.m_Customization = customization;
/*  45 */     this.m_ObjectName = objectName;
/*  46 */     this.m_UniqueIdentifier = new CustomObjectIdentifier(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomBusinessObject(String objectName) {
/*  51 */     this((String)null, objectName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean externalizeInitialValues() {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLockIdTemplate() {
/*  64 */     return "$V(main-table-name)-$V(key)";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomization() {
/*  72 */     return _getCustomization();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/*  80 */     return _getObjectName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String _getCustomization() {
/*  85 */     return this.m_Customization;
/*     */   }
/*     */ 
/*     */   
/*     */   public String _getObjectName() {
/*  90 */     return this.m_ObjectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomization(String customization) {
/*  95 */     this.m_Customization = customization;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 100 */     this.m_ObjectName = objectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyPropertyName() {
/* 105 */     return this.m_KeyPropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyPropertyName(String string) {
/* 110 */     this.m_KeyPropertyName = string;
/*     */     
/* 112 */     if (!StringUtil.isEmpty(this.m_KeyPropertyName)) {
/*     */       
/*     */       try {
/*     */         
/* 116 */         if (this.m_Properties.get(this.m_KeyPropertyName) == null || this.m_UniqueIdentifier.getSimpleKey() > 0) {
/* 117 */           set(this.m_KeyPropertyName, this.m_UniqueIdentifier.getSimpleKey());
/*     */         }
/* 119 */       } catch (ConversionNotSupportedException e) {
/*     */         
/* 121 */         e.printStackTrace();
/*     */       }
/* 123 */       catch (ConversionDataLoss e) {
/*     */         
/* 125 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 132 */     return "[Name=" + this.m_ObjectName + ",guid=" + this.m_Customization + ", props=" + this.m_Properties.toString() + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChanged() {
/* 137 */     return this.m_Properties.hasChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMemberValue(String name, Object value) {
/*     */     try {
/* 144 */       ObjectValueManager.setGridMemberValue(this, name, value, false);
/*     */     }
/* 146 */     catch (Exception e) {
/*     */       
/* 148 */       LbsConsole.getLogger("Data.Client.CustomBO").error(null, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetUId() {
/* 156 */     if (JLbsConstants.isResetUUIDs()) {
/* 157 */       setUId("");
/*     */     }
/*     */   }
/*     */   
/*     */   public void resetPrimaryKeys() {
/* 162 */     this.m_UniqueIdentifier.setSimpleKey(0);
/* 163 */     set("PARENTREF", 0);
/* 164 */     _setState(0);
/* 165 */     clearInitialValues();
/* 166 */     resetUId();
/*     */ 
/*     */ 
/*     */     
/* 170 */     Enumeration<String> propNames = this.m_Properties.properties();
/*     */     
/* 172 */     while (propNames.hasMoreElements()) {
/*     */       
/* 174 */       String key = propNames.nextElement();
/* 175 */       Object value = this.m_Properties.getProperty(key);
/*     */       
/* 177 */       if (value instanceof BasicBusinessObject) {
/*     */         
/* 179 */         ((BasicBusinessObject)value).resetPrimaryKeys(); continue;
/*     */       } 
/* 181 */       if (value instanceof BasicBusinessObjects) {
/*     */         
/* 183 */         BasicBusinessObjects<BasicBusinessObject> list = (BasicBusinessObjects)value;
/*     */         
/* 185 */         for (int i = 0; i < list.size(); i++) {
/*     */           
/* 187 */           BasicBusinessObject item = list.itemAt(i);
/* 188 */           item.resetPrimaryKeys();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ILbsCustBOValidatorProvider getValidatorProvider() {
/* 197 */     return (CustomBusinessObjectFieldHolder.getInstance()).ms_ValidatorProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitialValuesEnabled(boolean enabled) {
/* 202 */     super.setInitialValuesEnabled(enabled);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     Enumeration<String> propNames = this.m_Properties.properties();
/*     */     
/* 209 */     while (propNames.hasMoreElements()) {
/*     */       
/* 211 */       String key = propNames.nextElement();
/* 212 */       Object value = this.m_Properties.getProperty(key);
/*     */       
/* 214 */       if (value instanceof BasicBusinessObject) {
/*     */         
/* 216 */         ((BasicBusinessObject)value).setInitialValuesEnabled(enabled); continue;
/*     */       } 
/* 218 */       if (value instanceof BasicBusinessObjects)
/*     */       {
/* 220 */         ((BasicBusinessObjects)value).setInitialValuesEnabled(enabled);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(int state) {
/* 228 */     _setState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createLinkedObjects() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsBOXMLSerializer getSerializer() {
/* 238 */     return getSerializer(Thread.currentThread().getContextClassLoader());
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsBOXMLSerializer getSerializer(ClassLoader loader) {
/* 243 */     String className = "";
/* 244 */     if (!StringUtil.isEmpty(this.m_ObjectName))
/*     */     {
/* 246 */       className = className + this.m_ObjectName + "XML";
/*     */     }
/* 248 */     if (!StringUtil.isEmpty(className)) {
/*     */       
/*     */       try {
/*     */         
/* 252 */         Class<?> clazz = Class.forName(className, true, loader);
/* 253 */         return (ILbsBOXMLSerializer)clazz.newInstance();
/*     */       }
/* 255 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 259 */     return super.getSerializer();
/*     */   }
/*     */ 
/*     */   
/*     */   public IDataSerializer getDataSerializer(ClassLoader loader) {
/* 264 */     String className = "";
/* 265 */     if (!StringUtil.isEmpty(this.m_ObjectName))
/*     */     {
/* 267 */       className = className + this.m_ObjectName + "XML";
/*     */     }
/* 269 */     if (!StringUtil.isEmpty(className)) {
/*     */       
/*     */       try {
/*     */         
/* 273 */         Class<?> clazz = Class.forName(className, true, loader);
/* 274 */         return (IDataSerializer)clazz.newInstance();
/*     */       }
/* 276 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 280 */     return getDataSerializer();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 285 */     super.writeExternal(out);
/*     */     
/* 287 */     out.writeObject(this.m_Customization);
/* 288 */     out.writeObject(this.m_ObjectName);
/* 289 */     out.writeObject(this.m_KeyPropertyName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 294 */     super.readExternal(in);
/*     */     
/* 296 */     this.m_Customization = (String)in.readObject();
/* 297 */     this.m_ObjectName = (String)in.readObject();
/* 298 */     this.m_KeyPropertyName = (String)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setValidatorProvider(ILbsCustBOValidatorProvider validatorProvider) {
/* 303 */     (CustomBusinessObjectFieldHolder.getInstance()).ms_ValidatorProvider = validatorProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValidator() {
/* 308 */     if (this.m_ValidatorRequested) {
/* 309 */       return this.m_Validator;
/*     */     }
/*     */     
/*     */     try {
/* 313 */       if ((CustomBusinessObjectFieldHolder.getInstance()).ms_ValidatorProvider != null) {
/* 314 */         this.m_Validator = (CustomBusinessObjectFieldHolder.getInstance()).ms_ValidatorProvider.getObjectValidator(this);
/*     */       }
/*     */     } finally {
/*     */       
/* 318 */       this.m_ValidatorRequested = true;
/*     */     } 
/* 320 */     return this.m_Validator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 326 */     if (obj instanceof CustomBusinessObject) {
/*     */       
/* 328 */       CustomBusinessObject bo = (CustomBusinessObject)obj;
/* 329 */       if (bo._getState() == 0)
/* 330 */         return super.equals(obj); 
/* 331 */       return (StringUtil.equals(this.m_Customization, bo.m_Customization) && StringUtil.equals(this.m_ObjectName, bo.m_ObjectName) && 
/* 332 */         getUniqueIdentifier().getSimpleKey() == bo.getUniqueIdentifier().getSimpleKey());
/*     */     } 
/* 334 */     if (obj instanceof CustomObjectIdentifier) {
/*     */       
/* 336 */       CustomObjectIdentifier id = (CustomObjectIdentifier)obj;
/* 337 */       return id.equals(this);
/*     */     } 
/* 339 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 345 */     return (this.m_Customization + "." + this.m_ObjectName + "," + getUniqueIdentifier().getSimpleKey()).hashCode();
/*     */   }
/*     */   
/*     */   public static class CustomBusinessObjectFieldHolder
/*     */   {
/* 350 */     private transient ILbsCustBOValidatorProvider ms_ValidatorProvider = null;
/*     */ 
/*     */     
/*     */     private static CustomBusinessObjectFieldHolder getInstance() {
/* 354 */       return (CustomBusinessObjectFieldHolder)LbsClassInstanceProvider.getInstanceByClass(CustomBusinessObjectFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\CustomBusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */