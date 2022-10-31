/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ILbsValidationError;
/*    */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*    */ import java.util.ArrayList;
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
/*    */ public class LbsValidationResult
/*    */   extends ArrayList<ILbsValidationError>
/*    */   implements ILbsValidationResult
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected boolean m_CanContinue;
/*    */   
/*    */   public LbsValidationResult(boolean canContinue) {
/* 26 */     this.m_CanContinue = canContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinue() {
/* 31 */     return this.m_CanContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanContinue(boolean canContinue) {
/* 36 */     this.m_CanContinue = canContinue;
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsValidationError getLastError() {
/* 41 */     if (size() > 0) {
/*    */       
/* 43 */       Object item = get(size() - 1);
/* 44 */       if (item instanceof ILbsValidationError)
/* 45 */         return (ILbsValidationError)item; 
/*    */     } 
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLastErrorMessage() {
/* 52 */     ILbsValidationError err = getLastError();
/* 53 */     if (err != null)
/* 54 */       return err.getMessage(); 
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 60 */     boolean equal = super.equals(o);
/* 61 */     if (equal) {
/*    */       
/* 63 */       if (!(o instanceof LbsValidationResult))
/* 64 */         return false; 
/* 65 */       if (this.m_CanContinue != ((LbsValidationResult)o).canContinue())
/* 66 */         return false; 
/*    */     } 
/* 68 */     return equal;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 74 */     return super.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\LbsValidationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */