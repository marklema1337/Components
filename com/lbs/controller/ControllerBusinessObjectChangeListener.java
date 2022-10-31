/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.data.objects.BusinessObjectEvent;
/*    */ import com.lbs.data.objects.IBusinessObjectChangeListener;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ public class ControllerBusinessObjectChangeListener
/*    */   implements IBusinessObjectChangeListener
/*    */ {
/* 10 */   private Hashtable<String, Object> m_ChangeLog = new Hashtable<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void changed(BusinessObjectEvent e) {
/* 15 */     Object oldValue = e.getOldValue();
/* 16 */     if (oldValue == null)
/* 17 */       oldValue = new NullValue(); 
/* 18 */     this.m_ChangeLog.put(e.getMemberName(), oldValue);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMemberFullPath() {
/* 24 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, Object> getChangeLog() {
/* 29 */     return this.m_ChangeLog;
/*    */   }
/*    */   
/*    */   public static class NullValue {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerBusinessObjectChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */