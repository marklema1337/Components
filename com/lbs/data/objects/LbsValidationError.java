/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.ILbsValidationError;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
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
/*     */ public class LbsValidationError
/*     */   implements ILbsValidationError
/*     */ {
/*     */   private int m_ID;
/*     */   private int m_ResourceId;
/*     */   private int m_Tag;
/*     */   private int m_Severity;
/*     */   private String m_Message;
/*     */   private ILocalizationServices m_Service;
/*     */   private String[] m_MergeParams;
/*     */   private int m_ControlTag;
/*     */   private int m_SecondTag;
/*     */   
/*     */   public LbsValidationError(ILocalizationServices service) {
/*  33 */     this.m_Service = service;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsValidationError(ILocalizationServices service, int id, int sev, String msg, int resourceId, int strTag) {
/*  38 */     this(service);
/*  39 */     this.m_ID = id;
/*  40 */     this.m_Severity = sev;
/*  41 */     this.m_Message = msg;
/*  42 */     this.m_ResourceId = resourceId;
/*  43 */     this.m_Tag = strTag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsValidationError(ILocalizationServices service, int id, int sev, String msg, int resourceId, int strTag, String[] mergeParams) {
/*  49 */     this(service, id, sev, msg, resourceId, strTag);
/*  50 */     this.m_MergeParams = mergeParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getID() {
/*  55 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedMessage() {
/*  60 */     String s = localizeMessage();
/*  61 */     if (this.m_MergeParams != null) {
/*     */       
/*  63 */       int[] params = new int[this.m_MergeParams.length];
/*  64 */       for (int i = 0; i < params.length; i++)
/*     */       {
/*  66 */         params[i] = i + 1;
/*     */       }
/*  68 */       return JLbsStringUtil.mergeParameters(s, this.m_MergeParams, params);
/*     */     } 
/*  70 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private String localizeMessage() {
/*  75 */     if (this.m_Service != null) {
/*     */       
/*  77 */       String s = this.m_Service.getItem(this.m_ResourceId, this.m_Tag);
/*  78 */       if (!StringUtil.isEmpty(s))
/*  79 */         return s; 
/*     */     } 
/*  81 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  86 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeverity() {
/*  91 */     return this.m_Severity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setID(int id) {
/*  96 */     this.m_ID = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessage(String message) {
/* 101 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceID(int list, int tag) {
/* 106 */     this.m_ResourceId = list;
/* 107 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeverity(int severity) {
/* 112 */     this.m_Severity = severity;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getControlTag() {
/* 117 */     return this.m_ControlTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setControlTag(int controlTag) {
/* 122 */     this.m_ControlTag = controlTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSecondTag() {
/* 127 */     return this.m_SecondTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondTag(int secondTag) {
/* 132 */     this.m_SecondTag = secondTag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\LbsValidationError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */