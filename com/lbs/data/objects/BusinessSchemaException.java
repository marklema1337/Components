/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusinessSchemaException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public BusinessSchemaException(String message) {
/* 17 */     super(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public BusinessSchemaException(BusinessSchema schema, String message) {
/* 22 */     super((schema != null) ? (schema.getName() + " :\n" + message) : message);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessSchemaException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */