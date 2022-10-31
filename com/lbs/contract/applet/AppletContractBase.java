/*     */ package com.lbs.contract.applet;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.contract.ContractException;
/*     */ import com.lbs.contract.ContractParameter;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.ValueConverter;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
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
/*     */ public abstract class AppletContractBase
/*     */   implements IContractExecutor, IAppletContractConstants
/*     */ {
/*  28 */   protected static final LbsConsole ms_Logger = LbsConsole.getLogger(AppletContractBase.class);
/*     */   
/*  30 */   private static HashMap<String, Integer> ms_Statistics = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static String getMandatoryImplProp(Hashtable<String, String> implProps, String propName) throws ContractException {
/*  34 */     String value = implProps.get(propName);
/*     */     
/*  36 */     if (StringUtil.isEmpty(value))
/*  37 */       throw new ContractException("Contract implementation property named '" + propName + "' is mandatory and is undefined!"); 
/*  38 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getTypedMandatoryImplProp(Hashtable<String, String> implProps, String propName, Class<T> propType) throws ContractException {
/*  45 */     String value = getMandatoryImplProp(implProps, propName);
/*     */     
/*     */     try {
/*  48 */       return (T)ValueConverter.convert(propType, value);
/*     */     }
/*  50 */     catch (Exception e) {
/*     */       
/*  52 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getTypedImplProp(Hashtable<String, String> implProps, String propName, Class<T> propType, T defaultValue) throws ContractException {
/*  60 */     String value = implProps.get(propName);
/*     */     
/*     */     try {
/*  63 */       return (T)ValueConverter.convert(propType, value);
/*     */     }
/*  65 */     catch (Exception e) {
/*     */       
/*  67 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reportException(IClientContext context, IContractCallback callback, Throwable t) {
/*  73 */     if (callback != null)
/*     */     {
/*  75 */       callback.onException(context, new ContractExceptionEvent(t));
/*     */     }
/*  77 */     ms_Logger.error(t, t);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reportComplete(IClientContext context, IContractCallback callback, ContractParameter... outputs) {
/*  82 */     if (callback != null)
/*     */     {
/*  84 */       callback.onComplete(context, outputs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getContractDateProperty(Hashtable<String, String> implProps, String datePropName) throws ParseException {
/*  90 */     if (implProps == null || StringUtil.isEmpty(datePropName)) {
/*  91 */       return null;
/*     */     }
/*  93 */     String dateValue = implProps.get(datePropName);
/*  94 */     if (StringUtil.isEmpty(dateValue)) {
/*  95 */       return null;
/*     */     }
/*  97 */     SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss (zzzz)");
/*  98 */     Calendar cal = Calendar.getInstance();
/*  99 */     Date date = formatter.parse(dateValue);
/* 100 */     cal.setTime(date);
/* 101 */     return cal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getContractDateString(Calendar date) {
/* 106 */     if (date == null) {
/* 107 */       return "";
/*     */     }
/* 109 */     SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss (zzzz)");
/* 110 */     formatter.setCalendar(date);
/* 111 */     return formatter.format(date.getTime());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addMap(String key, int value) {
/* 116 */     synchronized (ms_Statistics) {
/*     */       
/* 118 */       if (ms_Statistics.get(key) != null) {
/*     */         
/* 120 */         value += ((Integer)ms_Statistics.get(key)).intValue();
/* 121 */         ms_Statistics.remove(key);
/*     */       } 
/* 123 */       ms_Statistics.put(key, Integer.valueOf(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static HashMap<String, Integer> getStatistics() {
/* 129 */     return ms_Statistics;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearMap() {
/* 134 */     ms_Statistics.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFormMode(String formName) {
/* 139 */     if (StringUtil.isEmpty(formName)) {
/* 140 */       return 0;
/*     */     }
/* 142 */     int idx = formName.indexOf("%");
/*     */     
/* 144 */     if (idx < 0) {
/* 145 */       return 0;
/*     */     }
/* 147 */     int idxPoint = formName.lastIndexOf(".");
/*     */     
/* 149 */     if (idxPoint < 0)
/* 150 */       return 0; 
/* 151 */     String mode = formName.substring(idx + 1, idxPoint);
/*     */     
/* 153 */     if (!StringUtil.isEmpty(mode))
/* 154 */       return Integer.valueOf(mode).intValue(); 
/* 155 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\AppletContractBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */