/*     */ package com.lbs.validation;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.CustomBusinessObject;
/*     */ import com.lbs.data.objects.ILbsCustBOValidatorProvider;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.ILbsObjectValidator;
/*     */ import com.lbs.platform.interfaces.ILbsValidationEvent;
/*     */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*     */ import com.lbs.transport.RemoteMethodResponse;
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
/*     */ public class CustomBOValidationService
/*     */   implements ILbsCustBOValidatorProvider
/*     */ {
/*     */   private IApplicationContext m_Context;
/*     */   
/*     */   public CustomBOValidationService(IApplicationContext context) {
/*  28 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObjectValidator(CustomBusinessObject object) {
/*  33 */     if (object == null) {
/*  34 */       return null;
/*     */     }
/*     */     
/*     */     try {
/*  38 */       RemoteMethodResponse response = this.m_Context.callRemoteMethod("SCS", "getObjectValidatorName", new Object[] {
/*  39 */             object._getCustomization(), object._getObjectName() });
/*  40 */       if (response.Result instanceof String) {
/*     */         
/*  42 */         String validatorName = (String)response.Result;
/*  43 */         Object validator = this.m_Context.createInstance(validatorName);
/*  44 */         if (validator instanceof ILbsObjectValidator) {
/*  45 */           return validator;
/*     */         }
/*     */       } 
/*  48 */     } catch (Exception e) {
/*     */       
/*  50 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsValidationResult validateCustomBO(IApplicationContext context, CustomBusinessObject bo, ILbsValidationEvent event) {
/*  58 */     ILbsObjectValidator validator = getValidator(context, bo);
/*  59 */     if (validator != null) {
/*     */       
/*  61 */       event = getValidationEvent(context, bo, event);
/*  62 */       return validator.validate(event);
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ILbsValidationEvent getValidationEvent(IApplicationContext context, CustomBusinessObject bo, ILbsValidationEvent event) {
/*  70 */     if (event == null) {
/*     */       
/*  72 */       LbsValidationEvent e = new LbsValidationEvent();
/*  73 */       e.Context = context;
/*  74 */       e.Data = bo;
/*  75 */       event = e;
/*     */     } 
/*  77 */     return event;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsValidationResult initializeCustomBO(IApplicationContext context, CustomBusinessObject bo, ILbsValidationEvent event) {
/*  83 */     ILbsObjectValidator validator = getValidator(context, bo);
/*  84 */     if (validator != null) {
/*     */       
/*  86 */       event = getValidationEvent(context, bo, event);
/*  87 */       return validator.initialize(event);
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsObjectValidator getValidator(IApplicationContext context, CustomBusinessObject bo) {
/*  94 */     if (context == null || bo == null) {
/*  95 */       return null;
/*     */     }
/*  97 */     CustomBusinessObject.setValidatorProvider(new CustomBOValidationService(context));
/*  98 */     Object validator = bo.getValidator();
/*  99 */     if (validator instanceof ILbsObjectValidator)
/*     */     {
/* 101 */       return (ILbsObjectValidator)validator;
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\validation\CustomBOValidationService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */