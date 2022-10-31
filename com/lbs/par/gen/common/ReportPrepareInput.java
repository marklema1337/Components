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
/*     */ public class ReportPrepareInput
/*     */   extends Parameter
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Mandatory
/*     */   @XStreamAlias("ReportContractId")
/*     */   private String m_ReportContractId;
/*     */   
/*     */   public String getReportContractId() {
/*  41 */     return this.m_ReportContractId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportContractId(String ReportContractId) {
/*  46 */     this.m_ReportContractId = ReportContractId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/*  52 */     super.checkMandatoryFields(mandatoryListener);
/*  53 */     if (isPropertyMandatory(mandatoryListener, "ReportContractId", true) && isEmpty(this.m_ReportContractId))
/*     */     {
/*  55 */       throw new ParameterException("'ReportContractId' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/*  62 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/*  63 */     if (isPropertyMandatory(mandatoryListener, "ReportContractId", true) && isEmpty(this.m_ReportContractId))
/*     */     {
/*  65 */       emptyPropertyNames.add("ReportContractId");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/*  72 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/*  78 */     super.describePropertiesXML(writer, localizationService);
/*  79 */     writer.writeProperty("ReportContractId", "String", getResource(localizationService, "", 0, 0, ""), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/*  84 */     writer.writeParameterHeader(serializer.startParameterTag("reportprepareinput", "ReportPrepareInput"), "ReportPrepareInput", "com.lbs.par.gen.common");
/*  85 */     serializer.writeParameterProperty(writer, "reportprepareinput", "ReportContractId", this.m_ReportContractId, params);
/*  86 */     writer.writeParameterFooter(serializer.endParameterTag("reportprepareinput", "ReportPrepareInput"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/*  91 */     writer.writeInnerObjectStart("ReportPrepareInput");
/*  92 */     serializer.writeParameterProperty(writer, "ReportContractId", this.m_ReportContractId, 11, params);
/*  93 */     writer.writeInnerObjectEnd("ReportPrepareInput");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/*  98 */     Object o = null;
/*  99 */     o = serializer.readParameterPropertyValue(parentNode, "reportprepareinput", "ReportContractId", String.class, params);
/* 100 */     if (o != null) {
/* 101 */       setReportContractId((String)o);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 106 */     Object o = null;
/* 107 */     o = serializer.readParameterPropertyValue(parentNode, "ReportContractId", String.class, params);
/* 108 */     if (o != null)
/* 109 */       setReportContractId((String)o); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\ReportPrepareInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */