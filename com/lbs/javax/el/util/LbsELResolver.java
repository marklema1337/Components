/*    */ package com.lbs.javax.el.util;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ELResolver;
/*    */ import java.beans.FeatureDescriptor;
/*    */ import java.util.Iterator;
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
/*    */ public abstract class LbsELResolver
/*    */   extends ELResolver
/*    */ {
/*    */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 36 */     Object value = getValue(context, base, property);
/* 37 */     if (value != null)
/* 38 */       return value.getClass(); 
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Object returnValue(ELContext context, Object value) {
/* 50 */     if (value != null) {
/*    */       
/* 52 */       context.setPropertyResolved(true);
/* 53 */       return value;
/*    */     } 
/* 55 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\e\\util\LbsELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */