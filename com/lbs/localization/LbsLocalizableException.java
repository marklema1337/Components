/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.message.JLbsMessage;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.ILbsExceptionConstants;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsLocalizableException
/*     */   extends Exception
/*     */   implements ILbsExceptionConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   protected String m_ResourceGroup = "UN";
/*     */   
/*     */   protected int m_ListID;
/*     */   
/*     */   protected int m_StringTag;
/*     */   
/*     */   protected String m_DefaultMessage;
/*     */   
/*     */   protected LbsMessage m_DefaultTemplateMessage;
/*     */   protected String m_MessageId;
/*     */   protected String m_LocalizedMessage;
/*     */   
/*     */   public LbsLocalizableException(String message) {
/*  35 */     super(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(String message, Throwable cause) {
/*  40 */     super(message, cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException() {
/*  45 */     this(-1003, 1, "Basic Platform Exception");
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause) {
/*  50 */     this(cause, -1003, 1, "Basic Platform Exception");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(int listID, int stringTag, String defaultMessage) {
/*  56 */     this.m_ListID = listID;
/*  57 */     this.m_StringTag = stringTag;
/*  58 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/*  63 */     super(cause);
/*  64 */     this.m_ListID = listID;
/*  65 */     this.m_StringTag = stringTag;
/*  66 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  72 */     this.m_ListID = listID;
/*  73 */     this.m_StringTag = stringTag;
/*  74 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  79 */     super(cause);
/*  80 */     this.m_ListID = listID;
/*  81 */     this.m_StringTag = stringTag;
/*  82 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(String resGroup, int listID, int stringTag, String defaultMessage) {
/*  88 */     this.m_ListID = listID;
/*  89 */     this.m_StringTag = stringTag;
/*  90 */     this.m_DefaultMessage = defaultMessage;
/*  91 */     this.m_ResourceGroup = resGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, String resGroup, int listID, int stringTag, String defaultMessage) {
/*  96 */     super(cause);
/*  97 */     this.m_ListID = listID;
/*  98 */     this.m_StringTag = stringTag;
/*  99 */     this.m_DefaultMessage = defaultMessage;
/* 100 */     this.m_ResourceGroup = resGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 106 */     this.m_ListID = listID;
/* 107 */     this.m_StringTag = stringTag;
/* 108 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/* 109 */     this.m_ResourceGroup = resGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, String resGroup, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 114 */     super(cause);
/* 115 */     this.m_ListID = listID;
/* 116 */     this.m_StringTag = stringTag;
/* 117 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/* 118 */     this.m_ResourceGroup = resGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, String messageId, LbsMessage defaultTemplateMessage) {
/* 123 */     super(cause);
/* 124 */     this.m_MessageId = messageId;
/* 125 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(String messageId, LbsMessage defaultTemplateMessage) {
/* 131 */     this.m_MessageId = messageId;
/* 132 */     this.m_DefaultTemplateMessage = defaultTemplateMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(Throwable cause, String messageId, String defaultMessage) {
/* 137 */     super(cause);
/* 138 */     this.m_MessageId = messageId;
/* 139 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsLocalizableException(String messageId, String defaultMessage) {
/* 145 */     this.m_MessageId = messageId;
/* 146 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 151 */     return this.m_ListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/* 156 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultMessage() {
/* 161 */     return this.m_DefaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsMessage getDefaultTemplateMessage() {
/* 166 */     return this.m_DefaultTemplateMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceGroup() {
/* 171 */     return this.m_ResourceGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageId() {
/* 176 */     return this.m_MessageId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void localizeMessage(ILocalizationServices lclService) {
/* 181 */     if (JLbsStringUtil.isEmpty(this.m_ResourceGroup))
/* 182 */       this.m_ResourceGroup = "UN"; 
/* 183 */     if (lclService != null) {
/*     */       
/* 185 */       String message = null;
/* 186 */       if (!JLbsStringUtil.isEmpty(this.m_MessageId)) {
/*     */         
/* 188 */         JLbsMessage msg = lclService.getMessage(this.m_MessageId);
/* 189 */         if (msg != null)
/*     */         {
/* 191 */           message = msg.getStringRepresentation(lclService);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 196 */         message = lclService.getItem(this.m_ListID, this.m_StringTag, this.m_ResourceGroup);
/*     */       } 
/* 198 */       if (message == null || message.length() == 0) {
/*     */         
/* 200 */         if (hasSimpleMessage()) {
/* 201 */           this.m_LocalizedMessage = getDefaultMessage();
/* 202 */         } else if (hasTemplateMessage()) {
/* 203 */           this.m_LocalizedMessage = getDefaultTemplateMessage().getMessage();
/*     */         } else {
/* 205 */           this.m_LocalizedMessage = "Localizable Exception with no default message. ListID:" + this.m_ListID + ", StringTag:" + 
/* 206 */             this.m_StringTag;
/*     */         }  return;
/*     */       } 
/* 209 */       if (hasTemplateMessage()) {
/*     */         
/* 211 */         LbsMessage tempMessage = new LbsMessage(message, this.m_DefaultTemplateMessage);
/* 212 */         this.m_LocalizedMessage = tempMessage.getMessage();
/*     */       }
/*     */       else {
/*     */         
/* 216 */         this.m_LocalizedMessage = message;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSimpleMessage() {
/* 224 */     return !JLbsStringUtil.isEmpty(this.m_DefaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTemplateMessage() {
/* 229 */     return (this.m_DefaultTemplateMessage != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedMessage() {
/* 234 */     if (JLbsConstants.isRunningServerSide(null) && this.m_LocalizedMessage == null)
/* 235 */       localizeMessage(JLbsLocalizer.getLocalizationService()); 
/* 236 */     if (this.m_LocalizedMessage != null && this.m_LocalizedMessage.length() > 0)
/* 237 */       return this.m_LocalizedMessage; 
/* 238 */     if (hasSimpleMessage())
/* 239 */       return getDefaultMessage(); 
/* 240 */     if (hasTemplateMessage())
/* 241 */       return getDefaultTemplateMessage().getMessage(); 
/* 242 */     return "Localizable Exception with no default message. ListID:" + this.m_ListID + ", StringTag:" + this.m_StringTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 249 */     return getLocalizedMessage();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsLocalizableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */