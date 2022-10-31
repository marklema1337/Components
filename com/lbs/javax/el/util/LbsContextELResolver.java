/*    */ package com.lbs.javax.el.util;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsContextELResolver
/*    */   extends LbsELResolver
/*    */ {
/*    */   private static final String CONTEXT = "context";
/*    */   private static IContextVariableResolver ms_VarResolver;
/*    */   private IApplicationContext m_Context;
/*    */   
/*    */   public LbsContextELResolver(IApplicationContext context) {
/* 26 */     this.m_Context = context;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getValue(ELContext context, Object base, Object property) {
/* 32 */     if (property instanceof String) {
/*    */       
/* 34 */       String propName = (String)property;
/* 35 */       if (base == null) {
/*    */         
/* 37 */         if (propName.equalsIgnoreCase("context"))
/* 38 */           return returnValue(context, this.m_Context); 
/* 39 */         Object object = getContextVariable(this.m_Context, propName);
/* 40 */         if (object != null) {
/* 41 */           return returnValue(context, object);
/*    */         }
/* 43 */       } else if (base instanceof IApplicationContext) {
/*    */         
/* 45 */         Object value = getContextVariable((IApplicationContext)base, propName);
/* 46 */         if (value != null)
/* 47 */           return returnValue(context, value); 
/*    */       } 
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Object getContextVariable(IApplicationContext context, String varName) {
/* 55 */     Object value = context.getVariable(varName);
/* 56 */     if (value != null)
/* 57 */       return value; 
/* 58 */     if (ms_VarResolver != null)
/* 59 */       value = ms_VarResolver.resolveApplicationVariable(context, varName); 
/* 60 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 66 */     if (property instanceof String) {
/*    */       
/* 68 */       String propName = (String)property;
/* 69 */       if (base == null)
/*    */       {
/* 71 */         if (propName.equalsIgnoreCase("context"))
/*    */         {
/* 73 */           return true;
/*    */         }
/*    */       }
/*    */     } 
/* 77 */     return super.isReadOnly(context, base, property);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 83 */     if (property instanceof String) {
/*    */       
/* 85 */       String propName = (String)property;
/* 86 */       if (base instanceof IApplicationContext) {
/*    */         
/* 88 */         if (value != null) {
/* 89 */           ((IApplicationContext)base).setVariable(propName, value);
/*    */         } else {
/* 91 */           ((IApplicationContext)base).removeVariable(propName);
/* 92 */         }  context.setPropertyResolved(true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setVarResolver(IContextVariableResolver varResolver) {
/* 99 */     ms_VarResolver = varResolver;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\e\\util\LbsContextELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */