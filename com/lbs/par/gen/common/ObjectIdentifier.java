/*     */ package com.lbs.par.gen.common;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.data.query.QueryBusinessObject;
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
/*     */ public class ObjectIdentifier
/*     */   extends RecordIdentifier
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("CacheQueryObject")
/*     */   private QueryBusinessObject m_CacheQueryObject;
/*     */   @XStreamAlias("BOClassName")
/*     */   private String m_BOClassName;
/*     */   
/*     */   public QueryBusinessObject getCacheQueryObject() {
/*  44 */     return this.m_CacheQueryObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCacheQueryObject(QueryBusinessObject CacheQueryObject) {
/*  49 */     this.m_CacheQueryObject = CacheQueryObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBOClassName() {
/*  54 */     return this.m_BOClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBOClassName(String BOClassName) {
/*  59 */     this.m_BOClassName = BOClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/*  65 */     super.checkMandatoryFields(mandatoryListener);
/*  66 */     if (isPropertyMandatory(mandatoryListener, "CacheQueryObject", false) && isEmpty(this.m_CacheQueryObject))
/*     */     {
/*  68 */       throw new ParameterException("'CacheQueryObject' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*  70 */     if (isPropertyMandatory(mandatoryListener, "BOClassName", false) && isEmpty(this.m_BOClassName))
/*     */     {
/*  72 */       throw new ParameterException("'BOClassName' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/*  79 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/*  80 */     if (isPropertyMandatory(mandatoryListener, "CacheQueryObject", false) && isEmpty(this.m_CacheQueryObject))
/*     */     {
/*  82 */       emptyPropertyNames.add("CacheQueryObject");
/*     */     }
/*  84 */     if (isPropertyMandatory(mandatoryListener, "BOClassName", false) && isEmpty(this.m_BOClassName))
/*     */     {
/*  86 */       emptyPropertyNames.add("BOClassName");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/*  93 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/*  99 */     super.describePropertiesXML(writer, localizationService);
/* 100 */     writer.startObjectProperty("CacheQueryObject", "QueryBusinessObject", getResource(localizationService, "", 0, 0, ""), false);
/* 101 */     writer.writeInnerObject(this.m_CacheQueryObject, QueryBusinessObject.class, localizationService);
/* 102 */     writer.endObjectProperty();
/* 103 */     writer.writeProperty("BOClassName", "String", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/* 108 */     writer.writeParameterHeader(serializer.startParameterTag("objectidentifier", "ObjectIdentifier"), "ObjectIdentifier", "com.lbs.par.gen.common");
/* 109 */     if (getCacheQueryObject() == null)
/* 110 */       setCacheQueryObject(QueryBusinessObject.class.newInstance()); 
/* 111 */     serializer.writeParameterPropertyObject(writer, "objectidentifier", "CacheQueryObject", this.m_CacheQueryObject, params);
/* 112 */     serializer.writeParameterProperty(writer, "objectidentifier", "BOClassName", this.m_BOClassName, params);
/* 113 */     writer.writeParameterFooter(serializer.endParameterTag("objectidentifier", "ObjectIdentifier"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/* 118 */     writer.writeInnerObjectStart("ObjectIdentifier");
/* 119 */     if (getCacheQueryObject() == null)
/* 120 */       setCacheQueryObject(QueryBusinessObject.class.newInstance()); 
/* 121 */     serializer.writeParameterPropertyObject(writer, "CacheQueryObject", this.m_CacheQueryObject, params);
/* 122 */     serializer.writeParameterProperty(writer, "BOClassName", this.m_BOClassName, 11, params);
/* 123 */     writer.writeInnerObjectEnd("ObjectIdentifier");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 128 */     Object o = null;
/* 129 */     o = serializer.readParameterPropertyObject(parentNode, "objectidentifier", "CacheQueryObject", QueryBusinessObject.class, params);
/* 130 */     if (o != null)
/* 131 */       setCacheQueryObject((QueryBusinessObject)o); 
/* 132 */     o = serializer.readParameterPropertyValue(parentNode, "objectidentifier", "BOClassName", String.class, params);
/* 133 */     if (o != null) {
/* 134 */       setBOClassName((String)o);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 139 */     Object o = null;
/* 140 */     o = serializer.readParameterPropertyObject(parentNode, "CacheQueryObject", QueryBusinessObject.class, params, defaultStateStr);
/* 141 */     if (o != null)
/* 142 */       setCacheQueryObject((QueryBusinessObject)o); 
/* 143 */     o = serializer.readParameterPropertyValue(parentNode, "BOClassName", String.class, params);
/* 144 */     if (o != null)
/* 145 */       setBOClassName((String)o); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\ObjectIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */