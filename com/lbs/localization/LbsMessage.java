/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.Serializable;
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
/*     */ public class LbsMessage
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  20 */   protected static IPropertyProvider ms_PropertyProvider = null;
/*     */   
/*     */   protected String m_TemplateMessage;
/*     */   
/*     */   protected String[] m_Replacements;
/*     */   
/*     */   protected String m_Result;
/*     */ 
/*     */   
/*     */   public LbsMessage(String templateMessage, LbsMessage parent) {
/*  30 */     this(templateMessage, parent.m_Replacements);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsMessage(String templateMessage, String[] replacements) {
/*  36 */     this.m_TemplateMessage = templateMessage;
/*  37 */     if (replacements == null)
/*  38 */       replacements = new String[0]; 
/*  39 */     this.m_Replacements = replacements;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsMessage(String templateMessage, Object source, String[] propNames) {
/*  44 */     this(templateMessage, prepareReplacements(source, propNames));
/*     */   }
/*     */ 
/*     */   
/*     */   private static String[] prepareReplacements(Object source, String[] propNames) {
/*  49 */     IPropertyProvider provider = ms_PropertyProvider;
/*  50 */     if (provider == null) {
/*  51 */       provider = new DefaultPropertyProvider();
/*     */     }
/*  53 */     if (propNames == null)
/*  54 */       propNames = new String[0]; 
/*  55 */     String[] replacements = new String[propNames.length];
/*  56 */     for (int i = 0; i < propNames.length; i++)
/*     */     {
/*  58 */       replacements[i] = provider.getValue(source, propNames[i]);
/*     */     }
/*  60 */     return replacements;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  65 */     if (JLbsStringUtil.isEmpty(this.m_Result)) {
/*  66 */       prepareResult();
/*     */     }
/*  68 */     return this.m_Result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void prepareResult() {
/*     */     try {
/*  75 */       this.m_Result = JLbsStringUtil.mergeParameters(this.m_TemplateMessage, this.m_Replacements, getIntArray());
/*     */     }
/*  77 */     catch (Exception t) {
/*     */ 
/*     */       
/*  80 */       this.m_Result = this.m_TemplateMessage;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] getIntArray() {
/*  86 */     int[] params = new int[this.m_Replacements.length];
/*  87 */     for (int i = 0; i < params.length; i++)
/*     */     {
/*  89 */       params[i] = i + 1;
/*     */     }
/*  91 */     return params;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setPropertyProvider(IPropertyProvider propertyProvider) {
/*  96 */     ms_PropertyProvider = propertyProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getReplacements() {
/* 101 */     return this.m_Replacements;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */