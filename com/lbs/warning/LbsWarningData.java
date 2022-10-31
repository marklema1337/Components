/*    */ package com.lbs.warning;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.resource.JLbsLocalizer;
/*    */ import java.io.Serializable;
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
/*    */ public class LbsWarningData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7033137609227393830L;
/*    */   private int m_Residual;
/*    */   private int m_Type;
/*    */   public static final int RECORD_LIMIT = 1;
/*    */   
/*    */   public LbsWarningData(int residual, int type) {
/* 28 */     this.m_Residual = residual;
/* 29 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLimitMessage(ILocalizationServices lclService) {
/* 34 */     String message = "";
/* 35 */     if (lclService != null)
/* 36 */       message = lclService.getItem(-1003, 79, "UN") + " " + this.m_Residual; 
/* 37 */     return message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLocalizedMessage(ILocalizationServices lclService) {
/* 42 */     String message = "";
/* 43 */     switch (this.m_Type) {
/*    */       
/*    */       case 1:
/* 46 */         message = getLimitMessage(lclService);
/*    */         break;
/*    */     } 
/* 49 */     return message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLocalizedMessage() {
/* 54 */     return getLocalizedMessage(JLbsLocalizer.getLocalizationService());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\warning\LbsWarningData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */