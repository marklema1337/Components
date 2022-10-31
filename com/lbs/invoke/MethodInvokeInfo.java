/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodInvokeInfo
/*    */   implements Serializable
/*    */ {
/* 17 */   public String MethodName = null;
/* 18 */   public Object[] Parameters = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized Object Invoke(Object target) throws EInvokeException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
/* 24 */     if (target == null)
/* 25 */       throw new EInvokeException("Can not run method on null instance!"); 
/* 26 */     Method method = MethodInvoker.findMethod(target.getClass(), this.MethodName, this.Parameters);
/* 27 */     return MethodInvoker.invokeMethod(method, target, this.Parameters);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\MethodInvokeInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */