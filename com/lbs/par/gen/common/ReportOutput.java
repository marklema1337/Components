/*     */ package com.lbs.par.gen.common;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReportOutput
/*     */   extends ContractResult
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("ReportName")
/*     */   private String m_ReportName;
/*     */   @XStreamAlias("ReportId")
/*     */   private long m_ReportId;
/*     */   @XStreamAlias("IsDBChunk")
/*  41 */   private Boolean m_IsDBChunk = Boolean.valueOf(false);
/*     */   @XStreamAlias("ReportContent")
/*     */   private byte[] m_ReportContent;
/*     */   @XStreamAlias("OutputType")
/*  45 */   private int m_OutputType = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReportName() {
/*  53 */     return this.m_ReportName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportName(String ReportName) {
/*  58 */     this.m_ReportName = ReportName;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getReportId() {
/*  63 */     return this.m_ReportId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportId(long ReportId) {
/*  68 */     this.m_ReportId = ReportId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsDBChunk() {
/*  73 */     if (this.m_IsDBChunk == null)
/*  74 */       return false; 
/*  75 */     return this.m_IsDBChunk.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsDBChunk(boolean IsDBChunk) {
/*  80 */     this.m_IsDBChunk = Boolean.valueOf(IsDBChunk);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getReportContent() {
/*  85 */     return this.m_ReportContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportContent(byte[] ReportContent) {
/*  90 */     this.m_ReportContent = ReportContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOutputType() {
/*  95 */     return this.m_OutputType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutputType(int OutputType) {
/* 100 */     this.m_OutputType = OutputType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/* 106 */     super.checkMandatoryFields(mandatoryListener);
/* 107 */     if (isPropertyMandatory(mandatoryListener, "ReportName", false) && isEmpty(this.m_ReportName))
/*     */     {
/* 109 */       throw new ParameterException("'ReportName' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 111 */     if (isPropertyMandatory(mandatoryListener, "ReportId", false) && isEmpty(this.m_ReportId))
/*     */     {
/* 113 */       throw new ParameterException("'ReportId' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 115 */     if (isPropertyMandatory(mandatoryListener, "IsDBChunk", false) && isEmpty(this.m_IsDBChunk))
/*     */     {
/* 117 */       throw new ParameterException("'IsDBChunk' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 119 */     if (isPropertyMandatory(mandatoryListener, "ReportContent", false) && isEmpty(this.m_ReportContent))
/*     */     {
/* 121 */       throw new ParameterException("'ReportContent' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 123 */     if (isPropertyMandatory(mandatoryListener, "OutputType", false) && isEmpty(this.m_OutputType))
/*     */     {
/* 125 */       throw new ParameterException("'OutputType' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/* 132 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/* 133 */     if (isPropertyMandatory(mandatoryListener, "ReportName", false) && isEmpty(this.m_ReportName))
/*     */     {
/* 135 */       emptyPropertyNames.add("ReportName");
/*     */     }
/* 137 */     if (isPropertyMandatory(mandatoryListener, "ReportId", false) && isEmpty(this.m_ReportId))
/*     */     {
/* 139 */       emptyPropertyNames.add("ReportId");
/*     */     }
/* 141 */     if (isPropertyMandatory(mandatoryListener, "IsDBChunk", false) && isEmpty(this.m_IsDBChunk))
/*     */     {
/* 143 */       emptyPropertyNames.add("IsDBChunk");
/*     */     }
/* 145 */     if (isPropertyMandatory(mandatoryListener, "ReportContent", false) && isEmpty(this.m_ReportContent))
/*     */     {
/* 147 */       emptyPropertyNames.add("ReportContent");
/*     */     }
/* 149 */     if (isPropertyMandatory(mandatoryListener, "OutputType", false) && isEmpty(this.m_OutputType))
/*     */     {
/* 151 */       emptyPropertyNames.add("OutputType");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 158 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 164 */     super.describePropertiesXML(writer, localizationService);
/* 165 */     writer.writeProperty("ReportName", "String", getResource(localizationService, "", 0, 0, ""), false);
/* 166 */     writer.writeProperty("ReportId", "long", getResource(localizationService, "", 0, 0, ""), false);
/* 167 */     writer.writeProperty("IsDBChunk", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/* 168 */     writer.writeProperty("ReportContent", "byte[]", getResource(localizationService, "", 0, 0, ""), false);
/* 169 */     writer.writeProperty("OutputType", "int", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/* 174 */     writer.writeParameterHeader(serializer.startParameterTag("reportoutput", "ReportOutput"), "ReportOutput", "com.lbs.par.gen.common");
/* 175 */     serializer.writeParameterProperty(writer, "reportoutput", "ReportName", this.m_ReportName, params);
/* 176 */     serializer.writeParameterProperty(writer, "reportoutput", "ReportId", Long.valueOf(this.m_ReportId), params);
/* 177 */     serializer.writeParameterProperty(writer, "reportoutput", "IsDBChunk", this.m_IsDBChunk, params);
/* 178 */     serializer.writeParameterProperty(writer, "reportoutput", "ReportContent", this.m_ReportContent, params);
/* 179 */     serializer.writeParameterProperty(writer, "reportoutput", "OutputType", Integer.valueOf(this.m_OutputType), params);
/* 180 */     writer.writeParameterFooter(serializer.endParameterTag("reportoutput", "ReportOutput"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/* 185 */     writer.writeInnerObjectStart("ReportOutput");
/* 186 */     serializer.writeParameterProperty(writer, "ReportName", this.m_ReportName, 11, params);
/* 187 */     serializer.writeParameterProperty(writer, "ReportId", Long.valueOf(this.m_ReportId), 4, params);
/* 188 */     serializer.writeParameterProperty(writer, "IsDBChunk", this.m_IsDBChunk, 1, params);
/* 189 */     serializer.writeParameterProperty(writer, "ReportContent", this.m_ReportContent, 1, params);
/* 190 */     serializer.writeParameterProperty(writer, "OutputType", Integer.valueOf(this.m_OutputType), 3, params);
/* 191 */     writer.writeInnerObjectEnd("ReportOutput");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 196 */     Object o = null;
/* 197 */     o = serializer.readParameterPropertyValue(parentNode, "reportoutput", "ReportName", String.class, params);
/* 198 */     if (o != null)
/* 199 */       setReportName((String)o); 
/* 200 */     o = serializer.readParameterPropertyValue(parentNode, "reportoutput", "ReportId", long.class, params);
/* 201 */     if (o != null)
/* 202 */       setReportId(((Long)o).longValue()); 
/* 203 */     o = serializer.readParameterPropertyValue(parentNode, "reportoutput", "IsDBChunk", boolean.class, params);
/* 204 */     if (o != null)
/* 205 */       setIsDBChunk(((Boolean)o).booleanValue()); 
/* 206 */     o = serializer.readParameterPropertyValue(parentNode, "reportoutput", "ReportContent", byte[].class, params);
/* 207 */     if (o != null)
/* 208 */       setReportContent((byte[])o); 
/* 209 */     o = serializer.readParameterPropertyValue(parentNode, "reportoutput", "OutputType", int.class, params);
/* 210 */     if (o != null) {
/* 211 */       setOutputType(((Integer)o).intValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 216 */     Object o = null;
/* 217 */     o = serializer.readParameterPropertyValue(parentNode, "ReportName", String.class, params);
/* 218 */     if (o != null)
/* 219 */       setReportName((String)o); 
/* 220 */     o = serializer.readParameterPropertyValue(parentNode, "ReportId", long.class, params);
/* 221 */     if (o != null)
/* 222 */       setReportId(((Long)o).longValue()); 
/* 223 */     o = serializer.readParameterPropertyValue(parentNode, "IsDBChunk", boolean.class, params);
/* 224 */     if (o != null)
/* 225 */       setIsDBChunk(((Boolean)o).booleanValue()); 
/* 226 */     o = serializer.readParameterPropertyValue(parentNode, "ReportContent", byte[].class, params);
/* 227 */     if (o != null)
/* 228 */       setReportContent((byte[])o); 
/* 229 */     o = serializer.readParameterPropertyValue(parentNode, "OutputType", int.class, params);
/* 230 */     if (o != null)
/* 231 */       setOutputType(((Integer)o).intValue()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\ReportOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */