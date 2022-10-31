/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.message.JLbsMessResource;
/*     */ import com.lbs.message.JLbsMessage;
/*     */ import com.lbs.message.JlbsSingleMessage;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
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
/*     */ public class ContractMessage
/*     */   extends Parameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("TYPE_WARNING")
/*     */   public static final int TYPE_WARNING = 1;
/*     */   @XStreamAlias("TYPE_QUESTION")
/*     */   public static final int TYPE_QUESTION = 2;
/*     */   @XStreamAlias("TYPE_INFORMATION")
/*     */   public static final int TYPE_INFORMATION = 3;
/*     */   @XStreamAlias("TYPE_ERROR")
/*     */   public static final int TYPE_ERROR = 4;
/*     */   @XStreamAlias("Type")
/*     */   private int m_Type;
/*     */   @XStreamAlias("DefaultMessage")
/*     */   private String m_DefaultMessage;
/*     */   @XStreamAlias("MessageResource")
/*     */   private JLbsResourceId m_MessageResource;
/*     */   @XStreamAlias("MessageSubstitutions")
/*     */   private String[] m_MessageSubstitutions;
/*     */   @XStreamAlias("MessageId")
/*     */   private String m_MessageId;
/*     */   
/*     */   public ContractMessage() {}
/*     */   
/*     */   public ContractMessage(int type, JLbsResourceId messageResource, String defaultMessage) {
/*  53 */     this.m_Type = type;
/*  54 */     this.m_MessageResource = messageResource;
/*  55 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractMessage(int type, JLbsResourceId messageResource, String defaultMessage, String[] messageSubstitutions) {
/*  61 */     this.m_Type = type;
/*  62 */     this.m_MessageResource = messageResource;
/*  63 */     this.m_DefaultMessage = defaultMessage;
/*  64 */     this.m_MessageSubstitutions = messageSubstitutions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractMessage(String messageId, String defaultMessage) {
/*  70 */     this.m_MessageId = messageId;
/*  71 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/*  76 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int Type) {
/*  81 */     this.m_Type = Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultMessage() {
/*  86 */     return this.m_DefaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMessage(String DefaultMessage) {
/*  91 */     this.m_DefaultMessage = DefaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getMessageResource() {
/*  96 */     return this.m_MessageResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageResource(JLbsResourceId MessageResource) {
/* 101 */     this.m_MessageResource = MessageResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getMessageSubstitutions() {
/* 106 */     return this.m_MessageSubstitutions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageSubstitutions(String[] messageSubstitutions) {
/* 111 */     this.m_MessageSubstitutions = messageSubstitutions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageId(String messageId) {
/* 116 */     this.m_MessageId = messageId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageId() {
/* 121 */     return this.m_MessageId;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean m_Initialized = false;
/*     */   
/*     */   public void initialize(ILocalizationServices localizationService) {
/* 128 */     if (this.m_Initialized)
/*     */       return; 
/* 130 */     if (!StringUtil.isEmpty(this.m_MessageId)) {
/*     */       
/* 132 */       JLbsMessage javaMessage = localizationService.getMessage(this.m_MessageId);
/* 133 */       if (javaMessage instanceof JlbsSingleMessage) {
/*     */         
/* 135 */         JLbsMessResource res = ((JlbsSingleMessage)javaMessage).getMessage();
/* 136 */         if (res != null)
/* 137 */           this.m_MessageResource = new JLbsResourceId(res.getResourceGroup(), res.getListId(), res.getStringTag()); 
/*     */       } 
/* 139 */       switch (javaMessage.getType()) {
/*     */         
/*     */         case 1:
/* 142 */           this.m_Type = 4;
/*     */           break;
/*     */         case 3:
/* 145 */           this.m_Type = 1;
/*     */           break;
/*     */         case 2:
/* 148 */           this.m_Type = 3;
/*     */           break;
/*     */       } 
/*     */     } 
/* 152 */     this.m_Initialized = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedMessage(ILocalizationServices localizationService) {
/* 157 */     String message = null;
/* 158 */     initialize(localizationService);
/* 159 */     if (this.m_MessageResource != null)
/*     */     {
/* 161 */       message = this.m_MessageResource.getLocalizedValue(localizationService);
/*     */     }
/* 163 */     if (StringUtil.isEmpty(message))
/* 164 */       message = this.m_DefaultMessage; 
/* 165 */     if (this.m_MessageSubstitutions != null)
/*     */     {
/* 167 */       if (!StringUtil.isEmpty(message)) {
/*     */         
/* 169 */         int[] params = new int[this.m_MessageSubstitutions.length];
/* 170 */         for (int i = 0; i < params.length; i++)
/*     */         {
/* 172 */           params[i] = i + 1;
/*     */         }
/* 174 */         message = JLbsStringUtil.mergeParameters(message, this.m_MessageSubstitutions, params);
/*     */       } 
/*     */     }
/* 177 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 183 */     return getResource(localizationService, "UN", -1, -1, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 189 */     super.describePropertiesXML(writer, localizationService);
/* 190 */     writer.writeProperty("Type", "int", getResource(localizationService, "", 0, 0, ""), false);
/* 191 */     writer.writeProperty("DefaultMessage", "String", getResource(localizationService, "", 0, 0, ""), false);
/* 192 */     writer.startObjectProperty("MessageResource", "JLbsResourceId", getResource(localizationService, "", 0, 0, ""), false);
/* 193 */     writer.writeInnerObject(this.m_MessageResource, JLbsResourceId.class, localizationService);
/* 194 */     writer.endObjectProperty();
/* 195 */     writer.writeProperty("MessageSubstitutions", "String[]", "", false);
/* 196 */     writer.writeProperty("MessageId", "String", null, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */