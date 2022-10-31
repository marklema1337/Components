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
/*    */ public class DBIndexSegment
/*    */   extends DBEntityItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public DBIndexSegment(DBField field) {
/* 19 */     super(3);
/* 20 */     setName(field.getName());
/* 21 */     setPhysicalName(field.getPhysicalName());
/*    */   }
/*    */ 
/*    */   
/*    */   public DBField getField() {
/* 26 */     DBIndex index = (DBIndex)getCollectionOwner();
/* 27 */     if (index == null) {
/* 28 */       return null;
/*    */     }
/* 30 */     DBTable table = index.getTable();
/* 31 */     if (table == null) {
/* 32 */       return null;
/*    */     }
/* 34 */     return table.getFields().get(this.m_Name);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     return getField().toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBIndexSegment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */