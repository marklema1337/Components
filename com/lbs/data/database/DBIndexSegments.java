/*    */ package com.lbs.data.database;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DBIndexSegments
/*    */   extends DBEntityCollection
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public DBIndexSegments(DBIndex index) {
/* 19 */     super(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public DBIndexSegment item(int index) {
/* 24 */     return (DBIndexSegment)entity(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBIndexSegments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */