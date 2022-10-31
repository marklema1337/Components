/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsDateEditWithCalendar;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.LbsComboEditImplementor;
/*     */ import com.lbs.controls.datedit.ILbsFixedDateSelectionListener;
/*     */ import com.lbs.controls.datedit.ILbsInternalDateEdit;
/*     */ import com.lbs.controls.datedit.LbsDateEditWithCalendarImplementor;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class LbsDateEditWithCalendarEmulator
/*     */   extends LbsComboEditEmulator
/*     */   implements ILbsDateEditWithCalendar
/*     */ {
/*     */   public LbsDateEditWithCalendarEmulator() {
/*  27 */     this(new LbsDateEditEmulator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsDateEditWithCalendarEmulator(ILbsInternalDateEdit edit) {
/*  37 */     super((ILbsMaskedEdit)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected LbsComboEditImplementor createImplementor(ILbsMaskedEdit edit) {
/*  42 */     return (LbsComboEditImplementor)new LbsDateEditWithCalendarImplementor(this, (ILbsInternalDateEdit)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtMenuButton() {
/*  47 */     addExtraButton(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsFixedDateSelectionListener getFixedDateSelectionHandler() {
/*  52 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getFixedDateSelectionHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedDateSelectionHandler(ILbsFixedDateSelectionListener listener) {
/*  57 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setFixedDateSelectionHandler(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowWeeks() {
/*  62 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).isShowWeeks();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowWeeks(boolean b) {
/*  67 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setShowWeeks(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalculatedDate(int index) {
/*  72 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setCalculatedDate(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(GregorianCalendar calendar) {
/*  77 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setDate(calendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar calendar) {
/*  82 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setDate(calendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/*  87 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getCalendar();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/*  92 */     super.requestFocus();
/*  93 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/*  98 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).resetModified(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 103 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getCalendarValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsDateEditWithCalendarEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */