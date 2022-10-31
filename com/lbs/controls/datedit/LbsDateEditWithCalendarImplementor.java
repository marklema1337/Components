/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboEdit;
/*     */ import com.lbs.control.interfaces.ILbsDateEdit;
/*     */ import com.lbs.control.interfaces.ILbsDateEditWithCalendar;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.controls.LbsComboEditImplementor;
/*     */ import com.lbs.util.JLbsDateUtil;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsDateEditWithCalendarImplementor
/*     */   extends LbsComboEditImplementor
/*     */ {
/*     */   private boolean m_ShowWeeks;
/*     */   protected ILbsFixedDateSelectionListener m_FixedDateSelectionHandler;
/*     */   
/*     */   public LbsDateEditWithCalendarImplementor(ILbsDateEditWithCalendar component, ILbsInternalDateEdit edit) {
/*  30 */     super((ILbsComboEdit)component, (ILbsMaskedEdit)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/*  35 */     this.m_Edit.grabFocus();
/*     */   }
/*     */   
/*     */   public void setDate(GregorianCalendar cal) {
/*  39 */     setDate(cal);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar calendar) {
/*  44 */     ((ILbsDateEdit)this.m_Edit).setDate(calendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/*  49 */     return ((ILbsDateEdit)this.m_Edit).getCalendar();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getCalculatedDate(int index) {
/*  54 */     return getCalculatedDate(index, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Calendar getCalculatedDate(int index, TimeZone timeZone) {
/*     */     Calendar calendar;
/*  61 */     if (timeZone != null) {
/*  62 */       calendar = new GregorianCalendar(timeZone);
/*     */     } else {
/*  64 */       calendar = new GregorianCalendar();
/*     */     } 
/*  66 */     switch (index) {
/*     */       
/*     */       case 0:
/*  69 */         calendar.add(5, -1);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  74 */         calendar.add(5, 1);
/*     */         break;
/*     */       case 4:
/*  77 */         calendar.set(7, 2);
/*     */         break;
/*     */       case 5:
/*  80 */         calendar.set(7, 6);
/*     */         break;
/*     */       case 7:
/*  83 */         calendar.set(5, 1);
/*     */         break;
/*     */       case 8:
/*  86 */         calendar.set(5, JLbsDateUtil.getNumDays(calendar.get(2), calendar
/*  87 */               .get(1)));
/*     */         break;
/*     */       case 10:
/*  90 */         calendar.set(7, 2);
/*  91 */         calendar.add(5, -7);
/*     */         break;
/*     */       case 11:
/*  94 */         calendar.set(7, 6);
/*  95 */         calendar.add(5, -7);
/*     */         break;
/*     */       case 13:
/*  98 */         calendar.set(7, 2);
/*  99 */         calendar.add(5, 7);
/*     */         break;
/*     */       case 14:
/* 102 */         calendar.set(7, 6);
/* 103 */         calendar.add(5, 7);
/*     */         break;
/*     */       case 16:
/* 106 */         calendar.set(5, 15);
/*     */         break;
/*     */       case 18:
/* 109 */         calendar.add(6, 7);
/*     */         break;
/*     */       case 19:
/* 112 */         calendar.add(6, 15);
/*     */         break;
/*     */       case 21:
/* 115 */         calendar.add(6, -7);
/*     */         break;
/*     */       case 22:
/* 118 */         calendar.add(6, -15);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 123 */     return calendar;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalculatedDate(int index) {
/* 128 */     ILbsInternalDateEdit edit = (ILbsInternalDateEdit)this.m_Edit;
/* 129 */     edit.setOriginalText(this.m_Edit.getText());
/* 130 */     edit.setValueChangedByParent(true);
/* 131 */     edit.setDate(getCalculatedDate(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 136 */     ILbsPopupMenuItem source = (ILbsPopupMenuItem)e.getSource();
/* 137 */     int index = source.getIndex();
/* 138 */     setCalculatedDate(index);
/* 139 */     if (this.m_FixedDateSelectionHandler != null) {
/* 140 */       this.m_FixedDateSelectionHandler.fixedDateSelected(index + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsFixedDateSelectionListener getFixedDateSelectionHandler() {
/* 145 */     return this.m_FixedDateSelectionHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedDateSelectionHandler(ILbsFixedDateSelectionListener listener) {
/* 150 */     this.m_FixedDateSelectionHandler = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowWeeks() {
/* 155 */     return this.m_ShowWeeks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowWeeks(boolean b) {
/* 160 */     this.m_ShowWeeks = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 165 */     ((ILbsDateEdit)this.m_Edit).resetModified(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 170 */     return ((ILbsDateEdit)this.m_Edit).getCalendarValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\LbsDateEditWithCalendarImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */