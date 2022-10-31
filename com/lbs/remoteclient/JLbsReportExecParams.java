/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.recording.interfaces.ILbsReportTestListener;
/*     */ import com.lbs.transport.TransportClient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsReportExecParams
/*     */ {
/*     */   private TransportClient m_Client;
/*     */   private String m_ReportingURI;
/*     */   private String m_RepName;
/*     */   private String m_Language;
/*     */   private Object m_ReportParams;
/*     */   private JLbsRunContextParameters m_CtxParams;
/*     */   private transient ILbsReportTestListener m_TestListener;
/*  22 */   private int m_AccessReportCount = 0;
/*     */ 
/*     */   
/*     */   public Object getRunCtx() {
/*  26 */     return (this.m_CtxParams != null) ? 
/*  27 */       this.m_CtxParams.RunContext : 
/*  28 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IParameter getReportParameter() {
/*  33 */     return (this.m_CtxParams != null) ? 
/*  34 */       this.m_CtxParams.ReportParameter : 
/*  35 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getInitCtx() {
/*  40 */     return (this.m_CtxParams != null) ? 
/*  41 */       this.m_CtxParams.InitContext : 
/*  42 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRunContextParameters getCtxParams() {
/*  47 */     return this.m_CtxParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRepName() {
/*  52 */     return this.m_RepName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReportingURI() {
/*  57 */     return this.m_ReportingURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getReportParams() {
/*  62 */     return this.m_ReportParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCtxParams(JLbsRunContextParameters object) {
/*  67 */     this.m_CtxParams = object;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRepName(String string) {
/*  72 */     this.m_RepName = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportingURI(String string) {
/*  77 */     this.m_ReportingURI = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportParams(Object object) {
/*  82 */     this.m_ReportParams = object;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClient getClient() {
/*  87 */     return this.m_Client;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClient(TransportClient client) {
/*  92 */     this.m_Client = client;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/*  97 */     return this.m_Language;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLanguage(String string) {
/* 102 */     this.m_Language = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsReportTestListener getTestListener() {
/* 107 */     return this.m_TestListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTestListener(ILbsReportTestListener testListener) {
/* 112 */     this.m_TestListener = testListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAccessReportCount(int count) {
/* 117 */     this.m_AccessReportCount = count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAccessReportCount() {
/* 122 */     return this.m_AccessReportCount;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsReportExecParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */