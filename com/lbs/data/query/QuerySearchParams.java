/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import java.io.Externalizable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QuerySearchParams
/*    */   extends QueryFilterTerm
/*    */   implements Serializable, Cloneable, Externalizable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QuerySearchParams() {}
/*    */   
/*    */   public QuerySearchParams(ILbsCultureInfo culture, String keyText, String fieldName) {
/* 31 */     super(culture, keyText, fieldName);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 36 */     return super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QuerySearchParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */