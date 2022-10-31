/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BasicObjectIdentifier;
/*     */ import com.lbs.data.objects.ObjectMode;
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.StringItemList;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
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
/*     */ public class QueryBusinessObject
/*     */   extends BasicBusinessObject
/*     */   implements Externalizable, ILbsXStreamListener, IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("TableName")
/*     */   protected String m_TableName;
/*     */   @XStreamAlias("SetValue")
/*     */   public String m_SetValue;
/*     */   @XStreamAlias("QueryName")
/*     */   protected String m_QueryName;
/*     */   @XStreamAlias("KeyNames")
/*     */   protected StringItemList m_KeyNames;
/*     */   @XStreamAlias("Mode")
/*     */   protected ObjectMode m_Mode;
/*     */   
/*     */   public QueryBusinessObject() {
/*  54 */     this.m_KeyNames = new StringItemList();
/*  55 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  61 */     this.m_UniqueIdentifier = new QueryObjectIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringItemList getKeyNames() {
/*  69 */     return this.m_KeyNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean compareObjects(QueryBusinessObject obj1, QueryBusinessObject obj2) {
/*  74 */     if (obj1 == null && obj2 == null) {
/*  75 */       return true;
/*     */     }
/*  77 */     if (obj1 == null || obj2 == null) {
/*  78 */       return false;
/*     */     }
/*  80 */     StringItemList list1 = obj1.getKeyNames();
/*  81 */     StringItemList list2 = obj2.getKeyNames();
/*     */     
/*  83 */     if (list1.size() != list2.size()) {
/*  84 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     for (int i = 0; i < list1.size(); i++) {
/*     */       
/*  93 */       String keyName1 = list1.getItem(i);
/*  94 */       String keyName2 = list2.getItem(i);
/*     */       
/*  96 */       if (!keyName1.equals(keyName2)) {
/*  97 */         return false;
/*     */       }
/*  99 */       Object keyVal1 = obj1.getProperties().getValue(keyName1);
/* 100 */       Object keyVal2 = obj2.getProperties().getValue(keyName2);
/*     */       
/* 102 */       if (keyVal1 != null && !keyVal1.equals(keyVal2)) {
/* 103 */         return false;
/*     */       }
/*     */     } 
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(boolean onlyKeyValues) {
/* 111 */     return toString(onlyKeyValues, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(boolean onlyKeyValues, int maxCount) {
/* 116 */     String query = (this.m_QueryName == null) ? "" : (this.m_QueryName + " ");
/*     */ 
/*     */     
/* 119 */     if (onlyKeyValues) {
/*     */       
/* 121 */       StringBuilder sb = new StringBuilder();
/* 122 */       sb.append(query);
/* 123 */       sb.append("[");
/*     */ 
/*     */       
/* 126 */       for (int i = 0; i < this.m_KeyNames.size(); i++) {
/*     */         
/* 128 */         String keyName = this.m_KeyNames.getItem(i);
/* 129 */         Object keyValue = this.m_Properties.getValue(keyName);
/*     */         
/* 131 */         if (i > 0) {
/* 132 */           sb.append(", ");
/*     */         }
/* 134 */         if (keyValue instanceof Calendar) {
/* 135 */           keyValue = StringUtil.toCanonicalString((Calendar)keyValue);
/*     */         }
/* 137 */         sb.append(keyName + "=" + keyValue);
/*     */       } 
/* 139 */       sb.append("]");
/* 140 */       return sb.toString();
/*     */     } 
/*     */     
/* 143 */     return query + this.m_Properties.toString(maxCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 148 */     return toString(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 153 */     if (obj instanceof QueryBusinessObject) {
/* 154 */       return compareObjects(this, (QueryBusinessObject)obj);
/*     */     }
/* 156 */     if (obj instanceof QueryObjectIdentifier) {
/* 157 */       return true;
/*     */     }
/* 159 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 165 */     StringItemList keyNames = getKeyNames();
/* 166 */     ArrayList<Object> content = new ArrayList();
/* 167 */     for (int i = 0; i < keyNames.size(); i++) {
/*     */       
/* 169 */       String keyName = keyNames.getItem(i);
/* 170 */       content.add(keyName);
/*     */       
/*     */       try {
/* 173 */         content.add(getProperties().get(keyName));
/*     */       }
/* 175 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 179 */     return content.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicObjectIdentifier getUniqueIdentifier() {
/* 187 */     ((QueryObjectIdentifier)this.m_UniqueIdentifier).update(this);
/* 188 */     return this.m_UniqueIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLockIdTemplate() {
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int _getState() {
/* 204 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void _setState(int state) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetPrimaryKeys() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLockId() {
/* 226 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLockId(String lockId) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocked(boolean locked) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkState() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String _getObjectName() {
/* 260 */     return "QueryBusinessObject";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int _getOptions() {
/* 265 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryName(String queryName) {
/* 270 */     this.m_QueryName = queryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryName() {
/* 275 */     return this.m_QueryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String tableName) {
/* 280 */     this.m_TableName = tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/* 285 */     return this.m_TableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/* 290 */     return this.m_SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String setValue) {
/* 295 */     this.m_SetValue = setValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMode(ObjectMode mode) {
/* 300 */     this.m_Mode = mode;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectMode getMode() {
/* 305 */     return this.m_Mode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 310 */     super.writeExternal(out);
/*     */     
/* 312 */     this.m_KeyNames.writeExternal(out);
/* 313 */     out.writeObject(this.m_QueryName);
/* 314 */     out.writeObject(this.m_Mode);
/* 315 */     out.writeObject(this.m_TableName);
/* 316 */     out.writeObject(this.m_SetValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 321 */     super.readExternal(in);
/*     */     
/* 323 */     this.m_KeyNames.readExternal(in);
/* 324 */     this.m_QueryName = (String)in.readObject();
/* 325 */     this.m_Mode = (ObjectMode)in.readObject();
/* 326 */     this.m_TableName = (String)in.readObject();
/* 327 */     this.m_SetValue = (String)in.readObject();
/*     */     
/* 329 */     this.m_UniqueIdentifier.update(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean externalizeInitialValues() {
/* 334 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 339 */     writer.startObject("QueryBusinessObject", "");
/* 340 */     describePropertiesXML(writer, localizationService);
/* 341 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 346 */     writer.writeProperty("QueryName", "String", null, false);
/* 347 */     writer.startObjectProperty("PropertyValues", "Map<String, String>", null, true);
/* 348 */     writer.endObjectProperty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 358 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 364 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryBusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */