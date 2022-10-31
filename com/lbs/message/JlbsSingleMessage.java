/*     */ package com.lbs.message;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringList;
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
/*     */ 
/*     */ 
/*     */ public class JlbsSingleMessage
/*     */   extends JLbsMessage
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected JLbsMessResource m_Title;
/*     */   protected JLbsMessResource m_Message;
/*     */   protected int m_DetailMessagesListId;
/*  25 */   protected String m_DetailResGroup = "UN";
/*     */ 
/*     */ 
/*     */   
/*     */   public JlbsSingleMessage(String id, String module, int type, JLbsMessResource message, JLbsMessResource[] buttonCaptions, int defaultButton) {
/*  30 */     super(id, module, type, buttonCaptions, defaultButton);
/*  31 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JlbsSingleMessage(String id, String module, int type, JLbsMessResource title, JLbsMessResource message, JLbsMessResource[] buttonCaptions, int defaultButton) {
/*  37 */     super(id, module, type, buttonCaptions, defaultButton);
/*  38 */     this.m_Title = title;
/*  39 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public JlbsSingleMessage(String id, String module, int type, JLbsMessResource message, int buttons, int defaultButton) {
/*  44 */     super(id, module, type, buttons, defaultButton);
/*  45 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JlbsSingleMessage(String id, String module, int type, JLbsMessResource title, JLbsMessResource message, int buttons, int defaultButton) {
/*  51 */     super(id, module, type, buttons, defaultButton);
/*  52 */     this.m_Title = title;
/*  53 */     this.m_Message = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMessResource getMessage() {
/*  58 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMainMessage(ILocalizationServices localizationService) {
/*  63 */     if (this.m_Message == null)
/*  64 */       return null; 
/*  65 */     return this.m_Message.getStringValue(localizationService);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle(ILocalizationServices localizationService) {
/*  70 */     if (this.m_Title == null)
/*  71 */       return null; 
/*  72 */     return this.m_Title.getStringValue(localizationService);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getDetailMessages(ILocalizationServices localizationService) {
/*  77 */     if (this.m_DetailMessagesListId == 0)
/*  78 */       return null; 
/*  79 */     return localizationService.getList(this.m_DetailMessagesListId, this.m_DetailResGroup);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailMessagesListId(int detailMessagesListId) {
/*  84 */     this.m_DetailMessagesListId = detailMessagesListId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetailResGroup(String detailResGroup) {
/*  89 */     this.m_DetailResGroup = detailResGroup;
/*  90 */     if (this.m_DetailResGroup == null) {
/*  91 */       this.m_DetailResGroup = "UN";
/*     */     }
/*     */   }
/*     */   
/*     */   protected JLbsMessageDialogResult show(ILbsMessageDialogImpl dialogImpl, ILocalizationServices localizationService, ILbsCultureInfo culture, ILbsMessageListener listener, Object[] messageFormatArguments) {
/*     */     Object buttonDefs;
/*  97 */     String mainMessage = getMainMessage(localizationService);
/*  98 */     if (mainMessage == null || mainMessage.length() == 0)
/*  99 */       mainMessage = this.m_DefaultMessage; 
/* 100 */     String title = getTitle(localizationService);
/* 101 */     JLbsStringList detailMessages = getDetailMessages(localizationService);
/* 102 */     if (listener != null) {
/*     */       
/* 104 */       title = listener.processTitleBeforeShow(this.m_Id, title);
/* 105 */       mainMessage = listener.processMainMessageBeforeShow(this.m_Id, mainMessage, true);
/* 106 */       detailMessages = listener.processDetailMessagesBeforeShow(this.m_Id, detailMessages);
/*     */     } 
/* 108 */     if (messageFormatArguments != null)
/* 109 */       mainMessage = LbsMessageFormatter.format(mainMessage, messageFormatArguments, culture); 
/* 110 */     if (listener != null)
/*     */     {
/* 112 */       mainMessage = listener.processMainMessageBeforeShow(this.m_Id, mainMessage, false);
/*     */     }
/* 114 */     mainMessage = JLbsStringUtil.checkNewLineChars(mainMessage);
/*     */     
/* 116 */     if (isButtonCaptionsSet()) {
/* 117 */       buttonDefs = getButtonCaptions(localizationService);
/*     */     } else {
/* 119 */       buttonDefs = Integer.valueOf(this.m_Buttons);
/* 120 */     }  return dialogImpl.showLbsMessageDialog(this, mainMessage, detailMessages, title, culture, this.m_DefaultButton, buttonDefs);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringRepresentation(ILocalizationServices localizationService) {
/* 125 */     StringBuilder sb = new StringBuilder();
/* 126 */     String mainMessage = getMainMessage(localizationService);
/* 127 */     if (!JLbsStringUtil.isEmpty(mainMessage)) {
/* 128 */       sb.append(mainMessage);
/*     */     } else {
/* 130 */       sb.append("Message " + this.m_Id);
/* 131 */     }  return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JlbsSingleMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */