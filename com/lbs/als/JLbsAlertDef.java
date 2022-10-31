/*     */ package com.lbs.als;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class JLbsAlertDef
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   private int m_Inactive = 0;
/*  19 */   private int m_IsRecurrent = 0;
/*  20 */   private JLbsRecurrenceRuleDef m_RecurrenceRuleDef = new JLbsRecurrenceRuleDef();
/*  21 */   private GregorianCalendar m_StartTime = new GregorianCalendar();
/*  22 */   private int m_Requester = 0;
/*  23 */   private int m_State = 0;
/*     */   
/*     */   public static final int STATE_NEW = 0;
/*     */   
/*     */   public static final int STATE_UNMODIFIED = 1;
/*     */   public static final int STATE_MODIFIED = 2;
/*     */   public static final int STATE_DELETED = 3;
/*  30 */   private int handleID = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInactive() {
/*  37 */     return this.m_Inactive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInactive(int value) {
/*  45 */     this.m_Inactive = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIsRecurrent() {
/*  53 */     return this.m_IsRecurrent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsRecurrent(int value) {
/*  61 */     this.m_IsRecurrent = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRecurrenceRuleDef getLRecurrenceRuleDef() {
/*  66 */     return this.m_RecurrenceRuleDef;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitializeLRecurrenceRuleDef() {
/*  71 */     this.m_RecurrenceRuleDef = new JLbsRecurrenceRuleDef();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GregorianCalendar getStartTime() {
/*  79 */     return this.m_StartTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartTime(GregorianCalendar value) {
/*  87 */     if (value == null)
/*  88 */       throw new NullPointerException("Value cannot be null!"); 
/*  89 */     this.m_StartTime = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public int _getState() {
/*  94 */     return this.m_State;
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setState(int state) {
/*  99 */     this.m_State = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequester() {
/* 104 */     return this.m_Requester;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequester(int requester) {
/* 109 */     this.m_Requester = requester;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHandleID() {
/* 116 */     return this.handleID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHandleID(int handleID) {
/* 121 */     this.handleID = handleID;
/*     */   }
/*     */   
/*     */   public boolean isOneTimeAlert() {
/* 125 */     return (getIsRecurrent() == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\als\JLbsAlertDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */