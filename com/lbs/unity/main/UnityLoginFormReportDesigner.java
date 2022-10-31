/*    */ package com.lbs.unity.main;
/*    */ 
/*    */ import com.lbs.transport.UserInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnityLoginFormReportDesigner
/*    */   extends UnityLoginForm
/*    */ {
/*    */   protected Object getMainFormInstance(UserInfo userInfo) throws Exception {
/* 14 */     this.m_MainForm = "com/lbs/unity/main/UnityClientReportDesignerMainForm";
/*    */     
/* 16 */     Object object = this.m_Context.createInstance(this.m_MainForm);
/* 17 */     UnityClientReportDesignerMainForm form = (UnityClientReportDesignerMainForm)object;
/* 18 */     form.initMainForm();
/*    */     
/* 20 */     return form;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVisible(boolean aFlag) {
/* 26 */     super.setVisible(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isCheckOldSessions() {
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\unity\main\UnityLoginFormReportDesigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */