/*     */ package com.lbs.par.gen.common;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.interfaces.Identifiable;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.IDataWriter;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.IParameterSerializer;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.util.ArrayList;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class RecordIdentifier
/*     */   extends Parameter
/*     */   implements IParameterSerializer, Identifiable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("TableName")
/*     */   private String m_TableName;
/*     */   @XStreamAlias("SetValue")
/*     */   private String m_SetValue;
/*     */   @XStreamAlias("KeyValue")
/*  37 */   private int m_KeyValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public RecordIdentifier() {}
/*     */ 
/*     */   
/*     */   public RecordIdentifier(String TableName, String SetValue, int KeyValue) {
/*  45 */     this.m_TableName = TableName;
/*  46 */     this.m_SetValue = SetValue;
/*  47 */     this.m_KeyValue = KeyValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecordIdentifier(String TableName, String SetValue) {
/*  52 */     this.m_TableName = TableName;
/*  53 */     this.m_SetValue = SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/*  58 */     return this.m_TableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String TableName) {
/*  63 */     this.m_TableName = TableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/*  68 */     return this.m_SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String SetValue) {
/*  73 */     this.m_SetValue = SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKeyValue() {
/*  78 */     return this.m_KeyValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyValue(int KeyValue) {
/*  83 */     this.m_KeyValue = KeyValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/*  89 */     super.checkMandatoryFields(mandatoryListener);
/*  90 */     if (isPropertyMandatory(mandatoryListener, "TableName", false) && isEmpty(this.m_TableName))
/*     */     {
/*  92 */       throw new ParameterException("'TableName' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*  94 */     if (isPropertyMandatory(mandatoryListener, "SetValue", false) && isEmpty(this.m_SetValue))
/*     */     {
/*  96 */       throw new ParameterException("'SetValue' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*  98 */     if (isPropertyMandatory(mandatoryListener, "KeyValue", false) && isEmpty(this.m_KeyValue))
/*     */     {
/* 100 */       throw new ParameterException("'KeyValue' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/* 107 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/* 108 */     if (isPropertyMandatory(mandatoryListener, "TableName", false) && isEmpty(this.m_TableName))
/*     */     {
/* 110 */       emptyPropertyNames.add("TableName");
/*     */     }
/* 112 */     if (isPropertyMandatory(mandatoryListener, "SetValue", false) && isEmpty(this.m_SetValue))
/*     */     {
/* 114 */       emptyPropertyNames.add("SetValue");
/*     */     }
/* 116 */     if (isPropertyMandatory(mandatoryListener, "KeyValue", false) && isEmpty(this.m_KeyValue))
/*     */     {
/* 118 */       emptyPropertyNames.add("KeyValue");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 125 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 131 */     super.describePropertiesXML(writer, localizationService);
/* 132 */     writer.writeProperty("TableName", "String", getResource(localizationService, "", 0, 0, ""), false);
/* 133 */     writer.writeProperty("SetValue", "String", getResource(localizationService, "", 0, 0, ""), false);
/* 134 */     writer.writeProperty("KeyValue", "int", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/* 139 */     writer.writeParameterHeader(serializer.startParameterTag("recordidentifier", "RecordIdentifier"), "RecordIdentifier", "com.lbs.par.gen.common");
/* 140 */     serializer.writeParameterProperty(writer, "recordidentifier", "TableName", this.m_TableName, params);
/* 141 */     serializer.writeParameterProperty(writer, "recordidentifier", "SetValue", this.m_SetValue, params);
/* 142 */     serializer.writeParameterProperty(writer, "recordidentifier", "KeyValue", Integer.valueOf(this.m_KeyValue), params);
/* 143 */     writer.writeParameterFooter(serializer.endParameterTag("recordidentifier", "RecordIdentifier"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/* 148 */     writer.writeInnerObjectStart("RecordIdentifier");
/* 149 */     serializer.writeParameterProperty(writer, "TableName", this.m_TableName, 11, params);
/* 150 */     serializer.writeParameterProperty(writer, "SetValue", this.m_SetValue, 11, params);
/* 151 */     serializer.writeParameterProperty(writer, "KeyValue", Integer.valueOf(this.m_KeyValue), 3, params);
/* 152 */     writer.writeInnerObjectEnd("RecordIdentifier");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 157 */     Object o = null;
/* 158 */     o = serializer.readParameterPropertyValue(parentNode, "recordidentifier", "TableName", String.class, params);
/* 159 */     if (o != null)
/* 160 */       setTableName((String)o); 
/* 161 */     o = serializer.readParameterPropertyValue(parentNode, "recordidentifier", "SetValue", String.class, params);
/* 162 */     if (o != null)
/* 163 */       setSetValue((String)o); 
/* 164 */     o = serializer.readParameterPropertyValue(parentNode, "recordidentifier", "KeyValue", int.class, params);
/* 165 */     if (o != null) {
/* 166 */       setKeyValue(((Integer)o).intValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 171 */     Object o = null;
/* 172 */     o = serializer.readParameterPropertyValue(parentNode, "TableName", String.class, params);
/* 173 */     if (o != null)
/* 174 */       setTableName((String)o); 
/* 175 */     o = serializer.readParameterPropertyValue(parentNode, "SetValue", String.class, params);
/* 176 */     if (o != null)
/* 177 */       setSetValue((String)o); 
/* 178 */     o = serializer.readParameterPropertyValue(parentNode, "KeyValue", int.class, params);
/* 179 */     if (o != null) {
/* 180 */       setKeyValue(((Integer)o).intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIdentifier() {
/* 186 */     String id = "";
/* 187 */     id = String.valueOf(id) + ";" + String.valueOf(this.m_KeyValue);
/* 188 */     return id;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\RecordIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */