/*     */ package com.lbs.message;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.localization.ILocalizationServices;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class JLbsMessage
/*     */   implements Serializable, ILbsMessageConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Id;
/*     */   protected String m_Module;
/*     */   protected int m_Type;
/*     */   protected JLbsMessResource[] m_ButtonCaptions;
/*     */   protected int m_Buttons;
/*     */   protected int m_DefaultButton;
/*     */   protected String m_DefaultMessage;
/*     */   
/*     */   public JLbsMessage(String id, String module, int type, JLbsMessResource[] buttonCaptions, int defaultButton) {
/*  34 */     this.m_Id = id;
/*  35 */     this.m_Module = module;
/*  36 */     this.m_Type = type;
/*  37 */     this.m_ButtonCaptions = buttonCaptions;
/*  38 */     this.m_DefaultButton = defaultButton;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessage(String id, String module, int type, int buttons, int defaultButton) {
/*  44 */     this.m_Id = id;
/*  45 */     this.m_Module = module;
/*  46 */     this.m_Type = type;
/*  47 */     this.m_Buttons = buttons;
/*  48 */     this.m_DefaultButton = defaultButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/*  53 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModule() {
/*  58 */     return this.m_Module;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/*  63 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getButtonCaptions(ILocalizationServices localizationService) {
/*  68 */     if (this.m_ButtonCaptions == null || this.m_ButtonCaptions.length == 0)
/*  69 */       return null; 
/*  70 */     StringBuilder sb = new StringBuilder();
/*  71 */     for (int i = 0; i < this.m_ButtonCaptions.length; i++) {
/*     */       
/*  73 */       if (i > 0)
/*  74 */         sb.append("|"); 
/*  75 */       sb.append(this.m_ButtonCaptions[i].getStringValue(localizationService));
/*     */     } 
/*  77 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getButtons() {
/*  82 */     return this.m_Buttons;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultButton() {
/*  87 */     return this.m_DefaultButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isButtonCaptionsSet() {
/*  92 */     return (this.m_ButtonCaptions != null && this.m_ButtonCaptions.length > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMessage(String defaultMessage) {
/*  97 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultMessage() {
/* 102 */     return this.m_DefaultMessage;
/*     */   }
/*     */   
/*     */   protected abstract JLbsMessageDialogResult show(ILbsMessageDialogImpl paramILbsMessageDialogImpl, ILocalizationServices paramILocalizationServices, ILbsCultureInfo paramILbsCultureInfo, ILbsMessageListener paramILbsMessageListener, Object[] paramArrayOfObject);
/*     */   
/*     */   public abstract String getStringRepresentation(ILocalizationServices paramILocalizationServices);
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */