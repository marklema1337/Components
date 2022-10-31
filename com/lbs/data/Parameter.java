/*     */ package com.lbs.data;
/*     */ 
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.Identifiable;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.lang.annotation.ElementType;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Parameter
/*     */   implements IParameter
/*     */ {
/*     */   @XStreamAlias("serviceGUID")
/*     */   protected String serviceGUID;
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static boolean isPropertyMandatory(IParameterMandatoryListener mandatoryListener, String propertyName, boolean defaultMandatory) {
/*  51 */     if (mandatoryListener != null)
/*  52 */       return mandatoryListener.isPropertyMandatory(propertyName, defaultMandatory); 
/*  53 */     return defaultMandatory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/*  63 */     ArrayList<String> emptyPropNames = new ArrayList<>();
/*  64 */     prepareEmptyPropertyNames(emptyPropNames, mandatoryListener);
/*  65 */     return emptyPropNames;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {}
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(int value) {
/*  74 */     return (value == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(float value) {
/*  79 */     return (value == 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(double value) {
/*  84 */     return (value == 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(byte value) {
/*  89 */     return (value == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(long value) {
/*  94 */     return (value == 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(String value) {
/*  99 */     return JLbsStringUtil.isEmpty(value);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty(Object value) {
/* 104 */     return (value == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 109 */     String tagName = getTagName();
/* 110 */     writer.startObject(tagName, getTagDescription(localizationService));
/* 111 */     describePropertiesXML(writer, localizationService);
/* 112 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTagName() {
/* 117 */     return getClass().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 122 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getResource(ILocalizationServices localizationService, String resGroup, int listId, int strTag, String defaultResource) {
/* 132 */     if (localizationService != null) {
/*     */       
/* 134 */       if (resGroup == null)
/* 135 */         resGroup = "UN"; 
/* 136 */       String resource = localizationService.getItem(listId, strTag, resGroup);
/* 137 */       if (!JLbsStringUtil.isEmpty(resource))
/* 138 */         return resource; 
/*     */     } 
/* 140 */     return defaultResource;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getIdentifierFromObject(Object item) {
/* 145 */     if (item instanceof Identifiable)
/* 146 */       return ((Identifiable)item).getIdentifier(); 
/* 147 */     return String.valueOf(item);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getIdentifierString(List<?> listProp) {
/* 152 */     StringBuilder id = new StringBuilder();
/* 153 */     if (listProp != null) {
/*     */       
/* 155 */       id.append("[");
/* 156 */       int idx = 0;
/* 157 */       for (Object item : listProp) {
/*     */         
/* 159 */         if (idx > 0)
/* 160 */           id.append(","); 
/* 161 */         id.append(getIdentifierFromObject(item));
/* 162 */         idx++;
/*     */       } 
/* 164 */       id.append("]");
/*     */     } 
/* 166 */     return id.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getIdentifierString(Map<?, ?> mapProp) {
/* 171 */     StringBuilder id = new StringBuilder();
/* 172 */     if (mapProp != null) {
/*     */       
/* 174 */       id.append("[");
/* 175 */       Set<?> keys = mapProp.entrySet();
/* 176 */       for (Object key : keys)
/*     */       {
/* 178 */         id.append("(" + getIdentifierFromObject(((Map.Entry)key).getKey()) + "," + getIdentifierFromObject(((Map.Entry)key).getValue()) + ")");
/*     */       }
/* 180 */       id.append("]");
/*     */     } 
/* 182 */     return id.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getIdentifierString(Parameter parameterProp) {
/* 187 */     if (parameterProp instanceof Identifiable)
/* 188 */       return ((Identifiable)parameterProp).getIdentifier(); 
/* 189 */     if (parameterProp != null)
/* 190 */       return (new StringBuilder(String.valueOf(parameterProp.hashCode()))).toString(); 
/* 191 */     return "null";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 197 */     return getParameterIdentifier((Class)getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Identifier getParameterIdentifier(Class<? extends IParameter> c) {
/* 202 */     String className = c.getName();
/* 203 */     int idx = className.lastIndexOf('.');
/* 204 */     if (idx > 0)
/* 205 */       className = className.substring(idx + 1); 
/* 206 */     return new Identifier(className);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getServiceGUID() {
/* 211 */     return this.serviceGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServiceGUID(String serviceGUID) {
/* 216 */     this.serviceGUID = serviceGUID;
/*     */   }
/*     */   
/*     */   @Target({ElementType.FIELD})
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   public static @interface Mandatory {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\Parameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */