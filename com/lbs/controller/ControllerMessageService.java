/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.message.JLbsMessageDialogResult;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControllerMessageService
/*    */   implements MessageService
/*    */ {
/* 12 */   private ArrayList<ValidationMessage> m_ValidationMessages = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsMessageDialogResult handleMessage(IApplicationContext context, String messageConstantId, String defaultMessage) {
/* 17 */     return context.showMessage(messageConstantId, defaultMessage, null, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsMessageDialogResult handleMessage(IApplicationContext context, String messageConstantId, String defaultMessage, String[] messageSubstitutions) {
/* 24 */     return context.showMessage(messageConstantId, defaultMessage, (Object[])messageSubstitutions, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean confirmed(IApplicationContext context, String messageConstantId, String defaultMessage) {
/* 30 */     int msgRes = (context.showMessage(messageConstantId, defaultMessage, null, null)).button;
/* 31 */     return !(msgRes != 1 && msgRes != 4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean confirmed(IApplicationContext context, String messageConstantId, String defaultMessage, String[] messageSubstitutions) {
/* 38 */     int msgRes = (context.showMessage(messageConstantId, defaultMessage, (Object[])messageSubstitutions, null)).button;
/* 39 */     return !(msgRes != 1 && msgRes != 4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addValidationMessage(ValidationMessage message) {
/* 45 */     this.m_ValidationMessages.add(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<ValidationMessage> getValidationMessages() {
/* 50 */     return this.m_ValidationMessages;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerMessageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */