/*     */ package com.lbs.appobjects;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GOUserParameters
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public int m_firm;
/*     */   public int m_period;
/*     */   public int m_SubCompany;
/*     */   public boolean m_workAreaBased;
/*     */   public boolean m_EDefter = false;
/*     */   public Object m_SignedData;
/*     */   public static final int ADMIN_LOGIN = -1;
/*     */   public static final int LICENSE_LOGIN = -2;
/*     */   public static final int PORTAL_LOGIN = -3;
/*     */   public static final int ONECLICK_LOGIN = -4;
/*     */   
/*     */   public GOUserParameters(int firm, int period, boolean workAreaBased) {
/*  37 */     setFirm(firm);
/*  38 */     setPeriod(period);
/*  39 */     setWorkAreaBased(workAreaBased);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GOUserParameters(int firm, int period, boolean workAreaBased, int subCompany) {
/*  47 */     setFirm(firm);
/*  48 */     setPeriod(period);
/*  49 */     setWorkAreaBased(workAreaBased);
/*  50 */     setSubCompany(subCompany);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GOUserParameters(int firm, int period, boolean workAreaBased, int subCompany, boolean eDefter) {
/*  56 */     this.m_firm = firm;
/*  57 */     this.m_period = period;
/*  58 */     this.m_SubCompany = subCompany;
/*  59 */     this.m_workAreaBased = workAreaBased;
/*  60 */     this.m_EDefter = eDefter;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  65 */     GOUserParameters clone = new GOUserParameters(this.m_firm, this.m_period, this.m_workAreaBased, this.m_SubCompany, this.m_EDefter);
/*  66 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirm() {
/*  75 */     return this.m_firm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPeriod() {
/*  84 */     return this.m_period;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirm(int firm) {
/*  93 */     this.m_firm = firm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPeriod(int period) {
/* 102 */     this.m_period = period;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getWorkAreaBased() {
/* 107 */     return this.m_workAreaBased;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorkAreaBased(boolean workAreaBased) {
/* 112 */     this.m_workAreaBased = workAreaBased;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSubCompany() {
/* 117 */     return this.m_SubCompany;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubCompany(int subCompany) {
/* 122 */     this.m_SubCompany = subCompany;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEDefter(boolean eDefter) {
/* 127 */     this.m_EDefter = eDefter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEDefter() {
/* 132 */     return this.m_EDefter;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\GOUserParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */