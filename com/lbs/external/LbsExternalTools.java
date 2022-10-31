/*    */ package com.lbs.external;
/*    */ 
/*    */ import com.lbs.dialogs.LbsObjectFactory;
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
/*    */ public class LbsExternalTools
/*    */ {
/*    */   public static Object[] launch(Object ctx, String className, Object[] inputs) throws ExternalToolException {
/* 20 */     ILbsExternalToolLauncher extTool = (ILbsExternalToolLauncher)LbsObjectFactory.createObject(ILbsExternalToolLauncher.class, new Object[0]);
/* 21 */     Object[] resultObjects = extTool.run(ctx, className, inputs);
/* 22 */     return resultObjects;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Object[] launch(Object ctx, String className, Object[] inputs, String message) throws ExternalToolException {
/* 27 */     ILbsExternalToolLauncher extTool = (ILbsExternalToolLauncher)LbsObjectFactory.createObject(ILbsExternalToolLauncher.class, new Object[0]);
/* 28 */     Object[] resultObjects = extTool.run(ctx, className, inputs, message);
/* 29 */     return resultObjects;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\external\LbsExternalTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */