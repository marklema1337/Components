/*    */ package com.lbs.javax.el.util;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.data.objects.ObjectValueManager;
/*    */ import com.lbs.javax.el.ELContext;
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
/*    */ 
/*    */ public class LbsObjectELResolver
/*    */   extends LbsELResolver
/*    */ {
/* 23 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(LbsObjectELResolver.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getValue(ELContext context, Object base, Object property) {
/* 28 */     if (base instanceof java.util.Map || base instanceof java.util.List || base instanceof com.lbs.platform.interfaces.IApplicationContext)
/* 29 */       return null; 
/* 30 */     if (base != null && property instanceof String)
/*    */       
/*    */       try {
/* 33 */         Object value = ObjectValueManager.getMemberValueEx(base, null, (String)property, null, true, true);
/* 34 */         if (value != null) {
/* 35 */           return returnValue(context, value);
/*    */         }
/* 37 */       } catch (Exception e) {
/*    */         
/* 39 */         ms_Logger.error(e, e);
/*    */       }  
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 47 */     if (base instanceof com.lbs.data.objects.BasicBusinessObject && property instanceof String)
/*    */       
/*    */       try {
/*    */         
/* 51 */         ObjectValueManager.setMemberValue(base, (String)property, value);
/* 52 */         context.setPropertyResolved(true);
/*    */       }
/* 54 */       catch (Exception e) {
/*    */         
/* 56 */         ms_Logger.error(e, e);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\e\\util\LbsObjectELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */