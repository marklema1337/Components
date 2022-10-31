/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ import com.lbs.controllers.LbsControllerException;
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
/*    */ public interface ILbsDefaultController
/*    */ {
/*    */   default Object getSelectedRowOfQueryGrid(int tag) throws LbsControllerException {
/* 18 */     return null;
/*    */   }
/*    */   
/*    */   default void clickDelete() throws LbsControllerException {}
/*    */   
/*    */   default void clickClose() throws LbsControllerException {}
/*    */   
/*    */   default void clickSave() throws LbsControllerException {}
/*    */   
/*    */   default void clickSend() throws LbsControllerException {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsDefaultController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */