/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.data.objects.BusinessObjectEvent;
/*    */ import java.util.ArrayList;
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
/*    */ public class BusinessObjectChangeLog
/*    */ {
/*    */   private BusinessObjectChangeHeader m_Header;
/* 18 */   private ArrayList m_ChangeEvents = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public BusinessObjectChangeLog(BusinessObjectChangeHeader header) {
/* 23 */     this.m_Header = header;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToChangeEvents(BusinessObjectEvent e) {
/* 28 */     this.m_ChangeEvents.add(e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList getChangeEvents() {
/* 34 */     return this.m_ChangeEvents;
/*    */   }
/*    */   
/*    */   public BusinessObjectChangeHeader getHeader() {
/* 38 */     return this.m_Header;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\BusinessObjectChangeLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */