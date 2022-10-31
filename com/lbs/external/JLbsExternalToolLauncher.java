/*    */ package com.lbs.external;
/*    */ 
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import com.lbs.transport.JLbsJarCache;
/*    */ import java.lang.reflect.Method;
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
/*    */ public class JLbsExternalToolLauncher
/*    */   implements ILbsExternalToolLauncher
/*    */ {
/*    */   private IClientContext m_Context;
/* 20 */   private Process m_Process = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public Object[] run(Object ctx, String className, Object[] params) throws ExternalToolException {
/* 25 */     if (ctx instanceof IClientContext) {
/* 26 */       this.m_Context = (IClientContext)ctx;
/*    */     }
/*    */     try {
/* 29 */       this.m_Context.loadJAR("LbsExternalTools.jar", false, true);
/*    */       
/* 31 */       byte[] clazz = JLbsJarCache.getClassEntry(className);
/*    */       
/* 33 */       Class<?> clazzInst = this.m_Context.loadClass(className);
/* 34 */       Object o = clazzInst.newInstance();
/*    */       
/* 36 */       Method m = clazzInst.getMethod("run", new Class[] { Object[].class });
/* 37 */       return (Object[])m.invoke(o, new Object[] { params });
/*    */     }
/* 39 */     catch (Exception e) {
/*    */       
/* 41 */       throw new ExternalToolException("Error running tool", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object[] run(Object ctx, String className, Object[] params, String msg) throws ExternalToolException {
/* 49 */     return run(ctx, className, params);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\external\JLbsExternalToolLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */