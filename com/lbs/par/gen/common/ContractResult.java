/*     */ package com.lbs.par.gen.common;
/*     */ 
/*     */ import com.lbs.contract.ContractMessage;
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.IDataWriter;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.IParameterSerializer;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ContractResult
/*     */   extends Parameter
/*     */   implements IParameterSerializer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamOmitField
/*     */   public static final transient int STATUS_NONE = 0;
/*     */   @XStreamOmitField
/*     */   public static final transient int STATUS_SUCCESS = 1;
/*     */   @XStreamOmitField
/*     */   public static final transient int STATUS_NOT_AUTHORIZED = 2;
/*     */   @XStreamOmitField
/*     */   public static final transient int STATUS_FAILED = 3;
/*     */   @XStreamOmitField
/*     */   public static final transient int STATUS_CANCELED = 4;
/*     */   @XStreamAlias("Status")
/*  47 */   private int m_Status = 0;
/*     */   @XStreamAlias("Messages")
/*  49 */   private List<ContractMessage> m_Messages = new ArrayList<>();
/*     */   @XStreamAlias("Variables")
/*  51 */   private Map<String, String> m_Variables = new HashMap<>();
/*     */   @XStreamAlias("Consumed")
/*  53 */   private Boolean m_Consumed = Boolean.valueOf(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatus() {
/*  61 */     return this.m_Status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(int Status) {
/*  66 */     this.m_Status = Status;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ContractMessage> getMessages() {
/*  71 */     return this.m_Messages;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessages(List<ContractMessage> Messages) {
/*  76 */     this.m_Messages = Messages;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> getVariables() {
/*  81 */     return this.m_Variables;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVariables(Map<String, String> Variables) {
/*  86 */     this.m_Variables = Variables;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getConsumed() {
/*  91 */     if (this.m_Consumed == null)
/*  92 */       return false; 
/*  93 */     return this.m_Consumed.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsumed(boolean Consumed) {
/*  98 */     this.m_Consumed = Boolean.valueOf(Consumed);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/* 104 */     super.checkMandatoryFields(mandatoryListener);
/* 105 */     if (isPropertyMandatory(mandatoryListener, "STATUS_NONE", false) && isEmpty(0))
/*     */     {
/* 107 */       throw new ParameterException("'STATUS_NONE' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 109 */     if (isPropertyMandatory(mandatoryListener, "STATUS_SUCCESS", false) && isEmpty(1))
/*     */     {
/* 111 */       throw new ParameterException("'STATUS_SUCCESS' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 113 */     if (isPropertyMandatory(mandatoryListener, "STATUS_NOT_AUTHORIZED", false) && isEmpty(2))
/*     */     {
/* 115 */       throw new ParameterException("'STATUS_NOT_AUTHORIZED' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 117 */     if (isPropertyMandatory(mandatoryListener, "STATUS_FAILED", false) && isEmpty(3))
/*     */     {
/* 119 */       throw new ParameterException("'STATUS_FAILED' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 121 */     if (isPropertyMandatory(mandatoryListener, "STATUS_CANCELED", false) && isEmpty(4))
/*     */     {
/* 123 */       throw new ParameterException("'STATUS_CANCELED' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 125 */     if (isPropertyMandatory(mandatoryListener, "Status", false) && isEmpty(this.m_Status))
/*     */     {
/* 127 */       throw new ParameterException("'Status' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 129 */     if (isPropertyMandatory(mandatoryListener, "Messages", false) && isEmpty(this.m_Messages))
/*     */     {
/* 131 */       throw new ParameterException("'Messages' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 133 */     if (isPropertyMandatory(mandatoryListener, "Variables", false) && isEmpty(this.m_Variables))
/*     */     {
/* 135 */       throw new ParameterException("'Variables' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/* 137 */     if (isPropertyMandatory(mandatoryListener, "Consumed", false) && isEmpty(this.m_Consumed))
/*     */     {
/* 139 */       throw new ParameterException("'Consumed' is mandatory. Please specify a valid non-empty value for this field!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareEmptyPropertyNames(ArrayList<String> emptyPropertyNames, IParameterMandatoryListener mandatoryListener) {
/* 146 */     super.prepareEmptyPropertyNames(emptyPropertyNames, mandatoryListener);
/* 147 */     if (isPropertyMandatory(mandatoryListener, "STATUS_NONE", false) && isEmpty(0))
/*     */     {
/* 149 */       emptyPropertyNames.add("STATUS_NONE");
/*     */     }
/* 151 */     if (isPropertyMandatory(mandatoryListener, "STATUS_SUCCESS", false) && isEmpty(1))
/*     */     {
/* 153 */       emptyPropertyNames.add("STATUS_SUCCESS");
/*     */     }
/* 155 */     if (isPropertyMandatory(mandatoryListener, "STATUS_NOT_AUTHORIZED", false) && isEmpty(2))
/*     */     {
/* 157 */       emptyPropertyNames.add("STATUS_NOT_AUTHORIZED");
/*     */     }
/* 159 */     if (isPropertyMandatory(mandatoryListener, "STATUS_FAILED", false) && isEmpty(3))
/*     */     {
/* 161 */       emptyPropertyNames.add("STATUS_FAILED");
/*     */     }
/* 163 */     if (isPropertyMandatory(mandatoryListener, "STATUS_CANCELED", false) && isEmpty(4))
/*     */     {
/* 165 */       emptyPropertyNames.add("STATUS_CANCELED");
/*     */     }
/* 167 */     if (isPropertyMandatory(mandatoryListener, "Status", false) && isEmpty(this.m_Status))
/*     */     {
/* 169 */       emptyPropertyNames.add("Status");
/*     */     }
/* 171 */     if (isPropertyMandatory(mandatoryListener, "Messages", false) && isEmpty(this.m_Messages))
/*     */     {
/* 173 */       emptyPropertyNames.add("Messages");
/*     */     }
/* 175 */     if (isPropertyMandatory(mandatoryListener, "Variables", false) && isEmpty(this.m_Variables))
/*     */     {
/* 177 */       emptyPropertyNames.add("Variables");
/*     */     }
/* 179 */     if (isPropertyMandatory(mandatoryListener, "Consumed", false) && isEmpty(this.m_Consumed))
/*     */     {
/* 181 */       emptyPropertyNames.add("Consumed");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 188 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 194 */     super.describePropertiesXML(writer, localizationService);
/* 195 */     writer.writeProperty("Status", "int", getResource(localizationService, "", 0, 0, ""), false);
/* 196 */     writer.startObjectProperty("Messages", "List<ContractMessage>", getResource(localizationService, "", 0, 0, ""), false);
/* 197 */     writer.writeInnerListObject(ContractMessage.class, localizationService);
/* 198 */     writer.endObjectProperty();
/* 199 */     writer.startObjectProperty("Variables", "Map<String, String>", getResource(localizationService, "", 0, 0, ""), false);
/* 200 */     writer.writeInnerMapObject(String.class, String.class, localizationService);
/* 201 */     writer.endObjectProperty();
/* 202 */     writer.writeProperty("Consumed", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, ILbsDataExchangeWriter writer, IDataExchangeParams params) throws Exception {
/* 207 */     writer.writeParameterHeader(serializer.startParameterTag("contractresult", "ContractResult"), "ContractResult", "com.lbs.par.gen.common");
/* 208 */     serializer.writeParameterProperty(writer, "contractresult", "Status", Integer.valueOf(this.m_Status), params);
/* 209 */     serializer.writeParameterPropertyOther(writer, "contractresult", "Messages", this.m_Messages, params);
/* 210 */     serializer.writeParameterPropertyOther(writer, "contractresult", "Variables", this.m_Variables, params);
/* 211 */     serializer.writeParameterProperty(writer, "contractresult", "Consumed", this.m_Consumed, params);
/* 212 */     writer.writeParameterFooter(serializer.endParameterTag("contractresult", "ContractResult"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameter(XMLSerializerBase serializer, IDataWriter writer, IDataExchangeParams params) throws Exception {
/* 217 */     writer.writeInnerObjectStart("ContractResult");
/* 218 */     serializer.writeParameterProperty(writer, "Status", Integer.valueOf(this.m_Status), 3, params);
/* 219 */     serializer.writeParameterPropertyOther(writer, "Messages", this.m_Messages, params);
/* 220 */     serializer.writeParameterPropertyOther(writer, "Variables", this.m_Variables, params);
/* 221 */     serializer.writeParameterProperty(writer, "Consumed", this.m_Consumed, 1, params);
/* 222 */     writer.writeInnerObjectEnd("ContractResult");
/*     */   }
/*     */ 
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params) {
/* 227 */     Object o = null;
/* 228 */     o = serializer.readParameterPropertyValue(parentNode, "contractresult", "Status", int.class, params);
/* 229 */     if (o != null)
/* 230 */       setStatus(((Integer)o).intValue()); 
/* 231 */     o = serializer.readParameterPropertyOther(parentNode, "contractresult", "Messages", List.class, params);
/* 232 */     if (o != null)
/* 233 */       setMessages((List<ContractMessage>)o); 
/* 234 */     o = serializer.readParameterPropertyOther(parentNode, "contractresult", "Variables", Map.class, params);
/* 235 */     if (o != null)
/* 236 */       setVariables((Map<String, String>)o); 
/* 237 */     o = serializer.readParameterPropertyValue(parentNode, "contractresult", "Consumed", boolean.class, params);
/* 238 */     if (o != null) {
/* 239 */       setConsumed(((Boolean)o).booleanValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void readParameter(XMLSerializerBase serializer, Element parentNode, IDataExchangeParams params, String defaultStateStr) {
/* 244 */     Object o = null;
/* 245 */     o = serializer.readParameterPropertyValue(parentNode, "Status", int.class, params);
/* 246 */     if (o != null)
/* 247 */       setStatus(((Integer)o).intValue()); 
/* 248 */     o = serializer.readParameterPropertyOther(parentNode, "Messages", List.class, params);
/* 249 */     if (o != null)
/* 250 */       setMessages((List<ContractMessage>)o); 
/* 251 */     o = serializer.readParameterPropertyOther(parentNode, "Variables", Map.class, params);
/* 252 */     if (o != null)
/* 253 */       setVariables((Map<String, String>)o); 
/* 254 */     o = serializer.readParameterPropertyValue(parentNode, "Consumed", boolean.class, params);
/* 255 */     if (o != null)
/* 256 */       setConsumed(((Boolean)o).booleanValue()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\par\gen\common\ContractResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */