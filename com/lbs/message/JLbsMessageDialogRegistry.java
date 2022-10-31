/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.util.LbsClassInstanceProvider;
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
/*    */ public class JLbsMessageDialogRegistry
/*    */ {
/*    */   public static void setDefaultDialog(ILbsMessageDialogImpl defaultDialog) {
/* 20 */     if (defaultDialog == null) {
/*    */       
/* 22 */       (JLbsMessageDialogRegistryFieldHolder.getInstance()).defaultDialog = getSwingDialog();
/*    */     }
/*    */     else {
/*    */       
/* 26 */       (JLbsMessageDialogRegistryFieldHolder.getInstance()).defaultDialog = defaultDialog;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsMessageDialogImpl getDefaultDialog() {
/* 32 */     if ((JLbsMessageDialogRegistryFieldHolder.getInstance()).defaultDialog == null)
/*    */     {
/* 34 */       (JLbsMessageDialogRegistryFieldHolder.getInstance()).defaultDialog = getSwingDialog();
/*    */     }
/* 36 */     return (JLbsMessageDialogRegistryFieldHolder.getInstance()).defaultDialog;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsMessageDialogImpl getSwingDialog() {
/* 41 */     return JLbsMessageDialogImplSwing.getInstance();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsMessageDialogImpl getEmulatorDialog(ILbsMessageExecutor executor) {
/* 46 */     return new JLbsMessageDialogImplEmulator(executor);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class JLbsMessageDialogRegistryFieldHolder
/*    */   {
/*    */     private ILbsMessageDialogImpl defaultDialog;
/*    */     
/*    */     private static JLbsMessageDialogRegistryFieldHolder getInstance() {
/* 55 */       return (JLbsMessageDialogRegistryFieldHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsMessageDialogRegistryFieldHolder.class);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\JLbsMessageDialogRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */