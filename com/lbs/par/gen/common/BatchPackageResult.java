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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BatchPackageResult
/*     */   extends ContractResult
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("BatchIds")
/*     */   private int[] m_BatchIds;
/*     */   
/*     */   public int[] getBatchIds() {
/*  45 */     return this.m_BatchIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBatchIds(int[] BatchIds) {
/*  50 */     this.m_BatchIds = BatchIds;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/*  56 */     super.checkMandatoryFields(mandatoryListener);
/*  57 */     if (isPropertyMandatory(mandatoryListener, "BatchIds", false) && isEmpty(this.m_BatchIds))
/*     */     {
/*  59 */       throw new ParameterException("'BatchIds' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/*  66 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/*  67 */     if (isPropertyMandatory(mandatoryListener, "BatchIds", false) && isEmpty(this.m_BatchIds))
/*     */     {
/*  69 */       emptyPropertyNames.add("BatchIds");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/*  76 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/*  82 */     super.describePropertiesXML(writer, localizationService);
/*  83 */     writer.writeProperty("BatchIds", "int[]", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/*  88 */     writer.writeParameterHeader(serializer.startParameterTag("batchpackageresult", "BatchPackageResult"), "BatchPackageResult", "com.lbs.par.gen.common");
/*  89 */     serializer.writeParameterProperty(writer, "batchpackageresult", "BatchIds", this.m_BatchIds, params);
/*  90 */     writer.writeParameterFooter(serializer.endParameterTag("batchpackageresult", "BatchPackageResult"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/*  95 */     writer.writeInnerObjectStart("BatchPackageResult");
/*  96 */     serializer.writeParameterProperty(writer, "BatchIds", this.m_BatchIds, 3, params);
/*  97 */     writer.writeInnerObjectEnd("BatchPackageResult");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 102 */     Object o = null;
/* 103 */     o = serializer.readParameterPropertyValue(parentNode, "batchpackageresult", "BatchIds", int[].class, params);
/* 104 */     if (o != null) {
/* 105 */       setBatchIds((int[])o);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 110 */     Object o = null;
/* 111 */     o = serializer.readParameterPropertyValue(parentNode, "BatchIds", int[].class, params);
/* 112 */     if (o != null)
/* 113 */       setBatchIds((int[])o); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\BatchPackageResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */