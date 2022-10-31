/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsMultiMessage
/*    */   extends JLbsMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected JLbsMessResource[] m_Titles;
/*    */   protected JLbsMessResource[] m_Messages;
/*    */   
/*    */   public JLbsMultiMessage(String id, String module, int type, JLbsMessResource[] titles, JLbsMessResource[] messages) {
/* 24 */     super(id, module, type, 1, 1);
/* 25 */     this.m_Titles = titles;
/* 26 */     this.m_Messages = messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTitles(ILocalizationServices localizationService) {
/* 31 */     if (this.m_Titles == null) {
/* 32 */       return null;
/*    */     }
/* 34 */     String[] titles = new String[this.m_Titles.length];
/* 35 */     for (int i = 0; i < titles.length; i++)
/*    */     {
/* 37 */       titles[i] = this.m_Titles[i].getStringValue(localizationService);
/*    */     }
/* 39 */     return titles;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getMessages(ILocalizationServices localizationService) {
/* 44 */     if (this.m_Messages == null) {
/* 45 */       return null;
/*    */     }
/* 47 */     String[] messages = new String[this.m_Messages.length];
/* 48 */     for (int i = 0; i < messages.length; i++)
/*    */     {
/* 50 */       messages[i] = this.m_Messages[i].getStringValue(localizationService);
/*    */     }
/* 52 */     return messages;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected JLbsMessageDialogResult show(ILbsMessageDialogImpl dialogImpl, ILocalizationServices localizationService, ILbsCultureInfo culture, ILbsMessageListener listener, Object[] messageFormatArguments) {
/* 58 */     String[] titles = getTitles(localizationService);
/* 59 */     String[] messages = getMessages(localizationService);
/* 60 */     if (listener != null) {
/*    */       
/* 62 */       titles = listener.processTitlesBeforeShow(this.m_Id, titles);
/* 63 */       messages = listener.processMainMessagesBeforeShow(this.m_Id, messages);
/*    */     } 
/* 65 */     dialogImpl.showMultiLineMessages(this, messages, titles);
/* 66 */     JLbsMessageDialogResult result = new JLbsMessageDialogResult();
/* 67 */     result.button = 1;
/* 68 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStringRepresentation(ILocalizationServices localizationService) {
/* 73 */     String[] messages = getMessages(localizationService);
/* 74 */     StringBuilder sb = new StringBuilder();
/* 75 */     if (messages != null)
/*    */     {
/* 77 */       for (int i = 0; i < messages.length; i++) {
/*    */         
/* 79 */         if (i > 0)
/* 80 */           sb.append("\n"); 
/* 81 */         sb.append(messages[i]);
/*    */       } 
/*    */     }
/* 84 */     if (sb.length() == 0)
/* 85 */       sb.append("Message " + this.m_Id); 
/* 86 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMultiMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */