/*     */ package com.lbs.platform.client;
/*     */ 
/*     */ import com.lbs.util.SetUtil;
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
/*     */ public class SessionProfileOptions
/*     */ {
/*     */   public static final int OPTION_BLH = 1;
/*     */   public static final int OPTION_QUERY = 2;
/*     */   public static final int OPTION_OBJECT = 4;
/*     */   public static final int OPTION_REMOTING = 8;
/*     */   public static final int OPTION_TRANSPORT = 16;
/*     */   public static final int OPTION_REPORTING = 32;
/*     */   public static final int OPTION_CONTEXT = 64;
/*     */   public static final int OPTION_OTHERS = 128;
/*     */   public static final int OPTION_ALL = 255;
/*     */   private boolean m_BusinessLogic = true;
/*     */   private boolean m_Query = true;
/*     */   private boolean m_Object = true;
/*     */   private boolean m_Remoting = true;
/*     */   private boolean m_Transport = true;
/*     */   private boolean m_Reporting = true;
/*     */   private boolean m_Context = true;
/*     */   private boolean m_Others = true;
/*     */   private boolean m_Ok = false;
/*     */   
/*     */   public SessionProfileOptions() {}
/*     */   
/*     */   public SessionProfileOptions(int options) {
/*  45 */     this.m_BusinessLogic = SetUtil.isOptionSet(options, 1);
/*  46 */     this.m_Query = SetUtil.isOptionSet(options, 2);
/*  47 */     this.m_Object = SetUtil.isOptionSet(options, 4);
/*  48 */     this.m_Remoting = SetUtil.isOptionSet(options, 8);
/*  49 */     this.m_Transport = SetUtil.isOptionSet(options, 16);
/*  50 */     this.m_Reporting = SetUtil.isOptionSet(options, 32);
/*  51 */     this.m_Context = SetUtil.isOptionSet(options, 64);
/*  52 */     this.m_Others = SetUtil.isOptionSet(options, 128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOk(boolean ok) {
/*  57 */     this.m_Ok = ok;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOk() {
/*  62 */     return this.m_Ok;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/*  67 */     int options = 0;
/*     */     
/*  69 */     if (this.m_BusinessLogic)
/*  70 */       options = SetUtil.setOption(options, 1); 
/*  71 */     if (this.m_Query)
/*  72 */       options = SetUtil.setOption(options, 2); 
/*  73 */     if (this.m_Object)
/*  74 */       options = SetUtil.setOption(options, 4); 
/*  75 */     if (this.m_Remoting)
/*  76 */       options = SetUtil.setOption(options, 8); 
/*  77 */     if (this.m_Transport)
/*  78 */       options = SetUtil.setOption(options, 16); 
/*  79 */     if (this.m_Reporting)
/*  80 */       options = SetUtil.setOption(options, 32); 
/*  81 */     if (this.m_Context)
/*  82 */       options = SetUtil.setOption(options, 64); 
/*  83 */     if (this.m_Others) {
/*  84 */       options = SetUtil.setOption(options, 128);
/*     */     }
/*  86 */     return options;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBusinessLogic() {
/*  91 */     return this.m_BusinessLogic;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusinessLogic(boolean businessLogic) {
/*  96 */     this.m_BusinessLogic = businessLogic;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getQuery() {
/* 101 */     return this.m_Query;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQuery(boolean query) {
/* 106 */     this.m_Query = query;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getObject() {
/* 111 */     return this.m_Object;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObject(boolean object) {
/* 116 */     this.m_Object = object;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRemoting() {
/* 121 */     return this.m_Remoting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRemoting(boolean remoting) {
/* 126 */     this.m_Remoting = remoting;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getTransport() {
/* 131 */     return this.m_Transport;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransport(boolean transport) {
/* 136 */     this.m_Transport = transport;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getReporting() {
/* 141 */     return this.m_Reporting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReporting(boolean reporting) {
/* 146 */     this.m_Reporting = reporting;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getContext() {
/* 151 */     return this.m_Context;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContext(boolean context) {
/* 156 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getOthers() {
/* 161 */     return this.m_Others;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOthers(boolean others) {
/* 166 */     this.m_Others = others;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\SessionProfileOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */