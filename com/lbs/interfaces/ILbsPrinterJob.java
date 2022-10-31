/*    */ package com.lbs.interfaces;
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
/*    */ public interface ILbsPrinterJob
/*    */ {
/*    */   boolean printDialog(Object paramObject);
/*    */   
/*    */   void print(Object paramObject, String paramString);
/*    */   
/*    */   default boolean isPrintAborted() {
/* 20 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsPrinterJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */