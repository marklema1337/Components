/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.localization.JLbsResourceId;
/*    */ import java.io.Serializable;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ public class OperationResult
/*    */ {
/*    */   private boolean m_CanContinue = true;
/* 11 */   private Hashtable<String, Serializable> m_ExtraData = new Hashtable<>();
/* 12 */   private JLbsResourceId m_Resource = null;
/* 13 */   private String m_DefaultMessage = null;
/*    */ 
/*    */   
/*    */   private boolean m_Silent = false;
/*    */ 
/*    */   
/*    */   public OperationResult() {}
/*    */ 
/*    */   
/*    */   public OperationResult(boolean canContinue) {
/* 23 */     this.m_CanContinue = canContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCanContinue() {
/* 28 */     return this.m_CanContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanContinue(boolean canContinue) {
/* 33 */     this.m_CanContinue = canContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putExtraData(String name, Serializable value) {
/* 38 */     this.m_ExtraData.put(name, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Serializable getExtraData(String name) {
/* 43 */     return this.m_ExtraData.get(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsResourceId getResource() {
/* 48 */     return this.m_Resource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResource(JLbsResourceId resource) {
/* 53 */     this.m_Resource = resource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResource(String resourceGroup, int listId, int stringTag) {
/* 58 */     JLbsResourceId resource = new JLbsResourceId(resourceGroup, listId, stringTag);
/* 59 */     this.m_Resource = resource;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDefaultMessage() {
/* 64 */     return this.m_DefaultMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDefaultMessage(String defaultMessage) {
/* 69 */     this.m_DefaultMessage = defaultMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSilent() {
/* 74 */     return this.m_Silent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSilent(boolean isSilent) {
/* 79 */     this.m_Silent = isSilent;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\OperationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */