/*    */ package com.lbs.external;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ILbsExternalToolLauncher
/*    */ {
/*    */   Object[] run(Object paramObject, String paramString, Object[] paramArrayOfObject) throws ExternalToolException;
/*    */   
/*    */   default Object[] run(Object ctx, String className, Object[] params, String message) throws ExternalToolException {
/* 16 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\external\ILbsExternalToolLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */