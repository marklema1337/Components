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
/*     */ public class ObjectUpdateIdentifier
/*     */   extends ObjectIdentifier
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("RequestLock")
/*  33 */   private Boolean m_RequestLock = Boolean.valueOf(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRequestLock() {
/*  41 */     if (this.m_RequestLock == null)
/*  42 */       return false; 
/*  43 */     return this.m_RequestLock.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequestLock(boolean RequestLock) {
/*  48 */     this.m_RequestLock = Boolean.valueOf(RequestLock);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/*  54 */     super.checkMandatoryFields(mandatoryListener);
/*  55 */     if (isPropertyMandatory(mandatoryListener, "RequestLock", false) && isEmpty(this.m_RequestLock))
/*     */     {
/*  57 */       throw new ParameterException("'RequestLock' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/*  64 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/*  65 */     if (isPropertyMandatory(mandatoryListener, "RequestLock", false) && isEmpty(this.m_RequestLock))
/*     */     {
/*  67 */       emptyPropertyNames.add("RequestLock");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/*  74 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/*  80 */     super.describePropertiesXML(writer, localizationService);
/*  81 */     writer.writeProperty("RequestLock", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/*  86 */     writer.writeParameterHeader(serializer.startParameterTag("objectupdateidentifier", "ObjectUpdateIdentifier"), "ObjectUpdateIdentifier", "com.lbs.par.gen.common");
/*  87 */     serializer.writeParameterProperty(writer, "objectupdateidentifier", "RequestLock", this.m_RequestLock, params);
/*  88 */     writer.writeParameterFooter(serializer.endParameterTag("objectupdateidentifier", "ObjectUpdateIdentifier"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/*  93 */     writer.writeInnerObjectStart("ObjectUpdateIdentifier");
/*  94 */     serializer.writeParameterProperty(writer, "RequestLock", this.m_RequestLock, 1, params);
/*  95 */     writer.writeInnerObjectEnd("ObjectUpdateIdentifier");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 100 */     Object o = null;
/* 101 */     o = serializer.readParameterPropertyValue(parentNode, "objectupdateidentifier", "RequestLock", boolean.class, params);
/* 102 */     if (o != null) {
/* 103 */       setRequestLock(((Boolean)o).booleanValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 108 */     Object o = null;
/* 109 */     o = serializer.readParameterPropertyValue(parentNode, "RequestLock", boolean.class, params);
/* 110 */     if (o != null)
/* 111 */       setRequestLock(((Boolean)o).booleanValue()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\ObjectUpdateIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */