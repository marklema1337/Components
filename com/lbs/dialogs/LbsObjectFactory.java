/*    */ package com.lbs.dialogs;
/*    */ 
/*    */ import com.lbs.util.JLbsConstants;
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
/*    */ public class LbsObjectFactory
/*    */ {
/*    */   private static ILbsObjectCreator objectCreator;
/*    */   private static ILbsObjectCreator objectCreatorOverridden;
/*    */   
/*    */   static {
/*    */     try {
/* 23 */       objectCreator = (ILbsObjectCreator)LbsObjectFactory.class.getClassLoader().loadClass("com.lbs.factory.JLbsObjectCreator").newInstance();
/*    */     }
/* 25 */     catch (Exception e) {
/*    */       
/* 27 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsObjectCreator getObjectCreator(Object component) {
/* 33 */     if (JLbsConstants.isRunningServerSide(component))
/*    */     {
/*    */       
/* 36 */       return objectCreatorOverridden;
/*    */     }
/* 38 */     return objectCreator;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <E> E createObject(Class<E> type, Object... parameters) {
/* 43 */     return createObject(null, type, parameters);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <E> E createObject(Object component, Class<E> type, Object... parameters) {
/* 48 */     return (E)getObjectCreator(component).createObject(type, parameters);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setObjectCreator(ILbsObjectCreator objectCreator) {
/* 53 */     objectCreatorOverridden = objectCreator;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dialogs\LbsObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */