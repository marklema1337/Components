/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ import com.lbs.controls.datedit.ILbsFixedDateSelectionListener;
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import java.util.Calendar;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ 
/*    */ public interface ILbsDateEditWithCalendar
/*    */   extends ILbsComboEdit
/*    */ {
/*    */   void setDate(GregorianCalendar paramGregorianCalendar);
/*    */   
/*    */   void setDate(Calendar paramCalendar);
/*    */   
/*    */   Calendar getCalendar();
/*    */   
/*    */   void addExtMenuButton();
/*    */   
/*    */   void setFixedDateSelectionHandler(ILbsFixedDateSelectionListener paramILbsFixedDateSelectionListener);
/*    */   
/*    */   ILbsFixedDateSelectionListener getFixedDateSelectionHandler();
/*    */   
/*    */   boolean isShowWeeks();
/*    */   
/*    */   void setShowWeeks(boolean paramBoolean);
/*    */   
/*    */   void setCalculatedDate(int paramInt);
/*    */   
/*    */   void resetModified(Calendar paramCalendar);
/*    */   
/*    */   Calendar getCalendarValue();
/*    */   
/*    */   default ILbsDateEdit getDateEdit() {
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   default void setCultureInfo(ILbsCultureInfo info) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsDateEditWithCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */