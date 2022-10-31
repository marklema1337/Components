/*     */ package com.lbs.par.gen.common;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.Parameter.Mandatory;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.IDataWriter;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.IParameterSerializer;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
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
/*     */ public class BatchExecOptions
/*     */   extends Parameter
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Mandatory
/*     */   @XStreamAlias("BatchName")
/*     */   private String m_BatchName;
/*     */   @XStreamAlias("ScheduleDate")
/*     */   private Calendar m_ScheduleDate;
/*     */   @XStreamAlias("TestMode")
/*  38 */   private Boolean m_TestMode = Boolean.valueOf(false);
/*     */   @XStreamAlias("Immediate")
/*  40 */   private Boolean m_Immediate = Boolean.valueOf(false);
/*     */   @XStreamAlias("DontSaveToDB")
/*  42 */   private Boolean m_DontSaveToDB = Boolean.valueOf(false);
/*     */ 
/*     */ 
/*     */   
/*     */   public BatchExecOptions() {}
/*     */ 
/*     */   
/*     */   public BatchExecOptions(String BatchName, Calendar ScheduleDate, boolean TestMode, boolean Immediate, boolean DontSaveToDB) {
/*  50 */     this.m_BatchName = BatchName;
/*  51 */     this.m_ScheduleDate = ScheduleDate;
/*  52 */     this.m_TestMode = Boolean.valueOf(TestMode);
/*  53 */     this.m_Immediate = Boolean.valueOf(Immediate);
/*  54 */     this.m_DontSaveToDB = Boolean.valueOf(DontSaveToDB);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBatchName() {
/*  59 */     return this.m_BatchName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBatchName(String BatchName) {
/*  64 */     this.m_BatchName = BatchName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getScheduleDate() {
/*  69 */     return this.m_ScheduleDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScheduleDate(Calendar ScheduleDate) {
/*  74 */     this.m_ScheduleDate = ScheduleDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getTestMode() {
/*  79 */     if (this.m_TestMode == null)
/*  80 */       return false; 
/*  81 */     return this.m_TestMode.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTestMode(boolean TestMode) {
/*  86 */     this.m_TestMode = Boolean.valueOf(TestMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getImmediate() {
/*  91 */     if (this.m_Immediate == null)
/*  92 */       return false; 
/*  93 */     return this.m_Immediate.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImmediate(boolean Immediate) {
/*  98 */     this.m_Immediate = Boolean.valueOf(Immediate);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDontSaveToDB() {
/* 103 */     if (this.m_DontSaveToDB == null)
/* 104 */       return false; 
/* 105 */     return this.m_DontSaveToDB.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDontSaveToDB(boolean DontSaveToDB) {
/* 110 */     this.m_DontSaveToDB = Boolean.valueOf(DontSaveToDB);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/* 116 */     super.checkMandatoryFields(mandatoryListener);
/* 117 */     if (isPropertyMandatory(mandatoryListener, "BatchName", true) && isEmpty(this.m_BatchName))
/*     */     {
/* 119 */       throw new ParameterException("'BatchName' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 121 */     if (isPropertyMandatory(mandatoryListener, "ScheduleDate", false) && isEmpty(this.m_ScheduleDate))
/*     */     {
/* 123 */       throw new ParameterException("'ScheduleDate' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 125 */     if (isPropertyMandatory(mandatoryListener, "TestMode", false) && isEmpty(this.m_TestMode))
/*     */     {
/* 127 */       throw new ParameterException("'TestMode' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 129 */     if (isPropertyMandatory(mandatoryListener, "Immediate", false) && isEmpty(this.m_Immediate))
/*     */     {
/* 131 */       throw new ParameterException("'Immediate' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 133 */     if (isPropertyMandatory(mandatoryListener, "DontSaveToDB", false) && isEmpty(this.m_DontSaveToDB))
/*     */     {
/* 135 */       throw new ParameterException("'DontSaveToDB' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/* 142 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/* 143 */     if (isPropertyMandatory(mandatoryListener, "BatchName", true) && isEmpty(this.m_BatchName))
/*     */     {
/* 145 */       emptyPropertyNames.add("BatchName");
/*     */     }
/* 147 */     if (isPropertyMandatory(mandatoryListener, "ScheduleDate", false) && isEmpty(this.m_ScheduleDate))
/*     */     {
/* 149 */       emptyPropertyNames.add("ScheduleDate");
/*     */     }
/* 151 */     if (isPropertyMandatory(mandatoryListener, "TestMode", false) && isEmpty(this.m_TestMode))
/*     */     {
/* 153 */       emptyPropertyNames.add("TestMode");
/*     */     }
/* 155 */     if (isPropertyMandatory(mandatoryListener, "Immediate", false) && isEmpty(this.m_Immediate))
/*     */     {
/* 157 */       emptyPropertyNames.add("Immediate");
/*     */     }
/* 159 */     if (isPropertyMandatory(mandatoryListener, "DontSaveToDB", false) && isEmpty(this.m_DontSaveToDB))
/*     */     {
/* 161 */       emptyPropertyNames.add("DontSaveToDB");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 168 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 174 */     super.describePropertiesXML(writer, localizationService);
/* 175 */     writer.writeProperty("BatchName", "String", getResource(localizationService, "", 0, 0, ""), true);
/* 176 */     writer.writeProperty("ScheduleDate", "Calendar", getResource(localizationService, "", 0, 0, ""), false);
/* 177 */     writer.writeProperty("TestMode", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/* 178 */     writer.writeProperty("Immediate", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/* 179 */     writer.writeProperty("DontSaveToDB", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/* 184 */     writer.writeParameterHeader(serializer.startParameterTag("batchexecoptions", "BatchExecOptions"), "BatchExecOptions", "com.lbs.par.gen.common");
/* 185 */     serializer.writeParameterProperty(writer, "batchexecoptions", "BatchName", this.m_BatchName, params);
/* 186 */     serializer.writeParameterProperty(writer, "batchexecoptions", "ScheduleDate", this.m_ScheduleDate, params);
/* 187 */     serializer.writeParameterProperty(writer, "batchexecoptions", "TestMode", this.m_TestMode, params);
/* 188 */     serializer.writeParameterProperty(writer, "batchexecoptions", "Immediate", this.m_Immediate, params);
/* 189 */     serializer.writeParameterProperty(writer, "batchexecoptions", "DontSaveToDB", this.m_DontSaveToDB, params);
/* 190 */     writer.writeParameterFooter(serializer.endParameterTag("batchexecoptions", "BatchExecOptions"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/* 195 */     writer.writeInnerObjectStart("BatchExecOptions");
/* 196 */     serializer.writeParameterProperty(writer, "BatchName", this.m_BatchName, 11, params);
/* 197 */     serializer.writeParameterProperty(writer, "ScheduleDate", this.m_ScheduleDate, 10, params);
/* 198 */     serializer.writeParameterProperty(writer, "TestMode", this.m_TestMode, 1, params);
/* 199 */     serializer.writeParameterProperty(writer, "Immediate", this.m_Immediate, 1, params);
/* 200 */     serializer.writeParameterProperty(writer, "DontSaveToDB", this.m_DontSaveToDB, 1, params);
/* 201 */     writer.writeInnerObjectEnd("BatchExecOptions");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 206 */     Object o = null;
/* 207 */     o = serializer.readParameterPropertyValue(parentNode, "batchexecoptions", "BatchName", String.class, params);
/* 208 */     if (o != null)
/* 209 */       setBatchName((String)o); 
/* 210 */     o = serializer.readParameterPropertyValue(parentNode, "batchexecoptions", "ScheduleDate", Calendar.class, params);
/* 211 */     if (o != null)
/* 212 */       setScheduleDate((Calendar)o); 
/* 213 */     o = serializer.readParameterPropertyValue(parentNode, "batchexecoptions", "TestMode", boolean.class, params);
/* 214 */     if (o != null)
/* 215 */       setTestMode(((Boolean)o).booleanValue()); 
/* 216 */     o = serializer.readParameterPropertyValue(parentNode, "batchexecoptions", "Immediate", boolean.class, params);
/* 217 */     if (o != null)
/* 218 */       setImmediate(((Boolean)o).booleanValue()); 
/* 219 */     o = serializer.readParameterPropertyValue(parentNode, "batchexecoptions", "DontSaveToDB", boolean.class, params);
/* 220 */     if (o != null) {
/* 221 */       setDontSaveToDB(((Boolean)o).booleanValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 226 */     Object o = null;
/* 227 */     o = serializer.readParameterPropertyValue(parentNode, "BatchName", String.class, params);
/* 228 */     if (o != null)
/* 229 */       setBatchName((String)o); 
/* 230 */     o = serializer.readParameterPropertyValue(parentNode, "ScheduleDate", Calendar.class, params);
/* 231 */     if (o != null)
/* 232 */       setScheduleDate((Calendar)o); 
/* 233 */     o = serializer.readParameterPropertyValue(parentNode, "TestMode", boolean.class, params);
/* 234 */     if (o != null)
/* 235 */       setTestMode(((Boolean)o).booleanValue()); 
/* 236 */     o = serializer.readParameterPropertyValue(parentNode, "Immediate", boolean.class, params);
/* 237 */     if (o != null)
/* 238 */       setImmediate(((Boolean)o).booleanValue()); 
/* 239 */     o = serializer.readParameterPropertyValue(parentNode, "DontSaveToDB", boolean.class, params);
/* 240 */     if (o != null)
/* 241 */       setDontSaveToDB(((Boolean)o).booleanValue()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\BatchExecOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */