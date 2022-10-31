/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import com.lbs.controls.ILbsTableGridPanel;
/*    */ import com.lbs.controls.JLbsPanel;
/*    */ import com.lbs.invoke.RttiUtil;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsTableGridFactory
/*    */ {
/*    */   private static boolean ms_LicenceCheck = false;
/*    */   
/*    */   public static JLbsPanel getTableGridPanel(TableReportInfo info) {
/* 15 */     ILbsTableGridPanel result = null;
/*    */     
/*    */     try {
/* 18 */       if (!ms_LicenceCheck) {
/*    */         
/* 20 */         setLicence();
/* 21 */         ms_LicenceCheck = true;
/*    */       } 
/*    */       
/* 24 */       Class<?> pivotclazz = Thread.currentThread().getContextClassLoader().loadClass("com.lbs.tablegrid.TableGridPanel");
/* 25 */       if (pivotclazz == null) {
/*    */         
/* 27 */         System.out.println("Table Grid doesn't exist.");
/* 28 */         return null;
/*    */       } 
/* 30 */       Object obj = pivotclazz.newInstance();
/* 31 */       result = (ILbsTableGridPanel)obj;
/* 32 */       if (result != null)
/*    */       {
/* 34 */         return result.getTableGridPanel(info);
/*    */       }
/*    */     }
/* 37 */     catch (Exception e) {
/*    */       
/* 39 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setLicence() {
/*    */     try {
/* 49 */       Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.jidesoft.utils.Lm");
/* 50 */       Method[] method = RttiUtil.getMethods(clazz, "verifyLicense");
/* 51 */       method[0].invoke(null, new Object[] { "Logo Yazilim San. ve Tic. A.S.", "J-Guar", "qbZiyEaYh:YdsFOrS0N5BbnkxUp8VJc" });
/*    */     }
/* 53 */     catch (Exception e) {
/*    */       
/* 55 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isLicenceChecked() {
/* 61 */     return ms_LicenceCheck;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setLicenceChecked(boolean licenceCheck) {
/* 66 */     ms_LicenceCheck = licenceCheck;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\JLbsTableGridFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */