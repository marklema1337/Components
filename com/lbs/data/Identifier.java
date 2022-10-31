/*     */ package com.lbs.data;
/*     */ 
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.util.ArrayList;
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
/*     */ public class Identifier
/*     */   implements IParameter, Comparable<Identifier>, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("Guid")
/*  27 */   private String m_Guid = "";
/*     */   
/*     */   @Mandatory
/*     */   @XStreamAlias("Id")
/*     */   private String m_Id;
/*     */   
/*     */   public Identifier() {
/*  34 */     checkGuid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier(String guid, String id) {
/*  40 */     this.m_Guid = guid;
/*  41 */     this.m_Id = id;
/*  42 */     checkGuid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier(String id) {
/*  48 */     this.m_Id = id;
/*  49 */     checkGuid();
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkGuid() {
/*  54 */     if (this.m_Guid == null) {
/*  55 */       this.m_Guid = "";
/*     */     }
/*     */   }
/*     */   
/*     */   public String getGuid() {
/*  60 */     return this.m_Guid;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGuid(String guid) {
/*  65 */     this.m_Guid = guid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/*  70 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  75 */     this.m_Id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasGuid() {
/*  80 */     return (this.m_Guid.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toSingleString() {
/*  85 */     return (this.m_Guid == null || this.m_Guid.length() == 0) ? 
/*  86 */       this.m_Id : (
/*  87 */       String.valueOf(this.m_Guid) + "." + this.m_Id);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Identifier toIdentifier(String singleString) {
/*  92 */     int idx = singleString.indexOf('.');
/*  93 */     if (idx < 0 || !singleString.startsWith("{"))
/*  94 */       return new Identifier(singleString); 
/*  95 */     return new Identifier(singleString.substring(0, idx), singleString.substring(idx + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (obj instanceof Identifier) {
/*     */       
/* 103 */       Identifier id = (Identifier)obj;
/* 104 */       return toSingleString().equals(id.toSingleString());
/*     */     } 
/* 106 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     return toSingleString().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     return toSingleString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Identifier o) {
/* 124 */     int guidDiff = this.m_Guid.compareTo(o.m_Guid);
/* 125 */     if (guidDiff != 0)
/* 126 */       return guidDiff; 
/* 127 */     return this.m_Id.compareTo(o.m_Id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 133 */     writer.startObject(getClass().getName(), "");
/* 134 */     describePropertiesXML(writer, localizationService);
/* 135 */     writer.endObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 141 */     writer.writeProperty("Guid", "String", "", false);
/* 142 */     writer.writeProperty("Id", "String", "", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/* 148 */     if (JLbsStringUtil.isEmpty(this.m_Id))
/*     */     {
/* 150 */       throw new ParameterException("'Id' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 157 */     ArrayList<String> list = new ArrayList<>();
/* 158 */     if (JLbsStringUtil.isEmpty(this.m_Id))
/* 159 */       list.add("Id"); 
/* 160 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 166 */     return Parameter.getParameterIdentifier((Class)getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 172 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\Identifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */